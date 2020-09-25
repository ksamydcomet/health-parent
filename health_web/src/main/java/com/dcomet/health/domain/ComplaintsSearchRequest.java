package com.dcomet.health.domain;

import com.dcomet.fw.domain.CriteriaOrder;
import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class ComplaintsSearchRequest extends SearchRequest implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public ComplaintsSearchRequest() {

    }

    public List<Criterion> addComplaintsSearchCriteria(ComplaintsSearchCriteria complaintsSearchCriteria) {
        if (complaintsSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", complaintsSearchCriteria.getId()));
        }
        if (complaintsSearchCriteria.getCplVisitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("cplVisitRID", complaintsSearchCriteria.getCplVisitRID()));
        }
        if (complaintsSearchCriteria.getCplPatRID()!= null) {
            getSearchCriterionList().add(Restrictions.eq("cplPatRID", complaintsSearchCriteria.getCplPatRID()));
        }
        if (CollectionUtils.isNotEmpty(complaintsSearchCriteria.getSortOrder())) {
            for (String sortOrder : complaintsSearchCriteria.getSortOrder()) {
                getSortOrder().add(new CriteriaOrder(sortOrder, !("asc".equalsIgnoreCase(complaintsSearchCriteria.getSortDesc()))));
            }
        }
        return getSearchCriterionList();
    }
}
