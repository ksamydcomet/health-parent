package com.dcomet.health.service.business;

import com.dcomet.health.service.JUnitConfigLocator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev1
 */
public class ReceiptServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    BillingService billingService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        billingService = (BillingService) configLocator
                .getBean("receiptService");
    }

    @Test
    public void testReceiptH() throws Throwable {
    }
}
