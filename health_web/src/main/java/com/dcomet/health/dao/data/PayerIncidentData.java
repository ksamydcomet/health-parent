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
@Table(name = "t_payer_incident_d")
public class PayerIncidentData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid_rid", updatable = false)
    private Integer pidRid;

    @Column(name = "pid_payer_rid")
    private Integer pidPayerRid;

    @Column(name = "pid_incident_rid")
    private Integer pidIncidentRid;

    @Column(name = "pid_incident_type")
    private Integer pidIncidentType;

    @Column(name = "pid_incident_date")
    @Temporal(TemporalType.DATE)
    private Date pidIncidentDate;

    @Column(name = "pid_identification_no")
    private String pidIdentificationNo;

    @Column(name = "pid_agency_rid")
    private Integer pidAgencyRid;

    @Column(name = "pid_plan_rid")
    private Integer pidPlanRid;

    @Column(name = "pid_payer_rid_for_dependent")
    private Integer pidPayerRidForDependent;

    @Column(name = "pid_billing_bedtype_rid")
    private Integer pidBillingBedtypeRid;

    @Column(name = "pid_user_rid")
    private Integer createdUserRid;

    @Column(name = "pid_created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "pid_mod_user_rid")
    private Integer modifiedUserRid;

    @Column(name = "pid_mod_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "pid_eligibility_amt")
    private Double pidEligibilityAmt;

    @Column(name = "pid_pat_category")
    private Integer pidPatCategory;

    @Column(name = "pid_plan_approval_no")
    private String pidPlanApprovalNo;

    @Column(name = "pid_entity_rid")
    private Integer pidEntityRid;

    @Column(name = "pid_min_deductable")
    private Float pidMinDeductable;

    @Column(name = "pid_valid_from")
    @Temporal(TemporalType.DATE)
    private Date pidValidFrom;

    @Column(name = "pid_valid_to")
    @Temporal(TemporalType.DATE)
    private Date pidValidTo;

    @Column(name = "pid_policy_holder_id")
    private String pidPolicyHolderId;

    @Column(name = "pid_policy_holder_name")
    private String pidPolicyHolderName;

    @Column(name = "pid_insurance_name")
    private String pidInsuranceName;

    @Column(name = "pid_patient_plan_type")
    private Integer pidPatientPlanType;

    @Column(name = "pid_remarks")
    private String pidRemarks;

    @Column(name = "pid_is_fin_dtls_updated")
    private Integer pidIsFinDtlsUpdated;

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

    public Integer getPidIsFinDtlsUpdated() {
        return pidIsFinDtlsUpdated;
    }

    public Integer getPidRid() {
        return pidRid;
    }

    public void setPidIsFinDtlsUpdated(Integer pidIsFinDtlsUpdated) {
        this.pidIsFinDtlsUpdated = pidIsFinDtlsUpdated;
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

    public Date getPidIncidentDate() {
        return pidIncidentDate;
    }

    public void setPidIncidentDate(Date pidIncidentDate) {
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

    public Date getPidValidFrom() {
        return pidValidFrom;
    }

    public void setPidValidFrom(Date pidValidFrom) {
        this.pidValidFrom = pidValidFrom;
    }

    public Date getPidValidTo() {
        return pidValidTo;
    }

    public void setPidValidTo(Date pidValidTo) {
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

        sb.append("\n\tpidIncidentDate: Date=");
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

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
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
