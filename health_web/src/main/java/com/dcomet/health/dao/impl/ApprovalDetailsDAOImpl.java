package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.ApprovalDetailsDAO;
import com.dcomet.health.dao.data.ApprovalDetailsData;
import com.dcomet.health.domain.ApprovalDetailsSearchRequest;
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

@Repository("approvalDetailsDAO")
public class ApprovalDetailsDAOImpl extends HibernateDaoSupport implements ApprovalDetailsDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveApprovalDetails(ApprovalDetailsData approvalDetailsData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(approvalDetailsData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<ApprovalDetailsData> getApprovalDetails(ApprovalDetailsSearchRequest approvalDetailsSearchRequest) throws DcometDAOException {
        List<ApprovalDetailsData> approvalDetailsDatas = new ArrayList<>();
        try {
            List<Criterion> criteriaList = approvalDetailsSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ApprovalDetailsData.class);
            defaultSortOrder.createSortCriteria(approvalDetailsSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            approvalDetailsDatas = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return approvalDetailsDatas;
    }

}
