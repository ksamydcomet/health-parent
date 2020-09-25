package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ItemOrder extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer ioPatientRid;
    private Integer ioVisitRid;
    private Integer ioUnitRid;
    private String ioOrderDate;
    private Integer ioOrderNo;
    private Integer ioOrderType;
    private Integer ioItemId;
    private String ioItemName;
    private Float ioItemQty;
    private Integer ioEntityRid;
    private Integer ioMajorProcedureStatus;
    private Integer ioState;
    private Integer ioStatus;
    private String ioCancellationReason;
    private String ioRemarks;
    private Integer ioAttnDocRid;
    private Integer ioOrderDocRid;
    private Integer ioProcessedBy;
    private String ioProcessedDatetime;
    private Integer ioScheduleRid;
    private Integer ioServiceUnit;

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

    public String getIoOrderDate() {
        return ioOrderDate;
    }

    public void setIoOrderDate(String ioOrderDate) {
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

    public Integer getIoMajorProcedureStatus() {
        return ioMajorProcedureStatus;
    }

    public void setIoMajorProcedureStatus(Integer ioMajorProcedureStatus) {
        this.ioMajorProcedureStatus = ioMajorProcedureStatus;
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

    public String getIoProcessedDatetime() {
        return ioProcessedDatetime;
    }

    public void setIoProcessedDatetime(String ioProcessedDatetime) {
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

        sb.append("\n\tioMajorProcedureStatus: Integer=");
        sb.append(ioMajorProcedureStatus);
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

        return sb.toString();
    }
}
