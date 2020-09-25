package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class AppointmentBookingSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer bookingUnitRid;
    private String bookinDate;
    private Integer bookingSeqNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingUnitRid() {
        return bookingUnitRid;
    }

    public void setBookingUnitRid(Integer bookingUnitRid) {
        this.bookingUnitRid = bookingUnitRid;
    }

    public String getBookinDate() {
        return bookinDate;
    }

    public void setBookinDate(String bookinDate) {
        this.bookinDate = bookinDate;
    }

    public Integer getBookingSeqNumber() {
        return bookingSeqNumber;
    }

    public void setBookingSeqNumber(Integer bookingSeqNumber) {
        this.bookingSeqNumber = bookingSeqNumber;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tbookingUnitRid: Integer=");
        sb.append(bookingUnitRid);
        sb.append(";");

        sb.append("\n\tbookinDate: String=");
        sb.append(bookinDate);
        sb.append(";");

        sb.append("\n\bookingSeqNumber: Integer=");
        sb.append(bookingSeqNumber);
        sb.append(";");

        return sb.toString();
    }

}
