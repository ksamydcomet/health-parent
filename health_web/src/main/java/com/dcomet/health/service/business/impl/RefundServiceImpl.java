package com.dcomet.health.service.business.impl;

import com.dcomet.module.billing.service.impl.DCometBillingServiceImpl;
import com.dcomet.module.billing.dao.data.RefundDData;
import com.dcomet.module.billing.dao.data.RefundHData;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.fw.domain.Base;
import com.dcomet.module.billing.domain.RefundH;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.RefundService;
import com.dcomet.module.billing.domain.BillD;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.module.billing.domain.ReceiptH;
import com.dcomet.module.billing.domain.RefundHSearchRequest;
import com.dcomet.module.domain.User;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("refundService")
@Transactional(propagation = Propagation.SUPPORTS)
public class RefundServiceImpl extends DCometBillingServiceImpl implements RefundService {

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("masterService")
    MasterService masterService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) {
        try {
            RefundH refundH = (RefundH) object;
            if (!"CANCEL_REFUND".equals(actionCode)) {
                if (refundH.getId() == null) {
                    AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("REF", refundH.getEntityRid());
                    refundH.setRefhNo(autoNumber.getAutoNumber());
                    refundH.setRefhPrefix(autoNumber.getAutoPrefix());
                    refundH.setRefhPrintableNo(autoNumber.getAutoNumber());
                    dataDictionaryService.saveAutoNumberIncrement("REF", refundH.getEntityRid());
                }
                RefundHData refundHData = billingAdapter.convertRefundHToRefundHData(refundH);
                if (null != actionCode) {
                    switch (actionCode) {
                        case "CREATE_REFUND": {
                            Integer nxtConState = 0;
                            if (refundH.getRefhApprovalCheck() == 1) {
                                nxtConState = getBOStateTransitionConfig("Refund_approval", getCurrentState(refundH.getId())).getBostcBostToStateIndex();
                            } else {
                                nxtConState = getBOStateTransitionConfig("Direct_refund", getCurrentState(refundH.getId())).getBostcBostToStateIndex();
                            }
                            refundHData.setRefhStatus(nxtConState);
                            break;
                        }
//                        case "APPROVE_REFUND": {
//                            Integer nxtConState = getBOStateTransitionConfig("Approved", getCurrentState(refundH.getId())).getBostcBostToStateIndex();
//                            refundHData.setRefhStatus(nxtConState);
//                            break;
//                        }
//                        case "REJECT_REFUND": {
//                            Integer nxtConState = getBOStateTransitionConfig("Rejected", getCurrentState(refundH.getId())).getBostcBostToStateIndex();
//                            refundHData.setRefhStatus(nxtConState);
//                            
//                            break;
//                        }
                        default:
                            refundHData.setRefhStatus(nextState);
                            break;
                    }
                }
                if (CollectionUtils.isNotEmpty(refundH.getRefundD())) {
                    List<RefundDData> refundDDataList = billingAdapter.convertRefundDToRefundDData(refundH.getRefundD());
                    for (RefundDData refundDData : refundDDataList) {
                        refundDData.setRefundHData(refundHData);
                    }
                    refundHData.setRefundDData(refundDDataList);
                }
                billingDAO.saveRefundH(refundHData);
                return refundHData.getId();
            } else {
                refundH.setRefhCancelledDate(refundH.getCurrentDateByUTZ());
                RefundHData refundHData = billingAdapter.convertRefundHToRefundHData(refundH);
                refundHData.setRefhStatus(nextState);
                billingDAO.saveRefundH(refundHData);
                return refundHData.getId();
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer getCurrentState(Integer boRID
    ) {
        RefundH refundH = getRefundH(boRID);
        return refundH != null ? refundH.getRefhStatus() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID
    ) {
        RefundH refundH = getRefundH(boRID);
        return refundH != null ? new StringBuilder(refundH.getRefhNo()).append("&").append(refundH.getRefhPayerName()).append("&").append(refundH.getRefhAmount()).toString() : null;
    }

    @Override
    public String getTransactionReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTransactionRefundReport(RefundHSearchRequest refundHSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            RefundH refundH = getRefundH(refundHSearchRequest, true).get(0);
            string = dataDictionaryService.getReportFromTemplate("REFUND_REPORT", refundH, "refund", refundHSearchRequest.getEntityRid());

            string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(refundHSearchRequest.getEntityRid()), "pe");

            Patient patient = clinicalService.getPatient(refundH.getRefhPayerRID());
            string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
            User user = CacheUtil.getUser(refundHSearchRequest.getUserId());
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
