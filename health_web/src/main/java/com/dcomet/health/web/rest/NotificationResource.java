package com.dcomet.health.web.rest;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.workflow.domain.BOAction;
import com.dcomet.fw.workflow.domain.BOMaster;
import com.dcomet.fw.workflow.domain.BOWorkList;
import com.dcomet.fw.workflow.domain.BOWorkListConfig;
import com.dcomet.fw.workflow.domain.BOWorkListConfigSearchRequest;
import com.dcomet.fw.workflow.domain.BOWorkListSettings;
import com.dcomet.fw.workflow.service.NotificationService;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.module.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
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
 * @author Dev4
 */
@Component
@Path("notification")
public class NotificationResource extends BaseResource {

    @Autowired
    @Qualifier("notificationService")
    public NotificationService notificationService;

    @Autowired
    @Qualifier("workFlowService")
    public WorkFlowService workFlowService;

    @GET
    @Path("/wl/{id}/{date}")
    @Produces("application/json")
    public List<BOWorkList> wlSearch(@Context final SecurityContext securityContext, @PathParam("id") final Integer unitId, @PathParam("date") final String worklistDate) {
        Base domain = new Base();
        addSecurityContext(securityContext, domain);
        domain.setUnitRid(unitId);
        return notificationService.loadWorklistSummary(domain, worklistDate);
    }

    @GET
    @Path("/worklisttask/{bowBowcRid}/{userUnitRID}/{date}")
    @Produces("application/json")
    public List<BOWorkList> searchWorklistTask(@Context final SecurityContext securityContext,
            @PathParam("bowBowcRid") final Integer bowBowcRid,
            @PathParam("userUnitRID") final Integer userUnitRID,
            @PathParam("date") final String worklistDate) {
        User user = getLoginUser(securityContext);
        return notificationService.getWorklistTasks(bowBowcRid, user.getEntityRid(), user.getId(), userUnitRID, worklistDate);
    }

    @GET
    @Path("/worklistaction/{boTypeIndex}/{boRID}")
    @Produces("application/json")
    public List<BOAction> getPermittedActions(@Context final SecurityContext securityContext, @PathParam("boTypeIndex") final Integer boTypeIndex,
            @PathParam("boRID") final Integer boRID) {
        User user = getLoginUser(securityContext);
        return workFlowService.getPermittedActions(boTypeIndex, boRID, user.getEntityRid());
    }

    @GET
    @Path("/boCode/{boTypeIndex}")
    @Produces("application/json")
    public List<String> getMasterCode(@PathParam("boTypeIndex") final Integer boTypeIndex) {
        List<String> strCode = new ArrayList<>();
        String str = workFlowService.getBOMasterCode(boTypeIndex);
        strCode.add(str);
        return strCode;

    }

    @GET
    @Path("/worklist/config")
    @Produces("application/json")
    public List<BOWorkListConfig> getBOWorklistConfig(@Context final SecurityContext securityContext) {
        BOWorkListConfigSearchRequest bOWorkListConfigSearchRequest = new BOWorkListConfigSearchRequest();
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("bowcEntRid", getLoginUser(securityContext).getEntityRid()));
        bOWorkListConfigSearchRequest.setSearchCriterionList(criterions);
        return notificationService.getBOWorklistConfig(bOWorkListConfigSearchRequest);
    }

    @GET
    @Path("/worklist/master")
    @Produces("application/json")
    public List<BOMaster> getBOMaster() {
        return notificationService.getBOMaster();
    }

    @GET
    @Path("/worklist/settings")
    @Produces("application/json")
    public List<BOWorkListSettings> getBOWorkListSettings() {
        return notificationService.getBOWorklistSettings();
    }
}
