package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.module.purchase.domain.GoodsReceiptD;
import com.dcomet.module.purchase.domain.GoodsReceiptH;
import com.dcomet.module.inventory.domain.StockTransaction;
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
public class GoodsReceiptServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    DCometPurchaseService purchaseService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        purchaseService = (PurchaseService) configLocator
                .getBean("purchaseService");
    }

    //-----Goods ReceiptH
    @Test
    public void testGoodsReceiptH() throws Throwable {
//
////        GoodsReceiptHSearchRequest parentSearchRequest = new GoodsReceiptHSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.like("grhNo", "GRN/2002"));
////        parentSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("grhDcNo", false));
////        parentSearchRequest.setSortOrder(orderList);
////
////        List<GoodsReceiptH> parentList = goodsReceiptService.getGoodsReceiptH(parentSearchRequest, true);
////        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
////        for (GoodsReceiptH goodsReceiptH : parentList.getListList()) {
////            System.out.println(goodsReceiptH.getGrhSupplierName() + " : " + goodsReceiptH.getGrhRemarks());
////        }
        GoodsReceiptH goodsReceiptH = new GoodsReceiptH();
//      goodsReceiptH.setId(1);
        goodsReceiptH.setGrhNo("GRN/2001");
        goodsReceiptH.setGrhSequenceNo(1);
        goodsReceiptH.setGrhSupplierRID(1);
        goodsReceiptH.setGrhSupplierName("Suresh");
        goodsReceiptH.setGrhDcNo("DC/2001");
        goodsReceiptH.setGrhDcDate(DateUtil.getCurrentDate());
        goodsReceiptH.setGrhInvoiceNo("INV/2001");
        goodsReceiptH.setGrhInvoiceDate(DateUtil.getCurrentDate());
        goodsReceiptH.setGrhGrossAmount(1000.00f);
        goodsReceiptH.setGrhDiscountAmount(0.00f);
        goodsReceiptH.setGrhTaxAmount(0.00f);
        goodsReceiptH.setGrhNetAmount(1000.00f);
        goodsReceiptH.setGrhStatus(1);
        goodsReceiptH.setEntityRid(1);
        goodsReceiptH.setGrhUnitRID(1);
        goodsReceiptH.setGrhVersion(1);
        goodsReceiptH.setCreatedUserRid(1);
//        goodsReceiptH.ssetGrhCurrentDateTimeetGrhGrnType("1");
        goodsReceiptH.setGrhRemarks("Remarks");
        goodsReceiptH.setGrhState(1);
        goodsReceiptH.setGrhMatrixType(1);

        List<GoodsReceiptD> childgoodsReceiptD = new ArrayList<>();
        List<StockTransaction> childStockTransaction = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {

            GoodsReceiptD goodsReceiptD = new GoodsReceiptD();
//            goodsReceiptD.setId(1);

            goodsReceiptD.setGrdHdID(1);
            goodsReceiptD.setGrdFreeQty(2f);
            goodsReceiptD.setGrdSkuRID(1);
            goodsReceiptD.setGrdItemName("Item3");
            goodsReceiptD.setGrdBatchNo("324");
            goodsReceiptD.setGrdExpiryDate("34");
            goodsReceiptD.setGrdOrderQty(10f);
            goodsReceiptD.setGrdReceiptQty(10f);
            goodsReceiptD.setGrdRejectedQty(10f);
            goodsReceiptD.setGrdAcceptedQty(10f);
            goodsReceiptD.setGrdRate(100.00f);
            goodsReceiptD.setGrdMrp(100.00f);
            goodsReceiptD.setGrdDiscount(0.00f);
            goodsReceiptD.setGrdTax(0.00f);
            goodsReceiptD.setGrdGross(1000.00f);
            goodsReceiptD.setGrdNet(1000.00f);

            childgoodsReceiptD.add(goodsReceiptD);
//
            StockTransaction stockTransaction = new StockTransaction();

////
//            stockTransaction.setTranID();
            stockTransaction.setTranDate(DateUtil.getCurrentDate());
            stockTransaction.setTranCode(1);
            stockTransaction.setTranSkuRID(1);
            stockTransaction.setTranBatchNo("324");
            stockTransaction.setTranExpiryDate(DateUtil.getCurrentDate());
            stockTransaction.setTranReceiptQty(5.0f);
            stockTransaction.setTranIssueQty(5.0f);
            stockTransaction.setTranMrp(10.00f);
            stockTransaction.setTranCostPrice(10.00f);
            stockTransaction.setTranItemTax(1f);
            stockTransaction.setTranItemDiscount(2f);
            stockTransaction.setTranStatus(1);
            stockTransaction.setTranRefNo("1");
            stockTransaction.setTranRefID(13);
            stockTransaction.setTranEntityID(4);
            stockTransaction.setTranUnitRID(1);

            childStockTransaction.add(stockTransaction);
            goodsReceiptH.setGoodsReceiptD(childgoodsReceiptD);
            goodsReceiptH.setChildStockTransaction(childStockTransaction);
//        }
//
            Gson gson = new Gson();
            System.out.println(gson.toJson(goodsReceiptH));
            purchaseService.saveGoodsReceiptH(goodsReceiptH, true);
        }
//    @Test
//    public void testSearchUser() throws Throwable {
//        GoodsReceiptHSearchCriteria goodsReceiptHSearchCriteria = new GoodsReceiptHSearchCriteria();
//        goodsReceiptHSearchCriteria.setId(2);
//        goodsReceiptHSearchCriteria.setGrhNo("GRN/101");
//        goodsReceiptHSearchCriteria.setGrhSupplierName("Anand");
////        goodsReceiptHSearchCriteria.setGrhDcDate(Date.);
//        List<GoodsReceiptH> use = goodsReceiptService.getGoodsReceiptH(new GoodsReceiptHSearchRequest(goodsReceiptHSearchCriteria), true);
////        return masterService.getUser(new UserSearchRequest(userSearchCriteria), true);
//        Gson ss = new Gson();
//        System.out.println("user Data: " + ss.toJson(use));
//    }

    }
}
