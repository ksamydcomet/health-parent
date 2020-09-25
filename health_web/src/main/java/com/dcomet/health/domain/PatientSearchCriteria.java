package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.Calendar;

/**
 *
 * @author Dev3
 */
public class PatientSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer patEntRID;
    private String patMrnNo;
    private Integer patSeqNo;
    private String patTitle;
    private String patName;
    private String patFullName;
    private String patFamilyName;
    private String patGenderIndex;
    private String patRegDate;
    private String patBloodGroupIndex;
    private String patDob;
    private String patMaritalStatus;
    private String patAddress;
    private String patCityIndex;
    private String patStateIndex;
    private String patCountryIndex;
    private Integer patPincode;
    private Integer patPhoneNo;
    private String patEmailId;
    private String patPhotoFilePath;
    private String patPhotoFileType;
    private String patVipStatus;
    private Integer patParentRid;
    private String patGeneratedDob;
    private String createdDateTime;
    private Integer patCreatedUserRid;
    private String patModifiedDateTime;
    private Integer patModifiedUserRid;
    private Integer patRowInvalidated;
    private String patRowInvalidatedDateTime;
    private Integer patState;
    private Integer patStatus;
    private Calendar patFromDate;
    private Calendar patToDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatEntRID() {
        return patEntRID;
    }

    public void setPatEntRID(Integer patEntRID) {
        this.patEntRID = patEntRID;
    }

    public String getPatMrnNo() {
        return patMrnNo;
    }

    public void setPatMrnNo(String patMrnNo) {
        this.patMrnNo = patMrnNo;
    }

    public Integer getPatSeqNo() {
        return patSeqNo;
    }

    public void setPatSeqNo(Integer patSeqNo) {
        this.patSeqNo = patSeqNo;
    }

    public String getPatRegDate() {
        return patRegDate;
    }

    public void setPatRegDate(String patRegDate) {
        this.patRegDate = patRegDate;
    }

    public String getPatTitle() {
        return patTitle;
    }

    public void setPatTitle(String patTitle) {
        this.patTitle = patTitle;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatFullName() {
        return patFullName;
    }

    public void setPatFullName(String patFullName) {
        this.patFullName = patFullName;
    }

    public String getPatFamilyName() {
        return patFamilyName;
    }

    public void setPatFamilyName(String patFamilyName) {
        this.patFamilyName = patFamilyName;
    }

    public String getPatGenderIndex() {
        return patGenderIndex;
    }

    public void setPatGenderIndex(String patGenderIndex) {
        this.patGenderIndex = patGenderIndex;
    }

    public String getPatBloodGroupIndex() {
        return patBloodGroupIndex;
    }

    public void setPatBloodGroupIndex(String patBloodGroupIndex) {
        this.patBloodGroupIndex = patBloodGroupIndex;
    }

    public String getPatDob() {
        return patDob;
    }

    public void setPatDob(String patDob) {
        this.patDob = patDob;
    }

    public String getPatMaritalStatus() {
        return patMaritalStatus;
    }

    public void setPatMaritalStatus(String patMaritalStatus) {
        this.patMaritalStatus = patMaritalStatus;
    }

    public String getPatAddress() {
        return patAddress;
    }

    public void setPatAddress(String patAddress) {
        this.patAddress = patAddress;
    }

    public String getPatCityIndex() {
        return patCityIndex;
    }

    public void setPatCityIndex(String patCityIndex) {
        this.patCityIndex = patCityIndex;
    }

    public String getPatStateIndex() {
        return patStateIndex;
    }

    public void setPatStateIndex(String patStateIndex) {
        this.patStateIndex = patStateIndex;
    }

    public String getPatCountryIndex() {
        return patCountryIndex;
    }

    public void setPatCountryIndex(String patCountryIndex) {
        this.patCountryIndex = patCountryIndex;
    }

    public Integer getPatPincode() {
        return patPincode;
    }

    public void setPatPincode(Integer patPincode) {
        this.patPincode = patPincode;
    }

    public Integer getPatPhoneNo() {
        return patPhoneNo;
    }

    public void setPatPhoneNo(Integer patPhoneNo) {
        this.patPhoneNo = patPhoneNo;
    }

    public String getPatEmailId() {
        return patEmailId;
    }

    public void setPatEmailId(String patEmailId) {
        this.patEmailId = patEmailId;
    }

    public String getPatPhotoFilePath() {
        return patPhotoFilePath;
    }

    public void setPatPhotoFilePath(String patPhotoFilePath) {
        this.patPhotoFilePath = patPhotoFilePath;
    }

    public String getPatPhotoFileType() {
        return patPhotoFileType;
    }

    public void setPatPhotoFileType(String patPhotoFileType) {
        this.patPhotoFileType = patPhotoFileType;
    }

    public String getPatVipStatus() {
        return patVipStatus;
    }

    public void setPatVipStatus(String patVipStatus) {
        this.patVipStatus = patVipStatus;
    }

    public Integer getPatParentRid() {
        return patParentRid;
    }

    public void setPatParentRid(Integer patParentRid) {
        this.patParentRid = patParentRid;
    }

    public String getPatGeneratedDob() {
        return patGeneratedDob;
    }

    public void setPatGeneratedDob(String patGeneratedDob) {
        this.patGeneratedDob = patGeneratedDob;
    }

    public Integer getPatCreatedUserRid() {
        return patCreatedUserRid;
    }

    public void setPatCreatedUserRid(Integer patCreatedUserRid) {
        this.patCreatedUserRid = patCreatedUserRid;
    }

    public String getPatModifiedDateTime() {
        return patModifiedDateTime;
    }

    public void setPatModifiedDateTime(String patModifiedDateTime) {
        this.patModifiedDateTime = patModifiedDateTime;
    }

    public Integer getPatModifiedUserRid() {
        return patModifiedUserRid;
    }

    public void setPatModifiedUserRid(Integer patModifiedUserRid) {
        this.patModifiedUserRid = patModifiedUserRid;
    }

    public Integer getPatRowInvalidated() {
        return patRowInvalidated;
    }

    public void setPatRowInvalidated(Integer patRowInvalidated) {
        this.patRowInvalidated = patRowInvalidated;
    }

    public String getPatRowInvalidatedDateTime() {
        return patRowInvalidatedDateTime;
    }

    public void setPatRowInvalidatedDateTime(String patRowInvalidatedDateTime) {
        this.patRowInvalidatedDateTime = patRowInvalidatedDateTime;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getPatState() {
        return patState;
    }

    public void setPatState(Integer patState) {
        this.patState = patState;
    }

    public Integer getPatStatus() {
        return patStatus;
    }

    public void setPatStatus(Integer patStatus) {
        this.patStatus = patStatus;
    }
    
    public Calendar getPatFromDate() {
        return patFromDate;
    }
    
    public void setPatFromDate(Calendar patFromDate) {
        this.patFromDate = patFromDate;
    }

    public Calendar getPatToDate() {
        return patToDate;
    }

    public void setPatToDate(Calendar patToDate) {
        this.patToDate = patToDate;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tpatEntRID: Integer=");
        sb.append(patEntRID);
        sb.append(";");

        sb.append("\n\tpatMrnNo: String=");
        sb.append(patMrnNo);
        sb.append(";");

        sb.append("\n\tpatSeqNo: Integer=");
        sb.append(patSeqNo);
        sb.append(";");

        sb.append("\n\tpatTitle: String=");
        sb.append(patTitle);
        sb.append(";");

        sb.append("\n\tpatName: String=");
        sb.append(patName);
        sb.append(";");

        sb.append("\n\tpatFullName: String=");
        sb.append(patFullName);
        sb.append(";");

        sb.append("\n\tpatFamilyName: String=");
        sb.append(patFamilyName);
        sb.append(";");

        sb.append("\n\tpatGenderIndex: String=");
        sb.append(patGenderIndex);
        sb.append(";");

        sb.append("\n\tpatBloodGroupIndex: String=");
        sb.append(patBloodGroupIndex);
        sb.append(";");

        sb.append("\n\tpatDob: String=");
        sb.append(patDob);
        sb.append(";");

        sb.append("\n\tpatMaritalStatus: String=");
        sb.append(patMaritalStatus);
        sb.append(";");

        sb.append("\n\tpatAddress: String=");
        sb.append(patAddress);
        sb.append(";");

        sb.append("\n\tpatCityIndex: String=");
        sb.append(patCityIndex);
        sb.append(";");

        sb.append("\n\tpatStateIndex: String=");
        sb.append(patStateIndex);
        sb.append(";");

        sb.append("\n\tpatCountryIndex: String=");
        sb.append(patCountryIndex);
        sb.append(";");

        sb.append("\n\tpatPincode: Integer=");
        sb.append(patPincode);
        sb.append(";");

        sb.append("\n\tpatPhoneNo: Integer=");
        sb.append(patPhoneNo);
        sb.append(";");

        sb.append("\n\tpatEmailId: String=");
        sb.append(patEmailId);
        sb.append(";");

        sb.append("\n\tpatPhotoFilePath: String=");
        sb.append(patPhotoFilePath);
        sb.append(";");

        sb.append("\n\tpatPhotoFileType: String=");
        sb.append(patPhotoFileType);
        sb.append(";");

        sb.append("\n\tpatVipStatus: String=");
        sb.append(patVipStatus);
        sb.append(";");

        sb.append("\n\tpatParentRid: Integer=");
        sb.append(patParentRid);
        sb.append(";");

        sb.append("\n\tpatGeneratedDob: String=");
        sb.append(patGeneratedDob);
        sb.append(";");

        sb.append("\n\tpatPhoneNo: String=");
        sb.append(patPhoneNo);
        sb.append(";");
        
        sb.append("\n\tpatCreatedUserRid: Integer=");
        sb.append(patCreatedUserRid);
        sb.append(";");

        sb.append("\n\tpatModifiedDateTime: String=");
        sb.append(patModifiedDateTime);
        sb.append(";");

        sb.append("\n\tpatModifiedUserRid: Integer=");
        sb.append(patModifiedUserRid);
        sb.append(";");

        sb.append("\n\tpatRowInvalidated: Integer=");
        sb.append(patRowInvalidated);
        sb.append(";");

        sb.append("\n\tpatRowInvalidatedDateTime: String=");
        sb.append(patRowInvalidatedDateTime);
        sb.append(";");

        sb.append("\n\tpatState: Integer=");
        sb.append(patState);
        sb.append(";");

        sb.append("\n\tpatStatus: Integer=");
        sb.append(patStatus);
        sb.append(";");
        
        return sb.toString();

    }

}
