package com.dcomet.health.batch.job.scheduler;

import java.util.Date;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dcomet.health.batch.job.AbstractJob;
import com.dcomet.health.batch.job.DashBoardJob;

/**
 * @author Dev2
 */
public class BAJUnitDashBoardJob extends TestCase {

    private ConfigLocator configLocator;
    private AbstractJob dashBoardJob;

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
        dashBoardJob = (DashBoardJob) configLocator.getBean("BADashBoardJob");
    }

    @Override
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDashBoardJob() throws Throwable {
        dashBoardJob.executeJob(new Date());
    }
}
