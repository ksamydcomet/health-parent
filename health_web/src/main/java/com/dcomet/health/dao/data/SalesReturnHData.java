package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Adhithya
 */
@Entity
@Table(name = "t_sales_return_h")
public class SalesReturnHData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SRH_RID", updatable = false)
    private Integer id;

    @Column(name = "SRH_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date srhDate;

    @Column(name = "SRH_PATIENT_RID")
    private Integer srhPatientRID;

    @Column(name = "SRH_PATIENT_MRN")
    private String srhPatientMrn;

    @Column(name = "SRH_PATIENT_NAME")
    private String srhPatientName;

    @Column(name = "SRH_VISIT_RID")
    private Integer srhVisitRID;

    @Column(name = "SRH_VISIT_TYPE")
    private Integer srhVisitType;

    @Column(name = "SRH_ROUNDOFF_AMT")
    private Float srhRoundoffAmount;

    @Column(name = "SRH_NET_AMT")
    private Float srhNetAmount;

    @Column(name = "SRH_UNIT_RID")
    private Integer srhUnitRID;

    @Column(name = "SRH_ENTITY_RID")
    private Integer srhEntityRID;

    @Column(name = "SRH_SEQ_NO")
    private String srhSeqNo;

    @Column(name = "SRH_BILL_RID")
    private Integer srhBIllRID;

    @Column(name = "SRH_BILL_NO")
    private String srhBillNo;

    @Column(name = "SRH_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "SRH_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "SRH_MODIFY_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "SRH_MODIFY_USER_RID")
    private Integer modifiedUserRid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SRD_SRH_RID", referencedColumnName = "SRH_RID", updatable = false)
    private List<SalesReturnDData> salesReturnDData;

    public SalesReturnHData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSrhDate() {
        return srhDate;
    }

    public void setSrhDate(Date srhDate) {
        this.srhDate = srhDate;
    }

    public Integer getSrhPatientRID() {
        return srhPatientRID;
    }

    public void setSrhPatientRID(Integer srhPatientRID) {
        this.srhPatientRID = srhPatientRID;
    }

    public String getSrhPatientMrn() {
        return srhPatientMrn;
    }

    public void setSrhPatientMrn(String srhPatientMrn) {
        this.srhPatientMrn = srhPatientMrn;
    }

    public String getSrhPatientName() {
        return srhPatientName;
    }

    public void setSrhPatientName(String srhPatientName) {
        this.srhPatientName = srhPatientName;
    }

    public Integer getSrhVisitRID() {
        return srhVisitRID;
    }

    public void setSrhVisitRID(Integer srhVisitRID) {
        this.srhVisitRID = srhVisitRID;
    }

    public Integer getSrhVisitType() {
        return srhVisitType;
    }

    public void setSrhVisitType(Integer srhVisitType) {
        this.srhVisitType = srhVisitType;
    }

    public Float getSrhRoundoffAmount() {
        return srhRoundoffAmount;
    }

    public void setSrhRoundoffAmount(Float srhRoundoffAmount) {
        this.srhRoundoffAmount = srhRoundoffAmount;
    }

    public Float getSrhNetAmount() {
        return srhNetAmount;
    }

    public void setSrhNetAmount(Float srhNetAmount) {
        this.srhNetAmount = srhNetAmount;
    }

    public Integer getSrhUnitRID() {
        return srhUnitRID;
    }

    public void setSrhUnitRID(Integer srhUnitRID) {
        this.srhUnitRID = srhUnitRID;
    }

    public Integer getSrhEntityRID() {
        return srhEntityRID;
    }

    public void setSrhEntityRID(Integer srhEntityRID) {
        this.srhEntityRID = srhEntityRID;
    }

    public String getSrhSeqNo() {
        return srhSeqNo;
    }

    public void setSrhSeqNo(String srhSeqNo) {
        this.srhSeqNo = srhSeqNo;
    }

    public Integer getSrhBIllRID() {
        return srhBIllRID;
    }

    public void setSrhBIllRID(Integer srhBIllRID) {
        this.srhBIllRID = srhBIllRID;
    }

    public String getSrhBillNo() {
        return srhBillNo;
    }

    public void setSrhBillNo(String srhBillNo) {
        this.srhBillNo = srhBillNo;
    }

    public List<SalesReturnDData> getSalesReturnDData() {
        return salesReturnDData;
    }

    public void setSalesReturnDData(List<SalesReturnDData> salesReturnDData) {
        this.salesReturnDData = salesReturnDData;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    @Override
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsrhDate: Date=");
        sb.append(srhDate);
        sb.append(";");

        sb.append("\n\tsrhPatientRID: Integer=");
        sb.append(srhPatientRID);
        sb.append(";");

        sb.append("\n\tsrhPatientMrn: String=");
        sb.append(srhPatientMrn);
        sb.append(";");

        sb.append("\n\tsrhPatientName: String=");
        sb.append(srhPatientName);
        sb.append(";");

        sb.append("\n\tsrhVisitRID: Integer=");
        sb.append(srhVisitRID);
        sb.append(";");

        sb.append("\n\tsrhVisitType: Integer=");
        sb.append(srhVisitType);
        sb.append(";");

        sb.append("\n\tsrhRoundoffAmount: Float=");
        sb.append(srhRoundoffAmount);
        sb.append(";");

        sb.append("\n\tsrhNetAmount: Float=");
        sb.append(srhNetAmount);
        sb.append(";");

        sb.append("\n\tsrhUnitRID: Integer=");
        sb.append(srhUnitRID);
        sb.append(";");

        sb.append("\n\tsrhEntityRID: Integer=");
        sb.append(srhEntityRID);
        sb.append(";");

        sb.append("\n\tsrhSeqNo: String=");
        sb.append(srhSeqNo);
        sb.append(";");

        sb.append("\n\tsrhBIllRID: Integer=");
        sb.append(srhBIllRID);
        sb.append(";");

        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tadModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        return sb.toString();

    }
}
