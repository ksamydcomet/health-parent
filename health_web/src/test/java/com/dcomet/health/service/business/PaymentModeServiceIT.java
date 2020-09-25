package com.dcomet.health.service.business;

import com.dcomet.module.billing.domain.PaymentModeDetails;
import com.dcomet.module.billing.domain.PaymentModeSearchCriteria;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PaymentModeServiceIT extends TestCase{

    private JUnitConfigLocator configLocator;
    BillingService billingService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        billingService = (BillingService) configLocator
                .getBean("billingService");
    }

    @Test
    public void testPmdDetails() throws Throwable {
        PaymentModeSearchRequest paymentModeSearchRequest = new PaymentModeSearchRequest();
        PaymentModeSearchCriteria paymentModeSearchCriteria = new PaymentModeSearchCriteria();
        paymentModeSearchCriteria.setId(33);
//        addSecurityContext(securityContext, paymentModeSearchRequest); 
//        paymentModeSearchRequest.setEntityCurrCode("IN");
        paymentModeSearchRequest.addPaymentModeCriteria(paymentModeSearchCriteria);
        List<PaymentModeDetails> advaResult = billingService.getPaymentMode(paymentModeSearchRequest);
        Gson ss = new Gson();
        System.out.println(ss.toJson(advaResult));
//
////        AdvanceDetailsSearchRequest parentSearchRequest = new AdvanceDetailsSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.like("adNo", "ADV/15"));
////        parentSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("adRefRID", false));
////        parentSearchRequest.setSortOrder(orderList);
////
////        List<AdvanceDetails> parentList = advanceDetailsService.getAdvanceDetails(parentSearchRequest, true);
////        System.out.println("parentList.getResultList()>>" + parentList.getResultList().size());
////        for (AdvanceDetails advanceDetails : parentList.getResultList()) {
////            System.out.println(advanceDetails.getAdEntityRID() + " : " + advanceDetails.getAdModDatetime());
////        }
//        AdvanceDetails advanceDetails = new AdvanceDetails();
////        advanceDetails.setId(27);
//        advanceDetails.setAdNo("ADV/35");
//        advanceDetails.setAdPrefix("ADV/");
//        advanceDetails.setAdPrintableNo("ADV/35");
//        advanceDetails.setAdType(1);
//        advanceDetails.setAdRefRID(559);
//        advanceDetails.setAdPayerRID(1);
//        advanceDetails.setAdPayerType(1);
//        advanceDetails.setAdPayerNo("");
//        advanceDetails.setAdPayerName("");
//        advanceDetails.setAdIncidentRID(2);
//        advanceDetails.setAdIncidentType(1);
////        advanceDetails.setAdDate("");
//        advanceDetails.setAdAmount(1.00f);
//        advanceDetails.setAdAdjustedAmount(1.00f);
//        advanceDetails.setAdRefundedAmount(1.00f);
//        advanceDetails.setAdStatus(1);
//        advanceDetails.setAdEntityRID(1);
//        advanceDetails.setAdRemarks("");
//        advanceDetails.setAdUnitRID(1);
//        advanceDetails.setAdUserRID(1);
////        advanceDetails.setAdCreatedDatetime("");
////        advanceDetails.setAdModUserRID(1);
////        advanceDetails.setAdModDatetime("");
//        advanceDetails.setAdRowInvalidated("");
//        advanceDetails.setAdRowHistoryID(1);
////        advanceDetails.setAdCancelledDate("");
//        advanceDetails.setAdContextType(1);
//        advanceDetails.setAdContextRID(1);
//        advanceDetails.setAdIstransferred(true);
//        advanceDetails.setAdCreationMode(1);
//        advanceDetails.setAdCancellationMode(1);
//        advanceDetails.setAdRecEntityRID(1);
//        List<PaymentModeDetails> childs = new ArrayList<PaymentModeDetails>();
//
//        PaymentModeDetails paymentModeDetails = new PaymentModeDetails();
//        paymentModeDetails.setPmdTransRID(14);
//        paymentModeDetails.setPmdTransType(1);
//        paymentModeDetails.setPmdPaymentMode(1);
//        paymentModeDetails.setPmdCardType("");
//        paymentModeDetails.setPmdDocNo("");
//        paymentModeDetails.setPmdDocDate("");
//        paymentModeDetails.setPmdDocExpDate("");
//        paymentModeDetails.setPmdDocApprovalNo("");
//        paymentModeDetails.setPmdBankName("");
//        paymentModeDetails.setPmdBankDetails("");
//        paymentModeDetails.setPmdAmount(1.00f);
//        paymentModeDetails.setPmdUserRID(1);
//        paymentModeDetails.setPmdEntityRID(1);
//        paymentModeDetails.setPmdDateTime("");
//        paymentModeDetails.setPmdCurrencyRID(1);
//        paymentModeDetails.setPmdCurrencyValue("0");
//        paymentModeDetails.setPmdCurrencyExchangeRate(1.00f);
//        paymentModeDetails.setPmdDisplaySymbol("");
//        paymentModeDetails.setPmdRemarks("");
//        paymentModeDetails.setPmdCardHolderName("");
//        childs.add(paymentModeDetails);
//
//        advanceDetails.setPaymentModeDetails(childs);
//        advanceDetailsService.save(advanceDetails, true);
    }
}
