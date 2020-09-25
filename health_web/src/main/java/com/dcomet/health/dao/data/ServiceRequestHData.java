package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dev1
 */
@Entity
@Table(name = "t_service_request_h")
public class ServiceRequestHData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SER_REQH_RID", updatable = false)
    private Integer serReqhId;

    @Column(name = "SER_REQH_NO", updatable = false)
    private String serReqhNo;

    @Column(name = "SER_REQH_PREFIX", updatable = false)
    private String serReqhPrefix;

    @Column(name = "SER_REQH_SEQUENCE", updatable = false)
    private Integer serReqhSequence;

    @Column(name = "SER_REQ_OP_VISIT_RID")//, updatable = false
    private Integer serReqOpVisitRid;

    @Column(name = "SER_REQH_PAT_RID", updatable = false)
    private Integer serReqhPatRid;

    @Column(name = "SER_REQH_PAT_MRN")
    private String serReqhPatMrn;

    @Column(name = "SER_REQH_PAT_NAME")
    private String serReqhPatName;

    @Column(name = "SER_REQH_BILL_H_RID")
    private Integer serReqhBillHRid;

    @Column(name = "SER_REQH_BILL_D_RID")
    private Integer serReqhBillDRid;

    @Column(name = "SER_REQH_LAB_EN_H_RID")
    private Integer serReqhLabEnHRid;

    @Column(name = "SER_REQH_PROCEDURE_RID")
    private Integer serReqhProcedureRid;

    @Column(name = "SER_REQH_OP_CHECK")
    private Integer serReqhOpCheck;

    @Column(name = "SER_REQH_STATE")
    private Integer serReqhState;

    @Column(name = "SER_REQH_STATUS")
    private Integer serReqhStatus;

    @Column(name = "SER_REQH_PROCESS_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar serReqhProcessDate;

    @Column(name = "SER_REQH_UNIT_RID")
    private Integer serReqhUnitRid;

    @Column(name = "SER_REQH_ENTITY_RID")
    private Integer serReqhEntityRid;

    @Column(name = "SER_REQH_CREATED_USER_RID", updatable = false)
    private Integer serReqhCreatedUserRid;

    @Column(name = "SER_REQH_CREATED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar serReqhCreatedDatetime;

    @Column(name = "SER_REQH_MODIFIED_USER_RID")
    private Integer serReqhModifiedUserRid;

    @Column(name = "SER_REQH_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar serReqhModifiedDatetime;

    public ServiceRequestHData() {
    }

    public Integer getSerReqhId() {
        return serReqhId;
    }

    public void setSerReqhId(Integer serReqhId) {
        this.serReqhId = serReqhId;
    }

    public String getSerReqhNo() {
        return serReqhNo;
    }

    public void setSerReqhNo(String serReqhNo) {
        this.serReqhNo = serReqhNo;
    }

    public String getSerReqhPrefix() {
        return serReqhPrefix;
    }

    public void setSerReqhPrefix(String serReqhPrefix) {
        this.serReqhPrefix = serReqhPrefix;
    }

    public Integer getSerReqhSequence() {
        return serReqhSequence;
    }

    public void setSerReqhSequence(Integer serReqhSequence) {
        this.serReqhSequence = serReqhSequence;
    }

    public Integer getSerReqOpVisitRid() {
        return serReqOpVisitRid;
    }

    public void setSerReqOpVisitRid(Integer serReqOpVisitRid) {
        this.serReqOpVisitRid = serReqOpVisitRid;
    }

    public Integer getSerReqhPatRid() {
        return serReqhPatRid;
    }

    public void setSerReqhPatRid(Integer serReqhPatRid) {
        this.serReqhPatRid = serReqhPatRid;
    }

    public String getSerReqhPatMrn() {
        return serReqhPatMrn;
    }

    public void setSerReqhPatMrn(String serReqhPatMrn) {
        this.serReqhPatMrn = serReqhPatMrn;
    }

    public String getSerReqhPatName() {
        return serReqhPatName;
    }

    public void setSerReqhPatName(String serReqhPatName) {
        this.serReqhPatName = serReqhPatName;
    }

    public Integer getSerReqhBillHRid() {
        return serReqhBillHRid;
    }

    public void setSerReqhBillHRid(Integer serReqhBillHRid) {
        this.serReqhBillHRid = serReqhBillHRid;
    }

    public Integer getSerReqhBillDRid() {
        return serReqhBillDRid;
    }

    public void setSerReqhBillDRid(Integer serReqhBillDRid) {
        this.serReqhBillDRid = serReqhBillDRid;
    }

    public Integer getSerReqhLabEnHRid() {
        return serReqhLabEnHRid;
    }

    public void setSerReqhLabEnHRid(Integer serReqhLabEnHRid) {
        this.serReqhLabEnHRid = serReqhLabEnHRid;
    }

    public Integer getSerReqhProcedureRid() {
        return serReqhProcedureRid;
    }

    public void setSerReqhProcedureRid(Integer serReqhProcedureRid) {
        this.serReqhProcedureRid = serReqhProcedureRid;
    }

    public Integer getSerReqhOpCheck() {
        return serReqhOpCheck;
    }

    public void setSerReqhOpCheck(Integer serReqhOpCheck) {
        this.serReqhOpCheck = serReqhOpCheck;
    }

    public Integer getSerReqhState() {
        return serReqhState;
    }

    public void setSerReqhState(Integer serReqhState) {
        this.serReqhState = serReqhState;
    }

    public Integer getSerReqhStatus() {
        return serReqhStatus;
    }

    public void setSerReqhStatus(Integer serReqhStatus) {
        this.serReqhStatus = serReqhStatus;
    }

    public Calendar getSerReqhProcessDate() {
        return serReqhProcessDate;
    }

    public void setSerReqhProcessDate(Calendar serReqhProcessDate) {
        this.serReqhProcessDate = serReqhProcessDate;
    }

    public Integer getSerReqhUnitRid() {
        return serReqhUnitRid;
    }

    public void setSerReqhUnitRid(Integer serReqhUnitRid) {
        this.serReqhUnitRid = serReqhUnitRid;
    }

    public Integer getSerReqhEntityRid() {
        return serReqhEntityRid;
    }

    public void setSerReqhEntityRid(Integer serReqhEntityRid) {
        this.serReqhEntityRid = serReqhEntityRid;
    }

    public Integer getSerReqhCreatedUserRid() {
        return serReqhCreatedUserRid;
    }

    public void setSerReqhCreatedUserRid(Integer serReqhCreatedUserRid) {
        this.serReqhCreatedUserRid = serReqhCreatedUserRid;
    }

    public Calendar getSerReqhCreatedDatetime() {
        return serReqhCreatedDatetime;
    }

    public void setSerReqhCreatedDatetime(Calendar serReqhCreatedDatetime) {
        this.serReqhCreatedDatetime = serReqhCreatedDatetime;
    }

    public Integer getSerReqhModifiedUserRid() {
        return serReqhModifiedUserRid;
    }

    public void setSerReqhModifiedUserRid(Integer serReqhModifiedUserRid) {
        this.serReqhModifiedUserRid = serReqhModifiedUserRid;
    }

    public Calendar getSerReqhModifiedDatetime() {
        return serReqhModifiedDatetime;
    }

    public void setSerReqhModifiedDatetime(Calendar serReqhModifiedDatetime) {
        this.serReqhModifiedDatetime = serReqhModifiedDatetime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tserReqhId: Integer=");
        sb.append(serReqhId);
        sb.append(";");

        sb.append("\n\tserReqOpVisitRid: Integer=");
        sb.append(serReqOpVisitRid);
        sb.append(";");

        sb.append("\n\tserReqhPatRid: Integer=");
        sb.append(serReqhPatRid);
        sb.append(";");

        sb.append("\n\tserReqhNo: String=");
        sb.append(serReqhNo);
        sb.append(";");

        sb.append("\n\tserReqhPrefix: String=");
        sb.append(serReqhPrefix);
        sb.append(";");

        sb.append("\n\tserReqhSequence: Integer=");
        sb.append(serReqhSequence);
        sb.append(";");

        sb.append("\n\tserReqhPatMrn: String=");
        sb.append(serReqhPatMrn);
        sb.append(";");

        sb.append("\n\tserReqhPatName: String=");
        sb.append(serReqhPatName);
        sb.append(";");

        sb.append("\n\tserReqhBillHRid: Integer=");
        sb.append(serReqhBillHRid);
        sb.append(";");

        sb.append("\n\tserReqhBillDRid: Integer=");
        sb.append(serReqhBillDRid);
        sb.append(";");

        sb.append("\n\tserReqhLabEnHRid: Integer=");
        sb.append(serReqhLabEnHRid);
        sb.append(";");

        sb.append("\n\tserReqhProcedureRid: Integer=");
        sb.append(serReqhProcedureRid);
        sb.append(";");

        sb.append("\n\tserReqhOpCheck: Integer=");
        sb.append(serReqhOpCheck);
        sb.append(";");

        sb.append("\n\tserReqhProcedureRid: Integer=");
        sb.append(serReqhProcedureRid);
        sb.append(";");

        sb.append("\n\tserReqhOpCheck: Integer=");
        sb.append(serReqhOpCheck);
        sb.append(";");

        sb.append("\n\tserReqhState: Integer=");
        sb.append(serReqhState);
        sb.append(";");

        sb.append("\n\tserReqhStatus: Integer=");
        sb.append(serReqhStatus);
        sb.append(";");

        sb.append("\n\tserReqhProcessDate: Calendar=");
        sb.append(serReqhProcessDate);
        sb.append(";");

        sb.append("\n\tserReqhUnitRid: Integer=");
        sb.append(serReqhUnitRid);
        sb.append(";");

        sb.append("\n\tserReqhEntityRid: Integer=");
        sb.append(serReqhEntityRid);
        sb.append(";");

        sb.append("\n\tserReqhCreatedUserRid: Integer=");
        sb.append(serReqhCreatedUserRid);
        sb.append(";");

        sb.append("\n\tserReqhCreatedDatetime: Calendar=");
        sb.append(serReqhCreatedDatetime);
        sb.append(";");

        sb.append("\n\tserReqhModifiedUserRid: Integer=");
        sb.append(serReqhModifiedUserRid);
        sb.append(";");

        sb.append("\n\tserReqhModifiedDatetime: Calendar=");
        sb.append(serReqhModifiedDatetime);
        sb.append(";");

        return sb.toString();
    }
}
