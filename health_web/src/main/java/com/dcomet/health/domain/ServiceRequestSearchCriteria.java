package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev3
 */
public class ServiceRequestSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer serReqOpRID;
    private Integer serReqOpVisitRID;
    private Integer serReqBillHRID;
    private Integer serReqBillDRID;
    private Integer serType;
    private Integer serReqStatus;
    private Integer serReqEntityRID;
    private Integer serReqLabEnHRID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerReqOpRID() {
        return serReqOpRID;
    }

    public void setSerReqOpRID(Integer serReqOpRID) {
        this.serReqOpRID = serReqOpRID;
    }

    public Integer getSerReqOpVisitRID() {
        return serReqOpVisitRID;
    }

    public void setSerReqOpVisitRID(Integer serReqOpVisitRID) {
        this.serReqOpVisitRID = serReqOpVisitRID;
    }

    public Integer getSerReqBillHRID() {
        return serReqBillHRID;
    }

    public Integer getSerType() {
        return serType;
    }

    public void setSerType(Integer serType) {
        this.serType = serType;
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

    public Integer getSerReqStatus() {
        return serReqStatus;
    }

    public void setSerReqStatus(Integer serReqStatus) {
        this.serReqStatus = serReqStatus;
    }

    public Integer getSerReqEntityRID() {
        return serReqEntityRID;
    }

    public void setSerReqEntityRID(Integer serReqEntityRID) {
        this.serReqEntityRID = serReqEntityRID;
    }

    public Integer getSerReqLabEnHRID() {
        return serReqLabEnHRID;
    }

    public void setSerReqLabEnHRID(Integer serReqLabEnHRID) {
        this.serReqLabEnHRID = serReqLabEnHRID;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tserReqOpRID: Integer=");
        sb.append(serReqOpRID);
        sb.append(";");

        sb.append("\n\tserReqOpVisitRID: Integer=");
        sb.append(serReqOpVisitRID);
        sb.append(";");

        sb.append("\n\tserReqBillHRID: Integer=");
        sb.append(serReqBillHRID);
        sb.append(";");

        sb.append("\n\tserReqBillDRID: Integer=");
        sb.append(serReqBillDRID);
        sb.append(";");

        sb.append("\n\tserReqStatus: Integer=");
        sb.append(serReqStatus);
        sb.append(";");

        sb.append("\n\tserReqEntityRID: Integer=");
        sb.append(serReqEntityRID);
        sb.append(";");

        sb.append("\n\tserReqLabEnHRID: Integer=");
        sb.append(serReqLabEnHRID);
        sb.append(";");

        return sb.toString();

    }
}
