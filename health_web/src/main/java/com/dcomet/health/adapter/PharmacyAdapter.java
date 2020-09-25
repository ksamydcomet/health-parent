package com.dcomet.health.adapter;

import com.dcomet.fw.adapter.BaseAdapter;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.SalesDData;
import com.dcomet.health.dao.data.SalesHData;
import com.dcomet.health.dao.data.SalesReturnDData;
import com.dcomet.health.dao.data.SalesReturnHData;
import com.dcomet.health.domain.SalesD;
import com.dcomet.health.domain.SalesH;
import com.dcomet.health.domain.SalesReturnD;
import com.dcomet.health.domain.SalesReturnH;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.MaterialIssueDData;
import com.dcomet.health.dao.data.MaterialIssueHData;
import com.dcomet.health.domain.MaterialIssueD;
import com.dcomet.health.domain.MaterialIssueH;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author KS
 */
@Component("pharmacyAdapter")
public class PharmacyAdapter extends BaseAdapter {

    //-------Sales-------
    public List<SalesH> convertSalesHDataToSalesH(
            List<SalesHData> salesHDataList) throws DcometServiceException {
        List<SalesH> salesHList = new ArrayList<>();
        for (SalesHData salesHData : salesHDataList) {
            salesHList.add(convertSalesHDataToSalesH(salesHData));
        }
        return salesHList;
    }

    public List<SalesHData> convertSalesHToSalesHData(
            List<SalesH> resultData) throws DcometServiceException {
        List<SalesHData> parentDataList = new ArrayList<>();
        for (SalesH parent : resultData) {
            parentDataList.add(convertSalesHToSalesHData(parent));
        }
        return parentDataList;
    }

    public List<SalesD> convertSalesDDataToSalesD(
            List<SalesDData> resultData) throws DcometServiceException {
        List<SalesD> child1List = new ArrayList<>();
        for (SalesDData child1Data : resultData) {
            child1List.add(convertSalesDDataToSalesD(child1Data));
        }
        return child1List;
    }

    public List<SalesDData> convertSalesDToSalesDData(List<SalesD> child1List)
            throws DcometServiceException {
        List<SalesDData> child1DataList = new ArrayList<>();
        for (SalesD child1 : child1List) {
            child1DataList.add(convertSalesDToSalesDData(child1));
        }
        return child1DataList;
    }

    public SalesH convertSalesHDataToSalesH(SalesHData salesHData)
            throws DcometServiceException {
        SalesH salesH = new SalesH();
        if (salesHData.getId() != null) {
            salesH.setId(salesHData.getId());
        }
        if (salesHData.getSalBhRID() != null) {
            salesH.setSalBhRID(salesHData.getSalBhRID());
        }
        if (salesHData.getSalBillNo() != null) {
            salesH.setSalBillNo(salesHData.getSalBillNo());
        }
        if (salesHData.getSalCustomerId() != null) {
            salesH.setSalCustomerId(salesHData.getSalCustomerId());
        }
        if (salesHData.getSalCustomerName() != null) {
            salesH.setSalCustomerName(salesHData.getSalCustomerName());
        }
        if (salesHData.getSalCustomerAddress() != null) {
            salesH.setSalCustomerAddress(salesHData.getSalCustomerAddress());
        }
        if (salesHData.getSalBillDate() != null) {
            salesH.setSalBillDate(DateUtil.convertDateToString(salesHData.getSalBillDate()));
        }
        if (salesHData.getSalCntPerson() != null) {
            salesH.setSalCntPerson(salesHData.getSalCntPerson());
        }
        if (salesHData.getSalPhoneNo() != null) {
            salesH.setSalPhoneNo(salesHData.getSalPhoneNo());
        }
        if (salesHData.getSalGrossAmount() != null) {
            salesH.setSalGrossAmount(salesHData.getSalGrossAmount());
        }
        if (salesHData.getSalTotalDiscount() != null) {
            salesH.setSalTotalDiscount(salesHData.getSalTotalDiscount());
        }
        if (salesHData.getSalTotalTax() != null) {
            salesH.setSalTotalTax(salesHData.getSalTotalTax());
        }
        if (salesHData.getSalRoundOffAmount() != null) {
            salesH.setSalRoundOffAmount(salesHData.getSalRoundOffAmount());
        }
        if (salesHData.getSalNetAmount() != null) {
            salesH.setSalNetAmount(salesHData.getSalNetAmount());
        }
        if (salesHData.getSalEntityRID() != null) {
            salesH.setEntityRid(salesHData.getSalEntityRID());
        }
        if (salesHData.getSalDueAmount() != null) {
            salesH.setSalDueAmount(salesHData.getSalDueAmount());
        }
        if (salesHData.getSalStatus() != null) {
            salesH.setSalStatus(salesHData.getSalStatus());
        }
        if (salesHData.getSalPaidAmount() != null) {
            salesH.setSalPaidAmount(salesHData.getSalPaidAmount());
        }
        if (salesHData.getSalCustomerRID() != null) {
            salesH.setSalCustomerRID(salesHData.getSalCustomerRID());
        }
        if (salesHData.getSalVersion() != null) {
            salesH.setSalVersion(salesHData.getSalVersion());
        }
        if (salesHData.getSalType() != null) {
            salesH.setSalType(salesHData.getSalType());
        }
        if (salesHData.getCreatedUserRid() != null) {
            salesH.setCreatedUserRid(salesHData.getCreatedUserRid());
        }
        if (salesHData.getCreatedDateTime() != null) {
            salesH.setCreatedDateTime(DateUtil.convertCalendarToString(salesHData.getCreatedDateTime()));
        }
        if (salesHData.getModifiedUserRid() != null) {
            salesH.setModifiedUserRid(salesHData.getModifiedUserRid());
        }
        if (salesHData.getModifiedDateTime() != null) {
            salesH.setModifiedDateTime(DateUtil.convertCalendarToString(salesHData.getModifiedDateTime()));
        }

        return salesH;
    }

