package com.dcomet.health.web.rest;                   

import com.dcomet.fw.workflow.domain.BOMaster;
import com.dcomet.fw.workflow.domain.BOMasterSearchRequest;
import com.dcomet.fw.workflow.domain.BOStateTransitionLog;
import com.dcomet.fw.workflow.domain.BOStateTransitionLogSearchCriteria;
import com.dcomet.fw.workflow.domain.BOStateTransitionLogSearchRequest;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.module.domain.Country;
import com.dcomet.module.domain.CountrySearchCriteria;
import com.dcomet.module.domain.CountrySearchRequest;
import com.dcomet.module.domain.CurrencyM;
import com.dcomet.module.domain.CurrencyMSearchCriteria;
import com.dcomet.module.domain.CurrencyMSearchRequest;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchCriteria;
import com.dcomet.module.domain.DdictSearchRequest;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.Feature;
import com.dcomet.module.domain.DdictType;
import com.dcomet.module.domain.FeatureEntityMap;
import com.dcomet.module.domain.FeatureEntityMapSearchCriteria;
import com.dcomet.module.domain.FeatureEntityMapSearchRequest;
import com.dcomet.module.domain.FeatureUnitMap;
import com.dcomet.module.domain.FeatureUnitMapSearchCriteria;
import com.dcomet.module.domain.FeatureUnitMapSearchRequest;
import com.dcomet.fw.domain.MailQueue;
import com.dcomet.fw.domain.MailQueueSearchCriteria;
import com.dcomet.fw.domain.MailQueueSearchRequest;
import com.dcomet.module.domain.SysParamEntityMap;
import com.dcomet.module.domain.SysParamEntityMapSearchCriteira;
import com.dcomet.module.domain.SysParamEntityMapSearchRequest;
import com.dcomet.module.domain.States;
import com.dcomet.module.domain.StatesSearchCriteria;
import com.dcomet.module.domain.StatesSearchRequest;
import com.dcomet.module.domain.SysParam;
import com.dcomet.module.domain.SysParamSearchCriteria;
import com.dcomet.module.domain.SysParamSearchRequest;
import com.dcomet.module.domain.TaxConfig;
import com.dcomet.module.domain.TaxConfigSearchCriteria;
import com.dcomet.module.domain.TaxConfigSearchRequest;
import com.dcomet.module.domain.ZipCode;
import com.dcomet.module.domain.ZipCodeSearchCriteria;
import com.dcomet.module.domain.ZipCodeSearchRequest;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.module.domain.CurrencyExchange;
import com.dcomet.module.domain.CurrencyExchangeSearchCriteria;
import com.dcomet.module.domain.CurrencyExchangeSearchRequest;
import com.dcomet.module.domain.CurrencyMap;
import com.dcomet.module.domain.CurrencyMapSearchCriteria;
import com.dcomet.module.domain.CurrencyMapSearchRequest;
import com.dcomet.module.domain.FeatureSearchCriteria;
import com.dcomet.module.domain.FeatureSearchRequest;
import com.dcomet.module.domain.Template;
import com.dcomet.module.domain.TemplateSearchCriteria;
import com.dcomet.module.domain.TemplateSearchRequest;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("dictionary/v1")
public class DataDictionaryResource extends BaseResource {

    @Autowired
    @Qualifier("dataDictionaryService")
    public DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("workFlowService")
    public WorkFlowService workFlowService;

    @GET
    @Path("/entity")
    @Produces("application/json")
    public List<Entity> getEntity(@Context final SecurityContext securityContext) throws Exception {
        return dataDictionaryService.getEntityByCache();
    }

    @GET
    @Path("/feature")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Feature> getFeature() throws Exception {
        return dataDictionaryService.getFeatureByCache();
    }

    @GET
    @Path("/ddict")
    @Consumes("application/json")
    @Produces("application/json")
    public List<DdictType> getDdict() throws Exception {
        return dataDictionaryService.getDdictTypeByCache();
    }

    @POST
    @Path("/ddictvalue")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Ddict> search(DdictSearchCriteria ddictSearchCriteria) {
        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
        ddictSearchRequest.addDdictCriteria(ddictSearchCriteria);
        return dataDictionaryService.getDdict(ddictSearchRequest);

    }

    @POST
    @Path("/ddictvalue/entitymap")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Ddict> getDdictByEntityMap(@Context final SecurityContext securityContext, DdictSearchCriteria ddictSearchCriteria) {
        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
        addSecurityContext(securityContext, ddictSearchRequest);
        ddictSearchRequest.addDdictCriteria(ddictSearchCriteria);
        return dataDictionaryService.getDdictByEntityMap(ddictSearchRequest);
    }

