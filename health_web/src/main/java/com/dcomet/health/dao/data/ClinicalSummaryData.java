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
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_clinical_summary")
public class ClinicalSummaryData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CS_RID", updatable = false)
    private Integer id;

    @Column(name = "CS_PAT_RID")
    private Integer csPatRid;

    @Column(name = "CS_VISIT_RID")
    private Integer csVisitRid;

    @Column(name = "CS_VISIT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date csVisitDate;

    @Lob
    @Column(name = "CS_NODE")
    private String csNode;

    @Column(name = "CS_SEND_EMAIL")
    private Integer csSendEmail;

    @Column(name = "CS_ENTITY_RID")
    private Integer csEntityRid;

    @Column(name = "CS_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "CS_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "CS_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "CS_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public ClinicalSummaryData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsPatRid() {
        return csPatRid;
    }

    public void setCsPatRid(Integer csPatRid) {
        this.csPatRid = csPatRid;
    }

    public Integer getCsVisitRid() {
        return csVisitRid;
    }

    public void setCsVisitRid(Integer csVisitRid) {
        this.csVisitRid = csVisitRid;
    }

    public Date getCsVisitDate() {
        return csVisitDate;
    }

    public void setCsVisitDate(Date csVisitDate) {
        this.csVisitDate = csVisitDate;
    }

    public String getCsNode() {
        return csNode;
    }

    public void setCsNode(String csNode) {
        this.csNode = csNode;
    }

    public Integer getCsSendEmail() {
        return csSendEmail;
    }

    public void setCsSendEmail(Integer csSendEmail) {
        this.csSendEmail = csSendEmail;
    }

    public Integer getCsEntityRid() {
        return csEntityRid;
    }

    public void setCsEntityRid(Integer csEntityRid) {
        this.csEntityRid = csEntityRid;
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

        sb.append("\n\tcsPatRid: Integer=");
        sb.append(csPatRid);
        sb.append(";");
        sb.append("\n\tcsVisitRid: Integer=");
        sb.append(csVisitRid);
        sb.append(";");

        sb.append("\n\tcsVisitDate: Date=");
        sb.append(csVisitDate);
        sb.append(";");

        sb.append("\n\tcsNode: String=");
        sb.append(csNode);
        sb.append(";");

        sb.append("\n\tcsSendEmail: Integer=");
        sb.append(csSendEmail);
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
