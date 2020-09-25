package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dev4
 */
public class Complaints extends Base implements Serializable {

    private Integer id;
    private Integer cplVisitRID;
    private Integer cplPatRID;
    private String cplName;
    private Integer cplNameIndex;
    private String cplDesc;
    private Integer cplDescRangeIndex;
    private String cplCurrentStatus;
    private Integer cplCurrentStatusIndex;

    private List<VisitPlan> visitPlan;
    private List<VisitTemplate> visitTemplate;
    private List<VisitVitals> visitVitals;

    public Complaints() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCplVisitRID() {
        return cplVisitRID;
    }

    public void setCplVisitRID(Integer cplVisitRID) {
        this.cplVisitRID = cplVisitRID;
    }

    public Integer getCplPatRID() {
        return cplPatRID;
    }

    public void setCplPatRID(Integer cplPatRID) {
        this.cplPatRID = cplPatRID;
    }

    public String getCplName() {
        return cplName;
    }

    public void setCplName(String cplName) {
        this.cplName = cplName;
    }

    public Integer getCplNameIndex() {
        return cplNameIndex;
    }

    public void setCplNameIndex(Integer cplNameIndex) {
        this.cplNameIndex = cplNameIndex;
    }

    public String getCplDesc() {
        return cplDesc;
    }

    public void setCplDesc(String cplDesc) {
        this.cplDesc = cplDesc;
    }

    public Integer getCplDescRangeIndex() {
        return cplDescRangeIndex;
    }

    public void setCplDescRangeIndex(Integer cplDescRangeIndex) {
        this.cplDescRangeIndex = cplDescRangeIndex;
    }

    public String getCplCurrentStatus() {
        return cplCurrentStatus;
    }

    public void setCplCurrentStatus(String cplCurrentStatus) {
        this.cplCurrentStatus = cplCurrentStatus;
    }

    public Integer getCplCurrentStatusIndex() {
        return cplCurrentStatusIndex;
    }

    public void setCplCurrentStatusIndex(Integer cplCurrentStatusIndex) {
        this.cplCurrentStatusIndex = cplCurrentStatusIndex;
    }

    public List<VisitPlan> getVisitPlan() {
        return visitPlan;
    }

    public void setVisitPlan(List<VisitPlan> visitPlan) {
        this.visitPlan = visitPlan;
    }

    public List<VisitTemplate> getVisitTemplate() {
        return visitTemplate;
    }

    public void setVisitTemplate(List<VisitTemplate> visitTemplate) {
        this.visitTemplate = visitTemplate;
    }

    public List<VisitVitals> getVisitVitals() {
        return visitVitals;
    }

    public void setVisitVitals(List<VisitVitals> visitVitals) {
        this.visitVitals = visitVitals;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tcplVisitRID: Integer=");
        sb.append(cplVisitRID);
        sb.append(";");

        sb.append("\n\tcplPatRID: Integer=");
        sb.append(cplPatRID);
        sb.append(";");

        sb.append("\n\tcplName: String=");
        sb.append(cplName);
        sb.append(";");

        sb.append("\n\tcplNameIndex: Integer=");
        sb.append(cplNameIndex);
        sb.append(";");

        sb.append("\n\tcplDesc: String=");
        sb.append(cplDesc);
        sb.append(";");

        sb.append("\n\tcplDescRangeIndex: Integer=");
        sb.append(cplDescRangeIndex);
        sb.append(";");

        sb.append("\n\tcplCurrentStatus: String=");
        sb.append(cplCurrentStatus);
        sb.append(";");

        sb.append("\n\tcplCurrentStatusIndex: Integer=");
        sb.append(cplCurrentStatusIndex);
        sb.append(";");

        return sb.toString();
    }
}
