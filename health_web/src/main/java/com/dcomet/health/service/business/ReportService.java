package com.dcomet.health.service.business;

import com.dcomet.health.dao.data.rt.RBillCollectionData;
import com.dcomet.health.domain.dbd.ReportSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import java.util.List;

public interface ReportService {

    public List<RBillCollectionData> getRBillCollectionData(ReportSearchRequest reportSearchRequest) throws DcometServiceException;

    public List<Object[]> getUnitSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getServiceSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

//    public List<Object[]> getPayerSummary(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;
    public List<Object[]> getPayerSummary(Integer entityRid, String fromDate, String toDate, String payerType) throws DcometServiceException;

    public List<Object[]> getCollectionSummaryReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getLaboratoryReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getPendingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getPharmacySalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getWiseCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;
    
    public List<Object[]> getDiscountBill(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getCancelledReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;
    
//    public List<Object[]> getAdvanceReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;

    public List<Object[]> getBillingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometServiceException;
}
