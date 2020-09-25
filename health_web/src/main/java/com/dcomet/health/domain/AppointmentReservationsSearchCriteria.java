package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class AppointmentReservationsSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer rsvResourceRID;
    private Integer rsvReservationType;
    private Integer rsvReservedForRid;
    private String rsvReservedForName;
    private Integer rsvReservedSlotValue;
    private String rsvFromDateTime;
    private String rsvToDateTime;
    private String rsvRemarks;
    private Integer rsvModUserRid;
    private String rsvModDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRsvResourceRID() {
        return rsvResourceRID;
    }

    public void setRsvResourceRID(Integer rsvResourceRID) {
        this.rsvResourceRID = rsvResourceRID;
    }

    public Integer getRsvReservationType() {
        return rsvReservationType;
    }

    public void setRsvReservationType(Integer rsvReservationType) {
        this.rsvReservationType = rsvReservationType;
    }

    public Integer getRsvReservedForRid() {
        return rsvReservedForRid;
    }

    public void setRsvReservedForRid(Integer rsvReservedForRid) {
        this.rsvReservedForRid = rsvReservedForRid;
    }

    public String getRsvReservedForName() {
        return rsvReservedForName;
    }

    public void setRsvReservedForName(String rsvReservedForName) {
        this.rsvReservedForName = rsvReservedForName;
    }

    public Integer getRsvReservedSlotValue() {
        return rsvReservedSlotValue;
    }

    public void setRsvReservedSlotValue(Integer rsvReservedSlotValue) {
        this.rsvReservedSlotValue = rsvReservedSlotValue;
    }

    public String getRsvFromDateTime() {
        return rsvFromDateTime;
    }

    public void setRsvFromDateTime(String rsvFromDateTime) {
        this.rsvFromDateTime = rsvFromDateTime;
    }

    public String getRsvToDateTime() {
        return rsvToDateTime;
    }

    public void setRsvToDateTime(String rsvToDateTime) {
        this.rsvToDateTime = rsvToDateTime;
    }

    public String getRsvRemarks() {
        return rsvRemarks;
    }

    public void setRsvRemarks(String rsvRemarks) {
        this.rsvRemarks = rsvRemarks;
    }

    public Integer getRsvModUserRid() {
        return rsvModUserRid;
    }

    public void setRsvModUserRid(Integer rsvModUserRid) {
        this.rsvModUserRid = rsvModUserRid;
    }

    public String getRsvModDateTime() {
        return rsvModDateTime;
    }

    public void setRsvModDateTime(String rsvModDateTime) {
        this.rsvModDateTime = rsvModDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\trsvResourceRID: Integer=");
        sb.append(rsvResourceRID);
        sb.append(";");

        sb.append("\n\trsvReservationType: Integer=");
        sb.append(rsvReservationType);
        sb.append(";");

        sb.append("\n\rsvReservedForRid: Integer=");
        sb.append(rsvReservedForRid);
        sb.append(";");

        sb.append("\n\trsvReservedForName: String=");
        sb.append(rsvReservedForName);
        sb.append(";");

        sb.append("\n\trsvReservedSlotValue: Integer=");
        sb.append(rsvReservedSlotValue);
        sb.append(";");

        sb.append("\n\trsvFromDateTime: String=");
        sb.append(rsvFromDateTime);
        sb.append(";");

        sb.append("\n\trsvToDateTime: String=");
        sb.append(rsvToDateTime);
        sb.append(";");

        sb.append("\n\trsvRemarks: String=");
        sb.append(rsvRemarks);
        sb.append(";");

        sb.append("\n\trsvModUserRid: Integer=");
        sb.append(rsvModUserRid);
        sb.append(";");

        sb.append("\n\trsvModDateTime: String=");
        sb.append(rsvModDateTime);
        sb.append(";");

        return sb.toString();

    }
}
