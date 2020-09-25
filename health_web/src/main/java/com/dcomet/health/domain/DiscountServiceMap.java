package com.dcomet.health.domain;

public class DiscountServiceMap {

    private Integer dsmRid;
    private Integer dsmServiceRid;
    private Integer dsmDisRid;
    private String dsmServiceName;
    private Float dsmDisPercentage;
    private Float dsmDisAmount;
    private Integer createdUserRid;
    private String createdDateTime;
    private Integer modifiedUserRid;
    private String modifiedDateTime;
    private Float dsmServicePrice;
    private Double dsmServiceDiscountPrice;

    public Integer getDsmRid() {
        return dsmRid;
    }

    public void setDsmRid(Integer dsmRid) {
        this.dsmRid = dsmRid;
    }

    public Integer getDsmServiceRid() {
        return dsmServiceRid;
    }

    public void setDsmServiceRid(Integer dsmServiceRid) {
        this.dsmServiceRid = dsmServiceRid;
    }

    public Integer getDsmDisRid() {
        return dsmDisRid;
    }

    public void setDsmDisRid(Integer dsmDisRid) {
        this.dsmDisRid = dsmDisRid;
    }

    public String getDsmServiceName() {
        return dsmServiceName;
    }

    public void setDsmServiceName(String dsmServiceName) {
        this.dsmServiceName = dsmServiceName;
    }

    public Float getDsmDisPercentage() {
        return dsmDisPercentage;
    }

    public void setDsmDisPercentage(Float dsmDisPercentage) {
        this.dsmDisPercentage = dsmDisPercentage;
    }

    public Float getDsmDisAmount() {
        return dsmDisAmount;
    }

    public void setDsmDisAmount(Float dsmDisAmount) {
        this.dsmDisAmount = dsmDisAmount;
    }

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(String modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Float getDsmServicePrice() {
        return dsmServicePrice;
    }

    public void setDsmServicePrice(Float dsmServicePrice) {
        this.dsmServicePrice = dsmServicePrice;
    }

    public Double getDsmServiceDiscountPrice() {
        return dsmServiceDiscountPrice;
    }

    public void setDsmServiceDiscountPrice(Double dsmServiceDiscountPrice) {
        this.dsmServiceDiscountPrice = dsmServiceDiscountPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tdsmRid: Integer=");
        sb.append(dsmRid);
        sb.append(";");

        sb.append("\n\tdsmServiceRid: Integer=");
        sb.append(dsmServiceRid);
        sb.append(";");

        sb.append("\n\tdsmDisRid: Integer=");
        sb.append(dsmDisRid);
        sb.append(";");

        sb.append("\n\tdsmServiceName: String=");
        sb.append(dsmServiceName);
        sb.append(";");

        sb.append("\n\tdsmDisPercentage: Float=");
        sb.append(dsmDisPercentage);
        sb.append(";");

        sb.append("\n\tdsmDisAmount: Float=");
        sb.append(dsmDisAmount);
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

        sb.append("\n\tdsmServicePrice: Double=");
        sb.append(dsmServicePrice);
        sb.append(";");

        sb.append("\n\tdsmServiceDiscountPrice: Double=");
        sb.append(dsmServiceDiscountPrice);
        sb.append(";");

        return sb.toString();
    }

}
