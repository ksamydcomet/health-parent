package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedTypeOccupancySummarySearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedTypeOccupancySummarySearchRequest() {
    }

       public List<Criterion> addBedTypeOccupancySummaryCriteria(BedTypeOccupancySummarySearchCriteria bedTypeOccupancySummarySearchCriteria) {
        if (bedTypeOccupancySummarySearchCriteria.getBtosRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("btosRid", bedTypeOccupancySummarySearchCriteria.getBtosRid()));
        }
        return getSearchCriterionList();
    }
}
