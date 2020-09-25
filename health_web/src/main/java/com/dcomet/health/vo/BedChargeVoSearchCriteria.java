package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author ABDUL
 */
public class BedChargeVoSearchCriteria extends SearchCriteria {

    String bcServiceName;
    Float bcServicePrice;
    Integer bcVisitRid;
    Integer bcPatientRid;
    Integer bcDaysCount;
    Float bcGrossAmount;
    Integer bcBedEntityRid;

    public String getBcServiceName() {
        return bcServiceName;
    }

    public void setBcServiceName(String bcServiceName) {
        this.bcServiceName = bcServiceName;
    }

    public Float getBcServicePrice() {
        return bcServicePrice;
    }

    public void setBcServicePrice(Float bcServicePrice) {
        this.bcServicePrice = bcServicePrice;
    }

    public Integer getBcVisitRid() {
        return bcVisitRid;
    }

    public void setBcVisitRid(Integer bcVisitRid) {
        this.bcVisitRid = bcVisitRid;
    }

    public Integer getBcPatientRid() {
        return bcPatientRid;
    }

    public void setBcPatientRid(Integer bcPatientRid) {
        this.bcPatientRid = bcPatientRid;
    }

    public Integer getBcDaysCount() {
        return bcDaysCount;
    }

    public void setBcDaysCount(Integer bcDaysCount) {
        this.bcDaysCount = bcDaysCount;
    }

    public Integer getBcBedEntityRid() {
        return bcBedEntityRid;
    }

    public void setBcBedEntityRid(Integer bcBedEntityRid) {
        this.bcBedEntityRid = bcBedEntityRid;
    }
}
