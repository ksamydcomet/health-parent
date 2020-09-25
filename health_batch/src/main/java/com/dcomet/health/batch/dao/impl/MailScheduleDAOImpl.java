package com.dcomet.health.batch.dao.impl;

import com.dcomet.health.batch.dao.MailScheduleDAO;
import com.dcomet.health.batch.dao.data.MailQueueData;
import com.dcomet.fw.exception.DcometBatchException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dev2
 */
@Repository("mailScheduleDAO")
public class MailScheduleDAOImpl extends HibernateDaoSupport implements MailScheduleDAO {

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<MailQueueData> getMailQueue() throws DcometBatchException {
        List<MailQueueData> mailQueueDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(MailQueueData.class);
            criteria.add(Restrictions.eq("mqSent", 0));
            mailQueueDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometBatchException(e);
        } catch (HibernateException e) {
            throw new DcometBatchException(e);
        } catch (Exception e) {
            throw new DcometBatchException(e);
        } catch (Throwable e) {
            throw new DcometBatchException(e);
        }
        return mailQueueDataList;
    }

    @Override
    public void saveMailQueue(List<MailQueueData> mailQueueDataList) throws DcometBatchException {
        try {
            for (MailQueueData mailQueueData : mailQueueDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(mailQueueData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometBatchException(e);
        }
    }

}
