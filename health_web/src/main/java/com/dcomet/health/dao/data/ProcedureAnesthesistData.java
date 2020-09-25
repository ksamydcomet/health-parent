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
@Table(name = "t_procedure_anesthesist")
public class ProcedureAnesthesistData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "PROCEDURE_RID")
    private Integer procedureRid;

    @Column(name = "PROCEDURE_ANESTHES_RID")
    private Integer procedureAnesthesRid;

    @Column(name = "PROCEDURE_ENTITY_RID")
    private Integer procedureEntityRid;

    @Column(name = "PROCEDURE_ANESTHESIST_ACTIVE")
    private Integer procedureAnesthesistActive;

    public ProcedureAnesthesistData() {
    }

    public ProcedureAnesthesistData(Integer id) {
        this.id = id;
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
