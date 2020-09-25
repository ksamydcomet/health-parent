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

/**
 *
 * @author Adhithya
 */
@Entity
@Table(name = "t_sales_d")
public class SalesDData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SAL_D_RID")
    private Integer id;

    @Column(name = "SAL_H_RID", updatable = false)
    private Integer salHRID;

    @Column(name = "SAL_SKU_RID")
    private Integer salSkuRID;

    @Column(name = "SAL_SKU_SEC_RID")
    private Integer salSkuSecRID;

    @Column(name = "SAL_ITEM_NAME")
    private String salItemName;

    @Column(name = "SAL_BATCH_NO")
    private String salBatchNo;

    @Column(name = "SAL_EXP_DATE")
    private String salExpDate;

    @Column(name = "SAL_ST_LOC")
    private String salStLoc;

    @Column(name = "SAL_STK_QTY")
    private Float salStockQty;

    @Column(name = "SAL_QTY")
    private Float salQty;

    @Column(name = "SAL_RETURN_QTY")
    private Float salReturnQty;

    @Column(name = "SAL_UOM")
    private String salUom;

    @Column(name = "SAL_RATE")
    private Float salRate;

    @Column(name = "SAL_GROSS_AMT")
    private Float salGrossAmount;

    @Column(name = "SAL_DISC")
    private Float salDiscount;

    @Column(name = "SAL_TAX")
    private Float salTax;

    @Column(name = "SAL_NET_AMT")
    private Float salNetAmount;

    @Column(name = "SAL_BASE_UOM")
    private Integer salBaseUom;

    @Column(name = "SAL_CUR_DATETIME_SALE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar salCurrentDateTimeSale;

    @Column(name = "SAL_CUR_DATETIME_RETURN")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar salCurrentDateTimeReturn;

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

    public Calendar getSalCurrentDateTimeSale() {
        return salCurrentDateTimeSale;
    }

    public void setSalCurrentDateTimeSale(Calendar salCurrentDateTimeSale) {
        this.salCurrentDateTimeSale = salCurrentDateTimeSale;
    }

    public Calendar getSalCurrentDateTimeReturn() {
        return salCurrentDateTimeReturn;
    }

    public void setSalCurrentDateTimeReturn(Calendar salCurrentDateTimeReturn) {
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

        sb.append("\n\tsalStockQty: Float=");
        sb.append(salStockQty);
        sb.append(";");

        sb.append("\n\tsalQty: Float=");
        sb.append(salQty);
        sb.append(";");

        sb.append("\n\tsalReturnQty: Float=");
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

        sb.append("\n\tsalCurrentDateTimeSale: Calendar=");
        sb.append(salCurrentDateTimeSale);
        sb.append(";");

        sb.append("\n\tsalCurrentDateTimeReturn: Calendar=");
        sb.append(salCurrentDateTimeReturn);
        sb.append(";");

        return sb.toString();

    }
}
