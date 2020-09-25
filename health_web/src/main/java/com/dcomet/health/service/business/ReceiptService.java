package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;

/**
 *
 * @author KS
 */
public interface ReceiptService extends BillingService {

    public String getTransactionReceiptReport(ReceiptHSearchRequest receiptHSearchRequest) throws DcometServiceException;

}
