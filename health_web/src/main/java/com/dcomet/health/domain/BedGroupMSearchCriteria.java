package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class BedGroupMSearchCriteria extends SearchCriteria {

    private Integer bgmRid;
    private String bgmBedGroupName;
    private Integer bgmBedUnitRid;
    private Integer bgmBedServicePointRid;
    private Integer bgmBedGroupIndex;
    private Integer bgmBedSubGroupIndex;
    private Integer bgmBedPriceTypeIndex;
    private Integer bgmBedEntityRid;

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

        return sb.toString();
    }
}
