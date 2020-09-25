package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Temporal;

/**
 *
 * @author Dev1
 */
@Entity
@Table(name = "t_drug_request_h")
public class DrugRequestHData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DRUG_REQ_H_RID", updatable = false)
    private Integer id;

    @Column(name = "DRUG_REQ_H_OP_RID")
    private Integer drugReqHOpRID;

    @Column(name = "DRUG_REQ_H_OP_VISIT_RID")//, updatable = false
    private Integer drugReqHOpVBisitRID;

    @Column(name = "DRUG_REQ_H_PAT_MRN")
    private String drugReqHPatMrn;

    @Column(name = "DRUG_REQ_H_PAT_NAME")
    private String drugReqHPatName;

    @Column(name = "DRUG_REQ_H_PAT_RID")
    private Integer drugReqHPatRid;

    @Column(name = "DRUG_REQ_H_BILL_H_RID")
    private Integer drugReqHBillHRID;

    @Column(name = "DRUG_REQ_H_BILL_NO")
    private String drugReqHBillNo;

    @Column(name = "DRUG_REQ_H_PROCEDURE_RID")
    private Integer drugReqHProcedureRid;

    @Column(name = "DRUG_REQ_H_STATUS")
    private Integer drugReqHStatus;

    @Column(name = "DRUG_REQ_H_STATE")
    private Integer drugReqHState;

    @Column(name = "DRUG_REQ_H_TYPE")
    private Integer drugReqHType;

    @Column(name = "DRUG_REQ_H_OP_CHECK")
    private Integer drugReqHOpCheck;

    @Column(name = "DRUG_REQ_H_FOLLOWUP_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date drugReqHfollowupDate;

    @Column(name = "DRUG_REQ_H_COMMENTS")
    private String drugReqHComments;

    @Column(name = "DRUG_REQ_H_ENTITY_RID")
    private Integer drugReqHEntityRID;

    @Column(name = "DRUG_REQ_H_LAB_EN_H_RID")
    private Integer drugReqHLabEnHRID;

    @Column(name = "DRUG_REQ_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "DRUG_REQ_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "DRUG_REQ_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "DRUG_REQ_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public DrugRequestHData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDrugReqHOpRID() {
        return drugReqHOpRID;
    }

    public void setDrugReqHOpRID(Integer drugReqHOpRID) {
        this.drugReqHOpRID = drugReqHOpRID;
    }

    public Integer getDrugReqHOpVBisitRID() {
        return drugReqHOpVBisitRID;
    }

    public void setDrugReqHOpVBisitRID(Integer drugReqHOpVBisitRID) {
        this.drugReqHOpVBisitRID = drugReqHOpVBisitRID;
    }

    public String getDrugReqHPatMrn() {
        return drugReqHPatMrn;
    }

    public void setDrugReqHPatMrn(String drugReqHPatMrn) {
        this.drugReqHPatMrn = drugReqHPatMrn;
    }

    public String getDrugReqHPatName() {
        return drugReqHPatName;
    }

    public void setDrugReqHPatName(String drugReqHPatName) {
        this.drugReqHPatName = drugReqHPatName;
    }

    public Integer getDrugReqHPatRid() {
        return drugReqHPatRid;
    }

    public void setDrugReqHPatRid(Integer drugReqHPatRid) {
        this.drugReqHPatRid = drugReqHPatRid;
    }

    public Integer getDrugReqHBillHRID() {
        return drugReqHBillHRID;
    }

    public void setDrugReqHBillHRID(Integer drugReqHBillHRID) {
        this.drugReqHBillHRID = drugReqHBillHRID;
    }

    public String getDrugReqHBillNo() {
        return drugReqHBillNo;
    }

    public void setDrugReqHBillNo(String drugReqHBillNo) {
        this.drugReqHBillNo = drugReqHBillNo;
    }

    public Integer getDrugReqHProcedureRid() {
        return drugReqHProcedureRid;
    }

    public void setDrugReqHProcedureRid(Integer drugReqHProcedureRid) {
        this.drugReqHProcedureRid = drugReqHProcedureRid;
    }

    public Integer getDrugReqHStatus() {
        return drugReqHStatus;
    }

    public void setDrugReqHStatus(Integer drugReqHStatus) {
        this.drugReqHStatus = drugReqHStatus;
    }

    public Integer getDrugReqHState() {
        return drugReqHState;
    }

    public void setDrugReqHState(Integer drugReqHState) {
        this.drugReqHState = drugReqHState;
    }

    public Integer getDrugReqHType() {
        return drugReqHType;
    }

    public void setDrugReqHType(Integer drugReqHType) {
        this.drugReqHType = drugReqHType;
    }

    public Integer getDrugReqHOpCheck() {
        return drugReqHOpCheck;
    }

    public void setDrugReqHOpCheck(Integer drugReqHOpCheck) {
        this.drugReqHOpCheck = drugReqHOpCheck;
    }

    public Date getDrugReqHfollowupDate() {
        return drugReqHfollowupDate;
    }

    public void setDrugReqHfollowupDate(Date drugReqHfollowupDate) {
        this.drugReqHfollowupDate = drugReqHfollowupDate;
    }

    public String getDrugReqHComments() {
        return drugReqHComments;
    }

    public void setDrugReqHComments(String drugReqHComments) {
        this.drugReqHComments = drugReqHComments;
    }

    public Integer getDrugReqHEntityRID() {
        return drugReqHEntityRID;
    }

    public void setDrugReqHEntityRID(Integer drugReqHEntityRID) {
        this.drugReqHEntityRID = drugReqHEntityRID;
    }

    public Integer getDrugReqHLabEnHRID() {
        return drugReqHLabEnHRID;
    }

    public void setDrugReqHLabEnHRID(Integer drugReqHLabEnHRID) {
        this.drugReqHLabEnHRID = drugReqHLabEnHRID;
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

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tdrugReqHOpRID: Integer=");
        sb.append(drugReqHOpRID);
        sb.append(";");

        sb.append("\n\tdrugReqHOpVBisitRID: Integer=");
        sb.append(drugReqHOpVBisitRID);
        sb.append(";");

        sb.append("\n\tdrugReqHPatMrn: String=");
        sb.append(drugReqHPatMrn);
        sb.append(";");

        sb.append("\n\tdrugReqHPatName: String=");
        sb.append(drugReqHPatName);
        sb.append(";");

        sb.append("\n\tdrugReqHPatRid: Integer=");
        sb.append(drugReqHPatRid);
        sb.append(";");

        sb.append("\n\tdrugReqHBillHRID: Integer=");
        sb.append(drugReqHBillHRID);
        sb.append(";");

        sb.append("\n\tdrugReqHBillNo: String=");
        sb.append(drugReqHBillNo);
        sb.append(";");

        sb.append("\n\tdrugReqHProcedureRid: Integer=");
        sb.append(drugReqHProcedureRid);
        sb.append(";");

        sb.append("\n\tdrugReqHStatus: Integer=");
        sb.append(drugReqHStatus);
        sb.append(";");

        sb.append("\n\tdrugReqHState: Integer=");
        sb.append(drugReqHState);
        sb.append(";");

        sb.append("\n\tdrugReqHType: Integer=");
        sb.append(drugReqHType);
        sb.append(";");

        sb.append("\n\tdrugReqHOpCheck: Integer=");
        sb.append(drugReqHOpCheck);
        sb.append(";");

        sb.append("\n\tdrugReqHfollowupDate: Date=");
        sb.append(drugReqHfollowupDate);
        sb.append(";");

        sb.append("\n\tdrugReqHComments: String=");
        sb.append(drugReqHComments);
        sb.append(";");

        return sb.toString();
    }
}