    public SalesHData convertSalesHToSalesHData(SalesH salesH)
            throws DcometServiceException {
        SalesHData salesHData = new SalesHData();
        if (salesH.getId() != null) {
            salesHData.setId(salesH.getId());
        }
        if (salesH.getSalBhRID() != null) {
            salesHData.setSalBhRID(salesH.getSalBhRID());
        }
        if (salesH.getSalBillNo() != null) {
            salesHData.setSalBillNo(salesH.getSalBillNo());
        }
        if (salesH.getSalCustomerId() != null) {
            salesHData.setSalCustomerId(salesH.getSalCustomerId());
        }
        if (salesH.getSalCustomerName() != null) {
            salesHData.setSalCustomerName(salesH.getSalCustomerName());
        }
        if (salesH.getSalCustomerAddress() != null) {
            salesHData.setSalCustomerAddress(salesH.getSalCustomerAddress());
        }
        if (salesH.getSalBillDate() != null) {
            salesHData.setSalBillDate(DateUtil.convertStringToDate(salesH.getSalBillDate()));
        }
        if (salesH.getSalCntPerson() != null) {
            salesHData.setSalCntPerson(salesH.getSalCntPerson());
        }
        if (salesH.getSalPhoneNo() != null) {
            salesHData.setSalPhoneNo(salesH.getSalPhoneNo());
        }
        if (salesH.getSalGrossAmount() != null) {
            salesHData.setSalGrossAmount(salesH.getSalGrossAmount());
        }
        if (salesH.getSalTotalDiscount() != null) {
            salesHData.setSalTotalDiscount(salesH.getSalTotalDiscount());
        }
        if (salesH.getSalTotalTax() != null) {
            salesHData.setSalTotalTax(salesH.getSalTotalTax());
        }
        if (salesH.getSalRoundOffAmount() != null) {
            salesHData.setSalRoundOffAmount(salesH.getSalRoundOffAmount());
        }
        if (salesH.getSalNetAmount() != null) {
            salesHData.setSalNetAmount(salesH.getSalNetAmount());
        }
        if (salesH.getEntityRid() != null) {
            salesHData.setSalEntityRID(salesH.getEntityRid());
        }
        if (salesH.getSalDueAmount() != null) {
            salesHData.setSalDueAmount(salesH.getSalDueAmount());
        }
        if (salesH.getSalStatus() != null) {
            salesHData.setSalStatus(salesH.getSalStatus());
        }
        if (salesH.getSalPaidAmount() != null) {
            salesHData.setSalPaidAmount(salesH.getSalPaidAmount());
        }
        if (salesH.getSalCustomerRID() != null) {
            salesHData.setSalCustomerRID(salesH.getSalCustomerRID());
        }
        if (salesH.getSalVersion() != null) {
            salesHData.setSalVersion(salesH.getSalVersion());
        }
        if (salesH.getSalType() != null) {
            salesHData.setSalType(salesH.getSalType());
        }
        if (salesH.getCreatedUserRid() != null) {
            salesHData.setCreatedUserRid(salesH.getCreatedUserRid());
        }
        if (salesH.getModifiedUserRid() != null) {
            salesHData.setModifiedUserRid(salesH.getModifiedUserRid());
        }

        return salesHData;
    }

