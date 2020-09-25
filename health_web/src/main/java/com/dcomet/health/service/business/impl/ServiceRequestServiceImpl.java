  package com.dcomet.health.service.business.impl;                     

import com.dcomet.health.adapter.ServiceRequestAdapter;
import com.dcomet.health.dao.ServiceRequestDAO;
import com.dcomet.health.dao.data.FavouriteServiceOrderDData;
import com.dcomet.health.dao.data.FavouriteServiceOrderHData;
import com.dcomet.fw.domain.Base;
import com.dcomet.health.domain.FavouriteServiceOrderD;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteServiceOrderH;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.domain.BOState;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.dao.data.ServiceRequestData;
import com.dcomet.health.dao.data.ServiceRequestHData;
import com.dcomet.health.domain.ServiceRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.ServiceRequestService;
import com.dcomet.module.domain.AutoNumber;
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
 * @author Dev4
 */
@Service("serviceRequestService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ServiceRequestServiceImpl extends BaseWorkFlowServiceImpl implements ServiceRequestService {

    @Autowired
    @Qualifier("serviceRequestDAO")
    ServiceRequestDAO serviceRequestDAO;

    @Autowired
    @Qualifier("serviceRequestAdapter")
    private ServiceRequestAdapter serviceRequestAdapter;

    @Autowired
    @Qualifier("masterService")
    private MasterService masterService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Override
    public Integer save(Base base, Integer nextState, String actionCode) {
        ServiceRequestHData serviceRequestHData = new ServiceRequestHData();
        try {
            ServiceRequestH serviceRequestH = (ServiceRequestH) base;
            serviceRequestH.setSerReqhState(nextState);
            serviceRequestH.setSerReqhStatus(nextState);
            serviceRequestH.setCreatedDateTime(base.getCurrentDateTimeByUTZ());
            serviceRequestH.setModifiedDateTime(base.getCurrentDateTimeByUTZ());
            if (!"CANCEL".equals(actionCode)) {
                if (serviceRequestH.getSerReqhId() == null) {
                    AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("SRQ", serviceRequestH.getEntityRid());
                    serviceRequestH.setSerReqhNo(autoNumber.getAutoNumber());
                    serviceRequestH.setSerReqhPrefix(autoNumber.getAutoPrefix());
                    serviceRequestH.setSerReqhSequence(autoNumber.getAutoSequenceNumber());
                    dataDictionaryService.saveAutoNumberIncrement("SRQ", serviceRequestH.getEntityRid());
                }
                serviceRequestHData = saveServiceRequestHData(serviceRequestH, true);
            } else {
                serviceRequestH.setSerReqhState(BOState.ICANCELED);
                serviceRequestH.setSerReqhStatus(BOState.ICANCELED);
                serviceRequestHData = serviceRequestAdapter.convertServiceRequestHToServiceRequestHData(serviceRequestH);
                serviceRequestDAO.saveServiceRequestH(serviceRequestHData);
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceRequestHData.getSerReqhId();
    }

    @Override
    public List<FavouriteServiceOrderD> getFavouriteServiceOrderD(FavouriteServiceOrderDSearchRequest favouriteServiceOrderDSearchRequest)
            throws DcometServiceException {
        List<FavouriteServiceOrderD> result = new ArrayList<>();
        List<FavouriteServiceOrderDData> resultData = serviceRequestDAO.getFavouriteServiceOrderD(favouriteServiceOrderDSearchRequest);
        if (CollectionUtils.isNotEmpty(resultData)) {
            result = serviceRequestAdapter.convertFavouriteServiceOrderDDataToFavouriteServiceOrderD(resultData);
        }
        return result;
    }

    @Override
    public void saveFavouriteServiceOrderD(List<FavouriteServiceOrderD> favouriteServiceOrderDList) throws DcometServiceException {
        List<FavouriteServiceOrderDData> favouriteServiceOrderDDataList = serviceRequestAdapter.convertFavouriteServiceOrderDToFavouriteServiceOrderDData(favouriteServiceOrderDList);
        serviceRequestDAO.saveFavouriteServiceOrderD(favouriteServiceOrderDDataList);
    }

    @Override
    public List<FavouriteServiceOrderH> getFavouriteServiceOrderH(FavouriteServiceOrderHSearchRequest favouriteServiceOrderHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<FavouriteServiceOrderH> result = new ArrayList<>();
        try {
            List<FavouriteServiceOrderHData> resultData = serviceRequestDAO.getFavouriteServiceOrderH(favouriteServiceOrderHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = serviceRequestAdapter.convertFavouriteServiceOrderHDataToFavouriteServiceOrderH(resultData);
                if (includeChilds) {
                    for (FavouriteServiceOrderH favouriteServiceOrderH : result) {
                        FavouriteServiceOrderDSearchRequest childSearchRequest = new FavouriteServiceOrderDSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("fsodFsohRID", favouriteServiceOrderH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<FavouriteServiceOrderD> favouriteServiceOrderDResult = getFavouriteServiceOrderD(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(favouriteServiceOrderDResult)) {
                            favouriteServiceOrderH.setFavouriteServiceOrderD(favouriteServiceOrderDResult);
                        }
                    }

                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public void saveFavouriteServiceOrderH(FavouriteServiceOrderH favouriteServiceOrderH, boolean includeChilds) throws DcometServiceException {
        try {
            FavouriteServiceOrderHData favouriteServiceOrderHData = serviceRequestAdapter.convertFavouriteServiceOrderHToFavouriteServiceOrderHData(favouriteServiceOrderH);
            favouriteServiceOrderHData.setFsohEntityRID(favouriteServiceOrderH.getEntityRid());
            favouriteServiceOrderHData.setFsohUserRID(favouriteServiceOrderH.getFsohUserRID());
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(favouriteServiceOrderH.getFavouriteServiceOrderD())) {
                    List<FavouriteServiceOrderDData> favouriteServiceOrderDList = serviceRequestAdapter.convertFavouriteServiceOrderDToFavouriteServiceOrderDData(favouriteServiceOrderH.getFavouriteServiceOrderD());
                    for (FavouriteServiceOrderDData favouriteServiceOrderDData : favouriteServiceOrderDList) {
                        favouriteServiceOrderDData.setFavouriteServiceOrderHData(favouriteServiceOrderHData);
                    }
                    favouriteServiceOrderHData.setFavouriteServiceOrderDData(favouriteServiceOrderDList);
                }
            }
            serviceRequestDAO.saveFavouriteServiceOrderH(favouriteServiceOrderHData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

//    @Override
//    public List<ServiceRequest> getServiceRequest(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometDAOException {
//        List<ServiceRequest> result = null;
//        try {
//            List<ServiceRequestData> listData = serviceRequestDAO.getServiceRequest(serviceRequestSearchRequest);
//            if (CollectionUtils.isNotEmpty(listData)) {
//                result = serviceRequestAdapter.convertServiceRequestDataToServiceRequest(listData);
//            }
//        } catch (DcometDAOException e) {
//            throw new DcometServiceException(e);
//        } catch (DcometServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DcometServiceException(e);
//        }
//        return result;
//    }
    @Override
    public Integer getCurrentState(Integer boRID) {
        Integer boState = 0;
        List<ServiceRequestHData> serviceOrderHDataList = load(boRID);
        if (CollectionUtils.isNotEmpty(serviceOrderHDataList)) {
            boState = serviceOrderHDataList.get(0).getSerReqhState();
        }
        return boState;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        List<ServiceRequestHData> serviceOrderHDataList = load(boRID);
        StringBuilder boDescriptor = new StringBuilder();
        if (CollectionUtils.isNotEmpty(serviceOrderHDataList)) {
            boDescriptor.append(serviceOrderHDataList.get(0).getSerReqhNo()).append("&");
            boDescriptor.append(serviceOrderHDataList.get(0).getSerReqhPatName()).append("&");
        }
        return boDescriptor.toString();
    }

    private List<ServiceRequestHData> load(Integer boRID) {
        ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("serReqhId", boRID));
        serviceRequestHSearchRequest.setSearchCriterionList(criterionList);
        List<ServiceRequestHData> serviceOrderHDataList = serviceRequestDAO.getServiceRequestH(serviceRequestHSearchRequest);
        return serviceOrderHDataList;
    }

    @Override
    public List<ServiceRequestH> getServiceRequestH(ServiceRequestHSearchRequest serviceRequestHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<ServiceRequestH> result = null;
        try {
            List<ServiceRequestHData> resultData = serviceRequestDAO.getServiceRequestH(serviceRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) { //
                result = serviceRequestAdapter.convertServiceRequestHDataToServiceRequestH(resultData);
                if (includeChilds) {
                    for (ServiceRequestH serviceRequestH : result) {
                        ServiceRequestHSearchRequest childSearchRequest = new ServiceRequestHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("serReqhRid", serviceRequestH.getSerReqhId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<ServiceRequest> serviceRequests = getServiceRequests(childSearchRequest);
                        if (serviceRequests != null && !serviceRequests.isEmpty()) {
                            serviceRequestH.setServiceRequest(serviceRequests);
                        }
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<ServiceRequestH> getServiceRequestHById(Integer ServiceReqRid) throws DcometServiceException {
        ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("serReqhId", ServiceReqRid));
        serviceRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<ServiceRequestH> serviceRequestHs = getServiceRequestH(serviceRequestHSearchRequest, true);
        return serviceRequestHs;
    }

    private ServiceRequestHData saveServiceRequestHData(ServiceRequestH serviceRequestH, boolean includeChilds) throws DcometServiceException {
        ServiceRequestHData serviceRequestHData = serviceRequestAdapter.convertServiceRequestHToServiceRequestHData(serviceRequestH);
        try {
            serviceRequestDAO.saveServiceRequestH(serviceRequestHData);
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(serviceRequestH.getServiceRequest())) {
                    List<ServiceRequestData> serviceRequestDatas = serviceRequestAdapter.convertServiceRequestToServiceRequestData(serviceRequestH.getServiceRequest());
                    for (ServiceRequestData serviceRequestData : serviceRequestDatas) {
                        serviceRequestData.setSerReqhRid(serviceRequestHData.getSerReqhId());
                    }
                    serviceRequestDAO.saveServiceRequest(serviceRequestDatas);
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceRequestHData;
    }

    @Override
    public Integer saveServiceRequestH(ServiceRequestH serviceRequestH, boolean includeChilds) throws DcometServiceException {
        try {
            ServiceRequestHData serviceRequestHData = serviceRequestAdapter.convertServiceRequestHToServiceRequestHData(serviceRequestH);
            serviceRequestDAO.saveServiceRequestH(serviceRequestHData);
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(serviceRequestH.getServiceRequest())) {
                    List<ServiceRequestData> serviceRequestDatas = serviceRequestAdapter.convertServiceRequestToServiceRequestData(serviceRequestH.getServiceRequest());
                    for (ServiceRequestData serviceRequestData : serviceRequestDatas) {
                        serviceRequestData.setSerReqhRid(serviceRequestHData.getSerReqhId());
                    }
                    serviceRequestDAO.saveServiceRequest(serviceRequestDatas);
                }
            }
            return serviceRequestHData.getSerReqOpVisitRid();

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer modifyServiceRequestH(ServiceRequestH serviceRequestH, boolean includeChilds) throws DcometServiceException {
        try {
            boolean check = false, valid = false;
            List<ServiceRequestData> serviceRequestExistList = new ArrayList<>();
            List<ServiceRequest> serviceRequestNewList = new ArrayList<>();
            ServiceRequestH serviceRequestHNew = serviceRequestH;
            ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
            List<Criterion> criterionList = new ArrayList<>();
            criterionList.add(Restrictions.eq("serReqhId", serviceRequestH.getSerReqhId()));
            serviceRequestHSearchRequest.setSearchCriterionList(criterionList);
            List<ServiceRequestHData> serviceOrderHDataList = serviceRequestDAO.getServiceRequestH(serviceRequestHSearchRequest);
            for (ServiceRequestHData serviceRequestHData : serviceOrderHDataList) {
                serviceRequestH.setSerReqhState(serviceRequestHData.getSerReqhState());
                serviceRequestH.setSerReqhStatus(serviceRequestHData.getSerReqhStatus());
            }
            ServiceRequestHData serviceRequestHData = serviceRequestAdapter.convertServiceRequestHToServiceRequestHData(serviceRequestH);
            serviceRequestDAO.saveServiceRequestH(serviceRequestHData);
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(serviceRequestH.getServiceRequest())) {
                    List<ServiceRequestData> serviceRequestDatas = serviceRequestAdapter.convertServiceRequestToServiceRequestData(serviceRequestH.getServiceRequest());
                    for (ServiceRequestData serviceRequestData : serviceRequestDatas) {
                        if (serviceRequestData.getSerReqRid() != null) {
                            check = true;
                            serviceRequestData.setSerReqhRid(serviceRequestHData.getSerReqhId());
                            serviceRequestExistList.add(serviceRequestData);
                        }
                    }
                    if (check) {
                        serviceRequestDAO.saveServiceRequest(serviceRequestExistList);
                    }
                    List<ServiceRequest> serviceRequestList = serviceRequestHNew.getServiceRequest();
                    for (ServiceRequest serviceRequest : serviceRequestList) {
                        if (serviceRequest.getSerReqRid() == null) {
                            valid = true;
                            serviceRequestNewList.add(serviceRequest);
                        }
                    }
                    if (valid) {
                        serviceRequestHNew.setSerReqhId(null);
                        serviceRequestHNew.setServiceRequest(serviceRequestNewList);
                        save(serviceRequestHNew, 0, "SERVICE_ORDER_REQ", "REQUEST");
                    }
                }
            }
            return serviceRequestHData.getSerReqOpVisitRid();

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<ServiceRequest> getServiceRequests(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometServiceException {
        List<ServiceRequest> result = null;
        try {
            List<ServiceRequestData> resultData = serviceRequestDAO.getServiceRequest(serviceRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = serviceRequestAdapter.convertServiceRequestDataToServiceRequest(resultData);
                for (ServiceRequest serviceRequest : result) {
                    serviceRequest.setSerReqServiceCost(masterService.getServiceMasterByID(serviceRequest.getSerReqItemRID()).getbPrice());
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }
}
