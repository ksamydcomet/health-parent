package com.dcomet.health.service.business;

import com.dcomet.health.dao.PurchaseDAO;
import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.inventory.service.DCometInventoryService;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Dev2
 */
public class InventoryServiceIT extends TestCase {

    @Autowired
    @Qualifier("purchaseDAO")
    PurchaseDAO purchaseDAO;

    private JUnitConfigLocator configLocator;
    DCometInventoryService inventoryService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        inventoryService = (DCometInventoryService) configLocator
                .getBean("inventoryService");
    }

    @Test
    public void testStockAdjustmentH() throws Throwable {
//        StockAdjustmentH stockAdjustmentH = new StockAdjustmentH();
////        stockAdjustmentH.setId(1);
//        stockAdjustmentH.setSahDate(DateUtil.getCurrentDate());
//        stockAdjustmentH.setSahRefNo("SAH/2001");
//        stockAdjustmentH.setSahSequenceNo("");
//        stockAdjustmentH.setSahUnitRID(1);
//        stockAdjustmentH.setEntityRid(4);
//        stockAdjustmentH.setCreatedUserRid(1);
//        stockAdjustmentH.setSahRemarksDesc("user");
////
//        List<StockAdjustmentD> childstockAdjustmentD = new ArrayList<>();
////
////////
//        for (int i = 1; i <= 1; i++) {
//            StockAdjustmentD stockAdjustmentD = new StockAdjustmentD();
////            stockAdjustmentD.setId(1);
//            stockAdjustmentD.setSadSahRID(7);
//            stockAdjustmentD.setSadSkuRID(1);
//            stockAdjustmentD.setSadSkuName("Item" + i);
//            stockAdjustmentD.setSadBatchNo("Batch" + i);
//            stockAdjustmentD.setSadExpiryDate("2012-12-12");
//            stockAdjustmentD.setSadRate(200f);
//            stockAdjustmentD.setSadMrp(200f);
//            stockAdjustmentD.setSadQty(10f);
////
//            childstockAdjustmentD.add(stockAdjustmentD);
////
//            List<StockTransaction> childStockTransaction = new ArrayList<>();
//            StockTransaction stockTransaction = new StockTransaction();
//////            
////            stockTransaction.setTranID(4);
//            stockTransaction.setTranDate(DateUtil.getCurrentDate());
//            stockTransaction.setTranCode(2);
//            stockTransaction.setTranSkuRID(1);
//            stockTransaction.setTranBatchNo("Batch" + i);
//            stockTransaction.setTranExpiryDate("2012-11-12");
//            stockTransaction.setTranIssueQty(10f);
//            stockTransaction.setTranMrp(200f);
//            stockTransaction.setTranCostPrice(200f);
//            stockTransaction.setTranItemTax(0f);
//            stockTransaction.setTranItemDiscount(0f);
//            stockTransaction.setTranStatus(1);
//            stockTransaction.setTranRefNo("3");
//            stockTransaction.setTranRefID(2);
//            stockTransaction.setTranEntityID(4);
//            stockTransaction.setTranUnitRID(1);
//            childStockTransaction.add(stockTransaction);
////////
//            stockAdjustmentH.setStockAdjustmentD(childstockAdjustmentD);
//            stockAdjustmentH.setStockTransaction(childStockTransaction);
//        }
////
//        inventoryService.saveStockAdjustmentH(stockAdjustmentH, true);
    }
