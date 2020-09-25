package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author ABDUL
 */
public class BedChargeVo extends Base implements Serializable {

    String bcServiceName;
    Float bcServicePrice;
    Integer bcServiceRid;
    Integer bcVisitRid;
    Integer bcPatientRid;
    Integer bcDaysCount;
    Integer bcBedRid;
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

    public Integer getBcServiceRid() {
        return bcServiceRid;
    }

    public void setBcServiceRid(Integer bcServiceRid) {
        this.bcServiceRid = bcServiceRid;
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

    public Integer getBcBedRid() {
        return bcBedRid;
    }

    public void setBcBedRid(Integer bcBedRid) {
        this.bcBedRid = bcBedRid;
    }

    public Float getBcGrossAmount() {
        return bcGrossAmount;
    }

    public void setBcGrossAmount(Float bcGrossAmount) {
        this.bcGrossAmount = bcGrossAmount;
    }

    public Integer getBcBedEntityRid() {
        return bcBedEntityRid;
    }

    public void setBcBedEntityRid(Integer bcBedEntityRid) {
        this.bcBedEntityRid = bcBedEntityRid;
    }
}
