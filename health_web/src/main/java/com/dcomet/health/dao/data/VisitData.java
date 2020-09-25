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
 * @author Adhithya
 */
@Entity
@Table(name = "t_visit")
public class VisitData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VIS_RID", updatable = false)
    private Integer id;

    @Column(name = "VIS_TYPE_INDEX")
    private Integer visTypeIndex;

    @Column(name = "VIS_SUBTYPE_INDEX")
    private Integer visSubTypeIndex;

    @Column(name = "VIS_ENT_RID")
    private Integer visEntRid;

    @Column(name = "VIS_PAT_RID")
    private Integer visPatRid;

    @Column(name = "VIS_PAT_TYPE")
    private String visPatType;

    @Column(name = "VIS_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date visDate;

    @Column(name = "VIS_TIME")
    private String visTime;

    @Column(name = "VIS_APPT_RID")
    private Integer visApptRid;

    @Column(name = "VIS_EPISODE_RID")
    private Integer visEpisodeRid;

    @Column(name = "VIS_SPECIALITY_INDEX")
    private Integer visSpecialityIndex;

    @Column(name = "VIS_REASON_INDEX")
    private Integer visReasonIndex;

    @Column(name = "VIS_REMARKS")
    private String visRemarks;

    @Column(name = "VIS_CONS_DOC_RID", updatable = false)
    private Integer visConsDocRid;

    @Column(name = "VIS_ATTN_DOC_RID", updatable = false)
    private Integer visAttnDocRid;

    @Column(name = "VIS_REF_TYPE_INDEX")
    private String visRefTypeIndex;

    @Column(name = "VIS_REF_RID")
    private Integer visRefRid;
    
    @Column(name = "VIS_REF_NAME")
    private String visRefName;

    @Column(name = "VIS_DOC_REMARKS")
    private String visDocRemarks;

    @Column(name = "VIS_STATE")
    private Integer visState;

    @Column(name = "VIS_STATUS")
    private Integer visStatus;

    @Column(name = "VIS_IS_COMPLETED")
    private Integer visIsCompleted;

    @Column(name = "VIS_DIAGNOSIS")
    private String visDiagnosis;
    
    @Column(name = "VIS_CS_NODES")
    private String visCsNodes;
    
    @Column(name = "VIS_TREATMENT")
    private String visTreatment;

    @Column(name = "VIS_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "VIS_CREATED_DATE_TIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "VIS_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "VIS_MODIFIED_DATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public VisitData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisTypeIndex() {
        return visTypeIndex;
    }

    public void setVisTypeIndex(Integer visTypeIndex) {
        this.visTypeIndex = visTypeIndex;
    }

    public Integer getVisSubTypeIndex() {
        return visSubTypeIndex;
    }

    public void setVisSubTypeIndex(Integer visSubTypeIndex) {
        this.visSubTypeIndex = visSubTypeIndex;
    }

    public Integer getVisEntRid() {
        return visEntRid;
    }

    public void setVisEntRid(Integer visEntRid) {
        this.visEntRid = visEntRid;
    }

    public Integer getVisPatRid() {
        return visPatRid;
    }

    public void setVisPatRid(Integer visPatRid) {
        this.visPatRid = visPatRid;
    }

    public String getVisPatType() {
        return visPatType;
    }

    public void setVisPatType(String visPatType) {
        this.visPatType = visPatType;
    }

    public Date getVisDate() {
        return visDate;
    }

    public void setVisDate(Date visDate) {
        this.visDate = visDate;
    }

    public String getVisTime() {
        return visTime;
    }

    public void setVisTime(String visTime) {
        this.visTime = visTime;
    }

    public Integer getVisApptRid() {
        return visApptRid;
    }

    public void setVisApptRid(Integer visApptRid) {
        this.visApptRid = visApptRid;
    }

    public Integer getVisEpisodeRid() {
        return visEpisodeRid;
    }

    public void setVisEpisodeRid(Integer visEpisodeRid) {
        this.visEpisodeRid = visEpisodeRid;
    }

    public Integer getVisSpecialityIndex() {
        return visSpecialityIndex;
    }

    public void setVisSpecialityIndex(Integer visSpecialityIndex) {
        this.visSpecialityIndex = visSpecialityIndex;
    }

    public Integer getVisReasonIndex() {
        return visReasonIndex;
    }

    public void setVisReasonIndex(Integer visReasonIndex) {
        this.visReasonIndex = visReasonIndex;
    }

    public String getVisRemarks() {
        return visRemarks;
    }

    public void setVisRemarks(String visRemarks) {
        this.visRemarks = visRemarks;
    }

    public Integer getVisConsDocRid() {
        return visConsDocRid;
    }

    public void setVisConsDocRid(Integer visConsDocRid) {
        this.visConsDocRid = visConsDocRid;
    }

    public Integer getVisAttnDocRid() {
        return visAttnDocRid;
    }

    public void setVisAttnDocRid(Integer visAttnDocRid) {
        this.visAttnDocRid = visAttnDocRid;
    }

    public String getVisRefTypeIndex() {
        return visRefTypeIndex;
    }

    public void setVisRefTypeIndex(String visRefTypeIndex) {
        this.visRefTypeIndex = visRefTypeIndex;
    }

    public Integer getVisRefRid() {
        return visRefRid;
    }

    public void setVisRefRid(Integer visRefRid) {
        this.visRefRid = visRefRid;
    }

    public String getVisRefName() {
        return visRefName;
    }

    public void setVisRefName(String visRefName) {
        this.visRefName = visRefName;
    }

    public String getVisDocRemarks() {
        return visDocRemarks;
    }

    public void setVisDocRemarks(String visDocRemarks) {
        this.visDocRemarks = visDocRemarks;
    }

    public Integer getVisState() {
        return visState;
    }

    public void setVisState(Integer visState) {
        this.visState = visState;
    }

    public Integer getVisStatus() {
        return visStatus;
    }

    public void setVisStatus(Integer visStatus) {
        this.visStatus = visStatus;
    }

    public Integer getVisIsCompleted() {
        return visIsCompleted;
    }

    public void setVisIsCompleted(Integer visIsCompleted) {
        this.visIsCompleted = visIsCompleted;
    }

    public String getVisDiagnosis() {
        return visDiagnosis;
    }

    public void setVisDiagnosis(String visDiagnosis) {
        this.visDiagnosis = visDiagnosis;
    }

    public String getVisCsNodes() {
        return visCsNodes;
    }

    public void setVisCsNodes(String visCsNodes) {
        this.visCsNodes = visCsNodes;
    }

    public String getVisTreatment() {
        return visTreatment;
    }

    public void setVisTreatment(String visTreatment) {
        this.visTreatment = visTreatment;
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

        sb.append("\n\tvisTypeIndex: Integer=");
        sb.append(visTypeIndex);
        sb.append(";");

        sb.append("\n\tvisSubTypeIndex: String=");
        sb.append(visSubTypeIndex);
        sb.append(";");

        sb.append("\n\tvisEntRid: Integer=");
        sb.append(visEntRid);
        sb.append(";");

        sb.append("\n\tvisPatRid: String=");
        sb.append(visPatRid);
        sb.append(";");

        sb.append("\n\tvisPatType: Integer=");
        sb.append(visPatType);
        sb.append(";");

        sb.append("\n\tvisDate: Date=");
        sb.append(visTypeIndex);
        sb.append(";");

        sb.append("\n\tvisTime: String=");
        sb.append(visTime);
        sb.append(";");

        sb.append("\n\tvisApptRid: String=");
        sb.append(visApptRid);
        sb.append(";");

        sb.append("\n\tvisEpisodeRid: Integer=");
        sb.append(visEpisodeRid);
        sb.append(";");

        sb.append("\n\tvisSpecialityIndex: Integer=");
        sb.append(visSpecialityIndex);
        sb.append(";");

        sb.append("\n\tvisReasonIndex: String=");
        sb.append(visReasonIndex);
        sb.append(";");

        sb.append("\n\tvisRemarks: String=");
        sb.append(visRemarks);
        sb.append(";");

        sb.append("\n\tvisConsDocRid: String=");
        sb.append(visConsDocRid);
        sb.append(";");

        sb.append("\n\tvisAttnDocRid: String=");
        sb.append(visAttnDocRid);
        sb.append(";");

        sb.append("\n\tvisRefTypeIndex: String=");
        sb.append(visRefTypeIndex);
        sb.append(";");

        sb.append("\n\tvisRefRid: String=");
        sb.append(visRefRid);
        sb.append(";");

        sb.append("\n\tvisDocRemarks: String=");
        sb.append(visDocRemarks);
        sb.append(";");

        sb.append("\n\tvisRefName: String=");
        sb.append(visRefName);
        sb.append(";");
        
        sb.append("\n\tvisState: String=");
        sb.append(visState);
        sb.append(";");

        sb.append("\n\tvisStatus: Integer=");
        sb.append(visStatus);
        sb.append(";");

        sb.append("\n\tvisIsCompleted: Integer=");
        sb.append(visIsCompleted);
        sb.append(";");

        sb.append("\n\tvisDiagnosis: String=");
        sb.append(visDiagnosis);
        sb.append(";");
        
        sb.append("\n\tvisCsNodes: String=");
        sb.append(visCsNodes);
        sb.append(";");
        
        sb.append("\n\tvisTreatment: String=");
        sb.append(visTreatment);
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
