package com.dcomet.health.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dev1
 */
public class DrugRequestD implements Serializable {

    private Integer id;
    private Integer druReqDSrhRID;
    private Integer drugReqDItemRID;
    private String drugReqDItemName;
    private Float drugReqDItemQty;
    private Float drugReqDItemPrice;
    private Integer drugReqDItemPackageRid;
    private String drugReqDItemBatchNo;
    private String drugReqDItemExpiryDate;
    private Integer drugReqDItemIssuedQty;
    private Integer drugReqDItemBalanceQty;
    private String drugReqDProcessDate;
    private Integer drugReqDMrng;
    private Integer drugReqDAfternoon;
    private Integer drugReqDEve;
    private Integer drugReqDNight;
    private Integer drugReqDBillDRID;

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

    public String getDrugReqDProcessDate() {
        return drugReqDProcessDate;
    }

    public void setDrugReqDProcessDate(String drugReqDProcessDate) {
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

        sb.append("\n\tdrugReqDItemQty: Float=");
        sb.append(drugReqDItemQty);
        sb.append(";");

        sb.append("\n\tdrugReqDItemPrice: Float=");
        sb.append(drugReqDItemPrice);
        sb.append(";");

        sb.append("\n\tdrugReqDItemPackageRid: Integer=");
        sb.append(drugReqDItemPackageRid);
        sb.append(";");

        sb.append("\n\tdrugReqDProcessDate: String=");
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
