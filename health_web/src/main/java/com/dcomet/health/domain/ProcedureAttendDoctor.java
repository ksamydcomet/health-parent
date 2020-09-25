package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ProcedureAttendDoctor extends Base implements Serializable {

    private Integer id;
    private Integer procedureRid;
    private Integer procedureAttDoctorRid;
    private Integer procedureEntityRid;
    private Integer procedureAttDoctorActive;

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

    public Integer getProcedureAttDoctorRid() {
        return procedureAttDoctorRid;
    }

    public void setProcedureAttDoctorRid(Integer procedureAttDoctorRid) {
        this.procedureAttDoctorRid = procedureAttDoctorRid;
    }

    public Integer getProcedureEntityRid() {
        return procedureEntityRid;
    }

    public void setProcedureEntityRid(Integer procedureEntityRid) {
        this.procedureEntityRid = procedureEntityRid;
    }

    public Integer getProcedureAttDoctorActive() {
        return procedureAttDoctorActive;
    }

    public void setProcedureAttDoctorActive(Integer procedureAttDoctorActive) {
        this.procedureAttDoctorActive = procedureAttDoctorActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tId: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprType: Integer=");
        sb.append(procedureRid);
        sb.append(";");

        sb.append("\n\tprCategory: Integer=");
        sb.append(procedureAttDoctorRid);
        sb.append(";");

        sb.append("\n\tprPatientRid: Integer=");
        sb.append(procedureEntityRid);
        sb.append(";");

        sb.append("\n\tprocedureAttDoctorActive: Integer=");
        sb.append(procedureAttDoctorActive);
        sb.append(";");

        return sb.toString();

    }

}
