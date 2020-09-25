package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "s_payer")
public class PayerMasterData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PD_ID", updatable = false)
    private Integer pdId;

    @Column(name = "PD_PAYER_NO")
    private String pdPayerNo;

    @Column(name = "PD_PAYER_TYPE")
    private Integer pdPayerType;

    @Column(name = "PD_PAYER_NAME")
    private String pdPayerName;

    @Column(name = "PD_PAYER_ADDRESS")
    private String pdPayerAddress;

    @Column(name = "PD_CITY")
    private String pdCity;

    @Column(name = "PD_NUIT_ID")
    private Integer pdNuitId;

    @Column(name = "PD_CONTACT")
    private Integer pdContact;

    @Column(name = "PD_IS_ACTIVE")
    private Integer pdIsActive;

    @Column(name = "PD_ENTITY_RID")
    private Integer pdEntityRid;

    @Column(name = "PD_CREATED_USER_RID")
    private Integer createdUserRid;

    @Column(name = "PD_CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "PD_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "PD_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public Integer getPdId() {
        return pdId;
    }

    public void setPdId(Integer pdId) {
        this.pdId = pdId;
    }

    public String getPdPayerNo() {
        return pdPayerNo;
    }

    public void setPdPayerNo(String pdPayerNo) {
        this.pdPayerNo = pdPayerNo;
    }

    public Integer getPdPayerType() {
        return pdPayerType;
    }

    public void setPdPayerType(Integer pdPayerType) {
        this.pdPayerType = pdPayerType;
    }

    public String getPdPayerName() {
        return pdPayerName;
    }

    public void setPdPayerName(String pdPayerName) {
        this.pdPayerName = pdPayerName;
    }

    public String getPdPayerAddress() {
        return pdPayerAddress;
    }

    public void setPdPayerAddress(String pdPayerAddress) {
        this.pdPayerAddress = pdPayerAddress;
    }

    public String getPdCity() {
        return pdCity;
    }

    public void setPdCity(String pdCity) {
        this.pdCity = pdCity;
    }

    public Integer getPdNuitId() {
        return pdNuitId;
    }

    public void setPdNuitId(Integer pdNuitId) {
        this.pdNuitId = pdNuitId;
    }

    public Integer getPdContact() {
        return pdContact;
    }

    public void setPdContact(Integer pdContact) {
        this.pdContact = pdContact;
    }

    public Integer getPdIsActive() {
        return pdIsActive;
    }

    public void setPdIsActive(Integer pdIsActive) {
        this.pdIsActive = pdIsActive;
    }

    public Integer getPdEntityRid() {
        return pdEntityRid;
    }

    public void setPdEntityRid(Integer pdEntityRid) {
        this.pdEntityRid = pdEntityRid;
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
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
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
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tpdId: Integer=");
        sb.append(pdId);
        sb.append(";");

        sb.append("\n\tpdPayerNo: String=");
        sb.append(pdPayerNo);
        sb.append(";");

        sb.append("\n\tpdPayerType: Integer=");
        sb.append(pdPayerType);
        sb.append(";");

        sb.append("\n\tpdPayerName: String=");
        sb.append(pdPayerName);
        sb.append(";");

        sb.append("\n\tpdPayerAddress: String=");
        sb.append(pdPayerAddress);
        sb.append(";");

        sb.append("\n\tpdCity: String=");
        sb.append(pdCity);
        sb.append(";");

        sb.append("\n\tpdIsActive: Integer=");
        sb.append(pdIsActive);
        sb.append(";");

        sb.append("\n\tpdEntityRid: Integer=");
        sb.append(pdEntityRid);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }

}
