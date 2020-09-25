package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Adhithya
 */
@Entity
@Table(name = "t_sales_return_d")
public class SalesReturnDData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SRD_RID")
    private Integer id;

    @Column(name = "SRD_SRH_RID", updatable = false)
    private Integer srdSrhRID;

    @Column(name = "SRD_SKU_RID")
    private Integer srdSkuRID;

    @Column(name = "SRD_SKU_SEC_RID")
    private Integer srdSkuSecRID;

    @Column(name = "SRD_SKU_NAME")
    private String srdSkuName;

    @Column(name = "SRD_ITEM_BATCH_NO")
    private String srdItemBatchNo;

    @Column(name = "SRD_ITEM_EXP_DATE")
    private String srdItemExpDate;

    @Column(name = "SRD_BILL_RID")
    private Integer srdBIllRID;

    @Column(name = "SRD_ISSH_RID")
    private Integer srdIsshRID;

    @Column(name = "SRD_ISSD_RID")
    private Integer srdIssdRID;

    @Column(name = "SRD_RETURN_QTY")
    private Float srdReturnQty;

    @Column(name = "SRD_RETURN_RATE")
    private Float srdReturnRate;

    @Column(name = "SRD_TAX_AMT")
    private Float srdTaxAmount;

    @Column(name = "SRD_RETURN_NET_AMT")
    private Float srdReturnNetAmount;

    @Column(name = "SRD_CONTEXT_TYPE")
    private String srdContextType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSrdSrhRID() {
        return srdSrhRID;
    }

    public void setSrdSrhRID(Integer srdSrhRID) {
        this.srdSrhRID = srdSrhRID;
    }

    public Integer getSrdSkuRID() {
        return srdSkuRID;
    }

    public void setSrdSkuRID(Integer srdSkuRID) {
        this.srdSkuRID = srdSkuRID;
    }

    public Integer getSrdSkuSecRID() {
        return srdSkuSecRID;
    }

    public void setSrdSkuSecRID(Integer srdSkuSecRID) {
        this.srdSkuSecRID = srdSkuSecRID;
    }

    public String getSrdSkuName() {
        return srdSkuName;
    }

    public void setSrdSkuName(String srdSkuName) {
        this.srdSkuName = srdSkuName;
    }

    public String getSrdItemBatchNo() {
        return srdItemBatchNo;
    }

    public void setSrdItemBatchNo(String srdItemBatchNo) {
        this.srdItemBatchNo = srdItemBatchNo;
    }

    public String getSrdItemExpDate() {
        return srdItemExpDate;
    }

    public void setSrdItemExpDate(String srdItemExpDate) {
        this.srdItemExpDate = srdItemExpDate;
    }

    public Integer getSrdBIllRID() {
        return srdBIllRID;
    }

    public void setSrdBIllRID(Integer srdBIllRID) {
        this.srdBIllRID = srdBIllRID;
    }

    public Integer getSrdIsshRID() {
        return srdIsshRID;
    }

    public void setSrdIsshRID(Integer srdIsshRID) {
        this.srdIsshRID = srdIsshRID;
    }

    public Integer getSrdIssdRID() {
        return srdIssdRID;
    }

    public void setSrdIssdRID(Integer srdIssdRID) {
        this.srdIssdRID = srdIssdRID;
    }

    public Float getSrdReturnQty() {
        return srdReturnQty;
    }

    public void setSrdReturnQty(Float srdReturnQty) {
        this.srdReturnQty = srdReturnQty;
    }

    public Float getSrdReturnRate() {
        return srdReturnRate;
    }

    public void setSrdReturnRate(Float srdReturnRate) {
        this.srdReturnRate = srdReturnRate;
    }

    public Float getSrdTaxAmount() {
        return srdTaxAmount;
    }

    public void setSrdTaxAmount(Float srdTaxAmount) {
        this.srdTaxAmount = srdTaxAmount;
    }

    public Float getSrdReturnNetAmount() {
        return srdReturnNetAmount;
    }

    public void setSrdReturnNetAmount(Float srdReturnNetAmount) {
        this.srdReturnNetAmount = srdReturnNetAmount;
    }

    public String getSrdContextType() {
        return srdContextType;
    }

    public void setSrdContextType(String srdContextType) {
        this.srdContextType = srdContextType;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsrdSrhRID: Integer=");
        sb.append(srdSrhRID);
        sb.append(";");

        sb.append("\n\tsrdSkuRID: Integer=");
        sb.append(srdSkuRID);
        sb.append(";");

        sb.append("\n\tsrdSkuSecRID: Integer=");
        sb.append(srdSkuSecRID);
        sb.append(";");

        sb.append("\n\trsrdSkuName: String=");
        sb.append(srdSkuName);
        sb.append(";");

        sb.append("\n\tsrdBIllRID: Integer=");
        sb.append(srdSrhRID);
        sb.append(";");

        sb.append("\n\tsrdIsshRID: Integer=");
        sb.append(srdIsshRID);
        sb.append(";");

        sb.append("\n\tsrdIssdRID: Integer=");
        sb.append(srdIssdRID);
        sb.append(";");

        sb.append("\n\tsrdReturnQty: Float=");
        sb.append(srdReturnQty);
        sb.append(";");

        sb.append("\n\tsrdReturnRate: Float=");
        sb.append(srdReturnRate);
        sb.append(";");

        sb.append("\n\tsrdTaxAmount: Float=");
        sb.append(srdTaxAmount);
        sb.append(";");

        sb.append("\n\tsrdReturnNetAmount: Float=");
        sb.append(srdReturnNetAmount);
        sb.append(";");

        sb.append("\n\tsrdContextType: String=");
        sb.append(srdContextType);
        sb.append(";");

        return sb.toString();
    }
}
