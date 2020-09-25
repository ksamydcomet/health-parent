//package com.dcomet.health.service.business;
//
//import com.dcomet.health.domain.CriteriaOrder;
//import com.dcomet.health.domain.SalesD;
//import com.dcomet.health.domain.SalesH;
//import com.dcomet.health.domain.SalesHSearchRequest;
//import com.dcomet.health.domain.SalesReturnD;
//import com.dcomet.health.domain.SalesReturnH;
//import com.dcomet.health.fw.util.DateUtil;
//import com.dcomet.health.service.JUnitConfigLocator;
//import com.google.gson.Gson;
//import java.util.ArrayList;
//import java.util.List;
//import junit.framework.TestCase;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Restrictions;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author KS
// */
//public class SalesServiceIT extends TestCase {
//
//    private JUnitConfigLocator configLocator;
//    SalesService salesService;
//
//    @Override
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        configLocator = JUnitConfigLocator.getInstance();
//        salesService = (SalesService) configLocator
//                .getBean("salesService");
//    }
//
//    @Test
//    public void testSalesH() throws Throwable {
//
////        SalesHSearchRequest parentSearchRequest = new SalesHSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.like("salBIllNo", "No123"));
////        parentSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("salCustomerId", false));
////        parentSearchRequest.setSortOrder(orderList);
////
////        List<SalesH> parentList = salesService.getSalesH(parentSearchRequest, true);
////        System.out.println("parentList.getResultList()>>" + parentList.size());
////        for (SalesH salesH : parentList) {
////            System.out.println(salesH.getSalBillNo() + " : " + salesH.getSalCustomerId());
////        }
////        SalesReturnHSearchRequest salesReturnHSearchRequest = new SalesReturnHSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.like("srhPatientName", "Kumar"));
////        salesReturnHSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("srhDate", false));
////        salesReturnHSearchRequest.setSortOrder(orderList);
////
////        Result<SalesReturnH> parentList = salesService.getSalesReturnH(salesReturnHSearchRequest, true);
////        System.out.println("parentList.getResultList()>>" + parentList.getResultList().size());
////        for (SalesReturnH salesReturnH : parentList.getResultList()) {
////            System.out.println(salesReturnH.getSrhPatientMrn() + " : " + salesReturnH.getSrhPatientRID());
////    }
//        SalesH parent = new SalesH();
//
//        parent.setSalBillNo("23");
//        parent.setSalCustomerId("2");
//        parent.setSalCustomerName("43");
//        parent.setSalCustomerAddress("north street");
//        parent.setSalBillDate("2015-12-12");
//        parent.setSalCntPerson("steve");
//        parent.setSalPhoneNo("98756321");
//        parent.setSalGrossAmount(23.34f);
//        parent.setSalTotalDiscount(34f);
//        parent.setSalTotalTax(34f);
//        parent.setSalRoundOffAmount(34);
//        parent.setSalNetAmount(45.67f);
//        parent.setEntityRid(2);
//        parent.setSalDueAmount(76.4f);
//        parent.setSalStatus("3");
//        parent.setSalPaidAmount(45.56f);
//        parent.setSalCustomerRID("2");
//        parent.setSalVersion(2);
//        parent.setSalType(3);
//        parent.setCreatedUserRid(1);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(parent));
//        salesService.saveSalesH(parent, true);
////
//        List<SalesD> childs = new ArrayList<SalesD>();
//
//        SalesD child = new SalesD();
//
//        child.setSalHRID(1);
//        child.setSalSkuRID(2);
//        child.setSalItemName("St");
//        child.setSalBatchNo("sale2");
//        child.setSalExpDate(DateUtil.getCurrentDate());
//        child.setSalStLoc("3324");
//        child.setSalStockQty(23f);
//        child.setSalQty(23f);
//        child.setSalReturnQty(34f);
//        child.setSalUom("se");
//        child.setSalRate(34.4f);
//        child.setSalGrossAmount(324.5f);
//        child.setSalDiscount(345.4f);
//        child.setSalNetAmount(34.34f);
//        child.setSalBaseUom(1);
//        child.setSalCurrentDateTimeSale(DateUtil.getCurrentDateTime());
//        child.setSalCurrentDateTimeReturn(DateUtil.getCurrentDateTime());
//
//        childs.add(child);
//        parent.setSalesD(childs);
//        salesService.saveSalesH(parent, true);
//    }
//
//    @Test
//    public void testSalesReturnH() throws Throwable {
//        SalesReturnH parent = new SalesReturnH();
//        parent.setCreatedUserRid(1);
//        parent.setSrhPatientRID(1);
//        parent.setSrhPatientMrn("1");
//        parent.setSrhPatientName("steve");
//        parent.setSrhVisitRID(1);
//        parent.setSrhVisitType(1);
//        parent.setSrhRoundoffAmount(12.23f);
//        parent.setSrhNetAmount(23.23f);
//        parent.setSrhUnitRID(1);
//        parent.setEntityRid(1);
//        parent.setModifiedUserRid(1);
//        parent.setSrhSeqNo("123");
//        parent.setSrhBIllRID(1);
//
//        List<SalesReturnD> childs = new ArrayList<>();
//
//        SalesReturnD child = new SalesReturnD();
//        child.setSrdSrhRID(1);
//        child.setSrdSkuRID(1);
//        child.setSrdSkuName("999");
//        child.setSrdReturnHRID(1);
//        child.setSrdReturnDRID(1);
//        child.setSrdBIllRID(1);
//        child.setSrdIsshRID(1);
//        child.setSrdIssdRID(1);
//        child.setSrdReturnQty(34.34f);
//        child.setSrdReturnRate(23.45f);
//        child.setSrdTaxAmount(3.4f);
//        child.setSrdReturnNetAmount(200f);
//        child.setSrdContextType("123");
//
//        childs.add(child);
//        parent.setSalesReturnD(childs);
//        salesService.saveSalesReturnH(parent, true);
//    }
//}
