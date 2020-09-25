package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_material_issue_h")
public class MaterialIssueHData implements Serializable,Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAT_ISSUE_RID")
    private Integer id;

    @Column(name = "MAT_ISSUE_NO")
    private String matIssueNo;

    @Column(name = "MAT_ISSUE_PREFIX")
    private String matIssuePrefix;

    @Column(name = "MAT_ISSUE_SEQUENCE")
    private Integer matIssueSequence;

    @Column(name = "MAT_VISIT_RID")
    private Integer matVisitRid;

    @Column(name = "MAT_PAT_RID")
    private Integer matPatRid;

    @Column(name = "MAT_PAT_NAME")
    private String matPatName;

    @Column(name = "MAT_ISSUE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date matIssueDate;

    @Column(name = "MAT_ISSUE_QTY")
    private Integer matIssueQty;
    
    @Column(name = "MAT_STATE")
    private Integer matState;
    
    @Column(name = "MAT_STATUS")
    private Integer matStatus;

    @Column(name = "MAT_ENT_RID")
    private Integer matEntRid;

    @Column(name = "MAT_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "MAT_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "MAT_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "MAT_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public MaterialIssueHData() {
    }

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

    public Date getMatIssueDate() {
        return matIssueDate;
    }

    public void setMatIssueDate(Date matIssueDate) {
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

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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

        sb.append("\n\tmatIssueDate: Date=");
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

        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tadModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        return sb.toString();

    }

}
