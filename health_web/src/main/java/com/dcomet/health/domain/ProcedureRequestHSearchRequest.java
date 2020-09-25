package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ABDUL
 */
public class ProcedureRequestHSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public ProcedureRequestHSearchRequest() {

    }

    public List<Criterion> addProcedureRequestHSearchCriteria(ProcedureRequestHSearchCriteria procedureRequestHSearchCriteria) {
        if (procedureRequestHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", procedureRequestHSearchCriteria.getId()));
        }
//        if (patientSearchCriteria.getPatGenderIndex() != null) {
//            getSearchCriterionList().add(Restrictions.eq("patGenderIndex", patientSearchCriteria.getPatGenderIndex()));
//        }
//        if (StringUtils.isNotBlank(patientSearchCriteria.getPatMrnNo())) {
//            getSearchCriterionList().add(Restrictions.like("patMrnNo", patientSearchCriteria.getPatMrnNo(), MatchMode.ANYWHERE));
//        }
//        if (StringUtils.isNotBlank(patientSearchCriteria.getPatRegDate())) {
//            getSearchCriterionList().add(Restrictions.eq("patRegDate", DateUtil.convertStringToDate(patientSearchCriteria.getPatRegDate())));
//        }
//        if (StringUtils.isNotBlank(patientSearchCriteria.getQ())) {
//            getSearchCriterionList().add(Restrictions.or(Restrictions.like("patMrnNo", patientSearchCriteria.getQ(), MatchMode.ANYWHERE),
//                    Restrictions.like("patPhoneNo", patientSearchCriteria.getQ(), MatchMode.ANYWHERE)));
//        }
//        if (CollectionUtils.isNotEmpty(patientSearchCriteria.getSortOrder())) {
//            for (String columnName : patientSearchCriteria.getSortOrder()) {
//                getSortOrder().add(new CriteriaOrder(columnName, "desc".equalsIgnoreCase(patientSearchCriteria.getSortDesc())));
//            }
//        }
//        if (patientSearchCriteria.getPatFromDate() != null && patientSearchCriteria.getPatToDate() != null) {
//            getSearchCriterionList().add(Restrictions.between("createdDateTime", patientSearchCriteria.getPatFromDate(), patientSearchCriteria.getPatToDate())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
