package com.dcomet.health.service.business;

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
import com.dcomet.health.dao.data.PatientImageData;
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
import com.dcomet.health.domain.FreeConsultationPatientDoctorMap;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMapSearchRequest;
import com.dcomet.health.domain.History;
import com.dcomet.health.domain.HistorySearchRequest;
import com.dcomet.health.domain.Kin;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.VisitSearchRequest;
import com.dcomet.health.domain.reportmodel.DoctorWiseReport;
import com.dcomet.health.vo.AppointmentSlots;
import com.dcomet.health.vo.AppointmentSlotsSearchRequest;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.health.vo.FreeConsutationVo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Adhithya
 */
public interface ClinicalService {

    public Patient getPatient(Integer patientId) throws DcometServiceException;

    public List<Patient> getPatient(PatientSearchRequest patientSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<Object[]> getAllVisitReport(Integer entityRid, String fromDate, String toDate, String visitType) throws DcometServiceException;

    public Integer savePatient(Patient patient) throws DcometDAOException;

    public Visit getVisit(Integer id) throws DcometServiceException;

    public Visit getVisitWithChild(Integer id) throws DcometServiceException;

    public String getCsPrint(VisitSearchRequest visitSearchRequest) throws DcometServiceException;

    public List<Visit> getVisit(VisitSearchRequest visitSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<Visit> getIpVisit(VisitSearchRequest visitSearchRequest, boolean includeChild) throws DcometServiceException;

    public Integer saveVisit(Visit visit) throws DcometServiceException;

    public Integer saveFreeConsultationPatientDoctorMap(Visit visit) throws DcometServiceException;

    public List<Appointment> getAppointment(AppointmentSearchRequest appointmentSearchRequest, boolean includeChilds) throws DcometServiceException;

    public AppointmentSlots getAppointmentSlots(AppointmentSlotsSearchRequest appointmentSlotsSearchRequest, Integer doctorRid, String selectedDay, Date selectedDate, boolean includeChilds) throws DcometServiceException; //Integer dateInterval,

    public void saveAppointment(Appointment appointment, boolean includeChild) throws DcometServiceException;

    public List<AppointmentBooking> getAppointmentBooking(AppointmentBookingSearchRequest appointmentBookingSearchRequest) throws DcometServiceException;

    public void saveAppointmentBooking(List<AppointmentBooking> appointmentBookingList) throws DcometServiceException;

    public List<AppointmentReservation> getAppointmentReservsation(AppointmentReservationSearchRequest appointmentReservationSearchRequest) throws DcometServiceException;

    public void saveAppointmentReservsation(List<AppointmentReservation> appointmentReservationList) throws DcometServiceException;

    public List<AppointmentResourceMap> getAppointmentResourceMap(AppointmentResourceMapSearchRequest appointmentResourcemapSearchRequest) throws DcometServiceException;

    public List<AppointmentToken> getAppointmentToken(AppointmentTokenSearchRequest appointmentTokenSearchRequest) throws DcometServiceException;

    public void saveAppointmentToken(List<AppointmentToken> appointmentToken) throws DcometServiceException;

    public List<Complaints> getComplaints(ComplaintsSearchRequest complaintsSearchRequest) throws DcometServiceException;

    public List<VisitVitals> getVisitVitals(VisitVitalsSearchRequest visitVitalsSearchRequest) throws DcometServiceException;

    public List<VisitPlan> getVisitPlan(VisitPlanSearchRequest visitPlanSearchRequest) throws DcometServiceException;

    public List<VisitTemplate> getVisitTemplate(VisitTemplateSearchRequest visitTemplateSearchRequest) throws DcometServiceException;

    public void saveMyPatient(Visit visit, boolean includeLists) throws DcometServiceException;

    public Integer saveVisitVitals(VisitVitals visitVitals) throws DcometServiceException;

    public Integer saveVisitTemplate(VisitTemplate visitTemplate) throws DcometServiceException;

    public Integer saveComplaints(Integer entityRid, List<Complaints> complaintsList) throws DcometServiceException;

    public Integer saveHistory(Integer entityRid, List<History> historys) throws DcometServiceException;

    public List<History> getHistorys(HistorySearchRequest historySearchRequest) throws DcometServiceException;

    public List<Kin> getKinDetails(PatientSearchRequest patientSearchRequest) throws DcometServiceException;

    public PatientImageData savePatientPhoto(PatientImageData photo) throws DcometServiceException;

    public PatientImageData getPatPhoto(Integer imgRid, Integer imgPatRid) throws DcometServiceException;

    public PatientImageData getPatPhotoByImgPatRid(Integer patRid) throws DcometServiceException;

    public PatientImageData getPatPhotoById(Integer id) throws DcometServiceException;

    public void savePatientImage(PatientImageData fileData) throws DcometServiceException;

    public Visit getVisitByPatientRid(Integer patRid, Date dateS) throws DcometServiceException;

    public List<Visit> getVisitWitPatientAlone(VisitSearchRequest visitSearchRequest, boolean includeChilds) throws DcometServiceException;

    public Visit getActiveIpVisitByPatientRid(Integer patRid) throws DcometServiceException;

    public Visit getActiveVisit(Integer patRid, Date dateS) throws DcometServiceException;

//--------Free consultation--------------------
    public Integer saveFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap) throws DcometDAOException;

    public List<FreeConsultationPatientDoctorMap> getFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapSearchRequest freeConsultationPatientDoctorMapSearchRequest) throws DcometServiceException;

    public FreeConsutationVo getFreeConsultationVo(Integer doctorRid, Integer patientRid) throws DcometServiceException;

    public BedChargeVo getBedCharge(Integer getBedCharge, Integer entityRid) throws DcometServiceException;

    public List<AdmissionDetails> getAdmissionDetails(AdmissionDetailsSearchRequest appointmentBookingSearchRequest) throws DcometServiceException;

    public void saveAdmissionDetails(AdmissionDetails admissionDetail) throws DcometServiceException;
//-------------------Reports-------------------//

    public List<Object[]> getNewRegistrationReport(Integer entityRid, String curDate) throws DcometServiceException;

    public List<Object[]> getReviewVisitReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getDateWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getSpecialityWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getCancelledAppointmentReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getReferralTypeReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    /**
     *
     * @param entityRid
     * @param fromDate
     * @param toDate
     * @return
     * @throws DcometServiceException
     */
    public List<DoctorWiseReport> getDoctorWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;

    public List<Object[]> getAppointmentWiseReport(Integer entityRid, String fromDate, String toDate) throws DcometServiceException;
}
