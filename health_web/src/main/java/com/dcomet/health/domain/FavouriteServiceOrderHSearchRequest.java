package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class FavouriteServiceOrderHSearchRequest extends SearchRequest implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public FavouriteServiceOrderHSearchRequest() {

    }

    public List<Criterion> addFavouriteServiceOrderHSearchCriteria(FavouriteServiceOrderHSearchCriteria favouriteServiceOrderHSearchCriteria) {
        if (favouriteServiceOrderHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", favouriteServiceOrderHSearchCriteria.getId()));
        }
        if (favouriteServiceOrderHSearchCriteria.getFsohUserRID()!= null) {
            getSearchCriterionList().add(Restrictions.eq("fsohUserRID", favouriteServiceOrderHSearchCriteria.getFsohUserRID()));
        }
        if (StringUtils.isNotBlank(favouriteServiceOrderHSearchCriteria.getFsohName())) {
            getSearchCriterionList().add(Restrictions.like("fsohName", favouriteServiceOrderHSearchCriteria.getFsohName(), MatchMode.ANYWHERE)); // convert String to Date
        }
        return getSearchCriterionList();
    }
}
