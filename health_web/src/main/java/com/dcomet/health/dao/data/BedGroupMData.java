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
import javax.persistence.Temporal;

@Entity
@Table(name = "t_bed_group_master")
public class BedGroupMData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BGM_RID", updatable = false)
    private Integer bgmRid;

    @Column(name = "BGM_BED_GROUP_NAME")
    private String bgmBedGroupName;

    @Column(name = "BGM_BED_UNIT_RID")
    private Integer bgmBedUnitRid;

    @Column(name = "BGM_BED_SERVICE_POINT_RID")
    private Integer bgmBedServicePointRid;

    @Column(name = "BGM_BED_GROUP_INDEX")
    private Integer bgmBedGroupIndex;

    @Column(name = "BGM_BED_SUB_GROUP_INDEX")
    private Integer bgmBedSubGroupIndex;

    @Column(name = "BGM_BED_PRICE_TYPE_INDEX")
    private Integer bgmBedPriceTypeIndex;

    @Column(name = "BGM_BED_ENTITY_RID")
    private Integer bgmBedEntityRid;

    @Column(name = "BGM_BED_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "BGM_BED_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "BGM_BED_MODIFIED_USER_RID", updatable = false)
    private Integer modifiedUserRid;

    @Column(name = "BGM_BED_MODIFIED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public Integer getBgmRid() {
        return bgmRid;
    }

    public void setBgmRid(Integer bgmRid) {
        this.bgmRid = bgmRid;
    }

    public String getBgmBedGroupName() {
        return bgmBedGroupName;
    }

    public void setBgmBedGroupName(String bgmBedGroupName) {
        this.bgmBedGroupName = bgmBedGroupName;
    }

    public Integer getBgmBedUnitRid() {
        return bgmBedUnitRid;
    }

    public void setBgmBedUnitRid(Integer bgmBedUnitRid) {
        this.bgmBedUnitRid = bgmBedUnitRid;
    }

    public Integer getBgmBedServicePointRid() {
        return bgmBedServicePointRid;
    }

    public void setBgmBedServicePointRid(Integer bgmBedServicePointRid) {
        this.bgmBedServicePointRid = bgmBedServicePointRid;
    }

    public Integer getBgmBedGroupIndex() {
        return bgmBedGroupIndex;
    }

    public void setBgmBedGroupIndex(Integer bgmBedGroupIndex) {
        this.bgmBedGroupIndex = bgmBedGroupIndex;
    }

    public Integer getBgmBedSubGroupIndex() {
        return bgmBedSubGroupIndex;
    }

    public void setBgmBedSubGroupIndex(Integer bgmBedSubGroupIndex) {
        this.bgmBedSubGroupIndex = bgmBedSubGroupIndex;
    }

    public Integer getBgmBedPriceTypeIndex() {
        return bgmBedPriceTypeIndex;
    }

    public void setBgmBedPriceTypeIndex(Integer bgmBedPriceTypeIndex) {
        this.bgmBedPriceTypeIndex = bgmBedPriceTypeIndex;
    }

    public Integer getBgmBedEntityRid() {
        return bgmBedEntityRid;
    }

    public void setBgmBedEntityRid(Integer bgmBedEntityRid) {
        this.bgmBedEntityRid = bgmBedEntityRid;
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

        sb.append("\n\tbgmRid: Integer=");
        sb.append(bgmRid);
        sb.append(";");

        sb.append("\n\tbgmBedGroupName: String=");
        sb.append(bgmBedGroupName);
        sb.append(";");

        sb.append("\n\tbgmBedUnitRid: Integer=");
        sb.append(bgmBedUnitRid);
        sb.append(";");

        sb.append("\n\tbgmBedServicePointRid: Integer=");
        sb.append(bgmBedServicePointRid);
        sb.append(";");

        sb.append("\n\tbgmBedGroupIndex: Integer=");
        sb.append(bgmBedGroupIndex);
        sb.append(";");

        sb.append("\n\tbgmBedSubGroupIndex: Integer=");
        sb.append(bgmBedSubGroupIndex);
        sb.append(";");

        sb.append("\n\tbgmBedPriceTypeIndex: Integer=");
        sb.append(bgmBedPriceTypeIndex);
        sb.append(";");

        sb.append("\n\tbtmBedEntityRid: Integer=");
        sb.append(bgmBedEntityRid);
        sb.append(";");

        sb.append("\n\tcreatedUserRids: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
