package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class FavouriteItemOrderHSearchRequest extends SearchRequest {

    @Override
    public String toString() {
        return super.toString();
    }

    public FavouriteItemOrderHSearchRequest() {

    }

    public List<Criterion> addFavouriteItemOrderHSearchCriteria(FavouriteItemOrderHSearchCriteria favouriteItemOrderHSearchCriteria) {
        if (favouriteItemOrderHSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", favouriteItemOrderHSearchCriteria.getId()));
        }
        if (StringUtils.isNotBlank(favouriteItemOrderHSearchCriteria.getFiohName())) {
            getSearchCriterionList().add(Restrictions.like("fiohName", favouriteItemOrderHSearchCriteria.getFiohName(), MatchMode.ANYWHERE)); // convert String to Date
        }
        return getSearchCriterionList();
    }
}
