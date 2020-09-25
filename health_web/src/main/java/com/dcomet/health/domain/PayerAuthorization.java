package com.dcomet.health.domain;

import java.io.Serializable;
import java.util.List;

public class PayerAuthorization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer padRid;
    private Integer padPayerRid;
    private Integer padIncidentRid;
    private String padIdentificationNo;
    private Integer padAgencyRid;
    private Integer padPlanRid;
    private Double padEligibilityAmt;
    private String padPlanApprovalNo;
    private String padValidFrom;
    private String padValidTo;
    private Integer padEntityRid;
    private Integer padState;
    private Integer padStatus;
    private String padBoCode;
    private Integer padBoRid;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;
    private Integer padRejectionReasonIndex;
    private String padRemarks;
    private Integer padIsValid;
    private Integer padGopState;
    private String padGopRejRemarks;
    private String padGopRequestedDate;

    private List<PayerIncident> payerIncident;

    public List<PayerIncident> getPayerIncident() {
        return payerIncident;
    }

    public void setPayerIncident(List<PayerIncident> payerIncident) {
        this.payerIncident = payerIncident;
    }

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

    public String getPadValidFrom() {
        return padValidFrom;
    }

    public void setPadValidFrom(String padValidFrom) {
        this.padValidFrom = padValidFrom;
    }

    public String getPadValidTo() {
        return padValidTo;
    }

    public void setPadValidTo(String padValidTo) {
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

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(String modifiedDateTime) {
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

    public String getPadGopRequestedDate() {
        return padGopRequestedDate;
    }

    public void setPadGopRequestedDate(String padGopRequestedDate) {
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

        sb.append("\n\tcreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: String=");
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
