package com.dcomet.health.domain;

import java.io.Serializable;

/**
 *
 * @author KS
 */
public class SalesD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer salHRID;
    private Integer salSkuRID;
    private Integer salSkuSecRID;
    private String salItemName;
    private String salBatchNo;
    private String salExpDate;
    private String salStLoc;
    private Float salStockQty;
    private Float salQty;
    private Float salReturnQty;
    private String salUom;
    private Float salRate;
    private Float salGrossAmount;
    private Float salDiscount;
    private Float salTax;
    private Float salNetAmount;
    private Integer salBaseUom;
    private String salCurrentDateTimeSale;
    private String salCurrentDateTimeReturn;

    public SalesD() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalHRID() {
        return salHRID;
    }

    public void setSalHRID(Integer salHRID) {
        this.salHRID = salHRID;
    }

    public Integer getSalSkuRID() {
        return salSkuRID;
    }

    public void setSalSkuRID(Integer salSkuRID) {
        this.salSkuRID = salSkuRID;
    }

    public Integer getSalSkuSecRID() {
        return salSkuSecRID;
    }

    public void setSalSkuSecRID(Integer salSkuSecRID) {
        this.salSkuSecRID = salSkuSecRID;
    }

    public String getSalItemName() {
        return salItemName;
    }

    public void setSalItemName(String salItemName) {
        this.salItemName = salItemName;
    }

    public String getSalBatchNo() {
        return salBatchNo;
    }

    public void setSalBatchNo(String salBatchNo) {
        this.salBatchNo = salBatchNo;
    }

    public String getSalExpDate() {
        return salExpDate;
    }

    public void setSalExpDate(String salExpDate) {
        this.salExpDate = salExpDate;
    }

    public String getSalStLoc() {
        return salStLoc;
    }

    public void setSalStLoc(String salStLoc) {
        this.salStLoc = salStLoc;
    }

    public Float getSalStockQty() {
        return salStockQty;
    }

    public void setSalStockQty(Float salStockQty) {
        this.salStockQty = salStockQty;
    }

    public Float getSalQty() {
        return salQty;
    }

    public void setSalQty(Float salQty) {
        this.salQty = salQty;
    }

    public Float getSalReturnQty() {
        return salReturnQty;
    }

    public void setSalReturnQty(Float salReturnQty) {
        this.salReturnQty = salReturnQty;
    }

    public String getSalUom() {
        return salUom;
    }

    public void setSalUom(String salUom) {
        this.salUom = salUom;
    }

    public Float getSalRate() {
        return salRate;
    }

    public void setSalRate(Float salRate) {
        this.salRate = salRate;
    }

    public Float getSalGrossAmount() {
        return salGrossAmount;
    }

    public void setSalGrossAmount(Float salGrossAmount) {
        this.salGrossAmount = salGrossAmount;
    }

    public Float getSalTax() {
        return salTax;
    }

    public void setSalTax(Float salTax) {
        this.salTax = salTax;
    }

    public Float getSalDiscount() {
        return salDiscount;
    }

    public void setSalDiscount(Float salDiscount) {
        this.salDiscount = salDiscount;
    }

    public Float getSalNetAmount() {
        return salNetAmount;
    }

    public void setSalNetAmount(Float salNetAmount) {
        this.salNetAmount = salNetAmount;
    }

    public Integer getSalBaseUom() {
        return salBaseUom;
    }

    public void setSalBaseUom(Integer salBaseUom) {
        this.salBaseUom = salBaseUom;
    }

    public String getSalCurrentDateTimeSale() {
        return salCurrentDateTimeSale;
    }

    public void setSalCurrentDateTimeSale(String salCurrentDateTimeSale) {
        this.salCurrentDateTimeSale = salCurrentDateTimeSale;
    }

    public String getSalCurrentDateTimeReturn() {
        return salCurrentDateTimeReturn;
    }

    public void setSalCurrentDateTimeReturn(String salCurrentDateTimeReturn) {
        this.salCurrentDateTimeReturn = salCurrentDateTimeReturn;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsalHRID: Integer=");
        sb.append(salHRID);
        sb.append(";");

        sb.append("\n\tsalSkuRID: Integer=");
        sb.append(salSkuRID);
        sb.append(";");

        sb.append("\n\tsalSkuSecRID: Integer=");
        sb.append(salSkuSecRID);
        sb.append(";");

        sb.append("\n\tsalItemName: String=");
        sb.append(salItemName);
        sb.append(";");

        sb.append("\n\tsalBatchNo: String=");
        sb.append(salBatchNo);
        sb.append(";");

        sb.append("\n\tsalExpDate: String=");
        sb.append(salExpDate);
        sb.append(";");

        sb.append("\n\tsalStLoc: String=");
        sb.append(salStLoc);
        sb.append(";");

        sb.append("\n\tsalStockQty: Integer=");
        sb.append(salStockQty);
        sb.append(";");

        sb.append("\n\tsalQty: Integer=");
        sb.append(salQty);
        sb.append(";");

        sb.append("\n\tsalReturnQty: Integer=");
        sb.append(salReturnQty);
        sb.append(";");

        sb.append("\n\tsalUom: String=");
        sb.append(salUom);
        sb.append(";");

        sb.append("\n\tsalRate: Float=");
        sb.append(salRate);
        sb.append(";");

        sb.append("\n\tsalGrossAmount: Float=");
        sb.append(salGrossAmount);
        sb.append(";");

        sb.append("\n\tsalDiscount: Float=");
        sb.append(salDiscount);
        sb.append(";");

        sb.append("\n\tsalNetAmount: Float=");
        sb.append(salNetAmount);
        sb.append(";");

        sb.append("\n\tsalBaseUom: Integer=");
        sb.append(salBaseUom);
        sb.append(";");

        sb.append("\n\tsalCurrentDateTimeSale: String=");
        sb.append(salCurrentDateTimeSale);
        sb.append(";");

        sb.append("\n\tsalCurrentDateTimeReturn: String=");
        sb.append(salCurrentDateTimeReturn);
        sb.append(";");

        return sb.toString();

    }
}
