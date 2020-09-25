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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_discharge")
public class DischargeData implements Serializable,Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIS_RID", updatable = false)
    private Integer id;

    @Column(name = "DIS_NO")
    private String disNo;

    @Column(name = "DIS_PREFIX")
    private String disPrefix;

    @Column(name = "DIS_SEQUENCE_NO")
    private Integer disSequenceNo;

    @Column(name = "DIS_VISIT_RID")
    private Integer disVisitRid;

    @Column(name = "DIS_PAT_RID")
    private Integer disPatRid;

    @Column(name = "DIS_PAT_NAME")
    private String disPatName;

    @Column(name = "DIS_NODES")
    private String disNodes;

    @Column(name = "DIS_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar disDateTime;

    @Column(name = "DIS_FOLLOWUP_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date disFollowUpDate;

    @Column(name = "DIS_TYPE")
    private Integer disType;

    @Column(name = "DIS_BY")
    private Integer dischargeBy;

    @Column(name = "DIS_DECLARATION")
    private Integer disDeclaration;

    @Column(name = "DIS_SMS")
    private Integer disSms;

    @Column(name = "DIS_NOTES")
    private String disNotes;

    @Column(name = "DIS_ENT_RID")
    private Integer disEntRid;

    @Column(name = "DIS_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "DIS_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "DIS_MOD_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "DIS_MOD_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public DischargeData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public String getDisPrefix() {
        return disPrefix;
    }

    public void setDisPrefix(String disPrefix) {
        this.disPrefix = disPrefix;
    }

    public Integer getDisSequenceNo() {
        return disSequenceNo;
    }

    public void setDisSequenceNo(Integer disSequenceNo) {
        this.disSequenceNo = disSequenceNo;
    }

    public Integer getDisVisitRid() {
        return disVisitRid;
    }

    public void setDisVisitRid(Integer disVisitRid) {
        this.disVisitRid = disVisitRid;
    }

    public Integer getDisPatRid() {
        return disPatRid;
    }

    public void setDisPatRid(Integer disPatRid) {
        this.disPatRid = disPatRid;
    }

    public String getDisPatName() {
        return disPatName;
    }

    public void setDisPatName(String disPatName) {
        this.disPatName = disPatName;
    }

    public String getDisNodes() {
        return disNodes;
    }

    public void setDisNodes(String disNodes) {
        this.disNodes = disNodes;
    }

    public Calendar getDisDateTime() {
        return disDateTime;
    }

    public void setDisDateTime(Calendar disDateTime) {
        this.disDateTime = disDateTime;
    }

    public Date getDisFollowUpDate() {
        return disFollowUpDate;
    }

    public void setDisFollowUpDate(Date disFollowUpDate) {
        this.disFollowUpDate = disFollowUpDate;
    }

    public Integer getDisType() {
        return disType;
    }

    public void setDisType(Integer disType) {
        this.disType = disType;
    }

    public Integer getDischargeBy() {
        return dischargeBy;
    }

    public void setDischargeBy(Integer dischargeBy) {
        this.dischargeBy = dischargeBy;
    }

    public Integer getDisDeclaration() {
        return disDeclaration;
    }

    public void setDisDeclaration(Integer disDeclaration) {
        this.disDeclaration = disDeclaration;
    }

    public Integer getDisSms() {
        return disSms;
    }

    public void setDisSms(Integer disSms) {
        this.disSms = disSms;
    }

    public String getDisNotes() {
        return disNotes;
    }

    public void setDisNotes(String disNotes) {
        this.disNotes = disNotes;
    }

    public Integer getDisEntRid() {
        return disEntRid;
    }

    public void setDisEntRid(Integer disEntRid) {
        this.disEntRid = disEntRid;
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
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
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
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
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

        sb.append("\n\tdisNo: String=");
        sb.append(disNo);
        sb.append(";");

        sb.append("\n\tdisPrefix: String=");
        sb.append(disPrefix);
        sb.append(";");

        sb.append("\n\tdisSequenceNo: Integer=");
        sb.append(disSequenceNo);
        sb.append(";");

        sb.append("\n\tdisVisitRid: Integer=");
        sb.append(disVisitRid);
        sb.append(";");

        sb.append("\n\tdisPatRid: Integer=");
        sb.append(disPatRid);
        sb.append(";");

        sb.append("\n\tdisPatName: String=");
        sb.append(disPatName);
        sb.append(";");

        sb.append("\n\tdisNodes: String=");
        sb.append(disNodes);
        sb.append(";");

        sb.append("\n\tdisDateTime: Calendar=");
        sb.append(disDateTime);
        sb.append(";");

        sb.append("\n\tdisFollowUpDate: Date=");
        sb.append(disFollowUpDate);
        sb.append(";");

        sb.append("\n\tdisType: Integer=");
        sb.append(disType);
        sb.append(";");

        sb.append("\n\tdischargeBy: Integer=");
        sb.append(dischargeBy);
        sb.append(";");

        sb.append("\n\tdisDeclaration: Integer=");
        sb.append(disDeclaration);
        sb.append(";");

        sb.append("\n\tdisSms: Integer=");
        sb.append(disSms);
        sb.append(";");

        sb.append("\n\tdisNotes: String=");
        sb.append(disNotes);
        sb.append(";");

        sb.append("\n\tdisEntRid: Integer=");
        sb.append(disEntRid);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");
        
        sb.append("\n\tmodifiedUserRid: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");
        return sb.toString();
    }

}
