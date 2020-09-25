package com.dcomet.health.adapter;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.BedCancellationHistoryData;
import com.dcomet.health.dao.data.BedChargeDefinitionData;
import com.dcomet.health.dao.data.BedMasterData;
import com.dcomet.health.dao.data.BedOccupancyData;
import com.dcomet.health.dao.data.BedOccupancyDetailsData;
import com.dcomet.health.dao.data.BedTransferData;
import com.dcomet.health.dao.data.BedTransferRequestData;
import com.dcomet.health.dao.data.BedGroupMData;
import com.dcomet.health.dao.data.BedTypeOccupancySummaryData;
import com.dcomet.health.dao.data.BedWardData;
import com.dcomet.health.dao.data.BirthDetailsData;
import com.dcomet.health.dao.data.DischargeData;
import com.dcomet.health.domain.BedMaster;
import com.dcomet.health.domain.BedCancellationHistory;
import com.dcomet.health.domain.BedChargeDefinition;
import com.dcomet.health.domain.BedOccupancy;
import com.dcomet.health.domain.BedOccupancyDetails;
import com.dcomet.health.domain.BedTransfer;
import com.dcomet.health.domain.BedTransferRequest;
import com.dcomet.health.domain.BedGroupM;
import com.dcomet.health.domain.BedTypeOccupancySummary;
import com.dcomet.health.domain.BedWard;
import com.dcomet.health.domain.BirthDetails;
import com.dcomet.health.domain.Discharge;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("bedManagementAdapter")

public class BedManagementAdapter {

    //-------------------------------------------------------BedCancellationHistory--------------------------------------------------------
    public List<BedCancellationHistory> convertBedCancellationHistoryDatatoBedCancellationHistory(List<BedCancellationHistoryData> bedCancellationHistoryDataList) throws DcometServiceException {
        List<BedCancellationHistory> bedCancellationHistoryList = new ArrayList<>();
        for (BedCancellationHistoryData bedCancellationHistoryData : bedCancellationHistoryDataList) {
            bedCancellationHistoryList.add(convertBedCancellationHistoryDatatoBedCancellationHistory(bedCancellationHistoryData));
        }
        return bedCancellationHistoryList;
    }

    public List<BedCancellationHistoryData> convertBedCancellationHistorytoBedCancellationHistoryData(List<BedCancellationHistory> bedCancellationHistoryList) throws DcometServiceException {
        List<BedCancellationHistoryData> bedCancellationHistoryDataList = new ArrayList<>();
        for (BedCancellationHistory bedCancellationHistory : bedCancellationHistoryList) {
            bedCancellationHistoryDataList.add(convertBedCancellationHistorytoBedCancellationHistoryData(bedCancellationHistory));
        }
        return bedCancellationHistoryDataList;
    }

    public BedCancellationHistory convertBedCancellationHistoryDatatoBedCancellationHistory(BedCancellationHistoryData bedCancellationHistoryData)
            throws DcometServiceException {
        BedCancellationHistory bedCancellationHistory = new BedCancellationHistory();

        if (bedCancellationHistoryData.getBchRid() != null) {
            bedCancellationHistory.setBchRid(bedCancellationHistoryData.getBchRid());
        }
        if (bedCancellationHistoryData.getBchPatientRid() != null) {
            bedCancellationHistory.setBchPatientRid(bedCancellationHistoryData.getBchPatientRid());
        }
        if (bedCancellationHistoryData.getBchBedRid() != null) {
            bedCancellationHistory.setBchBedRid(bedCancellationHistoryData.getBchBedRid());
        }
        if (bedCancellationHistoryData.getBchBedNo() != null) {
            bedCancellationHistory.setBchBedNo(bedCancellationHistoryData.getBchBedNo());
        }
        if (bedCancellationHistoryData.getBchReason() != null) {
            bedCancellationHistory.setBchReason(bedCancellationHistoryData.getBchReason());
        }
        if (bedCancellationHistoryData.getBchEntityRid() != null) {
            bedCancellationHistory.setBchEntityRid(bedCancellationHistoryData.getBchEntityRid());
        }
        if (bedCancellationHistoryData.getBchCancelDatetime() != null) {
            bedCancellationHistory.setBchCancelDatetime(DateUtil.convertCalendarToString(bedCancellationHistoryData.getBchCancelDatetime()));
        }
        if (bedCancellationHistoryData.getBchCreatedUserRid() != null) {
            bedCancellationHistory.setBchCreatedUserRid(bedCancellationHistoryData.getBchCreatedUserRid());
        }
        if (bedCancellationHistoryData.getBchCreatedDatetime() != null) {
            bedCancellationHistory.setBchCreatedDatetime(DateUtil.convertCalendarToString(bedCancellationHistoryData.getBchCreatedDatetime()));
        }
        return bedCancellationHistory;
    }

