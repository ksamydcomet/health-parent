package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class VisitTemplateSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public VisitTemplateSearchRequest() {

    }

    public List<Criterion> addVisitTemplateSearchCriteria(VisitTemplateSearchCriteria visitTemplateSearchCriteria) {
        if (visitTemplateSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", visitTemplateSearchCriteria.getId()));
        }
        if (visitTemplateSearchCriteria.getVistVisitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("vistVisitRID", visitTemplateSearchCriteria.getVistVisitRID()));
        }
        if (visitTemplateSearchCriteria.getVistPatRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("vistPatRID", visitTemplateSearchCriteria.getVistPatRID()));
        }
        return getSearchCriterionList();
    }
}
