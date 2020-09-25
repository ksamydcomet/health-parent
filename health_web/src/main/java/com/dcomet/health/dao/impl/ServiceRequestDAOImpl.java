package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.ServiceRequestDAO;
import com.dcomet.health.dao.data.FavouriteServiceOrderDData;
import com.dcomet.health.dao.data.FavouriteServiceOrderHData;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.ServiceRequestData;
import com.dcomet.health.dao.data.ServiceRequestHData;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dev4
 */
@Repository("serviceRequestDAO")
public class ServiceRequestDAOImpl extends HibernateDaoSupport implements ServiceRequestDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(
            @Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<FavouriteServiceOrderHData> getFavouriteServiceOrderH(FavouriteServiceOrderHSearchRequest favouriteServiceOrderHSearchRequest) throws DcometDAOException {
        List<FavouriteServiceOrderHData> favouriteServiceOrderHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = favouriteServiceOrderHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(FavouriteServiceOrderHData.class);
            defaultSortOrder.createSortCriteria(
                    favouriteServiceOrderHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            favouriteServiceOrderHDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return favouriteServiceOrderHDataList;
    }

    @Override
    public void saveFavouriteServiceOrderH(FavouriteServiceOrderHData favouriteServiceOrderHDataList) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().save(favouriteServiceOrderHDataList);

        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<FavouriteServiceOrderDData> getFavouriteServiceOrderD(FavouriteServiceOrderDSearchRequest favouriteServiceOrderDSearchRequest) throws DcometDAOException {
        List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = favouriteServiceOrderDSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(FavouriteServiceOrderDData.class);
            defaultSortOrder.createSortCriteria(
                    favouriteServiceOrderDSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            favouriteServiceOrderDDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return favouriteServiceOrderDDataList;
    }

    @Override
    public void saveFavouriteServiceOrderD(List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().save(favouriteServiceOrderDDataList);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

//    @Override
//    public List<ServiceRequestData> getServiceRequest(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometDAOException {
//        List<ServiceRequestData> serviceRequestDataList = new ArrayList<>();
//        try {
//            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ServiceRequestData.class);
//            if (CollectionUtils.isNotEmpty(serviceRequestSearchRequest.getSearchCriterionList())) {
//                for (Criterion criterion : serviceRequestSearchRequest.getSearchCriterionList()) {
//                    criteria.add(criterion);
//                }
//            }
//            serviceRequestDataList = criteria.list();
//
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return serviceRequestDataList;
//    }
//
//    @Override
//    public void saveServiceRequest(ServiceRequestData ServiceRequest) throws DcometDAOException {
//        try {
//            getSession().clear();
//            getSessionFactory().getCurrentSession().saveOrUpdate(ServiceRequest);
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
    @Override
    public List<ServiceRequestHData> getServiceRequestH(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometDAOException {
        List<ServiceRequestHData> serviceRequestHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = serviceRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ServiceRequestHData.class);
            defaultSortOrder.createSortCriteria(
                    serviceRequestHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            serviceRequestHDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return serviceRequestHDataList;
    }

    @Override
    public void saveServiceRequestH(ServiceRequestHData serviceRequestHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(serviceRequestHData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<ServiceRequestData> getServiceRequest(ServiceRequestHSearchRequest serviceRequestHSearchRequestList) throws DcometDAOException {
        List<ServiceRequestData> serviceRequestDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = serviceRequestHSearchRequestList
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ServiceRequestData.class);
            defaultSortOrder.createSortCriteria(
                    serviceRequestHSearchRequestList.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            serviceRequestDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return serviceRequestDataList;
    }

    @Override
    public void saveServiceRequest(List<ServiceRequestData> serviceRequestDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ServiceRequestData serviceRequestData : serviceRequestDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(serviceRequestData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

}
