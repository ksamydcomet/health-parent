package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ProcedureTechnician extends Base implements Serializable {

    private Integer id;
    private Integer procedureTechRid;
    private Integer procedureRid;
    private Integer procedureEntityRid;
    private Integer procedureTechActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcedureTechRid() {
        return procedureTechRid;
    }

    public void setProcedureTechRid(Integer procedureTechRid) {
        this.procedureTechRid = procedureTechRid;
    }

    public Integer getProcedureRid() {
        return procedureRid;
    }

    public void setProcedureRid(Integer procedureRid) {
        this.procedureRid = procedureRid;
    }

    public Integer getProcedureEntityRid() {
        return procedureEntityRid;
    }

    public void setProcedureEntityRid(Integer procedureEntityRid) {
        this.procedureEntityRid = procedureEntityRid;
    }

    public Integer getProcedureTechActive() {
        return procedureTechActive;
    }

    public void setProcedureTechActive(Integer procedureTechActive) {
        this.procedureTechActive = procedureTechActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprocedureTechRid: Integer=");
        sb.append(procedureTechRid);
        sb.append(";");

        sb.append("\n\tprocedureRid: Integer=");
        sb.append(procedureRid);
        sb.append(";");

        sb.append("\n\tprocedureEntityRid: Integer=");
        sb.append(procedureEntityRid);
        sb.append(";");

        sb.append("\n\tprocedureTechActive: Integer=");
        sb.append(procedureTechActive);
        sb.append(";");

        return sb.toString();

    }

}
