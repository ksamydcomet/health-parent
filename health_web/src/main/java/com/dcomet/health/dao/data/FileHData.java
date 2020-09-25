
package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;

/**
 *
 * @author MI Faris
 */

@Entity
@Table(name = "t_file_h")
public class FileHData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "REF_RID", updatable = false)
    private Integer refRid;

    @Column(name = "REF_TYPE", updatable = false)
    private Integer refType;

    @Type(type = "com.dcomet.health.dao.data.XMLData")
    @Column(name = "CONTENT")
    private Map<String, Object> context = new HashMap<>();

    @Column(name = "CREATED_USER_RID")
    private Integer createdUserRid;

    @Column(name = "CREATED_USER_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;
    
    @Column(name = "FILE_ENTITY_RID")
    private Integer flhEntityRID;

    public FileHData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefRid() {
        return refRid;
    }

    public void setRefRid(Integer refRid) {
        this.refRid = refRid;
    }

    public Integer getRefType() {
        return refType;
    }

    public void setRefType(Integer refType) {
        this.refType = refType;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getFlhEntityRID() {
        return flhEntityRID;
    }

    public void setFlhEntityRID(Integer flhEntityRID) {
        this.flhEntityRID = flhEntityRID;
    }
    
    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\trefRid: Integer=");
        sb.append(refRid);
        sb.append(";");

        sb.append("\n\trefType: Integer=");
        sb.append(refType);
        sb.append(";");

        sb.append("\n\tcontext: Integer=");
        sb.append(context);
        sb.append(";");
        
        sb.append("\n\tflhEntityRID: Integer=");
        sb.append(flhEntityRID);
        sb.append(";");

        return sb.toString();
    }

}
