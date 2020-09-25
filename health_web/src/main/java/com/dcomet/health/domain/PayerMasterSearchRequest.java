package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class PayerMasterSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPayerMasterSearchCriteria(PayerMasterSearchCriteria payerMasterSearchCriteria) {
        if (payerMasterSearchCriteria.getPdId() != null) {
            getSearchCriterionList().add(Restrictions.eq("pdId", payerMasterSearchCriteria.getPdId()));
        }
        if (payerMasterSearchCriteria.getPdPayerType() != null) {
            getSearchCriterionList().add(Restrictions.eq("pdPayerType", payerMasterSearchCriteria.getPdPayerType()));
        }
        if (payerMasterSearchCriteria.getPdIsActive() != null) {
            getSearchCriterionList().add(Restrictions.eq("pdIsActive", payerMasterSearchCriteria.getPdIsActive()));
        }
        if (StringUtils.isNotBlank(payerMasterSearchCriteria.getPdPayerName())) {
            getSearchCriterionList().add(Restrictions.like("pdPayerName", payerMasterSearchCriteria.getPdPayerName(), MatchMode.ANYWHERE));
        }

        return getSearchCriterionList();
    }

}
