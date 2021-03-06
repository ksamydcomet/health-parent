package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Calendar;

/**
 *
 * @author Dev4
 */
public class ComplaintsSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer cplVisitRID;
    private Integer cplPatRID;
    private String cplName;
    private Integer cplNameIndex;
    private String cplDesc;
    private Integer cplDescRangeIndex;
    private String cplCurrentStatus;
    private Integer cplCurrentStatusIndex;
    private Integer cplEntityRid;
    private Integer createdUserRid;
    private Calendar createdDateTime;
    private Integer modifiedUserRid;
    private Calendar modifiedDateTime;

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

    public Integer getCplEntityRid() {
        return cplEntityRid;
    }

    public void setCplEntityRid(Integer cplEntityRid) {
        this.cplEntityRid = cplEntityRid;
    }

    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }   

    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
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

        sb.append("\n\tcplEntityRid: Integer=");
        sb.append(cplEntityRid);
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
