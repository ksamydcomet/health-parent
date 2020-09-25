package com.dcomet.health.service.business;

import com.dcomet.module.master.domain.Sku;
import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.master.service.DCometMasterService;
import com.google.gson.Gson;
import javax.ws.rs.core.SecurityContext;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev4
 */
public class MasterIT extends TestCase {

    private JUnitConfigLocator configLocator;
    DCometMasterService masterService;
    SecurityContext secureContext;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        masterService = (DCometMasterService) configLocator
                .getBean("masterService");
    }

//    @Test
//    public void testSearchUser() throws Throwable {
//        System.out.println(DateUtil.convertDateToString(new Date(), "dd-MM-yyyy"));
//        System.out.println(DateUtil.convertDateToString(new Date(), "dd-MM-yyyy hh:mm:ss"));
//        System.out.println(
//                DateUtil.convertStringToDate(
//                        DateUtil.convertDateToString(new Date(), "dd-MM-yyyy hh:mm:ss"), "dd-MM-yyyy hh:mm:ss"));
//        System.out.println(
//                DateUtil.convertStringToDate(
//                        DateUtil.convertDateToString(new Date(), "dd-MM-yyyy"), "dd-MM-yyyy"));
//        UserSearchRequest searchRequest = new UserSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("userID", "admin"));
//        searchRequest.setSearchCriterionList(searchCriterionList);
//        System.out.println(masterService.getUser(searchRequest, true).get(0));
//    }
    @Test
    public void testSku() throws Throwable {
//        SkuSearchRequest skuSearchRequest = new SkuSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.eq("skuName", "324"));
//        skuSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("salCustomerId", false));
//        skuSearchRequest.setSortOrder(orderList);
//
//        List<Sku> parentList = masterService.getSku(skuSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        Gson gsn = new Gson();
//        System.out.println(gsn.toJson(parentList));
//        for (Sku sku : parentList) {
//            System.out.println(sku.getSkuGenericName() + " : " + sku.getSkuCode());
//        }
        Sku parent = new Sku();
////        parent.setId();
        parent.setSkuGenericName("one");
        parent.setSkuCode("456");
        parent.setSkuName("Stone");
        parent.setSkuDdCompanyIndex(2);
        parent.setSkuGroupRID(4);
        parent.setSkuDdTypeIndex(45);
//        parent.setSkuMinOrderQty(24);
        parent.setSkuExpiryDuration(34);
        parent.setSkuDdBaseUomIndex(87);
        parent.setSkuDdBaseUomConv(23.6f);
        parent.setSkuDdBaseUomDesc("45");
        parent.setSkuDdSaleUomIndex(324);
        parent.setSkuDdSaleUomConv(23.6f);
        parent.setSkuDdSaleUomDesc("34");
        parent.setSkuDdPurUomIndex(5);
        parent.setSkuDdPurUomConv(23.6f);
        parent.setSkuDdPurUomDesc("56");
        parent.setSkuDdPresentationUomIndex(34);
        parent.setSkuDdPresentationUomDesc("45t");
        parent.setSkuSaleVatPerc(23.6f);
        parent.setSkuPurchaseVatPerc(23.6f);
        parent.setSkuLastPurPrice(23.6f);
        parent.setSkuCategory(234);
        parent.setSkuIsReusable(1);
        parent.setSkuIsActive(1);
        parent.setSkuEntityRID(1);
        parent.setModifiedUserRid(2);
//        parent.setSkuLeadTime(20);
        parent.setSkuBudgetedCost(23.6f);
        parent.setSkuAvgPurPriceWithTax(23.6f);
        parent.setSkuLastPurPriceWithTax(23.6f);
        parent.setSkuLastMrp(23.6f);
        parent.setSkuIsPriceEditable(0);
        parent.setSkuConsignmentItem(1);
        parent.setSkuSlRID(2);
        parent.setSkuGroupParentRID(3);
        parent.setSkuIsConsumable(1);
        parent.setSkuClassDdIndex(23);
        parent.setSkuTotalSaleValue(23.6f);
        parent.setSkuTotalSaleQty(23.6f);
        parent.setSkuPercQtyContrib(23.6f);
        parent.setSkuPercRevenueContrib(23.8f);
        parent.setSkuAvgStockValue(23.6f);
        parent.setSkuOrderUomIndex(23);
        parent.setSkuOrderUomDesc("234");
        parent.setSkuParentRID(3);
        parent.setSkuNumOfDaysPassed(3);
//        parent.setSkuAvgLeadTime("23");
        parent.setSkuMaxReusableCount(34);
        parent.setSkuIsNonStockable(1);
        parent.setSkuSubContractedItem(1);
        parent.setSkuIsBillable(1);
        parent.setTempTrgtSkuRID(1);
        parent.setTempTrgtParentSkuRID(2);
        parent.setTempSrcSkuRID(3);
        parent.setTempGroupID(3);
        parent.setTempGroupParentID(2);
        parent.setPortGenericID(21);
        parent.setTrgtSkuRID(6);
        parent.setSkuSaleVatExempted(2);
        parent.setSkuPurVatExempted(3);
        parent.setRecordExist(1);

        Gson gson = new Gson();
        System.out.println(gson.toJson(parent));
        masterService.saveSku(parent, true);
    }
