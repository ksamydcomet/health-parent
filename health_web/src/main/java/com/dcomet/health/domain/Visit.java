package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import com.dcomet.module.billing.domain.BillH;
import java.io.Serializable;
import java.util.List;

public class Visit extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer visTypeIndex;
    private Integer visSubTypeIndex;
    private Integer visPatRid;
    private String visPatType;
    private String visDate;
    private Integer visApptRid;
    private Integer visEpisodeRid;
    private Integer visSpecialityIndex;
    private Integer visReasonIndex;
    private String visRemarks;
    private Integer visConsDocRid;
    private Integer visAttnDocRid;
    private String visRefTypeIndex;
    private Integer visRefRid;     
    private String visRefName;
    private String visDocRemarks;
    private Integer visState;
    private Integer visStatus;
    private Integer visIsCompleted;
    private String visDiagnosis;
    private String visHistory;
    private String visCsNodes;
    private String visTreatment;

    private String visSpecialityName;
    private String visReasonName;
    private Integer visFreeConsultationMappingRid;

    List<HealthBillH> billH;
    private List<BillH> billH1;
    List<Patient> patient;
    List<ServiceOrder> serviceOrder;
    private List<Complaints> complaints;
    private List<VisitPlan> visitPlan;
    private List<VisitTemplate> visitTemplate;
    private List<ServiceRequest> serviceRequest;
    private List<ServiceRequest> serviceRequestDrug;
    private List<VisitVitals> visitVitals;
    private List<BedOccupancy> bedOccupancy;
    private List<History> historyList;
    private FreeConsultationPatientDoctorMap consultationPatientDoctorMap;
    private AdmissionDetails admissionDetails;
    private List<ProcedureRequestH> procedureRequestHs;

    public Visit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisTypeIndex() {
        return visTypeIndex;
    }

    public void setVisTypeIndex(Integer visTypeIndex) {
        this.visTypeIndex = visTypeIndex;
    }

    public Integer getVisSubTypeIndex() {
        return visSubTypeIndex;
    }

    public void setVisSubTypeIndex(Integer visSubTypeIndex) {
        this.visSubTypeIndex = visSubTypeIndex;
    }

    public Integer getVisPatRid() {
        return visPatRid;
    }

    public void setVisPatRid(Integer visPatRid) {
        this.visPatRid = visPatRid;
    }

    public String getVisPatType() {
        return visPatType;
    }

    public void setVisPatType(String visPatType) {
        this.visPatType = visPatType;
    }

    public Integer getVisApptRid() {
        return visApptRid;
    }

    public void setVisApptRid(Integer visApptRid) {
        this.visApptRid = visApptRid;
    }

    public Integer getVisEpisodeRid() {
        return visEpisodeRid;
    }

    public List<ServiceRequest> getServiceRequestDrug() {
        return serviceRequestDrug;
    }

    public void setServiceRequestDrug(List<ServiceRequest> serviceRequestDrug) {
        this.serviceRequestDrug = serviceRequestDrug;
    }

    public void setVisEpisodeRid(Integer visEpisodeRid) {
        this.visEpisodeRid = visEpisodeRid;
    }

    public Integer getVisReasonIndex() {
        return visReasonIndex;
    }

    public void setVisReasonIndex(Integer visReasonIndex) {
        this.visReasonIndex = visReasonIndex;
    }

    public String getVisRemarks() {
        return visRemarks;
    }

    public void setVisRemarks(String visRemarks) {
        this.visRemarks = visRemarks;
    }

    public Integer getVisConsDocRid() {
        return visConsDocRid;
    }

    public void setVisConsDocRid(Integer visConsDocRid) {
        this.visConsDocRid = visConsDocRid;
    }

    public Integer getVisAttnDocRid() {
        return visAttnDocRid;
    }

    public void setVisAttnDocRid(Integer visAttnDocRid) {
        this.visAttnDocRid = visAttnDocRid;
    }

    public String getVisRefTypeIndex() {
        return visRefTypeIndex;
    }

    public void setVisRefTypeIndex(String visRefTypeIndex) {
        this.visRefTypeIndex = visRefTypeIndex;
    }

    public Integer getVisRefRid() {
        return visRefRid;
    }

    public void setVisRefRid(Integer visRefRid) {
        this.visRefRid = visRefRid;
    }

    public String getVisDocRemarks() {
        return visDocRemarks;
    }

    public void setVisDocRemarks(String visDocRemarks) {
        this.visDocRemarks = visDocRemarks;
    }

    public String getVisRefName() {
        return visRefName;
    }

    public void setVisRefName(String visRefName) {
        this.visRefName = visRefName;
    }

    public Integer getVisState() {
        return visState;
    }

    public void setVisState(Integer visState) {
        this.visState = visState;
    }

    public Integer getVisStatus() {
        return visStatus;
    }

    public void setVisStatus(Integer visStatus) {
        this.visStatus = visStatus;
    }

    public String getVisCsNodes() {
        return visCsNodes;
    }

    public void setVisCsNodes(String visCsNodes) {
        this.visCsNodes = visCsNodes;
    }

    public String getVisTreatment() {
        return visTreatment;
    }

    public void setVisTreatment(String visTreatment) {
        this.visTreatment = visTreatment;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    public List<ServiceOrder> getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(List<ServiceOrder> serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public List<Complaints> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaints> complaints) {
        this.complaints = complaints;
    }

    public List<VisitPlan> getVisitPlan() {
        return visitPlan;
    }

    public void setVisitPlan(List<VisitPlan> visitPlan) {
        this.visitPlan = visitPlan;
    }

    public List<VisitTemplate> getVisitTemplate() {
        return visitTemplate;
    }

    public void setVisitTemplate(List<VisitTemplate> visitTemplate) {
        this.visitTemplate = visitTemplate;
    }

    public List<VisitVitals> getVisitVitals() {
        return visitVitals;
    }

    public void setVisitVitals(List<VisitVitals> visitVitals) {
        this.visitVitals = visitVitals;
    }

    public List<ServiceRequest> getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(List<ServiceRequest> serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public List<HealthBillH> getBillH() {
        return billH;
    }

    public void setBillH(List<HealthBillH> billH) {
        this.billH = billH;
    }

    public List<BillH> getBillH1() {
        return billH1;
    }

    public void setBillH1(List<BillH> billH1) {
        this.billH1 = billH1;
    }

    public String getVisDate() {
        return visDate;
    }

    public void setVisDate(String visDate) {
        this.visDate = visDate;
    }

    public Integer getVisSpecialityIndex() {
        return visSpecialityIndex;
    }

    public void setVisSpecialityIndex(Integer visSpecialityIndex) {
        this.visSpecialityIndex = visSpecialityIndex;
    }

    public String getVisSpecialityName() {
        return visSpecialityName;
    }

    public void setVisSpecialityName(String visSpecialityName) {
        this.visSpecialityName = visSpecialityName;
    }

    public String getVisReasonName() {
        return visReasonName;
    }

    public void setVisReasonName(String visReasonName) {
        this.visReasonName = visReasonName;
    }

    public Integer getVisFreeConsultationMappingRid() {
        return visFreeConsultationMappingRid;
    }

    public void setVisFreeConsultationMappingRid(Integer visFreeConsultationMappingRid) {
        this.visFreeConsultationMappingRid = visFreeConsultationMappingRid;
    }

    public Integer getVisIsCompleted() {
        return visIsCompleted;
    }

    public void setVisIsCompleted(Integer visIsCompleted) {
        this.visIsCompleted = visIsCompleted;
    }

    public String getVisDiagnosis() {
        return visDiagnosis;
    }

    public void setVisDiagnosis(String visDiagnosis) {
        this.visDiagnosis = visDiagnosis;
    }

    public AdmissionDetails getAdmissionDetails() {
        return admissionDetails;
    }

    public void setAdmissionDetails(AdmissionDetails admissionDetails) {
        this.admissionDetails = admissionDetails;
    }

    public String getVisHistory() {
        return visHistory;
    }

    public void setVisHistory(String visHistory) {
        this.visHistory = visHistory;
    }

    public List<BedOccupancy> getBedOccupancy() {
        return bedOccupancy;
    }

    public void setBedOccupancy(List<BedOccupancy> bedOccupancy) {
        this.bedOccupancy = bedOccupancy;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public FreeConsultationPatientDoctorMap getConsultationPatientDoctorMap() {
        return consultationPatientDoctorMap;
    }

    public void setConsultationPatientDoctorMap(FreeConsultationPatientDoctorMap consultationPatientDoctorMap) {
        this.consultationPatientDoctorMap = consultationPatientDoctorMap;
    }

    public List<ProcedureRequestH> getProcedureRequestHs() {
        return procedureRequestHs;
    }

    public void setProcedureRequestHs(List<ProcedureRequestH> procedureRequestHs) {
        this.procedureRequestHs = procedureRequestHs;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tvisTypeIndex: Integer=");
        sb.append(visTypeIndex);
        sb.append(";");

        sb.append("\n\tvisSubTypeIndex: String=");
        sb.append(visSubTypeIndex);
        sb.append(";");

        sb.append("\n\tvisPatRid: Integer=");
        sb.append(visPatRid);
        sb.append(";");

        sb.append("\n\tvisPatType: String=");
        sb.append(visPatType);
        sb.append(";");

        sb.append("\n\tvisDate: String=");
        sb.append(visDate);
        sb.append(";");

        sb.append("\n\tvisDate: String=");
        sb.append(visTypeIndex);
        sb.append(";");

        sb.append("\n\tvisApptRid: String=");
        sb.append(visApptRid);
        sb.append(";");

        sb.append("\n\tvisEpisodeRid: Integer=");
        sb.append(visEpisodeRid);
        sb.append(";");

        sb.append("\n\tvisSpecialityIndex: Integer=");
        sb.append(visSpecialityIndex);
        sb.append(";");

        sb.append("\n\tvisReasonIndex: String=");
        sb.append(visReasonIndex);
        sb.append(";");

        sb.append("\n\tvisRemarks: String=");
        sb.append(visRemarks);
        sb.append(";");

        sb.append("\n\tvisConsDocRid: String=");
        sb.append(visConsDocRid);
        sb.append(";");

        sb.append("\n\tvisAttnDocRid: String=");
        sb.append(visAttnDocRid);
        sb.append(";");

        sb.append("\n\tvisRefTypeIndex: String=");
        sb.append(visRefTypeIndex);
        sb.append(";");

        sb.append("\n\tvisRefRid: String=");
        sb.append(visRefRid);
        sb.append(";");

        sb.append("\n\tvisDocRemarks: String=");
        sb.append(visDocRemarks);
        sb.append(";");

        sb.append("\n\tvisRefName: String=");
        sb.append(visRefName);
        sb.append(";");

        sb.append("\n\tvisState: String=");
        sb.append(visState);
        sb.append(";");

        sb.append("\n\tvisStatus: String=");
        sb.append(visStatus);
        sb.append(";");

        sb.append("\n\tvisSpecialityName: String=");
        sb.append(visSpecialityName);
        sb.append(";");

        sb.append("\n\tvisReasonName: String=");
        sb.append(visReasonName);
        sb.append(";");

        sb.append("\n\tvisFreeConsultationMappingRid: Integer=");
        sb.append(visFreeConsultationMappingRid);
        sb.append(";");

        sb.append("\n\tvisIsCompleted: Integer=");
        sb.append(visIsCompleted);
        sb.append(";");

        sb.append("\n\tvisDiagnosis: String=");
        sb.append(visDiagnosis);
        sb.append(";");

        sb.append("\n\tvisHistory: String=");
        sb.append(visHistory);
        sb.append(";");

        sb.append("\n\tvisCsNodes: String=");
        sb.append(visCsNodes);
        sb.append(";");

        sb.append("\n\tvisTreatment: String=");
        sb.append(visTreatment);
        sb.append(";");

        sb.append("\n\tadmissionDetails: String=");
        sb.append(admissionDetails);
        sb.append(";");

        return sb.toString();

    }
}
