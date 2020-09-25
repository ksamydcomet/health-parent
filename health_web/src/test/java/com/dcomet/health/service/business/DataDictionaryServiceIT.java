package com.dcomet.health.service.business;

import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.domain.CurrencyM;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class DataDictionaryServiceIT extends TestCase {

    DataDictionaryService dataDictionaryService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        JUnitConfigLocator configLocator = JUnitConfigLocator.getInstance();
        dataDictionaryService = (DataDictionaryService) configLocator.getBean("dataDictionaryService");
    }

    @Test
    public void testTimeZoneTest() throws Throwable {

        List<CurrencyM> currencyList = dataDictionaryService.getCurrencyM(null);
        CurrencyM currencym = currencyList.get(11);
        dataDictionaryService.saveCurrencyM(Arrays.asList(currencym));

//        Gson ss = new Gson();
//        BOMasterSearchRequest boMasterSearchRequest = new BOMasterSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("bomName", "Bill"));
//        boMasterSearchRequest.setSearchCriterionList(searchCriterionList);
//        System.out.println(ss.toJson(dataDictionaryService.getBOMaster(boMasterSearchRequest, true)));
//        Currency currency = new Currency();
//        currency.setName("cname");
//        currency.setUniversalCode("ucode");
//        System.out.println(dataDictionaryService.getCurrency());
    }

//    @Test
//    public void testDdict() throws Throwable {
//
////        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.eq("ddictDditTypeIndex", 1));
////        ddictSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("salCustomerId", false));
////        ddictSearchRequest.setSortOrder(orderList);
////        List<Ddict> parentList = dataDictionaryService.getDdict(ddictSearchRequest, true);
////        System.out.println("parentList.getListList()>>" + parentList.size());
////        for (Ddict ddict : parentList) {
////            System.out.println(ddict.getDdictDditTypeIndex() + " : " + ddict.getDdictValue());
////        }
////        Ddict ddict = new Ddict();
////        ddict.setDdictDditTypeIndex(1);
////        ddict.setDdictAbbrv("Abb");
////        ddict.setDdictValue("Value2");
////        ddict.setDdictParentIndex(1);
////        ddict.setDdictValid(1);
////        ddict.setDdictHelp("123");
////
////        dataDictionaryService.saveDdict(ddict, false);
//    }
//    @Test
//    public void testTaxConfig() throws Throwable {
//
////        TaxSearchRequest taxSearchRequest = new TaxSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.eq("id", 1));
////        taxSearchRequest.setSearchCriterionList(searchCriterionList);
//////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//////        orderList.add(new CriteriaOrder("salCustomerId", false));
//////        ddictSearchRequest.setSortOrder(orderList);
////
////        List<TaxConfig> parentList = dataDictionaryService.getTaxConfig(taxSearchRequest);
////        System.out.println("parentList.getListList()>>" + parentList.size());
////        Gson gsn = new Gson();
////        System.out.println(gsn.toJson(parentList));
////        for (TaxConfig taxConfig : parentList) {
////            System.out.println(taxConfig.getTcModDatetime() + " : " + taxConfig.getTcDestPercentage());
////        }
//        TaxConfig taxconfig = new TaxConfig();
////        taxconfig.setId(2);
//        taxconfig.setTcDestPercentage(10f);
//        taxconfig.setTcEntityRID(1);
//        taxconfig.setTcModifiedDateTime(DateUtil.getCurrentDateTime());
//        taxconfig.setTcModifiedUserRID(3);
//        taxconfig.setTcSrcPercentage(23f);
//        taxconfig.setTcType("a");
//        taxconfig.setTcValid(1);
//    
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(taxconfig));
//        dataDictionaryService.saveTaxConfig(taxconfig);
//        
//              }
//    @Ignore
//    public void testGetCurrency() throws Exception {
////        List<Currency> currencyList = dataDictionaryService.getCurrency();
//        assertNotNull(currencyList);
//        System.out.println("currencyList" + currencyList.size());
}
//    @Test
//    public void testInsertCurrency() throws Exception {
//        Currency currency = new Currency();
//        currency.setUniversalCode("TST");
//        currency.setName("Test Data");
//        dataDictionaryService.saveCurrency(currency);
//    }
//    @Test
//    public void testDdict() throws Throwable {
//        AutoNumber autonumber = dataDictionaryService.getAutoNumberByCategory("PO", 4);
//        dataDictionaryService.saveAutoNumberIncrement("PO", 4);
//        Gson ss = new Gson();
//        System.out.println(ss.toJson(autonumber));
//    }

