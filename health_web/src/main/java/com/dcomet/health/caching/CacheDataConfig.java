package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheConfig;
import com.dcomet.fw.common.caching.CacheAction;
import com.dcomet.fw.workflow.caching.BOConfigCache;
import com.dcomet.fw.workflow.caching.BOMaserCache;
import com.dcomet.fw.workflow.caching.BOStateTransitionConfigCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cacheDataConfig")
public class CacheDataConfig extends BaseCacheConfig {

    @Autowired
    @Qualifier("entityCache")
    private EntityCache entityCache;

    @Autowired
    @Qualifier("featureCache")
    private FeatureCache featureCache;

    @Autowired
    @Qualifier("roleCache")
    private RoleCache roleCache;

    @Autowired
    @Qualifier("ddictTypeCache")
    private DdictTypeCache ddictTypeCache;

    @Autowired
    @Qualifier("userCache")
    private UserCache userCache;

    @Autowired
    @Qualifier("unitCache")
    private UnitCache unitCache;

    @Autowired
    @Qualifier("boMaserCache")
    private BOMaserCache boMaserCache;

    @Autowired
    @Qualifier("boConfigCache")
    private BOConfigCache boConfigCache;

    @Autowired
    @Qualifier("boStateTransitionConfigCache")
    private BOStateTransitionConfigCache boStateTransitionConfigCache;

    @Override
    public CacheAction[] getAllActions() {
        return new CacheAction[]{featureCache, entityCache, roleCache, ddictTypeCache, userCache, unitCache, boMaserCache, boConfigCache,
            boStateTransitionConfigCache};
    }
}
