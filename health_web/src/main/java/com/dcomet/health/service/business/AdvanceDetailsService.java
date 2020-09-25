package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchRequest;
import com.dcomet.module.billing.domain.RefundH;

/**
 *
 * @author KS
 */
public interface AdvanceDetailsService extends BillingService {

    public String getTransactionAdvanceReport(AdvanceDetailsSearchRequest advanceDetailsSearchRequest) throws DcometServiceException;

    public void saveRefundWithAdvance(RefundH refundH) throws DcometServiceException;
}
