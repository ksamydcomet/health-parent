package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abdullah 
 */
public class DiscountMasterSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public DiscountMasterSearchRequest() {

    }

    public List<Criterion> addDiscountMasterSearchCriteria(DiscountMasterSearchCriteria discountMasterSearchCriteria) {
        if (discountMasterSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", discountMasterSearchCriteria.getId()));
        }
        if (discountMasterSearchCriteria.getDisIsActive() != null) {
            getSearchCriterionList().add(Restrictions.eq("disIsActive", discountMasterSearchCriteria.getDisIsActive()));
        }
        if (discountMasterSearchCriteria.getDisCategory() != null) {
            getSearchCriterionList().add(Restrictions.eq("disCategory", discountMasterSearchCriteria.getDisCategory()));
        }
        return getSearchCriterionList();
    }
}