    public SalesD convertSalesDDataToSalesD(SalesDData salesDData) throws DcometServiceException {
        SalesD salesD = new SalesD();
        if (salesDData.getId() != null) {
            salesD.setId(salesDData.getId());
        }
        if (salesDData.getSalHRID() != null) {
            salesD.setSalHRID(salesDData.getSalHRID());
        }
        if (salesDData.getSalSkuRID() != null) {
            salesD.setSalSkuRID(salesDData.getSalSkuRID());
        }
        if (salesDData.getSalItemName() != null) {
            salesD.setSalItemName(salesDData.getSalItemName());
        }
        if (salesDData.getSalBatchNo() != null) {
            salesD.setSalBatchNo(salesDData.getSalBatchNo());
        }
        if (salesDData.getSalExpDate() != null) {
            salesD.setSalExpDate(salesDData.getSalExpDate());
        }
        if (salesDData.getSalBatchNo() != null) {
            salesD.setSalBatchNo(salesDData.getSalBatchNo());
        }
        if (salesDData.getSalExpDate() != null) {
            salesD.setSalExpDate(salesDData.getSalExpDate());
        }
        if (salesDData.getSalStLoc() != null) {
            salesD.setSalStLoc(salesDData.getSalStLoc());
        }
        if (salesDData.getSalStockQty() != null) {
            salesD.setSalStockQty(salesDData.getSalStockQty());
        }
        if (salesDData.getSalQty() != null) {
            salesD.setSalQty(salesDData.getSalQty());
        }
        if (salesDData.getSalReturnQty() != null) {
            salesD.setSalReturnQty(salesDData.getSalReturnQty());
        }
        if (salesDData.getSalUom() != null) {
            salesD.setSalUom(salesDData.getSalUom());
        }
        if (salesDData.getSalRate() != null) {
            salesD.setSalRate(salesDData.getSalRate());
        }
        if (salesDData.getSalGrossAmount() != null) {
            salesD.setSalGrossAmount(salesDData.getSalGrossAmount());
        }
        if (salesDData.getSalDiscount() != null) {
            salesD.setSalDiscount(salesDData.getSalDiscount());
        }
        if (salesDData.getSalTax() != null) {
            salesD.setSalTax(salesDData.getSalTax());
        }
        if (salesDData.getSalNetAmount() != null) {
            salesD.setSalNetAmount(salesDData.getSalNetAmount());
        }
        if (salesDData.getSalBaseUom() != null) {
            salesD.setSalBaseUom(salesDData.getSalBaseUom());
        }
        if (salesDData.getSalCurrentDateTimeSale() != null) {
            salesD.setSalCurrentDateTimeSale(DateUtil.convertCalendarToString(salesDData.getSalCurrentDateTimeSale()));
        }
        if (salesDData.getSalCurrentDateTimeReturn() != null) {
            salesD.setSalCurrentDateTimeReturn(DateUtil.convertCalendarToString(salesDData.getSalCurrentDateTimeReturn()));
        }
        return salesD;
    }

    public SalesDData convertSalesDToSalesDData(SalesD salesD) throws DcometServiceException {
        SalesDData salesDData = new SalesDData();
        if (salesD.getId() != null) {
            salesDData.setId(salesD.getId());
        }
        if (salesD.getSalHRID() != null) {
            salesDData.setSalHRID(salesD.getSalHRID());
        }
        if (salesD.getSalSkuRID() != null) {
            salesDData.setSalSkuRID(salesD.getSalSkuRID());
        }
        if (salesD.getSalItemName() != null) {
            salesDData.setSalItemName(salesD.getSalItemName());
        }
        if (salesD.getSalBatchNo() != null) {
            salesDData.setSalBatchNo(salesD.getSalBatchNo());
        }
        if (salesD.getSalExpDate() != null) {
            salesDData.setSalExpDate(salesD.getSalExpDate());
        }
        if (salesD.getSalBatchNo() != null) {
            salesDData.setSalBatchNo(salesD.getSalBatchNo());
        }
        if (salesD.getSalExpDate() != null) {
            salesDData.setSalExpDate(salesD.getSalExpDate());
        }
        if (salesD.getSalStLoc() != null) {
            salesDData.setSalStLoc(salesD.getSalStLoc());
        }
        if (salesD.getSalStockQty() != null) {
            salesDData.setSalStockQty(salesD.getSalStockQty());
        }
        if (salesD.getSalQty() != null) {
            salesDData.setSalQty(salesD.getSalQty());
        }
        if (salesD.getSalReturnQty() != null) {
            salesDData.setSalReturnQty(salesD.getSalReturnQty());
        }
        if (salesD.getSalUom() != null) {
            salesDData.setSalUom(salesD.getSalUom());
        }
        if (salesD.getSalRate() != null) {
            salesDData.setSalRate(salesD.getSalRate());
        }
        if (salesD.getSalGrossAmount() != null) {
            salesDData.setSalGrossAmount(salesD.getSalGrossAmount());
        }
        if (salesD.getSalDiscount() != null) {
            salesDData.setSalDiscount(salesD.getSalDiscount());
        }
        if (salesD.getSalTax() != null) {
            salesDData.setSalTax(salesD.getSalTax());
        }
        if (salesD.getSalNetAmount() != null) {
            salesDData.setSalNetAmount(salesD.getSalNetAmount());
        }
        if (salesD.getSalBaseUom() != null) {
            salesDData.setSalBaseUom(salesD.getSalBaseUom());
        }
        if (salesD.getSalCurrentDateTimeSale() != null) {
            salesDData.setSalCurrentDateTimeSale(DateUtil.convertStringToCalendar(salesD.getSalCurrentDateTimeSale()));
        }
        if (salesD.getSalCurrentDateTimeReturn() != null) {
            salesDData.setSalCurrentDateTimeReturn(DateUtil.convertStringToCalendar(salesD.getSalCurrentDateTimeReturn()));
        }

        return salesDData;
    }

