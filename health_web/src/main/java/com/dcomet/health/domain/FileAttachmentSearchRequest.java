package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */

public class FileAttachmentSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public FileAttachmentSearchRequest() {
    }

    public List<Criterion> addFileAttachmentCriteria(FileAttachmentSearchCriteria fileAttachmentSearchCriteria) {
        if (fileAttachmentSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", fileAttachmentSearchCriteria.getId()));
        }
        if (fileAttachmentSearchCriteria.getTaRefRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("taRefRid", fileAttachmentSearchCriteria.getTaRefRid()));
        }
        if (fileAttachmentSearchCriteria.getTaRefRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("taType", fileAttachmentSearchCriteria.getTaType()));
        }
        if (fileAttachmentSearchCriteria.getTaEntityRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("taEntityRid", fileAttachmentSearchCriteria.getTaEntityRid()));
        }
        return getSearchCriterionList();
    }

}
