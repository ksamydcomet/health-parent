package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "t_bed_occupancy_details")
public class BedOccupancyDetailsData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOD_RID", updatable = false)
    private Integer bodRid;

    @Column(name = "BOD_BED_TYPE_RID")
    private Integer bodBedTypeRid;

    @Column(name = "BOD_BED_RID")
    private Integer bodBedRid;

    @Column(name = "BOD_WARD_RID")
    private Integer bodWardRid;

    @Column(name = "BOD_GROUP_RID")
    private Integer bodGroupRid;

    @Column(name = "BOD_WARD_UNIT_RID")
    private Integer bodWardUnitRid;

    @Column(name = "BOD_FROM_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar bodFromDatetime;

    @Column(name = "BOD_TO_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar bodToDatetime;

    @Column(name = "BOD_ENT_RID")
    private Integer bodEntRid;

    @Column(name = "BOD_USER_RID")
    private Integer bodUserRid;

    @Column(name = "BOD_SESSION_ID")
    private String bodSessionId;

    public BedOccupancyDetailsData() {
    }

    public Integer getBodRid() {
        return bodRid;
    }

    public void setBodRid(Integer bodRid) {
        this.bodRid = bodRid;
    }

    public Integer getBodBedTypeRid() {
        return bodBedTypeRid;
    }

    public void setBodBedTypeRid(Integer bodBedTypeRid) {
        this.bodBedTypeRid = bodBedTypeRid;
    }

    public Integer getBodBedRid() {
        return bodBedRid;
    }

    public void setBodBedRid(Integer bodBedRid) {
        this.bodBedRid = bodBedRid;
    }

    public Integer getBodWardRid() {
        return bodWardRid;
    }

    public void setBodWardRid(Integer bodWardRid) {
        this.bodWardRid = bodWardRid;
    }

    public Integer getBodGroupRid() {
        return bodGroupRid;
    }

    public void setBodGroupRid(Integer bodGroupRid) {
        this.bodGroupRid = bodGroupRid;
    }

    public Integer getBodWardUnitRid() {
        return bodWardUnitRid;
    }

    public void setBodWardUnitRid(Integer bodWardUnitRid) {
        this.bodWardUnitRid = bodWardUnitRid;
    }

    public Calendar getBodFromDatetime() {
        return bodFromDatetime;
    }

    public void setBodFromDatetime(Calendar bodFromDatetime) {
        this.bodFromDatetime = bodFromDatetime;
    }

    public Calendar getBodToDatetime() {
        return bodToDatetime;
    }

    public void setBodToDatetime(Calendar bodToDatetime) {
        this.bodToDatetime = bodToDatetime;
    }

    public Integer getBodEntRid() {
        return bodEntRid;
    }

    public void setBodEntRid(Integer bodEntRid) {
        this.bodEntRid = bodEntRid;
    }

    public Integer getBodUserRid() {
        return bodUserRid;
    }

    public void setBodUserRid(Integer bodUserRid) {
        this.bodUserRid = bodUserRid;
    }

    public String getBodSessionId() {
        return bodSessionId;
    }

    public void setBodSessionId(String bodSessionId) {
        this.bodSessionId = bodSessionId;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbodRid: Integer=");
        sb.append(bodRid);
        sb.append(";");

        sb.append("\n\tbodBedTypeRid: Integer=");
        sb.append(bodBedTypeRid);
        sb.append(";");

        sb.append("\n\tbodBedRid: Integer=");
        sb.append(bodBedRid);
        sb.append(";");

        sb.append("\n\tbodWardRid: Integer=");
        sb.append(bodWardRid);
        sb.append(";");

        sb.append("\n\tbodGroupRid: Integer=");
        sb.append(bodGroupRid);
        sb.append(";");

        sb.append("\n\tbodWardUnitRid: Integer=");
        sb.append(bodWardUnitRid);
        sb.append(";");

        sb.append("\n\tbodFromDatetime: Calendar=");
        sb.append(bodFromDatetime);
        sb.append(";");

        sb.append("\n\tbodToDatetime: Calendar=");
        sb.append(bodToDatetime);
        sb.append(";");

        sb.append("\n\tbodEntRid: Integer=");
        sb.append(bodEntRid);
        sb.append(";");

        sb.append("\n\tbodUserRid: Integer=");
        sb.append(bodUserRid);
        sb.append(";");

        sb.append("\n\tbodSessionId: String=");
        sb.append(bodSessionId);
        sb.append(";");

        return sb.toString();
    }

}
