package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AppointmentSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentSearchRequest() {

    }

    public List<Criterion> addAppointmentCriteria(AppointmentSearchCriteria appoitmentSearchCriteria) {
        if (appoitmentSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", appoitmentSearchCriteria.getId()));
        }
        if (appoitmentSearchCriteria.getApptStatus() != null) {
            getSearchCriterionList().add(Restrictions.eq("apptStatus", appoitmentSearchCriteria.getApptStatus()));
        }
        if (StringUtils.isNotBlank(appoitmentSearchCriteria.getApptPatientName())) {
            getSearchCriterionList().add(Restrictions.eq("apptPatientName", appoitmentSearchCriteria.getApptPatientName()));
        }
        if (StringUtils.isNotBlank(appoitmentSearchCriteria.getApptPatientPhone())) {
            getSearchCriterionList().add(Restrictions.eq("apptPatientPhone", appoitmentSearchCriteria.getApptPatientPhone()));
        }
        if (appoitmentSearchCriteria.getApptFromDate() != null) {
            getSearchCriterionList().add(Restrictions.eq("apptFromDate", appoitmentSearchCriteria.getApptFromDate())); // convert String to Date
        }
        return getSearchCriterionList();
    }
}
