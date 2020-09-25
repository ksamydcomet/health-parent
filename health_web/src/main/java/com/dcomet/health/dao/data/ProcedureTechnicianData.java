package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_procedure_technician")
public class ProcedureTechnicianData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "PROCEDURE_TECH_RID")
    private Integer procedureTechRid;

    @Column(name = "PROCEDURE_RID")
    private Integer procedureRid;

    @Column(name = "PROCEDURE_ENTITY_RID")
    private Integer procedureEntityRid;

    @Column(name = "PROCEDURE_TECH_ACTIVE")
    private Integer procedureTechActive;

    public ProcedureTechnicianData() {
    }

    public ProcedureTechnicianData(Integer id) {
        this.id = id;
    }

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
