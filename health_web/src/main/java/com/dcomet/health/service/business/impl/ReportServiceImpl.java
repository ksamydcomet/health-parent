package com.dcomet.health.service.business.impl;

import com.dcomet.health.dao.ReportDAO;
import com.dcomet.health.dao.data.rt.RBillCollectionData;
import com.dcomet.health.domain.dbd.ReportSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.service.business.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev3
 */
@Service("reportService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ReportServiceImpl implements ReportService {

    @Autowired
    @Qualifier("reportDAO")
    ReportDAO reportDAO;

    @Override
    public List<RBillCollectionData> getRBillCollectionData(ReportSearchRequest reportSearchRequest) throws DcometServiceException {
        return reportDAO.getRBillCollectionData(reportSearchRequest);
    }

    @Override
    public List<Object[]> getUnitSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getUnitSalesReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getServiceSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getServiceSalesReport(fromDate, toDate, entityRid);
    }
//    @Override
//    public List<Object[]> getPayerSummary(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
//        return reportDAO.getPayerSummary(fromDate, toDate, entityRid);
//    }

    @Override
    public List<Object[]> getPayerSummary(Integer entityRid, String fromDate, String toDate, String payerType) throws DcometServiceException {
        return reportDAO.getPayerSummary(entityRid, fromDate, toDate, payerType);
    }

    @Override
    public List<Object[]> getCollectionSummaryReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getCollectionSummaryReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getCollectionReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getLaboratoryReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getLaboratoryReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getPendingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getPendingSalesReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getPharmacySalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getPharmacySalesReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getWiseCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getWiseCollectionReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getCancelledReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getCancelledReport(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getDiscountBill(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getDiscountBill(fromDate, toDate, entityRid);
    }

    @Override
    public List<Object[]> getBillingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
        return reportDAO.getBillingSalesReport(fromDate, toDate, entityRid);
    }

//    @Override
//    public List<Object[]> getAdvanceReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException {
//        return reportDAO.getAdvanceReport(fromDate, toDate, entityRid);
//    }
}
