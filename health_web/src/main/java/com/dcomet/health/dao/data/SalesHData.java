package com.dcomet.health.dao.data;

import com.dcomet.fw.dao.Auditable;
import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Version;

/**
 *
 * @author Adhithya
 */
@Entity
@Table(name = "t_sales_h")
public class SalesHData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SAL_RID", updatable = false)
    private Integer id;

    @Column(name = "SAL_BH_RID", updatable = false)
    private Integer salBhRID;

    @Column(name = "SAL_BIL_NO", updatable = false)
    private String salBillNo;

    @Column(name = "SAL_CUS_ID")
    private String salCustomerId;

    @Column(name = "SAL_CUS_NAME")
    private String salCustomerName;

    @Column(name = "SAL_CUS_ADDRESS")
    private String salCustomerAddress;

    @Column(name = "SAL_BILL_DATE", updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date salBillDate;

    @Column(name = "SAL_CNT_PERSON")
    private String salCntPerson;

    @Column(name = "SAL_PH_NO")
    private String salPhoneNo;

    @Column(name = "SAL_GROSS_AMOUNT")
    private Float salGrossAmount;

    @Column(name = "SAL_TOTAL_DISC")
    private Float salTotalDiscount;

    @Column(name = "SAL_TOTAL_TAX")
    private Float salTotalTax;

    @Column(name = "SAL_ROUND_OFF_AMT")
    private Float salRoundOffAmount;

    @Column(name = "SAL_NET_AMT")
    private Float salNetAmount;

    @Column(name = "ENTITY_RID")
    private Integer salEntityRID;

    @Column(name = "SAL_DUE_AMT")
    private Float salDueAmount;

    @Column(name = "SAL_STATUS")
    private String salStatus;

    @Column(name = "SAL_PAID_AMT")
    private Float salPaidAmount;

    @Column(name = "SAL_CUS_RID")
    private String salCustomerRID;

    @Version
    @Column(name = "SAL_VERSION")
    private Integer salVersion;

    @Column(name = "SAL_TYPE")
    private Integer salType;

    @Column(name = "SAL_CREATED_USER_RID", updatable = false)
    private Integer createdUserRid;

    @Column(name = "SAL_CUR_DATETIME", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar createdDateTime;

    @Column(name = "SAL_MOD_USER_RID")
    private Integer modifiedUserRid;

    @Column(name = "SAL_MOD_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar modifiedDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SAL_H_RID", referencedColumnName = "SAL_RID", updatable = false)
    private List<SalesDData> salesDData;

    public SalesHData() {

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

    public Date getSalBillDate() {
        return salBillDate;
    }

    public void setSalBillDate(Date salBillDate) {
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

    public Integer getSalEntityRID() {
        return salEntityRID;
    }

    public void setSalEntityRID(Integer salEntityRID) {
        this.salEntityRID = salEntityRID;
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

    public List<SalesDData> getSalesDData() {
        return salesDData;
    }

    public void setSalesDData(List<SalesDData> salesDData) {
        this.salesDData = salesDData;
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

        sb.append("\n\tsalBillDate: Date=");
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

        sb.append("\n\tsalRoundOffAmount: Integer=");
        sb.append(salRoundOffAmount);
        sb.append(";");

        sb.append("\n\tsalNetAmount: Float=");
        sb.append(salNetAmount);
        sb.append(";");

        sb.append("\n\tsalEntityRID: Integer=");
        sb.append(salEntityRID);
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
