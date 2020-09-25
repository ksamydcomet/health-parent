package com.dcomet.health.adapter;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.ServiceOrderData;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.ServiceOrderDData;
import java.util.ArrayList;
import java.util.List;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.domain.ServiceOrderD;
import org.springframework.stereotype.Component;

@Component("serviceOrderAdapter")
public class ServiceOrderAdapter {

    //---------------------ServiceOrder----------------------
    public List<ServiceOrder> convertServiceOrderDataToServiceOrder(
            List<ServiceOrderData> serviceOrderDataList) throws DcometServiceException {
        List<ServiceOrder> serviceOrderList = new ArrayList<>();
        for (ServiceOrderData serviceOrderData : serviceOrderDataList) {
            serviceOrderList.add(convertServiceOrderDataToServiceOrder(serviceOrderData));
        }
        return serviceOrderList;
    }

    public List<ServiceOrderData> convertServiceOrderToServiceOrderData(List<ServiceOrder> serviceOrderList)
            throws DcometServiceException {
        List<ServiceOrderData> serviceOrderDataList = new ArrayList<>();
        for (ServiceOrder serviceOrder : serviceOrderList) {
            serviceOrderDataList.add(convertServiceOrderToServiceOrderData(serviceOrder));
        }
        return serviceOrderDataList;
    }

