package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_bed_type_occupancy_summary")

public class BedTypeOccupancySummaryData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BTOS_RID", updatable = false)
    private Integer btosRid;

    @Column(name = "BTOS_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar btosDate;

    @Column(name = "BTOS_BED_TYPE_RID")
    private Integer btosBedTypeRid;

    @Column(name = "BTOS_WARD_RID")
    private Integer btosWardRid;

    @Column(name = "BTOS_UNIT_RID")
    private Integer btosUnitRid;

    @Column(name = "BTOS_ENT_RID")
    private Integer btosEntRid;

    @Column(name = "BTOS_OCCUPIED_BED_COUNT")
    private Integer btosOccupiedBedCount;

    @Column(name = "BTOS_TOTAL_BED_COUNT")
    private Integer btosTotalBedCount;

    @Column(name = "BTOS_USER_RID")
    private Integer btosUserRid;

    @Column(name = "BTOS_SESSION_ID")
    private String btosSessionId;

    public BedTypeOccupancySummaryData() {
    }

    public Integer getBtosRid() {
        return btosRid;
    }

    public void setBtosRid(Integer btosRid) {
        this.btosRid = btosRid;
    }

    public Calendar getBtosDate() {
        return btosDate;
    }

    public void setBtosDate(Calendar btosDate) {
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

        sb.append("\n\tbtosDate: Calendar=");
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
