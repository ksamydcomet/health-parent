package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev4
 */
public class FavouriteItemOrderDSearchCriteria extends SearchCriteria {

    private Integer id;
    private Integer fiodFiohRID;
    private Integer fiodItemRID;
    private String fiodLastOrderDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFiodFiohRID() {
        return fiodFiohRID;
    }

    public void setFiodFiohRID(Integer fiodFiohRID) {
        this.fiodFiohRID = fiodFiohRID;
    }

    public Integer getFiodItemRID() {
        return fiodItemRID;
    }

    public void setFiodItemRID(Integer fiodItemRID) {
        this.fiodItemRID = fiodItemRID;
    }

    public String getFiodLastOrderDateTime() {
        return fiodLastOrderDateTime;
    }

    public void setFiodLastOrderDateTime(String fiodLastOrderDateTime) {
        this.fiodLastOrderDateTime = fiodLastOrderDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tfiodFiohRID: Integer=");
        sb.append(fiodFiohRID);
        sb.append(";");

        sb.append("\n\tfiodItemRID: Integer=");
        sb.append(fiodItemRID);
        sb.append(";");

        sb.append("\n\tfiodLastOrderDateTime: DateTime=");
        sb.append(fiodLastOrderDateTime);
        sb.append(";");

        return sb.toString();
    }
}
