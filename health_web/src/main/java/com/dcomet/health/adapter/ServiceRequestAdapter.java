package com.dcomet.health.adapter;

import com.dcomet.fw.adapter.BaseAdapter;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.FavouriteServiceOrderDData;
import com.dcomet.health.dao.data.FavouriteServiceOrderHData;
import com.dcomet.health.dao.data.ServiceRequestData;
import com.dcomet.health.domain.FavouriteServiceOrderD;
import com.dcomet.health.domain.FavouriteServiceOrderH;
import com.dcomet.health.domain.ServiceRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.ServiceRequestHData;
import com.dcomet.health.domain.ServiceRequestH;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev4
 */
@Component("serviceRequestAdapter")
public class ServiceRequestAdapter extends BaseAdapter {

    public List<FavouriteServiceOrderD> convertFavouriteServiceOrderDDataToFavouriteServiceOrderD(
            List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList) throws DcometServiceException {
        List<FavouriteServiceOrderD> favouriteServiceOrderDList = new ArrayList<>();
        for (FavouriteServiceOrderDData favouriteServiceOrderDData : favouriteServiceOrderDDataList) {
            favouriteServiceOrderDList.add(convertFavouriteServiceOrderDDataToFavouriteServiceOrderD(favouriteServiceOrderDData));
        }
        return favouriteServiceOrderDList;
    }

    public List<FavouriteServiceOrderDData> convertFavouriteServiceOrderDToFavouriteServiceOrderDData(
            List<FavouriteServiceOrderD> favouriteServiceOrderDList) throws DcometServiceException {
        List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList = new ArrayList<>();
        for (FavouriteServiceOrderD favouriteServiceOrderD : favouriteServiceOrderDList) {
            favouriteServiceOrderDDataList.add(convertFavouriteServiceOrderDToFavouriteServiceOrderDData(favouriteServiceOrderD));
        }
        return favouriteServiceOrderDDataList;
    }

    public FavouriteServiceOrderD convertFavouriteServiceOrderDDataToFavouriteServiceOrderD(FavouriteServiceOrderDData favouriteServiceOrderDData)
            throws DcometServiceException {
        FavouriteServiceOrderD favouriteServiceOrderD = new FavouriteServiceOrderD();
        if (favouriteServiceOrderDData.getId() != null) {
            favouriteServiceOrderD.setId(favouriteServiceOrderDData.getId());
        }
        if (favouriteServiceOrderDData.getFsodFsohRID() != null) {
            favouriteServiceOrderD.setFsodFsohRID(favouriteServiceOrderDData.getFsodFsohRID());
        }
        if (favouriteServiceOrderDData.getFsodServiceRID() != null) {
            favouriteServiceOrderD.setFsodServiceRID(favouriteServiceOrderDData.getFsodServiceRID());
        }
        if (favouriteServiceOrderDData.getFsodLastOrderDateTime() != null) {
            favouriteServiceOrderD.setFsodLastOrderDateTime(DateUtil.convertCalendarToString(favouriteServiceOrderDData.getFsodLastOrderDateTime()));
        }
//        if (favouriteServiceOrderDData.getFsodServiceNode() != null) {
//            favouriteServiceOrderD.setFsodServiceNode(favouriteServiceOrderDData.getFsodServiceNode());
//        }
        return favouriteServiceOrderD;
    }

    public FavouriteServiceOrderDData convertFavouriteServiceOrderDToFavouriteServiceOrderDData(FavouriteServiceOrderD favouriteServiceOrderD)
            throws DcometServiceException {
        FavouriteServiceOrderDData favouriteServiceOrderDData = new FavouriteServiceOrderDData();
        if (favouriteServiceOrderD.getId() != null) {
            favouriteServiceOrderDData.setId(favouriteServiceOrderD.getId());
        }
        if (favouriteServiceOrderD.getFsodFsohRID() != null) {
            favouriteServiceOrderDData.setFsodFsohRID(favouriteServiceOrderD.getFsodFsohRID());
        }
        if (favouriteServiceOrderD.getFsodServiceRID() != null) {
            favouriteServiceOrderDData.setFsodServiceRID(favouriteServiceOrderD.getFsodServiceRID());
        }
//        if (favouriteServiceOrderD.getFsodServiceNode() != null) {
//            favouriteServiceOrderDData.setFsodServiceNode(favouriteServiceOrderD.getFsodServiceNode());
//        }
        if (favouriteServiceOrderD.getFsodLastOrderDateTime() != null) {
            favouriteServiceOrderDData.setFsodLastOrderDateTime(DateUtil.convertStringToCalendar(favouriteServiceOrderD.getFsodLastOrderDateTime()));
        }
        return favouriteServiceOrderDData;

    }

