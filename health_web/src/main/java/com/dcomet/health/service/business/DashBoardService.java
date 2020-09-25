package com.dcomet.health.service.business;

import com.dcomet.health.domain.dbd.DBillCollection;
import com.dcomet.health.domain.dbd.DOpdPatient;
import com.dcomet.health.domain.dbd.DOpdReferral;
import com.dcomet.health.domain.dbd.DashBoardSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import java.util.List;

public interface DashBoardService {

    public List<DOpdPatient> getDOpdPatient(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException;

    public List<DOpdReferral> getDOpdReferral(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException;

    public List<DBillCollection> getDBillCollection(DashBoardSearchRequest dashBoardSearchRequest) throws DcometServiceException;

}
