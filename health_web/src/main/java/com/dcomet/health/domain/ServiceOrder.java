package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import com.dcomet.module.laboratory.domain.LabResultD;
import com.dcomet.module.master.domain.ServiceMaster;
import java.io.Serializable;
import java.util.List;

public class ServiceOrder extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer soAgainstUnitRID;
    private Integer soPatientRID;
    private Integer soVisitRID;
    private Integer soOrderingUnitRID;
    private String soOrderNo;
    private Integer soOrderType;
    private Integer soItemID;
    private String soItemName;
    private String soStartDate;
    private String soStartTime;
    private Float soQty;
    private Integer soStatus;
    private String soStatusModifiedDateTime;
    private Integer soProcessingUnitRID;
    private Integer soAttndDocRID;
    private Integer soOrderDocRID;
    private Integer soRecodedUserRID;
    private String soProcessedBY;
    private String soProcessedDate;
    private String soProcessedTime;
    private String soProcessingComments;
    private Integer soResultRID;
    private String soResultDateTime;
    private Integer soScheduleRID;
    private Integer soSerMode;
    private String soCancellationReason;
    private String soRemarks;
    private Integer soItemCategory;
    private Integer soParentRID;
    private Integer soRootParentID;
    private Integer soSignedUserRID;
    private Integer soCoSignedUserRID;
    private String soSignedDateTime;
    private Integer soRecurrenceID;
    private String soRecurrenceSpan;
    private Integer soOccurenceTotal;
    private Integer soOccurenceGenerated;
    private String soRecurrenceText;
    private Integer soMajorProcedureStatus;
    private Integer soServicePoint;
    private Integer soState;
    private Integer soAdviceUserRID;
    private String soAdviceDateTime;
    private Integer soConvertedUserRID;
    private String soConvertedDateTime;

    private Float soDiscPercentage;

    public List<LabResultD> labResultDList;
    public ServiceMaster serviceMaster;
    public List<ServiceOrderD> serviceOrderDList;

    public List<LabResultD> getLabResultDList() {
        return labResultDList;
    }

    public void setLabResultDList(List<LabResultD> labResultDList) {
        this.labResultDList = labResultDList;
    }

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    public void setServiceMaster(ServiceMaster serviceMaster) {
        this.serviceMaster = serviceMaster;
    }

    public List<ServiceOrderD> getServiceOrderDList() {
        return serviceOrderDList;
    }

    public void setServiceOrderDList(List<ServiceOrderD> serviceOrderDList) {
        this.serviceOrderDList = serviceOrderDList;
    }

    public ServiceOrder() {

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

    public String getSoStartDate() {
        return soStartDate;
    }

    public void setSoStartDate(String soStartDate) {
        this.soStartDate = soStartDate;
    }

    public String getSoStartTime() {
        return soStartTime;
    }

    public void setSoStartTime(String soStartTime) {
        this.soStartTime = soStartTime;
    }

    public Float getSoQty() {
        return soQty;
    }

    public void setSoQty(Float soQty) {
        this.soQty = soQty;
    }

    public Integer getSoStatus() {
        return soStatus;
    }

    public void setSoStatus(Integer soStatus) {
        this.soStatus = soStatus;
    }

    public String getSoStatusModifiedDateTime() {
        return soStatusModifiedDateTime;
    }

    public void setSoStatusModifiedDateTime(String soStatusModifiedDateTime) {
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

    public String getSoProcessedDate() {
        return soProcessedDate;
    }

    public void setSoProcessedDate(String soProcessedDate) {
        this.soProcessedDate = soProcessedDate;
    }

    public String getSoProcessedTime() {
        return soProcessedTime;
    }

    public void setSoProcessedTime(String soProcessedTime) {
        this.soProcessedTime = soProcessedTime;
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

    public String getSoResultDateTime() {
        return soResultDateTime;
    }

    public void setSoResultDateTime(String soResultDateTime) {
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

    public String getSoSignedDateTime() {
        return soSignedDateTime;
    }

    public void setSoSignedDateTime(String soSignedDateTime) {
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

    public String getSoAdviceDateTime() {
        return soAdviceDateTime;
    }

    public void setSoAdviceDateTime(String soAdviceDateTime) {
        this.soAdviceDateTime = soAdviceDateTime;
    }

    public Integer getSoConvertedUserRID() {
        return soConvertedUserRID;
    }

    public void setSoConvertedUserRID(Integer soConvertedUserRID) {
        this.soConvertedUserRID = soConvertedUserRID;
    }

    public String getSoConvertedDateTime() {
        return soConvertedDateTime;
    }

    public void setSoConvertedDateTime(String soConvertedDateTime) {
        this.soConvertedDateTime = soConvertedDateTime;
    }

    public Float getSoDiscPercentage() {
        return soDiscPercentage;
    }

    public void setSoDiscPercentage(Float soDiscPercentage) {
        this.soDiscPercentage = soDiscPercentage;
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

        sb.append("\n\tsoStartTime: String=");
        sb.append(soStartTime);
        sb.append(";");

        sb.append("\n\tsoQty: Float=");
        sb.append(soQty);
        sb.append(";");

        sb.append("\n\tsoStatus: Integer=");
        sb.append(soStatus);
        sb.append(";");

        sb.append("\n\tsoStatusModDateTime: String=");
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

        sb.append("\n\tsoProcessedDate: String=");
        sb.append(soProcessedDate);
        sb.append(";");

        sb.append("\n\tsoProcessedTime: String=");
        sb.append(soProcessedTime);
        sb.append(";");

        sb.append("\n\tsoProcessingComments: String=");
        sb.append(soProcessingComments);
        sb.append(";");

        sb.append("\n\tsoResultRID: Integer=");
        sb.append(soResultRID);
        sb.append(";");

        sb.append("\n\tsoResultDateTime: String=");
        sb.append(soResultDateTime);
        sb.append(";");

        sb.append("\n\tsoScheduleRID: Integer=");
        sb.append(soScheduleRID);
        sb.append(";");

        sb.append("\n\tsoSerModifiede: Integer=");
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

        sb.append("\n\tsoSignedDateTime: String=");
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

        sb.append("\n\tsoAdviceDateTime: String=");
        sb.append(soAdviceDateTime);
        sb.append(";");

        sb.append("\n\tsoConvertedUserRID: Integer=");
        sb.append(soConvertedUserRID);
        sb.append(";");

        sb.append("\n\tsoConvertedDateTime: String=");
        sb.append(soConvertedDateTime);
        sb.append(";");

        return sb.toString();
    }

}
