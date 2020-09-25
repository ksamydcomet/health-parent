package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Abdul
 */
public class AppointmentSlots extends Base implements Serializable {

    private Integer doctorRid;
    private String previousDay;
    private String selectedDay;
    private String nextDay;
    private String previousDate;
    private String selectedDate;
    private String nextDate;
    private String dateInterval;

    private List<AppointmentSlotsBydate> appointmentSlotsBydates;

    public AppointmentSlots() {
    }

    public Integer getDoctorRid() {
        return doctorRid;
    }

    public void setDoctorRid(Integer doctorRid) {
        this.doctorRid = doctorRid;
    }

    public String getPreviousDay() {
        return previousDay;
    }

    public void setPreviousDay(String previousDay) {
        this.previousDay = previousDay;
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public String getNextDay() {
        return nextDay;
    }

    public void setNextDay(String nextDay) {
        this.nextDay = nextDay;
    }

    public String getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public String getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(String dateInterval) {
        this.dateInterval = dateInterval;
    }

    public List<AppointmentSlotsBydate> getAppointmentSlotsBydates() {
        return appointmentSlotsBydates;
    }

    public void setAppointmentSlotsBydates(List<AppointmentSlotsBydate> appointmentSlotsBydates) {
        this.appointmentSlotsBydates = appointmentSlotsBydates;
    }

}
