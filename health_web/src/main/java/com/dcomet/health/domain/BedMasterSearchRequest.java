package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CVS
 */
public class BedMasterSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public BedMasterSearchRequest() {
    }

    public List<Criterion> addBedMasterCriteria(BedMasterSearchCriteria bedMasterSearchCriteria) {
        if (bedMasterSearchCriteria.getBedRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bedRid", bedMasterSearchCriteria.getBedRid()));
        }
        if (bedMasterSearchCriteria.getBedIsActive() != null) {
            getSearchCriterionList().add(Restrictions.eq("bedIsActive", bedMasterSearchCriteria.getBedIsActive()));
        }

        if (bedMasterSearchCriteria.getBedBgmRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bedBgmRid", bedMasterSearchCriteria.getBedBgmRid()));
        }
        if (bedMasterSearchCriteria.getBedState() != null) {
            getSearchCriterionList().add(Restrictions.eq("bedState", bedMasterSearchCriteria.getBedState()));
        }
        if (bedMasterSearchCriteria.getBedStatus() != null) {
            getSearchCriterionList().add(Restrictions.eq("bedStatus", bedMasterSearchCriteria.getBedStatus()));
        }
        if (StringUtils.isNotBlank(bedMasterSearchCriteria.getCreatedDateTime())) {
            getSearchCriterionList().add(Restrictions.lt("createdDateTime", DateUtil.convertStringToCalendar(bedMasterSearchCriteria.getCreatedDateTime())));
        }
        return getSearchCriterionList();
    }
}
