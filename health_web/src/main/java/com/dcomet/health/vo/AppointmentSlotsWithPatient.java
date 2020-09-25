package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Abdul
 */
public class AppointmentSlotsWithPatient extends Base implements Serializable {

    private String slotTime;
    private Integer patientRid;
    private Integer doctorRid;
    private Integer appointmentRid;
    private String patientName;
    private String patientMrn;
    private String patientMobileNo;
    private Integer patientGender;
    private Integer patientAge;
    private Integer patientVisitRid;
    private String dateS;
    private Integer isTimeFreeze;
    private Integer apptStatus;

    public AppointmentSlotsWithPatient() {
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public Integer getPatientRid() {
        return patientRid;
    }

    public void setPatientRid(Integer patientRid) {
        this.patientRid = patientRid;
    }

    public Integer getDoctorRid() {
        return doctorRid;
    }

    public void setDoctorRid(Integer doctorRid) {
        this.doctorRid = doctorRid;
    }

    public Integer getAppointmentRid() {
        return appointmentRid;
    }

    public void setAppointmentRid(Integer appointmentRid) {
        this.appointmentRid = appointmentRid;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public Integer getIsTimeFreeze() {
        return isTimeFreeze;
    }

    public void setIsTimeFreeze(Integer isTimeFreeze) {
        this.isTimeFreeze = isTimeFreeze;
    }

    public Integer getApptStatus() {
        return apptStatus;
    }

    public void setApptStatus(Integer apptStatus) {
        this.apptStatus = apptStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMrn() {
        return patientMrn;
    }

    public void setPatientMrn(String patientMrn) {
        this.patientMrn = patientMrn;
    }

    public String getPatientMobileNo() {
        return patientMobileNo;
    }

    public void setPatientMobileNo(String patientMobileNo) {
        this.patientMobileNo = patientMobileNo;
    }

    public Integer getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Integer patientGender) {
        this.patientGender = patientGender;
    }

    public Integer getPatientVisitRid() {
        return patientVisitRid;
    }

    public void setPatientVisitRid(Integer patientVisitRid) {
        this.patientVisitRid = patientVisitRid;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

}
