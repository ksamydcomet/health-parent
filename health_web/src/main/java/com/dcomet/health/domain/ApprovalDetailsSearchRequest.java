package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev1
 */
public class ApprovalDetailsSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public ApprovalDetailsSearchRequest() {

    }

    public List<Criterion> addApprovalDetailsCriteria(ApprovalDetailsSearchCriteria approvalDetailsSearchCriteria) {
        if (approvalDetailsSearchCriteria.getAppId() != null) {
            getSearchCriterionList().add(Restrictions.eq("appId", approvalDetailsSearchCriteria.getAppId()));
        }
        if (approvalDetailsSearchCriteria.getAppBillRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("appBillRid", approvalDetailsSearchCriteria.getAppBillRid()));
        }
        if (approvalDetailsSearchCriteria.getAppStatus() != null) {
            getSearchCriterionList().add(Restrictions.eq("appStatus", approvalDetailsSearchCriteria.getAppStatus()));
        }
        if (approvalDetailsSearchCriteria.getAppEntityRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("appEntityRid", approvalDetailsSearchCriteria.getAppEntityRid()));
        }
        return getSearchCriterionList();
    }

    public List<Criterion> addApprovalDetailsCriteria(Integer id) {

        return getSearchCriterionList();
    }
}
