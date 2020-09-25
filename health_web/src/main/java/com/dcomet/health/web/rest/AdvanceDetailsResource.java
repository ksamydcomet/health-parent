package com.dcomet.health.web.rest;

import com.dcomet.module.billing.domain.AdvanceDetails;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchCriteria;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchRequest;
import com.dcomet.health.service.business.AdvanceDetailsService;
import com.dcomet.module.billing.domain.RefundH;
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
@Path("advance/v1")
public class AdvanceDetailsResource extends BaseResource {

    @Autowired
    @Qualifier("advanceDetailsService")
    public AdvanceDetailsService advanceDetailsService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, AdvanceDetails advanceDetails, @PathParam("boRID") final Integer boRID,
            @PathParam("boCode") final String boCode, @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, advanceDetails);
        return advanceDetailsService.save(advanceDetails, boRID, boCode, actionCode);
    }

    @POST
    @Path("/saveList/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, List<AdvanceDetails> advanceDetailsList, @PathParam("boRID") final Integer boRID,
            @PathParam("boCode") final String boCode, @PathParam("actionCode") final String actionCode) {
        for (AdvanceDetails advanceDetails : advanceDetailsList) {
            addSecurityContext(securityContext, advanceDetails);
            advanceDetailsService.save(advanceDetails, advanceDetails.getId(), boCode, actionCode);
        }
    }

    @POST
    @Path("/saverefundwithadvance")
    @Consumes("application/json")
    @Produces("application/json")
    public void saverefundwithadvance(@Context final SecurityContext securityContext, RefundH refundH) {
        addSecurityContext(securityContext, refundH);
        advanceDetailsService.saveRefundWithAdvance(refundH);
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<AdvanceDetails> advasearch(@Context final SecurityContext securityContext, AdvanceDetailsSearchCriteria advanceDetailsSearchCriteria) {
        AdvanceDetailsSearchRequest advanceDetailsSearchRequest = new AdvanceDetailsSearchRequest();
        addSecurityContext(securityContext, advanceDetailsSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("adEntityRID", advanceDetailsSearchRequest.getEntityRid()));
        advanceDetailsSearchRequest.setSearchCriterionList(searchCriterionList);
        advanceDetailsSearchRequest.addAdvanceDetailsCriteria(advanceDetailsSearchCriteria);
        return advanceDetailsService.getAdvanceDetails(advanceDetailsSearchRequest, true);
    }

    @POST
    @Path("/advance")
    @Consumes("application/json")
    @Produces("application/json")
    public String getTransactionAdvanceReport(@Context final SecurityContext securityContext, AdvanceDetailsSearchCriteria advanceDetailsSearchCriteria) {
        AdvanceDetailsSearchRequest advanceDetailsSearchRequest = new AdvanceDetailsSearchRequest();
        addSecurityContext(securityContext, advanceDetailsSearchRequest);
        advanceDetailsSearchRequest.addAdvanceDetailsCriteria(advanceDetailsSearchCriteria);
        return advanceDetailsService.getTransactionAdvanceReport(advanceDetailsSearchRequest);
    }
}
