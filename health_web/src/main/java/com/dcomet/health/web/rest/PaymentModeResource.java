package com.dcomet.health.web.rest;

import com.dcomet.module.billing.domain.PaymentModeDetails;
import com.dcomet.module.billing.domain.PaymentModeSearchCriteria;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.health.service.business.BillingService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
 * @author KS
 */
@Component
@Path("pmd/v1")
public class PaymentModeResource extends BaseResource {

    @Autowired
    @Qualifier("billingService")
    public BillingService billingService;

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PaymentModeDetails> search(@Context final SecurityContext securityContext, PaymentModeSearchCriteria paymentModeSearchCriteria) {
        PaymentModeSearchRequest paymentModeSearchRequest = new PaymentModeSearchRequest();
        addSecurityContext(securityContext, paymentModeSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("pmdEntityRID", paymentModeSearchRequest.getEntityRid()));
        paymentModeSearchRequest.setSearchCriterionList(searchCriterionList);
        paymentModeSearchRequest.addPaymentModeCriteria(paymentModeSearchCriteria);
        return billingService.getPaymentMode(paymentModeSearchRequest);
    }
}
