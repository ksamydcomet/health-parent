package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KS
 */
public class SalesHSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public SalesHSearchRequest() {

    }

    public List<Criterion> addSalesHCriteria(SalesHSearchCriteria salesHSearchCriteria) {
        if (salesHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", salesHSearchCriteria.getId()));
        }
        if (salesHSearchCriteria.getSalBhRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("salBhRID", salesHSearchCriteria.getSalBhRID()));
        }
        if (StringUtils.isNotBlank(salesHSearchCriteria.getSalBillNo())) {
            getSearchCriterionList().add(Restrictions.eq("salBillNo", salesHSearchCriteria.getSalBillNo())); // convert String to Date
        }
        return getSearchCriterionList();
    }
}
