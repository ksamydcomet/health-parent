package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PrintTemplateSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPrintTemplateSearchCriteria(PrintTemplateSearchCriteria printTemplateSearchCriteria) {
        if (printTemplateSearchCriteria.getPtId() != null) {
            getSearchCriterionList().add(Restrictions.eq("ptId", printTemplateSearchCriteria.getPtId()));
        }
        return getSearchCriterionList();
    }
}
