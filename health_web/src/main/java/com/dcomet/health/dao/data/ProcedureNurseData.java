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
@Table(name = "t_procedure_nurse")
public class ProcedureNurseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "PROC_NURSE_RID")
    private Integer procNurseRid;

    @Column(name = "PROC_RID")
    private Integer procRid;

    @Column(name = "PROC_ENTITY_RID")
    private Integer procEntityRid;

    @Column(name = "PROC_NURSE_ACTIVE")
    private Integer procNurseActive;

    public ProcedureNurseData() {
    }

    public ProcedureNurseData(Integer id) {
        this.id = id;
    }

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
