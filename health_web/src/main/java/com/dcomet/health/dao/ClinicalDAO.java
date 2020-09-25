package com.dcomet.health.dao;

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
//import com.dcomet.health.dao.data.FreeConsultationCategoryData;
//import com.dcomet.health.dao.data.FreeConsultationConditionData;
import com.dcomet.health.dao.data.FreeConsultationPatientDoctorMapData;
//import com.dcomet.health.dao.data.FreeConsultationTransitionData;
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
import java.util.List;

/**
 *
 * @author KS
 */
public interface ClinicalDAO {

    //---------------AdmissionDetails-------------

    public List<AdmissionDetailsData> getAdmissionDetails(AdmissionDetailsSearchRequest admissionDetailsSearchRequest) throws DcometDAOException;

    public void saveAdmissionDetails(AdmissionDetailsData admissionDetailsData) throws DcometDAOException;

    public List<Object[]> getAllVisitReport(Integer entityRid, String fromDate, String toDate, String visitType) throws DcometDAOException;

    public List<AppointmentData> getAppointment(AppointmentSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveAppointment(AppointmentData advanceDetails) throws DcometDAOException;

//---------------AppointmentBooking-------------
    public List<AppointmentBookingData> getAppointmentBooking(AppointmentBookingSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveAppointmentBooking(List<AppointmentBookingData> appointmentBooking) throws DcometDAOException;

//---------------AppointmentReservation-------------
    public List<AppointmentReservationData> getAppointmentReservation(AppointmentReservationSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveAppointmentReservation(List<AppointmentReservationData> appointmentReservation) throws DcometDAOException;

//---------------AppointmentResourceMap-------------
    public List<AppointmentResourceMapData> getAppointmentResourceMap(AppointmentResourceMapSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveAppointmentResourceMap(List<AppointmentResourceMapData> appointmentResourceMapData) throws DcometDAOException;

//---------------AppointmentToken-------------
    public List<AppointmentTokenData> getAppointmentToken(AppointmentTokenSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveAppointmentToken(List<AppointmentTokenData> appointmentToken) throws DcometDAOException;

    public List<PatientData> getPatient(PatientSearchRequest patientSearchRequest) throws DcometDAOException;

    public void savePatient(PatientData patientData) throws DcometDAOException;

    //---------KinDetails ------------
    public List<KinData> getKinDetails(PatientSearchRequest patientSearchRequest) throws DcometDAOException;

    public void saveKinDetails(List<KinData> kinDataList) throws DcometDAOException;

    //visit
    public List<VisitData> getVisits(VisitSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveVisit(VisitData visitData) throws DcometDAOException;

    //-----Complaints----
    public List<ComplaintsData> getComplaints(ComplaintsSearchRequest complaintsSearchRequest) throws DcometDAOException;

    public void saveComplaints(ComplaintsData complaints) throws DcometDAOException;

    public void saveComplaintsList(Integer entityRid, List<ComplaintsData> complaintsList) throws DcometDAOException;

    //-----VisitTemplate----
    public List<VisitTemplateData> getVisitTemplate(VisitTemplateSearchRequest visitTemplateSearchRequest) throws DcometDAOException;

    public void saveVisitTemplate(VisitTemplateData visitTemplate) throws DcometDAOException;

    //-----VisitVitals----
    public List<VisitVitalsData> getVisitVitals(VisitVitalsSearchRequest visitVitalsSearchRequest) throws DcometDAOException;

    public void saveVisitVitals(VisitVitalsData visitVitals) throws DcometDAOException;

    //-----VisitPlan----
    public List<VisitPlanData> getVisitPlan(VisitPlanSearchRequest visitPlanSearchRequest) throws DcometDAOException;

    public void saveVisitPlan(VisitPlanData visitPlan) throws DcometDAOException;

    //----History
    public void saveHistoryList(Integer entityRid, List<HistoryData> historyDatas) throws DcometDAOException;

    public List<HistoryData> getHistory(HistorySearchRequest historySearchRequest) throws DcometDAOException;

    public PatientImageData savePatientPhoto(PatientImageData file) throws DcometDAOException;

    public PatientImageData getPatPhoto(Integer imgRid, Integer patRid) throws DcometDAOException;

    public PatientImageData getPatPhotoByImgPatRid(Integer imgPatRid) throws DcometDAOException;

    public PatientImageData getPatPhotoByImgRid(Integer imgRid) throws DcometDAOException;

    public void savePatImage(PatientImageData fileData) throws DcometDAOException;

    public void deletePatImage(PatientImageData fileData) throws DcometDAOException;

    //----------Free consultation------------- 
    public List<FreeConsultationPatientDoctorMapData> getFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest) throws DcometDAOException;

    public void saveFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData) throws DcometDAOException;

//    //--------ProcedureRequestH---------------
//    public List<ProcedureRequestHData> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;
//
//    public void saveProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometDAOException;
//
//    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctorData> procedureAttendDoctorDataList) throws DcometDAOException;
//
//    public void saveProcedureNurse(List<ProcedureNurseData> procedureNurseDataList) throws DcometDAOException;
//
//    public void saveProcedureTechnician(List<ProcedureTechnicianData> procedureTechnicianDataList) throws DcometDAOException;
//
//    public void saveProcedureAnesthesist(List<ProcedureAnesthesistData> procedureAnesthesistDataList) throws DcometDAOException;
//
//    public List<ProcedureAttendDoctorData> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;
//
//    public List<ProcedureNurseData> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;
//
//    public List<ProcedureTechnicianData> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;
//
//    public List<ProcedureAnesthesistData> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;
    public List<Object[]> getNewRegistrationReport(Integer entityRid, String curDate) throws DcometDAOException;

    public List<Object[]> getReviewVisitReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getDateWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getSpecialityWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getCancelledAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getReferralTypeReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    /**
     * @param entityRid
     * @param fromDate
     * @param toDate
     * @return
     * @throws DcometDAOException
     */
    public List<DoctorWiseReport> getDoctorWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

    public List<Object[]> getAppointmentWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometDAOException;

}
