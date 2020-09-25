package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedChargeDefinitionSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedChargeDefinitionSearchRequest() {
    }

    public List<Criterion> addBedChargeDefinitionCriteria(BedChargeDefinitionSearchCriteria bedChargeDefinitionSearchCriteria) {
        if (bedChargeDefinitionSearchCriteria.getBcdRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bcdRid", bedChargeDefinitionSearchCriteria.getBcdRid()));
        }        
        return getSearchCriterionList();
    }
}
