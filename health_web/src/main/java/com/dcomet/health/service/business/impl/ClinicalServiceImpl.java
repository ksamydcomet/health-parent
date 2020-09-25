package com.dcomet.health.service.business.impl;

import com.dcomet.health.adapter.ClinicalAdapter;
import com.dcomet.health.dao.ClinicalDAO;
import com.dcomet.health.dao.data.ComplaintsData;
import com.dcomet.health.dao.data.VisitPlanData;
import com.dcomet.health.dao.data.VisitTemplateData;
import com.dcomet.health.dao.data.VisitVitalsData;
import com.dcomet.health.domain.Complaints;
import com.dcomet.health.domain.ComplaintsSearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.domain.VisitPlan;
import com.dcomet.health.domain.VisitPlanSearchRequest;
import com.dcomet.health.domain.VisitTemplate;
import com.dcomet.health.domain.VisitTemplateSearchRequest;
import com.dcomet.health.domain.VisitVitals;
import com.dcomet.health.domain.VisitVitalsSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.dao.data.AdmissionDetailsData;
import com.dcomet.health.dao.data.AppointmentBookingData;
import com.dcomet.health.dao.data.AppointmentData;
import com.dcomet.health.dao.data.AppointmentReservationData;
import com.dcomet.health.dao.data.AppointmentResourceMapData;
import com.dcomet.health.dao.data.AppointmentTokenData;
import com.dcomet.health.dao.data.FreeConsultationPatientDoctorMapData;
import com.dcomet.health.dao.data.HistoryData;
import com.dcomet.health.dao.data.KinData;
import com.dcomet.health.dao.data.PatientData;
import com.dcomet.health.dao.data.PatientImageData;
import com.dcomet.health.dao.data.VisitData;
import com.dcomet.health.domain.AdmissionDetails;
import com.dcomet.health.domain.AdmissionDetailsSearchRequest;
import com.dcomet.health.domain.Appointment;
import com.dcomet.health.domain.AppointmentBooking;
import com.dcomet.health.domain.AppointmentBookingSearchRequest;
import com.dcomet.health.domain.AppointmentReservation;
import com.dcomet.health.domain.AppointmentReservationSearchRequest;
import com.dcomet.health.domain.AppointmentResourceMap;
import com.dcomet.health.domain.AppointmentResourceMapSearchRequest;
import com.dcomet.health.domain.AppointmentSearchRequest;
import com.dcomet.health.domain.AppointmentToken;
import com.dcomet.health.domain.AppointmentTokenSearchRequest;
import com.dcomet.health.domain.BedGroupM;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedMaster;
import com.dcomet.health.domain.BedMasterSearchRequest;
import com.dcomet.health.domain.BedOccupancy;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMap;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMapSearchRequest;
import com.dcomet.health.domain.History;
import com.dcomet.health.domain.HistorySearchRequest;
import com.dcomet.health.domain.Kin;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.domain.VisitSearchRequest;
import com.dcomet.health.domain.reportmodel.DoctorWiseReport;
import com.dcomet.health.service.business.BedManagementService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.ItemOrderService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.ServiceRequestService;
import com.dcomet.health.vo.AppointmentSlots;
import com.dcomet.health.vo.AppointmentSlotsBydate;
import com.dcomet.health.vo.AppointmentSlotsSearchRequest;
import com.dcomet.health.vo.AppointmentSlotsWithPatient;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.FreeConsutationVo;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.FreeConsultationCategory;
import com.dcomet.module.domain.FreeConsultationCondition;
import com.dcomet.module.domain.FreeConsultationTransition;
import com.dcomet.module.domain.Resource;
import com.dcomet.module.domain.ResourceWorkingHours;
import com.dcomet.module.domain.SysParam;
import com.dcomet.module.master.domain.HolidayMaster;
import com.dcomet.module.master.domain.ResourceServiceMap;
import com.dcomet.module.master.domain.ServiceMaster;
import com.dcomet.module.master.domain.ServicePriceType;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
@Service("clinicalService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ClinicalServiceImpl implements ClinicalService {

    @Autowired
    @Qualifier("clinicalDAO")
    ClinicalDAO clinicalDAO;

    @Autowired
    @Qualifier("clinicalAdapter")
    ClinicalAdapter clinicalAdapter;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("bedManagementService")
    BedManagementService bedManagementService;

    @Autowired
    @Qualifier("masterService")
    MasterService masterService;

    @Autowired
    @Qualifier("serviceRequestService")
    ServiceRequestService serviceRequestService;

    @Autowired
    @Qualifier("itemOrderService")
    ItemOrderService itemOrderService;

//    @Autowired
//    @Qualifier("billingService")
//    BillingService billingService;
    @Override
    public List<Appointment> getAppointment(AppointmentSearchRequest appointmentSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<Appointment> result = null;
        try {
            List<AppointmentData> listData = clinicalDAO.getAppointment(appointmentSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAppointmentDataToAppointment(listData);
                if (includeChilds) {
                    for (Appointment appointment : result) {
                        AppointmentResourceMapSearchRequest childSearchRequest = new AppointmentResourceMapSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("armApptRid", appointment.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<AppointmentResourceMap> appointmentResult = getAppointmentResourceMap(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(appointmentResult)) {
                            appointment.setAppointmentResourceMap(appointmentResult);
                        }
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
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointment(Appointment appointment, boolean includeChild) throws DcometServiceException {
        try {
            if (appointment.getId() != null) {
                List<Appointment> appointments = getAppointmentById(appointment.getId());
                if (appointment.getApptPatientRID() != 0 || appointment.getApptPatientRID() != null) {
                    appointment.setApptPatientRID(appointment.getApptPatientRID());
                } else {
                    appointment.setApptPatientRID(appointments.get(0).getApptPatientRID());
                }
                if (appointment.getApptvisitRID() != null) {
                    appointment.setApptvisitRID(appointment.getApptvisitRID());
                }
                if (appointment.getApptPatientMrn() != null) {
                    appointment.setApptPatientMrn(appointment.getApptPatientMrn());
                } else {
                    appointment.setApptPatientMrn(appointments.get(0).getApptPatientMrn());
                }
                appointment.setApptDocRID(appointments.get(0).getApptDocRID());
                appointment.setApptUnitRID(appointments.get(0).getApptUnitRID());
                appointment.setApptPatientAge(appointments.get(0).getApptPatientAge());
                appointment.setApptPatientGenderIndex(appointments.get(0).getApptPatientGenderIndex());
                appointment.setApptPatientName(appointments.get(0).getApptPatientName());
                appointment.setApptPatientPhone(appointments.get(0).getApptPatientPhone());
                appointment.setApptFromTime(appointments.get(0).getApptFromTime());
                appointment.setApptFromDateTime(appointments.get(0).getApptFromDateTime());
                AppointmentData appointmentData = clinicalAdapter.convertAppointmentToAppointmentData(appointment);
                clinicalDAO.saveAppointment(appointmentData);
            } else {
                AppointmentData appointmentData = clinicalAdapter.convertAppointmentToAppointmentData(appointment);
                clinicalDAO.saveAppointment(appointmentData);
                if (includeChild) {
                    if (CollectionUtils.isNotEmpty(appointment.getAppointmentResourceMap())) {
                        List<AppointmentResourceMapData> appointmentResourceMapDataList = clinicalAdapter.convertAppointmentResourceMapToAppointmentResourceMapData(appointment.getAppointmentResourceMap());
                        for (AppointmentResourceMapData appointmentResourceMapData : appointmentResourceMapDataList) {
                            appointmentResourceMapData.setArmApptRid(appointmentData.getId());
                        }
                        clinicalDAO.saveAppointmentResourceMap(appointmentResourceMapDataList);
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
    public List<AppointmentBooking> getAppointmentBooking(AppointmentBookingSearchRequest appointmentBookingSearchRequest)
            throws DcometServiceException {
        List<AppointmentBooking> result = null;
        try {
            List<AppointmentBookingData> listData = clinicalDAO.getAppointmentBooking(appointmentBookingSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAppointmentBookingDataToAppointmentBooking(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointmentBooking(List<AppointmentBooking> appointmentBookingList) {
        try {
            List<AppointmentBookingData> appointmentBookingDataList = clinicalAdapter.convertAppointmentBookingToAppointmentBookingData(appointmentBookingList);
            clinicalDAO.saveAppointmentBooking(appointmentBookingDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<AppointmentResourceMap> getAppointmentResourceMap(AppointmentResourceMapSearchRequest appointmentResourcemapSearchRequest) throws DcometServiceException {
        List<AppointmentResourceMap> result = null;
        try {
            List<AppointmentResourceMapData> listData = clinicalDAO.getAppointmentResourceMap(appointmentResourcemapSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAppointmentResourceMapDataToAppointmentResourceMap(listData);
//                if (includeChilds) {
//                    for (AppointmentResourceMap appointment : result) {
//                        AppointmentSearchRequest childSearchRequest = new AppointmentSearchRequest();
//                        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//                        searchCriterionList.add(Restrictions.eq("id", appointment.getArmApptRid()));
//                        childSearchRequest.setSearchCriterionList(searchCriterionList);
//                        List<Appointment> appointmentResult = getAppointment(childSearchRequest);
//                        if (CollectionUtils.isNotEmpty(appointmentResult)) {
//                            appointment.setAppointment(appointmentResult);
//                        }
//                    }
//                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

//    @Override
//    public void saveAppointmentResourceMap(List<AppointmentResourceMap> appointmentResourceMapList) {
//        try {
//            List<AppointmentResourceMapData> appointmentResourceMapDataList = clinicalAdapter.convertAppointmentResourceMapToAppointmentResourceMapData(appointmentResourceMapList);
//            clinicalDAO.saveAppointmentResourceMap(appointmentResourceMapDataList);
//        } catch (DcometDAOException e) {
//            throw new DcometServiceException(e);
//        } catch (DcometServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DcometServiceException(e);
//        }
//    }
    @Override
    public List<AppointmentReservation> getAppointmentReservsation(AppointmentReservationSearchRequest appointmentReservationSearchRequest) throws DcometServiceException {
        List<AppointmentReservation> result = null;
        try {
            List<AppointmentReservationData> listData = clinicalDAO.getAppointmentReservation(appointmentReservationSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAppointmentReservationDataToAppointmentReservation(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointmentReservsation(List<AppointmentReservation> appointmentReservationList) {
        try {
            List<AppointmentReservationData> appointmentReservationDataList = clinicalAdapter.convertAppointmentReservationToAppointmentReservationData(appointmentReservationList);
            clinicalDAO.saveAppointmentReservation(appointmentReservationDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<AppointmentToken> getAppointmentToken(AppointmentTokenSearchRequest appointmentTokenSearchRequest) throws DcometServiceException {
        List<AppointmentToken> result = null;
        try {
            List<AppointmentTokenData> listData = clinicalDAO.getAppointmentToken(appointmentTokenSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAppointmentTokenDataToAppointmentToken(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointmentToken(List<AppointmentToken> appointmentToken) {
        try {
            List<AppointmentTokenData> appointmentTokenDataList = clinicalAdapter.convertAppointmentTokenToAppointmentTokenData(appointmentToken);
            clinicalDAO.saveAppointmentToken(appointmentTokenDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Patient getPatient(Integer patientId) throws DcometServiceException {
        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
        patientSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", patientId)}));
        List<Patient> resultList = getPatient(patientSearchRequest, false);
        return CollectionUtils.isNotEmpty(resultList) ? resultList.get(0) : null;
    }

    @Override
    public List<Patient> getPatient(PatientSearchRequest patientSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<Patient> result = new ArrayList<>();
        try {
            List<PatientData> resultData = clinicalDAO.getPatient(patientSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertPatientDataToPatient(resultData);
                if (includeChilds) {
                    for (Patient patient : result) {
                        PatientSearchRequest childSearchRequest = new PatientSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("kinPatRid", patient.getId()));
                        searchCriterionList.add(Restrictions.eq("kinIsActive", 1));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Kin> ServiceResult = getKinDetails(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(ServiceResult)) {
                            patient.setKinDetails(ServiceResult);
                        }
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
        return result;
    }

    @Override
    public Integer savePatient(Patient patient) throws DcometServiceException {
        try {
            if (patient.getId() == null) {
                AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("MRN", patient.getEntityRid());
                patient.setPatMrnNo(autoNumber.getAutoNumber());
                patient.setPatSeqNo(autoNumber.getAutoSequenceNumber());
                patient.setPatRegDate(patient.getCurrentDateByUTZ());
                dataDictionaryService.saveAutoNumberIncrement("MRN", patient.getEntityRid());
            }
            PatientData patientData = clinicalAdapter.convertPatientToPatientData(patient);
            clinicalDAO.savePatient(patientData);
            if (CollectionUtils.isNotEmpty(patient.getKinDetails())) {
                List<KinData> kinDataList = clinicalAdapter.convertKinToKinData(patient.getKinDetails());
                clinicalDAO.saveKinDetails(kinDataList);
            }
            return patientData.getId();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Visit getVisit(Integer id) throws DcometServiceException {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        visitSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<Visit> result = getVisit(visitSearchRequest, false);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    public Visit getVisitWithChild(Integer id) throws DcometServiceException {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        visitSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<Visit> result = getVisit(visitSearchRequest, true);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    public Visit getActiveVisit(Integer patRid, Date dateS) throws DcometServiceException {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visPatRid", patRid));
        searchCriterionList.add(Restrictions.eq("visDate", dateS));
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        List<Visit> result = getVisitWitPatientAlone(visitSearchRequest, true);
        if (CollectionUtils.isEmpty(result)) {
            Visit visit = new Visit();
            searchCriterionList.clear();
            searchCriterionList.add(Restrictions.eq("visPatRid", patRid));
            searchCriterionList.add(Restrictions.eq("visTypeIndex", 19));//need to find visit from ddict
            visitSearchRequest.setSearchCriterionList(searchCriterionList);
            result = getVisitWitPatientAlone(visitSearchRequest, true);
            if (CollectionUtils.isNotEmpty(result)) {
                for (Visit visitdb : result) {
                    if (visitdb.getVisState() < 3) {
                        visit = visitdb;
                        result.clear();
                        result.add(visit);
                        break;
                    }
                }
            }
        }
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    public Visit getVisitByPatientRid(Integer patRid, Date dateS) throws DcometServiceException {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visPatRid", patRid));
        searchCriterionList.add(Restrictions.eq("visDate", dateS));
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        List<Visit> result = getVisitWitPatientAlone(visitSearchRequest, true);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    public Visit getActiveIpVisitByPatientRid(Integer patRid) throws DcometServiceException {
        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
        Visit visit = new Visit();
        int visitCheck = 0;
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("visPatRid", patRid));
        searchCriterionList.add(Restrictions.eq("visTypeIndex", 19));//need to find visit from ddict
        visitSearchRequest.setSearchCriterionList(searchCriterionList);
        List<Visit> result = getVisitWitPatientAlone(visitSearchRequest, true);
        if (CollectionUtils.isNotEmpty(result)) {
            for (Visit visitdb : result) {
                if (visitdb.getVisState() < 3) {
                    visitCheck = 1;
                    visit = visitdb;
                    break;
                }
            }
        }
        return visitCheck == 1 ? visit : null;
    }

    @Override
    public String getCsPrint(VisitSearchRequest visitSearchRequest) throws DcometServiceException {
        String string = new String();

        try {
            List<Visit> visitLisit = getVisit(visitSearchRequest, true);
            if (CollectionUtils.isNotEmpty(visitLisit)) {
                for (Visit visit : visitLisit) {
                    string = dataDictionaryService.getReportFromTemplate("CS_PRINT", visit, "visit", visitSearchRequest.getEntityRid());
                }
                ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
                List<Criterion> criterionList = new ArrayList<>();
                criterionList.add(Restrictions.eq("serReqOpVisitRid", visitLisit.get(0).getId()));
                serviceRequestHSearchRequest.setSearchCriterionList(criterionList);
                List<ServiceRequestH> serviceRequestHList = serviceRequestService.getServiceRequestH(serviceRequestHSearchRequest, true);
                if (CollectionUtils.isNotEmpty(serviceRequestHList)) {
                    for (ServiceRequestH serviceRequestH : serviceRequestHList) {
                        string = dataDictionaryService.getReportFromHTML(string, serviceRequestH, "serviceReqH");
                    }
                }
                Entity entity = CacheUtil.getEntity(visitSearchRequest.getEntityRid());
                string = dataDictionaryService.getReportFromHTML(string, entity, "ent");
                string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(visitSearchRequest.getEntityRid()), "pe");
            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    @Override
    public List<Visit> getVisit(VisitSearchRequest visitSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<Visit> result = null;
        try {
            List<VisitData> listData = clinicalDAO.getVisits(visitSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertVisitDataToVisit(listData);
                if (includeChilds) {
                    for (Visit visit : result) {
                        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("id", visit.getVisPatRid()));
                        patientSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Patient> patientResult = getPatient(patientSearchRequest, false);
                        if (patientResult != null && !patientResult.isEmpty()) {
                            visit.setPatient(patientResult);
                        }
                        searchCriterionList.clear();
                        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
                        searchCriterionList.add(Restrictions.eq("id", visit.getVisReasonIndex()));
                        ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Ddict> ddictResult = dataDictionaryService.getDdict(ddictSearchRequest);
                        if (ddictResult != null && !ddictResult.isEmpty()) {
                            visit.setVisReasonName(ddictResult.get(0).getDdictValue());
                        }
                        searchCriterionList.clear();
                        searchCriterionList.add(Restrictions.eq("id", visit.getVisSpecialityIndex()));
                        ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Ddict> ddictResult1 = dataDictionaryService.getDdict(ddictSearchRequest);
                        if (ddictResult1 != null && !ddictResult1.isEmpty()) {
                            visit.setVisSpecialityName(ddictResult1.get(0).getDdictValue());
                        }

                        searchCriterionList.clear();
                        BedOccupancySearchRequest bedOccupancySearchRequest = new BedOccupancySearchRequest();
                        searchCriterionList.add(Restrictions.eq("bocVisitRid", visit.getId()));
                        bedOccupancySearchRequest.setSearchCriterionList(searchCriterionList);
                        List<BedOccupancy> bedOccupancylist = bedManagementService.getBedOccupancy(bedOccupancySearchRequest);
                        if (CollectionUtils.isNotEmpty(bedOccupancylist)) {
                            if (visit.getVisTypeIndex() == 19) {
                                visit.setBedOccupancy(bedOccupancylist);
                            }
                        }

//                        searchCriterionList.clear();
//                        HistorySearchRequest historySearchRequest = new HistorySearchRequest();
//                        searchCriterionList.add(Restrictions.eq("hisVisitRid", visit.getId()));
//                        historySearchRequest.setSearchCriterionList(searchCriterionList);
//                        List<History> historylist = getHistory(historySearchRequest);
//                        if (CollectionUtils.isNotEmpty(historylist)) {
//                            visit.setHistoryList(historylist);
//                        }
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
        return result;
    }

    @Override
    public List<Visit> getVisitWitPatientAlone(VisitSearchRequest visitSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<Visit> result = null;
        try {
            List<VisitData> listData = clinicalDAO.getVisits(visitSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertVisitDataToVisit(listData);
                if (includeChilds) {
                    for (Visit visit : result) {
                        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("id", visit.getVisPatRid()));
                        patientSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Patient> patientResult = getPatient(patientSearchRequest, false);
                        if (patientResult != null && !patientResult.isEmpty()) {
                            visit.setPatient(patientResult);
                        }
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
        return result;
    }

    @Override
    public Integer saveVisit(Visit visit) throws DcometServiceException {
        try {
            if (visit.getId() == null) {
                VisitData visitData = clinicalAdapter.convertVisitToVisitData(visit);
                clinicalDAO.saveVisit(visitData);
                return visitData.getId();
            } else {
                Visit visitdb = getVisit(visit.getId());
                visit.setVisState(visitdb.getVisState());
                visit.setVisStatus(visitdb.getVisStatus());
                if (visit.getVisTypeIndex() == null || visit.getVisTypeIndex() == 0) {
                    visit.setVisTypeIndex(visitdb.getVisTypeIndex());
                }
                if (visit.getVisDate() == null || StringUtils.isBlank(visit.getVisDate())) {
                    visit.setVisDate(visitdb.getVisDate());
                }
                if (visit.getVisSpecialityIndex() == null || visit.getVisSpecialityIndex() == 0) {
                    visit.setVisSpecialityIndex(visitdb.getVisSpecialityIndex());
                }
                if (visit.getVisDiagnosis() == null) {
                    visit.setVisDiagnosis(visitdb.getVisDiagnosis());
                }
                if (visit.getVisIsCompleted() == null) {
                    visit.setVisIsCompleted(visitdb.getVisIsCompleted());
                }
                if (visit.getVisCsNodes() == null) {
                    visit.setVisCsNodes(visitdb.getVisCsNodes());
                }
                if (visit.getVisTreatment() == null) {
                    visit.setVisTreatment(visitdb.getVisTreatment());
                }
                visit.setVisReasonIndex(visitdb.getVisReasonIndex());
                visit.setVisRefTypeIndex(visitdb.getVisRefTypeIndex());
                visit.setVisRemarks(visitdb.getVisRemarks());
                VisitData visitData = clinicalAdapter.convertVisitToVisitData(visit);
                clinicalDAO.saveVisit(visitData);
                return visitData.getId();
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
    public List<Complaints> getComplaints(ComplaintsSearchRequest complaintsSearchRequest) throws DcometServiceException {
        List<Complaints> result = null;
        try {
            List<ComplaintsData> resultData = clinicalDAO.getComplaints(complaintsSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertComplaintsDataToComplaints(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<VisitVitals> getVisitVitals(VisitVitalsSearchRequest visitVitalsSearchRequest) throws DcometServiceException {
        List<VisitVitals> result = null;
        try {
            List<VisitVitalsData> resultData = clinicalDAO.getVisitVitals(visitVitalsSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertVisitVitalsDataToVisitVitals(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<VisitPlan> getVisitPlan(VisitPlanSearchRequest visitPlanSearchRequest) throws DcometServiceException {
        List<VisitPlan> result = null;
        try {
            List<VisitPlanData> resultData = clinicalDAO.getVisitPlan(visitPlanSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertVisitPlanDataToVisitPlan(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<VisitTemplate> getVisitTemplate(VisitTemplateSearchRequest visitTemplateSearchRequest) throws DcometServiceException {
        List<VisitTemplate> result = null;
        try {
            List<VisitTemplateData> resultData = clinicalDAO.getVisitTemplate(visitTemplateSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertVisitTemplateDataToVisitTemplate(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

//    public List<Visit> getMyPatients(VisitSearchRequest visitSearchRequest, boolean includeLists) throws DcometServiceException {
//        List<Visit> result = new ArrayList<>();
//        try {
//            List<SkuData> resultData = masterDAO.getSku(visitSearchRequest);
//            if (CollectionUtils.isNotEmpty(resultData)) {
//                result = masterAdapter.convertSkuDataToSku(resultData);
//                if (includeLists) {
//                    for (Sku sku : result) {
//                        SkuSearchRequest childSearchRequest = new SkuSearchRequest();
//                        List<Criterion> searchCriterionList = new ArrayList<>();
//                        searchCriterionList.add(Restrictions.eq("ssmSkuRID", sku.getId()));
//                        childSearchRequest.setSearchCriterionList(searchCriterionList);
//                        List<SkuSupplierMap> skuSupplierMapResult = getSkuSupplierMap(childSearchRequest);
//                        if (CollectionUtils.isNotEmpty(skuSupplierMapResult)) {
//                            sku.setSkuSupplierMapList(skuSupplierMapResult);
//                        }
//                        searchCriterionList.clear();
//                        searchCriterionList.add(Restrictions.eq("sumSkuRID", sku.getId()));
//                        childSearchRequest.setSearchCriterionList(searchCriterionList);
//                        List<SkuUnitMap> skuUnitMap2Result = getSkuUnitMap(childSearchRequest);
//                        if (CollectionUtils.isNotEmpty(skuUnitMap2Result)) {
//                            sku.setSkuUnitMapList(skuUnitMap2Result);
//                        }
//                    }
//
//                }
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
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMyPatient(Visit visit, boolean includeLists) throws DcometServiceException {
        try {
            if (includeLists) {
                if (CollectionUtils.isNotEmpty(visit.getComplaints())) {
                    for (Complaints complaints : visit.getComplaints()) {
                        complaints.setCurrentObject(visit);
                        ComplaintsData complaintsData = clinicalAdapter.convertComplaintsToComplaintsData(complaints);
                        clinicalDAO.saveComplaints(complaintsData);
                    }

                }
                if (CollectionUtils.isNotEmpty(visit.getVisitVitals())) {
                    for (VisitVitals visitVitals : visit.getVisitVitals()) {
                        visitVitals.setCurrentObject(visit);
                        VisitVitalsData visitVitalsData = clinicalAdapter.convertVisitVitalsToVisitVitalsData(visitVitals);
                        clinicalDAO.saveVisitVitals(visitVitalsData);
                    }

                }

                if (CollectionUtils.isNotEmpty(visit.getVisitPlan())) {
                    for (VisitPlan visitPlan : visit.getVisitPlan()) {
                        visitPlan.setCurrentObject(visit);
                        VisitPlanData visitPlanData = clinicalAdapter.convertVisitPlanToVisitPlanData(visitPlan);
                        clinicalDAO.saveVisitPlan(visitPlanData);
                    }

                }

                if (CollectionUtils.isNotEmpty(visit.getVisitTemplate())) {
                    for (VisitTemplate visitTemplate : visit.getVisitTemplate()) {
                        visitTemplate.setCurrentObject(visit);
                        VisitTemplateData visitTemplateData = clinicalAdapter.convertVisitTemplateToVisitTemplateData(visitTemplate);
                        clinicalDAO.saveVisitTemplate(visitTemplateData);
                    }
                }
//                if (CollectionUtils.isNotEmpty(visit.getServiceRequest())) {
//                    for (ServiceRequest serviceRequest : visit.getServiceRequest()) {
//                        serviceRequest.setCurrentObject(visit);
//                        if (serviceRequest.getSerType() == 1) {
//                            serviceRequestService.save(serviceRequest, 0, "REQUEST_SERVICE_ORDER", "SUBMIT");
//                        }
//                    }
//                }
//                if (CollectionUtils.isNotEmpty(visit.getServiceRequestDrug())) {
//                    for (ServiceRequest serviceRequest : visit.getServiceRequestDrug()) {
//                        serviceRequest.setCurrentObject(visit);
//                        if (serviceRequest.getSerType() == 2) {
//                            serviceRequestService.save(serviceRequest, 0, "REQUEST_DRUGS_ORDER", "SUBMIT");
//                        }
//                    }
//
//                }

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
    public Integer saveVisitVitals(VisitVitals visitVitals) throws DcometServiceException {
        try {
            VisitVitalsData visitVitalsData = clinicalAdapter.convertVisitVitalsToVisitVitalsData(visitVitals);
            clinicalDAO.saveVisitVitals(visitVitalsData);
            return visitVitalsData.getVitVisRID();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer saveVisitTemplate(VisitTemplate visitTemplate) throws DcometServiceException {
        try {
            VisitTemplateData visitTemplateData = clinicalAdapter.convertVisitTemplateToVisitTemplateData(visitTemplate);
            clinicalDAO.saveVisitTemplate(visitTemplateData);
            return visitTemplateData.getVistVisitRID();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public Integer saveComplaints(Integer entityRid, List<Complaints> complaintsList) throws DcometServiceException {
        try {
            List<ComplaintsData> complaintsDataList = clinicalAdapter.convertComplaintsToComplaintsData(complaintsList);
            clinicalDAO.saveComplaintsList(entityRid, complaintsDataList);
            return CollectionUtils.isNotEmpty(complaintsDataList) ? complaintsDataList.get(0).getCplVisitRID() : null;
        } catch (DcometServiceException e) {
            throw e;
        }
    }

    @Override
    public List<Kin> getKinDetails(PatientSearchRequest patientSearchRequest) throws DcometServiceException {
        List<Kin> result = null;
        try {
            List<KinData> resultData = clinicalDAO.getKinDetails(patientSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertKinDataToKin(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

//    @Override
//    public List<History> getHistory(HistorySearchRequest historySearchRequest) throws DcometServiceException {
//        List<History> result = null;
//        try {
//            List<HistoryData> listData = clinicalDAO.getHistory(historySearchRequest);
//            if (CollectionUtils.isNotEmpty(listData)) {
//                result = clinicalAdapter.convertHistoryDataToHistory(listData);
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
//
//    @Override
//    public void saveHistory(History history) throws DcometServiceException {
//        try {
//            HistoryData historyData = clinicalAdapter.convertHistoryToHistoryData(history);
//            clinicalDAO.saveHistory(historyData);
//
//        } catch (DcometDAOException e) {
//            throw new DcometServiceException(e);
//        } catch (DcometServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DcometServiceException(e);
//        }
//    }
    @Override
    public Integer saveHistory(Integer entityRid, List<History> historys) throws DcometServiceException {
        try {
            List<HistoryData> historyDatas = clinicalAdapter.convertHistoryToHistoryData(historys);
            clinicalDAO.saveHistoryList(entityRid, historyDatas);
            return CollectionUtils.isNotEmpty(historyDatas) ? historyDatas.get(0).getHisVisitRid() : null;
        } catch (DcometServiceException e) {
            throw e;
        }
    }

    @Override
    public List<History> getHistorys(HistorySearchRequest historySearchRequest) throws DcometServiceException {
        List<History> result = null;
        try {
            List<HistoryData> listData = clinicalDAO.getHistory(historySearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertHistoryDataToHistory(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PatientImageData savePatientPhoto(PatientImageData photo) throws DcometServiceException {
        try {
            return clinicalDAO.savePatientPhoto(photo);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePatientImage(PatientImageData fileData) throws DcometServiceException {
        try {
            PatientImageData patImageData = getPatPhoto(fileData.getId(), 0);
            if (patImageData == null) {
                PatientImageData patImageFile = getPatPhoto(fileData.getId(), fileData.getPatImgPatRid());
                fileData.setPatImgPhoto(patImageFile.getPatImgPhoto());
            } else {
                PatientImageData existPatImageData = getPatPhotoByImgPatRid(fileData.getPatImgPatRid());
                if (existPatImageData != null) {
                    clinicalDAO.deletePatImage(existPatImageData);
                }
                fileData.setPatImgPhoto(patImageData.getPatImgPhoto());
            }
            clinicalDAO.savePatImage(fileData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public PatientImageData getPatPhoto(Integer imgRid, Integer imgPatRid) throws DcometServiceException {
        return clinicalDAO.getPatPhoto(imgRid, imgPatRid);
    }

    @Override
    public PatientImageData getPatPhotoByImgPatRid(Integer imgPatRid) throws DcometServiceException {
        return clinicalDAO.getPatPhotoByImgPatRid(imgPatRid);
    }

    @Override
    public PatientImageData getPatPhotoById(Integer id) throws DcometServiceException {
        return clinicalDAO.getPatPhotoByImgRid(id);
    }

    @Override
    public AppointmentSlots getAppointmentSlots(AppointmentSlotsSearchRequest appointmentSlotsSearchRequest, Integer doctorRid, String selectedDay, Date selectedDate, boolean includeChilds) throws DcometServiceException { //, Integer dateInterval
        AppointmentSlots appointmentSlots = new AppointmentSlots();
        List<AppointmentSlotsBydate> appointmentSlotsBydates = new ArrayList<>();
        try {
            SysParam sysParam = dataDictionaryService.getSysParam("DATE_INTERVAL", appointmentSlotsSearchRequest.getEntityRid());
            List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
            String assignSValue = "";
            Integer check = 0;
            if (sysParam != null) {
                check = Integer.valueOf(sysParam.getParamValue());
                assignSValue = "-" + sysParam.getParamValue();
            } else {
                check = 1;
                assignSValue = "-" + "1";
            }
            Integer assignIvalue = Integer.valueOf(assignSValue);
            for (int i = assignIvalue; i <= check; i++) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String input = String.valueOf(dateFormat.format(selectedDate));
                Date myDate = dateFormat.parse(input);
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(myDate);
                cal1.add(Calendar.DAY_OF_YEAR, i);
                Date dateD = cal1.getTime();

                Calendar c = Calendar.getInstance();
                c.setTime(dateD);
                int dayI = c.get(Calendar.DAY_OF_WEEK);
                String dayS = getDayOfWeek(dayI);
                for (Ddict ddict : ddicts) {
                    if (Objects.equals(ddict.getDdictValue(), dayS)) {
                        dayI = ddict.getId();
                    }
                }
                appointmentSlotsBydates.add(getSlots(doctorRid, dayS, dayI, dateD, appointmentSlotsSearchRequest.getEntityRid()));
            }
            appointmentSlots.setAppointmentSlotsBydates(appointmentSlotsBydates);

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return appointmentSlots;
    }

    private Integer getFreezeDate(Date sDate) {
        Integer isFreeze = 0;
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = outFormat.format(sDate);
        String currentDate = outFormat.format(date);
        String[] s = selectedDate.split("-");
        String[] c = currentDate.split("-");
        String sYear = s[2];
        String sMonth = s[1];
        String sDay = s[0];
        String cYear = c[2];
        String cMonth = c[1];
        String cDay = c[0];
        Integer iSyear = Integer.valueOf(sYear);
        Integer iSMonth = Integer.valueOf(sMonth);
        Integer iSDay = Integer.valueOf(sDay);
        Integer iCYear = Integer.valueOf(cYear);
        Integer iCMonth = Integer.valueOf(cMonth);
        Integer iCDay = Integer.valueOf(cDay);
        if (iSyear < iCYear) {
            isFreeze = 1;
            return isFreeze;
        } else if (iSyear > iCYear) {
            isFreeze = 0;
            return isFreeze;
        } else if (Objects.equals(iSyear, iCYear)) {
            if (iSMonth < iCMonth) {
                isFreeze = 1;
                return isFreeze;
            } else if (iSMonth > iCMonth) {
                isFreeze = 0;
                return isFreeze;
            } else if (Objects.equals(iSMonth, iCMonth)) {
                if (iSDay < iCDay) {
                    isFreeze = 1;
                    return isFreeze;
                } else if (iSDay > iCDay) {
                    isFreeze = 0;
                    return isFreeze;
                } else if (Objects.equals(iSDay, iCDay)) {
                    isFreeze = 2;
                    return isFreeze;
                }
            }
        }

        return isFreeze;
    }

    private Integer getFreezeTime(String sTime) {
        Integer isDateFreeze = 0;
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
        String currentTime = outFormat.format(date);
        String[] s = sTime.split(":");
        String[] c = currentTime.split(":");
        String sMin = s[1];
        String sHour = s[0];
        String cMin = c[1];
        String cHour = c[0];
        Integer iSHour = Integer.valueOf(sHour);
        Integer iSMin = Integer.valueOf(sMin);
        Integer iCHour = Integer.valueOf(cHour);
        Integer iCMin = Integer.valueOf(cMin);
        if (iSHour < iCHour) {
            isDateFreeze = 1;
            return isDateFreeze;
        } else if (iSHour > iCHour) {
            isDateFreeze = 0;
            return isDateFreeze;
        } else if (Objects.equals(iSHour, iCHour)) {
            if (iSMin < iCMin || Objects.equals(iSMin, iCMin)) {
                isDateFreeze = 1;
                return isDateFreeze;
            } else if (iSMin > iCMin) {
                isDateFreeze = 0;
                return isDateFreeze;
            }
        }
        return isDateFreeze;
    }

    private String getDayOfWeek(Integer dayS) {
        String day = "";
        switch (dayS) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }
// ArrayList<String> fTimeList = new ArrayList<String>();
//        ArrayList<String> tTimeList = new ArrayList<String>();
//    private AppointmentSlotsBydate getAppointmentSlotsBydate(String FromTime, String ToTime, Date mDate, String mDay, Integer mInterval, Integer dayFlag) throws ParseException {

    private AppointmentSlotsBydate getAppointmentSlotsBydate(ArrayList<String> fTimeList, ArrayList<String> tTimeList, Date mDate, String mDay, Integer mInterval, Integer dayFlag) throws ParseException {
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
        AppointmentSlotsBydate appointmentSlotsBydate = new AppointmentSlotsBydate();
        Integer dateFreeze = getFreezeDate(mDate);
        try {
            if (CollectionUtils.isNotEmpty(fTimeList) && CollectionUtils.isNotEmpty(tTimeList)) {
                List<AppointmentSlotsWithPatient> appointmentSlotsWithPatients = new ArrayList<>();
                for (int i = 0; i < fTimeList.size(); i++) {
                    if (fTimeList.get(i) != null && tTimeList.get(i) != null) {
                        String FromTime = fTimeList.get(i);
                        String ToTime = tTimeList.get(i);
                        String mFromTime = FromTime, mToTime = ToTime;
                        mFromTime = df1.format(Time.valueOf(mFromTime));
                        mToTime = df1.format(Time.valueOf(mToTime));
                        appointmentSlotsBydate.setDateS(DateUtil.convertDateToString(mDate));
                        if (dateFreeze == 1) {//past time date freezed
                            appointmentSlotsBydate.setDayFlag(1);
                        } else if (dateFreeze == 0) {//future time
                            appointmentSlotsBydate.setDayFlag(3);
                        } else if (dateFreeze == 2) {//current day
                            appointmentSlotsBydate.setDayFlag(2);
                        }
                        appointmentSlotsBydate.setDayString(mDay);

                        for (Integer a = 1; a <= 100; a++) {
                            AppointmentSlotsWithPatient appointmentSlotsWithPatient = new AppointmentSlotsWithPatient();
                            if (a == 1) {
                                appointmentSlotsWithPatient.setSlotTime(mFromTime);
                                if (dateFreeze != 2) {
                                    appointmentSlotsWithPatient.setIsTimeFreeze(dateFreeze);
                                } else {
                                    appointmentSlotsWithPatient.setIsTimeFreeze(getFreezeTime(mFromTime));
                                }
                                appointmentSlotsWithPatients.add(appointmentSlotsWithPatient);
                            } else {
                                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                                Date d = df.parse(mFromTime);
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(d);
                                cal.add(Calendar.MINUTE, mInterval);
                                String newTime = df.format(cal.getTime());
                                Date d1 = df.parse(newTime);
                                Date d2 = df.parse(mToTime);
                                if (Objects.equals(newTime, mToTime) || d2.before(d1)) {
                                    break;
                                } else {
                                    appointmentSlotsWithPatient.setSlotTime(String.valueOf(newTime));
                                    if (dateFreeze != 2) {
                                        appointmentSlotsWithPatient.setIsTimeFreeze(dateFreeze);
                                    } else {
                                        appointmentSlotsWithPatient.setIsTimeFreeze(getFreezeTime(newTime));
                                    }
                                    appointmentSlotsWithPatients.add(appointmentSlotsWithPatient);
                                    mFromTime = newTime;
                                }
                            }
                        }
                    } else {
                        i = 100;
                    }
                }
                appointmentSlotsBydate.setAppointmentSlotsWithPatients(appointmentSlotsWithPatients);
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return appointmentSlotsBydate;
    }

    private AppointmentSlotsBydate getSlots(Integer resourceRid, String dayS, Integer dayI, Date selectedDate, Integer entityRid) throws ParseException {
        AppointmentSlotsBydate appointmentSlotsBydate = new AppointmentSlotsBydate();
        String FromTime = null, ToTime = null;
//        List<FromTime> ftList = new ArrayList<FromTime>();
        ArrayList<String> fTimeList = new ArrayList<>();
        ArrayList<String> tTimeList = new ArrayList<>();
        Integer interval = null;
        int holidayFlag = 0;
        String holidayName = null;
        SimpleDateFormat df = new SimpleDateFormat("DD:MM:YYYY");
        List<Resource> resources = masterService.getResourceByID(resourceRid);
        HolidayMaster holidayMaster = masterService.getHohidaysByEntityIDandDate(entityRid, selectedDate);
        if (holidayMaster != null) {
            if (holidayMaster.getHmValue() != 1) {
                if (CollectionUtils.isNotEmpty(resources)) {
                    interval = resources.get(0).getResSchedInterval();
                    if (CollectionUtils.isNotEmpty(resources.get(0).getResourceWorkingHours())) {
                        for (ResourceWorkingHours resourceWorkingHours : resources.get(0).getResourceWorkingHours()) {
                            if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                                FromTime = resourceWorkingHours.getWhFromTime();
                                ToTime = resourceWorkingHours.getWhToTime();

                                Time fTime = Time.valueOf(FromTime);
                                Time tTime = Time.valueOf(ToTime);
                                Time holidayFTime = Time.valueOf(holidayMaster.getHmWorkingFromTime());
                                Time holidayTTime = Time.valueOf(holidayMaster.getHmWorkingToTime());
                                if (fTime.before(holidayFTime)) {
                                    fTimeList.add(FromTime);
                                    tTimeList.add(String.valueOf(holidayFTime));
                                }
                                if (tTime.after(holidayTTime)) {
                                    fTimeList.add(String.valueOf(holidayTTime));
                                    tTimeList.add(ToTime);
                                }
                            }
                        }
                    }
                }
            } else {
                holidayFlag = 1;
                holidayName = holidayMaster.getHmHolidayName();
            }
        } else {
            if (CollectionUtils.isNotEmpty(resources)) {
                interval = resources.get(0).getResSchedInterval();
                if (CollectionUtils.isNotEmpty(resources.get(0).getResourceWorkingHours())) {
                    for (ResourceWorkingHours resourceWorkingHours : resources.get(0).getResourceWorkingHours()) {
                        if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                            FromTime = resourceWorkingHours.getWhFromTime();
                            ToTime = resourceWorkingHours.getWhToTime();
                            fTimeList.add(FromTime);
                            tTimeList.add(ToTime);
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(fTimeList) && CollectionUtils.isNotEmpty(tTimeList)) {
//            appointmentSlotsBydate = getAppointmentDeatails(getAppointmentSlotsBydate(FromTime, ToTime, selectedDate, dayS, interval, 1), selectedDate, resourceRid);
            appointmentSlotsBydate = getAppointmentDeatails(getAppointmentSlotsBydate(fTimeList, tTimeList, selectedDate, dayS, interval, 1), selectedDate, resourceRid);
        } else {
            appointmentSlotsBydate.setDateS(DateUtil.convertDateToString(selectedDate));
            appointmentSlotsBydate.setDayString(dayS);
            appointmentSlotsBydate.setDayFlag(0);
            if (holidayFlag == 1) {
                appointmentSlotsBydate.setDayIsHoliday(1);
                appointmentSlotsBydate.setHolidayName(holidayName);
            }
        }
        return appointmentSlotsBydate;
    }

    private AppointmentSlotsBydate getAppointmentDeatails(AppointmentSlotsBydate appointmentSlotsBydate, Date dateS, Integer resourceRid) {
        List<Appointment> appointments = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        appointments = getAppointmentByDate(dateS);
        if (CollectionUtils.isNotEmpty(appointments)) {
            for (Appointment appointment : appointments) {
                if (appointment.getApptStatus() != -1) {
                    if (CollectionUtils.isNotEmpty(appointment.getAppointmentResourceMap())) {
                        for (AppointmentResourceMap appointmentResourceMap : appointment.getAppointmentResourceMap()) {
                            if (Objects.equals(resourceRid, appointmentResourceMap.getArmResourceRid())) {
                                for (AppointmentSlotsWithPatient appointmentSlotsWithPatient : appointmentSlotsBydate.getAppointmentSlotsWithPatients()) {
                                    String aTime = df.format(Time.valueOf(appointment.getApptFromTime()));
                                    if (Objects.equals(aTime, appointmentSlotsWithPatient.getSlotTime())) {
                                        Visit visit = getVisitByPatientRid(appointment.getApptPatientRID(), dateS);
                                        if (visit != null) {
                                            appointmentSlotsWithPatient.setPatientVisitRid(visit.getId());
                                        }
                                        appointmentSlotsWithPatient.setPatientAge(appointment.getApptPatientAge());
                                        appointmentSlotsWithPatient.setPatientGender(appointment.getApptPatientGenderIndex());
                                        appointmentSlotsWithPatient.setDateS(DateUtil.convertDateToString(dateS));
                                        appointmentSlotsWithPatient.setDoctorRid(resourceRid);
                                        appointmentSlotsWithPatient.setAppointmentRid(appointment.getId());
                                        appointmentSlotsWithPatient.setPatientRid(appointment.getApptPatientRID());
                                        appointmentSlotsWithPatient.setPatientName(appointment.getApptPatientName());
                                        appointmentSlotsWithPatient.setPatientMrn(appointment.getApptPatientMrn());
                                        appointmentSlotsWithPatient.setPatientMobileNo(appointment.getApptPatientPhone());
                                        appointmentSlotsWithPatient.setApptStatus(appointment.getApptStatus());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return appointmentSlotsBydate;
    }

    private List<Appointment> getAppointmentByDate(Date dateS) {
        AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("apptFromDate", dateS));
        appointmentSearchRequest.setSearchCriterionList(searchCriterionList);
        return getAppointment(appointmentSearchRequest, true);
    }

    private List<Appointment> getAppointmentById(Integer appId) {
        AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("id", appId));
        appointmentSearchRequest.setSearchCriterionList(searchCriterionList);
        return getAppointment(appointmentSearchRequest, true);
    }

    @Override
    public Integer saveFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap) throws DcometDAOException {
        try {
            FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData = clinicalAdapter.convertFreeConsultationPatientDoctorMapToFreeConsultationPatientDoctorMapData(freeConsultationPatientDoctorMap);
            clinicalDAO.saveFreeConsultationPatientDoctorMap(freeConsultationPatientDoctorMapData);
            return freeConsultationPatientDoctorMapData.getId();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<FreeConsultationPatientDoctorMap> getFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest) throws DcometServiceException {
        List<FreeConsultationPatientDoctorMap> result = new ArrayList<>();
        try {
            List<FreeConsultationPatientDoctorMapData> resultData = clinicalDAO.getFreeConsultationPatientDoctorMap(freeConsultationPatientDoctorMapSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = clinicalAdapter.convertFreeConsultationPatientDoctorMapDataToFreeConsultationPatientDoctorMap(resultData);
                List<FreeConsultationCategory> freeConsultationCategorys = masterService.getFreeConsultationCategoryByResourceRid(resultData.get(0).getFcDoctorRid());
                if (CollectionUtils.isNotEmpty(freeConsultationCategorys)) {
                    List<FreeConsultationTransition> freeConsultationTransitions = masterService.getFreeConsultationTransitionByCatRid(freeConsultationCategorys.get(0).getFccCategoryRid());
                    if (CollectionUtils.isNotEmpty(freeConsultationTransitions)) {
                        for (FreeConsultationTransition freeConsultationTransition : freeConsultationTransitions) {
                            if (Objects.equals(result.get(0).getFcCurrentState(), freeConsultationTransition.getFctFromState())) {
                                result.get(0).setFcServiceRid(freeConsultationTransition.getFctServiceRid());
                            }
                        }
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
        return result;
    }

    private Integer getFcNextState(Integer currentState, FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap) throws ParseException {
        Integer NextState = 0;
        boolean check = true;
        FreeConsultationCategory freeConsultationCategory = new FreeConsultationCategory();
        List<FreeConsultationTransition> freeConsultationTransitions = new ArrayList<>();
        freeConsultationCategory = masterService.getFreeConsultationCategoryByResourceRid(freeConsultationPatientDoctorMap.getFcDoctorRid()).get(0);
        int catRid = freeConsultationCategory.getFccCategoryRid();
        freeConsultationTransitions = masterService.getFreeConsultationTransitionByCatRid(catRid);
        for (FreeConsultationTransition freeConsultationTransition : freeConsultationTransitions) {
            if (Objects.equals(freeConsultationTransition.getFctFromState(), currentState)) {
                NextState = freeConsultationTransition.getFctToState();
                check = false;
            }
        }
        if (check) {
            NextState = 0;
        }
        return NextState;
    }

    private FreeConsultationPatientDoctorMap getFcNextSservice(Integer currentState, FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData) throws ParseException {
        Integer serviceRid = 0;
        FreeConsultationCategory freeConsultationCategory = new FreeConsultationCategory();
        List<FreeConsultationTransition> freeConsultationTransitions = new ArrayList<>();
        FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap = new FreeConsultationPatientDoctorMap();
        List<FreeConsultationCategory> freeConsultationCategorys = new ArrayList<>();
        freeConsultationCategorys = masterService.getFreeConsultationCategoryByResourceRid(freeConsultationPatientDoctorMapData.getFcDoctorRid());
        if (CollectionUtils.isNotEmpty(freeConsultationCategorys)) {
            FreeConsultationCondition consultationCondition = masterService.getFreeConsultationConditionByRid(freeConsultationCategorys.get(0).getFccConditionRid()).get(0);
            int maxD = consultationCondition.getFcConditionMaximum();
            int catRid = freeConsultationCategorys.get(0).getFccCategoryRid();
            freeConsultationPatientDoctorMapData.getFcCurrentState();
            Date cDate = Calendar.getInstance().getTime();
            freeConsultationPatientDoctorMapData.getFcLastPaidVisitDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String input = String.valueOf(freeConsultationPatientDoctorMapData.getFcLastPaidVisitDate());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dateFormat.parse(input));
            cal1.add(Calendar.DAY_OF_YEAR, maxD);
            Date dateMax = cal1.getTime();
            boolean check = true;
            if (cDate.before(dateMax)) {
                freeConsultationTransitions = masterService.getFreeConsultationTransitionByCatRid(catRid);
                for (FreeConsultationTransition freeConsultationTransition : freeConsultationTransitions) {
                    if (Objects.equals(freeConsultationTransition.getFctFromState(), currentState)) {
                        freeConsultationPatientDoctorMap.setFcCurrentState(freeConsultationTransition.getFctToState());
                        freeConsultationPatientDoctorMap.setFcServiceRid(freeConsultationTransition.getFctServiceRid());
                        check = false;
                    }
                }
                if (check) {
                    freeConsultationPatientDoctorMap.setFcCurrentState(0);
                    freeConsultationPatientDoctorMap.setFcServiceRid(0);
                }
            } else {
                freeConsultationPatientDoctorMap.setFcCurrentState(0);
                freeConsultationPatientDoctorMap.setFcServiceRid(0);
            }
        }
        return freeConsultationPatientDoctorMap;
    }

    private FreeConsultationPatientDoctorMap getFreeConsultationPatientDoctorMapById(Integer id) throws DcometServiceException {
        FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest = new FreeConsultationPatientDoctorMapSearchRequest();
        freeConsultationPatientDoctorMapSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<FreeConsultationPatientDoctorMap> result = getFreeConsultationPatientDoctorMap(freeConsultationPatientDoctorMapSearchRequest);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    public Integer saveFreeConsultationPatientDoctorMap(Visit visit) throws DcometServiceException {
        int nState = 1;
        int PState = 0;
        try {
            FreeConsultationPatientDoctorMap patientDoctorMap = new FreeConsultationPatientDoctorMap();
            patientDoctorMap = visit.getConsultationPatientDoctorMap();
            patientDoctorMap.setCurrentObject(visit);
            if (visit.getConsultationPatientDoctorMap() != null) {
                int previousState = patientDoctorMap.getFcCurrentState();
                if (patientDoctorMap.getId() != null) {
                    FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMapDb = getFreeConsultationPatientDoctorMapById(patientDoctorMap.getId());
                    Integer nextState = getFcNextState(patientDoctorMap.getFcCurrentState(), freeConsultationPatientDoctorMapDb);
                    if (nextState == 0) {
                        patientDoctorMap.setFcCurrentState(nState);
                        patientDoctorMap.setFcPreviousState(PState);
                        patientDoctorMap.setFcLastPaidVisitDate(visit.getVisDate());
                        patientDoctorMap.setFcLastVisitDate(visit.getVisDate());
                    } else {
                        if (nextState == 1) {
                            patientDoctorMap.setFcLastPaidVisitDate(visit.getVisDate());
                        } else {
                            patientDoctorMap.setFcLastPaidVisitDate(freeConsultationPatientDoctorMapDb.getFcLastPaidVisitDate());
                        }
                        patientDoctorMap.setFcCurrentState(nextState);
                        patientDoctorMap.setFcPreviousState(previousState);
                    }
                    patientDoctorMap.setFcLastVisitDate(visit.getVisDate());
                    saveFreeConsultationPatientDoctorMap(patientDoctorMap);
                } else {
                    List<FreeConsultationCategory> consultationCategorys = masterService.getFreeConsultationCategoryByResourceRid(visit.getVisConsDocRid());
                    if (CollectionUtils.isNotEmpty(consultationCategorys)) {
                        patientDoctorMap.setFcCurrentState(nState);
                        patientDoctorMap.setFcPreviousState(PState);
                        patientDoctorMap.setFcLastVisitDate(visit.getVisDate());
                        patientDoctorMap.setFcLastPaidVisitDate(visit.getVisDate());
                        saveFreeConsultationPatientDoctorMap(patientDoctorMap);
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
        return null;
    }

    @Override
    public BedChargeVo getBedCharge(Integer visitRid, Integer entityRid) throws DcometServiceException {
        String code = "BED_CHARGE";
        SysParam sysParam = dataDictionaryService.getSysParam(code, entityRid);
        BedChargeVo bedChargeVo = new BedChargeVo();
        if (sysParam != null) {
            ServiceMaster serviceMaster = masterService.getServiceMasterByCode(sysParam.getParamValue(), entityRid);
            Visit vist = getVisitWithChild(visitRid);
            if (vist.getBedOccupancy() != null) {
                int bedrid = vist.getBedOccupancy().get(0).getBocBedRid();
                BedMasterSearchRequest bedMasterSearchRequest = new BedMasterSearchRequest();
                List<Criterion> searchCriterionList = new ArrayList<>();
                searchCriterionList.add(Restrictions.eq("bedRid", bedrid));
                bedMasterSearchRequest.setSearchCriterionList(searchCriterionList);
                List<BedMaster> bedMasters = bedManagementService.getBedMaster(bedMasterSearchRequest);
                if (CollectionUtils.isNotEmpty(bedMasters)) {
                    BedGroupMSearchRequest bedGroupMSearchRequest = new BedGroupMSearchRequest();
                    List<Criterion> searchCriterionList1 = new ArrayList<>();
                    searchCriterionList.add(Restrictions.eq("bgmRid", bedMasters.get(0).getBedBgmRid()));
                    bedGroupMSearchRequest.setSearchCriterionList(searchCriterionList1);
                    List<BedGroupM> bedGroupMs = bedManagementService.getBedGroupMaster(bedGroupMSearchRequest, false);
                    if (CollectionUtils.isNotEmpty(bedGroupMs)) {
                        for (ServicePriceType servicePriceType : serviceMaster.getServicePriceTypeList()) {
                            if (Objects.equals(servicePriceType.getSrPriceType(), bedGroupMs.get(0).getBgmBedPriceTypeIndex())) {
                                Float bedPrice = servicePriceType.getSrPriceAmount();
                                String dateStart = vist.getCreatedDateTime();
                                String dateStop = DateUtil.convertCalendarToString(Calendar.getInstance());
                                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                Date d1 = null;
                                Date d2 = null;
                                try {
                                    d1 = DateUtil.convertStringToDate(dateStart);
                                    d2 = DateUtil.convertStringToDate(dateStop);
                                    int diff = d2.getDay() - d1.getDay();
                                    diff = diff == 0 ? 1 : diff;
                                    Integer gprice = Math.round(bedPrice);
                                    Integer grossAmt = gprice * diff;
                                    Float gp = Float.valueOf(grossAmt);
                                    bedChargeVo.setBcDaysCount(diff);
                                    bedChargeVo.setBcServicePrice(bedPrice);
                                    bedChargeVo.setBcGrossAmount(gp);
                                    bedChargeVo.setBcServiceName(serviceMaster.getBsName());
                                    bedChargeVo.setBcServiceRid(serviceMaster.getId());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } else {
                bedChargeVo = null;
            }
        }
        return bedChargeVo;
    }

    @Override
    public FreeConsutationVo getFreeConsultationVo(Integer doctorRid, Integer patientRid) throws DcometServiceException {
        FreeConsutationVo freeConsutationVo = new FreeConsutationVo();
        try {
            FreeConsultationPatientDoctorMapSearchRequest patientDoctorMapSearchRequest = new FreeConsultationPatientDoctorMapSearchRequest();

            List<Criterion> searchCriterionList = new ArrayList<>();
            searchCriterionList.add(Restrictions.eq("fcDoctorRid", doctorRid));
            searchCriterionList.add(Restrictions.eq("fcPatientRid", patientRid));
            patientDoctorMapSearchRequest.setSearchCriterionList(searchCriterionList);
            List<FreeConsultationPatientDoctorMapData> resultData = clinicalDAO.getFreeConsultationPatientDoctorMap(patientDoctorMapSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {

                FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap = getFcNextSservice(resultData.get(0).getFcCurrentState(), resultData.get(0));
                if (freeConsultationPatientDoctorMap.getFcServiceRid() != 0) {
                    freeConsutationVo.setFcPatDocMapRid(resultData.get(0).getId());
                    freeConsutationVo.setFcCurrentState(freeConsultationPatientDoctorMap.getFcCurrentState());
                    freeConsutationVo.setFcServiceRid(freeConsultationPatientDoctorMap.getFcServiceRid());
                    ServiceMaster serviceMaster = masterService.getServiceMasterByID(freeConsultationPatientDoctorMap.getFcServiceRid());
                    freeConsutationVo.setFcServiceName(serviceMaster.getBsName());
                    freeConsutationVo.setFcServiceType(serviceMaster.getBsServiceType());
                    freeConsutationVo.setFcServicePrice(Float.valueOf(serviceMaster.getbPrice()));
                    freeConsutationVo.setFcServiceEPrice(Float.valueOf(serviceMaster.getbEprice()));
                } else {
                    List<FreeConsultationCategory> consultationCategorys = masterService.getFreeConsultationCategoryByResourceRid(doctorRid);
                    if (CollectionUtils.isNotEmpty(consultationCategorys)) {
                        Integer catRid = consultationCategorys.get(0).getFccCategoryRid();
                        if (catRid != null && catRid != 0) {
                            List<FreeConsultationTransition> freeConsultationTransitions = masterService.getFreeConsultationTransitionByCatRid(catRid);
                            freeConsutationVo.setFcPatDocMapRid(resultData.get(0).getId());
                            freeConsutationVo.setFcCurrentState(freeConsultationPatientDoctorMap.getFcCurrentState());
                            freeConsutationVo.setFcServiceRid(freeConsultationTransitions.get(0).getFctServiceRid());
                            if (freeConsultationTransitions.get(0).getFctServiceRid() != null) {
                                ServiceMaster serviceMaster = masterService.getServiceMasterByID(freeConsultationTransitions.get(0).getFctServiceRid());
                                freeConsutationVo.setFcServiceName(serviceMaster.getBsName());
                                freeConsutationVo.setFcServiceType(serviceMaster.getBsServiceType());
                                List<ResourceServiceMap> resourceServiceMaps = masterService.getResourceServiceMapByID(freeConsultationTransitions.get(0).getFctServiceRid(), doctorRid);
                                if (CollectionUtils.isNotEmpty(resourceServiceMaps)) {
                                    freeConsutationVo.setFcServicePrice(Float.valueOf(resourceServiceMaps.get(0).getRsmNormalPrice()));
                                    freeConsutationVo.setFcServiceEPrice(Float.valueOf(resourceServiceMaps.get(0).getRsmEmergencyPrice()));
                                } else {
                                    freeConsutationVo.setFcServicePrice(Float.valueOf(serviceMaster.getbPrice()));
                                    freeConsutationVo.setFcServiceEPrice(Float.valueOf(serviceMaster.getbEprice()));
                                }
                            }
                        }
                    }
                }
            } else {
                List<FreeConsultationCategory> consultationCategorys = masterService.getFreeConsultationCategoryByResourceRid(doctorRid);
                if (CollectionUtils.isNotEmpty(consultationCategorys)) {
                    Integer catRid = consultationCategorys.get(0).getFccCategoryRid();
                    if (catRid != null && catRid != 0) {
                        List<FreeConsultationTransition> freeConsultationTransitions = masterService.getFreeConsultationTransitionByCatRid(catRid);
                        freeConsutationVo.setFcServiceRid(freeConsultationTransitions.get(0).getFctServiceRid());
                        if (freeConsultationTransitions.get(0).getFctServiceRid() != null) {
                            ServiceMaster serviceMaster = masterService.getServiceMasterByID(freeConsultationTransitions.get(0).getFctServiceRid());
                            freeConsutationVo.setFcServiceName(serviceMaster.getBsName());
                            freeConsutationVo.setFcServiceType(serviceMaster.getBsServiceType());
                            List<ResourceServiceMap> resourceServiceMaps = masterService.getResourceServiceMapByID(freeConsultationTransitions.get(0).getFctServiceRid(), doctorRid);
                            if (CollectionUtils.isNotEmpty(resourceServiceMaps)) {
                                freeConsutationVo.setFcServicePrice(Float.valueOf(resourceServiceMaps.get(0).getRsmNormalPrice()));
                                freeConsutationVo.setFcServiceEPrice(Float.valueOf(resourceServiceMaps.get(0).getRsmEmergencyPrice()));
                            } else {
                                freeConsutationVo.setFcServicePrice(Float.valueOf(serviceMaster.getbPrice()));
                                freeConsutationVo.setFcServiceEPrice(Float.valueOf(serviceMaster.getbEprice()));
                            }
                        }
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
        return freeConsutationVo;
    }

    @Override
    public List<Visit> getIpVisit(VisitSearchRequest visitSearchRequest, boolean includeChild) throws DcometServiceException {
        List<Visit> result = null;
        try {
            List<VisitData> listData = clinicalDAO.getVisits(visitSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertVisitDataToVisit(listData);
                if (includeChild) {
                    for (Visit visit : result) {
                        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("id", visit.getVisPatRid()));
                        patientSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<Patient> patientResult = getPatient(patientSearchRequest, false);
                        if (patientResult != null && !patientResult.isEmpty()) {
                            visit.setPatient(patientResult);
                        }
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
        return result;
    }

    @Override
    public List<AdmissionDetails> getAdmissionDetails(AdmissionDetailsSearchRequest appointmentBookingSearchRequest)
            throws DcometServiceException {
        List<AdmissionDetails> result = null;
        try {
            List<AdmissionDetailsData> listData = clinicalDAO.getAdmissionDetails(appointmentBookingSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = clinicalAdapter.convertAdmissionDetailsDataToAdmissionDetails(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAdmissionDetails(AdmissionDetails admissionDetail) throws DcometServiceException {
        try {
            AdmissionDetailsData admissionDetailsData = clinicalAdapter.convertAdmissionDetailsToAdmissionDetailsData(admissionDetail);
            clinicalDAO.saveAdmissionDetails(admissionDetailsData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<Object[]> getAllVisitReport(Integer entityRid, String fromDate, String toDate, String visitType) throws DcometServiceException {
        return clinicalDAO.getAllVisitReport(entityRid, fromDate, toDate, visitType);
    }
    @Override
    public List<Object[]> getNewRegistrationReport(Integer entityRid, String curDate) throws DcometServiceException {
        return clinicalDAO.getNewRegistrationReport(entityRid, curDate);
    }

    @Override
    public List<Object[]> getReviewVisitReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getReviewVisitReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<Object[]> getAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getAppointmentReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<Object[]> getDateWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getDateWiseReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<Object[]> getSpecialityWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getSpecialityWiseReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<Object[]> getCancelledAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getCancelledAppointmentReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<Object[]> getReferralTypeReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getReferralTypeReport(entityRid, fromDate, toDate);
    }

    @Override
    public List<DoctorWiseReport> getDoctorWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        List<DoctorWiseReport> reportList = new ArrayList<>();
        List<DoctorWiseReport> tempReportList = clinicalDAO.getDoctorWiseReport(entityRid, fromDate, toDate);
        TreeMap<String, DoctorWiseReport> treeMapList = new TreeMap<>();
        for (DoctorWiseReport doctorWiseReport : tempReportList) {
            if (treeMapList.containsKey(doctorWiseReport.getDoctorName())) {
                DoctorWiseReport doctorWiseReport1 = treeMapList.get(doctorWiseReport.getDoctorName());
                doctorWiseReport.setAgeGreaterThan20(doctorWiseReport1.getAgeGreaterThan20() + doctorWiseReport.getAgeGreaterThan20());
                doctorWiseReport.setAgeLessthan1(doctorWiseReport1.getAgeLessthan1() + doctorWiseReport.getAgeLessthan1());
                doctorWiseReport.setAgeUpto4(doctorWiseReport1.getAgeUpto4() + doctorWiseReport.getAgeUpto4());
                doctorWiseReport.setAgeTotalCount(doctorWiseReport1.getAgeTotalCount() + doctorWiseReport.getAgeTotalCount());
                doctorWiseReport.setAgeUpto19(doctorWiseReport1.getAgeUpto19() + doctorWiseReport.getAgeUpto19());
                treeMapList.put(doctorWiseReport.getDoctorName(), doctorWiseReport);
            } else {
                treeMapList.put(doctorWiseReport.getDoctorName(), doctorWiseReport);
            }
        }
        for (Map.Entry<String, DoctorWiseReport> list : treeMapList.entrySet()) {
            reportList.add(list.getValue());
        }
        return reportList;
    }

    @Override
    public List<Object[]> getAppointmentWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException {
        return clinicalDAO.getAppointmentWiseReport(entityRid, fromDate, toDate);
    }
}
