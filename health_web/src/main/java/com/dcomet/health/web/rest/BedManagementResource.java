package com.dcomet.health.web.rest;

import com.dcomet.health.domain.BedMaster;
import com.dcomet.health.domain.BedCancellationHistory;
import com.dcomet.health.domain.BedCancellationHistorySearchCriteria;
import com.dcomet.health.domain.BedCancellationHistorySearchRequest;
import com.dcomet.health.domain.BedChargeDefinition;
import com.dcomet.health.domain.BedChargeDefinitionSearchCriteria;
import com.dcomet.health.domain.BedChargeDefinitionSearchRequest;
import com.dcomet.health.domain.BedOccupancy;
import com.dcomet.health.domain.BedOccupancyDetails;
import com.dcomet.health.domain.BedOccupancyDetailsSearchCriteria;
import com.dcomet.health.domain.BedOccupancyDetailsSearchRequest;
import com.dcomet.health.domain.BedOccupancySearchCriteria;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.BedTransfer;
import com.dcomet.health.domain.BedTransferRequest;
import com.dcomet.health.domain.BedTransferRequestSearchCriteria;
import com.dcomet.health.domain.BedTransferRequestSearchRequest;
import com.dcomet.health.domain.BedTransferSearchCriteria;
import com.dcomet.health.domain.BedTransferSearchRequest;
import com.dcomet.health.domain.BedGroupM;
import com.dcomet.health.domain.BedGroupMSearchCriteria;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedMasterSearchCriteria;
import com.dcomet.health.domain.BedMasterSearchRequest;
import com.dcomet.health.domain.BedTypeOccupancySummary;
import com.dcomet.health.domain.BedTypeOccupancySummarySearchCriteria;
import com.dcomet.health.domain.BedTypeOccupancySummarySearchRequest;
import com.dcomet.health.domain.BedWard;
import com.dcomet.health.domain.BedWardSearchCriteria;
import com.dcomet.health.domain.BedWardSearchRequest;
import com.dcomet.health.domain.BirthDetails;
import com.dcomet.health.domain.BirthDetailsSearchCriteria;
import com.dcomet.health.domain.BirthDetailsSearchRequest;
import com.dcomet.health.domain.Discharge;
import com.dcomet.health.domain.DischargeSearchCriteria;
import com.dcomet.health.domain.DischargeSearchRequest;
import com.dcomet.health.service.business.BedManagementService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("/bedmanagement/v1")
public class BedManagementResource extends BaseResource {