    public List<FavouriteServiceOrderH> convertFavouriteServiceOrderHDataToFavouriteServiceOrderH(
            List<FavouriteServiceOrderHData> favouriteServiceOrderHDataList) throws DcometServiceException {
        List<FavouriteServiceOrderH> favouriteServiceOrderHList = new ArrayList<>();
        for (FavouriteServiceOrderHData favouriteServiceOrderHData : favouriteServiceOrderHDataList) {
            favouriteServiceOrderHList.add(convertFavouriteServiceOrderHDataToFavouriteServiceOrderH(favouriteServiceOrderHData));
        }
        return favouriteServiceOrderHList;
    }

    public List<FavouriteServiceOrderHData> convertFavouriteServiceOrderHToFavouriteServiceOrderHData(
            List<FavouriteServiceOrderH> favouriteServiceOrderHList) throws DcometServiceException {
        List<FavouriteServiceOrderHData> favouriteServiceOrderHDataList = new ArrayList<>();
        for (FavouriteServiceOrderH favouriteServiceOrderH : favouriteServiceOrderHList) {
            favouriteServiceOrderHDataList.add(convertFavouriteServiceOrderHToFavouriteServiceOrderHData(favouriteServiceOrderH));
        }
        return favouriteServiceOrderHDataList;
    }

    public FavouriteServiceOrderH convertFavouriteServiceOrderHDataToFavouriteServiceOrderH(FavouriteServiceOrderHData favouriteServiceOrderHData)
            throws DcometServiceException {
        FavouriteServiceOrderH favouriteServiceOrderH = new FavouriteServiceOrderH();
        if (favouriteServiceOrderHData.getId() != null) {
            favouriteServiceOrderH.setId(favouriteServiceOrderHData.getId());
        }
        if (favouriteServiceOrderHData.getFsohUserRID() != null) {
            favouriteServiceOrderH.setFsohUserRID(favouriteServiceOrderHData.getFsohUserRID());
        }
        if (favouriteServiceOrderHData.getFsohName() != null) {
            favouriteServiceOrderH.setFsohName(favouriteServiceOrderHData.getFsohName());
        }
        if (favouriteServiceOrderHData.getFsohEntityRID() != null) {
            favouriteServiceOrderH.setEntityRid(favouriteServiceOrderHData.getFsohEntityRID());
        }
        if (favouriteServiceOrderHData.getCreatedDateTime() != null) {
            favouriteServiceOrderH.setCreatedDateTime(DateUtil.convertCalendarToString(favouriteServiceOrderHData.getCreatedDateTime()));
        }
        if (favouriteServiceOrderHData.getCreatedUserRid() != null) {
            favouriteServiceOrderH.setCreatedUserRid(favouriteServiceOrderHData.getCreatedUserRid());
        }
        if (favouriteServiceOrderHData.getModifiedDateTime() != null) {
            favouriteServiceOrderH.setModifiedDateTime(DateUtil.convertCalendarToString(favouriteServiceOrderHData.getModifiedDateTime()));
        }
        if (favouriteServiceOrderHData.getModifiedUserRid() != null) {
            favouriteServiceOrderH.setModifiedUserRid(favouriteServiceOrderHData.getModifiedUserRid());
        }
        return favouriteServiceOrderH;
    }

