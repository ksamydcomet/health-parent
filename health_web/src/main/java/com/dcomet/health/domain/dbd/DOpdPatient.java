/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcomet.health.domain.dbd;

import java.io.Serializable;

/**
 *
 * @author Dev1
 */
public class DOpdPatient implements Serializable, Comparable<DOpdPatient> {

    private Integer id;
    
    private Integer entRid;

    private String date;

    private Integer totalNoOfRegistration = 0;

    private Integer totalNoOfVisited = 0;

    private Integer totalNoOfMale = 0;

    private Integer totalNoOfFemale = 0;

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

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public int compareTo(DOpdPatient o) {
        return (this.id - o.id);
    }

}
