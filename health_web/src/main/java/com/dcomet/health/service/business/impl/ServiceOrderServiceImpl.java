package com.dcomet.health.service.business.impl;

import com.dcomet.health.adapter.ServiceOrderAdapter;
import com.dcomet.health.dao.ServiceOrderDAO;
import com.dcomet.health.dao.data.ServiceOrderData;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.fw.domain.Base;
import com.dcomet.module.master.domain.ServiceMaster;
import com.dcomet.module.master.domain.ServiceMasterSearchRequest;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.adapter.ClinicalAdapter;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.dao.data.ServiceOrderDData;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.ServiceOrderD;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.LaboratoryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.ServiceOrderService;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchRequest;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.laboratory.domain.LabResultD;
import com.dcomet.module.laboratory.domain.LabResultH;
import com.dcomet.module.master.service.DCometMasterService;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * @author Adhithya
 */
@Service("serviceOrderService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ServiceOrderServiceImpl extends BaseWorkFlowServiceImpl implements ServiceOrderService {

    @Autowired
    @Qualifier("serviceOrderDAO")
    ServiceOrderDAO serviceOrderDAO;

    @Autowired
    @Qualifier("serviceOrderAdapter")
    private ServiceOrderAdapter serviceOrderAdapter;

    @Autowired
    @Qualifier("dataDictionaryService")
    private DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    @Autowired
    @Qualifier("laboratoryService")
    LaboratoryService laboratoryService;

    @Autowired
    @Qualifier("clinicalAdapter")
    ClinicalAdapter clinicalAdapter;

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService1;

    @Override
    public List<ServiceOrder> getServiceOrder(ServiceOrderSearchRequest serviceOrderSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        try {
            serviceOrders = serviceOrderAdapter.convertServiceOrderDataToServiceOrder(serviceOrderDAO.getServiceOrder(serviceOrderSearchRequest));
            if (CollectionUtils.isNotEmpty(serviceOrders)) {
                if (includeChilds) {
                    for (ServiceOrder serviceOrder : serviceOrders) {
                        serviceOrder.setServiceMaster(masterService.getServiceMasterByID(serviceOrder.getSoItemID()));
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceOrders;
    }

    @Override
    public List<ServiceOrder> getServiceOrderWithD(ServiceOrderSearchRequest serviceOrderSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        try {
            serviceOrders = serviceOrderAdapter.convertServiceOrderDataToServiceOrder(serviceOrderDAO.getServiceOrder(serviceOrderSearchRequest));
            if (CollectionUtils.isNotEmpty(serviceOrders)) {
                if (includeChilds) {
                    for (ServiceOrder serviceOrder : serviceOrders) {
                        serviceOrder.setServiceMaster(masterService.getServiceMasterByID(serviceOrder.getSoItemID()));
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        List<ServiceOrderD> serviceOrderDs = new ArrayList<>();
                        ServiceOrderSearchRequest childSearchRequest = new ServiceOrderSearchRequest();
                        searchCriterionList.add(Restrictions.eq("sodSoRid", serviceOrder.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        serviceOrderDs = getServiceOrderD(childSearchRequest);
                        serviceOrder.setServiceOrderDList(serviceOrderDs);
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceOrders;
    }

    @Override
    public List<ServiceOrderD> getServiceOrderD(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException {
        List<ServiceOrderD> serviceOrderDs = new ArrayList<>();
        try {
            List<ServiceOrderDData> serviceOrderDDataList = serviceOrderDAO.getServiceOrderD(serviceOrderSearchRequest);
            serviceOrderDs = serviceOrderAdapter.convertServiceOrderDDataToServiceOrderD(serviceOrderDDataList);

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceOrderDs;
    }

//    @Override
//    public List<ServiceRequest> getServiceRequest(ServiceRequestSearchRequest serviceRequestSearchRequest) throws DcometServiceException {
//        List<ServiceRequest> result = null;
//        try {
//            List<ServiceRequestData> resultData = serviceOrderDAO.getServiceRequest(serviceRequestSearchRequest);
//            if (CollectionUtils.isNotEmpty(resultData)) {
//                result = serviceOrderAdapter.convertServiceRequestDataToServiceRequest(resultData);
//            }
//        } catch (DcometDAOException e) {
//            throw new DcometServiceException(e);
//        } catch (DcometServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DcometServiceException(e);
//        }
//        return result;
//    }
    private List<ServiceOrderData> getServiceOrderData(Integer id) throws DcometServiceException {
        ServiceOrderSearchRequest serviceOrderSearchRequest = new ServiceOrderSearchRequest();
        serviceOrderSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        return serviceOrderDAO.getServiceOrder(serviceOrderSearchRequest);
    }

    private AutoNumber generateAutoNumber(ServiceOrder serviceOrder) {
        AutoNumber autoNumber = null;
        if (serviceOrder.getId() == null) {
            autoNumber = dataDictionaryService.getAutoNumberByCategory("SO", serviceOrder.getEntityRid());
            dataDictionaryService.saveAutoNumberIncrement("SO", serviceOrder.getEntityRid());
        }
        return autoNumber;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) {
        ServiceOrder serviceOrder = (ServiceOrder) object;
        Integer nxtConState = null, isSampleRequired = null;
        ServiceOrderData serviceOrderData = null;
        try {
            if (null != actionCode) {
                switch (actionCode) {
                    case "BUILTIN_ACTION":
                        serviceOrderData = serviceOrderAdapter.convertServiceOrderToServiceOrderData(serviceOrder);
                        List<ServiceMaster> serviceMasterList = getServiceMasterList(serviceOrderData.getSoItemID());
                        serviceOrderData.setSoAgainstUnitRID(serviceMasterList.get(0).getBsUnit());
                        serviceOrderData.setSoSerMode(Integer.parseInt(serviceMasterList.get(0).getBsServiceType()));
                        if (serviceOrder.getId() != null) {
                            Integer currentState = getCurrentState(serviceOrderData.getId());
                            if (currentState == 2) {
                                isSampleRequired = serviceMasterList.get(0).getbSIsSampleRequired();
                                if (isSampleRequired == 1) {
                                    nxtConState = getBOStateTransitionConfig("SO_SampleCollected", currentState).getBostcBostToStateIndex();
                                } else {
                                    serviceOrderData.setSoProcessingUnitRID(serviceOrder.getUnitRid());
                                    serviceOrderData.setSoProcessedDate(Calendar.getInstance());
                                    serviceOrderData.setSoProcessedBY(serviceOrder.getUserId());
                                    serviceOrderData.setSoSignedUserRID(serviceOrder.getUserRid());
                                    serviceOrderData.setSoSignedDateTime(Calendar.getInstance());
                                    nxtConState = getBOStateTransitionConfig("SO_ConfirmToProcessed", currentState).getBostcBostToStateIndex();
                                }
                            } else {
                                nxtConState = getBOStateTransitionConfig("SO_Confirmed", currentState).getBostcBostToStateIndex();
                            }
                            serviceOrderData.setSoState(nxtConState);
                            serviceOrderData.setSoStatus(nxtConState);
                        } else {
                            AutoNumber autoNumber = generateAutoNumber(serviceOrder);
                            if (autoNumber != null) {
                                serviceOrderData.setSoOrderNo(autoNumber.getAutoNumber());
                                serviceOrderData.setSoState(nextState);
                                serviceOrderData.setSoStatus(nextState);
                            }
                        }
                        serviceOrderData.setSoEntityRID(serviceOrder.getEntityRid());
                        serviceOrderData.setSoAdviceUserRID(serviceOrder.getUserRid());
                        serviceOrderData.setSoStartDate(DateUtil.convertStringToCalendar(object.getCurrentDateTimeByUTZ()));
                        serviceOrderDAO.saveServiceOrder(serviceOrderData);
                        break;
                    case "CANCEL_SERVICE_ORDER":
                        serviceOrderData = getServiceOrderData(serviceOrder.getId()).get(0);
                        serviceOrderData.setSoState(nextState);
                        serviceOrderData.setSoStatus(nextState);
                        serviceOrderData.setSoEntityRID(serviceOrder.getEntityRid());
                        serviceOrderData.setSoAdviceUserRID(serviceOrder.getUserRid());
                        serviceOrderData.setSoStartDate(DateUtil.convertStringToCalendar(object.getCurrentDateTimeByUTZ()));
                        serviceOrderDAO.saveServiceOrder(serviceOrderData);
                        break;
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return serviceOrderData != null ? serviceOrderData.getId() : null;
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        Integer boState = 0;
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("id", boRID));
        List<ServiceOrderData> serviceOrderDataList = serviceOrderDAO.getServiceOrder(criterionList);
        if (CollectionUtils.isNotEmpty(serviceOrderDataList)) {
            boState = serviceOrderDataList.get(0).getSoState();
        }
        return boState;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        List<ServiceOrderData> serviceOrderDataList = load(boRID);
        StringBuilder boDescriptor = new StringBuilder();
        if (CollectionUtils.isNotEmpty(serviceOrderDataList)) {
            boDescriptor.append(serviceOrderDataList.get(0).getSoOrderNo()).append("&");
            boDescriptor.append(serviceOrderDataList.get(0).getCreatedDateTime()).append("&");
            boDescriptor.append(serviceOrderDataList.get(0).getSoStatus());
        }
        return boDescriptor.toString();
    }

    private List<ServiceOrderData> load(Integer boRID) {
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("id", boRID));
        List<ServiceOrderData> serviceOrderDataList = serviceOrderDAO.getServiceOrder(criterionList);
        return serviceOrderDataList;
    }

    private List<ServiceMaster> getServiceMasterList(Integer smRid) {
        ServiceMasterSearchRequest serviceMasterSearchRequest = new ServiceMasterSearchRequest();
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("id", smRid));
        serviceMasterSearchRequest.setSearchCriterionList(criterionList);
        return masterService.getServiceMaster(serviceMasterSearchRequest, true);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveServiceOrderResult(List<ServiceOrder> serviceOrderList) throws DcometServiceException {
        List<LabResultD> labResultDs = new ArrayList<>();

        LabResultH labResultH = new LabResultH();
        labResultH.setCurrentObject(serviceOrderList.get(0));
        for (ServiceOrder serviceOrder : serviceOrderList) {
            ServiceOrderData serviceOrderData = load(serviceOrder.getId()).get(0);
            String orderedDt = DateUtil.convertCalendarToString(serviceOrderData.getSoStartDate());

            save(serviceOrder, serviceOrder.getId(), "SERVICE_ORDER", "BUILTIN_ACTION");

            List<Criterion> searchCriterionList = new ArrayList<>();
            DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
            searchCriterionList.add(Restrictions.eq("id", serviceOrderData.getSoSerMode()));
            ddictSearchRequest.setSearchCriterionList(searchCriterionList);
            Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
            if (ddictResult != null) {
                if ((ddictResult.getDdictValue()).equals("Laboratory") && CollectionUtils.isNotEmpty(serviceOrder.getLabResultDList())) {
                    for (LabResultD labResultD : serviceOrder.getLabResultDList()) {
                        labResultD.setLrdSORID(serviceOrder.getId());
                        String nodes = labResultD.getLrdNodes();
                        if (!nodes.equals("0")) {
                            labResultD.setLrdServiceRid(serviceOrder.getSoItemID());
                        }

                        labResultDs.add(labResultD);

                        labResultH.setLrhDoctorRid(serviceOrder.getSoAttndDocRID());
                        labResultH.setLrhPatientVisitID(serviceOrder.getSoVisitRID());
                        labResultH.setLrhPatientID(serviceOrder.getSoPatientRID());
                        labResultH.setLrhRemarks(serviceOrder.getSoRemarks());
                        labResultH.setLrhSignBy(serviceOrder.getSoProcessedBY());
                        labResultH.setLrhState(2);
                        labResultH.setLrhStatus(2);
                        labResultH.setLrhOrderedDateTime(orderedDt);
                        labResultH.setLrhCollectedDateTime(orderedDt);
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(labResultDs)) {
            labResultH.setLabResultDs(labResultDs);
//            if (labResultH.getEntityRid() == 2) {
//                laboratoryService.saveLabResultH(labResultH, true);
//            } else {
            laboratoryService.save(labResultH, labResultH.getId(), "LAB_APPROVAL", "SUBMIT");
//            }
        }
    }

    @Override
    public List<Patient> getPendingServiceOrders(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException {
        List<Patient> patients = new ArrayList<>();
        try {
            patients = clinicalAdapter.convertPatientDataToPatient(serviceOrderDAO.getPendingServicePatients(serviceOrderSearchRequest));
            if (CollectionUtils.isNotEmpty(patients)) {
                for (Patient patient : patients) {
                    List<Criterion> criterions = new ArrayList<>();
                    criterions.add(Restrictions.eq("soPatientRID", patient.getId()));
                    criterions.addAll(serviceOrderSearchRequest.getSearchCriterionList());
                    ServiceOrderSearchRequest serviceOrderSearchRequestNew = new ServiceOrderSearchRequest();
                    serviceOrderSearchRequestNew.setSearchCriterionList(criterions);
                    patient.setServiceOrders(getServiceOrder(serviceOrderSearchRequestNew, true));
                }
            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return patients;
    }

    private List<Patient> getPendingServicePatients(ServiceOrderSearchRequest serviceOrderSearchRequest) {
        List<Patient> patients = new ArrayList<>();
        List<ServiceOrder> serviceOrders = serviceOrderAdapter.convertServiceOrderDataToServiceOrder(serviceOrderDAO.getServiceOrder(serviceOrderSearchRequest));
        HashMap<Integer, ServiceOrder> sod = new HashMap<>();
        if (CollectionUtils.isNotEmpty(serviceOrders)) {
            for (ServiceOrder serviceOrder : serviceOrders) {
                sod.put(serviceOrder.getSoPatientRID(), serviceOrder);
            }
            List a = new ArrayList<>();
            for (Map.Entry m : sod.entrySet()) {
                a.add(m.getKey());
            }

            List<Criterion> searchCriterionList = new ArrayList<>();
            PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
            searchCriterionList.add(Restrictions.in("id", a));
            patientSearchRequest.setSearchCriterionList(searchCriterionList);
            patients = clinicalService.getPatient(patientSearchRequest, false);
        }
        return patients;
    }

    @Override
    public List<Patient> getPendingServiceOrders1(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException {
        List<Patient> patients = new ArrayList<>();
        try {
            patients = getPendingServicePatients(serviceOrderSearchRequest);
            if (CollectionUtils.isNotEmpty(patients)) {
                for (Patient patient : patients) {
                    List<Criterion> criterions = new ArrayList<>();
                    criterions.add(Restrictions.eq("soPatientRID", patient.getId()));
                    criterions.addAll(serviceOrderSearchRequest.getSearchCriterionList());
                    ServiceOrderSearchRequest serviceOrderSearchRequestNew = new ServiceOrderSearchRequest();
                    serviceOrderSearchRequestNew.setSearchCriterionList(criterions);
                    patient.setServiceOrders(getServiceOrder(serviceOrderSearchRequestNew, true));
                }
            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return patients;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveServiceOrderWithD(List<ServiceOrder> serviceOrderList, boolean includeChild) throws DcometServiceException {
        try {
            for (ServiceOrder serviceOrder : serviceOrderList) {
                ServiceOrderData serviceOrderData = serviceOrderAdapter.convertServiceOrderToServiceOrderData(serviceOrder);
                serviceOrderDAO.saveServiceOrder(serviceOrderData);
                if (includeChild) {
                    if (CollectionUtils.isNotEmpty(serviceOrder.getServiceOrderDList())) {
                        List<ServiceOrderDData> serviceOrderDDataList = serviceOrderAdapter.convertServiceOrderDToServiceOrderDData(serviceOrder.getServiceOrderDList());
                        for (ServiceOrderDData serviceOrderDData : serviceOrderDDataList) {
                            serviceOrderDData.setSodSoRid(serviceOrderData.getId());
                        }
                        serviceOrderDAO.saveServiceOrderD(serviceOrderDDataList);
                    }
                }
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public String getRadioServicePrint(ServiceOrderSearchRequest serviceOrderSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            List<ServiceOrderD> serviceOrderDList = getServiceOrderD(serviceOrderSearchRequest);
            Patient patient = clinicalService.getPatient(serviceOrderDList.get(0).getSodPatRid());
            if (CollectionUtils.isNotEmpty(serviceOrderDList)) {
                for (ServiceOrderD serviceOrderD : serviceOrderDList) {
                    if (serviceOrderD.getSodNodes() != null) {
                        string = dataDictionaryService.getReportFromTemplate("RADIOLOGY_REPORT", serviceOrderDList, "soD", serviceOrderSearchRequest.getEntityRid());
                    }
                }
            }
            Entity entity = CacheUtil.getEntity(serviceOrderSearchRequest.getEntityRid());
            string = dataDictionaryService.getReportFromHTML(string, entity, "ent");
            string = dataDictionaryService.getReportFromHTML(string, masterService1.getPrintInfo(serviceOrderSearchRequest.getEntityRid()), "pe");
            string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }
}
