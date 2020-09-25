package com.dcomet.health.domain.dbd;

import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

public class ReportSearchRequest extends SearchRequest implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param reportSearchCriteria
     */
    public void addSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
        if (reportSearchCriteria.getEntityId() != null) {
            getSearchCriterionList().add(Restrictions.eq("entRid", reportSearchCriteria.getEntityId()));
        }
        if (StringUtils.isNotBlank(reportSearchCriteria.getFrom())) {
            getSearchCriterionList().add(Restrictions.ge("tranDate", DateUtil.convertStringToDate(reportSearchCriteria.getFrom())));
        }
        if (StringUtils.isNotBlank(reportSearchCriteria.getTo())) {
            getSearchCriterionList().add(Restrictions.le("tranDate", DateUtil.convertStringToDate(reportSearchCriteria.getTo())));
        }
    }
}
