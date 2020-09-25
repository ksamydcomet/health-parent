package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class KinSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public KinSearchRequest() {
    }

    public List<Criterion> addKinCriteria(KinSearchCriteria kinSearchCriteria) {
        if (kinSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", kinSearchCriteria.getId()));
        }
        if (kinSearchCriteria.getKinPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("kinPatRid", kinSearchCriteria.getKinPatRid()));
        }
        if (kinSearchCriteria.getKinOccupation() != null) {
            getSearchCriterionList().add(Restrictions.eq("kinOccupation", kinSearchCriteria.getKinOccupation()));
        }
        return getSearchCriterionList();
    }
}
