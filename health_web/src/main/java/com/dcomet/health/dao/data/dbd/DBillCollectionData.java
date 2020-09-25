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

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "d_billing_collection")
public class DBillCollectionData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "TRAN_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tranDate;

    @Column(name = "ENT_RID")
    private Integer entRid;

    @Column(name = "TOTAL_AMOUNT")
    private Float totalAmount;

    @Column(name = "TOTAL_CASH")
    private Float totalCash;

    @Column(name = "TOTAL_CARD")
    private Float totalCard;

    @Column(name = "TOTAL_CHEQUE")
    private Float totalCheque;

    public DBillCollectionData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Integer getEntRid() {
        return entRid;
    }

    public void setEntRid(Integer entRid) {
        this.entRid = entRid;
    }

   
    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Float totalCash) {
        this.totalCash = totalCash;
    }

    public Float getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(Float totalCard) {
        this.totalCard = totalCard;
    }

    public Float getTotalCheque() {
        return totalCheque;
    }

    public void setTotalCheque(Float totalCheque) {
        this.totalCheque = totalCheque;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\ttranDate: Date=");
        sb.append(tranDate);
        sb.append(";");

        sb.append("\n\tEntRid: Integer=");
        sb.append(entRid);
        sb.append(";");

        sb.append("\n\ttotalAmount: Float=");
        sb.append(totalAmount);
        sb.append(";");

        sb.append("\n\ttotalCash: Float=");
        sb.append(totalCash);
        sb.append(";");

        sb.append("\n\ttotalCard: Float=");
        sb.append(totalCard);
        sb.append(";");

        sb.append("\n\ttotalCheque: Float=");
        sb.append(totalCheque);
        sb.append(";");

        return sb.toString();
    }
}
