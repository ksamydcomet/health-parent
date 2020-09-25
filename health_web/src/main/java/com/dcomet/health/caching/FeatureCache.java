package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.domain.Feature;
import com.dcomet.module.service.DCometDictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author KS
 */
@Service("featureCache")
public class FeatureCache extends BaseCacheAction {

    @Autowired
    @Qualifier("dataDictionaryService")
    private DCometDictionaryService dataDictionaryService;

    public FeatureCache() {
        super(Feature.class, 1 * 5);
    }

    @Override
    protected List<Feature> getDataObjectsToCache() throws CacheException {
        return dataDictionaryService.getFeature();
    }

}
