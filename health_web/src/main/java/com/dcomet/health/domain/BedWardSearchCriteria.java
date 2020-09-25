package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class BedWardSearchCriteria extends SearchCriteria {

    private Integer bwRid;
    private String bwCode;
    private String bwName;
    private Integer bwType;
    private Integer bwCurrentType;
    private Integer bwIsIcu;
    private Integer bwIsActive;
    private Integer bwUnitRid;
    private Integer bwEntityRid;
    private Integer bwCreatedUserRid;
    private String bwCreatedDatetime;
    private Integer bwModifiedUserRid;
    private String bwModifiedDatetime;


    public Integer getBwRid() {
        return bwRid;
    }

    public void setBwRid(Integer bwRid) {
        this.bwRid = bwRid;
    }

    public String getBwCode() {
        return bwCode;
    }

    public void setBwCode(String bwCode) {
        this.bwCode = bwCode;
    }

    public String getBwName() {
        return bwName;
    }

    public void setBwName(String bwName) {
        this.bwName = bwName;
    }

    public Integer getBwType() {
        return bwType;
    }

    public void setBwType(Integer bwType) {
        this.bwType = bwType;
    }

    public Integer getBwCurrentType() {
        return bwCurrentType;
    }

    public void setBwCurrentType(Integer bwCurrentType) {
        this.bwCurrentType = bwCurrentType;
    }

    public Integer getBwIsIcu() {
        return bwIsIcu;
    }

    public void setBwIsIcu(Integer bwIsIcu) {
        this.bwIsIcu = bwIsIcu;
    }

    public Integer getBwIsActive() {
        return bwIsActive;
    }

    public void setBwIsActive(Integer bwIsActive) {
        this.bwIsActive = bwIsActive;
    }

    public Integer getBwUnitRid() {
        return bwUnitRid;
    }

    public void setBwUnitRid(Integer bwUnitRid) {
        this.bwUnitRid = bwUnitRid;
    }

    public Integer getBwEntityRid() {
        return bwEntityRid;
    }

    public void setBwEntityRid(Integer bwEntityRid) {
        this.bwEntityRid = bwEntityRid;
    }

    public Integer getBwCreatedUserRid() {
        return bwCreatedUserRid;
    }

    public void setBwCreatedUserRid(Integer bwCreatedUserRid) {
        this.bwCreatedUserRid = bwCreatedUserRid;
    }

    public String getBwCreatedDatetime() {
        return bwCreatedDatetime;
    }

    public void setBwCreatedDatetime(String bwCreatedDatetime) {
        this.bwCreatedDatetime = bwCreatedDatetime;
    }

    public Integer getBwModifiedUserRid() {
        return bwModifiedUserRid;
    }

    public void setBwModifiedUserRid(Integer bwModifiedUserRid) {
        this.bwModifiedUserRid = bwModifiedUserRid;
    }

    public String getBwModifiedDatetime() {
        return bwModifiedDatetime;
    }

    public void setBwModifiedDatetime(String bwModifiedDatetime) {
        this.bwModifiedDatetime = bwModifiedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbwRid: Integer=");
        sb.append(bwRid);
        sb.append(";");

        sb.append("\n\tbwCode: String=");
        sb.append(bwCode);
        sb.append(";");

        sb.append("\n\tbwName: String=");
        sb.append(bwName);
        sb.append(";");

        sb.append("\n\tbwType: Integer=");
        sb.append(bwType);
        sb.append(";");

        sb.append("\n\tbwCurrentType: Integer=");
        sb.append(bwCurrentType);
        sb.append(";");

        sb.append("\n\tbwIsIcu: Integer=");
        sb.append(bwIsIcu);
        sb.append(";");

        sb.append("\n\tbwIsActive: Integer=");
        sb.append(bwIsActive);
        sb.append(";");

        sb.append("\n\tbwUnitRid: Integer=");
        sb.append(bwUnitRid);
        sb.append(";");

        sb.append("\n\tbwEntityRid: Integer=");
        sb.append(bwEntityRid);
        sb.append(";");

        sb.append("\n\tbwCreatedUserRid: Integer=");
        sb.append(bwCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbwCreatedDatetime: String=");
        sb.append(bwCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbwModifiedUserRid: Integer=");
        sb.append(bwModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbwModifiedDatetime: String=");
        sb.append(bwModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
