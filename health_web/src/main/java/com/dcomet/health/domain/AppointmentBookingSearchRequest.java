package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AppointmentBookingSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentBookingSearchRequest() {

    }

    public List<Criterion> addAppointmentBookingCriteria(AppointmentBookingSearchCriteria appointmentBookingSearchCriteria) {
        if (appointmentBookingSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", appointmentBookingSearchCriteria.getId()));
        }
//        if (StringUtils.isNotBlank(appointmentBookingSearchCriteria.getBsName())) {
//            searchCriterionList.add(Restrictions.eq("bsName", appointmentBookingSearchCriteria.getBsName())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(appointmentBookingSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode", appointmentBookingSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
