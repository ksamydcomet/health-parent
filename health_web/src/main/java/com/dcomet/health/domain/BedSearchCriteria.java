package com.dcomet.health.domain;

public class BedSearchCriteria {

    private Integer bedRid;
    private String benNo;
    private Integer bedOccupiedPatRid;
    private Integer bedTypeDdIndex;
    private Integer bedWardRid;
    private Integer bedStatus;
    private Integer bedIsActive;
    private Integer bedEntityRid;
    private Integer bedCreatedUserRid;
    private String bedCreatedDatetime;
    private Integer bedModifiedUserRid;
    private String bedModifiedDatetime;

    public Integer getBedRid() {
        return bedRid;
    }

    public void setBedRid(Integer bedRid) {
        this.bedRid = bedRid;
    }

    public String getBenNo() {
        return benNo;
    }

    public void setBenNo(String benNo) {
        this.benNo = benNo;
    }

    public Integer getBedOccupiedPatRid() {
        return bedOccupiedPatRid;
    }

    public void setBedOccupiedPatRid(Integer bedOccupiedPatRid) {
        this.bedOccupiedPatRid = bedOccupiedPatRid;
    }

    public Integer getBedTypeDdIndex() {
        return bedTypeDdIndex;
    }

    public void setBedTypeDdIndex(Integer bedTypeDdIndex) {
        this.bedTypeDdIndex = bedTypeDdIndex;
    }

    public Integer getBedWardRid() {
        return bedWardRid;
    }

    public void setBedWardRid(Integer bedWardRid) {
        this.bedWardRid = bedWardRid;
    }

    public Integer getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(Integer bedStatus) {
        this.bedStatus = bedStatus;
    }

    public Integer getBedIsActive() {
        return bedIsActive;
    }

    public void setBedIsActive(Integer bedIsActive) {
        this.bedIsActive = bedIsActive;
    }

    public Integer getBedEntityRid() {
        return bedEntityRid;
    }

    public void setBedEntityRid(Integer bedEntityRid) {
        this.bedEntityRid = bedEntityRid;
    }

    public Integer getBedCreatedUserRid() {
        return bedCreatedUserRid;
    }

    public void setBedCreatedUserRid(Integer bedCreatedUserRid) {
        this.bedCreatedUserRid = bedCreatedUserRid;
    }

    public String getBedCreatedDatetime() {
        return bedCreatedDatetime;
    }

    public void setBedCreatedDatetime(String bedCreatedDatetime) {
        this.bedCreatedDatetime = bedCreatedDatetime;
    }

    public Integer getBedModifiedUserRid() {
        return bedModifiedUserRid;
    }

    public void setBedModifiedUserRid(Integer bedModifiedUserRid) {
        this.bedModifiedUserRid = bedModifiedUserRid;
    }

    public String getBedModifiedDatetime() {
        return bedModifiedDatetime;
    }

    public void setBedModifiedDatetime(String bedModifiedDatetime) {
        this.bedModifiedDatetime = bedModifiedDatetime;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbedRid: Integer=");
        sb.append(bedRid);
        sb.append(";");

        sb.append("\n\tbenNo: String=");
        sb.append(benNo);
        sb.append(";");

        sb.append("\n\tbedOccupiedPatRid: Integer=");
        sb.append(bedOccupiedPatRid);
        sb.append(";");

        sb.append("\n\tbedTypeDdIndex: Integer=");
        sb.append(bedTypeDdIndex);
        sb.append(";");

        sb.append("\n\tbedWardRid: Integer=");
        sb.append(bedWardRid);
        sb.append(";");

        sb.append("\n\tbedStatus: Integer=");
        sb.append(bedStatus);
        sb.append(";");

        sb.append("\n\tbedIsActive: Integer=");
        sb.append(bedIsActive);
        sb.append(";");

        sb.append("\n\tbedEntityRid: Integer=");
        sb.append(bedEntityRid);
        sb.append(";");

        sb.append("\n\tbedCreatedUserRid: Integer=");
        sb.append(bedCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbedCreatedDatetime: String=");
        sb.append(bedCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbedModifiedUserRid: Integer=");
        sb.append(bedModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbedModifiedDatetime: String=");
        sb.append(bedModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
