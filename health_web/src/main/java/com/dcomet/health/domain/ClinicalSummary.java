package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ClinicalSummary extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer csPatRid;
    private Integer csVisitRid;
    private String csVisitDate;
    private String csNode;
    private Integer csSendEmail;
    private Integer csEntityRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsPatRid() {
        return csPatRid;
    }

    public void setCsPatRid(Integer csPatRid) {
        this.csPatRid = csPatRid;
    }

    public Integer getCsVisitRid() {
        return csVisitRid;
    }

    public void setCsVisitRid(Integer csVisitRid) {
        this.csVisitRid = csVisitRid;
    }

    public String getCsVisitDate() {
        return csVisitDate;
    }

    public void setCsVisitDate(String csVisitDate) {
        this.csVisitDate = csVisitDate;
    }

    public String getCsNode() {
        return csNode;
    }

    public void setCsNode(String csNode) {
        this.csNode = csNode;
    }

    public Integer getCsSendEmail() {
        return csSendEmail;
    }

    public void setCsSendEmail(Integer csSendEmail) {
        this.csSendEmail = csSendEmail;
    }

    public Integer getCsEntityRid() {
        return csEntityRid;
    }

    public void setCsEntityRid(Integer csEntityRid) {
        this.csEntityRid = csEntityRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tcsPatRid: Integer=");
        sb.append(csPatRid);
        sb.append(";");
        sb.append("\n\tcsVisitRid: Integer=");
        sb.append(csVisitRid);
        sb.append(";");

        sb.append("\n\tcsVisitDate: Date=");
        sb.append(csVisitDate);
        sb.append(";");

        sb.append("\n\tcsNode: String=");
        sb.append(csNode);
        sb.append(";");

        sb.append("\n\tcsSendEmail: Integer=");
        sb.append(csSendEmail);
        sb.append(";");

        return sb.toString();
    }
}
