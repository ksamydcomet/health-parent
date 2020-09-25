package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_bed_master")
public class BedMasterData implements Serializable {//, Auditable

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BED_RID", updatable = false)
    private Integer bedRid;

    @Column(name = "BED_BGM_RID")
    private Integer bedBgmRid;

    @Column(name = "BED_NAME")
    private String bedName;

    @Column(name = "BED_NO", updatable = false)
    private String bedNo;

    @Column(name = "BED_PREFIX", updatable = false)
    private String bedPrefix;

    @Column(name = "BED_SEQUENCE", updatable = false)
    private Integer bedSequence;

    @Column(name = "BED_NOTES")
    private String bedNotes;

    @Column(name = "BED_STATE")
    private Integer bedState;

    @Column(name = "BED_STATUS")
    private Integer bedStatus;

    @Column(name = "BED_IS_ACTIVE")
    private Integer bedIsActive;

    @Column(name = "BED_ENTITY_RID")
    private Integer bedEntityRid;

    @Column(name = "BED_UNIT_RID")
    private Integer bedUnitRid;

//    @Column(name = "BED_CREATED_USER_RID", updatable = false)
//    private Integer createdUserRid;
//
//    @Column(name = "BED_CREATED1_DATE_TIME", updatable = false)
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Calendar createdDateTime;
//
//    @Column(name = "BED_MODIFIED_USER_RID")
//    private Integer modifiedUserRid;
//
//    @Column(name = "BED_MODIFIED_DATE_TIME")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Calendar modifiedDateTime;
    public BedMasterData() {
    }

    public Integer getBedRid() {
        return bedRid;
    }

    public void setBedRid(Integer bedRid) {
        this.bedRid = bedRid;
    }

    public Integer getBedBgmRid() {
        return bedBgmRid;
    }

    public void setBedBgmRid(Integer bedBgmRid) {
        this.bedBgmRid = bedBgmRid;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedPrefix() {
        return bedPrefix;
    }

    public void setBedPrefix(String bedPrefix) {
        this.bedPrefix = bedPrefix;
    }

    public Integer getBedSequence() {
        return bedSequence;
    }

    public void setBedSequence(Integer bedSequence) {
        this.bedSequence = bedSequence;
    }

    public String getBedNotes() {
        return bedNotes;
    }

    public void setBedNotes(String bedNotes) {
        this.bedNotes = bedNotes;
    }

    public Integer getBedState() {
        return bedState;
    }

    public void setBedState(Integer bedState) {
        this.bedState = bedState;
    }

    public Integer getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(Integer bedStatus) {
        this.bedStatus = bedStatus;
    }

    public Integer getBedIsActive() {
        return bedIsActive;
    }

    public void setBedIsActive(Integer bedIsActive) {
        this.bedIsActive = bedIsActive;
    }

    public Integer getBedEntityRid() {
        return bedEntityRid;
    }

    public void setBedEntityRid(Integer bedEntityRid) {
        this.bedEntityRid = bedEntityRid;
    }

    public Integer getBedUnitRid() {
        return bedUnitRid;
    }

    public void setBedUnitRid(Integer bedUnitRid) {
        this.bedUnitRid = bedUnitRid;
    }

//    @Override
//    public Integer getCreatedUserRid() {
//        return createdUserRid;
//    }
//
//    @Override
//    public void setCreatedUserRid(Integer createdUserRid) {
//        this.createdUserRid = createdUserRid;
//    }
//
//    @Override
//    public Calendar getCreatedDateTime() {
//        return createdDateTime;
//    }
//
//    @Override
//    public void setCreatedDateTime(Calendar createdDateTime) {
//        this.createdDateTime = createdDateTime;
//    }
//
//    @Override
//    public Integer getModifiedUserRid() {
//        return modifiedUserRid;
//    }
//
//    @Override
//    public void setModifiedUserRid(Integer modifiedUserRid) {
//        this.modifiedUserRid = modifiedUserRid;
//    }
//
//    @Override
//    public Calendar getModifiedDateTime() {
//        return modifiedDateTime;
//    }
//
//    @Override
//    public void setModifiedDateTime(Calendar modifiedDateTime) {
//        this.modifiedDateTime = modifiedDateTime;
//    }
    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbedRid: Integer=");
        sb.append(bedRid);
        sb.append(";");

        sb.append("\n\tbedBgmRid: Integer=");
        sb.append(bedBgmRid);
        sb.append(";");

        sb.append("\n\tbedName: String=");
        sb.append(bedName);
        sb.append(";");

        sb.append("\n\tbedNo: String=");
        sb.append(bedNo);
        sb.append(";");

        sb.append("\n\tbedPrefix: String=");
        sb.append(bedPrefix);
        sb.append(";");

        sb.append("\n\tbedSequence: Integer=");
        sb.append(bedSequence);
        sb.append(";");

        sb.append("\n\tbedNotes: String=");
        sb.append(bedNotes);
        sb.append(";");

        sb.append("\n\tbedState: Integer=");
        sb.append(bedState);
        sb.append(";");

        sb.append("\n\tbedStatus: Integer=");
        sb.append(bedStatus);
        sb.append(";");

        sb.append("\n\tbedIsActive: Integer=");
        sb.append(bedIsActive);
        sb.append(";");

        sb.append("\n\tbedEntityRid: Integer=");
        sb.append(bedEntityRid);
        sb.append(";");

        sb.append("\n\tbedUnitRid: Integer=");
        sb.append(bedUnitRid);
        sb.append(";");

//        sb.append("\n\tCreatedUserRid: Integer=");
//        sb.append(createdUserRid);
//        sb.append(";");
//
//        sb.append("\n\tCreatedDateTime: String=");
//        sb.append(createdDateTime);
//        sb.append(";");
//
//        sb.append("\n\tModifiedDateTime: String=");
//        sb.append(modifiedDateTime);
//        sb.append(";");
//
//        sb.append("\n\tadModifiedUserRID: String=");
//        sb.append(modifiedUserRid);
//        sb.append(";");
        return sb.toString();
    }
}
