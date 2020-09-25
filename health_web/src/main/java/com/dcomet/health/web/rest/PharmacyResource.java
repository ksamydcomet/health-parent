package com.dcomet.health.web.rest;

import com.dcomet.health.domain.HealthBillH;
import com.dcomet.health.domain.MaterialIssueH;
import com.dcomet.health.domain.MaterialIssueHSearchCriteria;
import com.dcomet.health.domain.MaterialIssueHSearchRequest;
import com.dcomet.health.domain.SalesReturnH;
import com.dcomet.health.service.business.PharmacyService;
import com.dcomet.module.billing.domain.BillH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("pharmacy")
public class PharmacyResource extends BaseResource {

    @Autowired
    @Qualifier("pharmacyService")
    public PharmacyService pharmacyService;

//    @POST
//    @Path("/sales")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public void save(SalesH parent) {
//        salesService.saveSalesH(parent, true);
//    }
    @POST
    @Path("/salesreturn")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, SalesReturnH parent) {
        addSecurityContext(securityContext, parent);
        pharmacyService.saveSalesReturnH(parent, true);
    }

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public MaterialIssueH save(@Context final SecurityContext securityContext, MaterialIssueH materialIssueH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode, @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, materialIssueH);
        pharmacyService.save(materialIssueH, boRID, boCode, actionCode);
        return materialIssueH;
    }

    @POST
    @Path("/materialIssue/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, MaterialIssueH parent) {
        addSecurityContext(securityContext, parent);
        pharmacyService.saveMaterialIssueH(parent, true);
    }

    @POST
    @Path("/materialIssue/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<MaterialIssueH> search(MaterialIssueHSearchCriteria materialIssueHSearchCriteria) {
        MaterialIssueHSearchRequest materialIssueHSearchRequest = new MaterialIssueHSearchRequest();
        materialIssueHSearchRequest.addMaterialIssueHSearchCriteria(materialIssueHSearchCriteria);
        return pharmacyService.getMaterialIssueH(materialIssueHSearchRequest, true);
    }
}
