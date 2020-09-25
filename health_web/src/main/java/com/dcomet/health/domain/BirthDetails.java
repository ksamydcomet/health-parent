package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BirthDetails extends Base implements Serializable {

    private Integer bdRid;
    private String bdBabyName;
    private Integer bdBabyPatRid;
    private Integer bdBabyVisitRid;
    private Integer bdMotherPatRid;
    private Integer bdMotherVisitRid;
    private String bdMotherMrn;
    private String bdBabyMrn;
    private String bdBabyBithDatetime;
    private Integer bdBabyGenderIndex;
    private Float bdBabyBirthWeight;
    private Float bdBabyBirthLength;
    private String bdDeliveryMode;
    private Integer bdDeliveredBy;
    private String bdRemarks;
    private Integer bdBedRid;
    private Integer bdWardRid;
    private String bdReportSequence;
    private Integer bdMotherDurationOfPregnancy;
    private Integer bdBabyHeadCircumference;
    private Integer bdApgarScore1min;
    private Integer bdApgarScore5min;
    private Integer bdApgarScore10min;
    private String bdPresentationType;
    private String bdPresentationRemarks;
    private Short bdBabyStillBirth;
    private String bdBabyCauseOfDeath;
    private String bdBabyFatherName;
    private Integer bdCaesareanPlan;
    private String bdFatherEductation;
    private String bdFatherOccupation;
    private String bdMotherEductation;
    private String bdMotherOccupation;
    private String bdInformantName;
    private String bdAidsUsed;
    private Integer bdMotherMarriageAge;
    private Integer bdChildrenAlive;
    private Integer bdRecordedBy;
    private Short bdMrfolderStatus;
    private Integer bdCreatedUserRid;
    private String bdCreatedDatetime;
    private Integer bdModifiedUserRid;
    private String bdModifiedDatetime;

    public BirthDetails() {
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

    public String getBdBabyBithDatetime() {
        return bdBabyBithDatetime;
    }

    public void setBdBabyBithDatetime(String bdBabyBithDatetime) {
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

    public String getBdCreatedDatetime() {
        return bdCreatedDatetime;
    }

    public void setBdCreatedDatetime(String bdCreatedDatetime) {
        this.bdCreatedDatetime = bdCreatedDatetime;
    }

    public Integer getBdModifiedUserRid() {
        return bdModifiedUserRid;
    }

    public void setBdModifiedUserRid(Integer bdModifiedUserRid) {
        this.bdModifiedUserRid = bdModifiedUserRid;
    }

    public String getBdModifiedDatetime() {
        return bdModifiedDatetime;
    }

    public void setBdModifiedDatetime(String bdModifiedDatetime) {
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

        sb.append("\n\tbdBabyBithDatetime: String=");
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

        sb.append("\n\tbdCreatedDatetime String=");
        sb.append(bdCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbdModifiedUserRid Integer=");
        sb.append(bdModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbdModifiedDatetime String=");
        sb.append(bdModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
