package com.dcomet.health.dao.data;

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
@Table(name = "t_visit_patient")
public class VisitPlanData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VISP_VISIT_RID", updatable = false)
    private Integer id;

    @Column(name = "VISP_PAT_RID")
    private Integer vispPatRID;

    @Column(name = "VISP_ENTITY_RID")
    private Integer vispEntityRid;

    @Column(name = "VISP_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "VISP_CREATED_USER_DATETIME", updatable = false)
    private Calendar createdDateTime;

    @Column(name = "VISP_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "VISP_MODIFIED_USER_DATETIME")
    private Calendar modifiedDateTime;

    public VisitPlanData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVispPatRID() {
        return vispPatRID;
    }

    public void setVispPatRID(Integer vispPatRID) {
        this.vispPatRID = vispPatRID;
    }

    public Integer getVispEntityRid() {
        return vispEntityRid;
    }

    public void setVispEntityRid(Integer vispEntityRid) {
        this.vispEntityRid = vispEntityRid;
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
        sb.append("\n\tcplRID: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tvispPatRID: Integer=");
        sb.append(vispPatRID);
        sb.append(";");

        sb.append("\n\tvispEntityRid: Integer=");
        sb.append(vispEntityRid);
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
