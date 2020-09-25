package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedOccupancySearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedOccupancySearchRequest() {
    }

     public List<Criterion> addBedOccupancyCriteria(BedOccupancySearchCriteria bedOccupancySearchCriteria) {
        if (bedOccupancySearchCriteria.getBocRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bocRid", bedOccupancySearchCriteria.getBocRid()));
        }
        if (bedOccupancySearchCriteria.getBocBedRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bocBedRid", bedOccupancySearchCriteria.getBocBedRid()));
        }
        if (bedOccupancySearchCriteria.getBocPrimaryDoctor()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bocPrimaryDoctor", bedOccupancySearchCriteria.getBocPrimaryDoctor()));
        }
        return getSearchCriterionList();
    }
}
