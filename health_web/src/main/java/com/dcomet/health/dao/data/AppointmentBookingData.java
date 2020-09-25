package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_appointment_booking")
public class AppointmentBookingData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOKING_ENT_RID", updatable = false)
    private Integer id;

    @Column(name = "BOOKING_UNIT_RID", updatable = false)
    private Integer bookingUnitRid;

    @Column(name = "BOOKING_DATE", updatable = false)
    private Date bookinDate;

    @Column(name = "BOOKING_SEQ_NUMBER")
    private Integer bookingSeqNumber;

    @Column(name = "BOOKING_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "BOOKING_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "BOOKING_MOD_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "BOOKING_MOD_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public AppointmentBookingData() {

    }

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

    public Date getBookinDate() {
        return bookinDate;
    }

    public void setBookinDate(Date bookinDate) {
        this.bookinDate = bookinDate;
    }

    public Integer getBookingSeqNumber() {
        return bookingSeqNumber;
    }

    public void setBookingSeqNumber(Integer bookingSeqNumber) {
        this.bookingSeqNumber = bookingSeqNumber;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    @Override
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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

        sb.append("\n\tbookinDate: Date=");
        sb.append(bookinDate);
        sb.append(";");

        sb.append("\n\bookingSeqNumber: Integer=");
        sb.append(bookingSeqNumber);
        sb.append(";");
        
        
        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tadModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        return sb.toString();
    }
}
