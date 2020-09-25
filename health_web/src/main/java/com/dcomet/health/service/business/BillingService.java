package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.module.billing.service.DCometBillingService;
import java.util.List;

/**
 *
 * @author KS
 */
public interface BillingService extends DCometBillingService {

    public String getTransactionReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException;

    public List<ServiceRequestH> getServiceRequestByBill(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometServiceException;

    public List<DrugRequestH> getmaterialRequestByBill(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException;

    public String getPayerReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException;  
}
