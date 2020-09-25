package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.module.billing.domain.RefundHSearchRequest;

/**
 *
 * @author KS
 */
public interface RefundService extends BillingService {
    
     public String getTransactionRefundReport(RefundHSearchRequest refundHSearchRequest) throws DcometServiceException;
}
