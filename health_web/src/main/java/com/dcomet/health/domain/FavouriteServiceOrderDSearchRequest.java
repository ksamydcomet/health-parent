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
public class FavouriteServiceOrderDSearchRequest extends SearchRequest implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public FavouriteServiceOrderDSearchRequest() {

    }

    public List<Criterion> addFavouriteServiceOrderDSearchCriteria(FavouriteServiceOrderDSearchCriteria favouriteServiceOrderDSearchCriteria) {
        if (favouriteServiceOrderDSearchCriteria.getId() != null) {
            getSearchCriterionList().add(Restrictions.eq("id", favouriteServiceOrderDSearchCriteria.getId()));
        }
//     if (StringUtils.isNotBlank(favouriteServiceOrderDSearchCriteria.getBsName())) {
//         searchCriterionList.add(Restrictions.eq("bsName", favouriteServiceOrderDSearchCriteria.getBsName())); // convert String to Date
//     }
//        if (StringUtils.isNotBlank(favouriteServiceOrderDSearchCriteria.getBsCode())) {
//            searchCriterionList.add(Restrictions.eq("bsCode",favouriteServiceOrderDSearchCriteria.getBsCode())); // convert String to Date
//        }
        return getSearchCriterionList();
    }
}