    @POST
    @Path("/ddict/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveDdict(@Context final SecurityContext securityContext, List<Ddict> dDict) {
        dataDictionaryService.saveDdictList(dDict, true);
    }

    @POST
    @Path("/ddictType/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveDdictType(@Context final SecurityContext securityContext, DdictType dDictType) {
        dataDictionaryService.saveDdictType(dDictType, true);
    }

    @POST
    @Path("/sysparam/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<SysParam> search(@Context final SecurityContext securityContext, SysParamSearchCriteria sysParamSearchCriteria) {
        SysParamSearchRequest sysParamSearchRequest = new SysParamSearchRequest();
//        addSecurityContext(securityContext, sysParamSearchRequest);
//        List<Criterion> searchCriterionList = new ArrayList<>();
//        searchCriterionList.add(Restrictions.eq("paramEntityRID", sysParamSearchRequest.getEntityRid()));
//        sysParamSearchRequest.setSearchCriterionList(searchCriterionList);
        sysParamSearchRequest.addSysParamCriteria(sysParamSearchCriteria);
//        return dataDictionaryService.getSysParam(sysParamSearchRequest, true);
        return dataDictionaryService.getSysParam(sysParamSearchRequest);
    }

    @POST
    @Path("/currencyexchange/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<CurrencyExchange> searchCurrencyExchange(@Context final SecurityContext securityContext, CurrencyExchangeSearchCriteria currencyExchangeSearchCriteria) {
        CurrencyExchangeSearchRequest currencyExchangeSearchRequest = new CurrencyExchangeSearchRequest();
        addSecurityContext(securityContext, currencyExchangeSearchRequest);
        List<Criterion> searCriterions = new ArrayList<>();
        searCriterions.add(Restrictions.eq("cerEntityRid", currencyExchangeSearchRequest.getEntityRid()));
        currencyExchangeSearchRequest.setSearchCriterionList(searCriterions);
        currencyExchangeSearchRequest.addCurrencyExchangeSearchCriteria(currencyExchangeSearchCriteria);
        return dataDictionaryService.getCurrencyExchange(currencyExchangeSearchRequest);

    }

    @POST
    @Path("/taxconfig/search")
    @Consumes("application/json")
    @Produces("application/json")

    public List<TaxConfig> search(TaxConfigSearchCriteria taxConfigSearchCriteria) {
        TaxConfigSearchRequest taxConfigSearchRequest = new TaxConfigSearchRequest();
        taxConfigSearchRequest.addTaxConfigCriteria(taxConfigSearchCriteria);
        return dataDictionaryService.getTaxConfig(taxConfigSearchRequest);

    }

    @POST
    @Path("/currencym/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<CurrencyM> search(CurrencyMSearchCriteria currencyMSearchCriteria) {
        CurrencyMSearchRequest currencyMSearchRequest = new CurrencyMSearchRequest();
        currencyMSearchRequest.addCurrencyMCriteria(currencyMSearchCriteria);
        return dataDictionaryService.getCurrencyM(currencyMSearchRequest);

    }

    @POST
    @Path("/currencymap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<CurrencyMap> searchCurrencyMap(CurrencyMapSearchCriteria currencyMapSearchCriteria) {
        CurrencyMapSearchRequest currencyMapSearchRequest = new CurrencyMapSearchRequest();
        currencyMapSearchRequest.addCurrencyMapCriteria(currencyMapSearchCriteria);
        return dataDictionaryService.getCurrencyMap(currencyMapSearchRequest, true);

    }

    @POST
    @Path("/featureentitymap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FeatureEntityMap> search(FeatureEntityMapSearchCriteria featureEntityMapSearchCriteria) {
        FeatureEntityMapSearchRequest featureEntityMapSearchRequest = new FeatureEntityMapSearchRequest();
        featureEntityMapSearchRequest.addFeatureEntityMapCriteria(featureEntityMapSearchCriteria);
        return dataDictionaryService.getFeatureEntityMap(featureEntityMapSearchRequest);

    }

    @POST
    @Path("/featureUnitMap/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FeatureUnitMap> search(FeatureUnitMapSearchCriteria featureUnitMapSearchCriteria) {
        FeatureUnitMapSearchRequest featureUnitMapSearchRequest = new FeatureUnitMapSearchRequest();
        featureUnitMapSearchRequest.addFeatureUnitMapCriteria(featureUnitMapSearchCriteria);
        return dataDictionaryService.getFeatureUnitMap(featureUnitMapSearchRequest);

    }

