package com.dcomet.health.dao.data.dbd;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "d_opd_patient")
public class DOpdPatientData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ENT_RID")
    private Integer entRid;

    @Column(name = "CAL_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @Column(name = "TOTAL_NO_OF_REGISTRATION")
    private Integer totalNoOfRegistration;

    @Column(name = "TOTAL_NO_OF_VISITED")
    private Integer totalNoOfVisited;

    @Column(name = "TOTAL_NO_OF_MALE")
    private Integer totalNoOfMale;

    @Column(name = "TOTAL_NO_OF_FEMALE")
    private Integer totalNoOfFemale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntRid() {
        return entRid;
    }

    public void setEntRid(Integer entRid) {
        this.entRid = entRid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotalNoOfRegistration() {
        return totalNoOfRegistration;
    }

    public void setTotalNoOfRegistration(Integer totalNoOfRegistration) {
        this.totalNoOfRegistration = totalNoOfRegistration;
    }

    public Integer getTotalNoOfVisited() {
        return totalNoOfVisited;
    }

    public void setTotalNoOfVisited(Integer totalNoOfVisited) {
        this.totalNoOfVisited = totalNoOfVisited;
    }

    public Integer getTotalNoOfMale() {
        return totalNoOfMale;
    }

    public void setTotalNoOfMale(Integer totalNoOfMale) {
        this.totalNoOfMale = totalNoOfMale;
    }

    public Integer getTotalNoOfFemale() {
        return totalNoOfFemale;
    }

    public void setTotalNoOfFemale(Integer totalNoOfFemale) {
        this.totalNoOfFemale = totalNoOfFemale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id:").append(id);
        sb.append(",");
        sb.append("\n");

        sb.append("\tEntRid:").append(entRid);
        sb.append(",");
        sb.append("\n");

        sb.append("\tDate:").append(date);
        sb.append(",");
        sb.append("\n");

        sb.append("\tTotalNoOfRegistration:").append(totalNoOfRegistration);
        sb.append(",");
        sb.append("\n");

        sb.append("\tTotalNoOfVisited:").append(totalNoOfVisited);
        sb.append(",");
        sb.append("\n");

        sb.append("\tTotalNoOfMale:").append(totalNoOfMale);
        sb.append(",");
        sb.append("\n");

        sb.append("\tTotalNoOfFemale:").append(totalNoOfFemale);
        sb.append(",");
        sb.append("\n");

        return sb.toString();
    }
}
