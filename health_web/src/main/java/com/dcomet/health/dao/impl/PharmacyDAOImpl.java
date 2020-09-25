package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.PharmacyDAO;
import com.dcomet.health.dao.data.SalesDData;
import com.dcomet.health.dao.data.SalesHData;
import com.dcomet.health.dao.data.SalesReturnDData;
import com.dcomet.health.dao.data.SalesReturnHData;
import com.dcomet.health.domain.SalesHSearchRequest;
import com.dcomet.health.domain.SalesReturnHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.MaterialIssueDData;
import com.dcomet.health.dao.data.MaterialIssueHData;
import com.dcomet.health.domain.MaterialIssueHSearchRequest;
import java.util.ArrayList;
import java.util.Date;
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
@Repository("pharmacyDAO")
public class PharmacyDAOImpl extends HibernateDaoSupport implements PharmacyDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(
            @Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    //----------Sales-----------
    @SuppressWarnings("unchecked")
    @Override
    public List<SalesHData> getSalesH(SalesHSearchRequest parentSearchRequest) throws DcometDAOException {
        List<SalesHData> parentDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = parentSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(SalesHData.class);
            defaultSortOrder.createSortCriteria(
                    parentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            parentDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return parentDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalesDData> getSalesD(SalesHSearchRequest parentSearchRequest) throws DcometDAOException {
        List<SalesDData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = parentSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SalesDData.class);
            defaultSortOrder.createSortCriteria(parentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            child1DataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return child1DataList;
    }

    @Override
    public void saveSalesH(SalesHData parentData) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(parentData);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveSalesD(List<SalesDData> child1DataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (SalesDData child1Data : child1DataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    //----------SalesReturn---------
    @SuppressWarnings("unchecked")
    @Override
    public List<SalesReturnHData> getSalesReturnH(SalesReturnHSearchRequest parentSearchRequest) throws DcometDAOException {
        List<SalesReturnHData> parentDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = parentSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SalesReturnHData.class);
            defaultSortOrder.createSortCriteria(parentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            parentDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return parentDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalesReturnDData> getSalesReturnD(SalesReturnHSearchRequest parentSearchRequest) throws DcometDAOException {
        List<SalesReturnDData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = parentSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(SalesReturnDData.class);
            defaultSortOrder.createSortCriteria(
                    parentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            child1DataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return child1DataList;
    }

    @Override
    public void saveSalesReturnH(SalesReturnHData salesReturnHData) throws DcometDAOException {
        try {
            if (salesReturnHData.getId() == null) {
                salesReturnHData.setSrhDate(new Date());
            }
            getSessionFactory().getCurrentSession().saveOrUpdate(salesReturnHData);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveSalesReturnD(List<SalesReturnDData> child1DataList) throws DcometDAOException {
        try {
            for (SalesReturnDData salesReturnDData : child1DataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(salesReturnDData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveMaterialIssueH(MaterialIssueHData materialIssueH) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(materialIssueH);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
}
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MaterialIssueHData> getMaterialIssueH(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometDAOException {
        List<MaterialIssueHData> parentDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = materialIssueHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(MaterialIssueHData.class);
            defaultSortOrder.createSortCriteria(
                    materialIssueHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            parentDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return parentDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MaterialIssueDData> getMaterialIssueD(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometDAOException {
        List<MaterialIssueDData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = materialIssueHSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MaterialIssueDData.class);
            defaultSortOrder.createSortCriteria(materialIssueHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            child1DataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return child1DataList;
    }

    @Override
    public void saveMaterialIssueD(List<MaterialIssueDData> materialIssueDList) throws DcometDAOException {
        try {
            getSession().clear();
            for (MaterialIssueDData childData : materialIssueDList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(childData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<MaterialIssueHData> getMaterialIssueHList(List<Criterion> criterionList) throws DcometDAOException {
        List<MaterialIssueHData> materialIssueHDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MaterialIssueHData.class);
            if (criterionList != null) {
                for (Criterion criterion : criterionList) {
                    criteria.add(criterion);
                }
            }
            materialIssueHDataList = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return materialIssueHDataList;
    }
        }
