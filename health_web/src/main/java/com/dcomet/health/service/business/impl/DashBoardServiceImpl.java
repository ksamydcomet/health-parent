package com.dcomet.health.service.business.impl;

import com.dcomet.health.adapter.DashBoardAdapter;
import com.dcomet.health.dao.DashBoardDAO;
import com.dcomet.health.domain.dbd.DBillCollection;
import com.dcomet.health.domain.dbd.DOpdPatient;
import com.dcomet.health.domain.dbd.DOpdReferral;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.service.business.DashBoardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev3
 */
@Service("dashBoardService")
@Transactional(propagation = Propagation.SUPPORTS)
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    @Qualifier("dashBoardDAO")
    DashBoardDAO dashBoardDAO;

    @Autowired
    @Qualifier("dashBoardAdapter")
    DashBoardAdapter dashBoardAdapter;

    @Override
    public List<DOpdPatient> getDOpdPatient(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException {
        return dashBoardAdapter
                .convertDOpdPatientDataToDOpdPatient(dashBoardDAO.getDOpdPatientData(dashBoardSearchRequest));
    }

    @Override
    public List<DOpdReferral> getDOpdReferral(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException {
        return dashBoardAdapter
                .convertDOpdReferralDataToDOpdReferral(dashBoardDAO.getDOpdReferralData(dashBoardSearchRequest));
    }

    @Override
    public List<DBillCollection> getDBillCollection(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException {
        return dashBoardAdapter
                .convertDBillCollectionDataToDBillCollection(dashBoardDAO.getDBillCollection(dashBoardSearchRequest));
    }

}
