package com.dcomet.health.dao;

import com.dcomet.health.dao.data.SalesDData;
import com.dcomet.health.dao.data.SalesHData;
import com.dcomet.health.dao.data.SalesReturnDData;
import com.dcomet.health.dao.data.SalesReturnHData;
import com.dcomet.health.domain.SalesHSearchRequest;
import com.dcomet.health.domain.SalesReturnHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.BedGroupMData;
import com.dcomet.health.dao.data.MaterialIssueDData;
import com.dcomet.health.dao.data.MaterialIssueHData;
import com.dcomet.health.domain.MaterialIssueHSearchRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author KS
 */
public interface PharmacyDAO {

    //--------Sales--------
    public List<SalesHData> getSalesH(SalesHSearchRequest userSearchRequest) throws DcometDAOException;

    public List<SalesDData> getSalesD(SalesHSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveSalesH(SalesHData salesH) throws DcometDAOException;

    public void saveSalesD(List<SalesDData> salesD) throws DcometDAOException;

    //----------SalesReturn---------
    public List<SalesReturnHData> getSalesReturnH(SalesReturnHSearchRequest userSearchRequest) throws DcometDAOException;

    public List<SalesReturnDData> getSalesReturnD(SalesReturnHSearchRequest userSearchRequest) throws DcometDAOException;

    public void saveSalesReturnH(SalesReturnHData salesReturnH) throws DcometDAOException;

    public void saveSalesReturnD(List<SalesReturnDData> salesReturnD) throws DcometDAOException;

    //------MaterialIssueH------
    public List<MaterialIssueHData> getMaterialIssueH(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometDAOException;

    public List<MaterialIssueHData> getMaterialIssueHList(List<Criterion> criterionList) throws DcometDAOException;

    public List<MaterialIssueDData> getMaterialIssueD(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometDAOException;

    public void saveMaterialIssueH(MaterialIssueHData materialIssueH) throws DcometDAOException;

    public void saveMaterialIssueD(List<MaterialIssueDData> materialIssueD) throws DcometDAOException;

}
