package com.dcomet.health.batch.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "s_report_schedule_d")
public class ReportScheduleDData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RSD_RID", updatable = false)
    private Integer id;

    @Column(name = "RSD_RSH_RID", updatable = false, insertable = false)
    private Integer rsdRshRID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RSD_RSH_RID")
    private ReportScheduleHData reportScheduleHData;

    @Column(name = "RSD_REPORT_URL")
    private String rsdReportUrl;

    @Column(name = "RSD_SEQ_NO")
    private Integer rsdSeqNo;

    @Column(name = "RSD_IS_ACTIVE")
    private Integer rsdIsActive;

    public ReportScheduleDData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRsdRshRID() {
        return rsdRshRID;
    }

    public void setRsdRshRID(Integer rsdRshRID) {
        this.rsdRshRID = rsdRshRID;
    }

    public String getRsdReportUrl() {
        return rsdReportUrl;
    }

    public void setRsdReportUrl(String rsdReportUrl) {
        this.rsdReportUrl = rsdReportUrl;
    }

    public Integer getRsdSeqNo() {
        return rsdSeqNo;
    }

    public void setRsdSeqNo(Integer rsdSeqNo) {
        this.rsdSeqNo = rsdSeqNo;
    }

    public Integer getRsdIsActive() {
        return rsdIsActive;
    }

    public void setRsdIsActive(Integer rsdIsActive) {
        this.rsdIsActive = rsdIsActive;
    }

    public ReportScheduleHData getReportScheduleHData() {
        return reportScheduleHData;
    }

    public void setReportScheduleHData(ReportScheduleHData reportScheduleHData) {
        this.reportScheduleHData = reportScheduleHData;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\rsdRshRID: Integer=");
        sb.append(rsdRshRID);
        sb.append(";");

        sb.append("\n\trsdReportUrl: String=");
        sb.append(rsdReportUrl);
        sb.append(";");

        sb.append("\n\rsdSeqNo: Integer=");
        sb.append(rsdSeqNo);
        sb.append(";");

        sb.append("\n\trsdIsActive: Integer=");
        sb.append(rsdIsActive);
        sb.append(";");
        return sb.toString();
    }

}
