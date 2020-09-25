package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_fav_item_order_h")
public class FavouriteItemOrderHData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIOH_RID", updatable = false)
    private Integer id;

    @Column(name = "FIOH_USER_RID", updatable = false)
    private Integer fiohUserRID;

    @Column(name = "FIOH_NAME")
    private String fiohName;

    @Column(name = "FIOH_ENTITY_RID", updatable = false)
    private Integer fiohEntityRID;

    @Column(name = "CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FIOD_FIOH_RID", referencedColumnName = "FIOH_RID", updatable = false)
    private List<FavouriteItemOrderDData> favouriteItemOrderDData;

    public FavouriteItemOrderHData() {

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

    public Integer getFiohEntityRID() {
        return fiohEntityRID;
    }

    public void setFiohEntityRID(Integer fiohEntityRID) {
        this.fiohEntityRID = fiohEntityRID;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    public List<FavouriteItemOrderDData> getFavouriteItemOrderDData() {
        return favouriteItemOrderDData;
    }

    public void setFavouriteItemOrderDData(List<FavouriteItemOrderDData> favouriteItemOrderDData) {
        this.favouriteItemOrderDData = favouriteItemOrderDData;
    }

    @Override
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
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

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        return sb.toString();
    }
}