//    -----Opening StockH

    @Test
    public void testOpeningStockH() throws Throwable {
//        OpeningStockH openingStockH = new OpeningStockH();
////        parent.setId(851);
//        openingStockH.setOsDate(DateUtil.getCurrentDate());
//        openingStockH.setOsRefNo("OS/2000");
//        openingStockH.setOsSequenceNo(1999);
//        openingStockH.setOsUnitRID(1);
//        openingStockH.setEntityRid(1);
//        openingStockH.setCreatedUserRid(1);
//        openingStockH.setOsMatrixValue(111);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(openingStockH));
//        inventoryService.saveOpeningStockH(openingStockH, true);
//
////
////
//        List<OpeningStockD> childOpeningStockD = new ArrayList<OpeningStockD>();
//        List<StockTransaction> childStockTransaction = new ArrayList<StockTransaction>();
//        for (int i = 1; i <= 2; i++) {
//            OpeningStockD openingStockD = new OpeningStockD();
//            openingStockD.setOsdSkuRID(1);
//            openingStockD.setOsdBatchNo("");
//            openingStockD.setOsdExpiryDate("2020/20");
//            openingStockD.setOsdQty(10.00f);
//            openingStockD.setOsdRate(100.00f);
//            openingStockD.setOsdMrp(100.00f);
////
//            childOpeningStockD.add(openingStockD);
////
//////
//            StockTransaction stockTransaction = new StockTransaction();
////        stockTransaction.setTranID();
//            stockTransaction.setTranDate("2015-01-12");
//            stockTransaction.setTranCode(007);
//            stockTransaction.setTranSkuRID(1);
//            stockTransaction.setTranBatchNo("Batch");
//            stockTransaction.setTranExpiryDate("2016-11-12");
//            stockTransaction.setTranReceiptQty(20f);
//            stockTransaction.setTranMrp(200f);
//            stockTransaction.setTranCostPrice(200f);
//            stockTransaction.setTranItemTax(0f);
//            stockTransaction.setTranItemDiscount(0f);
//            stockTransaction.setTranStatus(0);
//            stockTransaction.setTranRefNo("7");
//            stockTransaction.setTranRefID(23);
//            stockTransaction.setTranEntityID(4);
//            stockTransaction.setTranUnitRID(1);
////
//            childStockTransaction.add(stockTransaction);
////
//            Gson gsn = new Gson();
//            System.out.println(gsn.toJson(stockTransaction));
////            inventoryService.saveStockTransaction(childStockTransaction);
//
//            openingStockH.setOpeningStockD(childOpeningStockD);
//            openingStockH.setStockTransaction(childStockTransaction);
//
//            inventoryService.saveOpeningStockH(openingStockH, true);

//}
//        }
    }

    @Test
    public void testStockH() throws Throwable {

//        StockTransactionSearchRequest stockTransactionSearchRequest = new StockTransactionSearchRequest();
//        StockTransactionSearchCriteria stockTransactionSearchCriteria = new StockTransactionSearchCriteria();
////        stockTransactionSearchCriteria.setTranID(1);
//        stockTransactionSearchRequest.addStockTransactionCriteria(stockTransactionSearchCriteria);
        Gson ss = new Gson();
//        System.out.println(ss.toJson(inventoryService.getClosingStock(1)));

//        StockH stockH = new StockH();
////        stockH.setId(6);
//        stockH.setStkSkuRID(12);
//        stockH.setStkSkuName("Product23");
//        stockH.setStkBatchNo("88");
//        stockH.setStkExpiryDate("2016-12-12");
//        stockH.setStkUomIndex(2);
//        stockH.setStkDate(DateUtil.getCurrentDate());
//        stockH.setStkEntityRID(3);
//        stockH.setStkRate("23");
//        stockH.setStkUnitRID(4);
//        stockH.setStkMrp(200f);
//        stockH.setStkSkuRID(5);
////
////      
////
//        List<StockD> childStockD = new ArrayList<StockD>();
//        StockD stockD = new StockD();
////        stockD.setId(stkRID);
//        stockD.setStkdStkRID(4);
//        stockD.setStkdDdStockType("batch23");
//        stockD.setStkdDdStockTypeIndex(2);
//        stockD.setStkdQty(5f);
//
//        childStockD.add(stockD);
//        stockH.setStockDChildList(childStockD);
////        
////       
////
//        inventoryService.saveStockH(stockH, true);
//    }
    }
//    @Test
//    public void testStockH() throws Throwable {
//
//        StockAdjustmentH stockAdjustmentH = new StockAdjustmentH();
////        stockAdjustmentH.setId(1);
//        stockAdjustmentH.setSahDate(DateUtil.getCurrentDate());
//        stockAdjustmentH.setSahRefNo("SAH/2001");
//        stockAdjustmentH.setSahSequenceNo("008");
//        stockAdjustmentH.setSahUnitRID(1);
//        stockAdjustmentH.setSahEntityRID(4);
//        stockAdjustmentH.setSahCreatedUserRID(1);
//        stockAdjustmentH.setSahCreatedDateTime(DateUtil.getCurrentDateTime());
//        stockAdjustmentH.setSahRemarksDesc("user Needed");
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(stockAdjustmentH));
//        inventoryService.saveStockAdjustmentH(stockAdjustmentH, true);
//
//        List<StockAdjustmentD> childstockAdjustmentD = new ArrayList<StockAdjustmentD>();
//        List<StockTransaction> childStockTransaction = new ArrayList<StockTransaction>();
//
//        for (int i = 1; i <= 2; i++) {
//            StockAdjustmentD stockAdjustmentD = new StockAdjustmentD();
//
////        stockAdjustmentD.setId(1);
//            stockAdjustmentD.setSadSahRID(1);
//            stockAdjustmentD.setSadSkuRID(1);
//            stockAdjustmentD.setSadSkuName("Item" + i);
//            stockAdjustmentD.setSadBatchNo("Batch" + i);
//            stockAdjustmentD.setSadExpiryDate("2016-11-12");
//            stockAdjustmentD.setSadRate(500f);
//            stockAdjustmentD.setSadMrp(200f);
//            stockAdjustmentD.setSadQty(20f);
////
//            childstockAdjustmentD.add(stockAdjustmentD);
//
//            StockTransaction stockTransaction = new StockTransaction();
////        stockTransaction.setTranID();
//            stockTransaction.setTranDate(DateUtil.getCurrentDate());
//            stockTransaction.setTranCode(3);
//            stockTransaction.setTranSkuRID(1);
//            stockTransaction.setTranBatchNo("Batch" + i);
//            stockTransaction.setTranExpiryDate("2012-12-12");
//
//            stockTransaction.setTranMrp(200f);
//            stockTransaction.setTranCostPrice(200f);
//            stockTransaction.setTranItemTax(0f);
//            stockTransaction.setTranItemDiscount(0f);
//            stockTransaction.setTranStatus(1);
//            stockTransaction.setTranRefNo("12");
//            stockTransaction.setTranRefID(45);
//            stockTransaction.setTranEntityID(4);
//            stockTransaction.setTranUnitRID(1);
//
//            childStockTransaction.add(stockTransaction);
////
//            stockAdjustmentH.setStockAdjustmentD(childstockAdjustmentD);
//            stockAdjustmentH.setStockTransaction(childStockTransaction);
//        }
//        inventoryService.saveStockAdjustmentH(stockAdjustmentH, true);
//    }
//    }}

