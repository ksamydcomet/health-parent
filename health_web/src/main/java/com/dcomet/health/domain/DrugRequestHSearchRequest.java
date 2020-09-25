package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev1
 */
public class DrugRequestHSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public DrugRequestHSearchRequest() {

    }

    public List<Criterion> addDrugRequestSearchCriteria(DrugRequestHSearchCriteria drugRequestSearchCriteria) {
        if (drugRequestSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", drugRequestSearchCriteria.getId()));
        }
        if (drugRequestSearchCriteria.getDrugReqHOpVBisitRID() != null) {
            getSearchCriterionList().add(Restrictions.eq("drugReqHOpVBisitRID", drugRequestSearchCriteria.getDrugReqHOpVBisitRID()));
        }
        if (drugRequestSearchCriteria.getDrugReqHProcedureRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("drugReqHProcedureRid", drugRequestSearchCriteria.getDrugReqHProcedureRid()));
        }
        if (drugRequestSearchCriteria.getDrugReqHPatRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("drugReqHPatRid", drugRequestSearchCriteria.getDrugReqHPatRid()));
        }
        if (drugRequestSearchCriteria.getDrugReqHOpCheck() != null) {
            getSearchCriterionList().add(Restrictions.eq("drugReqHOpCheck", drugRequestSearchCriteria.getDrugReqHOpCheck()));
        }
//        if (StringUtils.isNotBlank(drugRequestSearchCriteria.getBsName())) {
//            searchCriterionList.add(Restrictions.eq("bsName", drugRequestSearchCriteria.getBsName())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(drugRequestSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode", drugRequestSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
