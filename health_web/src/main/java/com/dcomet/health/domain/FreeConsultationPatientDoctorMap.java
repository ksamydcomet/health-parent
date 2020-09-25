package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;

/**
 *
 * @author Abdul
 */
public class FreeConsultationPatientDoctorMap extends Base {

    private Integer id;
    private Integer fcPatientRid;
    private Integer fcDoctorRid;
    private Integer fcCurrentState;
    private String fcCurrentStatus;
    private Integer fcPreviousState;
    private String fcLastVisitDate;
    private String fcLastPaidVisitDate;
    private Integer fcEntityRid;

    private Integer fcServiceRid;
    private String fcServiceName;

    public FreeConsultationPatientDoctorMap() {

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

    public String getFcLastVisitDate() {
        return fcLastVisitDate;
    }

    public void setFcLastVisitDate(String fcLastVisitDate) {
        this.fcLastVisitDate = fcLastVisitDate;
    }

    public String getFcLastPaidVisitDate() {
        return fcLastPaidVisitDate;
    }

    public void setFcLastPaidVisitDate(String fcLastPaidVisitDate) {
        this.fcLastPaidVisitDate = fcLastPaidVisitDate;
    }

    public Integer getFcEntityRid() {
        return fcEntityRid;
    }

    public void setFcEntityRid(Integer fcEntityRid) {
        this.fcEntityRid = fcEntityRid;
    }

    public Integer getFcServiceRid() {
        return fcServiceRid;
    }

    public void setFcServiceRid(Integer fcServiceRid) {
        this.fcServiceRid = fcServiceRid;
    }

    public String getFcServiceName() {
        return fcServiceName;
    }

    public void setFcServiceName(String fcServiceName) {
        this.fcServiceName = fcServiceName;
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

        sb.append("\n\tfcServiceRid: Integer=");
        sb.append(fcServiceRid);
        sb.append(";");

        sb.append("\n\tfcServiceName: String=");
        sb.append(fcServiceName);
        sb.append(";");

        return sb.toString();
    }
}
