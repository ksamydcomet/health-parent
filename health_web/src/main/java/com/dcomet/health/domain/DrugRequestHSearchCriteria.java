package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Date;

/**
 *
 * @author Dev1
 */
public class DrugRequestHSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer drugReqHOpRID;
    private Integer drugReqHOpVBisitRID;
    private String drugReqHPatMrn;
    private String drugReqHPatName;
    private Integer drugReqHPatRid;
    private Integer drugReqHBillHRID;
    private Integer drugReqHProcedureRid;
    private Integer drugReqHStatus;
    private Integer drugReqHState;
    private Integer drugReqHType;
    private Integer drugReqHOpCheck;
    private String drugReqHfollowupDate;
    private String drugReqHComments;
    private Integer drugReqHEntityRID;
    private Integer drugReqHLabEnHRID;

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

    public String getDrugReqHfollowupDate() {
        return drugReqHfollowupDate;
    }

    public void setDrugReqHfollowupDate(String drugReqHfollowupDate) {
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

    public Integer getDrugReqHProcedureRid() {
        return drugReqHProcedureRid;
    }

    public void setDrugReqHProcedureRid(Integer drugReqHProcedureRid) {
        this.drugReqHProcedureRid = drugReqHProcedureRid;
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

        sb.append("\n\tdrugReqHStatus: Integer=");
        sb.append(drugReqHStatus);
        sb.append(";");

        sb.append("\n\tdrugReqHState: Integer=");
        sb.append(drugReqHState);
        sb.append(";");

        sb.append("\n\tdrugReqHType: Integer=");
        sb.append(drugReqHType);
        sb.append(";");

        sb.append("\n\tdrugReqHProcedureRid: Integer=");
        sb.append(drugReqHProcedureRid);
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
