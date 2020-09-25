package com.dcomet.health.dao.data.rt;

import com.dcomet.fw.excel.ExcelColumn;
import com.dcomet.fw.excel.Excelable;
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
@Table(name = "r_billing_collection")
@Excelable(name = "BillingCollectionReport")
public class RBillCollectionData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "TRAN_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    @ExcelColumn(name = "DATE")
    private Date tranDate;

    @Column(name = "ENT_RID")
    private Integer entRid;

    @Column(name = "ENT_NAME")
    @ExcelColumn(name = "ENTITY_NAME")
    private String entName;

    @Column(name = "TOTAL_CASH")
    @ExcelColumn(name = "CASH")
    private Float totalCash;

    @Column(name = "TOTAL_CARD")
    @ExcelColumn(name = "CARD")
    private Float totalCard;

    @Column(name = "TOTAL_CHEQUE")
    @ExcelColumn(name = "CHEQUE")
    private Float totalCheque;

    @Column(name = "TOTAL_AMOUNT")
    @ExcelColumn(name = "AMOUNT(day)")
    private Float totalAmount;

    @Column(name = "TOTAL_MONTH_AMOUNT")
    @ExcelColumn(name = "AMOUNT(Month)")
    private Float totalMonthAmount;

    public RBillCollectionData() {

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

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
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

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalMonthAmount() {
        return totalMonthAmount;
    }

    public void setTotalMonthAmount(Float totalMonthAmount) {
        this.totalMonthAmount = totalMonthAmount;
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

        sb.append("\n\tEntName : String=");
        sb.append(entName);
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

        sb.append("\n\ttotalAmount: Float=");
        sb.append(totalAmount);
        sb.append(";");

        sb.append("\n\ttotalMonthAmount: Float=");
        sb.append(totalMonthAmount);
        sb.append(";");

        return sb.toString();
    }
}
