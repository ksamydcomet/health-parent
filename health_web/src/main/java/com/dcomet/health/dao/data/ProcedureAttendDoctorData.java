package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_procedure_attend_doctor")
public class ProcedureAttendDoctorData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROCEDURE_DOC_RID", updatable = false)
    private Integer id;

    @Column(name = "PROCEDURE_RID")
    private Integer procedureRid;

    @Column(name = "PROCEDURE_ATT_DOCTOR_RID")
    private Integer procedureAttDoctorRid;

    @Column(name = "PROCEDURE_ENTITY_RID")
    private Integer procedureEntityRid;

    @Column(name = "PROCEDURE_ATT_DOCTOR_ACTIVE")
    private Integer procedureAttDoctorActive;

    public ProcedureAttendDoctorData() {
    }

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

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tprocedureRid: Integer=");
        sb.append(procedureRid);
        sb.append(";");

        sb.append("\n\tprocedureAttDoctorRid: Integer=");
        sb.append(procedureAttDoctorRid);
        sb.append(";");

        sb.append("\n\tprocedureEntityRid: Integer=");
        sb.append(procedureEntityRid);
        sb.append(";");

        sb.append("\n\tprocedureAttDoctorActive: Integer=");
        sb.append(procedureAttDoctorActive);
        sb.append(";");

        return sb.toString();

    }

}
