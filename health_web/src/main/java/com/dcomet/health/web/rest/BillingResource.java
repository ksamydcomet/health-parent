package com.dcomet.health.web.rest;

import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchCriteria;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.HealthBillH;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.billing.domain.BillHSearchCriteria;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.SalesH;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchCriteria;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.PharmacyService;
import com.dcomet.module.billing.domain.ReceiptH;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.collections.CollectionUtils;
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
@Path("billing/v1")
public class BillingResource extends BaseResource {

    @Autowired
    @Qualifier("billingService")
    public BillingService billingService;

    @Autowired
    @Qualifier("clinicalService")
    public ClinicalService clinicalService;

    @Autowired
    @Qualifier("pharmacyService")
    public PharmacyService pharmacyService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public BillH save(@Context final SecurityContext securityContext, HealthBillH healthBillH, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, healthBillH);
        billingService.save(healthBillH, boRID, boCode, actionCode);
        return healthBillH;
    }

    @POST
    @Path("/savedraft")
    @Consumes("application/json")
    @Produces("application/json")
    public BillH save(@Context final SecurityContext securityContext, BillH billH) {
        BillH billHObj = new BillH();
        addSecurityContext(securityContext, billH);
        billHObj = billingService.saveBillH(billH, true);
        return billHObj;
    }

    @POST
    @Path("/savebillwithcondition")
    @Consumes("application/json")
    @Produces("application/json")
    public BillH saveBillwWithCondition(@Context final SecurityContext securityContext, BillH billH) {
        BillH billHObj = new BillH();
        addSecurityContext(securityContext, billH);
        billHObj = billingService.saveBillHWithCondition(billH, true);
        return billHObj;
    }

    @POST
    @Path("/savebillhalone")
    @Consumes("application/json")
    @Produces("application/json")
    public BillH savebillHAlone(@Context final SecurityContext securityContext, BillH billH) {
        BillH billHObj = new BillH();
        addSecurityContext(securityContext, billH);
        billHObj = billingService.saveBillHAlone(billH);
        return billHObj;
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<HealthBillH> search(@Context final SecurityContext securityContext, BillHSearchCriteria billHSearchCriteria) {
        List<HealthBillH> returnResult = new ArrayList<>();
        BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
        addSecurityContext(securityContext, billHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bhEntityRID", billHSearchRequest.getEntityRid()));
        billHSearchRequest.setSearchCriterionList(searchCriterionList);
        billHSearchRequest.addBillHCriteria(billHSearchCriteria);
        List<BillH> result = billingService.getBillH(billHSearchRequest, true);
        if (CollectionUtils.isNotEmpty(result)) {
            for (BillH billH : result) {
                HealthBillH healthBillH = new HealthBillH();
                healthBillH.setBillH(billH);
                Patient patient = clinicalService.getPatient(billH.getBhPatientRID());
                healthBillH.setPatient(Arrays.asList(patient));
                SalesH salesH = pharmacyService.getSalesByBillId(billH.getId());
                if (salesH != null) {
                    healthBillH.setSalesH(Arrays.asList(salesH));
                }
                returnResult.add(healthBillH);
            }
        }
        return returnResult;
    }

    @POST
    @Path("/searchservicerequest")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceRequestH> searchServiceRequestByBill(@Context final SecurityContext securityContext, ServiceRequestHSearchCriteria serviceRequestHSearchCriteria) {
        ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
        addSecurityContext(securityContext, serviceRequestHSearchRequest);
        serviceRequestHSearchRequest.addServiceRequestCriteria(serviceRequestHSearchCriteria);
        return billingService.getServiceRequestByBill(serviceRequestHSearchRequest);
    }

    @POST
    @Path("/searchmaterialrequest")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DrugRequestH> searchMaterialRequestByBill(@Context final SecurityContext securityContext, DrugRequestHSearchCriteria drugRequestHSearchCriteria) {
        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
        addSecurityContext(securityContext, drugRequestHSearchRequest);
        drugRequestHSearchRequest.addDrugRequestSearchCriteria(drugRequestHSearchCriteria);
        return billingService.getmaterialRequestByBill(drugRequestHSearchRequest);
    }

    @POST
    @Path("/viewTransaction/search")
    @Consumes("application/json")
    @Produces("application/json")
    public String getTransactionReport(@Context final SecurityContext securityContext, BillHSearchCriteria billHSearchCriteria) {
        BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
        addSecurityContext(securityContext, billHSearchRequest);
        billHSearchRequest.addBillHCriteria(billHSearchCriteria);
        return billingService.getTransactionReport(billHSearchRequest);
    }

    @POST
    @Path("/payerReport")
    @Consumes("application/json")
    @Produces("application/json")
    public String getPayerReport(@Context final SecurityContext securityContext, BillHSearchCriteria billHSearchCriteria) throws Exception {
        BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
        addSecurityContext(securityContext, billHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bhEntityRID", billHSearchRequest.getEntityRid()));
        billHSearchRequest.setSearchCriterionList(searchCriterionList);
        billHSearchRequest.addBillHCriteria(billHSearchCriteria);
        return billingService.getPayerReport(billHSearchRequest);
    }

    @POST
    @Path("/saveReceipt/{checkValueForUpdateBill}")
    @Consumes("application/json")
    @Produces("application/json")
    public ReceiptH save(@Context final SecurityContext securityContext, ReceiptH parent, @PathParam("checkValueForUpdateBill") final Integer checkValueForUpdateBill) {
        addSecurityContext(securityContext, parent);
        if (parent.getRhUnitRID() == null) {
            parent.setRhUnitRID(parent.getUnitRid());
        }
        billingService.saveReceipt(parent, checkValueForUpdateBill);
        return parent;
    }
}
