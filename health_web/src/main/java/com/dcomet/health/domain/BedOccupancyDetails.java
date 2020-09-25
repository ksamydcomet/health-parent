package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class BedOccupancyDetails extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer bodRid;
    private Integer bodBedTypeRid;
    private Integer bodBedRid;
    private Integer bodWardRid;
    private Integer bodGroupRid;
    private Integer bodWardUnitRid;
    private String bodFromDatetime;
    private String bodToDatetime;
    private Integer bodEntRid;
    private Integer bodUserRid;
    private String bodSessionId;

    public BedOccupancyDetails() {
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

    public String getBodFromDatetime() {
        return bodFromDatetime;
    }

    public void setBodFromDatetime(String bodFromDatetime) {
        this.bodFromDatetime = bodFromDatetime;
    }

    public String getBodToDatetime() {
        return bodToDatetime;
    }

    public void setBodToDatetime(String bodToDatetime) {
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

        sb.append("\n\tbodFromDatetime: String=");
        sb.append(bodFromDatetime);
        sb.append(";");

        sb.append("\n\tbodToDatetime: String=");
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
