package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.adapter.BedManagementAdapter;
import com.dcomet.health.dao.BedManagementDAO;
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
import com.dcomet.health.domain.BedCancellationHistorySearchRequest;
import com.dcomet.health.domain.BedChargeDefinition;
import com.dcomet.health.domain.BedChargeDefinitionSearchRequest;
import com.dcomet.health.domain.BedOccupancy;
import com.dcomet.health.domain.BedOccupancyDetails;
import com.dcomet.health.domain.BedOccupancyDetailsSearchRequest;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.BedSearchRequest;
import com.dcomet.health.domain.BedTransfer;
import com.dcomet.health.domain.BedTransferRequest;
import com.dcomet.health.domain.BedTransferRequestSearchRequest;
import com.dcomet.health.domain.BedTransferSearchRequest;
import com.dcomet.health.domain.BedGroupM;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedMasterSearchRequest;
import com.dcomet.health.domain.BedTypeOccupancySummary;
import com.dcomet.health.domain.BedTypeOccupancySummarySearchRequest;
import com.dcomet.health.domain.BedWard;
import com.dcomet.health.domain.BedWardSearchRequest;
import com.dcomet.health.domain.BirthDetails;
import com.dcomet.health.domain.BirthDetailsSearchRequest;
import com.dcomet.health.domain.Discharge;
import com.dcomet.health.domain.DischargeSearchRequest;
import com.dcomet.health.service.business.BedManagementService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.module.domain.AutoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bedManagementService")
@Transactional(propagation = Propagation.SUPPORTS)
public class BedManagementServiceImpl extends BaseWorkFlowServiceImpl implements BedManagementService {

    @Autowired
    @Qualifier("bedManagementDAO")
    BedManagementDAO bedManagementDAO;

