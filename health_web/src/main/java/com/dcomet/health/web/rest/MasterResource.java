package com.dcomet.health.web.rest;

import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountMasterSearchCriteria;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.module.master.domain.PackageMaster;
import com.dcomet.module.master.domain.PackageMasterSearchCriteria;
import com.dcomet.module.master.domain.PackageMasterSearchRequest;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerMasterSearchCriteria;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.PrintableInfo;
import com.dcomet.health.domain.PrintableInfoSearchCriteria;
import com.dcomet.health.domain.PrintableInfoSearchRequest;
import com.dcomet.module.domain.WardMaster;
import com.dcomet.module.domain.WardMasterSearchCriteria;
import com.dcomet.module.domain.WardMasterSearchRequest;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.BedChargeVoSearchCriteria;
import com.dcomet.health.vo.BedChargeVoSearchRequest;
import com.dcomet.health.vo.ResourceAvailabilityVoSearchCriteria;
import com.dcomet.health.vo.ResourceAvailabilityVoSearchRequest;
import com.dcomet.health.vo.ResourceAvailabiltyVo;
import com.dcomet.module.dao.data.StaffData;
import com.dcomet.module.domain.FreeConsultationCondition;
import com.dcomet.module.domain.FreeConsultationConditionSearchCriteria;
import com.dcomet.module.domain.FreeConsultationConditionSearchRequest;
import com.dcomet.module.domain.Generic;
import com.dcomet.module.domain.GenericSearchCriteria;
import com.dcomet.module.domain.GenericSearchRequest;
import com.dcomet.module.domain.Privilege;
import com.dcomet.module.domain.PrivilegeSearchCriteria;
import com.dcomet.module.domain.PrivilegeSearchRequest;
import com.dcomet.module.domain.Resource;
import com.dcomet.module.domain.ResourceSearchCriteria;
import com.dcomet.module.domain.ResourceSearchRequest;
import com.dcomet.module.domain.Role;
import com.dcomet.module.domain.RoleSearchCriteria;
import com.dcomet.module.domain.RoleSearchRequest;
import com.dcomet.module.domain.Staff;
import com.dcomet.module.domain.Unit;
import com.dcomet.module.domain.UnitSearchCriteria;
import com.dcomet.module.domain.UnitSearchRequest;
import com.dcomet.module.domain.UnitTaxMap;
import com.dcomet.module.domain.UnitTaxMapSearchCriteria;
import com.dcomet.module.domain.UnitTaxMapSearchRequest;
import com.dcomet.module.domain.User;
import com.dcomet.module.domain.UserFeature;
import com.dcomet.module.domain.UserFeatureSearchCriteria;
import com.dcomet.module.domain.UserFeatureSearchRequest;
import com.dcomet.module.domain.UserSearchCriteria;
import com.dcomet.module.domain.UserSearchRequest;
import com.dcomet.module.domain.UserUnitMap;
import com.dcomet.module.domain.UserUnitMapSearchCriteria;
import com.dcomet.module.domain.UserUnitMapSearchRequest;
import com.dcomet.module.master.domain.ResourceServiceMap;
import com.dcomet.module.master.domain.ResourceServiceMapSearchCriteria;
import com.dcomet.module.master.domain.ResourceServiceMapSearchRequest;
import com.dcomet.module.master.domain.HolidayMaster;
import com.dcomet.module.master.domain.HolidayMasterSearchCriteria;
import com.dcomet.module.master.domain.HolidayMasterSearchRequest;
import com.dcomet.module.master.domain.ServiceMaster;
import com.dcomet.module.master.domain.ServiceMasterSearchCriteria;
import com.dcomet.module.master.domain.ServiceMasterSearchRequest;
import com.dcomet.module.master.domain.ServicePriceType;
import com.dcomet.module.master.domain.ServicePriceTypeSearchCriteria;
import com.dcomet.module.master.domain.ServicePriceTypeSearchRequest;
import com.dcomet.module.master.domain.Sku;
import com.dcomet.module.master.domain.SkuSearchCriteria;
import com.dcomet.module.master.domain.SkuSearchRequest;
import com.dcomet.module.master.domain.StaffSearchCriteria;
import com.dcomet.module.master.domain.StaffSearchRequest;
import com.dcomet.module.master.domain.Supplier;
import com.dcomet.module.master.domain.SupplierSearchCriteria;
import com.dcomet.module.master.domain.SupplierSearchRequest;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("master/v1")
public class MasterResource extends BaseResource {

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    @POST
    @Path("/item/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Sku parent) {
        addSecurityContext(securityContext, parent);
        masterService.saveSku(parent, true);
    }

