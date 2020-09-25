package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedWardSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedWardSearchRequest() {
    }

    public List<Criterion> addBedWardCriteria(BedWardSearchCriteria bedWardSearchCriteria) {
        if (bedWardSearchCriteria.getBwRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bwRid", bedWardSearchCriteria.getBwRid()));
        }
        return getSearchCriterionList();
    }
}
