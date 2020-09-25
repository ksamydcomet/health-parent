
package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_patient_image")
public class PatientImageData implements Serializable,Auditable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAT_IMG_ID", updatable = false)
    private Integer id;

    @Column(name = "PAT_IMG_PAT_RID")
    private Integer patImgPatRid;
    
    @Lob
    @Column(name = "PAT_IMG_PHOTO")
    private byte[] patImgPhoto;
    
    @Column(name = "PAT_IMG_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "PAT_IMG_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "PAT_IMG_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "PAT_IMG_MODIFIED_USER_RID")
    private Integer modifiedUserRid;
    
    @Column(name = "PAT_IMG_ENTITY_RID")
    private Integer patImgEntityRid;

    public PatientImageData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatImgPatRid() {
        return patImgPatRid;
    }

    public void setPatImgPatRid(Integer patImgPatRid) {
        this.patImgPatRid = patImgPatRid;
    }

    public byte[] getPatImgPhoto() {
        return patImgPhoto;
    }

    public void setPatImgPhoto(byte[] patImgPhoto) {
        this.patImgPhoto = patImgPhoto;
    }

    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public Integer getPatImgEntityRid() {
        return patImgEntityRid;
    }

    public void setPatImgEntityRid(Integer patImgEntityRid) {
        this.patImgEntityRid = patImgEntityRid;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tpatImgPatRid: Integer=");
        sb.append(patImgPatRid);
        sb.append(";");

        sb.append("\n\tpatImgPhoto: Byte=");
        sb.append(patImgPhoto);
        sb.append(";");

        sb.append("\n\tpatImgEntityRid: Integer=");
        sb.append(patImgEntityRid);
        sb.append(";");

        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
    
}
