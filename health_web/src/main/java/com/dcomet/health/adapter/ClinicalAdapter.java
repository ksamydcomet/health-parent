package com.dcomet.health.adapter;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.ComplaintsData;
import com.dcomet.health.dao.data.VisitPlanData;
import com.dcomet.health.dao.data.VisitTemplateData;
import com.dcomet.health.dao.data.VisitVitalsData;
import com.dcomet.health.domain.Complaints;
import com.dcomet.fw.exception.DcometServiceException;
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
import com.dcomet.health.dao.data.VisitData;
import com.dcomet.health.domain.AdmissionDetails;
import com.dcomet.health.domain.Appointment;
import com.dcomet.health.domain.AppointmentBooking;
import com.dcomet.health.domain.AppointmentReservation;
import com.dcomet.health.domain.AppointmentResourceMap;
import com.dcomet.health.domain.AppointmentToken;
import com.dcomet.health.domain.FreeConsultationPatientDoctorMap;
import com.dcomet.health.domain.History;
import com.dcomet.health.domain.Kin;
import com.dcomet.health.domain.Patient;
import java.util.ArrayList;
import java.util.List;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.domain.VisitPlan;
import com.dcomet.health.domain.VisitTemplate;
import com.dcomet.health.domain.VisitVitals;
import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component("clinicalAdapter")
public class ClinicalAdapter {

    //--------Appointment----------
    public List<Appointment> convertAppointmentDataToAppointment(
            List<AppointmentData> appointmentDataList) throws DcometServiceException {
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentData appointmentData : appointmentDataList) {
            appointmentList.add(convertAppointmentDataToAppointment(appointmentData));
        }
        return appointmentList;
    }

    public List<AppointmentData> convertAppointmentToAppointmentData(
            List<Appointment> appointmentList) throws DcometServiceException {
        List<AppointmentData> appointmentDataList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentDataList.add(convertAppointmentToAppointmentData(appointment));
        }
        return appointmentDataList;
    }

    public Appointment convertAppointmentDataToAppointment(AppointmentData appointmentData)
            throws DcometServiceException {
        Appointment appointment = new Appointment();
        if (appointmentData.getId() != null) {
            appointment.setId(appointmentData.getId());
        }
        if (appointmentData.getApptEntRID() != null) {
            appointment.setEntityRid(appointmentData.getApptEntRID());
        }
        if (appointmentData.getApptUnitRID() != null) {
            appointment.setApptUnitRID(appointmentData.getApptUnitRID());
        }
        if (appointmentData.getApptresType() != null) {
            appointment.setApptresType(appointmentData.getApptresType());
        }
        if (appointmentData.getApptvisitRID() != null) {
            appointment.setApptvisitRID(appointmentData.getApptvisitRID());
        }
        if (appointmentData.getApptUserNum() != null) {
            appointment.setApptUserNum(appointmentData.getApptUserNum());
        }
        if (appointmentData.getApptType() != null) {
            appointment.setApptType(appointmentData.getApptType());
        }
        if (appointmentData.getApptStatus() != null) {
            appointment.setApptStatus(appointmentData.getApptStatus());
        }
        if (appointmentData.getApptToken() != null) {
            appointment.setApptToken(appointmentData.getApptToken());
        }
        if (appointmentData.getApptFromDate() != null) {
            appointment.setApptFromDate(DateUtil.convertDateToString(appointmentData.getApptFromDate()));
        }
        if (appointmentData.getApptFromTime() != null) {
            appointment.setApptFromTime(appointmentData.getApptFromTime());
        }
        if (appointmentData.getApptArrivedTime() != null) {
            appointment.setApptArrivedTime(appointmentData.getApptArrivedTime());
        }
        if (appointmentData.getApptInprogressTime() != null) {
            appointment.setApptInprogressTime(appointmentData.getApptInprogressTime());
        }
        if (appointmentData.getApptCompletedTime() != null) {
            appointment.setApptCompletedTime(appointmentData.getApptCompletedTime());
        }
        if (appointmentData.getApptBilledTime() != null) {
            appointment.setApptBilledTime(appointmentData.getApptBilledTime());
        }
        if (appointmentData.getApptOrderRID() != null) {
            appointment.setApptOrderRID(appointmentData.getApptOrderRID());
        }
        if (appointmentData.getApptDuration() != null) {
            appointment.setApptDuration(appointmentData.getApptDuration());
        }
        if (appointmentData.getApptServiceRID() != null) {
            appointment.setApptServiceRID(appointmentData.getApptServiceRID());
        }
        if (appointmentData.getApptToDate() != null) {
            appointment.setApptToDate(DateUtil.convertDateToString(appointmentData.getApptToDate()));
        }
        if (appointmentData.getApptToTime() != null) {
            appointment.setApptToTime(appointmentData.getApptToTime());
        }
        if (appointmentData.getApptServiceName() != null) {
            appointment.setApptServiceName(appointmentData.getApptServiceName());
        }
        if (appointmentData.getApptServicePointRID() != null) {
            appointment.setApptServicePointRID(appointmentData.getApptServicePointRID());
        }
        if (appointmentData.getApptServicePointName() != null) {
            appointment.setApptServicePointName(appointmentData.getApptServicePointName());
        }
        if (appointmentData.getApptServiceProviderRID() != null) {
            appointment.setApptServiceProviderRID(appointmentData.getApptServiceProviderRID());
        }
        if (appointmentData.getApptServiceProviderName() != null) {
            appointment.setApptServiceProviderName(appointmentData.getApptServiceProviderName());
        }
        if (appointmentData.getApptPatientRID() != null) {
            appointment.setApptPatientRID(appointmentData.getApptPatientRID());
        }
        if (appointmentData.getApptPatientName() != null) {
            appointment.setApptPatientName(appointmentData.getApptPatientName());
        }
        if (appointmentData.getApptPatientPhone() != null) {
            appointment.setApptPatientPhone(appointmentData.getApptPatientPhone());
        }
        if (appointmentData.getApptPatientMrn() != null) {
            appointment.setApptPatientMrn(appointmentData.getApptPatientMrn());
        }
        if (appointmentData.getApptPatientAge() != null) {
            appointment.setApptPatientAge(appointmentData.getApptPatientAge());
        }
        if (appointmentData.getApptPatientGenderIndex() != null) {
            appointment.setApptPatientGenderIndex(appointmentData.getApptPatientGenderIndex());
        }
        if (appointmentData.getApptFromDateTime() != null) {
            appointment.setApptFromDateTime(DateUtil.convertCalendarToString(appointmentData.getApptFromDateTime()));
        }
        if (appointmentData.getApptToDateTime() != null) {
            appointment.setApptToDateTime(DateUtil.convertCalendarToString(appointmentData.getApptToDateTime()));
        }
        if (appointmentData.getApptBookingNumber() != null) {
            appointment.setApptBookingNumber(appointmentData.getApptBookingNumber());
        }
        if (appointmentData.getApptVisitStatus() != null) {
            appointment.setApptVisitStatus(appointmentData.getApptVisitStatus());
        }
        if (appointmentData.getApptOrders() != null) {
            appointment.setApptOrders(appointmentData.getApptOrders());
        }
        if (appointmentData.getApptCancelReason() != null) {
            appointment.setApptCancelReason(appointmentData.getApptCancelReason());
        }
        if (appointmentData.getApptIsFollowUpDone() != null) {
            appointment.setApptIsFollowUpDone(appointmentData.getApptIsFollowUpDone());
        }
        if (appointmentData.getApptIsLockAcquired() != null) {
            appointment.setApptIsLockAcquired(appointmentData.getApptIsLockAcquired());
        }
        if (appointmentData.getApptRemarks() != null) {
            appointment.setApptRemarks(appointmentData.getApptRemarks());
        }
        if (appointmentData.getCreatedUserRid() != null) {
            appointment.setCreatedUserRid(appointmentData.getCreatedUserRid());
        }
        if (appointmentData.getCreatedDateTime() != null) {
            appointment.setCreatedDateTime(DateUtil.convertCalendarToString(appointmentData.getCreatedDateTime()));
        }
        if (appointmentData.getModifiedUserRid() != null) {
            appointment.setModifiedUserRid(appointmentData.getModifiedUserRid());
        }
        if (appointmentData.getModifiedDateTime() != null) {
            appointment.setModifiedDateTime(DateUtil.convertCalendarToString(appointmentData.getModifiedDateTime()));
        }
        if (appointmentData.getApptCreatedUnitRID() != null) {
            appointment.setApptCreatedUnitRID(appointmentData.getApptCreatedUnitRID());
        }
        return appointment;
    }

    public AppointmentData convertAppointmentToAppointmentData(Appointment appointment)
            throws DcometServiceException {
        AppointmentData appointmentData = new AppointmentData();
        if (appointment.getId() != null) {
            appointmentData.setId(appointment.getId());
        }
        if (appointment.getEntityRid() != null) {
            appointmentData.setApptEntRID(appointment.getEntityRid());
        }
        if (appointment.getApptUnitRID() != null) {
            appointmentData.setApptUnitRID(appointment.getApptUnitRID());
        }
        if (appointment.getApptresType() != null) {
            appointmentData.setApptresType(appointment.getApptresType());
        }
        if (appointment.getApptvisitRID() != null) {
            appointmentData.setApptvisitRID(appointment.getApptvisitRID());
        }
        if (appointment.getApptUserNum() != null) {
            appointmentData.setApptUserNum(appointment.getApptUserNum());
        }
        if (appointment.getApptType() != null) {
            appointmentData.setApptType(appointment.getApptType());
        }
        if (appointment.getApptStatus() != null) {
            appointmentData.setApptStatus(appointment.getApptStatus());
        }
        if (appointment.getApptToken() != null) {
            appointmentData.setApptToken(appointment.getApptToken());
        }
        if (appointment.getApptFromDate() != null) {
            appointmentData.setApptFromDate(DateUtil.convertStringToDate(appointment.getApptFromDate()));
        }
        if (appointment.getApptFromTime() != null) {
            appointmentData.setApptFromTime(appointment.getApptFromTime());
        }
        if (appointment.getApptArrivedTime() != null) {
            appointmentData.setApptArrivedTime(appointment.getApptArrivedTime());
        }
        if (appointment.getApptInprogressTime() != null) {
            appointmentData.setApptInprogressTime(appointment.getApptInprogressTime());
        }
        if (appointment.getApptCompletedTime() != null) {
            appointmentData.setApptCompletedTime(appointment.getApptCompletedTime());
        }
        if (appointment.getApptBilledTime() != null) {
            appointmentData.setApptBilledTime(appointment.getApptBilledTime());
        }
        if (appointment.getApptOrderRID() != null) {
            appointmentData.setApptOrderRID(appointment.getApptOrderRID());
        }
        if (appointment.getApptDuration() != null) {
            appointmentData.setApptDuration(appointment.getApptDuration());
        }
        if (appointment.getApptServiceRID() != null) {
            appointmentData.setApptServiceRID(appointment.getApptServiceRID());
        }
        if (appointment.getApptToDate() != null) {
            appointmentData.setApptToDate(DateUtil.convertStringToDate(appointment.getApptToDate()));
        }
        if (appointment.getApptToTime() != null) {
            appointmentData.setApptToTime(appointment.getApptToTime());
        }
        if (appointment.getApptServiceName() != null) {
            appointmentData.setApptServiceName(appointment.getApptServiceName());
        }
        if (appointment.getApptServicePointRID() != null) {
            appointmentData.setApptServicePointRID(appointment.getApptServicePointRID());
        }
        if (appointment.getApptServicePointName() != null) {
            appointmentData.setApptServicePointName(appointment.getApptServicePointName());
        }
        if (appointment.getApptServiceProviderRID() != null) {
            appointmentData.setApptServiceProviderRID(appointment.getApptServiceProviderRID());
        }
        if (appointment.getApptServiceProviderName() != null) {
            appointmentData.setApptServiceProviderName(appointment.getApptServiceProviderName());
        }
        if (appointment.getApptPatientRID() != null) {
            appointmentData.setApptPatientRID(appointment.getApptPatientRID());
        }
        if (appointment.getApptPatientName() != null) {
            appointmentData.setApptPatientName(appointment.getApptPatientName());
        }
        if (appointment.getApptPatientPhone() != null) {
            appointmentData.setApptPatientPhone(appointment.getApptPatientPhone());
        }
        if (appointment.getApptPatientMrn() != null) {
            appointmentData.setApptPatientMrn(appointment.getApptPatientMrn());
        }
        if (appointment.getApptPatientAge() != null) {
            appointmentData.setApptPatientAge(appointment.getApptPatientAge());
        }
        if (appointment.getApptPatientGenderIndex() != null) {
            appointmentData.setApptPatientGenderIndex(appointment.getApptPatientGenderIndex());
        }
        if (appointment.getApptFromDateTime() != null) {
            appointmentData.setApptFromDateTime(DateUtil.convertStringToCalendar(appointment.getApptFromDateTime()));
        }
        if (appointment.getApptToDateTime() != null) {
            appointmentData.setApptToDateTime(DateUtil.convertStringToCalendar(appointment.getApptToDateTime()));
        }
        if (appointment.getCreatedUserRid() != null) {
            appointmentData.setCreatedUserRid(appointment.getCreatedUserRid());
        }
        if (appointment.getModifiedUserRid() != null) {
            appointmentData.setModifiedUserRid(appointment.getModifiedUserRid());
        }
        if (appointment.getApptBookingNumber() != null) {
            appointmentData.setApptBookingNumber(appointment.getApptBookingNumber());
        }
        if (appointment.getApptVisitStatus() != null) {
            appointmentData.setApptVisitStatus(appointment.getApptVisitStatus());
        }
        if (appointment.getApptOrders() != null) {
            appointmentData.setApptOrders(appointment.getApptOrders());
        }
        if (appointment.getApptCancelReason() != null) {
            appointmentData.setApptCancelReason(appointment.getApptCancelReason());
        }
        if (appointment.getApptIsFollowUpDone() != null) {
            appointmentData.setApptIsFollowUpDone(appointment.getApptIsFollowUpDone());
        }
        if (appointment.getApptIsLockAcquired() != null) {
            appointmentData.setApptIsLockAcquired(appointment.getApptIsLockAcquired());
        }
        if (appointment.getApptAttendingUserRID() != null) {
            appointmentData.setApptAttendingUserRID(appointment.getApptAttendingUserRID());
        }
        if (appointment.getApptRemarks() != null) {
            appointmentData.setApptRemarks(appointment.getApptRemarks());
        }
        if (appointment.getApptCreatedUnitRID() != null) {
            appointmentData.setApptCreatedUnitRID(appointment.getApptCreatedUnitRID());
        }

        return appointmentData;
    }

    //----------AppointmentBooking--------------
    public List<AppointmentBooking> convertAppointmentBookingDataToAppointmentBooking(
            List<AppointmentBookingData> resultData) throws DcometServiceException {
        List<AppointmentBooking> result = new ArrayList<>();
        for (AppointmentBookingData child1Data : resultData) {
            result.add(convertAppointmentBookingDataToAppointmentBooking(child1Data));
        }
        return result;
    }

    public List<AppointmentBookingData> convertAppointmentBookingToAppointmentBookingData(List<AppointmentBooking> appointmentBookingList)
            throws DcometServiceException {
        List<AppointmentBookingData> appointmentBookingDataList = new ArrayList<>();
        for (AppointmentBooking appointmentBooking : appointmentBookingList) {
            appointmentBookingDataList.add(convertAppointmentBookingToAppointmentBookingData(appointmentBooking));
        }
        return appointmentBookingDataList;
    }

    public AppointmentBooking convertAppointmentBookingDataToAppointmentBooking(AppointmentBookingData appointmentBookingData)
            throws DcometServiceException {
        AppointmentBooking appointmentBooking = new AppointmentBooking();
        if (appointmentBookingData.getId() != null) {
            appointmentBooking.setId(appointmentBookingData.getId());
        }
        if (appointmentBookingData.getBookingUnitRid() != null) {
            appointmentBooking.setBookingUnitRid(appointmentBookingData.getBookingUnitRid());
        }
        if (appointmentBookingData.getBookinDate() != null) {
            appointmentBooking.setBookinDate(DateUtil.convertDateToString(appointmentBookingData.getBookinDate()));
        }
        if (appointmentBookingData.getBookingSeqNumber() != null) {
            appointmentBooking.setBookingSeqNumber(appointmentBookingData.getBookingSeqNumber());
        }
        if (appointmentBookingData.getCreatedUserRid() != null) {
            appointmentBooking.setCreatedUserRid(appointmentBookingData.getCreatedUserRid());
        }
        if (appointmentBookingData.getCreatedDateTime() != null) {
            appointmentBooking.setCreatedDateTime(DateUtil.convertCalendarToString(appointmentBookingData.getCreatedDateTime()));
        }
        if (appointmentBookingData.getModifiedUserRid() != null) {
            appointmentBooking.setModifiedUserRid(appointmentBookingData.getModifiedUserRid());
        }
        if (appointmentBookingData.getModifiedDateTime() != null) {
            appointmentBooking.setModifiedDateTime(DateUtil.convertCalendarToString(appointmentBookingData.getModifiedDateTime()));
        }
        return appointmentBooking;
    }

    public AppointmentBookingData convertAppointmentBookingToAppointmentBookingData(AppointmentBooking appointmentBooking)
            throws DcometServiceException {
        AppointmentBookingData appointmentBookingData = new AppointmentBookingData();
        if (appointmentBooking.getId() != null) {
            appointmentBookingData.setId(appointmentBooking.getId());
        }
        if (appointmentBooking.getBookingUnitRid() != null) {
            appointmentBookingData.setBookingUnitRid(appointmentBooking.getBookingUnitRid());
        }
        if (appointmentBooking.getBookinDate() != null) {
            appointmentBookingData.setBookinDate(DateUtil.convertStringToDate(appointmentBooking.getBookinDate()));
        }
        if (appointmentBooking.getBookingSeqNumber() != null) {
            appointmentBookingData.setBookingSeqNumber(appointmentBooking.getBookingSeqNumber());
        }
        if (appointmentBooking.getCreatedUserRid() != null) {
            appointmentBookingData.setCreatedUserRid(appointmentBooking.getCreatedUserRid());
        }
        if (appointmentBooking.getModifiedUserRid() != null) {
            appointmentBookingData.setModifiedUserRid(appointmentBooking.getModifiedUserRid());
        }
        return appointmentBookingData;
    }

    //---------Appointment Reservation----------
    public List<AppointmentReservation> convertAppointmentReservationDataToAppointmentReservation(
            List<AppointmentReservationData> resultData) throws DcometServiceException {
        List<AppointmentReservation> result = new ArrayList<>();
        for (AppointmentReservationData child1Data : resultData) {
            result.add(convertAppointmentReservationDataToAppointmentReservation(child1Data));
        }
        return result;
    }

    public List<AppointmentReservationData> convertAppointmentReservationToAppointmentReservationData(List<AppointmentReservation> appointmentReservationList)
            throws DcometServiceException {
        List<AppointmentReservationData> appointmentReservationDataList = new ArrayList<>();
        for (AppointmentReservation appointmentReservation : appointmentReservationList) {
            appointmentReservationDataList.add(convertAppointmentReservationToAppointmentReservationData(appointmentReservation));
        }
        return appointmentReservationDataList;
    }

    public AppointmentReservation convertAppointmentReservationDataToAppointmentReservation(AppointmentReservationData appointmentReservationData)
            throws DcometServiceException {
        AppointmentReservation appointmentReservation = new AppointmentReservation();
        if (appointmentReservationData.getId() != null) {
            appointmentReservation.setId(appointmentReservationData.getId());
        }
        if (appointmentReservationData.getRsvResourceRID() != null) {
            appointmentReservation.setRsvResourceRID(appointmentReservationData.getRsvResourceRID());
        }
        if (appointmentReservationData.getRsvReservationType() != null) {
            appointmentReservation.setRsvReservationType(appointmentReservationData.getRsvReservationType());
        }
        if (appointmentReservationData.getRsvReservedForRid() != null) {
            appointmentReservation.setRsvReservedForRid(appointmentReservationData.getRsvReservedForRid());
        }
        if (appointmentReservationData.getRsvReservedForRid() != null) {
            appointmentReservation.setRsvReservedForRid(appointmentReservationData.getRsvReservedForRid());
        }
        if (appointmentReservationData.getRsvReservedForName() != null) {
            appointmentReservation.setRsvReservedForName(appointmentReservationData.getRsvReservedForName());
        }
        if (appointmentReservationData.getRsvReservedSlotValue() != null) {
            appointmentReservation.setRsvReservedSlotValue(appointmentReservationData.getRsvReservedSlotValue());
        }
        if (appointmentReservationData.getRsvFromDateTime() != null) {
            appointmentReservation.setRsvFromDateTime(DateUtil.convertCalendarToString(appointmentReservationData.getRsvFromDateTime()));
        }
        if (appointmentReservationData.getRsvToDateTime() != null) {
            appointmentReservation.setRsvToDateTime(DateUtil.convertCalendarToString(appointmentReservationData.getRsvToDateTime()));
        }
        if (appointmentReservationData.getRsvRemarks() != null) {
            appointmentReservation.setRsvRemarks(appointmentReservationData.getRsvRemarks());
        }
        if (appointmentReservationData.getCreatedUserRid() != null) {
            appointmentReservation.setCreatedUserRid(appointmentReservationData.getCreatedUserRid());
        }
        if (appointmentReservationData.getCreatedDateTime() != null) {
            appointmentReservation.setCreatedDateTime(DateUtil.convertCalendarToString(appointmentReservationData.getCreatedDateTime()));
        }
        if (appointmentReservationData.getModifiedUserRid() != null) {
            appointmentReservation.setModifiedUserRid(appointmentReservationData.getModifiedUserRid());
        }
        if (appointmentReservationData.getModifiedDateTime() != null) {
            appointmentReservation.setModifiedDateTime(DateUtil.convertCalendarToString(appointmentReservationData.getModifiedDateTime()));
        }
        return appointmentReservation;
    }

    public AppointmentReservationData convertAppointmentReservationToAppointmentReservationData(AppointmentReservation appointmentReservation)
            throws DcometServiceException {
        AppointmentReservationData appointmentReservationData = new AppointmentReservationData();
        if (appointmentReservation.getId() != null) {
            appointmentReservationData.setId(appointmentReservation.getId());
        }
        if (appointmentReservation.getRsvResourceRID() != null) {
            appointmentReservationData.setRsvResourceRID(appointmentReservation.getRsvResourceRID());
        }
        if (appointmentReservation.getRsvReservationType() != null) {
            appointmentReservationData.setRsvReservationType(appointmentReservation.getRsvReservationType());
        }
        if (appointmentReservation.getRsvReservedForRid() != null) {
            appointmentReservationData.setRsvReservedForRid(appointmentReservation.getRsvReservedForRid());
        }
        if (appointmentReservation.getRsvReservedForName() != null) {
            appointmentReservationData.setRsvReservedForName(appointmentReservation.getRsvReservedForName());
        }
        if (appointmentReservation.getRsvReservedSlotValue() != null) {
            appointmentReservationData.setRsvReservedSlotValue(appointmentReservation.getRsvReservedSlotValue());
        }
        if (appointmentReservation.getRsvFromDateTime() != null) {
            appointmentReservationData.setRsvFromDateTime(DateUtil.convertStringToCalendar(appointmentReservation.getRsvFromDateTime()));
        }
        if (appointmentReservation.getRsvToDateTime() != null) {
            appointmentReservationData.setRsvToDateTime(DateUtil.convertStringToCalendar(appointmentReservation.getRsvToDateTime()));
        }
        if (appointmentReservation.getId() != null) {
            appointmentReservationData.setId(appointmentReservation.getId());
        }
        if (appointmentReservation.getRsvRemarks() != null) {
            appointmentReservationData.setRsvRemarks(appointmentReservation.getRsvRemarks());
        }
        if (appointmentReservation.getCreatedUserRid() != null) {
            appointmentReservationData.setCreatedUserRid(appointmentReservation.getCreatedUserRid());
        }
        if (appointmentReservation.getModifiedUserRid() != null) {
            appointmentReservationData.setModifiedUserRid(appointmentReservation.getModifiedUserRid());
        }
        return appointmentReservationData;
    }

    public List<AppointmentResourceMap> convertAppointmentResourceMapDataToAppointmentResourceMap(
            List<AppointmentResourceMapData> resultData) throws DcometServiceException {
        List<AppointmentResourceMap> result = new ArrayList<>();
        for (AppointmentResourceMapData child1Data : resultData) {
            result.add(convertAppointmentResourceMapDataToAppointmentResourceMap(child1Data));
        }
        return result;
    }

    public List<AppointmentResourceMapData> convertAppointmentResourceMapToAppointmentResourceMapData(List<AppointmentResourceMap> appointmentResourceMapList)
            throws DcometServiceException {
        List<AppointmentResourceMapData> appointmentResourceMapDataList = new ArrayList<>();
        for (AppointmentResourceMap appointmentResourceMap : appointmentResourceMapList) {
            appointmentResourceMapDataList.add(convertAppointmentResourceMapToAppointmentResourceMapData(appointmentResourceMap));
        }
        return appointmentResourceMapDataList;
    }

    public AppointmentResourceMap convertAppointmentResourceMapDataToAppointmentResourceMap(AppointmentResourceMapData appointmentResourceMapData)
            throws DcometServiceException {
        AppointmentResourceMap appointmentResourceMap = new AppointmentResourceMap();
        if (appointmentResourceMapData.getId() != null) {
            appointmentResourceMap.setId(appointmentResourceMapData.getId());
        }
        if (appointmentResourceMapData.getArmApptRid() != null) {
            appointmentResourceMap.setArmApptRid(appointmentResourceMapData.getArmApptRid());
        }
        if (appointmentResourceMapData.getArmResourceRid() != null) {
            appointmentResourceMap.setArmResourceRid(appointmentResourceMapData.getArmResourceRid());
        }

        return appointmentResourceMap;
    }

    public AppointmentResourceMapData convertAppointmentResourceMapToAppointmentResourceMapData(AppointmentResourceMap appointmentResourceMap)
            throws DcometServiceException {
        AppointmentResourceMapData appointmentResourceMapData = new AppointmentResourceMapData();
        if (appointmentResourceMap.getId() != null) {
            appointmentResourceMapData.setId(appointmentResourceMap.getId());
        }
        if (appointmentResourceMap.getArmApptRid() != null) {
            appointmentResourceMapData.setArmApptRid(appointmentResourceMap.getArmApptRid());
        }
        if (appointmentResourceMap.getArmResourceRid() != null) {
            appointmentResourceMapData.setArmResourceRid(appointmentResourceMap.getArmResourceRid());
        }

        return appointmentResourceMapData;
    }
