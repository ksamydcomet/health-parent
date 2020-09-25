package com.dcomet.health.adapter;

import com.dcomet.fw.domain.MailQueue;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.DiscountMasterData;
import com.dcomet.health.dao.data.DiscountServiceMapData;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.PayerAuthorizationData;
import com.dcomet.health.dao.data.PayerMasterData;
import com.dcomet.health.dao.data.PayerIncidentData;
import com.dcomet.health.dao.data.PayerServiceMapData;
import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountServiceMap;
import com.dcomet.health.domain.FileAttachment;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerAuthorization;
import com.dcomet.health.domain.PayerIncident;
import com.dcomet.health.domain.PayerServiceMap;
import com.dcomet.health.domain.PrintableInfo;
import com.dcomet.module.dao.data.MailQueueData;
import com.dcomet.module.master.adapter.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("masterAdapter")
public class MasterAdapter extends DCometMasterAdapter {

    public List<DiscountMaster> convertDiscountMasterDataToDiscountMaster(List<DiscountMasterData> discountMasterDataList)
            throws DcometServiceException {
        List<DiscountMaster> discountMasterList = new ArrayList<>();
        for (DiscountMasterData discountMasterData : discountMasterDataList) {
            discountMasterList.add(convertDiscountMasterDataToDiscountMaster(discountMasterData));
        }
        return discountMasterList;
    }

    public List<DiscountMasterData> convertDiscountMasterToDiscountMasterData(List<DiscountMaster> discountMasterList)
            throws DcometServiceException {
        List<DiscountMasterData> discountMasterDataList = new ArrayList<>();
        for (DiscountMaster discountMaster : discountMasterList) {
            discountMasterDataList.add(convertDiscountMasterToDiscountMasterData(discountMaster));
        }
        return discountMasterDataList;
    }

    public DiscountMaster convertDiscountMasterDataToDiscountMaster(DiscountMasterData discountMasterData)
            throws DcometServiceException {
        DiscountMaster discountMaster = new DiscountMaster();
        if (discountMasterData.getId() != null) {
            discountMaster.setId(discountMasterData.getId());
        }
        if (discountMasterData.getDisCategory() != null) {
            discountMaster.setDisCategory(discountMasterData.getDisCategory());
        }
        if (discountMasterData.getDisCode() != null) {
            discountMaster.setDisCode(discountMasterData.getDisCode());
        }
        if (discountMasterData.getDisName() != null) {
            discountMaster.setDisName(discountMasterData.getDisName());
        }
        if (discountMasterData.getDisPercentage() != null) {
            discountMaster.setDisPercentage(discountMasterData.getDisPercentage());
        }
        if (discountMasterData.getDisIsActive() != null) {
            discountMaster.setDisIsActive(discountMasterData.getDisIsActive());
        }
        if (discountMasterData.getDisUnitRid() != null) {
            discountMaster.setDisUnitRid(discountMasterData.getDisUnitRid());
        }
        if (discountMasterData.getDisEntityRid() != null) {
            discountMaster.setDisEntityRid(discountMasterData.getDisEntityRid());
        }
        if (discountMasterData.getDisIsApproved() != null) {
            discountMaster.setDisIsApproved(discountMasterData.getDisIsApproved());
        }
        if (discountMasterData.getDisFromDate() != null) {
            discountMaster.setDisFromDate(DateUtil.convertDateToString(discountMasterData.getDisFromDate()));
        }
        if (discountMasterData.getDisToDate() != null) {
            discountMaster.setDisToDate(DateUtil.convertDateToString(discountMasterData.getDisToDate()));
        }

        return discountMaster;
    }

    public DiscountMasterData convertDiscountMasterToDiscountMasterData(DiscountMaster discountMaster)
            throws DcometServiceException {
        DiscountMasterData discountMasterData = new DiscountMasterData();
        if (discountMaster.getId() != null) {
            discountMasterData.setId(discountMaster.getId());
        }
        if (discountMaster.getDisCategory() != null) {
            discountMasterData.setDisCategory(discountMaster.getDisCategory());
        }
        if (discountMaster.getDisName() != null) {
            discountMasterData.setDisName(discountMaster.getDisName());
        }
        if (discountMaster.getDisUnitRid() != null) {
            discountMasterData.setDisUnitRid(discountMaster.getDisUnitRid());
        }
        if (discountMaster.getEntityRid() != null) {
            discountMasterData.setDisEntityRid(discountMaster.getEntityRid());
        }
        if (discountMaster.getDisPercentage() != null) {
            discountMasterData.setDisPercentage(discountMaster.getDisPercentage());
        }
        if (discountMaster.getDisIsActive() != null) {
            discountMasterData.setDisIsActive(discountMaster.getDisIsActive());
        }
        if (discountMaster.getDisIsApproved() != null) {
            discountMasterData.setDisIsApproved(discountMaster.getDisIsApproved());
        }
        if (discountMaster.getDisCode() != null) {
            discountMasterData.setDisCode(discountMaster.getDisCode());
        }
        if (discountMaster.getDisFromDate() != null) {
            discountMasterData.setDisFromDate(DateUtil.convertStringToDate(discountMaster.getDisFromDate()));
        }
        if (discountMaster.getDisToDate() != null) {
            discountMasterData.setDisToDate(DateUtil.convertStringToDate(discountMaster.getDisToDate()));
        }
        return discountMasterData;
    }

    public List<DiscountServiceMap> convertDiscountServiceMapDataToDiscountServiceMap(List<DiscountServiceMapData> discountServiceMapDataList)
            throws DcometServiceException {
        List<DiscountServiceMap> discountServiceMapList = new ArrayList<>();
        for (DiscountServiceMapData discountServiceMapData : discountServiceMapDataList) {
            discountServiceMapList.add(convertDiscountServiceMapDataToDiscountServiceMap(discountServiceMapData));
        }
        return discountServiceMapList;
    }

    public List<DiscountServiceMapData> convertDiscountServiceMapToDiscountServiceMapData(List<DiscountServiceMap> discountServiceMapList)
            throws DcometServiceException {
        List<DiscountServiceMapData> discountServiceMapDataList = new ArrayList<>();
        for (DiscountServiceMap discountServiceMap : discountServiceMapList) {
            discountServiceMapDataList.add(convertDiscountServiceMapToDiscountServiceMapData(discountServiceMap));
        }
        return discountServiceMapDataList;
    }

    public DiscountServiceMap convertDiscountServiceMapDataToDiscountServiceMap(DiscountServiceMapData discountServiceMapData)
            throws DcometServiceException {
        DiscountServiceMap discountServiceMap = new DiscountServiceMap();
        if (discountServiceMapData.getDsmRid() != null) {
            discountServiceMap.setDsmRid(discountServiceMapData.getDsmRid());
        }
        if (discountServiceMapData.getDsmServiceRid() != null) {
            discountServiceMap.setDsmServiceRid(discountServiceMapData.getDsmServiceRid());
        }
        if (discountServiceMapData.getDsmDisRid() != null) {
            discountServiceMap.setDsmDisRid(discountServiceMapData.getDsmDisRid());
        }
        if (discountServiceMapData.getDsmServiceName() != null) {
            discountServiceMap.setDsmServiceName(discountServiceMapData.getDsmServiceName());
        }
        if (discountServiceMapData.getDsmDisPercentage() != null) {
            discountServiceMap.setDsmDisPercentage(discountServiceMapData.getDsmDisPercentage());
        }
        if (discountServiceMapData.getDsmDisAmount() != null) {
            discountServiceMap.setDsmDisAmount(discountServiceMapData.getDsmDisAmount());
        }
        return discountServiceMap;
    }

