package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
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
import com.dcomet.health.domain.BedCancellationHistorySearchRequest;
import com.dcomet.health.domain.BedChargeDefinitionSearchRequest;
import com.dcomet.health.domain.BedOccupancyDetailsSearchRequest;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.BedSearchRequest;
import com.dcomet.health.domain.BedTransferRequestSearchRequest;
import com.dcomet.health.domain.BedTransferSearchRequest;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedMasterSearchRequest;
import com.dcomet.health.domain.BedTypeOccupancySummarySearchRequest;
import com.dcomet.health.domain.BedWardSearchRequest;
import com.dcomet.health.domain.BirthDetailsSearchRequest;
import com.dcomet.health.domain.DischargeSearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;

public interface BedManagementDAO {

    public List<BedCancellationHistoryData> getBedCancellationHistory(BedCancellationHistorySearchRequest bedCancellationHistorySearchRequest) throws DcometDAOException;

    public void saveBedCancellationHistory(BedCancellationHistoryData bedCancellationHistoryData) throws DcometDAOException;

    public List<BedChargeDefinitionData> getBedChargeDefinition(BedChargeDefinitionSearchRequest bedChargeDefinitionSearchRequest) throws DcometDAOException;

    public void saveBedChargeDefinition(BedChargeDefinitionData bedChargeDefinitionData) throws DcometDAOException;

    public List<BedMasterData> getBed(BedSearchRequest bedSearchRequest) throws DcometDAOException;

    public void saveBed(BedMasterData bedData) throws DcometDAOException;
    
    public void saveBedMaster(List<BedMasterData> bedDataList) throws DcometDAOException;
    
    public List<BedMasterData> getBedMaster(BedMasterSearchRequest bedMasterSearchRequest) throws DcometDAOException;

    public List<BedOccupancyData> getBedOccupancy(BedOccupancySearchRequest bedOccupancySearchRequest) throws DcometDAOException;

    public void saveBedOccupancyData(BedOccupancyData bedOccupancyData) throws DcometDAOException;

    public List<BedOccupancyDetailsData> getBedOccupancyDetails(BedOccupancyDetailsSearchRequest bedOccupancyDetailsSearchRequest) throws DcometDAOException;

    public void saveBedOccupancyDetails(BedOccupancyDetailsData bedOccupancyDetailsData) throws DcometDAOException;

    public List<BedTransferData> getBedTransfer(BedTransferSearchRequest bedTransferSearchRequest) throws DcometDAOException;

    public void saveBedTransfer(BedTransferData bedTransferData) throws DcometDAOException;

    public List<BedTransferRequestData> getBedTransferRequest(BedTransferRequestSearchRequest bedTransferRequestSearchRequest) throws DcometDAOException;

    public void saveBedTransferRequest(BedTransferRequestData bedTransferRequestData) throws DcometDAOException;

    public List<BedGroupMData> getBedGroupMaster(BedGroupMSearchRequest bedTypeMSearchRequest) throws DcometDAOException;

    public List<BedGroupMData> getBedTypeMList(List<Criterion> criterionList) throws DcometDAOException;
    
    public List<BedMasterData> getBedMasterList(List<Criterion> criterionList) throws DcometDAOException;

    public void saveBedGroupMaster(BedGroupMData bedTypeMData) throws DcometDAOException;

    public List<BedTypeOccupancySummaryData> getBedTypeOccupancySummary(BedTypeOccupancySummarySearchRequest bedTypeOccupancySummarySearchRequest) throws DcometDAOException;

    public void saveBedTypeOccupancySummary(BedTypeOccupancySummaryData bedTypeOccupancySummaryData) throws DcometDAOException;

    public List<BedWardData> getBedWard(BedWardSearchRequest bedWardSearchRequest) throws DcometDAOException;

    public void saveBedWard(BedWardData bedWardData) throws DcometDAOException;

    public List<BirthDetailsData> getBirthDetails(BirthDetailsSearchRequest birthDetailsSearchRequest) throws DcometDAOException;

    public void saveBirthDetails(BirthDetailsData birthDetailsData) throws DcometDAOException;
    
    public List<DischargeData> getDischargeDetails(DischargeSearchRequest dischargeSearchRequest) throws DcometDAOException;

    public void saveDischargeDetails(DischargeData dischargeData) throws DcometDAOException;

}
