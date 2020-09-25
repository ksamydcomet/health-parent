package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.module.purchase.domain.PurchaseOrderHSearchCriteria;
import com.dcomet.module.purchase.domain.PurchaseOrderHSearchRequest;
import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.purchase.service.DCometPurchaseService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev2
 */
public class PurchaseOrderServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    WorkFlowService purchaseOrderService;
    
    DCometPurchaseService purchaseService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        purchaseOrderService = (WorkFlowService) configLocator
                .getBean("purchaseOrderService");
        purchaseService = (DCometPurchaseService) configLocator
                .getBean("purchaseService");
    }
//
//    @Test
//    public void testPurchaseOrderH() throws Throwable {
//
//        PurchaseOrderHSearchRequest parentSearchRequest = new PurchaseOrderHSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("pohNumber", "POH/102"));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
////
//        List<PurchaseOrderH> parentList = purchaseOrderService.getPurchaseOrderH(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (PurchaseOrderH purchaseOrderH : parentList) {
//            System.out.println(purchaseOrderH.getPohRemarks() + " : " + purchaseOrderH.getModifiedDateTime());
//        }
//////        
//        PurchaseOrderH purchaseOrderH = new PurchaseOrderH();
//
////        purchaseOrderH.setId(4);
//        purchaseOrderH.setPohNumber("POH/102");
//        purchaseOrderH.setPohPrefix("POH/");
//        purchaseOrderH.setPohSequence(1234);
//        String date = DateUtil.getCurrentDate();
////        purchaseOrderH.setPohDate(DateUtil.convertDateStringToDate(date));
//        purchaseOrderH.setPohSupplierRID(1);
//        purchaseOrderH.setPohFinanceYear(2015);
//        purchaseOrderH.setPohQuoteRID(1);
//        purchaseOrderH.setPohGrossAmount(12.25f);
//        purchaseOrderH.setPohTaxAmount(12.34f);
//        purchaseOrderH.setPohDiscountAmount(16.35f);
//        purchaseOrderH.setPohFreightAmount(13.35f);
//        purchaseOrderH.setPohNetAmount(44.45f);
//        purchaseOrderH.setPohRemarks("User Needed");
//        purchaseOrderH.setPohStatus(1);
//        purchaseOrderH.setEntityRid(4);
//        purchaseOrderH.setModifiedUserRid(1);
//        purchaseOrderH.setPohType(1);
//        purchaseOrderH.setPohGrnReceivableUnitRID(1);
//        purchaseOrderH.setPohStoreRID(1);
//        purchaseOrderH.setPohApprovedUserRID(1);
//        purchaseOrderH.setPohApprovedDateTime("2014-10-15 13:07:29");
//        purchaseOrderH.setPohInvoiceNumber("25");
//        purchaseOrderH.setPohInvoiceDate("2014-10-15");
//        purchaseOrderH.setPohCessAmount(12.34f);
//        purchaseOrderH.setPohRoundOffAmount(12.34f);
//        purchaseOrderH.setPohDeliveryDate("2014-10-15");
//        purchaseOrderH.setPohTermsOfDelivery("dsg");
//        purchaseOrderH.setPohParentRID(1);
//        purchaseOrderH.setPohVersion(1);
//        purchaseOrderH.setCreatedUserRid(1);
//        purchaseOrderH.setPohAdvanceAmount(12.23f);
//        purchaseOrderH.setPohRejectionRemarks("dsg");
//        purchaseOrderH.setPohCreditPeriod(1);
//        purchaseOrderH.setCreatedUserRid(1);
//
////
//        List<PurchaseOrderD> childpurchaseOrderD = new ArrayList<PurchaseOrderD>();
//
//        PurchaseOrderD purchaseOrderD = new PurchaseOrderD();
////        purchaseOrderD.setId(4);
//
//        purchaseOrderD.setPodPohRID(5);
//        purchaseOrderD.setPodSkuRID(11);
//        purchaseOrderD.setPodSkuName("Stenil Red Block");
//        purchaseOrderD.setPodQty(12.34f);
//        purchaseOrderD.setPodReceiptQty(12.34f);
//        purchaseOrderD.setPodFreeQty(12.34f);
//        purchaseOrderD.setPodRate(12.34f);
//        purchaseOrderD.setPodMrp(12.34f);
//        purchaseOrderD.setPodTaxPercentage(12.34f);
//        purchaseOrderD.setPodTaxAmount(12.34f);
//        purchaseOrderD.setPodDiscountPercentage(12.34f);
//        purchaseOrderD.setPodDiscountAmount(12.34f);
//        purchaseOrderD.setPodNetAmount(12.34f);
//        purchaseOrderD.setPodStatus(1);
//        purchaseOrderD.setPodRowInvalidated(1);
//        purchaseOrderD.setPodModifiedUserRID(1);
//        purchaseOrderD.setPodModifiedDateTime("2014-10-15 13:07:29");
//        purchaseOrderD.setPodBatchNo("12");
//        purchaseOrderD.setPodExpiryDate("2014-10-15");
//        purchaseOrderD.setPodProcessedDate("2014-10-15");
//        purchaseOrderD.setPodTaxApplicabilityOn(1);
//        purchaseOrderD.setPodCessPercentage(13.05f);
//        purchaseOrderD.setPodCessAmount(13.05f);
//        purchaseOrderD.setPodClosingStock(13.05f);
//        purchaseOrderD.setPodSkuAvgLeadTime("12.00");
//        purchaseOrderD.setPodSafetyStock(13.05f);
//        purchaseOrderD.setPodReOrderLevel(1);
//        purchaseOrderD.setPodEoqf("12");
//        purchaseOrderD.setPodParentRID(1);
//
//        childpurchaseOrderD.add(purchaseOrderD);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(childpurchaseOrderD));
//        purchaseOrderService.savePurchaseOrderD(childpurchaseOrderD);
//
////        purchaseOrderH.setUserId("adminblr");
////        purchaseOrderH.setUserRid(1);
////        purchaseOrderH.setEntityRid(4);
////        purchaseOrderH.set
////        purchaseOrderH.setsetChildList(childpurchaseOrderD);
////        Base domainObj = new Base();
////        User user = getLoginUser(securityContext);
////        System.out.println("Poh number :" + user.getUserEntityRID());
////        System.out.println("Poh number :" + user.getId());
////        domainObj.setEntityRid(4);
////        domainObj.setUserRid(1);
////        domainObj.setUnitRid(4);
////        Gson gson = new Gson();
////        System.out.println(gson.toJson(purchaseOrderH));
////        String str = purchaseOrderService.save(purchaseOrderH, 1, "PURCHASE_ORDER", "SUBMIT", domainObj);
////        System.out.println("PohNumber : "+str);
////
//    }

    @Test
    public void testAddFormBODescriptor() throws Throwable {
        PurchaseOrderHSearchCriteria purchaseOrderHSearchCriteria = new PurchaseOrderHSearchCriteria();
//        purchaseOrderHSearchCriteria.setId(5);
        purchaseOrderHSearchCriteria.setPohFromDate(DateUtil.convertStringToCalendar("2016-04-26 00:00:00"));
        purchaseOrderHSearchCriteria.setPohToDate(DateUtil.convertStringToCalendar("2016-04-27 00:00:00"));
        PurchaseOrderHSearchRequest purchaseOrderHSearchRequest = new PurchaseOrderHSearchRequest();
        purchaseOrderHSearchRequest.addPurchaseOrderHSearchCriteria(purchaseOrderHSearchCriteria);
        purchaseService.getPurchaseOrderH(purchaseOrderHSearchRequest, true);
    }
}