//    @Test
//    public void testConsumptionDetails() throws Throwable {
//
//        List<ConsumptionDetails> childConsumptionDetails = new ArrayList<ConsumptionDetails>();
//        ConsumptionDetails consumptionDetails = new ConsumptionDetails();
//
//        for (int i = 1; i <= 2; i++) {
//////
////        consumptionDetails.setId(851);
//            consumptionDetails.setConsType(1);
//            consumptionDetails.setConsPatientRID(1);
//            consumptionDetails.setConsBhRID(2000);
//            consumptionDetails.setConsSkuRID(1);
//            consumptionDetails.setConsSkuName("NameCheck");
//            consumptionDetails.setConsBatchNo("Cons/123");
//            consumptionDetails.setConsExpiryDate("2015-12-22");
//            consumptionDetails.setConsMrp(200f);
//            consumptionDetails.setConsQty(20f);
//            consumptionDetails.setConsIsUnitService(2);
//            consumptionDetails.setConsUnitService("");
//            consumptionDetails.setConsEntityRID(4);
//            consumptionDetails.setConsUserRID(1);
//            consumptionDetails.setConsCreatedDateTime(DateUtil.getCurrentDateTime());
//
//            childConsumptionDetails.add(consumptionDetails);
//
//            Gson gson = new Gson();
//            System.out.println(gson.toJson(consumptionDetails));
//            inventoryService.saveConsumptionDetails(consumptionDetails);
//
//            List<StockTransaction> childStockTransaction = new ArrayList<StockTransaction>();
//            StockTransaction stockTransaction = new StockTransaction();
////            stockTransaction.setTranID();
//            stockTransaction.setTranDate("2015-01-12");
////        stockTransaction.setTranCode();
//            stockTransaction.setTranSkuRID(1);
//            stockTransaction.setTranBatchNo("Cons/123" + i);
//            stockTransaction.setTranExpiryDate("2015-12-22");
//            stockTransaction.setTranQty(20f);
//            stockTransaction.setTranMrp(200f);
//            stockTransaction.setTranCostPrice(200f);
//            stockTransaction.setTranItemTax(0f);
//            stockTransaction.setTranItemDiscount(0f);
//            stockTransaction.setTranStatus(1);
//            stockTransaction.setTranRefNo("1");
//            stockTransaction.setTranRefID(1);
//            stockTransaction.setTranEntityID(4);
//            stockTransaction.setTranUnitRID(1);
//
//            childStockTransaction.add(stockTransaction);
//
//            consumptionDetails.setConsumptionDetails(childConsumptionDetails);
//            consumptionDetails.setStockTransaction(childStockTransaction);
//
//            Gson gsn = new Gson();
//            System.out.println(gsn.toJson(consumptionDetails));
//            inventoryService.saveConsumptionDetails(consumptionDetails);
//        }
//    }
//}
//        @Test
//        public void testSearchUser() throws Throwable {
//            StockHSearchCriteria userSearchCriteria = new StockHSearchCriteria();
//            StockHSearchRequest searchRequest = new StockHSearchRequest();
//            System.out.println("1----1");
//            userSearchCriteria.setStkDate(DateUtil.getCurrentDate());
//            searchRequest.addStockHCriteria(userSearchCriteria);
//            List<StockH> stk = inventoryService.getStockH(searchRequest, true);
//            return masterService.getUser(new UserSearchRequest(userSearchCriteria), true);
//            Gson ss = new Gson();
//            System.out.println("user Data: " + ss.toJson(stk));
//        }
//        }}}
}
