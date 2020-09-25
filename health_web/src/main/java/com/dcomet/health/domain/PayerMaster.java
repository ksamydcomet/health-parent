package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

public class PayerMaster extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pdId;
    private String pdPayerNo;
    private Integer pdPayerType;
    private String pdPayerName;
    private String pdPayerAddress;
    private String pdCity;
    private Integer pdIsActive;
    private Integer pdNuitId;
    private Integer pdContact;
    private Integer pdEntityRid;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;

    private List<PayerServiceMap> payerServiceMap;

    public List<PayerServiceMap> getPayerServiceMap() {
        return payerServiceMap;
    }

    public void setPayerServiceMap(List<PayerServiceMap> payerServiceMap) {
        this.payerServiceMap = payerServiceMap;
    }

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

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    @Override
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(String createdDateTime) {
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
    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(String modifiedDateTime) {
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
        
        sb.append("\n\tpdNuitId: Integer=");
        sb.append(pdNuitId);
        sb.append(";");
        
        sb.append("\n\tpdContact: Integer=");
        sb.append(pdContact);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }

}
