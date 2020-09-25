package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PayerServiceMapSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPayerServiceMapSearchCriteria(PayerServiceMapSearchCriteria payerServiceMapSearchCriteria) {
        if (payerServiceMapSearchCriteria.getPsmId() != null) {
            getSearchCriterionList().add(Restrictions.eq("psmId", payerServiceMapSearchCriteria.getPsmId()));
        }
        if (payerServiceMapSearchCriteria.getPsmIsActive() != null) {
            getSearchCriterionList().add(Restrictions.eq("psmIsActive", payerServiceMapSearchCriteria.getPsmIsActive()));
        }
        return getSearchCriterionList();
    }
}
