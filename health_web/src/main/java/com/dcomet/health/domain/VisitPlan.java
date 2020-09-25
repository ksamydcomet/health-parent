package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class VisitPlan extends Base implements Serializable {

    private Integer id;
    private Integer vispPatRID;
   

    public VisitPlan() {

    }

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
       
        return sb.toString();
    }
}


