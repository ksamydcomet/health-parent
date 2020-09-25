package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.Visit;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import static com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl.IDRAFT;
import com.dcomet.health.domain.AdmissionDetails;
import com.dcomet.health.domain.Appointment;
import com.dcomet.health.domain.ProcedureRequestH;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.ProcedureRequestService;
import com.sun.org.apache.bcel.internal.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("visitService")
@Transactional(propagation = Propagation.SUPPORTS)
public class VisitServiceImpl extends BaseWorkFlowServiceImpl {

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("procedureRequestService")
    ProcedureRequestService procedureRequestService;

    @Autowired
    @Qualifier("billingService")
    WorkFlowService billingService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) {
        Integer patientRid = null;
        Visit visit = (Visit) object;
        visit.setVisState(nextState);
        visit.setVisStatus(nextState);
        visit.setVisDate(visit.getCurrentDateByUTZ());

        //--PatientSave--
        if (CollectionUtils.isNotEmpty(visit.getPatient())) {
            for (Patient patient : visit.getPatient()) {
                // SET IMPARTANT
                patient.setEntityRid(visit.getEntityRid());
                patient.setCurrentObject(object);
                patientRid = clinicalService.savePatient(patient);
            }
        }
        if (patientRid != null) {
            visit.setVisPatRid(patientRid);
        } else {
            patientRid = visit.getVisPatRid();
        }

        Integer visitId = clinicalService.saveVisit(visit);

        if (visit.getVisApptRid() != null && visit.getVisApptRid() != 0) {
            Appointment appointment = new Appointment();
            appointment.setId(visit.getVisApptRid());
            appointment.setApptPatientRID(visit.getVisPatRid());
            appointment.setApptStatus(2);
            appointment.setApptvisitRID(visitId);
            appointment.setApptPatientMrn(visit.getPatient().get(0).getPatMrnNo());
            clinicalService.saveAppointment(appointment, false);
        } else {
//            clinicalService.saveFreeConsultationPatientDoctorMap(visit);
        }
        ProcedureRequestH procedureRequestH = new ProcedureRequestH();
        if (CollectionUtils.isNotEmpty(visit.getProcedureRequestHs())) {
            procedureRequestH = visit.getProcedureRequestHs().get(0);
            procedureRequestH.setCurrentObject(visit);
            procedureRequestH.setPrVisitRid(visitId);
            procedureRequestH.setPrPatientRid(patientRid);
            if (visit.getVisTypeIndex() != 20) {
                int id = 0;
                if (!Objects.equals(procedureRequestH.getPrActionCode(), "REQUEST")) {
                    id = procedureRequestH.getId();
                }
                procedureRequestService.save(procedureRequestH, id, "PROCEDURE_REQUEST", procedureRequestH.getPrActionCode());
            }
        }
        AdmissionDetails admissionDetails = new AdmissionDetails();
        if (visit.getAdmissionDetails() != null) {
            admissionDetails = visit.getAdmissionDetails();
            admissionDetails.setCurrentObject(visit);
            admissionDetails.setAdVisitRid(visitId);
            admissionDetails.setAdPatientRid(patientRid);
            clinicalService.saveAdmissionDetails(admissionDetails);
        }
        if (CollectionUtils.isNotEmpty(visit.getBillH())) {
            for (BillH billH : visit.getBillH()) {
                billH.setCurrentObject(visit);
                billH.setBhPatientRID(patientRid);
                if (billH.getBhPayerRID() == null) {
                    billH.setBhPayerRID(patientRid);
                }
                billH.setBhPatientVisitRID(visitId);
                if (visit.getVisTypeIndex() == 19) {
                    billingService.save(billH, IDRAFT, "BILL", "REQUEST");
                } else {
                    billingService.save(billH, IDRAFT, "BILL", "BUILTIN_ACTION");
                }
            }
        }
        return patientRid;
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        Visit visit = clinicalService.getVisit(boRID);
        return visit != null ? visit.getVisState() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
