package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ABDUL
 */
public class BedChargeVoSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedChargeVoSearchRequest() {

    }

    public List<Criterion> addBedChargeVoSearchRequest(BedChargeVoSearchCriteria bedChargeVoSearchCriteria) {

        if (bedChargeVoSearchCriteria.getBcVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bcVisitRid", bedChargeVoSearchCriteria.getBcVisitRid()));
        }
        if (bedChargeVoSearchCriteria.getBcPatientRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bcPatientRid", bedChargeVoSearchCriteria.getBcPatientRid()));
        }
        if (bedChargeVoSearchCriteria.getBcBedEntityRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bcBedEntityRid", bedChargeVoSearchCriteria.getBcBedEntityRid()));
        }
        return getSearchCriterionList();
    }
}
