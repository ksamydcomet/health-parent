package com.dcomet.health.domain.dbd;

import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

public class DashBoardSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public DashBoardSearchRequest() {
    }

    public void addSearchCriteria(DashBoardSearchCriteria dashBoardSearchCriteria) {
        if (dashBoardSearchCriteria.getEntityId() != null) {
            getSearchCriterionList().add(Restrictions.eq("entityId", dashBoardSearchCriteria.getEntityId()));
        }
        if (dashBoardSearchCriteria.getUnitId() != null) {
            getSearchCriterionList().add(Restrictions.eq("unitId", dashBoardSearchCriteria.getUnitId()));
        }
        if (StringUtils.isNotBlank(dashBoardSearchCriteria.getToday())) {
            getSearchCriterionList().add(Restrictions.eq("date", DateUtil.convertStringToDate(dashBoardSearchCriteria.getToday()))); // convert String to Date
        }
        if (StringUtils.isNotBlank(dashBoardSearchCriteria.getFrom())) {
            getSearchCriterionList().add(Restrictions.ge("date", DateUtil.convertStringToDate(dashBoardSearchCriteria.getFrom()))); // convert String to Date
        }
        if (StringUtils.isNotBlank(dashBoardSearchCriteria.getTo())) {
            getSearchCriterionList().add(Restrictions.ge("date", DateUtil.convertStringToDate(dashBoardSearchCriteria.getTo()))); // convert String to Date
        }
    }
}