    //-------SalesReturn----------
    public List<SalesReturnH> convertSalesReturnHDataToSalesReturnH(List<SalesReturnHData> salesReturnHDataList) throws DcometServiceException {
        List<SalesReturnH> salesReturnHList = new ArrayList<>();
        for (SalesReturnHData salesReturnHData : salesReturnHDataList) {
            salesReturnHList.add(convertSalesReturnHDataToSalesReturnH(salesReturnHData));
        }
        return salesReturnHList;
    }

    public List<SalesReturnHData> convertSalesReturnHToSalesReturnHData(List<SalesReturnH> resultData) throws DcometServiceException {
        List<SalesReturnHData> parentDataList = new ArrayList<>();
        for (SalesReturnH parent : resultData) {
            parentDataList.add(convertSalesReturnHToSalesReturnHData(parent));
        }
        return parentDataList;
    }

    public List<SalesReturnD> convertSalesReturnDDataToSalesReturnD(List<SalesReturnDData> resultData) throws DcometServiceException {
        List<SalesReturnD> child1List = new ArrayList<>();
        for (SalesReturnDData child1Data : resultData) {
            child1List.add(convertSalesReturnDDataToSalesReturnD(child1Data));
        }
        return child1List;
    }

    public List<SalesReturnDData> convertSalesReturnDToSalesReturnDData(List<SalesReturnD> child1List) throws DcometServiceException {
        List<SalesReturnDData> child1DataList = new ArrayList<>();
        for (SalesReturnD child1 : child1List) {
            child1DataList.add(convertSalesReturnDToSalesReturnDData(child1));
        }
        return child1DataList;
    }

    public SalesReturnH convertSalesReturnHDataToSalesReturnH(SalesReturnHData salesReturnHData) throws DcometServiceException {
        SalesReturnH salesReturnH = new SalesReturnH();
        if (salesReturnHData.getId() != null) {
            salesReturnH.setId(salesReturnHData.getId());
        }
        if (salesReturnHData.getSrhPatientRID() != null) {
            salesReturnH.setSrhPatientRID(salesReturnHData.getSrhPatientRID());
        }
        if (salesReturnHData.getSrhPatientMrn() != null) {
            salesReturnH.setSrhPatientMrn(salesReturnHData.getSrhPatientMrn());
        }
        if (salesReturnHData.getSrhPatientName() != null) {
            salesReturnH.setSrhPatientName(salesReturnHData.getSrhPatientName());
        }
        if (salesReturnHData.getSrhVisitRID() != null) {
            salesReturnH.setSrhVisitRID(salesReturnHData.getSrhVisitRID());
        }
        if (salesReturnHData.getSrhVisitType() != null) {
            salesReturnH.setSrhVisitType(salesReturnHData.getSrhVisitType());
        }
        if (salesReturnHData.getSrhRoundoffAmount() != null) {
            salesReturnH.setSrhRoundoffAmount(salesReturnHData.getSrhRoundoffAmount());
        }
        if (salesReturnHData.getSrhNetAmount() != null) {
            salesReturnH.setSrhNetAmount(salesReturnHData.getSrhNetAmount());
        }
        if (salesReturnHData.getSrhUnitRID() != null) {
            salesReturnH.setSrhUnitRID(salesReturnHData.getSrhUnitRID());
        }
        if (salesReturnHData.getSrhEntityRID() != null) {
            salesReturnH.setEntityRid(salesReturnHData.getSrhEntityRID());
        }
        if (salesReturnHData.getSrhSeqNo() != null) {
            salesReturnH.setSrhSeqNo(salesReturnHData.getSrhSeqNo());
        }
        if (salesReturnHData.getSrhBIllRID() != null) {
            salesReturnH.setSrhBIllRID(salesReturnHData.getSrhBIllRID());
        }
        if (salesReturnHData.getSrhBillNo() != null) {
            salesReturnH.setSrhBillNo(salesReturnHData.getSrhBillNo());
        }
        if (salesReturnHData.getCreatedDateTime() != null) {
            salesReturnH.setCreatedDateTime(DateUtil.convertCalendarToString(salesReturnHData.getCreatedDateTime()));
        }
        if (salesReturnHData.getCreatedUserRid() != null) {
            salesReturnH.setCreatedUserRid(salesReturnHData.getCreatedUserRid());
        }
        if (salesReturnHData.getModifiedDateTime() != null) {
            salesReturnH.setModifiedDateTime(DateUtil.convertCalendarToString(salesReturnHData.getModifiedDateTime()));
        }
        if (salesReturnHData.getModifiedUserRid() != null) {
            salesReturnH.setModifiedUserRid(salesReturnHData.getModifiedUserRid());
        }
        return salesReturnH;
    }

