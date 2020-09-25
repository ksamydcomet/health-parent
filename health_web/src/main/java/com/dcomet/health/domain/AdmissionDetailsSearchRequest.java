/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dev1
 */
public class AdmissionDetailsSearchRequest extends SearchRequest{
    
    @Override
    public String toString() {
        return super.toString();
    }
    public AdmissionDetailsSearchRequest(){
        
}
     public List<Criterion> addAdmissionDetailsSearchCriteria(AdmissionDetailsSearchCriteria admissionDetailsSearchCriteria) {
        if (admissionDetailsSearchCriteria.getAdId() != null) {
            getSearchCriterionList().add(Restrictions.eq("adId", admissionDetailsSearchCriteria.getAdId()));
        }
       
        return getSearchCriterionList();
    }
}
