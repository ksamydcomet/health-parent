package com.dcomet.health.dao;

import com.dcomet.health.dao.data.dbd.DBillCollectionData;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.dbd.DOpdPatientData;
import com.dcomet.health.dao.data.dbd.DOpdReferralData;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import java.util.List;

/**
 *
 * @author KS
 */
public interface DashBoardDAO {

    public List<DOpdPatientData> getDOpdPatientData(DashBoardSearchRequest dashBoardSearchRequest) throws DcometDAOException;

    public List<DOpdReferralData> getDOpdReferralData(DashBoardSearchRequest dashBoardSearchRequest) throws DcometDAOException;

    public List<DBillCollectionData> getDBillCollection(DashBoardSearchRequest dashBoardSearchRequest) throws DcometDAOException;

}
