package com.dcomet.health.web.rest;

import com.dcomet.health.domain.SalesReturnH;
import com.dcomet.health.domain.SalesReturnHSearchCriteria;
import com.dcomet.health.domain.SalesReturnHSearchRequest;
import com.dcomet.health.service.business.PharmacyService;
import java.util.List;
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
@Path("sales/v1")
public class SalesResource extends BaseResource {

    @Autowired
    @Qualifier("pharmacyService")
    public PharmacyService pharmacyService;

    @POST
    @Path("/salesreturn/save")
    @Consumes("application/json")
    @Produces("application/json")
    public SalesReturnH save(@Context final SecurityContext securityContext, SalesReturnH parent) {
        addSecurityContext(securityContext, parent);
        pharmacyService.saveSalesReturnH(parent, true);
        return parent;
    }

    @POST
    @Path("/salesreturn/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<SalesReturnH> search(SalesReturnHSearchCriteria salesReturnHSearchCriteria) {
        SalesReturnHSearchRequest salesReturnHSearchRequest = new SalesReturnHSearchRequest();
        salesReturnHSearchRequest.addSalesReturnHCriteria(salesReturnHSearchCriteria);
        return pharmacyService.getSalesReturnH(salesReturnHSearchRequest, true);
    }

}
