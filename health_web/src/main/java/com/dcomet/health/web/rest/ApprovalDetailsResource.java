package com.dcomet.health.web.rest;

import com.dcomet.health.domain.ApprovalDetails;
import com.dcomet.health.domain.ApprovalDetailsSearchCriteria;
import com.dcomet.health.domain.ApprovalDetailsSearchRequest;
import com.dcomet.health.service.business.ApprovalDetailsService;
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
@Path("approvalDetails/v1")
public class ApprovalDetailsResource extends BaseResource {

    @Autowired
    @Qualifier("approvalDetailsService")
    public ApprovalDetailsService approvalDetailsService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public ApprovalDetails save(@Context final SecurityContext securityContext, ApprovalDetails approvalDetails, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, approvalDetails);
        approvalDetailsService.save(approvalDetails, boRID, boCode, actionCode);
        return approvalDetails;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ApprovalDetails> search(@Context final SecurityContext securityContext, ApprovalDetailsSearchCriteria approvalDetailsSearchCriteria) {
        ApprovalDetailsSearchRequest approvalDetailsSearchRequest = new ApprovalDetailsSearchRequest();
        addSecurityContext(securityContext, approvalDetailsSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("appEntityRid", approvalDetailsSearchRequest.getEntityRid()));
        approvalDetailsSearchRequest.setSearchCriterionList(searchCriterionList);
        approvalDetailsSearchRequest.addApprovalDetailsCriteria(approvalDetailsSearchCriteria);
        return approvalDetailsService.getApprovalDetails(approvalDetailsSearchRequest);
    }

}