    public SalesReturnHData convertSalesReturnHToSalesReturnHData(SalesReturnH salesReturnH) throws DcometServiceException {
        SalesReturnHData salesReturnHData = new SalesReturnHData();

        if (salesReturnH.getId() != null) {
            salesReturnHData.setId(salesReturnH.getId());
        }
        if (salesReturnH.getSrhPatientRID() != null) {
            salesReturnHData.setSrhPatientRID(salesReturnH.getSrhPatientRID());
        }
        if (salesReturnH.getSrhPatientMrn() != null) {
            salesReturnHData.setSrhPatientMrn(salesReturnH.getSrhPatientMrn());
        }
        if (salesReturnH.getSrhPatientName() != null) {
            salesReturnHData.setSrhPatientName(salesReturnH.getSrhPatientName());
        }
        if (salesReturnH.getSrhVisitRID() != null) {
            salesReturnHData.setSrhVisitRID(salesReturnH.getSrhVisitRID());
        }
        if (salesReturnH.getSrhVisitType() != null) {
            salesReturnHData.setSrhVisitType(salesReturnH.getSrhVisitType());
        }
        if (salesReturnH.getSrhRoundoffAmount() != null) {
            salesReturnHData.setSrhRoundoffAmount(salesReturnH.getSrhRoundoffAmount());
        }
        if (salesReturnH.getSrhNetAmount() != null) {
            salesReturnHData.setSrhNetAmount(salesReturnH.getSrhNetAmount());
        }
        if (salesReturnH.getSrhUnitRID() != null) {
            salesReturnHData.setSrhUnitRID(salesReturnH.getSrhUnitRID());
        }
        if (salesReturnH.getEntityRid() != null) {
            salesReturnHData.setSrhEntityRID(salesReturnH.getEntityRid());
        }
        if (salesReturnH.getSrhSeqNo() != null) {
            salesReturnHData.setSrhSeqNo(salesReturnH.getSrhSeqNo());
        }
        if (salesReturnH.getSrhBIllRID() != null) {
            salesReturnHData.setSrhBIllRID(salesReturnH.getSrhBIllRID());
        }
        if (salesReturnH.getSrhBillNo() != null) {
            salesReturnHData.setSrhBillNo(salesReturnH.getSrhBillNo());
        }
        if (salesReturnH.getCreatedUserRid() != null) {
            salesReturnHData.setCreatedUserRid(salesReturnH.getCreatedUserRid());
        }
        if (salesReturnH.getModifiedUserRid() != null) {
            salesReturnHData.setModifiedUserRid(salesReturnH.getModifiedUserRid());
        }
        return salesReturnHData;
    }

    public SalesReturnD convertSalesReturnDDataToSalesReturnD(SalesReturnDData salesReturnDData) throws DcometServiceException {
        SalesReturnD salesReturnD = new SalesReturnD();
        if (salesReturnDData.getId() != null) {
            salesReturnD.setId(salesReturnDData.getId());
        }
        if (salesReturnDData.getSrdSrhRID() != null) {
            salesReturnD.setSrdSrhRID(salesReturnDData.getSrdSrhRID());
        }
        if (salesReturnDData.getSrdSkuRID() != null) {
            salesReturnD.setSrdSkuRID(salesReturnDData.getSrdSkuRID());
        }
        if (salesReturnDData.getSrdSkuName() != null) {
            salesReturnD.setSrdSkuName(salesReturnDData.getSrdSkuName());
        }
        if (salesReturnDData.getSrdItemBatchNo() != null) {
            salesReturnD.setSrdItemBatchNo(salesReturnDData.getSrdItemBatchNo());
        }
        if (salesReturnDData.getSrdItemExpDate() != null) {
            salesReturnD.setSrdItemExpDate(salesReturnDData.getSrdItemExpDate());
        }
        if (salesReturnDData.getSrdBIllRID() != null) {
            salesReturnD.setSrdBIllRID(salesReturnDData.getSrdBIllRID());
        }
        if (salesReturnDData.getSrdIsshRID() != null) {
            salesReturnD.setSrdIsshRID(salesReturnDData.getSrdIsshRID());
        }
        if (salesReturnDData.getSrdIssdRID() != null) {
            salesReturnD.setSrdIssdRID(salesReturnDData.getSrdIssdRID());
        }
        if (salesReturnDData.getSrdReturnQty() != null) {
            salesReturnD.setSrdReturnQty(salesReturnDData.getSrdReturnQty());
        }
        if (salesReturnDData.getSrdReturnRate() != null) {
            salesReturnD.setSrdReturnRate(salesReturnDData.getSrdReturnRate());
        }
        if (salesReturnDData.getSrdTaxAmount() != null) {
            salesReturnD.setSrdTaxAmount(salesReturnDData.getSrdTaxAmount());
        }
        if (salesReturnDData.getSrdReturnNetAmount() != null) {
            salesReturnD.setSrdReturnNetAmount(salesReturnDData.getSrdReturnNetAmount());
        }
        if (salesReturnDData.getSrdContextType() != null) {
            salesReturnD.setSrdContextType(salesReturnDData.getSrdContextType());
        }

        return salesReturnD;
    }

