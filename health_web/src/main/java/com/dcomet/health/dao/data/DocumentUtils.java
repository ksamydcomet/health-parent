package com.dcomet.health.dao.data;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentUtils {

    /**
     * Private constructor since this is a utilities class with static methods.
     * There is no need to create an instance of DocumentUtils.
     */
    private DocumentUtils() {
    }

    /**
     * Filters the values from the JsonObject based on the provided mask
     * JsonObject. See the DocumentManager.get method of more details.
     *
     * @param srcObject JsonObject to mask
     * @param mask Mask to apply to JsonObject
     * @return The masked JsonObject
     */
    public static JsonObject maskJsonObject(final JsonObject srcObject, final JsonObject mask) {
        if (mask == null) {
            return srcObject;
        } else {
            final JsonObject maskedObject = new JsonObject();
            for (final Entry<String, JsonElement> entry : srcObject.entrySet()) {
                final String srcKey = entry.getKey();
                if (mask.has(srcKey)) {
                    final JsonElement srcValue = entry.getValue();
                    final JsonElement maskValue = mask.get(srcKey);
                    if (maskValue instanceof JsonObject && srcValue instanceof JsonObject) {
                        maskedObject.add(srcKey, maskJsonObject((JsonObject) srcValue, (JsonObject) maskValue));
                    } else if (maskValue instanceof JsonObject && srcValue instanceof JsonArray) {
                        final JsonArray jsonArray = srcValue.getAsJsonArray();
                        if (jsonArray.size() > 0) {
                            final JsonArray destArray = new JsonArray();
                            for (JsonElement jsonElement : jsonArray) {
                                if (jsonElement.isJsonObject()) {
                                    destArray.add(maskJsonObject((JsonObject) jsonElement, (JsonObject) maskValue));
                                }
                            }
                            maskedObject.add(srcKey, destArray);
                        }
                    } else {
                        maskedObject.add(srcKey, srcValue);
                    }
                }
            }
            return maskedObject;
        }
    }

    public static JsonObject applyJsonObject(final JsonObject baseObject, final JsonObject mergedObject) {
        if (mergedObject != null) {
            final List<String> keysToKeep = new LinkedList<>();
            for (final Entry<String, JsonElement> entry : mergedObject.entrySet()) {
                final String srcKey = entry.getKey();
                final JsonElement mergedValue = entry.getValue();
                if (baseObject.has(srcKey)) {
                    JsonElement baseValue = baseObject.get(srcKey);
                    if (baseValue instanceof JsonObject && mergedValue instanceof JsonObject) {
                        applyJsonObject(baseValue.getAsJsonObject(), mergedValue.getAsJsonObject());
                    } else {
                        baseObject.add(srcKey, mergedValue);
                    }
                } else {
                    baseObject.add(srcKey, mergedValue);
                }
            }
        }
        return baseObject;
    }

    private static HashMap<String, JsonElement> getUidMap(JsonArray jsonArray) {
        HashMap<String, JsonElement> uidMap = new HashMap<String, JsonElement>();
        int i = 0;
        for (JsonElement jsonElement : jsonArray) {
            String uid = Integer.toString(i);
            if (jsonElement.isJsonObject()) {
                JsonElement uidElem = jsonElement.getAsJsonObject().get("@uid");
                if (uidElem != null) {
                    uid = uidElem.getAsString();
                }
            }
            uidMap.put(uid, jsonElement);
            i++;
        }
        return uidMap;
    }

    /**
     * Converts the JsonObject to XML.
     *
     * @param jsonObject JsonObject to convert
     * @return XML representation of the JsonObject
     */
    public static String convertJsonObjectToXml(final JsonObject jsonObject) {
        final org.dom4j.Document doc = DocumentHelper.createDocument();
        doc.add(convertJsonObjectToDom4JElement(jsonObject));
        return doc.asXML();
    }

    public static Element convertJsonObjectToDom4JElement(final JsonObject jsonObject) {
        final org.dom4j.Document doc = DocumentHelper.createDocument();
        final Element rootElement = doc.addElement("data");
        insertChildren(rootElement, null, jsonObject);
        rootElement.detach();
        return rootElement;
    }

    private static Element insertChildren(final Element parent, final String parentName, final JsonElement bean) {
        if (bean == null) {
            return parent;
        }
        if (bean instanceof JsonObject) {
            final JsonObject jObject = (JsonObject) bean;
            for (final Entry<String, JsonElement> entry : jObject.entrySet()) {
                final String key = entry.getKey();
                if (!isXmlValid(key)) {
                    continue;
                }
                final JsonElement value = jObject.get(key);
                if (key.startsWith("@") && value != null) {
                    parent.addAttribute(key.substring(1), value.getAsString());
                } else if (value != null) {
                    if (value.isJsonArray()) {
                        insertChildren(parent, key, value);
                    } else {
                        final Element child = parent.addElement(key);
                        if (value.isJsonObject()) {
                            JsonElement uid = value.getAsJsonObject().get("@uid");
                            if (uid != null) {
                                child.addAttribute("uid", uid.getAsString());
                            }
                        }
                        insertChildren(child, key, value);
                    }
                }
            }
        } else if (bean instanceof JsonArray) {
            final JsonArray jArray = (JsonArray) bean;
            int i = 0;
            for (final JsonElement jsonElement : jArray) {
                final Element arrayElement = parent.addElement(parentName);
                if (jsonElement.isJsonObject()) {
                    final JsonObject jsonObject = jsonElement.getAsJsonObject();
                    JsonElement uid = jsonObject.get("@uid");
                    if (uid == null) {
                        uid = new JsonPrimitive(UUID.randomUUID().toString());
                        jsonObject.add("@uid", uid);
                    }
                    arrayElement.addAttribute("uid", uid.getAsString());
                } else {
                    arrayElement.addAttribute("index", Integer.toString(i++));
                }
                insertChildren(arrayElement, parentName, jsonElement);
            }
        } else if (bean instanceof JsonPrimitive) {
            parent.addText(((JsonPrimitive) bean).getAsString());
        } else if (bean instanceof JsonNull) {
            // Do NOthing
        }
        return parent;
    }

    private static boolean isXmlValid(final String key) {
        final String expression = "^_?(?!(xml|[_\\d\\W]))([\\w.-]+)$";
        final CharSequence inputStr = key;
        final Pattern pattern = Pattern.compile(expression);
        final Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    /**
     * Parses the provided XML string and turns it into a JsonObject
     *
     * @param xmlString XML string representation to convert to JsonObject
     * @return JsonObject represenation
     * @throws DocumentException Thrown if the XML string cannot be parsed.
     */
    public static JsonObject convertXmlToJsonObject(final String xmlString) throws DocumentException {
        final org.dom4j.Document document = DocumentHelper.parseText(xmlString);
        return convertXmlToJsonObject(document);
    }

    /**
     * Parses the provided XML string and turns it into a JsonObject
     *
     * @param document The DOM4J document to convert to a JsonObject
     * @return JsonObject representation of the DOM4J document
     * @throws DocumentException
     */
    public static JsonObject convertXmlToJsonObject(final org.dom4j.Document document) throws DocumentException {
        final Element root = document.getRootElement();
        return (JsonObject) convertToJsonObjectOrString(root);
    }

    public static JsonElement convertToJsonObjectOrString(final Element elem) {
        JsonElement jsonElement = null;
        if (elem.elements().size() > 0) {
            final JsonObject bean = new JsonObject();
            for (final Object elementObj : elem.elements()) {
                if (elementObj instanceof Element) {
                    final Element element = (Element) elementObj;
                    final String name = element.getName();
                    final JsonElement value = convertToJsonObjectOrString(element);

                    JsonElement newBeanValue = value;
                    JsonElement currentBeanValue = bean.get(name);
                    if (currentBeanValue != null) {
                        if (currentBeanValue.isJsonArray()) {
                            currentBeanValue.getAsJsonArray().add(value);
                            newBeanValue = currentBeanValue;
                        } else {
                            JsonArray newBeanValueArray = new JsonArray();
                            newBeanValueArray.add(currentBeanValue);
                            newBeanValueArray.add(value);
                            newBeanValue = newBeanValueArray;
                        }
                    }
                    bean.add(name, newBeanValue);
                }
            }

            if (elem.attributes().size() > 0) {
                for (final Object attributeObj : elem.attributes()) {
                    final Attribute attribute = (Attribute) attributeObj;
                    final String name = attribute.getName();
                    final String value = attribute.getValue();
                    bean.addProperty("@" + name, value);
                }
            }
            jsonElement = bean;
        } else {
            String elementText = elem.getText();
            if (elementText == null || elementText.trim().equals("")) {
                jsonElement = new JsonNull();
            } else {
                jsonElement = new JsonPrimitive(elem.getText());
            }
        }

        return jsonElement;
    }

    /**
     * Method used to create a Gson instance that works well with the Document
     * Manager.
     *
     * Gson does not serialize JsonElement's very well. This method creates a
     * Gson instance that handles JsonElement serialization. This Gson instance
     * also honors the XmlTransient attribute.
     *
     * @return Gson instance
     */
    public static Gson createGsonInstance() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(JsonElement.class, new JsonElementTypeAdapter());
        gsonBuilder.registerTypeAdapter(JsonObject.class, new JsonObjectTypeAdapter());
        gsonBuilder.registerTypeAdapter(JsonArray.class, new JsonArrayTypeAdapter());
        gsonBuilder.registerTypeAdapter(JsonPrimitive.class, new JsonPrimitiveTypeAdapter());
        gsonBuilder.registerTypeAdapter(JsonNull.class, new JsonNullTypeAdapter());
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.getAnnotation(XmlTransient.class) != null;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(XmlTransient.class) != null;
            }
        });
        Gson gson = gsonBuilder.create();
        return gson;
    }

    private static class JsonElementTypeAdapter implements JsonSerializer<JsonElement>, JsonDeserializer<JsonElement> {

        @Override
        public JsonElement serialize(JsonElement src, Type typeOfSrc,
                JsonSerializationContext context) {
            return src;
        }

        @Override
        public JsonElement deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return json;
        }

    }

    private static class JsonObjectTypeAdapter implements JsonSerializer<JsonObject>, JsonDeserializer<JsonObject> {

        @Override
        public JsonElement serialize(JsonObject src, Type typeOfSrc,
                JsonSerializationContext context) {
            return src;
        }

        @Override
        public JsonObject deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : json.getAsJsonObject();
        }
    }

    private static class JsonArrayTypeAdapter implements JsonSerializer<JsonArray>, JsonDeserializer<JsonArray> {

        @Override
        public JsonElement serialize(JsonArray src, Type typeOfSrc,
                JsonSerializationContext context) {
            return src;
        }

        @Override
        public JsonArray deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : json.getAsJsonArray();
        }
    }

    private static class JsonPrimitiveTypeAdapter implements JsonSerializer<JsonPrimitive>, JsonDeserializer<JsonPrimitive> {

        @Override
        public JsonElement serialize(JsonPrimitive src, Type typeOfSrc,
                JsonSerializationContext context) {
            return src;
        }

        @Override
        public JsonPrimitive deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : json.getAsJsonPrimitive();
        }
    }

    private static class JsonNullTypeAdapter implements JsonSerializer<JsonNull>, JsonDeserializer<JsonNull> {

        @Override
        public JsonElement serialize(JsonNull src, Type typeOfSrc,
                JsonSerializationContext context) {
            return src;
        }

        @Override
        public JsonNull deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : json.getAsJsonNull();
        }
    }

}
