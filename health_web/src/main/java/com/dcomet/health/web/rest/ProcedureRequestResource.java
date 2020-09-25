package com.dcomet.health.web.rest;

import com.dcomet.health.domain.ProcedureRequestH;
import com.dcomet.health.domain.ProcedureRequestHSearchCriteria;
import com.dcomet.health.domain.ProcedureRequestHSearchRequest;
import com.dcomet.health.service.business.ProcedureRequestService;
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

/**
 *
 * @author ABDUL
 */
@Component
@Path("procedurerequest/v1")
public class ProcedureRequestResource extends BaseResource {

    @Autowired
    @Qualifier("procedureRequestService")
    public ProcedureRequestService procedureRequestService;

    @POST
    @Path("/procedurerequest/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, ProcedureRequestH procedureRequestH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, procedureRequestH);
        return procedureRequestService.save(procedureRequestH, boRID, boCode, actionCode);
    }

    @POST
    @Path("/procedureRequest/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ProcedureRequestH> searchProcedureRequestH(@Context final SecurityContext securityContext, ProcedureRequestHSearchCriteria procedureRequestHSearchCriteria) {
        ProcedureRequestHSearchRequest procedureRequestHSearchRequest = new ProcedureRequestHSearchRequest();
        procedureRequestHSearchRequest.addProcedureRequestHSearchCriteria(procedureRequestHSearchCriteria);
        return procedureRequestService.getProcedureRequestH(procedureRequestHSearchRequest, true);
    }
}
