package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.ClinicalDAO;
import com.dcomet.health.dao.data.ComplaintsData;
import com.dcomet.health.dao.data.VisitPlanData;
import com.dcomet.health.dao.data.VisitTemplateData;
import com.dcomet.health.dao.data.VisitVitalsData;
import com.dcomet.health.domain.ComplaintsSearchRequest;
import com.dcomet.health.domain.VisitPlanSearchRequest;
import com.dcomet.health.domain.VisitTemplateSearchRequest;
import com.dcomet.health.domain.VisitVitalsSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
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
import com.dcomet.health.domain.AdmissionDetailsSearchRequest;
import com.dcomet.health.domain.AppointmentBookingSearchRequest;
import com.dcomet.health.domain.AppointmentReservationSearchRequest;
import com.dcomet.health.domain.AppointmentResourceMapSearchRequest;
import com.dcomet.health.domain.AppointmentSearchRequest;
import com.dcomet.health.domain.AppointmentTokenSearchRequest;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMapSearchRequest;
import com.dcomet.health.domain.HistorySearchRequest;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.VisitSearchRequest;
import com.dcomet.health.domain.reportmodel.DoctorWiseReport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KS
 */
@Repository("clinicalDAO")
public class ClinicalDAOImpl extends HibernateDaoSupport implements ClinicalDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AppointmentData> getAppointment(
            AppointmentSearchRequest appointmentSearchRequest)
            throws DcometDAOException {
        List<AppointmentData> appointmentDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = appointmentSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AppointmentData.class);
            defaultSortOrder.createSortCriteria(
                    appointmentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            appointmentDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return appointmentDataList;
    }

    @Override
    public void saveAppointment(AppointmentData appointmentData)
            throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(appointmentData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<AppointmentBookingData> getAppointmentBooking(
            AppointmentBookingSearchRequest appointmentBookingSearchRequest)
            throws DcometDAOException {

        List<AppointmentBookingData> appointmentBookingDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = appointmentBookingSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AppointmentBookingData.class);
            defaultSortOrder.createSortCriteria(
                    appointmentBookingSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            appointmentBookingDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return appointmentBookingDataList;
    }

    @Override
    public void saveAppointmentBooking(List<AppointmentBookingData> appointmentBookingDatalist) throws DcometDAOException {
        try {
            for (AppointmentBookingData appointmentBookingData : appointmentBookingDatalist) {
                getSessionFactory().getCurrentSession().saveOrUpdate(appointmentBookingData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<AppointmentReservationData> getAppointmentReservation(
            AppointmentReservationSearchRequest appointmentReservationSearchRequest)
            throws DcometDAOException {
        List<AppointmentReservationData> appointmentReservationDataList = new ArrayList<AppointmentReservationData>();
        try {
            List<Criterion> criteriaList = appointmentReservationSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AppointmentBookingData.class);
            defaultSortOrder.createSortCriteria(
                    appointmentReservationSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            appointmentReservationDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return appointmentReservationDataList;
    }

    @Override
    public void saveAppointmentReservation(List<AppointmentReservationData> appointmentReservationDataList)
            throws DcometDAOException {
        try {
            for (AppointmentReservationData appointmentReservationData : appointmentReservationDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(appointmentReservationData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<AppointmentResourceMapData> getAppointmentResourceMap(
            AppointmentResourceMapSearchRequest appointmentResourceMapSearchRequest)
            throws DcometDAOException {

        List<AppointmentResourceMapData> appointmentResourceMapDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = appointmentResourceMapSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AppointmentResourceMapData.class);
            defaultSortOrder.createSortCriteria(
                    appointmentResourceMapSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            appointmentResourceMapDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return appointmentResourceMapDataList;

    }

    @Override
    public void saveAppointmentResourceMap(List<AppointmentResourceMapData> appointmentResourceMapDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (AppointmentResourceMapData appointmentResourceMapData : appointmentResourceMapDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(appointmentResourceMapData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<AppointmentTokenData> getAppointmentToken(AppointmentTokenSearchRequest appointmentTokenSearchRequest) throws DcometDAOException {
        List<AppointmentTokenData> appointmentTokenDataList = new ArrayList<AppointmentTokenData>();
        try {
            List<Criterion> criteriaList = appointmentTokenSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AppointmentTokenData.class);
            defaultSortOrder.createSortCriteria(
                    appointmentTokenSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            appointmentTokenDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return appointmentTokenDataList;
    }

    @Override
    public void saveAppointmentToken(List<AppointmentTokenData> appointmentTokenDataList) throws DcometDAOException {
        try {
            for (AppointmentTokenData appointmentTokenData : appointmentTokenDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(appointmentTokenData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PatientData> getPatient(PatientSearchRequest patientSearchRequest) throws DcometDAOException {
        List<PatientData> patientDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = patientSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(PatientData.class);
            defaultSortOrder.createSortCriteria(
                    patientSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            patientDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return patientDataList;
    }

    @Override
    public void savePatient(PatientData patientData) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(patientData);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VisitData> getVisits(VisitSearchRequest visitSearchRequest) throws DcometDAOException {
        List<VisitData> visitDataList = new ArrayList<>();

        try {
            List<Criterion> criteriaList = visitSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(VisitData.class);
            defaultSortOrder.createSortCriteria(visitSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            visitDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return visitDataList;
    }

    @Override
    public void saveVisit(VisitData visitData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(visitData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }

    }

    @Override
    public List<ComplaintsData> getComplaints(ComplaintsSearchRequest complaintsSearchRequest) throws DcometDAOException {
        List<ComplaintsData> complaintsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = complaintsSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(ComplaintsData.class);
            defaultSortOrder.createSortCriteria(
                    complaintsSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            complaintsDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return complaintsDataList;
    }

    @Override
    public void saveComplaints(ComplaintsData complaints) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(complaints);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<VisitTemplateData> getVisitTemplate(VisitTemplateSearchRequest visitTemplateSearchRequest) throws DcometDAOException {
        List<VisitTemplateData> visitTemplateDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = visitTemplateSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(VisitTemplateData.class);
            defaultSortOrder.createSortCriteria(
                    visitTemplateSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            visitTemplateDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return visitTemplateDataList;
    }

    @Override
    public void saveVisitTemplate(VisitTemplateData visitTemplate) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(visitTemplate);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<VisitVitalsData> getVisitVitals(VisitVitalsSearchRequest visitVitalsSearchRequest) throws DcometDAOException {
        List<VisitVitalsData> visitVitalsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = visitVitalsSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(VisitVitalsData.class);
            defaultSortOrder.createSortCriteria(
                    visitVitalsSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            visitVitalsDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return visitVitalsDataList;
    }

    @Override
    public void saveVisitVitals(VisitVitalsData visitVitals) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(visitVitals);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<VisitPlanData> getVisitPlan(VisitPlanSearchRequest visitPlanSearchRequest) throws DcometDAOException {
        List<VisitPlanData> visitPlanDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = visitPlanSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(VisitPlanData.class);
            defaultSortOrder.createSortCriteria(
                    visitPlanSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            visitPlanDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return visitPlanDataList;
    }

    @Override
    public void saveVisitPlan(VisitPlanData visitPlan) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(visitPlan);
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveComplaintsList(Integer entityRid, List<ComplaintsData> complaintsList) throws DcometDAOException {
        try {
            for (ComplaintsData complaintsData : complaintsList) {
                complaintsData.setCplEntityRid(entityRid);
                getSessionFactory().getCurrentSession().saveOrUpdate(complaintsData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveKinDetails(List<KinData> kinDataDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (KinData kinData : kinDataDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(kinData);
            }
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<KinData> getKinDetails(PatientSearchRequest patientSearchRequest) throws DcometDAOException {
        List<KinData> kinDataDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = patientSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(KinData.class);
            defaultSortOrder.createSortCriteria(patientSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            kinDataDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return kinDataDataList;
    }

    @Override
    public void saveHistoryList(Integer entityRid, List<HistoryData> historyDatas) throws DcometDAOException {
        try {
            for (HistoryData historyData : historyDatas) {
                historyData.setHisEntityRid(entityRid);
                getSessionFactory().getCurrentSession().saveOrUpdate(historyData);
            }
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<HistoryData> getHistory(HistorySearchRequest historySearchRequest) throws DcometDAOException {
        List<HistoryData> historyDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = historySearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(HistoryData.class);
            defaultSortOrder.createSortCriteria(historySearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            historyDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return historyDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PatientImageData savePatientPhoto(PatientImageData file) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(file);
            getSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
        return file;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void savePatImage(PatientImageData fileData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(fileData);
            getSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public PatientImageData getPatPhoto(Integer imgRid, Integer imgPatRid) throws DcometDAOException {
        List<PatientImageData> patientImageDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientImageData.class);
            criteria.add(Restrictions.eq("id", imgRid));
            criteria.add(Restrictions.eq("patImgPatRid", imgPatRid));
            criteria.setFetchSize(1);
            patientImageDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(patientImageDataList) ? patientImageDataList.get(0) : null;

    }

    @SuppressWarnings("unchecked")
    @Override
    public PatientImageData getPatPhotoByImgPatRid(Integer imgPatRid) throws DcometDAOException {
        List<PatientImageData> patientImageDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientImageData.class);
            criteria.add(Restrictions.eq("patImgPatRid", imgPatRid));
            criteria.setFetchSize(1);
            patientImageDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(patientImageDataList) ? patientImageDataList.get(0) : null;

    }

    @SuppressWarnings("unchecked")
    @Override
    public PatientImageData getPatPhotoByImgRid(Integer imgRid) throws DcometDAOException {
        List<PatientImageData> patientImageDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientImageData.class);
            criteria.add(Restrictions.eq("id", imgRid));
            criteria.setFetchSize(1);
            patientImageDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(patientImageDataList) ? patientImageDataList.get(0) : null;

    }

    @Override
    public void deletePatImage(PatientImageData fileData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().delete(fileData);
            getSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<FreeConsultationPatientDoctorMapData> getFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest) throws DcometDAOException {
        List<FreeConsultationPatientDoctorMapData> freeConsultationPatientDoctorMapDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = freeConsultationPatientDoctorMapSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(FreeConsultationPatientDoctorMapData.class);
            defaultSortOrder.createSortCriteria(
                    freeConsultationPatientDoctorMapSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            freeConsultationPatientDoctorMapDataList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return freeConsultationPatientDoctorMapDataList;
    }

    @Override
    public void saveFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(freeConsultationPatientDoctorMapData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

//    @Override
//    public List<ProcedureRequestHData> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
//        List<ProcedureRequestHData> procedureRequestHDatas = new ArrayList<>();
//        try {
//            List<Criterion> criteriaList = procedureRequestHSearchRequest
//                    .getSearchCriterionList();
//            Criteria criteria = getSessionFactory().getCurrentSession()
//                    .createCriteria(ProcedureRequestHData.class);
//            defaultSortOrder.createSortCriteria(
//                    procedureRequestHSearchRequest.getSortOrder(), criteria);
//            if (criteriaList != null) {
//                for (Criterion criterion : criteriaList) {
//                    criteria.add(criterion);
//                }
//            }
//            procedureRequestHDatas = criteria.list();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return procedureRequestHDatas;
//    }
//
//    @Override
//    public void saveProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometDAOException {
//        try {
//            getSession().clear();
//            getSessionFactory().getCurrentSession().saveOrUpdate(procedureRequestHData);
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
//
//    @Override
//    public void saveProcedureTechnician(List<ProcedureTechnicianData> procedureTechnicianDataList) throws DcometDAOException {
//        try {
//            getSession().clear();
//            for (ProcedureTechnicianData child1Data : procedureTechnicianDataList) {
//                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
//            }
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
//
//    @Override
//    public void saveProcedureNurse(List<ProcedureNurseData> procedureNurseDataList) throws DcometDAOException {
//        try {
//            getSession().clear();
//            for (ProcedureNurseData child1Data : procedureNurseDataList) {
//                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
//            }
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
//
//    @Override
//    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctorData> procedureAttendDoctorDataList) throws DcometDAOException {
//        try {
//            getSession().clear();
//            for (ProcedureAttendDoctorData child1Data : procedureAttendDoctorDataList) {
//                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
//            }
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
//
//    @Override
//    public void saveProcedureAnesthesist(List<ProcedureAnesthesistData> procedureAnesthesistDataList) throws DcometDAOException {
//        try {
//            getSession().clear();
//            for (ProcedureAnesthesistData child1Data : procedureAnesthesistDataList) {
//                getSessionFactory().getCurrentSession().saveOrUpdate(child1Data);
//            }
//            getSession().flush();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<ProcedureAttendDoctorData> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
//        List<ProcedureAttendDoctorData> child1DataList = new ArrayList<>();
//        try {
//            List<Criterion> criteriaList = procedureRequestHSearchRequest
//                    .getSearchCriterionList();
//            Criteria criteria = getSessionFactory().getCurrentSession()
//                    .createCriteria(ProcedureAttendDoctorData.class);
//            defaultSortOrder.createSortCriteria(
//                    procedureRequestHSearchRequest.getSortOrder(), criteria);
//            if (criteriaList != null) {
//                for (Criterion criterion : criteriaList) {
//                    criteria.add(criterion);
//                }
//            }
//            child1DataList = criteria.list();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return child1DataList;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<ProcedureTechnicianData> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
//        List<ProcedureTechnicianData> child1DataList = new ArrayList<>();
//        try {
//            List<Criterion> criteriaList = procedureRequestHSearchRequest
//                    .getSearchCriterionList();
//            Criteria criteria = getSessionFactory().getCurrentSession()
//                    .createCriteria(ProcedureTechnicianData.class);
//            defaultSortOrder.createSortCriteria(
//                    procedureRequestHSearchRequest.getSortOrder(), criteria);
//            if (criteriaList != null) {
//                for (Criterion criterion : criteriaList) {
//                    criteria.add(criterion);
//                }
//            }
//            child1DataList = criteria.list();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return child1DataList;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<ProcedureNurseData> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
//        List<ProcedureNurseData> child1DataList = new ArrayList<>();
//        try {
//            List<Criterion> criteriaList = procedureRequestHSearchRequest
//                    .getSearchCriterionList();
//            Criteria criteria = getSessionFactory().getCurrentSession()
//                    .createCriteria(ProcedureNurseData.class);
//            defaultSortOrder.createSortCriteria(
//                    procedureRequestHSearchRequest.getSortOrder(), criteria);
//            if (criteriaList != null) {
//                for (Criterion criterion : criteriaList) {
//                    criteria.add(criterion);
//                }
//            }
//            child1DataList = criteria.list();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return child1DataList;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<ProcedureAnesthesistData> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException {
//        List<ProcedureAnesthesistData> child1DataList = new ArrayList<>();
//        try {
//            List<Criterion> criteriaList = procedureRequestHSearchRequest
//                    .getSearchCriterionList();
//            Criteria criteria = getSessionFactory().getCurrentSession()
//                    .createCriteria(ProcedureAnesthesistData.class);
//            defaultSortOrder.createSortCriteria(
//                    procedureRequestHSearchRequest.getSortOrder(), criteria);
//            if (criteriaList != null) {
//                for (Criterion criterion : criteriaList) {
//                    criteria.add(criterion);
//                }
//            }
//            child1DataList = criteria.list();
//        } catch (DataAccessException | HibernateException | IllegalStateException e) {
//            throw new DcometDAOException(e);
//        }
//        return child1DataList;
//    }
    //--------------------------AdmissionDetails----------------------------------------------------------------------------------------------
    @Override
    public List<AdmissionDetailsData> getAdmissionDetails(AdmissionDetailsSearchRequest admissionDetailsSearchRequest) throws DcometDAOException {
        List<AdmissionDetailsData> admissiondetailList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = admissionDetailsSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(AdmissionDetailsData.class);
            defaultSortOrder.createSortCriteria(
                    admissionDetailsSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            admissiondetailList = criteria.list();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
        return admissiondetailList;
    }

    @Override
    public void saveAdmissionDetails(AdmissionDetailsData admissionDetailsData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(admissionDetailsData);
            getSession().flush();
        } catch (DataAccessException | HibernateException | IllegalStateException e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<Object[]> getAllVisitReport(Integer entityRid, String fromDate, String toDate, String visitType) throws DcometDAOException {
        if (visitType.equals("NewVisits")) {
            SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT `pat_mrn_no`, `user_full_name`, `pat_full_name`, (SELECT ddict_value FROM s_ddict WHERE vis_reason_index = ddict_index)AS Reason, (SELECT ddict_value FROM s_ddict WHERE vis_speciality_index = ddict_index)AS Speciality, `staff_name`, (SELECT ddict_value FROM s_ddict WHERE vis_ref_type_index = ddict_index)AS RefType, `vis_ref_name`, `pat_phone_no`, `pat_address`, `pat_country_index`\n"
                    + "FROM `t_visit` INNER JOIN t_patient ON `pat_rid` = vis_pat_rid INNER JOIN s_user ON vis_created_user_rid = `user_rid` INNER JOIN `s_staff` ON `vis_cons_doc_rid` = `staff_rid` WHERE DATE(`vis_created_date_time`) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND `vis_ent_rid` IN (" + entityRid + ") AND `vis_reason_index` NOT IN (27)");
            List<Object[]> newVisit = sqlQuery.list();
            return newVisit;
        } else if (visitType.equals("RepeatVisits")) {
            SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT `pat_mrn_no`, `user_full_name`, `pat_full_name`, (SELECT ddict_value FROM s_ddict WHERE vis_reason_index = ddict_index)AS Reason, (SELECT ddict_value FROM s_ddict WHERE vis_speciality_index = ddict_index)AS Speciality, `staff_name`, (SELECT ddict_value FROM s_ddict WHERE vis_ref_type_index = ddict_index)AS RefType, `vis_ref_name`, `pat_phone_no`, `pat_address`, `pat_country_index`\n"
                    + "FROM `t_visit` INNER JOIN t_patient ON `pat_rid` = vis_pat_rid INNER JOIN s_user ON vis_created_user_rid = `user_rid` INNER JOIN `s_staff` ON `vis_cons_doc_rid` = `staff_rid` WHERE DATE(`vis_created_date_time`) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND `vis_ent_rid` IN (" + entityRid + ") AND `vis_reason_index` IN (27)");
            List<Object[]> repeatVisit = sqlQuery.list();
            return repeatVisit;
        } else if (visitType.equals("TotalVisits")) {
            SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT `pat_mrn_no`, `vis_reason_index`, `pat_full_name`, \n"
                    + "(SELECT ddict_value FROM s_ddict WHERE vis_reason_index = ddict_index)AS Reason, \n"
                    + "(SELECT ddict_value FROM s_ddict WHERE vis_speciality_index = ddict_index)AS Speciality, \n"
                    + "CASE WHEN vis_appt_rid = 0 THEN `staff_name` ELSE res_name END AS DoctorName, \n"
                    + "(SELECT ddict_value FROM s_ddict WHERE vis_ref_type_index = ddict_index)AS RefType, \n"
                    + "`vis_ref_name`, `pat_phone_no`, `pat_address`, `pat_country_index` \n"
                    + "FROM `t_visit` \n"
                    + "INNER JOIN t_patient ON `pat_rid` = vis_pat_rid \n"
                    + "INNER JOIN s_user ON vis_created_user_rid = `user_rid` \n"
                    + "INNER JOIN `s_staff` ON `vis_cons_doc_rid` = `staff_rid`\n"
                    + "INNER JOIN s_resource ON vis_cons_doc_rid = res_rid\n"
                    + "WHERE DATE(`vis_created_date_time`) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND `vis_ent_rid` IN (" + entityRid + ")");
            List<Object[]> totalVisit = sqlQuery.list();
            return totalVisit;
        }
        return null;
    }

    @Override
    public List<Object[]> getNewRegistrationReport(Integer entityRid, String curDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, pat_full_name, vis_ref_name, pat_dob, pat_gender_index, DATE(pat_created_date_time), \n"
                + "pat_phone_no, pat_email_id, pat_address, \n"
                + "(SELECT user_full_name FROM s_user WHERE user_rid = pat_created_user_rid)AS Created_User,\n"
                + "pat_ent_rid \n"
                + "FROM t_patient \n"
                + "INNER JOIN t_visit ON vis_pat_rid = pat_rid AND vis_date = DATE(pat_created_date_time)\n"
                + "WHERE DATE(pat_created_date_time) = '" + curDate + "' AND pat_ent_rid = " + entityRid + ";");
        List<Object[]> newRegReport = sqlQuery.list();
        return newRegReport;
    }

    @Override
    public List<Object[]> getReviewVisitReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, pat_full_name, DATE(pat_created_date_time), pat_dob, pat_gender_index, pat_phone_no, pat_email_id, \n"
                + "(SELECT staff_name FROM s_staff WHERE staff_rid = vis_cons_doc_rid) \n"
                + "FROM t_visit INNER JOIN t_patient ON `pat_rid` = vis_pat_rid \n"
                + "WHERE DATE(vis_created_date_time) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND vis_reason_index = 27 AND vis_ent_rid IN (" + entityRid + ");");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<Object[]> getAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, appt_from_date, appt_from_time, appt_patient_name,\n"
                + "(SELECT res_name FROM s_resource WHERE res_rid = arm_resource_rid)AS DoctorName,\n"
                + "(SELECT user_full_name FROM s_user WHERE appt_created_user_rid = user_rid)AS UserName\n"
                + "FROM t_appointment\n"
                + "LEFT JOIN t_visit ON vis_appt_rid = appt_rid\n"
                + "LEFT JOIN t_patient ON appt_patient_rid = pat_rid\n"
                + "INNER JOIN t_appointment_resource_map ON arm_appt_rid = appt_rid\n"
                + "WHERE DATE(appt_created_datetime) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND appt_ent_rid IN (" + entityRid + ")  AND appt_status NOT IN (-1);"
        );
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<Object[]> getDateWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DATE(vis_created_date_time),\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <12 THEN 1 ELSE 0 END) AS Age_less_than_1,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=12 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=48 THEN 1 ELSE 0 END) AS Age_1to4,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=60 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=228 THEN 1 ELSE 0 END) AS Age_5to19,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=240 THEN 1 ELSE 0 END) AS Age_20_and_above\n"
                + "FROM t_visit INNER JOIN t_patient ON pat_rid = vis_pat_rid \n"
                + "WHERE DATE(vis_created_date_time) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND vis_ent_rid IN (" + entityRid + ") \n"
                + "GROUP BY DATE(vis_created_date_time);");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<Object[]> getSpecialityWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ddict_value,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <12 THEN 1 ELSE 0 END) AS Age_less_than_1,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=12 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=48 THEN 1 ELSE 0 END) AS Age_1to4,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=60 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=228 THEN 1 ELSE 0 END) AS Age_5to19,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=240 THEN 1 ELSE 0 END) AS Age_20_and_above\n"
                + "FROM t_visit, s_ddict, t_patient\n"
                + "WHERE ddict_index = vis_speciality_index AND vis_pat_rid = pat_rid AND vis_ent_rid = pat_ent_rid AND vis_ent_rid IN (" + entityRid + ")\n"
                + "AND DATE(vis_created_date_time) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND vis_reason_index IS NOT NULL \n"
                + "GROUP BY vis_speciality_index;");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<Object[]> getCancelledAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT appt_patient_name, appt_patient_phone, appt_from_date, appt_from_time, \n"
                + "(SELECT user_full_name FROM s_user WHERE appt_mod_user_rid = user_rid)AS UserName, DATE(appt_mod_datetime), TIME(appt_mod_datetime) \n"
                + "FROM t_appointment \n"
                + "LEFT JOIN t_visit ON vis_appt_rid = appt_rid \n"
                + "LEFT JOIN t_patient ON appt_patient_rid = pat_rid \n"
                + "WHERE DATE(appt_created_datetime) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND appt_ent_rid IN (" + entityRid + ") AND appt_status IN(-1);");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<Object[]> getReferralTypeReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ddict_value,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <12 THEN 1 ELSE 0 END) AS Age_less_than_1,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=12 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=48 THEN 1 ELSE 0 END) AS Age_1to4,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=60 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=228 THEN 1 ELSE 0 END) AS Age_5to19,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=240 THEN 1 ELSE 0 END) AS Age_20_and_above\n"
                + "FROM t_visit, s_ddict, t_patient \n"
                + "WHERE ddict_index = vis_ref_type_index AND vis_pat_rid = pat_rid AND DATE(vis_created_date_time) BETWEEN '" + fromDate + "' AND '" + toDate + "' AND vis_ent_rid IN (" + entityRid + ") \n"
                + "GROUP BY vis_ref_type_index;");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

    @Override
    public List<DoctorWiseReport> getDoctorWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        List<DoctorWiseReport> doctorWiseReportList = new ArrayList<>();
        List<DoctorWiseReport> doctorWiseReportListwithOutAppRid = new ArrayList<>();
        List<DoctorWiseReport> doctorWiseReportListwithAppRid = new ArrayList<>();
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery(
                "SELECT staff_name,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <12 THEN 1 ELSE 0 END) AS Age_less_than_1,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=12 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=48 THEN 1 ELSE 0 END) AS Age_1to4,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=60 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=228 THEN 1 ELSE 0 END) AS Age_5to19,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=240 THEN 1 ELSE 0 END) AS Age_20_and_above\n"
                + "FROM t_visit,s_staff, t_patient \n"
                + "WHERE  \n"
                + "vis_cons_doc_rid = staff_rid \n"
                + "AND `staff_entity_rid`=vis_ent_rid\n"
                + "AND pat_rid=vis_pat_rid\n"
                + "AND pat_ent_rid=vis_ent_rid AND vis_date BETWEEN '" + fromDate + "' AND '" + toDate + "' \n"
                + "AND vis_ent_rid IN (" + entityRid + ") AND `vis_appt_rid` IN (0)\n"
                + "GROUP BY vis_cons_doc_rid;");
        List<Object[]> entities = sqlQuery.list();
        for (Object[] entity : entities) {
            doctorWiseReportListwithOutAppRid.add(new DoctorWiseReport(String.valueOf(entity[0]), ((BigDecimal) entity[1]).intValue(), ((BigDecimal) entity[2]).intValue(), ((BigDecimal) entity[3]).intValue(), ((BigDecimal) entity[4]).intValue()));
        }
        SQLQuery sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery("SELECT `res_name`,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <12 THEN 1 ELSE 0 END) AS Age_less_than_1,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=12 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=48 THEN 1 ELSE 0 END) AS Age_1to4,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=60 && PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) <=228 THEN 1 ELSE 0 END) AS Age_5to19,\n"
                + "SUM(CASE WHEN PERIOD_DIFF(DATE_FORMAT(CURDATE(), '%Y%m'), DATE_FORMAT(pat_dob, '%Y%m')) >=240 THEN 1 ELSE 0 END) AS Age_20_and_above\n"
                + "FROM t_visit, `s_resource`, t_patient \n"
                + "WHERE  `res_rid`=vis_cons_doc_rid  \n"
                + "AND `res_ent_rid`=vis_ent_rid\n"
                + "AND pat_rid=vis_pat_rid\n"
                + "AND pat_ent_rid=vis_ent_rid AND vis_date BETWEEN '" + fromDate + "' AND '" + toDate + "'\n"
                + "AND vis_ent_rid IN (" + entityRid + ") AND `vis_appt_rid` NOT IN (0)\n"
                + "GROUP BY vis_cons_doc_rid;");
        List<Object[]> entities2 = sqlQuery1.list();
        for (Object[] entity : entities2) {
            doctorWiseReportListwithAppRid.add(new DoctorWiseReport(String.valueOf(entity[0]), ((BigDecimal) entity[1]).intValue(), ((BigDecimal) entity[2]).intValue(), ((BigDecimal) entity[3]).intValue(), ((BigDecimal) entity[4]).intValue()));
        }
        doctorWiseReportList.addAll(doctorWiseReportListwithOutAppRid);
        doctorWiseReportList.addAll(doctorWiseReportListwithAppRid);
        return doctorWiseReportList;
    }

    @Override
    public List<Object[]> getAppointmentWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT appt_from_date, res_name, ddict_value, COUNT(*)  \n"
                + "FROM t_appointment   \n"
                + "INNER JOIN t_visit ON vis_appt_rid = appt_rid  \n"
                + "INNER JOIN s_ddict ON ddict_index = vis_speciality_index  \n"
                + "INNER JOIN s_resource ON res_rid = vis_cons_doc_rid\n"
                + "WHERE appt_from_date  BETWEEN '" + fromDate + "' AND '" + toDate + "' AND appt_ent_rid IN (" + entityRid + ") AND vis_appt_rid NOT IN (0)\n"
                + "GROUP BY appt_from_date, vis_speciality_index, vis_cons_doc_rid;");
        List<Object[]> val = sqlQuery.list();
        return val;
    }

}
