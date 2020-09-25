package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BedTransfer extends Base implements Serializable {

    private Integer btRid;
    private Integer btPatRid;
    private Integer btVisitRid;
    private Integer btFromWardRid;
    private Integer btToWardRid;
    private Integer btFromBed;
    private Integer btToBed;
    private String btTransferDatetime;
    private Integer btTransferredByRid;

    public BedTransfer() {
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

    public String getBtTransferDatetime() {
        return btTransferDatetime;
    }

    public void setBtTransferDatetime(String btTransferDatetime) {
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

        sb.append("\n\tbtTransferDatetime: String=");
        sb.append(btTransferDatetime);
        sb.append(";");

        sb.append("\n\tbtTransferredByRid: Integer=");
        sb.append(btTransferredByRid);
        sb.append(";");

        return sb.toString();
    }
}
