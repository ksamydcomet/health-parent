package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author ABDUL
 */
public class ProcedureRequestHSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer prType;
    private Integer prCategory;
    private Integer prPatientRid;
    private String prPrimaryProcedure;
    private Integer prProcedureRid;
    private Integer prVisitRid;
    private Integer prOtRoomRid;
    private Integer prPrimaryDoctorRid;
    private String prSurgeryDateTime;
    private String prEstimatedDuration;
    private String prEstimatedDurationMinIndex;
    private Integer prTechnicianRid;
    private Integer prOtAssistantRid;
    private Integer prAnesthesistType;
    private Integer prAnesthesistRid;
    private Integer prAnesthesistRequired;
    private Integer prIsTransplantRequested;
    private String prTransplantNotes;
    private Integer prIsBloodRequested;
    private String prBloodNotes;
    private String prSafeSurgeryChecklist;
    private Integer prState;
    private Integer prStatus;
    private String prInstructions;
    private String prRemarks;
    private String prOtComplications;
    private String postOtComplications;
    private String prPostInstructions;
    private String prFollowUp;
    private Integer prCanDrinkNormally;
    private String prDeNormalNotes;
    private String prLastFluidTime;
    private String prLastFoodTime;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrType() {
        return prType;
    }

    public void setPrType(Integer prType) {
        this.prType = prType;
    }

    public Integer getPrCategory() {
        return prCategory;
    }

    public void setPrCategory(Integer prCategory) {
        this.prCategory = prCategory;
    }

    public Integer getPrPatientRid() {
        return prPatientRid;
    }

    public void setPrPatientRid(Integer prPatientRid) {
        this.prPatientRid = prPatientRid;
    }

    public String getPrPrimaryProcedure() {
        return prPrimaryProcedure;
    }

    public void setPrPrimaryProcedure(String prPrimaryProcedure) {
        this.prPrimaryProcedure = prPrimaryProcedure;
    }

    public Integer getPrProcedureRid() {
        return prProcedureRid;
    }

    public void setPrProcedureRid(Integer prProcedureRid) {
        this.prProcedureRid = prProcedureRid;
    }

    public Integer getPrVisitRid() {
        return prVisitRid;
    }

    public void setPrVisitRid(Integer prVisitRid) {
        this.prVisitRid = prVisitRid;
    }

    public Integer getPrOtRoomRid() {
        return prOtRoomRid;
    }

    public void setPrOtRoomRid(Integer prOtRoomRid) {
        this.prOtRoomRid = prOtRoomRid;
    }

    public String getPrSurgeryDateTime() {
        return prSurgeryDateTime;
    }

    public void setPrSurgeryDateTime(String prSurgeryDateTime) {
        this.prSurgeryDateTime = prSurgeryDateTime;
    }

    public String getPrEstimatedDuration() {
        return prEstimatedDuration;
    }

    public void setPrEstimatedDuration(String prEstimatedDuration) {
        this.prEstimatedDuration = prEstimatedDuration;
    }

    public Integer getPrTechnicianRid() {
        return prTechnicianRid;
    }

    public void setPrTechnicianRid(Integer prTechnicianRid) {
        this.prTechnicianRid = prTechnicianRid;
    }

    public Integer getPrOtAssistantRid() {
        return prOtAssistantRid;
    }

    public void setPrOtAssistantRid(Integer prOtAssistantRid) {
        this.prOtAssistantRid = prOtAssistantRid;
    }

    public Integer getPrAnesthesistType() {
        return prAnesthesistType;
    }

    public void setPrAnesthesistType(Integer prAnesthesistType) {
        this.prAnesthesistType = prAnesthesistType;
    }

    public Integer getPrAnesthesistRid() {
        return prAnesthesistRid;
    }

    public void setPrAnesthesistRid(Integer prAnesthesistRid) {
        this.prAnesthesistRid = prAnesthesistRid;
    }

    public Integer getPrIsTransplantRequested() {
        return prIsTransplantRequested;
    }

    public void setPrIsTransplantRequested(Integer prIsTransplantRequested) {
        this.prIsTransplantRequested = prIsTransplantRequested;
    }

    public String getPrTransplantNotes() {
        return prTransplantNotes;
    }

    public void setPrTransplantNotes(String prTransplantNotes) {
        this.prTransplantNotes = prTransplantNotes;
    }

    public Integer getPrIsBloodRequested() {
        return prIsBloodRequested;
    }

    public void setPrIsBloodRequested(Integer prIsBloodRequested) {
        this.prIsBloodRequested = prIsBloodRequested;
    }

    public String getPrBloodNotes() {
        return prBloodNotes;
    }

    public void setPrBloodNotes(String prBloodNotes) {
        this.prBloodNotes = prBloodNotes;
    }

    public String getPrSafeSurgeryChecklist() {
        return prSafeSurgeryChecklist;
    }

    public void setPrSafeSurgeryChecklist(String prSafeSurgeryChecklist) {
        this.prSafeSurgeryChecklist = prSafeSurgeryChecklist;
    }

    public Integer getPrState() {
        return prState;
    }

    public void setPrState(Integer prState) {
        this.prState = prState;
    }

    public Integer getPrStatus() {
        return prStatus;
    }

    public void setPrStatus(Integer prStatus) {
        this.prStatus = prStatus;
    }

    public String getPrInstructions() {
        return prInstructions;
    }

    public void setPrInstructions(String prInstructions) {
        this.prInstructions = prInstructions;
    }

    public String getPrRemarks() {
        return prRemarks;
    }

    public void setPrRemarks(String prRemarks) {
        this.prRemarks = prRemarks;
    }

    public String getPrOtComplications() {
        return prOtComplications;
    }

    public void setPrOtComplications(String prOtComplications) {
        this.prOtComplications = prOtComplications;
    }

    public String getPostOtComplications() {
        return postOtComplications;
    }

    public void setPostOtComplications(String postOtComplications) {
        this.postOtComplications = postOtComplications;
    }

    public String getPrPostInstructions() {
        return prPostInstructions;
    }

    public void setPrPostInstructions(String prPostInstructions) {
        this.prPostInstructions = prPostInstructions;
    }

    public String getPrFollowUp() {
        return prFollowUp;
    }

    public void setPrFollowUp(String prFollowUp) {
        this.prFollowUp = prFollowUp;
    }

    public Integer getPrCanDrinkNormally() {
        return prCanDrinkNormally;
    }

    public void setPrCanDrinkNormally(Integer prCanDrinkNormally) {
        this.prCanDrinkNormally = prCanDrinkNormally;
    }

    public String getPrDeNormalNotes() {
        return prDeNormalNotes;
    }

    public void setPrDeNormalNotes(String prDeNormalNotes) {
        this.prDeNormalNotes = prDeNormalNotes;
    }

    public String getPrLastFluidTime() {
        return prLastFluidTime;
    }

    public void setPrLastFluidTime(String prLastFluidTime) {
        this.prLastFluidTime = prLastFluidTime;
    }

    public Integer getPrPrimaryDoctorRid() {
        return prPrimaryDoctorRid;
    }

    public void setPrPrimaryDoctorRid(Integer prPrimaryDoctorRid) {
        this.prPrimaryDoctorRid = prPrimaryDoctorRid;
    }

    public String getPrEstimatedDurationMinIndex() {
        return prEstimatedDurationMinIndex;
    }

    public void setPrEstimatedDurationMinIndex(String prEstimatedDurationMinIndex) {
        this.prEstimatedDurationMinIndex = prEstimatedDurationMinIndex;
    }

    public Integer getPrAnesthesistRequired() {
        return prAnesthesistRequired;
    }

    public void setPrAnesthesistRequired(Integer prAnesthesistRequired) {
        this.prAnesthesistRequired = prAnesthesistRequired;
    }

    public String getPrLastFoodTime() {
        return prLastFoodTime;
    }

    public void setPrLastFoodTime(String prLastFoodTime) {
        this.prLastFoodTime = prLastFoodTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprType: Integer=");
        sb.append(prType);
        sb.append(";");

        sb.append("\n\tprCategory: Integer=");
        sb.append(prCategory);
        sb.append(";");

        sb.append("\n\tprPatientRid: Integer=");
        sb.append(prPatientRid);
        sb.append(";");

        sb.append("\n\tprProcedureRid: Integer=");
        sb.append(prProcedureRid);
        sb.append(";");

        sb.append("\n\tprPrimaryProcedure: String=");
        sb.append(prPrimaryProcedure);
        sb.append(";");

        sb.append("\n\tprVisitRid: Integer=");
        sb.append(prVisitRid);
        sb.append(";");

        sb.append("\n\tprOtRoomRid: Integer=");
        sb.append(prOtRoomRid);
        sb.append(";");

        sb.append("\n\tprSurgeryDateTime: String=");
        sb.append(prSurgeryDateTime);
        sb.append(";");

        sb.append("\n\tprPrimaryDoctorRid: Integer=");
        sb.append(prPrimaryDoctorRid);
        sb.append(";");

        sb.append("\n\tprEstimatedDuration: String=");
        sb.append(prEstimatedDuration);
        sb.append(";");

        sb.append("\n\tprEstimatedDurationMinIndex: String=");
        sb.append(prEstimatedDurationMinIndex);
        sb.append(";");

        sb.append("\n\tprTechnicianRid: Integer=");
        sb.append(prTechnicianRid);
        sb.append(";");

        sb.append("\n\tprOtAssistantRid: Integer=");
        sb.append(prOtAssistantRid);
        sb.append(";");

        sb.append("\n\tprAnesthesistType: Integer=");
        sb.append(prAnesthesistType);
        sb.append(";");

        sb.append("\n\tprAnesthesistType: Integer=");
        sb.append(prAnesthesistRid);
        sb.append(";");

        sb.append("\n\tprAnesthesistRequired: Integer=");
        sb.append(prAnesthesistRequired);
        sb.append(";");

        sb.append("\n\tprIsTransplantRequested: Integer=");
        sb.append(prIsTransplantRequested);
        sb.append(";");

        sb.append("\n\tprTransplantNotes: String=");
        sb.append(prTransplantNotes);
        sb.append(";");

        sb.append("\n\tprIsBloodRequested: Integer=");
        sb.append(prIsBloodRequested);
        sb.append(";");

        sb.append("\n\tprBloodNotes: String=");
        sb.append(prBloodNotes);
        sb.append(";");

        sb.append("\n\tprState: Integer=");
        sb.append(prState);
        sb.append(";");

        sb.append("\n\tprStatus: String=");
        sb.append(prStatus);
        sb.append(";");

        sb.append("\n\tprInstructions: String=");
        sb.append(prInstructions);
        sb.append(";");

        sb.append("\n\tprRemarks: String=");
        sb.append(prRemarks);
        sb.append(";");

        sb.append("\n\tprOtComplications: String=");
        sb.append(prOtComplications);
        sb.append(";");

        sb.append("\n\tpostOtComplications: String=");
        sb.append(postOtComplications);
        sb.append(";");

        sb.append("\n\tprSafeSurgeryChecklist: String=");
        sb.append(prSafeSurgeryChecklist);
        sb.append(";");

        sb.append("\n\tprPostInstructions: String=");
        sb.append(prPostInstructions);
        sb.append(";");

        sb.append("\n\tprFollowUp: String=");
        sb.append(prFollowUp);
        sb.append(";");

        sb.append("\n\tprCanDrinkNormally: Integer=");
        sb.append(prCanDrinkNormally);
        sb.append(";");

        sb.append("\n\tprDeNormalNotes: String=");
        sb.append(prDeNormalNotes);
        sb.append(";");

        sb.append("\n\tprLastFluidTime: String=");
        sb.append(prLastFluidTime);
        sb.append(";");

        sb.append("\n\tprLastFoodTime: String=");
        sb.append(prLastFoodTime);
        sb.append(";");
        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
