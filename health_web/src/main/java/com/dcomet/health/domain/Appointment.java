package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

public class Appointment extends Base implements Serializable {

    private Integer id;
    private Integer apptUnitRID;
    private String apptresType;
    private Integer apptvisitRID;
    private String apptUserNum;
    private Integer apptType;
    private Integer apptStatus;
    private String apptToken;
    private String apptFromDate;
    private String apptFromTime;
    private String apptArrivedTime;
    private String apptInprogressTime;
    private String apptCompletedTime;
    private String apptBilledTime;
    private Integer apptOrderRID;
    private Integer apptDuration;
    private Integer apptServiceRID;
    private String apptToDate;
    private String apptToTime;
    private String apptServiceName;
    private Integer apptServicePointRID;
    private String apptServicePointName;
    private Integer apptServiceProviderRID;
    private String apptServiceProviderName;
    private Integer apptPatientRID;
    private String apptPatientName;
    private String apptPatientPhone;
    private String apptPatientMrn;
    private Integer apptPatientAge;
    private Integer apptPatientGenderIndex;
    private String apptFromDateTime;
    private String apptToDateTime;
    private String apptBookingNumber;
    private String apptVisitStatus;
    private String apptOrders;
    private String apptCancelReason;
    private Integer apptIsFollowUpDone;
    private Integer apptIsLockAcquired;
    private Integer apptAttendingUserRID;
    private String apptRemarks;
    private Integer apptCreatedUnitRID;
    private Integer apptDocRID;

    private List<AppointmentResourceMap> appointmentResourceMap;

    public Appointment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApptUnitRID() {
        return apptUnitRID;
    }

    public void setApptUnitRID(Integer apptUnitRID) {
        this.apptUnitRID = apptUnitRID;
    }

    public String getApptresType() {
        return apptresType;
    }

    public void setApptresType(String apptresType) {
        this.apptresType = apptresType;
    }

    public Integer getApptvisitRID() {
        return apptvisitRID;
    }

    public void setApptvisitRID(Integer apptvisitRID) {
        this.apptvisitRID = apptvisitRID;
    }

    public String getApptUserNum() {
        return apptUserNum;
    }

    public void setApptUserNum(String apptUserNum) {
        this.apptUserNum = apptUserNum;
    }

    public Integer getApptType() {
        return apptType;
    }

    public void setApptType(Integer apptType) {
        this.apptType = apptType;
    }

    public Integer getApptStatus() {
        return apptStatus;
    }

    public void setApptStatus(Integer apptStatus) {
        this.apptStatus = apptStatus;
    }

    public String getApptToken() {
        return apptToken;
    }

    public void setApptToken(String apptToken) {
        this.apptToken = apptToken;
    }

    public String getApptFromDate() {
        return apptFromDate;
    }

    public void setApptFromDate(String apptFromDate) {
        this.apptFromDate = apptFromDate;
    }

    public String getApptFromTime() {
        return apptFromTime;
    }

    public void setApptFromTime(String apptFromTime) {
        this.apptFromTime = apptFromTime;
    }

    public String getApptArrivedTime() {
        return apptArrivedTime;
    }

    public void setApptArrivedTime(String apptArrivedTime) {
        this.apptArrivedTime = apptArrivedTime;
    }

    public String getApptInprogressTime() {
        return apptInprogressTime;
    }

    public void setApptInprogressTime(String apptInprogressTime) {
        this.apptInprogressTime = apptInprogressTime;
    }

    public String getApptCompletedTime() {
        return apptCompletedTime;
    }

    public void setApptCompletedTime(String apptCompletedTime) {
        this.apptCompletedTime = apptCompletedTime;
    }

    public String getApptBilledTime() {
        return apptBilledTime;
    }

    public void setApptBilledTime(String apptBilledTime) {
        this.apptBilledTime = apptBilledTime;
    }

    public Integer getApptOrderRID() {
        return apptOrderRID;
    }

    public void setApptOrderRID(Integer apptOrderRID) {
        this.apptOrderRID = apptOrderRID;
    }

    public Integer getApptDuration() {
        return apptDuration;
    }

    public void setApptDuration(Integer apptDuration) {
        this.apptDuration = apptDuration;
    }

    public Integer getApptServiceRID() {
        return apptServiceRID;
    }

    public void setApptServiceRID(Integer apptServiceRID) {
        this.apptServiceRID = apptServiceRID;
    }

    public String getApptToDate() {
        return apptToDate;
    }

    public void setApptToDate(String apptToDate) {
        this.apptToDate = apptToDate;
    }

    public String getApptToTime() {
        return apptToTime;
    }

    public void setApptToTime(String apptToTime) {
        this.apptToTime = apptToTime;
    }

    public String getApptServiceName() {
        return apptServiceName;
    }

    public void setApptServiceName(String apptServiceName) {
        this.apptServiceName = apptServiceName;
    }

