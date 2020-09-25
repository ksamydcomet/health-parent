package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
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
import javax.persistence.TemporalType;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_item_order")
public class ItemOrderData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IO_RID", updatable = false)
    private Integer id;

    @Column(name = "IO_PATIENT_RID")
    private Integer ioPatientRid;

    @Column(name = "IO_VISIT_RID")
    private Integer ioVisitRid;

    @Column(name = "IO_UNIT_RID")
    private Integer ioUnitRid;

    @Column(name = "IO_ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date ioOrderDate;

    @Column(name = "IO_ORDER_NO", updatable = false)
    private Integer ioOrderNo;

    @Column(name = "IO_ORDER_TYPE")
    private Integer ioOrderType;

    @Column(name = "IO_ITEM_ID")
    private Integer ioItemId;

    @Column(name = "IO_ITEM_NAME")
    private String ioItemName;

    @Column(name = "IO_ITEM_QTY")
    private Float ioItemQty;

    @Column(name = "IO_ENTITY_RID")
    private Integer ioEntityRid;

    @Column(name = "IO_STATE")
    private Integer ioState;

    @Column(name = "IO_STATUS")
    private Integer ioStatus;

    @Column(name = "IO_CANCELLATION_REASON")
    private String ioCancellationReason;

    @Column(name = "IO_REMARKS")
    private String ioRemarks;

    @Column(name = "IO_ATTN_DOC_RID")
    private Integer ioAttnDocRid;

    @Column(name = "IO_MAJOR_PROCEDURE_STATUS")
    private Integer ioMajorProcedureStatus;

    @Column(name = "IO_ORDER_DOC_RID")
    private Integer ioOrderDocRid;

    @Column(name = "IO_PROCESSED_BY")
    private Integer ioProcessedBy;

    @Column(name = "IO_PROCESSED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar ioProcessedDatetime;

    @Column(name = "IO_SCHEDULE_RID")
    private Integer ioScheduleRid;

    @Column(name = "IO_SERVICE_UNIT")
    private Integer ioServiceUnit;

    @Column(name = "IO_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "IO_CREATED_DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "IO_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "IO_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    public ItemOrderData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIoPatientRid() {
        return ioPatientRid;
    }

    public void setIoPatientRid(Integer ioPatientRid) {
        this.ioPatientRid = ioPatientRid;
    }

    public Integer getIoVisitRid() {
        return ioVisitRid;
    }

    public void setIoVisitRid(Integer ioVisitRid) {
        this.ioVisitRid = ioVisitRid;
    }

    public Integer getIoUnitRid() {
        return ioUnitRid;
    }

    public void setIoUnitRid(Integer ioUnitRid) {
        this.ioUnitRid = ioUnitRid;
    }

    public Date getIoOrderDate() {
        return ioOrderDate;
    }

    public void setIoOrderDate(Date ioOrderDate) {
        this.ioOrderDate = ioOrderDate;
    }

    public Integer getIoOrderNo() {
        return ioOrderNo;
    }

    public void setIoOrderNo(Integer ioOrderNo) {
        this.ioOrderNo = ioOrderNo;
    }

    public Integer getIoOrderType() {
        return ioOrderType;
    }

    public void setIoOrderType(Integer ioOrderType) {
        this.ioOrderType = ioOrderType;
    }

    public Integer getIoItemId() {
        return ioItemId;
    }

    public void setIoItemId(Integer ioItemId) {
        this.ioItemId = ioItemId;
    }

    public String getIoItemName() {
        return ioItemName;
    }

    public void setIoItemName(String ioItemName) {
        this.ioItemName = ioItemName;
    }

    public Float getIoItemQty() {
        return ioItemQty;
    }

    public void setIoItemQty(Float ioItemQty) {
        this.ioItemQty = ioItemQty;
    }

    public Integer getIoEntityRid() {
        return ioEntityRid;
    }

    public void setIoEntityRid(Integer ioEntityRid) {
        this.ioEntityRid = ioEntityRid;
    }

    public Integer getIoState() {
        return ioState;
    }

    public void setIoState(Integer ioState) {
        this.ioState = ioState;
    }

    public Integer getIoStatus() {
        return ioStatus;
    }

    public void setIoStatus(Integer ioStatus) {
        this.ioStatus = ioStatus;
    }

    public String getIoCancellationReason() {
        return ioCancellationReason;
    }

    public void setIoCancellationReason(String ioCancellationReason) {
        this.ioCancellationReason = ioCancellationReason;
    }

    public String getIoRemarks() {
        return ioRemarks;
    }

    public void setIoRemarks(String ioRemarks) {
        this.ioRemarks = ioRemarks;
    }

    public Integer getIoAttnDocRid() {
        return ioAttnDocRid;
    }

    public void setIoAttnDocRid(Integer ioAttnDocRid) {
        this.ioAttnDocRid = ioAttnDocRid;
    }

    public Integer getIoMajorProcedureStatus() {
        return ioMajorProcedureStatus;
    }

    public void setIoMajorProcedureStatus(Integer ioMajorProcedureStatus) {
        this.ioMajorProcedureStatus = ioMajorProcedureStatus;
    }

    public Integer getIoOrderDocRid() {
        return ioOrderDocRid;
    }

    public void setIoOrderDocRid(Integer ioOrderDocRid) {
        this.ioOrderDocRid = ioOrderDocRid;
    }

    public Integer getIoProcessedBy() {
        return ioProcessedBy;
    }

    public void setIoProcessedBy(Integer ioProcessedBy) {
        this.ioProcessedBy = ioProcessedBy;
    }

    public Calendar getIoProcessedDatetime() {
        return ioProcessedDatetime;
    }

    public void setIoProcessedDatetime(Calendar ioProcessedDatetime) {
        this.ioProcessedDatetime = ioProcessedDatetime;
    }

    public Integer getIoScheduleRid() {
        return ioScheduleRid;
    }

    public void setIoScheduleRid(Integer ioScheduleRid) {
        this.ioScheduleRid = ioScheduleRid;
    }

    public Integer getIoServiceUnit() {
        return ioServiceUnit;
    }

    public void setIoServiceUnit(Integer ioServiceUnit) {
        this.ioServiceUnit = ioServiceUnit;
    }

    @Override
    public Integer getCreatedUserRid() {
        return createdUserRid;
    }

    @Override
    public void setCreatedUserRid(Integer createdUserRid) {
        this.createdUserRid = createdUserRid;
    }

    @Override
    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public Integer getModifiedUserRid() {
        return modifiedUserRid;
    }

    @Override
    public void setModifiedUserRid(Integer modifiedUserRid) {
        this.modifiedUserRid = modifiedUserRid;
    }

    @Override
    public Calendar getModifiedDateTime() {
        return modifiedDateTime;
    }

    @Override
    public void setModifiedDateTime(Calendar modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tioPatientRid: Integer=");
        sb.append(ioPatientRid);
        sb.append(";");

        sb.append("\n\tioVisitRid: Integer=");
        sb.append(ioVisitRid);
        sb.append(";");

        sb.append("\n\tioUnitRid: Integer=");
        sb.append(ioUnitRid);
        sb.append(";");

        sb.append("\n\tioOrderDate: Date=");
        sb.append(ioOrderDate);
        sb.append(";");

        sb.append("\n\tioOrderNo: Integer=");
        sb.append(ioOrderNo);
        sb.append(";");

        sb.append("\n\tioOrderType: Integer=");
        sb.append(ioOrderType);
        sb.append(";");

        sb.append("\n\tioItemId: String=");
        sb.append(ioItemId);
        sb.append(";");

        sb.append("\n\tioItemName: Date=");
        sb.append(ioItemName);
        sb.append(";");

        sb.append("\n\tioItemQty: Float=");
        sb.append(ioItemQty);
        sb.append(";");

        sb.append("\n\tioEntityRid: Integer=");
        sb.append(ioEntityRid);
        sb.append(";");

        sb.append("\n\tioState: Integer=");
        sb.append(ioState);
        sb.append(";");

        sb.append("\n\tioStatus: Integer=");
        sb.append(ioStatus);
        sb.append(";");

        sb.append("\n\tioCancellationReason: String=");
        sb.append(ioCancellationReason);
        sb.append(";");

        sb.append("\n\tioRemarks: String=");
        sb.append(ioRemarks);
        sb.append(";");

        sb.append("\n\tioAttnDocRid: Integer=");
        sb.append(ioAttnDocRid);
        sb.append(";");

        sb.append("\n\tioMajorProcedureStatus: Integer=");
        sb.append(ioMajorProcedureStatus);
        sb.append(";");

        sb.append("\n\tioOrderDocRid: Integer=");
        sb.append(ioOrderDocRid);
        sb.append(";");

        sb.append("\n\tioProcessedBy: Integer=");
        sb.append(ioProcessedBy);
        sb.append(";");

        sb.append("\n\tioProcessedDatetime: Calendar=");
        sb.append(ioProcessedDatetime);
        sb.append(";");

        sb.append("\n\tioScheduleRid: Integer=");
        sb.append(ioScheduleRid);
        sb.append(";");

        sb.append("\n\tioServiceUnit: Integer=");
        sb.append(ioServiceUnit);
        sb.append(";");

        sb.append("\n\tCreatedUserRid: Integer=");
        sb.append(createdUserRid);
        sb.append(";");

        sb.append("\n\tCreatedDateTime: String=");
        sb.append(createdDateTime);
        sb.append(";");

        sb.append("\n\tModifiedDateTime: String=");
        sb.append(modifiedDateTime);
        sb.append(";");

        sb.append("\n\tadModifiedUserRID: String=");
        sb.append(modifiedUserRid);
        sb.append(";");

        return sb.toString();
    }
}
