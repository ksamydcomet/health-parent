package com.dcomet.health.dao;

import com.dcomet.fw.workflow.service.NotificationService;
import com.dcomet.health.service.JUnitConfigLocator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev2
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "health-test-service-applicationcontext.xml")
//@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class NotificationDAOIT extends TestCase {

    private JUnitConfigLocator configLocator;
    NotificationService notificationService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        notificationService = (NotificationService) configLocator
                .getBean("notificationService");
    }

    @Test
    public void testAddFormBODescriptor() throws Throwable {
//        List<BOWorkList> boWorkList = notificationService.loadWorklistSummary("2015-12-30");
//        Gson ss = new Gson();
//        System.out.println("boWorkilisSummaryDAOIT");
//        System.out.println(ss.toJson(boWorkList));
//        System.out.println("boWorkList" + boWorkList);
    }

//    @Test
//    public void testAddFormBODescriptor() throws Throwable {
////        List<BOWorkList> boWorkList = notificationService.getWorklistTasks(65, 4, 11, 4, "2015-07-16");
////        System.out.println("boWorkList" + boWorkList);
//        List<BOWorkListSettings> boWorkListSettings = notificationService.getBOWorklistSettings();
//        System.out.println(boWorkListSettings);
//    }
}
