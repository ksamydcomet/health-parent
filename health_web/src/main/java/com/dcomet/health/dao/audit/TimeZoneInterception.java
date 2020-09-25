package com.dcomet.health.dao.audit;

import org.hibernate.EmptyInterceptor;

/**
 *
 * @author Dev2
 */
public class TimeZoneInterception extends EmptyInterceptor {

//    /**
//     *
//     * @param userId
//     * @return
//     */
//    private Calendar getTimeZone(Integer userId) {
//
//        if (userId != null) {
//            CacheManager cacheManager = CacheManager.getInstance();
//            @SuppressWarnings("unchecked")
//            Collection<User> userList = cacheManager.getAllObjects(User.class);
//            for (User user : userList) {
//                if (user.getId().equals(userId)) {
//                    return DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone());
//                }
//            }
//        }
//        return DateUtil.convertTimeBasedOnTimeZone(null);
//    }
//
//    /**
//     *
//     * @param userEntityId
//     * @return
//     */
//    private Entity getEntity(Integer userEntityId) {
//        CacheManager cacheManager = CacheManager.getInstance();
//        @SuppressWarnings("unchecked")
//        Collection<Entity> entityList = cacheManager.getAllObjects(Entity.class);
//        for (Entity entity : entityList) {
//            if (entity.getId().equals(userEntityId)) {
//                return entity;
//            }
//        }
//        return null;
//    }
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1;
//
//    @Override
//    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, org.hibernate.type.Type[] types) {
//        if (entity instanceof Auditable) {
//            if (!valuesHasChanged(currentState, previousState, propertyNames)) {//no need to do update exit
//                return false;
//            }
//            Map<String, String> values = new HashMap<>();
//            values.put("modifiedDateTime", "modifiedUserRid");
//            setValues(currentState, propertyNames, values);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
//        if (entity instanceof Auditable) {
//            Map<String, String> values = new HashMap<>();
//            setValues(state, propertyNames, values);
//            values.put("createdDateTime", "createdUserRid");
//            values.put("modifiedDateTime", "modifiedUserRid");
//            setValues(state, propertyNames, values);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     *
//     * @param currentState
//     * @param propertyNames
//     * @param propertyToSet
//     * @param value
//     */
//    private Object getValue(Object[] currentState, String[] propertyNames, String property) {
//        Calendar calender = Calendar.getInstance();
//        for (int i = 0; i < propertyNames.length; i++) {
//            String currString = propertyNames[i];
//            if (currString.equalsIgnoreCase(property)) {
//                return getTimeZone((Integer) currentState[i]);
//            }
//        }
//        return calender;
//    }
//
//    private void setValues(Object[] currentState, String[] propertyNames, Map<String, String> values) {
//        for (int i = 0; i < propertyNames.length; i++) {
//            String currString = propertyNames[i];
//            if (values.containsKey(currString)) { // maps can hold nulls
//                currentState[i] = getValue(currentState, propertyNames, values.get(currString));
//            }
//        }
//    }
//
//    /**
//     *
//     * @param currentState
//     * @param oldState
//     * @param propertyNames
//     * @return
//     */
//    private boolean valuesHasChanged(Object[] currentState, Object[] oldState, String[] propertyNames) {
//        Object currentObject = null;
//        Object oldObject = null;
//        String name;
//        if (oldState == null) {
//            return true;
//        }
//        for (int i = 0; i < propertyNames.length; i++) {
//            currentObject = currentState[i];
//            oldObject = oldState[i];
//            name = propertyNames[i];
//            if (name.equalsIgnoreCase("modifiedDateTime")) {
//                continue;
//            }
//
//            /*
//             * this if-else block could be improved for readability, however
//             * since it is run *a lot*, it is best to reduce the number of comparisons
//             * jmcclure 11/9/13
//             */
//            if (currentObject == null) {
//                if (oldObject == null) {
//                    continue; // both objects are null, do not register a change
//                } else {
//                    return true; // current == null, old != null - record a change
//                }
//            } else if (oldObject == null) {
//                return true; // current != null && old == null - record a change
//            }
//            // if we get here both current and old are not null
//
//            if (currentObject instanceof String) {
//                if (!(((String) currentObject).equals((String) oldObject))) {
//                    return true;
//                }
//            } else if (currentObject instanceof Integer) {
//                if (((Integer) currentObject).compareTo(((Integer) oldObject)) != 0) {
//                    return true;
//                }
//            } else if (currentObject instanceof Long) {
//                if (((Long) currentObject).compareTo(((Long) oldObject)) != 0) {
//                    return true;
//                }
//            } else if (currentObject instanceof Double) {
//                if (((Double) currentObject).compareTo(((Double) oldObject)) != 0) {
//                    return true;
//                }
//            } else if (currentObject instanceof Date) {
//                if (((Date) currentObject).getTime() != ((Date) oldObject).getTime()) {
//                    return true;
//                }
//            } else if (currentObject instanceof Timestamp) {
//                if (((Timestamp) currentObject).getTime() != ((Timestamp) oldObject).getTime()) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
