package com.dcomet.health.dao.impl;

import com.dcomet.module.billing.dao.impl.DCometBillingDAOImpl;
import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.BillingDAO;
import com.dcomet.module.billing.dao.data.BillDData;
import com.dcomet.module.billing.dao.data.BillHData;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.module.billing.dao.data.AdvanceDetailsData;
import com.dcomet.module.billing.dao.data.PaymentModeDetailsData;
import com.dcomet.module.billing.dao.data.ReceiptDData;
import com.dcomet.module.billing.dao.data.ReceiptHData;
import com.dcomet.module.billing.dao.data.RefundDData;
import com.dcomet.module.billing.dao.data.RefundHData;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchRequest;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.module.billing.domain.ReceiptDSearchRequest;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;
import com.dcomet.module.billing.domain.RefundHSearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KS
 */
@Repository("billingDAO")
public class BillingDAOImpl extends DCometBillingDAOImpl implements BillingDAO {

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
    public List<AdvanceDetailsData> getAdvanceDetails(
            AdvanceDetailsSearchRequest advanceDetailsSearchRequest)
            throws DcometDAOException {
        List<AdvanceDetailsData> advanceDetailsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = advanceDetailsSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AdvanceDetailsData.class);
            defaultSortOrder.createSortCriteria(
                    advanceDetailsSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            advanceDetailsDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return advanceDetailsDataList;
    }

    @Override
    public void saveAdvanceDetails(AdvanceDetailsData advanceDetailsData)
            throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(advanceDetailsData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BillHData> getBillH(BillHSearchRequest billHSearchRequest) throws DcometDAOException {
        List<BillHData> billHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = billHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(BillHData.class);
            defaultSortOrder.createSortCriteria(
                    billHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            billHDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return billHDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BillDData> getBillD(BillHSearchRequest billHSearchRequest) throws DcometDAOException {
        List<BillDData> billDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = billHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(BillDData.class);
            defaultSortOrder.createSortCriteria(
                    billHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            billDDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return billDDataList;
    }

    @Override
    public void saveBillH(List<BillHData> billHDataList)
            throws DcometDAOException {
        try {
            getSession().clear();
            for (BillHData billHData : billHDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(billHData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveBillD(List<BillDData> billDDataList)
            throws DcometDAOException {
        try {
            getSession().clear();
            for (BillDData billDData : billDDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(billDData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PaymentModeDetailsData> getPaymentModeDetails(PaymentModeSearchRequest paymentModeSearchRequest) throws DcometDAOException {
        List<PaymentModeDetailsData> paymentModeDetailsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = paymentModeSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(PaymentModeDetailsData.class);
            defaultSortOrder.createSortCriteria(
                    paymentModeSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            paymentModeDetailsDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return paymentModeDetailsDataList;
    }

    @Override
    public void savePaymentModeDetails(List<PaymentModeDetailsData> paymentModeDetailsDataList)
            throws DcometDAOException {
        try {
            for (PaymentModeDetailsData paymentModeDetailsData : paymentModeDetailsDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(paymentModeDetailsData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReceiptHData> getReceiptH(ReceiptHSearchRequest receiptHSearchRequest) throws DcometDAOException {
        List<ReceiptHData> receiptHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = receiptHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptHData.class);
            defaultSortOrder.createSortCriteria(
                    receiptHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            receiptHDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return receiptHDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReceiptDData> getReceiptD(ReceiptHSearchRequest receiptHSearchRequest) throws DcometDAOException {
        List<ReceiptDData> receiptDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = receiptHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptDData.class);
            defaultSortOrder.createSortCriteria(
                    receiptHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            receiptDDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return receiptDDataList;
    }

    @Override
    public void saveReceiptH(ReceiptHData receiptHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(receiptHData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @Override
    public void saveReceiptD(List<ReceiptDData> receiptDDataList) throws DcometDAOException {
        try {
            for (ReceiptDData receiptDData : receiptDDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(receiptDData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RefundHData> getRefundH(RefundHSearchRequest refundHSearchRequest) throws DcometDAOException {
        List<RefundHData> refundHDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = refundHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(RefundHData.class);
            defaultSortOrder.createSortCriteria(
                    refundHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            refundHDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return refundHDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RefundDData> getRefundD(RefundHSearchRequest refundHSearchRequest) throws DcometDAOException {
        List<RefundDData> refundDDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = refundHSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(RefundDData.class);
            defaultSortOrder.createSortCriteria(
                    refundHSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            refundDDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return refundDDataList;
    }

    @Override
    public void saveRefundH(RefundHData refundHData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(refundHData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @Override
    public void saveRefundD(List<RefundDData> refundDDataList) throws DcometDAOException {
        try {
            for (RefundDData refundDData : refundDDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(refundDData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReceiptDData> getReceiptDForBill(ReceiptDSearchRequest receiptDSearchRequest) throws DcometDAOException {
        List<ReceiptDData> receiptDDataList = new ArrayList<ReceiptDData>();
        try {
            List<Criterion> criteriaList = receiptDSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptDData.class);
            defaultSortOrder.createSortCriteria(
                    receiptDSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            receiptDDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return receiptDDataList;
    }
}
