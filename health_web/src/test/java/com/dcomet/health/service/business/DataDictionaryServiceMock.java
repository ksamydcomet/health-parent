package com.dcomet.health.service.business;

import com.dcomet.health.dao.DataDictionaryDAO;
import com.dcomet.health.dao.impl.DataDictionaryDAOImpl;
import com.dcomet.health.service.business.impl.DataDictionaryServiceImpl;
import com.dcomet.module.dao.data.CurrencyMData;
import com.dcomet.module.domain.CurrencyM;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import static org.mockito.Mockito.*;

/**
 *
 * @author Dev3
 */
public class DataDictionaryServiceMock extends TestCase {

    DataDictionaryDAO dataDictionaryDAO;
    DataDictionaryServiceImpl dataDictionaryService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        CurrencyMData currency = new CurrencyMData();
        currency.setId(1);
        dataDictionaryDAO = mock(DataDictionaryDAOImpl.class);
        dataDictionaryService = new DataDictionaryServiceImpl();
        dataDictionaryService.setDataDictionaryDAO(dataDictionaryDAO);
        when(dataDictionaryDAO.getCurrencyM(null)).thenReturn(Arrays.asList(currency));
    }

    @Ignore
    public void testGetCurrency() throws Exception {
        List<CurrencyM> currencyList = dataDictionaryService.getCurrencyM(null);
        assertNotNull(currencyList);
        assertEquals(currencyList.size(), 1);
    }
}
