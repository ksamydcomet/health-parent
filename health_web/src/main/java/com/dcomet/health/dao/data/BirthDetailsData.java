package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "t_birth_details")

public class BirthDetailsData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BD_RID")
    private Integer bdRid;

    @Column(name = "BD_BABY_NAME")
    private String bdBabyName;

    @Column(name = "BD_BABY_PAT_RID")
    private Integer bdBabyPatRid;

    @Column(name = "BD_BABY_VISIT_RID")
    private Integer bdBabyVisitRid;

    @Column(name = "BD_MOTHER_PAT_RID")
    private Integer bdMotherPatRid;

    @Column(name = "BD_MOTHER_VISIT_RID")
    private Integer bdMotherVisitRid;

    @Column(name = "BD_MOTHER_MRN")
    private String bdMotherMrn;

    @Column(name = "BD_BABY_MRN")
    private String bdBabyMrn;

    @Column(name = "BD_BABY_BITH_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar bdBabyBithDatetime;

    @Column(name = "BD_BABY_GENDER_INDEX")
    private Integer bdBabyGenderIndex;

    @Column(name = "BD_BABY_BIRTH_WEIGHT")
    private Float bdBabyBirthWeight;

    @Column(name = "BD_BABY_BIRTH_LENGTH")
    private Float bdBabyBirthLength;

    @Column(name = "BD_DELIVERY_MODE")
    private String bdDeliveryMode;

    @Column(name = "BD_DELIVERED_BY")
    private Integer bdDeliveredBy;

    @Column(name = "BD_REMARKS")
    private String bdRemarks;

    @Column(name = "BD_BED_RID")
    private Integer bdBedRid;

    @Column(name = "BD_WARD_RID")
    private Integer bdWardRid;

    @Column(name = "BD_REPORT_SEQUENCE")
    private String bdReportSequence;

    @Column(name = "BD_MOTHER_DURATION_OF_PREGNANCY")
    private Integer bdMotherDurationOfPregnancy;

    @Column(name = "BD_BABY_HEAD_CIRCUMFERENCE")
    private Integer bdBabyHeadCircumference;

    @Column(name = "BD_APGAR_SCORE_1MIN")
    private Integer bdApgarScore1min;

    @Column(name = "BD_APGAR_SCORE_5MIN")
    private Integer bdApgarScore5min;

    @Column(name = "BD_APGAR_SCORE_10MIN")
    private Integer bdApgarScore10min;

    @Column(name = "BD_PRESENTATION_TYPE")
    private String bdPresentationType;

    @Column(name = "BD_PRESENTATION_REMARKS")
    private String bdPresentationRemarks;

    @Column(name = "BD_BABY_STILL_BIRTH")
    private Short bdBabyStillBirth;

    @Column(name = "BD_BABY_CAUSE_OF_DEATH")
    private String bdBabyCauseOfDeath;

    @Column(name = "BD_BABY_FATHER_NAME")
    private String bdBabyFatherName;

    @Column(name = "BD_CAESAREAN_PLAN")
    private Integer bdCaesareanPlan;

    @Column(name = "BD_FATHER_EDUCTATION")
    private String bdFatherEductation;

    @Column(name = "BD_FATHER_OCCUPATION")
    private String bdFatherOccupation;

    @Column(name = "BD_MOTHER_EDUCTATION")
    private String bdMotherEductation;

    @Column(name = "BD_MOTHER_OCCUPATION")
    private String bdMotherOccupation;

    @Column(name = "BD_INFORMANT_NAME")
    private String bdInformantName;

    @Column(name = "BD_AIDS_USED")
    private String bdAidsUsed;

    @Column(name = "BD_MOTHER_MARRIAGE_AGE")
    private Integer bdMotherMarriageAge;

    @Column(name = "BD_CHILDREN_ALIVE")
    private Integer bdChildrenAlive;

    @Column(name = "BD_RECORDED_BY")
    private Integer bdRecordedBy;

    @Column(name = "BD_MRFOLDER_STATUS")
    private Short bdMrfolderStatus;

    @Column(name = "BD_CREATED_USER_RID")
    private Integer bdCreatedUserRid;

    @Column(name = "BD_CREATED_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar bdCreatedDatetime;

    @Column(name = "BD_MODIFIED_USER_RID")
    private Integer bdModifiedUserRid;

    @Column(name = "BD_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar bdModifiedDatetime;

    public BirthDetailsData() {
    }

    public BirthDetailsData(Integer bdRid) {
        this.bdRid = bdRid;
    }

    public Integer getBdRid() {
        return bdRid;
    }

    public void setBdRid(Integer bdRid) {
        this.bdRid = bdRid;
    }

    public String getBdBabyName() {
        return bdBabyName;
    }

    public void setBdBabyName(String bdBabyName) {
        this.bdBabyName = bdBabyName;
    }

    public Integer getBdBabyPatRid() {
        return bdBabyPatRid;
    }

    public void setBdBabyPatRid(Integer bdBabyPatRid) {
        this.bdBabyPatRid = bdBabyPatRid;
    }

    public Integer getBdBabyVisitRid() {
        return bdBabyVisitRid;
    }

    public void setBdBabyVisitRid(Integer bdBabyVisitRid) {
        this.bdBabyVisitRid = bdBabyVisitRid;
    }

    public Integer getBdMotherPatRid() {
        return bdMotherPatRid;
    }

    public void setBdMotherPatRid(Integer bdMotherPatRid) {
        this.bdMotherPatRid = bdMotherPatRid;
    }

    public Integer getBdMotherVisitRid() {
        return bdMotherVisitRid;
    }

    public void setBdMotherVisitRid(Integer bdMotherVisitRid) {
        this.bdMotherVisitRid = bdMotherVisitRid;
    }

    public String getBdMotherMrn() {
        return bdMotherMrn;
    }

    public void setBdMotherMrn(String bdMotherMrn) {
        this.bdMotherMrn = bdMotherMrn;
    }

    public String getBdBabyMrn() {
        return bdBabyMrn;
    }

    public void setBdBabyMrn(String bdBabyMrn) {
        this.bdBabyMrn = bdBabyMrn;
    }

    public Calendar getBdBabyBithDatetime() {
        return bdBabyBithDatetime;
    }

    public void setBdBabyBithDatetime(Calendar bdBabyBithDatetime) {
        this.bdBabyBithDatetime = bdBabyBithDatetime;
    }

    public Integer getBdBabyGenderIndex() {
        return bdBabyGenderIndex;
    }

    public void setBdBabyGenderIndex(Integer bdBabyGenderIndex) {
        this.bdBabyGenderIndex = bdBabyGenderIndex;
    }

    public Float getBdBabyBirthWeight() {
        return bdBabyBirthWeight;
    }

    public void setBdBabyBirthWeight(Float bdBabyBirthWeight) {
        this.bdBabyBirthWeight = bdBabyBirthWeight;
    }

    public Float getBdBabyBirthLength() {
        return bdBabyBirthLength;
    }

    public void setBdBabyBirthLength(Float bdBabyBirthLength) {
        this.bdBabyBirthLength = bdBabyBirthLength;
    }

    public String getBdDeliveryMode() {
        return bdDeliveryMode;
    }

    public void setBdDeliveryMode(String bdDeliveryMode) {
        this.bdDeliveryMode = bdDeliveryMode;
    }

    public Integer getBdDeliveredBy() {
        return bdDeliveredBy;
    }

    public void setBdDeliveredBy(Integer bdDeliveredBy) {
        this.bdDeliveredBy = bdDeliveredBy;
    }

    public String getBdRemarks() {
        return bdRemarks;
    }

    public void setBdRemarks(String bdRemarks) {
        this.bdRemarks = bdRemarks;
    }

    public Integer getBdBedRid() {
        return bdBedRid;
    }

    public void setBdBedRid(Integer bdBedRid) {
        this.bdBedRid = bdBedRid;
    }

    public Integer getBdWardRid() {
        return bdWardRid;
    }

    public void setBdWardRid(Integer bdWardRid) {
        this.bdWardRid = bdWardRid;
    }

    public String getBdReportSequence() {
        return bdReportSequence;
    }

    public void setBdReportSequence(String bdReportSequence) {
        this.bdReportSequence = bdReportSequence;
    }

    public Integer getBdMotherDurationOfPregnancy() {
        return bdMotherDurationOfPregnancy;
    }

    public void setBdMotherDurationOfPregnancy(Integer bdMotherDurationOfPregnancy) {
        this.bdMotherDurationOfPregnancy = bdMotherDurationOfPregnancy;
    }

    public Integer getBdBabyHeadCircumference() {
        return bdBabyHeadCircumference;
    }

    public void setBdBabyHeadCircumference(Integer bdBabyHeadCircumference) {
        this.bdBabyHeadCircumference = bdBabyHeadCircumference;
    }

    public Integer getBdApgarScore1min() {
        return bdApgarScore1min;
    }

    public void setBdApgarScore1min(Integer bdApgarScore1min) {
        this.bdApgarScore1min = bdApgarScore1min;
    }

    public Integer getBdApgarScore5min() {
        return bdApgarScore5min;
    }

    public void setBdApgarScore5min(Integer bdApgarScore5min) {
        this.bdApgarScore5min = bdApgarScore5min;
    }

    public Integer getBdApgarScore10min() {
        return bdApgarScore10min;
    }

    public void setBdApgarScore10min(Integer bdApgarScore10min) {
        this.bdApgarScore10min = bdApgarScore10min;
    }

    public String getBdPresentationType() {
        return bdPresentationType;
    }

    public void setBdPresentationType(String bdPresentationType) {
        this.bdPresentationType = bdPresentationType;
    }

    public String getBdPresentationRemarks() {
        return bdPresentationRemarks;
    }

    public void setBdPresentationRemarks(String bdPresentationRemarks) {
        this.bdPresentationRemarks = bdPresentationRemarks;
    }

    public Short getBdBabyStillBirth() {
        return bdBabyStillBirth;
    }

    public void setBdBabyStillBirth(Short bdBabyStillBirth) {
        this.bdBabyStillBirth = bdBabyStillBirth;
    }

    public String getBdBabyCauseOfDeath() {
        return bdBabyCauseOfDeath;
    }

    public void setBdBabyCauseOfDeath(String bdBabyCauseOfDeath) {
        this.bdBabyCauseOfDeath = bdBabyCauseOfDeath;
    }

    public String getBdBabyFatherName() {
        return bdBabyFatherName;
    }

    public void setBdBabyFatherName(String bdBabyFatherName) {
        this.bdBabyFatherName = bdBabyFatherName;
    }

    public Integer getBdCaesareanPlan() {
        return bdCaesareanPlan;
    }

    public void setBdCaesareanPlan(Integer bdCaesareanPlan) {
        this.bdCaesareanPlan = bdCaesareanPlan;
    }

    public String getBdFatherEductation() {
        return bdFatherEductation;
    }

    public void setBdFatherEductation(String bdFatherEductation) {
        this.bdFatherEductation = bdFatherEductation;
    }

    public String getBdFatherOccupation() {
        return bdFatherOccupation;
    }

    public void setBdFatherOccupation(String bdFatherOccupation) {
        this.bdFatherOccupation = bdFatherOccupation;
    }

    public String getBdMotherEductation() {
        return bdMotherEductation;
    }

    public void setBdMotherEductation(String bdMotherEductation) {
        this.bdMotherEductation = bdMotherEductation;
    }

    public String getBdMotherOccupation() {
        return bdMotherOccupation;
    }

    public void setBdMotherOccupation(String bdMotherOccupation) {
        this.bdMotherOccupation = bdMotherOccupation;
    }

    public String getBdInformantName() {
        return bdInformantName;
    }

    public void setBdInformantName(String bdInformantName) {
        this.bdInformantName = bdInformantName;
    }

    public String getBdAidsUsed() {
        return bdAidsUsed;
    }

    public void setBdAidsUsed(String bdAidsUsed) {
        this.bdAidsUsed = bdAidsUsed;
    }

    public Integer getBdMotherMarriageAge() {
        return bdMotherMarriageAge;
    }

    public void setBdMotherMarriageAge(Integer bdMotherMarriageAge) {
        this.bdMotherMarriageAge = bdMotherMarriageAge;
    }

    public Integer getBdChildrenAlive() {
        return bdChildrenAlive;
    }

    public void setBdChildrenAlive(Integer bdChildrenAlive) {
        this.bdChildrenAlive = bdChildrenAlive;
    }

    public Integer getBdRecordedBy() {
        return bdRecordedBy;
    }

    public void setBdRecordedBy(Integer bdRecordedBy) {
        this.bdRecordedBy = bdRecordedBy;
    }

    public Short getBdMrfolderStatus() {
        return bdMrfolderStatus;
    }

    public void setBdMrfolderStatus(Short bdMrfolderStatus) {
        this.bdMrfolderStatus = bdMrfolderStatus;
    }

    public Integer getBdCreatedUserRid() {
        return bdCreatedUserRid;
    }

    public void setBdCreatedUserRid(Integer bdCreatedUserRid) {
        this.bdCreatedUserRid = bdCreatedUserRid;
    }

    public Calendar getBdCreatedDatetime() {
        return bdCreatedDatetime;
    }

    public void setBdCreatedDatetime(Calendar bdCreatedDatetime) {
        this.bdCreatedDatetime = bdCreatedDatetime;
    }

    public Integer getBdModifiedUserRid() {
        return bdModifiedUserRid;
    }

    public void setBdModifiedUserRid(Integer bdModifiedUserRid) {
        this.bdModifiedUserRid = bdModifiedUserRid;
    }

    public Calendar getBdModifiedDatetime() {
        return bdModifiedDatetime;
    }

    public void setBdModifiedDatetime(Calendar bdModifiedDatetime) {
        this.bdModifiedDatetime = bdModifiedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbodRid: Integer=");
        sb.append(bdRid);
        sb.append(";");
        
        sb.append("\n\tbdBabyName: String=");
        sb.append(bdBabyName);
        sb.append(";");
        
        sb.append("\n\tbdBabyPatRid: Integer=");
        sb.append(bdBabyPatRid);
        sb.append(";");
        
        sb.append("\n\tbdBabyVisitRid: Integer=");
        sb.append(bdBabyVisitRid);
        sb.append(";");
        
        sb.append("\n\tbdMotherPatRid: Integer=");
        sb.append(bdMotherPatRid);
        sb.append(";");
        
        sb.append("\n\tbdMotherVisitRid: Integer=");
        sb.append(bdMotherVisitRid);
        sb.append(";");
        
        sb.append("\n\tbdMotherMrn: String=");
        sb.append(bdMotherMrn);
        sb.append(";");
        
        sb.append("\n\tbdBabyMrn: String=");
        sb.append(bdBabyMrn);
        sb.append(";");
        
        sb.append("\n\tbdBabyBithDatetime: Calendar=");
        sb.append(bdBabyBithDatetime);
        sb.append(";");
        
        sb.append("\n\tbdBabyGenderIndex: Integer=");
        sb.append(bdBabyGenderIndex);
        sb.append(";");
        
        sb.append("\n\tbdBabyBirthWeight: Float=");
        sb.append(bdBabyBirthWeight);
        sb.append(";");
        
        sb.append("\n\tbdBabyBirthLength: Float=");
        sb.append(bdBabyBirthLength);
        sb.append(";");
        
        sb.append("\n\tbdDeliveryMode: String=");
        sb.append(bdDeliveryMode);
        sb.append(";");
        
        sb.append("\n\tbdDeliveredBy: Integer=");
        sb.append(bdDeliveredBy);
        sb.append(";");
        
        sb.append("\n\tbdRemarks: String=");
        sb.append(bdRemarks);
        sb.append(";");
        
        sb.append("\n\tbdBedRid: Integer=");
        sb.append(bdBedRid);
        sb.append(";");
        
        sb.append("\n\tbdWardRid: Integer=");
        sb.append(bdWardRid);
        sb.append(";");
        
        sb.append("\n\tbdReportSequence: String=");
        sb.append(bdReportSequence);
        sb.append(";");
        
        sb.append("\n\tbdMotherDurationOfPregnancy: Integer=");
        sb.append(bdMotherDurationOfPregnancy);
        sb.append(";");
        
        sb.append("\n\tbdBabyHeadCircumference: Integer=");
        sb.append(bdBabyHeadCircumference);
        sb.append(";");
        
        sb.append("\n\tbdApgarScore1min Integer=");
        sb.append(bdApgarScore1min);
        sb.append(";");
        
        sb.append("\n\tbdApgarScore5min Integer=");
        sb.append(bdApgarScore5min);
        sb.append(";");
        
        sb.append("\n\tbdApgarScore10min Integer=");
        sb.append(bdApgarScore10min);
        sb.append(";");
        
        sb.append("\n\tbdPresentationType String=");
        sb.append(bdPresentationType);
        sb.append(";");
        
        sb.append("\n\tbdPresentationRemarks String=");
        sb.append(bdPresentationRemarks);
        sb.append(";");
        
        sb.append("\n\tbdBabyStillBirth Short=");
        sb.append(bdBabyStillBirth);
        sb.append(";");
        
        sb.append("\n\tbdBabyCauseOfDeath String=");
        sb.append(bdBabyCauseOfDeath);
        sb.append(";");
        
        sb.append("\n\tbdBabyFatherName String=");
        sb.append(bdBabyFatherName);
        sb.append(";");
        
        sb.append("\n\tbdCaesareanPlan String=");
        sb.append(bdCaesareanPlan);
        sb.append(";");
        
        sb.append("\n\tbdFatherEductation String=");
        sb.append(bdFatherEductation);
        sb.append(";");
        
        sb.append("\n\tbdFatherOccupation String=");
        sb.append(bdFatherOccupation);
        sb.append(";");
        
        sb.append("\n\tbdMotherEductation String=");
        sb.append(bdMotherEductation);
        sb.append(";");
        
        sb.append("\n\tbdMotherOccupation String=");
        sb.append(bdMotherOccupation);
        sb.append(";");
        
        sb.append("\n\tbdInformantName String=");
        sb.append(bdInformantName);
        sb.append(";");
        
        sb.append("\n\tbdAidsUsed String=");
        sb.append(bdAidsUsed);
        sb.append(";");
        
        sb.append("\n\tbdMotherMarriageAge Integer=");
        sb.append(bdMotherMarriageAge);
        sb.append(";");
        
        sb.append("\n\tbdChildrenAlive Integer=");
        sb.append(bdChildrenAlive);
        sb.append(";");
        
        sb.append("\n\tbdRecordedBy Integer=");
        sb.append(bdRecordedBy);
        sb.append(";");
        
        sb.append("\n\tbdMrfolderStatus Short=");
        sb.append(bdMrfolderStatus);
        sb.append(";");
        
        sb.append("\n\tbdCreatedUserRid Integer=");
        sb.append(bdCreatedUserRid);
        sb.append(";");
        
        sb.append("\n\tbdCreatedDatetime Calendar=");
        sb.append(bdCreatedDatetime);
        sb.append(";");
        
        sb.append("\n\tbdModifiedUserRid Integer=");
        sb.append(bdModifiedUserRid);
        sb.append(";");
        
        sb.append("\n\tbdModifiedDatetime Calendar=");
        sb.append(bdModifiedDatetime);
        sb.append(";");
       
      
           return sb.toString();
    }
}
