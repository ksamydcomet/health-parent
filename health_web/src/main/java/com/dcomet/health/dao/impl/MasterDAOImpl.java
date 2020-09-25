package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.domain.MailQueueSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.MasterDAO;
import com.dcomet.health.dao.data.DiscountMasterData;
import com.dcomet.health.dao.data.DiscountServiceMapData;
import com.dcomet.module.master.dao.data.PackageItemMapData;
import com.dcomet.module.master.dao.data.PackageMasterData;
import com.dcomet.module.master.dao.data.PackageServiceMapData;
import com.dcomet.health.dao.data.PayerAuthorizationData;
import com.dcomet.health.dao.data.PayerMasterData;
import com.dcomet.health.dao.data.PayerIncidentData;
import com.dcomet.health.dao.data.PayerServiceMapData;
import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.dao.data.TemplateMailData;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.module.master.domain.PackageMasterSearchRequest;
import com.dcomet.health.domain.PayerAuthorizationSearchRequest;
import com.dcomet.health.domain.PayerIncidentSearchRequest;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.PayerServiceMapSearchRequest;
import com.dcomet.health.domain.PrintableInfoSearchRequest;
import com.dcomet.module.dao.data.MailQueueData;
import com.dcomet.module.master.dao.impl.DCometMasterDAOImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("masterDAO")
public class MasterDAOImpl extends DCometMasterDAOImpl implements MasterDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Override
    public void saveDiscountMaster(DiscountMasterData discountMasterData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(discountMasterData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @Override
    public void saveDiscountServiceMap(List<DiscountServiceMapData> discountServiceMapDataList) throws DcometDAOException {
        try {
            for (DiscountServiceMapData discountServiceMapData : discountServiceMapDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(discountServiceMapData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DiscountMasterData> getDiscountMaster(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometDAOException {
        List<DiscountMasterData> discountMasterDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = discountMasterSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DiscountMasterData.class);
            defaultSortOrder.createSortCriteria(discountMasterSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            discountMasterDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return discountMasterDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DiscountServiceMapData> getDiscountServiceMap(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometDAOException {
        List<DiscountServiceMapData> discountServiceMapDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = discountMasterSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DiscountServiceMapData.class);
            defaultSortOrder.createSortCriteria(discountMasterSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            discountServiceMapDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return discountServiceMapDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PayerAuthorizationData> getPayerAuthorization(PayerAuthorizationSearchRequest payerAuthorizationSearchRequest) throws DcometDAOException {
        List<PayerAuthorizationData> payerAuthorizationDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = payerAuthorizationSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerAuthorizationData.class);
            defaultSortOrder.createSortCriteria(payerAuthorizationSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            payerAuthorizationDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return payerAuthorizationDataList;
    }

    @Override
    public void savePayerAuthorization(PayerAuthorizationData payerAuthorizationData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(payerAuthorizationData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PayerIncidentData> getPayerIncident(PayerIncidentSearchRequest payerIncidentSearchRequest) throws DcometDAOException {
        List<PayerIncidentData> payerIncidentDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = payerIncidentSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerIncidentData.class);
            defaultSortOrder.createSortCriteria(payerIncidentSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            payerIncidentDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return payerIncidentDataList;
    }

    @Override
    public void savePayerIncident(List<PayerIncidentData> payerIncidentDataList) throws DcometDAOException {
        try {
            for (PayerIncidentData payerIncidentData : payerIncidentDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(payerIncidentData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void savePayerMaster(PayerMasterData payerMasterData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(payerMasterData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void savePayerServiceMap(List<PayerServiceMapData> payerServiceMapDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (PayerServiceMapData payerServiceMapData : payerServiceMapDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(payerServiceMapData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PayerMasterData> getPayerMaster(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometDAOException {
        List<PayerMasterData> payerMasterDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = payerMasterSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerMasterData.class);
            defaultSortOrder.createSortCriteria(payerMasterSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            payerMasterDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return payerMasterDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PayerServiceMapData> getPayerServiceMap(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometDAOException {
        List<PayerServiceMapData> payerServiceMapDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = payerMasterSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerServiceMapData.class);
            defaultSortOrder.createSortCriteria(payerMasterSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            payerServiceMapDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return payerServiceMapDataList;
    }

//    @Override
//    public void deletePayerServiceMap(Integer psmPdRid) throws DcometDAOException {
//        try {
//            Query query = getSessionFactory().getCurrentSession().createQuery("delete PayerServiceMapData where psmPdRid = :psmPdRid");
//            query.setParameter("psmPdRid", psmPdRid);
//            query.executeUpdate();
//        } catch (DataAccessException e) {
//            throw new DcometDAOException(e);
//        } catch (HibernateException e) {
//            throw new DcometDAOException(e);
//        } catch (Exception e) {
//            throw new DcometDAOException(e);
//        } catch (Throwable e) {
//            throw new DcometDAOException(e);
//        }
//    }
    @Override
    public List<PayerServiceMapData> getPayerServiceMap(PayerServiceMapSearchRequest payerServiceMapSearchRequest) throws DcometDAOException {
        List<PayerServiceMapData> payerServiceMapDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = payerServiceMapSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PayerServiceMapData.class);
            defaultSortOrder.createSortCriteria(payerServiceMapSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            payerServiceMapDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return payerServiceMapDataList;
    }

    @Override
    public List<PrintableInfoData> getPrintInfo(PrintableInfoSearchRequest printableInfoSearchRequest) throws DcometDAOException {
        List<PrintableInfoData> printableInfoDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = printableInfoSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PrintableInfoData.class);
            defaultSortOrder.createSortCriteria(printableInfoSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            printableInfoDataList = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return printableInfoDataList;
    }

    @Override
    public PrintableInfoData getPrintInfo(Integer entityRid) throws DcometDAOException {
        List<PrintableInfoData> printableInfoDatas = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PrintableInfoData.class);
            criteria.add(Restrictions.eq("peEntityRid", entityRid));
            printableInfoDatas = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(printableInfoDatas) ? printableInfoDatas.get(0) : null;
    }

    @Override
    public void savePrintInfo(PrintableInfoData printableInfoData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(printableInfoData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public PrintableInfoData getPrintInfoLogo(Integer entRid) throws DcometDAOException {
        List<PrintableInfoData> printableInfoDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PrintableInfoData.class);
            criteria.add(Restrictions.eq("peEntityRid", entRid));
            criteria.setFetchSize(1);
            printableInfoDataList = criteria.list();

        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(printableInfoDataList) ? printableInfoDataList.get(0) : null;
    }

    @Override
    public List<MailQueueData> getMailQueue(MailQueueSearchRequest mailQueueSearchRequest) throws DcometDAOException {
        List<MailQueueData> mailQueueDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = mailQueueSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MailQueueData.class);
            defaultSortOrder.createSortCriteria(mailQueueSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            mailQueueDataList = criteria.list();

        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return mailQueueDataList;
    }

    @Override
    public void saveMailQueue(MailQueueData mailQueueData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(mailQueueData);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<TemplateMailData> getMailTemplate(String tempCode) throws DcometDAOException {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(TemplateMailData.class);
        criteria.add(Restrictions.eq("stmCode", tempCode));
        List<TemplateMailData> templateMailDataList = criteria.list();
        if (CollectionUtils.isNotEmpty(templateMailDataList)) {
            return templateMailDataList;
        }
        return null;
    }   
}
