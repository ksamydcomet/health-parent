package com.dcomet.health.web.rest;

import com.dcomet.module.inventory.domain.ConsumptionDetails;
import com.dcomet.module.inventory.domain.OpeningStockHSearchCriteria;
import com.dcomet.module.inventory.domain.OpeningStockHSearchRequest;
import com.dcomet.module.inventory.domain.OpeningStockH;
import com.dcomet.module.inventory.domain.StockAdjustmentH;
import com.dcomet.module.inventory.domain.StockAdjustmentHSearchCriteria;
import com.dcomet.module.inventory.domain.StockAdjustmentHSearchRequest;
import com.dcomet.module.inventory.domain.StockH;
import com.dcomet.module.inventory.domain.StockHSearchCriteria;
import com.dcomet.module.inventory.domain.StockHSearchRequest;
import com.dcomet.module.inventory.domain.StockTransaction;
import com.dcomet.module.inventory.domain.StockTransactionSearchCriteria;
import com.dcomet.module.inventory.domain.StockTransactionSearchRequest;
import com.dcomet.module.inventory.service.DCometInventoryService;
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
@Path("inventory/v1")
public class InventoryResource extends BaseResource {

    @Autowired
    @Qualifier("inventoryService")
    public DCometInventoryService inventoryService;

    @POST
    @Path("/openingstock/save")
    @Consumes("application/json")
    @Produces("application/json")
    public OpeningStockH save(@Context final SecurityContext securityContext, OpeningStockH parent) {
        addSecurityContext(securityContext, parent);
        inventoryService.saveOpeningStockH(parent, true);
        return parent;
    }

    @POST
    @Path("/openingstock/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<OpeningStockH> search(@Context final SecurityContext securityContext,OpeningStockHSearchCriteria openingStockHSearchCriteria) {
        OpeningStockHSearchRequest openingStockHSearchRequest = new OpeningStockHSearchRequest();
        addSecurityContext(securityContext, openingStockHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("osEntityRID", openingStockHSearchRequest.getEntityRid()));
        openingStockHSearchRequest.setSearchCriterionList(searchCriterionList);
        openingStockHSearchRequest.addOpeningStockHCriteria(openingStockHSearchCriteria);
        return inventoryService.getOpeningStockH(openingStockHSearchRequest, true);
    }

    @POST
    @Path("/stockadjustment/save")
    @Consumes("application/json")
    @Produces("application/json")
    public StockAdjustmentH save(@Context final SecurityContext securityContext, StockAdjustmentH parent) {
        addSecurityContext(securityContext, parent);
        inventoryService.saveStockAdjustmentH(parent, true);
        return parent;
    }

    @POST
    @Path("/stockadjustment/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<StockAdjustmentH> search(@Context final SecurityContext securityContext,StockAdjustmentHSearchCriteria stockAdjustmentHSearchCriteria) {
        StockAdjustmentHSearchRequest stockAdjustmentHSearchRequest = new StockAdjustmentHSearchRequest();
        addSecurityContext(securityContext, stockAdjustmentHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("sahEntityRID", stockAdjustmentHSearchRequest.getEntityRid()));
        stockAdjustmentHSearchRequest.setSearchCriterionList(searchCriterionList);
        stockAdjustmentHSearchRequest.addStockAdjustmentHCriteria(stockAdjustmentHSearchCriteria);
        return inventoryService.getStockAdjustmentH(stockAdjustmentHSearchRequest, true);
    }

    @POST
    @Path("/consumption/save")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ConsumptionDetails> save(@Context final SecurityContext securityContext, List<ConsumptionDetails> consumptionDetailsList) {
        for (ConsumptionDetails consumptionDetails : consumptionDetailsList) {
            addSecurityContext(securityContext, consumptionDetails);
            inventoryService.saveConsumptionDetails(consumptionDetails);
        }
        return consumptionDetailsList;
    }

    @POST
    @Path("/StockH/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<StockH> search(@Context final SecurityContext securityContext,StockHSearchCriteria stockHSearchCriteria) {
        StockHSearchRequest stockHSearchRequest = new StockHSearchRequest();
        addSecurityContext(securityContext, stockHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("stkEntityRID", stockHSearchRequest.getEntityRid()));
        stockHSearchRequest.setSearchCriterionList(searchCriterionList);
        stockHSearchRequest.addStockHCriteria(stockHSearchCriteria);
        return inventoryService.getStockH(stockHSearchRequest, true);

    }

    @POST
    @Path("/stockledger/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<StockTransaction> search(@Context final SecurityContext securityContext,StockTransactionSearchCriteria stockTransactionSearchCriteria) {
        StockTransactionSearchRequest stockTransactionSearchRequest = new StockTransactionSearchRequest();
        addSecurityContext(securityContext, stockTransactionSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("tranEntityID", stockTransactionSearchRequest.getEntityRid()));
        stockTransactionSearchRequest.setSearchCriterionList(searchCriterionList);
        stockTransactionSearchRequest.addStockTransactionCriteria(stockTransactionSearchCriteria);
        return inventoryService.getStockTransaction(stockTransactionSearchRequest);

    }

}
