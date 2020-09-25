package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.BaseCacheAction;
import com.dcomet.fw.common.caching.CacheException;
import com.dcomet.module.domain.User;
import com.dcomet.module.domain.UserSearchRequest;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author KS
 */
@Service("userCache")
public class UserCache extends BaseCacheAction {

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    public UserCache() {
        super(User.class, 1 * 5);
    }

    @Override
    protected List<User> getDataObjectsToCache() throws CacheException {
        return masterService.getUser(new UserSearchRequest(), false);
    }

}
