package com.dcomet.health.adapter;

import com.dcomet.fw.adapter.BaseAdapter;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.dbd.DBillCollectionData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.dcomet.health.dao.data.dbd.DOpdPatientData;
import com.dcomet.health.dao.data.dbd.DOpdReferralData;
import com.dcomet.health.domain.dbd.DBillCollection;
import com.dcomet.health.domain.dbd.DOpdPatient;
import com.dcomet.health.domain.dbd.DOpdReferral;
import com.dcomet.fw.exception.DcometServiceException;

@Component("dashBoardAdapter")
public class DashBoardAdapter extends BaseAdapter {

    public List<DOpdPatient> convertDOpdPatientDataToDOpdPatient(List<DOpdPatientData> dOpdPatientDataList) throws DcometServiceException {
        List<DOpdPatient> dOpdPatientList = new ArrayList<>();
        for (DOpdPatientData dOpdPatientData : dOpdPatientDataList) {
            dOpdPatientList.add(convertDOpdPatientDataToDOpdPatient(dOpdPatientData));
        }
        return dOpdPatientList;
    }

    public DOpdPatient convertDOpdPatientDataToDOpdPatient(DOpdPatientData dOpdPatientData)
            throws DcometServiceException {
        DOpdPatient dOpdPatient = new DOpdPatient();
        if (dOpdPatientData.getId() != null) {
            dOpdPatient.setId(dOpdPatientData.getId());
        }
        if (dOpdPatientData.getEntRid() != null) {
            dOpdPatient.setEntRid(dOpdPatientData.getEntRid());
        }
        if (dOpdPatientData.getDate() != null) {
            dOpdPatient.setDate(DateUtil.convertDateToString(dOpdPatientData.getDate()));
        }
        if (dOpdPatientData.getTotalNoOfRegistration() != null) {
            dOpdPatient.setTotalNoOfRegistration(dOpdPatientData.getTotalNoOfRegistration());
        }
        if (dOpdPatientData.getTotalNoOfVisited() != null) {
            dOpdPatient.setTotalNoOfVisited(dOpdPatientData.getTotalNoOfVisited());
        }

        if (dOpdPatientData.getTotalNoOfMale() != null) {
            dOpdPatient.setTotalNoOfMale(dOpdPatientData.getTotalNoOfMale());
        }
        if (dOpdPatientData.getTotalNoOfFemale() != null) {
            dOpdPatient.setTotalNoOfFemale(dOpdPatientData.getTotalNoOfFemale());
        }
        return dOpdPatient;
    }

    public List<DOpdReferral> convertDOpdReferralDataToDOpdReferral(List<DOpdReferralData> dOpdReferralDataList) throws DcometServiceException {
        List<DOpdReferral> dOpdReferralList = new ArrayList<>();
        for (DOpdReferralData dOpdReferralData : dOpdReferralDataList) {
            dOpdReferralList.add(convertDOpdReferralDataToDOpdReferral(dOpdReferralData));
        }
        return dOpdReferralList;
    }

    public DOpdReferral convertDOpdReferralDataToDOpdReferral(DOpdReferralData dOpdReferralData)
            throws DcometServiceException {
        DOpdReferral dOpdReferral = new DOpdReferral();
        if (dOpdReferralData.getId() != null) {
            dOpdReferral.setId(dOpdReferralData.getId());
        }
        if (dOpdReferralData.getEntRid() != null) {
            dOpdReferral.setEntRid(dOpdReferralData.getEntRid());
        }
        if (dOpdReferralData.getDate() != null) {
            dOpdReferral.setDate(DateUtil.convertDateToString(dOpdReferralData.getDate()));
        }
        if (dOpdReferralData.getTotalNoOfCount() != null) {
            dOpdReferral.setTotalNoOfCount(dOpdReferralData.getTotalNoOfCount());
        }
        if (dOpdReferralData.getReferralIndex() != null) {
            dOpdReferral.setReferralIndex(dOpdReferralData.getReferralIndex());
        }
        if (dOpdReferralData.getReferralValue() != null) {
            dOpdReferral.setReferralValue(dOpdReferralData.getReferralValue());
        }
        return dOpdReferral;
    }

    public List<DBillCollection> convertDBillCollectionDataToDBillCollection(
            List<DBillCollectionData> billingCollectionDataList) throws DcometServiceException {
        List<DBillCollection> billingCollectionList = new ArrayList<>();
        for (DBillCollectionData billingCollectionData : billingCollectionDataList) {
            billingCollectionList.add(convertDBillCollectionDataToDBillCollection(billingCollectionData));
        }
        return billingCollectionList;
    }

    public DBillCollection convertDBillCollectionDataToDBillCollection(DBillCollectionData billingCollectionData)
            throws DcometServiceException {
        DBillCollection billingCollection = new DBillCollection();

        if (billingCollectionData.getId() != null) {
            billingCollection.setId(billingCollectionData.getId());
        }
        if (billingCollectionData.getTranDate() != null) {
            billingCollection.setTranDate(billingCollectionData.getTranDate());
        }
        if (billingCollectionData.getEntRid() != null) {
            billingCollection.setEntRid(billingCollectionData.getEntRid());
        }
        if (billingCollectionData.getTotalAmount() != null) {
            billingCollection.setTotalAmount(billingCollectionData.getTotalAmount());
        }
        if (billingCollectionData.getTotalCash() != null) {
            billingCollection.setTotalCash(billingCollectionData.getTotalCash());
        }
        if (billingCollectionData.getTotalCard() != null) {
            billingCollection.setTotalCard(billingCollectionData.getTotalCard());
        }
        if (billingCollectionData.getTotalCheque() != null) {
            billingCollection.setTotalCheque(billingCollectionData.getTotalCheque());
        }
        return billingCollection;

    }
}
