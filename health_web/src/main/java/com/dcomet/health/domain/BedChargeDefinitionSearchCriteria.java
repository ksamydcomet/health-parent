
package com.dcomet.health.domain;

public class BedChargeDefinitionSearchCriteria {
    
    private Integer bcdRid;
    private Integer bcdBedTypeRid;
    private Integer bcdIsIcu;
    private Integer bcdMinHours;
    private String bcdGtMinHoursExpr;
    private String bcdLtMinHoursExpr;
    private Integer bcdEntityRid;

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
