/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.adapter;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.ApprovalDetailsData;
import com.dcomet.health.domain.ApprovalDetails;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev1
 */
@Component("approvalDetailsAdapter")
public class ApprovalDetailsAdapter {
    

    public List<ApprovalDetailsData> convertApprovalDetailsToApprovalDetailsData(List<ApprovalDetails> approvalDetailsList)
            throws DcometServiceException {
        List<ApprovalDetailsData> approvalDetailsDatas = new ArrayList<>();
        for (ApprovalDetails approvalDetails : approvalDetailsList) {
            approvalDetailsDatas.add(convertApprovalDetailsToApprovalDetailsData(approvalDetails));
        }
        return approvalDetailsDatas;
    }
//

    public List<ApprovalDetails> convertApprovalDetailsDataToApprovalDetails(List<ApprovalDetailsData> approvalDetailsDatas)
            throws DcometServiceException {
        List<ApprovalDetails> approvalDetails = new ArrayList<>();
        for (ApprovalDetailsData approvalDetailsData : approvalDetailsDatas) {
            approvalDetails.add(convertApprovalDetailsDataToApprovalDetails(approvalDetailsData));
        }
        return approvalDetails;
    }

    public ApprovalDetailsData convertApprovalDetailsToApprovalDetailsData(ApprovalDetails approvalDetails)
            throws DcometServiceException {
        ApprovalDetailsData approvalDetailsData = new ApprovalDetailsData();
        if (approvalDetails.getAppId() != null) {
            approvalDetailsData.setAppId(approvalDetails.getAppId());
        }
        if (approvalDetails.getAppBillCode() != null) {
            approvalDetailsData.setAppBillCode(approvalDetails.getAppBillCode());
        }
        if (approvalDetails.getAppBillRid() != null) {
            approvalDetailsData.setAppBillRid(approvalDetails.getAppBillRid());
        }
        if (approvalDetails.getAppCreatedDateTime() != null) {
            approvalDetailsData.setAppCreatedDateTime(approvalDetails.getAppCreatedDateTime());
        }
        if (approvalDetails.getAppCreatedUserRid() != null) {
            approvalDetailsData.setAppCreatedUserRid(approvalDetails.getAppCreatedUserRid());
        }
        if (approvalDetails.getAppDisRid() != null) {
            approvalDetailsData.setAppDisRid(approvalDetails.getAppDisRid());
        }
        if (approvalDetails.getAppEntityRid() != null) {
            approvalDetailsData.setAppEntityRid(approvalDetails.getAppEntityRid());
        }
        if (approvalDetails.getAppModifiedDateTime() != null) {
            approvalDetailsData.setAppModifiedDateTime(approvalDetails.getAppModifiedDateTime());
        }
        if (approvalDetails.getAppModifiedUserRid() != null) {
            approvalDetailsData.setAppModifiedUserRid(approvalDetails.getAppModifiedUserRid());
        }
        if (approvalDetails.getAppState() != null) {
            approvalDetailsData.setAppState(approvalDetails.getAppState());
        }
        if (approvalDetails.getAppStatus() != null) {
            approvalDetailsData.setAppStatus(approvalDetails.getAppStatus());
        }
        if (approvalDetails.getAppType() != null) {
            approvalDetailsData.setAppType(approvalDetails.getAppType());
        }
        if (approvalDetails.getAppUnitRid() != null) {
            approvalDetailsData.setAppUnitRid(approvalDetails.getAppUnitRid());
        }
        return approvalDetailsData;
    }

    public ApprovalDetails convertApprovalDetailsDataToApprovalDetails(ApprovalDetailsData approvalDetailsData)
            throws DcometServiceException {
        ApprovalDetails approvalDetails = new ApprovalDetails();

        if (approvalDetailsData.getAppId() != null) {
            approvalDetails.setAppId(approvalDetailsData.getAppId());
        }
        if (approvalDetailsData.getAppBillCode() != null) {
            approvalDetails.setAppBillCode(approvalDetailsData.getAppBillCode());
        }
        if (approvalDetailsData.getAppBillRid() != null) {
            approvalDetails.setAppBillRid(approvalDetailsData.getAppBillRid());
        }
        if (approvalDetailsData.getAppCreatedDateTime() != null) {
            approvalDetails.setAppCreatedDateTime(approvalDetailsData.getAppCreatedDateTime());
        }
        if (approvalDetailsData.getAppCreatedUserRid() != null) {
            approvalDetails.setAppCreatedUserRid(approvalDetailsData.getAppCreatedUserRid());
        }
        if (approvalDetailsData.getAppDisRid() != null) {
            approvalDetails.setAppDisRid(approvalDetailsData.getAppDisRid());
        }
        if (approvalDetailsData.getAppEntityRid() != null) {
            approvalDetails.setAppEntityRid(approvalDetailsData.getAppEntityRid());
        }
        if (approvalDetailsData.getAppModifiedDateTime() != null) {
            approvalDetails.setAppModifiedDateTime(approvalDetailsData.getAppModifiedDateTime());
        }
        if (approvalDetailsData.getAppModifiedUserRid() != null) {
            approvalDetails.setAppModifiedUserRid(approvalDetailsData.getAppModifiedUserRid());
        }
        if (approvalDetailsData.getAppState() != null) {
            approvalDetails.setAppState(approvalDetailsData.getAppState());
        }
        if (approvalDetailsData.getAppStatus() != null) {
            approvalDetails.setAppStatus(approvalDetailsData.getAppStatus());
        }
        if (approvalDetailsData.getAppType() != null) {
            approvalDetails.setAppType(approvalDetailsData.getAppType());
        }
        if (approvalDetailsData.getAppUnitRid() != null) {
            approvalDetails.setAppUnitRid(approvalDetailsData.getAppUnitRid());
        }
        return approvalDetails;
    }

}