    public DiscountServiceMapData convertDiscountServiceMapToDiscountServiceMapData(DiscountServiceMap discountServiceMap)
            throws DcometServiceException {
        DiscountServiceMapData discountServiceMapData = new DiscountServiceMapData();
        if (discountServiceMap.getDsmRid() != null) {
            discountServiceMapData.setDsmRid(discountServiceMap.getDsmRid());
        }
        if (discountServiceMap.getDsmServiceRid() != null) {
            discountServiceMapData.setDsmServiceRid(discountServiceMap.getDsmServiceRid());
        }
        if (discountServiceMap.getDsmDisRid() != null) {
            discountServiceMapData.setDsmDisRid(discountServiceMap.getDsmDisRid());
        }
        if (discountServiceMap.getDsmServiceName() != null) {
            discountServiceMapData.setDsmServiceName(discountServiceMap.getDsmServiceName());
        }
        if (discountServiceMap.getDsmDisPercentage() != null) {
            discountServiceMapData.setDsmDisPercentage(discountServiceMap.getDsmDisPercentage());
        }
        if (discountServiceMap.getDsmDisAmount() != null) {
            discountServiceMapData.setDsmDisAmount(discountServiceMap.getDsmDisAmount());
        }
        return discountServiceMapData;
    }

    public List<PayerAuthorization> convertPayerAuthorizationDataToPayerAuthorization(List<PayerAuthorizationData> payerAuthorizationDataList)
            throws DcometServiceException {
        List<PayerAuthorization> payerAuthorizationList = new ArrayList<>();
        for (PayerAuthorizationData payerAuthorizationData : payerAuthorizationDataList) {
            payerAuthorizationList.add(convertPayerAuthorizationDataToPayerAuthorization(payerAuthorizationData));
        }
        return payerAuthorizationList;
    }

    public List<PayerAuthorizationData> convertPayerAuthorizationToPayerAuthorizationData(List<PayerAuthorization> payerAuthorizationList)
            throws DcometServiceException {
        List<PayerAuthorizationData> payerAuthorizationDataList = new ArrayList<>();
        for (PayerAuthorization payerAuthorization : payerAuthorizationList) {
            payerAuthorizationDataList.add(convertPayerAuthorizationToPayerAuthorizationData(payerAuthorization));
        }
        return payerAuthorizationDataList;
    }

    public PayerAuthorization convertPayerAuthorizationDataToPayerAuthorization(PayerAuthorizationData payerAuthorizationData)
            throws DcometServiceException {
        PayerAuthorization payerAuthorization = new PayerAuthorization();
        if (payerAuthorizationData.getCreatedDateTime() != null) {
            payerAuthorization.setCreatedDateTime(DateUtil.convertCalendarToString(payerAuthorizationData.getCreatedDateTime()));
        }
        if (payerAuthorizationData.getCreatedUserRid() != null) {
            payerAuthorization.setCreatedUserRid(payerAuthorizationData.getCreatedUserRid());
        }
        if (payerAuthorizationData.getModifiedDateTime() != null) {
            payerAuthorization.setModifiedDateTime(DateUtil.convertCalendarToString(payerAuthorizationData.getModifiedDateTime()));
        }
        if (payerAuthorizationData.getModifiedUserRid() != null) {
            payerAuthorization.setModifiedUserRid(payerAuthorizationData.getModifiedUserRid());
        }
        if (payerAuthorizationData.getPadAgencyRid() != null) {
            payerAuthorization.setPadAgencyRid(payerAuthorizationData.getPadAgencyRid());
        }
        if (payerAuthorizationData.getPadBoCode() != null) {
            payerAuthorization.setPadBoCode(payerAuthorizationData.getPadBoCode());
        }
        if (payerAuthorizationData.getPadBoRid() != null) {
            payerAuthorization.setPadBoRid(payerAuthorizationData.getPadBoRid());
        }
        if (payerAuthorizationData.getPadEligibilityAmt() != null) {
            payerAuthorization.setPadEligibilityAmt(payerAuthorizationData.getPadEligibilityAmt());
        }
        if (payerAuthorizationData.getPadEntityRid() != null) {
            payerAuthorization.setPadEntityRid(payerAuthorizationData.getPadEntityRid());
        }
        if (payerAuthorizationData.getPadGopRejRemarks() != null) {
            payerAuthorization.setPadGopRejRemarks(payerAuthorizationData.getPadGopRejRemarks());
        }
        if (payerAuthorizationData.getPadGopRequestedDate() != null) {
            payerAuthorization.setPadGopRequestedDate(DateUtil.convertCalendarToString(payerAuthorizationData.getPadGopRequestedDate()));
        }
        if (payerAuthorizationData.getPadGopState() != null) {
            payerAuthorization.setPadGopState(payerAuthorizationData.getPadGopState());
        }
        if (payerAuthorizationData.getPadIdentificationNo() != null) {
            payerAuthorization.setPadIdentificationNo(payerAuthorizationData.getPadIdentificationNo());
        }
        if (payerAuthorizationData.getPadIncidentRid() != null) {
            payerAuthorization.setPadIncidentRid(payerAuthorizationData.getPadIncidentRid());
        }
        if (payerAuthorizationData.getPadIsValid() != null) {
            payerAuthorization.setPadIsValid(payerAuthorizationData.getPadIsValid());
        }
        if (payerAuthorizationData.getPadPayerRid() != null) {
            payerAuthorization.setPadPayerRid(payerAuthorizationData.getPadPayerRid());
        }
        if (payerAuthorizationData.getPadPlanApprovalNo() != null) {
            payerAuthorization.setPadPlanApprovalNo(payerAuthorizationData.getPadPlanApprovalNo());
        }
        if (payerAuthorizationData.getPadPlanRid() != null) {
            payerAuthorization.setPadPlanRid(payerAuthorizationData.getPadPlanRid());
        }
        if (payerAuthorizationData.getPadRejectionReasonIndex() != null) {
            payerAuthorization.setPadRejectionReasonIndex(payerAuthorizationData.getPadRejectionReasonIndex());
        }
        if (payerAuthorizationData.getPadRemarks() != null) {
            payerAuthorization.setPadRemarks(payerAuthorizationData.getPadRemarks());
        }
        if (payerAuthorizationData.getPadRid() != null) {
            payerAuthorization.setPadRid(payerAuthorizationData.getPadRid());
        }
        if (payerAuthorizationData.getPadState() != null) {
            payerAuthorization.setPadState(payerAuthorizationData.getPadState());
        }
        if (payerAuthorizationData.getPadStatus() != null) {
            payerAuthorization.setPadStatus(payerAuthorizationData.getPadStatus());
        }
        if (payerAuthorizationData.getPadValidFrom() != null) {
            payerAuthorization.setPadValidFrom(DateUtil.convertDateToString(payerAuthorizationData.getPadValidFrom()));
        }
        if (payerAuthorizationData.getPadValidTo() != null) {
            payerAuthorization.setPadValidTo(DateUtil.convertDateToString(payerAuthorizationData.getPadValidTo()));
        }
        return payerAuthorization;
    }

