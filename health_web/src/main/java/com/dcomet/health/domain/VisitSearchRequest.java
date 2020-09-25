package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class VisitSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public VisitSearchRequest() {

    }

    public List<Criterion> addVisitCriteria(VisitSearchCriteria visitSearchCriteria) {
        if (visitSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", visitSearchCriteria.getId()));
        }
        if (visitSearchCriteria.getVisPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("visPatRid", visitSearchCriteria.getVisPatRid()));
        }
        if (visitSearchCriteria.getVisReasonIndex() != null) {
            getSearchCriterionList().add(Restrictions.eq("visReasonIndex", visitSearchCriteria.getVisReasonIndex()));
        }
        if (visitSearchCriteria.getVisTypeIndex() != null) {
            getSearchCriterionList().add(Restrictions.eq("visTypeIndex", visitSearchCriteria.getVisTypeIndex()));
        }
        if (visitSearchCriteria.getVisSpecialityIndex() != null) {
            getSearchCriterionList().add(Restrictions.eq("visSpecialityIndex", visitSearchCriteria.getVisSpecialityIndex()));
        }
        if (visitSearchCriteria.getVisConsDocRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("visConsDocRid", visitSearchCriteria.getVisConsDocRid()));
        }
        if (visitSearchCriteria.getVisIsCompleted() != null) {
            getSearchCriterionList().add(Restrictions.eq("visIsCompleted", visitSearchCriteria.getVisIsCompleted()));
        }
        if (visitSearchCriteria.getVisDate() != null) {
            getSearchCriterionList().add(Restrictions.eq("visDate", DateUtil.convertStringToDate(visitSearchCriteria.getVisDate())));
        }
        if (StringUtils.isNotBlank(visitSearchCriteria.getCreatedDateTime())) {
            getSearchCriterionList().add(Restrictions.lt("createdDateTime", DateUtil.convertStringToCalendar(visitSearchCriteria.getCreatedDateTime())));
        }
        if (visitSearchCriteria.getVisFromDate() != null && visitSearchCriteria.getVisToDate() != null) {
            getSearchCriterionList().add(Restrictions.between("createdDateTime", visitSearchCriteria.getVisFromDate(), visitSearchCriteria.getVisToDate())); // convert String to Date
        }
        return getSearchCriterionList();
    }
}