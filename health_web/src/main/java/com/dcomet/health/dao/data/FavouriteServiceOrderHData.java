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
@Table(name = "t_fav_service_order_h")
public class FavouriteServiceOrderHData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FSOH_RID", updatable = false)
    private Integer id;

    @Column(name = "FSOH_USER_RID")
    private Integer fsohUserRID;

    @Column(name = "FSOH_NAME")
    private String fsohName;

    @Column(name = "FSOH_ENTITY_RID")
    private Integer fsohEntityRID;

    @Column(name = "CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FSOD_FSOH_RID", referencedColumnName = "FSOH_RID", updatable = false)
    private List<FavouriteServiceOrderDData> favouriteServiceOrderDData;

    public FavouriteServiceOrderHData() {

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

    public Integer getFsohEntityRID() {
        return fsohEntityRID;
    }

    public List<FavouriteServiceOrderDData> getFavouriteServiceOrderDData() {
        return favouriteServiceOrderDData;
    }

    public void setFavouriteServiceOrderDData(List<FavouriteServiceOrderDData> favouriteServiceOrderDData) {
        this.favouriteServiceOrderDData = favouriteServiceOrderDData;
    }

    public void setFsohEntityRID(Integer fsohEntityRID) {
        this.fsohEntityRID = fsohEntityRID;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
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