    public Integer getApptServicePointRID() {
        return apptServicePointRID;
    }

    public void setApptServicePointRID(Integer apptServicePointRID) {
        this.apptServicePointRID = apptServicePointRID;
    }

    public String getApptServicePointName() {
        return apptServicePointName;
    }

    public void setApptServicePointName(String apptServicePointName) {
        this.apptServicePointName = apptServicePointName;
    }

    public Integer getApptServiceProviderRID() {
        return apptServiceProviderRID;
    }

    public void setApptServiceProviderRID(Integer apptServiceProviderRID) {
        this.apptServiceProviderRID = apptServiceProviderRID;
    }

    public String getApptServiceProviderName() {
        return apptServiceProviderName;
    }

    public void setApptServiceProviderName(String apptServiceProviderName) {
        this.apptServiceProviderName = apptServiceProviderName;
    }

    public Integer getApptPatientRID() {
        return apptPatientRID;
    }

    public void setApptPatientRID(Integer apptPatientRID) {
        this.apptPatientRID = apptPatientRID;
    }

    public String getApptPatientName() {
        return apptPatientName;
    }

    public void setApptPatientName(String apptPatientName) {
        this.apptPatientName = apptPatientName;
    }

    public String getApptPatientPhone() {
        return apptPatientPhone;
    }

    public void setApptPatientPhone(String apptPatientPhone) {
        this.apptPatientPhone = apptPatientPhone;
    }

    public String getApptPatientMrn() {
        return apptPatientMrn;
    }

    public void setApptPatientMrn(String apptPatientMrn) {
        this.apptPatientMrn = apptPatientMrn;
    }

    public Integer getApptPatientAge() {
        return apptPatientAge;
    }

    public void setApptPatientAge(Integer apptPatientAge) {
        this.apptPatientAge = apptPatientAge;
    }

    public Integer getApptPatientGenderIndex() {
        return apptPatientGenderIndex;
    }

    public void setApptPatientGenderIndex(Integer apptPatientGenderIndex) {
        this.apptPatientGenderIndex = apptPatientGenderIndex;
    }

    public String getApptFromDateTime() {
        return apptFromDateTime;
    }

    public void setApptFromDateTime(String apptFromDateTime) {
        this.apptFromDateTime = apptFromDateTime;
    }

    public String getApptToDateTime() {
        return apptToDateTime;
    }

    public void setApptToDateTime(String apptToDateTime) {
        this.apptToDateTime = apptToDateTime;
    }

    public String getApptBookingNumber() {
        return apptBookingNumber;
    }

    public void setApptBookingNumber(String apptBookingNumber) {
        this.apptBookingNumber = apptBookingNumber;
    }

    public String getApptVisitStatus() {
        return apptVisitStatus;
    }

    public void setApptVisitStatus(String apptVisitStatus) {
        this.apptVisitStatus = apptVisitStatus;
    }

    public String getApptOrders() {
        return apptOrders;
    }

    public void setApptOrders(String apptOrders) {
        this.apptOrders = apptOrders;
    }

    public String getApptCancelReason() {
        return apptCancelReason;
    }

    public void setApptCancelReason(String apptCancelReason) {
        this.apptCancelReason = apptCancelReason;
    }

    public Integer getApptIsFollowUpDone() {
        return apptIsFollowUpDone;
    }

    public void setApptIsFollowUpDone(Integer apptIsFollowUpDone) {
        this.apptIsFollowUpDone = apptIsFollowUpDone;
    }

    public Integer getApptIsLockAcquired() {
        return apptIsLockAcquired;
    }

    public void setApptIsLockAcquired(Integer apptIsLockAcquired) {
        this.apptIsLockAcquired = apptIsLockAcquired;
    }

    public Integer getApptAttendingUserRID() {
        return apptAttendingUserRID;
    }

    public void setApptAttendingUserRID(Integer apptAttendingUserRID) {
        this.apptAttendingUserRID = apptAttendingUserRID;
    }

    public String getApptRemarks() {
        return apptRemarks;
    }

    public void setApptRemarks(String apptRemarks) {
        this.apptRemarks = apptRemarks;
    }

    public Integer getApptCreatedUnitRID() {
        return apptCreatedUnitRID;
    }

    public void setApptCreatedUnitRID(Integer apptCreatedUnitRID) {
        this.apptCreatedUnitRID = apptCreatedUnitRID;
    }

    public Integer getApptDocRID() {
        return apptDocRID;
    }

    public void setApptDocRID(Integer apptDocRID) {
        this.apptDocRID = apptDocRID;
    }

    public List<AppointmentResourceMap> getAppointmentResourceMap() {
        return appointmentResourceMap;
    }

