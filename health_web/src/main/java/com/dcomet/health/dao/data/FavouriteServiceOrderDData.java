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
@Table(name = "t_fav_service_order_d")
public class FavouriteServiceOrderDData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FSOD_RID", updatable = false)
    private Integer id;

    @Column(name = "FSOD_FSOH_RID", insertable = false, updatable = false)
    private Integer fsodFsohRID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FSOD_FSOH_RID")
    private FavouriteServiceOrderHData favouriteServiceOrderHData;

    @Column(name = "FSOD_SERVICE_RID")
    private Integer fsodServiceRID;

//    @Column(name = "fsod_service_node")
//    private String fsodServiceNode;

    @Column(name = "FSOD_LAST_ORDER_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fsodLastOrderDateTime;

    public FavouriteServiceOrderDData() {

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

    public FavouriteServiceOrderHData getFavouriteServiceOrderHData() {
        return favouriteServiceOrderHData;
    }

    public void setFavouriteServiceOrderHData(FavouriteServiceOrderHData favouriteServiceOrderHData) {
        this.favouriteServiceOrderHData = favouriteServiceOrderHData;
    }

    public void setFsodServiceRID(Integer fsodServiceRID) {
        this.fsodServiceRID = fsodServiceRID;
    }

    public Calendar getFsodLastOrderDateTime() {
        return fsodLastOrderDateTime;
    }

    public void setFsodLastOrderDateTime(Calendar fsodLastOrderDateTime) {
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

        sb.append("\n\tfsodLastOrderDateTime: Calendar=");
        sb.append(fsodLastOrderDateTime);
        sb.append(";");
        return sb.toString();
    }
}
