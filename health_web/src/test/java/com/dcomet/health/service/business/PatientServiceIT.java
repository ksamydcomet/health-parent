package com.dcomet.health.service.business;

import com.dcomet.fw.domain.CriteriaOrder;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev4
 */
public class PatientServiceIT extends TestCase {

    private JUnitConfigLocator configLocator;
    ClinicalService clinicalService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        configLocator = JUnitConfigLocator.getInstance();
        clinicalService = (ClinicalService) configLocator
                .getBean("clinicalService");
    }

    @Test
    public void testPatient() throws Throwable {

        PatientSearchRequest parentSearchRequest = new PatientSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.like("patSeqNo", "name"));
        parentSearchRequest.setSearchCriterionList(searchCriterionList);
        List<CriteriaOrder> orderList = new ArrayList<>();
        orderList.add(new CriteriaOrder("patMrnNo", false));
        parentSearchRequest.setSortOrder(orderList);

        List<Patient> parentList = clinicalService.getPatient(parentSearchRequest, false);
        System.out.println("parentList.getListList()>>" + parentList.size());
        for (Patient patient : parentList) {
            System.out.println(patient.getPatFamilyName() + " : " + patient.getPatCountryIndex());
        }
        Patient patient = new Patient();
//        patient.setId(1);
        patient.setEntityRid(5);
        patient.setPatMrnNo("5");
        patient.setPatSeqNo(5);
        patient.setPatTitle("2015-12-12");
        patient.setPatName("raju");
        patient.setPatFullName("male");
        patient.setPatFamilyName("asdf");
        patient.setPatGenderIndex("asd");
        patient.setPatBloodGroupIndex("asd");
        patient.setPatDob(DateUtil.getCurrentDate());
        patient.setPatMaritalStatus("asfd");
        patient.setPatAddress("wer");
        patient.setPatCityIndex("23145145");
        patient.setPatStateIndex("2131351");
        patient.setPatCountryIndex("@gmail.com");
        patient.setPatPincode(325322);
        patient.setPatPhoneNo("1314143");
        patient.setPatEmailId("4235");
        patient.setPatPhotoFilePath("1993-04-05");
        patient.setPatPhotoFileType("sf");

        patient.setPatVipStatus("name2");
        patient.setPatParentRid(4534);
        patient.setPatGeneratedDob(DateUtil.getCurrentDate());
        patient.setCreatedUserRid(45);
        patient.setModifiedUserRid(4);
        patient.setPatRowInvalidated(1);
        patient.setPatRowInvalidatedDateTime(DateUtil.getCurrentDateTime());
        patient.setPatState(12);
        patient.setPatStatus(3);

        Gson gson = new Gson();
        System.out.println(gson.toJson(patient));
//        patientService.savePatient(patient);

    }

}
