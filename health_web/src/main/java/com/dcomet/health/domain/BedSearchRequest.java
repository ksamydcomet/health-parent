package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedSearchRequest() {
    }

    public List<Criterion> addBedCriteria(BedSearchCriteria bedSearchCriteria) {
        if (bedSearchCriteria.getBedRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bedRid", bedSearchCriteria.getBedRid()));
        }
        return getSearchCriterionList();
    }
}