    public SalesReturnDData convertSalesReturnDToSalesReturnDData(SalesReturnD salesReturnD) throws DcometServiceException {
        SalesReturnDData salesReturnDData = new SalesReturnDData();
        if (salesReturnD.getId() != null) {
            salesReturnDData.setId(salesReturnD.getId());
        }
        if (salesReturnD.getSrdSrhRID() != null) {
            salesReturnDData.setSrdSrhRID(salesReturnD.getSrdSrhRID());
        }
        if (salesReturnD.getSrdSkuRID() != null) {
            salesReturnDData.setSrdSkuRID(salesReturnD.getSrdSkuRID());
        }
        if (salesReturnD.getSrdSkuName() != null) {
            salesReturnDData.setSrdSkuName(salesReturnD.getSrdSkuName());
        }
        if (salesReturnD.getSrdItemBatchNo() != null) {
            salesReturnDData.setSrdItemBatchNo(salesReturnD.getSrdItemBatchNo());
        }
        if (salesReturnD.getSrdItemExpDate() != null) {
            salesReturnDData.setSrdItemExpDate(salesReturnD.getSrdItemExpDate());
        }
        if (salesReturnD.getSrdBIllRID() != null) {
            salesReturnDData.setSrdBIllRID(salesReturnD.getSrdBIllRID());
        }
        if (salesReturnD.getSrdIsshRID() != null) {
            salesReturnDData.setSrdIsshRID(salesReturnD.getSrdIsshRID());
        }
        if (salesReturnD.getSrdIssdRID() != null) {
            salesReturnDData.setSrdIssdRID(salesReturnD.getSrdIssdRID());
        }
        if (salesReturnD.getSrdReturnQty() != null) {
            salesReturnDData.setSrdReturnQty(salesReturnD.getSrdReturnQty());
        }
        if (salesReturnD.getSrdReturnRate() != null) {
            salesReturnDData.setSrdReturnRate(salesReturnD.getSrdReturnRate());
        }
        if (salesReturnD.getSrdTaxAmount() != null) {
            salesReturnDData.setSrdTaxAmount(salesReturnD.getSrdTaxAmount());
        }
        if (salesReturnD.getSrdReturnNetAmount() != null) {
            salesReturnDData.setSrdReturnNetAmount(salesReturnD.getSrdReturnNetAmount());
        }
        if (salesReturnD.getSrdContextType() != null) {
            salesReturnDData.setSrdContextType(salesReturnD.getSrdContextType());
        }

        return salesReturnDData;
    }

    //-------MaterialIssueH-------
    public List<MaterialIssueH> convertMaterialIssueHDataToMaterialIssueH(
            List<MaterialIssueHData> materialIssueHDataList) throws DcometServiceException {
        List<MaterialIssueH> materialIssueHList = new ArrayList<>();
        for (MaterialIssueHData materialIssueHData : materialIssueHDataList) {
            materialIssueHList.add(convertMaterialIssueHDataToMaterialIssueH(materialIssueHData));
        }
        return materialIssueHList;
    }

    public List<MaterialIssueHData> convertMaterialIssueHToMaterialIssueHData(
            List<MaterialIssueH> resultData) throws DcometServiceException {
        List<MaterialIssueHData> parentDataList = new ArrayList<>();
        for (MaterialIssueH parent : resultData) {
            parentDataList.add(convertMaterialIssueHToMaterialIssueHData(parent));
        }
        return parentDataList;
    }

    //-------MaterialIssueD-------
    public List<MaterialIssueD> convertMaterialIssueDDataToMaterialIssueD(
            List<MaterialIssueDData> resultData) throws DcometServiceException {
        List<MaterialIssueD> child1List = new ArrayList<>();
        for (MaterialIssueDData child1Data : resultData) {
            child1List.add(convertMaterialIssueDDataToMaterialIssueD(child1Data));
        }
        return child1List;
    }

    public List<MaterialIssueDData> convertMaterialIssueDToMaterialIssueDData(List<MaterialIssueD> child1List)
            throws DcometServiceException {
        List<MaterialIssueDData> child1DataList = new ArrayList<>();
        for (MaterialIssueD child1 : child1List) {
            child1DataList.add(convertMaterialIssueDToMaterialIssueDData(child1));
        }
        return child1DataList;
    }

