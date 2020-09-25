package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_bed_transfer")

public class BedTransferData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BT_RID", updatable = false)
    private Integer btRid;

    @Column(name = "BT_PAT_RID")
    private Integer btPatRid;

    @Column(name = "BT_VISIT_RID")
    private Integer btVisitRid;

    @Column(name = "BT_FROM_WARD_RID")
    private Integer btFromWardRid;

    @Column(name = "BT_TO_WARD_RID")
    private Integer btToWardRid;

    @Column(name = "BT_FROM_BED")
    private Integer btFromBed;

    @Column(name = "BT_TO_BED")
    private Integer btToBed;

    @Column(name = "BT_TRANSFER_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar btTransferDatetime;

    @Column(name = "BT_TRANSFERRED_BY_RID")
    private Integer btTransferredByRid;

    public BedTransferData() {
    }

    public BedTransferData(Integer btRid) {
        this.btRid = btRid;
    }

    public Integer getBtRid() {
        return btRid;
    }

    public void setBtRid(Integer btRid) {
        this.btRid = btRid;
    }

    public Integer getBtPatRid() {
        return btPatRid;
    }

    public void setBtPatRid(Integer btPatRid) {
        this.btPatRid = btPatRid;
    }

    public Integer getBtVisitRid() {
        return btVisitRid;
    }

    public void setBtVisitRid(Integer btVisitRid) {
        this.btVisitRid = btVisitRid;
    }

    public Integer getBtFromWardRid() {
        return btFromWardRid;
    }

    public void setBtFromWardRid(Integer btFromWardRid) {
        this.btFromWardRid = btFromWardRid;
    }

    public Integer getBtToWardRid() {
        return btToWardRid;
    }

    public void setBtToWardRid(Integer btToWardRid) {
        this.btToWardRid = btToWardRid;
    }

    public Integer getBtFromBed() {
        return btFromBed;
    }

    public void setBtFromBed(Integer btFromBed) {
        this.btFromBed = btFromBed;
    }

    public Integer getBtToBed() {
        return btToBed;
    }

    public void setBtToBed(Integer btToBed) {
        this.btToBed = btToBed;
    }

    public Calendar getBtTransferDatetime() {
        return btTransferDatetime;
    }

    public void setBtTransferDatetime(Calendar btTransferDatetime) {
        this.btTransferDatetime = btTransferDatetime;
    }

    public Integer getBtTransferredByRid() {
        return btTransferredByRid;
    }

    public void setBtTransferredByRid(Integer btTransferredByRid) {
        this.btTransferredByRid = btTransferredByRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbtRid: Integer=");
        sb.append(btRid);
        sb.append(";");

        sb.append("\n\tbtPatRid: Integer=");
        sb.append(btPatRid);
        sb.append(";");

        sb.append("\n\tbtVisitRid: Integer=");
        sb.append(btVisitRid);
        sb.append(";");

        sb.append("\n\tbtFromWardRid: Integer=");
        sb.append(btFromWardRid);
        sb.append(";");

        sb.append("\n\tbtToWardRid: Integer=");
        sb.append(btToWardRid);
        sb.append(";");

        sb.append("\n\tbtFromBed: Integer=");
        sb.append(btFromBed);
        sb.append(";");

        sb.append("\n\tbtToBed: Integer=");
        sb.append(btToBed);
        sb.append(";");

        sb.append("\n\tbtTransferDatetime: Calendar=");
        sb.append(btTransferDatetime);
        sb.append(";");

        sb.append("\n\tbtTransferredByRid: Integer=");
        sb.append(btTransferredByRid);
        sb.append(";");

        return sb.toString();
    }
}
