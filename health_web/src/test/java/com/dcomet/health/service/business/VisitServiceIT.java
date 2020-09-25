package com.dcomet.health.service.business;

import com.dcomet.health.domain.Visit;
import com.dcomet.health.service.JUnitConfigLocator;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dev4
 */
public class VisitServiceIT extends TestCase {

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
    public void testVisit() throws Throwable {

//        VisitSearchRequest parentSearchRequest = new VisitSearchRequest();
//        List<Criterion> searchCriterionList = new ArrayList<Criterion>();
//        searchCriterionList.add(Restrictions.like("visPatType", "Anand"));
//        parentSearchRequest.setSearchCriterionList(searchCriterionList);
//        List<CriteriaOrder> orderList = new ArrayList<CriteriaOrder>();
//        orderList.add(new CriteriaOrder("id", false));
//        parentSearchRequest.setSortOrder(orderList);
//
//        List<Visit> parentList = visitService.getVisit(parentSearchRequest, true);
//        System.out.println("parentList.getListList()>>" + parentList.getListList().size());
//        for (Visit visit : parentList.getListList()) {
//            System.out.println(visit.getVisPatType() + " : " + visit.getVisTime());
//        }
        Visit visit = new Visit();
//        visit.setVisApptRid("01");
        visit.setVisTypeIndex(3);
//        visit.setVisSubTypeIndex("3");
        visit.setEntityRid(2);
        visit.setVisPatRid(5);
        visit.setVisPatType("Abdulah");
//        visit.setVisApptRid("32");
//        visit.setVisEpisodeRid("12");
//        visit.setVisReasonIndex("345");
        visit.setVisRemarks("None");
//        visit.setVisConsDocRid("4");
//        visit.setVisAttnDocRid("2341");
        visit.setVisRefTypeIndex("user");
//        visit.setVisRefRid("33");
        visit.setVisDocRemarks("Anand");
        visit.setVisState(6);
        visit.setVisStatus(56);
        visit.setCreatedUserRid(78);
        visit.setModifiedUserRid(67);
//        
        Gson gson = new Gson();
        System.out.println(gson.toJson(visit));
//        visitService.saveVisit(visit);

    }
}
