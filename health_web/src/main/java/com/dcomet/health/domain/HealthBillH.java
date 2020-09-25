package com.dcomet.health.domain;

import com.dcomet.module.billing.domain.BillH;
import java.util.List;

public class HealthBillH extends BillH {  

    private BillH billH;
    
    private List<Patient> patient;
    
    private List<SalesH> salesH;

    public List<SalesH> getSalesH() {
        return salesH;
    }

    public void setSalesH(List<SalesH> salesH) {
        this.salesH = salesH;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    public BillH getBillH() {
        return billH;
    }

    public void setBillH(BillH billH) {
        this.billH = billH;
    }
    
   
}
