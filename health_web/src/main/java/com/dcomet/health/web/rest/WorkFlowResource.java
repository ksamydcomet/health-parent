package com.dcomet.health.web.rest;

import com.dcomet.fw.workflow.service.WorkFlowService;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev1
 */
@Component
@Path("workflow")
public class WorkFlowResource extends BaseResource {

    @Autowired
    @Qualifier("workFlowService")
    public WorkFlowService workFlowService;

//    @GET
//    @Path("/worklistaction/{boTypeIndex}/{boRID}")
//    @Produces("application/json")
//    public List<BOAction> getPermittedActions(@PathParam("boTypeIndex") final Integer boTypeIndex, @PathParam("boRID") final Integer boRID) {
//        return workFlowService.getPermittedActions(boTypeIndex, boRID);
//    }
}
