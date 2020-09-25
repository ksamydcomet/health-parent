package com.dcomet.health.web.rest;

import com.dcomet.module.purchase.domain.PurchaseReturnHSearchCriteria;
import com.dcomet.module.purchase.domain.PurchaseReturnHSearchRequest;
import com.dcomet.module.purchase.domain.PurchaseReturnH;
import com.dcomet.module.purchase.service.DCometPurchaseService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("purchaseReturn/v1")
public class PurchaseReturnResource extends BaseResource{

    @Autowired
    @Qualifier("purchaseService")
    public DCometPurchaseService purchaseService;

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public PurchaseReturnH save(@Context final SecurityContext securityContext,PurchaseReturnH parent) {
        addSecurityContext(securityContext, parent);
        purchaseService.savePurchaseReturnH(parent, true);
        return parent;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PurchaseReturnH> search(@Context final SecurityContext securityContext,PurchaseReturnHSearchCriteria purchaseReturnHSearchCriteria) {
        PurchaseReturnHSearchRequest purchaseReturnHSearchRequest = new PurchaseReturnHSearchRequest();
        addSecurityContext(securityContext, purchaseReturnHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("prhEntityRID", purchaseReturnHSearchRequest.getEntityRid()));
        purchaseReturnHSearchRequest.setSearchCriterionList(searchCriterionList);
        purchaseReturnHSearchRequest.addPurchaseReturnHCriteria(purchaseReturnHSearchCriteria);
        return purchaseService.getPurchaseReturnH(purchaseReturnHSearchRequest, true);
    }
}
