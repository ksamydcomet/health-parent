package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Calendar;

/**
 *
 * @author Dev4
 */
public class VisitVitalsSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer vitVisRID;
    private Integer vitPatRID;
    private String vitName;
    private Integer vitDdictIndex;
    private String vitUom;
    private String vitValue;
    private Integer vitEntityRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVitVisRID() {
        return vitVisRID;
    }

    public void setVitVisRID(Integer vitVisRID) {
        this.vitVisRID = vitVisRID;
    }

    public Integer getVitPatRID() {
        return vitPatRID;
    }

    public void setVitPatRID(Integer vitPatRID) {
        this.vitPatRID = vitPatRID;
    }

    public String getVitName() {
        return vitName;
    }

    public void setVitName(String vitName) {
        this.vitName = vitName;
    }

    public Integer getVitDdictIndex() {
        return vitDdictIndex;
    }

    public void setVitDdictIndex(Integer vitDdictIndex) {
        this.vitDdictIndex = vitDdictIndex;
    }

    public String getVitUom() {
        return vitUom;
    }

    public void setVitUom(String vitUom) {
        this.vitUom = vitUom;
    }

    public String getVitValue() {
        return vitValue;
    }

    public void setVitValue(String vitValue) {
        this.vitValue = vitValue;
    }

    public Integer getVitEntityRid() {
        return vitEntityRid;
    }

    public void setVitEntityRid(Integer vitEntityRid) {
        this.vitEntityRid = vitEntityRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tvitVisRID: Integer=");
        sb.append(vitVisRID);
        sb.append(";");

        sb.append("\n\tvitPatRID: Integer=");
        sb.append(vitPatRID);
        sb.append(";");

        sb.append("\n\tvitName: String=");
        sb.append(vitName);
        sb.append(";");

        sb.append("\n\tvitDdictIndex: String=");
        sb.append(vitDdictIndex);
        sb.append(";");

        sb.append("\n\tvitUom: String=");
        sb.append(vitUom);
        sb.append(";");

        sb.append("\n\tvitValue: String=");
        sb.append(vitValue);
        sb.append(";");

        sb.append("\n\tvitEntityRid: Integer=");
        sb.append(vitEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
