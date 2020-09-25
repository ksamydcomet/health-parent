package com.dcomet.health.web.rest;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.dbd.DOpdPatient;
import com.dcomet.health.domain.dbd.DOpdReferral;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import com.dcomet.health.service.business.DashBoardService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
 * @author KS
 */
@Component
@Path("dashboard/opd")
public class OPDDashBoardResource extends BaseResource {

    @Autowired
    @Qualifier("dashBoardService")
    public DashBoardService dashBoardService;

    @GET
    @Path("/visit")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDOpdPatient(dashBoardSearchRequest);
    }

    @GET
    @Path("/visit/today")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getTodayVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.eq("date", DateUtil.getToday()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDOpdPatient(dashBoardSearchRequest);
    }

    @GET
    @Path("/visit/week")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getWeekVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.ge("date", DateUtil.getFirstDayOfWeek()));
        searchCriterionList.add(Restrictions.le("date", DateUtil.getLastDayOfWeek()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return getWeekVisits(dashBoardService.getDOpdPatient(dashBoardSearchRequest));
    }

    @GET
    @Path("/visit/week/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getPreviousWeekVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.ge("date", DateUtil.getFirstDayOfLastWeek()));
        searchCriterionList.add(Restrictions.le("date", DateUtil.getLastDayOfLastWeek()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return getPreviousWeekVisits(dashBoardService.getDOpdPatient(dashBoardSearchRequest));
    }

    @GET
    @Path("/visit/month")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getMonthVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.ge("date", DateUtil.getFirstDayOfMonth()));
        searchCriterionList.add(Restrictions.le("date", DateUtil.getLastDayOfMonth()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDOpdPatient(dashBoardSearchRequest);
    }

    @GET
    @Path("/visit/year")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdPatient> getYearVisits(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.ge("date", DateUtil.getFirstDayOfYear()));
        searchCriterionList.add(Restrictions.le("date", DateUtil.getLastDayOfYear()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDOpdPatient(dashBoardSearchRequest);
    }

    @GET
    @Path("/referral/today")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DOpdReferral> getReferral(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("date", DateUtil.getToday()));
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDOpdReferral(dashBoardSearchRequest);
    }

    /**
     *
     * @param dOpdPatientList
     * @return
     */
    private List<DOpdPatient> getWeekVisits(List<DOpdPatient> dOpdPatientList) {
        List<DOpdPatient> returnObject = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Calendar dayOfWeeek = Calendar.getInstance();
            dayOfWeeek.set(Calendar.DAY_OF_WEEK, i);
            dayOfWeeek.set(Calendar.HOUR, 12);
            dayOfWeeek.clear(Calendar.MINUTE);
            dayOfWeeek.clear(Calendar.SECOND);
            returnObject.add(getVisit(i, dOpdPatientList, DateUtil.convertDateToString(dayOfWeeek.getTime())));
        }
        Collections.sort(returnObject);
        return returnObject;
    }

    /**
     *
     * @param dOpdPatientList
     * @return
     */
    private List<DOpdPatient> getPreviousWeekVisits(List<DOpdPatient> dOpdPatientList) {
        List<DOpdPatient> returnObject = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Calendar dayOfWeeek = Calendar.getInstance();
            dayOfWeeek.add(Calendar.WEEK_OF_YEAR, -1);
            dayOfWeeek.set(Calendar.DAY_OF_WEEK, i);
            dayOfWeeek.set(Calendar.HOUR, 12);
            dayOfWeeek.clear(Calendar.MINUTE);
            dayOfWeeek.clear(Calendar.SECOND);
            returnObject.add(getVisit(i, dOpdPatientList, DateUtil.convertDateToString(dayOfWeeek.getTime())));
        }
        Collections.sort(returnObject);
        return returnObject;
    }

    /**
     *
     * @param id
     * @param dOpdPatientList
     * @param date
     * @return
     */
    private DOpdPatient getVisit(int id, List<DOpdPatient> dOpdPatientList, String date) {
        for (DOpdPatient dOpdPatient : dOpdPatientList) {
            Calendar day = Calendar.getInstance();
            day.setTime(DateUtil.convertStringToDate(dOpdPatient.getDate()));
            day.clear(Calendar.HOUR);
            day.clear(Calendar.MINUTE);
            day.clear(Calendar.SECOND);
            if (date.equals(DateUtil.convertDateToString(day.getTime()))) {
                dOpdPatient.setId(id);
                return dOpdPatient;
            }

        }
        DOpdPatient dOpdPatient = new DOpdPatient();
        dOpdPatient.setId(id);
        dOpdPatient.setDate(date);
        return dOpdPatient;
    }

}
