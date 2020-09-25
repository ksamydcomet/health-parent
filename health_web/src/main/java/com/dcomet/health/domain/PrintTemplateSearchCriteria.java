package com.dcomet.health.domain;


import com.dcomet.fw.domain.SearchCriteria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class PrintTemplateSearchCriteria extends SearchCriteria {

    private Integer ptId;
    private String ptType;
    private String ptName;
    private String ptNodes;
    private Integer ptEntityRID;
    private String ptDefaultData;

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public String getPtType() {
        return ptType;
    }

    public void setPtType(String ptType) {
        this.ptType = ptType;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getPtNodes() {
        return ptNodes;
    }

    public void setPtNodes(String ptNodes) {
        this.ptNodes = ptNodes;
    }

    public Integer getPtEntityRID() {
        return ptEntityRID;
    }

    public void setPtEntityRID(Integer ptEntityRID) {
        this.ptEntityRID = ptEntityRID;
    }

    public String getPtDefaultData() {
        return ptDefaultData;
    }

    public void setPtDefaultData(String ptDefaultData) {
        this.ptDefaultData = ptDefaultData;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tptId: Integer=");
        sb.append(ptId);
        sb.append(";");

        sb.append("\n\tptType: String=");
        sb.append(ptType);
        sb.append(";");

        sb.append("\n\tptName: String=");
        sb.append(ptName);
        sb.append(";");

        sb.append("\n\tptEntityRID: Integer=");
        sb.append(ptEntityRID);
        sb.append(";");

        sb.append("\n\tptDefaultData: String=");
        sb.append(ptDefaultData);
        sb.append(";");

        return sb.toString();

    }

}