    public PayerAuthorizationData convertPayerAuthorizationToPayerAuthorizationData(PayerAuthorization payerAuthorization)
            throws DcometServiceException {
        PayerAuthorizationData payerAuthorizationData = new PayerAuthorizationData();

        if (payerAuthorization.getCreatedUserRid() != null) {
            payerAuthorizationData.setCreatedUserRid(payerAuthorization.getCreatedUserRid());
        }

        if (payerAuthorization.getModifiedUserRid() != null) {
            payerAuthorizationData.setModifiedUserRid(payerAuthorization.getModifiedUserRid());
        }
        if (payerAuthorization.getPadAgencyRid() != null) {
            payerAuthorizationData.setPadAgencyRid(payerAuthorization.getPadAgencyRid());
        }
        if (payerAuthorization.getPadBoCode() != null) {
            payerAuthorizationData.setPadBoCode(payerAuthorization.getPadBoCode());
        }
        if (payerAuthorization.getPadBoRid() != null) {
            payerAuthorizationData.setPadBoRid(payerAuthorization.getPadBoRid());
        }
        if (payerAuthorization.getPadEligibilityAmt() != null) {
            payerAuthorizationData.setPadEligibilityAmt(payerAuthorization.getPadEligibilityAmt());
        }
        if (payerAuthorization.getPadEntityRid() != null) {
            payerAuthorizationData.setPadEntityRid(payerAuthorization.getPadEntityRid());
        }
        if (payerAuthorization.getPadGopRejRemarks() != null) {
            payerAuthorizationData.setPadGopRejRemarks(payerAuthorization.getPadGopRejRemarks());
        }
        if (payerAuthorization.getPadGopRequestedDate() != null) {
            payerAuthorizationData.setPadGopRequestedDate(DateUtil.convertStringToCalendar(payerAuthorization.getPadGopRequestedDate()));
        }
        if (payerAuthorization.getPadGopState() != null) {
            payerAuthorizationData.setPadGopState(payerAuthorization.getPadGopState());
        }
        if (payerAuthorization.getPadIdentificationNo() != null) {
            payerAuthorizationData.setPadIdentificationNo(payerAuthorization.getPadIdentificationNo());
        }
        if (payerAuthorization.getPadIncidentRid() != null) {
            payerAuthorizationData.setPadIncidentRid(payerAuthorization.getPadIncidentRid());
        }
        if (payerAuthorization.getPadIsValid() != null) {
            payerAuthorizationData.setPadIsValid(payerAuthorization.getPadIsValid());
        }
        if (payerAuthorization.getPadPayerRid() != null) {
            payerAuthorizationData.setPadPayerRid(payerAuthorization.getPadPayerRid());
        }
        if (payerAuthorization.getPadPlanApprovalNo() != null) {
            payerAuthorizationData.setPadPlanApprovalNo(payerAuthorization.getPadPlanApprovalNo());
        }
        if (payerAuthorization.getPadPlanRid() != null) {
            payerAuthorizationData.setPadPlanRid(payerAuthorization.getPadPlanRid());
        }
        if (payerAuthorization.getPadRejectionReasonIndex() != null) {
            payerAuthorizationData.setPadRejectionReasonIndex(payerAuthorization.getPadRejectionReasonIndex());
        }
        if (payerAuthorization.getPadRemarks() != null) {
            payerAuthorizationData.setPadRemarks(payerAuthorization.getPadRemarks());
        }
        if (payerAuthorization.getPadRid() != null) {
            payerAuthorizationData.setPadRid(payerAuthorization.getPadRid());
        }
        if (payerAuthorization.getPadState() != null) {
            payerAuthorizationData.setPadState(payerAuthorization.getPadState());
        }
        if (payerAuthorization.getPadStatus() != null) {
            payerAuthorizationData.setPadStatus(payerAuthorization.getPadStatus());
        }
        if (payerAuthorization.getPadValidFrom() != null) {
            payerAuthorizationData.setPadValidFrom(DateUtil.convertStringToDate(payerAuthorization.getPadValidFrom()));
        }
        if (payerAuthorization.getPadValidTo() != null) {
            payerAuthorizationData.setPadValidTo(DateUtil.convertStringToDate(payerAuthorization.getPadValidTo()));
        }
        return payerAuthorizationData;
    }

    //------------------------------------------payerIncident-----------------------------------------------------------------------------------
    public List<PayerIncident> convertPayerIncidentDataToPayerIncident(List<PayerIncidentData> payerIncidentDataList)
            throws DcometServiceException {
        List<PayerIncident> payerIncidentList = new ArrayList<>();
        for (PayerIncidentData payerIncidentData : payerIncidentDataList) {
            payerIncidentList.add(convertPayerIncidentDataToPayerIncident(payerIncidentData));
        }
        return payerIncidentList;
    }

    public List<PayerIncidentData> convertPayerIncidentToPayerIncidentData(List<PayerIncident> payerIncidentList)
            throws DcometServiceException {
        List<PayerIncidentData> payerIncidentDataList = new ArrayList<>();
        for (PayerIncident payerIncident : payerIncidentList) {
            payerIncidentDataList.add(convertPayerIncidentToPayerIncidentData(payerIncident));
        }
        return payerIncidentDataList;
    }

