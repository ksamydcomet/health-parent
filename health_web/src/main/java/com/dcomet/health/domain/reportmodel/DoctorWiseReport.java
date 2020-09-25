package com.dcomet.health.domain.reportmodel;


public class DoctorWiseReport {

    private String doctorName;
    private Integer ageLessthan1;
    private Integer ageUpto4;
    private Integer ageUpto19;
    private Integer ageGreaterThan20;
    private Integer ageTotalCount;

    public DoctorWiseReport(String doctorName, Integer ageLessthan1, Integer ageUpto4, Integer ageUpto19, Integer ageGreaterThan20) {
        this.doctorName = doctorName;
        this.ageLessthan1 = ageLessthan1;
        this.ageUpto4 = ageUpto4;
        this.ageUpto19 = ageUpto19;
        this.ageGreaterThan20 = ageGreaterThan20;
        this.ageTotalCount = ageLessthan1 + ageUpto4 + ageUpto19 + ageGreaterThan20;
    }

    public DoctorWiseReport() {
        super();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getAgeLessthan1() {
        return ageLessthan1;
    }

    public void setAgeLessthan1(Integer ageLessthan1) {
        this.ageLessthan1 = ageLessthan1;
    }

    public Integer getAgeUpto4() {
        return ageUpto4;
    }

    public void setAgeUpto4(Integer ageUpto4) {
        this.ageUpto4 = ageUpto4;
    }

    public Integer getAgeUpto19() {
        return ageUpto19;
    }

    public void setAgeUpto19(Integer ageUpto19) {
        this.ageUpto19 = ageUpto19;
    }

    public Integer getAgeGreaterThan20() {
        return ageGreaterThan20;
    }

    public void setAgeGreaterThan20(Integer ageGreaterThan20) {
        this.ageGreaterThan20 = ageGreaterThan20;
    }

    public Integer getAgeTotalCount() {
        return ageTotalCount;
    }

    public void setAgeTotalCount(Integer ageTotalCount) {
        this.ageTotalCount = ageTotalCount;
    }

}
