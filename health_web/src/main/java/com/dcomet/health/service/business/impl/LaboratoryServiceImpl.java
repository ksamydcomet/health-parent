package com.dcomet.health.service.business.impl;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.Visit;
import com.dcomet.health.domain.VisitSearchRequest;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.LaboratoryService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchRequest;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.domain.Staff;
import com.dcomet.module.domain.User;
import com.dcomet.module.laboratory.adapter.DCometLaboratoryAdapter;
import com.dcomet.module.laboratory.dao.DCometLaboratoryDAO;
import com.dcomet.module.laboratory.domain.LabResultD;
import com.dcomet.module.laboratory.domain.LabResultDSearchRequest;
import com.dcomet.module.laboratory.domain.LabResultH;
import com.dcomet.module.laboratory.domain.LabResultHSearchRequest;
import com.dcomet.module.laboratory.service.impl.DCometLaboratoryServiceImpl;
import com.dcomet.module.master.domain.LabServiceExtn;
import com.dcomet.module.master.domain.StaffSearchRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adhithya
 */
@Service("laboratoryService")
@Transactional(propagation = Propagation.SUPPORTS)
public class LaboratoryServiceImpl extends DCometLaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    @Qualifier("laboratoryAdapter")
    DCometLaboratoryAdapter laboratoryAdapter;

    @Autowired
    @Qualifier("laboratoryDAO")
    DCometLaboratoryDAO laboratoryDAO;

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    @Override
    public List<LabResultH> getLabResultH(LabResultHSearchRequest labResultHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<LabResultH> labResultHs = new ArrayList<>();
        try {
            labResultHs = laboratoryAdapter.convertLabResultHDataToLabResultH(laboratoryDAO.getLabResultH(labResultHSearchRequest));
            if (CollectionUtils.isNotEmpty(labResultHs)) {
                if (includeChilds) {
                    for (LabResultH labResultH : labResultHs) {
                        Patient patient = clinicalService.getPatient(labResultH.getLrhPatientID());
                        labResultH.setLrhPatientName(patient.getPatFullName());
                        labResultH.setLrhPatientMrn(patient.getPatMrnNo());

                        LabResultDSearchRequest labResultDSearchRequest = new LabResultDSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("lrdHRID", labResultH.getId()));
                        labResultDSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<LabResultD> labResultDs = getLabResultD(labResultDSearchRequest);
                        if (CollectionUtils.isNotEmpty(labResultDs)) {
                            labResultH.setLabResultDs(labResultDs);
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
        return labResultHs;
    }

    @Override
    public String getLabServicePrint(LabResultHSearchRequest labResultHSearchRequest, Integer groupId) throws DcometServiceException {
        String string = new String();
        Ddict dDictObj = new Ddict();
        boolean check = false;
        try {
            LabResultH labResultH = getLabResultHForPrint(labResultHSearchRequest, true, groupId).get(0);
            labResultH.setLrhCurrentDateTime(labResultHSearchRequest.getCurrentDateTimeByUTZ());
            List<Criterion> searchCriterionList = new ArrayList<>();
            VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
            searchCriterionList.add(Restrictions.eq("id", labResultH.getLrhPatientVisitID()));
            visitSearchRequest.setSearchCriterionList(searchCriterionList);
            Visit visit = clinicalService.getVisit(visitSearchRequest, true).get(0);
            Patient patient = visit.getPatient().get(0);
            if (null != labResultH) {
                for (LabResultD labResultD : labResultH.getLabResultDs()) {
                    if (!"0".equals(labResultD.getLrdNodes())) {
                        List<LabServiceExtn> labServiceExtns = labResultD.getServiceMaster().getLabServiceExtnList();
                        for (LabServiceExtn labServiceExtn : labServiceExtns) {
                            if (Objects.equals(labServiceExtn.getLsGenderType(), patient.getPatGenderIndex())) {
                                labServiceExtn.setLsTemplateNode(setObjValInTemplate(labServiceExtn.getLsTemplateNode(), labResultD.getLrdNodes()));
                                labResultD.getServiceMaster().setLabServiceExtnList(Arrays.asList(labServiceExtn));
                                break;
                            }
                        }
                    }
                    searchCriterionList.clear();
                    DdictSearchRequest dDictSearchRequest = new DdictSearchRequest();
                    searchCriterionList.add(Restrictions.eq("id", Integer.parseInt(labResultD.getServiceMaster().getBsCategory())));
                    dDictSearchRequest.setSearchCriterionList(searchCriterionList);
                    Ddict dDictResult = dataDictionaryService.getDdict(dDictSearchRequest).get(0);
                    if ("Profile".equals(dDictResult.getDdictValue())) {
                        dDictObj = dDictResult;
                        labResultD.setLrdNServRangeState("Profile");
                        check = true;
                    }
                }
                string = dataDictionaryService.getReportFromTemplate("LAB_REPORT", labResultH, "lrh", labResultHSearchRequest.getEntityRid());
                if (check) {
                    string = dataDictionaryService.getReportFromHTML(string, dDictObj, "dDictValue");
                }
                Entity entity = CacheUtil.getEntity(labResultHSearchRequest.getEntityRid());
                string = dataDictionaryService.getReportFromHTML(string, entity, "ent");
                string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(labResultHSearchRequest.getEntityRid()), "pe");
                string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
                searchCriterionList.clear();
                DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
                searchCriterionList.add(Restrictions.eq("id", Integer.parseInt(patient.getPatTitle())));
                ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
                if (ddictResult != null) {
                    string = dataDictionaryService.getReportFromHTML(string, ddictResult, "dDict");
                }
                searchCriterionList.clear();
                StaffSearchRequest staffSearchRequest = new StaffSearchRequest();
                searchCriterionList.add(Restrictions.eq("id", visit.getVisConsDocRid()));
                staffSearchRequest.setSearchCriterionList(searchCriterionList);
                Staff staff = masterService.getStaff(staffSearchRequest, false).get(0);
                if (staff != null) {
                    string = dataDictionaryService.getReportFromHTML(string, staff, "staff");
                }
                User user = CacheUtil.getUser(labResultHSearchRequest.getUserId());
                string = dataDictionaryService.getReportFromHTML(string, user, "user");

            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    @Override
    public List<LabResultH> getLabResultHForPrint(LabResultHSearchRequest labResultHSearchRequest, boolean includeChilds, Integer groupId) throws DcometServiceException {
        List<LabResultH> labResultHs = new ArrayList<>();
        try {
            labResultHs = laboratoryAdapter.convertLabResultHDataToLabResultH(laboratoryDAO.getLabResultH(labResultHSearchRequest));
            if (CollectionUtils.isNotEmpty(labResultHs)) {
                if (includeChilds) {
                    for (LabResultH labResultH : labResultHs) {
                        Patient patient = clinicalService.getPatient(labResultH.getLrhPatientID());
                        labResultH.setLrhPatientName(patient.getPatFullName());
                        labResultH.setLrhPatientMrn(patient.getPatMrnNo());

                        LabResultDSearchRequest labResultDSearchRequest = new LabResultDSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("lrdHRID", labResultH.getId()));
                        if (groupId != 0) {
                            searchCriterionList.add(Restrictions.eq("lrdServiceGroupRid", groupId));
                        }
                        labResultDSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<LabResultD> labResultDs = getLabResultD(labResultDSearchRequest);
                        if (CollectionUtils.isNotEmpty(labResultDs)) {
                            labResultH.setLabResultDs(labResultDs);
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
        return labResultHs;
    }

    private static String setObjValInTemplate(String template, String result) throws Exception {
        JSONObject obj = new JSONObject(result);
        Elements elements = Jsoup.parse(template).select("table#printTable");
        for (Element e : elements) {
            for (Element t : e.select("td")) {
                for (Element sp : t.select("span")) {
                    String spanId = sp.attr("id");
                    Iterator<String> keys = obj.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        String value = (String) obj.get(key);
                        if ((spanId).equals(key)) {
                            t.empty();
                            sp.attr("style", "border:none;font-size: smaller;");
                            t.append(sp.text(value).toString());
                        }
                    }
                }
            }
        }
        return elements.html();
    }
}
