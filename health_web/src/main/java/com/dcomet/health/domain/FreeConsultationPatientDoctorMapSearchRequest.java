package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abdul
 */
public class FreeConsultationPatientDoctorMapSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public FreeConsultationPatientDoctorMapSearchRequest() {

    }

    public List<Criterion> addFreeConsultationPatientDoctorMapCriteria(FreeConsultationPatientDoctorMapSearchCriteria freeConsultationPatientDoctorMapSearchCriteria) {
        if (freeConsultationPatientDoctorMapSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", freeConsultationPatientDoctorMapSearchCriteria.getId()));
        }
        return getSearchCriterionList();
    }
}
