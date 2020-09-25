package com.dcomet.health.web.rest;

import com.dcomet.health.domain.Complaints;
import com.dcomet.health.domain.ComplaintsSearchCriteria;
import com.dcomet.health.domain.ComplaintsSearchRequest;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMap;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMapSearchCriteria;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMapSearchRequest;
import com.dcomet.health.domain.History;
import com.dcomet.health.domain.HistorySearchCriteria;
import com.dcomet.health.domain.HistorySearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.domain.VisitPlan;
import com.dcomet.health.domain.VisitPlanSearchCriteria;
import com.dcomet.health.domain.VisitPlanSearchRequest;
import com.dcomet.health.domain.VisitTemplate;
import com.dcomet.health.domain.VisitTemplateSearchCriteria;
import com.dcomet.health.domain.VisitTemplateSearchRequest;
import com.dcomet.health.domain.VisitVitals;
import com.dcomet.health.domain.VisitVitalsSearchCriteria;
import com.dcomet.health.domain.VisitVitalsSearchRequest;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.BedChargeVoSearchCriteria;
import com.dcomet.health.vo.BedChargeVoSearchRequest;
import com.dcomet.health.vo.FreeConsultationVoSearchCriteria;
import com.dcomet.health.vo.FreeConsultationVoSearchRequest;
import com.dcomet.health.vo.FreeConsutationVo;
import com.dcomet.module.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("clinical/v1")
public class ClinicalResource extends BaseResource {

    @Autowired
    @Qualifier("clinicalService")
    public ClinicalService clinicalService;

    @POST
    @Path("/mypatients/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Visit visit) {
        addSecurityContext(securityContext, visit);
        clinicalService.saveMyPatient(visit, true);
    }

// ----------Complaints----------------
    @POST
    @Path("/complaints/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Complaints> search(@Context final SecurityContext securityContext, ComplaintsSearchCriteria complaintsSearchCriteria) {
        ComplaintsSearchRequest complaintsSearchRequest = new ComplaintsSearchRequest();
        addSecurityContext(securityContext, complaintsSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("cplEntityRid", complaintsSearchRequest.getEntityRid()));
        complaintsSearchRequest.setSearchCriterionList(searchCriterionList);
        complaintsSearchRequest.addComplaintsSearchCriteria(complaintsSearchCriteria);
        return clinicalService.getComplaints(complaintsSearchRequest);

    }

    @POST
    @Path("/complaints/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, List<Complaints> complaintsList) {
        User user = getLoginUser(securityContext);
        return clinicalService.saveComplaints(user.getEntityRid(), complaintsList);
    }

    @POST
    @Path("/history/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer saveHistory(@Context final SecurityContext securityContext, List<History> historys) {
        User user = getLoginUser(securityContext);
        return clinicalService.saveHistory(user.getEntityRid(), historys);
    }

    @POST
    @Path("/history/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<History> searchHistory(@Context final SecurityContext securityContext, HistorySearchCriteria historySearchCriteria) {
        HistorySearchRequest historySearchRequest = new HistorySearchRequest();
        addSecurityContext(securityContext, historySearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("hisEntityRid", historySearchRequest.getEntityRid()));
        historySearchRequest.setSearchCriterionList(searchCriterionList);
        historySearchRequest.addHistoryCriteria(historySearchCriteria);
        return clinicalService.getHistorys(historySearchRequest);
    }

    // ----------VisitVitals----------------
    @POST
    @Path("/visitvitals/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<VisitVitals> search(@Context final SecurityContext securityContext, VisitVitalsSearchCriteria visitVitalsSearchCriteria) {
        VisitVitalsSearchRequest visitVitalsSearchRequest = new VisitVitalsSearchRequest();
        addSecurityContext(securityContext, visitVitalsSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("vitEntityRid", visitVitalsSearchRequest.getEntityRid()));
        visitVitalsSearchRequest.setSearchCriterionList(searchCriterionList);
        visitVitalsSearchRequest.addVisitVitalsSearchCriteria(visitVitalsSearchCriteria);
        return clinicalService.getVisitVitals(visitVitalsSearchRequest);
    }

    //----ServiceRequest---
    @POST
    @Path("/visitVitals/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, VisitVitals visitVitals) {
        addSecurityContext(securityContext, visitVitals);
        return clinicalService.saveVisitVitals(visitVitals);
    }

