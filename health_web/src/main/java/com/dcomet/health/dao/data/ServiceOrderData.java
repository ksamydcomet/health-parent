package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import com.dcomet.module.laboratory.dao.data.LabResultDData;
import java.io.Serializable;
import java.util.Calendar;
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
 * @author Adhithya
 */
@Entity
@Table(name = "t_service_order")
public class ServiceOrderData implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SO_RID", updatable = false)
    private Integer id;

    @Column(name = "SO_AGAINST_UNIT_RID")
    private Integer soAgainstUnitRID;

    @Column(name = "SO_PATIENT_RID")
    private Integer soPatientRID;

    @Column(name = "SO_VISIT_RID")
    private Integer soVisitRID;

    @Column(name = "SO_ORDERING_UNIT_RID")
    private Integer soOrderingUnitRID;

    @Column(name = "SO_ORDER_NO", updatable = false)
    private String soOrderNo;

    @Column(name = "SO_ORDER_TYPE")
    private Integer soOrderType;

    @Column(name = "SO_ITEM_ID")
    private Integer soItemID;

    @Column(name = "SO_ITEM_NAME")
    private String soItemName;

    @Column(name = "SO_START_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soStartDate;

    @Column(name = "SO_QTY")
    private Float soQty;

    @Column(name = "SO_ENTITY_RID")
    private Integer soEntityRID;

    @Column(name = "SO_STATUS")
    private Integer soStatus;

    @Column(name = "SO_STATUS_MOD_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soStatusModifiedDateTime;

    @Column(name = "SO_PROCESSING_UNIT_RID")
    private Integer soProcessingUnitRID;

    @Column(name = "SO_ATTND_DOC_RID")
    private Integer soAttndDocRID;

    @Column(name = "SO_ORDER_DOC_RID")
    private Integer soOrderDocRID;

    @Column(name = "SO_RECODED_USER_RID")
    private Integer soRecodedUserRID;

    @Column(name = "SO_PROCESSED_BY")
    private String soProcessedBY;

    @Column(name = "SO_PROCESSED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soProcessedDate;

    @Column(name = "SO_PROCESSING_COMMENTS")
    private String soProcessingComments;

    @Column(name = "SO_RESULT_RID")
    private Integer soResultRID;

    @Column(name = "SO_RESULT_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soResultDateTime;

    @Column(name = "SO_SCHEDULE_RID")
    private Integer soScheduleRID;

    @Column(name = "SO_SER_MODE")
    private Integer soSerMode;

    @Column(name = "SO_CANCELLATION_REASON")
    private String soCancellationReason;

    @Column(name = "SO_REMARKS")
    private String soRemarks;

    @Column(name = "SO_ITEM_CATEGORY")
    private Integer soItemCategory;

    @Column(name = "SO_PARENT_RID")
    private Integer soParentRID;

    @Column(name = "SO_ROOT_PARENT_ID")
    private Integer soRootParentID;

    @Column(name = "SO_SIGNED_USER_RID")
    private Integer soSignedUserRID;

    @Column(name = "SO_CO_SIGNED_USER_RID")
    private Integer soCoSignedUserRID;

    @Column(name = "SO_SIGNED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soSignedDateTime;

    @Column(name = "SO_RECURRENCE_ID")
    private Integer soRecurrenceID;

    @Column(name = "SO_RECURRENCE_SPAN")
    private String soRecurrenceSpan;

    @Column(name = "SO_OCCURENCES_TOTAL")
    private Integer soOccurenceTotal;

    @Column(name = "SO_OCCUR_GENERATED")
    private Integer soOccurenceGenerated;

    @Column(name = "SO_RECURRENCE_TEXT")
    private String soRecurrenceText;

    @Column(name = "SO_MAJOR_PROCEDURE_STATUS", updatable = false)
    private Integer soMajorProcedureStatus;

    @Column(name = "SO_SERVICE_POINT")
    private Integer soServicePoint;

    @Column(name = "SO_STATE")
    private Integer soState;

    @Column(name = "SO_ADVISED_USER_RID", updatable = false)
    private Integer soAdviceUserRID;

    @Column(name = "SO_ADVISED_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soAdviceDateTime;

    @Column(name = "SO_CONVERTED_USER_RID")
    private Integer soConvertedUserRID;

    @Column(name = "SO_CONVERTED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar soConvertedDateTime;

    @Column(name = "SO_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "SO_ORDER_DATE", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "SO_MODIFIED_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "SO_MODIFIED_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LRD_H_RID", referencedColumnName = "SO_RID", updatable = false)
    private List<LabResultDData> labResultDData;

    public List<LabResultDData> getLabResultDData() {
        return labResultDData;
    }

    public void setLabResultDData(List<LabResultDData> labResultDData) {
        this.labResultDData = labResultDData;
    }

    public ServiceOrderData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoAgainstUnitRID() {
        return soAgainstUnitRID;
    }

    public void setSoAgainstUnitRID(Integer soAgainstUnitRID) {
        this.soAgainstUnitRID = soAgainstUnitRID;
    }

    public Integer getSoPatientRID() {
        return soPatientRID;
    }

    public void setSoPatientRID(Integer soPatientRID) {
        this.soPatientRID = soPatientRID;
    }

    public Integer getSoVisitRID() {
        return soVisitRID;
    }

    public void setSoVisitRID(Integer soVisitRID) {
        this.soVisitRID = soVisitRID;
    }

    public Integer getSoOrderingUnitRID() {
        return soOrderingUnitRID;
    }

    public void setSoOrderingUnitRID(Integer soOrderingUnitRID) {
        this.soOrderingUnitRID = soOrderingUnitRID;
    }

    public String getSoOrderNo() {
        return soOrderNo;
    }

    public void setSoOrderNo(String soOrderNo) {
        this.soOrderNo = soOrderNo;
    }

    public Integer getSoOrderType() {
        return soOrderType;
    }

    public void setSoOrderType(Integer soOrderType) {
        this.soOrderType = soOrderType;
    }

    public Integer getSoItemID() {
        return soItemID;
    }

    public void setSoItemID(Integer soItemID) {
        this.soItemID = soItemID;
    }

    public String getSoItemName() {
        return soItemName;
    }

    public void setSoItemName(String soItemName) {
        this.soItemName = soItemName;
    }

    public Calendar getSoStartDate() {
        return soStartDate;
    }

    public void setSoStartDate(Calendar soStartDate) {
        this.soStartDate = soStartDate;
    }

    public Float getSoQty() {
        return soQty;
    }

    public void setSoQty(Float soQty) {
        this.soQty = soQty;
    }

    public Integer getSoEntityRID() {
        return soEntityRID;
    }

    public void setSoEntityRID(Integer soEntityRID) {
        this.soEntityRID = soEntityRID;
    }

    public Integer getSoStatus() {
        return soStatus;
    }

    public void setSoStatus(Integer soStatus) {
        this.soStatus = soStatus;
    }

    public Calendar getSoStatusModifiedDateTime() {
        return soStatusModifiedDateTime;
    }

    public void setSoStatusModifiedDateTime(Calendar soStatusModifiedDateTime) {
        this.soStatusModifiedDateTime = soStatusModifiedDateTime;
    }

    public Integer getSoProcessingUnitRID() {
        return soProcessingUnitRID;
    }

    public void setSoProcessingUnitRID(Integer soProcessingUnitRID) {
        this.soProcessingUnitRID = soProcessingUnitRID;
    }

    public Integer getSoAttndDocRID() {
        return soAttndDocRID;
    }

    public void setSoAttndDocRID(Integer soAttndDocRID) {
        this.soAttndDocRID = soAttndDocRID;
    }

    public Integer getSoOrderDocRID() {
        return soOrderDocRID;
    }

    public void setSoOrderDocRID(Integer soOrderDocRID) {
        this.soOrderDocRID = soOrderDocRID;
    }

    public Integer getSoRecodedUserRID() {
        return soRecodedUserRID;
    }

    public void setSoRecodedUserRID(Integer soRecodedUserRID) {
        this.soRecodedUserRID = soRecodedUserRID;
    }

    public String getSoProcessedBY() {
        return soProcessedBY;
    }

    public void setSoProcessedBY(String soProcessedBY) {
        this.soProcessedBY = soProcessedBY;
    }

    public Calendar getSoProcessedDate() {
        return soProcessedDate;
    }

    public void setSoProcessedDate(Calendar soProcessedDate) {
        this.soProcessedDate = soProcessedDate;
    }

    public String getSoProcessingComments() {
        return soProcessingComments;
    }

    public void setSoProcessingComments(String soProcessingComments) {
        this.soProcessingComments = soProcessingComments;
    }

    public Integer getSoResultRID() {
        return soResultRID;
    }

    public void setSoResultRID(Integer soResultRID) {
        this.soResultRID = soResultRID;
    }

    public Calendar getSoResultDateTime() {
        return soResultDateTime;
    }

    public void setSoResultDateTime(Calendar soResultDateTime) {
        this.soResultDateTime = soResultDateTime;
    }

    public Integer getSoScheduleRID() {
        return soScheduleRID;
    }

    public void setSoScheduleRID(Integer soScheduleRID) {
        this.soScheduleRID = soScheduleRID;
    }

    public Integer getSoSerMode() {
        return soSerMode;
    }

    public void setSoSerMode(Integer soSerMode) {
        this.soSerMode = soSerMode;
    }

    public String getSoCancellationReason() {
        return soCancellationReason;
    }

    public void setSoCancellationReason(String soCancellationReason) {
        this.soCancellationReason = soCancellationReason;
    }

    public String getSoRemarks() {
        return soRemarks;
    }

    public void setSoRemarks(String soRemarks) {
        this.soRemarks = soRemarks;
    }

    public Integer getSoItemCategory() {
        return soItemCategory;
    }

    public void setSoItemCategory(Integer soItemCategory) {
        this.soItemCategory = soItemCategory;
    }

    public Integer getSoParentRID() {
        return soParentRID;
    }

    public void setSoParentRID(Integer soParentRID) {
        this.soParentRID = soParentRID;
    }

    public Integer getSoRootParentID() {
        return soRootParentID;
    }

    public void setSoRootParentID(Integer soRootParentID) {
        this.soRootParentID = soRootParentID;
    }

    public Integer getSoSignedUserRID() {
        return soSignedUserRID;
    }

    public void setSoSignedUserRID(Integer soSignedUserRID) {
        this.soSignedUserRID = soSignedUserRID;
    }

    public Integer getSoCoSignedUserRID() {
        return soCoSignedUserRID;
    }

    public void setSoCoSignedUserRID(Integer soCoSignedUserRID) {
        this.soCoSignedUserRID = soCoSignedUserRID;
    }

    public Calendar getSoSignedDateTime() {
        return soSignedDateTime;
    }

    public void setSoSignedDateTime(Calendar soSignedDateTime) {
        this.soSignedDateTime = soSignedDateTime;
    }

    public Integer getSoRecurrenceID() {
        return soRecurrenceID;
    }

    public void setSoRecurrenceID(Integer soRecurrenceID) {
        this.soRecurrenceID = soRecurrenceID;
    }

    public String getSoRecurrenceSpan() {
        return soRecurrenceSpan;
    }

    public void setSoRecurrenceSpan(String soRecurrenceSpan) {
        this.soRecurrenceSpan = soRecurrenceSpan;
    }

    public Integer getSoOccurenceTotal() {
        return soOccurenceTotal;
    }

    public void setSoOccurenceTotal(Integer soOccurenceTotal) {
        this.soOccurenceTotal = soOccurenceTotal;
    }

    public Integer getSoOccurenceGenerated() {
        return soOccurenceGenerated;
    }

    public void setSoOccurenceGenerated(Integer soOccurenceGenerated) {
        this.soOccurenceGenerated = soOccurenceGenerated;
    }

    public String getSoRecurrenceText() {
        return soRecurrenceText;
    }

    public void setSoRecurrenceText(String soRecurrenceText) {
        this.soRecurrenceText = soRecurrenceText;
    }

    public Integer getSoMajorProcedureStatus() {
        return soMajorProcedureStatus;
    }

    public void setSoMajorProcedureStatus(Integer soMajorProcedureStatus) {
        this.soMajorProcedureStatus = soMajorProcedureStatus;
    }

    public Integer getSoServicePoint() {
        return soServicePoint;
    }

    public void setSoServicePoint(Integer soServicePoint) {
        this.soServicePoint = soServicePoint;
    }

    public Integer getSoState() {
        return soState;
    }

    public void setSoState(Integer soState) {
        this.soState = soState;
    }

    public Integer getSoAdviceUserRID() {
        return soAdviceUserRID;
    }

    public void setSoAdviceUserRID(Integer soAdviceUserRID) {
        this.soAdviceUserRID = soAdviceUserRID;
    }

    public Calendar getSoAdviceDateTime() {
        return soAdviceDateTime;
    }

    public void setSoAdviceDateTime(Calendar soAdviceDateTime) {
        this.soAdviceDateTime = soAdviceDateTime;
    }

    public Integer getSoConvertedUserRID() {
        return soConvertedUserRID;
    }

    public void setSoConvertedUserRID(Integer soConvertedUserRID) {
        this.soConvertedUserRID = soConvertedUserRID;
    }

    public Calendar getSoConvertedDateTime() {
        return soConvertedDateTime;
    }

    public void setSoConvertedDateTime(Calendar soConvertedDateTime) {
        this.soConvertedDateTime = soConvertedDateTime;
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

        sb.append("\n\tsoAgainstUnitRID: Integer=");
        sb.append(soAgainstUnitRID);
        sb.append(";");

        sb.append("\n\tsoPatientRID: Integer=");
        sb.append(soPatientRID);
        sb.append(";");

        sb.append("\n\tsoVisitRID: Integer=");
        sb.append(soVisitRID);
        sb.append(";");

        sb.append("\n\tsoOrderingUnitRID: Integer=");
        sb.append(soOrderingUnitRID);
        sb.append(";");

        sb.append("\n\tsoOrderNo: String=");
        sb.append(soOrderNo);
        sb.append(";");

        sb.append("\n\tsoOrderType: Integer=");
        sb.append(soOrderType);
        sb.append(";");

        sb.append("\n\tsoItemID: Integer=");
        sb.append(soItemID);
        sb.append(";");

        sb.append("\n\tsoItemName: String=");
        sb.append(soItemName);
        sb.append(";");

        sb.append("\n\tsoStartDate: Date=");
        sb.append(soStartDate);
        sb.append(";");

        sb.append("\n\tsoQty: Integer=");
        sb.append(soQty);
        sb.append(";");

        sb.append("\n\tsoEntityRID: Integer=");
        sb.append(soEntityRID);
        sb.append(";");

        sb.append("\n\tsoStatus: Integer=");
        sb.append(soStatus);
        sb.append(";");

        sb.append("\n\tsoStatusModifiedDateTime: Calendar=");
        sb.append(soStatusModifiedDateTime);
        sb.append(";");

        sb.append("\n\tsoProcessingUnitRID: Integer=");
        sb.append(soProcessingUnitRID);
        sb.append(";");

        sb.append("\n\tsoAttndDocRID: Integer=");
        sb.append(soAttndDocRID);
        sb.append(";");

        sb.append("\n\tsoOrderDocRID: Integer=");
        sb.append(soOrderDocRID);
        sb.append(";");

        sb.append("\n\tsoRecodedUserRID: Integer=");
        sb.append(soRecodedUserRID);
        sb.append(";");

        sb.append("\n\tsoProcessedBY: Integer=");
        sb.append(soProcessedBY);
        sb.append(";");

        sb.append("\n\tsoProcessedDate: Date=");
        sb.append(soProcessedDate);
        sb.append(";");

        sb.append("\n\tsoProcessingComments: String=");
        sb.append(soProcessingComments);
        sb.append(";");

        sb.append("\n\tsoResultRID: Integer=");
        sb.append(soResultRID);
        sb.append(";");

        sb.append("\n\tsoResultDateTime: Calendar=");
        sb.append(soResultDateTime);
        sb.append(";");

        sb.append("\n\tsoScheduleRID: Integer=");
        sb.append(soScheduleRID);
        sb.append(";");

        sb.append("\n\tsoSerMode: Integer=");
        sb.append(soSerMode);
        sb.append(";");

        sb.append("\n\tsoCancellationReason: String=");
        sb.append(soCancellationReason);
        sb.append(";");

        sb.append("\n\tsoRemarks: String=");
        sb.append(soRemarks);
        sb.append(";");

        sb.append("\n\tsoItemCategory: Integer=");
        sb.append(soItemCategory);
        sb.append(";");

        sb.append("\n\tsoParentRID: Integer=");
        sb.append(soParentRID);
        sb.append(";");

        sb.append("\n\tsoRootParentID: Integer=");
        sb.append(soRootParentID);
        sb.append(";");

        sb.append("\n\tsoSignedUserRID: Integer=");
        sb.append(soSignedUserRID);
        sb.append(";");

        sb.append("\n\tsoCoSignedUserRID: Integer=");
        sb.append(soCoSignedUserRID);
        sb.append(";");

        sb.append("\n\tsoSignedDateTime: Calendar=");
        sb.append(soSignedDateTime);
        sb.append(";");

        sb.append("\n\tsoRecurrenceID: Integer=");
        sb.append(soRecurrenceID);
        sb.append(";");

        sb.append("\n\tsoRecurrenceSpan: String=");
        sb.append(soRecurrenceSpan);
        sb.append(";");

        sb.append("\n\tsoOccurenceTotal: Integer=");
        sb.append(soOccurenceTotal);
        sb.append(";");

        sb.append("\n\tsoOccurenceGenerated: Integer=");
        sb.append(soOccurenceGenerated);
        sb.append(";");

        sb.append("\n\tsoRecurrenceText: String=");
        sb.append(soRecurrenceText);
        sb.append(";");

        sb.append("\n\tsoMajorProcedureStatus: Integer=");
        sb.append(soMajorProcedureStatus);
        sb.append(";");

        sb.append("\n\tsoServicePoint: Integer=");
        sb.append(soServicePoint);
        sb.append(";");

        sb.append("\n\tsoState: Integer=");
        sb.append(soState);
        sb.append(";");

        sb.append("\n\tsoAdviceUserRID: Integer=");
        sb.append(soAdviceUserRID);
        sb.append(";");

        sb.append("\n\tsoAdviceDateTime: Calendar=");
        sb.append(soAdviceDateTime);
        sb.append(";");

        sb.append("\n\tsoConvertedUserRID: Integer=");
        sb.append(soConvertedUserRID);
        sb.append(";");

        sb.append("\n\tsoConvertedDateTime: Calendar=");
        sb.append(soConvertedDateTime);
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
