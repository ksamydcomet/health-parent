package com.dcomet.health.web.excel;

import com.dcomet.fw.excel.ExcelColumn;
import com.dcomet.fw.excel.Excelable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Provider
@Component
@Produces({"application/vnd.ms-excel"})
public class ExcelProvider implements MessageBodyWriter<Collection<?>> {

    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExcelProvider.class);

    public Workbook convertAssignmentsToWorkbook(Collection<?> excelables, Class<?> collectionClass) {
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet("Results");

        int rowCount = 0;
        Row titleRow = sheet.createRow(rowCount++);
        int fieldCount = 0;
        if (collectionClass != null) {
            fieldCount = extractColumnHeaders(titleRow, fieldCount, collectionClass, "");
            Field[] fields = collectionClass.getDeclaredFields();
            for (Object excelable : excelables) {
                Row row = sheet.createRow(rowCount++);
                convertToColumns(fields, 0, excelable, row);
            }
        }
        for (int i = 0; i < fieldCount; i++) {
            sheet.autoSizeColumn(i);
        }
        return wb;
    }

    protected int extractColumnHeaders(Row titleRow, int fieldCount, Class<?> clazz, String columnNamePrefix) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelColumn excelColumnDef = field.getAnnotation(ExcelColumn.class);
            if (excelColumnDef != null) {
                Class<?> fieldClass = field.getType();
                String columnName = excelColumnDef.name();
                columnName = columnNamePrefix + (columnName == null ? field.getName() : columnName);
                if (fieldClass.getAnnotation(Excelable.class) != null) {
                    fieldCount = extractColumnHeaders(titleRow, fieldCount, fieldClass, columnName + " ");
                } else {
                    Cell cell = titleRow.createCell(fieldCount++);
                    columnName = columnName == null ? field.getName() : columnName;
                    cell.setCellValue(columnName);
                }
            }
        }
        return fieldCount;
    }

    protected int convertToColumns(Field[] fields, int fieldCount, Object object, Row row) {
        for (Field field : fields) {
            ExcelColumn excelColumnDef = field.getAnnotation(ExcelColumn.class);
            if (excelColumnDef != null) {
                Object obj = null;
                try {
                    Class<?> fieldClass = field.getType();
                    obj = object == null ? null : PropertyUtils.getProperty(object, field.getName());
                    if (fieldClass.getAnnotation(Excelable.class) != null) {
                        fieldCount = this.convertToColumns(fieldClass.getDeclaredFields(), fieldCount, obj, row);
                    } else {
                        Cell cell = row.createCell(fieldCount++);
                        if (obj != null) {
                            if (obj instanceof Number) {
                                cell.setCellValue(((Number) obj).doubleValue());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            } else {
                                cell.setCellValue(obj.toString());
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.trace("IllegalArgumentException: ", e);
                } catch (IllegalAccessException e) {
                    logger.trace("IllegalAccessException: ", e);
                } catch (InvocationTargetException e) {
                    logger.trace("InvocationTargetException: ", e);
                } catch (NoSuchMethodException e) {
                    logger.trace("NoSuchMethodException: ", e);
                }
            }
        }
        return fieldCount;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if ("application/vnd.ms-excel".equals(mediaType == null ? null : mediaType.toString())) {
            return true;
        }
        if (Collection.class.isAssignableFrom(type) && genericType instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
            if (types != null && types.length > 0) {
                for (Type t : types) {
                    if (t instanceof Class && ((Class<?>) t).isAnnotationPresent(Excelable.class)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public long getSize(Collection<?> excelables, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Collection<?> excelables, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {
        Class<?> collectionClass = null;
        Type[] types = genericType instanceof ParameterizedType ? ((ParameterizedType) genericType).getActualTypeArguments() : null;
        if (types != null && types.length > 0) {
            for (Type t : types) {
                if (t instanceof Class && ((Class<?>) t).isAnnotationPresent(Excelable.class)) {
                    collectionClass = (Class<?>) t;
                }
            }
        }
        if (collectionClass == null) {
            if (excelables != null && excelables.size() > 0) {
                collectionClass = excelables.iterator().next().getClass();
            }
        }
        Workbook wb = this.convertAssignmentsToWorkbook(excelables, collectionClass);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        entityStream.write(baos.toByteArray());
    }

}
