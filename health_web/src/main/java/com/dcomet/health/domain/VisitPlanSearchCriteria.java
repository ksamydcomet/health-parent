package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Calendar;

/**
 *
 * @author Dev4
 */
public class VisitPlanSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer vispPatRID;
    private Integer vispEntityRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVispPatRID() {
        return vispPatRID;
    }

    public void setVispPatRID(Integer vispPatRID) {
        this.vispPatRID = vispPatRID;
    }

    public Integer getVispEntityRid() {
        return vispEntityRid;
    }

    public void setVispEntityRid(Integer vispEntityRid) {
        this.vispEntityRid = vispEntityRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tcplRID: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tvispPatRID: Integer=");
        sb.append(vispPatRID);
        sb.append(";");

        sb.append("\n\tvispEntityRid: Integer=");
        sb.append(vispEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
