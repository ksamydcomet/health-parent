package com.dcomet.health.web.rest;

import com.dcomet.module.billing.domain.RefundHSearchCriteria;
import com.dcomet.module.billing.domain.RefundHSearchRequest;
import com.dcomet.module.billing.domain.RefundH;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.RefundService;
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
 * @author KS
 */
@Component
@Path("refund/v1")
public class RefundResource extends BaseResource {

    @Autowired
    @Qualifier("refundService")
    public RefundService refundService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public RefundH save(@Context final SecurityContext securityContext, RefundH refundH, @PathParam("boRID") final Integer boRID, @PathParam("boCode")
            final String boCode, @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, refundH);
        refundService.save(refundH, boRID, boCode, actionCode);
        return refundH;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<RefundH> search(@Context final SecurityContext securityContext,RefundHSearchCriteria refundHSearchCriteria) {
        RefundHSearchRequest refundHSearchRequest = new RefundHSearchRequest();
         addSecurityContext(securityContext, refundHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("refhEntityRID", refundHSearchRequest.getEntityRid()));
        refundHSearchRequest.setSearchCriterionList(searchCriterionList);
        refundHSearchRequest.addRefundHCriteria(refundHSearchCriteria);
        return refundService.getRefundH(refundHSearchRequest, true);
    }

    @POST
    @Path("/searchRefunds")
    @Consumes("application/json")
    @Produces("application/json")
    public String getTransactionRefundReport(@Context final SecurityContext securityContext, RefundHSearchCriteria refundHSearchCriteria) {
        RefundHSearchRequest refundHSearchRequest = new RefundHSearchRequest();
        addSecurityContext(securityContext, refundHSearchRequest);
        refundHSearchRequest.addRefundHCriteria(refundHSearchCriteria);
        return refundService.getTransactionRefundReport(refundHSearchRequest);
    }

}
