package com.dcomet.health.domain;

import com.dcomet.fw.domain.CriteriaOrder;
import com.dcomet.fw.domain.SearchRequest;
import com.dcomet.fw.util.DateUtil;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev3
 */
public class PatientSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public PatientSearchRequest() {

    }

    public List<Criterion> addPatientSearchCriteria(PatientSearchCriteria patientSearchCriteria) {
        if (patientSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", patientSearchCriteria.getId()));
        }
        if (patientSearchCriteria.getPatGenderIndex() != null) {
            getSearchCriterionList().add(Restrictions.eq("patGenderIndex", patientSearchCriteria.getPatGenderIndex()));
        }
        if (StringUtils.isNotBlank(patientSearchCriteria.getPatMrnNo())) {
            getSearchCriterionList().add(Restrictions.like("patMrnNo", patientSearchCriteria.getPatMrnNo(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(patientSearchCriteria.getPatRegDate())) {
            getSearchCriterionList().add(Restrictions.eq("patRegDate", DateUtil.convertStringToDate(patientSearchCriteria.getPatRegDate())));
        }
        if (StringUtils.isNotBlank(patientSearchCriteria.getQ())) {
            getSearchCriterionList().add(Restrictions.or(Restrictions.like("patMrnNo", patientSearchCriteria.getQ(), MatchMode.ANYWHERE),
                    Restrictions.like("patPhoneNo", patientSearchCriteria.getQ(), MatchMode.ANYWHERE)));
        }
        if (CollectionUtils.isNotEmpty(patientSearchCriteria.getSortOrder())) {
            for (String columnName : patientSearchCriteria.getSortOrder()) {
                getSortOrder().add(new CriteriaOrder(columnName, "desc".equalsIgnoreCase(patientSearchCriteria.getSortDesc())));
            }
        }
        if (patientSearchCriteria.getPatFromDate()!= null && patientSearchCriteria.getPatToDate()!= null) {
            getSearchCriterionList().add(Restrictions.between("createdDateTime", patientSearchCriteria.getPatFromDate(), patientSearchCriteria.getPatToDate())); // convert String to Date
        }
        return getSearchCriterionList();
    }
}
