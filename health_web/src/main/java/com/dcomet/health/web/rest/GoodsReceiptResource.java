package com.dcomet.health.web.rest;

import com.dcomet.module.purchase.domain.GoodsReceiptHSearchCriteria;
import com.dcomet.module.purchase.domain.GoodsReceiptHSearchRequest;
import com.dcomet.module.purchase.domain.GoodsReceiptH;
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
@Path("goodsReceipt/v1")
public class GoodsReceiptResource extends BaseResource{

    @Autowired
    @Qualifier("purchaseService")
    public DCometPurchaseService purchaseService;

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public GoodsReceiptH save(@Context final SecurityContext securityContext, GoodsReceiptH parent) {
        addSecurityContext(securityContext, parent);
        purchaseService.saveGoodsReceiptH(parent, true);
        return parent;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<GoodsReceiptH> search(@Context final SecurityContext securityContext,GoodsReceiptHSearchCriteria goodsReceiptHSearchCriteria) {
        GoodsReceiptHSearchRequest goodsReceiptHSearchRequest = new GoodsReceiptHSearchRequest();
        addSecurityContext(securityContext, goodsReceiptHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("grhEntityRID", goodsReceiptHSearchRequest.getEntityRid()));
        goodsReceiptHSearchRequest.setSearchCriterionList(searchCriterionList);
        goodsReceiptHSearchRequest.addGoodsReceiptHCriteria(goodsReceiptHSearchCriteria);
        return purchaseService.getGoodsReceiptH(goodsReceiptHSearchRequest, true);
    }
}
