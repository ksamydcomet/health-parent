package com.dcomet.health.batch.dao.impl;

import com.dcomet.fw.domain.ReportScheduleHSearchRequest;
import com.dcomet.health.batch.dao.ReportScheduleDAO;
import com.dcomet.health.batch.dao.data.ReportScheduleHData;
import com.dcomet.fw.exception.DcometBatchException;
import java.sql.Timestamp;
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
 * @author Dev2
 */
@Repository("reportScheduleDAO")
public class ReportScheduleDAOImpl extends HibernateDaoSupport implements ReportScheduleDAO {

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<ReportScheduleHData> getReportScheduleH(ReportScheduleHSearchRequest reportScheduleHSearchRequest) throws DcometBatchException {
        List<ReportScheduleHData> reportScheduleHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = reportScheduleHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ReportScheduleHData.class);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            reportScheduleHDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometBatchException(e);
        } catch (HibernateException e) {
            throw new DcometBatchException(e);
        } catch (Exception e) {
            throw new DcometBatchException(e);
        } catch (Throwable e) {
            throw new DcometBatchException(e);
        }
        return reportScheduleHDataList;
    }

    @Override
    public void nextReportSchedule(ReportScheduleHData reportScheduleH) throws DcometBatchException {
        reportScheduleH.setRshLastExeDateTime(new Timestamp(System.currentTimeMillis()));
        getSessionFactory().getCurrentSession().saveOrUpdate(reportScheduleH);
    }
}