    //-----------VisitTemplate---------------
    @POST
    @Path("/visitTemplate/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, VisitTemplate visitTemplate) {
        addSecurityContext(securityContext, visitTemplate);
        return clinicalService.saveVisitTemplate(visitTemplate);
    }

    @POST
    @Path("/visittemplate/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<VisitTemplate> search(VisitTemplateSearchCriteria visitTemplateSearchCriteria) {
        VisitTemplateSearchRequest visitTemplateSearchRequest = new VisitTemplateSearchRequest();
        visitTemplateSearchRequest.addVisitTemplateSearchCriteria(visitTemplateSearchCriteria);
        return clinicalService.getVisitTemplate(visitTemplateSearchRequest);

    }

//    @POST
//    @Path("/visitTemplate/save")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public void save(@Context final SecurityContext securityContext, VisitTemplate visitTemplate) {
//        addSecurityContext(securityContext, visitTemplate);
//        clinicalService.saveVisitTemplate(visitTemplate);
//    }
    // ----------VisitPlan----------------
    @POST
    @Path("/visitpatient/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<VisitPlan> search(VisitPlanSearchCriteria visitPlanSearchCriteria) {
        VisitPlanSearchRequest visitPlanSearchRequest = new VisitPlanSearchRequest();
        visitPlanSearchRequest.addVisitPlanSearchCriteria(visitPlanSearchCriteria);
        return clinicalService.getVisitPlan(visitPlanSearchRequest);

    }

//    @POST
//    @Path("/visitPlan/save")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public void save(@Context final SecurityContext securityContext, VisitPlan visitPlan) {
//        addSecurityContext(securityContext, visitPlan);
//        clinicalService.saveVisitPlan(visitPlan);
//    }   
    @POST
    @Path("/fcpatdocmap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FreeConsultationPatientDoctorMap> search(@Context final SecurityContext securityContext, FreeConsultationPatientDoctorMapSearchCriteria freeConsultationPatientDoctorMapSearchCriteria) {
        FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest = new FreeConsultationPatientDoctorMapSearchRequest();
        addSecurityContext(securityContext, freeConsultationPatientDoctorMapSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("fcEntityRid", freeConsultationPatientDoctorMapSearchRequest.getEntityRid()));
        freeConsultationPatientDoctorMapSearchRequest.setSearchCriterionList(searchCriterionList);
        freeConsultationPatientDoctorMapSearchRequest.addFreeConsultationPatientDoctorMapCriteria(freeConsultationPatientDoctorMapSearchCriteria);
        return clinicalService.getFreeConsultationPatientDoctorMap(freeConsultationPatientDoctorMapSearchRequest);

    }

    @POST
    @Path("/freeconvo/search")
    @Consumes("application/json")
    @Produces("application/json")
    public FreeConsutationVo searchFreeConsutationVo(@Context final SecurityContext securityContext, FreeConsultationVoSearchCriteria freeConsultationVoSearchCriteria) {
        FreeConsultationVoSearchRequest freeConsultationVoSearchRequest = new FreeConsultationVoSearchRequest();
        freeConsultationVoSearchRequest.addFreeConsultationVoSearchRequest(freeConsultationVoSearchCriteria);
        Integer doctorRid = freeConsultationVoSearchCriteria.getFcDoctorRid();
        Integer patRid = freeConsultationVoSearchCriteria.getFcPatientRid();
        return clinicalService.getFreeConsultationVo(doctorRid, patRid);
    }

    @POST
    @Path("/bedchargevo/search")
    @Consumes("application/json")
    @Produces("application/json")
    public BedChargeVo getBedCharge(@Context final SecurityContext securityContext, BedChargeVoSearchCriteria bedChargeVoSearchCriteria) {
        BedChargeVoSearchRequest bedChargeVoSearchRequest = new BedChargeVoSearchRequest();
        bedChargeVoSearchRequest.addBedChargeVoSearchRequest(bedChargeVoSearchCriteria);
        Integer visitRid = bedChargeVoSearchCriteria.getBcVisitRid();
        Integer entityRid = bedChargeVoSearchCriteria.getBcBedEntityRid();
        return clinicalService.getBedCharge(visitRid, entityRid);
    }
}
