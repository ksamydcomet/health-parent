package com.dcomet.health.service.business;

import com.dcomet.health.service.JUnitConfigLocator;
import junit.framework.TestCase;
import org.junit.Before;

public class BillingServiceIT extends TestCase {

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

//    @Test
//    public void testBillH() throws Throwable {

//        BillHSearchRequest parentSearchRequest = new BillHSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("bhBillNo", "101"));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("bhReferenceRID", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<BillH> parentList = billingService.getBillH(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
//        for (BillH billH : parentList.getListList()) {
//            System.out.println(billH.getBhPatientNo() + " : " + billH.getBhRemarks());
//        }
//        
//        ReceiptHSearchRequest receiptSearchRequest = new ReceiptHSearchRequest();
//        List<Criterion> receiptSearchCriterionList = new ArrayList<Criterion>();
//        receiptSearchCriterionList.add(Restrictions.like("rhNo", "REC/5"));
//        receiptSearchRequest.setSearchCriterionList(receiptSearchCriterionList);
//        List<CriteriaOrder> receiptOrderList = new ArrayList<CriteriaOrder>();
//        receiptOrderList.add(new CriteriaOrder("rhPayerRID", false));
//        receiptSearchRequest.setSortOrder(receiptOrderList);
//
//        List<ReceiptH> receiptList = billingService.getReceiptH(receiptSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + receiptList.getListList().size());
//        for (ReceiptH receiptH : receiptList.getListList()) {
//            System.out.println(receiptH.getRhPayerName() + " : " + receiptH.getRhDate());
//        }
//        BillH billH = new BillH();
////        billH.setId(2);
//        billH.setBhBillNo("101");
//        billH.setBhPrefix("GRN/2001");
//        billH.setBhPrintableBillNo("2001");
//        billH.setBhPatientRID(1);
//        billH.setBhPatientNo("Greefin");
//        billH.setBhPatientVisitRID("");
//        billH.setBhReferenceRID(1);
//        billH.setBhType(1);
//        billH.setBhPayerType("");
//        billH.setBhPayerRID(1);
//        billH.setBhBillDate("");
//        billH.setBhStatus(0);
//        billH.setBhGrossAmount(0.00f);
//        billH.setBhTotalDiscountAmount(0.00f);
//        billH.setBhTotalTaxAmount(0.00f);
//        billH.setBhNetAmount(0.00f);
//        billH.setBhDiscount(0.00f);
//        billH.setBhIsDiscountPercentage(10);
//        billH.setBhEntityRID(1);
//        billH.setBhRemarks("Remarks");
//        billH.setBhUnitRID(1);
//        billH.setBhUserRID(1);
//        billH.setBhCreatedDateTime("24-12-2015");
//        billH.setBhModUserRID(1);
//        billH.setBhModDateTime("24-12-2015");
//        billH.setBhDcmRID(1);
//        billH.setBhDiscountReasonRID(1);
//        billH.setBhDiscountApprovalUserRID(1);
//        billH.setBhVersion(1);
//        billH.setBhState(1);
//        billH.setBhDraftBillNo("123");
//        billH.setBhIsDraft(1);
//        billH.setBhPayerAmount(1.00f);
//        billH.setBhDueAmount(1.00f);
//        billH.setBhPaidAmount(1f);
//
//        List<BillD> childs = new ArrayList<BillD>();
//
//        BillD billD = new BillD();
//        billD.setBdBhRID(1);
//        billD.setBdChargeRID(1);
//        billD.setBdChargeCode("Item1");
//        billD.setBdChargeType("");
//        billD.setBdChargeName("");
//        billD.setBdGroupRID(1);
//        billD.setBdItemRID(1);
//        billD.setBdServiceName("");
//        billD.setBdQty(100.00f);
//        billD.setBdPrice(100.00f);
//        billD.setBdGrossAmount(0.00f);
//        billD.setBdTotalDiscountAmount(0.00f);
//        billD.setBdTotalTaxAmount(1000.00f);
//        billD.setBdTaxPercent(1000.00f);
//        billD.setBdNetAmount(0.00f);
//        billD.setBdPatientRID(1);
//        billD.setBdPatientAmount(1000.00f);
//        billD.setBdIsDiscountPercentage(1);
//        billD.setBdDiscountValue(1.00f);
//        billD.setBdIsItemLevelDiscountGiven(0);
//        billD.setBdDiscountReasonRID(0);
//        billD.setBdDiscountVoucherNo("");
//        billD.setBdDiscountApprovalUserRID(1);
//        billD.setBdIsItemOrderedFromBIll(1);
//        billD.setBdIsManualInclusion(1);
//        billD.setBdIsDsicountApproved(1);
//        childs.add(billD);
//
//        billH.setBillD(childs);
//        billingService.saveBillH(billH, true);
//
//    }
////    
//
//    @Test
//    public void testReceiptH() throws Throwable {
//        ReceiptH receiptH = new ReceiptH();
////        receiptH.setId(2);
//        receiptH.setRhNo("REC/2");
//        receiptH.setRhPrefix("REC/");
//        receiptH.setRhPrintableNo("REC/2");
//        receiptH.setRhType(1);
//        receiptH.setRhDate("2015-12-24");
//        receiptH.setRhPayerRID(559);
//        receiptH.setRhPayerType(1);
//        receiptH.setRhPayerNo("");
//        receiptH.setRhPayerName("Greefin");
//        receiptH.setRhIncidentRID(1);
//        receiptH.setRhIncidentType(1);
//        receiptH.setRhTotalAmount(0.00f);
//        receiptH.setRhPaidAmount(0.00f);
//        receiptH.setRhStatus(1);
//        receiptH.setRhEntityRID(1);
//        receiptH.setRhUnitRID(1);
//        receiptH.setRhUserRID(1);
////        receiptH.setRhCreatedDatetime("2015-12-24 11:05:42");
////        receiptH.setRhModUserRID(1);
////        receiptH.setRhModDatetime("");
//        receiptH.setRhRowInvalidated(1);
//        receiptH.setRhRowHistoryID(1);
//        receiptH.setRhAdvanceAdjustedAmount(0.00f);
//        receiptH.setRhRemarks("");
//        receiptH.setRhTdsAmount(12.20f);
////        receiptH.setRhCancelledDatetime("");
//        receiptH.setRhCreditAdjustedAmount(10.00f);
//        receiptH.setRhCreationMode(1);
//        receiptH.setRhCancellationMode(1);
//        receiptH.setRhReceivedOn(1);
////        receiptH.setRhDatetime("2015-12-24 6:05:42");
//        receiptH.setRhPrintCounter(1);
//        receiptH.setRhApproverUserRID(1);
//
//        List<ReceiptD> childs = new ArrayList<ReceiptD>();
//
//        ReceiptD receiptD = new ReceiptD();
//        receiptD.setRdRhRID(1);
//        receiptD.setRdBillRID(1);
//        receiptD.setRdAdvRID(1);
//        receiptD.setRdBillAmount(12.35f);
//        receiptD.setRdPaidAmount(60.78f);
//        receiptD.setRdAdvAdjustedAmount(13.45f);
//        receiptD.setRdTdsAmount(12.35f);
//        receiptD.setRdCreditAdjustedAmount(20.00f);
//        childs.add(receiptD);
//
//        List<PaymentModeDetails> childPaymentModeDetails = new ArrayList<PaymentModeDetails>();
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
//        childPaymentModeDetails.add(paymentModeDetails);
//
//        receiptH.setReceiptD(childs);
//        receiptH.setPaymentModeDetailsList(childPaymentModeDetails);
//        billingService.saveReceiptH(receiptH, true);
//    }
//    @Test
//    public void testCurrencyConversion() throws Throwable {
//        CurrencyConversion currencyConversion = new CurrencyConversion();
////        parent.setId(27);
//        currencyConversion.setCcCurrencyRIDBase(2);
//        currencyConversion.setCcCurrencyRIDTo(45);
//        currencyConversion.setCcConversionFactor(23.50);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(currencyConversion));
//        billingService.saveCurrencyConversion(currencyConversion);
//    }

}
