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
@Table(name = "d_opd_referral")
public class DOpdReferralData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ENT_RID")
    private Integer entRid;

    @Column(name = "CAL_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @Column(name = "TOTAL_NO_OF_COUNT")
    private Integer totalNoOfCount;

    @Column(name = "REF_TYPE_INDEX")
    private Integer referralIndex;

    @Column(name = "REF_TYPE_VALUE")
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
