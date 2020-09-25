package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedTransferRequestSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedTransferRequestSearchRequest() {
    }

    public List<Criterion> addBedTransferRequestCriteria(BedTransferRequestSearchCriteria bedTransferRequestSearchCriteria) {
        if (bedTransferRequestSearchCriteria.getBtrRid()!= null) {
            getSearchCriterionList().add(Restrictions.eq("btrRid", bedTransferRequestSearchCriteria.getBtrRid()));
        }
        return getSearchCriterionList();
    }
}
