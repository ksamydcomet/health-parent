package com.dcomet.health.dao.audit;

import com.dcomet.fw.common.caching.CacheManager;
import com.dcomet.fw.dao.audit.TimeZoneInterception;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.User;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Dev2
 */
public class HealthTimeZoneInterception extends TimeZoneInterception {

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public Calendar getTimeZone(Integer userId) {

        if (userId != null) {
            CacheManager cacheManager = CacheManager.getInstance();
            @SuppressWarnings("unchecked")
            Collection<User> userList = cacheManager.getAllObjects(User.class);
            for (User user : userList) {
                if (user.getId().equals(userId)) {
                    return DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone());
                }
            }
        }
        return DateUtil.convertTimeBasedOnTimeZone(null);
    }

    /**
     *
     * @param userEntityId
     * @return
     */
    private Entity getEntity(Integer userEntityId) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<Entity> entityList = cacheManager.getAllObjects(Entity.class);
        for (Entity entity : entityList) {
            if (entity.getId().equals(userEntityId)) {
                return entity;
            }
        }
        return null;
    }

}
