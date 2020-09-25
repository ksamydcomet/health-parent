package com.dcomet.health.web.rest;

import com.dcomet.health.caching.CacheUtil;
import com.dcomet.fw.domain.Base;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.Unit;
import com.dcomet.module.domain.User;
import javax.ws.rs.core.SecurityContext;

public class BaseResource {

    protected User getUser(String userId) {
        return CacheUtil.getUser(userId);
    }

    protected Entity getEntity(Integer entityRId) {
        return CacheUtil.getEntity(entityRId);
    }

    protected Unit getUnit(Integer entityRID) {
        return CacheUtil.getUnit(entityRID);
    }

    protected User getLoginUser(SecurityContext securityContext) {
        return getUser(securityContext.getUserPrincipal().getName());
    }

    protected Entity getParentEntity(SecurityContext securityContext) {
        Entity entity = getEntity(getLoginUser(securityContext).getUserEntityRID());
        if (entity.getEntityParentRID() == 0) {
            return entity;
        } else {
            return getEntity(entity.getEntityParentRID());
        }
    }

    protected void addSecurityContext(SecurityContext securityContext, Base domainObj) {
        addCondtionalSecurityContext(securityContext, domainObj);
        domainObj.setUnitRid(getUnit(domainObj.getEntityRid()).getId());
    }

    protected void addCondtionalSecurityContext(SecurityContext securityContext, Base domainObj) {
        User user = getLoginUser(securityContext);
        domainObj.setUserRid(user.getId());
        domainObj.setUserId(user.getUserID());
        domainObj.setEntityRid(user.getEntityRid());
        domainObj.setEntityCurrCode(getEntity(user.getEntityRid()).getEntityCountryCode());

        domainObj.setModifiedUserRid(user.getId());
        domainObj.setCreatedUserRid(user.getId());
        domainObj.setCurrentDateByUTZ(DateUtil.convertDateToString(DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone()).getTime()));
        domainObj.setCurrentDateTimeByUTZ(DateUtil.convertDateTimeToString(DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone()).getTime()));
    }
}
