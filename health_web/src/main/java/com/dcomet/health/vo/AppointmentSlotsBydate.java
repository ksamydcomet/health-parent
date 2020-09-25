package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Abdul
 */
public class AppointmentSlotsBydate extends Base implements Serializable {

    private String dateS;
    private Integer dayFlag;
    private String dayString;
    private Integer dayIsHoliday;
    private String holidayName;

    private List<AppointmentSlotsWithPatient> appointmentSlotsWithPatients;

    public AppointmentSlotsBydate() {

    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public Integer getDayFlag() {
        return dayFlag;
    }

    public void setDayFlag(Integer dayFlag) {
        this.dayFlag = dayFlag;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public Integer getDayIsHoliday() {
        return dayIsHoliday;
    }

    public void setDayIsHoliday(Integer dayIsHoliday) {
        this.dayIsHoliday = dayIsHoliday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public List<AppointmentSlotsWithPatient> getAppointmentSlotsWithPatients() {
        return appointmentSlotsWithPatients;
    }

    public void setAppointmentSlotsWithPatients(List<AppointmentSlotsWithPatient> appointmentSlotsWithPatients) {
        this.appointmentSlotsWithPatients = appointmentSlotsWithPatients;
    }

}