    @Autowired
    @Qualifier("bedManagementService")
    public BedManagementService bedManagementService;

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public BedMaster save(@Context final SecurityContext securityContext, BedMaster bedMaster, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, bedMaster);
        bedManagementService.save(bedMaster, boRID, boCode, actionCode);
        return bedMaster;
    }

    @POST
    @Path("/bedcancellationhistory/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedCancellationHistory bedCancellationHistory) {
        bedManagementService.saveBedCancellationHistory(bedCancellationHistory);
    }

    @POST
    @Path("/bedcancellationhistory/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedCancellationHistory> search(BedCancellationHistorySearchCriteria bedCancellationHistorySearchCriteria) {
        BedCancellationHistorySearchRequest bedCancellationHistorySearchRequest = new BedCancellationHistorySearchRequest();
        bedCancellationHistorySearchRequest.addBedCancellationHistoryCriteria(bedCancellationHistorySearchCriteria);
        return bedManagementService.getBedCancellationHistory(bedCancellationHistorySearchRequest);
    }

    @POST
    @Path("/bedchargedefinition/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedChargeDefinition bedChargeDefinition) {
        bedManagementService.saveBedChargeDefinition(bedChargeDefinition);
    }

    @POST
    @Path("/bedchargedefinition/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedChargeDefinition> search(BedChargeDefinitionSearchCriteria bedChargeDefinitionSearchCriteria) {
        BedChargeDefinitionSearchRequest bedChargeDefinitionSearchRequest = new BedChargeDefinitionSearchRequest();
        bedChargeDefinitionSearchRequest.addBedChargeDefinitionCriteria(bedChargeDefinitionSearchCriteria);
        return bedManagementService.getBedChargeDefinition(bedChargeDefinitionSearchRequest);
    }

    @POST
    @Path("/bed/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedMaster bed) {
        bedManagementService.saveBed(bed);
    }

    @POST
    @Path("/bedMaster/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedMaster> search(BedMasterSearchCriteria bedMasterSearchCriteria) {
        BedMasterSearchRequest bedMasterSearchRequest = new BedMasterSearchRequest();
        bedMasterSearchRequest.addBedMasterCriteria(bedMasterSearchCriteria);
        return bedManagementService.getBedMaster(bedMasterSearchRequest);
    }

    @POST
    @Path("/bedoccupancy/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedOccupancy bedOccupancy) {
        addSecurityContext(securityContext, bedOccupancy);
        bedManagementService.saveBedOccupancy(bedOccupancy);
    }

    @POST
    @Path("/bedoccupancy/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedOccupancy> search(BedOccupancySearchCriteria bedOccupancySearchCriteria) {
        BedOccupancySearchRequest bedOccupancySearchRequest = new BedOccupancySearchRequest();
        bedOccupancySearchRequest.addBedOccupancyCriteria(bedOccupancySearchCriteria);
        return bedManagementService.getBedOccupancy(bedOccupancySearchRequest);
    }

    @POST
    @Path("/occupancydetails/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedOccupancyDetails bedOccupancyDetails) {
        addSecurityContext(securityContext, bedOccupancyDetails);
        bedManagementService.saveBedOccupancyDetails(bedOccupancyDetails);
    }

    @POST
    @Path("/bedoccupancydetails/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedOccupancyDetails> search(BedOccupancyDetailsSearchCriteria bedOccupancyDetailsSearchCriteria) {
        BedOccupancyDetailsSearchRequest bedOccupancyDetailsSearchRequest = new BedOccupancyDetailsSearchRequest();
        bedOccupancyDetailsSearchRequest.addBedOccupancyDetailsCriteria(bedOccupancyDetailsSearchCriteria);
        return bedManagementService.getBedOccupancyDetails(bedOccupancyDetailsSearchRequest);
    }

    @POST
    @Path("/bedtransfer/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedTransfer bedTransfer) {
        bedManagementService.saveBedTransfer(bedTransfer);
    }

    @POST
    @Path("/bedtransfer/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedTransfer> search(BedTransferSearchCriteria bedTransferSearchCriteria) {
        BedTransferSearchRequest bedTransferSearchRequest = new BedTransferSearchRequest();
        bedTransferSearchRequest.addBedTransferCriteria(bedTransferSearchCriteria);
        return bedManagementService.getBedTransfer(bedTransferSearchRequest);
    }

    @POST
    @Path("/bedtransferrequest/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedTransferRequest bedTransferRequest) {
        bedManagementService.saveBedTransferRequest(bedTransferRequest);
    }

    @POST
    @Path("/bedtransferrequest/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedTransferRequest> search(BedTransferRequestSearchCriteria bedTransferRequestSearchCriteria) {
        BedTransferRequestSearchRequest bedTransferRequestSearchRequest = new BedTransferRequestSearchRequest();
        bedTransferRequestSearchRequest.addBedTransferRequestCriteria(bedTransferRequestSearchCriteria);
        return bedManagementService.getBedTransferRequest(bedTransferRequestSearchRequest);
    }

    @POST
    @Path("/bedGroupMaster/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedGroupM bedGroupM) {
        addSecurityContext(securityContext, bedGroupM);
        bedManagementService.saveBedGroupMaster(bedGroupM, true);
    }

    @POST
    @Path("/bedGroupMaster/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedGroupM> search(@Context final SecurityContext securityContext, BedGroupMSearchCriteria bedTypeMSearchCriteria) {
        BedGroupMSearchRequest bedTypeMSearchRequest = new BedGroupMSearchRequest();
        addSecurityContext(securityContext, bedTypeMSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bgmBedEntityRid", bedTypeMSearchRequest.getEntityRid()));
        bedTypeMSearchRequest.setSearchCriterionList(searchCriterionList);
        bedTypeMSearchRequest.addBedGroupMCriteria(bedTypeMSearchCriteria);
        return bedManagementService.getBedGroupMaster(bedTypeMSearchRequest, true);
    }

    @POST
    @Path("/bedtypeoccupancysummary/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedTypeOccupancySummary bedTypeOccupancySummary) {
        bedManagementService.saveBedTypeOccupancySummary(bedTypeOccupancySummary);
    }

    @POST
    @Path("/bedtypeoccupancysummary/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedTypeOccupancySummary> search(BedTypeOccupancySummarySearchCriteria bedTypeOccupancySummarySearchCriteria) {
        BedTypeOccupancySummarySearchRequest bedTypeOccupancySummarySearchRequest = new BedTypeOccupancySummarySearchRequest();
        bedTypeOccupancySummarySearchRequest.addBedTypeOccupancySummaryCriteria(bedTypeOccupancySummarySearchCriteria);
        return bedManagementService.getBedTypeOccupancySummary(bedTypeOccupancySummarySearchRequest);
    }

    @POST
    @Path("/bedward/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BedWard bedWard) {
        bedManagementService.saveBedWard(bedWard);
    }

    @POST
    @Path("/bedward/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BedWard> search(BedWardSearchCriteria bedWardSearchCriteria) {
        BedWardSearchRequest bedWardSearchRequest = new BedWardSearchRequest();
        bedWardSearchRequest.addBedWardCriteria(bedWardSearchCriteria);
        return bedManagementService.getBedWard(bedWardSearchRequest);
    }

    @POST
    @Path("/birthdetails/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, BirthDetails birthDetails) {
        bedManagementService.saveBirthDetails(birthDetails);
    }

    @POST
    @Path("/birthdetails/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BirthDetails> search(BirthDetailsSearchCriteria birthDetailsSearchCriteria) {
        BirthDetailsSearchRequest birthDetailsSearchRequest = new BirthDetailsSearchRequest();
        birthDetailsSearchRequest.addBirthDetailsCriteria(birthDetailsSearchCriteria);
        return bedManagementService.getBirthDetails(birthDetailsSearchRequest);
    }

    @POST
    @Path("/discharge/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Discharge discharge) {
        addSecurityContext(securityContext, discharge);
        bedManagementService.saveDischargeDetails(discharge);
    }

    @POST
    @Path("/dischargedetails/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Discharge> search(DischargeSearchCriteria dischargeSearchCriteria) {
        DischargeSearchRequest dischargeSearchRequest = new DischargeSearchRequest();
        dischargeSearchRequest.addDischargeCriteria(dischargeSearchCriteria);
        return bedManagementService.getDischargeDetails(dischargeSearchRequest);
    }
}
