package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.DashBoardDAO;
import com.dcomet.health.dao.data.dbd.DBillCollectionData;
import com.dcomet.health.dao.data.dbd.DOpdPatientData;
import com.dcomet.health.dao.data.dbd.DOpdReferralData;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
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
 * @author KS
 */
@Repository("dashBoardDAO")
public class DashBoardDAOImpl extends HibernateDaoSupport implements DashBoardDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(
            @Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DOpdPatientData> getDOpdPatientData(
            DashBoardSearchRequest dashBoardSearchRequest)
            throws DcometDAOException {
        List<DOpdPatientData> dOpdPatientDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = dashBoardSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(DOpdPatientData.class);
            defaultSortOrder.createSortCriteria(
                    dashBoardSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            dOpdPatientDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return dOpdPatientDataList;
    }

    @Override
    public List<DOpdReferralData> getDOpdReferralData(DashBoardSearchRequest dashBoardSearchRequest) throws DcometDAOException {
        List<DOpdReferralData> dOpdReferralDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = dashBoardSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(DOpdReferralData.class);
            defaultSortOrder.createSortCriteria(
                    dashBoardSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            dOpdReferralDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return dOpdReferralDataList;
    }

    @Override
    public List<DBillCollectionData> getDBillCollection(DashBoardSearchRequest dashBoardSearchRequest) throws DcometDAOException {
        List<DBillCollectionData> billingCollectionDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = dashBoardSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(DBillCollectionData.class);
            defaultSortOrder.createSortCriteria(
                    dashBoardSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            billingCollectionDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return billingCollectionDataList;
    }
}
