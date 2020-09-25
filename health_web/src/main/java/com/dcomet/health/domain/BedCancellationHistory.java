package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BedCancellationHistory extends Base implements Serializable {

    private Integer bchRid;
    private Integer bchPatientRid;
    private Integer bchBedRid;
    private String bchBedNo;
    private String bchReason;
    private Integer bchEntityRid;
    private String bchCancelDatetime;
    private Integer bchCreatedUserRid;
    private String bchCreatedDatetime;

    public BedCancellationHistory() {
    }

    public Integer getBchRid() {
        return bchRid;
    }

    public void setBchRid(Integer bchRid) {
        this.bchRid = bchRid;
    }

    public Integer getBchPatientRid() {
        return bchPatientRid;
    }

    public void setBchPatientRid(Integer bchPatientRid) {
        this.bchPatientRid = bchPatientRid;
    }

    public Integer getBchBedRid() {
        return bchBedRid;
    }

    public void setBchBedRid(Integer bchBedRid) {
        this.bchBedRid = bchBedRid;
    }

    public String getBchBedNo() {
        return bchBedNo;
    }

    public void setBchBedNo(String bchBedNo) {
        this.bchBedNo = bchBedNo;
    }

    public String getBchReason() {
        return bchReason;
    }

    public void setBchReason(String bchReason) {
        this.bchReason = bchReason;
    }

    public Integer getBchEntityRid() {
        return bchEntityRid;
    }

    public void setBchEntityRid(Integer bchEntityRid) {
        this.bchEntityRid = bchEntityRid;
    }

    public String getBchCancelDatetime() {
        return bchCancelDatetime;
    }

    public void setBchCancelDatetime(String bchCancelDatetime) {
        this.bchCancelDatetime = bchCancelDatetime;
    }

    public Integer getBchCreatedUserRid() {
        return bchCreatedUserRid;
    }

    public void setBchCreatedUserRid(Integer bchCreatedUserRid) {
        this.bchCreatedUserRid = bchCreatedUserRid;
    }

    public String getBchCreatedDatetime() {
        return bchCreatedDatetime;
    }

    public void setBchCreatedDatetime(String bchCreatedDatetime) {
        this.bchCreatedDatetime = bchCreatedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbchRid: Integer=");
        sb.append(bchRid);
        sb.append(";");

        sb.append("\n\tbchPatientRid: Integer=");
        sb.append(bchPatientRid);
        sb.append(";");

        sb.append("\n\tbchBedRid: Integer=");
        sb.append(bchBedRid);
        sb.append(";");

        sb.append("\n\tbchBedNo: String=");
        sb.append(bchBedNo);
        sb.append(";");

        sb.append("\n\tbchReason: String=");
        sb.append(bchReason);
        sb.append(";");

        sb.append("\n\tbchEntityRid: Integer=");
        sb.append(bchEntityRid);
        sb.append(";");

        sb.append("\n\tbchCancelDatetime: String=");
        sb.append(bchCancelDatetime);
        sb.append(";");

        sb.append("\n\tbchCreatedUserRid: Date=");
        sb.append(bchCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbchCreatedDatetime: String=");
        sb.append(bchCreatedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