    @POST
    @Path("/zipcode/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<ZipCode> search(ZipCodeSearchCriteria zipCodeSearchCriteria) {
        ZipCodeSearchRequest zipCodeSearchRequest = new ZipCodeSearchRequest();
        zipCodeSearchRequest.addZipCodeSearchCriteria(zipCodeSearchCriteria);
        return dataDictionaryService.getZipCode(zipCodeSearchRequest);

    }

    @POST
    @Path("/states/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<States> search(StatesSearchCriteria statesSearchCriteria) {
        StatesSearchRequest statesSearchRequest = new StatesSearchRequest();
        statesSearchRequest.addStatesSearchCriteria(statesSearchCriteria);
        return dataDictionaryService.getStates(statesSearchRequest);

    }

    @POST
    @Path("/country/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Country> search(CountrySearchCriteria countrySearchCriteria) {
        CountrySearchRequest countrySearchRequest = new CountrySearchRequest();
        countrySearchRequest.addCountryCriteria(countrySearchCriteria);
        return dataDictionaryService.getCountry(countrySearchRequest);

    }

    @POST
    @Path("/mailqueue/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<MailQueue> search(MailQueueSearchCriteria mailQueueSearchCriteria) {
        MailQueueSearchRequest mailQueueSearchRequest = new MailQueueSearchRequest();
        mailQueueSearchRequest.addMailQueueSearchCriteria(mailQueueSearchCriteria);
        return dataDictionaryService.getMailQueue(mailQueueSearchRequest);

    }

    @POST
    @Path("/mailqueue/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, List<MailQueue> mailQueueList) {
        dataDictionaryService.saveMailQueue(mailQueueList);
    }

//    @POST
//    @Path("/mailschequeue/search")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public List<MailScheQueue> search(MailScheQueueSearchCriteria mailScheQueueSearchCriteria) {
//        MailScheQueueSearchRequest mailScheQueueSearchRequest = new MailScheQueueSearchRequest();
//        mailScheQueueSearchRequest.addMailScheQueueSearchCriteria(mailScheQueueSearchCriteria);
//        return dataDictionaryService.getMailScheQueue(mailScheQueueSearchRequest);
//
//    }
//
//    @POST
//    @Path("/mailschequeue/save")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public void save(@Context final SecurityContext securityContext, MailScheQueue parent) {
//        dataDictionaryService.saveMailScheQueue(parent);
//    }
    @POST
    @Path("/template/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Template> search(TemplateSearchCriteria templateSearchCriteria) {
        TemplateSearchRequest templateSearchRequest = new TemplateSearchRequest();
        templateSearchRequest.addTemplateCriteria(templateSearchCriteria);
        return dataDictionaryService.getTemplate(templateSearchRequest);
    }

    @POST
    @Path("/stateTranLog/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BOStateTransitionLog> search(BOStateTransitionLogSearchCriteria boStateTransitionLogSearchCriteria) {
        BOStateTransitionLogSearchRequest boStateTransitionLogSearchRequest = new BOStateTransitionLogSearchRequest();
        boStateTransitionLogSearchRequest.addBOStateTransitionLogCriteria(boStateTransitionLogSearchCriteria);
        return workFlowService.getBOStateTransitionLog(boStateTransitionLogSearchRequest);

    }

    @GET
    @Path("/bomaster/{bomName}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<BOMaster> getBOMaster(@PathParam("bomName") final String bomName) {
        BOMasterSearchRequest boMasterSearchRequest = new BOMasterSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("bomName", bomName));
        boMasterSearchRequest.setSearchCriterionList(searchCriterionList);
        return workFlowService.getBOMaster(boMasterSearchRequest, true);
    }

    @POST
    @Path("/ddict/entitymap")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Ddict> getDdictValueByEntityMap(@Context final SecurityContext securityContext, DdictSearchCriteria ddictSearchCriteria) {
        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
        addSecurityContext(securityContext, ddictSearchRequest);
        ddictSearchRequest.addDdictCriteria(ddictSearchCriteria);
        return dataDictionaryService.getDidictByEntityMap(ddictSearchRequest);
    }

    @POST
    @Path("/feature/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void save(@Context final SecurityContext securityContext, Feature feature) {
        dataDictionaryService.saveFeature(feature);
    }

    @POST
    @Path("/feature/entitymap")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Feature> getfeatureByEntityMap(@Context final SecurityContext securityContext, FeatureSearchCriteria featureSearchCriteria) {
        FeatureSearchRequest featureSearchRequest = new FeatureSearchRequest();
        addSecurityContext(securityContext, featureSearchRequest);
        featureSearchRequest.addFeatureCriteria(featureSearchCriteria);
        return dataDictionaryService.getFeatureByEntityMap(featureSearchRequest);
    }

}
