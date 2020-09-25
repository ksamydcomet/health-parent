package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.MailQueue;
import com.dcomet.fw.domain.MailQueueSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.adapter.MasterAdapter;
import com.dcomet.health.dao.MasterDAO;
import com.dcomet.health.dao.data.DiscountMasterData;
import com.dcomet.health.dao.data.DiscountServiceMapData;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.PayerAuthorizationData;
import com.dcomet.health.dao.data.PayerMasterData;
import com.dcomet.health.dao.data.PayerIncidentData;
import com.dcomet.health.dao.data.PayerServiceMapData;
import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.dao.data.TemplateMailData;
import com.dcomet.health.domain.BedGroupM;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedOccupancy;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.health.domain.DiscountServiceMap;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerAuthorization;
import com.dcomet.health.domain.PayerAuthorizationSearchRequest;
import com.dcomet.health.domain.PayerIncident;
import com.dcomet.health.domain.PayerIncidentSearchRequest;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.PayerServiceMap;
import com.dcomet.health.domain.PrintableInfo;
import com.dcomet.health.domain.PrintableInfoSearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.service.business.BedManagementService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.FileService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.vo.AvailableResource;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.ResourceAvailabilityWithCategory;
import com.dcomet.health.vo.ResourceAvailabiltyVo;
import com.dcomet.module.dao.data.MailQueueData;
import com.dcomet.module.dao.data.ResourceAvailabilityData;
import com.dcomet.module.dao.data.UserData;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.Page;
import com.dcomet.module.domain.Resource;
import com.dcomet.module.domain.ResourceAvailability;
import com.dcomet.module.domain.ResourceAvailabilitySearchRequest;
import com.dcomet.module.domain.ResourceSearchRequest;
import com.dcomet.module.domain.ResourceWorkingHours;
import com.dcomet.module.domain.SysParam;
import com.dcomet.module.domain.Unit;
import com.dcomet.module.domain.User;
import com.dcomet.module.domain.UserSearchRequest;
import com.dcomet.module.domain.UserUnitMap;
import com.dcomet.module.domain.UserUnitMapSearchRequest;
import com.dcomet.module.domain.WardMaster;
import com.dcomet.module.master.domain.ServiceMaster;
import com.dcomet.module.master.domain.ServiceMasterSearchRequest;
import com.dcomet.module.master.domain.ServicePriceType;
import com.dcomet.module.master.service.impl.DCometMasterServiceImpl;
import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("masterService")
@Transactional(propagation = Propagation.SUPPORTS)
public class MasterServiceImpl extends DCometMasterServiceImpl implements MasterService {

    @Autowired
    @Qualifier("masterDAO")
    MasterDAO masterDAO;

    @Autowired
    @Qualifier("masterAdapter")
    MasterAdapter masterAdapter;

    @Autowired
    @Qualifier("fileService")
    FileService fileService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("bedManagementService")
    BedManagementService bedManagementService;

