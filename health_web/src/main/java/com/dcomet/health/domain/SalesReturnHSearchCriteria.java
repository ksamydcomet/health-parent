package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class SalesReturnHSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String srhDate;
    private Integer srhPatientRID;
    private String srhPatientMrn;
    private String srhPatientName;
    private Integer srhVisitRID;
    private Integer srhVisitType;
    private Float srhRoundoffAmount;
    private Float srhNetAmount;
    private Integer srhUnitRID;
    private Integer srhEntityRID;
    private String srhModifyDateTime;
    private Integer srhModifyUserRID;
    private String srhSeqNo;
    private Integer srhBIllRID;
    private String srhBillNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrhDate() {
        return srhDate;
    }

    public void setSrhDate(String srhDate) {
        this.srhDate = srhDate;
    }

    public Integer getSrhPatientRID() {
        return srhPatientRID;
    }

    public void setSrhPatientRID(Integer srhPatientRID) {
        this.srhPatientRID = srhPatientRID;
    }

    public String getSrhPatientMrn() {
        return srhPatientMrn;
    }

    public void setSrhPatientMrn(String srhPatientMrn) {
        this.srhPatientMrn = srhPatientMrn;
    }

    public String getSrhPatientName() {
        return srhPatientName;
    }

    public void setSrhPatientName(String srhPatientName) {
        this.srhPatientName = srhPatientName;
    }

    public Integer getSrhVisitRID() {
        return srhVisitRID;
    }

    public void setSrhVisitRID(Integer srhVisitRID) {
        this.srhVisitRID = srhVisitRID;
    }

    public Integer getSrhVisitType() {
        return srhVisitType;
    }

    public void setSrhVisitType(Integer srhVisitType) {
        this.srhVisitType = srhVisitType;
    }

    public Float getSrhRoundoffAmount() {
        return srhRoundoffAmount;
    }

    public void setSrhRoundoffAmount(Float srhRoundoffAmount) {
        this.srhRoundoffAmount = srhRoundoffAmount;
    }

    public Float getSrhNetAmount() {
        return srhNetAmount;
    }

    public void setSrhNetAmount(Float srhNetAmount) {
        this.srhNetAmount = srhNetAmount;
    }

    public Integer getSrhUnitRID() {
        return srhUnitRID;
    }

    public void setSrhUnitRID(Integer srhUnitRID) {
        this.srhUnitRID = srhUnitRID;
    }

    public Integer getSrhEntityRID() {
        return srhEntityRID;
    }

    public void setSrhEntityRID(Integer srhEntityRID) {
        this.srhEntityRID = srhEntityRID;
    }

    public String getSrhModifyDateTime() {
        return srhModifyDateTime;
    }

    public void setSrhModifyDateTime(String srhModifyDateTime) {
        this.srhModifyDateTime = srhModifyDateTime;
    }

    public Integer getSrhModifyUserRID() {
        return srhModifyUserRID;
    }

    public void setSrhModifyUserRID(Integer srhModifyUserRID) {
        this.srhModifyUserRID = srhModifyUserRID;
    }

    public String getSrhSeqNo() {
        return srhSeqNo;
    }

    public void setSrhSeqNo(String srhSeqNo) {
        this.srhSeqNo = srhSeqNo;
    }

    public Integer getSrhBIllRID() {
        return srhBIllRID;
    }

    public void setSrhBIllRID(Integer srhBIllRID) {
        this.srhBIllRID = srhBIllRID;
    }

    public String getSrhBillNo() {
        return srhBillNo;
    }

    public void setSrhBillNo(String srhBillNo) {
        this.srhBillNo = srhBillNo;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsrhDate: String=");
        sb.append(srhDate);
        sb.append(";");

        sb.append("\n\tsrhPatientMrn: String=");
        sb.append(srhPatientMrn);
        sb.append(";");

        sb.append("\n\tsrhPatientName: String=");
        sb.append(srhPatientName);
        sb.append(";");

        sb.append("\n\tsrhVisitRID: Integer=");
        sb.append(srhVisitRID);
        sb.append(";");

        sb.append("\n\tsrhVisitType: Integer=");
        sb.append(srhVisitType);
        sb.append(";");

        sb.append("\n\tsrhRoundoffAmount: Float=");
        sb.append(srhRoundoffAmount);
        sb.append(";");

        sb.append("\n\tsrhNetAmount: Float=");
        sb.append(srhNetAmount);
        sb.append(";");

        sb.append("\n\tsrhUnitRID: Integer=");
        sb.append(srhUnitRID);
        sb.append(";");

        sb.append("\n\tsrhEntityRID: Integer=");
        sb.append(srhEntityRID);
        sb.append(";");

        sb.append("\n\tsrhModifyDateTime: String=");
        sb.append(srhModifyDateTime);
        sb.append(";");

        sb.append("\n\tsrhModifyUserRID: Integer=");
        sb.append(srhModifyUserRID);
        sb.append(";");

        sb.append("\n\tsrhSeqNo: String=");
        sb.append(srhSeqNo);
        sb.append(";");

        sb.append("\n\tsrhBIllRID: Integer=");
        sb.append(srhBIllRID);
        sb.append(";");

        return sb.toString();
    }
}
