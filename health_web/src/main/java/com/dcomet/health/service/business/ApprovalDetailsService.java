package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.ApprovalDetails;
import com.dcomet.health.domain.ApprovalDetailsSearchRequest;
import java.util.List;

/**
 *
 * @author Dev1
 */
public interface ApprovalDetailsService extends WorkFlowService {

    public List<ApprovalDetails> getApprovalDetails(ApprovalDetailsSearchRequest approvalDetailsSearchRequest) throws DcometServiceException;

    public void saveApprovalDetails(ApprovalDetails approvalDetails) throws DcometServiceException;
}
