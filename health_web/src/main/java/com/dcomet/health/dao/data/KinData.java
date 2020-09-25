package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_kindetails")
public class KinData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KIN_RID", updatable = false)
    private Integer id;

    @Column(name = "KIN_PATRID")
    private Integer kinPatRid;

    @Column(name = "KIN_NAME")
    private String kinName;

    @Column(name = "KIN_MOBILENO")
    private String kinMobileNo;

    @Column(name = "KIN_OCCUPATION")
    private String kinOccupation;

    @Column(name = "KIN_RELATIONSHIP")
    private String kinRelationship;

    @Column(name = "KIN_IS_ACTIVE")
    private Integer kinIsActive;

    public KinData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKinPatRid() {
        return kinPatRid;
    }

    public void setKinPatRid(Integer kinPatRid) {
        this.kinPatRid = kinPatRid;
    }

    public String getKinMobileNo() {
        return kinMobileNo;
    }

    public void setKinMobileNo(String kinMobileNo) {
        this.kinMobileNo = kinMobileNo;
    }

    public String getKinName() {
        return kinName;
    }

    public void setKinName(String kinName) {
        this.kinName = kinName;
    }

    public String getKinOccupation() {
        return kinOccupation;
    }

    public void setKinOccupation(String kinOccupation) {
        this.kinOccupation = kinOccupation;
    }

    public String getKinRelationship() {
        return kinRelationship;
    }

    public void setKinRelationship(String kinRelationship) {
        this.kinRelationship = kinRelationship;
    }

    public Integer getKinIsActive() {
        return kinIsActive;
    }

    public void setKinIsActive(Integer kinIsActive) {
        this.kinIsActive = kinIsActive;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tkinPatRid: Integer=");
        sb.append(kinPatRid);
        sb.append(";");

        sb.append("\n\tkinName: String=");
        sb.append(kinName);
        sb.append(";");

        sb.append("\n\tkinMobileNo: String=");
        sb.append(kinMobileNo);
        sb.append(";");

        sb.append("\n\tkinOccupation: String=");
        sb.append(kinOccupation);
        sb.append(";");

        sb.append("\n\tkinRelationship: String=");
        sb.append(kinRelationship);
        sb.append(";");

        sb.append("\n\tkinIsActive: Integer=");
        sb.append(kinIsActive);
        sb.append(";");

        return sb.toString();
    }
}
