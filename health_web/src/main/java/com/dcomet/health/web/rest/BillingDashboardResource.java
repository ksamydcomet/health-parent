package com.dcomet.health.web.rest;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.dbd.DBillCollection;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import com.dcomet.health.service.business.DashBoardService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("dashboard/bill")
public class BillingDashboardResource extends BaseResource {

    @Autowired
    @Qualifier("dashBoardService")
    public DashBoardService dashBoardService;

    @GET
    @Path("/payment/today")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DBillCollection> getPayment(@Context final SecurityContext securityContext) {
        DashBoardSearchRequest dashBoardSearchRequest = new DashBoardSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("entRid", getLoginUser(securityContext).getEntityRid()));
        searchCriterionList.add(Restrictions.eq("tranDate", DateUtil.getToday()));
        dashBoardSearchRequest.setSearchCriterionList(searchCriterionList);
        return dashBoardService.getDBillCollection(dashBoardSearchRequest);
    }
}