    public FavouriteServiceOrderHData convertFavouriteServiceOrderHToFavouriteServiceOrderHData(FavouriteServiceOrderH favouriteServiceOrderH)
            throws DcometServiceException {
        FavouriteServiceOrderHData favouriteServiceOrderHData = new FavouriteServiceOrderHData();
        if (favouriteServiceOrderH.getId() != null) {
            favouriteServiceOrderHData.setId(favouriteServiceOrderH.getId());
        }
        if (favouriteServiceOrderH.getFsohUserRID() != null) {
            favouriteServiceOrderHData.setFsohUserRID(favouriteServiceOrderH.getFsohUserRID());
        }
        if (favouriteServiceOrderH.getFsohName() != null) {
            favouriteServiceOrderHData.setFsohName(favouriteServiceOrderH.getFsohName());
        }
        if (favouriteServiceOrderH.getEntityRid() != null) {
            favouriteServiceOrderHData.setFsohEntityRID(favouriteServiceOrderH.getEntityRid());
        }

        if (favouriteServiceOrderH.getCreatedUserRid() != null) {
            favouriteServiceOrderHData.setCreatedUserRid(favouriteServiceOrderH.getCreatedUserRid());
        }
        if (favouriteServiceOrderH.getModifiedUserRid() != null) {
            favouriteServiceOrderHData.setModifiedUserRid(favouriteServiceOrderH.getModifiedUserRid());
        }

        return favouriteServiceOrderHData;
    }

    //-----------------------------ServiceRequest-------------------------
    public List<ServiceRequestH> convertServiceRequestHDataToServiceRequestH(
            List<ServiceRequestHData> serviceRequestHDataList) throws DcometServiceException {
        List<ServiceRequestH> serviceRequestHList = new ArrayList<>();
        for (ServiceRequestHData serviceRequestHData : serviceRequestHDataList) {
            serviceRequestHList.add(convertServiceRequestHDataToServiceRequestH(serviceRequestHData));
        }
        return serviceRequestHList;
    }

    public List<ServiceRequestHData> convertServiceRequestHToServiceRequestHData(
            List<ServiceRequestH> serviceRequestHList) throws DcometServiceException {
        List<ServiceRequestHData> serviceRequestHDataList = new ArrayList<>();
        for (ServiceRequestH serviceRequestH : serviceRequestHList) {
            serviceRequestHDataList.add(convertServiceRequestHToServiceRequestHData(serviceRequestH));
        }
        return serviceRequestHDataList;
    }

