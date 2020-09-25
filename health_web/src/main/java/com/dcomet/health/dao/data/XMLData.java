
package com.dcomet.health.dao.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.hibernate.HibernateException;
import org.hibernate.type.BlobType;
import org.hibernate.usertype.UserType;

/**
 *
 * @author MI Faris
 */
public class XMLData implements UserType, Serializable {

    private static final long serialVersionUID = -2572755030090473059L;

    public static final BlobType BLOB = new BlobType();

    private static final int[] SQL_TYPES = new int[]{2007};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class<?> returnedClass() {
        return Map.class;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        final Map mapx = (Map) x;
        final Map mapy = (Map) y;
        return mapx.equals(mapy);
    }

    @Override
    public int hashCode(final Object object) throws HibernateException {
        return object.hashCode();
    }

    @Override
    public Object nullSafeGet(final ResultSet resultSet, final String[] columnNames, final Object object) throws HibernateException, SQLException {
        Map<String, Object> retValue = null;
        String blobAsString = null;
        final StringBuffer buffer = new StringBuffer();
        // First we get the stream
        InputStream reader = null;
        try {
            final boolean isSqlXml = checkIfSqlXmlType(resultSet.getMetaData(), columnNames[0]);
//            System.out.println("-----------------------:" + isSqlXml);
            if (isSqlXml) {
                final SQLXML xmlType = resultSet.getSQLXML(columnNames[0]);
                if (xmlType != null) {
                    reader = xmlType.getBinaryStream();
                }
            } else {
                final Blob blob = resultSet.getBlob(columnNames[0]);
                if (blob != null) {
                    reader = blob.getBinaryStream();
                }
            }

            if (reader != null) {
//                final BufferedInputStream br = new BufferedInputStream(reader);
//                int read = br.read();
//                while (read != 0) {
//                    buffer.append(read + '\n');
//                    read = br.read();
//                }
//                br.close();
                blobAsString = IOUtils.toString(reader);
            }

            if (blobAsString != null) {

                final org.dom4j.Document document = DocumentHelper.parseText(blobAsString);
                if (document.getRootElement().nodeCount() < 1) {
                    return Maps.newHashMap();
                }

                final JsonObject jsonObject = DocumentUtils.convertXmlToJsonObject(document);
                retValue = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
            }
        } catch (final IOException | DocumentException ioe) {
            throw new HibernateException("Unable to retrieve BLOB from database:" + ioe.getMessage(), ioe);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return retValue;
    }

    private boolean checkIfSqlXmlType(final ResultSetMetaData metaData, final String srcColumnName) throws SQLException {
        if (metaData != null) {
            int typeColumnIndex = -1;
            final int numCols = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= numCols; columnIndex++) {
                final String columnName = metaData.getColumnName(columnIndex);
                if (columnName == null || srcColumnName.equals(columnName)) {
                    typeColumnIndex = columnIndex;
                    final Integer columnType = metaData.getColumnType(typeColumnIndex);
                    if (columnType == Types.SQLXML) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index) throws HibernateException, SQLException {

        final boolean isSqlXml = false;//preparedStatement.isWrapperFor(OraclePreparedStatement.class);
        if (value == null) {
            preparedStatement.setNull(index, isSqlXml ? Types.VARCHAR : Types.BLOB);
        } else {
            final StringWriter writer = new StringWriter();
            try {
                new ObjectMapper().writeValue(writer, value);
            } catch (final JsonGenerationException | JsonMappingException e) {
                throw new HibernateException("Unable to set CLOB in database:" + e.getMessage(), e);
            } catch (final IOException e) {
                throw new HibernateException("Unable to set CLOB in database:" + e.getMessage(), e);
            }

            final String xmlString = DocumentUtils.convertJsonObjectToXml(new JsonParser().parse(writer.toString()).getAsJsonObject());
//            System.out.println(xmlString);

            if (isSqlXml) {
                final Connection con = preparedStatement.getConnection();
                final SQLXML xmlVal = con.createSQLXML();
                xmlVal.setString(xmlString);
                preparedStatement.setSQLXML(index, xmlVal);

            } else {
//                final Reader reader = new BStringReader(xmlString);
                InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
                preparedStatement.setBlob(index, stream);
            }
        }
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        if (value != null) {
            final Map<String, Object> map = (Map<String, Object>) value;
            return Maps.newHashMap(map);
        }
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) this.deepCopy(value);
    }

    @Override
    public Object assemble(final Serializable cached, final Object value) throws HibernateException {
        return this.deepCopy(value);
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return this.deepCopy(original);
    }
}
