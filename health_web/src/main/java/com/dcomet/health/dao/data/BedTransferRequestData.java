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

@Entity
@Table(name = "t_bed_transfer_request")

public class BedTransferRequestData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BTR_RID", updatable = false)
    private Integer btrRid;

    @Column(name = "BTR_PATIENT_RID")
    private Integer btrPatientRid;

    @Column(name = "BTR_VISIT_RID")
    private Integer btrVisitRid;

    @Column(name = "BTR_FROM_BED_RID")
    private Integer btrFromBedRid;

    @Column(name = "BTR_FROM_BED_NO")
    private String btrFrombedNo;

    @Column(name = "BTR_FROM_WARD_RID")
    private Integer btrFromWardRid;

    @Column(name = "BTR_CURRENT_BILLING_CLASS_RID")
    private Integer btrCurrentBillingClassRid;

    @Column(name = "BTR_TO_BED_RID")
    private Integer btrToBedRid;

    @Column(name = "BTR_TO_BED_NO")
    private String btrToBedNo;

    @Column(name = "BTR_TO_WARD_RID")
    private Integer btrToWardRid;

    @Column(name = "BTR_NEW_BILLING_CLASS_RID")
    private Integer btrNewBillingClassRid;

    @Column(name = "BTR_TRANSFER_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar btrTransferDatetime;

    @Column(name = "BTR_STATE")
    private Integer btrState;

    @Column(name = "BTR_STATUS")
    private Integer btrStatus;

    @Column(name = "BTR_ENTITY_RID")
    private Integer btrEntityRid;

    @Column(name = "BTR_REMARKS")
    private String btrRemarks;

    @Column(name = "BTR_CREATED_USER_RID")
    private Integer btrCreatedUserRid;

    @Column(name = "BTR_CREATED_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar btrCreatedDatetime;

    @Column(name = "BTR_MODIFIED_USER_RID")
    private Integer btrModifiedUserRid;

    @Column(name = "BTR_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar btrModifiedDatetime;

    public BedTransferRequestData() {
    }

    public BedTransferRequestData(Integer btrRid) {
        this.btrRid = btrRid;
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

    public Calendar getBtrCreatedDatetime() {
        return btrCreatedDatetime;
    }

    public void setBtrCreatedDatetime(Calendar btrCreatedDatetime) {
        this.btrCreatedDatetime = btrCreatedDatetime;
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

    public Calendar getBtrModifiedDatetime() {
        return btrModifiedDatetime;
    }

    public void setBtrModifiedDatetime(Calendar btrModifiedDatetime) {
        this.btrModifiedDatetime = btrModifiedDatetime;
    }

    public Integer getBtrModifiedUserRid() {
        return btrModifiedUserRid;
    }

    public void setBtrModifiedUserRid(Integer btrModifiedUserRid) {
        this.btrModifiedUserRid = btrModifiedUserRid;
    }

    public Calendar getBtrTransferDatetime() {
        return btrTransferDatetime;
    }

    public void setBtrTransferDatetime(Calendar btrTransferDatetime) {
        this.btrTransferDatetime = btrTransferDatetime;
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

        sb.append("\n\tbtrTransferDatetime: Calendar=");
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

        sb.append("\n\tbtrCreatedDatetime: Calendar=");
        sb.append(btrCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbtrModifiedUserRid: Integer=");
        sb.append(btrModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbtrModifiedDatetime: Calendar=");
        sb.append(btrModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