    public ServiceRequestH convertServiceRequestHDataToServiceRequestH(ServiceRequestHData serviceRequestHData)
            throws DcometServiceException {
        ServiceRequestH serviceRequestH = new ServiceRequestH();
        if (serviceRequestHData.getSerReqhId() != null) {
            serviceRequestH.setSerReqhId(serviceRequestHData.getSerReqhId());
        }
        if (serviceRequestHData.getSerReqhNo() != null) {
            serviceRequestH.setSerReqhNo(serviceRequestHData.getSerReqhNo());
        }
        if (serviceRequestHData.getSerReqhPrefix() != null) {
            serviceRequestH.setSerReqhPrefix(serviceRequestHData.getSerReqhPrefix());
        }
        if (serviceRequestHData.getSerReqhSequence() != null) {
            serviceRequestH.setSerReqhSequence(serviceRequestHData.getSerReqhSequence());
        }
        if (serviceRequestHData.getSerReqOpVisitRid() != null) {
            serviceRequestH.setSerReqOpVisitRid(serviceRequestHData.getSerReqOpVisitRid());
        }
        if (serviceRequestHData.getSerReqhPatRid() != null) {
            serviceRequestH.setSerReqhPatRid(serviceRequestHData.getSerReqhPatRid());
        }
        if (serviceRequestHData.getSerReqhPatMrn() != null) {
            serviceRequestH.setSerReqhPatMrn(serviceRequestHData.getSerReqhPatMrn());
        }
        if (serviceRequestHData.getSerReqhPatName() != null) {
            serviceRequestH.setSerReqhPatName(serviceRequestHData.getSerReqhPatName());
        }
        if (serviceRequestHData.getSerReqhBillHRid() != null) {
            serviceRequestH.setSerReqhBillHRid(serviceRequestHData.getSerReqhBillHRid());
        }
        if (serviceRequestHData.getSerReqhBillDRid() != null) {
            serviceRequestH.setSerReqhBillDRid(serviceRequestHData.getSerReqhBillDRid());
        }
        if (serviceRequestHData.getSerReqhLabEnHRid() != null) {
            serviceRequestH.setSerReqhLabEnHRid(serviceRequestHData.getSerReqhLabEnHRid());
        }
        if (serviceRequestHData.getSerReqhOpCheck() != null) {
            serviceRequestH.setSerReqhOpCheck(serviceRequestHData.getSerReqhOpCheck());
        }
        if (serviceRequestHData.getSerReqhProcedureRid() != null) {
            serviceRequestH.setSerReqhProcedureRid(serviceRequestHData.getSerReqhProcedureRid());
        }
        if (serviceRequestHData.getSerReqhState() != null) {
            serviceRequestH.setSerReqhState(serviceRequestHData.getSerReqhState());
        }
        if (serviceRequestHData.getSerReqhStatus() != null) {
            serviceRequestH.setSerReqhStatus(serviceRequestHData.getSerReqhStatus());
        }
        if (serviceRequestHData.getSerReqhProcessDate() != null) {
            serviceRequestH.setSerReqhProcessDate(DateUtil.convertCalendarToString(serviceRequestHData.getSerReqhProcessDate()));
        }
        if (serviceRequestHData.getSerReqhUnitRid() != null) {
            serviceRequestH.setSerReqhUnitRid(serviceRequestHData.getSerReqhUnitRid());
        }
        if (serviceRequestHData.getSerReqhEntityRid() != null) {
            serviceRequestH.setEntityRid(serviceRequestHData.getSerReqhEntityRid());
        }
        if (serviceRequestHData.getSerReqhCreatedUserRid() != null) {
            serviceRequestH.setCreatedUserRid(serviceRequestHData.getSerReqhCreatedUserRid());
        }
        if (serviceRequestHData.getSerReqhCreatedDatetime() != null) {
            serviceRequestH.setCreatedDateTime(DateUtil.convertCalendarToString(serviceRequestHData.getSerReqhCreatedDatetime()));
        }
        if (serviceRequestHData.getSerReqhModifiedUserRid() != null) {
            serviceRequestH.setModifiedUserRid(serviceRequestHData.getSerReqhModifiedUserRid());
        }
        if (serviceRequestHData.getSerReqhModifiedDatetime() != null) {
            serviceRequestH.setModifiedDateTime(DateUtil.convertCalendarToString(serviceRequestHData.getSerReqhModifiedDatetime()));
        }
        return serviceRequestH;
    }

