package com.dcomet.health.dao;

import com.dcomet.health.dao.data.FavouriteServiceOrderDData;
import com.dcomet.health.dao.data.FavouriteServiceOrderHData;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.ServiceRequestData;
import com.dcomet.health.dao.data.ServiceRequestHData;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface ServiceRequestDAO {

//      ------------FavouriteServiceOrderH----------------
    public List<FavouriteServiceOrderHData> getFavouriteServiceOrderH(FavouriteServiceOrderHSearchRequest FavouriteServiceOrderHSearchRequestList) throws DcometDAOException;

    public void saveFavouriteServiceOrderH(FavouriteServiceOrderHData favouriteServiceOrderHDataList) throws DcometDAOException;

    //    ------------FavouriteServiceOrderD----------------
    public List<FavouriteServiceOrderDData> getFavouriteServiceOrderD(FavouriteServiceOrderDSearchRequest FavouriteServiceOrderDSearchRequestList) throws DcometDAOException;

    public void saveFavouriteServiceOrderD(List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList) throws DcometDAOException;

//    public Long getServiceRequestTotalCount(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometDAOException;
//    public void saveServiceRequest(ServiceRequestData ServiceRequest) throws DcometDAOException;
//
//    public List<ServiceRequestData> getServiceRequest(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometDAOException;
    public List<ServiceRequestHData> getServiceRequestH(ServiceRequestHSearchRequest ServiceRequestHSearchRequest) throws DcometDAOException;

    public void saveServiceRequestH(ServiceRequestHData serviceRequestHDataList) throws DcometDAOException;

    public List<ServiceRequestData> getServiceRequest(ServiceRequestHSearchRequest serviceRequestHSearchRequestList) throws DcometDAOException;

    public void saveServiceRequest(List<ServiceRequestData> serviceRequestDataList) throws DcometDAOException;
}
