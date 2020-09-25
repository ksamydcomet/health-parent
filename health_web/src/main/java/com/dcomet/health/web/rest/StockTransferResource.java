package com.dcomet.health.web.rest;

import com.dcomet.health.service.business.InventoryService;
import com.dcomet.module.inventory.domain.StockMovementH;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("stockTransfer")
public class StockTransferResource extends BaseResource {

    @Autowired
    @Qualifier("inventoryService")
    public InventoryService inventoryService;

    @POST
    @Path("/stockmovement/save")
    @Consumes("application/json")
    @Produces("application/json")
    public StockMovementH save(@Context final SecurityContext securityContext, StockMovementH parent) {
        addSecurityContext(securityContext, parent);
        inventoryService.saveStockMovementH(parent, true);
        return parent;
    }
}
