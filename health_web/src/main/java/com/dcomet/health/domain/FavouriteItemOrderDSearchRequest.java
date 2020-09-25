package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev4
 */
public class FavouriteItemOrderDSearchRequest extends SearchRequest implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public FavouriteItemOrderDSearchRequest() {

    }

    public List<Criterion> addFavouriteItemOrderDSearchCriteria(FavouriteItemOrderDSearchCriteria favouriteItemOrderDSearchCriteria) {
        if (favouriteItemOrderDSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", favouriteItemOrderDSearchCriteria.getId()));
        }
//        if (StringUtils.isNotBlank(favouriteItemOrderDSearchCriteria.getBsName())) {
//            searchCriterionList.add(Restrictions.eq("bsName", favouriteItemOrderDSearchCriteria.getBsName())); // convert String to Date
//        }
//        if (StringUtils.isNotBlank(favouriteItemOrderDSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode",favouriteItemOrderDSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
