package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Calendar;

/**
 *
 * @author Dev3
 */
public class VisitSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer visTypeIndex;
    private Integer visSubTypeIndex;
    private Integer visEntRid;
    private Integer visPatRid;
    private String visPatType;
    private String visDate;
    private String visTime;
    private Integer visApptRid;
    private Integer visEpisodeRid;
    private Integer visSpecialityIndex;
    private Integer visReasonIndex;
    private String visRemarks;
    private Integer visConsDocRid;
    private Integer visAttnDocRid;
    private String visRefTypeIndex;
    private Integer visRefRid;
    private String visDocRemarks;
    private String visRefName;
    private Integer visState;
    private Integer visStatus;
    private Integer visIsCompleted;
    private String visDiagnosis;
    private String visCsNodes;
    private String visTreatment;
    private String createdDateTime;
    private Integer visCreatedUserRid;
    private String visModifiedDateTime;
    private Integer visModifiedUserRid;

    private Calendar visFromDate;
    private Calendar visToDate;

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

    public String getVisDate() {
        return visDate;
    }

    public void setVisDate(String visDate) {
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

    public String getVisDocRemarks() {
        return visDocRemarks;
    }

    public void setVisDocRemarks(String visDocRemarks) {
        this.visDocRemarks = visDocRemarks;
    }

    public String getVisRefName() {
        return visRefName;
    }

    public void setVisRefName(String visRefName) {
        this.visRefName = visRefName;
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
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getVisCreatedUserRid() {
        return visCreatedUserRid;
    }

    public void setVisCreatedUserRid(Integer visCreatedUserRid) {
        this.visCreatedUserRid = visCreatedUserRid;
    }

    public String getVisModifiedDateTime() {
        return visModifiedDateTime;
    }

    public void setVisModifiedDateTime(String visModifiedDateTime) {
        this.visModifiedDateTime = visModifiedDateTime;
    }

    public Integer getVisModifiedUserRid() {
        return visModifiedUserRid;
    }

    public void setVisModifiedUserRid(Integer visModifiedUserRid) {
        this.visModifiedUserRid = visModifiedUserRid;
    }

    public Calendar getVisFromDate() {
        return visFromDate;
    }

    public void setVisFromDate(Calendar visFromDate) {
        this.visFromDate = visFromDate;
    }

    public Calendar getVisToDate() {
        return visToDate;
    }

    public void setVisToDate(Calendar visToDate) {
        this.visToDate = visToDate;
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

        sb.append("\n\tvisSubTypeIndex: Integer=");
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

        sb.append("\n\tvisDate: String=");
        sb.append(visDate);
        sb.append(";");

        sb.append("\n\tvisTime: String=");
        sb.append(visTime);
        sb.append(";");

        sb.append("\n\tvisApptRid: Integer=");
        sb.append(visApptRid);
        sb.append(";");

        sb.append("\n\tvisEpisodeRid: Integer=");
        sb.append(visEpisodeRid);
        sb.append(";");

        sb.append("\n\tvisSpecialityIndex: Integer=");
        sb.append(visSpecialityIndex);
        sb.append(";");

        sb.append("\n\tvisReasonIndex: Integer=");
        sb.append(visReasonIndex);
        sb.append(";");

        sb.append("\n\tvisRemarks: String=");
        sb.append(visRemarks);
        sb.append(";");

        sb.append("\n\tvisConsDocRid: Integer=");
        sb.append(visConsDocRid);
        sb.append(";");

        sb.append("\n\tvisAttnDocRid: Integer=");
        sb.append(visAttnDocRid);
        sb.append(";");

        sb.append("\n\tvisRefTypeIndex: String=");
        sb.append(visRefTypeIndex);
        sb.append(";");

        sb.append("\n\tvisRefRid: Integer=");
        sb.append(visRefRid);
        sb.append(";");

        sb.append("\n\tvisDocRemarks: String=");
        sb.append(visDocRemarks);
        sb.append(";");

        sb.append("\n\tvisRefName: String=");
        sb.append(visRefName);
        sb.append(";");
        
        sb.append("\n\tvisState: Integer=");
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

        sb.append("\n\tvisCreatedUserRid: Integer=");
        sb.append(visCreatedUserRid);
        sb.append(";");

        sb.append("\n\tvisModifiedDateTime: String=");
        sb.append(visModifiedDateTime);
        sb.append(";");

        sb.append("\n\tvisModifiedUserRid: Integer=");
        sb.append(visModifiedUserRid);
        sb.append(";");

        return sb.toString();
    }
}
