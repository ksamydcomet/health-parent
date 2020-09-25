
package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev7
 */
@Entity
@Table(name = "t_bed_charge_definition")

public class BedChargeDefinitionData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BCD_RID", updatable = false)
    private Integer bcdRid;

    @Column(name = "BCD_BED_TYPE_RID")
    private Integer bcdBedTypeRid;

    @Column(name = "BCD_IS_ICU")
    private Integer bcdIsIcu;

    @Column(name = "BCD_MIN_HOURS")
    private Integer bcdMinHours;

    @Column(name = "BCD_GT_MIN_HOURS_EXPR")
    private String bcdGtMinHoursExpr;

    @Column(name = "BCD_LT_MIN_HOURS_EXPR")
    private String bcdLtMinHoursExpr;

    @Column(name = "BCD_ENTITY_RID")
    private Integer bcdEntityRid;

    public BedChargeDefinitionData() {
    }

    public Integer getBcdRid() {
        return bcdRid;
    }

    public void setBcdRid(Integer bcdRid) {
        this.bcdRid = bcdRid;
    }

    public Integer getBcdBedTypeRid() {
        return bcdBedTypeRid;
    }

    public void setBcdBedTypeRid(Integer bcdBedTypeRid) {
        this.bcdBedTypeRid = bcdBedTypeRid;
    }

    public Integer getBcdIsIcu() {
        return bcdIsIcu;
    }

    public void setBcdIsIcu(Integer bcdIsIcu) {
        this.bcdIsIcu = bcdIsIcu;
    }

    public Integer getBcdMinHours() {
        return bcdMinHours;
    }

    public void setBcdMinHours(Integer bcdMinHours) {
        this.bcdMinHours = bcdMinHours;
    }

    public String getBcdGtMinHoursExpr() {
        return bcdGtMinHoursExpr;
    }

    public void setBcdGtMinHoursExpr(String bcdGtMinHoursExpr) {
        this.bcdGtMinHoursExpr = bcdGtMinHoursExpr;
    }

    public String getBcdLtMinHoursExpr() {
        return bcdLtMinHoursExpr;
    }

    public void setBcdLtMinHoursExpr(String bcdLtMinHoursExpr) {
        this.bcdLtMinHoursExpr = bcdLtMinHoursExpr;
    }

    public Integer getBcdEntityRid() {
        return bcdEntityRid;
    }

    public void setBcdEntityRid(Integer bcdEntityRid) {
        this.bcdEntityRid = bcdEntityRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tbcdRid: Integer=");
        sb.append(bcdRid);
        sb.append(";");

        sb.append("\n\tbcdBedTypeRid: Integer=");
        sb.append(bcdBedTypeRid);
        sb.append(";");

        sb.append("\n\tbcdIsIcu: Integer=");
        sb.append(bcdIsIcu);
        sb.append(";");

        sb.append("\n\tbcdMinHours: Integer=");
        sb.append(bcdMinHours);
        sb.append(";");

        sb.append("\n\tbcdGtMinHoursExpr: String=");
        sb.append(bcdGtMinHoursExpr);
        sb.append(";");

        sb.append("\n\tbcdLtMinHoursExpr: String=");
        sb.append(bcdLtMinHoursExpr);
        sb.append(";");

        sb.append("\n\tbcdEntityRid: Integer=");
        sb.append(bcdEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