    @Override
    public List<DiscountMaster> getDiscountMaster(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometServiceException {
        List<DiscountMaster> result = null;
        try {
            List<DiscountMasterData> listData = masterDAO.getDiscountMaster(discountMasterSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = masterAdapter.convertDiscountMasterDataToDiscountMaster(listData);
                if (CollectionUtils.isNotEmpty(result)) {
                    for (DiscountMaster discountMaster : result) {
                        discountMaster.setDiscountCategoryText(dataDictionaryService.getDdictByValue(discountMaster.getDisCategory()));
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<PayerAuthorization> getPayerAuthorization(PayerAuthorizationSearchRequest payerAuthorizationSearchRequest, boolean includechilds) throws DcometDAOException {
        List<PayerAuthorization> result = null;
        try {
            List<PayerAuthorizationData> listData = masterDAO.getPayerAuthorization(payerAuthorizationSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = masterAdapter.convertPayerAuthorizationDataToPayerAuthorization(listData);
                if (includechilds) {
                    for (PayerAuthorization payerAuthorization : result) {
                        PayerIncidentSearchRequest childSearchRequest = new PayerIncidentSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("pidPayerRid", payerAuthorization.getPadRid()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<PayerIncident> ServiceResult = getPayerIncident(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(ServiceResult)) {
                            payerAuthorization.setPayerIncident(ServiceResult);
                        }
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public void saveDiscountMaster(DiscountMaster discountMaster) throws DcometServiceException {
        try {
            DiscountMasterData discountMastertData = masterAdapter.convertDiscountMasterToDiscountMasterData(discountMaster);
            masterDAO.saveDiscountMaster(discountMastertData);

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public void savePayerMaster(PayerMaster payerMaster, boolean includeChild) throws DcometDAOException {
        try {
            PayerMasterData payerMasterData = masterAdapter.convertPayerMasterToPayerMasterData(payerMaster);
//            if (payerMasterData.getPdId() != null) {
//                if (CollectionUtils.isNotEmpty(payerMaster.getPayerServiceMap())) {
//                    masterDAO.deletePayerMaster(payerMasterData.getPdId());
//                    masterDAO.deletePayerServiceMap(payerMasterData.getPdId());
//
//                }
//            }
            masterDAO.savePayerMaster(payerMasterData);
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(payerMaster.getPayerServiceMap())) {
                    List<PayerServiceMapData> payerServiceMapDataList = masterAdapter.convertPayerServiceMapToPayerServiceMapData(payerMaster.getPayerServiceMap());
                    for (PayerServiceMapData payerServiceMapData : payerServiceMapDataList) {
                        payerServiceMapData.setPsmPdRid(payerMasterData.getPdId());
                    }
                    masterDAO.savePayerServiceMap(payerServiceMapDataList);
                }

            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<DiscountServiceMap> getDiscountServiceMap(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometServiceException {
        List<DiscountServiceMap> result = null;
        try {
            List<DiscountServiceMapData> discountServiceMapDatalist = masterDAO.getDiscountServiceMap(discountMasterSearchRequest);
            if (CollectionUtils.isNotEmpty(discountServiceMapDatalist)) {
                result = masterAdapter.convertDiscountServiceMapDataToDiscountServiceMap(discountServiceMapDatalist);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePayerAuthorization(PayerAuthorization payerAuthorization, boolean includeChild) throws DcometDAOException {
        try {
            PayerAuthorizationData payerAuthorizationData = masterAdapter.convertPayerAuthorizationToPayerAuthorizationData(payerAuthorization);
            masterDAO.savePayerAuthorization(payerAuthorizationData);
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(payerAuthorization.getPayerIncident())) {
                    List<PayerIncidentData> payerIncidentDataList = masterAdapter.convertPayerIncidentToPayerIncidentData(payerAuthorization.getPayerIncident());
                    for (PayerIncidentData payerIncidentData : payerIncidentDataList) {
                        payerIncidentData.setPidPayerRid(payerIncidentData.getPidRid());
                    }
                    masterDAO.savePayerIncident(payerIncidentDataList);
                }
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<PayerIncident> getPayerIncident(PayerIncidentSearchRequest payerIncidentSearchRequest) throws DcometDAOException {
        List<PayerIncident> result = null;
        try {

            List<PayerIncidentData> listData = masterDAO.getPayerIncident(payerIncidentSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = masterAdapter.convertPayerIncidentDataToPayerIncident(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<PayerMaster> getPayerMaster(PayerMasterSearchRequest payerMasterSearchRequest, boolean includechilds) throws DcometDAOException {
        List<PayerMaster> result = null;
        try {
            List<PayerMasterData> listData = masterDAO.getPayerMaster(payerMasterSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = masterAdapter.convertPayerMasterDataToPayerMaster(listData);
                if (includechilds) {
                    for (PayerMaster payerMaster : result) {
                        PayerMasterSearchRequest childSearchRequest = new PayerMasterSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("psmPdRid", payerMaster.getPdId()));
                        searchCriterionList.add(Restrictions.eq("psmIsActive", 1));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<PayerServiceMap> ServiceResult = getPayerServiceMap(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(ServiceResult)) {
                            payerMaster.setPayerServiceMap(ServiceResult);
                        }
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<PayerServiceMap> getPayerServiceMap(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometDAOException {
        List<PayerServiceMap> result = null;
        try {
            List<PayerServiceMapData> payerServiceMapDatalist = masterDAO.getPayerServiceMap(payerMasterSearchRequest);
            if (CollectionUtils.isNotEmpty(payerServiceMapDatalist)) {
                result = masterAdapter.convertPayerServiceMapDataToPayerServiceMap(payerServiceMapDatalist);

            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<PrintableInfo> getPrintInfo(PrintableInfoSearchRequest printableInfoSearchRequest) throws DcometServiceException {
        List<PrintableInfo> result = null;
        try {
            List<PrintableInfoData> printableInfoData = masterDAO.getPrintInfo(printableInfoSearchRequest);
            if (CollectionUtils.isNotEmpty(printableInfoData)) {
                result = masterAdapter.convertPrintableInfoDataToPrintableInfo(printableInfoData);
            }
        } catch (DcometServiceException e) {
            throw e;
        }
        return result;
    }

    @Override
    public PrintableInfo getPrintInfo(Integer entityRid) throws DcometServiceException {
        return masterAdapter.convertPrintableInfoDataToPrintableInfo(Arrays.asList(masterDAO.getPrintInfo(entityRid))).get(0);
    }

    @Override
    public void savePrintableInfo(List<PrintableInfo> printableInfoList) throws DcometServiceException {
        try {
            List<PrintableInfoData> printableInfoDataList = masterAdapter.convertPrintableInfoToPrintableInfoData(printableInfoList);
            for (PrintableInfoData printableInfoData : printableInfoDataList) {
                masterDAO.savePrintInfo(printableInfoData);
            }
        } catch (DcometServiceException e) {
            throw e;
        }
    }

    @Override
    public PrintableInfoData getPrintInfoLogo(Integer pntEntRid) throws DcometServiceException {
        return masterDAO.getPrintInfoLogo(pntEntRid);
    }

    @Override
    public TemplateMailData getMailTemplate(String tempCode) throws DcometServiceException {
        return masterDAO.getMailTemplate(tempCode).get(0);
    }

    @Override
    public void createMail(String subject, String mailTo, String templateCode, Object object, String prefix) throws DcometServiceException {
        try {
            String string = new String();
            String filePathWithName = null;
            Visit visit = (Visit) object;
            List<FileAttachmentData> fileAttachmentList = fileService.getByRefId(visit.getId(), 0);
            SysParam sysParam = dataDictionaryService.getSysParam("CREATE_EMAIL", visit.getEntityRid());
            if (sysParam != null) {
                subject = sysParam.getParamName();
            }
            MailQueue mailQueue = new MailQueue();
            mailQueue.setMqTo(mailTo);
            mailQueue.setMqSubject(subject);
            string = dataDictionaryService.getReportFromTemplate(templateCode, visit, "visit", visit.getEntityRid());
            string = dataDictionaryService.getReportFromHTML(string, getPrintInfo(visit.getEntityRid()), "pe");
            mailQueue.setMqBody(string);
            if (sysParam != null) {
                if (CollectionUtils.isNotEmpty(fileAttachmentList)) {
                    for (FileAttachmentData fileAttachmentData : fileAttachmentList) {
                        filePathWithName = sysParam.getParamValue();//+ File.separator + fileAttachmentData.getTaFileName()
                        File directory = new File(filePathWithName);
                        if (!directory.exists()) {
                            directory.mkdirs();
                        }
                        File file = new File(directory + File.separator + fileAttachmentData.getTaFileName());
                        file.createNewFile();
                        FileUtils.writeByteArrayToFile(file, fileAttachmentData.getTaFile());
                        mailQueue.setMqFileNameWithPath(file.toString());
                        mailQueue.setMqFileName(fileAttachmentData.getTaFileName());
                        mailQueue.setMqIsAttachmentExist(1);
                    }
                } else {
                    mailQueue.setMqIsAttachmentExist(0);
                }
            }
//        mailQueue.setMqPostDate("");
//        mailQueue.setMqPostTime("");
            mailQueue.setMqSent(0);
            mailQueue.setMqIsHtmlContent(1);
            saveMailQueue(mailQueue);
        } catch (Exception ex) {
            throw new DcometServiceException(ex);
        }
    }

    @Override
    public void saveMailQueue(MailQueue mailQueueList) throws DcometServiceException {
        try {
            MailQueueData mailQueueDataList = masterAdapter.convertMailQueueToMailQueueData(mailQueueList);
            masterDAO.saveMailQueue(mailQueueDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<MailQueue> getMailQueue(MailQueueSearchRequest mailQueueSearchRequest) throws DcometServiceException {
        List<MailQueue> result = null;
        try {
            List<MailQueueData> resultData = masterDAO.getMailQueue(mailQueueSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = masterAdapter.convertMailQueueDataToMailQueue(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<User> getUserInfo(UserSearchRequest userSearchRequest, boolean includeChild) throws DcometDAOException {
        List<User> result = null;
        try {
            List<UserData> resultData = masterDAO.getUser(userSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = masterAdapter.convertUserDataToUser(resultData);
                if (includeChild) {
                    for (User user : result) {
                        List<Page> pageList = getPageLayout(user.getId());
                        Collections.sort(pageList);
                        if (CollectionUtils.isNotEmpty(pageList)) {
                            user.setPageList(pageList);
                        }
                        user.setEntity(dataDictionaryService.getUserEntity(user.getEntityRid(), true));
                        UserUnitMapSearchRequest userUnitMapSearchRequest = new UserUnitMapSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("suUserRID", user.getId()));
                        userUnitMapSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<UserUnitMap> userUnitMapList = getUserUnitMap(userUnitMapSearchRequest, true);
                        if (CollectionUtils.isNotEmpty(userUnitMapList)) {
                            user.setUserUnitMapList(userUnitMapList);
                        }
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    private String getDayOfWeek(Integer dayS) {
        String day = "";
        switch (dayS) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    @Override
    public ResourceAvailabiltyVo getResourceAvailablity(String scheduleDateandTime, String estimatedDuration, String eDIndex, Integer resEntityRid) throws DcometServiceException {
        ResourceAvailabiltyVo resourceAvailabiltyVo = new ResourceAvailabiltyVo();
        resourceAvailabiltyVo.setScheduledDateAndTime(scheduleDateandTime);
        resourceAvailabiltyVo.setEstimatedDuration(estimatedDuration);
        resourceAvailabiltyVo.setEstimatedIndex(eDIndex);

        List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
        List<Ddict> ddicts1 = dataDictionaryService.getDdictByType("STAFF_CATEGORY");

        try {
            String s1 = scheduleDateandTime;
            String[] words = s1.split("\\s");
            String dte = words[0];
            String tim = words[2];
            tim = tim + ":00";

            String eD = estimatedDuration;
            String edHr = "";
            String edM = "";
            if (eD.contains(":")) {
                String[] eds = eD.split(":");
                edHr = eds[0];
                edM = eds[1];
            } else {
                if (eDIndex == "Hrs") {
                    edHr = estimatedDuration;
                    edM = "0";
                } else {
                    edM = estimatedDuration;
                    edHr = "0";
                }
            }
            int edH1 = Integer.valueOf(edHr);
            int edM1 = Integer.valueOf(edM);
            int totMin = 0;
            if (edH1 != 0) {
                totMin = edH1 * 60;
            }
            if (edM1 != 0) {
                totMin = totMin + edM1;
            }

            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date d = df.parse(tim);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, totMin);
            String newTime = df.format(cal.getTime());

            String fTime = tim;
            Time f2Time = Time.valueOf(fTime);
            String tTime = newTime;
            Time t2Time = Time.valueOf(tTime);

            Date sdate = DateUtil.convertStringToDate(scheduleDateandTime);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdate);
            Date dateD = cal1.getTime();

            Calendar c = Calendar.getInstance();
            c.setTime(dateD);
            int dayI = c.get(Calendar.DAY_OF_WEEK);
            String dayS = getDayOfWeek(dayI);
            for (Ddict ddict : ddicts) {
                if (Objects.equals(ddict.getDdictValue(), dayS)) {
                    dayI = ddict.getId();
                }
            }
            resourceAvailabiltyVo.setResourceAvailabilityWithCategorys(getResAvailabilityWithCategory(dayI, dte, ddicts1, f2Time, t2Time, resEntityRid));

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

        return resourceAvailabiltyVo;
    }

    private List<ResourceAvailabilityWithCategory> getResAvailabilityWithCategory(Integer dayI, String sDate, List<Ddict> ddicts1, Time f2Time, Time t2Time, Integer resEntityRid) throws DcometServiceException {
        List<ResourceAvailabilityWithCategory> availabilityWithCategorys = new ArrayList<>();
        List<Ddict> ddicts2 = dataDictionaryService.getDdictByType("UNIT_TYPE");
        int ddictOtCategory = 0;
        String ddictOtCategoryValue = null;
        for (Ddict ddict : ddicts2) {
            if (ddict.getDdictValue().equals("Operation Theatre")) {
                ddictOtCategory = ddict.getId();
                ddictOtCategoryValue = ddict.getDdictValue();
            }
        }
        for (Ddict ddict : ddicts1) {
            ResourceSearchRequest resourceSearchRequest = new ResourceSearchRequest();
            List<Criterion> searchCriterionList = new ArrayList<>();
            searchCriterionList.add(Restrictions.eq("resEntRID", resEntityRid));
            searchCriterionList.add(Restrictions.eq("resCategory", ddict.getId()));
            resourceSearchRequest.setSearchCriterionList(searchCriterionList);
            List<Resource> resources = getResource(resourceSearchRequest, true);
            int countD = 0;
            if (CollectionUtils.isNotEmpty(resources)) {
                ResourceAvailabilityWithCategory availabilityWithCategory = new ResourceAvailabilityWithCategory();
                List<AvailableResource> availableResources = new ArrayList<>();
                for (Resource resource : resources) {
                    if (CollectionUtils.isNotEmpty(resource.getResourceWorkingHours())) {
                        for (ResourceWorkingHours resourceWorkingHours : resource.getResourceWorkingHours()) {
                            if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                                String FromTime = resourceWorkingHours.getWhFromTime();
                                String ToTime = resourceWorkingHours.getWhToTime();//
                                Time f1Time = Time.valueOf(FromTime);
                                Time t1Time = Time.valueOf(ToTime);

                                if (f1Time.before(f2Time) && t1Time.after(t2Time)) {
                                    if (CollectionUtils.isNotEmpty(resource.getResourceAvailability())) {
                                        boolean availablecheck = true;
                                        for (ResourceAvailability resourceAvailability : resource.getResourceAvailability()) {
                                            String s1 = resourceAvailability.getAvailFromDateTime();
                                            String[] words = s1.split("\\s");
                                            String rDate = words[0];
                                            if (Objects.equals(rDate, sDate)) {
                                                availablecheck = false;
                                                String resFromTime = resourceAvailability.getAvailFromDateFromTime();
                                                String resToTime = resourceAvailability.getAvailToDateToTime();
                                                Time resf1Time = Time.valueOf(resFromTime);
                                                Time rest1Time = Time.valueOf(resToTime);
                                                if (resf1Time.before(f2Time) && rest1Time.after(t2Time)) {
                                                    if (countD == 0) {
                                                        availabilityWithCategory.setCategoryName(ddict.getDdictValue());
                                                        availabilityWithCategory.setResourceCategory(ddict.getId());
                                                        countD++;
                                                    }
                                                    AvailableResource availableResource = new AvailableResource();
                                                    availableResource.setResourceName(resource.getResName());
                                                    availableResource.setResourceRid(resource.getId());
                                                    availableResources.add(availableResource);
                                                }
                                            }
                                        }
                                        if (availablecheck) {
                                            if (countD == 0) {
                                                availabilityWithCategory.setCategoryName(ddict.getDdictValue());
                                                availabilityWithCategory.setResourceCategory(ddict.getId());
                                                countD++;
                                            }
                                            AvailableResource availableResource = new AvailableResource();
                                            availableResource.setResourceName(resource.getResName());
                                            availableResource.setResourceRid(resource.getId());
                                            availableResources.add(availableResource);
                                        }
                                    } else {
                                        if (countD == 0) {
                                            availabilityWithCategory.setCategoryName(ddict.getDdictValue());
                                            availabilityWithCategory.setResourceCategory(ddict.getId());
                                            countD++;
                                        }
                                        AvailableResource availableResource = new AvailableResource();
                                        availableResource.setResourceName(resource.getResName());
                                        availableResource.setResourceRid(resource.getId());
                                        availableResources.add(availableResource);
                                    }
                                }
                            }
                        }
                    }
                }
                availabilityWithCategory.setAvailableResources(availableResources);
                availabilityWithCategorys.add(availabilityWithCategory);
            }
        }
        Unit unit = getUnitByType(ddictOtCategory).get(0);
        int countC = 0;
        ResourceAvailabilityWithCategory availabilityWithCategory = new ResourceAvailabilityWithCategory();
        List<AvailableResource> availableResources = new ArrayList<>();
        for (WardMaster wardMaster : unit.getWardMasters()) {
            List<Resource> resources1 = getResourceByTypeRID(wardMaster.getWrdRid());
            if (CollectionUtils.isNotEmpty(resources1)) {
                for (Resource resource : resources1) {
                    if (CollectionUtils.isNotEmpty(resource.getResourceWorkingHours())) {
                        for (ResourceWorkingHours resourceWorkingHours : resource.getResourceWorkingHours()) {

                            if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                                String FromTime = resourceWorkingHours.getWhFromTime();
                                String ToTime = resourceWorkingHours.getWhToTime();
                                Time f1Time = Time.valueOf(FromTime);
                                Time t1Time = Time.valueOf(ToTime);

                                if (f1Time.before(f2Time) && t1Time.after(t2Time)) {
                                    if (CollectionUtils.isNotEmpty(resource.getResourceAvailability())) {
                                        boolean availablecheck = true;
                                        for (ResourceAvailability resourceAvailability : resource.getResourceAvailability()) {
                                            String s1 = resourceAvailability.getAvailFromDateTime();
                                            String[] words = s1.split("\\s");
                                            String rDate = words[0];
                                            if (Objects.equals(rDate, sDate)) {
                                                availablecheck = false;
                                                String resFromTime = resourceAvailability.getAvailFromDateFromTime();
                                                String resToTime = resourceAvailability.getAvailToDateToTime();
                                                Time resf1Time = Time.valueOf(resFromTime);
                                                Time rest1Time = Time.valueOf(resToTime);
                                                if (resf1Time.before(f2Time) && rest1Time.after(t2Time)) {
                                                    if (countC == 0) {
                                                        availabilityWithCategory.setCategoryName(ddictOtCategoryValue);
                                                        availabilityWithCategory.setResourceCategory(0);
                                                        countC++;
                                                    }
                                                    AvailableResource availableResource = new AvailableResource();
                                                    availableResource.setResourceName(resource.getResName());
                                                    availableResource.setResourceRid(resource.getId());
                                                    availableResources.add(availableResource);
                                                }
                                            }
                                        }
                                        if (availablecheck) {
                                            if (countC == 0) {
                                                availabilityWithCategory.setCategoryName(ddictOtCategoryValue);
                                                availabilityWithCategory.setResourceCategory(0);
                                                countC++;
                                            }
                                            AvailableResource availableResource = new AvailableResource();
                                            availableResource.setResourceName(resource.getResName());
                                            availableResource.setResourceRid(resource.getId());
                                            availableResources.add(availableResource);
                                        }
                                    } else {
                                        if (countC == 0) {
                                            availabilityWithCategory.setCategoryName(ddictOtCategoryValue);
                                            availabilityWithCategory.setResourceCategory(0);
                                            countC++;
                                        }
                                        AvailableResource availableResource = new AvailableResource();
                                        availableResource.setResourceName(resource.getResName());
                                        availableResource.setResourceRid(resource.getId());
                                        availableResources.add(availableResource);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        availabilityWithCategory.setAvailableResources(availableResources);
        availabilityWithCategorys.add(availabilityWithCategory);
        return availabilityWithCategorys;
    }

    @Override
    public List<ResourceAvailability> getResourceAvailabilityById(Integer resId) throws DcometServiceException {
        ResourceAvailabilitySearchRequest resourceAvailabilitySearchRequest = new ResourceAvailabilitySearchRequest();
        resourceAvailabilitySearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("availResourceRID", resId)}));
        List<ResourceAvailability> result = getResourceAvailability(resourceAvailabilitySearchRequest);
        return CollectionUtils.isNotEmpty(result) ? result : null;
    }

    @Override
    public void deleteResourceAvailability(ResourceAvailability resourceAvailability) throws DcometServiceException {
        try {
            ResourceAvailabilityData availabilityData = masterAdapter.convertResourceAvailabilityToResourceAvailabilityData(resourceAvailability);
            masterDAO.deleteResourceAvailability(availabilityData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }
}
