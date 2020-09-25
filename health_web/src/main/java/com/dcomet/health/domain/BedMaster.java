package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BedMaster extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bedRid;
    private Integer bedBgmRid;
    private String bedName;
    private String bedNo;
    private String bedPrefix;
    private Integer bedSequence;
    private String bedNotes;
    private Integer bedState;
    private Integer bedStatus;
    private Integer bedIsActive;
    private Integer bedEntityRid;
    private Integer bedUnitRid;

    public BedMaster() {

    }

    public Integer getBedRid() {
        return bedRid;
    }

    public void setBedRid(Integer bedRid) {
        this.bedRid = bedRid;
    }

    public Integer getBedBgmRid() {
        return bedBgmRid;
    }

    public void setBedBgmRid(Integer bedBgmRid) {
        this.bedBgmRid = bedBgmRid;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedPrefix() {
        return bedPrefix;
    }

    public void setBedPrefix(String bedPrefix) {
        this.bedPrefix = bedPrefix;
    }

    public Integer getBedSequence() {
        return bedSequence;
    }

    public void setBedSequence(Integer bedSequence) {
        this.bedSequence = bedSequence;
    }

    public String getBedNotes() {
        return bedNotes;
    }

    public void setBedNotes(String bedNotes) {
        this.bedNotes = bedNotes;
    }

    public Integer getBedState() {
        return bedState;
    }

    public void setBedState(Integer bedState) {
        this.bedState = bedState;
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

    public Integer getBedUnitRid() {
        return bedUnitRid;
    }

    public void setBedUnitRid(Integer bedUnitRid) {
        this.bedUnitRid = bedUnitRid;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbedRid: Integer=");
        sb.append(bedRid);
        sb.append(";");

        sb.append("\n\tbedBgmRid: Integer=");
        sb.append(bedBgmRid);
        sb.append(";");

        sb.append("\n\tbedName: String=");
        sb.append(bedName);
        sb.append(";");

        sb.append("\n\tbedNo: String=");
        sb.append(bedNo);
        sb.append(";");

        sb.append("\n\tbedPrefix: String=");
        sb.append(bedPrefix);
        sb.append(";");

        sb.append("\n\tbedNotes: String=");
        sb.append(bedNotes);
        sb.append(";");

        sb.append("\n\tbedSequence: Integer=");
        sb.append(bedSequence);
        sb.append(";");

        sb.append("\n\tbedState: Integer=");
        sb.append(bedState);
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

        sb.append("\n\tbedUnitRid: Integer=");
        sb.append(bedUnitRid);
        sb.append(";");

        return sb.toString();
    }
}
