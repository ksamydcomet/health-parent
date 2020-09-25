package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.adapter.ProcedureRequestAdapter;
import com.dcomet.health.dao.ProcedureRequestDAO;
import com.dcomet.health.dao.data.ProcedureAnesthesistData;
import com.dcomet.health.dao.data.ProcedureAttendDoctorData;
import com.dcomet.health.dao.data.ProcedureNurseData;
import com.dcomet.health.dao.data.ProcedureRequestHData;
import com.dcomet.health.dao.data.ProcedureTechnicianData;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.ProcedureAnesthesist;
import com.dcomet.health.domain.ProcedureAttendDoctor;
import com.dcomet.health.domain.ProcedureNurse;
import com.dcomet.health.domain.ProcedureRequestH;
import com.dcomet.health.domain.ProcedureRequestHSearchRequest;
import com.dcomet.health.domain.ProcedureTechnician;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.ItemOrderService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.ProcedureRequestService;
import com.dcomet.health.service.business.ServiceRequestService;
import com.dcomet.health.vo.BedChargeVo;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.ResourceAvailability;
import com.dcomet.module.domain.ResourceWorkingHours;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
 * @author ABDUL
 */
@Service("procedureRequestService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ProcedureRequestServiceImpl extends BaseWorkFlowServiceImpl implements ProcedureRequestService {

    @Autowired
    @Qualifier("procedureRequestAdapter")
    ProcedureRequestAdapter procedureRequestAdapter;

    @Autowired
    @Qualifier("procedureRequestDAO")
    ProcedureRequestDAO procedureRequestDAO;

    @Autowired
    @Qualifier("serviceRequestService")
    ServiceRequestService serviceRequestService;

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("billingService")
    BillingService billingService;

    @Autowired
    @Qualifier("masterService")
    MasterService masterService;

    @Autowired
    @Qualifier("itemOrderService")
    ItemOrderService itemOrderService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) throws DcometServiceException {
        ProcedureRequestH procedureRequestH = (ProcedureRequestH) object;
        ProcedureRequestHData procedureRequestHData = new ProcedureRequestHData();
        try {
            procedureRequestH.setPrState(nextState);
            procedureRequestH.setPrStatus(nextState);
            procedureRequestHData = procedureRequestAdapter.convertProcedureRequestHToProcedureRequestHData(procedureRequestH);
            procedureRequestDAO.saveProcedureRequestH(procedureRequestHData);

            if (CollectionUtils.isNotEmpty(procedureRequestH.getProcedureAttendDoctorList())) {
                List<ProcedureAttendDoctorData> procedureAttendDoctorDataList = procedureRequestAdapter.convertProcedureAttendDoctorToProcedureAttendDoctorData(procedureRequestH.getProcedureAttendDoctorList());
                for (ProcedureAttendDoctorData procedureAttendDoctorData : procedureAttendDoctorDataList) {
                    procedureAttendDoctorData.setProcedureRid(procedureRequestHData.getId());
                    procedureAttendDoctorData.setProcedureEntityRid(procedureRequestH.getEntityRid());
                }
                resourceAvailabilityDoctorSave(procedureRequestH, procedureAttendDoctorDataList);
                procedureRequestDAO.saveProcedureAttendDoctor(procedureAttendDoctorDataList);
            }

            if (CollectionUtils.isNotEmpty(procedureRequestH.getProcedureNurseList())) {
                List<ProcedureNurseData> procedureNurseDataList = procedureRequestAdapter.convertProcedureNurseToProcedureNurseData(procedureRequestH.getProcedureNurseList());
                for (ProcedureNurseData procedureNurseData : procedureNurseDataList) {
                    procedureNurseData.setProcRid(procedureRequestHData.getId());
                    procedureNurseData.setProcEntityRid(procedureRequestH.getEntityRid());
                }
                resourceAvailabilityNurseSave(procedureRequestH, procedureNurseDataList);
                procedureRequestDAO.saveProcedureNurse(procedureNurseDataList);
            }

            if (CollectionUtils.isNotEmpty(procedureRequestH.getProcedureTechnicianList())) {
                List<ProcedureTechnicianData> procedureTechnicianDataList = procedureRequestAdapter.convertProcedureTechnicianToProcedureTechnicianData(procedureRequestH.getProcedureTechnicianList());
                for (ProcedureTechnicianData procedureTechnicianData : procedureTechnicianDataList) {
                    procedureTechnicianData.setProcedureRid(procedureRequestHData.getId());
                    procedureTechnicianData.setProcedureEntityRid(procedureRequestH.getEntityRid());
                }
                resourceAvailabilityTechnisionSave(procedureRequestH, procedureTechnicianDataList);
                procedureRequestDAO.saveProcedureTechnician(procedureTechnicianDataList);
            }

            if (CollectionUtils.isNotEmpty(procedureRequestH.getProcedureAnesthesistList())) {
                List<ProcedureAnesthesistData> procedureAnesthesistDataList = procedureRequestAdapter.convertProcedureAnesthesistToProcedureAnesthesistData(procedureRequestH.getProcedureAnesthesistList());
                for (ProcedureAnesthesistData procedureAnesthesistData : procedureAnesthesistDataList) {
                    procedureAnesthesistData.setProcedureRid(procedureRequestHData.getId());
                    procedureAnesthesistData.setProcedureEntityRid(procedureRequestH.getEntityRid());
                }
                resourceAvailabilityAnesthesistSave(procedureRequestH, procedureAnesthesistDataList);
                procedureRequestDAO.saveProcedureAnesthesist(procedureAnesthesistDataList);
            }
            if (procedureRequestH.getPrOtRoomRid() != null) {
                resourceAvailabilityOtRoomSave(procedureRequestH);
            }
            if (CollectionUtils.isNotEmpty(procedureRequestH.getServiceRequestH())) {
                for (ServiceRequestH serviceRequestH : procedureRequestH.getServiceRequestH()) {
                    serviceRequestH.setCurrentObject(procedureRequestH);
                    serviceRequestH.setSerReqhProcedureRid(procedureRequestHData.getId());
                    serviceRequestService.save(serviceRequestH, 0, "SERVICE_ORDER_REQ", "REQUEST");
                }
            }
            if (CollectionUtils.isNotEmpty(procedureRequestH.getDrugRequestHs())) {
                for (DrugRequestH drugRequestH : procedureRequestH.getDrugRequestHs()) {
                    drugRequestH.setCurrentObject(procedureRequestH);
                    drugRequestH.setDrugReqHProcedureRid(procedureRequestHData.getId());
                    itemOrderService.save(drugRequestH, 0, "DRUG_REQUEST", "REQUEST");
                }
            }

            if (actionCode.equals("MARK_AS_DISCHARGE")) {
                BillH billH = billingService.getDraftBill(procedureRequestH.getPrPatientRid());
                if (billH.getBhEligibleAmount() == null) {
                    billH.setBhEligibleAmount(0f);
                    billH.setBhPaidAmount(0f);
                }
                billH.setCurrentObject(procedureRequestH);
                if (procedureRequestH.getPrCategory() != 1 && procedureRequestH.getBedChargeVo() != null) {
                    billH.setBhGrossAmount(billH.getBhGrossAmount() + procedureRequestH.getBedChargeVo().getBcServicePrice());
                    billH.setBhNetAmount(billH.getBhNetAmount() + procedureRequestH.getBedChargeVo().getBcServicePrice());
                }
                billingService.save(billH, billH.getId(), "BILL", "BUILTIN_ACTION");
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return procedureRequestHData.getId();
    }

    private String getProcedureFromTime(String scheduleDatetime, String estimatedDuration, String eDIndex) {
        String s1 = scheduleDatetime;
        String[] words = s1.split("\\s");
        String dte = words[0];
        String tim = words[1];
        return tim;
    }

    private String getProcedureToTime(String scheduleDatetime, String estimatedDuration, String eDIndex) {
        String newTime = "";
        try {
            String s1 = scheduleDatetime;
            String[] words = s1.split("\\s");
            String dte = words[0];
            String tim = words[1];
//            tim = tim + ":00";

            String eD = estimatedDuration;
            String edHr = "";
            String edM = "";
            if (eD.contains(":")) {
                String[] eds = eD.split(":");
                edHr = eds[0];
                edM = eds[1];
            } else {
                if (eDIndex == "Hrs") {
                    edHr = estimatedDuration;
                    edM = "0";
                } else {
                    edM = estimatedDuration;
                    edHr = "0";
                }
            }
            int edH1 = Integer.valueOf(edHr);
            int edM1 = Integer.valueOf(edM);
            int totMin = 0;
            if (edH1 != 0) {
                totMin = edH1 * 60;
            }
            if (edM1 != 0) {
                totMin = totMin + edM1;
            }

            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date d = df.parse(tim);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, totMin);
            newTime = df.format(cal.getTime());

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return newTime;
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

    private void resourceAvailabilityOtRoomSave(ProcedureRequestH procedureRequestH) {
        try {
            String procedureFromTime = getProcedureFromTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String procedureToTime = getProcedureToTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String s1 = procedureRequestH.getPrSurgeryDateTime();
            String[] words = s1.split("\\s");
            String dte = words[0];

            List<ResourceAvailability> resourceAvailabilitys = masterService.getResourceAvailabilityById(procedureRequestH.getPrOtRoomRid());
            if (CollectionUtils.isNotEmpty(resourceAvailabilitys)) {
                List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                for (ResourceAvailability resourceAvailability : resourceAvailabilitys) {
                    if (resourceAvailability.getAvailFromDateFromTime().equals(dte)) {
                        Time fAtime = Time.valueOf(resourceAvailability.getAvailFromDateFromTime());
                        Time tAtime = Time.valueOf(resourceAvailability.getAvailToDateToTime());
                        Time pFtime = Time.valueOf(procedureFromTime);
                        Time pTtime = Time.valueOf(procedureToTime);
                        if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                    resourceAvailability1.setCurrentObject(procedureRequestH);
                                    resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                    resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                    resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                    resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                    resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                    resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                    resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                    resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                    resourceAvailabilitys1.add(resourceAvailability1);
                                } else {
                                    ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                    resourceAvailability1.setCurrentObject(procedureRequestH);
                                    resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                    resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                    resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                    resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                    resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                    resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                    resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                    resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                    resourceAvailabilitys1.add(resourceAvailability1);
                                }
                            }
                            masterService.deleteResourceAvailability(resourceAvailability);
                            masterService.saveResourceAvailability(resourceAvailabilitys1);
                        }
                    }
                }
            } else {
                Time pFtime = Time.valueOf(procedureFromTime);
                Time pTtime = Time.valueOf(procedureToTime);
                List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
                Date myDate = DateUtil.convertStringToDate(dte);

                Calendar c = Calendar.getInstance();
                c.setTime(myDate);
                int dayI = c.get(Calendar.DAY_OF_WEEK);
                String dayS = getDayOfWeek(dayI);
                for (Ddict ddict : ddicts) {
                    if (Objects.equals(ddict.getDdictValue(), dayS)) {
                        dayI = ddict.getId();
                    }
                }

                List<ResourceWorkingHours> resourceWorkingHourses = masterService.getResourceWorkingHoursById(procedureRequestH.getPrOtRoomRid());
                List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                for (ResourceWorkingHours resourceWorkingHours : resourceWorkingHourses) {
                    if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                        String FromTime = resourceWorkingHours.getWhFromTime();
                        String ToTime = resourceWorkingHours.getWhToTime();

                        Time fAtime = Time.valueOf(FromTime);
                        Time tAtime = Time.valueOf(ToTime);

                        if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                    resourceAvailability1.setCurrentObject(procedureRequestH);
                                    resourceAvailability1.setAvailResourceRID(procedureRequestH.getPrOtRoomRid());
                                    resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                    resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                    resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                    resourceAvailabilitys1.add(resourceAvailability1);
                                } else {
                                    ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                    resourceAvailability1.setCurrentObject(procedureRequestH);
                                    resourceAvailability1.setAvailResourceRID(procedureRequestH.getPrOtRoomRid());
                                    resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                    resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                    resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                    resourceAvailabilitys1.add(resourceAvailability1);
                                }
                            }
                        } else {
                            ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                            resourceAvailability1.setCurrentObject(procedureRequestH);
                            resourceAvailability1.setAvailResourceRID(procedureRequestH.getPrOtRoomRid());
                            resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                            resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                            resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                            resourceAvailabilitys1.add(resourceAvailability1);
                        }
                    }
                }
                masterService.saveResourceAvailability(resourceAvailabilitys1);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    private void resourceAvailabilityDoctorSave(ProcedureRequestH procedureRequestH, List<ProcedureAttendDoctorData> procedureAttendDoctorDataList) {
        try {
            String procedureFromTime = getProcedureFromTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String procedureToTime = getProcedureToTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String s1 = procedureRequestH.getPrSurgeryDateTime();
            String[] words = s1.split("\\s");
            String dte = words[0];

            for (ProcedureAttendDoctorData procedureAttendDoctorData : procedureAttendDoctorDataList) {
                List<ResourceAvailability> resourceAvailabilitys = masterService.getResourceAvailabilityById(procedureAttendDoctorData.getProcedureAttDoctorRid());
                if (CollectionUtils.isNotEmpty(resourceAvailabilitys)) {
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceAvailability resourceAvailability : resourceAvailabilitys) {
                        if (resourceAvailability.getAvailFromDateFromTime().equals(dte)) {
                            Time fAtime = Time.valueOf(resourceAvailability.getAvailFromDateFromTime());
                            Time tAtime = Time.valueOf(resourceAvailability.getAvailToDateToTime());
                            Time pFtime = Time.valueOf(procedureFromTime);
                            Time pTtime = Time.valueOf(procedureToTime);
                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                                masterService.deleteResourceAvailability(resourceAvailability);
                                masterService.saveResourceAvailability(resourceAvailabilitys1);
                            }
                        }
                    }
                } else {
                    Time pFtime = Time.valueOf(procedureFromTime);
                    Time pTtime = Time.valueOf(procedureToTime);
                    List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
                    Date myDate = DateUtil.convertStringToDate(dte);

                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    int dayI = c.get(Calendar.DAY_OF_WEEK);
                    String dayS = getDayOfWeek(dayI);
                    for (Ddict ddict : ddicts) {
                        if (Objects.equals(ddict.getDdictValue(), dayS)) {
                            dayI = ddict.getId();
                        }
                    }

                    List<ResourceWorkingHours> resourceWorkingHourses = masterService.getResourceWorkingHoursById(procedureAttendDoctorData.getProcedureAttDoctorRid());
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceWorkingHours resourceWorkingHours : resourceWorkingHourses) {
                        if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                            String FromTime = resourceWorkingHours.getWhFromTime();
                            String ToTime = resourceWorkingHours.getWhToTime();

                            Time fAtime = Time.valueOf(FromTime);
                            Time tAtime = Time.valueOf(ToTime);

                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureAttendDoctorData.getProcedureAttDoctorRid());
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureAttendDoctorData.getProcedureAttDoctorRid());
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                            } else {
                                ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                resourceAvailability1.setCurrentObject(procedureRequestH);
                                resourceAvailability1.setAvailResourceRID(procedureAttendDoctorData.getProcedureAttDoctorRid());
                                resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                resourceAvailabilitys1.add(resourceAvailability1);
                            }
                        }
                    }
                    masterService.saveResourceAvailability(resourceAvailabilitys1);
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

    private void resourceAvailabilityNurseSave(ProcedureRequestH procedureRequestH, List<ProcedureNurseData> procedureNurseDataList) {
        try {
            String procedureFromTime = getProcedureFromTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String procedureToTime = getProcedureToTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String s1 = procedureRequestH.getPrSurgeryDateTime();
            String[] words = s1.split("\\s");
            String dte = words[0];
            for (ProcedureNurseData procedureNurseData : procedureNurseDataList) {
                List<ResourceAvailability> resourceAvailabilitys = masterService.getResourceAvailabilityById(procedureNurseData.getProcNurseRid());
                if (CollectionUtils.isNotEmpty(resourceAvailabilitys)) {
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceAvailability resourceAvailability : resourceAvailabilitys) {
                        if (resourceAvailability.getAvailFromDateFromTime().equals(dte)) {
                            Time fAtime = Time.valueOf(resourceAvailability.getAvailFromDateFromTime());
                            Time tAtime = Time.valueOf(resourceAvailability.getAvailToDateToTime());
                            Time pFtime = Time.valueOf(procedureFromTime);
                            Time pTtime = Time.valueOf(procedureToTime);
                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                                masterService.deleteResourceAvailability(resourceAvailability);
                                masterService.saveResourceAvailability(resourceAvailabilitys1);
                            }
                        }
                    }
                } else {
                    Time pFtime = Time.valueOf(procedureFromTime);
                    Time pTtime = Time.valueOf(procedureToTime);
                    List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
                    Date myDate = DateUtil.convertStringToDate(dte);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    int dayI = c.get(Calendar.DAY_OF_WEEK);
                    String dayS = getDayOfWeek(dayI);
                    for (Ddict ddict : ddicts) {
                        if (Objects.equals(ddict.getDdictValue(), dayS)) {
                            dayI = ddict.getId();
                        }
                    }
                    List<ResourceWorkingHours> resourceWorkingHourses = masterService.getResourceWorkingHoursById(procedureNurseData.getProcNurseRid());
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceWorkingHours resourceWorkingHours : resourceWorkingHourses) {
                        if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                            String FromTime = resourceWorkingHours.getWhFromTime();
                            String ToTime = resourceWorkingHours.getWhToTime();

                            Time fAtime = Time.valueOf(FromTime);
                            Time tAtime = Time.valueOf(ToTime);

                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureNurseData.getProcNurseRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureNurseData.getProcNurseRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                            } else {
                                ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                resourceAvailability1.setCurrentObject(procedureRequestH);
                                resourceAvailability1.setAvailResourceRID(procedureNurseData.getProcNurseRid());
                                resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                resourceAvailabilitys1.add(resourceAvailability1);
                            }
                        }
                    }
                    masterService.saveResourceAvailability(resourceAvailabilitys1);
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

    private void resourceAvailabilityTechnisionSave(ProcedureRequestH procedureRequestH, List<ProcedureTechnicianData> procedureTechnicianDataList) {
        try {
            String procedureFromTime = getProcedureFromTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String procedureToTime = getProcedureToTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String s1 = procedureRequestH.getPrSurgeryDateTime();
            String[] words = s1.split("\\s");
            String dte = words[0];
            for (ProcedureTechnicianData procedureTechnicianData : procedureTechnicianDataList) {
                List<ResourceAvailability> resourceAvailabilitys = masterService.getResourceAvailabilityById(procedureTechnicianData.getProcedureTechRid());
                if (CollectionUtils.isNotEmpty(resourceAvailabilitys)) {
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceAvailability resourceAvailability : resourceAvailabilitys) {
                        if (resourceAvailability.getAvailFromDateFromTime().equals(dte)) {
                            Time fAtime = Time.valueOf(resourceAvailability.getAvailFromDateFromTime());
                            Time tAtime = Time.valueOf(resourceAvailability.getAvailToDateToTime());
                            Time pFtime = Time.valueOf(procedureFromTime);
                            Time pTtime = Time.valueOf(procedureToTime);
                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                                masterService.deleteResourceAvailability(resourceAvailability);
                                masterService.saveResourceAvailability(resourceAvailabilitys1);
                            }
                        }
                    }
                } else {
                    Time pFtime = Time.valueOf(procedureFromTime);
                    Time pTtime = Time.valueOf(procedureToTime);
                    List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
                    Date myDate = DateUtil.convertStringToDate(dte);

                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    int dayI = c.get(Calendar.DAY_OF_WEEK);
                    String dayS = getDayOfWeek(dayI);
                    for (Ddict ddict : ddicts) {
                        if (Objects.equals(ddict.getDdictValue(), dayS)) {
                            dayI = ddict.getId();
                        }
                    }

                    List<ResourceWorkingHours> resourceWorkingHourses = masterService.getResourceWorkingHoursById(procedureTechnicianData.getProcedureTechRid());
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceWorkingHours resourceWorkingHours : resourceWorkingHourses) {
                        if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                            String FromTime = resourceWorkingHours.getWhFromTime();
                            String ToTime = resourceWorkingHours.getWhToTime();

                            Time fAtime = Time.valueOf(FromTime);
                            Time tAtime = Time.valueOf(ToTime);

                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureTechnicianData.getProcedureTechRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureTechnicianData.getProcedureTechRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                            } else {
                                ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                resourceAvailability1.setCurrentObject(procedureRequestH);
                                resourceAvailability1.setAvailResourceRID(procedureTechnicianData.getProcedureTechRid());
                                resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                resourceAvailabilitys1.add(resourceAvailability1);
                            }
                        }
                    }
                    masterService.saveResourceAvailability(resourceAvailabilitys1);
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

    private void resourceAvailabilityAnesthesistSave(ProcedureRequestH procedureRequestH, List<ProcedureAnesthesistData> procedureAnesthesistDataList) {
        try {
            String procedureFromTime = getProcedureFromTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String procedureToTime = getProcedureToTime(procedureRequestH.getPrSurgeryDateTime(), procedureRequestH.getPrEstimatedDuration(), procedureRequestH.getPrEdIndexText());
            String s1 = procedureRequestH.getPrSurgeryDateTime();
            String[] words = s1.split("\\s");
            String dte = words[0];
            for (ProcedureAnesthesistData procedureAnesthesistData : procedureAnesthesistDataList) {
                List<ResourceAvailability> resourceAvailabilitys = masterService.getResourceAvailabilityById(procedureAnesthesistData.getProcedureAnesthesRid());
                if (CollectionUtils.isNotEmpty(resourceAvailabilitys)) {
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceAvailability resourceAvailability : resourceAvailabilitys) {
                        if (resourceAvailability.getAvailFromDateFromTime().equals(dte)) {
                            Time fAtime = Time.valueOf(resourceAvailability.getAvailFromDateFromTime());
                            Time tAtime = Time.valueOf(resourceAvailability.getAvailToDateToTime());
                            Time pFtime = Time.valueOf(procedureFromTime);
                            Time pTtime = Time.valueOf(procedureToTime);
                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailReason(resourceAvailability.getAvailReason());
                                        resourceAvailability1.setAvailRemarks(resourceAvailability.getAvailRemarks());
                                        resourceAvailability1.setAvailFlag(resourceAvailability.getAvailFlag());
                                        resourceAvailability1.setAvailResourceRID(resourceAvailability.getAvailResourceRID());
                                        resourceAvailability1.setAvailUnitRID(resourceAvailability.getAvailUnitRID());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                                masterService.deleteResourceAvailability(resourceAvailability);
                                masterService.saveResourceAvailability(resourceAvailabilitys1);
                            }
                        }
                    }
                } else {
                    Time pFtime = Time.valueOf(procedureFromTime);
                    Time pTtime = Time.valueOf(procedureToTime);
                    List<Ddict> ddicts = dataDictionaryService.getDdictByType("DAYS");
                    Date myDate = DateUtil.convertStringToDate(dte);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    int dayI = c.get(Calendar.DAY_OF_WEEK);
                    String dayS = getDayOfWeek(dayI);
                    for (Ddict ddict : ddicts) {
                        if (Objects.equals(ddict.getDdictValue(), dayS)) {
                            dayI = ddict.getId();
                        }
                    }

                    List<ResourceWorkingHours> resourceWorkingHourses = masterService.getResourceWorkingHoursById(procedureAnesthesistData.getProcedureAnesthesRid());
                    List<ResourceAvailability> resourceAvailabilitys1 = new ArrayList<>();
                    for (ResourceWorkingHours resourceWorkingHours : resourceWorkingHourses) {
                        if (Objects.equals(dayI, resourceWorkingHours.getWhDayOfWeek())) {
                            String FromTime = resourceWorkingHours.getWhFromTime();
                            String ToTime = resourceWorkingHours.getWhToTime();

                            Time fAtime = Time.valueOf(FromTime);
                            Time tAtime = Time.valueOf(ToTime);

                            if (fAtime.before(pFtime) && tAtime.after(pTtime)) {
                                for (int i = 0; i < 2; i++) {
                                    if (i == 0) {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureAnesthesistData.getProcedureAnesthesRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(pFtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    } else {
                                        ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                        resourceAvailability1.setCurrentObject(procedureRequestH);
                                        resourceAvailability1.setAvailResourceRID(procedureAnesthesistData.getProcedureAnesthesRid());
                                        resourceAvailability1.setAvailFromDateFromTime(String.valueOf(pTtime));
                                        resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                        resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                        resourceAvailabilitys1.add(resourceAvailability1);
                                    }
                                }
                            } else {
                                ResourceAvailability resourceAvailability1 = new ResourceAvailability();
                                resourceAvailability1.setCurrentObject(procedureRequestH);
                                resourceAvailability1.setAvailResourceRID(procedureAnesthesistData.getProcedureAnesthesRid());
                                resourceAvailability1.setAvailFromDateTime(procedureRequestH.getPrSurgeryDateTime());
                                resourceAvailability1.setAvailFromDateFromTime(String.valueOf(fAtime));
                                resourceAvailability1.setAvailToDateToTime(String.valueOf(tAtime));
                                resourceAvailabilitys1.add(resourceAvailability1);
                            }
                        }
                    }
                    masterService.saveResourceAvailability(resourceAvailabilitys1);
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
    public Integer getCurrentState(Integer boRID) throws DcometServiceException {
        ProcedureRequestH procedureRequestH = getProcedureRequestH(boRID);
        return procedureRequestH != null ? procedureRequestH.getPrState() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) throws DcometServiceException {
        ProcedureRequestH procedureRequestH = getProcedureRequestH(boRID);
        return procedureRequestH != null ? new StringBuilder(clinicalService.getPatient(procedureRequestH.getPrPatientRid()).getPatFullName()).append("&").append(procedureRequestH.getCreatedDateTime()).toString() : null;
    }

    private ProcedureRequestH getProcedureRequestH(Integer rid) throws DcometServiceException {
        ProcedureRequestHSearchRequest procedureRequestHSearchRequest = new ProcedureRequestHSearchRequest();
        procedureRequestHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", rid)}));
        List<ProcedureRequestH> procedureRequestHs = getProcedureRequestH(procedureRequestHSearchRequest, true);
        return CollectionUtils.isNotEmpty(procedureRequestHs) ? procedureRequestHs.get(0) : null;
    }

    @Override
    public List<ProcedureRequestH> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<ProcedureRequestH> result = new ArrayList<>();
        try {
            List<ProcedureRequestHData> resultData = procedureRequestDAO.getProcedureRequestH(procedureRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = procedureRequestAdapter.convertProcedureRequestHDataToProcedureRequestH(resultData);
                for (Integer i = 0; i < result.size(); i++) {
                    Patient patient = clinicalService.getPatient(result.get(i).getPrPatientRid());
                    result.get(i).setPrPatientName(patient.getPatFullName());
                    result.get(i).setPrPatientMobile(patient.getPatPhoneNo());
                    result.get(i).setPrPatientUhid(patient.getPatMrnNo());
                }
                if (result.get(0).getPrDraftBillId() == null) {
                    if (result.get(0).getPrVisitRid() != null) {
                        Visit visit = clinicalService.getVisit(result.get(0).getPrVisitRid());
                        if (visit.getVisIsCompleted() == null) {
                            BillH billH = billingService.getDraftBill(visit.getVisPatRid());
                            if (billH != null) {
                                result.get(0).setPrDraftBillId(billH.getId());
                            }
                        }
                    }
                } else {
                    BillH billH = billingService.getBillH(result.get(0).getPrDraftBillId());
                    result.get(0).setPrDraftBillState(billH.getBhState());
                }
                if (includeChilds) {
                    for (ProcedureRequestH procedureRequestH : result) {
                        ProcedureRequestHSearchRequest childSearchRequest = new ProcedureRequestHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("procedureRid", procedureRequestH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<ProcedureAttendDoctor> procedureAttendDoctorResult = getProcedureAttendDoctor(childSearchRequest);
                        if (procedureAttendDoctorResult != null && !procedureAttendDoctorResult.isEmpty()) {
                            procedureRequestH.setProcedureAttendDoctorList(procedureAttendDoctorResult);
                        }
                        searchCriterionList.clear();
                        searchCriterionList.add(Restrictions.eq("procRid", procedureRequestH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<ProcedureNurse> procedureNurseResult = getProcedureNurse(childSearchRequest);
                        if (procedureNurseResult != null && !procedureNurseResult.isEmpty()) {
                            procedureRequestH.setProcedureNurseList(procedureNurseResult);
                        }
                        searchCriterionList.clear();
                        searchCriterionList.add(Restrictions.eq("procedureRid", procedureRequestH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<ProcedureTechnician> procedureTechnicianResult = getProcedureTechnician(childSearchRequest);
                        if (procedureTechnicianResult != null && !procedureTechnicianResult.isEmpty()) {
                            procedureRequestH.setProcedureTechnicianList(procedureTechnicianResult);
                        }
                        searchCriterionList.clear();
                        searchCriterionList.add(Restrictions.eq("procedureRid", procedureRequestH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<ProcedureAnesthesist> procedureAnesthesists = getProcedureAnesthesist(childSearchRequest);
                        if (procedureAnesthesists != null && !procedureAnesthesists.isEmpty()) {
                            procedureRequestH.setProcedureAnesthesistList(procedureAnesthesists);
                        }
                        ServiceRequestHSearchRequest serRequestSearchRequest = new ServiceRequestHSearchRequest();
                        List<Criterion> searchCriterionLists = new ArrayList<>();
                        searchCriterionLists.add(Restrictions.eq("serReqhProcedureRid", procedureRequestH.getId()));
                        serRequestSearchRequest.setSearchCriterionList(searchCriterionLists);
                        List<ServiceRequestH> serviceRequestHResult = serviceRequestService.getServiceRequestH(serRequestSearchRequest, true);
                        if (serviceRequestHResult != null && !serviceRequestHResult.isEmpty()) {
                            procedureRequestH.setServiceRequestH(serviceRequestHResult);
                        }
                        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
                        List<Criterion> searchCriterionLists1 = new ArrayList<>();
                        searchCriterionLists1.add(Restrictions.eq("drugReqHProcedureRid", procedureRequestH.getId()));
                        drugRequestHSearchRequest.setSearchCriterionList(searchCriterionLists1);
                        List<DrugRequestH> drugRequestHs = itemOrderService.getDrugH(drugRequestHSearchRequest, true);
                        if (drugRequestHs != null && !drugRequestHs.isEmpty()) {
                            procedureRequestH.setDrugRequestHs(drugRequestHs);
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
    public void saveProcedureRequestH(ProcedureRequestH procedureRequestH) throws DcometDAOException {
        try {
            ProcedureRequestHData procedureRequestHData = procedureRequestAdapter.convertProcedureRequestHToProcedureRequestHData(procedureRequestH);
            procedureRequestDAO.saveProcedureRequestH(procedureRequestHData);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctor> procedureAttendDoctorList) {
        try {
            List<ProcedureAttendDoctorData> procedureAttendDoctorDataList = procedureRequestAdapter.convertProcedureAttendDoctorToProcedureAttendDoctorData(procedureAttendDoctorList);
            procedureRequestDAO.saveProcedureAttendDoctor(procedureAttendDoctorDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<ProcedureAttendDoctor> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException {
        List<ProcedureAttendDoctor> result = null;
        try {
            List<ProcedureAttendDoctorData> resultData = procedureRequestDAO.getProcedureAttendDoctor(procedureRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = procedureRequestAdapter.convertProcedureAttendDoctorDataToProcedureAttendDoctor(resultData);
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
    public void saveProcedureNurse(List<ProcedureNurse> procedureNurseList) throws DcometServiceException {
        try {
            List<ProcedureNurseData> procedureNurseDataList = procedureRequestAdapter.convertProcedureNurseToProcedureNurseData(procedureNurseList);
            procedureRequestDAO.saveProcedureNurse(procedureNurseDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<ProcedureNurse> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException {
        List<ProcedureNurse> result = null;
        try {
            List<ProcedureNurseData> resultData = procedureRequestDAO.getProcedureNurse(procedureRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = procedureRequestAdapter.convertProcedureNurseDataToProcedureNurse(resultData);
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
    public void saveProcedureTechnician(List<ProcedureTechnician> procedureTechnicianList) throws DcometServiceException {
        try {
            List<ProcedureTechnicianData> procedureTechnicianDataList = procedureRequestAdapter.convertProcedureTechnicianToProcedureTechnicianData(procedureTechnicianList);
            procedureRequestDAO.saveProcedureTechnician(procedureTechnicianDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<ProcedureTechnician> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException {
        List<ProcedureTechnician> result = null;
        try {
            List<ProcedureTechnicianData> resultData = procedureRequestDAO.getProcedureTechnician(procedureRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = procedureRequestAdapter.convertProcedureTechnicianDataToProcedureTechnician(resultData);
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
    public List<ProcedureAnesthesist> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException {
        List<ProcedureAnesthesist> result = null;
        try {
            List<ProcedureAnesthesistData> resultData = procedureRequestDAO.getProcedureAnesthesist(procedureRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = procedureRequestAdapter.convertProcedureAnesthesistDataToProcedureAnesthesist(resultData);
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
}