    public MaterialIssueH convertMaterialIssueHDataToMaterialIssueH(MaterialIssueHData materialIssueHData) throws DcometServiceException {
        MaterialIssueH materialIssueH = new MaterialIssueH();

        if (materialIssueHData.getId() != null) {
            materialIssueH.setId(materialIssueHData.getId());
        }
        if (materialIssueHData.getMatIssueNo() != null) {
            materialIssueH.setMatIssueNo(materialIssueHData.getMatIssueNo());
        }
        if (materialIssueHData.getMatIssuePrefix() != null) {
            materialIssueH.setMatIssuePrefix(materialIssueHData.getMatIssuePrefix());
        }
        if (materialIssueHData.getMatIssueSequence() != null) {
            materialIssueH.setMatIssueSequence(materialIssueHData.getMatIssueSequence());
        }
        if (materialIssueHData.getMatVisitRid() != null) {
            materialIssueH.setMatVisitRid(materialIssueHData.getMatVisitRid());
        }
        if (materialIssueHData.getMatPatRid() != null) {
            materialIssueH.setMatPatRid(materialIssueHData.getMatPatRid());
        }
        if (materialIssueHData.getMatPatName() != null) {
            materialIssueH.setMatPatName(materialIssueHData.getMatPatName());
        }
        if (materialIssueHData.getMatIssueDate() != null) {
            materialIssueH.setMatIssueDate(DateUtil.convertDateToString(materialIssueHData.getMatIssueDate()));
        }
        if (materialIssueHData.getMatIssueQty() != null) {
            materialIssueH.setMatIssueQty(materialIssueHData.getMatIssueQty());
        }
        if (materialIssueHData.getMatState() != null) {
            materialIssueH.setMatState(materialIssueHData.getMatState());
        }
        if (materialIssueHData.getMatStatus() != null) {
            materialIssueH.setMatStatus(materialIssueHData.getMatStatus());
        }
        if (materialIssueHData.getMatEntRid() != null) {
            materialIssueH.setMatEntRid(materialIssueHData.getMatEntRid());
        }
        if (materialIssueHData.getCreatedUserRid() != null) {
            materialIssueH.setCreatedUserRid(materialIssueHData.getCreatedUserRid());
        }
        if (materialIssueHData.getCreatedDateTime() != null) {
            materialIssueH.setCreatedDateTime(DateUtil.convertCalendarToString(materialIssueHData.getCreatedDateTime()));
        }
        if (materialIssueHData.getModifiedUserRid() != null) {
            materialIssueH.setModifiedUserRid(materialIssueHData.getModifiedUserRid());
        }
        if (materialIssueHData.getModifiedDateTime() != null) {
            materialIssueH.setModifiedDateTime(DateUtil.convertCalendarToString(materialIssueHData.getModifiedDateTime()));
        }

        return materialIssueH;
    }

    public MaterialIssueHData convertMaterialIssueHToMaterialIssueHData(MaterialIssueH materialIssueH) throws DcometServiceException {
        MaterialIssueHData materialIssueHData = new MaterialIssueHData();

        if (materialIssueH.getId() != null) {
            materialIssueHData.setId(materialIssueH.getId());
        }
        if (materialIssueH.getId() != null) {
            materialIssueHData.setId(materialIssueH.getId());
        }
        if (materialIssueH.getMatIssueNo() != null) {
            materialIssueHData.setMatIssueNo(materialIssueH.getMatIssueNo());
        }
        if (materialIssueH.getMatIssuePrefix() != null) {
            materialIssueHData.setMatIssuePrefix(materialIssueH.getMatIssuePrefix());
        }
        if (materialIssueH.getMatIssueSequence() != null) {
            materialIssueHData.setMatIssueSequence(materialIssueH.getMatIssueSequence());
        }
        if (materialIssueH.getMatVisitRid() != null) {
            materialIssueHData.setMatVisitRid(materialIssueH.getMatVisitRid());
        }
        if (materialIssueH.getMatPatRid() != null) {
            materialIssueHData.setMatPatRid(materialIssueH.getMatPatRid());
        }
        if (materialIssueH.getMatPatName() != null) {
            materialIssueHData.setMatPatName(materialIssueH.getMatPatName());
        }
        if (materialIssueH.getMatIssueDate() != null) {
            materialIssueHData.setMatIssueDate(DateUtil.convertStringToDate(materialIssueH.getMatIssueDate()));
        }
        if (materialIssueH.getMatIssueQty() != null) {
            materialIssueHData.setMatIssueQty(materialIssueH.getMatIssueQty());
        }
        if (materialIssueH.getMatState() != null) {
            materialIssueHData.setMatState(materialIssueH.getMatState());
        }
        if (materialIssueH.getMatStatus() != null) {
            materialIssueHData.setMatStatus(materialIssueH.getMatStatus());
        }
        if (materialIssueH.getMatEntRid() != null) {
            materialIssueHData.setMatEntRid(materialIssueH.getMatEntRid());
        }
        if (materialIssueH.getCreatedUserRid() != null) {
            materialIssueHData.setCreatedUserRid(materialIssueH.getCreatedUserRid());
        }
        if (materialIssueH.getCreatedDateTime() != null) {
            materialIssueHData.setCreatedDateTime(DateUtil.convertStringToCalendar(materialIssueH.getCreatedDateTime()));
        }
        if (materialIssueHData.getModifiedUserRid() != null) {
            materialIssueHData.setModifiedUserRid(materialIssueHData.getModifiedUserRid());
        }
        if (materialIssueH.getModifiedDateTime() != null) {
            materialIssueHData.setModifiedDateTime(DateUtil.convertStringToCalendar(materialIssueH.getModifiedDateTime()));
        }
        return materialIssueHData;
    }

