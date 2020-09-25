package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class BedTypeOccupancySummarySearchCriteria extends SearchCriteria {

    private Integer btosRid;
    private String btosDate;
    private Integer btosBedTypeRid;
    private Integer btosWardRid;
    private Integer btosUnitRid;
    private Integer btosEntRid;
    private Integer btosOccupiedBedCount;
    private Integer btosTotalBedCount;
    private Integer btosUserRid;
    private String btosSessionId;

    public Integer getBtosRid() {
        return btosRid;
    }

    public void setBtosRid(Integer btosRid) {
        this.btosRid = btosRid;
    }

    public String getBtosDate() {
        return btosDate;
    }

    public void setBtosDate(String btosDate) {
        this.btosDate = btosDate;
    }

    public Integer getBtosBedTypeRid() {
        return btosBedTypeRid;
    }

    public void setBtosBedTypeRid(Integer btosBedTypeRid) {
        this.btosBedTypeRid = btosBedTypeRid;
    }

    public Integer getBtosWardRid() {
        return btosWardRid;
    }

    public void setBtosWardRid(Integer btosWardRid) {
        this.btosWardRid = btosWardRid;
    }

    public Integer getBtosUnitRid() {
        return btosUnitRid;
    }

    public void setBtosUnitRid(Integer btosUnitRid) {
        this.btosUnitRid = btosUnitRid;
    }

    public Integer getBtosEntRid() {
        return btosEntRid;
    }

    public void setBtosEntRid(Integer btosEntRid) {
        this.btosEntRid = btosEntRid;
    }

    public Integer getBtosOccupiedBedCount() {
        return btosOccupiedBedCount;
    }

    public void setBtosOccupiedBedCount(Integer btosOccupiedBedCount) {
        this.btosOccupiedBedCount = btosOccupiedBedCount;
    }

    public Integer getBtosTotalBedCount() {
        return btosTotalBedCount;
    }

    public void setBtosTotalBedCount(Integer btosTotalBedCount) {
        this.btosTotalBedCount = btosTotalBedCount;
    }

    public Integer getBtosUserRid() {
        return btosUserRid;
    }

    public void setBtosUserRid(Integer btosUserRid) {
        this.btosUserRid = btosUserRid;
    }

    public String getBtosSessionId() {
        return btosSessionId;
    }

    public void setBtosSessionId(String btosSessionId) {
        this.btosSessionId = btosSessionId;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbtosRid: Integer=");
        sb.append(btosRid);
        sb.append(";");

        sb.append("\n\tbtosDate: String=");
        sb.append(btosDate);
        sb.append(";");

        sb.append("\n\tbtosBedTypeRid: Integer=");
        sb.append(btosBedTypeRid);
        sb.append(";");

        sb.append("\n\tbtosWardRid: Integer=");
        sb.append(btosWardRid);
        sb.append(";");

        sb.append("\n\tbtosUnitRid: Integer=");
        sb.append(btosUnitRid);
        sb.append(";");

        sb.append("\n\tbtosEntRid: Integer=");
        sb.append(btosEntRid);
        sb.append(";");

        sb.append("\n\tbtosOccupiedBedCount: Integer=");
        sb.append(btosOccupiedBedCount);
        sb.append(";");

        sb.append("\n\tbtosTotalBedCount: Integer=");
        sb.append(btosTotalBedCount);
        sb.append(";");

        sb.append("\n\tbtosUserRid: Integer=");
        sb.append(btosUserRid);
        sb.append(";");

        sb.append("\n\tbtosSessionId: String=");
        sb.append(btosSessionId);
        sb.append(";");

        return sb.toString();
    }

}
