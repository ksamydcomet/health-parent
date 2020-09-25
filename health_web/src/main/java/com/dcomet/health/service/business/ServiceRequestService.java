package com.dcomet.health.service.business;

import com.dcomet.health.domain.FavouriteServiceOrderD;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteServiceOrderH;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.ServiceRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface ServiceRequestService extends WorkFlowService {

    public List<FavouriteServiceOrderD> getFavouriteServiceOrderD(FavouriteServiceOrderDSearchRequest favouriteServiceOrderDSearchRequest) throws DcometServiceException;

    public void saveFavouriteServiceOrderD(List<FavouriteServiceOrderD> resourceList) throws DcometServiceException;

    public List<FavouriteServiceOrderH> getFavouriteServiceOrderH(FavouriteServiceOrderHSearchRequest favouriteServiceOrderHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public void saveFavouriteServiceOrderH(FavouriteServiceOrderH favouriteServiceOrderHList, boolean includeChilds) throws DcometServiceException;

//    public List<ServiceRequest> getServiceRequest(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometDAOException;
//
//    public void saveServiceRequest(List<ServiceRequest> serviceRequestList) throws DcometDAOException;
    public List<ServiceRequestH> getServiceRequestH(ServiceRequestHSearchRequest serviceRequestHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<ServiceRequestH> getServiceRequestHById(Integer ServiceReqRid) throws DcometServiceException;

    public Integer saveServiceRequestH(ServiceRequestH serviceRequestH, boolean includeChilds) throws DcometServiceException;

    public Integer modifyServiceRequestH(ServiceRequestH serviceRequestH, boolean includeChilds) throws DcometServiceException;

    public List<ServiceRequest> getServiceRequests(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometServiceException;

}
