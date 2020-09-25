package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AppointmentResourceMapSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public AppointmentResourceMapSearchRequest() {

    }

    public List<Criterion> addAppointmentResourceMapCriteria(AppointmentResourceMapSearchCriteria appointmentResourceMapSearchCriteria) {
        if (appointmentResourceMapSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", appointmentResourceMapSearchCriteria.getId()));
        }
        if (appointmentResourceMapSearchCriteria.getArmApptRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("armApptRid", appointmentResourceMapSearchCriteria.getArmApptRid()));
        }
        if (appointmentResourceMapSearchCriteria.getArmResourceRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("armResourceRid", appointmentResourceMapSearchCriteria.getArmResourceRid()));
        }
        return getSearchCriterionList();
    }
}
