package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.domain.Role;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev4
 */
@Service("roleCache")
public class RoleCache extends BaseCacheAction {

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    public RoleCache() {
        super(Role.class, 1 * 5);
    }

    @Override
    protected List<Role> getDataObjectsToCache() throws CacheException {
        return masterService.getRole();
    }
}
