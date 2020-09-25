package com.dcomet.health.caching;

import com.dcomet.fw.common.caching.CacheManager;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.Unit;
import com.dcomet.module.domain.User;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Dev1
 */
public class CacheUtil {

    public static User getUser(String userId) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<User> userList = cacheManager.getAllObjects(User.class);
        for (User user : userList) {
            if (StringUtils.equalsIgnoreCase(user.getUserID(), userId)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserById(Integer userId) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<User> userList = cacheManager.getAllObjects(User.class);
        for (User user : userList) {
            if (Objects.equal(user.getId(), userId)) {
                return user;
            }
        }
        return null;
    }

    public static Unit getUnitByUnitId(Integer unitRID) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<Unit> unitList = cacheManager.getAllObjects(Unit.class);
        for (Unit unit : unitList) {
            if (unit.getId().equals(unitRID)) {
                return unit;
            }
        }
        return null;
    }

    public static Unit getUnit(Integer entityRID) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<Unit> unitList = cacheManager.getAllObjects(Unit.class);
        for (Unit unit : unitList) {
            if (unit.getEntityRid().equals(entityRID)) {
                return unit;
            }
        }
        return null;
    }

    public static List<Unit> getUnits(Integer entityRID) {
        List<Unit> units = new ArrayList<>();
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<Unit> unitList = cacheManager.getAllObjects(Unit.class);
        for (Unit unit : unitList) {
            if (unit.getEntityRid().equals(entityRID)) {
                units.add(unit);
            }
        }
        return units;
    }

    public static Entity getEntity(Integer entityRId) {
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Collection<Entity> entityList = cacheManager.getAllObjects(Entity.class);
        for (Entity entity : entityList) {
            if (entity.getId().equals(entityRId)) {
                return entity;
            }
        }
        return null;
    }

    public static boolean getEntityType(Integer entityRId) {
        Entity entity = getEntity(entityRId);
        return entity.getEntityParentRID() == 0;
    }

    public static Entity getParentEntity(Integer entityRId) {
        Entity entity = getEntity(entityRId);
        if (entity.getEntityParentRID() == 0) {
            return entity;
        } else {
            return getEntity(entity.getEntityParentRID());
        }
    }

    public static Integer getParentEntityId(Integer entityRId) {
        Entity entity = getEntity(entityRId);
        if (entity.getEntityParentRID() == 0) {
            return entity.getId();
        } else {
            return getEntity(entity.getEntityParentRID()).getId();
        }
    }

    public static List<Entity> getChildEntities(Integer entityRId) {
        List<Entity> entitys = new ArrayList<>();
        CacheManager cacheManager = CacheManager.getInstance();
        @SuppressWarnings("unchecked")
        Entity entity = getEntity(entityRId);
        Collection<Entity> entityList = cacheManager.getAllObjects(Entity.class);
        if (0 == entity.getEntityParentRID()) {
            entitys.add(entity);
            for (Entity entity1 : entityList) {
                if (entity1.getEntityParentRID().equals(entityRId)) {
                    entitys.add(entity1);
                }
            }
        } else {
            entitys.add(getEntity(entity.getEntityParentRID()));
            for (Entity entity1 : entityList) {
                if (entity1.getEntityParentRID().equals(entity.getEntityParentRID())) {
                    entitys.add(entity1);
                }
            }
        }
        return CollectionUtils.isNotEmpty(entitys) ? entitys : null;
    }

    public static User getLoginUser(SecurityContext securityContext) {
        return getUser(securityContext.getUserPrincipal().getName());
    }

}
