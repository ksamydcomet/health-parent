package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev4
 */
public class FavouriteServiceOrderHSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer fsohUserRID;
    private String fsohName;
    private Integer fsohEntityRID;
    private String createdDatetime;
    private Integer createdUserRID;
    private String modifiedDatetime;
    private Integer modifiedUserRID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFsohUserRID() {
        return fsohUserRID;
    }

    public void setFsohUserRID(Integer fsohUserRID) {
        this.fsohUserRID = fsohUserRID;
    }

    public String getFsohName() {
        return fsohName;
    }

    public void setFsohName(String fsohName) {
        this.fsohName = fsohName;
    }

    public Integer getFsohEntityRID() {
        return fsohEntityRID;
    }

    public void setFsohEntityRID(Integer fsohEntityRID) {
        this.fsohEntityRID = fsohEntityRID;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Integer getCreatedUserRID() {
        return createdUserRID;
    }

    public void setCreatedUserRID(Integer createdUserRID) {
        this.createdUserRID = createdUserRID;
    }

    public String getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(String modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public Integer getModifiedUserRID() {
        return modifiedUserRID;
    }

    public void setModifiedUserRID(Integer modifiedUserRID) {
        this.modifiedUserRID = modifiedUserRID;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\fsohRID: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tfsohUserRID: Integer=");
        sb.append(fsohUserRID);
        sb.append(";");

        sb.append("\n\tfsohName: Integer=");
        sb.append(fsohName);
        sb.append(";");

        sb.append("\n\tfsohEntityRID: String=");
        sb.append(fsohEntityRID);
        sb.append(";");

        sb.append("\n\tcreatedDatetime: String=");
        sb.append(createdDatetime);
        sb.append(";");

        sb.append("\n\tcreatedUserRID: Integer=");
        sb.append(createdUserRID);
        sb.append(";");

        sb.append("\n\tmodifiedDatetime: String=");
        sb.append(modifiedDatetime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRID: Integer=");
        sb.append(modifiedUserRID);
        sb.append(";");

        return sb.toString();

    }
}
