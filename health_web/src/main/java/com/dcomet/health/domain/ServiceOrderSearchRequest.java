package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ServiceOrderSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public ServiceOrderSearchRequest() {

    }

    public List<Criterion> addServiceOrderCriteria(ServiceOrderSearchCriteria serviceOrderSearchCriteria) {
        if (serviceOrderSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", serviceOrderSearchCriteria.getId()));
        }
        if (serviceOrderSearchCriteria.getSoPatientRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("soPatientRID", serviceOrderSearchCriteria.getSoPatientRID()));
        }
        if (serviceOrderSearchCriteria.getSoVisitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("soVisitRID", serviceOrderSearchCriteria.getSoVisitRID()));
        }
        if (StringUtils.isNotBlank(serviceOrderSearchCriteria.getSoStartDate())) {
            getSearchCriterionList().add(Restrictions.eq("soStartDate", serviceOrderSearchCriteria.getSoStartDate()));
        }
        if (StringUtils.isNotBlank(serviceOrderSearchCriteria.getSoOrderDate())) {
            getSearchCriterionList().add(Restrictions.eq("createdDateTime", DateUtil.convertStringToCalendar(serviceOrderSearchCriteria.getSoOrderDate())));
        }
        if (serviceOrderSearchCriteria.getSoState() != null) {
            getSearchCriterionList().add(Restrictions.eq("soState", serviceOrderSearchCriteria.getSoState()));
        }
        if (serviceOrderSearchCriteria.getSoAgainstUnitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("soAgainstUnitRID", serviceOrderSearchCriteria.getSoAgainstUnitRID()));
        }
        if (serviceOrderSearchCriteria.getSoSerMode() != null) {
            getSearchCriterionList().add(Restrictions.eq("soSerMode", serviceOrderSearchCriteria.getSoSerMode()));
        }
        if (serviceOrderSearchCriteria.getSoEntityRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("soEntityRID", serviceOrderSearchCriteria.getSoEntityRID()));
        }
        if (serviceOrderSearchCriteria.getSoMajorProcedureStatus() != null) {
            getSearchCriterionList().add(Restrictions.eq("soMajorProcedureStatus", serviceOrderSearchCriteria.getSoMajorProcedureStatus()));
        }
        if (serviceOrderSearchCriteria.getSoFromDate() != null && serviceOrderSearchCriteria.getSoToDate() != null) {
            getSearchCriterionList().add(Restrictions.between("modifiedDateTime", serviceOrderSearchCriteria.getSoFromDate(), serviceOrderSearchCriteria.getSoToDate()));
        }
        return getSearchCriterionList();
    }
}
