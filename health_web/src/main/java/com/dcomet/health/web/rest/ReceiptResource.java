package com.dcomet.health.web.rest;

import com.dcomet.module.billing.domain.ReceiptH;
import com.dcomet.module.billing.domain.ReceiptHSearchCriteria;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;
import com.dcomet.health.service.business.ReceiptService;
import com.dcomet.module.billing.domain.ReceiptD;
import com.dcomet.module.billing.domain.ReceiptDSearchCriteria;
import com.dcomet.module.billing.domain.ReceiptDSearchRequest;
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
 * @author Dev1
 */
@Component
@Path("receipt/v1")
public class ReceiptResource extends BaseResource {

    @Autowired
    @Qualifier("receiptService")
    public ReceiptService receiptService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public ReceiptH save(@Context final SecurityContext securityContext, ReceiptH receiptH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, receiptH);
        receiptService.save(receiptH, boRID, boCode, actionCode);
        return receiptH;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ReceiptH> search(@Context final SecurityContext securityContext, ReceiptHSearchCriteria receiptHSearchCriteria) {
        ReceiptHSearchRequest receiptHSearchRequest = new ReceiptHSearchRequest();
        addSecurityContext(securityContext, receiptHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("rhEntityRID", receiptHSearchRequest.getEntityRid()));
        receiptHSearchRequest.setSearchCriterionList(searchCriterionList);
        receiptHSearchRequest.addReceiptHCriteria(receiptHSearchCriteria);
        return receiptService.getReceiptH(receiptHSearchRequest, true);
    }

    @POST
    @Path("/searchreceiptd")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ReceiptD> search(@Context final SecurityContext securityContext, ReceiptDSearchCriteria ReceiptDSearchCriteria) {
        addSecurityContext(securityContext, ReceiptDSearchCriteria);
        ReceiptDSearchRequest receiptDSearchRequest = new ReceiptDSearchRequest();
        receiptDSearchRequest.addReceiptDCriteria(ReceiptDSearchCriteria);
        return receiptService.getReceiptDForBill(receiptDSearchRequest);
    }

    @POST
    @Path("/receiptTransaction")
    @Consumes("application/json")
    @Produces("application/json")
    public String getTransactionReceiptH(@Context final SecurityContext securityContext, ReceiptHSearchCriteria receiptHSearchCriteria) {
        ReceiptHSearchRequest receiptHSearchRequest = new ReceiptHSearchRequest();
        addSecurityContext(securityContext, receiptHSearchRequest);
        receiptHSearchRequest.addReceiptHCriteria(receiptHSearchCriteria);
        return receiptService.getTransactionReceiptReport(receiptHSearchRequest);
    }

}
