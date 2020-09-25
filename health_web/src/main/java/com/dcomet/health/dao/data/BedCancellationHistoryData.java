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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_bed_cancellation_history")

public class BedCancellationHistoryData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BCH_RID", updatable = false)
    private Integer bchRid;

    @Column(name = "BCH_PATIENT_RID")
    private Integer bchPatientRid;

    @Column(name = "BCH_BED_RID")
    private Integer bchBedRid;

    @Column(name = "BCH_BED_NO")
    private String bchBedNo;

    @Column(name = "BCH_REASON")
    private String bchReason;

    @Column(name = "BCH_ENTITY_RID")
    private Integer bchEntityRid;

    @Column(name = "BCH_CANCEL_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar bchCancelDatetime;

    @Column(name = "BCH_CREATED_USER_RID")
    private Integer bchCreatedUserRid;

    @Column(name = "BCH_CREATED_DATETIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar bchCreatedDatetime;

    public BedCancellationHistoryData() {
    }

    public BedCancellationHistoryData(Integer bchRid) {
        this.bchRid = bchRid;
    }

    public Integer getBchRid() {
        return bchRid;
    }

    public void setBchRid(Integer bchRid) {
        this.bchRid = bchRid;
    }

    public Integer getBchPatientRid() {
        return bchPatientRid;
    }

    public void setBchPatientRid(Integer bchPatientRid) {
        this.bchPatientRid = bchPatientRid;
    }

    public Integer getBchBedRid() {
        return bchBedRid;
    }

    public void setBchBedRid(Integer bchBedRid) {
        this.bchBedRid = bchBedRid;
    }

    public String getBchBedNo() {
        return bchBedNo;
    }

    public void setBchBedNo(String bchBedNo) {
        this.bchBedNo = bchBedNo;
    }

    public String getBchReason() {
        return bchReason;
    }

    public void setBchReason(String bchReason) {
        this.bchReason = bchReason;
    }

    public Integer getBchEntityRid() {
        return bchEntityRid;
    }

    public void setBchEntityRid(Integer bchEntityRid) {
        this.bchEntityRid = bchEntityRid;
    }

    public Calendar getBchCancelDatetime() {
        return bchCancelDatetime;
    }

    public void setBchCancelDatetime(Calendar bchCancelDatetime) {
        this.bchCancelDatetime = bchCancelDatetime;
    }

    public Integer getBchCreatedUserRid() {
        return bchCreatedUserRid;
    }

    public void setBchCreatedUserRid(Integer bchCreatedUserRid) {
        this.bchCreatedUserRid = bchCreatedUserRid;
    }

    public Calendar getBchCreatedDatetime() {
        return bchCreatedDatetime;
    }

    public void setBchCreatedDatetime(Calendar bchCreatedDatetime) {
        this.bchCreatedDatetime = bchCreatedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbchRid: Integer=");
        sb.append(bchRid);
        sb.append(";");

        sb.append("\n\tbchPatientRid: Integer=");
        sb.append(bchPatientRid);
        sb.append(";");

        sb.append("\n\tbchBedRid: Integer=");
        sb.append(bchBedRid);
        sb.append(";");

        sb.append("\n\tbchBedNo: String=");
        sb.append(bchBedNo);
        sb.append(";");

        sb.append("\n\tbchReason: String=");
        sb.append(bchReason);
        sb.append(";");

        sb.append("\n\tbchEntityRid: Integer=");
        sb.append(bchEntityRid);
        sb.append(";");

        sb.append("\n\tbchCancelDatetime: Calendar=");
        sb.append(bchCancelDatetime);
        sb.append(";");

        sb.append("\n\tbchCreatedUserRid: Integer=");
        sb.append(bchCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbchCreatedDatetime: Calendar=");
        sb.append(bchCreatedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