    public ServiceOrder convertServiceOrderDataToServiceOrder(ServiceOrderData serviceOrderData)
            throws DcometServiceException {
        ServiceOrder serviceOrder = new ServiceOrder();
        if (serviceOrderData.getId() != null) {
            serviceOrder.setId(serviceOrderData.getId());
        }
        if (serviceOrderData.getSoAgainstUnitRID() != null) {
            serviceOrder.setSoAgainstUnitRID(serviceOrderData.getSoAgainstUnitRID());
        }
        if (serviceOrderData.getSoPatientRID() != null) {
            serviceOrder.setSoPatientRID(serviceOrderData.getSoPatientRID());
        }
        if (serviceOrderData.getSoVisitRID() != null) {
            serviceOrder.setSoVisitRID(serviceOrderData.getSoVisitRID());
        }
        if (serviceOrderData.getSoOrderingUnitRID() != null) {
            serviceOrder.setSoOrderingUnitRID(serviceOrderData.getSoOrderingUnitRID());
        }
        if (serviceOrderData.getSoOrderNo() != null) {
            serviceOrder.setSoOrderNo(serviceOrderData.getSoOrderNo());
        }
        if (serviceOrderData.getSoOrderType() != null) {
            serviceOrder.setSoOrderType(serviceOrderData.getSoOrderType());
        }
        if (serviceOrderData.getSoItemID() != null) {
            serviceOrder.setSoItemID(serviceOrderData.getSoItemID());
        }
        if (serviceOrderData.getSoItemName() != null) {
            serviceOrder.setSoItemName(serviceOrderData.getSoItemName());
        }
        if (serviceOrderData.getSoStartDate() != null) {
            serviceOrder.setSoStartDate(DateUtil.convertCalendarToString(serviceOrderData.getSoStartDate()));
        }
        if (serviceOrderData.getSoQty() != null) {
            serviceOrder.setSoQty(serviceOrderData.getSoQty());
        }
        if (serviceOrderData.getSoEntityRID() != null) {
            serviceOrder.setEntityRid(serviceOrderData.getSoEntityRID());
        }
        if (serviceOrderData.getSoStatus() != null) {
            serviceOrder.setSoStatus(serviceOrderData.getSoStatus());
        }
        if (serviceOrderData.getSoStatusModifiedDateTime() != null) {
            serviceOrder.setSoStatusModifiedDateTime(DateUtil.convertCalendarToString(serviceOrderData.getSoStatusModifiedDateTime()));
        }
        if (serviceOrderData.getSoProcessingUnitRID() != null) {
            serviceOrder.setSoProcessingUnitRID(serviceOrderData.getSoProcessingUnitRID());
        }
        if (serviceOrderData.getSoAttndDocRID() != null) {
            serviceOrder.setSoAttndDocRID(serviceOrderData.getSoAttndDocRID());
        }
        if (serviceOrderData.getSoOrderDocRID() != null) {
            serviceOrder.setSoOrderDocRID(serviceOrderData.getSoOrderDocRID());
        }
        if (serviceOrderData.getSoRecodedUserRID() != null) {
            serviceOrder.setSoRecodedUserRID(serviceOrderData.getSoRecodedUserRID());
        }
        if (serviceOrderData.getSoProcessedBY() != null) {
            serviceOrder.setSoProcessedBY(serviceOrderData.getSoProcessedBY());
        }
        if (serviceOrderData.getSoProcessedDate() != null) {
            serviceOrder.setSoProcessedDate(DateUtil.convertCalendarToString(serviceOrderData.getSoProcessedDate()));
        }
        if (serviceOrderData.getSoProcessingComments() != null) {
            serviceOrder.setSoProcessingComments(serviceOrderData.getSoProcessingComments());
        }
        if (serviceOrderData.getSoResultRID() != null) {
            serviceOrder.setSoResultRID(serviceOrderData.getSoResultRID());
        }
        if (serviceOrderData.getSoResultDateTime() != null) {
            serviceOrder.setSoResultDateTime(DateUtil.convertCalendarToString(serviceOrderData.getSoResultDateTime()));
        }
        if (serviceOrderData.getSoScheduleRID() != null) {
            serviceOrder.setSoScheduleRID(serviceOrderData.getSoScheduleRID());
        }
        if (serviceOrderData.getSoSerMode() != null) {
            serviceOrder.setSoSerMode(serviceOrderData.getSoSerMode());
        }
        if (serviceOrderData.getSoCancellationReason() != null) {
            serviceOrder.setSoCancellationReason(serviceOrderData.getSoCancellationReason());
        }
        if (serviceOrderData.getSoRemarks() != null) {
            serviceOrder.setSoRemarks(serviceOrderData.getSoRemarks());
        }
        if (serviceOrderData.getSoItemCategory() != null) {
            serviceOrder.setSoItemCategory(serviceOrderData.getSoItemCategory());
        }
        if (serviceOrderData.getSoParentRID() != null) {
            serviceOrder.setSoParentRID(serviceOrderData.getSoParentRID());
        }
        if (serviceOrderData.getSoRootParentID() != null) {
            serviceOrder.setSoRootParentID(serviceOrderData.getSoRootParentID());
        }
        if (serviceOrderData.getSoSignedUserRID() != null) {
            serviceOrder.setSoSignedUserRID(serviceOrderData.getSoSignedUserRID());
        }
        if (serviceOrderData.getSoCoSignedUserRID() != null) {
            serviceOrder.setSoCoSignedUserRID(serviceOrderData.getSoCoSignedUserRID());
        }
        if (serviceOrderData.getSoSignedDateTime() != null) {
            serviceOrder.setSoSignedDateTime(DateUtil.convertCalendarToString(serviceOrderData.getSoSignedDateTime()));
        }
        if (serviceOrderData.getSoRecurrenceID() != null) {
            serviceOrder.setSoRecurrenceID(serviceOrderData.getSoRecurrenceID());
        }
        if (serviceOrderData.getSoRecurrenceSpan() != null) {
            serviceOrder.setSoRecurrenceSpan(serviceOrderData.getSoRecurrenceSpan());
        }
        if (serviceOrderData.getSoOccurenceTotal() != null) {
            serviceOrder.setSoOccurenceTotal(serviceOrderData.getSoOccurenceTotal());
        }
        if (serviceOrderData.getSoOccurenceGenerated() != null) {
            serviceOrder.setSoOccurenceGenerated(serviceOrderData.getSoOccurenceGenerated());
        }
        if (serviceOrderData.getSoRecurrenceText() != null) {
            serviceOrder.setSoRecurrenceText(serviceOrderData.getSoRecurrenceText());
        }
        if (serviceOrderData.getSoMajorProcedureStatus() != null) {
            serviceOrder.setSoMajorProcedureStatus(serviceOrderData.getSoMajorProcedureStatus());
        }
        if (serviceOrderData.getSoServicePoint() != null) {
            serviceOrder.setSoServicePoint(serviceOrderData.getSoServicePoint());
        }
        if (serviceOrderData.getSoState() != null) {
            serviceOrder.setSoState(serviceOrderData.getSoState());
        }
        if (serviceOrderData.getSoAdviceUserRID() != null) {
            serviceOrder.setSoAdviceUserRID(serviceOrderData.getSoAdviceUserRID());
        }
        if (serviceOrderData.getSoAdviceDateTime() != null) {
            serviceOrder.setSoAdviceDateTime(DateUtil.convertCalendarToString(serviceOrderData.getSoAdviceDateTime()));
        }
        if (serviceOrderData.getSoConvertedUserRID() != null) {
            serviceOrder.setSoConvertedUserRID(serviceOrderData.getSoConvertedUserRID());
        }
        if (serviceOrderData.getSoConvertedDateTime() != null) {
            serviceOrder.setSoConvertedDateTime(DateUtil.convertCalendarToString(serviceOrderData.getSoConvertedDateTime()));
        }
        if (serviceOrderData.getCreatedUserRid() != null) {
            serviceOrder.setCreatedUserRid(serviceOrderData.getCreatedUserRid());
        }
        if (serviceOrderData.getCreatedDateTime() != null) {
            serviceOrder.setCreatedDateTime(DateUtil.convertCalendarToString(serviceOrderData.getCreatedDateTime()));
        }
        if (serviceOrderData.getModifiedUserRid() != null) {
            serviceOrder.setModifiedUserRid(serviceOrderData.getModifiedUserRid());
        }
        if (serviceOrderData.getModifiedDateTime() != null) {
            serviceOrder.setModifiedDateTime(DateUtil.convertCalendarToString(serviceOrderData.getModifiedDateTime()));
        }

        return serviceOrder;
    }

