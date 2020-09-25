package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *          
 * @author Ananth
 */
@Entity
@Table(name = "t_discount_m")
public class DiscountMasterData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIS_RID", updatable = false)
    private Integer id;

    @Column(name = "DIS_CATEGORY")
    private Integer disCategory;

    @Column(name = "DIS_CODE")
    private String disCode;

    @Column(name = "DIS_NAME")
    private String disName;

    @Column(name = "DIS_PERCENTAGE")
    private Float disPercentage;

    @Column(name = "DIS_IS_ACTIVE")
    private Integer disIsActive;

    @Column(name = "DIS_IS_APPROVED")
    private Integer disIsApproved;

    @Column(name = "DIS_FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date disFromDate;

    @Column(name = "DIS_TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date disToDate;

    @Column(name = "DIS_CREATED_USER_RID")
    private Integer createdUserRid;

    @Column(name = "DIS_CREATED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "DIS_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "DIS_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;
    
    @Column(name = "DIS_ENTITY_RID")
    private Integer disEntityRid;
    
    @Column(name = "DIS_UNIT_RID")
    private Integer disUnitRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDisCategory() {
        return disCategory;
    }

    public void setDisCategory(Integer disCategory) {
        this.disCategory = disCategory;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public Float getDisPercentage() {
        return disPercentage;
    }

    public void setDisPercentage(Float disPercentage) {
        this.disPercentage = disPercentage;
    }

    public Integer getDisIsActive() {
        return disIsActive;
    }

    public void setDisIsActive(Integer disIsActive) {
        this.disIsActive = disIsActive;
    }

    public Integer getDisIsApproved() {
        return disIsApproved;
    }

    public void setDisIsApproved(Integer disIsApproved) {
        this.disIsApproved = disIsApproved;
    }

    public Date getDisFromDate() {
        return disFromDate;
    }

    public void setDisFromDate(Date disFromDate) {
        this.disFromDate = disFromDate;
    }

    public Date getDisToDate() {
        return disToDate;
    }

    public void setDisToDate(Date disToDate) {
        this.disToDate = disToDate;
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

    public Integer getDisEntityRid() {
        return disEntityRid;
    }

    public void setDisEntityRid(Integer disEntityRid) {
        this.disEntityRid = disEntityRid;
    }

    public Integer getDisUnitRid() {
        return disUnitRid;
    }

    public void setDisUnitRid(Integer disUnitRid) {
        this.disUnitRid = disUnitRid;
    }
    
    

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tdisCategory: Integer=");
        sb.append(disCategory);
        sb.append(";");
        
        sb.append("\n\tdisEntityRid: Integer=");
        sb.append(disEntityRid);
        sb.append(";");
        
        sb.append("\n\tdisUnitRid: Integer=");
        sb.append(disUnitRid);
        sb.append(";");

        sb.append("\n\tdisCode: String=");
        sb.append(disCode);
        sb.append(";");

        sb.append("\n\tdisName: String=");
        sb.append(disName);
        sb.append(";");

        sb.append("\n\tdisPercentage: Float=");
        sb.append(disPercentage);
        sb.append(";");

        sb.append("\n\tdisIsActive: Integer=");
        sb.append(disIsActive);
        sb.append(";");

        sb.append("\n\tdisIsApproved: Integer=");
        sb.append(disIsApproved);
        sb.append(";");

        sb.append("\n\tdisFromDate: Date=");
        sb.append(disFromDate);
        sb.append(";");

        sb.append("\n\tdisToDate: Date=");
        sb.append(disToDate);
        sb.append(";");

        return sb.toString();
    }

}
