package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class ItemOrderSearchRequest extends SearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public ItemOrderSearchRequest() {

    }

    public List<Criterion> addItemOrderCriteria(ItemOrderSearchCriteria itemOrderSearchCriteria) {
        if (itemOrderSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", itemOrderSearchCriteria.getId()));
        }
        if (itemOrderSearchCriteria.getIoPatientRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("ioPatientRid", itemOrderSearchCriteria.getIoPatientRid()));
        }
        if (itemOrderSearchCriteria.getIoVisitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("ioVisitRid", itemOrderSearchCriteria.getIoVisitRid()));
        }
        if (StringUtils.isNotBlank(itemOrderSearchCriteria.getIoOrderDate())) {
            getSearchCriterionList().add(Restrictions.eq("ioOrderDate", itemOrderSearchCriteria.getIoOrderDate()));
        }
        if (itemOrderSearchCriteria.getIoState() != null) {
            getSearchCriterionList().add(Restrictions.eq("ioState", itemOrderSearchCriteria.getIoState()));
        }
        if (itemOrderSearchCriteria.getIoMajorProcedureStatus() != null) {
            getSearchCriterionList().add(Restrictions.eq("ioMajorProcedureStatus", itemOrderSearchCriteria.getIoMajorProcedureStatus()));
        }
        return getSearchCriterionList();
    }
}