    public ServiceOrderData convertServiceOrderToServiceOrderData(ServiceOrder serviceOrder)
            throws DcometServiceException {
        ServiceOrderData serviceOrderData = new ServiceOrderData();
        if (serviceOrder.getId() != null) {
            serviceOrderData.setId(serviceOrder.getId());
        }
        if (serviceOrder.getSoAgainstUnitRID() != null) {
            serviceOrderData.setSoAgainstUnitRID(serviceOrder.getSoAgainstUnitRID());
        }
        if (serviceOrder.getSoPatientRID() != null) {
            serviceOrderData.setSoPatientRID(serviceOrder.getSoPatientRID());
        }
        if (serviceOrder.getSoVisitRID() != null) {
            serviceOrderData.setSoVisitRID(serviceOrder.getSoVisitRID());
        }
        if (serviceOrder.getSoOrderingUnitRID() != null) {
            serviceOrderData.setSoOrderingUnitRID(serviceOrder.getSoOrderingUnitRID());
        }
        if (serviceOrder.getSoOrderNo() != null) {
            serviceOrderData.setSoOrderNo(serviceOrder.getSoOrderNo());
        }
        if (serviceOrder.getSoOrderType() != null) {
            serviceOrderData.setSoOrderType(serviceOrder.getSoOrderType());
        }
        if (serviceOrder.getSoItemID() != null) {
            serviceOrderData.setSoItemID(serviceOrder.getSoItemID());
        }
        if (serviceOrder.getSoItemName() != null) {
            serviceOrderData.setSoItemName(serviceOrder.getSoItemName());
        }
        if (serviceOrder.getSoStartDate() != null) {
            serviceOrderData.setSoStartDate(DateUtil.convertStringToCalendar(serviceOrder.getSoStartDate()));
        }
        if (serviceOrder.getSoQty() != null) {
            serviceOrderData.setSoQty(serviceOrder.getSoQty());
        }
        if (serviceOrder.getEntityRid() != null) {
            serviceOrderData.setSoEntityRID(serviceOrder.getEntityRid());
        }
        if (serviceOrder.getSoStatus() != null) {
            serviceOrderData.setSoStatus(serviceOrder.getSoStatus());
        }
        if (serviceOrder.getSoStatusModifiedDateTime() != null) {
            serviceOrderData.setSoStatusModifiedDateTime(DateUtil.convertStringToCalendar(serviceOrder.getSoStatusModifiedDateTime()));
        }
        if (serviceOrder.getSoProcessingUnitRID() != null) {
            serviceOrderData.setSoProcessingUnitRID(serviceOrder.getSoProcessingUnitRID());
        }
        if (serviceOrder.getSoAttndDocRID() != null) {
            serviceOrderData.setSoAttndDocRID(serviceOrder.getSoAttndDocRID());
        }
        if (serviceOrder.getSoOrderDocRID() != null) {
            serviceOrderData.setSoOrderDocRID(serviceOrder.getSoOrderDocRID());
        }
        if (serviceOrder.getSoRecodedUserRID() != null) {
            serviceOrderData.setSoRecodedUserRID(serviceOrder.getSoRecodedUserRID());
        }
        if (serviceOrder.getSoProcessedBY() != null) {
            serviceOrderData.setSoProcessedBY(serviceOrder.getSoProcessedBY());
        }
        if (serviceOrder.getSoProcessedDate() != null) {
            serviceOrderData.setSoProcessedDate(DateUtil.convertStringToCalendar(serviceOrder.getSoProcessedDate()));
        }
        if (serviceOrder.getSoProcessingComments() != null) {
            serviceOrderData.setSoProcessingComments(serviceOrder.getSoProcessingComments());
        }
        if (serviceOrder.getSoResultRID() != null) {
            serviceOrderData.setSoResultRID(serviceOrder.getSoResultRID());
        }
        if (serviceOrder.getSoResultDateTime() != null) {
            serviceOrderData.setSoResultDateTime(DateUtil.convertStringToCalendar(serviceOrder.getSoResultDateTime()));
        }
        if (serviceOrder.getSoScheduleRID() != null) {
            serviceOrderData.setSoScheduleRID(serviceOrder.getSoScheduleRID());
        }
        if (serviceOrder.getSoSerMode() != null) {
            serviceOrderData.setSoSerMode(serviceOrder.getSoSerMode());
        }
        if (serviceOrder.getSoCancellationReason() != null) {
            serviceOrderData.setSoCancellationReason(serviceOrder.getSoCancellationReason());
        }
        if (serviceOrder.getSoRemarks() != null) {
            serviceOrderData.setSoRemarks(serviceOrder.getSoRemarks());
        }
        if (serviceOrder.getSoItemCategory() != null) {
            serviceOrderData.setSoItemCategory(serviceOrder.getSoItemCategory());
        }
        if (serviceOrder.getSoParentRID() != null) {
            serviceOrderData.setSoParentRID(serviceOrder.getSoParentRID());
        }
        if (serviceOrder.getSoRootParentID() != null) {
            serviceOrderData.setSoRootParentID(serviceOrder.getSoRootParentID());
        }
        if (serviceOrder.getSoSignedUserRID() != null) {
            serviceOrderData.setSoSignedUserRID(serviceOrder.getSoSignedUserRID());
        }
        if (serviceOrder.getSoCoSignedUserRID() != null) {
            serviceOrderData.setSoCoSignedUserRID(serviceOrder.getSoCoSignedUserRID());
        }
        if (serviceOrder.getSoSignedDateTime() != null) {
            serviceOrderData.setSoSignedDateTime(DateUtil.convertStringToCalendar(serviceOrder.getSoSignedDateTime()));
        }
        if (serviceOrder.getSoRecurrenceID() != null) {
            serviceOrderData.setSoRecurrenceID(serviceOrder.getSoRecurrenceID());
        }
        if (serviceOrder.getSoRecurrenceSpan() != null) {
            serviceOrderData.setSoRecurrenceSpan(serviceOrder.getSoRecurrenceSpan());
        }
        if (serviceOrder.getSoOccurenceTotal() != null) {
            serviceOrderData.setSoOccurenceTotal(serviceOrder.getSoOccurenceTotal());
        }
        if (serviceOrder.getSoOccurenceGenerated() != null) {
            serviceOrderData.setSoOccurenceGenerated(serviceOrder.getSoOccurenceGenerated());
        }
        if (serviceOrder.getSoRecurrenceText() != null) {
            serviceOrderData.setSoRecurrenceText(serviceOrder.getSoRecurrenceText());
        }
        if (serviceOrder.getSoMajorProcedureStatus() != null) {
            serviceOrderData.setSoMajorProcedureStatus(serviceOrder.getSoMajorProcedureStatus());
        }
        if (serviceOrder.getSoServicePoint() != null) {
            serviceOrderData.setSoServicePoint(serviceOrder.getSoServicePoint());
        }
        if (serviceOrder.getSoState() != null) {
            serviceOrderData.setSoState(serviceOrder.getSoState());
        }
        if (serviceOrder.getSoAdviceUserRID() != null) {
            serviceOrderData.setSoAdviceUserRID(serviceOrder.getSoAdviceUserRID());
        }
        if (serviceOrder.getSoAdviceDateTime() != null) {
            serviceOrderData.setSoAdviceDateTime(DateUtil.convertStringToCalendar(serviceOrder.getSoAdviceDateTime()));
        }
        if (serviceOrder.getSoConvertedUserRID() != null) {
            serviceOrderData.setSoConvertedUserRID(serviceOrder.getSoConvertedUserRID());
        }
        if (serviceOrder.getSoConvertedDateTime() != null) {
            serviceOrderData.setSoConvertedDateTime(DateUtil.convertStringToCalendar(serviceOrder.getSoConvertedDateTime()));
        }
        if (serviceOrder.getCreatedUserRid() != null) {
            serviceOrderData.setCreatedUserRid(serviceOrder.getCreatedUserRid());
        }
        if (serviceOrder.getModifiedUserRid() != null) {
            serviceOrderData.setModifiedUserRid(serviceOrder.getModifiedUserRid());
        }

        return serviceOrderData;
    }

