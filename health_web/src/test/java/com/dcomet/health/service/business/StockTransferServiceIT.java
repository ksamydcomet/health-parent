package com.dcomet.health.service.business;

import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.inventory.service.DCometInventoryService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev2
 */
public class StockTransferServiceIT extends TestCase {

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

    //-----Stock MovementH
    @Test
    public void testStockMovementH() throws Throwable {

////        StockMovementHSearchRequest parentSearchRequest = new StockMovementHSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.like("smhStCode", "STN/1002"));
////        parentSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("id", false));
////        parentSearchRequest.setSortOrder(orderList);
////
////        List<StockMovementH> parentList = stockTransferService.getStockMovementH(parentSearchRequest, true);
////        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
////        for (StockMovementH stockMovementH : parentList.getListList()) {
////            System.out.println(stockMovementH.getSmhTransportDetails() + " : " + stockMovementH.getSmhCurrentDateTime());
//        StockMovementH stockMovementH = new StockMovementH();
////        parent.setId(851);
//        stockMovementH.setSmhStCode("SMH/101");
//        stockMovementH.setSmhDate(DateUtil.getCurrentDate());
//        stockMovementH.setSmhSourceEntityRID(4);
//        stockMovementH.setSmhDestinationEntityRID(1);
//        stockMovementH.setSmhSourceUnitRID(1);
//        stockMovementH.setSmhDestinationUnitRID(3);
//        stockMovementH.setSmhRemarks("xxxxxxxxxx");
//        stockMovementH.setModifiedUserRid(1);
//        stockMovementH.setSmhTransportModeIndex(1);
//        stockMovementH.setSmhTransportModeDesc("12");
//        stockMovementH.setSmhTransportDetails("XX");
//        stockMovementH.setSmhPackingDetails("YY");
//        stockMovementH.setCreatedUserRid(1);
//        stockMovementH.setSmhState(1);
//        stockMovementH.setSmhTotalQty(200f);
//        stockMovementH.setSmhTransType(1);
//        stockMovementH.setSmhMatrixType(1);
//
//        List<StockMovementD> childStockMovementD = new ArrayList<StockMovementD>();
//        List<StockTransaction> childstockTransaction = new ArrayList<StockTransaction>();
//        for (int i = 1; i <= 2; i++) {
//            StockMovementD stockMovementD = new StockMovementD();
//
//            stockMovementD.setSmdSkuRID(1);
//            stockMovementD.setSmdBatchNo("777");
//            stockMovementD.setSmdExpiryDate("02/2016");
//            stockMovementD.setSmdMrp(112.2f);
//            stockMovementD.setSmdRate(112.2f);
//            stockMovementD.setSmdUomID(1);
//            stockMovementD.setSmdTransferQty(50f);
//            stockMovementD.setSmdAcceptQty(5f);
//            stockMovementD.setSmdRejectQty(0f);
//            stockMovementD.setSmdTransferStatus(1);
//
//            childStockMovementD.add(stockMovementD);
////          
//        }
//        StockTransaction stockTransaction = new StockTransaction();
//////
////            stockTransaction.setTranID();
//        stockTransaction.setTranDate(DateUtil.getCurrentDate());
//        stockTransaction.setTranCode(1);
//        stockTransaction.setTranSkuRID(1);
//        stockTransaction.setTranBatchNo("777");
//        stockTransaction.setTranExpiryDate("02/2016");
//        stockTransaction.setTranMrp(112.2f);
//        stockTransaction.setTranCostPrice(112.2f);
//        stockTransaction.setTranItemTax(1f);
//        stockTransaction.setTranItemDiscount(2f);
//        stockTransaction.setTranStatus(1);
//        stockTransaction.setTranRefNo("2");
//        stockTransaction.setTranRefID(5);
//        stockTransaction.setTranEntityID(1);
//        stockTransaction.setTranUnitRID(3);
//
//        childstockTransaction.add(stockTransaction);
//
//        stockMovementH.setStockMovementD(childStockMovementD);
//        stockMovementH.setStockTransaction(childstockTransaction);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(stockMovementH));
//        stockTransferService.saveStockMovementH(stockMovementH, true);
//    }
    }
}
