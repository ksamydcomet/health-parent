package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev4
 */
public class FavouriteItemOrderHSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer fiohUserRID;
    private String fiohName;
    private Integer fiohEntityRID;
    private String createdDateTime;
    private Integer createdUserRID;
    private String modifiedDateTime;
    private Integer modifiedUserRID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFiohUserRID() {
        return fiohUserRID;
    }

    public void setFiohUserRID(Integer fiohUserRID) {
        this.fiohUserRID = fiohUserRID;
    }

    public String getFiohName() {
        return fiohName;
    }

    public void setFiohName(String fiohName) {
        this.fiohName = fiohName;
    }

    public Integer getFiohEntityRID() {
        return fiohEntityRID;
    }

    public void setFiohEntityRID(Integer fiohEntityRID) {
        this.fiohEntityRID = fiohEntityRID;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getCreatedUserRID() {
        return createdUserRID;
    }

    public void setCreatedUserRID(Integer createdUserRID) {
        this.createdUserRID = createdUserRID;
    }

    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(String modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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
        sb.append("\n\tfiohrid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tfiohUserRID: Integer=");
        sb.append(fiohUserRID);
        sb.append(";");

        sb.append("\n\tfiohName: String=");
        sb.append(fiohName);
        sb.append(";");

        sb.append("\n\tfiohEntityRID: Integer=");
        sb.append(fiohEntityRID);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tcreatedUserRID: Integer=");
        sb.append(createdUserRID);
        sb.append(";");

        sb.append("\n\tmodified_datetime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tmodified_user_rid: Integer=");
        sb.append(modifiedUserRID);
        sb.append(";");

        return sb.toString();
    }
}
