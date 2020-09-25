package com.dcomet.health.batch.dao.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "s_report_schedule_h")
public class ReportScheduleHData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RSH_RID", updatable = false)
    private Integer id;

    @Column(name = "RSH_DESC", updatable = false)
    private String rshDesc;

    @Column(name = "RSH_ENTITY_RID", updatable = false)
    private Integer rshEntityRID;

    @Column(name = "RSH_FREQUENCY", updatable = false)
    private String rshFrequency;

    @Column(name = "RSH_START_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date rshStartDateTime;

    @Column(name = "RSH_SUBJECT", updatable = false)
    private String rshSubject;

    @Column(name = "RSH_OUTPUT_MODE", updatable = false)
    private Integer rshOutputMode;

    @Column(name = "RSH_OUTPUT_COMMUNICATION", updatable = false)
    private String rshOutputCommunication;

    @Column(name = "RSH_OUTPUT_FILNAME", updatable = false)
    private String rshOutputFilname;

    @Column(name = "RSH_OUTPUT_FILE_EXTN", updatable = false)
    private String rshOutputFileExtn;

    @Column(name = "RSH_OUTPUT_FILEPATH", updatable = false)
    private String rshOutputFilepath;

    @Column(name = "RSH_LAST_EXE_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date rshLastExeDateTime;

    @Column(name = "RSH_NEXT_SCH_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date rshNextSchDateTime;

    @Column(name = "RSH_USER_ID", updatable = false)
    private String rshUserID;

    @Column(name = "RSH_USER_PASSWORD", updatable = false)
    private String rshUserPassword;

    @Column(name = "RSH_REPORT_HEADER", updatable = false)
    private String rshReportHeader;

    @Column(name = "RSH_HTML_HEADER", updatable = false)
    private String rshHtmlHeader;

    @Column(name = "RSH_HTML_FOOTER", updatable = false)
    private String rshHtmlFooter;

    @Column(name = "RSH_IS_ACTIVE", updatable = false)
    private Integer rshIsActive;

    @Column(name = "RSH_IS_ATTACHED", updatable = false)
    private Integer rshIsAttached;

    @Column(name = "RSH_MAIL_IDS", updatable = false)
    private String rshMailIds;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RSD_RSH_RID", referencedColumnName = "RSH_RID", updatable = false)
    private List<ReportScheduleDData> reportScheduleDData;

    public ReportScheduleHData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRshDesc() {
        return rshDesc;
    }

    public void setRshDesc(String rshDesc) {
        this.rshDesc = rshDesc;
    }

    public Integer getRshEntityRID() {
        return rshEntityRID;
    }

    public void setRshEntityRID(Integer rshEntityRID) {
        this.rshEntityRID = rshEntityRID;
    }

    public String getRshFrequency() {
        return rshFrequency;
    }

    public void setRshFrequency(String rshFrequency) {
        this.rshFrequency = rshFrequency;
    }

    public Date getRshStartDateTime() {
        return rshStartDateTime;
    }

    public void setRshStartDateTime(Date rshStartDateTime) {
        this.rshStartDateTime = rshStartDateTime;
    }

    public String getRshSubject() {
        return rshSubject;
    }

    public void setRshSubject(String rshSubject) {
        this.rshSubject = rshSubject;
    }

    public Integer getRshOutputMode() {
        return rshOutputMode;
    }

    public void setRshOutputMode(Integer rshOutputMode) {
        this.rshOutputMode = rshOutputMode;
    }

    public String getRshOutputCommunication() {
        return rshOutputCommunication;
    }

    public void setRshOutputCommunication(String rshOutputCommunication) {
        this.rshOutputCommunication = rshOutputCommunication;
    }

    public String getRshOutputFilname() {
        return rshOutputFilname;
    }

    public void setRshOutputFilname(String rshOutputFilname) {
        this.rshOutputFilname = rshOutputFilname;
    }

    public String getRshOutputFileExtn() {
        return rshOutputFileExtn;
    }

    public void setRshOutputFileExtn(String rshOutputFileExtn) {
        this.rshOutputFileExtn = rshOutputFileExtn;
    }

    public String getRshOutputFilepath() {
        return rshOutputFilepath;
    }

    public void setRshOutputFilepath(String rshOutputFilepath) {
        this.rshOutputFilepath = rshOutputFilepath;
    }

    public Date getRshLastExeDateTime() {
        return rshLastExeDateTime;
    }

    public void setRshLastExeDateTime(Date rshLastExeDateTime) {
        this.rshLastExeDateTime = rshLastExeDateTime;
    }

    public Date getRshNextSchDateTime() {
        return rshNextSchDateTime;
    }

    public void setRshNextSchDateTime(Date rshNextSchDateTime) {
        this.rshNextSchDateTime = rshNextSchDateTime;
    }

    public String getRshUserID() {
        return rshUserID;
    }

    public void setRshUserID(String rshUserID) {
        this.rshUserID = rshUserID;
    }

    public String getRshUserPassword() {
        return rshUserPassword;
    }

    public void setRshUserPassword(String rshUserPassword) {
        this.rshUserPassword = rshUserPassword;
    }

    public String getRshReportHeader() {
        return rshReportHeader;
    }

    public void setRshReportHeader(String rshReportHeader) {
        this.rshReportHeader = rshReportHeader;
    }

    public String getRshHtmlHeader() {
        return rshHtmlHeader;
    }

    public void setRshHtmlHeader(String rshHtmlHeader) {
        this.rshHtmlHeader = rshHtmlHeader;
    }

    public String getRshHtmlFooter() {
        return rshHtmlFooter;
    }

    public void setRshHtmlFooter(String rshHtmlFooter) {
        this.rshHtmlFooter = rshHtmlFooter;
    }

    public Integer getRshIsActive() {
        return rshIsActive;
    }

    public void setRshIsActive(Integer rshIsActive) {
        this.rshIsActive = rshIsActive;
    }

    public Integer getRshIsAttached() {
        return rshIsAttached;
    }

    public void setRshIsAttached(Integer rshIsAttached) {
        this.rshIsAttached = rshIsAttached;
    }

    public String getRshMailIds() {
        return rshMailIds;
    }

    public void setRshMailIds(String rshMailIds) {
        this.rshMailIds = rshMailIds;
    }

    public List<ReportScheduleDData> getReportScheduleDData() {
        return reportScheduleDData;
    }

    public void setReportScheduleDData(List<ReportScheduleDData> reportScheduleDData) {
        this.reportScheduleDData = reportScheduleDData;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\trshDesc: String=");
        sb.append(rshDesc);
        sb.append(";");

        sb.append("\n\trshEntityRID: Integer=");
        sb.append(rshEntityRID);
        sb.append(";");

        sb.append("\n\trshFrequency: String=");
        sb.append(rshFrequency);
        sb.append(";");

        sb.append("\n\trshStartDateTime: Calendar=");
        sb.append(rshStartDateTime);
        sb.append(";");

        sb.append("\n\rshSubject: String=");
        sb.append(rshSubject);
        sb.append(";");

        sb.append("\n\trshOutputMode: Integer=");
        sb.append(rshOutputMode);
        sb.append(";");

        sb.append("\n\trshOutputCommunication: String=");
        sb.append(rshOutputCommunication);
        sb.append(";");

        sb.append("\n\trshOutputFilname: String=");
        sb.append(rshOutputFilname);
        sb.append(";");

        sb.append("\n\trshOutputFileExtn: String=");
        sb.append(rshOutputFileExtn);
        sb.append(";");

        sb.append("\n\trshOutputFilepath: Integer=");
        sb.append(rshOutputFilepath);
        sb.append(";");

        sb.append("\n\trshLastExeDateTime: Calendar=");
        sb.append(rshLastExeDateTime);
        sb.append(";");

        sb.append("\n\trshNextSchDateTime: Calendar=");
        sb.append(rshNextSchDateTime);
        sb.append(";");

        sb.append("\n\trshUserID: String=");
        sb.append(rshUserID);
        sb.append(";");

        sb.append("\n\trshUserPassword: String=");
        sb.append(rshUserPassword);
        sb.append(";");

        sb.append("\n\trshReportHeader: String=");
        sb.append(rshReportHeader);
        sb.append(";");

        sb.append("\n\trshHtmlHeader: String=");
        sb.append(rshHtmlHeader);
        sb.append(";");

        sb.append("\n\trshHtmlFooter: String=");
        sb.append(rshHtmlFooter);
        sb.append(";");

        sb.append("\n\rshIsActive: Integer=");
        sb.append(rshIsActive);
        sb.append(";");

        sb.append("\n\trshIsAttached: Integer=");
        sb.append(rshIsAttached);
        sb.append(";");

        sb.append("\n\trshMailIds: String=");
        sb.append(rshMailIds);
        sb.append(";");

        return sb.toString();
    }

}
