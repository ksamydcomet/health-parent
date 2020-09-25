package com.dcomet.health.domain;

import java.io.Serializable;

public class AppointmentToken implements Serializable {

    private Integer id;
    private Integer tokenUnitRID;
    private String tokenDate;
    private Integer tokenNumber;

    public AppointmentToken() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTokenUnitRID() {
        return tokenUnitRID;
    }

    public void setTokenUnitRID(Integer tokenUnitRID) {
        this.tokenUnitRID = tokenUnitRID;
    }

    public String getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(String tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Integer getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(Integer tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\ttokenUnitRID: Integer=");
        sb.append(tokenUnitRID);
        sb.append(";");

        sb.append("\n\ttokenDate: String=");
        sb.append(tokenDate);
        sb.append(";");

        sb.append("\n\ttokenNumber: Integer=");
        sb.append(tokenNumber);
        sb.append(";");

        return sb.toString();
    }

}
