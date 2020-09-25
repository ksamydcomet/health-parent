package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
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
import java.util.List;

public interface BedManagementService extends WorkFlowService {

    public List<BedGroupM> getBedGroupMaster(BedGroupMSearchRequest bedTypeMSearchRequest, boolean includeChild) throws DcometServiceException;

    public void saveBedGroupMaster(BedGroupM bedTypeM, boolean includeChild) throws DcometServiceException;
    
    public List<BedMaster> getBedMaster(BedMasterSearchRequest bedMasterSearchRequest) throws DcometServiceException;

    public List<BedCancellationHistory> getBedCancellationHistory(BedCancellationHistorySearchRequest bedCancellationHistorySearchRequest) throws DcometServiceException;

    public void saveBedCancellationHistory(BedCancellationHistory bedCancellationHistory) throws DcometServiceException;

    public List<BedChargeDefinition> getBedChargeDefinition(BedChargeDefinitionSearchRequest bedChargeDefinitionSearchRequest) throws DcometServiceException;

    public void saveBedChargeDefinition(BedChargeDefinition bedChargeDefinition) throws DcometServiceException;

    public List<BedMaster> getBed(BedSearchRequest bedSearchRequest) throws DcometServiceException;

    public void saveBed(BedMaster bed) throws DcometServiceException;

    public List<BedOccupancy> getBedOccupancy(BedOccupancySearchRequest bedOccupancySearchRequest) throws DcometServiceException;

    public void saveBedOccupancy(BedOccupancy bedOccupancy) throws DcometServiceException;

    public List<BedOccupancyDetails> getBedOccupancyDetails(BedOccupancyDetailsSearchRequest bedOccupancyDetailsSearchRequest) throws DcometServiceException;

    public void saveBedOccupancyDetails(BedOccupancyDetails bedOccupancyDetails) throws DcometServiceException;

    public List<BedTransfer> getBedTransfer(BedTransferSearchRequest bedTransferSearchRequest) throws DcometServiceException;

    public void saveBedTransfer(BedTransfer bedTransfer) throws DcometServiceException;

    public List<BedTransferRequest> getBedTransferRequest(BedTransferRequestSearchRequest bedTransferRequestSearchRequest) throws DcometServiceException;

    public void saveBedTransferRequest(BedTransferRequest bedTransferRequest) throws DcometServiceException;

    public List<BedTypeOccupancySummary> getBedTypeOccupancySummary(BedTypeOccupancySummarySearchRequest bedTypeOccupancySummarySearchRequest) throws DcometServiceException;

    public void saveBedTypeOccupancySummary(BedTypeOccupancySummary bedTypeOccupancySummary) throws DcometServiceException;

    public List<BedWard> getBedWard(BedWardSearchRequest bedWardSearchRequest) throws DcometServiceException;

    public void saveBedWard(BedWard bedWard) throws DcometServiceException;

    public List<BirthDetails> getBirthDetails(BirthDetailsSearchRequest birthDetailsSearchRequest) throws DcometServiceException;

    public void saveBirthDetails(BirthDetails birthDetails) throws DcometServiceException;

    public List<Discharge> getDischargeDetails(DischargeSearchRequest dischargeSearchRequest) throws DcometServiceException;

    public Discharge getDischarge(Integer id) throws DcometServiceException;

    public void saveDischargeDetails(Discharge discharge) throws DcometServiceException;

}