    public PayerIncident convertPayerIncidentDataToPayerIncident(PayerIncidentData payerIncidentData)
            throws DcometServiceException {
        PayerIncident payerIncident = new PayerIncident();
        if (payerIncidentData.getPidValidTo() != null) {
            payerIncident.setPidValidTo(DateUtil.convertDateToString(payerIncidentData.getPidValidTo()));
        }
        if (payerIncidentData.getPidValidFrom() != null) {
            payerIncident.setPidValidFrom(DateUtil.convertDateToString(payerIncidentData.getPidValidFrom()));
        }
        if (payerIncidentData.getPidRid() != null) {
            payerIncident.setPidRid(payerIncidentData.getPidRid());
        }
        if (payerIncidentData.getPidRemarks() != null) {
            payerIncident.setPidRemarks(payerIncidentData.getPidRemarks());
        }
        if (payerIncidentData.getPidPolicyHolderName() != null) {
            payerIncident.setPidPolicyHolderName(payerIncidentData.getPidPolicyHolderName());
        }
        if (payerIncidentData.getPidPolicyHolderId() != null) {
            payerIncident.setPidPolicyHolderId(payerIncidentData.getPidPolicyHolderId());
        }
        if (payerIncidentData.getPidPlanRid() != null) {
            payerIncident.setPidPlanRid(payerIncidentData.getPidPlanRid());
        }
        if (payerIncidentData.getPidPlanApprovalNo() != null) {
            payerIncident.setPidPlanApprovalNo(payerIncidentData.getPidPlanApprovalNo());
        }
        if (payerIncidentData.getPidPayerRidForDependent() != null) {
            payerIncident.setPidPayerRidForDependent(payerIncidentData.getPidPayerRidForDependent());
        }
        if (payerIncidentData.getPidPayerRid() != null) {
            payerIncident.setPidPayerRid(payerIncidentData.getPidPayerRid());
        }
        if (payerIncidentData.getPidPatientPlanType() != null) {
            payerIncident.setPidPatientPlanType(payerIncidentData.getPidPatientPlanType());
        }
        if (payerIncidentData.getPidPatCategory() != null) {
            payerIncident.setPidPatCategory(payerIncidentData.getPidPatCategory());
        }
        if (payerIncidentData.getPidMinDeductable() != null) {
            payerIncident.setPidMinDeductable(payerIncidentData.getPidMinDeductable());
        }
        if (payerIncidentData.getPidIsFinDtlsUpdated() != null) {
            payerIncident.setPidIsFinDtlsUpdated(payerIncidentData.getPidIsFinDtlsUpdated());
        }
        if (payerIncidentData.getPidInsuranceName() != null) {
            payerIncident.setPidInsuranceName(payerIncidentData.getPidInsuranceName());
        }
        if (payerIncidentData.getPidIncidentType() != null) {
            payerIncident.setPidIncidentType(payerIncidentData.getPidIncidentType());
        }
        if (payerIncidentData.getPidIncidentRid() != null) {
            payerIncident.setPidIncidentRid(payerIncidentData.getPidIncidentRid());
        }
        if (payerIncidentData.getCreatedDateTime() != null) {
            payerIncident.setCreatedDateTime(DateUtil.convertCalendarToString(payerIncidentData.getCreatedDateTime()));
        }
        if (payerIncidentData.getCreatedUserRid() != null) {
            payerIncident.setCreatedUserRid(payerIncidentData.getCreatedUserRid());
        }
        if (payerIncidentData.getModifiedDateTime() != null) {
            payerIncident.setModifiedDateTime(DateUtil.convertCalendarToString(payerIncidentData.getModifiedDateTime()));
        }
        if (payerIncidentData.getModifiedUserRid() != null) {
            payerIncident.setModifiedUserRid(payerIncidentData.getModifiedUserRid());
        }
        if (payerIncidentData.getPidAgencyRid() != null) {
            payerIncident.setPidAgencyRid(payerIncidentData.getPidAgencyRid());
        }
        if (payerIncidentData.getPidBillingBedtypeRid() != null) {
            payerIncident.setPidBillingBedtypeRid(payerIncidentData.getPidBillingBedtypeRid());
        }
        if (payerIncidentData.getPidEligibilityAmt() != null) {
            payerIncident.setPidEligibilityAmt(payerIncidentData.getPidEligibilityAmt());
        }
        if (payerIncidentData.getPidEntityRid() != null) {
            payerIncident.setPidEntityRid(payerIncidentData.getPidEntityRid());
        }
        if (payerIncidentData.getPidIdentificationNo() != null) {
            payerIncident.setPidIdentificationNo(payerIncidentData.getPidIdentificationNo());
        }
        if (payerIncidentData.getPidIncidentDate() != null) {
            payerIncident.setPidIncidentDate(DateUtil.convertDateToString(payerIncidentData.getPidIncidentDate()));
        }

        return payerIncident;
    }

    public PayerIncidentData convertPayerIncidentToPayerIncidentData(PayerIncident payerIncident)
            throws DcometServiceException {
        PayerIncidentData payerIncidentData = new PayerIncidentData();

        if (payerIncident.getPidValidTo() != null) {
            payerIncidentData.setPidValidTo(DateUtil.convertStringToDate(payerIncident.getPidValidTo()));
        }
        if (payerIncident.getPidValidFrom() != null) {
            payerIncidentData.setPidValidFrom(DateUtil.convertStringToDate(payerIncident.getPidValidFrom()));
        }
        if (payerIncident.getPidRid() != null) {
            payerIncidentData.setPidRid(payerIncident.getPidRid());
        }
        if (payerIncident.getPidRemarks() != null) {
            payerIncidentData.setPidRemarks(payerIncident.getPidRemarks());
        }
        if (payerIncident.getPidPolicyHolderName() != null) {
            payerIncidentData.setPidPolicyHolderName(payerIncident.getPidPolicyHolderName());
        }
        if (payerIncident.getPidPolicyHolderId() != null) {
            payerIncidentData.setPidPolicyHolderId(payerIncident.getPidPolicyHolderId());
        }
        if (payerIncident.getPidPlanRid() != null) {
            payerIncidentData.setPidPlanRid(payerIncident.getPidPlanRid());
        }
        if (payerIncident.getPidPlanApprovalNo() != null) {
            payerIncidentData.setPidPlanApprovalNo(payerIncident.getPidPlanApprovalNo());
        }
        if (payerIncident.getPidPayerRidForDependent() != null) {
            payerIncidentData.setPidPayerRidForDependent(payerIncident.getPidPayerRidForDependent());
        }
        if (payerIncident.getPidPayerRid() != null) {
            payerIncidentData.setPidPayerRid(payerIncident.getPidPayerRid());
        }
        if (payerIncident.getPidPatientPlanType() != null) {
            payerIncidentData.setPidPatientPlanType(payerIncident.getPidPatientPlanType());
        }
        if (payerIncident.getPidPatCategory() != null) {
            payerIncidentData.setPidPatCategory(payerIncident.getPidPatCategory());
        }
        if (payerIncident.getPidMinDeductable() != null) {
            payerIncidentData.setPidMinDeductable(payerIncident.getPidMinDeductable());
        }
        if (payerIncident.getPidIsFinDtlsUpdated() != null) {
            payerIncidentData.setPidIsFinDtlsUpdated(payerIncident.getPidIsFinDtlsUpdated());
        }
        if (payerIncident.getPidInsuranceName() != null) {
            payerIncidentData.setPidInsuranceName(payerIncident.getPidInsuranceName());
        }
        if (payerIncident.getPidIncidentType() != null) {
            payerIncidentData.setPidIncidentType(payerIncident.getPidIncidentType());
        }
        if (payerIncident.getPidIncidentRid() != null) {
            payerIncidentData.setPidIncidentRid(payerIncident.getPidIncidentRid());
        }

        if (payerIncident.getCreatedUserRid() != null) {
            payerIncidentData.setCreatedUserRid(payerIncident.getCreatedUserRid());
        }

        if (payerIncident.getModifiedUserRid() != null) {
            payerIncidentData.setModifiedUserRid(payerIncident.getModifiedUserRid());
        }
        if (payerIncident.getPidAgencyRid() != null) {
            payerIncidentData.setPidAgencyRid(payerIncident.getPidAgencyRid());
        }
        if (payerIncident.getPidBillingBedtypeRid() != null) {
            payerIncidentData.setPidBillingBedtypeRid(payerIncident.getPidBillingBedtypeRid());
        }
        if (payerIncident.getPidEligibilityAmt() != null) {
            payerIncidentData.setPidEligibilityAmt(payerIncident.getPidEligibilityAmt());
        }
        if (payerIncident.getPidEntityRid() != null) {
            payerIncidentData.setPidEntityRid(payerIncident.getPidEntityRid());
        }
        if (payerIncident.getPidIdentificationNo() != null) {
            payerIncidentData.setPidIdentificationNo(payerIncident.getPidIdentificationNo());
        }
        if (payerIncident.getPidIncidentDate() != null) {
            payerIncidentData.setPidIncidentDate(DateUtil.convertStringToDate(payerIncident.getPidIncidentDate()));
        }

        return payerIncidentData;
    }

