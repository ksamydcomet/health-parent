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
@Table(name = "t_service_request")
public class ServiceRequestData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SER_REQ_RID", updatable = false)
    private Integer serReqRid;

    @Column(name = "SER_REQ_REQH_RID", updatable = false)
    private Integer serReqhRid;

    @Column(name = "SER_REQ_ITEM_RID", updatable = false)
    private Integer serReqItemRID;

    @Column(name = "SER_REQ_ITEM_GROUP_RID", updatable = false)
    private Integer serReqItemGroupRid;

    @Column(name = "SER_REQ_ITEM_NAME", updatable = false)
    private String serReqItemName;

    @Column(name = "SER_REQ_ITEM_PRICE", updatable = false)
    private Float serReqItemPrice;

    @Column(name = "SER_REQ_SERVICE_TYPE", updatable = false)
    private Integer serType;

    @Column(name = "SER_REQ_SERVICE_PACKAGE_RID", updatable = false)
    private Integer serReqServicePackageRid;

    @Column(name = "SER_REQ_ITEM_QTY", updatable = false)
    private Integer serReqItemQty;

    @Column(name = "SER_REQ_MRNG", updatable = false)
    private Integer serReqMorning;

    @Column(name = "SER_REQ_AFTERNOON", updatable = false)
    private Integer serReqAfternoon;

    @Column(name = "SER_REQ_EVE", updatable = false)
    private Integer serReqEvening;

    @Column(name = "SER_REQ_NIGHT", updatable = false)
    private Integer serReqNight;

    @Column(name = "SER_REQ_BILL_H_RID")
    private Integer serReqBillHRID;

    @Column(name = "SER_REQ_BILL_D_RID")
    private Integer serReqBillDRID;

    public ServiceRequestData() {

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

    public void setSerReqItemRID(Integer serReqItemRID) {
        this.serReqItemRID = serReqItemRID;
    }

    public Integer getSerReqItemGroupRid() {
        return serReqItemGroupRid;
    }

    public void setSerReqItemGroupRid(Integer serReqItemGroupRid) {
        this.serReqItemGroupRid = serReqItemGroupRid;
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

    public Integer getSerReqServicePackageRid() {
        return serReqServicePackageRid;
    }

    public void setSerReqServicePackageRid(Integer serReqServicePackageRid) {
        this.serReqServicePackageRid = serReqServicePackageRid;
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

        sb.append("\n\tserReqItemName: String=");
        sb.append(serReqItemName);
        sb.append(";");

        sb.append("\n\tserReqItemPrice: Float=");
        sb.append(serReqItemPrice);
        sb.append(";");

        sb.append("\n\tserType: Integer=");
        sb.append(serType);
        sb.append(";");

        sb.append("\n\tserReqServicePackageRid: Integer=");
        sb.append(serReqServicePackageRid);
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

        return sb.toString();

    }
}
