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
 * @author Abdul
 */
@Entity
@Table(name = "t_free_consultation_patient_doctor_map")
public class FreeConsultationPatientDoctorMapData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FC_RID", updatable = false)
    private Integer id;

    @Column(name = "FC_PATIENT_RID")
    private Integer fcPatientRid;

    @Column(name = "FC_DOCTOR_RID")
    private Integer fcDoctorRid;

    @Column(name = "FC_CURRENT_STATE")
    private Integer fcCurrentState;

    @Column(name = "FC_CURRENT_STATUS")
    private String fcCurrentStatus;

    @Column(name = "FC_PREVIOUS_STATE")
    private Integer fcPreviousState;

    @Column(name = "FC_LAST_VISIT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fcLastVisitDate;

    @Column(name = "FC_LAST_PAID_VISIT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fcLastPaidVisitDate;

    @Column(name = "FC_ENTITY_RID")
    private Integer fcEntityRid;

    @Column(name = "FC_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "FC_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "FC_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "FC_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public FreeConsultationPatientDoctorMapData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFcPatientRid() {
        return fcPatientRid;
    }

    public void setFcPatientRid(Integer fcPatientRid) {
        this.fcPatientRid = fcPatientRid;
    }

    public Integer getFcDoctorRid() {
        return fcDoctorRid;
    }

    public void setFcDoctorRid(Integer fcDoctorRid) {
        this.fcDoctorRid = fcDoctorRid;
    }

    public Integer getFcCurrentState() {
        return fcCurrentState;
    }

    public void setFcCurrentState(Integer fcCurrentState) {
        this.fcCurrentState = fcCurrentState;
    }

    public String getFcCurrentStatus() {
        return fcCurrentStatus;
    }

    public void setFcCurrentStatus(String fcCurrentStatus) {
        this.fcCurrentStatus = fcCurrentStatus;
    }

    public Integer getFcPreviousState() {
        return fcPreviousState;
    }

    public void setFcPreviousState(Integer fcPreviousState) {
        this.fcPreviousState = fcPreviousState;
    }

    public Date getFcLastVisitDate() {
        return fcLastVisitDate;
    }

    public void setFcLastVisitDate(Date fcLastVisitDate) {
        this.fcLastVisitDate = fcLastVisitDate;
    }

    public Date getFcLastPaidVisitDate() {
        return fcLastPaidVisitDate;
    }

    public void setFcLastPaidVisitDate(Date fcLastPaidVisitDate) {
        this.fcLastPaidVisitDate = fcLastPaidVisitDate;
    }

    public Integer getFcEntityRid() {
        return fcEntityRid;
    }

    public void setFcEntityRid(Integer fcEntityRid) {
        this.fcEntityRid = fcEntityRid;
    }

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

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

        sb.append("\n\tfcPatientRid: Integer=");
        sb.append(fcPatientRid);
        sb.append(";");

        sb.append("\n\tfcDoctorRid: Integer=");
        sb.append(fcDoctorRid);
        sb.append(";");

        sb.append("\n\tfcCurrentState: Integer=");
        sb.append(fcCurrentState);
        sb.append(";");

        sb.append("\n\tfcCurrentStatus: String=");
        sb.append(fcCurrentStatus);
        sb.append(";");

        sb.append("\n\tfcPreviousState: Integer=");
        sb.append(fcPreviousState);
        sb.append(";");

        sb.append("\n\tfcLastVisitDate: Date=");
        sb.append(fcLastVisitDate);
        sb.append(";");

        sb.append("\n\tfcLastPaidVisitDate: Date=");
        sb.append(fcLastPaidVisitDate);
        sb.append(";");

        sb.append("\n\tfcEntityRid: Integer=");
        sb.append(fcEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
