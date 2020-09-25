package com.dcomet.health.batch.dao.data;

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
 * @author Dev4
 */
@Entity
@Table(name = "t_mail_queue")
public class MailQueueData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MQ_RID", updatable = false)
    private Integer id;

    @Column(name = "MQ_SUBJECT")
    private String mqSubject;

    @Column(name = "MQ_FROM")
    private String mqFrom;

    @Column(name = "MQ_TO")
    private String mqTo;

    @Column(name = "MQ_BODY")
    private String mqBody;

    @Column(name = "MQ_SENT")
    private Integer mqSent;

    @Column(name = "MQ_TX_ERRORS")
    private Integer mqTxErrors;

    @Column(name = "MQ_POST_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mqPostDate;

    @Column(name = "MQ_POST_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar mqPostTime;

    @Column(name = "MQ_SENT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mqSentDate;

    @Column(name = "MQ_SENT_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar mqSentTime;

    @Column(name = "MQ_FILE_NAME")
    private String mqFileName;

    @Column(name = "MQ_FILE_NAME_WITH_PATH")
    private String mqFileNameWithPath;

    @Column(name = "MQ_IS_ATTACHMENT_EXIST")
    private Integer mqIsAttachmentExist;

    @Column(name = "MQ_NO_OF_ITERATION")
    private Integer mqNoOfIteration;

    @Column(name = "MQ_IS_HTML_CONTENT")
    private Integer mqIsHtmlContent;

    @Column(name = "MQ_REASON_FOR_FAILURE")
    private String mqReasonForFailure;

    public MailQueueData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMqSubject() {
        return mqSubject;
    }

    public void setMqSubject(String mqSubject) {
        this.mqSubject = mqSubject;
    }

    public String getMqFrom() {
        return mqFrom;
    }

    public void setMqFrom(String mqFrom) {
        this.mqFrom = mqFrom;
    }

    public String getMqTo() {
        return mqTo;
    }

    public void setMqTo(String mqTo) {
        this.mqTo = mqTo;
    }

    public String getMqBody() {
        return mqBody;
    }

    public void setMqBody(String mqBody) {
        this.mqBody = mqBody;
    }

    public Integer getMqSent() {
        return mqSent;
    }

    public void setMqSent(Integer mqSent) {
        this.mqSent = mqSent;
    }

    public Integer getMqTxErrors() {
        return mqTxErrors;
    }

    public void setMqTxErrors(Integer mqTxErrors) {
        this.mqTxErrors = mqTxErrors;
    }

    public Date getMqPostDate() {
        return mqPostDate;
    }

    public void setMqPostDate(Date mqPostDate) {
        this.mqPostDate = mqPostDate;
    }

    public Calendar getMqPostTime() {
        return mqPostTime;
    }

    public void setMqPostTime(Calendar mqPostTime) {
        this.mqPostTime = mqPostTime;
    }

    public Date getMqSentDate() {
        return mqSentDate;
    }

    public void setMqSentDate(Date mqSentDate) {
        this.mqSentDate = mqSentDate;
    }

    public Calendar getMqSentTime() {
        return mqSentTime;
    }

    public void setMqSentTime(Calendar mqSentTime) {
        this.mqSentTime = mqSentTime;
    }

    public String getMqFileName() {
        return mqFileName;
    }

    public void setMqFileName(String mqFileName) {
        this.mqFileName = mqFileName;
    }

    public String getMqFileNameWithPath() {
        return mqFileNameWithPath;
    }

    public void setMqFileNameWithPath(String mqFileNameWithPath) {
        this.mqFileNameWithPath = mqFileNameWithPath;
    }

    public Integer getMqIsAttachmentExist() {
        return mqIsAttachmentExist;
    }

    public void setMqIsAttachmentExist(Integer mqIsAttachmentExist) {
        this.mqIsAttachmentExist = mqIsAttachmentExist;
    }

    public Integer getMqNoOfIteration() {
        return mqNoOfIteration;
    }

    public void setMqNoOfIteration(Integer mqNoOfIteration) {
        this.mqNoOfIteration = mqNoOfIteration;
    }

    public Integer getMqIsHtmlContent() {
        return mqIsHtmlContent;
    }

    public void setMqIsHtmlContent(Integer mqIsHtmlContent) {
        this.mqIsHtmlContent = mqIsHtmlContent;
    }

    public String getMqReasonForFailure() {
        return mqReasonForFailure;
    }

    public void setMqReasonForFailure(String mqReasonForFailure) {
        this.mqReasonForFailure = mqReasonForFailure;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tmqSubject: String=");
        sb.append(mqSubject);
        sb.append(";");

        sb.append("\n\tmqFrom: String=");
        sb.append(mqFrom);
        sb.append(";");

        sb.append("\n\tmqTo: String=");
        sb.append(mqTo);
        sb.append(";");

        sb.append("\n\tmqBody: String=");
        sb.append(mqBody);
        sb.append(";");

        sb.append("\n\tmqSent: Integer=");
        sb.append(mqSent);
        sb.append(";");

        sb.append("\n\tmqTxErrors: Integer=");
        sb.append(mqTxErrors);
        sb.append(";");

        sb.append("\n\tmqPostDate: Date=");
        sb.append(mqPostDate);
        sb.append(";");

        sb.append("\n\tmqPostTime: Calendar=");
        sb.append(mqPostTime);
        sb.append(";");

        sb.append("\n\tmqSentDate: Date=");
        sb.append(mqSentDate);
        sb.append(";");

        sb.append("\n\tmqSentTime: Calendar=");
        sb.append(mqSentTime);
        sb.append(";");

        sb.append("\n\tmqFileName: String=");
        sb.append(mqFileName);
        sb.append(";");

        sb.append("\n\tmqFileNameWithPath: String=");
        sb.append(mqFileNameWithPath);
        sb.append(";");

        sb.append("\n\tmqIsAttachmentExist: Integer=");
        sb.append(mqIsAttachmentExist);
        sb.append(";");

        sb.append("\n\tmqNoOfIteration: Integer=");
        sb.append(mqNoOfIteration);
        sb.append(";");

        sb.append("\n\tmqIsHtmlContent: Integer=");
        sb.append(mqIsHtmlContent);
        sb.append(";");

        sb.append("\n\tmqReasonForFailure: String=");
        sb.append(mqReasonForFailure);
        sb.append(";");

        return sb.toString();

    }
}
