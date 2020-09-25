package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CVS
 */
public class FreeConsultationVoSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public FreeConsultationVoSearchRequest() {

    }

    public List<Criterion> addFreeConsultationVoSearchRequest(FreeConsultationVoSearchCriteria freeConsultationVoSearchCriteria) {
        if (freeConsultationVoSearchCriteria.getFcServiceRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("fcServiceRid", freeConsultationVoSearchCriteria.getFcServiceRid()));
        }
//        if (StringUtils.isNotBlank(appointmentSlotsSearchCriteria.getPreviousDay())) {
//            getSearchCriterionList().add(Restrictions.eq("previousDay", appointmentSlotsSearchCriteria.getPreviousDay()));
//        }

        return getSearchCriterionList();
    }
}
