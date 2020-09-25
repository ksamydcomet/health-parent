package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.service.DCometDictionaryService;

/**
 *
 * @author KS
 */
@Service("entityCache")
public class EntityCache extends BaseCacheAction {

    @Autowired
    @Qualifier("dataDictionaryService")
    private DCometDictionaryService dataDictionaryService;

    public EntityCache() {
        super(Entity.class, 1 * 5);
    }

    @Override
    protected List<Entity> getDataObjectsToCache() throws CacheException {
        return dataDictionaryService.getEntity();
    }

}
