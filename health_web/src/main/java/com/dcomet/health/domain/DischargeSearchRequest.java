package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class DischargeSearchRequest extends SearchRequest implements Serializable  {

    private static final long serialVersionUID = 1L;

    public DischargeSearchRequest() {

    }

    public List<Criterion> addDischargeCriteria(DischargeSearchCriteria dischargeSearchCriteria) {
        if (dischargeSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", dischargeSearchCriteria.getId()));
        }
        if (dischargeSearchCriteria.getDisVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("disVisitRid", dischargeSearchCriteria.getDisVisitRid()));
        }
        if (dischargeSearchCriteria.getDisPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("disPatRid", dischargeSearchCriteria.getDisPatRid()));
        }
        if (dischargeSearchCriteria.getDisDateTime()!= null) {
            getSearchCriterionList().add(Restrictions.eq("disDateTime", dischargeSearchCriteria.getDisDateTime()));
        }

        return getSearchCriterionList();
    }
}
