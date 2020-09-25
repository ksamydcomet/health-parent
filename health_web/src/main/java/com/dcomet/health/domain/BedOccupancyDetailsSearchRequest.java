package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedOccupancyDetailsSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedOccupancyDetailsSearchRequest() {
    }

    public List<Criterion> addBedOccupancyDetailsCriteria(BedOccupancyDetailsSearchCriteria bedOccupancyDetailsSearchCriteria) {
        if (bedOccupancyDetailsSearchCriteria.getBodRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bodRid", bedOccupancyDetailsSearchCriteria.getBodRid()));
        }      
        if (bedOccupancyDetailsSearchCriteria.getBodBedRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bodBedRid", bedOccupancyDetailsSearchCriteria.getBodBedRid()));
        }      
        if (bedOccupancyDetailsSearchCriteria.getBodWardRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bodWardRid", bedOccupancyDetailsSearchCriteria.getBodWardRid()));
        }      
        return getSearchCriterionList();
    }
}
