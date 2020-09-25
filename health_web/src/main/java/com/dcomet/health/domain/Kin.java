package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class Kin extends Base implements Serializable {

    private Integer id;
    private Integer kinPatRid;
    private String kinName;
    private String kinMobileNo;
    private String kinOccupation;
    private String kinRelationship;
    private Integer kinIsActive;

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

    public String getKinName() {
        return kinName;
    }

    public void setKinName(String kinName) {
        this.kinName = kinName;
    }

    public String getKinMobileNo() {
        return kinMobileNo;
    }

    public void setKinMobileNo(String kinMobileNo) {
        this.kinMobileNo = kinMobileNo;
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
