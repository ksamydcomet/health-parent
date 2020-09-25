package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev4
 */
public class DischargeSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String disNo;
    private String disPrefix;
    private Integer disSequenceNo;
    private Integer disVisitRid;
    private Integer disPatRid;
    private String disPatName;
    private String disNodes;
    private String disDateTime;
    private String disFollowUpDate;
    private Integer disType;
    private Integer dischargeBy;
    private Integer disDeclaration;
    private Integer disSms;
    private String disNotes;
    private Integer disEntRid;
    private String createdDateTime;
    private Integer createdUserRid;
    private String modifiedDateTime;
    private Integer modifiedUserRid;

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

    public String getDisDateTime() {
        return disDateTime;
    }

    public void setDisDateTime(String disDateTime) {
        this.disDateTime = disDateTime;
    }

    public String getDisFollowUpDate() {
        return disFollowUpDate;
    }

    public void setDisFollowUpDate(String disFollowUpDate) {
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

        sb.append("\n\tdisDateTime: String=");
        sb.append(disDateTime);
        sb.append(";");

        sb.append("\n\tdisFollowUpDate: String=");
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
