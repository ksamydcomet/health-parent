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
@Table(name = "t_visit_vitals")
public class VisitVitalsData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vit_rid", updatable = false)
    private Integer id;

    @Column(name = "VIT_VIS_RID", updatable = false)
    private Integer vitVisRID;

    @Column(name = "VIT_PAT_RID", updatable = false)
    private Integer vitPatRID;

    @Column(name = "VIT_NAME")
    private String vitName;

    @Column(name = "VIT_DDICT_INDEX")
    private Integer vitDdictIndex;

    @Column(name = "VIT_UOM")
    private String vitUom;

    @Column(name = "VIT_VALUE")
    private String vitValue;

    @Column(name = "VIT_ENTITY_RID")
    private Integer vitEntityRid;

    @Column(name = "VIT_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "VIT_CREATED_USER_DATETIME", updatable = false)
    private Calendar createdDateTime;

    @Column(name = "VIT_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "VIT_MODIFIED_USER_DATETIME")
    private Calendar modifiedDateTime;

    public VisitVitalsData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVitVisRID() {
        return vitVisRID;
    }

    public void setVitVisRID(Integer vitVisRID) {
        this.vitVisRID = vitVisRID;
    }

    public Integer getVitPatRID() {
        return vitPatRID;
    }

    public void setVitPatRID(Integer vitPatRID) {
        this.vitPatRID = vitPatRID;
    }

    public String getVitName() {
        return vitName;
    }

    public void setVitName(String vitName) {
        this.vitName = vitName;
    }

    public Integer getVitDdictIndex() {
        return vitDdictIndex;
    }

    public void setVitDdictIndex(Integer vitDdictIndex) {
        this.vitDdictIndex = vitDdictIndex;
    }

    public String getVitUom() {
        return vitUom;
    }

    public void setVitUom(String vitUom) {
        this.vitUom = vitUom;
    }

    public String getVitValue() {
        return vitValue;
    }

    public void setVitValue(String vitValue) {
        this.vitValue = vitValue;
    }

    public Integer getVitEntityRid() {
        return vitEntityRid;
    }

    public void setVitEntityRid(Integer vitEntityRid) {
        this.vitEntityRid = vitEntityRid;
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

        sb.append("\n\tvitVisRID: Integer=");
        sb.append(vitVisRID);
        sb.append(";");

        sb.append("\n\tvitPatRID: Integer=");
        sb.append(vitPatRID);
        sb.append(";");

        sb.append("\n\tvitName: String=");
        sb.append(vitName);
        sb.append(";");

        sb.append("\n\tvitDdictIndex: String=");
        sb.append(vitDdictIndex);
        sb.append(";");

        sb.append("\n\tvitUom: String=");
        sb.append(vitUom);
        sb.append(";");

        sb.append("\n\tvitValue: String=");
        sb.append(vitValue);
        sb.append(";");
        
        sb.append("\n\tvitEntityRid: Integer=");
        sb.append(vitEntityRid);
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
