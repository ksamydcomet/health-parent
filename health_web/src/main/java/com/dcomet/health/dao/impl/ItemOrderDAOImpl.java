package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.ItemOrderDAO;
import com.dcomet.health.dao.data.FavouriteItemOrderDData;
import com.dcomet.health.dao.data.FavouriteItemOrderHData;
import com.dcomet.health.domain.FavouriteItemOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteItemOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.DrugRequestDData;
import com.dcomet.health.dao.data.DrugRequestHData;
import com.dcomet.health.dao.data.ItemOrderData;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.ItemOrderSearchRequest;
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
@Repository("itemOrderDAO")
public class ItemOrderDAOImpl extends HibernateDaoSupport implements ItemOrderDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<FavouriteItemOrderHData> getFavouriteItemOrderH(FavouriteItemOrderHSearchRequest favouriteItemOrderHSearchRequest) throws DcometDAOException {
        List<FavouriteItemOrderHData> favouriteItemOrderHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = favouriteItemOrderHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(FavouriteItemOrderHData.class);
            defaultSortOrder.createSortCriteria(
                    favouriteItemOrderHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            favouriteItemOrderHDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return favouriteItemOrderHDataList;
    }

    @Override
    public void saveFavouriteItemOrderH(FavouriteItemOrderHData favouriteItemOrderHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().save(favouriteItemOrderHData);
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
    public List<FavouriteItemOrderDData> getFavouriteItemOrderD(FavouriteItemOrderDSearchRequest favouriteItemOrderDSearchRequest) throws DcometDAOException {
        List<FavouriteItemOrderDData> favouriteItemOrderDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = favouriteItemOrderDSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(FavouriteItemOrderDData.class);
            defaultSortOrder.createSortCriteria(
                    favouriteItemOrderDSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            favouriteItemOrderDDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return favouriteItemOrderDDataList;
    }

    @Override
    public void saveFavouriteItemOrderD(List<FavouriteItemOrderDData> favouriteItemOrderDDataList) throws DcometDAOException {
        try {
            for (FavouriteItemOrderDData favouriteItemOrderDData : favouriteItemOrderDDataList) {
                getSessionFactory().getCurrentSession().save(favouriteItemOrderDData);
            }

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
    public void saveDrugH(DrugRequestHData drugRequestHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(drugRequestHData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveDrugD(List<DrugRequestDData> drugRequestDDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (DrugRequestDData drugRequestDData : drugRequestDDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(drugRequestDData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<DrugRequestHData> getDrugH(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometDAOException {
        List<DrugRequestHData> drugRequestHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = drugRequestHSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DrugRequestHData.class);
            defaultSortOrder.createSortCriteria(drugRequestHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            drugRequestHDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return drugRequestHDataList;
    }

    @Override
    public List<DrugRequestDData> getDrugD(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometDAOException {
        List<DrugRequestDData> drugRequestDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = drugRequestHSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DrugRequestDData.class);
            defaultSortOrder.createSortCriteria(drugRequestHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            drugRequestDDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return drugRequestDDataList;
    }

    @Override
    public List<ItemOrderData> getItemOrder(ItemOrderSearchRequest itemOrderSearchRequest) throws DcometDAOException {
        List<ItemOrderData> itemOrderDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = itemOrderSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ItemOrderData.class);
            defaultSortOrder.createSortCriteria(itemOrderSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            itemOrderDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return itemOrderDataList;
    }

    @Override
    public void saveItemOrder(ItemOrderData itemOrderData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(itemOrderData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }
}
