package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ServiceRequestSearchRequest extends SearchRequest implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public String toString() {
        return super.toString();
    }

    public ServiceRequestSearchRequest() {

    }

    public List<Criterion> addServiceRequestCriteria(ServiceRequestSearchCriteria serviceRequestSearchCriteria) {
        if (serviceRequestSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", serviceRequestSearchCriteria.getId()));
        }
        if (serviceRequestSearchCriteria.getSerType() != null) {
            getSearchCriterionList().add(Restrictions.eq("serType", serviceRequestSearchCriteria.getSerType()));
        }
        if (serviceRequestSearchCriteria.getSerReqOpVisitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqOpVisitRID", serviceRequestSearchCriteria.getSerReqOpVisitRID()));
        }
        return getSearchCriterionList();
    }
}