    //----------------------------------------------------Payer-------------------------------------------------------------------------------------------
    public List<PayerMaster> convertPayerMasterDataToPayerMaster(List<PayerMasterData> payerMasterDataList)
            throws DcometServiceException {
        List<PayerMaster> payerMasterList = new ArrayList<>();
        for (PayerMasterData payerMasterData : payerMasterDataList) {
            payerMasterList.add(convertPayerMasterDataToPayerMaster(payerMasterData));
        }
        return payerMasterList;
    }

    public List<PayerMasterData> convertPayerMasterToPayerMasterData(List<PayerMaster> payerMasterList)
            throws DcometServiceException {
        List<PayerMasterData> payerMasterDataList = new ArrayList<>();
        for (PayerMaster payerMaster : payerMasterList) {
            payerMasterDataList.add(convertPayerMasterToPayerMasterData(payerMaster));
        }
        return payerMasterDataList;
    }

    public PayerMaster convertPayerMasterDataToPayerMaster(PayerMasterData payerMasterData)
            throws DcometServiceException {
        PayerMaster payerMaster = new PayerMaster();
        if (payerMasterData.getCreatedDateTime() != null) {
            payerMaster.setCreatedDateTime(DateUtil.convertCalendarToString(payerMasterData.getCreatedDateTime()));
        }
        if (payerMasterData.getCreatedUserRid() != null) {
            payerMaster.setCreatedUserRid(payerMasterData.getCreatedUserRid());
        }
        if (payerMasterData.getModifiedDateTime() != null) {
            payerMaster.setModifiedDateTime(DateUtil.convertCalendarToString(payerMasterData.getModifiedDateTime()));
        }
        if (payerMasterData.getModifiedUserRid() != null) {
            payerMaster.setModifiedUserRid(payerMasterData.getModifiedUserRid());
        }
        if (payerMasterData.getPdCity() != null) {
            payerMaster.setPdCity(payerMasterData.getPdCity());
        }
        if (payerMasterData.getPdEntityRid() != null) {
            payerMaster.setPdEntityRid(payerMasterData.getPdEntityRid());
        }
        if (payerMasterData.getPdId() != null) {
            payerMaster.setPdId(payerMasterData.getPdId());
        }
        if (payerMasterData.getPdIsActive() != null) {
            payerMaster.setPdIsActive(payerMasterData.getPdIsActive());
        }
        if (payerMasterData.getPdNuitId()!= null) {
            payerMaster.setPdNuitId(payerMasterData.getPdNuitId());
        }
        if (payerMasterData.getPdContact()!= null) {
            payerMaster.setPdContact(payerMasterData.getPdContact());
        }
        if (payerMasterData.getPdPayerAddress() != null) {
            payerMaster.setPdPayerAddress(payerMasterData.getPdPayerAddress());
        }
        if (payerMasterData.getPdPayerName() != null) {
            payerMaster.setPdPayerName(payerMasterData.getPdPayerName());
        }
        if (payerMasterData.getPdPayerNo() != null) {
            payerMaster.setPdPayerNo(payerMasterData.getPdPayerNo());
        }
        if (payerMasterData.getPdPayerType() != null) {
            payerMaster.setPdPayerType(payerMasterData.getPdPayerType());
        }
        return payerMaster;

    }

    public PayerMasterData convertPayerMasterToPayerMasterData(PayerMaster payerMaster)
            throws DcometServiceException {
        PayerMasterData payerMasterData = new PayerMasterData();
        if (payerMaster.getCreatedUserRid() != null) {
            payerMasterData.setCreatedUserRid(payerMaster.getCreatedUserRid());
        }
        if (payerMaster.getModifiedUserRid() != null) {
            payerMasterData.setModifiedUserRid(payerMaster.getModifiedUserRid());
        }
        if (payerMaster.getPdCity() != null) {
            payerMasterData.setPdCity(payerMaster.getPdCity());
        }
        if (payerMaster.getEntityRid() != null) {
            payerMasterData.setPdEntityRid(payerMaster.getEntityRid());
        }
        if (payerMaster.getPdId() != null) {
            payerMasterData.setPdId(payerMaster.getPdId());
        }
        if (payerMaster.getPdIsActive() != null) {
            payerMasterData.setPdIsActive(payerMaster.getPdIsActive());
        }
        if (payerMaster.getPdNuitId()!= null) {
            payerMasterData.setPdNuitId(payerMaster.getPdNuitId());
        }
        if (payerMaster.getPdContact()!= null) {
            payerMasterData.setPdContact(payerMaster.getPdContact());
        }
        if (payerMaster.getPdPayerAddress() != null) {
            payerMasterData.setPdPayerAddress(payerMaster.getPdPayerAddress());
        }
        if (payerMaster.getPdPayerName() != null) {
            payerMasterData.setPdPayerName(payerMaster.getPdPayerName());
        }
        if (payerMaster.getPdPayerNo() != null) {
            payerMasterData.setPdPayerNo(payerMaster.getPdPayerNo());
        }
        if (payerMaster.getPdPayerType() != null) {
            payerMasterData.setPdPayerType(payerMaster.getPdPayerType());
        }
        return payerMasterData;

    }

    public List<PayerServiceMap> convertPayerServiceMapDataToPayerServiceMap(List<PayerServiceMapData> payerServiceDataList)
            throws DcometServiceException {
        List<PayerServiceMap> payerServiceMapList = new ArrayList<>();
        for (PayerServiceMapData payerServiceMapData : payerServiceDataList) {
            payerServiceMapList.add(convertPayerServiceMapDataToPayerServiceMap(payerServiceMapData));
        }
        return payerServiceMapList;
    }

    public List<PayerServiceMapData> convertPayerServiceMapToPayerServiceMapData(List<PayerServiceMap> payerServiceMapList)
            throws DcometServiceException {
        List<PayerServiceMapData> payerServiceMapDataList = new ArrayList<>();
        for (PayerServiceMap payerServiceMap : payerServiceMapList) {
            payerServiceMapDataList.add(convertPayerServiceMapToPayerServiceMapData(payerServiceMap));
        }
        return payerServiceMapDataList;
    }

