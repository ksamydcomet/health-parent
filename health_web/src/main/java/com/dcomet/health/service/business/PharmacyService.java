package com.dcomet.health.service.business;

import com.dcomet.health.domain.SalesD;
import com.dcomet.health.domain.SalesH;
import com.dcomet.health.domain.SalesHSearchRequest;
import com.dcomet.health.domain.SalesReturnD;
import com.dcomet.health.domain.SalesReturnH;
import com.dcomet.health.domain.SalesReturnHSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.dao.data.MaterialIssueHData;
import com.dcomet.health.domain.MaterialIssueD;
import com.dcomet.health.domain.MaterialIssueH;
import com.dcomet.health.domain.MaterialIssueHSearchRequest;
import java.util.List;

/**
 *
 * @author KS
 */
public interface PharmacyService extends WorkFlowService{

//------Sales----
    public SalesH getSalesH(Integer id) throws DcometServiceException;

    public SalesH getSalesByBillId(Integer billId) throws DcometServiceException;

    public List<SalesH> getSalesH(SalesHSearchRequest salesHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<SalesD> getSalesD(SalesHSearchRequest salesDSearchRequest) throws DcometServiceException;

    public void saveSalesH(SalesH salesH, boolean includeChild) throws DcometServiceException;

    public void saveSalesD(List<SalesD> salesDList) throws DcometServiceException;

    //-------SalesReturn-----------
    public SalesReturnH getSalesReturnH(Integer id) throws DcometServiceException;

    public List<SalesReturnH> getSalesReturnH(SalesReturnHSearchRequest salesReturnHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<SalesReturnD> getSalesReturnD(SalesReturnHSearchRequest salesReturnDSearchRequest) throws DcometServiceException;

    public void saveSalesReturnH(SalesReturnH salesReturnH, boolean includeChild) throws DcometServiceException;

    public void saveSalesReturnD(List<SalesReturnD> salesReturnDList);

    //-------MaterialIssueH-----

    public MaterialIssueHData saveMaterialIssueH(MaterialIssueH materialIssueH, boolean includeChild) throws DcometServiceException;

    public List<MaterialIssueH> getMaterialIssueH(MaterialIssueHSearchRequest materialIssueHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<MaterialIssueD> getMaterialIssueD(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometServiceException;

    public void saveMaterialIssueD(List<MaterialIssueD> materialIssueDList) throws DcometServiceException;
}
