package com.dcomet.health.web.rest;

import com.dcomet.health.domain.PrintTemplate;
import com.dcomet.health.domain.PrintTemplateSearchCriteria;
import com.dcomet.health.domain.PrintTemplateSearchRequest;
import com.dcomet.health.service.business.UtilityService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("utility/v1")
public class UtilityResource extends BaseResource {

    @Autowired
    @Qualifier("utilityService")
    UtilityService utilityService;

    @POST
    @Path("/Template/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, List<PrintTemplate> printTemplateList) {
        utilityService.savePrintTemplate(printTemplateList);
    }

    @POST
    @Path("/Template/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<PrintTemplate> searchPrintTemplate(PrintTemplateSearchCriteria PrintTemplateSearchCriteria) {
        PrintTemplateSearchRequest PrintTemplateSearchRequest = new PrintTemplateSearchRequest();
        PrintTemplateSearchRequest.addPrintTemplateSearchCriteria(PrintTemplateSearchCriteria);
        return utilityService.getPrintTemplate(PrintTemplateSearchRequest);
    }

}
