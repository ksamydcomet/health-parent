package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ProcedureAnesthesist extends Base implements Serializable {

    private Integer id;
    private Integer procedureRid;
    private Integer procedureAnesthesRid;
    private Integer procedureEntityRid;
    private Integer procedureAnesthesistActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcedureRid() {
        return procedureRid;
    }

    public void setProcedureRid(Integer procedureRid) {
        this.procedureRid = procedureRid;
    }

    public Integer getProcedureAnesthesRid() {
        return procedureAnesthesRid;
    }

    public void setProcedureAnesthesRid(Integer procedureAnesthesRid) {
        this.procedureAnesthesRid = procedureAnesthesRid;
    }

    public Integer getProcedureEntityRid() {
        return procedureEntityRid;
    }

    public void setProcedureEntityRid(Integer procedureEntityRid) {
        this.procedureEntityRid = procedureEntityRid;
    }

    public Integer getProcedureAnesthesistActive() {
        return procedureAnesthesistActive;
    }

    public void setProcedureAnesthesistActive(Integer procedureAnesthesistActive) {
        this.procedureAnesthesistActive = procedureAnesthesistActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprocedureRid: Integer=");
        sb.append(procedureRid);
        sb.append(";");

        sb.append("\n\tprocedureAnesthesRid: Integer=");
        sb.append(procedureAnesthesRid);
        sb.append(";");

        sb.append("\n\tprocedureEntityRid: Integer=");
        sb.append(procedureEntityRid);
        sb.append(";");

        sb.append("\n\tprocedureAnesthesistActive: Integer=");
        sb.append(procedureAnesthesistActive);
        sb.append(";");

        return sb.toString();
    }
}
