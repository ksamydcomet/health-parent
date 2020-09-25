package com.dcomet.health.batch.job.scheduler;

import com.dcomet.health.batch.job.AbstractJob;
import com.dcomet.health.batch.job.ReportScheduleJob;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dev2
 */
public class BBJUnitReportSchedule extends TestCase {

    private ConfigLocator configLocator;
    private AbstractJob reportScheduleJob;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = ConfigLocator.getInstance();
        reportScheduleJob = (ReportScheduleJob) configLocator.getBean("BBReportScheduleJob");
    }

    @Override
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReportScheduleJob() throws Throwable {
        reportScheduleJob.executeJob(new Date());
    }
}
