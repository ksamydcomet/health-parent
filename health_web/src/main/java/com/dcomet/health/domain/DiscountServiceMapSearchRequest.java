/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev6
 */
public class DiscountServiceMapSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public DiscountServiceMapSearchRequest() {

    }

    public List<Criterion> addDiscountServiceMapSearchCriteria(DiscountServiceMapSearchCriteria discountServiceMapSearchCriteria) {
        if (discountServiceMapSearchCriteria.getDsmRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("dsmDisRid", discountServiceMapSearchCriteria.getDsmDisRid()));
        }
        return getSearchCriterionList();
    }

}