    public BedCancellationHistoryData convertBedCancellationHistorytoBedCancellationHistoryData(BedCancellationHistory bedCancellationHistory)
            throws DcometServiceException {
        BedCancellationHistoryData bedCancellationHistoryData = new BedCancellationHistoryData();
        if (bedCancellationHistory.getBchBedRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchPatientRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchBedRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchBedNo() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchReason() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchEntityRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchCancelDatetime() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchCreatedUserRid() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        if (bedCancellationHistory.getBchCreatedDatetime() != null) {
            bedCancellationHistoryData.setBchRid(bedCancellationHistory.getBchRid());
        }
        return bedCancellationHistoryData;
    }

//---------------------------------------------------BedChargeDefinition-----------------------------------------------
    public List<BedChargeDefinition> convertBedChargeDefinitionDatatoBedChargeDefinition(List<BedChargeDefinitionData> bedChargeDefinitionDataList) throws DcometServiceException {
        List<BedChargeDefinition> bedChargeDefinitionList = new ArrayList<>();
        for (BedChargeDefinitionData bedChargeDefinitionData : bedChargeDefinitionDataList) {
            bedChargeDefinitionList.add(convertBedChargeDefinitionDatatoBedChargeDefinition(bedChargeDefinitionData));
        }
        return bedChargeDefinitionList;
    }

    public List<BedChargeDefinitionData> convertBedChargeDefinitionToBedChargeDefinitionData(List<BedChargeDefinition> bedChargeDefinitionList) throws DcometServiceException {
        List<BedChargeDefinitionData> bedChargeDefinitionDataList = new ArrayList<>();
        for (BedChargeDefinition bedChargeDefinition : bedChargeDefinitionList) {
            bedChargeDefinitionDataList.add(convertBedChargeDefinitionToBedChargeDefinitionData(bedChargeDefinition));
        }
        return bedChargeDefinitionDataList;
    }

    public BedChargeDefinition convertBedChargeDefinitionDatatoBedChargeDefinition(BedChargeDefinitionData bedChargeDefinitionData)
            throws DcometServiceException {
        BedChargeDefinition bedChargeDefinition = new BedChargeDefinition();

        if (bedChargeDefinitionData.getBcdRid() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdBedTypeRid() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdIsIcu() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdMinHours() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdGtMinHoursExpr() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdLtMinHoursExpr() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        if (bedChargeDefinitionData.getBcdEntityRid() != null) {
            bedChargeDefinition.setBcdRid(bedChargeDefinitionData.getBcdRid());
        }
        return bedChargeDefinition;
    }

    public BedChargeDefinitionData convertBedChargeDefinitionToBedChargeDefinitionData(BedChargeDefinition bedChargeDefinition) throws DcometServiceException {

        BedChargeDefinitionData bedChargeDefinitionData = new BedChargeDefinitionData();

        if (bedChargeDefinition.getBcdRid() != null) {
            bedChargeDefinitionData.setBcdRid(bedChargeDefinition.getBcdRid());
        }
        if (bedChargeDefinition.getBcdBedTypeRid() != null) {
            bedChargeDefinitionData.setBcdBedTypeRid(bedChargeDefinition.getBcdBedTypeRid());
        }
        if (bedChargeDefinition.getBcdIsIcu() != null) {
            bedChargeDefinitionData.setBcdIsIcu(bedChargeDefinition.getBcdIsIcu());
        }
        if (bedChargeDefinition.getBcdMinHours() != null) {
            bedChargeDefinitionData.setBcdMinHours(bedChargeDefinition.getBcdMinHours());
        }
        if (bedChargeDefinition.getBcdGtMinHoursExpr() != null) {
            bedChargeDefinitionData.setBcdGtMinHoursExpr(bedChargeDefinition.getBcdGtMinHoursExpr());
        }
        if (bedChargeDefinition.getBcdLtMinHoursExpr() != null) {
            bedChargeDefinitionData.setBcdLtMinHoursExpr(bedChargeDefinition.getBcdLtMinHoursExpr());
        }
        if (bedChargeDefinition.getBcdEntityRid() != null) {
            bedChargeDefinitionData.setBcdEntityRid(bedChargeDefinition.getBcdEntityRid());
        }
        return bedChargeDefinitionData;
    }

//    --------------------------------------Bed-------------------------------------------------------
    public List<BedMaster> convertBedMasterDataToBedMaster(List<BedMasterData> bedMasterDataList) throws DcometServiceException {
        List<BedMaster> bedList = new ArrayList<>();
        for (BedMasterData bedMasterData : bedMasterDataList) {
            bedList.add(convertBedMasterDataToBedMaster(bedMasterData));
        }
        return bedList;
    }

    public List<BedMasterData> convertBedMasterToBedMasterData(List<BedMaster> bedList) throws DcometServiceException {
        List<BedMasterData> bedDataList = new ArrayList<>();
        for (BedMaster bed : bedList) {
            bedDataList.add(convertBedMasterToBedMasterData(bed));
        }
        return bedDataList;
    }

    public BedMaster convertBedMasterDataToBedMaster(BedMasterData bedMasterData)
            throws DcometServiceException {
        BedMaster bedMaster = new BedMaster();

        if (bedMasterData.getBedRid() != null) {
            bedMaster.setBedRid(bedMasterData.getBedRid());
        }
        if (bedMasterData.getBedBgmRid() != null) {
            bedMaster.setBedBgmRid(bedMasterData.getBedBgmRid());
        }
        if (bedMasterData.getBedName() != null) {
            bedMaster.setBedName(bedMasterData.getBedName());
        }
        if (bedMasterData.getBedNo() != null) {
            bedMaster.setBedNo(bedMasterData.getBedNo());
        }
        if (bedMasterData.getBedPrefix() != null) {
            bedMaster.setBedPrefix(bedMasterData.getBedPrefix());
        }
        if (bedMasterData.getBedSequence() != null) {
            bedMaster.setBedSequence(bedMasterData.getBedSequence());
        }
        if (bedMasterData.getBedNotes() != null) {
            bedMaster.setBedNotes(bedMasterData.getBedNotes());
        }
        if (bedMasterData.getBedState() != null) {
            bedMaster.setBedState(bedMasterData.getBedState());
        }
        if (bedMasterData.getBedStatus() != null) {
            bedMaster.setBedStatus(bedMasterData.getBedStatus());
        }
        if (bedMasterData.getBedIsActive() != null) {
            bedMaster.setBedIsActive(bedMasterData.getBedIsActive());
        }
        if (bedMasterData.getBedUnitRid() != null) {
            bedMaster.setBedUnitRid(bedMasterData.getBedUnitRid());
        }
        if (bedMasterData.getBedEntityRid() != null) {
            bedMaster.setBedEntityRid(bedMasterData.getBedEntityRid());
        }
//        if (bedData.getCreatedUserRid() != null) {
//            bed.setCreatedUserRid(bedData.getCreatedUserRid());
//        }
//        if (bedData.getCreatedDateTime() != null) {
//            bed.setCreatedDateTime(DateUtil.convertCalendarToString(bedData.getCreatedDateTime()));
//        }
//        if (bedData.getModifiedUserRid() != null) {
//            bed.setModifiedUserRid(bedData.getModifiedUserRid());
//        }
//        if (bedData.getModifiedDateTime() != null) {
//            bed.setModifiedDateTime(DateUtil.convertCalendarToString(bedData.getModifiedDateTime()));
//        }
        return bedMaster;
    }

    public BedMasterData convertBedMasterToBedMasterData(BedMaster bedMaster) throws DcometServiceException {
        BedMasterData bedMasterData = new BedMasterData();

        if (bedMaster.getBedRid() != null) {
            bedMasterData.setBedRid(bedMaster.getBedRid());
        }
        if (bedMaster.getBedBgmRid() != null) {
            bedMasterData.setBedBgmRid(bedMaster.getBedBgmRid());
        }
        if (bedMaster.getBedName() != null) {
            bedMasterData.setBedName(bedMaster.getBedName());
        }
        if (bedMaster.getBedNo() != null) {
            bedMasterData.setBedNo(bedMaster.getBedNo());
        }
        if (bedMaster.getBedPrefix() != null) {
            bedMasterData.setBedPrefix(bedMaster.getBedPrefix());
        }
        if (bedMaster.getBedSequence() != null) {
            bedMasterData.setBedSequence(bedMaster.getBedSequence());
        }
        if (bedMaster.getBedNotes() != null) {
            bedMasterData.setBedNotes(bedMaster.getBedNotes());
        }
        if (bedMaster.getBedState() != null) {
            bedMasterData.setBedState(bedMaster.getBedState());
        }
        if (bedMaster.getBedStatus() != null) {
            bedMasterData.setBedStatus(bedMaster.getBedStatus());
        }
        if (bedMaster.getBedIsActive() != null) {
            bedMasterData.setBedIsActive(bedMaster.getBedIsActive());
        }
        if (bedMaster.getBedUnitRid() != null) {
            bedMasterData.setBedUnitRid(bedMaster.getBedUnitRid());
        }
        if (bedMaster.getBedEntityRid() != null) {
            bedMasterData.setBedEntityRid(bedMaster.getBedEntityRid());
        }
//        if (bedMaster.getCreatedUserRid() != null) {
//            bedMasterData.setCreatedUserRid(bedMaster.getCreatedUserRid());
//        }
//        if (bedMaster.getModifiedUserRid() != null) {
//            bedMasterData.setModifiedUserRid(bedMaster.getModifiedUserRid());
//        }
        return bedMasterData;
    }
//---------------------------------------------------------------BedOccupancy-------------------------------------------------------------

    public List<BedOccupancy> convertBedOccupancyDatatoBedOccupancy(List<BedOccupancyData> bedOccupancyDataList) throws DcometServiceException {
        List<BedOccupancy> bedOccupancyList = new ArrayList<>();
        for (BedOccupancyData bedOccupancyData : bedOccupancyDataList) {
            bedOccupancyList.add(convertBedOccupancyDatatoBedOccupancy(bedOccupancyData));
        }
        return bedOccupancyList;
    }

    public List<BedOccupancyData> convertBedOccupancyToBedOccupancyData(List<BedOccupancy> bedOccupancyList) throws DcometServiceException {
        List<BedOccupancyData> bedOccupancyDataList = new ArrayList<>();
        for (BedOccupancy bedOccupancy : bedOccupancyList) {
            bedOccupancyDataList.add(convertBedOccupancyToBedOccupancyData(bedOccupancy));
        }
        return bedOccupancyDataList;
    }

    public BedOccupancy convertBedOccupancyDatatoBedOccupancy(BedOccupancyData bedOccupancyData)
            throws DcometServiceException {
        BedOccupancy bedOccupancy = new BedOccupancy();

        if (bedOccupancyData.getBocRid() != null) {
            bedOccupancy.setBocRid(bedOccupancyData.getBocRid());
        }
        if (bedOccupancyData.getBocPatRid() != null) {
            bedOccupancy.setBocPatRid(bedOccupancyData.getBocPatRid());
        }
        if (bedOccupancyData.getBocVisitRid() != null) {
            bedOccupancy.setBocVisitRid(bedOccupancyData.getBocVisitRid());
        }
        if (bedOccupancyData.getBocBedRid() != null) {
            bedOccupancy.setBocBedRid(bedOccupancyData.getBocBedRid());
        }
        if (bedOccupancyData.getBocFromDatetime() != null) {
            bedOccupancy.setBocFromDatetime(DateUtil.convertCalendarToString(bedOccupancyData.getBocFromDatetime()));
        }
        if (bedOccupancyData.getBocToDatetime() != null) {
            bedOccupancy.setBocToDatetime(DateUtil.convertCalendarToString(bedOccupancyData.getBocToDatetime()));
        }
        if (bedOccupancyData.getBocReserveStatus() != null) {
            bedOccupancy.setBocReserveStatus(bedOccupancyData.getBocReserveStatus());
        }
        if (bedOccupancyData.getBocBillingBedtypeRid() != null) {
            bedOccupancy.setBocBillingBedtypeRid(bedOccupancyData.getBocBillingBedtypeRid());
        }
        if (bedOccupancyData.getBocPrimaryDoctor() != null) {
            bedOccupancy.setBocPrimaryDoctor(bedOccupancyData.getBocPrimaryDoctor());
        }
        if (bedOccupancyData.getBocSecondaryDoctor() != null) {
            bedOccupancy.setBocSecondaryDoctor(bedOccupancyData.getBocSecondaryDoctor());
        }
        if (bedOccupancyData.getBocModifiedUserRid() != null) {
            bedOccupancy.setModifiedUserRid(bedOccupancyData.getBocModifiedUserRid());
        }
        if (bedOccupancyData.getModifiedDateTime() != null) {
            bedOccupancy.setModifiedDateTime(DateUtil.convertCalendarToString(bedOccupancyData.getModifiedDateTime()));
        }
        return bedOccupancy;
    }

    public BedOccupancyData convertBedOccupancyToBedOccupancyData(BedOccupancy bedOccupancy) throws DcometServiceException {

        BedOccupancyData bedOccupancyData = new BedOccupancyData();

        if (bedOccupancy.getBocRid() != null) {
            bedOccupancyData.setBocRid(bedOccupancy.getBocRid());
        }
        if (bedOccupancy.getBocPatRid() != null) {
            bedOccupancyData.setBocPatRid(bedOccupancy.getBocPatRid());
        }
        if (bedOccupancy.getBocVisitRid() != null) {
            bedOccupancyData.setBocVisitRid(bedOccupancy.getBocVisitRid());
        }
        if (bedOccupancy.getBocBedRid() != null) {
            bedOccupancyData.setBocBedRid(bedOccupancy.getBocBedRid());
        }
        if (bedOccupancy.getBocFromDatetime() != null) {
            bedOccupancyData.setBocFromDatetime(DateUtil.convertStringToCalendar(bedOccupancy.getBocFromDatetime()));
        }
        if (bedOccupancy.getBocToDatetime() != null) {
            bedOccupancyData.setBocToDatetime(DateUtil.convertStringToCalendar(bedOccupancy.getBocToDatetime()));
        }
        if (bedOccupancy.getBocReserveStatus() != null) {
            bedOccupancyData.setBocReserveStatus(bedOccupancy.getBocReserveStatus());
        }
        if (bedOccupancy.getBocBillingBedtypeRid() != null) {
            bedOccupancyData.setBocBillingBedtypeRid(bedOccupancy.getBocBillingBedtypeRid());
        }
        if (bedOccupancy.getBocPrimaryDoctor() != null) {
            bedOccupancyData.setBocPrimaryDoctor(bedOccupancy.getBocPrimaryDoctor());
        }
        if (bedOccupancy.getBocSecondaryDoctor() != null) {
            bedOccupancyData.setBocSecondaryDoctor(bedOccupancy.getBocSecondaryDoctor());
        }
        if (bedOccupancy.getModifiedUserRid() != null) {
            bedOccupancyData.setBocModifiedUserRid(bedOccupancy.getModifiedUserRid());
        }
        return bedOccupancyData;
    }

//----------------------------------------------------------BedOccupancyDetails-------------------------------------------------
    public List<BedOccupancyDetails> convertBedOccupancyDetailsDatatoBedOccupancyDetails(List<BedOccupancyDetailsData> bedOccupancyDetailsDataList) throws DcometServiceException {
        List<BedOccupancyDetails> bedOccupancyDetailsList = new ArrayList<>();
        for (BedOccupancyDetailsData bedOccupancyDetailsData : bedOccupancyDetailsDataList) {
            bedOccupancyDetailsList.add(convertBedOccupancyDetailsDatatoBedOccupancyDetails(bedOccupancyDetailsData));
        }
        return bedOccupancyDetailsList;
    }

    public List<BedOccupancyDetailsData> convertBedOccupancyDetailsToBedOccupancyDetailsData(List<BedOccupancyDetails> bedOccupancyDetailsList) throws DcometServiceException {
        List<BedOccupancyDetailsData> bedOccupancyDetailsDataList = new ArrayList<>();
        for (BedOccupancyDetails bedOccupancyDetails : bedOccupancyDetailsList) {
            bedOccupancyDetailsDataList.add(convertBedOccupancyDetailsToBedOccupancyDetailsData(bedOccupancyDetails));
        }
        return bedOccupancyDetailsDataList;
    }

    public BedOccupancyDetails convertBedOccupancyDetailsDatatoBedOccupancyDetails(BedOccupancyDetailsData bedOccupancyDetailsData)
            throws DcometServiceException {
        BedOccupancyDetails bedOccupancyDetails = new BedOccupancyDetails();

        if (bedOccupancyDetailsData.getBodRid() != null) {
            bedOccupancyDetails.setBodRid(bedOccupancyDetailsData.getBodRid());
        }
        if (bedOccupancyDetailsData.getBodBedTypeRid() != null) {
            bedOccupancyDetails.setBodBedTypeRid(bedOccupancyDetailsData.getBodBedTypeRid());
        }
        if (bedOccupancyDetailsData.getBodBedRid() != null) {
            bedOccupancyDetails.setBodBedRid(bedOccupancyDetailsData.getBodBedRid());
        }
        if (bedOccupancyDetailsData.getBodWardRid() != null) {
            bedOccupancyDetails.setBodWardRid(bedOccupancyDetailsData.getBodWardRid());
        }
        if (bedOccupancyDetailsData.getBodGroupRid() != null) {
            bedOccupancyDetails.setBodGroupRid(bedOccupancyDetailsData.getBodGroupRid());
        }
        if (bedOccupancyDetailsData.getBodWardUnitRid() != null) {
            bedOccupancyDetails.setBodWardUnitRid(bedOccupancyDetailsData.getBodWardUnitRid());
        }
        if (bedOccupancyDetailsData.getBodFromDatetime() != null) {
            bedOccupancyDetails.setBodFromDatetime(DateUtil.convertCalendarToString(bedOccupancyDetailsData.getBodFromDatetime()));
        }
        if (bedOccupancyDetailsData.getBodToDatetime() != null) {
            bedOccupancyDetails.setBodToDatetime(DateUtil.convertCalendarToString(bedOccupancyDetailsData.getBodToDatetime()));
        }
        if (bedOccupancyDetailsData.getBodEntRid() != null) {
            bedOccupancyDetails.setEntityRid(bedOccupancyDetailsData.getBodEntRid());
        }
        if (bedOccupancyDetailsData.getBodUserRid() != null) {
            bedOccupancyDetails.setBodUserRid(bedOccupancyDetailsData.getBodUserRid());
        }
        if (bedOccupancyDetailsData.getBodSessionId() != null) {
            bedOccupancyDetails.setBodSessionId(bedOccupancyDetailsData.getBodSessionId());
        }
        return bedOccupancyDetails;
    }

    public BedOccupancyDetailsData convertBedOccupancyDetailsToBedOccupancyDetailsData(BedOccupancyDetails bedOccupancyDetails) throws DcometServiceException {

        BedOccupancyDetailsData bedOccupancyDetailsData = new BedOccupancyDetailsData();

        if (bedOccupancyDetails.getBodRid() != null) {
            bedOccupancyDetailsData.setBodRid(bedOccupancyDetails.getBodRid());
        }
        if (bedOccupancyDetails.getBodBedTypeRid() != null) {
            bedOccupancyDetailsData.setBodBedTypeRid(bedOccupancyDetails.getBodBedTypeRid());
        }
        if (bedOccupancyDetails.getBodBedRid() != null) {
            bedOccupancyDetailsData.setBodBedRid(bedOccupancyDetails.getBodBedRid());
        }
        if (bedOccupancyDetails.getBodWardRid() != null) {
            bedOccupancyDetailsData.setBodWardRid(bedOccupancyDetails.getBodWardRid());
        }
        if (bedOccupancyDetails.getBodGroupRid() != null) {
            bedOccupancyDetailsData.setBodGroupRid(bedOccupancyDetails.getBodGroupRid());
        }
        if (bedOccupancyDetails.getBodWardUnitRid() != null) {
            bedOccupancyDetailsData.setBodWardUnitRid(bedOccupancyDetails.getBodWardUnitRid());
        }
        if (bedOccupancyDetails.getBodFromDatetime() != null) {
            bedOccupancyDetailsData.setBodFromDatetime(DateUtil.convertStringToCalendar(bedOccupancyDetails.getBodFromDatetime()));
        }
        if (bedOccupancyDetails.getBodToDatetime() != null) {
            bedOccupancyDetailsData.setBodToDatetime(DateUtil.convertStringToCalendar(bedOccupancyDetails.getBodToDatetime()));
        }
        if (bedOccupancyDetails.getEntityRid() != null) {
            bedOccupancyDetailsData.setBodEntRid(bedOccupancyDetails.getEntityRid());
        }
        if (bedOccupancyDetails.getBodUserRid() != null) {
            bedOccupancyDetailsData.setBodUserRid(bedOccupancyDetails.getBodUserRid());
        }
        if (bedOccupancyDetails.getBodSessionId() != null) {
            bedOccupancyDetailsData.setBodSessionId(bedOccupancyDetails.getBodSessionId());
        }
        return bedOccupancyDetailsData;
    }

//    --------------------------------------BedTransfer--------------------------------------------------------------
    public List<BedTransfer> convertBedTransferDatatoBedTransfer(List<BedTransferData> bedTransferDataList) throws DcometServiceException {
        List<BedTransfer> bedTransferList = new ArrayList<>();
        for (BedTransferData bedTransferData : bedTransferDataList) {
            bedTransferList.add(convertBedTransferDatatoBedTransfer(bedTransferData));
        }
        return bedTransferList;
    }

    public List<BedTransferData> convertBedTransferToBedTransferData(List<BedTransfer> bedTransferList) throws DcometServiceException {
        List<BedTransferData> bedTransferDataList = new ArrayList<>();
        for (BedTransfer bedTransfer : bedTransferList) {
            bedTransferDataList.add(convertBedTransferToBedTransferData(bedTransfer));
        }
        return bedTransferDataList;
    }

    public BedTransfer convertBedTransferDatatoBedTransfer(BedTransferData bedTransferData)
            throws DcometServiceException {
        BedTransfer bedTransfer = new BedTransfer();

        if (bedTransferData.getBtRid() != null) {
            bedTransfer.setBtRid(bedTransferData.getBtRid());
        }
        if (bedTransferData.getBtPatRid() != null) {
            bedTransfer.setBtPatRid(bedTransferData.getBtPatRid());
        }
        if (bedTransferData.getBtVisitRid() != null) {
            bedTransfer.setBtVisitRid(bedTransferData.getBtVisitRid());
        }
        if (bedTransferData.getBtFromWardRid() != null) {
            bedTransfer.setBtFromWardRid(bedTransferData.getBtFromWardRid());
        }
        if (bedTransferData.getBtToWardRid() != null) {
            bedTransfer.setBtToWardRid(bedTransferData.getBtToWardRid());
        }
        if (bedTransferData.getBtFromBed() != null) {
            bedTransfer.setBtFromBed(bedTransferData.getBtFromBed());
        }
        if (bedTransferData.getBtToBed() != null) {
            bedTransfer.setBtToBed(bedTransferData.getBtToBed());
        }
        if (bedTransferData.getBtTransferDatetime() != null) {
            bedTransfer.setBtTransferDatetime(DateUtil.convertCalendarToString(bedTransferData.getBtTransferDatetime()));
        }
        if (bedTransferData.getBtTransferredByRid() != null) {
            bedTransfer.setBtTransferredByRid(bedTransferData.getBtTransferredByRid());
        }
        return bedTransfer;
    }

    public BedTransferData convertBedTransferToBedTransferData(BedTransfer bedTransfer) throws DcometServiceException {
        BedTransferData bedTransferData = new BedTransferData();

        if (bedTransfer.getBtRid() != null) {
            bedTransferData.setBtRid(bedTransfer.getBtRid());
        }
        if (bedTransfer.getBtPatRid() != null) {
            bedTransferData.setBtPatRid(bedTransfer.getBtPatRid());
        }
        if (bedTransfer.getBtVisitRid() != null) {
            bedTransferData.setBtVisitRid(bedTransfer.getBtVisitRid());
        }
        if (bedTransfer.getBtFromWardRid() != null) {
            bedTransferData.setBtFromWardRid(bedTransfer.getBtFromWardRid());
        }
        if (bedTransfer.getBtToWardRid() != null) {
            bedTransferData.setBtToWardRid(bedTransfer.getBtToWardRid());
        }
        if (bedTransfer.getBtFromBed() != null) {
            bedTransferData.setBtFromBed(bedTransfer.getBtFromBed());
        }
        if (bedTransfer.getBtToBed() != null) {
            bedTransferData.setBtToBed(bedTransfer.getBtToBed());
        }
        if (bedTransfer.getBtTransferredByRid() != null) {
            bedTransferData.setBtTransferredByRid(bedTransfer.getBtTransferredByRid());
        }
        return bedTransferData;
    }

//    -------------------------------------------------BedTransferRequest-------------------------------------------------------------------
    public List<BedTransferRequest> convertBedTransferRequestDatatoBedTransferRequest(List<BedTransferRequestData> bedTransferRequestDataList) throws DcometServiceException {
        List<BedTransferRequest> bedTransferRequestList = new ArrayList<>();
        for (BedTransferRequestData bedTransferRequestData : bedTransferRequestDataList) {
            bedTransferRequestList.add(convertBedTransferRequestDatatoBedTransferRequest(bedTransferRequestData));
        }
        return bedTransferRequestList;
    }

    public List<BedTransferRequestData> convertBedTransferRequestToBedTransferRequestData(List<BedTransferRequest> bedTransferRequestList) throws DcometServiceException {
        List<BedTransferRequestData> bedTransferRequestDataList = new ArrayList<>();
        for (BedTransferRequest bedTransferRequest : bedTransferRequestList) {
            bedTransferRequestDataList.add(convertBedTransferRequestToBedTransferRequestData(bedTransferRequest));
        }
        return bedTransferRequestDataList;
    }

    public BedTransferRequest convertBedTransferRequestDatatoBedTransferRequest(BedTransferRequestData bedTransferRequestData)
            throws DcometServiceException {
        BedTransferRequest bedTransferRequest = new BedTransferRequest();

        if (bedTransferRequestData.getBtrRid() != null) {
            bedTransferRequest.setBtrRid(bedTransferRequestData.getBtrRid());
        }
        if (bedTransferRequestData.getBtrPatientRid() != null) {
            bedTransferRequest.setBtrPatientRid(bedTransferRequestData.getBtrPatientRid());
        }
        if (bedTransferRequestData.getBtrVisitRid() != null) {
            bedTransferRequest.setBtrVisitRid(bedTransferRequestData.getBtrVisitRid());
        }
        if (bedTransferRequestData.getBtrFrombedNo() != null) {
            bedTransferRequest.setBtrFrombedNo(bedTransferRequestData.getBtrFrombedNo());
        }
        if (bedTransferRequestData.getBtrFromWardRid() != null) {
            bedTransferRequest.setBtrFromWardRid(bedTransferRequestData.getBtrFromWardRid());
        }
        if (bedTransferRequestData.getBtrCurrentBillingClassRid() != null) {
            bedTransferRequest.setBtrCurrentBillingClassRid(bedTransferRequestData.getBtrCurrentBillingClassRid());
        }
        if (bedTransferRequestData.getBtrToBedRid() != null) {
            bedTransferRequest.setBtrToBedRid(bedTransferRequestData.getBtrToBedRid());
        }
        if (bedTransferRequestData.getBtrToBedNo() != null) {
            bedTransferRequest.setBtrToBedNo(bedTransferRequestData.getBtrToBedNo());
        }
        if (bedTransferRequestData.getBtrToWardRid() != null) {
            bedTransferRequest.setBtrToWardRid(bedTransferRequestData.getBtrToWardRid());
        }
        if (bedTransferRequestData.getBtrNewBillingClassRid() != null) {
            bedTransferRequest.setBtrNewBillingClassRid(bedTransferRequestData.getBtrNewBillingClassRid());
        }
        if (bedTransferRequestData.getBtrTransferDatetime() != null) {
            bedTransferRequest.setBtrTransferDatetime(DateUtil.convertCalendarToString(bedTransferRequestData.getBtrTransferDatetime()));
        }
        if (bedTransferRequestData.getBtrState() != null) {
            bedTransferRequest.setBtrState(bedTransferRequestData.getBtrState());
        }
        if (bedTransferRequestData.getBtrStatus() != null) {
            bedTransferRequest.setBtrStatus(bedTransferRequestData.getBtrStatus());
        }
        if (bedTransferRequestData.getBtrEntityRid() != null) {
            bedTransferRequest.setBtrEntityRid(bedTransferRequestData.getBtrEntityRid());
        }
        if (bedTransferRequestData.getBtrRemarks() != null) {
            bedTransferRequest.setBtrRemarks(bedTransferRequestData.getBtrRemarks());
        }
        if (bedTransferRequestData.getBtrCreatedUserRid() != null) {
            bedTransferRequest.setBtrCreatedUserRid(bedTransferRequestData.getBtrCreatedUserRid());
        }
        if (bedTransferRequestData.getBtrCreatedDatetime() != null) {
            bedTransferRequest.setBtrCreatedDatetime(DateUtil.convertCalendarToString(bedTransferRequestData.getBtrCreatedDatetime()));
        }
        if (bedTransferRequestData.getBtrModifiedUserRid() != null) {
            bedTransferRequest.setBtrModifiedUserRid(bedTransferRequestData.getBtrModifiedUserRid());
        }
        if (bedTransferRequestData.getBtrModifiedDatetime() != null) {
            bedTransferRequest.setBtrModifiedDatetime(DateUtil.convertCalendarToString(bedTransferRequestData.getBtrModifiedDatetime()));
        }
        return bedTransferRequest;
    }

    public BedTransferRequestData convertBedTransferRequestToBedTransferRequestData(BedTransferRequest bedTransferRequest) throws DcometServiceException {
        BedTransferRequestData bedTransferRequestData = new BedTransferRequestData();

        if (bedTransferRequest.getBtrRid() != null) {
            bedTransferRequestData.setBtrRid(bedTransferRequest.getBtrRid());
        }
        if (bedTransferRequest.getBtrPatientRid() != null) {
            bedTransferRequestData.setBtrPatientRid(bedTransferRequest.getBtrPatientRid());
        }
        if (bedTransferRequest.getBtrVisitRid() != null) {
            bedTransferRequestData.setBtrVisitRid(bedTransferRequest.getBtrVisitRid());
        }
        if (bedTransferRequest.getBtrFromBedRid() != null) {
            bedTransferRequestData.setBtrFromBedRid(bedTransferRequest.getBtrFromBedRid());
        }
        if (bedTransferRequest.getBtrFrombedNo() != null) {
            bedTransferRequestData.setBtrFrombedNo(bedTransferRequest.getBtrFrombedNo());
        }
        if (bedTransferRequest.getBtrFromWardRid() != null) {
            bedTransferRequestData.setBtrFromWardRid(bedTransferRequest.getBtrFromWardRid());
        }
        if (bedTransferRequest.getBtrCurrentBillingClassRid() != null) {
            bedTransferRequestData.setBtrCurrentBillingClassRid(bedTransferRequest.getBtrCurrentBillingClassRid());
        }
        if (bedTransferRequest.getBtrToBedRid() != null) {
            bedTransferRequestData.setBtrRid(bedTransferRequest.getBtrRid());
        }
        if (bedTransferRequest.getBtrToBedNo() != null) {
            bedTransferRequestData.setBtrToBedNo(bedTransferRequest.getBtrToBedNo());
        }
        if (bedTransferRequest.getBtrToWardRid() != null) {
            bedTransferRequestData.setBtrToWardRid(bedTransferRequest.getBtrToWardRid());
        }
        if (bedTransferRequest.getBtrNewBillingClassRid() != null) {
            bedTransferRequestData.setBtrNewBillingClassRid(bedTransferRequest.getBtrNewBillingClassRid());
        }
        if (bedTransferRequest.getBtrState() != null) {
            bedTransferRequestData.setBtrState(bedTransferRequest.getBtrState());
        }
        if (bedTransferRequest.getBtrStatus() != null) {
            bedTransferRequestData.setBtrStatus(bedTransferRequest.getBtrStatus());
        }
        if (bedTransferRequest.getBtrEntityRid() != null) {
            bedTransferRequestData.setBtrEntityRid(bedTransferRequest.getBtrEntityRid());
        }
        if (bedTransferRequest.getBtrRemarks() != null) {
            bedTransferRequestData.setBtrRemarks(bedTransferRequest.getBtrRemarks());
        }
        if (bedTransferRequest.getBtrCreatedUserRid() != null) {
            bedTransferRequestData.setBtrCreatedUserRid(bedTransferRequest.getBtrCreatedUserRid());
        }
        if (bedTransferRequest.getBtrModifiedUserRid() != null) {
            bedTransferRequestData.setBtrModifiedUserRid(bedTransferRequest.getBtrModifiedUserRid());
        }
        return bedTransferRequestData;
    }

//    --------------------------------------------------------------BedGroupM--------------------------------------------------------------
    public List<BedGroupM> convertBedGroupMDatatoBedGroupM(List<BedGroupMData> bedGroupMDataList) throws DcometServiceException {
        List<BedGroupM> bedGroupMList = new ArrayList<>();
        for (BedGroupMData bedGroupMData : bedGroupMDataList) {
            bedGroupMList.add(convertBedGroupMDatatoBedGroupM(bedGroupMData));
        }
        return bedGroupMList;
    }

    public List<BedGroupMData> convertBedGroupMToBedGroupMData(List<BedGroupM> bedGroupMList) throws DcometServiceException {
        List<BedGroupMData> bedGroupMDataList = new ArrayList<>();
        for (BedGroupM bedGroupM : bedGroupMList) {
            bedGroupMDataList.add(convertBedGroupMToBedGroupMData(bedGroupM));
        }
        return bedGroupMDataList;
    }

    public BedGroupM convertBedGroupMDatatoBedGroupM(BedGroupMData bedGroupMData)
            throws DcometServiceException {
        BedGroupM bedGroupM = new BedGroupM();

        if (bedGroupMData.getBgmRid() != null) {
            bedGroupM.setBgmRid(bedGroupMData.getBgmRid());
        }
        if (bedGroupMData.getBgmBedGroupName() != null) {
            bedGroupM.setBgmBedGroupName(bedGroupMData.getBgmBedGroupName());
        }
        if (bedGroupMData.getBgmBedGroupIndex() != null) {
            bedGroupM.setBgmBedGroupIndex(bedGroupMData.getBgmBedGroupIndex());
        }
        if (bedGroupMData.getBgmBedSubGroupIndex() != null) {
            bedGroupM.setBgmBedSubGroupIndex(bedGroupMData.getBgmBedSubGroupIndex());
        }
        if (bedGroupMData.getBgmBedPriceTypeIndex() != null) {
            bedGroupM.setBgmBedPriceTypeIndex(bedGroupMData.getBgmBedPriceTypeIndex());
        }
        if (bedGroupMData.getBgmBedServicePointRid() != null) {
            bedGroupM.setBgmBedServicePointRid(bedGroupMData.getBgmBedServicePointRid());
        }
        if (bedGroupMData.getBgmBedUnitRid() != null) {
            bedGroupM.setBgmBedUnitRid(bedGroupMData.getBgmBedUnitRid());
        }
        if (bedGroupMData.getBgmBedEntityRid() != null) {
            bedGroupM.setBgmBedEntityRid(bedGroupMData.getBgmBedEntityRid());
        }
        if (bedGroupMData.getCreatedUserRid() != null) {
            bedGroupM.setCreatedUserRid(bedGroupMData.getCreatedUserRid());
        }
        if (bedGroupMData.getCreatedDateTime() != null) {
            bedGroupM.setCreatedDateTime(DateUtil.convertCalendarToString(bedGroupMData.getCreatedDateTime()));
        }
        if (bedGroupMData.getModifiedUserRid() != null) {
            bedGroupM.setModifiedUserRid(bedGroupMData.getModifiedUserRid());
        }
        if (bedGroupMData.getModifiedDateTime() != null) {
            bedGroupM.setModifiedDateTime(DateUtil.convertCalendarToString(bedGroupMData.getModifiedDateTime()));
        }

        return bedGroupM;
    }

    public BedGroupMData convertBedGroupMToBedGroupMData(BedGroupM bedGroupM) throws DcometServiceException {
        BedGroupMData bedGroupMData = new BedGroupMData();

        if (bedGroupM.getBgmRid() != null) {
            bedGroupMData.setBgmRid(bedGroupM.getBgmRid());
        }
        if (bedGroupM.getBgmBedGroupName() != null) {
            bedGroupMData.setBgmBedGroupName(bedGroupM.getBgmBedGroupName());
        }
        if (bedGroupM.getBgmBedGroupIndex() != null) {
            bedGroupMData.setBgmBedGroupIndex(bedGroupM.getBgmBedGroupIndex());
        }
        if (bedGroupM.getBgmBedSubGroupIndex() != null) {
            bedGroupMData.setBgmBedSubGroupIndex(bedGroupM.getBgmBedSubGroupIndex());
        }
        if (bedGroupM.getBgmBedPriceTypeIndex() != null) {
            bedGroupMData.setBgmBedPriceTypeIndex(bedGroupM.getBgmBedPriceTypeIndex());
        }
        if (bedGroupM.getBgmBedServicePointRid() != null) {
            bedGroupMData.setBgmBedServicePointRid(bedGroupM.getBgmBedServicePointRid());
        }
        if (bedGroupM.getBgmBedUnitRid() != null) {
            bedGroupMData.setBgmBedUnitRid(bedGroupM.getBgmBedUnitRid());
        }
        if (bedGroupM.getEntityRid() != null) {
            bedGroupMData.setBgmBedEntityRid(bedGroupM.getEntityRid());
        }
        if (bedGroupM.getCreatedUserRid() != null) {
            bedGroupMData.setCreatedUserRid(bedGroupM.getCreatedUserRid());
        }
        if (bedGroupM.getCreatedDateTime() != null) {
            bedGroupMData.setCreatedDateTime(DateUtil.convertStringToCalendar(bedGroupM.getCreatedDateTime()));
        }
        if (bedGroupM.getModifiedUserRid() != null) {
            bedGroupMData.setModifiedUserRid(bedGroupM.getModifiedUserRid());
        }
        if (bedGroupM.getModifiedDateTime() != null) {
            bedGroupMData.setModifiedDateTime(DateUtil.convertStringToCalendar(bedGroupM.getModifiedDateTime()));
        }
        return bedGroupMData;
    }

//    --------------------------------------------------------------BedTypeOccupancySummary---------------------------------------------------
    public List<BedTypeOccupancySummary> convertBedTypeOccupancySummaryDatatoBedTypeOccupancySummary(List<BedTypeOccupancySummaryData> bedTypeOccupancySummaryDataList) throws DcometServiceException {
        List<BedTypeOccupancySummary> bedTypeOccupancySummaryList = new ArrayList<>();
        for (BedTypeOccupancySummaryData bedTypeOccupancySummaryData : bedTypeOccupancySummaryDataList) {
            bedTypeOccupancySummaryList.add(convertBedTypeOccupancySummaryDatatoBedTypeOccupancySummary(bedTypeOccupancySummaryData));
        }
        return bedTypeOccupancySummaryList;
    }

    public List<BedTypeOccupancySummaryData> convertBedTypeOccupancySummaryToBedTypeOccupancySummaryData(List<BedTypeOccupancySummary> bedTypeOccupancySummaryList) throws DcometServiceException {
        List<BedTypeOccupancySummaryData> bedTypeOccupancySummaryDataList = new ArrayList<>();
        for (BedTypeOccupancySummary bedTypeOccupancySummary : bedTypeOccupancySummaryList) {
            bedTypeOccupancySummaryDataList.add(convertBedTypeOccupancySummaryToBedTypeOccupancySummaryData(bedTypeOccupancySummary));
        }
        return bedTypeOccupancySummaryDataList;
    }

    public BedTypeOccupancySummary convertBedTypeOccupancySummaryDatatoBedTypeOccupancySummary(BedTypeOccupancySummaryData bedTypeOccupancySummaryData)
            throws DcometServiceException {
        BedTypeOccupancySummary bedTypeOccupancySummary = new BedTypeOccupancySummary();

        if (bedTypeOccupancySummaryData.getBtosRid() != null) {
            bedTypeOccupancySummary.setBtosRid(bedTypeOccupancySummaryData.getBtosRid());
        }
        if (bedTypeOccupancySummaryData.getBtosDate() != null) {
            bedTypeOccupancySummary.setBtosDate(DateUtil.convertCalendarToString(bedTypeOccupancySummaryData.getBtosDate()));
        }
        if (bedTypeOccupancySummaryData.getBtosBedTypeRid() != null) {
            bedTypeOccupancySummary.setBtosBedTypeRid(bedTypeOccupancySummaryData.getBtosBedTypeRid());
        }
        if (bedTypeOccupancySummaryData.getBtosWardRid() != null) {
            bedTypeOccupancySummary.setBtosWardRid(bedTypeOccupancySummaryData.getBtosWardRid());
        }
        if (bedTypeOccupancySummaryData.getBtosUnitRid() != null) {
            bedTypeOccupancySummary.setBtosUnitRid(bedTypeOccupancySummaryData.getBtosUnitRid());
        }
        if (bedTypeOccupancySummaryData.getBtosEntRid() != null) {
            bedTypeOccupancySummary.setBtosEntRid(bedTypeOccupancySummaryData.getBtosEntRid());
        }
        if (bedTypeOccupancySummaryData.getBtosOccupiedBedCount() != null) {
            bedTypeOccupancySummary.setBtosOccupiedBedCount(bedTypeOccupancySummaryData.getBtosOccupiedBedCount());
        }
        if (bedTypeOccupancySummaryData.getBtosTotalBedCount() != null) {
            bedTypeOccupancySummary.setBtosTotalBedCount(bedTypeOccupancySummaryData.getBtosTotalBedCount());
        }
        if (bedTypeOccupancySummaryData.getBtosUserRid() != null) {
            bedTypeOccupancySummary.setBtosUserRid(bedTypeOccupancySummaryData.getBtosUserRid());
        }
        if (bedTypeOccupancySummaryData.getBtosSessionId() != null) {
            bedTypeOccupancySummary.setBtosSessionId(bedTypeOccupancySummaryData.getBtosSessionId());
        }

        return bedTypeOccupancySummary;
    }

    public BedTypeOccupancySummaryData convertBedTypeOccupancySummaryToBedTypeOccupancySummaryData(BedTypeOccupancySummary BedTypeOccupancySummary) throws DcometServiceException {
        BedTypeOccupancySummaryData bedTypeOccupancySummaryData = new BedTypeOccupancySummaryData();

        if (BedTypeOccupancySummary.getBtosRid() != null) {
            bedTypeOccupancySummaryData.setBtosRid(BedTypeOccupancySummary.getBtosRid());
        }
        if (BedTypeOccupancySummary.getBtosDate() != null) {
            bedTypeOccupancySummaryData.setBtosDate(DateUtil.convertStringToCalendar(BedTypeOccupancySummary.getBtosDate()));
        }
        if (BedTypeOccupancySummary.getBtosBedTypeRid() != null) {
            bedTypeOccupancySummaryData.setBtosBedTypeRid(BedTypeOccupancySummary.getBtosBedTypeRid());
        }
        if (BedTypeOccupancySummary.getBtosWardRid() != null) {
            bedTypeOccupancySummaryData.setBtosWardRid(BedTypeOccupancySummary.getBtosWardRid());
        }
        if (BedTypeOccupancySummary.getBtosUnitRid() != null) {
            bedTypeOccupancySummaryData.setBtosUnitRid(BedTypeOccupancySummary.getBtosUnitRid());
        }
        if (BedTypeOccupancySummary.getBtosEntRid() != null) {
            bedTypeOccupancySummaryData.setBtosEntRid(BedTypeOccupancySummary.getBtosEntRid());
        }
        if (BedTypeOccupancySummary.getBtosOccupiedBedCount() != null) {
            bedTypeOccupancySummaryData.setBtosOccupiedBedCount(BedTypeOccupancySummary.getBtosOccupiedBedCount());
        }
        if (BedTypeOccupancySummary.getBtosTotalBedCount() != null) {
            bedTypeOccupancySummaryData.setBtosTotalBedCount(BedTypeOccupancySummary.getBtosTotalBedCount());
        }
        if (BedTypeOccupancySummary.getBtosUserRid() != null) {
            bedTypeOccupancySummaryData.setBtosUserRid(BedTypeOccupancySummary.getBtosUserRid());
        }
        if (BedTypeOccupancySummary.getBtosSessionId() != null) {
            bedTypeOccupancySummaryData.setBtosSessionId(BedTypeOccupancySummary.getBtosSessionId());
        }
        return bedTypeOccupancySummaryData;
    }

//    ------------------------------------------------------------------BedWard---------------------------------------------------------------
    public List<BedWard> convertBedWardDatatoBedWard(List<BedWardData> bedWardDataList) throws DcometServiceException {
        List<BedWard> bedWardList = new ArrayList<>();
        for (BedWardData bedWardData : bedWardDataList) {
            bedWardList.add(convertBedWardDatatoBedWard(bedWardData));
        }
        return bedWardList;
    }

    public List<BedWardData> convertBedWardToBedWardData(List<BedWard> bedWardList) throws DcometServiceException {
        List<BedWardData> bedWardDataList = new ArrayList<>();
        for (BedWard bedWard : bedWardList) {
            bedWardDataList.add(convertBedWardToBedWardData(bedWard));
        }
        return bedWardDataList;
    }

    public BedWard convertBedWardDatatoBedWard(BedWardData bedWardData)
            throws DcometServiceException {
        BedWard bedWard = new BedWard();

        if (bedWardData.getBwRid() != null) {
            bedWard.setBwRid(bedWardData.getBwRid());
        }
        if (bedWardData.getBwCode() != null) {
            bedWard.setBwCode(bedWardData.getBwCode());
        }
        if (bedWardData.getBwName() != null) {
            bedWard.setBwName(bedWardData.getBwName());
        }
        if (bedWardData.getBwType() != null) {
            bedWard.setBwType(bedWardData.getBwType());
        }
        if (bedWardData.getBwCurrentType() != null) {
            bedWard.setBwCurrentType(bedWardData.getBwCurrentType());
        }
        if (bedWardData.getBwIsIcu() != null) {
            bedWard.setBwIsIcu(bedWardData.getBwIsIcu());
        }
        if (bedWardData.getBwIsActive() != null) {
            bedWard.setBwIsActive(bedWardData.getBwIsActive());
        }
        if (bedWardData.getBwUnitRid() != null) {
            bedWard.setBwUnitRid(bedWardData.getBwUnitRid());
        }
        if (bedWardData.getBwEntityRid() != null) {
            bedWard.setBwEntityRid(bedWardData.getBwEntityRid());
        }
        if (bedWardData.getBwCreatedUserRid() != null) {
            bedWard.setBwCreatedUserRid(bedWardData.getBwCreatedUserRid());
        }
        if (bedWardData.getBwCreatedDatetime() != null) {
            bedWard.setBwCreatedDatetime(DateUtil.convertCalendarToString(bedWardData.getBwCreatedDatetime()));
        }
        if (bedWardData.getBwModifiedUserRid() != null) {
            bedWard.setBwModifiedUserRid(bedWardData.getBwModifiedUserRid());
        }
        if (bedWardData.getBwModifiedDatetime() != null) {
            bedWard.setBwModifiedDatetime(DateUtil.convertCalendarToString(bedWardData.getBwModifiedDatetime()));
        }
        return bedWard;
    }

    public BedWardData convertBedWardToBedWardData(BedWard BedWard) throws DcometServiceException {
        BedWardData bedWardData = new BedWardData();

        if (BedWard.getBwRid() != null) {
            bedWardData.setBwRid(BedWard.getBwRid());
        }
        if (BedWard.getBwCode() != null) {
            bedWardData.setBwCode(BedWard.getBwCode());
        }
        if (BedWard.getBwName() != null) {
            bedWardData.setBwName(BedWard.getBwName());
        }
        if (BedWard.getBwType() != null) {
            bedWardData.setBwType(BedWard.getBwType());
        }
        if (BedWard.getBwCurrentType() != null) {
            bedWardData.setBwCurrentType(BedWard.getBwCurrentType());
        }
        if (BedWard.getBwIsIcu() != null) {
            bedWardData.setBwIsIcu(BedWard.getBwIsIcu());
        }
        if (BedWard.getBwIsActive() != null) {
            bedWardData.setBwIsActive(BedWard.getBwIsActive());
        }
        if (BedWard.getBwUnitRid() != null) {
            bedWardData.setBwUnitRid(BedWard.getBwUnitRid());
        }
        if (BedWard.getBwEntityRid() != null) {
            bedWardData.setBwEntityRid(BedWard.getBwEntityRid());
        }
        if (BedWard.getBwCreatedUserRid() != null) {
            bedWardData.setBwCreatedUserRid(BedWard.getBwCreatedUserRid());
        }
        if (BedWard.getBwCreatedDatetime() != null) {
            bedWardData.setBwCreatedDatetime(DateUtil.convertStringToCalendar(BedWard.getBwCreatedDatetime()));
        }
        if (BedWard.getBwModifiedUserRid() != null) {
            bedWardData.setBwModifiedUserRid(BedWard.getBwModifiedUserRid());
        }
        if (BedWard.getBwModifiedDatetime() != null) {
            bedWardData.setBwModifiedDatetime(DateUtil.convertStringToCalendar(BedWard.getBwModifiedDatetime()));
        }
        return bedWardData;
    }
//    ----------------------------------------------BirthDetails--------------------------------------------------------------------

    public List<BirthDetails> convertBirthDetailsDatatoBirthDetails(List<BirthDetailsData> birthDetailsDataList) throws DcometServiceException {
        List<BirthDetails> birthDetailsList = new ArrayList<>();
        for (BirthDetailsData birthDetailsData : birthDetailsDataList) {
            birthDetailsList.add(convertBirthDetailsDatatoBirthDetails(birthDetailsData));
        }
        return birthDetailsList;
    }

    public List<BirthDetailsData> convertBirthDetailsToBirthDetailsData(List<BirthDetails> birthDetailsList) throws DcometServiceException {
        List<BirthDetailsData> birthDetailsDataList = new ArrayList<>();
        for (BirthDetails birthDetails : birthDetailsList) {
            birthDetailsDataList.add(convertBirthDetailsToBirthDetailsData(birthDetails));
        }
        return birthDetailsDataList;
    }

    public BirthDetails convertBirthDetailsDatatoBirthDetails(BirthDetailsData birthDetailsData)
            throws DcometServiceException {
        BirthDetails birthDetails = new BirthDetails();

        if (birthDetailsData.getBdRid() != null) {
            birthDetails.setBdRid(birthDetailsData.getBdRid());
        }
        if (birthDetailsData.getBdBabyName() != null) {
            birthDetails.setBdBabyName(birthDetailsData.getBdBabyName());
        }
        if (birthDetailsData.getBdBabyPatRid() != null) {
            birthDetails.setBdBabyPatRid(birthDetailsData.getBdBabyPatRid());
        }
        if (birthDetailsData.getBdBabyVisitRid() != null) {
            birthDetails.setBdBabyVisitRid(birthDetailsData.getBdBabyVisitRid());
        }
        if (birthDetailsData.getBdMotherPatRid() != null) {
            birthDetails.setBdMotherPatRid(birthDetailsData.getBdMotherPatRid());
        }
        if (birthDetailsData.getBdMotherVisitRid() != null) {
            birthDetails.setBdMotherVisitRid(birthDetailsData.getBdMotherVisitRid());
        }
        if (birthDetailsData.getBdMotherMrn() != null) {
            birthDetails.setBdMotherMrn(birthDetailsData.getBdMotherMrn());
        }
        if (birthDetailsData.getBdBabyMrn() != null) {
            birthDetails.setBdBabyMrn(birthDetailsData.getBdBabyMrn());
        }
        if (birthDetailsData.getBdBabyBithDatetime() != null) {
            birthDetails.setBdBabyBithDatetime(DateUtil.convertCalendarToString(birthDetailsData.getBdBabyBithDatetime()));
        }
        if (birthDetailsData.getBdBabyGenderIndex() != null) {
            birthDetails.setBdBabyGenderIndex(birthDetailsData.getBdBabyGenderIndex());
        }
        if (birthDetailsData.getBdBabyBirthWeight() != null) {
            birthDetails.setBdBabyBirthWeight(birthDetailsData.getBdBabyBirthWeight());
        }
        if (birthDetailsData.getBdBabyBirthLength() != null) {
            birthDetails.setBdBabyBirthLength(birthDetailsData.getBdBabyBirthLength());
        }
        if (birthDetailsData.getBdDeliveryMode() != null) {
            birthDetails.setBdDeliveryMode(birthDetailsData.getBdDeliveryMode());
        }
        if (birthDetailsData.getBdDeliveredBy() != null) {
            birthDetails.setBdDeliveredBy(birthDetailsData.getBdDeliveredBy());
        }
        if (birthDetailsData.getBdRemarks() != null) {
            birthDetails.setBdRemarks(birthDetailsData.getBdRemarks());
        }
        if (birthDetailsData.getBdBedRid() != null) {
            birthDetails.setBdBedRid(birthDetailsData.getBdBedRid());
        }
        if (birthDetailsData.getBdWardRid() != null) {
            birthDetails.setBdWardRid(birthDetailsData.getBdWardRid());
        }
        if (birthDetailsData.getBdReportSequence() != null) {
            birthDetails.setBdReportSequence(birthDetailsData.getBdReportSequence());
        }
        if (birthDetailsData.getBdMotherDurationOfPregnancy() != null) {
            birthDetails.setBdMotherDurationOfPregnancy(birthDetailsData.getBdMotherDurationOfPregnancy());
        }
        if (birthDetailsData.getBdBabyHeadCircumference() != null) {
            birthDetails.setBdBabyHeadCircumference(birthDetailsData.getBdBabyHeadCircumference());
        }
        if (birthDetailsData.getBdApgarScore1min() != null) {
            birthDetails.setBdApgarScore1min(birthDetailsData.getBdApgarScore1min());
        }
        if (birthDetailsData.getBdApgarScore5min() != null) {
            birthDetails.setBdApgarScore5min(birthDetailsData.getBdApgarScore5min());
        }
        if (birthDetailsData.getBdApgarScore10min() != null) {
            birthDetails.setBdApgarScore10min(birthDetailsData.getBdApgarScore10min());
        }
        if (birthDetailsData.getBdPresentationType() != null) {
            birthDetails.setBdPresentationType(birthDetailsData.getBdPresentationType());
        }
        if (birthDetailsData.getBdPresentationRemarks() != null) {
            birthDetails.setBdPresentationRemarks(birthDetailsData.getBdPresentationRemarks());
        }
        if (birthDetailsData.getBdBabyStillBirth() != null) {
            birthDetails.setBdBabyStillBirth(birthDetailsData.getBdBabyStillBirth());
        }
        if (birthDetailsData.getBdBabyCauseOfDeath() != null) {
            birthDetails.setBdBabyCauseOfDeath(birthDetailsData.getBdBabyCauseOfDeath());
        }
        if (birthDetailsData.getBdBabyFatherName() != null) {
            birthDetails.setBdBabyFatherName(birthDetailsData.getBdBabyFatherName());
        }
        if (birthDetailsData.getBdCaesareanPlan() != null) {
            birthDetails.setBdCaesareanPlan(birthDetailsData.getBdCaesareanPlan());
        }
        if (birthDetailsData.getBdFatherEductation() != null) {
            birthDetails.setBdFatherEductation(birthDetailsData.getBdFatherEductation());
        }
        if (birthDetailsData.getBdFatherOccupation() != null) {
            birthDetails.setBdFatherOccupation(birthDetailsData.getBdFatherOccupation());
        }
        if (birthDetailsData.getBdMotherEductation() != null) {
            birthDetails.setBdMotherEductation(birthDetailsData.getBdMotherEductation());
        }
        if (birthDetailsData.getBdMotherOccupation() != null) {
            birthDetails.setBdMotherOccupation(birthDetailsData.getBdMotherOccupation());
        }
        if (birthDetailsData.getBdInformantName() != null) {
            birthDetails.setBdInformantName(birthDetailsData.getBdInformantName());
        }
        if (birthDetailsData.getBdAidsUsed() != null) {
            birthDetails.setBdAidsUsed(birthDetailsData.getBdAidsUsed());
        }
        if (birthDetailsData.getBdMotherMarriageAge() != null) {
            birthDetails.setBdMotherMarriageAge(birthDetailsData.getBdMotherMarriageAge());
        }
        if (birthDetailsData.getBdChildrenAlive() != null) {
            birthDetails.setBdChildrenAlive(birthDetailsData.getBdChildrenAlive());
        }
        if (birthDetailsData.getBdRecordedBy() != null) {
            birthDetails.setBdRecordedBy(birthDetailsData.getBdRecordedBy());
        }
        if (birthDetailsData.getBdMrfolderStatus() != null) {
            birthDetails.setBdMrfolderStatus(birthDetailsData.getBdMrfolderStatus());
        }
        if (birthDetailsData.getBdCreatedUserRid() != null) {
            birthDetails.setBdCreatedUserRid(birthDetailsData.getBdCreatedUserRid());
        }
        if (birthDetailsData.getBdCreatedDatetime() != null) {
            birthDetails.setBdCreatedDatetime(DateUtil.convertCalendarToString(birthDetailsData.getBdCreatedDatetime()));
        }
        if (birthDetailsData.getBdModifiedUserRid() != null) {
            birthDetails.setBdModifiedUserRid(birthDetailsData.getBdModifiedUserRid());
        }
        if (birthDetailsData.getBdModifiedDatetime() != null) {
            birthDetails.setBdModifiedDatetime(DateUtil.convertCalendarToString(birthDetailsData.getBdModifiedDatetime()));
        }
        return birthDetails;
    }

    public BirthDetailsData convertBirthDetailsToBirthDetailsData(BirthDetails BirthDetails) throws DcometServiceException {
        BirthDetailsData birthDetailsData = new BirthDetailsData();

        if (BirthDetails.getBdRid() != null) {
            birthDetailsData.setBdRid(BirthDetails.getBdRid());
        }
        if (BirthDetails.getBdBabyName() != null) {
            birthDetailsData.setBdBabyName(BirthDetails.getBdBabyName());
        }
        if (BirthDetails.getBdBabyPatRid() != null) {
            birthDetailsData.setBdBabyPatRid(BirthDetails.getBdBabyPatRid());
        }
        if (BirthDetails.getBdBabyVisitRid() != null) {
            birthDetailsData.setBdBabyVisitRid(BirthDetails.getBdBabyVisitRid());
        }
        if (BirthDetails.getBdMotherPatRid() != null) {
            birthDetailsData.setBdMotherPatRid(BirthDetails.getBdMotherPatRid());
        }
        if (BirthDetails.getBdMotherVisitRid() != null) {
            birthDetailsData.setBdMotherVisitRid(BirthDetails.getBdMotherVisitRid());
        }
        if (BirthDetails.getBdMotherMrn() != null) {
            birthDetailsData.setBdMotherMrn(BirthDetails.getBdMotherMrn());
        }
        if (BirthDetails.getBdBabyMrn() != null) {
            birthDetailsData.setBdBabyMrn(BirthDetails.getBdBabyMrn());
        }
        if (BirthDetails.getBdBabyGenderIndex() != null) {
            birthDetailsData.setBdBabyGenderIndex(BirthDetails.getBdBabyGenderIndex());
        }
        if (BirthDetails.getBdBabyBirthWeight() != null) {
            birthDetailsData.setBdBabyBirthWeight(BirthDetails.getBdBabyBirthWeight());
        }
        if (BirthDetails.getBdBabyBirthLength() != null) {
            birthDetailsData.setBdBabyBirthLength(BirthDetails.getBdBabyBirthLength());
        }
        if (BirthDetails.getBdDeliveryMode() != null) {
            birthDetailsData.setBdDeliveryMode(BirthDetails.getBdDeliveryMode());
        }
        if (BirthDetails.getBdDeliveredBy() != null) {
            birthDetailsData.setBdDeliveredBy(BirthDetails.getBdDeliveredBy());
        }
        if (BirthDetails.getBdRemarks() != null) {
            birthDetailsData.setBdRemarks(BirthDetails.getBdRemarks());
        }
        if (BirthDetails.getBdBedRid() != null) {
            birthDetailsData.setBdBedRid(BirthDetails.getBdBedRid());
        }
        if (BirthDetails.getBdWardRid() != null) {
            birthDetailsData.setBdWardRid(BirthDetails.getBdWardRid());
        }
        if (BirthDetails.getBdReportSequence() != null) {
            birthDetailsData.setBdReportSequence(BirthDetails.getBdReportSequence());
        }
        if (BirthDetails.getBdMotherDurationOfPregnancy() != null) {
            birthDetailsData.setBdMotherDurationOfPregnancy(BirthDetails.getBdMotherDurationOfPregnancy());
        }
        if (BirthDetails.getBdBabyHeadCircumference() != null) {
            birthDetailsData.setBdBabyHeadCircumference(BirthDetails.getBdBabyHeadCircumference());
        }
        if (BirthDetails.getBdApgarScore1min() != null) {
            birthDetailsData.setBdApgarScore1min(BirthDetails.getBdApgarScore1min());
        }
        if (BirthDetails.getBdApgarScore5min() != null) {
            birthDetailsData.setBdApgarScore5min(BirthDetails.getBdApgarScore5min());
        }
        if (BirthDetails.getBdApgarScore10min() != null) {
            birthDetailsData.setBdApgarScore10min(BirthDetails.getBdApgarScore10min());
        }
        if (BirthDetails.getBdPresentationType() != null) {
            birthDetailsData.setBdPresentationType(BirthDetails.getBdPresentationType());
        }
        if (BirthDetails.getBdPresentationRemarks() != null) {
            birthDetailsData.setBdPresentationRemarks(BirthDetails.getBdPresentationRemarks());
        }
        if (BirthDetails.getBdBabyStillBirth() != null) {
            birthDetailsData.setBdBabyStillBirth(BirthDetails.getBdBabyStillBirth());
        }
        if (BirthDetails.getBdBabyCauseOfDeath() != null) {
            birthDetailsData.setBdBabyCauseOfDeath(BirthDetails.getBdBabyCauseOfDeath());
        }
        if (BirthDetails.getBdBabyFatherName() != null) {
            birthDetailsData.setBdBabyFatherName(BirthDetails.getBdBabyFatherName());
        }
        if (BirthDetails.getBdCaesareanPlan() != null) {
            birthDetailsData.setBdCaesareanPlan(BirthDetails.getBdCaesareanPlan());
        }
        if (BirthDetails.getBdFatherEductation() != null) {
            birthDetailsData.setBdFatherEductation(BirthDetails.getBdFatherEductation());
        }
        if (BirthDetails.getBdFatherOccupation() != null) {
            birthDetailsData.setBdFatherOccupation(BirthDetails.getBdFatherOccupation());
        }
        if (BirthDetails.getBdMotherEductation() != null) {
            birthDetailsData.setBdMotherEductation(BirthDetails.getBdMotherEductation());
        }
        if (BirthDetails.getBdMotherOccupation() != null) {
            birthDetailsData.setBdMotherOccupation(BirthDetails.getBdMotherOccupation());
        }
        if (BirthDetails.getBdInformantName() != null) {
            birthDetailsData.setBdInformantName(BirthDetails.getBdInformantName());
        }
        if (BirthDetails.getBdAidsUsed() != null) {
            birthDetailsData.setBdAidsUsed(BirthDetails.getBdAidsUsed());
        }
        if (BirthDetails.getBdMotherMarriageAge() != null) {
            birthDetailsData.setBdMotherMarriageAge(BirthDetails.getBdMotherMarriageAge());
        }
        if (BirthDetails.getBdChildrenAlive() != null) {
            birthDetailsData.setBdChildrenAlive(BirthDetails.getBdChildrenAlive());
        }
        if (BirthDetails.getBdRecordedBy() != null) {
            birthDetailsData.setBdRecordedBy(BirthDetails.getBdRecordedBy());
        }
        if (BirthDetails.getBdMrfolderStatus() != null) {
            birthDetailsData.setBdMrfolderStatus(BirthDetails.getBdMrfolderStatus());
        }
        if (BirthDetails.getBdCreatedUserRid() != null) {
            birthDetailsData.setBdCreatedUserRid(BirthDetails.getBdCreatedUserRid());
        }
        if (BirthDetails.getBdModifiedUserRid() != null) {
            birthDetailsData.setBdModifiedUserRid(BirthDetails.getBdModifiedUserRid());
        }
        return birthDetailsData;
    }

    //----------------------------------Discharge-----------------------------
    public List<Discharge> convertDischargeDataToDischarge(List<DischargeData> dischargeDataList) throws DcometServiceException {
        List<Discharge> dischargeList = new ArrayList<>();
        for (DischargeData dischargeData : dischargeDataList) {
            dischargeList.add(convertDischargeDataToDischarge(dischargeData));
        }
        return dischargeList;
    }

    public List<DischargeData> convertDischargeToDischargeData(List<Discharge> dischargeList)
            throws DcometServiceException {
        List<DischargeData> dischargeDataList = new ArrayList<>();
        for (Discharge discharge : dischargeList) {
            dischargeDataList.add(convertDischargeToDischargeData(discharge));
        }
        return dischargeDataList;
    }

    public Discharge convertDischargeDataToDischarge(DischargeData dischargeData) throws DcometServiceException {
        Discharge discharge = new Discharge();

        if (dischargeData.getId() != null) {
            discharge.setId(dischargeData.getId());
        }
        if (dischargeData.getDisNo() != null) {
            discharge.setDisNo(dischargeData.getDisNo());
        }
        if (dischargeData.getDisPrefix() != null) {
            discharge.setDisPrefix(dischargeData.getDisPrefix());
        }
        if (dischargeData.getDisSequenceNo() != null) {
            discharge.setDisSequenceNo(dischargeData.getDisSequenceNo());
        }
        if (dischargeData.getDisVisitRid() != null) {
            discharge.setDisVisitRid(dischargeData.getDisVisitRid());
        }
        if (dischargeData.getDisPatRid() != null) {
            discharge.setDisPatRid(dischargeData.getDisPatRid());
        }
        if (dischargeData.getDisPatName() != null) {
            discharge.setDisPatName(dischargeData.getDisPatName());
        }
        if (dischargeData.getDisNodes() != null) {
            discharge.setDisNodes(dischargeData.getDisNodes());
        }
        if (dischargeData.getDisDateTime() != null) {
            discharge.setDisDateTime(DateUtil.convertCalendarToString(dischargeData.getDisDateTime()));
        }
        if (dischargeData.getDisFollowUpDate() != null) {
            discharge.setDisFollowUpDate(DateUtil.convertDateToString(dischargeData.getDisFollowUpDate()));
        }
        if (dischargeData.getDisType() != null) {
            discharge.setDisType(dischargeData.getDisType());
        }
        if (dischargeData.getDischargeBy() != null) {
            discharge.setDischargeBy(dischargeData.getDischargeBy());
        }
        if (dischargeData.getDisDeclaration() != null) {
            discharge.setDisDeclaration(dischargeData.getDisDeclaration());
        }
        if (dischargeData.getDisSms() != null) {
            discharge.setDisSms(dischargeData.getDisSms());
        }
        if (dischargeData.getDisNotes() != null) {
            discharge.setDisNotes(dischargeData.getDisNotes());
        }
        if (dischargeData.getDisEntRid() != null) {
            discharge.setDisEntRid(dischargeData.getDisEntRid());
        }
        if (dischargeData.getCreatedDateTime() != null) {
            discharge.setCreatedDateTime(DateUtil.convertCalendarToString(dischargeData.getCreatedDateTime()));
        }
        if (dischargeData.getCreatedUserRid() != null) {
            discharge.setCreatedUserRid(dischargeData.getCreatedUserRid());
        }
        if (dischargeData.getModifiedDateTime() != null) {
            discharge.setModifiedDateTime(DateUtil.convertCalendarToString(dischargeData.getModifiedDateTime()));
        }
        if (dischargeData.getModifiedUserRid() != null) {
            discharge.setModifiedUserRid(dischargeData.getModifiedUserRid());
        }
        return discharge;
    }

    public DischargeData convertDischargeToDischargeData(Discharge discharge) throws DcometServiceException {
        DischargeData dischargeData = new DischargeData();

        if (discharge.getId() != null) {
            dischargeData.setId(discharge.getId());
        }
        if (discharge.getDisNo() != null) {
            dischargeData.setDisNo(discharge.getDisNo());
        }
        if (discharge.getDisPrefix() != null) {
            dischargeData.setDisPrefix(discharge.getDisPrefix());
        }
        if (discharge.getDisSequenceNo() != null) {
            dischargeData.setDisSequenceNo(discharge.getDisSequenceNo());
        }
        if (discharge.getDisVisitRid() != null) {
            dischargeData.setDisVisitRid(discharge.getDisVisitRid());
        }
        if (discharge.getDisPatRid() != null) {
            dischargeData.setDisPatRid(discharge.getDisPatRid());
        }
        if (discharge.getDisPatName() != null) {
            dischargeData.setDisPatName(discharge.getDisPatName());
        }
        if (discharge.getDisNodes() != null) {
            dischargeData.setDisNodes(discharge.getDisNodes());
        }
        if (discharge.getDisDateTime() != null) {
            dischargeData.setDisDateTime(DateUtil.convertStringToCalendar(discharge.getDisDateTime()));
        }
        if (discharge.getDisFollowUpDate() != null) {
            dischargeData.setDisFollowUpDate(DateUtil.convertStringToDate(discharge.getDisFollowUpDate()));
        }
        if (discharge.getDisType() != null) {
            dischargeData.setDisType(discharge.getDisType());
        }
        if (discharge.getDischargeBy() != null) {
            dischargeData.setDischargeBy(discharge.getDischargeBy());
        }
        if (discharge.getDisDeclaration() != null) {
            dischargeData.setDisDeclaration(discharge.getDisDeclaration());
        }
        if (discharge.getDisSms() != null) {
            dischargeData.setDisSms(discharge.getDisSms());
        }
        if (discharge.getDisNotes() != null) {
            dischargeData.setDisNotes(discharge.getDisNotes());
        }
        if (discharge.getDisEntRid() != null) {
            dischargeData.setDisEntRid(discharge.getDisEntRid());
        }
        if (discharge.getCreatedDateTime() != null) {
            dischargeData.setCreatedDateTime(DateUtil.convertStringToCalendar(discharge.getCreatedDateTime()));
        }
        if (discharge.getCreatedUserRid() != null) {
            dischargeData.setCreatedUserRid(discharge.getCreatedUserRid());
        }
        if (discharge.getModifiedDateTime() != null) {
            dischargeData.setModifiedDateTime(DateUtil.convertStringToCalendar(discharge.getModifiedDateTime()));
        }
        if (discharge.getModifiedUserRid() != null) {
            dischargeData.setModifiedUserRid(discharge.getModifiedUserRid());
        }
        return dischargeData;
    }
}
