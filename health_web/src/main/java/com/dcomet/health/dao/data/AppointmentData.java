package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_appointment")
public class AppointmentData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APPT_RID", updatable = false)
    private Integer id;

    @Column(name = "APPT_ENT_RID", updatable = false)
    private Integer apptEntRID;

    @Column(name = "APPT_UNIT_RID", updatable = false)
    private Integer apptUnitRID;

    @Column(name = "APPT_RES_TYPE")
    private String apptresType;

    @Column(name = "APPT_VISIT_RID")
    private Integer apptvisitRID;

    @Column(name = "APPT_USER_NUM")
    private String apptUserNum;

    @Column(name = "APPT_TYPE")
    private Integer apptType;

    @Column(name = "APPT_STATUS")
    private Integer apptStatus;

    @Column(name = "APPT_TOKEN")
    private String apptToken;

    @Column(name = "APPT_FROM_DATE", updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date apptFromDate;

    @Column(name = "APPT_FROM_TIME")
    private String apptFromTime;

    @Column(name = "APPT_ARRIVED_TIME")
    private String apptArrivedTime;

    @Column(name = "APPT_INPROGRESS_TIME")
    private String apptInprogressTime;

    @Column(name = "APPT_COMPLETED_TIME")
    private String apptCompletedTime;

    @Column(name = "APPT_BILLED_TIME", updatable = false)
    private String apptBilledTime;

    @Column(name = "APPT_ORDER_RID", updatable = false)
    private Integer apptOrderRID;

    @Column(name = "APPT_DURATION")
    private Integer apptDuration;

    @Column(name = "APPT_SERVICE_RID", updatable = false)
    private Integer apptServiceRID;

    @Column(name = "APPT_TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date apptToDate;

    @Column(name = "APPT_TO_TIME")
    private String apptToTime;

    @Column(name = "APPT_SERVICE_NAME")
    private String apptServiceName;

    @Column(name = "APPT_SERVICE_POINT_RID")
    private Integer apptServicePointRID;

    @Column(name = "APPT_SERVICE_POINT_NAME")
    private String apptServicePointName;

    @Column(name = "APPT_SERVICE_PROVIDER_RID", updatable = false)
    private Integer apptServiceProviderRID;

    @Column(name = "APPT_SERVICE_PROVIDER_NAME")
    private String apptServiceProviderName;

    @Column(name = "APPT_PATIENT_RID")
    private Integer apptPatientRID;

    @Column(name = "APPT_PATIENT_NAME")
    private String apptPatientName;

    @Column(name = "APPT_PATIENT_PHONE")
    private String apptPatientPhone;

    @Column(name = "APPT_PATIENT_MRN")
    private String apptPatientMrn;

    @Column(name = "APPT_PATIENT_AGE")
    private Integer apptPatientAge;

    @Column(name = "APPT_PATIENT_GENDER_INDEX")
    private Integer apptPatientGenderIndex;

    @Column(name = "APPT_FROM_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar apptFromDateTime;

    @Column(name = "APPT_TO_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar apptToDateTime;
    
    @Column(name = "APPT_BOOKING_NUMBER", updatable = false)
    private String apptBookingNumber;

    @Column(name = "APPT_VISIT_STATUS")
    private String apptVisitStatus;

    @Column(name = "APPT_ORDERS")
    private String apptOrders;

    @Column(name = "APPT_CANCEL_REASON")
    private String apptCancelReason;

    @Column(name = "APPT_IS_FOLLOW_UP_DONE")
    private Integer apptIsFollowUpDone;

    @Column(name = "APPT_IS_LOCK_ACQUIRED")
    private Integer apptIsLockAcquired;

    @Column(name = "APPT_ATTENDING_USER_RID")
    private Integer apptAttendingUserRID;

    @Column(name = "APPT_REMARKS")
    private String apptRemarks;

    @Column(name = "APPT_CREATED_UNIT_RID", updatable = false)
    private Integer apptCreatedUnitRID;
    
    @Column(name = "APPT_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;
    
    @Column(name = "APPT_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;   
    
    @Column(name = "APPT_MOD_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "APPT_MOD_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;   
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ARM_APPT_RID", referencedColumnName = "APPT_RID", updatable = false)
    private List<AppointmentResourceMapData> appointmentResourceMapData;

    public AppointmentData() {

    }

    public List<AppointmentResourceMapData> getAppointmentResourceMapData() {
        return appointmentResourceMapData;
    }

    public void setAppointmentResourceMapData(List<AppointmentResourceMapData> appointmentResourceMapData) {
        this.appointmentResourceMapData = appointmentResourceMapData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApptEntRID() {
        return apptEntRID;
    }

    public void setApptEntRID(Integer apptEntRID) {
        this.apptEntRID = apptEntRID;
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

    public Date getApptFromDate() {
        return apptFromDate;
    }

    public void setApptFromDate(Date apptFromDate) {
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

    public Date getApptToDate() {
        return apptToDate;
    }

    public void setApptToDate(Date apptToDate) {
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

    public Calendar getApptFromDateTime() {
        return apptFromDateTime;
    }

    public void setApptFromDateTime(Calendar apptFromDateTime) {
        this.apptFromDateTime = apptFromDateTime;
    }

    public Calendar getApptToDateTime() {
        return apptToDateTime;
    }

    public void setApptToDateTime(Calendar apptToDateTime) {
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

        sb.append("\n\tapptEntRID: Integer=");
        sb.append(apptEntRID);
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
