package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_appointment_reservation")
public class AppointmentReservationData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RSV_RID", updatable = false)
    private Integer id;

    @Column(name = "RSV_RESOURCE_RID")
    private Integer rsvResourceRID;

    @Column(name = "RSV_RESERVATION_TYPE")
    private Integer rsvReservationType;

    @Column(name = "RSV_RESERVED_FOR_RID")
    private Integer rsvReservedForRid;

    @Column(name = "RSV_RESERVED_FOR_NAME")
    private String rsvReservedForName;

    @Column(name = "RSV_RESERVED_SLOT_VALUE")
    private Integer rsvReservedSlotValue;

    @Column(name = "RSV_FROM_DATETIME")
    private Calendar rsvFromDateTime;

    @Column(name = "RSV_TO_DATETIME")
    private Calendar rsvToDateTime;

    @Column(name = "RSV_REMARKS")
    private String rsvRemarks;

    @Column(name = "RSV_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "RSV_CREATED_DATETIME", updatable = false)
    private Calendar createdDateTime;

    @Column(name = "RSV_MOD_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "RSV_MOD_DATETIME")
    private Calendar modifiedDateTime;

    public AppointmentReservationData() {

    }

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

    public Calendar getRsvFromDateTime() {
        return rsvFromDateTime;
    }

    public void setRsvFromDateTime(Calendar rsvFromDateTime) {
        this.rsvFromDateTime = rsvFromDateTime;
    }

    public Calendar getRsvToDateTime() {
        return rsvToDateTime;
    }

    public void setRsvToDateTime(Calendar rsvToDateTime) {
        this.rsvToDateTime = rsvToDateTime;
    }

    public String getRsvRemarks() {
        return rsvRemarks;
    }

    public void setRsvRemarks(String rsvRemarks) {
        this.rsvRemarks = rsvRemarks;
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

        sb.append("\n\trsvFromDateTime: Calendar=");
        sb.append(rsvFromDateTime);
        sb.append(";");

        sb.append("\n\trsvToDateTime: Calendar=");
        sb.append(rsvToDateTime);
        sb.append(";");

        sb.append("\n\trsvRemarks: String=");
        sb.append(rsvRemarks);
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
