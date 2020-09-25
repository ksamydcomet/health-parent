package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_visit_template")
public class VisitTemplateData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VIST_RID", updatable = false)
    private Integer id;

    @Column(name = "VIST_VISIT_RID", updatable = false)
    private Integer vistVisitRID;

    @Column(name = "VIST_GROUP")
    private String vistGroup;

    @Column(name = "VIST_SUB_GROUP")
    private String vistSubGroup;

    @Column(name = "VIST_TYPE")
    private String vistType;

    @Column(name = "VIST_SPECIALITY")
    private String vistSpeciality;

    @Column(name = "VIST_PAT_RID", updatable = false)
    private Integer vistPatRID;

    @Column(name = "VIST_ENTITY_RID")
    private Integer vistEntityRid;

    @Column(name = "VIST_NODES")
    private String vistNodes;

    @Column(name = "VIST_SEQ_NUM")
    private Integer vistSeqNum;

    @Column(name = "VIST_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "VIST_CREATED_USER_DATETIME", updatable = false)
    private Calendar createdDateTime;

    @Column(name = "VIST_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "VIST_MODIFIED_USER_DATETIME")
    private Calendar modifiedDateTime;

    public VisitTemplateData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVistVisitRID() {
        return vistVisitRID;
    }

    public void setVistVisitRID(Integer vistVisitRID) {
        this.vistVisitRID = vistVisitRID;
    }

    public String getVistGroup() {
        return vistGroup;
    }

    public void setVistGroup(String vistGroup) {
        this.vistGroup = vistGroup;
    }

    public String getVistSubGroup() {
        return vistSubGroup;
    }

    public void setVistSubGroup(String vistSubGroup) {
        this.vistSubGroup = vistSubGroup;
    }

    public String getVistType() {
        return vistType;
    }

    public void setVistType(String vistType) {
        this.vistType = vistType;
    }

    public String getVistSpeciality() {
        return vistSpeciality;
    }

    public void setVistSpeciality(String vistSpeciality) {
        this.vistSpeciality = vistSpeciality;
    }

    public Integer getVistPatRID() {
        return vistPatRID;
    }

    public void setVistPatRID(Integer vistPatRID) {
        this.vistPatRID = vistPatRID;
    }

    public Integer getVistEntityRid() {
        return vistEntityRid;
    }

    public void setVistEntityRid(Integer vistEntityRid) {
        this.vistEntityRid = vistEntityRid;
    }

    public String getVistNodes() {
        return vistNodes;
    }

    public void setVistNodes(String vistNodes) {
        this.vistNodes = vistNodes;
    }

    public Integer getVistSeqNum() {
        return vistSeqNum;
    }

    public void setVistSeqNum(Integer vistSeqNum) {
        this.vistSeqNum = vistSeqNum;
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

        sb.append("\n\tvistVisitRID: Integer=");
        sb.append(vistVisitRID);
        sb.append(";");

        sb.append("\n\tvistGroup: String=");
        sb.append(vistGroup);
        sb.append(";");

        sb.append("\n\tvistSubGroup: String=");
        sb.append(vistSubGroup);
        sb.append(";");

        sb.append("\n\tvistType: String=");
        sb.append(vistType);
        sb.append(";");

        sb.append("\n\tvistSpeciality: String=");
        sb.append(vistSpeciality);
        sb.append(";");

        sb.append("\n\tvistPatRID: Integer=");
        sb.append(vistPatRID);
        sb.append(";");

        sb.append("\n\tvistNodes: String=");
        sb.append(vistNodes);
        sb.append(";");

        sb.append("\n\tvistSeqNum: Integer=");
        sb.append(vistSeqNum);
        sb.append(";");

        sb.append("\n\tvistEntityRid: Integer=");
        sb.append(vistEntityRid);
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
