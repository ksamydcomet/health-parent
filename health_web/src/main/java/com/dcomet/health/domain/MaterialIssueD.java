package com.dcomet.health.domain;

import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class MaterialIssueD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer matIssueHRid;
    private Integer matSkuRid;
    private String matItemName;
    private String matBatchNo;
    private String matExpDate;
    private Integer matOrderQty;
    private Integer matIssuedQty;
    private Integer matStkQty;
    private Integer matReturnQty;
    private Float matRate;
    private Float matGrossAmount;
    private Float matDiscAmount;
    private Float matTaxAmount;
    private Float matNetAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatIssueHRid() {
        return matIssueHRid;
    }

    public void setMatIssueHRid(Integer matIssueHRid) {
        this.matIssueHRid = matIssueHRid;
    }

    public Integer getMatSkuRid() {
        return matSkuRid;
    }

    public void setMatSkuRid(Integer matSkuRid) {
        this.matSkuRid = matSkuRid;
    }

    public String getMatItemName() {
        return matItemName;
    }

    public void setMatItemName(String matItemName) {
        this.matItemName = matItemName;
    }

    public String getMatBatchNo() {
        return matBatchNo;
    }

    public void setMatBatchNo(String matBatchNo) {
        this.matBatchNo = matBatchNo;
    }

    public String getMatExpDate() {
        return matExpDate;
    }

    public void setMatExpDate(String matExpDate) {
        this.matExpDate = matExpDate;
    }

    public Integer getMatOrderQty() {
        return matOrderQty;
    }

    public void setMatOrderQty(Integer matOrderQty) {
        this.matOrderQty = matOrderQty;
    }

    public Integer getMatIssuedQty() {
        return matIssuedQty;
    }

    public void setMatIssuedQty(Integer matIssuedQty) {
        this.matIssuedQty = matIssuedQty;
    }

    public Integer getMatStkQty() {
        return matStkQty;
    }

    public void setMatStkQty(Integer matStkQty) {
        this.matStkQty = matStkQty;
    }

    public Integer getMatReturnQty() {
        return matReturnQty;
    }

    public void setMatReturnQty(Integer matReturnQty) {
        this.matReturnQty = matReturnQty;
    }

    public Float getMatRate() {
        return matRate;
    }

    public void setMatRate(Float matRate) {
        this.matRate = matRate;
    }

    public Float getMatGrossAmount() {
        return matGrossAmount;
    }

    public void setMatGrossAmount(Float matGrossAmount) {
        this.matGrossAmount = matGrossAmount;
    }

    public Float getMatDiscAmount() {
        return matDiscAmount;
    }

    public void setMatDiscAmount(Float matDiscAmount) {
        this.matDiscAmount = matDiscAmount;
    }

    public Float getMatTaxAmount() {
        return matTaxAmount;
    }

    public void setMatTaxAmount(Float matTaxAmount) {
        this.matTaxAmount = matTaxAmount;
    }

    public Float getMatNetAmount() {
        return matNetAmount;
    }

    public void setMatNetAmount(Float matNetAmount) {
        this.matNetAmount = matNetAmount;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tmatIssueHRid: Integer=");
        sb.append(matIssueHRid);
        sb.append(";");

        sb.append("\n\tmatSkuRid: Integer=");
        sb.append(matSkuRid);
        sb.append(";");

        sb.append("\n\tmatItemName: String=");
        sb.append(matItemName);
        sb.append(";");

        sb.append("\n\tmatBatchNo: String=");
        sb.append(matBatchNo);
        sb.append(";");

        sb.append("\n\tmatExpDate: String=");
        sb.append(matExpDate);
        sb.append(";");

        sb.append("\n\tmatOrderQty: Integer=");
        sb.append(matOrderQty);
        sb.append(";");

        sb.append("\n\tmatIssuedQty: Integer=");
        sb.append(matIssuedQty);
        sb.append(";");

        sb.append("\n\tmatStkQty: Integer=");
        sb.append(matStkQty);
        sb.append(";");

        sb.append("\n\tmatReturnQty: Integer=");
        sb.append(matReturnQty);
        sb.append(";");

        sb.append("\n\tmatRate: Float=");
        sb.append(matRate);
        sb.append(";");

        sb.append("\n\tmatGrossAmount: Float=");
        sb.append(matGrossAmount);
        sb.append(";");

        sb.append("\n\tmatDiscAmount: Float=");
        sb.append(matDiscAmount);
        sb.append(";");

        sb.append("\n\tmatTaxAmount: Float=");
        sb.append(matTaxAmount);
        sb.append(";");

        sb.append("\n\tmatNetAmount: Float=");
        sb.append(matNetAmount);
        sb.append(";");

        return sb.toString();
    }

}