//-----------AppointmentToken-----------

    public List<AppointmentToken> convertAppointmentTokenDataToAppointmentToken(
            List<AppointmentTokenData> resultData) throws DcometServiceException {
        List<AppointmentToken> result = new ArrayList<>();
        for (AppointmentTokenData child1Data : resultData) {
            result.add(convertAppointmentTokenDataToAppointmentToken(child1Data));
        }
        return result;
    }

    public List<AppointmentTokenData> convertAppointmentTokenToAppointmentTokenData(List<AppointmentToken> appointmentTokenList)
            throws DcometServiceException {
        List<AppointmentTokenData> appointmentTokenDataList = new ArrayList<>();
        for (AppointmentToken appointmentToken : appointmentTokenList) {
            appointmentTokenDataList.add(convertAppointmentTokenToAppointmentTokenData(appointmentToken));
        }
        return appointmentTokenDataList;
    }

    public AppointmentToken convertAppointmentTokenDataToAppointmentToken(AppointmentTokenData appointmentResourceMapData)
            throws DcometServiceException {
        AppointmentToken appointmentResourceMap = new AppointmentToken();
        if (appointmentResourceMapData.getId() != null) {
            appointmentResourceMap.setId(appointmentResourceMapData.getId());
        }
        if (appointmentResourceMapData.getTokenUnitRID() != null) {
            appointmentResourceMap.setTokenUnitRID(appointmentResourceMapData.getTokenUnitRID());
        }
        if (appointmentResourceMapData.getTokenDate() != null) {
            appointmentResourceMap.setTokenDate(DateUtil.convertDateToString(appointmentResourceMapData.getTokenDate()));
        }
        if (appointmentResourceMapData.getTokenNumber() != null) {
            appointmentResourceMap.setTokenNumber(appointmentResourceMapData.getTokenNumber());
        }

        return appointmentResourceMap;
    }

    public AppointmentTokenData convertAppointmentTokenToAppointmentTokenData(AppointmentToken appointmentResourceMap)
            throws DcometServiceException {
        AppointmentTokenData appointmentResourceMapData = new AppointmentTokenData();
        if (appointmentResourceMap.getId() != null) {
            appointmentResourceMapData.setId(appointmentResourceMap.getId());
        }
        if (appointmentResourceMap.getTokenUnitRID() != null) {
            appointmentResourceMapData.setTokenUnitRID(appointmentResourceMap.getTokenUnitRID());
        }
        if (appointmentResourceMap.getTokenDate() != null) {
            appointmentResourceMapData.setTokenDate(DateUtil.convertStringToDate(appointmentResourceMap.getTokenDate()));
        }
        if (appointmentResourceMap.getTokenNumber() != null) {
            appointmentResourceMapData.setTokenNumber(appointmentResourceMap.getTokenNumber());
        }

        return appointmentResourceMapData;
    }

    //----------------------Patient------------------------    
    public List<Patient> convertPatientDataToPatient(
            List<PatientData> resultData) throws DcometServiceException {
        List<Patient> patientList = new ArrayList<>();
        for (PatientData patientData : resultData) {
            patientList.add(convertPatientDataToPatient(patientData));
        }
        return patientList;
    }

    public List<PatientData> convertPatientToPatientData(List<Patient> patientList)
            throws DcometServiceException {
        List<PatientData> patientDataList = new ArrayList<>();
        for (Patient patient : patientList) {
            patientDataList.add(convertPatientToPatientData(patient));
        }
        return patientDataList;
    }

    public Patient convertPatientDataToPatient(PatientData patientData)
            throws DcometServiceException {
        Patient patient = new Patient();
        if (patientData.getId() != null) {
            patient.setId(patientData.getId());
        }
        if (patientData.getPatMrnNo() != null) {
            patient.setPatMrnNo(patientData.getPatMrnNo());
        }
        if (patientData.getPatEntRid() != null) {
            patient.setEntityRid(patientData.getPatEntRid());
        }
        if (patientData.getPatSeqNo() != null) {
            patient.setPatSeqNo(patientData.getPatSeqNo());
        }
        if (patientData.getPatRegDate() != null) {
            patient.setPatRegDate(DateUtil.convertDateToString(patientData.getPatRegDate()));
        }
        if (patientData.getPatTitle() != null) {
            patient.setPatTitle(patientData.getPatTitle());
        }
        if (patientData.getPatName() != null) {
            patient.setPatName(patientData.getPatName());
        }
        if (patientData.getPatFullName() != null) {
            patient.setPatFullName(patientData.getPatFullName());
        }
        if (patientData.getPatFamilyName() != null) {
            patient.setPatFamilyName(patientData.getPatFamilyName());
        }
        if (patientData.getPatGenderIndex() != null) {
            patient.setPatGenderIndex(patientData.getPatGenderIndex());
            patient.setPatGender((patient.getPatGenderIndex()).equals("1")
                    ? "Male" : (patient.getPatGenderIndex()).equals("2") ? "Female" : "Trangender");
        }
        if (patientData.getPatBloodGroupIndex() != null) {
            patient.setPatBloodGroupIndex(patientData.getPatBloodGroupIndex());
        }
        if (patientData.getPatDob() != null) {
            patient.setPatDob(DateUtil.convertDateToString(patientData.getPatDob()));

            Calendar.getInstance().setTime(DateUtil.convertStringToDate(patient.getPatDob()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtil.convertStringToDate(DateUtil.getCurrentDate()));
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateUtil.convertStringToDate(patient.getPatDob()));
            patient.setPatAge(cal.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));
        }
        if (patientData.getPatMaritalStatus() != null) {
            patient.setPatMaritalStatus(patientData.getPatMaritalStatus());
        }
        if (patientData.getPatAddress() != null) {
            patient.setPatAddress(patientData.getPatAddress());
        }
        if (patientData.getPatCityIndex() != null) {
            patient.setPatCityIndex(patientData.getPatCityIndex());
        }
        if (patientData.getPatStateIndex() != null) {
            patient.setPatStateIndex(patientData.getPatStateIndex());
        }
        if (patientData.getPatCountryIndex() != null) {
            patient.setPatCountryIndex(patientData.getPatCountryIndex());
        }
        if (patientData.getPatPincode() != null) {
            patient.setPatPincode(patientData.getPatPincode());
        }
        if (patientData.getPatPhoneNo() != null) {
            patient.setPatPhoneNo(patientData.getPatPhoneNo());
        }
        if (patientData.getPatEmailId() != null) {
            patient.setPatEmailId(patientData.getPatEmailId());
        }
        if (patientData.getPatPhotoFilePath() != null) {
            patient.setPatPhotoFilePath(patientData.getPatPhotoFilePath());
        }
        if (patientData.getPatPhotoFileType() != null) {
            patient.setPatPhotoFileType(patientData.getPatPhotoFileType());
        }
        if (patientData.getPatVipStatus() != null) {
            patient.setPatVipStatus(patientData.getPatVipStatus());
        }
        if (patientData.getPatParentRid() != null) {
            patient.setPatParentRid(patientData.getPatParentRid());
        }
        if (patientData.getPatGeneratedDob() != null) {
            patient.setPatGeneratedDob(DateUtil.convertDateToString(patientData.getPatGeneratedDob()));
        }
        if (patientData.getPatRowInvalidated() != null) {
            patient.setPatRowInvalidated(patientData.getPatRowInvalidated());
        }
        if (patientData.getPatRowInvalidatedDateTime() != null) {
            patient.setPatRowInvalidatedDateTime(DateUtil.convertCalendarToString(patientData.getPatRowInvalidatedDateTime()));
        }
        if (patientData.getPatState() != null) {
            patient.setPatState(patientData.getPatState());
        }
        if (patientData.getPatStatus() != null) {
            patient.setPatStatus(patientData.getPatStatus());
        }
        if (patientData.getCreatedDateTime() != null) {
            patient.setCreatedDateTime(DateUtil.convertCalendarToString(patientData.getCreatedDateTime()));
        }
        if (patientData.getCreatedUserRid() != null) {
            patient.setCreatedUserRid(patientData.getCreatedUserRid());
        }
        if (patientData.getModifiedDateTime() != null) {
            patient.setModifiedDateTime(DateUtil.convertCalendarToString(patientData.getModifiedDateTime()));
        }
        if (patientData.getModifiedUserRid() != null) {
            patient.setModifiedUserRid(patientData.getModifiedUserRid());
        }
        return patient;
    }

    public PatientData convertPatientToPatientData(Patient patient)
            throws DcometServiceException {
        PatientData patientData = new PatientData();
        if (patient.getId() != null) {
            patientData.setId(patient.getId());
        }
        if (patient.getPatMrnNo() != null) {
            patientData.setPatMrnNo(patient.getPatMrnNo());
        }
        if (patient.getEntityRid() != null) {
            patientData.setPatEntRid(patient.getEntityRid());
        }
        if (patient.getPatSeqNo() != null) {
            patientData.setPatSeqNo(patient.getPatSeqNo());
        }
        if (patient.getPatRegDate() != null) {
            patientData.setPatRegDate(DateUtil.convertStringToDate(patient.getPatRegDate()));
        }
        if (patient.getPatTitle() != null) {
            patientData.setPatTitle(patient.getPatTitle());
        }
        if (patient.getPatName() != null) {
            patientData.setPatName(patient.getPatName());
        }
        if (patient.getPatFullName() != null) {
            patientData.setPatFullName(patient.getPatFullName());
        }
        if (patient.getPatFamilyName() != null) {
            patientData.setPatFamilyName(patient.getPatFamilyName());
        }
        if (patient.getPatGenderIndex() != null) {
            patientData.setPatGenderIndex(patient.getPatGenderIndex());
        }
        if (patient.getPatBloodGroupIndex() != null) {
            patientData.setPatBloodGroupIndex(patient.getPatBloodGroupIndex());
        }
        if (patient.getPatDob() != null) {
            patientData.setPatDob(DateUtil.convertStringToDate(patient.getPatDob()));
        }
        if (patient.getPatMaritalStatus() != null) {
            patientData.setPatMaritalStatus(patient.getPatMaritalStatus());
        }
        if (patient.getPatAddress() != null) {
            patientData.setPatAddress(patient.getPatAddress());
        }
        if (patient.getPatCityIndex() != null) {
            patientData.setPatCityIndex(patient.getPatCityIndex());
        }
        if (patient.getPatStateIndex() != null) {
            patientData.setPatStateIndex(patient.getPatStateIndex());
        }
        if (patient.getPatCountryIndex() != null) {
            patientData.setPatCountryIndex(patient.getPatCountryIndex());
        }
        if (patient.getPatPincode() != null) {
            patientData.setPatPincode(patient.getPatPincode());
        }
        if (patient.getPatPhoneNo() != null) {
            patientData.setPatPhoneNo(patient.getPatPhoneNo());
        }
        if (patient.getPatEmailId() != null) {
            patientData.setPatEmailId(patient.getPatEmailId());
        }
        if (patient.getPatPhotoFilePath() != null) {
            patientData.setPatPhotoFilePath(patient.getPatPhotoFilePath());
        }
        if (patient.getPatPhotoFileType() != null) {
            patientData.setPatPhotoFileType(patient.getPatPhotoFileType());
        }
        if (patient.getPatVipStatus() != null) {
            patientData.setPatVipStatus(patient.getPatVipStatus());
        }
        if (patient.getPatParentRid() != null) {
            patientData.setPatParentRid(patient.getPatParentRid());
        }
        if (patient.getPatGeneratedDob() != null) {
            patientData.setPatGeneratedDob(DateUtil.convertStringToDate(patient.getPatGeneratedDob()));
        }
        if (patient.getPatRowInvalidated() != null) {
            patientData.setPatRowInvalidated(patient.getPatRowInvalidated());
        }
        if (patient.getPatRowInvalidatedDateTime() != null) {
            patientData.setPatRowInvalidatedDateTime(DateUtil.convertStringToCalendar(patient.getPatRowInvalidatedDateTime()));
        }
        if (patient.getPatState() != null) {
            patientData.setPatState(patient.getPatState());
        }
        if (patient.getPatStatus() != null) {
            patientData.setPatStatus(patient.getPatStatus());
        }
        if (patient.getCreatedUserRid() != null) {
            patientData.setCreatedUserRid(patient.getCreatedUserRid());
        }
        if (patient.getModifiedUserRid() != null) {
            patientData.setModifiedUserRid(patient.getModifiedUserRid());
        }

        return patientData;
    }

    //----------------------------------Visit-----------------------------
    public List<Visit> convertVisitDataToVisit(List<VisitData> visitDataList) throws DcometServiceException {
        List<Visit> visitList = new ArrayList<>();
        for (VisitData visitData : visitDataList) {
            visitList.add(convertVisitDataToVisit(visitData));
        }
        return visitList;
    }

    public List<VisitData> convertVisitToVisitData(List<Visit> visitList)
            throws DcometServiceException {
        List<VisitData> visitDataList = new ArrayList<>();
        for (Visit visit : visitList) {
            visitDataList.add(convertVisitToVisitData(visit));
        }
        return visitDataList;
    }

    public Visit convertVisitDataToVisit(VisitData visitData)
            throws DcometServiceException {
        Visit visit = new Visit();
        if (visitData.getId() != null) {
            visit.setId(visitData.getId());
        }
        if (visitData.getVisTypeIndex() != null) {
            visit.setVisTypeIndex(visitData.getVisTypeIndex());
        }
        if (visitData.getVisSubTypeIndex() != null) {
            visit.setVisSubTypeIndex(visitData.getVisSubTypeIndex());
        }
        if (visitData.getVisEntRid() != null) {
            visit.setEntityRid(visitData.getVisEntRid());
        }
        if (visitData.getVisPatRid() != null) {
            visit.setVisPatRid(visitData.getVisPatRid());
        }
        if (visitData.getVisPatType() != null) {
            visit.setVisPatType(visitData.getVisPatType());
        }
        if (visitData.getVisDate() != null) {
            visit.setVisDate(DateUtil.convertDateToString(visitData.getVisDate()));
        }
        if (visitData.getVisApptRid() != null) {
            visit.setVisApptRid(visitData.getVisApptRid());
        }
        if (visitData.getVisEpisodeRid() != null) {
            visit.setVisEpisodeRid(visitData.getVisEpisodeRid());
        }
        if (visitData.getVisSpecialityIndex() != null) {
            visit.setVisSpecialityIndex(visitData.getVisSpecialityIndex());
        }
        if (visitData.getVisReasonIndex() != null) {
            visit.setVisReasonIndex(visitData.getVisReasonIndex());
        }
        if (visitData.getVisRemarks() != null) {
            visit.setVisRemarks(visitData.getVisRemarks());
        }
        if (visitData.getVisConsDocRid() != null) {
            visit.setVisConsDocRid(visitData.getVisConsDocRid());
        }
        if (visitData.getVisAttnDocRid() != null) {
            visit.setVisAttnDocRid(visitData.getVisAttnDocRid());
        }
        if (visitData.getVisRefTypeIndex() != null) {
            visit.setVisRefTypeIndex(visitData.getVisRefTypeIndex());
        }
        if (visitData.getVisRefRid() != null) {
            visit.setVisRefRid(visitData.getVisRefRid());
        }
        if (visitData.getVisRefName() != null) {
            visit.setVisRefName(visitData.getVisRefName());
        }
        if (visitData.getVisDocRemarks() != null) {
            visit.setVisDocRemarks(visitData.getVisDocRemarks());
        }
        if (visitData.getVisState() != null) {
            visit.setVisState(visitData.getVisState());
        }
        if (visitData.getVisStatus() != null) {
            visit.setVisStatus(visitData.getVisStatus());
        }
        if (visitData.getVisIsCompleted() != null) {
            visit.setVisIsCompleted(visitData.getVisIsCompleted());
        }
        if (visitData.getVisDiagnosis() != null) {
            visit.setVisDiagnosis(visitData.getVisDiagnosis());
        }
        if (visitData.getVisCsNodes() != null) {
            visit.setVisCsNodes(visitData.getVisCsNodes());
        }
        if (visitData.getVisTreatment() != null) {
            visit.setVisTreatment(visitData.getVisTreatment());
        }
        if (visitData.getCreatedDateTime() != null) {
            visit.setCreatedDateTime(DateUtil.convertCalendarToString(visitData.getCreatedDateTime()));
        }
        if (visitData.getCreatedUserRid() != null) {
            visit.setCreatedUserRid(visitData.getCreatedUserRid());
        }
        if (visitData.getModifiedDateTime() != null) {
            visit.setModifiedDateTime(DateUtil.convertCalendarToString(visitData.getModifiedDateTime()));
        }
        if (visitData.getModifiedUserRid() != null) {
            visit.setModifiedUserRid(visitData.getModifiedUserRid());
        }

        return visit;
    }

    public VisitData convertVisitToVisitData(Visit visit)
            throws DcometServiceException {
        VisitData visitData = new VisitData();
        if (visit.getId() != null) {
            visitData.setId(visit.getId());
        }
        if (visit.getVisTypeIndex() != null) {
            visitData.setVisTypeIndex(visit.getVisTypeIndex());
        }
        if (visit.getVisSubTypeIndex() != null) {
            visitData.setVisSubTypeIndex(visit.getVisSubTypeIndex());
        }
        if (visit.getEntityRid() != null) {
            visitData.setVisEntRid(visit.getEntityRid());
        }
        if (visit.getVisPatRid() != null) {
            visitData.setVisPatRid(visit.getVisPatRid());
        }
        if (visit.getVisPatType() != null) {
            visitData.setVisPatType(visit.getVisPatType());
        }
        if (visit.getVisDate() != null) {
            visitData.setVisDate(DateUtil.convertStringToDate(visit.getVisDate()));
        }
        if (visit.getVisApptRid() != null) {
            visitData.setVisApptRid(visit.getVisApptRid());
        }
        if (visit.getVisEpisodeRid() != null) {
            visitData.setVisEpisodeRid(visit.getVisEpisodeRid());
        }
        if (visit.getVisSpecialityIndex() != null) {
            visitData.setVisSpecialityIndex(visit.getVisSpecialityIndex());
        }
        if (visit.getVisReasonIndex() != null) {
            visitData.setVisReasonIndex(visit.getVisReasonIndex());
        }
        if (visit.getVisRemarks() != null) {
            visitData.setVisRemarks(visit.getVisRemarks());
        }
        if (visit.getVisConsDocRid() != null) {
            visitData.setVisConsDocRid(visit.getVisConsDocRid());
        }
        if (visit.getVisAttnDocRid() != null) {
            visitData.setVisAttnDocRid(visit.getVisAttnDocRid());
        }
        if (visit.getVisRefTypeIndex() != null) {
            visitData.setVisRefTypeIndex(visit.getVisRefTypeIndex());
        }
        if (visit.getVisRefRid() != null) {
            visitData.setVisRefRid(visit.getVisRefRid());
        }
        if (visit.getVisRefName() != null) {
            visitData.setVisRefName(visit.getVisRefName());
        }
        if (visit.getVisDocRemarks() != null) {
            visitData.setVisDocRemarks(visit.getVisDocRemarks());
        }
        if (visit.getVisState() != null) {
            visitData.setVisState(visit.getVisState());
        }
        if (visit.getVisStatus() != null) {
            visitData.setVisStatus(visit.getVisStatus());
        }
        if (visit.getVisIsCompleted() != null) {
            visitData.setVisIsCompleted(visit.getVisIsCompleted());
        }
        if (visit.getVisDiagnosis() != null) {
            visitData.setVisDiagnosis(visit.getVisDiagnosis());
        }
        if (visit.getVisCsNodes() != null) {
            visitData.setVisCsNodes(visit.getVisCsNodes());
        }
        if (visit.getVisTreatment() != null) {
            visitData.setVisTreatment(visit.getVisTreatment());
        }
        if (visit.getCreatedUserRid() != null) {
            visitData.setCreatedUserRid(visit.getCreatedUserRid());
        }
        if (visit.getModifiedUserRid() != null) {
            visitData.setModifiedUserRid(visit.getModifiedUserRid());
        }

        return visitData;
    }

    //-----------------------------Complaints-------------------------
    public List<Complaints> convertComplaintsDataToComplaints(
            List<ComplaintsData> complaintsDataList) throws DcometServiceException {
        List<Complaints> complaintsList = new ArrayList<>();
        for (ComplaintsData complaintsData : complaintsDataList) {
            complaintsList.add(convertComplaintsDataToComplaints(complaintsData));
        }
        return complaintsList;
    }

    public List<ComplaintsData> convertComplaintsToComplaintsData(List<Complaints> complaintsList)
            throws DcometServiceException {
        List<ComplaintsData> complaintsDataList = new ArrayList<>();
        for (Complaints complaints : complaintsList) {
            complaintsDataList.add(convertComplaintsToComplaintsData(complaints));
        }
        return complaintsDataList;
    }

    public Complaints convertComplaintsDataToComplaints(ComplaintsData complaintsData)
            throws DcometServiceException {
        Complaints complaints = new Complaints();

        if (complaintsData.getId() != null) {
            complaints.setId(complaintsData.getId());
        }
        if (complaintsData.getCplVisitRID() != null) {
            complaints.setCplVisitRID(complaintsData.getCplVisitRID());
        }
        if (complaintsData.getCplPatRID() != null) {
            complaints.setCplPatRID(complaintsData.getCplPatRID());
        }
        if (complaintsData.getCplName() != null) {
            complaints.setCplName(complaintsData.getCplName());
        }
        if (complaintsData.getCplNameIndex() != null) {
            complaints.setCplNameIndex(complaintsData.getCplNameIndex());
        }
        if (complaintsData.getCplDesc() != null) {
            complaints.setCplDesc(complaintsData.getCplDesc());
        }
        if (complaintsData.getCplDescRangeIndex() != null) {
            complaints.setCplDescRangeIndex(complaintsData.getCplDescRangeIndex());
        }
        if (complaintsData.getCplCurrentStatus() != null) {
            complaints.setCplCurrentStatus(complaintsData.getCplCurrentStatus());
        }
        if (complaintsData.getCplCurrentStatusIndex() != null) {
            complaints.setCplCurrentStatusIndex(complaintsData.getCplCurrentStatusIndex());
        }
        if (complaintsData.getCplEntityRid() != null) {
            complaints.setEntityRid(complaintsData.getCplEntityRid());
        }
        if (complaintsData.getCreatedUserRid() != null) {
            complaints.setCreatedUserRid(complaintsData.getCreatedUserRid());
        }
        if (complaintsData.getCreatedDateTime() != null) {
            complaints.setCreatedDateTime(DateUtil.convertCalendarToString(complaintsData.getCreatedDateTime()));
        }
        if (complaintsData.getModifiedUserRid() != null) {
            complaints.setModifiedUserRid(complaintsData.getModifiedUserRid());
        }
        if (complaintsData.getModifiedDateTime() != null) {
            complaints.setModifiedDateTime(DateUtil.convertCalendarToString(complaintsData.getModifiedDateTime()));
        }

        return complaints;
    }

    public ComplaintsData convertComplaintsToComplaintsData(Complaints complaints)
            throws DcometServiceException {
        ComplaintsData complaintsData = new ComplaintsData();

        if (complaints.getId() != null) {
            complaintsData.setId(complaints.getId());
        }
        if (complaints.getCplVisitRID() != null) {
            complaintsData.setCplVisitRID(complaints.getCplVisitRID());
        }
        if (complaints.getCplPatRID() != null) {
            complaintsData.setCplPatRID(complaints.getCplPatRID());
        }
        if (complaints.getCplName() != null) {
            complaintsData.setCplName(complaints.getCplName());
        }
        if (complaints.getCplNameIndex() != null) {
            complaintsData.setCplNameIndex(complaints.getCplNameIndex());
        }
        if (complaints.getCplDesc() != null) {
            complaintsData.setCplDesc(complaints.getCplDesc());
        }
        if (complaints.getCplDescRangeIndex() != null) {
            complaintsData.setCplDescRangeIndex(complaints.getCplDescRangeIndex());
        }
        if (complaints.getCplCurrentStatus() != null) {
            complaintsData.setCplCurrentStatus(complaints.getCplCurrentStatus());
        }
        if (complaints.getCplCurrentStatusIndex() != null) {
            complaintsData.setCplCurrentStatusIndex(complaints.getCplCurrentStatusIndex());
        }
        if (complaints.getEntityRid() != null) {
            complaintsData.setCplEntityRid(complaints.getEntityRid());
        }
        if (complaints.getCreatedUserRid() != null) {
            complaintsData.setCreatedUserRid(complaints.getCreatedUserRid());
        }
        if (complaints.getModifiedUserRid() != null) {
            complaintsData.setModifiedUserRid(complaints.getModifiedUserRid());
        }

        return complaintsData;
    }

    //-----------------------------VisitPlan-------------------------
    public List<VisitPlan> convertVisitPlanDataToVisitPlan(
            List<VisitPlanData> visitPlanDataList) throws DcometServiceException {
        List<VisitPlan> visitPlanList = new ArrayList<>();
        for (VisitPlanData visitPlanData : visitPlanDataList) {
            visitPlanList.add(convertVisitPlanDataToVisitPlan(visitPlanData));
        }
        return visitPlanList;
    }

    public List<VisitPlanData> convertVisitPlanToVisitPlanData(List<VisitPlan> visitPlanList)
            throws DcometServiceException {
        List<VisitPlanData> visitPlanDataList = new ArrayList<>();
        for (VisitPlan visitPlan : visitPlanList) {
            visitPlanDataList.add(convertVisitPlanToVisitPlanData(visitPlan));
        }
        return visitPlanDataList;
    }

    public VisitPlan convertVisitPlanDataToVisitPlan(VisitPlanData visitPlanData)
            throws DcometServiceException {
        VisitPlan visitPlan = new VisitPlan();

        if (visitPlanData.getId() != null) {
            visitPlan.setId(visitPlanData.getId());
        }
        if (visitPlanData.getVispPatRID() != null) {
            visitPlan.setVispPatRID(visitPlanData.getVispPatRID());
        }
        if (visitPlanData.getVispEntityRid() != null) {
            visitPlan.setEntityRid(visitPlanData.getVispEntityRid());
        }
        if (visitPlanData.getCreatedUserRid() != null) {
            visitPlan.setCreatedUserRid(visitPlanData.getCreatedUserRid());
        }
        if (visitPlanData.getCreatedDateTime() != null) {
            visitPlan.setCreatedDateTime(DateUtil.convertCalendarToString(visitPlanData.getCreatedDateTime()));
        }
        if (visitPlanData.getModifiedUserRid() != null) {
            visitPlan.setModifiedUserRid(visitPlanData.getModifiedUserRid());
        }
        if (visitPlanData.getModifiedDateTime() != null) {
            visitPlan.setModifiedDateTime(DateUtil.convertCalendarToString(visitPlanData.getModifiedDateTime()));
        }

        return visitPlan;
    }

    public VisitPlanData convertVisitPlanToVisitPlanData(VisitPlan visitPlan)
            throws DcometServiceException {
        VisitPlanData visitPlanData = new VisitPlanData();

        if (visitPlan.getId() != null) {
            visitPlanData.setId(visitPlan.getId());
        }
        if (visitPlan.getVispPatRID() != null) {
            visitPlanData.setVispPatRID(visitPlan.getVispPatRID());
        }
        if (visitPlan.getEntityRid() != null) {
            visitPlanData.setVispEntityRid(visitPlan.getEntityRid());
        }
        if (visitPlan.getCreatedUserRid() != null) {
            visitPlanData.setCreatedUserRid(visitPlan.getCreatedUserRid());
        }

        if (visitPlan.getModifiedUserRid() != null) {
            visitPlanData.setModifiedUserRid(visitPlan.getModifiedUserRid());
        }

        return visitPlanData;
    }

    //-----------------------------VisitVitals-------------------------
    public List<VisitVitals> convertVisitVitalsDataToVisitVitals(
            List<VisitVitalsData> visitVitalsDataList) throws DcometServiceException {
        List<VisitVitals> visitVitalsList = new ArrayList<>();
        for (VisitVitalsData visitVitalsData : visitVitalsDataList) {
            visitVitalsList.add(convertVisitVitalsDataToVisitVitals(visitVitalsData));
        }
        return visitVitalsList;
    }

    public List<VisitVitalsData> convertVisitVitalsToVisitVitalsData(List<VisitVitals> visitVitalsList)
            throws DcometServiceException {
        List<VisitVitalsData> visitVitalsDataList = new ArrayList<>();
        for (VisitVitals visitVitals : visitVitalsList) {
            visitVitalsDataList.add(convertVisitVitalsToVisitVitalsData(visitVitals));
        }
        return visitVitalsDataList;
    }

    public VisitVitals convertVisitVitalsDataToVisitVitals(VisitVitalsData visitVitalsData)
            throws DcometServiceException {
        VisitVitals visitVitals = new VisitVitals();

        if (visitVitalsData.getId() != null) {
            visitVitals.setId(visitVitalsData.getId());
        }
        if (visitVitalsData.getVitVisRID() != null) {
            visitVitals.setVitVisRID(visitVitalsData.getVitVisRID());
        }
        if (visitVitalsData.getVitPatRID() != null) {
            visitVitals.setVitPatRID(visitVitalsData.getVitPatRID());
        }
        if (visitVitalsData.getVitName() != null) {
            visitVitals.setVitName(visitVitalsData.getVitName());
        }
        if (visitVitalsData.getVitDdictIndex() != null) {
            visitVitals.setVitDdictIndex(visitVitalsData.getVitDdictIndex());
        }
        if (visitVitalsData.getVitUom() != null) {
            visitVitals.setVitUom(visitVitalsData.getVitUom());
        }
        if (visitVitalsData.getVitValue() != null) {
            visitVitals.setVitValue(visitVitalsData.getVitValue());
        }
        if (visitVitalsData.getVitEntityRid() != null) {
            visitVitals.setEntityRid(visitVitalsData.getVitEntityRid());
        }
        if (visitVitalsData.getCreatedUserRid() != null) {
            visitVitals.setCreatedUserRid(visitVitalsData.getCreatedUserRid());
        }
        if (visitVitalsData.getCreatedDateTime() != null) {
            visitVitals.setCreatedDateTime(DateUtil.convertCalendarToString(visitVitalsData.getCreatedDateTime()));
        }
        if (visitVitalsData.getModifiedUserRid() != null) {
            visitVitals.setModifiedUserRid(visitVitalsData.getModifiedUserRid());
        }
        if (visitVitalsData.getModifiedDateTime() != null) {
            visitVitals.setModifiedDateTime(DateUtil.convertCalendarToString(visitVitalsData.getModifiedDateTime()));
        }

        return visitVitals;
    }

    public VisitVitalsData convertVisitVitalsToVisitVitalsData(VisitVitals visitVitals)
            throws DcometServiceException {
        VisitVitalsData visitVitalsData = new VisitVitalsData();

        if (visitVitals.getId() != null) {
            visitVitalsData.setId(visitVitals.getId());
        }
        if (visitVitals.getVitVisRID() != null) {
            visitVitalsData.setVitVisRID(visitVitals.getVitVisRID());
        }
        if (visitVitals.getVitPatRID() != null) {
            visitVitalsData.setVitPatRID(visitVitals.getVitPatRID());
        }
        if (visitVitals.getVitName() != null) {
            visitVitalsData.setVitName(visitVitals.getVitName());
        }
        if (visitVitals.getVitDdictIndex() != null) {
            visitVitalsData.setVitDdictIndex(visitVitals.getVitDdictIndex());
        }
        if (visitVitals.getVitUom() != null) {
            visitVitalsData.setVitUom(visitVitals.getVitUom());
        }
        if (visitVitals.getVitValue() != null) {
            visitVitalsData.setVitValue(visitVitals.getVitValue());
        }
        if (visitVitals.getEntityRid() != null) {
            visitVitalsData.setVitEntityRid(visitVitals.getEntityRid());
        }
        if (visitVitals.getCreatedUserRid() != null) {
            visitVitalsData.setCreatedUserRid(visitVitals.getCreatedUserRid());
        }
        if (visitVitals.getModifiedUserRid() != null) {
            visitVitalsData.setModifiedUserRid(visitVitals.getModifiedUserRid());
        }

        return visitVitalsData;
    }

    //-----------------------------VisitTemplate-------------------------
    public List<VisitTemplate> convertVisitTemplateDataToVisitTemplate(
            List<VisitTemplateData> visitTemplateDataList) throws DcometServiceException {
        List<VisitTemplate> visitTemplateList = new ArrayList<>();
        for (VisitTemplateData visitTemplateData : visitTemplateDataList) {
            visitTemplateList.add(convertVisitTemplateDataToVisitTemplate(visitTemplateData));
        }
        return visitTemplateList;
    }

    public List<VisitTemplateData> convertVisitTemplateToVisitTemplateData(List<VisitTemplate> visitTemplateList)
            throws DcometServiceException {
        List<VisitTemplateData> visitTemplateDataList = new ArrayList<>();
        for (VisitTemplate visitTemplate : visitTemplateList) {
            visitTemplateDataList.add(convertVisitTemplateToVisitTemplateData(visitTemplate));
        }
        return visitTemplateDataList;
    }

    public VisitTemplate convertVisitTemplateDataToVisitTemplate(VisitTemplateData visitTemplateData)
            throws DcometServiceException {
        VisitTemplate visitTemplate = new VisitTemplate();

        if (visitTemplateData.getId() != null) {
            visitTemplate.setId(visitTemplateData.getId());
        }
        if (visitTemplateData.getVistVisitRID() != null) {
            visitTemplate.setVistVisitRID(visitTemplateData.getVistVisitRID());
        }
        if (visitTemplateData.getVistGroup() != null) {
            visitTemplate.setVistGroup(visitTemplateData.getVistGroup());
        }
        if (visitTemplateData.getVistSubGroup() != null) {
            visitTemplate.setVistSubGroup(visitTemplateData.getVistSubGroup());
        }
        if (visitTemplateData.getVistType() != null) {
            visitTemplate.setVistType(visitTemplateData.getVistType());
        }
        if (visitTemplateData.getVistSpeciality() != null) {
            visitTemplate.setVistSpeciality(visitTemplateData.getVistSpeciality());
        }
        if (visitTemplateData.getVistPatRID() != null) {
            visitTemplate.setVistPatRID(visitTemplateData.getVistPatRID());
        }
        if (visitTemplateData.getVistEntityRid() != null) {
            visitTemplate.setEntityRid(visitTemplateData.getVistEntityRid());
        }
        if (visitTemplateData.getVistNodes() != null) {
            visitTemplate.setVistNodes(visitTemplateData.getVistNodes());
        }
        if (visitTemplateData.getVistSeqNum() != null) {
            visitTemplate.setVistSeqNum(visitTemplateData.getVistSeqNum());
        }
        if (visitTemplateData.getCreatedUserRid() != null) {
            visitTemplate.setCreatedUserRid(visitTemplateData.getCreatedUserRid());
        }
        if (visitTemplateData.getCreatedDateTime() != null) {
            visitTemplate.setCreatedDateTime(DateUtil.convertCalendarToString(visitTemplateData.getCreatedDateTime()));
        }
        if (visitTemplateData.getModifiedUserRid() != null) {
            visitTemplate.setModifiedUserRid(visitTemplateData.getModifiedUserRid());
        }
        if (visitTemplateData.getModifiedDateTime() != null) {
            visitTemplate.setModifiedDateTime(DateUtil.convertCalendarToString(visitTemplateData.getModifiedDateTime()));
        }

        return visitTemplate;
    }

    public VisitTemplateData convertVisitTemplateToVisitTemplateData(VisitTemplate visitTemplate)
            throws DcometServiceException {
        VisitTemplateData visitTemplateData = new VisitTemplateData();

        if (visitTemplate.getId() != null) {
            visitTemplateData.setId(visitTemplate.getId());
        }
        if (visitTemplate.getVistVisitRID() != null) {
            visitTemplateData.setVistVisitRID(visitTemplate.getVistVisitRID());
        }
        if (visitTemplate.getVistGroup() != null) {
            visitTemplateData.setVistGroup(visitTemplate.getVistGroup());
        }
        if (visitTemplate.getVistSubGroup() != null) {
            visitTemplateData.setVistSubGroup(visitTemplate.getVistSubGroup());
        }
        if (visitTemplate.getVistType() != null) {
            visitTemplateData.setVistType(visitTemplate.getVistType());
        }
        if (visitTemplate.getVistSpeciality() != null) {
            visitTemplateData.setVistSpeciality(visitTemplate.getVistSpeciality());
        }
        if (visitTemplate.getVistPatRID() != null) {
            visitTemplateData.setVistPatRID(visitTemplate.getVistPatRID());
        }
        if (visitTemplate.getEntityRid() != null) {
            visitTemplateData.setVistEntityRid(visitTemplate.getEntityRid());
        }
        if (visitTemplate.getVistNodes() != null) {
            visitTemplateData.setVistNodes(visitTemplate.getVistNodes());
        }
        if (visitTemplate.getVistSeqNum() != null) {
            visitTemplateData.setVistSeqNum(visitTemplate.getVistSeqNum());
        }
        if (visitTemplate.getCreatedUserRid() != null) {
            visitTemplateData.setCreatedUserRid(visitTemplate.getCreatedUserRid());
        }
        if (visitTemplate.getModifiedUserRid() != null) {
            visitTemplateData.setModifiedUserRid(visitTemplate.getModifiedUserRid());
        }

        return visitTemplateData;
    }

    //----------------------------------KinDetails-----------------------------
    public List<Kin> convertKinDataToKin(List<KinData> kinDataList) throws DcometServiceException {
        List<Kin> kinList = new ArrayList<>();
        for (KinData kinData : kinDataList) {
            kinList.add(convertKinDataToKin(kinData));
        }
        return kinList;
    }

    public List<KinData> convertKinToKinData(List<Kin> kinList) throws DcometServiceException {
        List<KinData> kinDataList = new ArrayList<>();
        for (Kin kin : kinList) {
            kinDataList.add(convertKinToKinData(kin));
        }
        return kinDataList;
    }

    public Kin convertKinDataToKin(KinData kinData) throws DcometServiceException {
        Kin kin = new Kin();

        if (kinData.getId() != null) {
            kin.setId(kinData.getId());
        }
        if (kinData.getKinPatRid() != null) {
            kin.setKinPatRid(kinData.getKinPatRid());
        }
        if (kinData.getKinName() != null) {
            kin.setKinName(kinData.getKinName());
        }
        if (kinData.getKinMobileNo() != null) {
            kin.setKinMobileNo(kinData.getKinMobileNo());
        }
        if (kinData.getKinOccupation() != null) {
            kin.setKinOccupation(kinData.getKinOccupation());
        }
        if (kinData.getKinRelationship() != null) {
            kin.setKinRelationship(kinData.getKinRelationship());
        }
        if (kinData.getKinIsActive() != null) {
            kin.setKinIsActive(kinData.getKinIsActive());
        }
        return kin;
    }

    public KinData convertKinToKinData(Kin kin) throws DcometServiceException {
        KinData kinData = new KinData();

        if (kin.getId() != null) {
            kinData.setId(kin.getId());
        }
        if (kin.getKinPatRid() != null) {
            kinData.setKinPatRid(kin.getKinPatRid());
        }
        if (kin.getKinName() != null) {
            kinData.setKinName(kin.getKinName());
        }
        if (kin.getKinMobileNo() != null) {
            kinData.setKinMobileNo(kin.getKinMobileNo());
        }
        if (kin.getKinOccupation() != null) {
            kinData.setKinOccupation(kin.getKinOccupation());
        }
        if (kin.getKinRelationship() != null) {
            kinData.setKinRelationship(kin.getKinRelationship());
        }
        if (kin.getKinIsActive() != null) {
            kinData.setKinIsActive(kin.getKinIsActive());
        }
        return kinData;
    }

    //----------------------------------History-----------------------------
    public List<History> convertHistoryDataToHistory(List<HistoryData> historyDataList) throws DcometServiceException {
        List<History> historyList = new ArrayList<>();
        for (HistoryData historyData : historyDataList) {
            historyList.add(convertHistoryDataToHistory(historyData));
        }
        return historyList;
    }

    public List<HistoryData> convertHistoryToHistoryData(List<History> historyList)
            throws DcometServiceException {
        List<HistoryData> historyDataList = new ArrayList<>();
        for (History history : historyList) {
            historyDataList.add(convertHistoryToHistoryData(history));
        }
        return historyDataList;
    }

    public History convertHistoryDataToHistory(HistoryData historyData) throws DcometServiceException {
        History history = new History();

        if (historyData.getId() != null) {
            history.setId(historyData.getId());
        }
        if (historyData.getHisVisitRid() != null) {
            history.setHisVisitRid(historyData.getHisVisitRid());
        }
        if (historyData.getHisPatRid() != null) {
            history.setHisPatRid(historyData.getHisPatRid());
        }
        if (historyData.getHisPoLEye() != null) {
            history.setHisPoLEye(historyData.getHisPoLEye());
        }
        if (historyData.getHisPoREye() != null) {
            history.setHisPoREye(historyData.getHisPoREye());
        }
        if (historyData.getHisMedPastMedicalHistory() != null) {
            history.setHisMedPastMedicalHistory(historyData.getHisMedPastMedicalHistory());
        }
        if (historyData.getHisMedPersonalHistory() != null) {
            history.setHisMedPersonalHistory(historyData.getHisMedPersonalHistory());
        }
        if (historyData.getHisMedDrugHistory() != null) {
            history.setHisMedDrugHistory(historyData.getHisMedDrugHistory());
        }
        if (historyData.getHisMedPastDentalHistory() != null) {
            history.setHisMedPastDentalHistory(historyData.getHisMedPastDentalHistory());
        }
        if (historyData.getHisMedAllergies() != null) {
            history.setHisMedAllergies(historyData.getHisMedAllergies());
        }
        if (historyData.getHisMedAllergiesStatus() != null) {
            history.setHisMedAllergiesStatus(historyData.getHisMedAllergiesStatus());
        }
        if (historyData.getHisMedDm() != null) {
            history.setHisMedDm(historyData.getHisMedDm());
        }
        if (historyData.getHisMedDmStatus() != null) {
            history.setHisMedDmStatus(historyData.getHisMedDmStatus());
        }
        if (historyData.getHisMedHt() != null) {
            history.setHisMedHt(historyData.getHisMedHt());
        }
        if (historyData.getHisMedHtStatus() != null) {
            history.setHisMedHtStatus(historyData.getHisMedHtStatus());
        }
        if (historyData.getHisMedAsthma() != null) {
            history.setHisMedAsthma(historyData.getHisMedAsthma());
        }
        if (historyData.getHisMedAsthmaStatus() != null) {
            history.setHisMedAsthmaStatus(historyData.getHisMedAsthmaStatus());
        }
        if (historyData.getHisMedEczema() != null) {
            history.setHisMedEczema(historyData.getHisMedEczema());
        }
        if (historyData.getHisMedEczemaStatus() != null) {
            history.setHisMedEczemaStatus(historyData.getHisMedEczemaStatus());
        }
        if (historyData.getHisMedGlaucoma() != null) {
            history.setHisMedGlaucoma(historyData.getHisMedGlaucoma());
        }
        if (historyData.getHisMedGlaucomaStatus() != null) {
            history.setHisMedGlaucomaStatus(historyData.getHisMedGlaucomaStatus());
        }
        if (historyData.getHisMedArthritis() != null) {
            history.setHisMedArthritis(historyData.getHisMedArthritis());
        }
        if (historyData.getHisMedArthritisStatus() != null) {
            history.setHisMedArthritisStatus(historyData.getHisMedArthritisStatus());
        }
        if (historyData.getHisMedOthers() != null) {
            history.setHisMedOthers(historyData.getHisMedOthers());
        }
        if (historyData.getHisMedOthersStatus() != null) {
            history.setHisMedOthersStatus(historyData.getHisMedOthersStatus());
        }
        if (historyData.getHisMedSurgicalHistory() != null) {
            history.setHisMedSurgicalHistory(historyData.getHisMedSurgicalHistory());
        }
        if (historyData.getHisFsFamilyHistory() != null) {
            history.setHisFsFamilyHistory(historyData.getHisFsFamilyHistory());
        }
        if (historyData.getHisFsDm() != null) {
            history.setHisFsDm(historyData.getHisFsDm());
        }
        if (historyData.getHisFsDmStatus() != null) {
            history.setHisFsDmStatus(historyData.getHisFsDmStatus());
        }
        if (historyData.getHisFsHt() != null) {
            history.setHisFsHt(historyData.getHisFsHt());
        }
        if (historyData.getHisFsHtStatus() != null) {
            history.setHisFsHtStatus(historyData.getHisFsHtStatus());
        }
        if (historyData.getHisFsAsthma() != null) {
            history.setHisFsAsthma(historyData.getHisFsAsthma());
        }
        if (historyData.getHisFsAsthmaStatus() != null) {
            history.setHisFsAsthmaStatus(historyData.getHisFsAsthmaStatus());
        }
        if (historyData.getHisFsGlaucoma() != null) {
            history.setHisFsGlaucoma(historyData.getHisFsGlaucoma());
        }
        if (historyData.getHisFsGlaucomaStatus() != null) {
            history.setHisFsGlaucomaStatus(historyData.getHisFsGlaucomaStatus());
        }
        if (historyData.getHisFsArthritis() != null) {
            history.setHisFsArthritis(historyData.getHisFsArthritis());
        }
        if (historyData.getHisFsArthritisStatus() != null) {
            history.setHisFsArthritisStatus(historyData.getHisFsArthritisStatus());
        }
        if (historyData.getHisFsSmoking() != null) {
            history.setHisFsSmoking(historyData.getHisFsSmoking());
        }
        if (historyData.getHisFsSmokingStatus() != null) {
            history.setHisFsSmokingStatus(historyData.getHisFsSmokingStatus());
        }
        if (historyData.getHisFsAlcohol() != null) {
            history.setHisFsAlcohol(historyData.getHisFsAlcohol());
        }
        if (historyData.getHisFsAlcoholStatus() != null) {
            history.setHisFsAlcoholStatus(historyData.getHisFsAlcoholStatus());
        }
        if (historyData.getHisFsOthers() != null) {
            history.setHisFsOthers(historyData.getHisFsOthers());
        }
        if (historyData.getHisFsOthersStatus() != null) {
            history.setHisFsOthersStatus(historyData.getHisFsOthersStatus());
        }
        if (historyData.getHisEntityRid() != null) {
            history.setHisEntityRid(historyData.getHisEntityRid());
        }
        if (historyData.getCreatedDateTime() != null) {
            history.setCreatedDateTime(DateUtil.convertCalendarToString(historyData.getCreatedDateTime()));
        }
        if (historyData.getCreatedUserRid() != null) {
            history.setCreatedUserRid(historyData.getCreatedUserRid());
        }
        if (historyData.getModifiedDateTime() != null) {
            history.setModifiedDateTime(DateUtil.convertCalendarToString(historyData.getModifiedDateTime()));
        }
        if (historyData.getModifiedUserRid() != null) {
            history.setModifiedUserRid(historyData.getModifiedUserRid());
        }

        return history;
    }

    public HistoryData convertHistoryToHistoryData(History history) throws DcometServiceException {
        HistoryData historyData = new HistoryData();

        if (history.getId() != null) {
            historyData.setId(history.getId());
        }
        if (history.getHisVisitRid() != null) {
            historyData.setHisVisitRid(history.getHisVisitRid());
        }
        if (history.getHisPatRid() != null) {
            historyData.setHisPatRid(history.getHisPatRid());
        }
        if (history.getHisPoLEye() != null) {
            historyData.setHisPoLEye(history.getHisPoLEye());
        }
        if (history.getHisPoREye() != null) {
            historyData.setHisPoREye(history.getHisPoREye());
        }
        if (history.getHisMedPastMedicalHistory() != null) {
            historyData.setHisMedPastMedicalHistory(history.getHisMedPastMedicalHistory());
        }
        if (history.getHisMedPersonalHistory() != null) {
            historyData.setHisMedPersonalHistory(history.getHisMedPersonalHistory());
        }
        if (history.getHisMedDrugHistory() != null) {
            historyData.setHisMedDrugHistory(history.getHisMedDrugHistory());
        }
        if (history.getHisMedPastDentalHistory() != null) {
            historyData.setHisMedPastDentalHistory(history.getHisMedPastDentalHistory());
        }
        if (history.getHisMedAllergies() != null) {
            historyData.setHisMedAllergies(history.getHisMedAllergies());
        }
        if (history.getHisMedAllergiesStatus() != null) {
            historyData.setHisMedAllergiesStatus(history.getHisMedAllergiesStatus());
        }
        if (history.getHisMedDm() != null) {
            historyData.setHisMedDm(history.getHisMedDm());
        }
        if (history.getHisMedDmStatus() != null) {
            historyData.setHisMedDmStatus(history.getHisMedDmStatus());
        }
        if (history.getHisMedHt() != null) {
            historyData.setHisMedHt(history.getHisMedHt());
        }
        if (history.getHisMedHtStatus() != null) {
            historyData.setHisMedHtStatus(history.getHisMedHtStatus());
        }
        if (history.getHisMedAsthma() != null) {
            historyData.setHisMedAsthma(history.getHisMedAsthma());
        }
        if (history.getHisMedAsthmaStatus() != null) {
            historyData.setHisMedAsthmaStatus(history.getHisMedAsthmaStatus());
        }
        if (history.getHisMedEczema() != null) {
            historyData.setHisMedEczema(history.getHisMedEczema());
        }
        if (history.getHisMedEczemaStatus() != null) {
            historyData.setHisMedEczemaStatus(history.getHisMedEczemaStatus());
        }
        if (history.getHisMedGlaucoma() != null) {
            historyData.setHisMedGlaucoma(history.getHisMedGlaucoma());
        }
        if (history.getHisMedGlaucomaStatus() != null) {
            historyData.setHisMedGlaucomaStatus(history.getHisMedGlaucomaStatus());
        }
        if (history.getHisMedArthritis() != null) {
            historyData.setHisMedArthritis(history.getHisMedArthritis());
        }
        if (history.getHisMedArthritisStatus() != null) {
            historyData.setHisMedArthritisStatus(history.getHisMedArthritisStatus());
        }
        if (history.getHisMedOthers() != null) {
            historyData.setHisMedOthers(history.getHisMedOthers());
        }
        if (history.getHisMedOthersStatus() != null) {
            historyData.setHisMedOthersStatus(history.getHisMedOthersStatus());
        }
        if (history.getHisMedSurgicalHistory() != null) {
            historyData.setHisMedSurgicalHistory(history.getHisMedSurgicalHistory());
        }
        if (history.getHisFsFamilyHistory() != null) {
            historyData.setHisFsFamilyHistory(history.getHisFsFamilyHistory());
        }
        if (history.getHisFsDm() != null) {
            historyData.setHisFsDm(history.getHisFsDm());
        }
        if (history.getHisFsDmStatus() != null) {
            historyData.setHisFsDmStatus(history.getHisFsDmStatus());
        }
        if (history.getHisFsHt() != null) {
            historyData.setHisFsHt(history.getHisFsHt());
        }
        if (history.getHisFsHtStatus() != null) {
            historyData.setHisFsHtStatus(history.getHisFsHtStatus());
        }
        if (history.getHisFsAsthma() != null) {
            historyData.setHisFsAsthma(history.getHisFsAsthma());
        }
        if (history.getHisFsAsthmaStatus() != null) {
            historyData.setHisFsAsthmaStatus(history.getHisFsAsthmaStatus());
        }
        if (history.getHisFsGlaucoma() != null) {
            historyData.setHisFsGlaucoma(history.getHisFsGlaucoma());
        }
        if (history.getHisFsGlaucomaStatus() != null) {
            historyData.setHisFsGlaucomaStatus(history.getHisFsGlaucomaStatus());
        }
        if (history.getHisFsArthritis() != null) {
            historyData.setHisFsArthritis(history.getHisFsArthritis());
        }
        if (history.getHisFsArthritisStatus() != null) {
            historyData.setHisFsArthritisStatus(history.getHisFsArthritisStatus());
        }
        if (history.getHisFsSmoking() != null) {
            historyData.setHisFsSmoking(history.getHisFsSmoking());
        }
        if (history.getHisFsSmokingStatus() != null) {
            historyData.setHisFsSmokingStatus(history.getHisFsSmokingStatus());
        }
        if (history.getHisFsAlcohol() != null) {
            historyData.setHisFsAlcohol(history.getHisFsAlcohol());
        }
        if (history.getHisFsAlcoholStatus() != null) {
            historyData.setHisFsAlcoholStatus(history.getHisFsAlcoholStatus());
        }
        if (history.getHisFsOthers() != null) {
            historyData.setHisFsOthers(history.getHisFsOthers());
        }
        if (history.getHisFsOthersStatus() != null) {
            historyData.setHisFsOthersStatus(history.getHisFsOthersStatus());
        }
        if (history.getHisEntityRid() != null) {
            historyData.setHisEntityRid(history.getEntityRid());
        }
        if (history.getCreatedDateTime() != null) {
            historyData.setCreatedDateTime(DateUtil.convertStringToCalendar(history.getCreatedDateTime()));
        }
        if (history.getCreatedUserRid() != null) {
            historyData.setCreatedUserRid(history.getCreatedUserRid());
        }
        if (history.getModifiedDateTime() != null) {
            historyData.setModifiedDateTime(DateUtil.convertStringToCalendar(history.getModifiedDateTime()));
        }
        if (history.getModifiedUserRid() != null) {
            historyData.setModifiedUserRid(history.getModifiedUserRid());
        }
        return historyData;
    }

    //----------------------------------FreeConsultationPatientDoctorMap-----------------------------
    public List<FreeConsultationPatientDoctorMap> convertFreeConsultationPatientDoctorMapDataToFreeConsultationPatientDoctorMap(List<FreeConsultationPatientDoctorMapData> freeConsultationPatientDoctorMapDataList) throws DcometServiceException {
        List<FreeConsultationPatientDoctorMap> freeConsultationPatientDoctorMaps = new ArrayList<>();
        for (FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData : freeConsultationPatientDoctorMapDataList) {
            freeConsultationPatientDoctorMaps.add(convertFreeConsultationPatientDoctorMapDataToFreeConsultationPatientDoctorMap(freeConsultationPatientDoctorMapData));
        }
        return freeConsultationPatientDoctorMaps;
    }

    public List<FreeConsultationPatientDoctorMapData> convertFreeConsultationPatientDoctorMapToFreeConsultationPatientDoctorMapData(List<FreeConsultationPatientDoctorMap> freeConsultationPatientDoctorMapList)
            throws DcometServiceException {
        List<FreeConsultationPatientDoctorMapData> freeConsultationPatientDoctorMapDataList = new ArrayList<>();
        for (FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap : freeConsultationPatientDoctorMapList) {
            freeConsultationPatientDoctorMapDataList.add(convertFreeConsultationPatientDoctorMapToFreeConsultationPatientDoctorMapData(freeConsultationPatientDoctorMap));
        }
        return freeConsultationPatientDoctorMapDataList;
    }

    public FreeConsultationPatientDoctorMap convertFreeConsultationPatientDoctorMapDataToFreeConsultationPatientDoctorMap(FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData) throws DcometServiceException {
        FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap = new FreeConsultationPatientDoctorMap();

        if (freeConsultationPatientDoctorMapData.getId() != null) {
            freeConsultationPatientDoctorMap.setId(freeConsultationPatientDoctorMapData.getId());
        }
        if (freeConsultationPatientDoctorMapData.getFcPatientRid() != null) {
            freeConsultationPatientDoctorMap.setFcPatientRid(freeConsultationPatientDoctorMapData.getFcPatientRid());
        }
        if (freeConsultationPatientDoctorMapData.getFcDoctorRid() != null) {
            freeConsultationPatientDoctorMap.setFcDoctorRid(freeConsultationPatientDoctorMapData.getFcDoctorRid());
        }
        if (freeConsultationPatientDoctorMapData.getFcCurrentState() != null) {
            freeConsultationPatientDoctorMap.setFcCurrentState(freeConsultationPatientDoctorMapData.getFcCurrentState());
        }
        if (freeConsultationPatientDoctorMapData.getFcPreviousState() != null) {
            freeConsultationPatientDoctorMap.setFcPreviousState(freeConsultationPatientDoctorMapData.getFcPreviousState());
        }
        if (freeConsultationPatientDoctorMapData.getFcCurrentStatus() != null) {
            freeConsultationPatientDoctorMap.setFcCurrentStatus(freeConsultationPatientDoctorMapData.getFcCurrentStatus());
        }
        if (freeConsultationPatientDoctorMapData.getFcLastVisitDate() != null) {
            freeConsultationPatientDoctorMap.setFcLastVisitDate(DateUtil.convertDateToString(freeConsultationPatientDoctorMapData.getFcLastVisitDate()));
        }
        if (freeConsultationPatientDoctorMapData.getFcLastPaidVisitDate() != null) {
            freeConsultationPatientDoctorMap.setFcLastPaidVisitDate(DateUtil.convertDateToString(freeConsultationPatientDoctorMapData.getFcLastPaidVisitDate()));
        }
        if (freeConsultationPatientDoctorMapData.getFcEntityRid() != null) {
            freeConsultationPatientDoctorMap.setFcEntityRid(freeConsultationPatientDoctorMapData.getFcEntityRid());
        }
        return freeConsultationPatientDoctorMap;
    }

    public FreeConsultationPatientDoctorMapData convertFreeConsultationPatientDoctorMapToFreeConsultationPatientDoctorMapData(FreeConsultationPatientDoctorMap freeConsultationPatientDoctorMap) throws DcometServiceException {
        FreeConsultationPatientDoctorMapData freeConsultationPatientDoctorMapData = new FreeConsultationPatientDoctorMapData();

        if (freeConsultationPatientDoctorMap.getId() != null) {
            freeConsultationPatientDoctorMapData.setId(freeConsultationPatientDoctorMap.getId());
        }
        if (freeConsultationPatientDoctorMap.getFcPatientRid() != null) {
            freeConsultationPatientDoctorMapData.setFcPatientRid(freeConsultationPatientDoctorMap.getFcPatientRid());
        }
        if (freeConsultationPatientDoctorMap.getFcDoctorRid() != null) {
            freeConsultationPatientDoctorMapData.setFcDoctorRid(freeConsultationPatientDoctorMap.getFcDoctorRid());
        }
        if (freeConsultationPatientDoctorMap.getFcCurrentState() != null) {
            freeConsultationPatientDoctorMapData.setFcCurrentState(freeConsultationPatientDoctorMap.getFcCurrentState());
        }
        if (freeConsultationPatientDoctorMap.getFcPreviousState() != null) {
            freeConsultationPatientDoctorMapData.setFcPreviousState(freeConsultationPatientDoctorMap.getFcPreviousState());
        }
        if (freeConsultationPatientDoctorMap.getFcCurrentStatus() != null) {
            freeConsultationPatientDoctorMapData.setFcCurrentStatus(freeConsultationPatientDoctorMap.getFcCurrentStatus());
        }
        if (freeConsultationPatientDoctorMap.getFcLastVisitDate() != null) {
            freeConsultationPatientDoctorMapData.setFcLastVisitDate(DateUtil.convertStringToDate(freeConsultationPatientDoctorMap.getFcLastVisitDate()));
        }
        if (freeConsultationPatientDoctorMap.getFcLastPaidVisitDate() != null) {
            freeConsultationPatientDoctorMapData.setFcLastPaidVisitDate(DateUtil.convertStringToDate(freeConsultationPatientDoctorMap.getFcLastPaidVisitDate()));
        }
        if (freeConsultationPatientDoctorMap.getEntityRid() != null) {
            freeConsultationPatientDoctorMapData.setFcEntityRid(freeConsultationPatientDoctorMap.getEntityRid());
        }
        if (freeConsultationPatientDoctorMap.getCreatedUserRid() != null) {
            freeConsultationPatientDoctorMapData.setCreatedUserRid(freeConsultationPatientDoctorMap.getCreatedUserRid());
        }
        if (freeConsultationPatientDoctorMap.getCreatedDateTime() != null) {
            freeConsultationPatientDoctorMapData.setCreatedDateTime(DateUtil.convertStringToCalendar(freeConsultationPatientDoctorMap.getCreatedDateTime()));
        }
        if (freeConsultationPatientDoctorMap.getModifiedUserRid() != null) {
            freeConsultationPatientDoctorMapData.setModifiedUserRid(freeConsultationPatientDoctorMap.getModifiedUserRid());
        }
        if (freeConsultationPatientDoctorMap.getModifiedDateTime() != null) {
            freeConsultationPatientDoctorMapData.setModifiedDateTime(DateUtil.convertStringToCalendar(freeConsultationPatientDoctorMap.getModifiedDateTime()));
        }
        return freeConsultationPatientDoctorMapData;
    }

