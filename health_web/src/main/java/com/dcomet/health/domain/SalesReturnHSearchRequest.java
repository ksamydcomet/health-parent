package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KS
 */
public class SalesReturnHSearchRequest extends SearchRequest implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public SalesReturnHSearchRequest() {

    }

    public List<Criterion> addSalesReturnHCriteria(SalesReturnHSearchCriteria salesReturnHSearchCriteria) {
        if (salesReturnHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", salesReturnHSearchCriteria.getId()));
        }
//        if (StringUtils.isNotBlank(salesReturnHSearchCriteria.getSrhDate())) {
//            searchCriterionList.add(Restrictions.eq("srhDate", salesReturnHSearchCriteria.getSrhDate())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(salesReturnHSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode", salesReturnHSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
