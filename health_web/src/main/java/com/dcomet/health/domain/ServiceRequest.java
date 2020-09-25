package com.dcomet.health.domain;

import java.io.Serializable;

public class ServiceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer serReqRid;
    private Integer serReqhRid;
    private Integer serReqItemRID;
    private Integer serReqItemGroupRid;
    private Integer serReqServicePackageRid;
    private String serReqItemName;
    private Float serReqItemPrice;
    private Integer serType;
    private Integer serReqItemQty;
    private Integer serReqMorning;
    private Integer serReqAfternoon;
    private Integer serReqEvening;
    private Integer serReqNight;
    private Integer serReqBillHRID;
    private Integer serReqBillDRID;

    private Float serReqServiceCost;

    public ServiceRequest() {

    }

    public Integer getSerReqRid() {
        return serReqRid;
    }

    public void setSerReqRid(Integer serReqRid) {
        this.serReqRid = serReqRid;
    }

    public Integer getSerReqhRid() {
        return serReqhRid;
    }

    public void setSerReqhRid(Integer serReqhRid) {
        this.serReqhRid = serReqhRid;
    }

    public Integer getSerReqItemRID() {
        return serReqItemRID;
    }

    public Integer getSerReqItemGroupRid() {
        return serReqItemGroupRid;
    }

    public void setSerReqItemGroupRid(Integer serReqItemGroupRid) {
        this.serReqItemGroupRid = serReqItemGroupRid;
    }

    public Integer getSerReqServicePackageRid() {
        return serReqServicePackageRid;
    }

    public void setSerReqServicePackageRid(Integer serReqServicePackageRid) {
        this.serReqServicePackageRid = serReqServicePackageRid;
    }

    public void setSerReqItemRID(Integer serReqItemRID) {
        this.serReqItemRID = serReqItemRID;
    }

    public String getSerReqItemName() {
        return serReqItemName;
    }

    public void setSerReqItemName(String serReqItemName) {
        this.serReqItemName = serReqItemName;
    }

    public Float getSerReqItemPrice() {
        return serReqItemPrice;
    }

    public void setSerReqItemPrice(Float serReqItemPrice) {
        this.serReqItemPrice = serReqItemPrice;
    }

    public Integer getSerType() {
        return serType;
    }

    public void setSerType(Integer serType) {
        this.serType = serType;
    }

    public Integer getSerReqItemQty() {
        return serReqItemQty;
    }

    public void setSerReqItemQty(Integer serReqItemQty) {
        this.serReqItemQty = serReqItemQty;
    }

    public Integer getSerReqMorning() {
        return serReqMorning;
    }

    public void setSerReqMorning(Integer serReqMorning) {
        this.serReqMorning = serReqMorning;
    }

    public Integer getSerReqAfternoon() {
        return serReqAfternoon;
    }

    public void setSerReqAfternoon(Integer serReqAfternoon) {
        this.serReqAfternoon = serReqAfternoon;
    }

    public Integer getSerReqEvening() {
        return serReqEvening;
    }

    public void setSerReqEvening(Integer serReqEvening) {
        this.serReqEvening = serReqEvening;
    }

    public Integer getSerReqNight() {
        return serReqNight;
    }

    public void setSerReqNight(Integer serReqNight) {
        this.serReqNight = serReqNight;
    }

    public Integer getSerReqBillHRID() {
        return serReqBillHRID;
    }

    public void setSerReqBillHRID(Integer serReqBillHRID) {
        this.serReqBillHRID = serReqBillHRID;
    }

    public Integer getSerReqBillDRID() {
        return serReqBillDRID;
    }

    public void setSerReqBillDRID(Integer serReqBillDRID) {
        this.serReqBillDRID = serReqBillDRID;
    }

    public Float getSerReqServiceCost() {
        return serReqServiceCost;
    }

    public void setSerReqServiceCost(Float serReqServiceCost) {
        this.serReqServiceCost = serReqServiceCost;
    }    

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tserReqRid: Integer=");
        sb.append(serReqRid);
        sb.append(";");

        sb.append("\n\tserReqhRid: Integer=");
        sb.append(serReqhRid);
        sb.append(";");

        sb.append("\n\tserReqItemRID: Integer=");
        sb.append(serReqItemRID);
        sb.append(";");

        sb.append("\n\tserReqItemGroupRid: Integer=");
        sb.append(serReqItemGroupRid);
        sb.append(";");

        sb.append("\n\tserReqServicePackageRid: Integer=");
        sb.append(serReqServicePackageRid);
        sb.append(";");

        sb.append("\n\tserReqItemName: String=");
        sb.append(serReqItemName);
        sb.append(";");

        sb.append("\n\tserReqItemPrice: Float=");
        sb.append(serReqItemPrice);
        sb.append(";");

        sb.append("\n\tserType: Integer=");
        sb.append(serType);
        sb.append(";");

        sb.append("\n\tserReqItemQty: Integer=");
        sb.append(serReqItemQty);
        sb.append(";");

        sb.append("\n\tserReqMorning: Integer=");
        sb.append(serReqMorning);
        sb.append(";");

        sb.append("\n\tserReqAfternoon: Integer=");
        sb.append(serReqAfternoon);
        sb.append(";");

        sb.append("\n\tserReqEvening: Integer=");
        sb.append(serReqEvening);
        sb.append(";");

        sb.append("\n\tserReqNight: Integer=");
        sb.append(serReqNight);
        sb.append(";");

        sb.append("\n\tserReqBillHRID: Integer=");
        sb.append(serReqBillHRID);
        sb.append(";");

        sb.append("\n\tserReqBillDRID: Integer=");
        sb.append(serReqBillDRID);
        sb.append(";");

        sb.append("\n\tserReqServiceCost: Integer=");
        sb.append(serReqServiceCost);
        sb.append(";");

        return sb.toString();

    }
}
