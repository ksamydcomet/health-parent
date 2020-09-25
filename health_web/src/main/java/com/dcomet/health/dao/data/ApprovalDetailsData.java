package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev1
 */
@Entity
@Table(name = "t_approval_details")
@XmlRootElement

public class ApprovalDetailsData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_RID")
    private Integer appId;
    
    @Column(name = "APP_TYPE")
    private Integer appType;
    
    @Column(name = "APP_BILL_RID")
    private Integer appBillRid;
    
    @Size(max = 30)
    @Column(name = "APP_BILL_CODE")
    private String appBillCode;
    
    @Column(name = "APP_STATE")
    private Integer appState;
    
    @Column(name = "APP_STATUS")
    private Integer appStatus;
    
    @Column(name = "APP_DIS_RID")
    private Integer appDisRid;
    
    @Column(name = "APP_UNIT_RID")
    private Integer appUnitRid;
    
    @Column(name = "APP_ENTITY_RID")
    private Integer appEntityRid;
    
    @Column(name = "APP_CREATED_USER_RID")
    private Integer appCreatedUserRid;
    
    @Column(name = "APP_MODIFIED_USER_RID")
    private Integer appModifiedUserRid;
    
    @Column(name = "APP_CREATED_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar appCreatedDateTime;
    
    @Column(name = "APP_MODIFIED_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar appModifiedDateTime;

    public ApprovalDetailsData() {
    }

    public ApprovalDetailsData(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppBillRid() {
        return appBillRid;
    }

    public void setAppBillRid(Integer appBillRid) {
        this.appBillRid = appBillRid;
    }

    public String getAppBillCode() {
        return appBillCode;
    }

    public void setAppBillCode(String appBillCode) {
        this.appBillCode = appBillCode;
    }

    public Integer getAppState() {
        return appState;
    }

    public void setAppState(Integer appState) {
        this.appState = appState;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public Integer getAppDisRid() {
        return appDisRid;
    }

    public void setAppDisRid(Integer appDisRid) {
        this.appDisRid = appDisRid;
    }

    public Integer getAppUnitRid() {
        return appUnitRid;
    }

    public void setAppUnitRid(Integer appUnitRid) {
        this.appUnitRid = appUnitRid;
    }

    public Integer getAppEntityRid() {
        return appEntityRid;
    }

    public void setAppEntityRid(Integer appEntityRid) {
        this.appEntityRid = appEntityRid;
    }

    public Integer getAppCreatedUserRid() {
        return appCreatedUserRid;
    }

    public void setAppCreatedUserRid(Integer appCreatedUserRid) {
        this.appCreatedUserRid = appCreatedUserRid;
    }

    public Integer getAppModifiedUserRid() {
        return appModifiedUserRid;
    }

    public void setAppModifiedUserRid(Integer appModifiedUserRid) {
        this.appModifiedUserRid = appModifiedUserRid;
    }

    public Calendar getAppCreatedDateTime() {
        return appCreatedDateTime;
    }

    public void setAppCreatedDateTime(Calendar appCreatedDateTime) {
        this.appCreatedDateTime = appCreatedDateTime;
    }

    public Calendar getAppModifiedDateTime() {
        return appModifiedDateTime;
    }

    public void setAppModifiedDateTime(Calendar appModifiedDateTime) {
        this.appModifiedDateTime = appModifiedDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tappId: Integer=");
        sb.append(appId);
        sb.append(";");

        sb.append("\n\tappType: Integer=");
        sb.append(appType);
        sb.append(";");

        sb.append("\n\tappBillRid: Integer=");
        sb.append(appBillRid);
        sb.append(";");

        sb.append("\n\tappState: Integer=");
        sb.append(appState);
        sb.append(";");

        sb.append("\n\tappStatus: Integer=");
        sb.append(appStatus);
        sb.append(";");

        sb.append("\n\tappDisRid: Integer=");
        sb.append(appDisRid);
        sb.append(";");

        sb.append("\n\tappUnitRid: Integer=");
        sb.append(appUnitRid);
        sb.append(";");

        sb.append("\n\tappEntityRid: Integer=");
        sb.append(appEntityRid);
        sb.append(";");

        sb.append("\n\tappCreatedUserRid: String=");
        sb.append(appCreatedUserRid);
        sb.append(";");

        sb.append("\n\tappCreatedUserRid: String=");
        sb.append(appCreatedUserRid);
        sb.append(";");

        sb.append("\n\tappModifiedUserRid: String=");
        sb.append(appModifiedUserRid);
        sb.append(";");

        sb.append("\n\tappCreatedDateTime: Calendar=");
        sb.append(appCreatedDateTime);
        sb.append(";");

        sb.append("\n\tappModifiedDateTime: Calendar=");
        sb.append(appModifiedDateTime);
        sb.append(";");

        return sb.toString();
    }

    @Override
    public Calendar getCreatedDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedDateTime(Calendar clndr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCreatedUserRid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedUserRid(Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getModifiedDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModifiedDateTime(Calendar clndr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getModifiedUserRid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModifiedUserRid(Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
