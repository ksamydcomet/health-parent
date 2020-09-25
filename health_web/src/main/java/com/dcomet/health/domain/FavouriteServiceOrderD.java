package com.dcomet.health.domain;

import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class FavouriteServiceOrderD implements Serializable {

    private Integer id;
    private Integer fsodFsohRID;
    private Integer fsodServiceRID;
//    private String fsodServiceNode;
    private String fsodLastOrderDateTime;

    public FavouriteServiceOrderD() {

    }

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

//    public String getFsodServiceNode() {
//        return fsodServiceNode;
//    }
//
//    public void setFsodServiceNode(String fsodServiceNode) {
//        this.fsodServiceNode = fsodServiceNode;
//    }

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
