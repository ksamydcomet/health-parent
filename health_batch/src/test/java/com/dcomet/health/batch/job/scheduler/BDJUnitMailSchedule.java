package com.dcomet.health.batch.job.scheduler;

import com.dcomet.health.batch.job.AbstractJob;
import com.dcomet.health.batch.job.MailScheduleJob;
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
public class BDJUnitMailSchedule extends TestCase {

    private ConfigLocator configLocator;
    private AbstractJob abstractJob;

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
        abstractJob = (MailScheduleJob) configLocator.getBean("BDMailScheduleJob");
    }

    @Override
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMailScheduleJob() throws Throwable {
        abstractJob.executeJob(new Date());
    }
}
