package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PayerIncidentSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPayerIncidentSearchCriteria(PayerIncidentSearchCriteria payerIncidentSearchCriteria) {
        if (payerIncidentSearchCriteria.getPidRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("pidRid", payerIncidentSearchCriteria.getPidRid()));
        }
        return getSearchCriterionList();
    }

}
