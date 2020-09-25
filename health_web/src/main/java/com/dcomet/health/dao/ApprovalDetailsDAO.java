package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.ApprovalDetailsData;
import com.dcomet.health.domain.ApprovalDetailsSearchRequest;
import java.util.List;

/**
 *
 * @author Dev1
 */
public interface ApprovalDetailsDAO {

    public void saveApprovalDetails(ApprovalDetailsData approvalDetailsData) throws DcometDAOException;

    public List<ApprovalDetailsData> getApprovalDetails(ApprovalDetailsSearchRequest approvalDetailsSearchRequest) throws DcometDAOException;
}
