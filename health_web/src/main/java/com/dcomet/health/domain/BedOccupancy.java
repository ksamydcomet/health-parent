package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BedOccupancy extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer bocRid;
    private Integer bocPatRid;
    private Integer bocVisitRid;
    private Integer bocBedRid;
    private Integer bocPrimaryDoctor;
    private String bocSecondaryDoctor;    
    private String bocFromDatetime;
    private String bocToDatetime;
    private String bocReserveStatus;
    private Integer bocBillingBedtypeRid;

    private Integer visitPatCount;

    public BedOccupancy() {
    }

    public Integer getBocRid() {
        return bocRid;
    }

    public void setBocRid(Integer bocRid) {
        this.bocRid = bocRid;
    }

    public Integer getBocPatRid() {
        return bocPatRid;
    }

    public void setBocPatRid(Integer bocPatRid) {
        this.bocPatRid = bocPatRid;
    }

    public Integer getBocVisitRid() {
        return bocVisitRid;
    }

    public void setBocVisitRid(Integer bocVisitRid) {
        this.bocVisitRid = bocVisitRid;
    }

    public Integer getBocBedRid() {
        return bocBedRid;
    }

    public void setBocBedRid(Integer bocBedRid) {
        this.bocBedRid = bocBedRid;
    }

    public Integer getBocPrimaryDoctor() {
        return bocPrimaryDoctor;
    }

    public void setBocPrimaryDoctor(Integer bocPrimaryDoctor) {
        this.bocPrimaryDoctor = bocPrimaryDoctor;
    }

    public String getBocSecondaryDoctor() {
        return bocSecondaryDoctor;
    }

    public void setBocSecondaryDoctor(String bocSecondaryDoctor) {
        this.bocSecondaryDoctor = bocSecondaryDoctor;
    }
    
    public String getBocFromDatetime() {
        return bocFromDatetime;
    }

    public void setBocFromDatetime(String bocFromDatetime) {
        this.bocFromDatetime = bocFromDatetime;
    }

    public String getBocToDatetime() {
        return bocToDatetime;
    }

    public void setBocToDatetime(String bocToDatetime) {
        this.bocToDatetime = bocToDatetime;
    }

    public String getBocReserveStatus() {
        return bocReserveStatus;
    }

    public void setBocReserveStatus(String bocReserveStatus) {
        this.bocReserveStatus = bocReserveStatus;
    }

    public Integer getBocBillingBedtypeRid() {
        return bocBillingBedtypeRid;
    }

    public void setBocBillingBedtypeRid(Integer bocBillingBedtypeRid) {
        this.bocBillingBedtypeRid = bocBillingBedtypeRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbocRid: Integer=");
        sb.append(bocRid);
        sb.append(";");

        sb.append("\n\tbocPatRid: Integer=");
        sb.append(bocPatRid);
        sb.append(";");

        sb.append("\n\tbocVisitRid: Integer=");
        sb.append(bocVisitRid);
        sb.append(";");

        sb.append("\n\tbocBedRid: Integer=");
        sb.append(bocBedRid);
        sb.append(";");

        sb.append("\n\tbocFromDatetime: String=");
        sb.append(bocFromDatetime);
        sb.append(";");

        sb.append("\n\tbocToDatetime: String=");
        sb.append(bocToDatetime);
        sb.append(";");

        sb.append("\n\tbocReserveStatus: String=");
        sb.append(bocReserveStatus);
        sb.append(";");

        sb.append("\n\tbocBillingBedtypeRid: Integer=");
        sb.append(bocBillingBedtypeRid);
        sb.append(";");
        
        sb.append("\n\tbocPrimaryDoctor: Integer=");
        sb.append(bocPrimaryDoctor);
        sb.append(";");
        
        sb.append("\n\tbocSecondaryDoctor: String=");
        sb.append(bocSecondaryDoctor);
        sb.append(";");

        sb.append("\n\tvisitPatCount: Integer=");
        sb.append(visitPatCount);
        sb.append(";");

        return sb.toString();
    }
}
