package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ProcedureNurse extends Base implements Serializable {

    private Integer id;
    private Integer procNurseRid;
    private Integer procRid;
    private Integer procEntityRid;
    private Integer procNurseActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcNurseRid() {
        return procNurseRid;
    }

    public void setProcNurseRid(Integer procNurseRid) {
        this.procNurseRid = procNurseRid;
    }

    public Integer getProcRid() {
        return procRid;
    }

    public void setProcRid(Integer procRid) {
        this.procRid = procRid;
    }

    public Integer getProcEntityRid() {
        return procEntityRid;
    }

    public void setProcEntityRid(Integer procEntityRid) {
        this.procEntityRid = procEntityRid;
    }

    public Integer getProcNurseActive() {
        return procNurseActive;
    }

    public void setProcNurseActive(Integer procNurseActive) {
        this.procNurseActive = procNurseActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprocNurseRid: Integer=");
        sb.append(procNurseRid);
        sb.append(";");

        sb.append("\n\tprocRid: Integer=");
        sb.append(procRid);
        sb.append(";");

        sb.append("\n\tprocEntityRid: Integer=");
        sb.append(procEntityRid);
        sb.append(";");

        sb.append("\n\tprocNurseActive: Integer=");
        sb.append(procNurseActive);
        sb.append(";");

        return sb.toString();

    }

}
