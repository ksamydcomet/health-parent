package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev1
 */
public class ServiceRequestHSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public ServiceRequestHSearchRequest() {

    }

    public java.util.List<Criterion> addServiceRequestCriteria(ServiceRequestHSearchCriteria serviceRequestHSearchCriteria) {
        if (serviceRequestHSearchCriteria.getSerReqhId() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqhId", serviceRequestHSearchCriteria.getSerReqhId()));
        }
        if (serviceRequestHSearchCriteria.getSerReqOpVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqOpVisitRid", serviceRequestHSearchCriteria.getSerReqOpVisitRid()));
        }
        if (serviceRequestHSearchCriteria.getSerReqhProcedureRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqhProcedureRid", serviceRequestHSearchCriteria.getSerReqhProcedureRid()));
        }
        if (serviceRequestHSearchCriteria.getSerReqhOpCheck() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqhOpCheck", serviceRequestHSearchCriteria.getSerReqhOpCheck()));
        }
        if (serviceRequestHSearchCriteria.getSerReqhPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("serReqhPatRid", serviceRequestHSearchCriteria.getSerReqhPatRid()));
        }
        return getSearchCriterionList();
    }
}