    public PayerServiceMapData convertPayerServiceMapToPayerServiceMapData(PayerServiceMap payerServiceMap)
            throws DcometServiceException {
        PayerServiceMapData payerServiceMapData = new PayerServiceMapData();
        if (payerServiceMap.getCreatedUserRid() != null) {
            payerServiceMapData.setCreatedUserRid(payerServiceMap.getCreatedUserRid());
        }
        if (payerServiceMap.getModifiedUserRid() != null) {
            payerServiceMapData.setModifiedUserRid(payerServiceMap.getModifiedUserRid());
        }
        if (payerServiceMap.getPsmDiscountPercent() != null) {
            payerServiceMapData.setPsmDiscountPercent(payerServiceMap.getPsmDiscountPercent());
        }
        if (payerServiceMap.getPsmDiscountVal() != null) {
            payerServiceMapData.setPsmDiscountVal(payerServiceMap.getPsmDiscountVal());
        }
        if (payerServiceMap.getPsmId() != null) {
            payerServiceMapData.setPsmId(payerServiceMap.getPsmId());
        }
        if (payerServiceMap.getPsmIsExcluded() != null) {
            payerServiceMapData.setPsmIsExcluded(payerServiceMap.getPsmIsExcluded());
        }
        if (payerServiceMap.getPsmIsIncluded() != null) {
            payerServiceMapData.setPsmIsIncluded(payerServiceMap.getPsmIsIncluded());
        }
        if (payerServiceMap.getPsmPdRid() != null) {
            payerServiceMapData.setPsmPdRid(payerServiceMap.getPsmPdRid());
        }
        if (payerServiceMap.getPsmServiceCost() != null) {
            payerServiceMapData.setPsmServiceCost(payerServiceMap.getPsmServiceCost());
        }
        if (payerServiceMap.getPsmServiceGroup() != null) {
            payerServiceMapData.setPsmServiceGroup(payerServiceMap.getPsmServiceGroup());
        }
        if (payerServiceMap.getPsmServiceName() != null) {
            payerServiceMapData.setPsmServiceName(payerServiceMap.getPsmServiceName());
        }
        if (payerServiceMap.getPsmServiceRid() != null) {
            payerServiceMapData.setPsmServiceRid(payerServiceMap.getPsmServiceRid());
        }
        if (payerServiceMap.getPsmDiscountPrice() != null) {
            payerServiceMapData.setPsmDiscountPrice(payerServiceMap.getPsmDiscountPrice());
        }
        if (payerServiceMap.getPsmIsActive() != null) {
            payerServiceMapData.setPsmIsActive(payerServiceMap.getPsmIsActive());
        }
        return payerServiceMapData;
    }

    public PayerServiceMap convertPayerServiceMapDataToPayerServiceMap(PayerServiceMapData payerServiceMapData)
            throws DcometServiceException {
        PayerServiceMap payerServiceMap = new PayerServiceMap();
        if (payerServiceMapData.getCreatedUserRid() != null) {
            payerServiceMap.setCreatedUserRid(payerServiceMapData.getCreatedUserRid());
        }
        if (payerServiceMapData.getModifiedUserRid() != null) {
            payerServiceMap.setModifiedUserRid(payerServiceMapData.getModifiedUserRid());
        }
        if (payerServiceMapData.getPsmDiscountPercent() != null) {
            payerServiceMap.setPsmDiscountPercent(payerServiceMapData.getPsmDiscountPercent());
        }
        if (payerServiceMapData.getPsmDiscountVal() != null) {
            payerServiceMap.setPsmDiscountVal(payerServiceMapData.getPsmDiscountVal());
        }
        if (payerServiceMapData.getPsmId() != null) {
            payerServiceMap.setPsmId(payerServiceMapData.getPsmId());
        }
        if (payerServiceMapData.getPsmIsExcluded() != null) {
            payerServiceMap.setPsmIsExcluded(payerServiceMapData.getPsmIsExcluded());
        }
        if (payerServiceMapData.getPsmIsIncluded() != null) {
            payerServiceMap.setPsmIsIncluded(payerServiceMapData.getPsmIsIncluded());
        }
        if (payerServiceMapData.getPsmPdRid() != null) {
            payerServiceMap.setPsmPdRid(payerServiceMapData.getPsmPdRid());
        }
        if (payerServiceMapData.getPsmServiceCost() != null) {
            payerServiceMap.setPsmServiceCost(payerServiceMapData.getPsmServiceCost());
        }
        if (payerServiceMapData.getPsmServiceGroup() != null) {
            payerServiceMap.setPsmServiceGroup(payerServiceMapData.getPsmServiceGroup());
        }
        if (payerServiceMapData.getPsmServiceName() != null) {
            payerServiceMap.setPsmServiceName(payerServiceMapData.getPsmServiceName());
        }
        if (payerServiceMapData.getPsmServiceRid() != null) {
            payerServiceMap.setPsmServiceRid(payerServiceMapData.getPsmServiceRid());
        }
        if (payerServiceMapData.getCreatedDateTime() != null) {
            payerServiceMap.setCreatedDateTime(DateUtil.convertCalendarToString(payerServiceMapData.getCreatedDateTime()));
        }
        if (payerServiceMapData.getModifiedDateTime() != null) {
            payerServiceMap.setModifiedDateTime(DateUtil.convertCalendarToString(payerServiceMapData.getModifiedDateTime()));
        }
        if (payerServiceMapData.getPsmDiscountPrice() != null) {
            payerServiceMap.setPsmDiscountPrice(payerServiceMapData.getPsmDiscountPrice());
        }
        if (payerServiceMapData.getPsmIsActive() != null) {
            payerServiceMap.setPsmIsActive(payerServiceMapData.getPsmIsActive());
        }
        return payerServiceMap;
    }

    // ------------- Printable Info -----------------
    public List<PrintableInfo> convertPrintableInfoDataToPrintableInfo(List<PrintableInfoData> printableInfoDataList) throws DcometServiceException {
        List<PrintableInfo> printableInfoList = new ArrayList<>();
        for (PrintableInfoData printableInfoData : printableInfoDataList) {
            printableInfoList.add(convertPrintableInfoDataToPrintableInfo(printableInfoData));
        }
        return printableInfoList;
    }

    public List<PrintableInfoData> convertPrintableInfoToPrintableInfoData(List<PrintableInfo> printableInfoList) throws DcometServiceException {
        List<PrintableInfoData> printableInfoDataList = new ArrayList<>();
        for (PrintableInfo printableInfo : printableInfoList) {
            printableInfoDataList.add(convertPrintableInfoToPrintableInfoData(printableInfo));
        }
        return printableInfoDataList;
    }

