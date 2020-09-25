package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.adapter.ApprovalDetailsAdapter;
import com.dcomet.health.dao.ApprovalDetailsDAO;
import com.dcomet.health.dao.data.ApprovalDetailsData;
import com.dcomet.health.domain.ApprovalDetails;
import com.dcomet.health.domain.ApprovalDetailsSearchRequest;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.health.service.business.ApprovalDetailsService;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.MasterService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev1
 */
@Service("approvalDetailsService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ApprovalDetailsServiceImpl extends BaseWorkFlowServiceImpl implements ApprovalDetailsService {

    @Autowired
    @Qualifier("approvalDetailsDAO")
    ApprovalDetailsDAO approvalDetailsDAO;

    @Autowired
    @Qualifier("approvalDetailsAdapter")
    ApprovalDetailsAdapter approvalDetailsAdapter;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    @Autowired
    @Qualifier("billingService")
    public BillingService billingService;

    @Override
    public List<ApprovalDetails> getApprovalDetails(ApprovalDetailsSearchRequest approvalDetailsSearchRequest) throws DcometServiceException {
        List<ApprovalDetails> approvalDetails = null;
        try {
            List<ApprovalDetailsData> approvalDetailsDatas = approvalDetailsDAO.getApprovalDetails(approvalDetailsSearchRequest);
            if (CollectionUtils.isNotEmpty(approvalDetailsDatas)) {
                approvalDetails = approvalDetailsAdapter.convertApprovalDetailsDataToApprovalDetails(approvalDetailsDatas);
                for (ApprovalDetails approvalDetails1 : approvalDetails) {
                    DiscountMaster discountMaster = getDiscountMaster(approvalDetails1.getAppDisRid());
                    approvalDetails1.setDiscName(discountMaster.getDisName());
                    approvalDetails1.setDiscPercentage(discountMaster.getDisPercentage());
                    approvalDetails1.setBillNetAmount(billingService.getBillH(approvalDetails1.getAppBillRid()).getBhNetAmount());
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return approvalDetails;
    }

    private DiscountMaster getDiscountMaster(Integer discountRid) throws DcometServiceException {
        List<Criterion> searchCriterionList = new ArrayList<>();
        DiscountMaster discountMaster = new DiscountMaster();
        DiscountMasterSearchRequest discountMasterSearchRequest = new DiscountMasterSearchRequest();
        searchCriterionList.add(Restrictions.eq("id", discountRid));
        discountMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        discountMaster = masterService.getDiscountMaster(discountMasterSearchRequest).get(0);
        return discountMaster;
    }

    @Override
    public void saveApprovalDetails(ApprovalDetails approvalDetails) throws DcometServiceException {
        try {
            ApprovalDetailsData approvalDetailsData = approvalDetailsAdapter.convertApprovalDetailsToApprovalDetailsData(approvalDetails);
            approvalDetailsDAO.saveApprovalDetails(approvalDetailsData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer save(Base base, Integer nextState, String string) throws DcometServiceException {
        ApprovalDetails approvalDetails = (ApprovalDetails) base;
        ApprovalDetailsData approvalDetailsData = new ApprovalDetailsData();
        try {
            approvalDetails.setAppState(nextState);
            approvalDetails.setAppStatus(nextState);
            approvalDetailsData = approvalDetailsAdapter.convertApprovalDetailsToApprovalDetailsData(approvalDetails);
            approvalDetailsDAO.saveApprovalDetails(approvalDetailsData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return approvalDetailsData.getAppId();
    }

    @Override
    public Integer getCurrentState(Integer intgr) throws DcometServiceException {
        List<ApprovalDetails> approvalDetails = getApprovalDetails(intgr);
        return approvalDetails != null ? approvalDetails.get(0).getAppState() : 0;
    }

    private List<ApprovalDetails> getApprovalDetails(Integer id) {
        List<Criterion> searchCriterionList = new ArrayList<>();
        ApprovalDetailsSearchRequest approvalDetailsSearchRequest = new ApprovalDetailsSearchRequest();
        searchCriterionList.add(Restrictions.eq("appId", id));
        approvalDetailsSearchRequest.setSearchCriterionList(searchCriterionList);
        List<ApprovalDetails> approvalDetails = getApprovalDetails(approvalDetailsSearchRequest);
        return approvalDetails;
    }

    @Override
    public String buildBODescriptor(String[] strings, Integer intgr) throws DcometServiceException {
        List<ApprovalDetails> approvalDetails = getApprovalDetails(intgr);
        return approvalDetails != null ? new StringBuilder(approvalDetails.get(0).getAppBillCode()).append("&").append(billingService.getBillH(approvalDetails.get(0).getAppBillRid()).getBhPatientName()).append("&").append(getDiscountMaster(approvalDetails.get(0).getAppDisRid()).getDisPercentage()).toString() : null;
    }

}
