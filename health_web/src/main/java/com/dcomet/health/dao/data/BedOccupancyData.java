package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_bed_occupancy")
public class BedOccupancyData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOC_RID", updatable = false)
    private Integer bocRid;

    @Column(name = "BOC_PAT_RID")
    private Integer bocPatRid;

    @Column(name = "BOC_VISIT_RID")
    private Integer bocVisitRid;

    @Column(name = "BOC_BED_RID")
    private Integer bocBedRid;

    @Column(name = "BOC_FROM_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar bocFromDatetime;

    @Column(name = "BOC_TO_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar bocToDatetime;

    @Column(name = "BOC_RESERVE_STATUS")
    private String bocReserveStatus;

    @Column(name = "BOC_BILLING_BEDTYPE_RID")
    private Integer bocBillingBedtypeRid;
    
    @Column(name = "BOC_PRIMARY_DOCTOR")
    private Integer bocPrimaryDoctor;
    
    @Column(name = "BOC_SECONDARY_DOCTOR")
    private String bocSecondaryDoctor;    

    @Column(name = "BOC_MODIFIED_USER_RID")
    private Integer bocModifiedUserRid;

    @Column(name = "BOC_MODIFIED_DATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public BedOccupancyData() {
    }

    public BedOccupancyData(Integer bocRid) {
        this.bocRid = bocRid;
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

    public Calendar getBocFromDatetime() {
        return bocFromDatetime;
    }

    public void setBocFromDatetime(Calendar bocFromDatetime) {
        this.bocFromDatetime = bocFromDatetime;
    }

    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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
    
    public Integer getBocModifiedUserRid() {
        return bocModifiedUserRid;
    }

    public void setBocModifiedUserRid(Integer bocModifiedUserRid) {
        this.bocModifiedUserRid = bocModifiedUserRid;
    }

    public Calendar getBocToDatetime() {
        return bocToDatetime;
    }

    public void setBocToDatetime(Calendar bocToDatetime) {
        this.bocToDatetime = bocToDatetime;
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

        sb.append("\n\tbocFromDatetime: Calendar=");
        sb.append(bocFromDatetime);
        sb.append(";");

        sb.append("\n\tbocToDatetime: Calendar=");
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

        sb.append("\n\tbocModifiedUserRid: Integer=");
        sb.append(bocModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbocModifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
