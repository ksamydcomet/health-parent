package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.master.domain.ServiceMaster;
import com.dcomet.module.master.domain.ServiceMasterSearchRequest;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author KS
 */
@Service("serviceMasterCache")
public class ServiceMasterCache extends BaseCacheAction {

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    public ServiceMasterCache() {
        super(ServiceMaster.class, 1 * 5);
    }

    @Override
    protected List<ServiceMaster> getDataObjectsToCache() throws CacheException {
        return masterService.getServiceMaster(new ServiceMasterSearchRequest(), false);
    }

}
