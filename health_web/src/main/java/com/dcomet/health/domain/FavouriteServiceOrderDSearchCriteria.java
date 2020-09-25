package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev4
 */
public class FavouriteServiceOrderDSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer fsodFsohRID;
    private Integer fsodServiceRID;
    private String fsodLastOrderDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFsodFsohRID() {
        return fsodFsohRID;
    }

    public void setFsodFsohRID(Integer fsodFsohRID) {
        this.fsodFsohRID = fsodFsohRID;
    }

    public Integer getFsodServiceRID() {
        return fsodServiceRID;
    }

    public void setFsodServiceRID(Integer fsodServiceRID) {
        this.fsodServiceRID = fsodServiceRID;
    }

    public String getFsodLastOrderDateTime() {
        return fsodLastOrderDateTime;
    }

    public void setFsodLastOrderDateTime(String fsodLastOrderDateTime) {
        this.fsodLastOrderDateTime = fsodLastOrderDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\fsodRID: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tfsodFsohRID: Integer=");
        sb.append(fsodFsohRID);
        sb.append(";");

        sb.append("\n\tfsodServiceRID: Integer=");
        sb.append(fsodServiceRID);
        sb.append(";");

        sb.append("\n\tfsodLastOrderDateTime: String=");
        sb.append(fsodLastOrderDateTime);
        sb.append(";");
        return sb.toString();
    }
}