    @GET
    @Path("/role")
    @Produces("application/json")
    public List<Role> getRole() throws Exception {
        return masterService.getRoleByCache();
    }

    @POST
    @Path("/roles/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(Role parent) {
        masterService.saveRole(parent, true);
    }

    @POST
    @Path("/roles/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Role> search(@Context final SecurityContext securityContext, RoleSearchCriteria RoleSearchCriteria) {
        addSecurityContext(securityContext, RoleSearchCriteria);
        RoleSearchRequest searchRequest = new RoleSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("roleEntityRID", RoleSearchCriteria.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addRoleSearchCriteria(RoleSearchCriteria);
        return masterService.getRole(searchRequest, true);
    }

    @POST
    @Path("/supplier/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, Supplier supplier) {
        addSecurityContext(securityContext, supplier);
        return masterService.saveSupplier(supplier, true);
    }

    @POST
    @Path("/user/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<User> search(@Context final SecurityContext securityContext, UserSearchCriteria userSearchCriteria) {
        addSecurityContext(securityContext, userSearchCriteria);
        UserSearchRequest searchRequest = new UserSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("userEntityRID", userSearchCriteria.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        return masterService.getUser(searchRequest, true);
    }

    @GET
    @Path("/login/user/info")
    @Consumes("application/json")
    @Produces("application/json")
    public User loginInfo(@Context final SecurityContext securityContext) {
        UserSearchRequest userSearchRequest = new UserSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("userID", securityContext.getUserPrincipal().getName()));
        userSearchRequest.setSearchCriterionList(searchCriterionList);
        return masterService.getUserInfo(userSearchRequest, true).get(0);
    }

    @POST
    @Path("/service/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, ServiceMaster service) {
        addSecurityContext(securityContext, service);
        return masterService.saveServiceMaster(service, true);
    }

    @POST
    @Path("/user/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, User parent) {
        addCondtionalSecurityContext(securityContext, parent);
        masterService.saveUser(parent, true);
    }

    @POST
    @Path("/userPaaswordCheck/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer saveCheckPassword(@Context final SecurityContext securityContext, User user) {
        addSecurityContext(securityContext, user);
        return masterService.checkPassword(user);
    }

    @POST
    @Path("/resource/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveResource(@Context final SecurityContext securityContext, Resource resource) {
        addCondtionalSecurityContext(securityContext, resource);
        masterService.saveResource(resource, true);
    }

    @POST
    @Path("/staff/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Staff parent) {
        addCondtionalSecurityContext(securityContext, parent);
        masterService.saveStaff(parent, true);
    }

    @POST
    @Path("/unit/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Unit unit) {
        addSecurityContext(securityContext, unit);
        masterService.saveUnit(unit, true);
    }

    @POST
    @Path("/service/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceMaster> search(@Context final SecurityContext securityContext, ServiceMasterSearchCriteria serviceMasterSearchCriteria) {
        User user = getLoginUser(securityContext);
        ServiceMasterSearchRequest serviceMasterSearchRequest = new ServiceMasterSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bsServiceEntityRID", user.getEntityRid()));
        serviceMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        serviceMasterSearchRequest.addServiceMasterCriteria(serviceMasterSearchCriteria);
        return masterService.getServiceMaster(serviceMasterSearchRequest, true);

    }

    @POST
    @Path("/service/saveservice")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveSalary(@Context final SecurityContext securityContext, List<ServiceMaster> serviceList) {
        User user = getLoginUser(securityContext);
        masterService.saveServiceMasteList(serviceList, user.getEntityRid());
    }

    @POST
    @Path("/privilege/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Privilege> search(@Context final SecurityContext securityContext, PrivilegeSearchCriteria privilegeSearchCriteria) {
        PrivilegeSearchRequest searchRequest = new PrivilegeSearchRequest();
//        addSecurityContext(securityContext, searchRequest);
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("userEntityRID", searchRequest.getEntityRid()));
//        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addPrivilegeSearchCriteria(privilegeSearchCriteria);
        return masterService.getPrivilege(searchRequest, true);

    }

