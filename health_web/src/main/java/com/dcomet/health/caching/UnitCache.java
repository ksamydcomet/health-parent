package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.domain.Unit;
import com.dcomet.module.domain.UnitSearchRequest;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author KS
 */
@Service("unitCache")
public class UnitCache extends BaseCacheAction {

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    public UnitCache() {
        super(Unit.class, 1 * 5);
    }

    @Override
    protected List<Unit> getDataObjectsToCache() throws CacheException {
        return masterService.getUnit(new UnitSearchRequest(), false);
    }

}
