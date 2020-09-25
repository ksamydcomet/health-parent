package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedTransferSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedTransferSearchRequest() {
    }

    public List<Criterion> addBedTransferCriteria(BedTransferSearchCriteria bedTransferSearchCriteria) {
        if (bedTransferSearchCriteria.getBtRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("btRid", bedTransferSearchCriteria.getBtRid()));
        }
        return getSearchCriterionList();
    }
}
