package com.dcomet.health.service.caching;

import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.health.service.business.DataDictionaryService;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class JUnitCacheServiceTest extends TestCase {

    private JUnitConfigLocator configLocator;

    DataDictionaryService dataDictionaryService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        dataDictionaryService = (DataDictionaryService) configLocator.getBean("dataDictionaryService");
    }

    @Test
    public void testCacheServiceTest() throws Throwable {
        System.out.println(dataDictionaryService.getDdictType());
    }
}
