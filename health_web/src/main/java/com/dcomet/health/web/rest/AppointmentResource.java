package com.dcomet.health.web.rest;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.Appointment;
import com.dcomet.health.domain.AppointmentResourceMap;
import com.dcomet.health.domain.AppointmentResourceMapSearchCriteria;
import com.dcomet.health.domain.AppointmentResourceMapSearchRequest;
import com.dcomet.health.domain.AppointmentSearchCriteria;
import com.dcomet.health.domain.AppointmentSearchRequest;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.vo.AppointmentSlots;
import com.dcomet.health.vo.AppointmentSlotsSearchCriteria;
import com.dcomet.health.vo.AppointmentSlotsSearchRequest;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author Dev4
 */
@Component
@Path("appointment/v1")
public class AppointmentResource extends BaseResource {

    @Autowired
    @Qualifier("clinicalService")
    public ClinicalService clinicalService;

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            addSecurityContext(securityContext, appointment);
            clinicalService.saveAppointment(appointment, true);
        }
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Appointment> search(@Context final SecurityContext securityContext, AppointmentSearchCriteria appointmentSearchCriteria) {
        AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
        addSecurityContext(securityContext, appointmentSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("apptEntRID", appointmentSearchRequest.getEntityRid()));
        appointmentSearchRequest.setSearchCriterionList(searchCriterionList);
        appointmentSearchRequest.addAppointmentCriteria(appointmentSearchCriteria);
        return clinicalService.getAppointment(appointmentSearchRequest, true);
    }

    @POST
    @Path("arm/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<AppointmentResourceMap> search(AppointmentResourceMapSearchCriteria appointmentResourceMapSearchCriteria) {
        AppointmentResourceMapSearchRequest appointmentResourceMapSearchRequest = new AppointmentResourceMapSearchRequest();
        appointmentResourceMapSearchRequest.addAppointmentResourceMapCriteria(appointmentResourceMapSearchCriteria);
        return clinicalService.getAppointmentResourceMap(appointmentResourceMapSearchRequest);
    }

    @POST
    @Path("appointmentslots/search")
    @Consumes("application/json")
    @Produces("application/json")
    public AppointmentSlots search(@Context final SecurityContext securityContext, AppointmentSlotsSearchCriteria appointmentSlotsSearchCriteria) {
        AppointmentSlotsSearchRequest appointmentSlotsSearchRequest = new AppointmentSlotsSearchRequest();
        addSecurityContext(securityContext, appointmentSlotsSearchRequest);
        appointmentSlotsSearchRequest.addAppointmentSlotsSearchRequest(appointmentSlotsSearchCriteria);
//        String previousDay = appointmentSlotsSearchCriteria.getPreviousDay();
        String selectedDay = appointmentSlotsSearchCriteria.getSelectedDay();
//        String nextDay = appointmentSlotsSearchCriteria.getNextDay();
        Integer doctorRid = appointmentSlotsSearchCriteria.getDoctorRid();
//        Integer dateInterval = appointmentSlotsSearchCriteria.getDateInterval();
//        Date previousDate = DateUtil.convertStringToDate(appointmentSlotsSearchCriteria.getPreviousDate());
//        Date nextDate = DateUtil.convertStringToDate(appointmentSlotsSearchCriteria.getNextDate());
        Date selectedDate = DateUtil.convertStringToDate(appointmentSlotsSearchCriteria.getSelectedDate());

        return clinicalService.getAppointmentSlots(appointmentSlotsSearchRequest, doctorRid, selectedDay, selectedDate, true);//dateInterval,
    }

    @POST
    @Path("/search/count")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Appointment> searchAppointmentsCount(@Context final SecurityContext securityContext, AppointmentSearchCriteria appointmentSearchCriteria) {
        AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
        appointmentSearchRequest.addAppointmentCriteria(appointmentSearchCriteria);
        Date sdate = DateUtil.convertStringToDate(appointmentSearchCriteria.getApptFromDate());
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("apptFromDate", sdate));
        appointmentSearchRequest.setSearchCriterionList(searchCriterionList);
        return clinicalService.getAppointment(appointmentSearchRequest, true);
    }

    @POST
    @Path("/search/appointment")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Appointment> searchAppointmentsByMobileNo(@Context final SecurityContext securityContext, AppointmentSearchCriteria appointmentSearchCriteria) {
        AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
        appointmentSearchRequest.addAppointmentCriteria(appointmentSearchCriteria);
        Date sdate = DateUtil.convertStringToDate(appointmentSearchCriteria.getApptFromDate());
//        Date sdate = DateUtil.convertStringToDate(appointmentSearchCriteria.getApptPatientPhone());
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("apptFromDate", sdate));
        if (appointmentSearchCriteria.getApptPatientRID() != 0) {
            searchCriterionList.add(Restrictions.eq("apptPatientRID", appointmentSearchCriteria.getApptPatientRID()));
        } else {
            searchCriterionList.add(Restrictions.eq("apptPatientPhone", appointmentSearchCriteria.getApptPatientPhone()));
        }
        appointmentSearchRequest.setSearchCriterionList(searchCriterionList);
        return clinicalService.getAppointment(appointmentSearchRequest, true);
    }
}
