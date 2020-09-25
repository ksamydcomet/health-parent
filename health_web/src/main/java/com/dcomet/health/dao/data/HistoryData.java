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
import javax.persistence.TemporalType;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_history")
public class HistoryData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HIS_RID", updatable = false)
    private Integer id;

    @Column(name = "HIS_VISIT_RID", updatable = false)
    private Integer hisVisitRid;

    @Column(name = "HIS_PAT_RID", updatable = false)
    private Integer hisPatRid;

    @Column(name = "HIS_PO_R_EYE")
    private String hisPoREye;

    @Column(name = "HIS_PO_L_EYE")
    private String hisPoLEye;

    @Column(name = "HIS_MED_PAST_MEDICAL_HISTORY")
    private String hisMedPastMedicalHistory;

    @Column(name = "HIS_MED_PERSONAL_HISTORY")
    private String hisMedPersonalHistory;

    @Column(name = "HIS_MED_DRUG_HISTORY")
    private String hisMedDrugHistory;

    @Column(name = "HIS_MED_PAST_DENTAL_HISTORY")
    private String hisMedPastDentalHistory;

    @Column(name = "HIS_MED_ALLERGIES")
    private String hisMedAllergies;

    @Column(name = "HIS_MED_ALLERGIES_STATUS")
    private String hisMedAllergiesStatus;

    @Column(name = "HIS_MED_DM")
    private String hisMedDm;

    @Column(name = "HIS_MED_DM_STATUS")
    private String hisMedDmStatus;

    @Column(name = "HIS_MED_HT")
    private String hisMedHt;

    @Column(name = "HIS_MED_HT_STATUS")
    private String hisMedHtStatus;

    @Column(name = "HIS_MED_ASTHMA")
    private String hisMedAsthma;

    @Column(name = "HIS_MED_ASTHMA_STATUS")
    private String hisMedAsthmaStatus;

    @Column(name = "HIS_MED_ECZEMA")
    private String hisMedEczema;

    @Column(name = "HIS_MED_ECZEMA_STATUS")
    private String hisMedEczemaStatus;

    @Column(name = "HIS_MED_GLAUCOMA")
    private String hisMedGlaucoma;

    @Column(name = "HIS_MED_GLAUCOMA_STATUS")
    private String hisMedGlaucomaStatus;

    @Column(name = "HIS_MED_ARTHRITIS")
    private String hisMedArthritis;

    @Column(name = "HIS_MED_ARTHRITIS_STATUS")
    private String hisMedArthritisStatus;

    @Column(name = "HIS_MED_OTHERS")
    private String hisMedOthers;

    @Column(name = "HIS_MED_OTHERS_STATUS")
    private String hisMedOthersStatus;

    @Column(name = "HIS_MED_SURGICAL_HISTORY")
    private String hisMedSurgicalHistory;

    @Column(name = "HIS_FS_FAMILY_HISTORY")
    private String hisFsFamilyHistory;

    @Column(name = "HIS_FS_DM")
    private String hisFsDm;

    @Column(name = "HIS_FS_DM_STATUS")
    private String hisFsDmStatus;

    @Column(name = "HIS_FS_HT")
    private String hisFsHt;

    @Column(name = "HIS_FS_HT_STATUS")
    private String hisFsHtStatus;

    @Column(name = "HIS_FS_ASTHMA")
    private String hisFsAsthma;

    @Column(name = "HIS_FS_ASTHMA_STATUS")
    private String hisFsAsthmaStatus;

    @Column(name = "HIS_FS_GLAUCOMA")
    private String hisFsGlaucoma;

    @Column(name = "HIS_FS_GLAUCOMA_STATUS")
    private String hisFsGlaucomaStatus;

    @Column(name = "HIS_FS_ARTHRITIS")
    private String hisFsArthritis;

    @Column(name = "HIS_FS_ARTHRITIS_STATUS")
    private String hisFsArthritisStatus;

    @Column(name = "HIS_FS_SMOKING")
    private String hisFsSmoking;

    @Column(name = "HIS_FS_SMOKING_STATUS")
    private String hisFsSmokingStatus;

    @Column(name = "HIS_FS_ALCOHOL")
    private String hisFsAlcohol;

    @Column(name = "HIS_FS_ALCOHOL_STATUS")
    private String hisFsAlcoholStatus;

    @Column(name = "HIS_FS_OTHERS")
    private String hisFsOthers;

    @Column(name = "HIS_FS_OTHERS_STATUS")
    private String hisFsOthersStatus;

    @Column(name = "HIS_ENTITY_RID")
    private Integer hisEntityRid;

    @Column(name = "HIS_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "his_created_datetime", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "HIS_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "HIS_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public HistoryData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHisVisitRid() {
        return hisVisitRid;
    }

    public void setHisVisitRid(Integer hisVisitRid) {
        this.hisVisitRid = hisVisitRid;
    }

    public Integer getHisPatRid() {
        return hisPatRid;
    }

    public void setHisPatRid(Integer hisPatRid) {
        this.hisPatRid = hisPatRid;
    }

    public String getHisPoREye() {
        return hisPoREye;
    }

    public void setHisPoREye(String hisPoREye) {
        this.hisPoREye = hisPoREye;
    }

    public String getHisPoLEye() {
        return hisPoLEye;
    }

    public void setHisPoLEye(String hisPoLEye) {
        this.hisPoLEye = hisPoLEye;
    }

    public String getHisMedPastMedicalHistory() {
        return hisMedPastMedicalHistory;
    }

    public void setHisMedPastMedicalHistory(String hisMedPastMedicalHistory) {
        this.hisMedPastMedicalHistory = hisMedPastMedicalHistory;
    }

    public String getHisMedPersonalHistory() {
        return hisMedPersonalHistory;
    }

    public void setHisMedPersonalHistory(String hisMedPersonalHistory) {
        this.hisMedPersonalHistory = hisMedPersonalHistory;
    }

    public String getHisMedDrugHistory() {
        return hisMedDrugHistory;
    }

    public void setHisMedDrugHistory(String hisMedDrugHistory) {
        this.hisMedDrugHistory = hisMedDrugHistory;
    }

    public String getHisMedPastDentalHistory() {
        return hisMedPastDentalHistory;
    }

    public void setHisMedPastDentalHistory(String hisMedPastDentalHistory) {
        this.hisMedPastDentalHistory = hisMedPastDentalHistory;
    }

    public String getHisMedAllergies() {
        return hisMedAllergies;
    }

    public void setHisMedAllergies(String hisMedAllergies) {
        this.hisMedAllergies = hisMedAllergies;
    }

    public String getHisMedAllergiesStatus() {
        return hisMedAllergiesStatus;
    }

    public void setHisMedAllergiesStatus(String hisMedAllergiesStatus) {
        this.hisMedAllergiesStatus = hisMedAllergiesStatus;
    }

    public String getHisMedDm() {
        return hisMedDm;
    }

    public void setHisMedDm(String hisMedDm) {
        this.hisMedDm = hisMedDm;
    }

    public String getHisMedDmStatus() {
        return hisMedDmStatus;
    }

    public void setHisMedDmStatus(String hisMedDmStatus) {
        this.hisMedDmStatus = hisMedDmStatus;
    }

    public String getHisMedHt() {
        return hisMedHt;
    }

    public void setHisMedHt(String hisMedHt) {
        this.hisMedHt = hisMedHt;
    }

    public String getHisMedHtStatus() {
        return hisMedHtStatus;
    }

    public void setHisMedHtStatus(String hisMedHtStatus) {
        this.hisMedHtStatus = hisMedHtStatus;
    }

    public String getHisMedAsthma() {
        return hisMedAsthma;
    }

    public void setHisMedAsthma(String hisMedAsthma) {
        this.hisMedAsthma = hisMedAsthma;
    }

    public String getHisMedAsthmaStatus() {
        return hisMedAsthmaStatus;
    }

    public void setHisMedAsthmaStatus(String hisMedAsthmaStatus) {
        this.hisMedAsthmaStatus = hisMedAsthmaStatus;
    }

    public String getHisMedEczema() {
        return hisMedEczema;
    }

    public void setHisMedEczema(String hisMedEczema) {
        this.hisMedEczema = hisMedEczema;
    }

    public String getHisMedEczemaStatus() {
        return hisMedEczemaStatus;
    }

    public void setHisMedEczemaStatus(String hisMedEczemaStatus) {
        this.hisMedEczemaStatus = hisMedEczemaStatus;
    }

    public String getHisMedGlaucoma() {
        return hisMedGlaucoma;
    }

    public void setHisMedGlaucoma(String hisMedGlaucoma) {
        this.hisMedGlaucoma = hisMedGlaucoma;
    }

    public String getHisMedGlaucomaStatus() {
        return hisMedGlaucomaStatus;
    }

    public void setHisMedGlaucomaStatus(String hisMedGlaucomaStatus) {
        this.hisMedGlaucomaStatus = hisMedGlaucomaStatus;
    }

    public String getHisMedArthritis() {
        return hisMedArthritis;
    }

    public void setHisMedArthritis(String hisMedArthritis) {
        this.hisMedArthritis = hisMedArthritis;
    }

    public String getHisMedArthritisStatus() {
        return hisMedArthritisStatus;
    }

    public void setHisMedArthritisStatus(String hisMedArthritisStatus) {
        this.hisMedArthritisStatus = hisMedArthritisStatus;
    }

    public String getHisMedOthers() {
        return hisMedOthers;
    }

    public void setHisMedOthers(String hisMedOthers) {
        this.hisMedOthers = hisMedOthers;
    }

    public String getHisMedOthersStatus() {
        return hisMedOthersStatus;
    }

    public void setHisMedOthersStatus(String hisMedOthersStatus) {
        this.hisMedOthersStatus = hisMedOthersStatus;
    }

    public String getHisMedSurgicalHistory() {
        return hisMedSurgicalHistory;
    }

    public void setHisMedSurgicalHistory(String hisMedSurgicalHistory) {
        this.hisMedSurgicalHistory = hisMedSurgicalHistory;
    }

    public String getHisFsFamilyHistory() {
        return hisFsFamilyHistory;
    }

    public void setHisFsFamilyHistory(String hisFsFamilyHistory) {
        this.hisFsFamilyHistory = hisFsFamilyHistory;
    }

    public String getHisFsDm() {
        return hisFsDm;
    }

    public void setHisFsDm(String hisFsDm) {
        this.hisFsDm = hisFsDm;
    }

    public String getHisFsDmStatus() {
        return hisFsDmStatus;
    }

    public void setHisFsDmStatus(String hisFsDmStatus) {
        this.hisFsDmStatus = hisFsDmStatus;
    }

    public String getHisFsHt() {
        return hisFsHt;
    }

    public void setHisFsHt(String hisFsHt) {
        this.hisFsHt = hisFsHt;
    }

    public String getHisFsHtStatus() {
        return hisFsHtStatus;
    }

    public void setHisFsHtStatus(String hisFsHtStatus) {
        this.hisFsHtStatus = hisFsHtStatus;
    }

    public String getHisFsAsthma() {
        return hisFsAsthma;
    }

    public void setHisFsAsthma(String hisFsAsthma) {
        this.hisFsAsthma = hisFsAsthma;
    }

    public String getHisFsAsthmaStatus() {
        return hisFsAsthmaStatus;
    }

    public void setHisFsAsthmaStatus(String hisFsAsthmaStatus) {
        this.hisFsAsthmaStatus = hisFsAsthmaStatus;
    }

    public String getHisFsGlaucoma() {
        return hisFsGlaucoma;
    }

    public void setHisFsGlaucoma(String hisFsGlaucoma) {
        this.hisFsGlaucoma = hisFsGlaucoma;
    }

    public String getHisFsGlaucomaStatus() {
        return hisFsGlaucomaStatus;
    }

    public void setHisFsGlaucomaStatus(String hisFsGlaucomaStatus) {
        this.hisFsGlaucomaStatus = hisFsGlaucomaStatus;
    }

    public String getHisFsArthritis() {
        return hisFsArthritis;
    }

    public void setHisFsArthritis(String hisFsArthritis) {
        this.hisFsArthritis = hisFsArthritis;
    }

    public String getHisFsArthritisStatus() {
        return hisFsArthritisStatus;
    }

    public void setHisFsArthritisStatus(String hisFsArthritisStatus) {
        this.hisFsArthritisStatus = hisFsArthritisStatus;
    }

    public String getHisFsSmoking() {
        return hisFsSmoking;
    }

    public void setHisFsSmoking(String hisFsSmoking) {
        this.hisFsSmoking = hisFsSmoking;
    }

    public String getHisFsSmokingStatus() {
        return hisFsSmokingStatus;
    }

    public void setHisFsSmokingStatus(String hisFsSmokingStatus) {
        this.hisFsSmokingStatus = hisFsSmokingStatus;
    }

    public String getHisFsAlcohol() {
        return hisFsAlcohol;
    }

    public void setHisFsAlcohol(String hisFsAlcohol) {
        this.hisFsAlcohol = hisFsAlcohol;
    }

    public String getHisFsAlcoholStatus() {
        return hisFsAlcoholStatus;
    }

    public void setHisFsAlcoholStatus(String hisFsAlcoholStatus) {
        this.hisFsAlcoholStatus = hisFsAlcoholStatus;
    }

    public String getHisFsOthers() {
        return hisFsOthers;
    }

    public void setHisFsOthers(String hisFsOthers) {
        this.hisFsOthers = hisFsOthers;
    }

    public String getHisFsOthersStatus() {
        return hisFsOthersStatus;
    }

    public void setHisFsOthersStatus(String hisFsOthersStatus) {
        this.hisFsOthersStatus = hisFsOthersStatus;
    }

    public Integer getHisEntityRid() {
        return hisEntityRid;
    }

    public void setHisEntityRid(Integer hisEntityRid) {
        this.hisEntityRid = hisEntityRid;
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
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\thisVisitRid: Integer=");
        sb.append(hisVisitRid);
        sb.append(";");

        sb.append("\n\thisPatRid: Integer=");
        sb.append(hisPatRid);
        sb.append(";");

        sb.append("\n\t hisPoREye: String=");
        sb.append(hisPoREye);
        sb.append(";");

        sb.append("\n\thisPoLEye: String=");
        sb.append(hisPoLEye);
        sb.append(";");

        sb.append("\n\thisMedPastMedicalHistory: String=");
        sb.append(hisMedPastMedicalHistory);
        sb.append(";");

        sb.append("\n\thisMedPersonalHistory: String=");
        sb.append(hisMedPersonalHistory);
        sb.append(";");

        sb.append("\n\thisMedDrugHistory: String=");
        sb.append(hisMedDrugHistory);
        sb.append(";");

        sb.append("\n\thisMedPastDentalHistory: String=");
        sb.append(hisMedPastDentalHistory);
        sb.append(";");

        sb.append("\n\thisMedAllergies: String=");
        sb.append(hisMedAllergies);
        sb.append(";");

        sb.append("\n\thisMedAllergiesStatus: String=");
        sb.append(hisMedAllergiesStatus);
        sb.append(";");

        sb.append("\n\thisMedDm: String=");
        sb.append(hisMedDm);
        sb.append(";");

        sb.append("\n\thisMedDmStatus: String=");
        sb.append(hisMedDmStatus);
        sb.append(";");

        sb.append("\n\thisMedHt: String=");
        sb.append(hisMedHt);
        sb.append(";");

        sb.append("\n\thisMedHtStatus: String=");
        sb.append(hisMedHtStatus);
        sb.append(";");

        sb.append("\n\thisMedAsthma: String=");
        sb.append(hisMedAsthma);
        sb.append(";");

        sb.append("\n\thisMedAsthmaStatus: String=");
        sb.append(hisMedAsthmaStatus);
        sb.append(";");

        sb.append("\n\thisMedEczema: String=");
        sb.append(hisMedEczema);
        sb.append(";");

        sb.append("\n\thisMedEczemaStatus: String=");
        sb.append(hisMedEczemaStatus);
        sb.append(";");

        sb.append("\nt\thisMedGlaucoma: String=");
        sb.append(hisMedGlaucoma);
        sb.append(";");

        sb.append("\nt\thisMedGlaucomaStatus: String=");
        sb.append(hisMedGlaucomaStatus);
        sb.append(";");

        sb.append("\n\thisMedArthritis: String=");
        sb.append(hisMedArthritis);
        sb.append(";");

        sb.append("\n\thisMedArthritisStatus: String=");
        sb.append(hisMedArthritisStatus);
        sb.append(";");

        sb.append("\n\thisMedOthers: String=");
        sb.append(hisMedOthers);
        sb.append(";");

        sb.append("\n\thisMedOthersStatus: String=");
        sb.append(hisMedOthersStatus);
        sb.append(";");

        sb.append("\n\thisMedSurgicalHistory: String=");
        sb.append(hisMedSurgicalHistory);
        sb.append(";");

        sb.append("\n\thisFsFamilyHistory: String=");
        sb.append(hisFsFamilyHistory);
        sb.append(";");

        sb.append("\n\thisFsDmStatus: String=");
        sb.append(hisFsDmStatus);
        sb.append(";");

        sb.append("\n\thisFsDm: String=");
        sb.append(hisFsDm);
        sb.append(";");

        sb.append("\n\thisFsHt: String=");
        sb.append(hisFsHt);
        sb.append(";");

        sb.append("\n\thisFsHtStatus: String=");
        sb.append(hisFsHtStatus);
        sb.append(";");

        sb.append("\n\thisFsAsthma: String=");
        sb.append(hisFsAsthma);
        sb.append(";");

        sb.append("\n\thisFsAsthmaStatus: String=");
        sb.append(hisFsAsthmaStatus);
        sb.append(";");

        sb.append("\n\thisFsGlaucoma: String=");
        sb.append(hisFsGlaucoma);
        sb.append(";");

        sb.append("\n\thisFsGlaucomaStatus: String=");
        sb.append(hisFsGlaucomaStatus);
        sb.append(";");

        sb.append("\n\thisFsArthritis: String=");
        sb.append(hisFsArthritis);
        sb.append(";");

        sb.append("\n\thisFsArthritisStatus: String=");
        sb.append(hisFsArthritisStatus);
        sb.append(";");

        sb.append("\n\thisFsSmoking: String=");
        sb.append(hisFsSmoking);
        sb.append(";");

        sb.append("\n\thisFsSmokingStatus: String=");
        sb.append(hisFsSmokingStatus);
        sb.append(";");

        sb.append("\n\thisFsAlcohol: String=");
        sb.append(hisFsAlcohol);
        sb.append(";");

        sb.append("\n\thisFsAlcoholStatus: String=");
        sb.append(hisFsAlcoholStatus);
        sb.append(";");

        sb.append("\n\thisFsOthers: String=");
        sb.append(hisFsOthers);
        sb.append(";");

        sb.append("\n\thisFsOthersStatus: String=");
        sb.append(hisFsOthersStatus);
        sb.append(";");

        sb.append("\n\thisEntityRid: Integer=");
        sb.append(hisEntityRid);
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
