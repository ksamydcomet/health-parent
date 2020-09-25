package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.ServiceOrderDAO;
import com.dcomet.health.dao.data.ServiceOrderData;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.PatientData;
import com.dcomet.health.dao.data.ServiceOrderDData;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KS
 */
@Repository("serviceOrderDAO")
public class ServiceOrderDAOImpl extends HibernateDaoSupport implements ServiceOrderDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public Long getServiceOrderTotalCount(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException {
        Long totalRecordCount = 0L;
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ServiceOrderData.class);
            List<Criterion> criteriaList = serviceOrderSearchRequest.getSearchCriterionList();
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            totalRecordCount = ((Integer) criteria.setProjection(
                    Projections.rowCount()).uniqueResult()).longValue();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return totalRecordCount;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceOrderData> getServiceOrders(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException {
        List<ServiceOrderData> serviceOrderDataList = new ArrayList<ServiceOrderData>();

        try {
            Long startRow = serviceOrderSearchRequest.getStartRow();
            Long endRow = serviceOrderSearchRequest.getEndRow();
            List<Criterion> criteriaList = serviceOrderSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ServiceOrderData.class);
            defaultSortOrder.createSortCriteria(serviceOrderSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            criteria.setFirstResult(startRow.intValue());
            criteria.setMaxResults((endRow.intValue() - startRow.intValue()));
            serviceOrderDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return serviceOrderDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceOrderData> getServiceOrder(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException {
        List<ServiceOrderData> serviceOrderDataList = new ArrayList<ServiceOrderData>();
        try {
            List<Criterion> criteriaList = serviceOrderSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ServiceOrderData.class);
            defaultSortOrder.createSortCriteria(serviceOrderSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            serviceOrderDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return serviceOrderDataList;
    }

    @Override
    public void saveServiceOrder(ServiceOrderData serviceOrderData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(serviceOrderData);
            getSession().flush();
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
    public void saveServiceOrderD(List<ServiceOrderDData> serviceOrderDDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ServiceOrderDData serviceOrderDData : serviceOrderDDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(serviceOrderDData);
            }
            getSession().flush();
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
    public List<ServiceOrderData> getServiceOrder(List<Criterion> criterionList) throws DcometDAOException {
        List<ServiceOrderData> serviceOrderDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ServiceOrderData.class);
            if (criterionList != null) {
                for (Criterion criterion : criterionList) {
                    criteria.add(criterion);
                }
            }
            serviceOrderDataList = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return serviceOrderDataList;
    }

    @Override
    public List<ServiceOrderDData> getServiceOrderD(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException {
        List<ServiceOrderDData> serviceOrderDDataList = new ArrayList<ServiceOrderDData>();
        try {
            List<Criterion> criteriaList = serviceOrderSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ServiceOrderDData.class);
            defaultSortOrder.createSortCriteria(serviceOrderSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            serviceOrderDDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return serviceOrderDDataList;
    }

    @Override
    public List<PatientData> getPendingServicePatients(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException {
        List<PatientData> patientDatas = new ArrayList<>();
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceOrderData.class, "so")
                    .setProjection(Projections.property("so.soPatientRID")).setProjection(Projections.groupProperty("so.soPatientRID"));
            List<Criterion> criteriaList = serviceOrderSearchRequest.getSearchCriterionList();
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    detachedCriteria.add(criterion);
                }
            }
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientData.class, "p");
            criteria.add(Property.forName("p.id").in(detachedCriteria));
            patientDatas = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return patientDatas;
    }
   
}
