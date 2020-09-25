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
public class VisitPlanSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public VisitPlanSearchRequest() {

    }

    public List<Criterion> addVisitPlanSearchCriteria(VisitPlanSearchCriteria visitPlanSearchCriteria) {
        if (visitPlanSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", visitPlanSearchCriteria.getId()));
        }
//        if (StringUtils.isNotBlank(visitPlanSearchCriteria.getSalBillDate())) {
//            searchCriterionList.add(Restrictions.eq("salBillDate", visitPlanSearchCriteria.getSalBillDate())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(visitPlanSearchCriteria.getSalBillNo())) {
//            searchCriterionList.add(Restrictions.eq("salBillNo", visitPlanSearchCriteria.getSalBillNo())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
