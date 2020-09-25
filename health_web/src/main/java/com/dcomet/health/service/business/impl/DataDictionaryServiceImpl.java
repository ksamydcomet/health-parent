package com.dcomet.health.service.business.impl;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.module.domain.CurrencyMap;
import com.dcomet.module.domain.CurrencyMapSearchRequest;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.service.impl.DCometDictionaryServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev3
 */
@Service("dataDictionaryService")
@Transactional(propagation = Propagation.SUPPORTS)
public class DataDictionaryServiceImpl extends DCometDictionaryServiceImpl implements DataDictionaryService {

    @Override
    public List<Entity> getUserEntity(Integer entityRid, boolean includeChild) throws DcometServiceException {
        List<Entity> entityList = new ArrayList<>();
        try {
            entityList = CacheUtil.getChildEntities(entityRid);
            if (includeChild) {
                for (Entity entity : entityList) {
                    if (Objects.equals(entity.getId(), entityRid)) {
                        CurrencyMapSearchRequest currencyMapSearchRequest = new CurrencyMapSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("currMapEntRID", entity.getId()));
                        currencyMapSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<CurrencyMap> currencyMapList = getCurrencyMap(currencyMapSearchRequest, true);
                        if (CollectionUtils.isNotEmpty(currencyMapList)) {
                            entity.setCurrencyMapList(currencyMapList);
                        }
                    }
                    entity.setUnits(CacheUtil.getUnits(entity.getId()));
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return entityList;
    }

}
