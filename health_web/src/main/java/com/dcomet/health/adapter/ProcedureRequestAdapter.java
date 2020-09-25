package com.dcomet.health.adapter;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.ProcedureAnesthesistData;
import com.dcomet.health.dao.data.ProcedureAttendDoctorData;
import com.dcomet.health.dao.data.ProcedureNurseData;
import com.dcomet.health.dao.data.ProcedureRequestHData;
import com.dcomet.health.dao.data.ProcedureTechnicianData;
import com.dcomet.health.domain.ProcedureAnesthesist;
import com.dcomet.health.domain.ProcedureAttendDoctor;
import com.dcomet.health.domain.ProcedureNurse;
import com.dcomet.health.domain.ProcedureRequestH;
import com.dcomet.health.domain.ProcedureTechnician;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author ABDUL
 */
@Component("procedureRequestAdapter")
public class ProcedureRequestAdapter {

    //--------------ProcedureRequestH----------------------------       
    public List<ProcedureRequestH> convertProcedureRequestHDataToProcedureRequestH(List<ProcedureRequestHData> procedureRequestHDataList) throws DcometServiceException {
        List<ProcedureRequestH> procedureRequestHs = new ArrayList<>();
        for (ProcedureRequestHData procedureRequestHData : procedureRequestHDataList) {
            procedureRequestHs.add(convertProcedureRequestHDataToProcedureRequestH(procedureRequestHData));
        }
        return procedureRequestHs;
    }

    public List<ProcedureRequestHData> convertProcedureRequestHToProcedureRequestHData(List<ProcedureRequestH> procedureRequestHList)
            throws DcometServiceException {
        List<ProcedureRequestHData> procedureRequestHDataList = new ArrayList<>();
        for (ProcedureRequestH procedureRequestH : procedureRequestHList) {
            procedureRequestHDataList.add(convertProcedureRequestHToProcedureRequestHData(procedureRequestH));
        }
        return procedureRequestHDataList;
    }