    @POST
    @Path("/package/search")
    @Consumes("application/json")
    @Produces("application/json")

    public List<PackageMaster> search(@Context final SecurityContext securityContext, PackageMasterSearchCriteria packageMasterSearchCriteria) {
        PackageMasterSearchRequest searchRequest = new PackageMasterSearchRequest();
//        addSecurityContext(securityContext, searchRequest);
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("userEntityRID", searchRequest.getEntityRid()));
//        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addPackageMasterCriteria(packageMasterSearchCriteria);
        return masterService.getPackageMasters(searchRequest, true);

    }
//    @POST
//    @Path("/discount/search")
//    @Consumes("application/json")
//    @Produces("application/json")
//
//    public List<DiscountMasterMaster> search(@Context final SecurityContext securityContext, DiscountMasterSearchCriteria discountMasterSearchCriteria) {
//        DiscountMasterSearchRequest searchRequest = new DiscountMasterSearchRequest();
////        addSecurityContext(securityContext, searchRequest);
////        List<Criterion> searchCriterionList = new ArrayList<>();
////        searchCriterionList.add(Restrictions.eq("userEntityRID", searchRequest.getEntityRid()));
////        searchRequest.setSearchCriterionList(searchCriterionList);
//        searchRequest.addDiscountMasterCriteria(discountMasterSearchCriteria);
//        return masterService.getDiscountMasters(searchRequest, true);
//
//    }

    @POST
    @Path("/sku/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Sku> search(@Context final SecurityContext securityContext, SkuSearchCriteria skuSearchCriteria) {
        SkuSearchRequest searchRequest = new SkuSearchRequest();
        addSecurityContext(securityContext, searchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("skuEntityRID", searchRequest.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addSkuSearchCriteria(skuSearchCriteria);
        return masterService.getSku(searchRequest, true);
    }

    @POST
    @Path("/supplier/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Supplier> search(@Context final SecurityContext securityContext, SupplierSearchCriteria supplierSearchCriteria) {
        SupplierSearchRequest searchRequest = new SupplierSearchRequest();
        addSecurityContext(securityContext, searchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("supEntityRID", searchRequest.getEntityRid()));
        searchRequest.setSearchCriterionList(searchCriterionList);
        searchRequest.addSupplierCriteria(supplierSearchCriteria);
        return masterService.getSupplier(searchRequest, true);

    }

    @GET
    @Path("/login/user/unit")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Unit> userUnit(@Context final SecurityContext securityContext) {
        User user = getLoginUser(securityContext);
        UnitSearchRequest unitSearchRequest = new UnitSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("unitEntityRID", user.getEntityRid()));
        unitSearchRequest.setSearchCriterionList(searchCriterionList);
        return masterService.getUnit(unitSearchRequest, false);
    }

    //--------------Resource--------------------
    @POST
    @Path("/resource/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Resource> search(@Context final SecurityContext securityContext, ResourceSearchCriteria resourceSearchCriteria) {
        ResourceSearchRequest resourceSearchRequest = new ResourceSearchRequest();
        addSecurityContext(securityContext, resourceSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("resEntRID", resourceSearchRequest.getEntityRid()));
        resourceSearchRequest.setSearchCriterionList(searchCriterionList);
        resourceSearchRequest.addResourceCriteria(resourceSearchCriteria);
        return masterService.getResource(resourceSearchRequest, true);
    }

    //---------------------ResourceServiceMap-----------------------------------------------
    @POST
    @Path("/resourceServiceMap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ResourceServiceMap> search(@Context final SecurityContext securityContext, ResourceServiceMapSearchCriteria resourceServiceMapSearchCriteria) {
        ResourceServiceMapSearchRequest resourceServiceMapSearchRequest = new ResourceServiceMapSearchRequest();
        addSecurityContext(securityContext, resourceServiceMapSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        resourceServiceMapSearchRequest.setSearchCriterionList(searchCriterionList);
        resourceServiceMapSearchRequest.addResourceServiceMapSearchCriteria(resourceServiceMapSearchCriteria);
        return masterService.getResourceServiceMap(resourceServiceMapSearchRequest);
    }

    //--------------Generic--------------------
    @POST
    @Path("/generic/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Generic> search(GenericSearchCriteria genericSearchCriteria) {
        GenericSearchRequest genericSearchRequest = new GenericSearchRequest();
//        addSecurityContext(securityContext, genericSearchRequest);
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("resEntRID", genericSearchRequest.getEntityRid()));
//        genericSearchRequest.setSearchCriterionList(searchCriterionList);
        genericSearchRequest.addGenericSearchCriteria(genericSearchCriteria);
        return masterService.getGeneric(genericSearchRequest);

    }

    //    --------------UnitTaxMap--------------------
    @POST
    @Path("/unittaxmap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<UnitTaxMap> search(@Context final SecurityContext securityContext, UnitTaxMapSearchCriteria unitTaxMapSearchCriteria) {
        UnitTaxMapSearchRequest unitTaxMapSearchRequest = new UnitTaxMapSearchRequest();
//        addSecurityContext(securityContext, unitTaxMapSearchRequest);
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("resEntRID", unitTaxMapSearchRequest.getEntityRid()));
//        unitTaxMapSearchRequest.setSearchCriterionList(searchCriterionList);
        unitTaxMapSearchRequest.addUnitTaxMapCriteria(unitTaxMapSearchCriteria);
        return masterService.getUnitTaxMap(unitTaxMapSearchRequest);

    }

    //------------------Staff------------------------
    @POST
    @Path("/staff/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Staff> search(@Context final SecurityContext securityContext, StaffSearchCriteria staffSearchCriteria) {
        StaffSearchRequest staffSearchRequest = new StaffSearchRequest();
        addSecurityContext(securityContext, staffSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("staffEntityRID", staffSearchRequest.getEntityRid()));
        staffSearchRequest.setSearchCriterionList(searchCriterionList);
        staffSearchRequest.addStaffSearchCriteria(staffSearchCriteria);
        return masterService.getStaff(staffSearchRequest, true);

    }

    @POST
    @Path("/staffnc/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Staff> searchStaffNc(@Context final SecurityContext securityContext, StaffSearchCriteria staffSearchCriteria) {
        StaffSearchRequest staffSearchRequest = new StaffSearchRequest();
        addSecurityContext(securityContext, staffSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("staffEntityRID", staffSearchRequest.getEntityRid()));
        staffSearchRequest.setSearchCriterionList(searchCriterionList);
        staffSearchRequest.addStaffSearchCriteria(staffSearchCriteria);
        return masterService.getStaff(staffSearchRequest, false);

    }

    //------------------StaffUnitMap------------------------
    @POST
    @Path("/userunit/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<UserUnitMap> searchStaffUnitMap(UserUnitMapSearchCriteria userUnitMapSearchCriteria) {
        UserUnitMapSearchRequest userUnitMapSearchRequest = new UserUnitMapSearchRequest();
        userUnitMapSearchRequest.addUserUnitMapSearchCriteria(userUnitMapSearchCriteria);
        return masterService.getUserUnitMap(userUnitMapSearchRequest);
    }

    @POST
    @Path("/unit/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Unit> search(@Context final SecurityContext securityContext, UnitSearchCriteria unitSearchCriteria) {
        UnitSearchRequest unitSearchRequest = new UnitSearchRequest();
        addSecurityContext(securityContext, unitSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("unitEntityRID", unitSearchRequest.getEntityRid()));
        unitSearchRequest.setSearchCriterionList(searchCriterionList);
        unitSearchRequest.addUnitCriteria(unitSearchCriteria);
        return masterService.getUnit(unitSearchRequest, true);
    }

    //    --------------UserFeature--------------------
    @POST
    @Path("/userFeature/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<UserFeature> search(@Context final SecurityContext securityContext, UserFeatureSearchCriteria userFeatureSearchCriteria) {
        UserFeatureSearchRequest userFeatureSearchRequest = new UserFeatureSearchRequest();
        addSecurityContext(securityContext, userFeatureSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("ufEntityRID", userFeatureSearchRequest.getEntityRid()));
        userFeatureSearchRequest.setSearchCriterionList(searchCriterionList);
        userFeatureSearchRequest.addUserFeatureCriteria(userFeatureSearchCriteria);
        return masterService.getUserFeature(userFeatureSearchRequest);

    }

    @GET
    @Path("/logout")
    public void logout(@Context LoginContext loginContext, @Context HttpServletRequest httpServletRequest) throws LoginException {
        HttpSession session = httpServletRequest.getSession(true);
        session.invalidate();
        loginContext.logout();
    }

    @POST
    @Path("/investigation/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServiceMaster> searchInvestigation(@Context final SecurityContext securityContext, ServiceMasterSearchCriteria servicemasterSearchCriteria) {
        ServiceMasterSearchRequest serviceMasterSearchRequest = new ServiceMasterSearchRequest();
        addSecurityContext(securityContext, serviceMasterSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bsServiceEntityRID", serviceMasterSearchRequest.getEntityRid()));
        serviceMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        serviceMasterSearchRequest.addServiceMasterCriteria(servicemasterSearchCriteria);
        return masterService.getServiceMaster(serviceMasterSearchRequest, true);
    }

    @POST
    @Path("/payer/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PayerMaster> searchPayerMaster(@Context final SecurityContext securityContext, PayerMasterSearchCriteria payerMasterSearchCriteria) {
        PayerMasterSearchRequest payerMasterSearchRequest = new PayerMasterSearchRequest();
        addSecurityContext(securityContext, payerMasterSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("pdEntityRid", payerMasterSearchRequest.getEntityRid()));
        payerMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        payerMasterSearchRequest.addPayerMasterSearchCriteria(payerMasterSearchCriteria);
        return masterService.getPayerMaster(payerMasterSearchRequest, true);
    }

    @POST
    @Path("/payer/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, PayerMaster parent) {
        addSecurityContext(securityContext, parent);
        masterService.savePayerMaster(parent, true);
    }

    //------------------PrintInfo------------------------
    @POST
    @Path("/printInfo/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PrintableInfo> search(@Context final SecurityContext securityContext, PrintableInfoSearchCriteria printableInfoSearchCriteria) {
        addSecurityContext(securityContext, printableInfoSearchCriteria);
        PrintableInfoSearchRequest printableInfoSearchRequest = new PrintableInfoSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("peEntityRid", printableInfoSearchCriteria.getPeEntityRid()));
        printableInfoSearchRequest.setSearchCriterionList(searchCriterionList);
        printableInfoSearchRequest.addPrintableInfoCriteria(printableInfoSearchCriteria);
        return masterService.getPrintInfo(printableInfoSearchRequest);
    }

    @POST
    @Path("/printInfo/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void savePrintableInfo(@Context final SecurityContext securityContext, List<PrintableInfo> printableInfoList) {
        masterService.savePrintableInfo(printableInfoList);
    }

    @GET
    @Path("/printInfo/logo")
    @Produces("image/png")
    public Response getUserPhoto(@QueryParam("id") Integer pntEntRid, @QueryParam("size") String size) throws IOException {
        PrintableInfoData photo = masterService.getPrintInfoLogo(pntEntRid);
        if (photo == null) {
            return null;
        } else {
            return makeResponse(photo, size);
        }
    }

    @GET
    @Path("/dcoctorSign/img")
    @Produces("image/png")
    public Response getDoctorSign(@QueryParam("id") Integer doctorRid, @QueryParam("size") String size) throws IOException {
        StaffData photo = masterService.getDoctorSignature(doctorRid);
        if (photo == null) {
            return null;
        } else {
            return makeResponse1(photo, size);
        }
    }

    //------------------HolidayMaster------------------------
    @POST
    @Path("/holiday/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<HolidayMaster> searchHolidayMaster(@Context final SecurityContext securityContext, HolidayMasterSearchCriteria holidayMasterSearchCriteria) {
        addSecurityContext(securityContext, holidayMasterSearchCriteria);
        HolidayMasterSearchRequest holidayMasterSearchRequest = new HolidayMasterSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("hmHolidayEntity", holidayMasterSearchCriteria.getEntityRid()));
        holidayMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        holidayMasterSearchRequest.addHolidayCriteria(holidayMasterSearchCriteria);
        return masterService.getHolidayMaster(holidayMasterSearchRequest);
    }

    @POST
    @Path("/holiday/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveHolidayMaster(@Context final SecurityContext securityContext, List<HolidayMaster> holidayMasterList) {
        masterService.saveHolidayMaster(holidayMasterList);
    }

