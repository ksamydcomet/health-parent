package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_fav_item_order_d")
public class FavouriteItemOrderDData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIOD_RID", updatable = false)
    private Integer id;

    @Column(name = "FIOD_FIOH_RID", insertable = false, updatable = false)
    private Integer fiodFiohRID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIOD_FIOH_RID")
    private FavouriteItemOrderHData favouriteItemOrderHData;

    @Column(name = "FIOD_ITEM_RID", updatable = false)
    private Integer fiodItemRID;

    @Column(name = "FIOD_DRUG_NODE", updatable = false)
    private String fiodDrugNode;

    @Column(name = "FIOD_LAST_ORDER_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fiodLastOrderDateTime;

    public FavouriteItemOrderDData() {

    }

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

    public FavouriteItemOrderHData getFavouriteItemOrderHData() {
        return favouriteItemOrderHData;
    }

    public String getFiodDrugNode() {
        return fiodDrugNode;
    }

    public void setFiodDrugNode(String fiodDrugNode) {
        this.fiodDrugNode = fiodDrugNode;
    }

    public void setFavouriteItemOrderHData(FavouriteItemOrderHData favouriteItemOrderHData) {
        this.favouriteItemOrderHData = favouriteItemOrderHData;
    }

    public Integer getFiodItemRID() {
        return fiodItemRID;
    }

    public void setFiodItemRID(Integer fiodItemRID) {
        this.fiodItemRID = fiodItemRID;
    }

    public Calendar getFiodLastOrderDateTime() {
        return fiodLastOrderDateTime;
    }

    public void setFiodLastOrderDateTime(Calendar fiodLastOrderDateTime) {
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

        sb.append("\n\tfiodLastOrderDateTime: Calendar=");
        sb.append(fiodLastOrderDateTime);
        sb.append(";");

        return sb.toString();
    }
}
