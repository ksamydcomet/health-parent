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
 * @author Dev3
 */
@Entity
@Table(name = "t_patient")
public class PatientData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAT_RID", updatable = false)
    private Integer id;

    @Column(name = "PAT_ENT_RID", updatable = false)
    private Integer patEntRid;

    @Column(name = "PAT_MRN_NO", updatable = false)
    private String patMrnNo;

    @Column(name = "PAT_SEQ_NO", updatable = false)
    private Integer patSeqNo;

    @Column(name = "PAT_TITLE")
    private String patTitle;

    @Column(name = "PAT_NAME")
    private String patName;

    @Column(name = "PAT_FULL_NAME")
    private String patFullName;

    @Column(name = "PAT_FAMILY_NAME")
    private String patFamilyName;

    @Column(name = "PAT_GENDER_INDEX")
    private String patGenderIndex;

    @Column(name = "PAT_BLOOD_GROUP_INDEX")
    private String patBloodGroupIndex;

    @Column(name = "PAT_DOB")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date patDob;

    @Column(name = "pat_reg_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date patRegDate;

    @Column(name = "PAT_MARITAL_STATUS")
    private String patMaritalStatus;

    @Column(name = "PAT_ADDRESS")
    private String patAddress;

    @Column(name = "PAT_CITY_INDEX")
    private String patCityIndex;

    @Column(name = "PAT_STATE_INDEX")
    private String patStateIndex;

    @Column(name = "PAT_COUNTRY_INDEX")
    private String patCountryIndex;

    @Column(name = "PAT_PINCODE")
    private Integer patPincode;

    @Column(name = "PAT_PHONE_NO")
    private String patPhoneNo;

    @Column(name = "PAT_EMAIL_ID")
    private String patEmailId;

    @Column(name = "PAT_PHOTO_FILE_PATH")
    private String patPhotoFilePath;

    @Column(name = "PAT_PHOTO_FILE_TYPE")
    private String patPhotoFileType;

    @Column(name = "PAT_VIP_STATUS")
    private String patVipStatus;

    @Column(name = "PAT_PARENT_RID")
    private Integer patParentRid;

    @Column(name = "PAT_GENERATED_DOB")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date patGeneratedDob;

    @Column(name = "PAT_ROW_INVALIDATED")
    private Integer patRowInvalidated;

    @Column(name = "PAT_ROW_INVALIDATED_DATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar patRowInvalidatedDateTime;

    @Column(name = "PAT_STATE")
    private Integer patState;

    @Column(name = "PAT_STATUS")
    private Integer patStatus;

    @Column(name = "PAT_CREATED_DATE_TIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "PAT_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "PAT_MODIFIED_DATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @Column(name = "PAT_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    public PatientData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatEntRid() {
        return patEntRid;
    }

    public Date getPatRegDate() {
        return patRegDate;
    }

    public void setPatRegDate(Date patRegDate) {
        this.patRegDate = patRegDate;
    }

    public void setPatEntRid(Integer patEntRid) {
        this.patEntRid = patEntRid;
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

    public Date getPatDob() {
        return patDob;
    }

    public void setPatDob(Date patDob) {
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

    public String getPatPhoneNo() {
        return patPhoneNo;
    }

    public void setPatPhoneNo(String patPhoneNo) {
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

    public Date getPatGeneratedDob() {
        return patGeneratedDob;
    }

    public void setPatGeneratedDob(Date patGeneratedDob) {
        this.patGeneratedDob = patGeneratedDob;
    }

    public Integer getPatRowInvalidated() {
        return patRowInvalidated;
    }

    public void setPatRowInvalidated(Integer patRowInvalidated) {
        this.patRowInvalidated = patRowInvalidated;
    }

    public Calendar getPatRowInvalidatedDateTime() {
        return patRowInvalidatedDateTime;
    }

    public void setPatRowInvalidatedDateTime(Calendar patRowInvalidatedDateTime) {
        this.patRowInvalidatedDateTime = patRowInvalidatedDateTime;
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
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tpatEntRid: Integer=");
        sb.append(patEntRid);
        sb.append(";");

        sb.append("\n\tpatMrnNo: String=");
        sb.append(patMrnNo);
        sb.append(";");

        sb.append("\n\tpatSeqNo: String=");
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

        sb.append("\n\tpatRowInvalidated: String=");
        sb.append(patRowInvalidated);
        sb.append(";");

        sb.append("\n\tpatRowInvalidatedDateTime: Calendar=");
        sb.append(patRowInvalidatedDateTime);
        sb.append(";");

        sb.append("\n\tpatState: Integer=");
        sb.append(patState);
        sb.append(";");

        sb.append("\n\tpatStatus: Integer=");
        sb.append(patStatus);
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
