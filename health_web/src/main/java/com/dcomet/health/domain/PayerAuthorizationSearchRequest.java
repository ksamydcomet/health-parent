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

public class PayerAuthorizationSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPayerAuthorizationSearchCriteria(PayerAuthorizationSearchCriteria payerAuthorizationSearchCriteria) {
        if (payerAuthorizationSearchCriteria.getPadRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("padRid", payerAuthorizationSearchCriteria.getPadRid()));
        }
        return getSearchCriterionList();
    }
}
