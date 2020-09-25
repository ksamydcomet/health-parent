package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

public class PayerServiceMap extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer psmId;
    private Integer psmPdRid;
    private String psmServiceGroup;
    private Integer psmServiceRid;
    private String psmServiceName;
    private Float psmServiceCost;
    private Float psmDiscountPercent;
    private Float psmDiscountVal;
    private Integer psmIsIncluded;
    private Integer psmIsExcluded;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;
    private Float psmDiscountPrice;
    private Integer psmIsActive;

    public Integer getPsmId() {
        return psmId;
    }

    public void setPsmId(Integer psmId) {
        this.psmId = psmId;
    }

    public Integer getPsmPdRid() {
        return psmPdRid;
    }

    public void setPsmPdRid(Integer psmPdRid) {
        this.psmPdRid = psmPdRid;
    }

    public String getPsmServiceGroup() {
        return psmServiceGroup;
    }

    public void setPsmServiceGroup(String psmServiceGroup) {
        this.psmServiceGroup = psmServiceGroup;
    }

    public Integer getPsmServiceRid() {
        return psmServiceRid;
    }

    public void setPsmServiceRid(Integer psmServiceRid) {
        this.psmServiceRid = psmServiceRid;
    }

    public Integer getPsmIsActive() {
        return psmIsActive;
    }

    public void setPsmIsActive(Integer psmIsActive) {
        this.psmIsActive = psmIsActive;
    }

    

    public String getPsmServiceName() {
        return psmServiceName;
    }

    public void setPsmServiceName(String psmServiceName) {
        this.psmServiceName = psmServiceName;
    }

    public Float getPsmServiceCost() {
        return psmServiceCost;
    }

    public void setPsmServiceCost(Float psmServiceCost) {
        this.psmServiceCost = psmServiceCost;
    }

    public Float getPsmDiscountPercent() {
        return psmDiscountPercent;
    }

    public void setPsmDiscountPercent(Float psmDiscountPercent) {
        this.psmDiscountPercent = psmDiscountPercent;
    }

    public Float getPsmDiscountVal() {
        return psmDiscountVal;
    }

    public void setPsmDiscountVal(Float psmDiscountVal) {
        this.psmDiscountVal = psmDiscountVal;
    }

    public Integer getPsmIsIncluded() {
        return psmIsIncluded;
    }

    public void setPsmIsIncluded(Integer psmIsIncluded) {
        this.psmIsIncluded = psmIsIncluded;
    }

    public Integer getPsmIsExcluded() {
        return psmIsExcluded;
    }

    public void setPsmIsExcluded(Integer psmIsExcluded) {
        this.psmIsExcluded = psmIsExcluded;
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
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(String modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Float getPsmDiscountPrice() {
        return psmDiscountPrice;
    }

    public void setPsmDiscountPrice(Float psmDiscountPrice) {
        this.psmDiscountPrice = psmDiscountPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tpsmId: Integer=");
        sb.append(psmId);
        sb.append(";");

        sb.append("\n\tpsmPdRid: Integer=");
        sb.append(psmPdRid);
        sb.append(";");

        sb.append("\n\tpsmServiceGroup: String=");
        sb.append(psmServiceGroup);
        sb.append(";");

        sb.append("\n\tpsmServiceRid: Integer=");
        sb.append(psmServiceRid);
        sb.append(";");

        sb.append("\n\tpsmServiceName: String=");
        sb.append(psmServiceName);
        sb.append(";");

         sb.append("\n\tpsmIsActive: Integer=");
        sb.append(psmIsActive);
        sb.append(";");

        sb.append("\n\tpsmServiceCost: Float=");
        sb.append(psmServiceCost);
        sb.append(";");

        sb.append("\n\tpsmDiscountPercent: Float=");
        sb.append(psmDiscountPercent);
        sb.append(";");

        sb.append("\n\tpsmDiscountVal: Float=");
        sb.append(psmDiscountVal);
        sb.append(";");

        sb.append("\n\tpsmDiscountPrice: Float=");
        sb.append(psmDiscountPrice);
        sb.append(";");

        sb.append("\n\tpsmIsIncluded: Integer=");
        sb.append(psmIsIncluded);
        sb.append(";");

        sb.append("\n\tpsmIsExcluded: Integer=");
        sb.append(psmIsExcluded);
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
