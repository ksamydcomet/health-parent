package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Abdullah
 */
public class DiscountMasterSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer disCategory;
    private String disFromDate;
    private String disToDate;
    private String disCode;
    private String disName;
    private Float disPercentage;
    private Integer disIsActive;
    private Integer disIsApproved;
      private Integer disEntityRid;
    private Integer disUnitRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDisCategory() {
        return disCategory;
    }

    public void setDisCategory(Integer disCategory) {
        this.disCategory = disCategory;
    }

    public String getDisFromDate() {
        return disFromDate;
    }

    public void setDisFromDate(String disFromDate) {
        this.disFromDate = disFromDate;
    }

    public String getDisToDate() {
        return disToDate;
    }

    public Integer getDisEntityRid() {
        return disEntityRid;
    }

    public void setDisEntityRid(Integer disEntityRid) {
        this.disEntityRid = disEntityRid;
    }

    public Integer getDisUnitRid() {
        return disUnitRid;
    }

    public void setDisUnitRid(Integer disUnitRid) {
        this.disUnitRid = disUnitRid;
    }

    public void setDisToDate(String disToDate) {
        this.disToDate = disToDate;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public Float getDisPercentage() {
        return disPercentage;
    }

    public void setDisPercentage(Float disPercentage) {
        this.disPercentage = disPercentage;
    }

    public Integer getDisIsActive() {
        return disIsActive;
    }

    public void setDisIsActive(Integer disIsActive) {
        this.disIsActive = disIsActive;
    }

    public Integer getDisIsApproved() {
        return disIsApproved;
    }

    public void setDisIsApproved(Integer disIsApproved) {
        this.disIsApproved = disIsApproved;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tdisCategory: Integer=");
        sb.append(disCategory);
        sb.append(";");

        sb.append("\n\tdisCode: String=");
        sb.append(disCode);
        sb.append(";");

        sb.append("\n\tdisName: String=");
        sb.append(disName);
        sb.append(";");

        sb.append("\n\tdisPercentage: Float=");
        sb.append(disPercentage);
        sb.append(";");

        sb.append("\n\tdisIsActive: Integer=");
        sb.append(disIsActive);
        sb.append(";");

        sb.append("\n\tdisIsApproved: Integer=");
        sb.append(disIsApproved);
        sb.append(";");

        sb.append("\n\tdisFromDate: Date=");
        sb.append(disFromDate);
        sb.append(";");

        sb.append("\n\tdisToDate: Date=");
        sb.append(disToDate);
        sb.append(";");

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");
        
         sb.append("\n\tdisEntityRid: Integer=");
        sb.append(disEntityRid);
        sb.append(";");

        sb.append("\n\tdisUnitRid: Integer=");
        sb.append(disUnitRid);
        sb.append(";");

        return sb.toString();
    }
}