    public ServiceRequestHData convertServiceRequestHToServiceRequestHData(ServiceRequestH serviceRequestH)
            throws DcometServiceException {
        ServiceRequestHData serviceRequestHData = new ServiceRequestHData();
        if (serviceRequestH.getSerReqhId() != null) {
            serviceRequestHData.setSerReqhId(serviceRequestH.getSerReqhId());
        }
        if (serviceRequestH.getSerReqhNo() != null) {
            serviceRequestHData.setSerReqhNo(serviceRequestH.getSerReqhNo());
        }
        if (serviceRequestH.getSerReqhPrefix() != null) {
            serviceRequestHData.setSerReqhPrefix(serviceRequestH.getSerReqhPrefix());
        }
        if (serviceRequestH.getSerReqhSequence() != null) {
            serviceRequestHData.setSerReqhSequence(serviceRequestH.getSerReqhSequence());
        }
        if (serviceRequestH.getSerReqOpVisitRid() != null) {
            serviceRequestHData.setSerReqOpVisitRid(serviceRequestH.getSerReqOpVisitRid());
        }
        if (serviceRequestH.getSerReqhPatRid() != null) {
            serviceRequestHData.setSerReqhPatRid(serviceRequestH.getSerReqhPatRid());
        }
        if (serviceRequestH.getSerReqhPatMrn() != null) {
            serviceRequestHData.setSerReqhPatMrn(serviceRequestH.getSerReqhPatMrn());
        }
        if (serviceRequestH.getSerReqhPatName() != null) {
            serviceRequestHData.setSerReqhPatName(serviceRequestH.getSerReqhPatName());
        }
        if (serviceRequestH.getSerReqhBillHRid() != null) {
            serviceRequestHData.setSerReqhBillHRid(serviceRequestH.getSerReqhBillHRid());
        }
        if (serviceRequestH.getSerReqhBillDRid() != null) {
            serviceRequestHData.setSerReqhBillDRid(serviceRequestH.getSerReqhBillDRid());
        }
        if (serviceRequestH.getSerReqhLabEnHRid() != null) {
            serviceRequestHData.setSerReqhLabEnHRid(serviceRequestH.getSerReqhLabEnHRid());
        }
        if (serviceRequestH.getSerReqhOpCheck() != null) {
            serviceRequestHData.setSerReqhOpCheck(serviceRequestH.getSerReqhOpCheck());
        }
        if (serviceRequestH.getSerReqhProcedureRid() != null) {
            serviceRequestHData.setSerReqhProcedureRid(serviceRequestH.getSerReqhProcedureRid());
        }
        if (serviceRequestH.getSerReqhState() != null) {
            serviceRequestHData.setSerReqhState(serviceRequestH.getSerReqhState());
        }
        if (serviceRequestH.getSerReqhStatus() != null) {
            serviceRequestHData.setSerReqhStatus(serviceRequestH.getSerReqhStatus());
        }
        if (serviceRequestH.getSerReqhUnitRid() != null) {
            serviceRequestHData.setSerReqhUnitRid(serviceRequestH.getSerReqhUnitRid());
        }
        if (serviceRequestH.getEntityRid() != null) {
            serviceRequestHData.setSerReqhEntityRid(serviceRequestH.getEntityRid());
        }
        if (serviceRequestH.getCreatedUserRid() != null) {
            serviceRequestHData.setSerReqhCreatedUserRid(serviceRequestH.getCreatedUserRid());
        }
        if (serviceRequestH.getCreatedDateTime() != null) {
            serviceRequestHData.setSerReqhCreatedDatetime(DateUtil.convertStringToCalendar(serviceRequestH.getCreatedDateTime()));
        }
        if (serviceRequestH.getModifiedUserRid() != null) {
            serviceRequestHData.setSerReqhModifiedUserRid(serviceRequestH.getModifiedUserRid());
        }
        if (serviceRequestH.getModifiedDateTime() != null) {
            serviceRequestHData.setSerReqhModifiedDatetime(DateUtil.convertStringToCalendar(serviceRequestH.getModifiedDateTime()));
        }
        return serviceRequestHData;
    }

    public List<ServiceRequest> convertServiceRequestDataToServiceRequest(
            List<ServiceRequestData> serviceRequestDataList) throws DcometServiceException {
        List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>();
        for (ServiceRequestData serviceRequestData : serviceRequestDataList) {
            serviceRequestList.add(convertServiceRequestDataToServiceRequest(serviceRequestData));
        }
        return serviceRequestList;
    }

    public List<ServiceRequestData> convertServiceRequestToServiceRequestData(List<ServiceRequest> serviceRequestList)
            throws DcometServiceException {
        List<ServiceRequestData> serviceRequestDataList = new ArrayList<ServiceRequestData>();
        for (ServiceRequest serviceRequest : serviceRequestList) {
            serviceRequestDataList.add(convertServiceRequestToServiceRequestData(serviceRequest));
        }
        return serviceRequestDataList;
    }

