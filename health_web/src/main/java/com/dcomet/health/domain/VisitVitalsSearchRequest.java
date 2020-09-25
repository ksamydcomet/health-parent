package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class VisitVitalsSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public VisitVitalsSearchRequest() {

    }

    public List<Criterion> addVisitVitalsSearchCriteria(VisitVitalsSearchCriteria visitVitalsSearchCriteria) {
        if (visitVitalsSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", visitVitalsSearchCriteria.getId()));
        }
        if (visitVitalsSearchCriteria.getVitVisRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("vitVisRID", visitVitalsSearchCriteria.getVitVisRID()));
        }
//        if (StringUtils.isNotBlank(visitVitalsSearchCriteria.getSalBillDate())) {
//            searchCriterionList.add(Restrictions.eq("salBillDate", visitVitalsSearchCriteria.getSalBillDate())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(visitVitalsSearchCriteria.getSalBillNo())) {
//            searchCriterionList.add(Restrictions.eq("salBillNo", visitVitalsSearchCriteria.getSalBillNo())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
