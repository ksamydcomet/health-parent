package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

public class PayerMasterSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;

    private Integer pdId;
    private String pdPayerNo;
    private Integer pdPayerType;
    private String pdPayerName;
    private String pdPayerAddress;
    private String pdCity;
    private Integer pdIsActive;
    private Integer pdEntityRid;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;

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
