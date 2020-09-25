package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author KS
 */
public class SalesH extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer salBhRID;
    private String salBillNo;
    private String salCustomerId;
    private String salCustomerName;
    private String salCustomerAddress;
    private String salBillDate;
    private String salCntPerson;
    private String salPhoneNo;
    private Float salGrossAmount;
    private Float salTotalDiscount;
    private Float salTotalTax;
    private Float salRoundOffAmount;
    private Float salNetAmount;
    private Float salDueAmount;
    private String salStatus;
    private Float salPaidAmount;
    private String salCustomerRID;
    private Integer salVersion;
    private Integer salType;

    public List<SalesD> salesD;

    public List<SalesD> getSalesD() {
        return salesD;
    }

    public void setSalesD(List<SalesD> salesD) {
        this.salesD = salesD;
    }

    public SalesH() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalBhRID() {
        return salBhRID;
    }

    public void setSalBhRID(Integer salBhRID) {
        this.salBhRID = salBhRID;
    }

    public String getSalBillNo() {
        return salBillNo;
    }

    public void setSalBillNo(String salBillNo) {
        this.salBillNo = salBillNo;
    }

    public String getSalCustomerId() {
        return salCustomerId;
    }

    public void setSalCustomerId(String salCustomerId) {
        this.salCustomerId = salCustomerId;
    }

    public String getSalCustomerName() {
        return salCustomerName;
    }

    public void setSalCustomerName(String salCustomerName) {
        this.salCustomerName = salCustomerName;
    }

    public String getSalCustomerAddress() {
        return salCustomerAddress;
    }

    public void setSalCustomerAddress(String salCustomerAddress) {
        this.salCustomerAddress = salCustomerAddress;
    }

    public String getSalBillDate() {
        return salBillDate;
    }

    public void setSalBillDate(String salBillDate) {
        this.salBillDate = salBillDate;
    }

    public String getSalCntPerson() {
        return salCntPerson;
    }

    public void setSalCntPerson(String salCntPerson) {
        this.salCntPerson = salCntPerson;
    }

    public String getSalPhoneNo() {
        return salPhoneNo;
    }

    public void setSalPhoneNo(String salPhoneNo) {
        this.salPhoneNo = salPhoneNo;
    }

    public Float getSalGrossAmount() {
        return salGrossAmount;
    }

    public void setSalGrossAmount(Float salGrossAmount) {
        this.salGrossAmount = salGrossAmount;
    }

    public Float getSalTotalDiscount() {
        return salTotalDiscount;
    }

    public void setSalTotalDiscount(Float salTotalDiscount) {
        this.salTotalDiscount = salTotalDiscount;
    }

    public Float getSalTotalTax() {
        return salTotalTax;
    }

    public void setSalTotalTax(Float salTotalTax) {
        this.salTotalTax = salTotalTax;
    }

    public Float getSalRoundOffAmount() {
        return salRoundOffAmount;
    }

    public void setSalRoundOffAmount(Float salRoundOffAmount) {
        this.salRoundOffAmount = salRoundOffAmount;
    }

    public Float getSalNetAmount() {
        return salNetAmount;
    }

    public void setSalNetAmount(Float salNetAmount) {
        this.salNetAmount = salNetAmount;
    }

    public Float getSalDueAmount() {
        return salDueAmount;
    }

    public void setSalDueAmount(Float salDueAmount) {
        this.salDueAmount = salDueAmount;
    }

    public String getSalStatus() {
        return salStatus;
    }

    public void setSalStatus(String salStatus) {
        this.salStatus = salStatus;
    }

    public Float getSalPaidAmount() {
        return salPaidAmount;
    }

    public void setSalPaidAmount(Float salPaidAmount) {
        this.salPaidAmount = salPaidAmount;
    }

    public String getSalCustomerRID() {
        return salCustomerRID;
    }

    public void setSalCustomerRID(String salCustomerRID) {
        this.salCustomerRID = salCustomerRID;
    }

    public Integer getSalVersion() {
        return salVersion;
    }

    public void setSalVersion(Integer salVersion) {
        this.salVersion = salVersion;
    }

    public Integer getSalType() {
        return salType;
    }

    public void setSalType(Integer salType) {
        this.salType = salType;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsalBIllNo: String=");
        sb.append(salBillNo);
        sb.append(";");

        sb.append("\n\tsalCustomerId: String=");
        sb.append(salCustomerId);
        sb.append(";");

        sb.append("\n\tsalCustomerName: String=");
        sb.append(salCustomerName);
        sb.append(";");

        sb.append("\n\tsalCustomerAddress: String=");
        sb.append(salCustomerAddress);
        sb.append(";");

        sb.append("\n\tsalBillDate: String=");
        sb.append(salBillDate);
        sb.append(";");

        sb.append("\n\tsalCntPerson: String=");
        sb.append(salCntPerson);
        sb.append(";");

        sb.append("\n\tsalPhoneNo: String=");
        sb.append(salPhoneNo);
        sb.append(";");

        sb.append("\n\tsalGrossAmount: Float=");
        sb.append(salGrossAmount);
        sb.append(";");

        sb.append("\n\tsalTotalDiscount: Float=");
        sb.append(salTotalDiscount);
        sb.append(";");

        sb.append("\nt\tsalTotalTax: Float=");
        sb.append(salTotalTax);
        sb.append(";");

        sb.append("\n\tsalRoundOffAmount: Float=");
        sb.append(salRoundOffAmount);
        sb.append(";");

        sb.append("\n\tsalNetAmount: Float=");
        sb.append(salNetAmount);
        sb.append(";");

        sb.append("\n\tsalDueAmount: Float=");
        sb.append(salDueAmount);
        sb.append(";");

        sb.append("\n\tsalStatus: String=");
        sb.append(salStatus);
        sb.append(";");

        sb.append("\n\tsalPaidAmount: Integer=");
        sb.append(salPaidAmount);
        sb.append(";");

        sb.append("\n\tsalCustomerRID: String=");
        sb.append(salCustomerRID);
        sb.append(";");

        sb.append("\n\tsalVersion: Integer=");
        sb.append(salVersion);
        sb.append(";");

        sb.append("\n\tsalType: Integer=");
        sb.append(salType);
        sb.append(";");

        return sb.toString();
    }
}
