package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BedTransferRequest extends Base implements Serializable {

    private Integer btrRid;
    private Integer btrPatientRid;
    private Integer btrVisitRid;
    private Integer btrFromBedRid;
    private String btrFrombedNo;
    private Integer btrFromWardRid;
    private Integer btrCurrentBillingClassRid;
    private Integer btrToBedRid;
    private String btrToBedNo;
    private Integer btrToWardRid;
    private Integer btrNewBillingClassRid;
    private String btrTransferDatetime;
    private Integer btrState;
    private Integer btrStatus;
    private Integer btrEntityRid;
    private String btrRemarks;
    private Integer btrCreatedUserRid;
    private String btrCreatedDatetime;
    private Integer btrModifiedUserRid;
    private String btrModifiedDatetime;

    public BedTransferRequest() {
    }

    public Integer getBtrRid() {
        return btrRid;
    }

    public void setBtrRid(Integer btrRid) {
        this.btrRid = btrRid;
    }

    public Integer getBtrPatientRid() {
        return btrPatientRid;
    }

    public void setBtrPatientRid(Integer btrPatientRid) {
        this.btrPatientRid = btrPatientRid;
    }

    public Integer getBtrVisitRid() {
        return btrVisitRid;
    }

    public void setBtrVisitRid(Integer btrVisitRid) {
        this.btrVisitRid = btrVisitRid;
    }

    public Integer getBtrFromBedRid() {
        return btrFromBedRid;
    }

    public void setBtrFromBedRid(Integer btrFromBedRid) {
        this.btrFromBedRid = btrFromBedRid;
    }

    public String getBtrFrombedNo() {
        return btrFrombedNo;
    }

    public void setBtrFrombedNo(String btrFrombedNo) {
        this.btrFrombedNo = btrFrombedNo;
    }

    public Integer getBtrFromWardRid() {
        return btrFromWardRid;
    }

    public void setBtrFromWardRid(Integer btrFromWardRid) {
        this.btrFromWardRid = btrFromWardRid;
    }

    public Integer getBtrCurrentBillingClassRid() {
        return btrCurrentBillingClassRid;
    }

    public void setBtrCurrentBillingClassRid(Integer btrCurrentBillingClassRid) {
        this.btrCurrentBillingClassRid = btrCurrentBillingClassRid;
    }

    public Integer getBtrToBedRid() {
        return btrToBedRid;
    }

    public void setBtrToBedRid(Integer btrToBedRid) {
        this.btrToBedRid = btrToBedRid;
    }

    public String getBtrToBedNo() {
        return btrToBedNo;
    }

    public void setBtrToBedNo(String btrToBedNo) {
        this.btrToBedNo = btrToBedNo;
    }

    public Integer getBtrToWardRid() {
        return btrToWardRid;
    }

    public void setBtrToWardRid(Integer btrToWardRid) {
        this.btrToWardRid = btrToWardRid;
    }

    public Integer getBtrNewBillingClassRid() {
        return btrNewBillingClassRid;
    }

    public void setBtrNewBillingClassRid(Integer btrNewBillingClassRid) {
        this.btrNewBillingClassRid = btrNewBillingClassRid;
    }

    public String getBtrTransferDatetime() {
        return btrTransferDatetime;
    }

    public void setBtrTransferDatetime(String btrTransferDatetime) {
        this.btrTransferDatetime = btrTransferDatetime;
    }

    public Integer getBtrState() {
        return btrState;
    }

    public void setBtrState(Integer btrState) {
        this.btrState = btrState;
    }

    public Integer getBtrStatus() {
        return btrStatus;
    }

    public void setBtrStatus(Integer btrStatus) {
        this.btrStatus = btrStatus;
    }

    public Integer getBtrEntityRid() {
        return btrEntityRid;
    }

    public void setBtrEntityRid(Integer btrEntityRid) {
        this.btrEntityRid = btrEntityRid;
    }

    public String getBtrRemarks() {
        return btrRemarks;
    }

    public void setBtrRemarks(String btrRemarks) {
        this.btrRemarks = btrRemarks;
    }

    public Integer getBtrCreatedUserRid() {
        return btrCreatedUserRid;
    }

    public void setBtrCreatedUserRid(Integer btrCreatedUserRid) {
        this.btrCreatedUserRid = btrCreatedUserRid;
    }

    public String getBtrCreatedDatetime() {
        return btrCreatedDatetime;
    }

    public void setBtrCreatedDatetime(String btrCreatedDatetime) {
        this.btrCreatedDatetime = btrCreatedDatetime;
    }

    public Integer getBtrModifiedUserRid() {
        return btrModifiedUserRid;
    }

    public void setBtrModifiedUserRid(Integer btrModifiedUserRid) {
        this.btrModifiedUserRid = btrModifiedUserRid;
    }

    public String getBtrModifiedDatetime() {
        return btrModifiedDatetime;
    }

    public void setBtrModifiedDatetime(String btrModifiedDatetime) {
        this.btrModifiedDatetime = btrModifiedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbtrRid: Integer=");
        sb.append(btrRid);
        sb.append(";");

        sb.append("\n\tbtrPatientRid: Integer=");
        sb.append(btrPatientRid);
        sb.append(";");

        sb.append("\n\tbtrVisitRid: Integer=");
        sb.append(btrVisitRid);
        sb.append(";");

        sb.append("\n\tbtrFromBedRid: Integer=");
        sb.append(btrFromBedRid);
        sb.append(";");

        sb.append("\n\tbtrFrombedNo: String=");
        sb.append(btrFrombedNo);
        sb.append(";");

        sb.append("\n\tbtrFromWardRid: Integer=");
        sb.append(btrFromWardRid);
        sb.append(";");

        sb.append("\n\tbtrCurrentBillingClassRid: Integer=");
        sb.append(btrCurrentBillingClassRid);
        sb.append(";");

        sb.append("\n\tbtrToBedRid: Integer=");
        sb.append(btrToBedRid);
        sb.append(";");

        sb.append("\n\tbtrToBedNo: String=");
        sb.append(btrToBedNo);
        sb.append(";");

        sb.append("\n\tbtrToWardRid: Integer=");
        sb.append(btrToWardRid);
        sb.append(";");

        sb.append("\n\tbtrNewBillingClassRid: Integer=");
        sb.append(btrNewBillingClassRid);
        sb.append(";");

        sb.append("\n\tbtrTransferDatetime: String=");
        sb.append(btrTransferDatetime);
        sb.append(";");

        sb.append("\n\tbtrState: Integer=");
        sb.append(btrState);
        sb.append(";");

        sb.append("\n\tbtrStatus: Integer=");
        sb.append(btrStatus);
        sb.append(";");

        sb.append("\n\tbtrEntityRid: Integer=");
        sb.append(btrEntityRid);
        sb.append(";");

        sb.append("\n\tbtrRemarks: String=");
        sb.append(btrRemarks);
        sb.append(";");

        sb.append("\n\tbtrCreatedUserRid: Integer=");
        sb.append(btrCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbtrCreatedDatetime: String=");
        sb.append(btrCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbtrModifiedUserRid: Integer=");
        sb.append(btrModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbtrModifiedDatetime: String=");
        sb.append(btrModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
