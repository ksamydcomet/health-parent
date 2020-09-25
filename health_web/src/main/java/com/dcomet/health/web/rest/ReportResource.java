package com.dcomet.health.web.rest;

import com.dcomet.health.domain.dbd.ReportSearchCriteria;
import com.dcomet.health.domain.dbd.ReportSearchRequest;
import com.dcomet.health.service.business.ReportService;
import com.dcomet.module.domain.User;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev4
 */
@Component
@Path("report/v1")
public class ReportResource extends BaseResource {

    @Autowired
    @Qualifier("reportService")
    public ReportService reportService;

    @POST
    @Path("/billing/collection/excel")
    @Produces("application/json")
    public Response getBillingCollection(@Context final SecurityContext securityContext, ReportSearchCriteria reportSearchCriteria) throws Exception {
        ReportSearchRequest reportSearchRequest = new ReportSearchRequest();
        reportSearchRequest.addSearchCriteria(reportSearchCriteria);
        return Response.ok(reportService.getRBillCollectionData(reportSearchRequest), "application/vnd.ms-excel").build();
    }

    @GET
    @Path("/unitSalesReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getUnitSalesReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getUnitSalesReport(fromDate, toDate, user.getEntityRid());
    }

    @GET
    @Path("/serviceSalesReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getServiceSalesReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getServiceSalesReport(fromDate, toDate, user.getEntityRid());
    }

//    @GET
//    @Path("/getPayerSummary/{fromDate}/{toDate}")
//    @Produces("application/json")
//    public List<Object[]> getPayerSummary(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
//        User user = getLoginUser(securityContext);
//        return reportService.getPayerSummary(fromDate, toDate, user.getEntityRid());
//    }

    @GET
    @Path("/getPayerSummary/{entityRid}/{fromDate}/{toDate}/{payerType}")
    @Produces("application/json")
    public List<Object[]> getPayerSummary(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate, @PathParam("payerType") final String payerType) throws Exception {
//        String entityRids = " ";
//        entityRids = getEntityRid(entityRid);
        return reportService.getPayerSummary(entityRid, fromDate, toDate, payerType);
    }

    @GET
    @Path("/collectionSummaryReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getCollectionSummaryReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getCollectionSummaryReport(fromDate, toDate, user.getEntityRid());
    }

    @GET
    @Path("/collectionReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getCollectionReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getCollectionReport(fromDate, toDate, user.getEntityRid());
    }

    @GET
    @Path("/laboratoryReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getLaboratoryReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getLaboratoryReport(fromDate, toDate, user.getEntityRid());
    }
    
    @GET
    @Path("/pendingSalesReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getPendingSalesReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getPendingSalesReport(fromDate, toDate, user.getEntityRid());
    }
    
    @GET
    @Path("/pharmacySalesReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getPharmacySalesReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getPharmacySalesReport(fromDate, toDate, user.getEntityRid());
    }
    @GET
    @Path("/wiseCollectionReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getWiseCollectionReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getWiseCollectionReport(fromDate, toDate, user.getEntityRid());
    }
    @GET
    @Path("/discountBillReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getDiscountBill(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getDiscountBill(fromDate, toDate, user.getEntityRid());
    }
    @GET
    @Path("/cancelledReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getCancelledReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getCancelledReport(fromDate, toDate, user.getEntityRid());
    }
//    @GET
//    @Path("/getAdvanceReport/{fromDate}/{toDate}")
//    @Produces("application/json")
//    public List<Object[]> getAdvanceReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
//        User user = getLoginUser(securityContext);
//        return reportService.getAdvanceReport(fromDate, toDate, user.getEntityRid());
//    }

    
    @GET
    @Path("/billingSalesReport/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getBillingSalesReport(@Context final SecurityContext securityContext, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        User user = getLoginUser(securityContext);
        return reportService.getBillingSalesReport(fromDate, toDate, user.getEntityRid());
    }
}
