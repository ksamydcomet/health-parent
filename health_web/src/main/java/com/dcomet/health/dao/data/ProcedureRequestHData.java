package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
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
 * @author ABDUL
 */
@Entity
@Table(name = "t_procedure_request_h")
public class ProcedureRequestHData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PR_RID", updatable = false)
    private Integer id;

    @Column(name = "PR_TYPE")
    private Integer prType;

    @Column(name = "PR_CATEGORY")
    private Integer prCategory;

    @Column(name = "PR_PATIENT_RID")
    private Integer prPatientRid;

    @Column(name = "PR_PRIMARY_PROCEDURE")
    private String prPrimaryProcedure;

    @Column(name = "PR_PROCEDURE_RID")
    private Integer prProcedureRid;

    @Column(name = "PR_VISIT_RID")
    private Integer prVisitRid;

    @Column(name = "PR_DRAFT_BILL_RID")
    private Integer prDraftBillId;

    @Column(name = "PR_OT_ROOM_RID")
    private Integer prOtRoomRid;

    @Column(name = "PR_OT_ROOM_NAME")
    private String prOtRoomName;

    @Column(name = "PR_PRIMARY_DOCTOR_RID")
    private Integer prPrimaryDoctorRid;

    @Column(name = "PR_SURGERY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar prSurgeryDateTime;

    @Column(name = "PR_ESTIMATED_DURATION")
    private String prEstimatedDuration;

    @Column(name = "PR_ESTIMATED_DURATION_MIN_INDEX")
    private String prEstimatedDurationMinIndex;

    @Column(name = "PR_TECHNICIAN_RID")
    private Integer prTechnicianRid;

    @Column(name = "PR_OT_ASSISTANT_RID")
    private Integer prOtAssistantRid;

    @Column(name = "PR_ANESTHESIST_TYPE")
    private Integer prAnesthesistType;

    @Column(name = "PR_ANESTHESIST_RID")
    private Integer prAnesthesistRid;

    @Column(name = "PR_ANESTHESIST_REQUIRED")
    private Integer prAnesthesistRequired;

    @Column(name = "PR_IS_TRANSPLANT_REQUESTED")
    private Integer prIsTransplantRequested;

    @Column(name = "PR_TRANSPLANT_NOTES")
    private String prTransplantNotes;

    @Column(name = "PR_IS_BLOOD_REQUESTED")
    private Integer prIsBloodRequested;

    @Column(name = "PR_BLOOD_NOTES")
    private String prBloodNotes;

    @Column(name = "PR_SAFE_SURGERY_CHECKLIST")
    private String prSafeSurgeryChecklist;

    @Column(name = "PR_STATE")
    private Integer prState;

    @Column(name = "PR_STATUS")
    private Integer prStatus;

    @Column(name = "PR_INSTRUCTIONS")
    private String prInstructions;

    @Column(name = "PR_REMARKS")
    private String prRemarks;

    @Column(name = "PR_OT_COMPLICATIONS")
    private String prOtComplications;

    @Column(name = "POST_OT_COMPLICATIONS")
    private String postOtComplications;

    @Column(name = "PR_POST_INSTRUCTIONS")
    private String prPostInstructions;

    @Column(name = "PR_FOLLOW_UP")
    private String prFollowUp;

    @Column(name = "PR_CAN_DRINK_NORMALLY")
    private Integer prCanDrinkNormally;

    @Column(name = "PR_DE_NORMAL_NOTES")
    private String prDeNormalNotes;

    @Column(name = "PR_LAST_FLUID_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar prLastFluidTime;

    @Column(name = "PR_LAST_FOOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar prLastFoodTime;

    @Column(name = "PR_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "PR_CREATED_DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "PR_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "PR_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

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

    public Integer getPrDraftBillId() {
        return prDraftBillId;
    }

    public void setPrDraftBillId(Integer prDraftBillId) {
        this.prDraftBillId = prDraftBillId;
    }

    public Integer getPrOtRoomRid() {
        return prOtRoomRid;
    }

    public void setPrOtRoomRid(Integer prOtRoomRid) {
        this.prOtRoomRid = prOtRoomRid;
    }

    public String getPrOtRoomName() {
        return prOtRoomName;
    }

    public void setPrOtRoomName(String prOtRoomName) {
        this.prOtRoomName = prOtRoomName;
    }

    public Calendar getPrSurgeryDateTime() {
        return prSurgeryDateTime;
    }

    public void setPrSurgeryDateTime(Calendar prSurgeryDateTime) {
        this.prSurgeryDateTime = prSurgeryDateTime;
    }

    public String getPrEstimatedDuration() {
        return prEstimatedDuration;
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

    public Calendar getPrLastFluidTime() {
        return prLastFluidTime;
    }

    public void setPrLastFluidTime(Calendar prLastFluidTime) {
        this.prLastFluidTime = prLastFluidTime;
    }

    public Calendar getPrLastFoodTime() {
        return prLastFoodTime;
    }

    public void setPrLastFoodTime(Calendar prLastFoodTime) {
        this.prLastFoodTime = prLastFoodTime;
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

        sb.append("\n\tprVisitRid: Integer=");
        sb.append(prVisitRid);
        sb.append(";");

        sb.append("\n\tprOtRoomRid: Integer=");
        sb.append(prOtRoomRid);
        sb.append(";");

        sb.append("\n\tprOtRoomName: Integer=");
        sb.append(prOtRoomName);
        sb.append(";");

        sb.append("\n\tprPrimaryDoctorRid: Integer=");
        sb.append(prPrimaryDoctorRid);
        sb.append(";");

        sb.append("\n\tprSurgeryDateTime: Calendar=");
        sb.append(prSurgeryDateTime);
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

        sb.append("\n\tprAnesthesistRid: Integer=");
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

        sb.append("\n\tprSafeSurgeryChecklist: String=");
        sb.append(prSafeSurgeryChecklist);
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

        sb.append("\n\tprLastFluidTime: Calendar=");
        sb.append(prLastFluidTime);
        sb.append(";");

        sb.append("\n\tprLastFoodTime: Calendar=");
        sb.append(prLastFoodTime);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