    public PrintableInfo convertPrintableInfoDataToPrintableInfo(PrintableInfoData printableInfoData) throws DcometServiceException {
        PrintableInfo printableInfo = new PrintableInfo();

        if (printableInfoData.getPeRid() != null) {
            printableInfo.setPeRid(printableInfoData.getPeRid());
        }
        if (printableInfoData.getPeEntityRid() != null) {
            printableInfo.setPeEntityRid(printableInfoData.getPeEntityRid());
        }
        if (printableInfoData.getPeTinNo() != null) {
            printableInfo.setPeTinNo(printableInfoData.getPeTinNo());
        }
        if (printableInfoData.getPeCstNo() != null) {
            printableInfo.setPeCstNo(printableInfoData.getPeCstNo());
        }
        if (printableInfoData.getPeCinNo() != null) {
            printableInfo.setPeCinNo(printableInfoData.getPeCinNo());
        }
        if (printableInfoData.getPeUniqueIdNo() != null) {
            printableInfo.setPeUniqueIdNo(printableInfoData.getPeUniqueIdNo());
        }
        if (printableInfoData.getPeTaxNo() != null) {
            printableInfo.setPeTaxNo(printableInfoData.getPeTaxNo());
        }
        if (printableInfoData.getPeName() != null) {
            printableInfo.setPeName(printableInfoData.getPeName());
        }
        if (printableInfoData.getPePhoneNo() != null) {
            printableInfo.setPePhoneNo(printableInfoData.getPePhoneNo());
        }
        if (printableInfoData.getPeEmail() != null) {
            printableInfo.setPeEmail(printableInfoData.getPeEmail());
        }
        if (printableInfoData.getPeWebsite() != null) {
            printableInfo.setPeWebsite(printableInfoData.getPeWebsite());
        }
        if (printableInfoData.getPeAddress() != null) {
            printableInfo.setPeAddress(printableInfoData.getPeAddress());
        }
        if (printableInfoData.getPeCity() != null) {
            printableInfo.setPeCity(printableInfoData.getPeCity());
        }
        if (printableInfoData.getPeCountry() != null) {
            printableInfo.setPeCountry(printableInfoData.getPeCountry());
        }
        if (printableInfoData.getPeIsExcisable() != null) {
            printableInfo.setPeIsExcisable(printableInfoData.getPeIsExcisable());
        }
        if (printableInfoData.getPeRegion() != null) {
            printableInfo.setPeRegion(printableInfoData.getPeRegion());
        }
        if (printableInfoData.getPeDivision() != null) {
            printableInfo.setPeDivision(printableInfoData.getPeDivision());
        }
        if (printableInfoData.getPeCommissionerate() != null) {
            printableInfo.setPeCommissionerate(printableInfoData.getPeCommissionerate());
        }
        if (printableInfoData.getPeOthers() != null) {
            printableInfo.setPeOthers(printableInfoData.getPeOthers());
        }
        if (printableInfoData.getPeLogo() != null) {
            printableInfo.setPeLogo(printableInfoData.getPeLogo());
        }
        return printableInfo;
    }

    public PrintableInfoData convertPrintableInfoToPrintableInfoData(PrintableInfo printableInfo) throws DcometServiceException {
        PrintableInfoData printableInfoData = new PrintableInfoData();

        if (printableInfo.getPeRid() != null) {
            printableInfoData.setPeRid(printableInfo.getPeRid());
        }
        if (printableInfo.getPeEntityRid() != null) {
            printableInfoData.setPeEntityRid(printableInfo.getPeEntityRid());
        }
        if (printableInfo.getPeTinNo() != null) {
            printableInfoData.setPeTinNo(printableInfo.getPeTinNo());
        }
        if (printableInfo.getPeCstNo() != null) {
            printableInfoData.setPeCstNo(printableInfo.getPeCstNo());
        }
        if (printableInfo.getPeCinNo() != null) {
            printableInfoData.setPeCinNo(printableInfo.getPeCinNo());
        }
        if (printableInfo.getPeUniqueIdNo() != null) {
            printableInfoData.setPeUniqueIdNo(printableInfo.getPeUniqueIdNo());
        }
        if (printableInfo.getPeTaxNo() != null) {
            printableInfoData.setPeTaxNo(printableInfo.getPeTaxNo());
        }
        if (printableInfo.getPeName() != null) {
            printableInfoData.setPeName(printableInfo.getPeName());
        }
        if (printableInfo.getPePhoneNo() != null) {
            printableInfoData.setPePhoneNo(printableInfo.getPePhoneNo());
        }
        if (printableInfo.getPeEmail() != null) {
            printableInfoData.setPeEmail(printableInfo.getPeEmail());
        }
        if (printableInfo.getPeWebsite() != null) {
            printableInfoData.setPeWebsite(printableInfo.getPeWebsite());
        }
        if (printableInfo.getPeAddress() != null) {
            printableInfoData.setPeAddress(printableInfo.getPeAddress());
        }
        if (printableInfo.getPeCity() != null) {
            printableInfoData.setPeCity(printableInfo.getPeCity());
        }
        if (printableInfo.getPeCountry() != null) {
            printableInfoData.setPeCountry(printableInfo.getPeCountry());
        }
        if (printableInfo.getPeIsExcisable() != null) {
            printableInfoData.setPeIsExcisable(printableInfo.getPeIsExcisable());
        }
        if (printableInfo.getPeRegion() != null) {
            printableInfoData.setPeRegion(printableInfo.getPeRegion());
        }
        if (printableInfo.getPeDivision() != null) {
            printableInfoData.setPeDivision(printableInfo.getPeDivision());
        }
        if (printableInfo.getPeCommissionerate() != null) {
            printableInfoData.setPeCommissionerate(printableInfo.getPeCommissionerate());
        }
        if (printableInfo.getPeOthers() != null) {
            printableInfoData.setPeOthers(printableInfo.getPeOthers());
        }
        if (printableInfo.getPeLogo() != null) {
            printableInfoData.setPeLogo(printableInfo.getPeLogo());
        }
        return printableInfoData;
    }

    public List<FileAttachment> convertFileUploadDataToFileUpload(List<FileAttachmentData> resultData) throws DcometServiceException {
        List<FileAttachment> fileUploadsList = new ArrayList<>();
        for (FileAttachmentData fileUploadData : resultData) {
            fileUploadsList.add(convertFileUploadDataToFileUpload(fileUploadData));
        }
        return fileUploadsList;
    }

    public List<FileAttachmentData> convertFileUploadToFileUploadData(List<FileAttachment> resultDataList) throws DcometServiceException {
        List<FileAttachmentData> fileUploadsDataList = new ArrayList<>();
        for (FileAttachment fileUpload : resultDataList) {
            fileUploadsDataList.add(convertFileUploadToFileUploadData(fileUpload));
        }
        return fileUploadsDataList;
    }

    public FileAttachment convertFileUploadDataToFileUpload(FileAttachmentData fileUploadData) throws DcometServiceException {
        FileAttachment fileUpload = new FileAttachment();

        if (fileUploadData.getId() != null) {
            fileUpload.setId(fileUploadData.getId());
        }
        if (fileUploadData.getTaType() != null) {
            fileUpload.setTaType(fileUploadData.getTaType());
        }
        if (fileUploadData.getTaRefRid() != null) {
            fileUpload.setTaRefRid(fileUploadData.getTaRefRid());
        }
        if (fileUploadData.getTaFileName() != null) {
            fileUpload.setTaFileName(fileUploadData.getTaFileName());
        }
        if (fileUploadData.getTaFileDesc() != null) {
            fileUpload.setTaFileDesc(fileUploadData.getTaFileDesc());
        }
        if (fileUploadData.getTaActive() != null) {
            fileUpload.setTaActive(fileUploadData.getTaActive());
        }
        if (fileUploadData.getTaFile() != null) {
            fileUpload.setTaFile(fileUploadData.getTaFile());
        }
        if (fileUploadData.getTaEntityRid() != null) {
            fileUpload.setTaEntityRid(fileUploadData.getTaEntityRid());
        }
        return fileUpload;
    }

