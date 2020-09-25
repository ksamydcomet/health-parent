package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AppointmentReservationSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentReservationSearchRequest() {

    }

    public List<Criterion> addAppointmentReservationCriteria(AppointmentReservationsSearchCriteria appointmentReservationSearchCriteria) {
        if (appointmentReservationSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", appointmentReservationSearchCriteria.getId()));
        }
//        if (StringUtils.isNotBlank(appointmentReservationSearchCriteria.getBsName())) {
//            searchCriterionList.add(Restrictions.eq("bsName", appointmentReservationSearchCriteria.getBsName())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(appointmentReservationSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode", appointmentReservationSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
