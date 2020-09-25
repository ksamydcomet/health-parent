package com.dcomet.health.web.rest;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.VisitSearchCriteria;
import com.dcomet.health.domain.VisitSearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.domain.Ddict;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("visit/v1")
public class VisitResource extends BaseResource {

    @Autowired
    @Qualifier("clinicalService")
    public ClinicalService clinicalService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    @Autowired
    @Qualifier("billingService")
    public BillingService billingService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("visitService")
    public WorkFlowService visitService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, Visit visit, @PathParam("boRID") final Integer boRID,
            @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, visit);
        return visitService.save(visit, boRID, boCode, actionCode);
    }

//    @POST
//    @Path("/save/{boRID}/{boCode}/{actionCode}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public BillH saveVisitWithBillAlone(@Context final SecurityContext securityContext, Visit visit, @PathParam("boRID") final Integer boRID,
//            @PathParam("boCode") final String boCode,
//            @PathParam("actionCode") final String actionCode) {
//        addSecurityContext(securityContext, visit);
//        Visit visit1 = new Visit();
//        visit1 = visit;
//        visit1.setBillH(null);
//        int patRid = visitService.save(visit1, boRID, boCode, actionCode);
//        String dateStop = DateUtil.convertCalendarToString(Calendar.getInstance());
//        Date d2 = null;
//        d2 = DateUtil.convertStringToDate(dateStop);
//        visit = clinicalService.getActiveVisit(patRid, d2);
//        b
//    }
    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, Visit Visit) {
        addSecurityContext(securityContext, Visit);
        return clinicalService.saveVisit(Visit);
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Visit> search(@Context final SecurityContext securityContext, VisitSearchCriteria visitSearchCriteria) {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        addSecurityContext(securityContext, visitSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visEntRid", visitSearchRequest.getEntityRid()));
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        visitSearchRequest.addVisitCriteria(visitSearchCriteria);
        return clinicalService.getVisit(visitSearchRequest, true);
    }

    @POST
    @Path("/patient/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Visit> getIpVisit(@Context final SecurityContext securityContext, VisitSearchCriteria visitSearchCriteria) {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        addSecurityContext(securityContext, visitSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visEntRid", visitSearchRequest.getEntityRid()));
        List<Ddict> ddicts = dataDictionaryService.getDdictByType("VISIT_TYPE");
        for (Ddict ddict : ddicts) {
            if (Objects.equals(ddict.getDdictValue(), "IP")) {
                searchCriterionList.add(Restrictions.eq("visTypeIndex", ddict.getId()));
            }
        }
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        visitSearchRequest.addVisitCriteria(visitSearchCriteria);
        return clinicalService.getIpVisit(visitSearchRequest, true);
    }

    @POST
    @Path("/active/visit/search")
    @Consumes("application/json")
    @Produces("application/json")
    public Visit getActiveVisit(@Context final SecurityContext securityContext, VisitSearchCriteria visitSearchCriteria) {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        addSecurityContext(securityContext, visitSearchRequest);
        int patRid = visitSearchCriteria.getVisPatRid();
        Date visitDate = DateUtil.convertStringToDate(visitSearchCriteria.getVisDate());
        return clinicalService.getActiveVisit(patRid, visitDate);
    }

    @POST
    @Path("/active/ip/visit/search")
    @Consumes("application/json")
    @Produces("application/json")
    public Visit getActiveIpVisit(@Context final SecurityContext securityContext, VisitSearchCriteria visitSearchCriteria) {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        addSecurityContext(securityContext, visitSearchRequest);
        int patRid = visitSearchCriteria.getVisPatRid();
        return clinicalService.getActiveIpVisitByPatientRid(patRid);
    }

    @POST
    @Path("/csprint")
    @Consumes("application/json")
    @Produces("application/json")
    public String getCsPrint(@Context final SecurityContext securityContext, VisitSearchCriteria visitSearchCriteria) {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        addSecurityContext(securityContext, visitSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visEntRid", visitSearchRequest.getEntityRid()));
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        visitSearchRequest.addVisitCriteria(visitSearchCriteria);
        return clinicalService.getCsPrint(visitSearchRequest);
    }

    @POST
    @Path("/send/email")
    @Consumes("application/json")
    @Produces("application/json")
    public void sendEmail(@Context final SecurityContext securityContext, Visit visit) {
        addSecurityContext(securityContext, visit);
        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("id", visit.getVisPatRid()));
        searchCriterionList.add(Restrictions.eq("patEntRid", visit.getEntityRid()));
        patientSearchRequest.setSearchCriterionList(searchCriterionList);
        List<Patient> patient = clinicalService.getPatient(patientSearchRequest, false);
        if (CollectionUtils.isNotEmpty(patient)) {
            for (Patient patientData : patient) {
                if (patientData.getPatEmailId() != null) {
                    masterService.createMail("", patientData.getPatEmailId(), "PAT_MAIL", visit, "patMail");
                }
            }
        }
    }
}
