/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class MaterialIssueHSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public MaterialIssueHSearchRequest() {

    }

    public List<Criterion> addMaterialIssueHSearchCriteria(MaterialIssueHSearchCriteria materialIssueHSearchCriteria) {
        if (materialIssueHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", materialIssueHSearchCriteria.getId()));
        }
        if (materialIssueHSearchCriteria.getMatVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("matVisitRid", materialIssueHSearchCriteria.getMatVisitRid()));
        }
        if (materialIssueHSearchCriteria.getMatPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("matPatRid", materialIssueHSearchCriteria.getMatPatRid()));
        }
        if (materialIssueHSearchCriteria.getMatState()!= null) {
            getSearchCriterionList().add(Restrictions.eq("matState", materialIssueHSearchCriteria.getMatState()));
        }
        if (materialIssueHSearchCriteria.getMatStatus()!= null) {
            getSearchCriterionList().add(Restrictions.eq("matStatus", materialIssueHSearchCriteria.getMatStatus()));
        }
        return getSearchCriterionList();
    }
}
