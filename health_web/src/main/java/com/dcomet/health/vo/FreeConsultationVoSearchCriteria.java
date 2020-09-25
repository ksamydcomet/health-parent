package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author CVS
 */
public class FreeConsultationVoSearchCriteria extends SearchCriteria {

    private Integer fcPatientRid;
    private Integer fcDoctorRid;
    private Integer fcPatDocMapRid;
    private Integer fcCurrentState;
    private Integer fcServiceRid;
    private String fcServiceName;
    private Float fcServicePrice;
    private Float fcServiceEPrice;

    public Integer getFcPatientRid() {
        return fcPatientRid;
    }

    public void setFcPatientRid(Integer fcPatientRid) {
        this.fcPatientRid = fcPatientRid;
    }

    public Integer getFcDoctorRid() {
        return fcDoctorRid;
    }

    public void setFcDoctorRid(Integer fcDoctorRid) {
        this.fcDoctorRid = fcDoctorRid;
    }

    public Integer getFcPatDocMapRid() {
        return fcPatDocMapRid;
    }

    public void setFcPatDocMapRid(Integer fcPatDocMapRid) {
        this.fcPatDocMapRid = fcPatDocMapRid;
    }

    public Integer getFcCurrentState() {
        return fcCurrentState;
    }

    public void setFcCurrentState(Integer fcCurrentState) {
        this.fcCurrentState = fcCurrentState;
    }

    public Integer getFcServiceRid() {
        return fcServiceRid;
    }

    public void setFcServiceRid(Integer fcServiceRid) {
        this.fcServiceRid = fcServiceRid;
    }

    public String getFcServiceName() {
        return fcServiceName;
    }

    public void setFcServiceName(String fcServiceName) {
        this.fcServiceName = fcServiceName;
    }

    public Float getFcServicePrice() {
        return fcServicePrice;
    }

    public void setFcServicePrice(Float fcServicePrice) {
        this.fcServicePrice = fcServicePrice;
    }

    public Float getFcServiceEPrice() {
        return fcServiceEPrice;
    }

    public void setFcServiceEPrice(Float fcServiceEPrice) {
        this.fcServiceEPrice = fcServiceEPrice;
    }
}
