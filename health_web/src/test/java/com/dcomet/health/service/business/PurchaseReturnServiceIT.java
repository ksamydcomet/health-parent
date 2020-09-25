package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.module.purchase.domain.PurchaseReturnD;
import com.dcomet.module.purchase.domain.PurchaseReturnH;
import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.purchase.service.DCometPurchaseService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev2
 */
public class PurchaseReturnServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    PurchaseService purchaseService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        purchaseService = (PurchaseService) configLocator
                .getBean("purchaseService");
    }

    //-----Purchase ReturnH
    @Test
    public void testPurchaseReturnH() throws Throwable {

//        PurchaseReturnHSearchRequest parentSearchRequest = new PurchaseReturnHSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("prhNo", "POR/102"));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<PurchaseReturnH> parentList = purchaseReturnService.getPurchaseReturnH(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (PurchaseReturnH purchaseReturnH : parentList) {
//            System.out.println(purchaseReturnH.getPrhSupplierName() + " : " + purchaseReturnH.getPrhReturnAmount());
//        }
        PurchaseReturnH purchaseReturnH = new PurchaseReturnH();
//        parent.setId(851);
        purchaseReturnH.setPrhNo("12345");
        purchaseReturnH.setPrhSupplierRID(1);
        purchaseReturnH.setPrhSupplierName("Ajith");
        purchaseReturnH.setPrhStatus(1);
        purchaseReturnH.setPrhState(1);
        purchaseReturnH.setPrhReturnAmount(77.66f);
        purchaseReturnH.setEntityRid(1);
        purchaseReturnH.setModifiedUserRid(1);
        purchaseReturnH.setPrhNumber("123");
        purchaseReturnH.setPrhGatePassNo("63");
        purchaseReturnH.setPrhGrossAmount(57.68f);
        purchaseReturnH.setPrhTaxAmount(47.56f);
        purchaseReturnH.setPrhRoundOffAmount(94.37f);
        purchaseReturnH.setPrhTypeIndex(1);
        purchaseReturnH.setPrhUnitRID(1);
        purchaseReturnH.setPrhStateTypeIndex(1);
        purchaseReturnH.setCreatedUserRid(1);
        purchaseReturnH.setPrhGrhRID(1);
        purchaseReturnH.setPrhMatrixType(1);

        Gson gson = new Gson();
        System.out.println(gson.toJson(purchaseReturnH));
        purchaseService.savePurchaseReturnH(purchaseReturnH, true);

//
        List<PurchaseReturnD> childpurchaseReturnD = new ArrayList<>();
//
        PurchaseReturnD purchaseReturnD = new PurchaseReturnD();

        purchaseReturnD.setPrdPrhRID(2);
        purchaseReturnD.setPrdSkuRID(1);
        purchaseReturnD.setPrdSkuName("Stenil");
        purchaseReturnD.setPrdBatchNo("POR/22");
        purchaseReturnD.setPrdBaseUomIndex(2);
        purchaseReturnD.setPrdBaseUomDesc("PR");
        purchaseReturnD.setPrdPrice(12.34f);
        purchaseReturnD.setPrdPodQty(43.34f);
        purchaseReturnD.setPrdQty(34.24f);
        purchaseReturnD.setPrdStatus(1);
        purchaseReturnD.setPrdRowInvalidated(1);
        purchaseReturnD.setPrdExpiryDate("2017-12-12");
        purchaseReturnD.setPrdPackDate(DateUtil.getCurrentDate());
        purchaseReturnD.setPrdGrossAmount(12.24f);
        purchaseReturnD.setPrdMrp(24.35f);
        purchaseReturnD.setPrdAbatedMrp(12.46f);
        purchaseReturnD.setPrdTaxApplicabilityOn(1);
        purchaseReturnD.setPrdTaxPerc(12.46f);
        purchaseReturnD.setPrdTaxAmount(12.46f);
        purchaseReturnD.setPrdNetAmount(12.46f);
        purchaseReturnD.setPrdGrdRID(1);

        childpurchaseReturnD.add(purchaseReturnD);

        Gson gsn = new Gson();
        System.out.println(gsn.toJson(purchaseReturnD));
        purchaseService.savePurchaseReturnD(childpurchaseReturnD);

//
//        List<StockLedger> childStockLedger = new ArrayList<StockLedger>();
////
//        StockLedger stockLedger = new StockLedger();
//        stockLedger.setSlTranType(1);
//        stockLedger.setSlTranRefNo("GRN/2001");
//        stockLedger.setSlSkuRID(1);
//        stockLedger.setSlBatchNo("");
//        stockLedger.setSlExppiryDate("");
//        stockLedger.setSlStockIn(10.00f);
//        stockLedger.setSlStockOut(0.00f);
//        stockLedger.setSlRemainQty(10.00f);
//        stockLedger.setSlStkRID(0);
//        stockLedger.setSlGrnOsRID(0);
//        stockLedger.setSlTranDate("2015-12-23");
//        stockLedger.setSlStatusConfirmCancel(16);
//        stockLedger.setSlCurrentDateTime("2015-12-23 11:03:00");
//        stockLedger.setSlEntityID(1);
//        stockLedger.setSlUnitRID(1);
//        stockLedger.setSlslRID(0);
//        childStockLedger.add(stockLedger);
//        purchaseReturnH.setChildList(childpurchaseReturnD);
//        purchaseReturnH.setChildStockLedger(childStockLedger);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(purchaseReturnH));
//        purchaseReturnService.savePurchaseReturnH(purchaseReturnH, true);
//    }
    }
}