////}
//        List<SkuSupplierMap> childs = new ArrayList<SkuSupplierMap>();
//
//        SkuSupplierMap child1 = new SkuSupplierMap();
//
//        child1.setSsmSkuRID(21);
//        child1.setSsmSupRID(3);
//        child1.setSsmIsPreffered(2);
//        child1.setSsmPurchaseRate(234.435f);
//        child1.setSsmRowInvalidated(3);
//        child1.setSsmModifiedUserRID(4);
//        child1.setSsmModifiedDateTime(DateUtil.getCurrentDateTime());
////
//        childs.add(child1);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(child1));
//        masterService.saveSkuSupplierMap(childs);
//    }
//}
//////
//        List<SkuUnitMap> childSkuUnitMap = new ArrayList<SkuUnitMap>();
//
//        SkuUnitMap child2 = new SkuUnitMap();
//
//        child2.setSumSkuRID(5);
//        child2.setSumUnitRID(2);
//        child2.setSumReorderLevel(2);
//        child2.setSumMaxStockLevel(3);
//        child2.setSumOnStockTaking(3);
//        child2.setSumIndentingUnitRID(4);
//        child2.setSumPurIndentingUnitRID(4);
//        child2.setSumMaxQtyUsedPday(34.546f);
//        child2.setSumAvgQtyUsedPday(34.456f);
//        child2.setTrgtUnitRID(4);
//        child2.setRecordExist(1);
//        child2.setSumEntityRID(1);
//
//        childSkuUnitMap.add(child2);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(child2));
//        masterService.saveSkuUnitMap(childSkuUnitMap);
////
//        parent.setSkuSupplierMapList(null);
//        parent.setSkuUnitMapList(childSkuUnitMap);
//
//    @Test
//    public void testServiceMaster() throws Throwable {
//        ServiceMasterSearchRequest serviceMasterSearchRequest = new ServiceMasterSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.eq("bsGroup", "1"));
//        serviceMasterSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("salCustomerId", false));
//        serviceMasterSearchRequest.setSortOrder(orderList);
//
//        List<ServiceMaster> parentList = masterService.getServiceMaster(serviceMasterSearchRequest);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (ServiceMaster serviceMaster : parentList) {
//            System.out.println(serviceMaster.getBsCode() + " : " + serviceMaster.getBsName());
//        }
////
//        ServiceMaster serv = new ServiceMaster();
//        serv.setBsAdmission("1");
//        serv.setBsCategory("4");
//        serv.setBsCode("Serv43");
//        serv.setBsDaycare("Yes");
//        serv.setBsGroup("4");
//        serv.setBsName("Ananth");
//        serv.setBsParentGroupRid("23");
//        serv.setBsPriceatbill("234");
//        serv.setBsPriceatorder("1");
//        serv.setBsQtyatOrdering("200");
//        serv.setBsQtyatbilling("23");
//        serv.setBsServiceActive("1");
//        serv.setBsServiceConversion("324");
//        serv.setBsServiceEntityRID(2);
//        serv.setBsServiceManual("qwerty");
//        serv.setBsServiceProcedure("asdf");
//        serv.setBsServiceSchedule("Scheduled");
//        serv.setBsServiceType("4");
//        serv.setBsUnit("8");
//        serv.setbEprice(45);
//        serv.setbPrice(54);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(serv));
//        masterService.saveServiceMaster(serv);
//
//    }
//}
//}
//    @Test
//    public void testUser() throws Throwable {
//        UserSearchRequest userSearchRequest = new UserSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.eq("userID", "adminblr"));
//        userSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("salCustomerId", false));
////        serviceMasterSearchRequest.setSortOrder(orderList);
////
//        List<User> parentList = masterService.getUser(userSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (User user : parentList) {
//            System.out.println(user.getUserCity() + " : " + user.getUserFullName());
//        User user = new User();
//
//        user.setUserID("34");
////        user.setUserTypeRID(1);
//        user.setUserType("4");
//        user.setUserPassword("pass");
//        user.setUserFullName("Ajith");
//        user.setUserGender("Male");
//        user.setUserDOB(DateUtil.getCurrentDate());
//        user.setUserStreetAddress("Guindy");
//        user.setUserCity("chennai");
//        user.setUserCountry("Ind");
//        user.setUserPincode("600505");
//        user.setUserPhone("346565");
//        user.setUserMobile("3435w34");
//        user.setUserEmail("2GMAIL");
//        user.setUserAccountRID(3);
//        user.setUserEntityRID(4);
//        user.setUserValid(1);
//        user.setUserRegDateTime(DateUtil.getCurrentDateTime());
//        user.setUserUnRegDateTime(DateUtil.getCurrentDateTime());
//        user.setUserModifiedDateTime(DateUtil.getCurrentDateTime());
//        user.setUserModifiedUserRID(67);
//        user.setUserRowInvalidated(5);
//        user.setUserFeatureRID(6);
//        user.setUserIsCommon(1);
//        user.setUserCode("34");
//        user.setUserPasswordExpiryDate(DateUtil.getCurrentDate());
//
//        user.setUserAccntLockedTillDtime(DateUtil.getCurrentDateTime());
//        user.setUserAccntIsLocked(1);
//        user.setUserIncorrectPasswordCntr(2);
//        user.setUserIsStaff(1);
//        user.setUserIsSystemDefined(1);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(user));
//        masterService.saveUser(user, true);
//}}
//}
////
//        List<UserRoleMap> childUserRoleMap = new ArrayList<UserRoleMap>();
//
//        UserRoleMap child2 = new UserRoleMap();
//
//        child2.setUrUserRID(2);
//        child2.setUrRoleRID(3);
//        childUserRoleMap.add(child2);
//
//        user.setUserRoleMapList(childUserRoleMap);

