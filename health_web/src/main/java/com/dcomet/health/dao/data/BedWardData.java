package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_bed_ward")

public class BedWardData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BW_RID")
    private Integer bwRid;

    @Column(name = "BW_CODE")
    private String bwCode;

    @Column(name = "BW_NAME")
    private String bwName;

    @Column(name = "BW_TYPE")
    private Integer bwType;

    @Column(name = "BW_CURRENT_TYPE")
    private Integer bwCurrentType;

    @Column(name = "BW_IS_ICU")
    private Integer bwIsIcu;

    @Column(name = "BW_IS_ACTIVE")
    private Integer bwIsActive;

    @Column(name = "BW_UNIT_RID")
    private Integer bwUnitRid;

    @Column(name = "BW_ENTITY_RID")
    private Integer bwEntityRid;

    @Column(name = "BW_CREATED_USER_RID")
    private Integer bwCreatedUserRid;

    @Column(name = "BW_CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar bwCreatedDatetime;

    @Column(name = "BW_MODIFIED_USER_RID")
    private Integer bwModifiedUserRid;

    @Column(name = "BW_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar bwModifiedDatetime;

    public BedWardData() {
    }

    public Integer getBwRid() {
        return bwRid;
    }

    public void setBwRid(Integer bwRid) {
        this.bwRid = bwRid;
    }

    public String getBwCode() {
        return bwCode;
    }

    public void setBwCode(String bwCode) {
        this.bwCode = bwCode;
    }

    public String getBwName() {
        return bwName;
    }

    public void setBwName(String bwName) {
        this.bwName = bwName;
    }

    public Integer getBwType() {
        return bwType;
    }

    public void setBwType(Integer bwType) {
        this.bwType = bwType;
    }

    public Integer getBwCurrentType() {
        return bwCurrentType;
    }

    public void setBwCurrentType(Integer bwCurrentType) {
        this.bwCurrentType = bwCurrentType;
    }

    public Integer getBwIsIcu() {
        return bwIsIcu;
    }

    public void setBwIsIcu(Integer bwIsIcu) {
        this.bwIsIcu = bwIsIcu;
    }

    public Integer getBwIsActive() {
        return bwIsActive;
    }

    public void setBwIsActive(Integer bwIsActive) {
        this.bwIsActive = bwIsActive;
    }

    public Integer getBwUnitRid() {
        return bwUnitRid;
    }

    public void setBwUnitRid(Integer bwUnitRid) {
        this.bwUnitRid = bwUnitRid;
    }

    public Integer getBwEntityRid() {
        return bwEntityRid;
    }

    public void setBwEntityRid(Integer bwEntityRid) {
        this.bwEntityRid = bwEntityRid;
    }

    public Integer getBwCreatedUserRid() {
        return bwCreatedUserRid;
    }

    public void setBwCreatedUserRid(Integer bwCreatedUserRid) {
        this.bwCreatedUserRid = bwCreatedUserRid;
    }

    public Calendar getBwCreatedDatetime() {
        return bwCreatedDatetime;
    }

    public void setBwCreatedDatetime(Calendar bwCreatedDatetime) {
        this.bwCreatedDatetime = bwCreatedDatetime;
    }

    public Integer getBwModifiedUserRid() {
        return bwModifiedUserRid;
    }

    public void setBwModifiedUserRid(Integer bwModifiedUserRid) {
        this.bwModifiedUserRid = bwModifiedUserRid;
    }

    public Calendar getBwModifiedDatetime() {
        return bwModifiedDatetime;
    }

    public void setBwModifiedDatetime(Calendar bwModifiedDatetime) {
        this.bwModifiedDatetime = bwModifiedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbwRid: Integer=");
        sb.append(bwRid);
        sb.append(";");

        sb.append("\n\tbwCode: String=");
        sb.append(bwCode);
        sb.append(";");

        sb.append("\n\tbwName: String=");
        sb.append(bwName);
        sb.append(";");

        sb.append("\n\tbwType: Integer=");
        sb.append(bwType);
        sb.append(";");

        sb.append("\n\tbwCurrentType: Integer=");
        sb.append(bwCurrentType);
        sb.append(";");

        sb.append("\n\tbwIsIcu: Integer=");
        sb.append(bwIsIcu);
        sb.append(";");

        sb.append("\n\tbwIsActive: Integer=");
        sb.append(bwIsActive);
        sb.append(";");

        sb.append("\n\tbwUnitRid: Integer=");
        sb.append(bwUnitRid);
        sb.append(";");

        sb.append("\n\tbwEntityRid: Integer=");
        sb.append(bwEntityRid);
        sb.append(";");

        sb.append("\n\tbwCreatedUserRid: Integer=");
        sb.append(bwCreatedUserRid);
        sb.append(";");

        sb.append("\n\tbwCreatedDatetime: Calendar=");
        sb.append(bwCreatedDatetime);
        sb.append(";");

        sb.append("\n\tbwModifiedUserRid: Integer=");
        sb.append(bwModifiedUserRid);
        sb.append(";");

        sb.append("\n\tbwModifiedDatetime: Calendar=");
        sb.append(bwModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
