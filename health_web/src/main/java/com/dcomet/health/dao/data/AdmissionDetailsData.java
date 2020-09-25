/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev1
 */
@Entity
@Table(name = "t_admission_details")

public class AdmissionDetailsData implements Serializable,Auditable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    @Column(name = "ad_id")
    private Integer adId;
    @Column(name = "ad_patient_rid")
    private Integer adPatientRid;
    @Column(name = "ad_visit_rid")
    private Integer adVisitRid;
    @Column(name = "ad_attn_doctor_rid")
    private Integer adAttnDoctorRid;
    @Column(name = "ad_consulting_doctor_rid")
    private Integer adConsultingDoctorRid;
    @Column(name = "ad_staff_incharge_rid")
    private Integer adStaffInchargeRid;
    @Column(name = "ad_admitting_unit_rid")
    private Integer adAdmittingUnitRid;
    @Column(name = "ad_service_unit_rid")
    private Integer adServiceUnitRid;
    @Column(name = "ad_bed_rid")
    private Integer adBedRid;
    @Column(name = "ad_has_procedure")
    private Integer adHasProcedure;
    @Column(name = "ad_state")
    private Integer adState;
    @Column(name = "ad_status")
    private Integer adStatus;
    @Column(name = "ad_entity_rid")
    private Integer adEntityRid;
    @Column(name = "ad_created_user_rid")
    private Integer adCreatedUserRid;
    @Column(name = "ad_created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar adCreatedDateTime;
    @Column(name = "ad_modified_user_rid")
    private Integer adModifiedUserRid;
    @Column(name = "ad_modified_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar adModifiedDateTime;
 
  
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

    public Calendar getAdCreatedDateTime() {
        return adCreatedDateTime;
    }

    public void setAdCreatedDateTime(Calendar adCreatedDateTime) {
        this.adCreatedDateTime = adCreatedDateTime;
    }

    public Calendar getAdModifiedDateTime() {
        return adModifiedDateTime;
    }

    public void setAdModifiedDateTime(Calendar adModifiedDateTime) {
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

    @Override
    public Calendar getCreatedDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedDateTime(Calendar clndr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCreatedUserRid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedUserRid(Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getModifiedDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModifiedDateTime(Calendar clndr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getModifiedUserRid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModifiedUserRid(Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
