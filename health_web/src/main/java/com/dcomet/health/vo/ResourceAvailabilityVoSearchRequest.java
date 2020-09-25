package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ABDUL
 */
public class ResourceAvailabilityVoSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public ResourceAvailabilityVoSearchRequest() {

    }

    public List<Criterion> addResourceAvailabilityVoSearchRequest(ResourceAvailabilityVoSearchCriteria resourceAvailabilityVoSearchCriteria) {

        if (StringUtils.isNotBlank(resourceAvailabilityVoSearchCriteria.getScheduledDateAndTime())) {
            getSearchCriterionList().add(Restrictions.eq("scheduledDateAndTime", resourceAvailabilityVoSearchCriteria.getScheduledDateAndTime()));
        }
        if (StringUtils.isNotBlank(resourceAvailabilityVoSearchCriteria.getEstimatedDuration())) {
            getSearchCriterionList().add(Restrictions.eq("estimatedDuration", resourceAvailabilityVoSearchCriteria.getScheduledDateAndTime()));
        }

        return getSearchCriterionList();
    }
}
