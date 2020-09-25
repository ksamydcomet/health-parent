package com.dcomet.health.domain.dbd;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dev4
 */
public class DBillCollection implements Serializable {

    private Integer id;
    private Date tranDate;
    private Integer entRid;
    private Float totalAmount;
    private Float totalCash;
    private Float totalCard;
    private Float totalCheque;

    public DBillCollection() {

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
  

