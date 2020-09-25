package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
@Table(name = "s_payer_service_map")
public class PayerServiceMapData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PSM_ID", updatable = false)
    private Integer psmId;

    @Column(name = "PSM_PD_RID")
    private Integer psmPdRid;

    @Column(name = "PSM_SERVICE_GROUP")
    private String psmServiceGroup;
    @Column(name = "PSM_SERVICE_RID")
    private Integer psmServiceRid;

    @Column(name = "PSM_SERVICE_NAME")
    private String psmServiceName;

    @Column(name = "PSM_SERVICE_COST")
    private Float psmServiceCost;
    
    @Column(name = "PSM_IS_ACTIVE")
    private Integer psmIsActive;

    @Column(name = "PSM_DISCOUNT_PERCENT")
    private Float psmDiscountPercent;

    @Column(name = "PSM_DISCOUNT_VAL")
    private Float psmDiscountVal;

    @Column(name = "PSM_IS_INCLUDED")
    private Integer psmIsIncluded;

    @Column(name = "PSM_IS_EXCLUDED")
    private Integer psmIsExcluded;

    @Column(name = "PSM_CREATED_USER_RID")
    private Integer createdUserRid;

    @Column(name = "PSM_CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "PSM_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "PSM_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "PSM_DISCOUNT_PRICE")
    private Float psmDiscountPrice;

    public PayerServiceMapData() {
    }

    public Integer getPsmIsActive() {
        return psmIsActive;
    }

    public void setPsmIsActive(Integer psmIsActive) {
        this.psmIsActive = psmIsActive;
    }

  

   

    public Integer getPsmId() {
        return psmId;
    }

    public void setPsmId(Integer psmId) {
        this.psmId = psmId;
    }

    public Integer getPsmPdRid() {
        return psmPdRid;
    }

    public void setPsmPdRid(Integer psmPdRid) {
        this.psmPdRid = psmPdRid;
    }

    public String getPsmServiceGroup() {
        return psmServiceGroup;
    }

    public void setPsmServiceGroup(String psmServiceGroup) {
        this.psmServiceGroup = psmServiceGroup;
    }

    public Integer getPsmServiceRid() {
        return psmServiceRid;
    }

    public void setPsmServiceRid(Integer psmServiceRid) {
        this.psmServiceRid = psmServiceRid;
    }

    public String getPsmServiceName() {
        return psmServiceName;
    }

    public void setPsmServiceName(String psmServiceName) {
        this.psmServiceName = psmServiceName;
    }

    public Float getPsmServiceCost() {
        return psmServiceCost;
    }

    public void setPsmServiceCost(Float psmServiceCost) {
        this.psmServiceCost = psmServiceCost;
    }

    public Float getPsmDiscountPercent() {
        return psmDiscountPercent;
    }

    public void setPsmDiscountPercent(Float psmDiscountPercent) {
        this.psmDiscountPercent = psmDiscountPercent;
    }

    public Float getPsmDiscountVal() {
        return psmDiscountVal;
    }

    public void setPsmDiscountVal(Float psmDiscountVal) {
        this.psmDiscountVal = psmDiscountVal;
    }

    public Integer getPsmIsIncluded() {
        return psmIsIncluded;
    }

    public void setPsmIsIncluded(Integer psmIsIncluded) {
        this.psmIsIncluded = psmIsIncluded;
    }

    public Integer getPsmIsExcluded() {
        return psmIsExcluded;
    }

    public void setPsmIsExcluded(Integer psmIsExcluded) {
        this.psmIsExcluded = psmIsExcluded;
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

    public Float getPsmDiscountPrice() {
        return psmDiscountPrice;
    }

    public void setPsmDiscountPrice(Float psmDiscountPrice) {
        this.psmDiscountPrice = psmDiscountPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\tpsmId: Integer=");
        sb.append(psmId);
        sb.append(";");

        sb.append("\n\tpsmPdRid: Integer=");
        sb.append(psmPdRid);
        sb.append(";");

        sb.append("\n\tpsmServiceGroup: String=");
        sb.append(psmServiceGroup);
        sb.append(";");

        sb.append("\n\tpsmIsActive: Integer=");
        sb.append(psmIsActive);
        sb.append(";");
        
        sb.append("\n\tpsmServiceRid: Integer=");
        sb.append(psmServiceRid);
        sb.append(";");

        sb.append("\n\tpsmServiceName: String=");
        sb.append(psmServiceName);
        sb.append(";");

        sb.append("\n\tpsmServiceCost: Float=");
        sb.append(psmServiceCost);
        sb.append(";");

        sb.append("\n\tpsmDiscountPercent: Float=");
        sb.append(psmDiscountPercent);
        sb.append(";");

        sb.append("\n\tpsmDiscountVal: Float=");
        sb.append(psmDiscountVal);
        sb.append(";");

        sb.append("\n\tpsmIsIncluded: Integer=");
        sb.append(psmIsIncluded);
        sb.append(";");

        sb.append("\n\tpsmIsExcluded: Integer=");
        sb.append(psmIsExcluded);
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

        return sb.toString();
    }

}
