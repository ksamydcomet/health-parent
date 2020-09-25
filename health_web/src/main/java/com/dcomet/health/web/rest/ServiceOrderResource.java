package com.dcomet.health.web.rest;

import static com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl.IDRAFT;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.ServiceOrderSearchCriteria;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.domain.ServiceOrderD;
import com.dcomet.health.service.business.ServiceOrderService;
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

@Component
@Path("serviceOrder/v1")
public class ServiceOrderResource extends BaseResource {

    @Autowired
    @Qualifier("serviceOrderService")
    public ServiceOrderService serviceOrderService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, ServiceOrder serviceOrder, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, serviceOrder);
        serviceOrderService.save(serviceOrder, boRID, boCode, actionCode);
    }

    @POST
    @Path("/saveresult")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceOrder> saveServiceOrderResult(@Context final SecurityContext securityContext, List<ServiceOrder> serviceOrderList) {
        for (ServiceOrder serviceOrder : serviceOrderList) {
            addSecurityContext(securityContext, serviceOrder);
        }
        serviceOrderService.saveServiceOrderResult(serviceOrderList);
        return serviceOrderList;
    }
    
    @POST
    @Path("/saveserviceorderD")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceOrder> saveServiceOrderWithD(@Context final SecurityContext securityContext, List<ServiceOrder> serviceOrderList) {
        for (ServiceOrder serviceOrder : serviceOrderList) {
            addSecurityContext(securityContext, serviceOrder);
        }
        serviceOrderService.saveServiceOrderWithD(serviceOrderList, true);
        return serviceOrderList;
    }

    @POST
    @Path("/saveList/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, List<ServiceOrder> serviceOrderList, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        for (ServiceOrder serviceOrder : serviceOrderList) {
            addSecurityContext(securityContext, serviceOrder);
            serviceOrderService.save(serviceOrder, serviceOrder.getId(), boCode, actionCode);
        }
    }

    @POST
    @Path("/saveSo/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveSo(@Context final SecurityContext securityContext, List<ServiceOrder> serviceOrderList, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        for (ServiceOrder serviceOrder : serviceOrderList) {
            addSecurityContext(securityContext, serviceOrder);
            serviceOrderService.save(serviceOrder, boRID, boCode, actionCode);
        }
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceOrder> search(ServiceOrderSearchCriteria serviceOrderSearchCriteria) {
        ServiceOrderSearchRequest searchRequest = new ServiceOrderSearchRequest();
        searchRequest.addServiceOrderCriteria(serviceOrderSearchCriteria);
        return serviceOrderService.getServiceOrder(searchRequest, true);
    }
    
    @POST
    @Path("/searchServiceOrderD")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceOrder> searchServiceOrderD(ServiceOrderSearchCriteria serviceOrderSearchCriteria) {
        ServiceOrderSearchRequest searchRequest = new ServiceOrderSearchRequest();
        searchRequest.addServiceOrderCriteria(serviceOrderSearchCriteria);
        return serviceOrderService.getServiceOrderWithD(searchRequest, true);
    }
    
    @POST
    @Path("/pendingsearch")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Patient> searchPendingOrders(@Context final SecurityContext securityContext, ServiceOrderSearchCriteria serviceOrderSearchCriteria) {
        ServiceOrderSearchRequest searchRequest = new ServiceOrderSearchRequest();
        addSecurityContext(securityContext, searchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("soEntityRID", searchRequest.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addServiceOrderCriteria(serviceOrderSearchCriteria);

        return serviceOrderService.getPendingServiceOrders1(searchRequest);
    }    
    
    @POST
    @Path("/radiology/print")
    @Consumes("application/json")
    @Produces("application/json")
    public String getRadioServicePrint(@Context final SecurityContext securityContext, ServiceOrderSearchCriteria serviceOrderSearchCriteria) {
        ServiceOrderSearchRequest searchRequest = new ServiceOrderSearchRequest();
        addSecurityContext(securityContext, searchRequest);
        searchRequest.addServiceOrderCriteria(serviceOrderSearchCriteria);
        return serviceOrderService.getRadioServicePrint(searchRequest);
    }
}
