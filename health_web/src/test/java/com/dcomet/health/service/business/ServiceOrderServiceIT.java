package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev4
 */
public class ServiceOrderServiceIT extends TestCase {
//

    private JUnitConfigLocator configLocator;
    ServiceOrderService serviceOrderService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        serviceOrderService = (ServiceOrderService) configLocator.getBean("serviceOrderService");
    }
//

    @Test
    public void testServiceRequest() throws Throwable {
//        
//        ServiceRequestSearchRequest parentSearchRequest = new ServiceRequestSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("serReqOpVisitRID", 3));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<ServiceRequest> parentList = serviceOrderService.getServiceRequest(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
//        for (ServiceRequest serviceRequest : parentList.getListList()) {
//            System.out.println(serviceRequest.getSerReqBillHRID() + " : " + serviceRequest.getSerReqLabEnHRID());
//        }

//        ServiceRequestH serviceRequestH = new ServiceRequestH();
//        serviceRequest.setSerReqOpRID(7);
//        serviceRequest.setSerReqOpVisitRID(6);
//        serviceRequest.setSerReqBillHRID(5);
//        serviceRequest.setSerReqBillDRID(4);
//        serviceRequest.setSerReqStatus(3);
//        serviceRequest.setSerReqEntityRID(2);
//        serviceRequest.setSerReqLabEnHRID(1);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(serviceRequestH));
//        serviceOrderService.saveServiceRequest(serviceRequest);
    }

    @Test
    public void testServiceOrder() throws Throwable {
//        
//        ServiceOrderSearchRequest parentSearchRequest = new ServiceOrderSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("soPatientRID", 3));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<ServiceOrder> parentList = serviceOrderService.getServiceOrder(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
//        for (ServiceOrder serviceOrder : parentList.getListList()) {
//            System.out.println(serviceOrder.getSoConvertedDatetime() + " : " + serviceOrder.getSoStatusModDateTime());
//        }
//        
        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setSoAgainstUnitRID(1);
        serviceOrder.setSoPatientRID(2);
        serviceOrder.setSoVisitRID(3);
        serviceOrder.setSoOrderingUnitRID(4);
        serviceOrder.setSoOrderNo("555");
        serviceOrder.setSoOrderType(2);
        serviceOrder.setSoItemID(3);
        serviceOrder.setSoItemName("45");
        serviceOrder.setSoStartDate(DateUtil.getCurrentDate());
//        serviceOrder.setSoStartTime("4.05");
        serviceOrder.setSoQty(34f);
        serviceOrder.setEntityRid(2);
        serviceOrder.setSoStatus(2);
        serviceOrder.setSoStatusModifiedDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoProcessingUnitRID(1);
        serviceOrder.setCreatedUserRid(1);
        serviceOrder.setSoAttndDocRID(2);
        serviceOrder.setSoOrderDocRID(2);
        serviceOrder.setSoRecodedUserRID(2);
        serviceOrder.setSoProcessedBY("Admin");
        serviceOrder.setSoProcessedDate(DateUtil.getCurrentDate());
//        serviceOrder.setSoProcessedTime("4.05");
        serviceOrder.setSoProcessingComments("none");
        serviceOrder.setSoResultRID(1);
        serviceOrder.setSoResultDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoScheduleRID(2);
        serviceOrder.setSoSerMode(2);
        serviceOrder.setSoCancellationReason("none");
        serviceOrder.setSoRemarks("non");
        serviceOrder.setSoItemCategory(2);
        serviceOrder.setSoParentRID(2);
        serviceOrder.setSoRootParentID(2);
        serviceOrder.setSoSignedUserRID(2);
        serviceOrder.setSoCoSignedUserRID(2);
        serviceOrder.setSoSignedDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoRecurrenceID(1);
        serviceOrder.setSoRecurrenceSpan("45");
        serviceOrder.setSoOccurenceTotal(234);
        serviceOrder.setSoOccurenceGenerated(3);
        serviceOrder.setSoRecurrenceText("text");
        serviceOrder.setModifiedUserRid(1);
        serviceOrder.setSoMajorProcedureStatus(1);
        serviceOrder.setSoServicePoint(1);
        serviceOrder.setSoState(1);
        serviceOrder.setSoAdviceUserRID(1);
        serviceOrder.setSoAdviceDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoConvertedUserRID(2);
        serviceOrder.setSoConvertedDateTime(DateUtil.getCurrentDateTime());
//
        Gson gson = new Gson();
        System.out.println(gson.toJson(serviceOrder));
//        serviceOrderService.saveServiceOrder(serviceOrder, serviceOrder);
//    }
//}
    }
}