    public ProcedureRequestH convertProcedureRequestHDataToProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometServiceException {
        ProcedureRequestH procedureRequestH = new ProcedureRequestH();

        if (procedureRequestHData.getId() != null) {
            procedureRequestH.setId(procedureRequestHData.getId());
        }
        if (procedureRequestHData.getPrType() != null) {
            procedureRequestH.setPrType(procedureRequestHData.getPrType());
        }
        if (procedureRequestHData.getPrCategory() != null) {
            procedureRequestH.setPrCategory(procedureRequestHData.getPrCategory());
        }
        if (procedureRequestHData.getPrPatientRid() != null) {
            procedureRequestH.setPrPatientRid(procedureRequestHData.getPrPatientRid());
        }
        if (procedureRequestHData.getPrPrimaryProcedure() != null) {
            procedureRequestH.setPrPrimaryProcedure(procedureRequestHData.getPrPrimaryProcedure());
        }
        if (procedureRequestHData.getPrPrimaryDoctorRid() != null) {
            procedureRequestH.setPrPrimaryDoctorRid(procedureRequestHData.getPrPrimaryDoctorRid());
        }
        if (procedureRequestHData.getPrProcedureRid() != null) {
            procedureRequestH.setPrProcedureRid(procedureRequestHData.getPrProcedureRid());
        }
        if (procedureRequestHData.getPrVisitRid() != null) {
            procedureRequestH.setPrVisitRid(procedureRequestHData.getPrVisitRid());
        }
        if (procedureRequestHData.getPrDraftBillId() != null) {
            procedureRequestH.setPrDraftBillId(procedureRequestHData.getPrDraftBillId());
        }
        if (procedureRequestHData.getPrOtRoomRid() != null) {
            procedureRequestH.setPrOtRoomRid(procedureRequestHData.getPrOtRoomRid());
        }
        if (procedureRequestHData.getPrOtRoomName() != null) {
            procedureRequestH.setPrOtRoomName(procedureRequestHData.getPrOtRoomName());
        }
        if (procedureRequestHData.getPrSurgeryDateTime() != null) {
            procedureRequestH.setPrSurgeryDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrSurgeryDateTime()));
        }
        if (procedureRequestHData.getPrEstimatedDuration() != null) {
            procedureRequestH.setPrEstimatedDuration(procedureRequestHData.getPrEstimatedDuration());
        }
        if (procedureRequestHData.getPrEstimatedDurationMinIndex() != null) {
            procedureRequestH.setPrEstimatedDurationMinIndex(procedureRequestHData.getPrEstimatedDurationMinIndex());
        }
        if (procedureRequestHData.getPrTechnicianRid() != null) {
            procedureRequestH.setPrTechnicianRid(procedureRequestHData.getPrTechnicianRid());
        }
        if (procedureRequestHData.getPrOtAssistantRid() != null) {
            procedureRequestH.setPrOtAssistantRid(procedureRequestHData.getPrOtAssistantRid());
        }
        if (procedureRequestHData.getPrAnesthesistType() != null) {
            procedureRequestH.setPrAnesthesistType(procedureRequestHData.getPrAnesthesistType());
        }
        if (procedureRequestHData.getPrAnesthesistRid() != null) {
            procedureRequestH.setPrAnesthesistRid(procedureRequestHData.getPrAnesthesistRid());
        }
        if (procedureRequestHData.getPrAnesthesistRequired() != null) {
            procedureRequestH.setPrAnesthesistRequired(procedureRequestHData.getPrAnesthesistRequired());
        }
        if (procedureRequestHData.getPrIsTransplantRequested() != null) {
            procedureRequestH.setPrIsTransplantRequested(procedureRequestHData.getPrIsTransplantRequested());
        }
        if (procedureRequestHData.getPrTransplantNotes() != null) {
            procedureRequestH.setPrTransplantNotes(procedureRequestHData.getPrTransplantNotes());
        }
        if (procedureRequestHData.getPrIsBloodRequested() != null) {
            procedureRequestH.setPrIsBloodRequested(procedureRequestHData.getPrIsBloodRequested());
        }
        if (procedureRequestHData.getPrBloodNotes() != null) {
            procedureRequestH.setPrBloodNotes(procedureRequestHData.getPrBloodNotes());
        }
        if (procedureRequestHData.getPrSafeSurgeryChecklist() != null) {
            procedureRequestH.setPrSafeSurgeryChecklist(procedureRequestHData.getPrSafeSurgeryChecklist());
        }
        if (procedureRequestHData.getPrState() != null) {
            procedureRequestH.setPrState(procedureRequestHData.getPrState());
        }
        if (procedureRequestHData.getPrStatus() != null) {
            procedureRequestH.setPrStatus(procedureRequestHData.getPrStatus());
        }
        if (procedureRequestHData.getPrInstructions() != null) {
            procedureRequestH.setPrInstructions(procedureRequestHData.getPrInstructions());
        }
        if (procedureRequestHData.getPrRemarks() != null) {
            procedureRequestH.setPrRemarks(procedureRequestHData.getPrRemarks());
        }
        if (procedureRequestHData.getPrOtComplications() != null) {
            procedureRequestH.setPrOtComplications(procedureRequestHData.getPrOtComplications());
        }
        if (procedureRequestHData.getPrPostInstructions() != null) {
            procedureRequestH.setPrPostInstructions(procedureRequestHData.getPrPostInstructions());
        }
        if (procedureRequestHData.getPostOtComplications() != null) {
            procedureRequestH.setPostOtComplications(procedureRequestHData.getPostOtComplications());
        }
        if (procedureRequestHData.getPrFollowUp() != null) {
            procedureRequestH.setPrFollowUp(procedureRequestHData.getPrFollowUp());
        }
        if (procedureRequestHData.getPrCanDrinkNormally() != null) {
            procedureRequestH.setPrCanDrinkNormally(procedureRequestHData.getPrCanDrinkNormally());
        }
        if (procedureRequestHData.getPrLastFluidTime() != null) {
            procedureRequestH.setPrLastFluidTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrLastFluidTime()));
        }
        if (procedureRequestHData.getPrLastFoodTime() != null) {
            procedureRequestH.setPrLastFoodTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrLastFoodTime()));
        }
        if (procedureRequestHData.getCreatedUserRid() != null) {
            procedureRequestH.setCreatedUserRid(procedureRequestHData.getCreatedUserRid());
        }
        if (procedureRequestHData.getCreatedDateTime() != null) {
            procedureRequestH.setCreatedDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getCreatedDateTime()));
        }
        if (procedureRequestHData.getModifiedUserRid() != null) {
            procedureRequestH.setModifiedUserRid(procedureRequestHData.getModifiedUserRid());
        }
        if (procedureRequestHData.getModifiedDateTime() != null) {
            procedureRequestH.setModifiedDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getModifiedDateTime()));
        }
        return procedureRequestH;
    }

    public ProcedureRequestHData convertProcedureRequestHToProcedureRequestHData(ProcedureRequestH procedureRequestH) throws DcometServiceException {
        ProcedureRequestHData procedureRequestHData = new ProcedureRequestHData();

        if (procedureRequestH.getId() != null) {
            procedureRequestHData.setId(procedureRequestH.getId());
        }
        if (procedureRequestH.getPrType() != null) {
            procedureRequestHData.setPrType(procedureRequestH.getPrType());
        }
        if (procedureRequestH.getPrCategory() != null) {
            procedureRequestHData.setPrCategory(procedureRequestH.getPrCategory());
        }
        if (procedureRequestH.getPrPatientRid() != null) {
            procedureRequestHData.setPrPatientRid(procedureRequestH.getPrPatientRid());
        }
        if (procedureRequestH.getPrPrimaryProcedure() != null) {
            procedureRequestHData.setPrPrimaryProcedure(procedureRequestH.getPrPrimaryProcedure());
        }
        if (procedureRequestH.getPrProcedureRid() != null) {
            procedureRequestHData.setPrProcedureRid(procedureRequestH.getPrProcedureRid());
        }
        if (procedureRequestH.getPrVisitRid() != null) {
            procedureRequestHData.setPrVisitRid(procedureRequestH.getPrVisitRid());
        }
        if (procedureRequestH.getPrDraftBillId() != null) {
            procedureRequestHData.setPrDraftBillId(procedureRequestH.getPrDraftBillId());
        }
        if (procedureRequestH.getPrOtRoomRid() != null) {
            procedureRequestHData.setPrOtRoomRid(procedureRequestH.getPrOtRoomRid());
        }
        if (procedureRequestH.getPrOtRoomName() != null) {
            procedureRequestHData.setPrOtRoomName(procedureRequestH.getPrOtRoomName());
        }
        if (procedureRequestH.getPrPrimaryDoctorRid() != null) {
            procedureRequestHData.setPrPrimaryDoctorRid(procedureRequestH.getPrPrimaryDoctorRid());
        }
        if (procedureRequestH.getPrSurgeryDateTime() != null) {
            procedureRequestHData.setPrSurgeryDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrSurgeryDateTime()));
        }
        if (procedureRequestH.getPrEstimatedDuration() != null) {
            procedureRequestHData.setPrEstimatedDuration(procedureRequestH.getPrEstimatedDuration());
        }
        if (procedureRequestH.getPrEstimatedDurationMinIndex() != null) {
            procedureRequestHData.setPrEstimatedDurationMinIndex(procedureRequestH.getPrEstimatedDurationMinIndex());
        }
        if (procedureRequestH.getPrTechnicianRid() != null) {
            procedureRequestHData.setPrTechnicianRid(procedureRequestH.getPrTechnicianRid());
        }
        if (procedureRequestH.getPrOtAssistantRid() != null) {
            procedureRequestHData.setPrOtAssistantRid(procedureRequestH.getPrOtAssistantRid());
        }
        if (procedureRequestH.getPrAnesthesistType() != null) {
            procedureRequestHData.setPrAnesthesistType(procedureRequestH.getPrAnesthesistType());
        }
        if (procedureRequestH.getPrAnesthesistRid() != null) {
            procedureRequestHData.setPrAnesthesistRid(procedureRequestH.getPrAnesthesistRid());
        }
        if (procedureRequestH.getPrAnesthesistRequired() != null) {
            procedureRequestHData.setPrAnesthesistRequired(procedureRequestH.getPrAnesthesistRequired());
        }
        if (procedureRequestH.getPrIsTransplantRequested() != null) {
            procedureRequestHData.setPrIsTransplantRequested(procedureRequestH.getPrIsTransplantRequested());
        }
        if (procedureRequestH.getPrTransplantNotes() != null) {
            procedureRequestHData.setPrTransplantNotes(procedureRequestH.getPrTransplantNotes());
        }
        if (procedureRequestH.getPrIsBloodRequested() != null) {
            procedureRequestHData.setPrIsBloodRequested(procedureRequestH.getPrIsBloodRequested());
        }
        if (procedureRequestH.getPrBloodNotes() != null) {
            procedureRequestHData.setPrBloodNotes(procedureRequestH.getPrBloodNotes());
        }
        if (procedureRequestH.getPrSafeSurgeryChecklist() != null) {
            procedureRequestHData.setPrSafeSurgeryChecklist(procedureRequestH.getPrSafeSurgeryChecklist());
        }
        if (procedureRequestH.getPrState() != null) {
            procedureRequestHData.setPrState(procedureRequestH.getPrState());
        }
        if (procedureRequestH.getPrStatus() != null) {
            procedureRequestHData.setPrStatus(procedureRequestH.getPrStatus());
        }
        if (procedureRequestH.getPostOtComplications() != null) {
            procedureRequestHData.setPostOtComplications(procedureRequestH.getPostOtComplications());
        }
        if (procedureRequestH.getPrInstructions() != null) {
            procedureRequestHData.setPrInstructions(procedureRequestH.getPrInstructions());
        }
        if (procedureRequestH.getPrRemarks() != null) {
            procedureRequestHData.setPrRemarks(procedureRequestH.getPrRemarks());
        }
        if (procedureRequestH.getPrOtComplications() != null) {
            procedureRequestHData.setPrOtComplications(procedureRequestH.getPrOtComplications());
        }
        if (procedureRequestH.getPrPostInstructions() != null) {
            procedureRequestHData.setPrPostInstructions(procedureRequestH.getPrPostInstructions());
        }
        if (procedureRequestH.getPrFollowUp() != null) {
            procedureRequestHData.setPrFollowUp(procedureRequestH.getPrFollowUp());
        }
        if (procedureRequestH.getPrCanDrinkNormally() != null) {
            procedureRequestHData.setPrCanDrinkNormally(procedureRequestH.getPrCanDrinkNormally());
        }
        if (procedureRequestH.getPrLastFluidTime() != null) {
            procedureRequestHData.setPrLastFluidTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrLastFluidTime()));
        }
        if (procedureRequestH.getPrLastFoodTime() != null) {
            procedureRequestHData.setPrLastFoodTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrLastFoodTime()));
        }
        if (procedureRequestH.getCreatedUserRid() != null) {
            procedureRequestHData.setCreatedUserRid(procedureRequestH.getCreatedUserRid());
        }
        if (procedureRequestH.getCreatedDateTime() != null) {
            procedureRequestHData.setCreatedDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getCreatedDateTime()));
        }
        if (procedureRequestH.getModifiedUserRid() != null) {
            procedureRequestHData.setModifiedUserRid(procedureRequestH.getModifiedUserRid());
        }
        if (procedureRequestH.getModifiedDateTime() != null) {
            procedureRequestHData.setModifiedDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getModifiedDateTime()));
        }
        return procedureRequestHData;
    }

    //-----------ProcedureTechnician-----------
    public List<ProcedureTechnician> convertProcedureTechnicianDataToProcedureTechnician(
            List<ProcedureTechnicianData> resultData) throws DcometServiceException {
        List<ProcedureTechnician> result = new ArrayList<>();
        for (ProcedureTechnicianData child1Data : resultData) {
            result.add(convertProcedureTechnicianDataToProcedureTechnician(child1Data));
        }
        return result;
    }

    public List<ProcedureTechnicianData> convertProcedureTechnicianToProcedureTechnicianData(List<ProcedureTechnician> procedureTechnicianList)
            throws DcometServiceException {
        List<ProcedureTechnicianData> procedureTechnicianDataList = new ArrayList<>();
        for (ProcedureTechnician procedureTechnician : procedureTechnicianList) {
            procedureTechnicianDataList.add(convertProcedureTechnicianToProcedureTechnicianData(procedureTechnician));
        }
        return procedureTechnicianDataList;
    }

    public ProcedureTechnician convertProcedureTechnicianDataToProcedureTechnician(ProcedureTechnicianData procedureTechnicianData)
            throws DcometServiceException {
        ProcedureTechnician procedureTechnician = new ProcedureTechnician();
        if (procedureTechnicianData.getId() != null) {
            procedureTechnician.setId(procedureTechnicianData.getId());
        }
        if (procedureTechnicianData.getProcedureTechRid() != null) {
            procedureTechnician.setProcedureTechRid(procedureTechnicianData.getProcedureTechRid());
        }
        if (procedureTechnicianData.getProcedureRid() != null) {
            procedureTechnician.setProcedureRid(procedureTechnicianData.getProcedureRid());
        }
        if (procedureTechnicianData.getProcedureEntityRid() != null) {
            procedureTechnician.setProcedureEntityRid(procedureTechnicianData.getProcedureEntityRid());
        }
        if (procedureTechnicianData.getProcedureTechActive() != null) {
            procedureTechnician.setProcedureTechActive(procedureTechnicianData.getProcedureTechActive());
        }

        return procedureTechnician;
    }

    public ProcedureTechnicianData convertProcedureTechnicianToProcedureTechnicianData(ProcedureTechnician procedureTechnician)
            throws DcometServiceException {
        ProcedureTechnicianData procedureTechnicianData = new ProcedureTechnicianData();
        if (procedureTechnician.getId() != null) {
            procedureTechnicianData.setId(procedureTechnician.getId());
        }
        if (procedureTechnician.getProcedureTechRid() != null) {
            procedureTechnicianData.setProcedureTechRid(procedureTechnician.getProcedureTechRid());
        }
        if (procedureTechnician.getProcedureRid() != null) {
            procedureTechnicianData.setProcedureRid(procedureTechnician.getProcedureRid());
        }
        if (procedureTechnician.getProcedureEntityRid() != null) {
            procedureTechnicianData.setProcedureEntityRid(procedureTechnician.getProcedureEntityRid());
        }
        if (procedureTechnician.getProcedureTechActive() != null) {
            procedureTechnicianData.setProcedureTechActive(procedureTechnician.getProcedureTechActive());
        }

        return procedureTechnicianData;
    }

    //-----------ProcedureAttenDoctor-----------
    public List<ProcedureAttendDoctor> convertProcedureAttendDoctorDataToProcedureAttendDoctor(
            List<ProcedureAttendDoctorData> resultData) throws DcometServiceException {
        List<ProcedureAttendDoctor> result = new ArrayList<>();
        for (ProcedureAttendDoctorData child1Data : resultData) {
            result.add(convertProcedureAttendDoctorDataToProcedureAttendDoctor(child1Data));
        }
        return result;
    }

    public List<ProcedureAttendDoctorData> convertProcedureAttendDoctorToProcedureAttendDoctorData(List<ProcedureAttendDoctor> procedureAttendDoctorList)
            throws DcometServiceException {
        List<ProcedureAttendDoctorData> procedureAttendDoctorDataList = new ArrayList<>();
        for (ProcedureAttendDoctor procedureAttendDoctor : procedureAttendDoctorList) {
            procedureAttendDoctorDataList.add(convertProcedureAttendDoctorToProcedureAttendDoctorData(procedureAttendDoctor));
        }
        return procedureAttendDoctorDataList;
    }

    public ProcedureAttendDoctor convertProcedureAttendDoctorDataToProcedureAttendDoctor(ProcedureAttendDoctorData procedureAttendDoctorData)
            throws DcometServiceException {
        ProcedureAttendDoctor procedureAttendDoctor = new ProcedureAttendDoctor();
        if (procedureAttendDoctorData.getId() != null) {
            procedureAttendDoctor.setId(procedureAttendDoctorData.getId());
        }
        if (procedureAttendDoctorData.getProcedureAttDoctorRid() != null) {
            procedureAttendDoctor.setProcedureAttDoctorRid(procedureAttendDoctorData.getProcedureAttDoctorRid());
        }
        if (procedureAttendDoctorData.getProcedureRid() != null) {
            procedureAttendDoctor.setProcedureRid(procedureAttendDoctorData.getProcedureRid());
        }
        if (procedureAttendDoctorData.getProcedureEntityRid() != null) {
            procedureAttendDoctor.setProcedureEntityRid(procedureAttendDoctorData.getProcedureEntityRid());
        }
        if (procedureAttendDoctorData.getProcedureAttDoctorActive() != null) {
            procedureAttendDoctor.setProcedureAttDoctorActive(procedureAttendDoctorData.getProcedureAttDoctorActive());
        }

        return procedureAttendDoctor;
    }

    public ProcedureAttendDoctorData convertProcedureAttendDoctorToProcedureAttendDoctorData(ProcedureAttendDoctor procedureAttendDoctor)
            throws DcometServiceException {
        ProcedureAttendDoctorData procedureAttendDoctorData = new ProcedureAttendDoctorData();
        if (procedureAttendDoctor.getId() != null) {
            procedureAttendDoctorData.setId(procedureAttendDoctor.getId());
        }
        if (procedureAttendDoctor.getProcedureAttDoctorRid() != null) {
            procedureAttendDoctorData.setProcedureAttDoctorRid(procedureAttendDoctor.getProcedureAttDoctorRid());
        }
        if (procedureAttendDoctor.getProcedureRid() != null) {
            procedureAttendDoctorData.setProcedureRid(procedureAttendDoctor.getProcedureRid());
        }
        if (procedureAttendDoctor.getProcedureEntityRid() != null) {
            procedureAttendDoctorData.setProcedureEntityRid(procedureAttendDoctor.getProcedureEntityRid());
        }
        if (procedureAttendDoctor.getProcedureAttDoctorActive() != null) {
            procedureAttendDoctorData.setProcedureAttDoctorActive(procedureAttendDoctor.getProcedureAttDoctorActive());
        }

        return procedureAttendDoctorData;
    }

    //-----------ProcedureTechnician-----------
    public List<ProcedureNurse> convertProcedureNurseDataToProcedureNurse(
            List<ProcedureNurseData> resultData) throws DcometServiceException {
        List<ProcedureNurse> result = new ArrayList<>();
        for (ProcedureNurseData child1Data : resultData) {
            result.add(convertProcedureNurseDataToProcedureNurse(child1Data));
        }
        return result;
    }

    public List<ProcedureNurseData> convertProcedureNurseToProcedureNurseData(List<ProcedureNurse> procedureNurseList)
            throws DcometServiceException {
        List<ProcedureNurseData> procedureNurseDataList = new ArrayList<>();
        for (ProcedureNurse procedureNurse : procedureNurseList) {
            procedureNurseDataList.add(convertProcedureNurseToProcedureNurseData(procedureNurse));
        }
        return procedureNurseDataList;
    }

    public ProcedureNurse convertProcedureNurseDataToProcedureNurse(ProcedureNurseData procedureNurseData)
            throws DcometServiceException {
        ProcedureNurse procedureNurse = new ProcedureNurse();
        if (procedureNurseData.getId() != null) {
            procedureNurse.setId(procedureNurseData.getId());
        }
        if (procedureNurseData.getProcNurseRid() != null) {
            procedureNurse.setProcNurseRid(procedureNurseData.getProcNurseRid());
        }
        if (procedureNurseData.getProcRid() != null) {
            procedureNurse.setProcRid(procedureNurseData.getProcRid());
        }
        if (procedureNurseData.getProcEntityRid() != null) {
            procedureNurse.setProcEntityRid(procedureNurseData.getProcEntityRid());
        }
        if (procedureNurseData.getProcNurseActive() != null) {
            procedureNurse.setProcNurseActive(procedureNurseData.getProcNurseActive());
        }

        return procedureNurse;
    }

    public ProcedureNurseData convertProcedureNurseToProcedureNurseData(ProcedureNurse procedureNurse)
            throws DcometServiceException {
        ProcedureNurseData procedureNurseData = new ProcedureNurseData();
        if (procedureNurse.getId() != null) {
            procedureNurseData.setId(procedureNurse.getId());
        }
        if (procedureNurse.getProcNurseRid() != null) {
            procedureNurseData.setProcNurseRid(procedureNurse.getProcNurseRid());
        }
        if (procedureNurse.getProcRid() != null) {
            procedureNurseData.setProcRid(procedureNurse.getProcRid());
        }
        if (procedureNurse.getProcEntityRid() != null) {
            procedureNurseData.setProcEntityRid(procedureNurse.getProcEntityRid());
        }
        if (procedureNurse.getProcNurseActive() != null) {
            procedureNurseData.setProcNurseActive(procedureNurse.getProcNurseActive());
        }

        return procedureNurseData;
    }

    //-----------ProcedureAnesthesist-----------
    public List<ProcedureAnesthesist> convertProcedureAnesthesistDataToProcedureAnesthesist(
            List<ProcedureAnesthesistData> resultData) throws DcometServiceException {
        List<ProcedureAnesthesist> result = new ArrayList<>();
        for (ProcedureAnesthesistData child1Data : resultData) {
            result.add(convertProcedureAnesthesistDataToProcedureAnesthesist(child1Data));
        }
        return result;
    }

    public List<ProcedureAnesthesistData> convertProcedureAnesthesistToProcedureAnesthesistData(List<ProcedureAnesthesist> procedureAnesthesistList)
            throws DcometServiceException {
        List<ProcedureAnesthesistData> procedureAnesthesistDataList = new ArrayList<>();
        for (ProcedureAnesthesist procedureAnesthesist : procedureAnesthesistList) {
            procedureAnesthesistDataList.add(convertProcedureAnesthesistToProcedureAnesthesistData(procedureAnesthesist));
        }
        return procedureAnesthesistDataList;
    }

    public ProcedureAnesthesist convertProcedureAnesthesistDataToProcedureAnesthesist(ProcedureAnesthesistData procedureAnesthesistData)
            throws DcometServiceException {
        ProcedureAnesthesist procedureAnesthesist = new ProcedureAnesthesist();
        if (procedureAnesthesistData.getId() != null) {
            procedureAnesthesist.setId(procedureAnesthesistData.getId());
        }
        if (procedureAnesthesistData.getProcedureAnesthesRid() != null) {
            procedureAnesthesist.setProcedureAnesthesRid(procedureAnesthesistData.getProcedureAnesthesRid());
        }
        if (procedureAnesthesistData.getProcedureRid() != null) {
            procedureAnesthesist.setProcedureRid(procedureAnesthesistData.getProcedureRid());
        }
        if (procedureAnesthesistData.getProcedureEntityRid() != null) {
            procedureAnesthesist.setProcedureEntityRid(procedureAnesthesistData.getProcedureEntityRid());
        }
        if (procedureAnesthesistData.getProcedureAnesthesistActive() != null) {
            procedureAnesthesist.setProcedureAnesthesistActive(procedureAnesthesistData.getProcedureAnesthesistActive());
        }

        return procedureAnesthesist;
    }

    public ProcedureAnesthesistData convertProcedureAnesthesistToProcedureAnesthesistData(ProcedureAnesthesist procedureAnesthesist)
            throws DcometServiceException {
        ProcedureAnesthesistData procedureAnesthesistData = new ProcedureAnesthesistData();
        if (procedureAnesthesist.getId() != null) {
            procedureAnesthesistData.setId(procedureAnesthesist.getId());
        }
        if (procedureAnesthesist.getProcedureAnesthesRid() != null) {
            procedureAnesthesistData.setProcedureAnesthesRid(procedureAnesthesist.getProcedureAnesthesRid());
        }
        if (procedureAnesthesist.getProcedureRid() != null) {
            procedureAnesthesistData.setProcedureRid(procedureAnesthesist.getProcedureRid());
        }
        if (procedureAnesthesist.getProcedureEntityRid() != null) {
            procedureAnesthesistData.setProcedureEntityRid(procedureAnesthesist.getProcedureEntityRid());
        }
        if (procedureAnesthesist.getProcedureAnesthesistActive() != null) {
            procedureAnesthesistData.setProcedureAnesthesistActive(procedureAnesthesist.getProcedureAnesthesistActive());
        }

        return procedureAnesthesistData;
    }

}
