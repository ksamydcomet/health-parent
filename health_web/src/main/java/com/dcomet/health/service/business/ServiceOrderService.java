package com.dcomet.health.service.business;

import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.ServiceOrderD;
import java.util.List;

/**
 *
 * @author Adhithya
 */
public interface ServiceOrderService extends WorkFlowService {

    public List<ServiceOrder> getServiceOrder(ServiceOrderSearchRequest serviceOrderSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<ServiceOrderD> getServiceOrderD(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException;
    
    public List<ServiceOrder> getServiceOrderWithD(ServiceOrderSearchRequest serviceOrderSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<Patient> getPendingServiceOrders(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException;
    
    public List<Patient> getPendingServiceOrders1(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException;

    public void saveServiceOrderResult(List<ServiceOrder> serviceOrderList) throws DcometServiceException;

    public void saveServiceOrderWithD(List<ServiceOrder> serviceOrderList, boolean includeChild) throws DcometServiceException;
    
    public String getRadioServicePrint(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException;

}
