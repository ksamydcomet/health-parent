package com.dcomet.health.service.business;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.domain.ServiceRequest;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev4
 */
public class ClinicalServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    ClinicalService clinicalService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        clinicalService = (ClinicalService) configLocator
                .getBean("clinicalService");
    }

//    @Test
//    public void testServiceRequest() throws Throwable {
//        ServiceRequest serviceRequest = new ServiceRequest();
//        serviceRequest.setSerReqOpRID(1);
//        serviceRequest.setSerReqOpVisitRID(2);
//        serviceRequest.setSerReqBillHRID(3);
//        serviceRequest.setSerReqBillDRID(4);
//        serviceRequest.setSerReqStatus(5);
//        serviceRequest.setSerReqEntityRID(6);
//        serviceRequest.setSerReqLabEnHRID(7);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(serviceRequest));
////        clinicalService.saveServiceRequest(serviceRequest);
//    }

    @Test
    public void testServiceOrder() throws Throwable {
        ServiceOrder serviceOrder = new ServiceOrder();
//        serviceOrder.setId(3);
        serviceOrder.setSoAgainstUnitRID(1);
        serviceOrder.setSoPatientRID(2);
        serviceOrder.setSoVisitRID(3);
        serviceOrder.setSoOrderingUnitRID(4);
        serviceOrder.setSoOrderNo("12345");
        serviceOrder.setSoOrderType(2);
        serviceOrder.setSoItemID(3);
        serviceOrder.setSoItemName("45");
        serviceOrder.setSoStartDate(DateUtil.getCurrentDate());
        serviceOrder.setSoStartTime("4.05");
        serviceOrder.setSoQty(34f);
//        serviceOrder.setSoEntityRID(2);
        serviceOrder.setSoStatus(2);
        serviceOrder.setSoStatusModifiedDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoProcessingUnitRID(1);
//        serviceOrder.setSoOrderDate(DateUtil.getCurrentDate());
        serviceOrder.setSoAttndDocRID(2);
        serviceOrder.setSoOrderDocRID(2);
        serviceOrder.setSoRecodedUserRID(2);
        serviceOrder.setSoProcessedBY("admin");
        serviceOrder.setSoProcessedDate(DateUtil.getCurrentDate());
        serviceOrder.setSoProcessedTime("4.05");
        serviceOrder.setSoProcessingComments("none");
//        serviceOrder.setSoVisitRID(1);
//        serviceOrder.setSo(DateUtil.getCurrentDateTime());
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
//        serviceOrder.setSoModifiedUserRID(1);

        serviceOrder.setSoMajorProcedureStatus(1);
        serviceOrder.setSoServicePoint(1);
        serviceOrder.setSoState(1);
        serviceOrder.setSoAdviceUserRID(1);
        serviceOrder.setSoAdviceDateTime(DateUtil.getCurrentDateTime());
        serviceOrder.setSoConvertedUserRID(2);
        serviceOrder.setSoConvertedDateTime(DateUtil.getCurrentDateTime());
        Gson gson = new Gson();
        System.out.println(gson.toJson(serviceOrder));
//        clinicalService.saveServiceOrder(serviceOrder);
    }
}
