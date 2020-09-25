package com.dcomet.health.web.rest;

import com.dcomet.module.laboratory.domain.LabResultH;
import com.dcomet.module.laboratory.domain.LabResultHSearchRequest;
import com.dcomet.module.laboratory.domain.LabResultHSearchCriteria;
import com.dcomet.module.master.domain.LabServiceExtn;
import com.dcomet.module.master.domain.LabServiceExtnSearchCriteria;
import com.dcomet.module.master.domain.LabServiceExtnSearchRequest;
import com.dcomet.health.service.business.LaboratoryService;
import com.dcomet.health.service.business.MasterService;
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

/**
 *
 * @author Dev4
 */
@Component
@Path("laboratory/v1")
public class LaboratoryResource extends BaseResource {

    @Autowired
    @Qualifier("laboratoryService")
    public LaboratoryService laboratoryService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    @POST
    @Path("/labresult/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<LabResultH> search(@Context final SecurityContext securityContext, LabResultHSearchCriteria labResultSearchCriteria) {
        LabResultHSearchRequest labResultHSearchRequest = new LabResultHSearchRequest();
        addSecurityContext(securityContext, labResultHSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("lrhEntityRid", labResultHSearchRequest.getEntityRid()));
        labResultHSearchRequest.setSearchCriterionList(searchCriterionList);
        labResultHSearchRequest.addLabResultHCriteria(labResultSearchCriteria);
        return laboratoryService.getLabResultH(labResultHSearchRequest, true);
    }

    @POST
    @Path("/laboratory/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<LabServiceExtn> searchLabServiceExtn(LabServiceExtnSearchCriteria labServiceExtnSearchCriteria) {
        LabServiceExtnSearchRequest labServiceExtnSearchRequest = new LabServiceExtnSearchRequest();
        labServiceExtnSearchRequest.addLabServiceExtnCriteria(labServiceExtnSearchCriteria);
        return masterService.getLabServiceExtn(labServiceExtnSearchRequest);
    }

    @POST
    @Path("/modify/labresults")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, LabResultH labResultH) {
        addSecurityContext(securityContext, labResultH);
        laboratoryService.saveLabResultH(labResultH, true);
    }

    @POST
    @Path("/save/{boRID}/{boCode}/{actionCode}")
    @Consumes("application/json")
    @Produces("application/json")
    public LabResultH save(@Context final SecurityContext securityContext, LabResultH master, @PathParam("boRID") final Integer boRID, @PathParam("boCode") final String boCode,
            @PathParam("actionCode") final String actionCode) {
        addSecurityContext(securityContext, master);
        laboratoryService.save(master, boRID, boCode, actionCode);
        return master;
    }

    @POST
    @Path("/lab/print/{groupId}")
    @Consumes("application/json")
    @Produces("application/json")
    public String getLabPrint(@Context final SecurityContext securityContext, LabResultHSearchCriteria labResultHSearchCriteria, @PathParam("groupId") final Integer groupId) {
        LabResultHSearchRequest labResultHSearchRequest = new LabResultHSearchRequest();
        addSecurityContext(securityContext, labResultHSearchRequest);
        labResultHSearchRequest.addLabResultHCriteria(labResultHSearchCriteria);
        return laboratoryService.getLabServicePrint(labResultHSearchRequest, groupId);
    }
}
