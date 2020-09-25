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
 * @author Dev4
 */
public class HistorySearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public HistorySearchRequest() {

    }

    public List<Criterion> addHistoryCriteria(HistorySearchCriteria historySearchCriteria) {
        if (historySearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", historySearchCriteria.getId()));
        }
        if (historySearchCriteria.getHisVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("hisVisitRid", historySearchCriteria.getHisVisitRid()));
        }
        if (historySearchCriteria.getHisPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("hisPatRid", historySearchCriteria.getHisPatRid()));
        }
        if (StringUtils.isNotBlank(historySearchCriteria.getCreatedDateTime())) {
            getSearchCriterionList().add(Restrictions.lt("createdDateTime", DateUtil.convertStringToCalendar(historySearchCriteria.getCreatedDateTime())));
        }
        return getSearchCriterionList();
    }
}
