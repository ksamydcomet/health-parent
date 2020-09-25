package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abdul
 */
public class AppointmentSlotsSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentSlotsSearchRequest() {

    }

    public List<Criterion> addAppointmentSlotsSearchRequest(AppointmentSlotsSearchCriteria appointmentSlotsSearchCriteria) {

        if (appointmentSlotsSearchCriteria.getDoctorRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("doctorRid", appointmentSlotsSearchCriteria.getDoctorRid()));
        }
        if (appointmentSlotsSearchCriteria.getDateInterval() != null) {
            getSearchCriterionList().add(Restrictions.eq("dateInterval", appointmentSlotsSearchCriteria.getDateInterval()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getPreviousDay())) {
            getSearchCriterionList().add(Restrictions.eq("previousDay", appointmentSlotsSearchCriteria.getPreviousDay()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getNextDay())) {
            getSearchCriterionList().add(Restrictions.eq("nextDay", appointmentSlotsSearchCriteria.getNextDay()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getSelectedDay())) {
            getSearchCriterionList().add(Restrictions.eq("selectedDay", appointmentSlotsSearchCriteria.getSelectedDay()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getPreviousDate())) {
            getSearchCriterionList().add(Restrictions.eq("previousDate", appointmentSlotsSearchCriteria.getPreviousDate()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getNextDate())) {
            getSearchCriterionList().add(Restrictions.eq("nextDate", appointmentSlotsSearchCriteria.getNextDate()));
        }
        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getSelectedDate())) {
            getSearchCriterionList().add(Restrictions.eq("selectedDate", appointmentSlotsSearchCriteria.getSelectedDate()));
        }

        return getSearchCriterionList();
    }
}
