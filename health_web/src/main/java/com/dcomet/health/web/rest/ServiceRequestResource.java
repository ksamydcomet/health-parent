package com.dcomet.health.web.rest;

import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.FavouriteServiceOrderD;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchCriteria;
import com.dcomet.health.domain.FavouriteServiceOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteServiceOrderH;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchCriteria;
import com.dcomet.health.domain.FavouriteServiceOrderHSearchRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchCriteria;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.ServiceRequestService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev4
 */
@Component
@Path("servicerequest/v1")
public class ServiceRequestResource extends BaseResource {

    @Autowired
    @Qualifier("serviceRequestService")
    public ServiceRequestService serviceRequestService;

    @POST
    @Path("/favouriteserviceorderd/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FavouriteServiceOrderD> search(FavouriteServiceOrderDSearchCriteria favouriteServiceOrderDSearchCriteria) {
        FavouriteServiceOrderDSearchRequest favouriteServiceOrderDSearchRequest = new FavouriteServiceOrderDSearchRequest();
        favouriteServiceOrderDSearchRequest.addFavouriteServiceOrderDSearchCriteria(favouriteServiceOrderDSearchCriteria);
        return serviceRequestService.getFavouriteServiceOrderD(favouriteServiceOrderDSearchRequest);
    }

    @POST
    @Path("/favouriteServiceOrder/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FavouriteServiceOrderH> search(FavouriteServiceOrderHSearchCriteria favouriteServiceOrderHSearchCriteria) {
        FavouriteServiceOrderHSearchRequest favouriteServiceOrderHSearchRequest = new FavouriteServiceOrderHSearchRequest();
        favouriteServiceOrderHSearchRequest.addFavouriteServiceOrderHSearchCriteria(favouriteServiceOrderHSearchCriteria);
        return serviceRequestService.getFavouriteServiceOrderH(favouriteServiceOrderHSearchRequest, true);
    }

    @POST
    @Path("/favouriteServiceOrder/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, FavouriteServiceOrderH favouriteServiceOrderH) {
        addSecurityContext(securityContext, favouriteServiceOrderH);
        serviceRequestService.saveFavouriteServiceOrderH(favouriteServiceOrderH, true);
    }

//    @POST
//    @Path("/serviceRequest/search")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public List<ServiceRequest> search(ServiceRequestSearchCriteria serviceRequestSearchCriteria) {
//        ServiceRequestSearchRequest serviceRequestSearchRequest = new ServiceRequestSearchRequest();
//        serviceRequestSearchRequest.addServiceRequestCriteria(serviceRequestSearchCriteria);
//        return serviceRequestService.getServiceRequest(serviceRequestSearchRequest);
//    }
//    @POST
//    @Path("/service/save/{boRID}/{boCode}/{actionCode}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public List<ServiceRequest> save(@Context final SecurityContext securityContext, List<ServiceRequest> serviceRequestList, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
//            @PathParam("actionCode") final String actionCode) {
//        for (ServiceRequest serviceRequest : serviceRequestList) {
//            addSecurityContext(securityContext, serviceRequest);
//            serviceRequestService.save(serviceRequest, boRID, boCode, actionCode);
//        }
//        return serviceRequestList;
//    }
    @POST
    @Path("/service/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public ServiceRequestH save(@Context final SecurityContext securityContext, ServiceRequestH serviceRequestH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, serviceRequestH);
        serviceRequestService.save(serviceRequestH, boRID, boCode, actionCode);
        return serviceRequestH;
    }

    @POST
    @Path("/drugs/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DrugRequestH> saveDrugList(@Context final SecurityContext securityContext, List<DrugRequestH> drugRequestHs, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        for (DrugRequestH drugRequestH : drugRequestHs) {
            serviceRequestService.save(drugRequestH, boRID, boCode, actionCode);
        }
        return drugRequestHs;
    }

    @POST
    @Path("/service/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, ServiceRequestH serviceRequestH) {
        addSecurityContext(securityContext, serviceRequestH);
        return serviceRequestService.saveServiceRequestH(serviceRequestH, true);
    }
    
    @POST
    @Path("/service/modify/")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer modify(@Context final SecurityContext securityContext, ServiceRequestH serviceRequestH) {
        addSecurityContext(securityContext, serviceRequestH);
        return serviceRequestService.modifyServiceRequestH(serviceRequestH, true);
    }

    @POST
    @Path("/serviceRequest/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceRequestH> search(@Context final SecurityContext securityContext, ServiceRequestHSearchCriteria serviceRequestHSearchCriteria) {
        ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
        addSecurityContext(securityContext, serviceRequestHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("serReqhEntityRid", serviceRequestHSearchRequest.getEntityRid()));
        serviceRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        serviceRequestHSearchRequest.addServiceRequestCriteria(serviceRequestHSearchCriteria);
        return serviceRequestService.getServiceRequestH(serviceRequestHSearchRequest, true);
    }
}
