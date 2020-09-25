/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.dao.data; 

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_payer_authorization_d")
public class PayerAuthorizationData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pad_rid", updatable = false)
    private Integer padRid;

    @Column(name = "pad_payer_rid")
    private Integer padPayerRid;

    @Column(name = "pad_incident_rid")
    private Integer padIncidentRid;

    @Column(name = "pad_identification_no")
    private String padIdentificationNo;

    @Column(name = "pad_agency_rid")
    private Integer padAgencyRid;

    @Column(name = "pad_plan_rid")
    private Integer padPlanRid;

    @Column(name = "pad_eligibility_amt")
    private Double padEligibilityAmt;

    @Column(name = "pad_plan_approval_no")
    private String padPlanApprovalNo;

    @Column(name = "pad_valid_from")
    @Temporal(TemporalType.DATE)
    private Date padValidFrom;

    @Column(name = "pad_valid_to")
    @Temporal(TemporalType.DATE)
    private Date padValidTo;

    @Column(name = "pad_entity_rid")
    private Integer padEntityRid;

    @Column(name = "pad_state")
    private Integer padState;

    @Column(name = "pad_status")
    private Integer padStatus;

    @Column(name = "pad_bo_code")
    private String padBoCode;

    @Column(name = "pad_bo_rid")
    private Integer padBoRid;

    @Column(name = "pad_user_rid")
    private Integer createdUserRid;

    @Column(name = "pad_created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "pad_mod_user_rid")
    private Integer modifiedUserRid;

    @Column(name = "pad_mod_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "pad_rejection_reason_index")
    private Integer padRejectionReasonIndex;

    @Column(name = "pad_remarks")
    private String padRemarks;

    @Column(name = "pad_is_valid")
    private Integer padIsValid;

    @Column(name = "pad_gop_state")
    private Integer padGopState;

    @Column(name = "pad_gop_rej_remarks")
    private String padGopRejRemarks;

    @Column(name = "pad_gop_requested_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar padGopRequestedDate;

    public Integer getPadRid() {
        return padRid;
    }

    public void setPadRid(Integer padRid) {
        this.padRid = padRid;
    }

    public Integer getPadPayerRid() {
        return padPayerRid;
    }

    public void setPadPayerRid(Integer padPayerRid) {
        this.padPayerRid = padPayerRid;
    }

    public Integer getPadIncidentRid() {
        return padIncidentRid;
    }

    public void setPadIncidentRid(Integer padIncidentRid) {
        this.padIncidentRid = padIncidentRid;
    }

    public String getPadIdentificationNo() {
        return padIdentificationNo;
    }

    public void setPadIdentificationNo(String padIdentificationNo) {
        this.padIdentificationNo = padIdentificationNo;
    }

    public Integer getPadAgencyRid() {
        return padAgencyRid;
    }

    public void setPadAgencyRid(Integer padAgencyRid) {
        this.padAgencyRid = padAgencyRid;
    }

    public Integer getPadPlanRid() {
        return padPlanRid;
    }

    public void setPadPlanRid(Integer padPlanRid) {
        this.padPlanRid = padPlanRid;
    }

    public Double getPadEligibilityAmt() {
        return padEligibilityAmt;
    }

    public void setPadEligibilityAmt(Double padEligibilityAmt) {
        this.padEligibilityAmt = padEligibilityAmt;
    }

    public String getPadPlanApprovalNo() {
        return padPlanApprovalNo;
    }

    public void setPadPlanApprovalNo(String padPlanApprovalNo) {
        this.padPlanApprovalNo = padPlanApprovalNo;
    }

    public Date getPadValidFrom() {
        return padValidFrom;
    }

    public void setPadValidFrom(Date padValidFrom) {
        this.padValidFrom = padValidFrom;
    }

    public Date getPadValidTo() {
        return padValidTo;
    }

    public void setPadValidTo(Date padValidTo) {
        this.padValidTo = padValidTo;
    }

    public Integer getPadEntityRid() {
        return padEntityRid;
    }

    public void setPadEntityRid(Integer padEntityRid) {
        this.padEntityRid = padEntityRid;
    }

    public Integer getPadState() {
        return padState;
    }

    public void setPadState(Integer padState) {
        this.padState = padState;
    }

    public Integer getPadStatus() {
        return padStatus;
    }

    public void setPadStatus(Integer padStatus) {
        this.padStatus = padStatus;
    }

    public String getPadBoCode() {
        return padBoCode;
    }

    public void setPadBoCode(String padBoCode) {
        this.padBoCode = padBoCode;
    }

    public Integer getPadBoRid() {
        return padBoRid;
    }

    public void setPadBoRid(Integer padBoRid) {
        this.padBoRid = padBoRid;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    @Override
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Integer getPadRejectionReasonIndex() {
        return padRejectionReasonIndex;
    }

    public void setPadRejectionReasonIndex(Integer padRejectionReasonIndex) {
        this.padRejectionReasonIndex = padRejectionReasonIndex;
    }

    public String getPadRemarks() {
        return padRemarks;
    }

    public void setPadRemarks(String padRemarks) {
        this.padRemarks = padRemarks;
    }

    public Integer getPadIsValid() {
        return padIsValid;
    }

    public void setPadIsValid(Integer padIsValid) {
        this.padIsValid = padIsValid;
    }

    public Integer getPadGopState() {
        return padGopState;
    }

    public void setPadGopState(Integer padGopState) {
        this.padGopState = padGopState;
    }

    public String getPadGopRejRemarks() {
        return padGopRejRemarks;
    }

    public void setPadGopRejRemarks(String padGopRejRemarks) {
        this.padGopRejRemarks = padGopRejRemarks;
    }

    public Calendar getPadGopRequestedDate() {
        return padGopRequestedDate;
    }

    public void setPadGopRequestedDate(Calendar padGopRequestedDate) {
        this.padGopRequestedDate = padGopRequestedDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tpadRid: Integer=");
        sb.append(padRid);
        sb.append(";");

        sb.append("\n\tpadPayerRid: Integer=");
        sb.append(padPayerRid);
        sb.append(";");

        sb.append("\n\tpadIncidentRid: Integer=");
        sb.append(padIncidentRid);
        sb.append(";");

        sb.append("\n\tpadIdentificationNo: String=");
        sb.append(padIdentificationNo);
        sb.append(";");

        sb.append("\n\tpadAgencyRid: Integer=");
        sb.append(padAgencyRid);
        sb.append(";");

        sb.append("\n\tpadPlanRid: Integer=");
        sb.append(padPlanRid);
        sb.append(";");

        sb.append("\n\tpadEligibilityAmt: Double=");
        sb.append(padEligibilityAmt);
        sb.append(";");

        sb.append("\n\tpadPlanApprovalNo: String=");
        sb.append(padPlanApprovalNo);
        sb.append(";");

        sb.append("\n\tpadValidFrom: String=");
        sb.append(padValidFrom);
        sb.append(";");

        sb.append("\n\tpadValidTo: String=");
        sb.append(padValidTo);
        sb.append(";");

        sb.append("\n\tpadEntityRid: Integer=");
        sb.append(padEntityRid);
        sb.append(";");

        sb.append("\n\tpadState: Integer=");
        sb.append(padState);
        sb.append(";");

        sb.append("\n\tpadStatus: Integer=");
        sb.append(padStatus);
        sb.append(";");

        sb.append("\n\tpadBoCode: String=");
        sb.append(padBoCode);
        sb.append(";");

        sb.append("\n\tpadBoRid: Integer=");
        sb.append(padBoRid);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tpadRejectionReasonIndex: Integer=");
        sb.append(padRejectionReasonIndex);
        sb.append(";");

        sb.append("\n\tpadRemarks: String=");
        sb.append(padRemarks);
        sb.append(";");

        sb.append("\n\tpadIsValid: Integer=");
        sb.append(padIsValid);
        sb.append(";");

        sb.append("\n\tpadGopState: Integer=");
        sb.append(padGopState);
        sb.append(";");

        sb.append("\n\tpadGopRejRemarks: String=");
        sb.append(padGopRejRemarks);
        sb.append(";");

        sb.append("\n\tpadGopRequestedDate: String=");
        sb.append(padGopRequestedDate);
        sb.append(";");

        return sb.toString();
    }

}
