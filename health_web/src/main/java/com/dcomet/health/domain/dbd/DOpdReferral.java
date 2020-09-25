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
public class DOpdReferral implements Serializable, Comparable<DOpdReferral> {

    private Integer id;
    
    private Integer entRid;

    private String date;

    private Integer totalNoOfCount;

    private Integer referralIndex;

    private String referralValue;

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

    public Integer getTotalNoOfCount() {
        return totalNoOfCount;
    }

    public void setTotalNoOfCount(Integer totalNoOfCount) {
        this.totalNoOfCount = totalNoOfCount;
    }

    public Integer getReferralIndex() {
        return referralIndex;
    }

    public void setReferralIndex(Integer referralIndex) {
        this.referralIndex = referralIndex;
    }

    public String getReferralValue() {
        return referralValue;
    }

    public void setReferralValue(String referralValue) {
        this.referralValue = referralValue;
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

        sb.append("\tTotalNoOfCount:").append(totalNoOfCount);
        sb.append(",");
        sb.append("\n");

        sb.append("\tReferralIndex:").append(referralIndex);
        sb.append(",");
        sb.append("\n");

        sb.append("\tReferralValue:").append(referralValue);
        sb.append(",");
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public int compareTo(DOpdReferral o) {
        return (this.id - o.id);
    }

}
