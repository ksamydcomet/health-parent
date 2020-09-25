package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dev4
 */
public class MaterialIssueH extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String matIssueNo;
    private String matIssuePrefix;
    private Integer matIssueSequence;
    private Integer matVisitRid;
    private Integer matPatRid;
    private String matPatName;
    private String matIssueDate;
    private Integer matIssueQty;
    private Integer matState;
    private Integer matStatus;
    private Integer matEntRid;

    public List<MaterialIssueD> materialIssueD;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatIssueNo() {
        return matIssueNo;
    }

    public void setMatIssueNo(String matIssueNo) {
        this.matIssueNo = matIssueNo;
    }

    public String getMatIssuePrefix() {
        return matIssuePrefix;
    }

    public void setMatIssuePrefix(String matIssuePrefix) {
        this.matIssuePrefix = matIssuePrefix;
    }

    public Integer getMatIssueSequence() {
        return matIssueSequence;
    }

    public void setMatIssueSequence(Integer matIssueSequence) {
        this.matIssueSequence = matIssueSequence;
    }

    public Integer getMatVisitRid() {
        return matVisitRid;
    }

    public void setMatVisitRid(Integer matVisitRid) {
        this.matVisitRid = matVisitRid;
    }

    public Integer getMatPatRid() {
        return matPatRid;
    }

    public void setMatPatRid(Integer matPatRid) {
        this.matPatRid = matPatRid;
    }

    public String getMatPatName() {
        return matPatName;
    }

    public void setMatPatName(String matPatName) {
        this.matPatName = matPatName;
    }

    public String getMatIssueDate() {
        return matIssueDate;
    }

    public void setMatIssueDate(String matIssueDate) {
        this.matIssueDate = matIssueDate;
    }

    public Integer getMatIssueQty() {
        return matIssueQty;
    }

    public void setMatIssueQty(Integer matIssueQty) {
        this.matIssueQty = matIssueQty;
    }

    public Integer getMatState() {
        return matState;
    }

    public void setMatState(Integer matState) {
        this.matState = matState;
    }

    public Integer getMatStatus() {
        return matStatus;
    }

    public void setMatStatus(Integer matStatus) {
        this.matStatus = matStatus;
    }

    public Integer getMatEntRid() {
        return matEntRid;
    }

    public void setMatEntRid(Integer matEntRid) {
        this.matEntRid = matEntRid;
    }

    public List<MaterialIssueD> getMaterialIssueD() {
        return materialIssueD;
    }

    public void setMaterialIssueD(List<MaterialIssueD> materialIssueD) {
        this.materialIssueD = materialIssueD;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tmatIssueNo: String=");
        sb.append(matIssueNo);
        sb.append(";");

        sb.append("\n\tmatIssuePrefix: String=");
        sb.append(matIssuePrefix);
        sb.append(";");

        sb.append("\n\tmatIssueSequence: Integer=");
        sb.append(matIssueSequence);
        sb.append(";");

        sb.append("\n\tmatVisitRid: Integer=");
        sb.append(matVisitRid);
        sb.append(";");

        sb.append("\n\tmatPatRid: Integer=");
        sb.append(matPatRid);
        sb.append(";");

        sb.append("\n\tmatPatName: String=");
        sb.append(matPatName);
        sb.append(";");

        sb.append("\n\tmatIssueDate: String=");
        sb.append(matIssueDate);
        sb.append(";");

        sb.append("\n\tmatIssueQty: Integer=");
        sb.append(matIssueQty);
        sb.append(";");

        sb.append("\n\tmatState: Integer=");
        sb.append(matState);
        sb.append(";");

        sb.append("\n\tmatStatus: Integer=");
        sb.append(matStatus);
        sb.append(";");

        sb.append("\n\tmatEntRid: Integer=");
        sb.append(matEntRid);
        sb.append(";");

        return sb.toString();

    }

}
