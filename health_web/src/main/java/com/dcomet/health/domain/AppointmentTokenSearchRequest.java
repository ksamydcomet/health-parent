package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AppointmentTokenSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentTokenSearchRequest() {

    }

    public List<Criterion> addAppointmentTokenCriteria(AppointmentTokenSearchCriteria appointmentTokenSearchCriteria) {
        if (appointmentTokenSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", appointmentTokenSearchCriteria.getId()));
        }
//        if (DateUtils.isNotBlank(appointmentTokenSearchCriteria.getTokenDate())) {
//            searchCriterionList.add(Restrictions.eq("tokenDate", appointmentTokenSearchCriteria.getTokenDate())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(appointmentTokenSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode", appointmentTokenSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
