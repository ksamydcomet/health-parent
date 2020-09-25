package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Dev1
 */
public class ApprovalDetails extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appId;
    private Integer appType;
    private Integer appBillRid;
    private String appBillCode;
    private Integer appState;
    private Integer appStatus;
    private Integer appDisRid;
    private Integer appUnitRid;
    private Integer appEntityRid;
    private Integer appCreatedUserRid;
    private Integer appModifiedUserRid;
    private Calendar appCreatedDateTime;
    private Calendar appModifiedDateTime;

    private String discName;
    private Float discPercentage;
    private Float billNetAmount;

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

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public Float getDiscPercentage() {
        return discPercentage;
    }

    public void setDiscPercentage(Float discPercentage) {
        this.discPercentage = discPercentage;
    }

    public Float getBillNetAmount() {
        return billNetAmount;
    }

    public void setBillNetAmount(Float billNetAmount) {
        this.billNetAmount = billNetAmount;
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

}
