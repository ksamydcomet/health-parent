package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.ProcedureRequestDAO;
import com.dcomet.health.dao.data.ProcedureAnesthesistData;
import com.dcomet.health.dao.data.ProcedureAttendDoctorData;
import com.dcomet.health.dao.data.ProcedureNurseData;
import com.dcomet.health.dao.data.ProcedureRequestHData;
import com.dcomet.health.dao.data.ProcedureTechnicianData;
import com.dcomet.health.domain.ProcedureRequestHSearchRequest;
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
 * @author ABDUL
 */
@Repository("procedureRequestDAO")
public class ProcedureRequestDAOImpl extends HibernateDaoSupport implements ProcedureRequestDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<ProcedureRequestHData> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
        List<ProcedureRequestHData> procedureRequestHDatas = new ArrayList<>();
        try {
            List<Criterion> criteriaList = procedureRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ProcedureRequestHData.class);
            defaultSortOrder.createSortCriteria(
                    procedureRequestHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            procedureRequestHDatas = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return procedureRequestHDatas;
    }

    @Override
    public void saveProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(procedureRequestHData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveProcedureTechnician(List<ProcedureTechnicianData> procedureTechnicianDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ProcedureTechnicianData child1Data : procedureTechnicianDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveProcedureNurse(List<ProcedureNurseData> procedureNurseDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ProcedureNurseData child1Data : procedureNurseDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctorData> procedureAttendDoctorDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ProcedureAttendDoctorData child1Data : procedureAttendDoctorDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveProcedureAnesthesist(List<ProcedureAnesthesistData> procedureAnesthesistDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (ProcedureAnesthesistData child1Data : procedureAnesthesistDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProcedureAttendDoctorData> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
        List<ProcedureAttendDoctorData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = procedureRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ProcedureAttendDoctorData.class);
            defaultSortOrder.createSortCriteria(
                    procedureRequestHSearchRequest.getSortOrder(), criteria);
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

    @SuppressWarnings("unchecked")
    @Override
    public List<ProcedureTechnicianData> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
        List<ProcedureTechnicianData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = procedureRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ProcedureTechnicianData.class);
            defaultSortOrder.createSortCriteria(
                    procedureRequestHSearchRequest.getSortOrder(), criteria);
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

    @SuppressWarnings("unchecked")
    @Override
    public List<ProcedureNurseData> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
        List<ProcedureNurseData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = procedureRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ProcedureNurseData.class);
            defaultSortOrder.createSortCriteria(
                    procedureRequestHSearchRequest.getSortOrder(), criteria);
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

    @SuppressWarnings("unchecked")
    @Override
    public List<ProcedureAnesthesistData> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
        List<ProcedureAnesthesistData> child1DataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = procedureRequestHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ProcedureAnesthesistData.class);
            defaultSortOrder.createSortCriteria(
                    procedureRequestHSearchRequest.getSortOrder(), criteria);
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
}
