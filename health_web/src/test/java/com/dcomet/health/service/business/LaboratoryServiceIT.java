package com.dcomet.health.service.business;

import com.dcomet.module.laboratory.domain.LabResultD;
import com.dcomet.module.laboratory.domain.LabResultH;
import com.dcomet.module.master.domain.LabServiceExtn;
import com.dcomet.health.service.JUnitConfigLocator;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author KS
 */
public class LaboratoryServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    LaboratoryService laboratoryService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        laboratoryService = (LaboratoryService) configLocator
                .getBean("laboratoryService");
    }
//

    @Test
    public void testLabResultH() throws Throwable {

//        LabResultHSearchRequest labResultHSearchRequest = new LabResultHSearchRequest();
//        List<Criteria/Order> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("lrhDate", false));
//        labResultHSearchRequest.setSortOrder(orderList);
////
//        List<LabResultH> labResultHList = laboratoryService.getLabResultH(labResultHSearchRequest, true);
//        System.out.println("parentList.getResultList()>>" + labResultHList.size());
//        for (LabResultH labResultH : labResultHList) {
//            System.out.println(labResultH.getLrhDate() + " : " + labResultH.getLrhPatientID());
//}
//            LabServiceSearchRequest labServiceSearchRequest = new LabServiceSearchRequest();
//            List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//            searchCriterionList.add(Restrictions.like("lsName", "lab234"));
//            labServiceSearchRequest.setSearchCriterionList(searchCriterionList);
//            List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//            orderList.add(new CriteriaOrder("lsGroupID", false));
//            labServiceSearchRequest.setSortOrder(orderList);
//
//            List<LabService> labServiceList = laboratoryService.getLabService(labServiceSearchRequest);
//            System.out.println("parentList.getResultList()>>" + labServiceList.size());
//            for (LabService labService : labServiceList) {
//                System.out.println(labService.getLsDateType() + " : " + labService.getLsName());
////            }
//            TemplateSearchRequest templateSearchRequest = new TemplateSearchRequest();
//            List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//            searchCriterionList.add(Restrictions.like("labTempName", "temp"));
//            templateSearchRequest.setSearchCriterionList(searchCriterionList);
//            List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//            orderList.add(new CriteriaOrder("labTempEntityRID", false));
//            templateSearchRequest.setSortOrder(orderList);
//
//            List<Template> templateList = laboratoryService.getTemplate(templateSearchRequest);
//            System.out.println("parentList.getResultList()>>" + templateList.size());
//            for (Template template : templateList) {
//                System.out.println(template.getLabTempEntityRID() + " : " + template.getLabTempNodes());
//            }
//
        LabResultH parent = new LabResultH();
        parent.setLrhDate("02-01-2015");
        parent.setLrhDraftsign(1);
        parent.setLrhLabNo("LAB004");
        parent.setLrhPatientID(1234);
        parent.setLrhPatientVisitID(4321);
        parent.setLrhSignBy("Abdul");
//
        List<LabResultD> childs = new ArrayList<>();
//
        LabResultD child = new LabResultD();
        child.setLrdNR("");
        child.setLrdNServCheck("");
        child.setLrdNServName("serv12");
        child.setLrdNServRange("");
        child.setLrdNServTechnology("Doc");
        child.setLrdNServUnit("3");
        child.setLrdServiceType(3);
        child.setLrdTTempValue("201");
        childs.add(child);
////
//        parent.setLabResultDList(childs);
//        laboratoryService.saveLabResultH(parent, true);
        long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println("Heap Size = " + heapSize);
//    }
    }

    @Test
    public void testLabService() throws Throwable {
        LabServiceExtn parentt = new LabServiceExtn();
        parentt.setLsServiceRID(3);
        parentt.setLsServiceName("");
        parentt.setLsProcessTechType("");
        parentt.setLsGenderType("");
        parentt.setLsRangeMinValue(2.9f);
        parentt.setLsRangeMaxValue(4.9f);
        parentt.setLsDescription("");
        parentt.setLsUom("");
        parentt.setLsOthers("");
        parentt.setLsRemarks("");
        parentt.setLsIsEditable(1);
        parentt.setLsResultType(1);
        parentt.setLsTemplateRID(1);

//   
//    }
////        @Test
////        public void testCacheServiceTest() throws Throwable {
////            Template parent = new Template();
////            parent.setLabTempEntityRID(1);
////            parent.setLabTempName("temp");
////            parent.setLabTempNodes("43");
//
////            parent.setLabListDList(childs);
////            laboratoryService.saveLabListH(parent, true);
////            long heapSize = Runtime.getRuntime().totalMemory();
////            System.out.println("Heap Size = " + heapSize);
//        }
//        @Test
//        public void testLabService() throws Throwable {
//            LabService parent = new LabService();
//            parent.setLsName("23");
//            parent.setLsGroupID(1);
//            parent.setLsSampleTypeID(1);
//            parent.setLsResultTypeID(1);
//            parent.setLsPageNo(1);
//            parent.setLsSequenceNo(1);
//            parent.setLsType(1);
//            parent.setLsServiceRID(1);
//            parent.setLsUnit("23");
//            parent.setLsLength("23");
//            parent.setLsDateType(2);
//            parent.setLsPossibleValues("23");
//            parent.setLsNormalRange("23");
//            parent.setLsIsEditable(1);
//            parent.setLsTechMethod("12");
//            parent.setLsTemplateNode("12");
//            laboratoryService.saveLabService(parent);
//        }}
////        @Test
////        public void testCacheServiceTest() throws Throwable {
////            Template parent = new Template();
////            parent.setLabTempEntityRID(1);
////            parent.setLabTempName("temp");
////            parent.setLabTempNodes("43");
////
////            laboratoryService.saveTemplate(parent);
////        }
//    
    }
}
