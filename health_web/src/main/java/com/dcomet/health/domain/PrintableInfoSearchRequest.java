
package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MI
 */
public class PrintableInfoSearchRequest extends SearchRequest implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Criterion> addPrintableInfoCriteria(PrintableInfoSearchCriteria printableInfoSearchCriteria) {
        if (printableInfoSearchCriteria.getPeRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("peRid", printableInfoSearchCriteria.getPeRid()));
        }
        if (printableInfoSearchCriteria.getPeEntityRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("peEntityRid", printableInfoSearchCriteria.getPeEntityRid()));
        }
        return getSearchCriterionList();
    }
}
