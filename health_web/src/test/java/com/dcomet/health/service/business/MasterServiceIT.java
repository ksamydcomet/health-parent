/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.service.business;

import com.dcomet.health.service.JUnitConfigLocator;
import com.dcomet.module.domain.Page;
import com.dcomet.module.domain.UserFeature;
import com.dcomet.module.domain.UserFeatureSearchRequest;
import com.dcomet.module.master.service.DCometMasterService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dev1
 */
public class MasterServiceIT {

    private JUnitConfigLocator configLocator;
    DCometMasterService masterService;

    public MasterServiceIT() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        configLocator = JUnitConfigLocator.getInstance();
        masterService = (DCometMasterService) configLocator
                .getBean("masterService");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        UserFeatureSearchRequest userFeatureSearchRequest = new UserFeatureSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("ufUserRID", 1));
        userFeatureSearchRequest.setSearchCriterionList(searchCriterionList);
        List<UserFeature> userFeatureList = masterService.getUserFeature(userFeatureSearchRequest);
        List<Page> pages = convertUserFeatureToPage(userFeatureList);
        Collections.sort(pages);
        System.out.println(pages);
    }

    private List<Page> convertUserFeatureToPage(List<UserFeature> userFeatureList) {

        HashMap<Page, List<Page>> parentMap = new HashMap<>();

        // Add parent
        for (UserFeature userFeature : userFeatureList) {
            if (StringUtils.isEmpty(userFeature.getUfCommand())) {
                parentMap.put(convertUserFeatureToPage(userFeature), new ArrayList<Page>());
            }
        }
        // Add Child
        for (UserFeature userFeature : userFeatureList) {
            if (StringUtils.isNotEmpty(userFeature.getUfCommand())) {
                addToParent(userFeature, parentMap);
            }
        }
        // Add childs To Parent
        for (Map.Entry<Page, List<Page>> entry : parentMap.entrySet()) {
            entry.getKey().setSub(CollectionUtils.isNotEmpty(entry.getValue()) ? entry.getValue() : null);
        }
        return new ArrayList<>(parentMap.keySet());
    }

    private void addToParent(UserFeature userFeature, HashMap<Page, List<Page>> parentMap) {
        for (Map.Entry<Page, List<Page>> entry : parentMap.entrySet()) {
            if (Objects.equals(userFeature.getUfParentGroup(), entry.getKey().getUfRid())) {
                entry.getValue().add(convertUserFeatureToPage(userFeature));
            }
        }
    }

    private Page convertUserFeatureToPage(UserFeature userFeature) {
        Page page = new Page();
        page.setId(userFeature.getUfName());
        page.setUfRid(userFeature.getId());
        if (StringUtils.isNotEmpty(userFeature.getUfCommand())) {
            page.setLink(userFeature.getUfCommand());
        }
        return page;
    }
}
