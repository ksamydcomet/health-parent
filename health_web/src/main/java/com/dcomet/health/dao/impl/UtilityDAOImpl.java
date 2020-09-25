package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.UtilityDAO;
import com.dcomet.health.dao.data.PayerServiceMapData;
import com.dcomet.health.dao.data.PrintTemplateData;
import com.dcomet.health.domain.PrintTemplateSearchRequest;
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

@Repository("utilityDAO")
public class UtilityDAOImpl extends HibernateDaoSupport implements UtilityDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void savePrintTemplate(PrintTemplateData printTemplateData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(printTemplateData);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<PrintTemplateData> getPrintTemplate(PrintTemplateSearchRequest printTemplateSearchRequest) throws DcometDAOException {
        List<PrintTemplateData> printTemplateDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = printTemplateSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerServiceMapData.class);
            defaultSortOrder.createSortCriteria(printTemplateSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            printTemplateDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return printTemplateDataList;
    }
}
