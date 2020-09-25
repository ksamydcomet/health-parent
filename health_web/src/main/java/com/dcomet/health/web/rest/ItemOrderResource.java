package com.dcomet.health.web.rest;

import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchCriteria;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.FavouriteItemOrderH;
import com.dcomet.health.domain.FavouriteItemOrderHSearchCriteria;
import com.dcomet.health.domain.FavouriteItemOrderHSearchRequest;
import com.dcomet.health.domain.ItemOrder;
import com.dcomet.health.domain.ItemOrderSearchCriteria;
import com.dcomet.health.domain.ItemOrderSearchRequest;
import com.dcomet.health.service.business.ItemOrderService;
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
 * @author Dev4
 */
@Component
@Path("itemorder/v1")
public class ItemOrderResource extends BaseResource {

    @Autowired
    @Qualifier("itemOrderService")
    public ItemOrderService itemOrderService;

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FavouriteItemOrderH> search(FavouriteItemOrderHSearchCriteria favouriteItemOrderHSearchCriteria) {
        FavouriteItemOrderHSearchRequest favouriteItemOrderHSearchRequest = new FavouriteItemOrderHSearchRequest();
        favouriteItemOrderHSearchRequest.addFavouriteItemOrderHSearchCriteria(favouriteItemOrderHSearchCriteria);
        return itemOrderService.getFavouriteItemOrderH(favouriteItemOrderHSearchRequest, true);
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, FavouriteItemOrderH favouriteItemOrderH) {
        addSecurityContext(securityContext, favouriteItemOrderH);
        itemOrderService.saveFavouriteItemOrderH(favouriteItemOrderH, true);
    }

    @POST
    @Path("/drugs/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, DrugRequestH drugRequestH) {
        addSecurityContext(securityContext, drugRequestH);
        return itemOrderService.saveDrugH(drugRequestH, true);
    }

    @POST
    @Path("/drugs/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DrugRequestH> search(@Context final SecurityContext securityContext, DrugRequestHSearchCriteria drugRequestHSearchCriteria) {
        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
        addSecurityContext(securityContext, drugRequestHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("drugReqHEntityRID", drugRequestHSearchRequest.getEntityRid()));
        drugRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        drugRequestHSearchRequest.addDrugRequestSearchCriteria(drugRequestHSearchCriteria);
        return itemOrderService.getDrugH(drugRequestHSearchRequest, true);

    }

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public DrugRequestH save(@Context final SecurityContext securityContext, DrugRequestH drugRequestH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, drugRequestH);
        itemOrderService.save(drugRequestH, boRID, boCode, actionCode);
        return drugRequestH;
    }

    @POST
    @Path("/drugsprint/search")
    @Consumes("application/json")
    @Produces("application/json")
    public String getDrugPrint(@Context final SecurityContext securityContext, DrugRequestHSearchCriteria drugRequestHSearchCriteria) {
        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
        addSecurityContext(securityContext, drugRequestHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("drugReqHEntityRID", drugRequestHSearchRequest.getEntityRid()));
        drugRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        drugRequestHSearchRequest.addDrugRequestSearchCriteria(drugRequestHSearchCriteria);
        return itemOrderService.getDrugPrintService(drugRequestHSearchRequest);
    }

    @POST
    @Path("/itemorder/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ItemOrder> search(@Context final SecurityContext securityContext, ItemOrderSearchCriteria itemOrderSearchCriteria) {
        ItemOrderSearchRequest searchRequest = new ItemOrderSearchRequest();
        addSecurityContext(securityContext, searchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("ioEntityRid", searchRequest.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addItemOrderCriteria(itemOrderSearchCriteria);
        return itemOrderService.getItemOrder(searchRequest);
    }

    @POST
    @Path("/itemorder/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveItemOrder(@Context final SecurityContext securityContext, List<ItemOrder> itemOrderList) {
        for (ItemOrder itemOrder : itemOrderList) {
            addSecurityContext(securityContext, itemOrder);
        }
        itemOrderService.saveItemOrder(itemOrderList);
    }
}
