package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
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
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_discount_service_map")
public class DiscountServiceMapData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DSM_RID", updatable = false)
    private Integer dsmRid;

    @Column(name = "DSM_SERVICE_RID")
    private Integer dsmServiceRid;

    @Column(name = "DSM_DIS_RID")
    private Integer dsmDisRid;

    @Column(name = "DSM_SERVICE_NAME")
    private String dsmServiceName;

    @Column(name = "DSM_DIS_PERCENTAGE")
    private Float dsmDisPercentage;

    @Column(name = "DSM_DIS_AMOUNT")
    private Float dsmDisAmount;

    @Column(name = "DSM_CREATED_USER_RID")
    private Integer createdUserRid;

    @Column(name = "DSM_CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "DSM_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "DSM_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "DSM_SERVICE_PRICE")
    private Float dsmServicePrice;

    @Column(name = "DSM_SERVICE_DISCOUNT_PRICE")
    private Double dsmServiceDiscountPrice;

    public DiscountServiceMapData() {
    }

    public Integer getDsmRid() {
        return dsmRid;
    }

    public void setDsmRid(Integer dsmRid) {
        this.dsmRid = dsmRid;
    }

    public Integer getDsmServiceRid() {
        return dsmServiceRid;
    }

    public void setDsmServiceRid(Integer dsmServiceRid) {
        this.dsmServiceRid = dsmServiceRid;
    }

    public Integer getDsmDisRid() {
        return dsmDisRid;
    }

    public void setDsmDisRid(Integer dsmDisRid) {
        this.dsmDisRid = dsmDisRid;
    }

    public String getDsmServiceName() {
        return dsmServiceName;
    }

    public void setDsmServiceName(String dsmServiceName) {
        this.dsmServiceName = dsmServiceName;
    }

    public Float getDsmDisPercentage() {
        return dsmDisPercentage;
    }

    public void setDsmDisPercentage(Float dsmDisPercentage) {
        this.dsmDisPercentage = dsmDisPercentage;
    }

    public Float getDsmDisAmount() {
        return dsmDisAmount;
    }

    public void setDsmDisAmount(Float dsmDisAmount) {
        this.dsmDisAmount = dsmDisAmount;
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
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
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
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Float getDsmServicePrice() {
        return dsmServicePrice;
    }

    public void setDsmServicePrice(Float dsmServicePrice) {
        this.dsmServicePrice = dsmServicePrice;
    }

    public Double getDsmServiceDiscountPrice() {
        return dsmServiceDiscountPrice;
    }

    public void setDsmServiceDiscountPrice(Double dsmServiceDiscountPrice) {
        this.dsmServiceDiscountPrice = dsmServiceDiscountPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tdsmRid: Integer=");
        sb.append(dsmRid);
        sb.append(";");

        sb.append("\n\tdsmServiceRid: Integer=");
        sb.append(dsmServiceRid);
        sb.append(";");

        sb.append("\n\tdsmDisRid: Integer=");
        sb.append(dsmDisRid);
        sb.append(";");

        sb.append("\n\tdsmServiceName: String=");
        sb.append(dsmServiceName);
        sb.append(";");

        sb.append("\n\tdsmDisPercentage: Float=");
        sb.append(dsmDisPercentage);
        sb.append(";");

        sb.append("\n\tdsmDisAmount: Float=");
        sb.append(dsmDisAmount);
        sb.append(";");

        sb.append("\n\tcreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tcreatedDateTime: Calendar=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tmodifiedUserRid: Integer=");
        sb.append(modifiedUserRid);
        sb.append(";");

        sb.append("\n\tmodifiedDateTime: Calendar=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tdsmServicePrice: Double=");
        sb.append(dsmServicePrice);
        sb.append(";");

        sb.append("\n\tdsmServiceDiscountPrice: Double=");
        sb.append(dsmServiceDiscountPrice);
        sb.append(";");

        return sb.toString();
    }

}
