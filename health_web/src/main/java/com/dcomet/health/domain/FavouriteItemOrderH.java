package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dev4
 */
public class FavouriteItemOrderH extends Base implements Serializable {

    private Integer id;
    private Integer fiohUserRID;
    private String fiohName;

    private List<FavouriteItemOrderD> favouriteItemOrderD;

    public FavouriteItemOrderH() {

    }

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

    public List<FavouriteItemOrderD> getFavouriteItemOrderD() {
        return favouriteItemOrderD;
    }

    public void setFavouriteItemOrderD(List<FavouriteItemOrderD> favouriteItemOrderD) {
        this.favouriteItemOrderD = favouriteItemOrderD;
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

        return sb.toString();
    }
}