    public ServiceRequest convertServiceRequestDataToServiceRequest(ServiceRequestData serviceRequestData)
            throws DcometServiceException {
        ServiceRequest serviceRequest = new ServiceRequest();
        if (serviceRequestData.getSerReqRid() != null) {
            serviceRequest.setSerReqRid(serviceRequestData.getSerReqRid());
        }
        if (serviceRequestData.getSerReqhRid() != null) {
            serviceRequest.setSerReqhRid(serviceRequestData.getSerReqhRid());
        }
        if (serviceRequestData.getSerReqItemRID() != null) {
            serviceRequest.setSerReqItemRID(serviceRequestData.getSerReqItemRID());
        }
        if (serviceRequestData.getSerReqItemGroupRid() != null) {
            serviceRequest.setSerReqItemGroupRid(serviceRequestData.getSerReqItemGroupRid());
        }
        if (serviceRequestData.getSerReqServicePackageRid() != null) {
            serviceRequest.setSerReqServicePackageRid(serviceRequestData.getSerReqServicePackageRid());
        }
        if (serviceRequestData.getSerReqItemName() != null) {
            serviceRequest.setSerReqItemName(serviceRequestData.getSerReqItemName());
        }
        if (serviceRequestData.getSerReqItemPrice() != null) {
            serviceRequest.setSerReqItemPrice(serviceRequestData.getSerReqItemPrice());
        }
        if (serviceRequestData.getSerType() != null) {
            serviceRequest.setSerType(serviceRequestData.getSerType());
        }
        if (serviceRequestData.getSerReqItemQty() != null) {
            serviceRequest.setSerReqItemQty(serviceRequestData.getSerReqItemQty());
        }
        if (serviceRequestData.getSerReqMorning() != null) {
            serviceRequest.setSerReqMorning(serviceRequestData.getSerReqMorning());
        }
        if (serviceRequestData.getSerReqAfternoon() != null) {
            serviceRequest.setSerReqAfternoon(serviceRequestData.getSerReqAfternoon());
        }
        if (serviceRequestData.getSerReqEvening() != null) {
            serviceRequest.setSerReqEvening(serviceRequestData.getSerReqEvening());
        }
        if (serviceRequestData.getSerReqNight() != null) {
            serviceRequest.setSerReqNight(serviceRequestData.getSerReqNight());
        }
        if (serviceRequestData.getSerReqBillHRID() != null) {
            serviceRequest.setSerReqBillHRID(serviceRequestData.getSerReqBillHRID());
        }
        if (serviceRequestData.getSerReqBillDRID() != null) {
            serviceRequest.setSerReqBillDRID(serviceRequestData.getSerReqBillDRID());
        }

        return serviceRequest;
    }

    public ServiceRequestData convertServiceRequestToServiceRequestData(ServiceRequest serviceRequest)
            throws DcometServiceException {
        ServiceRequestData serviceRequestData = new ServiceRequestData();
        if (serviceRequest.getSerReqRid() != null) {
            serviceRequestData.setSerReqRid(serviceRequest.getSerReqRid());
        }
        if (serviceRequest.getSerReqhRid() != null) {
            serviceRequestData.setSerReqhRid(serviceRequest.getSerReqhRid());
        }
        if (serviceRequest.getSerReqItemRID() != null) {
            serviceRequestData.setSerReqItemRID(serviceRequest.getSerReqItemRID());
        }
        if (serviceRequest.getSerReqItemGroupRid() != null) {
            serviceRequestData.setSerReqItemGroupRid(serviceRequest.getSerReqItemGroupRid());
        }
        if (serviceRequest.getSerReqServicePackageRid() != null) {
            serviceRequestData.setSerReqServicePackageRid(serviceRequest.getSerReqServicePackageRid());
        }
        if (serviceRequest.getSerReqItemName() != null) {
            serviceRequestData.setSerReqItemName(serviceRequest.getSerReqItemName());
        }
        if (serviceRequest.getSerReqItemPrice() != null) {
            serviceRequestData.setSerReqItemPrice(serviceRequest.getSerReqItemPrice());
        }
        if (serviceRequest.getSerType() != null) {
            serviceRequestData.setSerType(serviceRequest.getSerType());
        }
        if (serviceRequest.getSerReqItemQty() != null) {
            serviceRequestData.setSerReqItemQty(serviceRequest.getSerReqItemQty());
        }
        if (serviceRequest.getSerReqMorning() != null) {
            serviceRequestData.setSerReqMorning(serviceRequest.getSerReqMorning());
        }
        if (serviceRequest.getSerReqAfternoon() != null) {
            serviceRequestData.setSerReqAfternoon(serviceRequest.getSerReqAfternoon());
        }
        if (serviceRequest.getSerReqEvening() != null) {
            serviceRequestData.setSerReqEvening(serviceRequest.getSerReqEvening());
        }
        if (serviceRequest.getSerReqNight() != null) {
            serviceRequestData.setSerReqNight(serviceRequest.getSerReqNight());
        }
        if (serviceRequest.getSerReqBillHRID() != null) {
            serviceRequestData.setSerReqBillHRID(serviceRequest.getSerReqBillHRID());
        }
        if (serviceRequest.getSerReqBillDRID() != null) {
            serviceRequestData.setSerReqBillDRID(serviceRequest.getSerReqBillDRID());
        }
        return serviceRequestData;
    }
}