//    //--------------ProcedureRequestH----------------------------       
//    public List<ProcedureRequestH> convertProcedureRequestHDataToProcedureRequestH(List<ProcedureRequestHData> procedureRequestHDataList) throws DcometServiceException {
//        List<ProcedureRequestH> procedureRequestHs = new ArrayList<>();
//        for (ProcedureRequestHData procedureRequestHData : procedureRequestHDataList) {
//            procedureRequestHs.add(convertProcedureRequestHDataToProcedureRequestH(procedureRequestHData));
//        }
//        return procedureRequestHs;
//    }
//
//    public List<ProcedureRequestHData> convertProcedureRequestHToProcedureRequestHData(List<ProcedureRequestH> procedureRequestHList)
//            throws DcometServiceException {
//        List<ProcedureRequestHData> procedureRequestHDataList = new ArrayList<>();
//        for (ProcedureRequestH procedureRequestH : procedureRequestHList) {
//            procedureRequestHDataList.add(convertProcedureRequestHToProcedureRequestHData(procedureRequestH));
//        }
//        return procedureRequestHDataList;
//    }
//
//    public ProcedureRequestH convertProcedureRequestHDataToProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometServiceException {
//        ProcedureRequestH procedureRequestH = new ProcedureRequestH();
//
//        if (procedureRequestHData.getId() != null) {
//            procedureRequestH.setId(procedureRequestHData.getId());
//        }
//        if (procedureRequestHData.getPrType() != null) {
//            procedureRequestH.setPrType(procedureRequestHData.getPrType());
//        }
//        if (procedureRequestHData.getPrCategory() != null) {
//            procedureRequestH.setPrCategory(procedureRequestHData.getPrCategory());
//        }
//        if (procedureRequestHData.getPrPatientRid() != null) {
//            procedureRequestH.setPrPatientRid(procedureRequestHData.getPrPatientRid());
//        }
//        if (procedureRequestHData.getPrPrimaryProcedure() != null) {
//            procedureRequestH.setPrPrimaryProcedure(procedureRequestHData.getPrPrimaryProcedure());
//        }
//        if (procedureRequestHData.getPrPrimaryDoctorRid() != null) {
//            procedureRequestH.setPrPrimaryDoctorRid(procedureRequestHData.getPrPrimaryDoctorRid());
//        }
//        if (procedureRequestHData.getPrProcedureRid() != null) {
//            procedureRequestH.setPrProcedureRid(procedureRequestHData.getPrProcedureRid());
//        }
//        if (procedureRequestHData.getPrVisitRid() != null) {
//            procedureRequestH.setPrVisitRid(procedureRequestHData.getPrVisitRid());
//        }
//        if (procedureRequestHData.getPrOtRoomRid() != null) {
//            procedureRequestH.setPrOtRoomRid(procedureRequestHData.getPrOtRoomRid());
//        }
//        if (procedureRequestHData.getPrSurgeryDateTime() != null) {
//            procedureRequestH.setPrSurgeryDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrSurgeryDateTime()));
//        }
//        if (procedureRequestHData.getPrEstimatedDuration() != null) {
//            procedureRequestH.setPrEstimatedDuration(procedureRequestHData.getPrEstimatedDuration());
//        }
//        if (procedureRequestHData.getPrEstimatedDurationMinIndex() != null) {
//            procedureRequestH.setPrEstimatedDurationMinIndex(procedureRequestHData.getPrEstimatedDurationMinIndex());
//        }
//        if (procedureRequestHData.getPrTechnicianRid() != null) {
//            procedureRequestH.setPrTechnicianRid(procedureRequestHData.getPrTechnicianRid());
//        }
//        if (procedureRequestHData.getPrOtAssistantRid() != null) {
//            procedureRequestH.setPrOtAssistantRid(procedureRequestHData.getPrOtAssistantRid());
//        }
//        if (procedureRequestHData.getPrAnesthesistType() != null) {
//            procedureRequestH.setPrAnesthesistType(procedureRequestHData.getPrAnesthesistType());
//        }
//        if (procedureRequestHData.getPrAnesthesistRid() != null) {
//            procedureRequestH.setPrAnesthesistRid(procedureRequestHData.getPrAnesthesistRid());
//        }
//        if (procedureRequestHData.getPrAnesthesistRequired() != null) {
//            procedureRequestH.setPrAnesthesistRequired(procedureRequestHData.getPrAnesthesistRequired());
//        }
//        if (procedureRequestHData.getPrIsTransplantRequested() != null) {
//            procedureRequestH.setPrIsTransplantRequested(procedureRequestHData.getPrIsTransplantRequested());
//        }
//        if (procedureRequestHData.getPrTransplantNotes() != null) {
//            procedureRequestH.setPrTransplantNotes(procedureRequestHData.getPrTransplantNotes());
//        }
//        if (procedureRequestHData.getPrIsBloodRequested() != null) {
//            procedureRequestH.setPrIsBloodRequested(procedureRequestHData.getPrIsBloodRequested());
//        }
//        if (procedureRequestHData.getPrBloodNotes() != null) {
//            procedureRequestH.setPrBloodNotes(procedureRequestHData.getPrBloodNotes());
//        }
//        if (procedureRequestHData.getPrState() != null) {
//            procedureRequestH.setPrState(procedureRequestHData.getPrState());
//        }
//        if (procedureRequestHData.getPrStatus() != null) {
//            procedureRequestH.setPrStatus(procedureRequestHData.getPrStatus());
//        }
//        if (procedureRequestHData.getPrInstructions() != null) {
//            procedureRequestH.setPrInstructions(procedureRequestHData.getPrInstructions());
//        }
//        if (procedureRequestHData.getPrRemarks() != null) {
//            procedureRequestH.setPrRemarks(procedureRequestHData.getPrRemarks());
//        }
//        if (procedureRequestHData.getPrOtComplications() != null) {
//            procedureRequestH.setPrOtComplications(procedureRequestHData.getPrOtComplications());
//        }
//        if (procedureRequestHData.getPrFollowUp() != null) {
//            procedureRequestH.setPrFollowUp(procedureRequestHData.getPrFollowUp());
//        }
//        if (procedureRequestHData.getPrCanDrinkNormally() != null) {
//            procedureRequestH.setPrCanDrinkNormally(procedureRequestHData.getPrCanDrinkNormally());
//        }
//        if (procedureRequestHData.getPrLastFluidTime() != null) {
//            procedureRequestH.setPrLastFluidTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrLastFluidTime()));
//        }
//        if (procedureRequestHData.getPrLastFoodTime() != null) {
//            procedureRequestH.setPrLastFoodTime(DateUtil.convertCalendarToString(procedureRequestHData.getPrLastFoodTime()));
//        }
//        if (procedureRequestHData.getCreatedUserRid() != null) {
//            procedureRequestH.setCreatedUserRid(procedureRequestHData.getCreatedUserRid());
//        }
//        if (procedureRequestHData.getCreatedDateTime() != null) {
//            procedureRequestH.setCreatedDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getCreatedDateTime()));
//        }
//        if (procedureRequestHData.getModifiedUserRid() != null) {
//            procedureRequestH.setModifiedUserRid(procedureRequestHData.getModifiedUserRid());
//        }
//        if (procedureRequestHData.getModifiedDateTime() != null) {
//            procedureRequestH.setModifiedDateTime(DateUtil.convertCalendarToString(procedureRequestHData.getModifiedDateTime()));
//        }
//        return procedureRequestH;
//    }
//
//    public ProcedureRequestHData convertProcedureRequestHToProcedureRequestHData(ProcedureRequestH procedureRequestH) throws DcometServiceException {
//        ProcedureRequestHData procedureRequestHData = new ProcedureRequestHData();
//
//        if (procedureRequestH.getId() != null) {
//            procedureRequestHData.setId(procedureRequestH.getId());
//        }
//        if (procedureRequestH.getPrType() != null) {
//            procedureRequestHData.setPrType(procedureRequestH.getPrType());
//        }
//        if (procedureRequestH.getPrCategory() != null) {
//            procedureRequestHData.setPrCategory(procedureRequestH.getPrCategory());
//        }
//        if (procedureRequestH.getPrPatientRid() != null) {
//            procedureRequestHData.setPrPatientRid(procedureRequestH.getPrPatientRid());
//        }
//        if (procedureRequestH.getPrPrimaryProcedure() != null) {
//            procedureRequestHData.setPrPrimaryProcedure(procedureRequestH.getPrPrimaryProcedure());
//        }
//        if (procedureRequestH.getPrProcedureRid() != null) {
//            procedureRequestHData.setPrProcedureRid(procedureRequestH.getPrProcedureRid());
//        }
//        if (procedureRequestH.getPrVisitRid() != null) {
//            procedureRequestHData.setPrVisitRid(procedureRequestH.getPrVisitRid());
//        }
//        if (procedureRequestH.getPrOtRoomRid() != null) {
//            procedureRequestHData.setPrOtRoomRid(procedureRequestH.getPrOtRoomRid());
//        }
//        if (procedureRequestH.getPrPrimaryDoctorRid() != null) {
//            procedureRequestHData.setPrPrimaryDoctorRid(procedureRequestH.getPrPrimaryDoctorRid());
//        }
//        if (procedureRequestH.getPrSurgeryDateTime() != null) {
//            procedureRequestHData.setPrSurgeryDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrSurgeryDateTime()));
//        }
//        if (procedureRequestH.getPrEstimatedDuration() != null) {
//            procedureRequestHData.setPrEstimatedDuration(procedureRequestH.getPrEstimatedDuration());
//        }
//        if (procedureRequestH.getPrEstimatedDurationMinIndex() != null) {
//            procedureRequestHData.setPrEstimatedDurationMinIndex(procedureRequestH.getPrEstimatedDurationMinIndex());
//        }
//        if (procedureRequestH.getPrTechnicianRid() != null) {
//            procedureRequestHData.setPrTechnicianRid(procedureRequestH.getPrTechnicianRid());
//        }
//        if (procedureRequestH.getPrOtAssistantRid() != null) {
//            procedureRequestHData.setPrOtAssistantRid(procedureRequestH.getPrOtAssistantRid());
//        }
//        if (procedureRequestH.getPrAnesthesistType() != null) {
//            procedureRequestHData.setPrAnesthesistType(procedureRequestH.getPrAnesthesistType());
//        }
//        if (procedureRequestH.getPrAnesthesistRid() != null) {
//            procedureRequestHData.setPrAnesthesistRid(procedureRequestH.getPrAnesthesistRid());
//        }
//        if (procedureRequestH.getPrAnesthesistRequired() != null) {
//            procedureRequestHData.setPrAnesthesistRequired(procedureRequestH.getPrAnesthesistRequired());
//        }
//        if (procedureRequestH.getPrIsTransplantRequested() != null) {
//            procedureRequestHData.setPrIsTransplantRequested(procedureRequestH.getPrIsTransplantRequested());
//        }
//        if (procedureRequestH.getPrTransplantNotes() != null) {
//            procedureRequestHData.setPrTransplantNotes(procedureRequestH.getPrTransplantNotes());
//        }
//        if (procedureRequestH.getPrIsBloodRequested() != null) {
//            procedureRequestHData.setPrIsBloodRequested(procedureRequestH.getPrIsBloodRequested());
//        }
//        if (procedureRequestH.getPrBloodNotes() != null) {
//            procedureRequestHData.setPrBloodNotes(procedureRequestH.getPrBloodNotes());
//        }
//        if (procedureRequestH.getPrState() != null) {
//            procedureRequestHData.setPrState(procedureRequestH.getPrState());
//        }
//        if (procedureRequestH.getPrStatus() != null) {
//            procedureRequestHData.setPrStatus(procedureRequestH.getPrStatus());
//        }
//        if (procedureRequestH.getPrInstructions() != null) {
//            procedureRequestHData.setPrInstructions(procedureRequestH.getPrInstructions());
//        }
//        if (procedureRequestH.getPrRemarks() != null) {
//            procedureRequestHData.setPrRemarks(procedureRequestH.getPrRemarks());
//        }
//        if (procedureRequestH.getPrOtComplications() != null) {
//            procedureRequestHData.setPrOtComplications(procedureRequestH.getPrOtComplications());
//        }
//        if (procedureRequestH.getPrFollowUp() != null) {
//            procedureRequestHData.setPrFollowUp(procedureRequestH.getPrFollowUp());
//        }
//        if (procedureRequestH.getPrCanDrinkNormally() != null) {
//            procedureRequestHData.setPrCanDrinkNormally(procedureRequestH.getPrCanDrinkNormally());
//        }
//        if (procedureRequestH.getPrLastFluidTime() != null) {
//            procedureRequestHData.setPrLastFluidTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrLastFluidTime()));
//        }
//        if (procedureRequestH.getPrLastFoodTime() != null) {
//            procedureRequestHData.setPrLastFoodTime(DateUtil.convertStringToCalendar(procedureRequestH.getPrLastFoodTime()));
//        }
//        if (procedureRequestH.getCreatedUserRid() != null) {
//            procedureRequestHData.setCreatedUserRid(procedureRequestH.getCreatedUserRid());
//        }
//        if (procedureRequestH.getCreatedDateTime() != null) {
//            procedureRequestHData.setCreatedDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getCreatedDateTime()));
//        }
//        if (procedureRequestH.getModifiedUserRid() != null) {
//            procedureRequestHData.setModifiedUserRid(procedureRequestH.getModifiedUserRid());
//        }
//        if (procedureRequestH.getModifiedDateTime() != null) {
//            procedureRequestHData.setModifiedDateTime(DateUtil.convertStringToCalendar(procedureRequestH.getModifiedDateTime()));
//        }
//        return procedureRequestHData;
//    }
//
//    //-----------ProcedureTechnician-----------
//    public List<ProcedureTechnician> convertProcedureTechnicianDataToProcedureTechnician(
//            List<ProcedureTechnicianData> resultData) throws DcometServiceException {
//        List<ProcedureTechnician> result = new ArrayList<>();
//        for (ProcedureTechnicianData child1Data : resultData) {
//            result.add(convertProcedureTechnicianDataToProcedureTechnician(child1Data));
//        }
//        return result;
//    }
//
//    public List<ProcedureTechnicianData> convertProcedureTechnicianToProcedureTechnicianData(List<ProcedureTechnician> procedureTechnicianList)
//            throws DcometServiceException {
//        List<ProcedureTechnicianData> procedureTechnicianDataList = new ArrayList<>();
//        for (ProcedureTechnician procedureTechnician : procedureTechnicianList) {
//            procedureTechnicianDataList.add(convertProcedureTechnicianToProcedureTechnicianData(procedureTechnician));
//        }
//        return procedureTechnicianDataList;
//    }
//
//    public ProcedureTechnician convertProcedureTechnicianDataToProcedureTechnician(ProcedureTechnicianData procedureTechnicianData)
//            throws DcometServiceException {
//        ProcedureTechnician procedureTechnician = new ProcedureTechnician();
//        if (procedureTechnicianData.getId() != null) {
//            procedureTechnician.setId(procedureTechnicianData.getId());
//        }
//        if (procedureTechnicianData.getProcedureTechRid() != null) {
//            procedureTechnician.setProcedureTechRid(procedureTechnicianData.getProcedureTechRid());
//        }
//        if (procedureTechnicianData.getProcedureRid() != null) {
//            procedureTechnician.setProcedureRid(procedureTechnicianData.getProcedureRid());
//        }
//        if (procedureTechnicianData.getProcedureEntityRid() != null) {
//            procedureTechnician.setProcedureEntityRid(procedureTechnicianData.getProcedureEntityRid());
//        }
//
//        return procedureTechnician;
//    }
//
//    public ProcedureTechnicianData convertProcedureTechnicianToProcedureTechnicianData(ProcedureTechnician procedureTechnician)
//            throws DcometServiceException {
//        ProcedureTechnicianData procedureTechnicianData = new ProcedureTechnicianData();
//        if (procedureTechnician.getId() != null) {
//            procedureTechnicianData.setId(procedureTechnician.getId());
//        }
//        if (procedureTechnician.getProcedureTechRid() != null) {
//            procedureTechnicianData.setProcedureTechRid(procedureTechnician.getProcedureTechRid());
//        }
//        if (procedureTechnician.getProcedureRid() != null) {
//            procedureTechnicianData.setProcedureRid(procedureTechnician.getProcedureRid());
//        }
//        if (procedureTechnician.getProcedureEntityRid() != null) {
//            procedureTechnicianData.setProcedureEntityRid(procedureTechnician.getProcedureEntityRid());
//        }
//
//        return procedureTechnicianData;
//    }
//
//    //-----------ProcedureAttenDoctor-----------
//    public List<ProcedureAttendDoctor> convertProcedureAttendDoctorDataToProcedureAttendDoctor(
//            List<ProcedureAttendDoctorData> resultData) throws DcometServiceException {
//        List<ProcedureAttendDoctor> result = new ArrayList<>();
//        for (ProcedureAttendDoctorData child1Data : resultData) {
//            result.add(convertProcedureAttendDoctorDataToProcedureAttendDoctor(child1Data));
//        }
//        return result;
//    }
//
//    public List<ProcedureAttendDoctorData> convertProcedureAttendDoctorToProcedureAttendDoctorData(List<ProcedureAttendDoctor> procedureAttendDoctorList)
//            throws DcometServiceException {
//        List<ProcedureAttendDoctorData> procedureAttendDoctorDataList = new ArrayList<>();
//        for (ProcedureAttendDoctor procedureAttendDoctor : procedureAttendDoctorList) {
//            procedureAttendDoctorDataList.add(convertProcedureAttendDoctorToProcedureAttendDoctorData(procedureAttendDoctor));
//        }
//        return procedureAttendDoctorDataList;
//    }
//
//    public ProcedureAttendDoctor convertProcedureAttendDoctorDataToProcedureAttendDoctor(ProcedureAttendDoctorData procedureAttendDoctorData)
//            throws DcometServiceException {
//        ProcedureAttendDoctor procedureAttendDoctor = new ProcedureAttendDoctor();
//        if (procedureAttendDoctorData.getId() != null) {
//            procedureAttendDoctor.setId(procedureAttendDoctorData.getId());
//        }
//        if (procedureAttendDoctorData.getProcedureAttDoctorRid() != null) {
//            procedureAttendDoctor.setProcedureAttDoctorRid(procedureAttendDoctorData.getProcedureAttDoctorRid());
//        }
//        if (procedureAttendDoctorData.getProcedureRid() != null) {
//            procedureAttendDoctor.setProcedureRid(procedureAttendDoctorData.getProcedureRid());
//        }
//        if (procedureAttendDoctorData.getProcedureEntityRid() != null) {
//            procedureAttendDoctor.setProcedureEntityRid(procedureAttendDoctorData.getProcedureEntityRid());
//        }
//
//        return procedureAttendDoctor;
//    }
//
//    public ProcedureAttendDoctorData convertProcedureAttendDoctorToProcedureAttendDoctorData(ProcedureAttendDoctor procedureAttendDoctor)
//            throws DcometServiceException {
//        ProcedureAttendDoctorData procedureAttendDoctorData = new ProcedureAttendDoctorData();
//        if (procedureAttendDoctor.getId() != null) {
//            procedureAttendDoctorData.setId(procedureAttendDoctor.getId());
//        }
//        if (procedureAttendDoctor.getProcedureAttDoctorRid() != null) {
//            procedureAttendDoctorData.setProcedureAttDoctorRid(procedureAttendDoctor.getProcedureAttDoctorRid());
//        }
//        if (procedureAttendDoctor.getProcedureRid() != null) {
//            procedureAttendDoctorData.setProcedureRid(procedureAttendDoctor.getProcedureRid());
//        }
//        if (procedureAttendDoctor.getProcedureEntityRid() != null) {
//            procedureAttendDoctorData.setProcedureEntityRid(procedureAttendDoctor.getProcedureEntityRid());
//        }
//
//        return procedureAttendDoctorData;
//    }
//
//    //-----------ProcedureTechnician-----------
//    public List<ProcedureNurse> convertProcedureNurseDataToProcedureNurse(
//            List<ProcedureNurseData> resultData) throws DcometServiceException {
//        List<ProcedureNurse> result = new ArrayList<>();
//        for (ProcedureNurseData child1Data : resultData) {
//            result.add(convertProcedureNurseDataToProcedureNurse(child1Data));
//        }
//        return result;
//    }
//
//    public List<ProcedureNurseData> convertProcedureNurseToProcedureNurseData(List<ProcedureNurse> procedureNurseList)
//            throws DcometServiceException {
//        List<ProcedureNurseData> procedureNurseDataList = new ArrayList<>();
//        for (ProcedureNurse procedureNurse : procedureNurseList) {
//            procedureNurseDataList.add(convertProcedureNurseToProcedureNurseData(procedureNurse));
//        }
//        return procedureNurseDataList;
//    }
//
//    public ProcedureNurse convertProcedureNurseDataToProcedureNurse(ProcedureNurseData procedureNurseData)
//            throws DcometServiceException {
//        ProcedureNurse procedureNurse = new ProcedureNurse();
//        if (procedureNurseData.getId() != null) {
//            procedureNurse.setId(procedureNurseData.getId());
//        }
//        if (procedureNurseData.getProcNurseRid() != null) {
//            procedureNurse.setProcNurseRid(procedureNurseData.getProcNurseRid());
//        }
//        if (procedureNurseData.getProcRid() != null) {
//            procedureNurse.setProcRid(procedureNurseData.getProcRid());
//        }
//        if (procedureNurseData.getProcEntityRid() != null) {
//            procedureNurse.setProcEntityRid(procedureNurseData.getProcEntityRid());
//        }
//
//        return procedureNurse;
//    }
//
//    public ProcedureNurseData convertProcedureNurseToProcedureNurseData(ProcedureNurse procedureNurse)
//            throws DcometServiceException {
//        ProcedureNurseData procedureNurseData = new ProcedureNurseData();
//        if (procedureNurse.getId() != null) {
//            procedureNurseData.setId(procedureNurse.getId());
//        }
//        if (procedureNurse.getProcNurseRid() != null) {
//            procedureNurseData.setProcNurseRid(procedureNurse.getProcNurseRid());
//        }
//        if (procedureNurse.getProcRid() != null) {
//            procedureNurseData.setProcRid(procedureNurse.getProcRid());
//        }
//        if (procedureNurse.getProcEntityRid() != null) {
//            procedureNurseData.setProcEntityRid(procedureNurse.getProcEntityRid());
//        }
//
//        return procedureNurseData;
//    }
//
//    //-----------ProcedureAnesthesist-----------
//    public List<ProcedureAnesthesist> convertProcedureAnesthesistDataToProcedureAnesthesist(
//            List<ProcedureAnesthesistData> resultData) throws DcometServiceException {
//        List<ProcedureAnesthesist> result = new ArrayList<>();
//        for (ProcedureAnesthesistData child1Data : resultData) {
//            result.add(convertProcedureAnesthesistDataToProcedureAnesthesist(child1Data));
//        }
//        return result;
//    }
//
//    public List<ProcedureAnesthesistData> convertProcedureAnesthesistToProcedureAnesthesistData(List<ProcedureAnesthesist> procedureAnesthesistList)
//            throws DcometServiceException {
//        List<ProcedureAnesthesistData> procedureAnesthesistDataList = new ArrayList<>();
//        for (ProcedureAnesthesist procedureAnesthesist : procedureAnesthesistList) {
//            procedureAnesthesistDataList.add(convertProcedureAnesthesistToProcedureAnesthesistData(procedureAnesthesist));
//        }
//        return procedureAnesthesistDataList;
//    }
//
//    public ProcedureAnesthesist convertProcedureAnesthesistDataToProcedureAnesthesist(ProcedureAnesthesistData procedureAnesthesistData)
//            throws DcometServiceException {
//        ProcedureAnesthesist procedureAnesthesist = new ProcedureAnesthesist();
//        if (procedureAnesthesistData.getId() != null) {
//            procedureAnesthesist.setId(procedureAnesthesistData.getId());
//        }
//        if (procedureAnesthesistData.getProcedureAnesthesRid() != null) {
//            procedureAnesthesist.setProcedureAnesthesRid(procedureAnesthesistData.getProcedureAnesthesRid());
//        }
//        if (procedureAnesthesistData.getProcedureRid() != null) {
//            procedureAnesthesist.setProcedureRid(procedureAnesthesistData.getProcedureRid());
//        }
//        if (procedureAnesthesistData.getProcedureEntityRid() != null) {
//            procedureAnesthesist.setProcedureEntityRid(procedureAnesthesistData.getProcedureEntityRid());
//        }
//
//        return procedureAnesthesist;
//    }
//
//    public ProcedureAnesthesistData convertProcedureAnesthesistToProcedureAnesthesistData(ProcedureAnesthesist procedureAnesthesist)
//            throws DcometServiceException {
//        ProcedureAnesthesistData procedureAnesthesistData = new ProcedureAnesthesistData();
//        if (procedureAnesthesist.getId() != null) {
//            procedureAnesthesistData.setId(procedureAnesthesist.getId());
//        }
//        if (procedureAnesthesist.getProcedureAnesthesRid() != null) {
//            procedureAnesthesistData.setProcedureAnesthesRid(procedureAnesthesist.getProcedureAnesthesRid());
//        }
//        if (procedureAnesthesist.getProcedureRid() != null) {
//            procedureAnesthesistData.setProcedureRid(procedureAnesthesist.getProcedureRid());
//        }
//        if (procedureAnesthesist.getProcedureEntityRid() != null) {
//            procedureAnesthesistData.setProcedureEntityRid(procedureAnesthesist.getProcedureEntityRid());
//        }
//
//        return procedureAnesthesistData;
//    }
    //-----------------------------AdmissionDetails-------------------------
    public List<AdmissionDetails> convertAdmissionDetailsDataToAdmissionDetails(
            List<AdmissionDetailsData> admissionDetailsDataList) throws DcometServiceException {
        List<AdmissionDetails> admissionDetailsList = new ArrayList<>();
        for (AdmissionDetailsData admissionDetailsData : admissionDetailsDataList) {
            admissionDetailsList.add(convertAdmissionDetailsDataToAdmissionDetails(admissionDetailsData));
        }
        return admissionDetailsList;
    }

    public List<AdmissionDetailsData> convertAdmissionDetailsToAdmissionDetailsData(List<AdmissionDetails> admissionDetailsList)
            throws DcometServiceException {
        List<AdmissionDetailsData> admissionDetailsDataList = new ArrayList<>();
        for (AdmissionDetails admissionDetails : admissionDetailsList) {
            admissionDetailsDataList.add(convertAdmissionDetailsToAdmissionDetailsData(admissionDetails));
        }
        return admissionDetailsDataList;
    }

    public AdmissionDetails convertAdmissionDetailsDataToAdmissionDetails(AdmissionDetailsData admissionDetailsData)
            throws DcometServiceException {
        AdmissionDetails admissionDetails = new AdmissionDetails();

        if (admissionDetailsData.getAdId() != null) {
            admissionDetails.setAdId(admissionDetailsData.getAdId());
        }
        if (admissionDetailsData.getAdPatientRid() != null) {
            admissionDetails.setAdPatientRid(admissionDetailsData.getAdPatientRid());
        }
        if (admissionDetailsData.getAdVisitRid() != null) {
            admissionDetails.setAdVisitRid(admissionDetailsData.getAdVisitRid());
        }
        if (admissionDetailsData.getAdAttnDoctorRid() != null) {
            admissionDetails.setAdAttnDoctorRid(admissionDetailsData.getAdAttnDoctorRid());
        }
        if (admissionDetailsData.getAdConsultingDoctorRid() != null) {
            admissionDetails.setAdConsultingDoctorRid(admissionDetailsData.getAdConsultingDoctorRid());
        }
        if (admissionDetailsData.getAdStaffInchargeRid() != null) {
            admissionDetails.setAdStaffInchargeRid(admissionDetailsData.getAdStaffInchargeRid());
        }
        if (admissionDetailsData.getAdAdmittingUnitRid() != null) {
            admissionDetails.setAdAdmittingUnitRid(admissionDetailsData.getAdAdmittingUnitRid());
        }
        if (admissionDetailsData.getAdEntityRid() != null) {
            admissionDetails.setEntityRid(admissionDetailsData.getAdEntityRid());
        }
        if (admissionDetailsData.getAdServiceUnitRid() != null) {
            admissionDetails.setAdServiceUnitRid(admissionDetailsData.getAdServiceUnitRid());
        }
        if (admissionDetailsData.getAdBedRid() != null) {
            admissionDetails.setAdBedRid(admissionDetailsData.getAdBedRid());
        }
        if (admissionDetailsData.getAdHasProcedure() != null) {
            admissionDetails.setAdHasProcedure(admissionDetailsData.getAdHasProcedure());
        }
        if (admissionDetailsData.getAdState() != null) {
            admissionDetails.setAdState(admissionDetailsData.getAdState());
        }
        if (admissionDetailsData.getAdStatus() != null) {
            admissionDetails.setAdStatus(admissionDetailsData.getAdStatus());
        }
        if (admissionDetailsData.getCreatedUserRid() != null) {
            admissionDetails.setCreatedUserRid(admissionDetailsData.getCreatedUserRid());
        }
        if (admissionDetailsData.getCreatedDateTime() != null) {
            admissionDetails.setCreatedDateTime(DateUtil.convertCalendarToString(admissionDetailsData.getCreatedDateTime()));
        }
        if (admissionDetailsData.getModifiedUserRid() != null) {
            admissionDetails.setModifiedUserRid(admissionDetailsData.getModifiedUserRid());
        }
        if (admissionDetailsData.getModifiedDateTime() != null) {
            admissionDetails.setModifiedDateTime(DateUtil.convertCalendarToString(admissionDetailsData.getModifiedDateTime()));
        }

        return admissionDetails;
    }

    public AdmissionDetailsData convertAdmissionDetailsToAdmissionDetailsData(AdmissionDetails admissionDetails)
            throws DcometServiceException {
        AdmissionDetailsData admissionDetailsData = new AdmissionDetailsData();

        if (admissionDetails.getAdId() != null) {
            admissionDetailsData.setAdId(admissionDetails.getAdId());
        }
        if (admissionDetails.getAdPatientRid() != null) {
            admissionDetailsData.setAdPatientRid(admissionDetails.getAdPatientRid());
        }
        if (admissionDetails.getAdVisitRid() != null) {
            admissionDetailsData.setAdVisitRid(admissionDetails.getAdVisitRid());
        }
        if (admissionDetails.getAdAttnDoctorRid() != null) {
            admissionDetailsData.setAdAttnDoctorRid(admissionDetails.getAdAttnDoctorRid());
        }
        if (admissionDetails.getAdConsultingDoctorRid() != null) {
            admissionDetailsData.setAdConsultingDoctorRid(admissionDetails.getAdConsultingDoctorRid());
        }
        if (admissionDetails.getAdStaffInchargeRid() != null) {
            admissionDetailsData.setAdStaffInchargeRid(admissionDetails.getAdStaffInchargeRid());
        }
        if (admissionDetails.getAdAdmittingUnitRid() != null) {
            admissionDetailsData.setAdAdmittingUnitRid(admissionDetails.getAdAdmittingUnitRid());
        }
        if (admissionDetails.getEntityRid() != null) {
            admissionDetailsData.setAdEntityRid(admissionDetails.getEntityRid());
        }
        if (admissionDetails.getAdServiceUnitRid() != null) {
            admissionDetailsData.setAdServiceUnitRid(admissionDetails.getAdServiceUnitRid());
        }
        if (admissionDetails.getAdBedRid() != null) {
            admissionDetailsData.setAdBedRid(admissionDetails.getAdBedRid());
        }
        if (admissionDetails.getAdHasProcedure() != null) {
            admissionDetailsData.setAdHasProcedure(admissionDetails.getAdHasProcedure());
        }
        if (admissionDetails.getAdState() != null) {
            admissionDetailsData.setAdState(admissionDetails.getAdState());
        }
        if (admissionDetails.getAdStatus() != null) {
            admissionDetailsData.setAdStatus(admissionDetails.getAdStatus());
        }
        if (admissionDetails.getCreatedUserRid() != null) {
            Integer value = admissionDetails.getCreatedUserRid();
            admissionDetailsData.setAdCreatedUserRid(value);
        }
        if (admissionDetails.getCreatedDateTime() != null) {
            admissionDetailsData.setAdCreatedDateTime(DateUtil.convertStringToCalendar(admissionDetails.getCreatedDateTime()));
        }
        if (admissionDetails.getModifiedUserRid() != null) {
            admissionDetailsData.setAdModifiedUserRid(admissionDetails.getModifiedUserRid());
        }
        if (admissionDetails.getModifiedDateTime() != null) {
            admissionDetailsData.setAdModifiedDateTime(DateUtil.convertStringToCalendar(admissionDetails.getModifiedDateTime()));
        }
        return admissionDetailsData;
    }

}