    @Autowired
    @Qualifier("bedManagementAdapter")
    BedManagementAdapter bedManagementAdapter;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Override
    public List<BedCancellationHistory> getBedCancellationHistory(BedCancellationHistorySearchRequest bedCancellationHistorySearchRequest) throws DcometServiceException {
        List<BedCancellationHistory> result = null;
        try {
            List<BedCancellationHistoryData> listData = bedManagementDAO.getBedCancellationHistory(bedCancellationHistorySearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedCancellationHistoryDatatoBedCancellationHistory(listData);
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
    public void saveBedCancellationHistory(BedCancellationHistory bedCancellationHistory) throws DcometServiceException {
        try {
            bedManagementDAO.saveBedCancellationHistory(bedManagementAdapter.convertBedCancellationHistorytoBedCancellationHistoryData(bedCancellationHistory));
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<BedChargeDefinition> getBedChargeDefinition(BedChargeDefinitionSearchRequest bedChargeDefinitionSearchRequest) throws DcometServiceException {
        List<BedChargeDefinition> result = null;
        try {
            List<BedChargeDefinitionData> listData = bedManagementDAO.getBedChargeDefinition(bedChargeDefinitionSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedChargeDefinitionDatatoBedChargeDefinition(listData);
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
    public void saveBedChargeDefinition(BedChargeDefinition bedChargeDefinition) throws DcometServiceException {
        bedManagementDAO.saveBedChargeDefinition(bedManagementAdapter.convertBedChargeDefinitionToBedChargeDefinitionData(bedChargeDefinition));
    }

    @Override
    public List<BedMaster> getBed(BedSearchRequest bedSearchRequest) throws DcometServiceException {
        List<BedMaster> result = null;
        try {
            List<BedMasterData> listData = bedManagementDAO.getBed(bedSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedMasterDataToBedMaster(listData);
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
    public List<BedMaster> getBedMaster(BedMasterSearchRequest bedMasterSearchRequest) throws DcometServiceException {
        List<BedMaster> result = null;
        try {
            List<BedMasterData> listData = bedManagementDAO.getBedMaster(bedMasterSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedMasterDataToBedMaster(listData);
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
    public void saveBed(BedMaster bed) throws DcometServiceException {
        bedManagementDAO.saveBed(bedManagementAdapter.convertBedMasterToBedMasterData(bed));
    }

    @Override
    public List<BedOccupancy> getBedOccupancy(BedOccupancySearchRequest bedOccupancySearchRequest) throws DcometServiceException {
        List<BedOccupancy> result = null;
        try {
            List<BedOccupancyData> listData = bedManagementDAO.getBedOccupancy(bedOccupancySearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedOccupancyDatatoBedOccupancy(listData);
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
    public void saveBedOccupancy(BedOccupancy bedOccupancy) throws DcometServiceException {
        try {
            bedManagementDAO.saveBedOccupancyData(bedManagementAdapter.convertBedOccupancyToBedOccupancyData(bedOccupancy));
//            if (bedOccupancy.getBocBedRid() != null) {
//                BedTypeMSearchRequest bedTypeMSearchRequest = new BedTypeMSearchRequest();
//                List<Criterion> criterionList = new ArrayList<>();
//                criterionList.add(Restrictions.like("btmRid", bedOccupancy.getBocBedRid()));
//                bedTypeMSearchRequest.setSearchCriterionList(criterionList);
//                List<BedTypeM> bedTypeMs = getBedTypeM(bedTypeMSearchRequest);
//                if (CollectionUtils.isNotEmpty(bedTypeMs)) {
//                    for (BedTypeM bedTypeM : bedTypeMs) {
//                        bedTypeM.setBtmState(1);
//                        bedTypeM.setBtmStatus(1);
//                        saveBedTypeM(bedTypeM);
//                    }
//                }
//            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

    }

    @Override
    public List<BedOccupancyDetails> getBedOccupancyDetails(BedOccupancyDetailsSearchRequest bedOccupancyDetailsSearchRequest) throws DcometServiceException {
        List<BedOccupancyDetails> result = null;
        try {
            List<BedOccupancyDetailsData> listData = bedManagementDAO.getBedOccupancyDetails(bedOccupancyDetailsSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedOccupancyDetailsDatatoBedOccupancyDetails(listData);
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
    public void saveBedOccupancyDetails(BedOccupancyDetails bedOccupancyDetails) throws DcometServiceException {
        try {
            bedManagementDAO.saveBedOccupancyDetails(bedManagementAdapter.convertBedOccupancyDetailsToBedOccupancyDetailsData(bedOccupancyDetails));
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<BedTransfer> getBedTransfer(BedTransferSearchRequest bedTransferSearchRequest) throws DcometServiceException {
        List<BedTransfer> result = null;
        try {
            List<BedTransferData> listData = bedManagementDAO.getBedTransfer(bedTransferSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedTransferDatatoBedTransfer(listData);
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
    public void saveBedTransfer(BedTransfer bedTransfer) throws DcometServiceException {
        bedManagementDAO.saveBedTransfer(bedManagementAdapter.convertBedTransferToBedTransferData(bedTransfer));
    }

    @Override
    public List<BedTransferRequest> getBedTransferRequest(BedTransferRequestSearchRequest bedTransferRequestSearchRequest) throws DcometServiceException {
        List<BedTransferRequest> result = null;
        try {
            List<BedTransferRequestData> listData = bedManagementDAO.getBedTransferRequest(bedTransferRequestSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedTransferRequestDatatoBedTransferRequest(listData);
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
    public void saveBedTransferRequest(BedTransferRequest bedTransferRequest) throws DcometServiceException {
        bedManagementDAO.saveBedTransferRequest(bedManagementAdapter.convertBedTransferRequestToBedTransferRequestData(bedTransferRequest));
    }

    @Override
    public List<BedGroupM> getBedGroupMaster(BedGroupMSearchRequest bedGroupMSearchRequest, boolean includeChild) throws DcometServiceException {
        List<BedGroupM> result = null;
        try {
            List<BedGroupMData> listData = bedManagementDAO.getBedGroupMaster(bedGroupMSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedGroupMDatatoBedGroupM(listData);
                if (includeChild) {
                    for (BedGroupM bedGroupM : result) {
                        BedMasterSearchRequest childSearchRequest = new BedMasterSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("bedBgmRid", bedGroupM.getBgmRid()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<BedMaster> bedMasterResult = getBedMaster(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(bedMasterResult)) {
                            bedGroupM.setBedMasterList(bedMasterResult);
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
    public void saveBedGroupMaster(BedGroupM bedGroupM, boolean includeChild) throws DcometServiceException {
        try {
            BedGroupMData bedGroupMData = bedManagementAdapter.convertBedGroupMToBedGroupMData(bedGroupM);
            bedManagementDAO.saveBedGroupMaster(bedGroupMData);
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(bedGroupM.getBedMasterList())) {
                    List<BedMasterData> bedMasterDataList = bedManagementAdapter.convertBedMasterToBedMasterData(bedGroupM.getBedMasterList());
                    for (BedMasterData bedMasterData : bedMasterDataList) {
                        if (bedMasterData.getBedRid() == null) {
                            AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("BED", bedGroupM.getEntityRid());
                            bedMasterData.setBedNo(autoNumber.getAutoNumber());
                            bedMasterData.setBedPrefix(autoNumber.getAutoPrefix());
                            bedMasterData.setBedSequence(autoNumber.getAutoSequenceNumber());
                            dataDictionaryService.saveAutoNumberIncrement("BED", bedGroupM.getEntityRid());
                        }
                        bedMasterData.setBedBgmRid(bedGroupMData.getBgmRid()); 
                        bedMasterData.setBedEntityRid(bedGroupM.getEntityRid());
                    }
                    bedManagementDAO.saveBedMaster(bedMasterDataList);
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
    public List<BedTypeOccupancySummary> getBedTypeOccupancySummary(BedTypeOccupancySummarySearchRequest bedTypeOccupancySummarySearchRequest) throws DcometServiceException {
        List<BedTypeOccupancySummary> result = null;
        try {
            List<BedTypeOccupancySummaryData> listData = bedManagementDAO.getBedTypeOccupancySummary(bedTypeOccupancySummarySearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedTypeOccupancySummaryDatatoBedTypeOccupancySummary(listData);
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
    public void saveBedTypeOccupancySummary(BedTypeOccupancySummary bedTypeOccupancySummary) throws DcometServiceException {
        bedManagementDAO.saveBedTypeOccupancySummary(bedManagementAdapter.convertBedTypeOccupancySummaryToBedTypeOccupancySummaryData(bedTypeOccupancySummary));
    }

    @Override
    public List<BedWard> getBedWard(BedWardSearchRequest bedWardSearchRequest) throws DcometServiceException {
        List<BedWard> result = null;
        try {
            List<BedWardData> listData = bedManagementDAO.getBedWard(bedWardSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBedWardDatatoBedWard(listData);
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
    public void saveBedWard(BedWard bedWard) throws DcometServiceException {
        bedManagementDAO.saveBedWard(bedManagementAdapter.convertBedWardToBedWardData(bedWard));
    }

    @Override
    public List<BirthDetails> getBirthDetails(BirthDetailsSearchRequest birthDetailsSearchRequest) throws DcometServiceException {
        List<BirthDetails> result = null;
        try {
            List<BirthDetailsData> listData = bedManagementDAO.getBirthDetails(birthDetailsSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertBirthDetailsDatatoBirthDetails(listData);
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
    public void saveBirthDetails(BirthDetails birthDetails) throws DcometServiceException {
        bedManagementDAO.saveBirthDetails(bedManagementAdapter.convertBirthDetailsToBirthDetailsData(birthDetails));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) throws DcometServiceException {
        BedMaster bedMaster = (BedMaster) object;

        if (nextState == -2) {
            nextState = 1;
        }
        bedMaster.setBedState(nextState);
        bedMaster.setBedStatus(nextState);
        BedMasterData bedMasterData = bedManagementAdapter.convertBedMasterToBedMasterData(bedMaster);
        bedManagementDAO.saveBed(bedMasterData);
        return bedMasterData.getBedRid();
    }

    @Override
    public Integer getCurrentState(Integer boRID) throws DcometServiceException {
        Integer boState = 0;
        List<BedMasterData> bedMasterDataList = load(boRID);
        if (CollectionUtils.isNotEmpty(bedMasterDataList)) {
//            boState = bedTypeMDataList.get(0).getBtmStatus();
        }
        return boState;
    }

    @Override
    public String buildBODescriptor(String[] strings, Integer boRID) throws DcometServiceException {
        List<BedMasterData> bedMasterDataList = load(boRID);
        StringBuilder boDescriptor = new StringBuilder();
        if (CollectionUtils.isNotEmpty(bedMasterDataList)) {
//            boDescriptor.append(bedTypeMDataList.get(0).getBtmNo()).append("&");
//            boDescriptor.append(DateUtil.convertCalendarToString(bedMasterDataList.get(0).getCreatedDateTime()));
        }
        return boDescriptor.toString();
    }

    private List<BedMasterData> load(Integer boRID) {
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("bedRid", boRID));
        List<BedMasterData> bedMasterDataList = bedManagementDAO.getBedMasterList(criterionList);
        return bedMasterDataList;
    }

    @Override
    public List<Discharge> getDischargeDetails(DischargeSearchRequest dischargeSearchRequest) throws DcometServiceException {
        List<Discharge> result = null;
        try {
            List<DischargeData> listData = bedManagementDAO.getDischargeDetails(dischargeSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = bedManagementAdapter.convertDischargeDataToDischarge(listData);
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
    public Discharge getDischarge(Integer id) throws DcometServiceException {
        DischargeSearchRequest dischargeSearchRequest = new DischargeSearchRequest();
        dischargeSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<Discharge> result = getDischargeDetails(dischargeSearchRequest);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDischargeDetails(Discharge discharge) throws DcometServiceException {
        try {
//            if (discharge.getId() == null) {
//                AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("DIS", discharge.getEntityRid());
//                discharge.setDisNo(autoNumber.getAutoNumber());
//                discharge.setDisPrefix(autoNumber.getAutoPrefix());
//                discharge.setDisSequenceNo(autoNumber.getAutoSequenceNumber());
//                dataDictionaryService.saveAutoNumberIncrement("DIS", discharge.getEntityRid());
//            }

            if (discharge.getId() == null) {
                discharge.setDisEntRid(discharge.getEntityRid());
                bedManagementDAO.saveDischargeDetails(bedManagementAdapter.convertDischargeToDischargeData(discharge));
            } else {
                Discharge dischargedb = getDischarge(discharge.getId());
                discharge.setDisDateTime(dischargedb.getDisDateTime());
                discharge.setDisFollowUpDate(dischargedb.getDisFollowUpDate());
                discharge.setDisType(dischargedb.getDisType());
                discharge.setDischargeBy(dischargedb.getDischargeBy());
                discharge.setDisDeclaration(dischargedb.getDisDeclaration());
                discharge.setDisSms(dischargedb.getDisSms());
                discharge.setDisNotes(dischargedb.getDisNotes());
                discharge.setDisEntRid(discharge.getEntityRid());
                DischargeData dischargeData = bedManagementAdapter.convertDischargeToDischargeData(discharge);
                bedManagementDAO.saveDischargeDetails(dischargeData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }
}
