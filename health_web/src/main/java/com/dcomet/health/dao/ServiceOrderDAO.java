package com.dcomet.health.dao;

import com.dcomet.health.dao.data.ServiceOrderData;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.PatientData;
import com.dcomet.health.dao.data.ServiceOrderDData;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author KS
 */
public interface ServiceOrderDAO {

    public Long getServiceOrderTotalCount(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException;

    public List<ServiceOrderData> getServiceOrders(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException;

    public List<ServiceOrderData> getServiceOrder(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException;

    public List<ServiceOrderData> getServiceOrder(List<Criterion> criterionList) throws DcometDAOException;

    public void saveServiceOrder(ServiceOrderData serviceOrderData) throws DcometDAOException;

    public List<PatientData> getPendingServicePatients(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException;

    public List<ServiceOrderDData> getServiceOrderD(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometDAOException;

    public void saveServiceOrderD(List<ServiceOrderDData> serviceOrderDDataList) throws DcometDAOException;

}
