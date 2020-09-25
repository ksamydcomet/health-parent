package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BirthDetailsSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BirthDetailsSearchRequest() {
    }

    public List<Criterion> addBirthDetailsCriteria(BirthDetailsSearchCriteria birthDetailsSearchCriteria) {
        if (birthDetailsSearchCriteria.getBdRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bdRid", birthDetailsSearchCriteria.getBdRid()));
        }
        return getSearchCriterionList();
    }
}
