package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedCancellationHistorySearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedCancellationHistorySearchRequest() {
    }

    public List<Criterion> addBedCancellationHistoryCriteria(BedCancellationHistorySearchCriteria bedCancellationHistorySearchCriteria) {
        if (bedCancellationHistorySearchCriteria.getBchRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bchRid", bedCancellationHistorySearchCriteria.getBchRid()));
        }
        return getSearchCriterionList();
    }
}
