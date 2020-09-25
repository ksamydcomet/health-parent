package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BedGroupMSearchRequest extends SearchRequest implements java.io.Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public BedGroupMSearchRequest() {
    }

    public List<Criterion> addBedGroupMCriteria(BedGroupMSearchCriteria bedTypeMSearchCriteria) {
        if (bedTypeMSearchCriteria.getBgmRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bgmRid", bedTypeMSearchCriteria.getBgmRid()));
        }
        if (bedTypeMSearchCriteria.getBgmBedServicePointRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bgmBedServicePointRid", bedTypeMSearchCriteria.getBgmBedServicePointRid()));
        }
        if (bedTypeMSearchCriteria.getBgmBedUnitRid() != null) {
            getSearchCriterionList().add(Restrictions.eq("bgmBedUnitRid", bedTypeMSearchCriteria.getBgmBedUnitRid()));
        }
        if (bedTypeMSearchCriteria.getBgmBedGroupIndex()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bgmBedGroupIndex", bedTypeMSearchCriteria.getBgmBedGroupIndex()));
        }
        if (bedTypeMSearchCriteria.getBgmBedSubGroupIndex() != null) {
            getSearchCriterionList().add(Restrictions.eq("bgmBedSubGroupIndex", bedTypeMSearchCriteria.getBgmBedSubGroupIndex()));
        }
        if (bedTypeMSearchCriteria.getBgmBedPriceTypeIndex()!= null) {
            getSearchCriterionList().add(Restrictions.eq("bgmBedPriceTypeIndex", bedTypeMSearchCriteria.getBgmBedPriceTypeIndex()));
        }
//        if (bedTypeMSearchCriteria.getBtmName() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmName", bedTypeMSearchCriteria.getBtmName()));
//        }
//        if (bedTypeMSearchCriteria.getBtmCategory() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmCategory", bedTypeMSearchCriteria.getBtmCategory()));
//        }
//        if (bedTypeMSearchCriteria.getBtmGroupRid() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmGroupRid", bedTypeMSearchCriteria.getBtmGroupRid()));
//        }
//        if (bedTypeMSearchCriteria.getBtmServicePointIndex() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmServicePointIndex", bedTypeMSearchCriteria.getBtmServicePointIndex()));
//        }
//        if (bedTypeMSearchCriteria.getBtmActiveYesno() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmActiveYesno", bedTypeMSearchCriteria.getBtmActiveYesno()));
//        }
//        if (bedTypeMSearchCriteria.getBtmRoomType() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmRoomType", bedTypeMSearchCriteria.getBtmRoomType()));
//        }
//        if (bedTypeMSearchCriteria.getBtmState() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmState", bedTypeMSearchCriteria.getBtmState()));
//        }
//        if (bedTypeMSearchCriteria.getBtmStatus() != null) {
//            getSearchCriterionList().add(Restrictions.eq("btmStatus", bedTypeMSearchCriteria.getBtmStatus()));
//        }
        return getSearchCriterionList();
    }
}