//    @POST
//    @Path("/servicepoint/save")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public void saveServicePoint(@Context final SecurityContext securityContext, WardMaster parent) {
//        addCondtionalSecurityContext(securityContext, parent);
//        masterService.saveWardMaster(parent, true);
//    }
    //------------------Staff------------------------
    @POST
    @Path("/servicepoint/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<WardMaster> searchWardMaster(@Context final SecurityContext securityContext, WardMasterSearchCriteria wardMasterSearchCriteria) {
        WardMasterSearchRequest wardMasterSearchRequest = new WardMasterSearchRequest();
        addSecurityContext(securityContext, wardMasterSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("wrdEntityRid", wardMasterSearchRequest.getEntityRid()));
        wardMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        wardMasterSearchRequest.addWardMasterSearchCriteria(wardMasterSearchCriteria);
        return masterService.getWardMaster(wardMasterSearchRequest, true);

    }

    //------------------FreeConsultationCondition------------------------
    @POST
    @Path("/fccondition/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FreeConsultationCondition> searchFreeConsultationCondition(@Context final SecurityContext securityContext, FreeConsultationConditionSearchCriteria freeConsultationConditionSearchCriteria) {
        addSecurityContext(securityContext, freeConsultationConditionSearchCriteria);
        FreeConsultationConditionSearchRequest freeConsultationConditionSearchRequest = new FreeConsultationConditionSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<>();        
//        freeConsultationConditionSearchRequest.setSearchCriterionList(searchCriterionList);
        freeConsultationConditionSearchRequest.addFreeConsultationConditionCriteria(freeConsultationConditionSearchCriteria);
        return masterService.getFreeConsultationCondition(freeConsultationConditionSearchRequest);
    }

    //----------resourceAvailabilityVo----------------
    @POST
    @Path("resourceavailable/search")
    @Consumes("application/json")
    @Produces("application/json")
    public ResourceAvailabiltyVo search(@Context final SecurityContext securityContext, ResourceAvailabilityVoSearchCriteria resourceAvailabilityVoSearchCriteria) {
        ResourceAvailabilityVoSearchRequest resourceAvailabilityVoSearchRequest = new ResourceAvailabilityVoSearchRequest();
        addSecurityContext(securityContext, resourceAvailabilityVoSearchRequest);
        resourceAvailabilityVoSearchRequest.addResourceAvailabilityVoSearchRequest(resourceAvailabilityVoSearchCriteria);
        String scheduledateAndTime = resourceAvailabilityVoSearchCriteria.getScheduledDateAndTime();
        String estimatedDuration = resourceAvailabilityVoSearchCriteria.getEstimatedDuration();
        String eDindex = resourceAvailabilityVoSearchCriteria.getEstimatedIndex();
        Integer resEntityRid = resourceAvailabilityVoSearchCriteria.getResEntityRid();
        ResourceAvailabiltyVo resourceAvailabiltyVo = masterService.getResourceAvailablity(scheduledateAndTime, estimatedDuration, eDindex, resEntityRid);
        return resourceAvailabiltyVo;
    }

    //------------------ServicePriceType------------------------
    @POST
    @Path("/servicePriceType/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ServicePriceType> searchServicePriceType(@Context final SecurityContext securityContext, ServicePriceTypeSearchCriteria servicePriceTypeSearchCriteria) {
        addSecurityContext(securityContext, servicePriceTypeSearchCriteria);
        ServicePriceTypeSearchRequest servicePriceTypeSearchRequest = new ServicePriceTypeSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("srPriceEntityRid", servicePriceTypeSearchCriteria.getEntityRid()));
        servicePriceTypeSearchRequest.setSearchCriterionList(searchCriterionList);
        servicePriceTypeSearchRequest.addSearchCriteria(servicePriceTypeSearchCriteria);
        return masterService.getServicePriceType(servicePriceTypeSearchRequest);
    }

    //------------------DiscountSearch------------------------
    @POST
    @Path("/discount/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DiscountMaster> searchDiscount(@Context final SecurityContext securityContext, DiscountMasterSearchCriteria discountMasterSearchCriteria) {
        addSecurityContext(securityContext, discountMasterSearchCriteria);
        DiscountMasterSearchRequest discountMasterSearchRequest = new DiscountMasterSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("disEntityRid", discountMasterSearchCriteria.getEntityRid()));
        discountMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        discountMasterSearchRequest.addDiscountMasterSearchCriteria(discountMasterSearchCriteria);
        return masterService.getDiscountMaster(discountMasterSearchRequest);
    }

    Response makeResponse(PrintableInfoData photo, String size) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);
        try {
            InputStream in = new ByteArrayInputStream(photo.getPeLogo());
            BufferedImage image = ImageIO.read(in);
            if (size != null) {
                float maxDimension = 750.0f;
                if (size.equals("thumbnail")) {
                    maxDimension = 200.0f;
                } else if (size.equals("tinythumbnail")) {
                    maxDimension = 100.0f;
                }
                int origWidth = image.getWidth();
                float widthFactor = origWidth / maxDimension;
                int origHeight = image.getHeight();
                float heightFactor = origHeight / maxDimension;
                float factor = widthFactor > heightFactor ? widthFactor : heightFactor;
                int imageType = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
                image = resizeImageWithHint(image, (int) (origWidth / factor), (int) (origHeight / factor), imageType);
            }
            ImageIO.write(image, "png", bo);
        } catch (IOException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Date expires = new Date(System.currentTimeMillis() + 3600 * 1000);
        return Response.ok().expires(expires).entity(bo.toByteArray()).build();
    }

    Response makeResponse1(StaffData photo, String size) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);
        try {
            InputStream in = new ByteArrayInputStream(photo.getStaffSignature());
            BufferedImage image = ImageIO.read(in);
            if (size != null) {
                float maxDimension = 750.0f;
                if (size.equals("thumbnail")) {
                    maxDimension = 200.0f;
                } else if (size.equals("tinythumbnail")) {
                    maxDimension = 100.0f;
                }
                int origWidth = image.getWidth();
                float widthFactor = origWidth / maxDimension;
                int origHeight = image.getHeight();
                float heightFactor = origHeight / maxDimension;
                float factor = widthFactor > heightFactor ? widthFactor : heightFactor;
                int imageType = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
                image = resizeImageWithHint(image, (int) (origWidth / factor), (int) (origHeight / factor), imageType);
            }
            ImageIO.write(image, "png", bo);
        } catch (IOException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Date expires = new Date(System.currentTimeMillis() + 3600 * 1000);
        return Response.ok().expires(expires).entity(bo.toByteArray()).build();
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }

    @POST
    @Path("/package/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void savePackage(@Context final SecurityContext securityContext, PackageMaster packageMaster) {
        addSecurityContext(securityContext, packageMaster);
        masterService.savePackageMaster(packageMaster, true);
    }

    @POST
    @Path("/discount/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveDiscount(@Context final SecurityContext securityContext, DiscountMaster discountMaster) {
        addSecurityContext(securityContext, discountMaster);
        masterService.saveDiscountMaster(discountMaster);
    }

}
