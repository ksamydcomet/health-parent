package com.dcomet.health.service.business.impl;

import com.dcomet.module.billing.service.impl.DCometBillingServiceImpl;
import com.dcomet.module.billing.dao.data.AdvanceDetailsData;
import com.dcomet.module.billing.dao.data.PaymentModeDetailsData;
import com.dcomet.module.billing.domain.AdvanceDetails;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.fw.domain.Base;
import com.dcomet.module.billing.domain.RefundD;
import com.dcomet.module.billing.domain.RefundH;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.RefundService;
import com.dcomet.health.service.business.AdvanceDetailsService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchRequest;
import com.dcomet.module.billing.domain.BillD;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.module.billing.domain.PaymentModeDetails;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.module.billing.domain.ReceiptH;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchRequest;
import com.dcomet.module.domain.User;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("advanceDetailsService")
@Transactional(propagation = Propagation.SUPPORTS)
public class AdvanceDetailsServiceImpl extends DCometBillingServiceImpl implements AdvanceDetailsService {

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("refundService")
    public RefundService refundService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;
    private Object refundHSearchRequest;

    private AutoNumber generateAutoNumber(AdvanceDetails advanceDetails) {
        AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("ADV", advanceDetails.getEntityRid());
        dataDictionaryService.saveAutoNumberIncrement("ADV", advanceDetails.getEntityRid());
        return autoNumber;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base base, Integer nextState, String actionCode) {
        AdvanceDetails advanceDetails = (AdvanceDetails) base;
        AdvanceDetailsData advanceDetailsData = null;
        Float zeroBalance = 0f;
        try {
            if (null != actionCode) {
                switch (actionCode) {
                    case "BUILTIN_ACTION":
                    case "CREATE_ADVANCE":
                        advanceDetailsData = createAdvanceDetail(advanceDetails, advanceDetailsData, nextState);
                        break;
                    case "REFUND_REQUEST":
                        advanceDetails.setAdStatus(nextState);
                        advanceDetails.setAdRegDate(advanceDetails.getCurrentDateByUTZ());
                        advanceDetailsData = billingAdapter.convertAdvanceDetailsToAdvanceDetailsData(advanceDetails);
                        if (Objects.equal(actionCode, "REFUND_REQUEST")) {
                            advanceDetailsData.setAdRefundedAmount(0.0f);
                        }
                        billingDAO.saveAdvanceDetails(advanceDetailsData);
                        createRefund(advanceDetails, advanceDetailsData, actionCode);
                        break;
                    case "CANCEL_ADVANCE":
                        advanceDetails.setAdStatus(nextState);
                        advanceDetails.setAdRegDate(advanceDetails.getCurrentDateByUTZ());
                        advanceDetailsData = billingAdapter.convertAdvanceDetailsToAdvanceDetailsData(advanceDetails);
                        Integer currentState = getCurrentState(advanceDetails.getId());
                        String bostcCondtion = "";
                        if (Objects.equal(advanceDetails.getAdCancelFrom(), "CANCEL_FROM_REFUND")) {
                            if (!advanceDetails.getAdAdjustedAmount().equals(zeroBalance)) {
                                bostcCondtion = "Partially cancelled";
                            } else {
                                bostcCondtion = "Cancelled";
                            }
                        } else {
                            if (!advanceDetails.getAdAdjustedAmount().equals(zeroBalance)) {
                                bostcCondtion = "Adjusted_Amount!=0";
                            } else {
                                bostcCondtion = "Adjusted_Amount=0";
                            }
                        }
                        Integer nxtConState = getBOStateTransitionConfig(bostcCondtion, currentState).getBostcBostToStateIndex();
                        advanceDetailsData.setAdStatus(nxtConState);
                        billingDAO.saveAdvanceDetails(advanceDetailsData);
                        if (advanceDetails.getAdCancelFrom() != null && Objects.equal(advanceDetails.getAdCancelFrom(), "DIRECT")) {
                            createRefund(advanceDetails, advanceDetailsData, actionCode);
                        }
                        break;
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return advanceDetailsData != null ? advanceDetailsData.getId() : null;
    }

    private AdvanceDetailsData createAdvanceDetail(AdvanceDetails advanceDetails, AdvanceDetailsData advanceDetailsData, Integer nextState) {
        Float zeroBalance = 0f;
        String bostcCondtion = "";
        if (advanceDetails.getId() == null) {
            AutoNumber autoNumber = generateAutoNumber(advanceDetails);
            if (autoNumber != null) {
                advanceDetails.setAdNo(autoNumber.getAutoNumber());
                advanceDetails.setAdPrefix(autoNumber.getAutoPrefix());
                advanceDetails.setAdPrintableNo(autoNumber.getAutoNumber());
            }
            advanceDetails.setAdRegDate(advanceDetails.getCurrentDateByUTZ());
            advanceDetails.setAdStatus(nextState);
            advanceDetails.setAdAdjustedAmount(0f);
            advanceDetails.setAdRefundedAmount(0f);
            advanceDetails.setAdPaidAmount(0f);

        } else if (advanceDetails.getId() != null) {
            if (CollectionUtils.isNotEmpty(advanceDetails.getRefundH())) {
                if (advanceDetails.getAdAdjustedAmount() != 0) {
                    advanceDetails.setAdStatus(3);
                } else {
                    advanceDetails.setAdStatus(1);
                }
                for (RefundH refundH : advanceDetails.getRefundH()) {
                    refundH.setCurrentObject(advanceDetails);
                    refundService.save(refundH, refundH.getId(), "REFUND", "CANCEL_REFUND");
                }
            } else {
                AdvanceDetails advanceDetailsDb = getAdvanceDetails(advanceDetails.getId());
                if (advanceDetailsDb.getAdStatus() != 6) {
                    if (!advanceDetails.getAdBalanceAmount().equals(zeroBalance)) {
                        bostcCondtion = "AdvanceBalance!=0";
                    } else {
                        bostcCondtion = "AdvanceBalance=0";
                    }
                } else {
                    if (!advanceDetails.getAdAdjustedAmount().equals(zeroBalance)) {
                        bostcCondtion = "Adjusted_Amount!=0";
                    } else {
                        bostcCondtion = "Adjusted_Amount=0";
                    }
                }

                Integer currentState = getCurrentState(advanceDetails.getId());
                Integer nxtConState = getBOStateTransitionConfig(bostcCondtion, currentState).getBostcBostToStateIndex();
                advanceDetails.setAdStatus(nxtConState);
            }
        }
        advanceDetailsData = billingAdapter.convertAdvanceDetailsToAdvanceDetailsData(advanceDetails);
        billingDAO.saveAdvanceDetails(advanceDetailsData);
        advanceDetails.setId(advanceDetailsData.getId());
        createPaymentModeDetails(advanceDetails, advanceDetailsData);
        return advanceDetailsData;
    }

    private void createPaymentModeDetails(AdvanceDetails advanceDetails, AdvanceDetailsData advanceDetailsData) {
        if (advanceDetails.getPaymentModeDetails() != null && !advanceDetails.getPaymentModeDetails().isEmpty()) {
            List<PaymentModeDetailsData> paymentModeDetailsDataList = billingAdapter.convertPaymentModeDetailsToPaymentModeDetailsData(advanceDetails.getPaymentModeDetails());
            for (PaymentModeDetailsData paymentModeDetailsData : paymentModeDetailsDataList) {
                paymentModeDetailsData.setPmdEntityRID(advanceDetails.getEntityRid());
                paymentModeDetailsData.setPmdRegDate(DateUtil.convertStringToDate(advanceDetails.getCurrentDateByUTZ()));
                paymentModeDetailsData.setCreatedUserRid(advanceDetails.getUserRid());
                paymentModeDetailsData.setModifiedUserRid(advanceDetails.getUserRid());
                paymentModeDetailsData.setPmdTransRID(advanceDetailsData.getId());
            }
            billingDAO.savePaymentModeDetails(paymentModeDetailsDataList);
        }
    }

    private void createRefund(AdvanceDetails advanceDetails, AdvanceDetailsData advanceDetailsData, String actionCode) {

        RefundH refundH = new RefundH();
        refundH.setCurrentObject(advanceDetails);
        if (Objects.equal(actionCode, "REFUND_REQUEST")) {
            refundH.setRefhApprovalCheck(1);
        } else {
            refundH.setRefhApprovalCheck(0);
        }
        refundH.setRefhUnitRID(advanceDetails.getUnitRid());
        refundH.setRefhPayerRID(advanceDetailsData.getAdPayerRID());
        refundH.setRefhPayerNo(advanceDetailsData.getAdPayerNo());
        refundH.setRefhPayerName(advanceDetailsData.getAdPayerName());
        refundH.setRefhPayerType(1);
        refundH.setRefhAmount(advanceDetails.getAdRefundedAmount());
        List<RefundD> refundDList = new ArrayList<>();
        RefundD refundD = new RefundD();
        refundD.setRefdAdvanceRID(advanceDetailsData.getId());
        refundD.setRefdAmount(advanceDetails.getAdRefundedAmount());
        refundD.setRefdType(1);
        refundDList.add(refundD);
        refundH.setRefundD(refundDList);
        refundService.save(refundH, IDRAFT, "REFUND", "CREATE_REFUND");
    }

    @Override
    public void saveRefundWithAdvance(RefundH refundH) throws DcometServiceException {
        try {
            Integer id = refundService.save(refundH, refundH.getId(), "REFUND", refundH.getRefhActionCode());
            RefundH refundHDb = getRefundHWithChild(id);
            if (CollectionUtils.isNotEmpty(refundHDb.getRefundD())) {
                for (RefundD refundD : refundHDb.getRefundD()) {
                    AdvanceDetails advanceDetails = getAdvanceDetails(refundD.getRefdAdvanceRID());
                    if (Objects.equal(refundH.getRefhActionCode(), "REJECT_REFUND")) {
                        advanceDetails.setAdBalanceAmount(advanceDetails.getAdAmount() - advanceDetails.getAdAdjustedAmount());
                        save(advanceDetails, advanceDetails.getId(), "ADVANCE", "BUILTIN_ACTION");
                    } else if (Objects.equal(refundH.getRefhActionCode(), "APPROVE_REFUND")) {
                        advanceDetails.setAdCancelFrom("CANCEL_FROM_REFUND");
                        save(advanceDetails, advanceDetails.getId(), "ADVANCE", "CANCEL_ADVANCE");
                    }
                }
            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        AdvanceDetails advanceDetails = getAdvanceDetails(boRID);
        return advanceDetails != null ? advanceDetails.getAdStatus() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        AdvanceDetails advanceDetails = getAdvanceDetails(boRID);
        return advanceDetails != null ? new StringBuilder(advanceDetails.getAdNo()).append("&").append(advanceDetails.getAdPayerName()).toString() : null;
    }

    @Override
    public String getTransactionReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTransactionAdvanceReport(AdvanceDetailsSearchRequest advanceDetailsSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            AdvanceDetails advanceDetails = getAdvanceDetails(advanceDetailsSearchRequest, true).get(0);
            string = dataDictionaryService.getReportFromTemplate("ADVANCE_REPORT", advanceDetails, "advance", advanceDetailsSearchRequest.getEntityRid());

            List<Criterion> searchCriterionList = new ArrayList<>();
            PaymentModeSearchRequest paymentModeSearchRequest = new PaymentModeSearchRequest();
            searchCriterionList.add(Restrictions.eq("pmdTransRID", advanceDetails.getId()));
            paymentModeSearchRequest.setSearchCriterionList(searchCriterionList);
            List<PaymentModeDetailsData> paymentMode = billingDAO.getPaymentModeDetails(paymentModeSearchRequest);
            if (CollectionUtils.isNotEmpty(paymentMode)) {
                List<PaymentModeDetails> result = null;
                result = billingAdapter.convertPaymentModeDetailsDataToPaymentModeDetails(paymentMode);
                for (PaymentModeDetails paymentModeDetails : result) {
                    if (paymentMode.get(0).getPmdTransType() == 1) {
                        searchCriterionList.clear();
                        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
                        searchCriterionList.add(Restrictions.eq("id", paymentModeDetails.getPmdPaymentMode()));
                        ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                        Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
                        if (ddictResult != null) {
                            string = dataDictionaryService.getReportFromHTML(string, ddictResult, "dict");
                        }
                    }
                }
            }

            string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(advanceDetailsSearchRequest.getEntityRid()), "pe");

            Patient patient = clinicalService.getPatient(advanceDetails.getAdPayerRID());
            string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
            User user = CacheUtil.getUser(advanceDetailsSearchRequest.getUserId());
            string = dataDictionaryService.getReportFromHTML(string, user, "usr");

        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    @Override
    public String getPayerReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillH saveBillH(BillH billH, boolean includeChild) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillH saveBillHAlone(BillH billH) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServiceRequestH> getServiceRequestByBill(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DrugRequestH> getmaterialRequestByBill(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillH saveBillHWithCondition(BillH billH, boolean includeChild) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BillD> setBillD(List<BillD> billDs) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillH modifyBillH(BillH billH, boolean includeChild) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveReceipt(ReceiptH receipt, Integer value) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillH getBillDHById(Integer bhrid) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
