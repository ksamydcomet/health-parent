package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.rt.RBillCollectionData;
import com.dcomet.health.domain.dbd.ReportSearchRequest;
import java.util.List;

/**
 *
 * @author KS
 */
public interface ReportDAO {

    public List<RBillCollectionData> getRBillCollectionData(ReportSearchRequest reportSearchRequest) throws DcometDAOException;

    public List<Object[]> getUnitSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

//    public List<Object[]> getUnitSalesDetailsReport(String fromDate, String toDate, Integer unitRid, Integer entityRid) throws DcometDAOException;
    public List<Object[]> getServiceSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

//    public List<Object[]> getPayerSummary(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;
    public List<Object[]> getPayerSummary(Integer entityRid, String fromDate, String toDate, String payerType) throws DcometDAOException;

    public List<Object[]> getCollectionSummaryReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getLaboratoryReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getPendingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getPharmacySalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getWiseCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getCancelledReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getDiscountBill(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;
    
//    public List<Object[]> getadvanceReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

    public List<Object[]> getBillingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException;

}
