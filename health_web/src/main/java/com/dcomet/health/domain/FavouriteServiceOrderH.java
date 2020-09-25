package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dev4
 */
public class FavouriteServiceOrderH extends Base implements Serializable {

    private Integer id;
    private Integer fsohUserRID;
    private String fsohName;
    private Integer fsohVisitRid;

    private List<FavouriteServiceOrderD> favouriteServiceOrderD;

    public FavouriteServiceOrderH() {

    }

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

    public Integer getFsohVisitRid() {
        return fsohVisitRid;
    }

    public void setFsohVisitRid(Integer fsohVisitRid) {
        this.fsohVisitRid = fsohVisitRid;
    }
 
    public List<FavouriteServiceOrderD> getFavouriteServiceOrderD() {
        return favouriteServiceOrderD;
    }

    public void setFavouriteServiceOrderD(List<FavouriteServiceOrderD> favouriteServiceOrderD) {
        this.favouriteServiceOrderD = favouriteServiceOrderD;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\fsohRID: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\fsohUserRID: Integer=");
        sb.append(fsohUserRID);
        sb.append(";");

        sb.append("\n\tfsohName: Integer=");
        sb.append(fsohName);
        sb.append(";");
        
        sb.append("\n\tfsohVisitRid: Integer=");
        sb.append(fsohVisitRid);
        sb.append(";");

        return sb.toString();

    }
}
