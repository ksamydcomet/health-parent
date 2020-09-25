package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.domain.DdictType;
import com.dcomet.module.service.DCometDictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author KS
 */
@Service("ddictTypeCache")
public class DdictTypeCache extends BaseCacheAction {

    @Autowired
    @Qualifier("dataDictionaryService")
    private DCometDictionaryService dataDictionaryService;

    public DdictTypeCache() {
        super(DdictType.class, 1 * 5);
    }

    @Override
    protected List<DdictType> getDataObjectsToCache() throws CacheException {
        return dataDictionaryService.getDdictType();
    }

}