    public MaterialIssueD convertMaterialIssueDDataToMaterialIssueD(MaterialIssueDData materialIssueDData) throws DcometServiceException {
        MaterialIssueD materialIssueD = new MaterialIssueD();
        if (materialIssueDData.getId() != null) {
            materialIssueD.setId(materialIssueDData.getId());
        }
        if (materialIssueDData.getMatIssueHRid() != null) {
            materialIssueD.setMatIssueHRid(materialIssueDData.getMatIssueHRid());
        }
        if (materialIssueDData.getMatSkuRid() != null) {
            materialIssueD.setMatSkuRid(materialIssueDData.getMatSkuRid());
        }
        if (materialIssueDData.getMatItemName() != null) {
            materialIssueD.setMatItemName(materialIssueDData.getMatItemName());
        }
        if (materialIssueDData.getMatBatchNo() != null) {
            materialIssueD.setMatBatchNo(materialIssueDData.getMatBatchNo());
        }
        if (materialIssueDData.getMatExpDate() != null) {
            materialIssueD.setMatExpDate(materialIssueDData.getMatExpDate());
        }
        if (materialIssueDData.getMatOrderQty() != null) {
            materialIssueD.setMatOrderQty(materialIssueDData.getMatOrderQty());
        }
        if (materialIssueDData.getMatIssuedQty() != null) {
            materialIssueD.setMatIssuedQty(materialIssueDData.getMatIssuedQty());
        }
        if (materialIssueDData.getMatStkQty() != null) {
            materialIssueD.setMatStkQty(materialIssueDData.getMatStkQty());
        }
        if (materialIssueDData.getMatReturnQty() != null) {
            materialIssueD.setMatReturnQty(materialIssueDData.getMatReturnQty());
        }
        if (materialIssueDData.getMatRate() != null) {
            materialIssueD.setMatRate(materialIssueDData.getMatRate());
        }
        if (materialIssueDData.getMatGrossAmount() != null) {
            materialIssueD.setMatGrossAmount(materialIssueDData.getMatGrossAmount());
        }
        if (materialIssueDData.getMatDiscAmount() != null) {
            materialIssueD.setMatDiscAmount(materialIssueDData.getMatDiscAmount());
        }
        if (materialIssueDData.getMatTaxAmount() != null) {
            materialIssueD.setMatTaxAmount(materialIssueDData.getMatTaxAmount());
        }

        if (materialIssueDData.getMatNetAmount() != null) {
            materialIssueD.setMatNetAmount(materialIssueDData.getMatNetAmount());
        }
        return materialIssueD;
    }

    public MaterialIssueDData convertMaterialIssueDToMaterialIssueDData(MaterialIssueD materialIssueD) throws DcometServiceException {
        MaterialIssueDData materialIssueDData = new MaterialIssueDData();

        if (materialIssueD.getId() != null) {
            materialIssueDData.setId(materialIssueD.getId());
        }
        if (materialIssueD.getMatIssueHRid() != null) {
            materialIssueDData.setMatIssueHRid(materialIssueD.getMatIssueHRid());
        }
        if (materialIssueD.getMatSkuRid() != null) {
            materialIssueDData.setMatSkuRid(materialIssueD.getMatSkuRid());
        }
        if (materialIssueD.getMatItemName() != null) {
            materialIssueDData.setMatItemName(materialIssueD.getMatItemName());
        }
        if (materialIssueD.getMatBatchNo() != null) {
            materialIssueDData.setMatBatchNo(materialIssueD.getMatBatchNo());
        }
        if (materialIssueD.getMatExpDate() != null) {
            materialIssueDData.setMatExpDate(materialIssueD.getMatExpDate());
        }
        if (materialIssueD.getMatOrderQty() != null) {
            materialIssueDData.setMatOrderQty(materialIssueD.getMatOrderQty());
        }
        if (materialIssueD.getMatIssuedQty() != null) {
            materialIssueDData.setMatIssuedQty(materialIssueD.getMatIssuedQty());
        }
        if (materialIssueD.getMatStkQty() != null) {
            materialIssueDData.setMatStkQty(materialIssueD.getMatStkQty());
        }
        if (materialIssueD.getMatReturnQty() != null) {
            materialIssueDData.setMatReturnQty(materialIssueD.getMatReturnQty());
        }
        if (materialIssueD.getMatRate() != null) {
            materialIssueDData.setMatRate(materialIssueD.getMatRate());
        }
        if (materialIssueD.getMatGrossAmount() != null) {
            materialIssueDData.setMatGrossAmount(materialIssueD.getMatGrossAmount());
        }
        if (materialIssueD.getMatDiscAmount() != null) {
            materialIssueDData.setMatDiscAmount(materialIssueD.getMatDiscAmount());
        }
        if (materialIssueD.getMatTaxAmount() != null) {
            materialIssueDData.setMatTaxAmount(materialIssueD.getMatTaxAmount());
        }
        if (materialIssueD.getMatNetAmount() != null) {
            materialIssueDData.setMatNetAmount(materialIssueD.getMatNetAmount());
        }
        return materialIssueDData;
    }
}