//        Gson gson = new Gson();
//        System.out.println(gson.toJson(child2));
//        masterService.saveUserRoleMap(child2);
//    }
//}
//    }
//
//    @Test
//    public void testSupplier() throws Throwable {
//        SupplierSearchRequest supplierSearchRequest = new SupplierSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.eq("supCode", "Code1"));
//        supplierSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("salCustomerId", false));
////        serviceMasterSearchRequest.setSortOrder(orderList);
//
//        List<Supplier> parentList = masterService.getSupplier(supplierSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (Supplier supplier : parentList) {
//            System.out.println(supplier.getSupCity() + " : " + supplier.getSupContactPerson());
////        
////    }
//        }
//    }
//
//    @Test
//    public void testRole() throws Throwable {
////        RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
////        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
////        searchCriterionList.add(Restrictions.eq("roleProductRID", 1));
////        roleSearchRequest.setSearchCriterionList(searchCriterionList);
////        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
////        orderList.add(new CriteriaOrder("salCustomerId", false));
////        serviceMasterSearchRequest.setSortOrder(orderList);
////
////        List<Role> parentList = masterService.getRole(roleSearchRequest, true);
////        System.out.println("parentList.getListList()>>" + parentList.size());
////        for (Role role : parentList) {
////            System.out.println(role.getRoleName() + " : " + role.getRoleEntityRID());
////        }
//        Role role = new Role();
//        role.setRoleEntityRID(2);
//        role.setRoleProductRID(3);
//        role.setRoleName("Admin234");
//        role.setRoleValid(2);
//        role.setRoleRecordExist(3);
//        role.setTempTrgtRoleRID(2);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(role));
//        masterService.saveRole(role);
//    }
//}
//
//        List<UserPrivilegeView> childs = new ArrayList<UserPrivilegeView>();
//
//        UserPrivilegeView child1 = new UserPrivilegeView();
//        child1.setUpFeatureCode("Priv23");
//        child1.setUpFeatureRID(3);
//        child1.setUpUserEntityRID(2);
//        childs.add(child1);
//        List<Privilege> childPrivilege = new ArrayList<Privilege>();
//
//        Privilege child2 = new Privilege();
//        child2.setPrivFeatureRID(23);
//        child2.setPrivProdRID(4);
//        child2.setPrivRecordExist(4);
//        child2.setTempTrgtPrivRID(5);
////
//        childPrivilege.add(child2);
//
////        user.setUserPrivilegeViewList(childs);
//        role.setPrivilegeList(childPrivilege);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(role));
////        masterService.saveRole(role, true);
//    }
//
//    @Test
//    public void testUnit() throws Throwable {
//        UnitSearchRequest unitSearchRequest = new UnitSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.eq("id", 1));
//        unitSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("salCustomerId", false));
//        unitSearchRequest.setSortOrder(orderList);
////
//        List<Unit> parentList = masterService.getUnit(unitSearchRequest);
//        System.out.println("parentList.getListList()>>" + parentList.size());
//        for (Unit unit : parentList) {
//            System.out.println(unit.getUnitName() + " : " + unit.getUnitEntityRID());
//        }
//        Unit serv = new Unit();
////
//        serv.setUnitCode("Unit1");
//        serv.setUnitName("unit");
//        serv.setUnitEntityRID(2);
//        serv.setUnitParentRID(34);
//        serv.setUnitHead(34);
//        serv.setUnitWorkHrsFrom("nMA");
//        serv.setUnitWorkHasTo("sTRING");
//        serv.setUnitHasBeds(3);
//        serv.setUnitIsSubStore(4);
//        serv.setUnitIsMAINSTORE(4);
//        serv.setUnitModifiedUserRID(3);
//        serv.setUnitModifiedDateTime("2015-12-12");
//        serv.setUnitIsDepartment(2);
//        serv.setUnitIsClinical(2);
////        serv.setUnitCanMakeGRN(1);
//        serv.setUnitDispName("nAMW4");
//        serv.setUnitStreet("cROSS STREET");
//        serv.setUnitCity(1);
//        serv.setUnitState(2);
//        serv.setUnitCountry(3);
//        serv.setUnitPinCode(23344);
//        serv.setUnitPhone("98754333");
//        serv.setUnitTinNo("No1");
//        serv.setUnitDlNo("435");
//        serv.setUnitDLValidUpto("2days");
//        serv.setUnitIsDefOpPharmacy(1);
//        serv.setUnitIsDefIPPharmacy(2);
//        serv.setUnitIsDefOpMaterial(1);
//        serv.setUnitIsDefIPMaterial(2);
//        serv.setUnitIsExternal(43);
//        serv.setUnitIsServiceProvider(23);
//        serv.setUnitIsPurchasing(2);
//        serv.setUnitCanProcessIndent(23);
//        serv.setUnitCSTNo("CST2");
//        serv.setUnitTypeIndex(2);
//        serv.setUnitCostCenterRID(3);
//        serv.setUnitLocalSTNo("ST2");
//        serv.setUnitTypeIndex(23);
//        serv.setUnitCostCenterRID(45);
//        serv.setUnitLocalSTNo("12");
//        serv.setUnitIsTaxable(1);
//        serv.setUnitCategory(56);
//        serv.setUnitIsSurgical(2);
//        serv.setUnitIsPurchaseTaxable(3);
//        serv.setUnitTracksPatientDelay(8);
//        serv.setUnitIsEmergency(1);
//        serv.setUnitStockKeepingUnitRID(6);
//        serv.setUnitIsNeonatal(1);
//        serv.setUnitIsBilling(1);
//        serv.setUnitRequireMrdRequest(1);
//        serv.setUnitEmailID("asdf@.com");
//        serv.setUnitMapGeographicPoints("sdfg");
////
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(serv));
//        masterService.saveUnit(serv);
//    }
}
//}
//
//    }
//////    @Test
//////    public void testSku() throws Throwable {
////    UserFeatureSearchCriteria userFeatureSearchCriteria = new UserFeatureSearchCriteria();
////    UserFeatureSearchRequest userFeatureSearchRequest = new UserFeatureSearchRequest();
//////        userFeatureSearchRequest.addUserFeatureCriteria(userFeatureSearchCriteria);
////    Gson ss = new Gson();
////        System.out.println(ss.toJson(masterService.getUserFeature(userFeatureSearchRequest)));
////    }
//}