    public FileAttachmentData convertFileUploadToFileUploadData(FileAttachment fileUpload) throws DcometServiceException {
        FileAttachmentData fileUploadData = new FileAttachmentData();

        if (fileUpload.getId() != null) {
            fileUploadData.setId(fileUpload.getId());
        }
        if (fileUpload.getTaType() != null) {
            fileUploadData.setTaType(fileUpload.getTaType());
        }
        if (fileUpload.getTaRefRid() != null) {
            fileUploadData.setTaRefRid(fileUpload.getTaRefRid());
        }
        if (fileUpload.getTaFileName() != null) {
            fileUploadData.setTaFileName(fileUpload.getTaFileName());
        }
        if (fileUpload.getTaFileDesc() != null) {
            fileUploadData.setTaFileDesc(fileUpload.getTaFileDesc());
        }
        if (fileUpload.getTaActive() != null) {
            fileUploadData.setTaActive(fileUpload.getTaActive());
        }
        if (fileUpload.getTaFile() != null) {
            fileUploadData.setTaFile(fileUpload.getTaFile());
        }
        if (fileUpload.getTaEntityRid() != null) {
            fileUploadData.setTaEntityRid(fileUpload.getTaEntityRid());
        }
        return fileUploadData;
    }

    //--------------------MailQueueData--------------------
    public List<MailQueue> convertMailQueueDataToMailQueue(
            List<MailQueueData> mailQueueDataList) throws DcometServiceException {
        List<MailQueue> mailQueueList = new ArrayList<>();
        for (MailQueueData mailQueueData : mailQueueDataList) {
            mailQueueList.add(convertMailQueueDataToMailQueue(mailQueueData));
        }
        return mailQueueList;
    }

    public List<MailQueueData> convertMailQueueToMailQueueData(
            List<MailQueue> mailQueueList) throws DcometServiceException {
        List<MailQueueData> mailQueueDataList = new ArrayList<MailQueueData>();
        for (MailQueue mailQueue : mailQueueList) {
            mailQueueDataList.add(convertMailQueueToMailQueueData(mailQueue));
        }
        return mailQueueDataList;
    }

    public MailQueue convertMailQueueDataToMailQueue(MailQueueData mailQueueData)
            throws DcometServiceException {
        MailQueue mailQueue = new MailQueue();

        if (mailQueueData.getId() != null) {
            mailQueue.setId(mailQueueData.getId());
        }
        if (mailQueueData.getMqSubject() != null) {
            mailQueue.setMqSubject(mailQueueData.getMqSubject());
        }
        if (mailQueueData.getMqFrom() != null) {
            mailQueue.setMqFrom(mailQueueData.getMqFrom());
        }
        if (mailQueueData.getMqTo() != null) {
            mailQueue.setMqTo(mailQueueData.getMqTo());
        }
        if (mailQueueData.getMqBody() != null) {
            mailQueue.setMqBody(mailQueueData.getMqBody());
        }
        if (mailQueueData.getMqSent() != null) {
            mailQueue.setMqSent(mailQueueData.getMqSent());
        }
        if (mailQueueData.getMqTxErrors() != null) {
            mailQueue.setMqTxErrors(mailQueueData.getMqTxErrors());
        }
        if (mailQueueData.getMqPostDate() != null) {
            mailQueue.setMqPostDate(DateUtil.convertDateToString(mailQueueData.getMqPostDate()));
        }
        if (mailQueueData.getMqPostTime() != null) {
            mailQueue.setMqPostTime(DateUtil.convertCalendarToString(mailQueueData.getMqPostTime()));
        }
        if (mailQueueData.getMqSentDate() != null) {
            mailQueue.setMqSentDate(DateUtil.convertDateToString(mailQueueData.getMqSentDate()));
        }
        if (mailQueueData.getMqSentTime() != null) {
            mailQueue.setMqSentTime(DateUtil.convertCalendarToString(mailQueueData.getMqSentTime()));
        }
        if (mailQueueData.getMqFileName() != null) {
            mailQueue.setMqFileName(mailQueueData.getMqFileName());
        }
        if (mailQueueData.getMqFileNameWithPath() != null) {
            mailQueue.setMqFileNameWithPath(mailQueueData.getMqFileNameWithPath());
        }
        if (mailQueueData.getMqIsAttachmentExist() != null) {
            mailQueue.setMqIsAttachmentExist(mailQueueData.getMqIsAttachmentExist());
        }
        if (mailQueueData.getMqNoOfIteration() != null) {
            mailQueue.setMqNoOfIteration(mailQueueData.getMqNoOfIteration());
        }
        if (mailQueueData.getMqIsHtmlContent() != null) {
            mailQueue.setMqIsHtmlContent(mailQueueData.getMqIsHtmlContent());
        }
        if (mailQueueData.getMqReasonForFailure() != null) {
            mailQueue.setMqReasonForFailure(mailQueueData.getMqReasonForFailure());
        }
        return mailQueue;
    }

    public MailQueueData convertMailQueueToMailQueueData(MailQueue mailQueue) throws DcometServiceException {
        MailQueueData mailQueueData = new MailQueueData();

        if (mailQueue.getId() != null) {
            mailQueueData.setId(mailQueue.getId());
        }
        if (mailQueue.getMqSubject() != null) {
            mailQueueData.setMqSubject(mailQueue.getMqSubject());
        }
        if (mailQueue.getMqFrom() != null) {
            mailQueueData.setMqFrom(mailQueue.getMqFrom());
        }
        if (mailQueue.getMqTo() != null) {
            mailQueueData.setMqTo(mailQueue.getMqTo());
        }
        if (mailQueue.getMqBody() != null) {
            mailQueueData.setMqBody(mailQueue.getMqBody());
        }
        if (mailQueue.getMqSent() != null) {
            mailQueueData.setMqSent(mailQueue.getMqSent());
        }
        if (mailQueue.getMqTxErrors() != null) {
            mailQueueData.setMqTxErrors(mailQueue.getMqTxErrors());
        }
        if (mailQueue.getMqPostDate() != null) {
            mailQueueData.setMqPostDate(DateUtil.convertStringToDate(mailQueue.getMqPostDate()));
        }
        if (mailQueue.getMqPostTime() != null) {
            mailQueueData.setMqPostTime(DateUtil.convertStringToCalendar(mailQueue.getMqPostTime()));
        }
        if (mailQueue.getMqSentDate() != null) {
            mailQueueData.setMqSentDate(DateUtil.convertStringToDate(mailQueue.getMqSentDate()));
        }
        if (mailQueue.getMqSentTime() != null) {
            mailQueueData.setMqSentTime(DateUtil.convertStringToCalendar(mailQueue.getMqSentTime()));
        }
        if (mailQueue.getMqFileName() != null) {
            mailQueueData.setMqFileName(mailQueue.getMqFileName());
        }
        if (mailQueue.getMqFileNameWithPath() != null) {
            mailQueueData.setMqFileNameWithPath(mailQueue.getMqFileNameWithPath());
        }
        if (mailQueue.getMqIsAttachmentExist() != null) {
            mailQueueData.setMqIsAttachmentExist(mailQueue.getMqIsAttachmentExist());
        }
        if (mailQueue.getMqNoOfIteration() != null) {
            mailQueueData.setMqNoOfIteration(mailQueue.getMqNoOfIteration());
        }
        if (mailQueue.getMqIsHtmlContent() != null) {
            mailQueueData.setMqIsHtmlContent(mailQueue.getMqIsHtmlContent());
        }
        if (mailQueue.getMqReasonForFailure() != null) {
            mailQueueData.setMqReasonForFailure(mailQueue.getMqReasonForFailure());
        }
        return mailQueueData;
    }
}
