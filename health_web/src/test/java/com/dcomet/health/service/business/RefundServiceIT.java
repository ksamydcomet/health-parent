package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.module.billing.domain.RefundD;
import com.dcomet.module.billing.domain.RefundH;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class RefundServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    BillingService billingService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        billingService = (BillingService) configLocator
                .getBean("refundService");
    }

    @Test
    public void testRefundH() throws Throwable {

//        RefundHSearchRequest parentSearchRequest = new RefundHSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("refhPrefix", "REF/"));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<RefundH> parentList = refundService.getRefundH(parentSearchRequest, true);
//        System.out.println("parentList.getResultList()>>" + parentList.size());
//        for (RefundH refund : parentList) {
//            System.out.println(refund.getRefhPrintableNo() + " : " + refund.getRefhDate());
//        }
        RefundH refundH = new RefundH();
//        refundH.setId(2);
        refundH.setRefhNo("REF/5");
        refundH.setRefhPrefix("REF/");
        refundH.setRefhPrintableNo("REF/5");

        refundH.setRefhPayerRID(559);
        refundH.setRefhPayerType(1);
        refundH.setRefhPayerNo("23");
        refundH.setRefhPayerName("Ajith");
        refundH.setRefhIncidentRID(1);
        refundH.setRefhIncidentType(2);
        refundH.setRefhAmount(1.00f);
        refundH.setRefhStatus(1);
        refundH.setEntityRid(1);
        refundH.setRefhPaymentMode(1);
        refundH.setRefhRemarks("aaaa");
        refundH.setCreatedUserRid(1);
        refundH.setRefhRowInvalidated(1);
        refundH.setRefhRowHistoryRID(1);
        refundH.setRefhUnitRID(1);
        refundH.setRefhContextType(1);
        refundH.setRefhRoundoffAmount(1.00f);
        refundH.setModifiedUserRid(1);
        refundH.setRefhCancelledDate(DateUtil.getCurrentDate());
        refundH.setRefhApproverUserRID(1);
        refundH.setRefhBeniciaryName("bbbbbb");

        Gson gson = new Gson();
        System.out.println(gson.toJson(refundH));
        billingService.saveRefundH(refundH, true);

        List<RefundD> childs = new ArrayList<>();
        RefundD refundD = new RefundD();

        refundD.setRefdRefhRID(1);
        refundD.setRefdAdvanceRID(1);
        refundD.setRefdType(1);
        refundD.setRefdAmount(12.35f);

        childs.add(refundD);

        Gson gsn = new Gson();
        System.out.println(gsn.toJson(refundD));
        billingService.saveRefundD(childs);

//        refundH.setChildList(childs);
        billingService.saveRefundH(refundH, true);
    }
}
