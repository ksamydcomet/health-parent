package com.dcomet.health.domain;

import java.io.Serializable;
import com.dcomet.fw.domain.Base;
import java.util.List;

/**
 *
 * @author Dev1
 */
public class ServiceRequestH extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer serReqhId;
    private String serReqhNo;
    private String serReqhPrefix;
    private Integer serReqhSequence;
    private Integer serReqOpVisitRid;
    private Integer serReqhPatRid;
    private String serReqhPatMrn;
    private String serReqhPatName;
    private Integer serReqhBillHRid;
    private Integer serReqhBillDRid;
    private Integer serReqhLabEnHRid;
    private Integer serReqhProcedureRid;
    private Integer serReqhOpCheck;
    private Integer serReqhState;
    private Integer serReqhStatus;
    private String serReqhProcessDate;
    private Integer serReqhUnitRid;
    private Integer serReqhEntityRid;

    private Integer SerReqhVisitType;

    public List<ServiceRequest> serviceRequest;

    public ServiceRequestH() {
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

    public Integer getSerReqhLabEnHRid() {
        return serReqhLabEnHRid;
    }

    public void setSerReqhLabEnHRid(Integer serReqhLabEnHRid) {
        this.serReqhLabEnHRid = serReqhLabEnHRid;
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

    public String getSerReqhProcessDate() {
        return serReqhProcessDate;
    }

    public void setSerReqhProcessDate(String serReqhProcessDate) {
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

    public Integer getSerReqhVisitType() {
        return SerReqhVisitType;
    }

    public void setSerReqhVisitType(Integer SerReqhVisitType) {
        this.SerReqhVisitType = SerReqhVisitType;
    }

    public List<ServiceRequest> getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(List<ServiceRequest> serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tserReqhId: Integer=");
        sb.append(serReqhId);
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

        sb.append("\n\tserReqOpVisitRid: Integer=");
        sb.append(serReqOpVisitRid);
        sb.append(";");

        sb.append("\n\tserReqhPatRid: Integer=");
        sb.append(serReqhPatRid);
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

        sb.append("\n\tserReqhState: Integer=");
        sb.append(serReqhState);
        sb.append(";");

        sb.append("\n\tserReqhStatus: Integer=");
        sb.append(serReqhStatus);
        sb.append(";");

        sb.append("\n\tserReqhProcessDate: String=");
        sb.append(serReqhProcessDate);
        sb.append(";");

        sb.append("\n\tserReqhUnitRid: Integer=");
        sb.append(serReqhUnitRid);
        sb.append(";");

        sb.append("\n\tserReqhEntityRid: Integer=");
        sb.append(serReqhEntityRid);
        sb.append(";");

        sb.append("\n\tSerReqhVisitType: Integer=");
        sb.append(SerReqhVisitType);
        sb.append(";");

        return sb.toString();
    }
}
