package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_complaints")
public class ComplaintsData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CPL_RID", updatable = false)
    private Integer id;

    @Column(name = "CPL_VISIT_RID", updatable = false)
    private Integer cplVisitRID;

    @Column(name = "CPL_PAT_RID", updatable = false)
    private Integer cplPatRID;

    @Column(name = "CPL_NAME")
    private String cplName;

    @Column(name = "CPL_NAME_INDEX")
    private Integer cplNameIndex;

    @Column(name = "CPL_DESC")
    private String cplDesc;

    @Column(name = "CPL_DESC_RANGE_INDEX")
    private Integer cplDescRangeIndex;

    @Column(name = "CPL_CURRENT_STATUS")
    private String cplCurrentStatus;

    @Column(name = "CPL_CURRENT_STATUS_INDEX")
    private Integer cplCurrentStatusIndex;

    @Column(name = "CPL_ENTITY_RID")
    private Integer cplEntityRid;

    @Column(name = "CPL_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "CPL_CREATED_USER_DATETIME", updatable = false)
    private Calendar createdDateTime;

    @Column(name = "CPL_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "CPL_MODIFIED_USER_DATETIME")
    private Calendar modifiedDateTime;

    public ComplaintsData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCplVisitRID() {
        return cplVisitRID;
    }

    public void setCplVisitRID(Integer cplVisitRID) {
        this.cplVisitRID = cplVisitRID;
    }

    public Integer getCplPatRID() {
        return cplPatRID;
    }

    public void setCplPatRID(Integer cplPatRID) {
        this.cplPatRID = cplPatRID;
    }

    public String getCplName() {
        return cplName;
    }

    public void setCplName(String cplName) {
        this.cplName = cplName;
    }

    public String getCplCurrentStatus() {
        return cplCurrentStatus;
    }

    public void setCplCurrentStatus(String cplCurrentStatus) {
        this.cplCurrentStatus = cplCurrentStatus;
    }

    public Integer getCplEntityRid() {
        return cplEntityRid;
    }

    public void setCplEntityRid(Integer cplEntityRid) {
        this.cplEntityRid = cplEntityRid;
    }

    public Integer getCplNameIndex() {
        return cplNameIndex;
    }

    public void setCplNameIndex(Integer cplNameIndex) {
        this.cplNameIndex = cplNameIndex;
    }

    public String getCplDesc() {
        return cplDesc;
    }

    public void setCplDesc(String cplDesc) {
        this.cplDesc = cplDesc;
    }

    public Integer getCplDescRangeIndex() {
        return cplDescRangeIndex;
    }

    public void setCplDescRangeIndex(Integer cplDescRangeIndex) {
        this.cplDescRangeIndex = cplDescRangeIndex;
    }

    public Integer getCplCurrentStatusIndex() {
        return cplCurrentStatusIndex;
    }

    public void setCplCurrentStatusIndex(Integer cplCurrentStatusIndex) {
        this.cplCurrentStatusIndex = cplCurrentStatusIndex;
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

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tcplVisitRID: Integer=");
        sb.append(cplVisitRID);
        sb.append(";");

        sb.append("\n\tcplPatRID: Integer=");
        sb.append(cplPatRID);
        sb.append(";");

        sb.append("\n\tcplName: String=");
        sb.append(cplName);
        sb.append(";");

        sb.append("\n\tcplNameIndex: Integer=");
        sb.append(cplNameIndex);
        sb.append(";");

        sb.append("\n\tcplDesc: String=");
        sb.append(cplDesc);
        sb.append(";");

        sb.append("\n\tcplDescRangeIndex: Integer=");
        sb.append(cplDescRangeIndex);
        sb.append(";");

        sb.append("\n\tcplCurrentStatus: String=");
        sb.append(cplCurrentStatus);
        sb.append(";");

        sb.append("\n\tcplCurrentStatusIndex: Integer=");
        sb.append(cplCurrentStatusIndex);
        sb.append(";");

        sb.append("\n\tcplEntityRid: Integer=");
        sb.append(cplEntityRid);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