    //---------------------ServiceOrderD----------------------
    public List<ServiceOrderD> convertServiceOrderDDataToServiceOrderD(
            List<ServiceOrderDData> serviceOrderDDataList) throws DcometServiceException {
        List<ServiceOrderD> serviceOrderDList = new ArrayList<>();
        for (ServiceOrderDData serviceOrderDData : serviceOrderDDataList) {
            serviceOrderDList.add(convertServiceOrderDDataToServiceOrderD(serviceOrderDData));
        }
        return serviceOrderDList;
    }

    public List<ServiceOrderDData> convertServiceOrderDToServiceOrderDData(List<ServiceOrderD> serviceOrderDList) throws DcometServiceException {
        List<ServiceOrderDData> serviceOrderDDataList = new ArrayList<>();
        for (ServiceOrderD serviceOrderD : serviceOrderDList) {
            serviceOrderDDataList.add(convertServiceOrderDToServiceOrderDData(serviceOrderD));
        }
        return serviceOrderDDataList;
    }

    public ServiceOrderD convertServiceOrderDDataToServiceOrderD(ServiceOrderDData serviceOrderDData)
            throws DcometServiceException {
        ServiceOrderD serviceOrderD = new ServiceOrderD();
        if (serviceOrderDData.getId() != null) {
            serviceOrderD.setId(serviceOrderDData.getId());
        }
        if (serviceOrderDData.getSodSoRid() != null) {
            serviceOrderD.setSodSoRid(serviceOrderDData.getSodSoRid());
        }
        if (serviceOrderDData.getSodVisRid() != null) {
            serviceOrderD.setSodVisRid(serviceOrderDData.getSodVisRid());
        }
        if (serviceOrderDData.getSodPatRid() != null) {
            serviceOrderD.setSodPatRid(serviceOrderDData.getSodPatRid());
        }
        if (serviceOrderDData.getSodNodes() != null) {
            serviceOrderD.setSodNodes(serviceOrderDData.getSodNodes());
        }
        return serviceOrderD;
    }

    public ServiceOrderDData convertServiceOrderDToServiceOrderDData(ServiceOrderD serviceOrderD)
            throws DcometServiceException {
        ServiceOrderDData serviceOrderDData = new ServiceOrderDData();
        if (serviceOrderD.getId() != null) {
            serviceOrderDData.setId(serviceOrderD.getId());
        }
        if (serviceOrderD.getSodSoRid() != null) {
            serviceOrderDData.setSodSoRid(serviceOrderD.getSodSoRid());
        }
        if (serviceOrderD.getSodVisRid() != null) {
            serviceOrderDData.setSodVisRid(serviceOrderD.getSodVisRid());
        }
        if (serviceOrderD.getSodPatRid() != null) {
            serviceOrderDData.setSodPatRid(serviceOrderD.getSodPatRid());
        }
        if (serviceOrderD.getSodNodes() != null) {
            serviceOrderDData.setSodNodes(serviceOrderD.getSodNodes());
        }
        return serviceOrderDData;
    }
}
