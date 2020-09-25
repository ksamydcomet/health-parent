/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dev1
 */
public class AdmissionDetails extends Base {

    private Integer adId;
    private Integer adPatientRid;
    private Integer adVisitRid;
    private Integer adAttnDoctorRid;
    private Integer adConsultingDoctorRid;
    private Integer adStaffInchargeRid;
    private Integer adAdmittingUnitRid;
    private Integer adServiceUnitRid;
    private Integer adBedRid;
    private Integer adHasProcedure;
    private Integer adState;
    private Integer adStatus;
    private Integer adEntityRid;
    private Integer adCreatedUserRid;
    private String adCreatedDateTime;
    private Integer adModifiedUserRid;
        private String adModifiedDateTime;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getAdPatientRid() {
        return adPatientRid;
    }

    public void setAdPatientRid(Integer adPatientRid) {
        this.adPatientRid = adPatientRid;
    }

    public Integer getAdVisitRid() {
        return adVisitRid;
    }

    public void setAdVisitRid(Integer adVisitRid) {
        this.adVisitRid = adVisitRid;
    }

    public Integer getAdAttnDoctorRid() {
        return adAttnDoctorRid;
    }

    public void setAdAttnDoctorRid(Integer adAttnDoctorRid) {
        this.adAttnDoctorRid = adAttnDoctorRid;
    }

    public Integer getAdConsultingDoctorRid() {
        return adConsultingDoctorRid;
    }

    public void setAdConsultingDoctorRid(Integer adConsultingDoctorRid) {
        this.adConsultingDoctorRid = adConsultingDoctorRid;
    }

    public Integer getAdStaffInchargeRid() {
        return adStaffInchargeRid;
    }

    public void setAdStaffInchargeRid(Integer adStaffInchargeRid) {
        this.adStaffInchargeRid = adStaffInchargeRid;
    }

    public Integer getAdAdmittingUnitRid() {
        return adAdmittingUnitRid;
    }

    public void setAdAdmittingUnitRid(Integer adAdmittingUnitRid) {
        this.adAdmittingUnitRid = adAdmittingUnitRid;
    }

    public Integer getAdServiceUnitRid() {
        return adServiceUnitRid;
    }

    public void setAdServiceUnitRid(Integer adServiceUnitRid) {
        this.adServiceUnitRid = adServiceUnitRid;
    }

    public Integer getAdBedRid() {
        return adBedRid;
    }

    public void setAdBedRid(Integer adBedRid) {
        this.adBedRid = adBedRid;
    }

    public Integer getAdHasProcedure() {
        return adHasProcedure;
    }

    public void setAdHasProcedure(Integer adHasProcedure) {
        this.adHasProcedure = adHasProcedure;
    }

    public Integer getAdState() {
        return adState;
    }

    public void setAdState(Integer adState) {
        this.adState = adState;
    }

    public Integer getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(Integer adStatus) {
        this.adStatus = adStatus;
    }

    public Integer getAdEntityRid() {
        return adEntityRid;
    }

    public void setAdEntityRid(Integer adEntityRid) {
        this.adEntityRid = adEntityRid;
    }

    public Integer getAdCreatedUserRid() {
        return adCreatedUserRid;
    }

    public void setAdCreatedUserRid(Integer adCreatedUserRid) {
        this.adCreatedUserRid = adCreatedUserRid;
    }

  
    public Integer getAdModifiedUserRid() {
        return adModifiedUserRid;
    }

    public void setAdModifiedUserRid(Integer adModifiedUserRid) {
        this.adModifiedUserRid = adModifiedUserRid;
    }

    public String getAdCreatedDateTime() {
        return adCreatedDateTime;
    }

    public void setAdCreatedDateTime(String adCreatedDateTime) {
        this.adCreatedDateTime = adCreatedDateTime;
    }

    public String getAdModifiedDateTime() {
        return adModifiedDateTime;
    }

    public void setAdModifiedDateTime(String adModifiedDateTime) {
        this.adModifiedDateTime = adModifiedDateTime;
    }


    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tadId: Integer=");
        sb.append(adId);
        sb.append(";");

        sb.append("\n\tadPatientRid: Integer=");
        sb.append(adPatientRid);
        sb.append(";");

        sb.append("\n\tadAttnDoctorRid: Integer=");
        sb.append(adAttnDoctorRid);
        sb.append(";");

        sb.append("\n\tadVisitRid: Integer=");
        sb.append(adVisitRid);
        sb.append(";");

        sb.append("\n\tadConsultingDoctorRid: Integer=");
        sb.append(adConsultingDoctorRid);
        sb.append(";");

        sb.append("\n\tadStaffInchargeRid: Integer=");
        sb.append(adStaffInchargeRid);
        sb.append(";");

        sb.append("\n\tadAdmittingUnitRid: Integer=");
        sb.append(adAdmittingUnitRid);
        sb.append(";");

        sb.append("\n\tadServiceUnitRid: Integer=");
        sb.append(adServiceUnitRid);
        sb.append(";");

        sb.append("\n\tadBedRid: Integer=");
        sb.append(adBedRid);
        sb.append(";");

        sb.append("\n\tadHasProcedure: Integer=");
        sb.append(adHasProcedure);
        sb.append(";");

        sb.append("\n\tadState: Integer=");
        sb.append(adState);
        sb.append(";");

        sb.append("\n\tadStatus: Integer=");
        sb.append(adStatus);
        sb.append(";");

        sb.append("\n\tadEntityRid: Integer=");
        sb.append(adEntityRid);
        sb.append(";");

        sb.append("\n\tadCreatedUserRid: Integer=");
        sb.append(adCreatedUserRid);
        sb.append(";");

        sb.append("\n\tadCreatedDateTime: String=");
        sb.append(adCreatedDateTime);
        sb.append(";");

        sb.append("\n\tadModifiedUserRid: Integer=");
        sb.append(adModifiedUserRid);
        sb.append(";");

        sb.append("\n\tadModifiedDateTime: String=");
        sb.append(adModifiedDateTime);
        sb.append(";");
        return sb.toString();
    }
}