    public void setAppointmentResourceMap(List<AppointmentResourceMap> appointmentResourceMap) {
        this.appointmentResourceMap = appointmentResourceMap;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tapptUnitRID: Integer=");
        sb.append(apptUnitRID);
        sb.append(";");

        sb.append("\n\tapptresType: Charcter=");
        sb.append(apptresType);
        sb.append(";");

        sb.append("\n\tapptvisitRID: Integer=");
        sb.append(apptvisitRID);
        sb.append(";");

        sb.append("\n\tapptUserNum: String=");
        sb.append(apptUserNum);
        sb.append(";");

        sb.append("\n\tapptType: Integer=");
        sb.append(apptType);
        sb.append(";");

        sb.append("\n\tapptStatus: Integer=");
        sb.append(apptStatus);
        sb.append(";");

        sb.append("\n\tapptToken: String=");
        sb.append(apptToken);
        sb.append(";");

        sb.append("\n\tapptFromDate: Dtae=");
        sb.append(apptFromDate);
        sb.append(";");

        sb.append("\n\tapptFromTime: String=");
        sb.append(apptFromTime);
        sb.append(";");

        sb.append("\n\tapptArrivedTime: String=");
        sb.append(apptArrivedTime);
        sb.append(";");

        sb.append("\n\tapptInprogressTime: String=");
        sb.append(apptInprogressTime);
        sb.append(";");

        sb.append("\n\tapptCompletedTime: String=");
        sb.append(apptCompletedTime);
        sb.append(";");

        sb.append("\n\tapptBilledTime: String=");
        sb.append(apptBilledTime);
        sb.append(";");

        sb.append("\n\tapptOrderRID: Integer=");
        sb.append(apptOrderRID);
        sb.append(";");

        sb.append("\n\tapptDuration: Integer=");
        sb.append(apptDuration);
        sb.append(";");

        sb.append("\n\tapptServiceRID: Integer=");
        sb.append(apptServiceRID);
        sb.append(";");

        sb.append("\n\tapptToDate: Date=");
        sb.append(apptToDate);
        sb.append(";");

        sb.append("\n\tapptToTime: String=");
        sb.append(apptToTime);
        sb.append(";");

        sb.append("\n\tapptServiceName: String=");
        sb.append(apptServiceName);
        sb.append(";");

        sb.append("\n\tapptServicePointRID: Integer=");
        sb.append(apptServicePointRID);
        sb.append(";");

        sb.append("\n\tapptServicePointName: String=");
        sb.append(apptServicePointName);
        sb.append(";");

        sb.append("\n\tapptServiceProviderRID: Integer=");
        sb.append(apptServiceProviderRID);
        sb.append(";");

        sb.append("\n\tapptServiceProviderName: String=");
        sb.append(apptServiceProviderName);
        sb.append(";");

        sb.append("\n\tapptPatientRID: Integer=");
        sb.append(apptPatientRID);
        sb.append(";");

        sb.append("\n\tapptPatientName: Integer=");
        sb.append(apptPatientName);
        sb.append(";");

        sb.append("\n\tapptPatientPhone: Integer=");
        sb.append(apptPatientPhone);
        sb.append(";");

        sb.append("\n\tapptPatientMrn: String=");
        sb.append(apptPatientMrn);
        sb.append(";");

        sb.append("\n\tapptPatientAge: Integer=");
        sb.append(apptPatientAge);
        sb.append(";");

        sb.append("\n\tapptPatientGenderIndex: Integer=");
        sb.append(apptPatientGenderIndex);
        sb.append(";");

        sb.append("\n\tapptFromDateTime: Calendar=");
        sb.append(apptFromDateTime);
        sb.append(";");

        sb.append("\n\tapptToDateTime: Calendar=");
        sb.append(apptToDateTime);
        sb.append(";");

        sb.append("\n\tapptBookingNumber: String=");
        sb.append(apptBookingNumber);
        sb.append(";");

        sb.append("\n\tapptVisitStatus: String=");
        sb.append(apptVisitStatus);
        sb.append(";");

        sb.append("\n\tapptOrders: String=");
        sb.append(apptOrders);
        sb.append(";");

        sb.append("\n\tapptCancelReason: String=");
        sb.append(apptCancelReason);
        sb.append(";");

        sb.append("\n\tapptIsFollowUpDone: Integer=");
        sb.append(apptIsFollowUpDone);
        sb.append(";");

        sb.append("\n\tapptIsLockAcquired: Integer=");
        sb.append(apptIsLockAcquired);
        sb.append(";");

        sb.append("\n\tapptAttendingUserRID: String=");
        sb.append(apptAttendingUserRID);
        sb.append(";");

        sb.append("\n\tapptRemarks: String=");
        sb.append(apptRemarks);
        sb.append(";");

        sb.append("\n\tapptCreatedUnitRID: Integer=");
        sb.append(apptCreatedUnitRID);
        sb.append(";");

        return sb.toString();
    }
}
