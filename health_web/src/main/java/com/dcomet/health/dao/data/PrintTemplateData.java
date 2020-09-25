package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_print_template")
public class PrintTemplateData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_ID", updatable = false)
    private Integer ptId;

    @Column(name = "PT_TYPE")
    private String ptType;

    @Column(name = "PT_NAME")
    private String ptName;

    @Column(name = "PT_NODES")
    private String ptNodes;

    @Column(name = "PT_ENTITY_RID")
    private Integer ptEntityRID;

    @Column(name = "PT_DEFAULT_DATA")
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
