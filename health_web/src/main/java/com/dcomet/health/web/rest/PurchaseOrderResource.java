package com.dcomet.health.web.rest;

import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.service.business.PurchaseService;
import com.dcomet.module.purchase.domain.GoodsReceiptHSearchRequest;
import com.dcomet.module.purchase.domain.PurchaseOrderHSearchCriteria;
import com.dcomet.module.purchase.domain.PurchaseOrderHSearchRequest;
import com.dcomet.module.purchase.domain.PurchaseOrderH;
import com.dcomet.module.purchase.domain.PurchaseRequestH;
import com.dcomet.module.purchase.domain.PurchaseRequestHSearchCriteria;
import com.dcomet.module.purchase.domain.PurchaseRequestHSearchRequest;
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
@Path("purchaseOrder/v1")
public class PurchaseOrderResource extends BaseResource {

    @Autowired
    @Qualifier("purchaseOrderService")
    public WorkFlowService workFlowService;
    
    @Autowired
    @Qualifier("purchaseService")
    public PurchaseService purchaseService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public PurchaseOrderH save(@Context final SecurityContext securityContext, PurchaseOrderH parent, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, parent);
        workFlowService.save(parent, boRID, boCode, actionCode);
        return parent;
    }

    @POST
    @Path("/poh/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PurchaseOrderH> search(@Context final SecurityContext securityContext,PurchaseOrderHSearchCriteria purchaseOrderHSearchCriteria) {
        PurchaseOrderHSearchRequest purchaseOrderHSearchRequest = new PurchaseOrderHSearchRequest();
        addSecurityContext(securityContext, purchaseOrderHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("pohEntityRID", purchaseOrderHSearchRequest.getEntityRid()));
        purchaseOrderHSearchRequest.setSearchCriterionList(searchCriterionList);
        purchaseOrderHSearchRequest.addPurchaseOrderHSearchCriteria(purchaseOrderHSearchCriteria);
        return purchaseService.getPurchaseOrderH(purchaseOrderHSearchRequest, true);
    }

    @POST
    @Path("/poh/save")
    @Consumes("application/json")
    @Produces("application/json")
    public PurchaseOrderH save(@Context final SecurityContext securityContext, PurchaseOrderH parent) {
        addSecurityContext(securityContext, parent);
        purchaseService.savePurchaseOrderH(parent, true);
        return parent;
    }
//    ----------PurchaseRequest----

    @POST
    @Path("/prh/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PurchaseRequestH> search(@Context final SecurityContext securityContext,PurchaseRequestHSearchCriteria purchaseRequestHSearchCriteria) {
        PurchaseRequestHSearchRequest purchaseRequestHSearchRequest = new PurchaseRequestHSearchRequest();
        addSecurityContext(securityContext, purchaseRequestHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("prqhEntityRID", purchaseRequestHSearchRequest.getEntityRid()));
        purchaseRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        purchaseRequestHSearchRequest.addPurchaseRequestHSearchCriteria(purchaseRequestHSearchCriteria);
        return purchaseService.getPurchaseRequestH(purchaseRequestHSearchRequest, true);
    }

    @POST
    @Path("/preqh/save")
    @Consumes("application/json")
    @Produces("application/json")
    public PurchaseRequestH save(@Context final SecurityContext securityContext, PurchaseRequestH parent) {
        addSecurityContext(securityContext, parent);
        purchaseService.savePurchaseRequestH(parent, true);
        return parent;
    }
}
