package com.dcomet.health.service.business.impl;

import com.dcomet.module.billing.service.impl.DCometBillingServiceImpl;
import com.dcomet.module.billing.dao.data.ReceiptDData;
import com.dcomet.module.billing.dao.data.ReceiptHData;
import com.dcomet.module.billing.domain.AdvanceDetails;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.fw.domain.Base;
import com.dcomet.module.billing.domain.PaymentModeDetails;
import com.dcomet.module.billing.domain.ReceiptD;
import com.dcomet.module.billing.domain.ReceiptH;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import static com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl.IDRAFT;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.AdvanceDetailsService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.ReceiptService;
import com.dcomet.module.billing.domain.BillD;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.module.billing.domain.ReceiptDSearchRequest;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;
import com.dcomet.module.domain.CurrencyExchange;
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

/**
 *
 * @author Dev1
 */
@Service("receiptService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ReceiptServiceImpl extends DCometBillingServiceImpl implements ReceiptService {

    @Autowired
    @Qualifier("advanceDetailsService")
    AdvanceDetailsService advanceDetailsService;

    @Autowired
    @Qualifier("masterService")
    MasterService masterService;

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("dataDictionaryService")
    public DataDictionaryService dataDictionaryService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) {
        ReceiptH receiptH = (ReceiptH) object;
        Integer advRID = null;
        ReceiptHData receiptHData = new ReceiptHData();
        try {
            if (null != actionCode) {
                switch (actionCode) {
                    case "BUILTIN_ACTION":
                    case "CREATE_RECEIPT":
                        // Generate Auto Number
                        AutoNumber autoNumber = generateAutoNumber(receiptH);
                        if (autoNumber != null) {
                            receiptH.setRhNo(autoNumber.getAutoNumber());
                            receiptH.setRhPrefix(autoNumber.getAutoPrefix());
                            receiptH.setRhPrintableNo(autoNumber.getAutoNumber());
                        }
                        // Save H
                        receiptH.setRhStatus(nextState);
                        receiptH.setRhDate(receiptH.getCurrentDateByUTZ());
                        receiptHData = billingAdapter.convertReceiptHToReceiptHData(receiptH);
                        billingDAO.saveReceiptH(receiptHData);
                        receiptH.setId(receiptHData.getId());

                        //Create Advance
                        advRID = createAdvance(receiptH);
                        // Create Payment
                        createPayment(receiptH, receiptHData);

                        // Save D
                        if (CollectionUtils.isNotEmpty(receiptH.getReceiptD())) {
                            for (ReceiptD receiptD : receiptH.getReceiptD()) {
                                if (receiptD.getRdAdvAdjustedAmount() != null) {
                                    receiptD.setRdAdvRID(advRID);
                                }
                                if (receiptH.getRhBhRID() != null && receiptH.getRhBhRID() != 0) {
                                    receiptD.setRdBillRID(receiptH.getRhBhRID());
                                }
                            }
                            List<ReceiptDData> receiptDDataList = billingAdapter.convertReceiptDToReceiptDData(receiptH.getReceiptD());
                            for (ReceiptDData receiptDData : receiptDDataList) {
                                receiptDData.setReceiptHData(receiptHData);
                            }
                            billingDAO.saveReceiptD(receiptDDataList);
                        }
                        break;

                    case "CANCEL_RECEIPT":
                        cancelReceipt(receiptH, nextState);
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
        return receiptHData.getId();
    }

    private Integer createAdvance(ReceiptH receiptH) {
        Integer advRID = null;
        if (CollectionUtils.isNotEmpty(receiptH.getAdvanceDetails())) {
            for (AdvanceDetails advanceDetails : receiptH.getAdvanceDetails()) {
                advanceDetails.setCurrentObject(receiptH);
                advanceDetails.setAdBalanceAmount(advanceDetails.getAdBalanceAmount() - advanceDetails.getAdAdjustedAmount());
                advanceDetails.setAdPaidAmount(advanceDetails.getAdPaidAmount() + advanceDetails.getAdAdjustedAmount());
                advRID = advanceDetailsService.save(advanceDetails, IDRAFT, "ADVANCE", "BUILTIN_ACTION");
            }
        }
        return advRID;
    }

    private void createPayment(ReceiptH receiptH, ReceiptHData receiptHData) {
        if (CollectionUtils.isNotEmpty(receiptH.getPaymentModeDetails())) {
            List<PaymentModeDetails> paymentModeDetailsList = new ArrayList<>();
            for (PaymentModeDetails paymentModeDetails : receiptH.getPaymentModeDetails()) {
                if (!Objects.equal(receiptH.getRhBaseCurRID(), paymentModeDetails.getPmdCurrencyRID())) {
                    List<CurrencyExchange> currencyExchanges = dataDictionaryService.getCurrencyExchangeByEntity(receiptH.getEntityRid());
                    if (CollectionUtils.isNotEmpty(currencyExchanges)) {
                        for (CurrencyExchange currencyExchange : currencyExchanges) {
                            if (Objects.equal(currencyExchange.getCerDispCurRid(), paymentModeDetails.getPmdCurrencyRID())) {
                                paymentModeDetails.setPmdCurrencyRID(paymentModeDetails.getPmdCurrencyRID());
                                Float eFactor = currencyExchange.getCerBaseToDispFactor();
                                paymentModeDetails.setPmdCurrencyValue(paymentModeDetails.getPmdAmount());
                                paymentModeDetails.setPmdAmount(paymentModeDetails.getPmdAmount() * eFactor);
                                paymentModeDetails.setPmdCurrencyExchangeRate(eFactor);

                            }
                        }
                    }
                } else {
                    paymentModeDetails.setPmdCurrencyValue(paymentModeDetails.getPmdAmount());
                    paymentModeDetails.setPmdCurrencyExchangeRate(1.0f);
                }
                paymentModeDetails.setPmdTransRID(receiptHData.getId());
                paymentModeDetails.setPmdRegDate(receiptH.getCurrentDateByUTZ());
                paymentModeDetails.setEntityRid(receiptH.getEntityRid());
                paymentModeDetails.setCreatedUserRid(receiptH.getUserRid());
                paymentModeDetails.setModifiedUserRid(receiptH.getUserRid());
                paymentModeDetailsList.add(paymentModeDetails);
            }
            savePaymentMode(paymentModeDetailsList);
        }
    }

    private AutoNumber generateAutoNumber(ReceiptH receiptH) {
        AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("REC", receiptH.getEntityRid());
        dataDictionaryService.saveAutoNumberIncrement("REC", receiptH.getEntityRid());
        return autoNumber;
    }

    private Integer cancelReceipt(ReceiptH receiptH, Integer nextState) {
        // Save H
        receiptH.setRhStatus(nextState);
        ReceiptHData receiptHData = billingAdapter.convertReceiptHToReceiptHData(receiptH);
        receiptHData.setRhCancelledDateTime(DateUtil.convertStringToCalendar(receiptH.getCurrentDateTimeByUTZ()));
        billingDAO.saveReceiptH(receiptHData);
        // Create Advance
        if (CollectionUtils.isNotEmpty(receiptH.getAdvanceDetails())) {
            AdvanceDetails advanceDetails = new AdvanceDetails();
            advanceDetails.setAdType(0);
            advanceDetails.setAdRefRID(receiptH.getRhBhRID());
            advanceDetails.setAdPayerRID(receiptH.getRhPayerRID());
            advanceDetails.setAdPayerName(receiptH.getRhPayerName());
            advanceDetails.setAdPayerNo(receiptH.getRhPayerNo());//PayerRID or PatientRID
            advanceDetails.setAdAmount(receiptH.getRhPaidAmount());
            advanceDetails.setAdAdjustedAmount(0f);
            advanceDetails.setAdRefundedAmount(0f);
            advanceDetails.setAdBalanceAmount(receiptH.getRhPaidAmount());
            advanceDetails.setAdCreationMode(1);
            advanceDetails.setCurrentObject(receiptH);
            advanceDetailsService.save(advanceDetails, IDRAFT, "ADVANCE", "CREATE_ADVANCE");
        }

        return receiptHData.getId();
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        ReceiptH receiptH = getReceiptH(boRID);
        return receiptH != null ? receiptH.getRhStatus() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        return "";
    }

    @Override
    public String getTransactionReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTransactionReceiptReport(ReceiptHSearchRequest receiptHSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            ReceiptH receiptH = getReceiptH(receiptHSearchRequest, true).get(0);

            if (receiptH.getRhPayerType() != 31) {
                String curdate = DateUtil.getCurrentDate();
                receiptH.setCurdate(curdate);
                for (ReceiptD receiptD : receiptH.getReceiptD()) {
                    if (receiptD.getRdBillRID() != null && receiptD.getRdBillRID() != 0) {
                        String billno = getBillH(receiptD.getRdBillRID()).getBhBillNo();
                        if (billno != null) {
                            receiptD.setRdBillno(billno);
                        }
                    }
                }
                string = dataDictionaryService.getReportFromTemplate("RECEIPT_REPORT", receiptH, "receipt", receiptHSearchRequest.getEntityRid());
                List<Criterion> searchCriterionList = new ArrayList<>();
                PayerMasterSearchRequest PayerMasterSearchRequest = new PayerMasterSearchRequest();
                searchCriterionList.add(Restrictions.eq("pdId", receiptH.getRhPayerRID()));
                PayerMasterSearchRequest.setSearchCriterionList(searchCriterionList);
                List<PayerMaster> payerMasterList = masterService.getPayerMaster(PayerMasterSearchRequest, false);
                for (PayerMaster payerMaster : payerMasterList) {
                    string = dataDictionaryService.getReportFromHTML(string, payerMaster, "payer");
                }
            } else {
                string = dataDictionaryService.getReportFromTemplate("RECEIPT_REPORT", receiptH, "receipt", receiptHSearchRequest.getEntityRid());
            }

            List<Criterion> searchCriterionList = new ArrayList<>();
            DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
            searchCriterionList.add(Restrictions.eq("id", receiptH.getRhPayerType()));
            ddictSearchRequest.setSearchCriterionList(searchCriterionList);
            Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
            string = dataDictionaryService.getReportFromHTML(string, ddictResult, "dict");
            searchCriterionList.clear();
            BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
            searchCriterionList.add(Restrictions.eq("id", receiptH.getRhBhRID()));
            billHSearchRequest.setSearchCriterionList(searchCriterionList);
            List<BillH> billHList = getBillH(billHSearchRequest, true);
            if (CollectionUtils.isNotEmpty(billHList)) {
                for (BillH billH : billHList) {
                    string = dataDictionaryService.getReportFromHTML(string, billH, "bill");
                }
            } else {
                BillH billH = new BillH();
                billH.setBhBillNo("--");
                billH.setBhBillDate("--");
                billH.setBhDoctorName("--");
                billH.setModifiedDateTime("--");
                billH.setBhGrossAmount(0.0f);
                billH.setBhPaidAmount(0.0f);
                billH.setBhDueAmount(0.0f);
                string = dataDictionaryService.getReportFromHTML(string, billH, "bill");

            }

            Patient patient = clinicalService.getPatient(receiptH.getRhPayerRID());
            string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
            List<Criterion> searchCriterionLists = new ArrayList<>();
            DdictSearchRequest ddictSearchRequestTitle = new DdictSearchRequest();
            searchCriterionLists.add(Restrictions.eq("id", Integer.parseInt(patient.getPatTitle())));
            ddictSearchRequestTitle.setSearchCriterionList(searchCriterionLists);
            Ddict ddictResultTitle = dataDictionaryService.getDdict(ddictSearchRequestTitle).get(0);
            if (ddictResultTitle != null) {
                string = dataDictionaryService.getReportFromHTML(string, ddictResultTitle, "dDictTitle");
            }

            User user = CacheUtil.getUser(receiptHSearchRequest.getUserId());
            string = dataDictionaryService.getReportFromHTML(string, user, "usr");

            string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(receiptHSearchRequest.getEntityRid()), "pe");

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
