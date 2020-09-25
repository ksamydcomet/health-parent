package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "t_drug_request_d")
public class DrugRequestDData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DRUG_REQ_D_RID", updatable = false, insertable = false)
    private Integer id;

    @Column(name = "DRUG_REQ_D_SRH_RID", updatable = false)
    private Integer druReqDSrhRID;

    @Column(name = "DRUG_REQ_D_ITEM_RID")
    private Integer drugReqDItemRID;

    @Column(name = "DRUG_REQ_D_ITEM_NAME")
    private String drugReqDItemName;

    @Column(name = "DRUG_REQ_D_ITEM_QTY")
    private Float drugReqDItemQty;

    @Column(name = "DRUG_REQ_D_ITEM_PRICE")
    private Float drugReqDItemPrice;

    @Column(name = "DRUG_REQ_D_ITEM_PACKAGE_RID")
    private Integer drugReqDItemPackageRid;

    @Column(name = "DRUG_REQ_D_ITEM_BATCH_NO")
    private String drugReqDItemBatchNo;

    @Column(name = "DRUG_REQ_D_ITEM_EXPIRY_DATE")
    private String drugReqDItemExpiryDate;

    @Column(name = "DRUG_REQ_D_ITEM_ISSUED_QTY")
    private Integer drugReqDItemIssuedQty;

    @Column(name = "DRUG_REQ_D_ITEM_BALANCE_QTY")
    private Integer drugReqDItemBalanceQty;

    @Column(name = "DRUG_REQ_D_PROCESS_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date drugReqDProcessDate;

    @Column(name = "DRUG_REQ_D_MRNG")
    private Integer drugReqDMrng;

    @Column(name = "DRUG_REQ_D_AFTERNOON")
    private Integer drugReqDAfternoon;

    @Column(name = "DRUG_REQ_D_EVE")
    private Integer drugReqDEve;

    @Column(name = "DRUG_REQ_D_NIGHT")
    private Integer drugReqDNight;

    @Column(name = "DRUG_REQ_D_BILL_D_RID")
    private Integer drugReqDBillDRID;

    public DrugRequestDData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDruReqDSrhRID() {
        return druReqDSrhRID;
    }

    public void setDruReqDSrhRID(Integer druReqDSrhRID) {
        this.druReqDSrhRID = druReqDSrhRID;
    }

    public Integer getDrugReqDItemRID() {
        return drugReqDItemRID;
    }

    public void setDrugReqDItemRID(Integer drugReqDItemRID) {
        this.drugReqDItemRID = drugReqDItemRID;
    }

    public String getDrugReqDItemName() {
        return drugReqDItemName;
    }

    public void setDrugReqDItemName(String drugReqDItemName) {
        this.drugReqDItemName = drugReqDItemName;
    }

    public Float getDrugReqDItemQty() {
        return drugReqDItemQty;
    }

    public void setDrugReqDItemQty(Float drugReqDItemQty) {
        this.drugReqDItemQty = drugReqDItemQty;
    }

    public Float getDrugReqDItemPrice() {
        return drugReqDItemPrice;
    }

    public void setDrugReqDItemPrice(Float drugReqDItemPrice) {
        this.drugReqDItemPrice = drugReqDItemPrice;
    }

    public Integer getDrugReqDItemPackageRid() {
        return drugReqDItemPackageRid;
    }

    public void setDrugReqDItemPackageRid(Integer drugReqDItemPackageRid) {
        this.drugReqDItemPackageRid = drugReqDItemPackageRid;
    }

    public String getDrugReqDItemBatchNo() {
        return drugReqDItemBatchNo;
    }

    public void setDrugReqDItemBatchNo(String drugReqDItemBatchNo) {
        this.drugReqDItemBatchNo = drugReqDItemBatchNo;
    }

    public String getDrugReqDItemExpiryDate() {
        return drugReqDItemExpiryDate;
    }

    public void setDrugReqDItemExpiryDate(String drugReqDItemExpiryDate) {
        this.drugReqDItemExpiryDate = drugReqDItemExpiryDate;
    }

    public Integer getDrugReqDItemIssuedQty() {
        return drugReqDItemIssuedQty;
    }

    public void setDrugReqDItemIssuedQty(Integer drugReqDItemIssuedQty) {
        this.drugReqDItemIssuedQty = drugReqDItemIssuedQty;
    }

    public Integer getDrugReqDItemBalanceQty() {
        return drugReqDItemBalanceQty;
    }

    public void setDrugReqDItemBalanceQty(Integer drugReqDItemBalanceQty) {
        this.drugReqDItemBalanceQty = drugReqDItemBalanceQty;
    }

    public Date getDrugReqDProcessDate() {
        return drugReqDProcessDate;
    }

    public void setDrugReqDProcessDate(Date drugReqDProcessDate) {
        this.drugReqDProcessDate = drugReqDProcessDate;
    }

    public Integer getDrugReqDMrng() {
        return drugReqDMrng;
    }

    public void setDrugReqDMrng(Integer drugReqDMrng) {
        this.drugReqDMrng = drugReqDMrng;
    }

    public Integer getDrugReqDAfternoon() {
        return drugReqDAfternoon;
    }

    public void setDrugReqDAfternoon(Integer drugReqDAfternoon) {
        this.drugReqDAfternoon = drugReqDAfternoon;
    }

    public Integer getDrugReqDEve() {
        return drugReqDEve;
    }

    public void setDrugReqDEve(Integer drugReqDEve) {
        this.drugReqDEve = drugReqDEve;
    }

    public Integer getDrugReqDNight() {
        return drugReqDNight;
    }

    public void setDrugReqDNight(Integer drugReqDNight) {
        this.drugReqDNight = drugReqDNight;
    }

    public Integer getDrugReqDBillDRID() {
        return drugReqDBillDRID;
    }

    public void setDrugReqDBillDRID(Integer drugReqDBillDRID) {
        this.drugReqDBillDRID = drugReqDBillDRID;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tdruReqDSrhRID: Integer=");
        sb.append(druReqDSrhRID);
        sb.append(";");

        sb.append("\n\tdrugReqDItemRID: Integer=");
        sb.append(drugReqDItemRID);
        sb.append(";");

        sb.append("\n\tdrugReqDItemName: String=");
        sb.append(drugReqDItemName);
        sb.append(";");

        sb.append("\n\tdrugReqDItemQty: Integer=");
        sb.append(drugReqDItemQty);
        sb.append(";");

        sb.append("\n\tdrugReqDItemPrice: Float=");
        sb.append(drugReqDItemPrice);
        sb.append(";");

        sb.append("\n\tdrugReqDItemPackageRid: Integer=");
        sb.append(drugReqDItemPackageRid);
        sb.append(";");

        sb.append("\n\tdrugReqDProcessDate: Date=");
        sb.append(drugReqDProcessDate);
        sb.append(";");

        sb.append("\n\tdrugReqDMrng: Integer=");
        sb.append(drugReqDMrng);
        sb.append(";");

        sb.append("\n\tdrugReqDAfternoon: Integer=");
        sb.append(drugReqDAfternoon);
        sb.append(";");

        sb.append("\n\tdrugReqDEve: Integer=");
        sb.append(drugReqDEve);
        sb.append(";");

        sb.append("\n\tdrugReqDNight: Integer=");
        sb.append(drugReqDNight);
        sb.append(";");

        sb.append("\n\tdrugReqDBillDRID: Integer=");
        sb.append(drugReqDBillDRID);
        sb.append(";");

        return sb.toString();

    }
}
