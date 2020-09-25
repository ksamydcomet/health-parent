package com.dcomet.health.service.business;

import com.dcomet.fw.domain.MailQueue;
import com.dcomet.fw.domain.MailQueueSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.dao.data.TemplateMailData;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.health.domain.DiscountServiceMap;
import com.dcomet.module.master.domain.PackageItemMap;
import com.dcomet.module.master.domain.PackageMaster;
import com.dcomet.module.master.domain.PackageMasterSearchRequest;
import com.dcomet.module.master.domain.PackageServiceMap;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerAuthorization;
import com.dcomet.health.domain.PayerAuthorizationSearchRequest;
import com.dcomet.health.domain.PayerIncident;
import com.dcomet.health.domain.PayerIncidentSearchRequest;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.PayerServiceMap;
import com.dcomet.health.domain.PrintableInfo;
import com.dcomet.health.domain.PrintableInfoSearchRequest;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.ResourceAvailabiltyVo;
import com.dcomet.module.domain.ResourceAvailability;
import com.dcomet.module.domain.User;
import com.dcomet.module.domain.UserSearchRequest;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.List;

public interface MasterService extends DCometMasterService {

    public List<DiscountMaster> getDiscountMaster(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometServiceException;

    public void saveDiscountMaster(DiscountMaster discountMaster) throws DcometServiceException;

    public List<DiscountServiceMap> getDiscountServiceMap(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometServiceException;

    public List<PayerAuthorization> getPayerAuthorization(PayerAuthorizationSearchRequest payerAuthorizationSearchRequest, boolean includechilds) throws DcometDAOException;

    public void savePayerAuthorization(PayerAuthorization payerAuthorization, boolean includeChild) throws DcometServiceException;

    public List<PayerIncident> getPayerIncident(PayerIncidentSearchRequest payerIncidentSearchRequest) throws DcometServiceException;

    public List<PayerMaster> getPayerMaster(PayerMasterSearchRequest payerMasterSearchRequest, boolean includechilds) throws DcometServiceException;

    public void deleteResourceAvailability(ResourceAvailability resourceAvailability) throws DcometServiceException;

    public void savePayerMaster(PayerMaster payerMaster, boolean includeChild) throws DcometServiceException;

    public List<PayerServiceMap> getPayerServiceMap(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometServiceException;

    public List<PrintableInfo> getPrintInfo(PrintableInfoSearchRequest printableInfoSearchRequest) throws DcometServiceException;

    public PrintableInfo getPrintInfo(Integer entityRid) throws DcometServiceException;

    public PrintableInfoData getPrintInfoLogo(Integer pntEntRid) throws DcometServiceException;

    public void savePrintableInfo(List<PrintableInfo> printableInfoList) throws DcometServiceException;

    public TemplateMailData getMailTemplate(String tempCode) throws DcometServiceException;

    public List<MailQueue> getMailQueue(MailQueueSearchRequest mailQueueSearchRequest) throws DcometServiceException;

    public void saveMailQueue(MailQueue mailQueue) throws DcometServiceException;

    public void createMail(String subject, String mailTo, String template, Object object, String prefix) throws DcometServiceException;

    public List<User> getUserInfo(UserSearchRequest userSearchRequest, boolean includeChild) throws DcometDAOException;

    public List<ResourceAvailability> getResourceAvailabilityById(Integer resId) throws DcometServiceException;

    public ResourceAvailabiltyVo getResourceAvailablity(String scheduleDateandTime, String estimatedDuration, String edIndex, Integer resEntityRid) throws DcometServiceException;

}
