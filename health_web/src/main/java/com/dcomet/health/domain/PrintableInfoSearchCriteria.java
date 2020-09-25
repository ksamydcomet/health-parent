package com.dcomet.health.domain;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author MI
 */
public class PrintableInfoSearchCriteria extends SearchCriteria {

    private static final long serialVersionUID = 1L;

    private Integer peRid;
    private Integer peEntityRid;
    private String peTinNo;
    private String peCstNo;
    private String peCinNo;
    private String peUniqueIdNo;
    private String peTaxNo;
    private String peName;
    private String pePhoneNo;
    private String peEmail;
    private String peWebsite;
    private String peAddress;
    private String peCity;
    private String peCountry;
    private String peIsExcisable;
    private String peRegion;
    private String peDivision;
    private String peCommissionerate;
    private String peOthers;
    private byte[] peLogo;

    public Integer getPeRid() {
        return peRid;
    }

    public void setPeRid(Integer peRid) {
        this.peRid = peRid;
    }

    public Integer getPeEntityRid() {
        return peEntityRid;
    }

    public void setPeEntityRid(Integer peEntityRid) {
        this.peEntityRid = peEntityRid;
    }

    public String getPeTinNo() {
        return peTinNo;
    }

    public void setPeTinNo(String peTinNo) {
        this.peTinNo = peTinNo;
    }

    public String getPeCstNo() {
        return peCstNo;
    }

    public void setPeCstNo(String peCstNo) {
        this.peCstNo = peCstNo;
    }

    public String getPeCinNo() {
        return peCinNo;
    }

    public void setPeCinNo(String peCinNo) {
        this.peCinNo = peCinNo;
    }

    public String getPeUniqueIdNo() {
        return peUniqueIdNo;
    }

    public void setPeUniqueIdNo(String peUniqueIdNo) {
        this.peUniqueIdNo = peUniqueIdNo;
    }

    public String getPeTaxNo() {
        return peTaxNo;
    }

    public void setPeTaxNo(String peTaxNo) {
        this.peTaxNo = peTaxNo;
    }

    public String getPeName() {
        return peName;
    }

    public void setPeName(String peName) {
        this.peName = peName;
    }

    public String getPePhoneNo() {
        return pePhoneNo;
    }

    public void setPePhoneNo(String pePhoneNo) {
        this.pePhoneNo = pePhoneNo;
    }

    public String getPeEmail() {
        return peEmail;
    }

    public void setPeEmail(String peEmail) {
        this.peEmail = peEmail;
    }

    public String getPeWebsite() {
        return peWebsite;
    }

    public void setPeWebsite(String peWebsite) {
        this.peWebsite = peWebsite;
    }

    public String getPeAddress() {
        return peAddress;
    }

    public void setPeAddress(String peAddress) {
        this.peAddress = peAddress;
    }

    public String getPeCity() {
        return peCity;
    }

    public void setPeCity(String peCity) {
        this.peCity = peCity;
    }

    public String getPeCountry() {
        return peCountry;
    }

    public void setPeCountry(String peCountry) {
        this.peCountry = peCountry;
    }

    public String getPeIsExcisable() {
        return peIsExcisable;
    }

    public void setPeIsExcisable(String peIsExcisable) {
        this.peIsExcisable = peIsExcisable;
    }

    public String getPeRegion() {
        return peRegion;
    }

    public void setPeRegion(String peRegion) {
        this.peRegion = peRegion;
    }

    public String getPeDivision() {
        return peDivision;
    }

    public void setPeDivision(String peDivision) {
        this.peDivision = peDivision;
    }

    public String getPeCommissionerate() {
        return peCommissionerate;
    }

    public void setPeCommissionerate(String peCommissionerate) {
        this.peCommissionerate = peCommissionerate;
    }

    public String getPeOthers() {
        return peOthers;
    }

    public void setPeOthers(String peOthers) {
        this.peOthers = peOthers;
    }

    public byte[] getPeLogo() {
        return peLogo;
    }

    public void setPeLogo(byte[] peLogo) {
        this.peLogo = peLogo;
    }

    @Override
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tpeRid: Integer=");
        sb.append(peRid);
        sb.append(";");

        sb.append("\n\tpeEntityRid: Integer=");
        sb.append(peEntityRid);
        sb.append(";");

        sb.append("\n\tpeTinNo: String=");
        sb.append(peTinNo);
        sb.append(";");

        sb.append("\n\tpeCstNo: String=");
        sb.append(peCstNo);
        sb.append(";");

        sb.append("\n\tpeCinNo: String=");
        sb.append(peCinNo);
        sb.append(";");

        sb.append("\n\tpeUniqueIdNo: String=");
        sb.append(peUniqueIdNo);
        sb.append(";");

        sb.append("\n\tpeTaxNo: String=");
        sb.append(peTaxNo);
        sb.append(";");

        sb.append("\n\tpeName: String=");
        sb.append(peName);
        sb.append(";");

        sb.append("\n\tpePhoneNo: String=");
        sb.append(pePhoneNo);
        sb.append(";");

        sb.append("\n\tpeEmail: String=");
        sb.append(peEmail);
        sb.append(";");

        sb.append("\n\tpeWebsite: String=");
        sb.append(peWebsite);
        sb.append(";");

        sb.append("\n\tpeAddress: String=");
        sb.append(peAddress);
        sb.append(";");

        sb.append("\n\tpeCity: String=");
        sb.append(peCity);
        sb.append(";");

        sb.append("\n\tpeCountry: String=");
        sb.append(peCountry);
        sb.append(";");

        sb.append("\n\tpeIsExcisable: String=");
        sb.append(peIsExcisable);
        sb.append(";");

        sb.append("\n\tpeRegion: String=");
        sb.append(peRegion);
        sb.append(";");

        sb.append("\n\tpeDivision: String=");
        sb.append(peDivision);
        sb.append(";");

        sb.append("\n\tpeOthers: String=");
        sb.append(peOthers);
        sb.append(";");

        sb.append("\n\tpeLogo: byte=");
        sb.append(peLogo);
        sb.append(";");

        return sb.toString();
    }

}
