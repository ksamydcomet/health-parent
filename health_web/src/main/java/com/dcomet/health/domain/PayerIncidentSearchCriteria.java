package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class PayerIncidentSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;

    private Integer pidRid;
    private Integer pidPayerRid;
    private Integer pidIncidentRid;
    private Integer pidIncidentType;
    private String pidIncidentDate;
    private String pidIdentificationNo;
    private Integer pidAgencyRid;
    private Integer pidPlanRid;
    private Integer pidPayerRidForDependent;
    private Integer pidBillingBedtypeRid;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;
    private Double pidEligibilityAmt;
    private Integer pidPatCategory;
    private String pidPlanApprovalNo;
    private Integer pidEntityRid;
    private Float pidMinDeductable;
    private String pidValidFrom;
    private String pidValidTo;
    private String pidPolicyHolderId;
    private String pidPolicyHolderName;
    private String pidInsuranceName;
    private Integer pidPatientPlanType;
    private String pidRemarks;
    private Integer pidIsFinDtlsUpdated;

    public Integer getPidRid() {
        return pidRid;
    }

    public void setPidRid(Integer pidRid) {
        this.pidRid = pidRid;
    }

    public Integer getPidPayerRid() {
        return pidPayerRid;
    }

    public void setPidPayerRid(Integer pidPayerRid) {
        this.pidPayerRid = pidPayerRid;
    }

    public Integer getPidIncidentRid() {
        return pidIncidentRid;
    }

    public void setPidIncidentRid(Integer pidIncidentRid) {
        this.pidIncidentRid = pidIncidentRid;
    }

    public Integer getPidIncidentType() {
        return pidIncidentType;
    }

    public void setPidIncidentType(Integer pidIncidentType) {
        this.pidIncidentType = pidIncidentType;
    }

    public String getPidIncidentDate() {
        return pidIncidentDate;
    }

    public void setPidIncidentDate(String pidIncidentDate) {
        this.pidIncidentDate = pidIncidentDate;
    }

    public String getPidIdentificationNo() {
        return pidIdentificationNo;
    }

    public void setPidIdentificationNo(String pidIdentificationNo) {
        this.pidIdentificationNo = pidIdentificationNo;
    }

    public Integer getPidAgencyRid() {
        return pidAgencyRid;
    }

    public void setPidAgencyRid(Integer pidAgencyRid) {
        this.pidAgencyRid = pidAgencyRid;
    }

    public Integer getPidPlanRid() {
        return pidPlanRid;
    }

    public void setPidPlanRid(Integer pidPlanRid) {
        this.pidPlanRid = pidPlanRid;
    }

    public Integer getPidPayerRidForDependent() {
        return pidPayerRidForDependent;
    }

    public void setPidPayerRidForDependent(Integer pidPayerRidForDependent) {
        this.pidPayerRidForDependent = pidPayerRidForDependent;
    }

    public Integer getPidBillingBedtypeRid() {
        return pidBillingBedtypeRid;
    }

    public void setPidBillingBedtypeRid(Integer pidBillingBedtypeRid) {
        this.pidBillingBedtypeRid = pidBillingBedtypeRid;
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

    public Double getPidEligibilityAmt() {
        return pidEligibilityAmt;
    }

    public void setPidEligibilityAmt(Double pidEligibilityAmt) {
        this.pidEligibilityAmt = pidEligibilityAmt;
    }

    public Integer getPidPatCategory() {
        return pidPatCategory;
    }

    public void setPidPatCategory(Integer pidPatCategory) {
        this.pidPatCategory = pidPatCategory;
    }

    public String getPidPlanApprovalNo() {
        return pidPlanApprovalNo;
    }

    public void setPidPlanApprovalNo(String pidPlanApprovalNo) {
        this.pidPlanApprovalNo = pidPlanApprovalNo;
    }

    public Integer getPidEntityRid() {
        return pidEntityRid;
    }

    public void setPidEntityRid(Integer pidEntityRid) {
        this.pidEntityRid = pidEntityRid;
    }

    public Float getPidMinDeductable() {
        return pidMinDeductable;
    }

    public void setPidMinDeductable(Float pidMinDeductable) {
        this.pidMinDeductable = pidMinDeductable;
    }

    public String getPidValidFrom() {
        return pidValidFrom;
    }

    public void setPidValidFrom(String pidValidFrom) {
        this.pidValidFrom = pidValidFrom;
    }

    public String getPidValidTo() {
        return pidValidTo;
    }

    public void setPidValidTo(String pidValidTo) {
        this.pidValidTo = pidValidTo;
    }

    public String getPidPolicyHolderId() {
        return pidPolicyHolderId;
    }

    public void setPidPolicyHolderId(String pidPolicyHolderId) {
        this.pidPolicyHolderId = pidPolicyHolderId;
    }

    public String getPidPolicyHolderName() {
        return pidPolicyHolderName;
    }

    public void setPidPolicyHolderName(String pidPolicyHolderName) {
        this.pidPolicyHolderName = pidPolicyHolderName;
    }

    public String getPidInsuranceName() {
        return pidInsuranceName;
    }

    public void setPidInsuranceName(String pidInsuranceName) {
        this.pidInsuranceName = pidInsuranceName;
    }

    public Integer getPidPatientPlanType() {
        return pidPatientPlanType;
    }

    public void setPidPatientPlanType(Integer pidPatientPlanType) {
        this.pidPatientPlanType = pidPatientPlanType;
    }

    public String getPidRemarks() {
        return pidRemarks;
    }

    public void setPidRemarks(String pidRemarks) {
        this.pidRemarks = pidRemarks;
    }

    public Integer getPidIsFinDtlsUpdated() {
        return pidIsFinDtlsUpdated;
    }

    public void setPidIsFinDtlsUpdated(Integer pidIsFinDtlsUpdated) {
        this.pidIsFinDtlsUpdated = pidIsFinDtlsUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tpidRid: Integer=");
        sb.append(pidRid);
        sb.append(";");

        sb.append("\n\tpidPayerRid: Integer=");
        sb.append(pidPayerRid);
        sb.append(";");

        sb.append("\n\tpidIncidentRid: Integer=");
        sb.append(pidIncidentRid);
        sb.append(";");

        sb.append("\n\tpidIncidentType: Integer=");
        sb.append(pidIncidentType);
        sb.append(";");

        sb.append("\n\tpidIncidentDate: String=");
        sb.append(pidIncidentDate);
        sb.append(";");

        sb.append("\n\tpidIdentificationNo: String=");
        sb.append(pidIdentificationNo);
        sb.append(";");

        sb.append("\n\tpidAgencyRid: Integer=");
        sb.append(pidAgencyRid);
        sb.append(";");

        sb.append("\n\tpidPlanRid: Integer=");
        sb.append(pidPlanRid);
        sb.append(";");

        sb.append("\n\tpidPayerRidForDependent: Integer=");
        sb.append(pidPayerRidForDependent);
        sb.append(";");

        sb.append("\n\tpidBillingBedtypeRid: Integer=");
        sb.append(pidBillingBedtypeRid);
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

        sb.append("\n\tpidEligibilityAmt: Double=");
        sb.append(pidEligibilityAmt);
        sb.append(";");

        sb.append("\n\tpidPatCategory: Integer=");
        sb.append(pidPatCategory);
        sb.append(";");

        sb.append("\n\tpidPlanApprovalNo: String=");
        sb.append(pidPlanApprovalNo);
        sb.append(";");

        sb.append("\n\tpidEntityRid: Integer=");
        sb.append(pidEntityRid);
        sb.append(";");

        sb.append("\n\tpidMinDeductable: Float=");
        sb.append(pidMinDeductable);
        sb.append(";");

        sb.append("\n\tpidPolicyHolderId: String=");
        sb.append(pidPolicyHolderId);
        sb.append(";");

        sb.append("\n\tpidPolicyHolderName: String=");
        sb.append(pidPolicyHolderName);
        sb.append(";");

        sb.append("\n\tpidInsuranceName: String=");
        sb.append(pidInsuranceName);
        sb.append(";");

        sb.append("\n\tpidPatientPlanType: Integer=");
        sb.append(pidPatientPlanType);
        sb.append(";");

        sb.append("\n\tpidRemarks: String=");
        sb.append(pidRemarks);
        sb.append(";");

        sb.append("\n\tpidIsFinDtlsUpdated: Integer=");
        sb.append(pidIsFinDtlsUpdated);
        sb.append(";");

        return sb.toString();
    }

}
