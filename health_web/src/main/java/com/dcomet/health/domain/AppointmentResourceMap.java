package com.dcomet.health.domain;

import java.io.Serializable;
import java.util.List;

public class AppointmentResourceMap implements Serializable {

    private Integer id;
    private Integer armApptRid;
    private Integer armResourceRid;
    private List<Appointment> appointment;

    public AppointmentResourceMap() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArmApptRid() {
        return armApptRid;
    }

    public void setArmApptRid(Integer armApptRid) {
        this.armApptRid = armApptRid;
    }

    public Integer getArmResourceRid() {
        return armResourceRid;
    }

    public void setArmResourceRid(Integer armResourceRid) {
        this.armResourceRid = armResourceRid;
    }

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tarmApptRid: Integer=");
        sb.append(armApptRid);
        sb.append(";");

        sb.append("\n\tarmResourceRid: Integer=");
        sb.append(armResourceRid);
        sb.append(";");

        return sb.toString();
    }

}
