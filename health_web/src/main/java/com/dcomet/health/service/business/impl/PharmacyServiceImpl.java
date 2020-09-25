package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.health.adapter.PharmacyAdapter;
import com.dcomet.health.dao.PharmacyDAO;
import com.dcomet.health.dao.data.SalesDData;
import com.dcomet.health.dao.data.SalesHData;
import com.dcomet.health.dao.data.SalesReturnDData;
import com.dcomet.health.dao.data.SalesReturnHData;
import com.dcomet.module.billing.domain.AdvanceDetails;
import com.dcomet.health.domain.SalesD;
import com.dcomet.health.domain.SalesH;
import com.dcomet.health.domain.SalesHSearchRequest;
import com.dcomet.health.domain.SalesReturnD;
import com.dcomet.health.domain.SalesReturnH;
import com.dcomet.health.domain.SalesReturnHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.workflow.domain.BOState;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.dao.data.MaterialIssueDData;
import com.dcomet.health.dao.data.MaterialIssueHData;
import com.dcomet.health.domain.MaterialIssueD;
import com.dcomet.health.domain.MaterialIssueH;
import com.dcomet.health.domain.MaterialIssueHSearchRequest;
import com.dcomet.health.service.business.AdvanceDetailsService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.InventoryService;
import com.dcomet.health.service.business.PharmacyService;
import com.dcomet.module.domain.AutoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("pharmacyService")
@Transactional(propagation = Propagation.SUPPORTS)
public class PharmacyServiceImpl extends BaseWorkFlowServiceImpl implements PharmacyService {

    @Autowired
    @Qualifier("dataDictionaryService")
    private DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("pharmacyDAO")
    PharmacyDAO pharmacyDAO;

    @Autowired
    @Qualifier("pharmacyAdapter")
    PharmacyAdapter pharmacyAdapter;

    @Autowired
    @Qualifier("inventoryService")
    InventoryService inventoryService;

    @Autowired
    @Qualifier("advanceDetailsService")
    AdvanceDetailsService advanceDetailsService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) throws DcometServiceException {
        MaterialIssueH materialIssueH = (MaterialIssueH) object;
        if (materialIssueH.getId() == null) {
            AutoNumber autoNumber = dataDictionaryService.getAutoNumberByCategory("MAT", materialIssueH.getEntityRid());
            materialIssueH.setMatIssueNo(autoNumber.getAutoNumber());
            materialIssueH.setMatIssuePrefix(autoNumber.getAutoPrefix());
            materialIssueH.setMatIssueSequence(autoNumber.getAutoSequenceNumber());
            dataDictionaryService.saveAutoNumberIncrement("MAT", materialIssueH.getEntityRid());
        }
        materialIssueH.setMatStatus(nextState);
        materialIssueH.setMatState(nextState);
        MaterialIssueHData materialIssueHData = saveMaterialIssueH(materialIssueH, true);
        return materialIssueHData.getId();
    }

    @Override
    public Integer getCurrentState(Integer boRID) throws DcometServiceException {
        Integer boState = 0;
        List<MaterialIssueHData> materialIssueHDataList = load(boRID);
        if (CollectionUtils.isNotEmpty(materialIssueHDataList)) {
            boState = materialIssueHDataList.get(0).getMatStatus();
        }
        return boState;
    }

    @Override
    public String buildBODescriptor(String[] strings, Integer boRID) throws DcometServiceException {
        List<MaterialIssueHData> materialIssueHDataList = load(boRID);
        StringBuilder boDescriptor = new StringBuilder();
        if (CollectionUtils.isNotEmpty(materialIssueHDataList)) {
            boDescriptor.append(materialIssueHDataList.get(0).getMatIssueNo()).append("&");
            boDescriptor.append(DateUtil.convertCalendarToString(materialIssueHDataList.get(0).getCreatedDateTime()));
        }
        return boDescriptor.toString();
    }

    private List<MaterialIssueHData> load(Integer boRID) {
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("id", boRID));
        List<MaterialIssueHData> materialIssueHDataList = pharmacyDAO.getMaterialIssueHList(criterionList);
        return materialIssueHDataList;
    }

    @Override
    public SalesH getSalesH(Integer id) throws DcometServiceException {
        SalesHSearchRequest salesHSearchRequest = new SalesHSearchRequest();
        salesHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<SalesH> salesHList = getSalesH(salesHSearchRequest, true);
        return CollectionUtils.isNotEmpty(salesHList) ? salesHList.get(0) : null;
    }

    @Override
    public SalesH getSalesByBillId(Integer billId) throws DcometServiceException {
        SalesHSearchRequest salesHSearchRequest = new SalesHSearchRequest();
        salesHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("salBhRID", billId)}));
        List<SalesH> salesHList = getSalesH(salesHSearchRequest, true);
        return CollectionUtils.isNotEmpty(salesHList) ? salesHList.get(0) : null;
    }

    //-------Sales-------
    @Override
    public List<SalesH> getSalesH(SalesHSearchRequest salesHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<SalesH> result = null;
        try {
            List<SalesHData> resultData = pharmacyDAO.getSalesH(salesHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertSalesHDataToSalesH(resultData);
                if (includeChilds) {
                    for (SalesH salesH : result) {
                        SalesHSearchRequest childSearchRequest = new SalesHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("salHRID", salesH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<SalesD> salesDResult = getSalesD(childSearchRequest);
                        if (salesDResult != null && !salesDResult.isEmpty()) {
                            salesH.setSalesD(salesDResult);
                        }
                    }

                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<SalesD> getSalesD(SalesHSearchRequest salesDSearchRequest) throws DcometServiceException {
        List<SalesD> result = null;
        try {
            List<SalesDData> resultData = pharmacyDAO.getSalesD(salesDSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertSalesDDataToSalesD(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSalesH(SalesH salesH, boolean includeChild) throws DcometServiceException {
        try {
            Integer stkType = 0, tranCode = 0, ddStkTypeIndex = 4;
            String curDatetime = salesH.getCurrentDateTimeByUTZ(), ddStockType = "Book Stock";
            Float ReceiptQty = 0.00f;
            SalesHData salesHData = pharmacyAdapter.convertSalesHToSalesHData(salesH);
            pharmacyDAO.saveSalesH(salesHData);
            if (includeChild) {

                if (CollectionUtils.isNotEmpty(salesH.getSalesD())) {
                    List<SalesDData> salesDDataList = pharmacyAdapter.convertSalesDToSalesDData(salesH.getSalesD());
                    for (SalesDData salesDData : salesDDataList) {
                        salesDData.setSalHRID(salesHData.getId());
                        inventoryService.handleStock(stkType, tranCode, false, salesDData.getSalSkuRID(), salesDData.getSalSkuSecRID(),
                                salesDData.getSalBatchNo(), salesDData.getSalExpDate(), ReceiptQty, salesDData.getSalReturnQty(), salesDData.getSalBaseUom(),
                                salesDData.getSalRate(), salesDData.getSalRate(), salesHData.getSalBillNo(), salesHData.getSalBhRID(),
                                curDatetime, ddStockType, ddStkTypeIndex, salesHData.getSalEntityRID(), salesH.getUnitRid());
                    }
                    pharmacyDAO.saveSalesD(salesDDataList);
                }
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public void saveSalesD(List<SalesD> salesDList) {
        try {
            List<SalesDData> salesDDataList = pharmacyAdapter.convertSalesDToSalesDData(salesDList);
            pharmacyDAO.saveSalesD(salesDDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    //----------SalesReturn-------
    @Override
    public SalesReturnH getSalesReturnH(Integer id) throws DcometServiceException {
        SalesReturnHSearchRequest salesReturnHSearchRequest = new SalesReturnHSearchRequest();
        salesReturnHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
        List<SalesReturnH> salesReturnHList = getSalesReturnH(salesReturnHSearchRequest, true);
        return CollectionUtils.isNotEmpty(salesReturnHList) ? salesReturnHList.get(0) : null;
    }

    @Override
    public List<SalesReturnH> getSalesReturnH(SalesReturnHSearchRequest salesReturnHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<SalesReturnH> result = null;
        try {
            List<SalesReturnHData> resultData = pharmacyDAO.getSalesReturnH(salesReturnHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertSalesReturnHDataToSalesReturnH(resultData);
                if (includeChilds) {
                    for (SalesReturnH salesReturnH : result) {
                        SalesReturnHSearchRequest childSearchRequest = new SalesReturnHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("srdSrhRID", salesReturnH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<SalesReturnD> salesReturnDResult = getSalesReturnD(childSearchRequest);
                        if (salesReturnDResult != null && !salesReturnDResult.isEmpty()) {
                            salesReturnH.setSalesReturnD(salesReturnDResult);
                        }
                    }

                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public List<SalesReturnD> getSalesReturnD(SalesReturnHSearchRequest salesReturnDSearchRequest) throws DcometServiceException {
        List<SalesReturnD> result = null;
        try {
            List<SalesReturnDData> resultData = pharmacyDAO.getSalesReturnD(salesReturnDSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertSalesReturnDDataToSalesReturnD(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    private void stockBysaveSalesH(SalesReturnH salesReturnH, SalesReturnHData salesReturnHData, List<SalesReturnDData> salesReturnDDataList) {
        Integer UOM = 0, ddStkTypeIndex = 4;
        Float issueQty = 0.00f;
        for (SalesReturnDData salesReturnDData : salesReturnDDataList) {
            inventoryService.handleStock(1, 1, false, salesReturnDData.getSrdSkuRID(), salesReturnDData.getSrdSkuSecRID(), salesReturnDData.getSrdItemBatchNo(),
                    salesReturnDData.getSrdItemExpDate(), salesReturnDData.getSrdReturnQty(), issueQty, UOM, salesReturnDData.getSrdReturnRate(),
                    salesReturnDData.getSrdReturnRate(), salesReturnHData.getSrhBillNo(), salesReturnHData.getSrhBIllRID(),
                    salesReturnH.getCurrentDateTimeByUTZ(), "Book Stock", ddStkTypeIndex, salesReturnHData.getSrhEntityRID(), salesReturnH.getUnitRid());
        }
    }

    private void updateReturnStockInSalesD(SalesReturnHData salesReturnHData, List<SalesReturnDData> salesReturnDDataList) {
        SalesHSearchRequest salesHSearchRequest = new SalesHSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("salBhRID", salesReturnHData.getSrhBIllRID()));
        salesHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<SalesDData> salesDDataList = pharmacyAdapter.convertSalesDToSalesDData(getSalesH(salesHSearchRequest, true).get(0).getSalesD());
        if (CollectionUtils.isNotEmpty(salesDDataList)) {
            for (SalesDData salesDData : salesDDataList) {
                for (SalesReturnDData salesReturnDData : salesReturnDDataList) {
                    if (Objects.equals(salesReturnDData.getSrdBIllRID(), salesDData.getId())) {
                        Float rQty = salesDData.getSalReturnQty();
                        rQty = rQty - salesReturnDData.getSrdReturnQty();
                        salesDData.setSalReturnQty(rQty);
                    }
                }
                pharmacyDAO.saveSalesD(salesDDataList);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSalesReturnH(SalesReturnH salesReturnH, boolean includeChild) throws DcometServiceException {
        try {
            SalesReturnHData salesReturnHData = pharmacyAdapter.convertSalesReturnHToSalesReturnHData(salesReturnH);
            pharmacyDAO.saveSalesReturnH(salesReturnHData);
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(salesReturnH.getSalesReturnD())) {
                    List<SalesReturnDData> salesReturnDDataList = pharmacyAdapter.convertSalesReturnDToSalesReturnDData(salesReturnH.getSalesReturnD());
                    for (SalesReturnDData salesReturnDData : salesReturnDDataList) {
                        salesReturnDData.setSrdSrhRID(salesReturnHData.getId());
                    }
                    pharmacyDAO.saveSalesReturnD(salesReturnDDataList);
                    stockBysaveSalesH(salesReturnH, salesReturnHData, salesReturnDDataList);
                    updateReturnStockInSalesD(salesReturnHData, salesReturnDDataList);
                }
            }

            if (salesReturnH.getSrhPaidAmount() != 0) {
                AdvanceDetails advanceDetails = new AdvanceDetails();
                advanceDetails.setAdType(0);
                advanceDetails.setAdRefRID(salesReturnHData.getSrhBIllRID());
                advanceDetails.setAdPayerRID(salesReturnHData.getSrhPatientRID()); //PayerRID or PatientRID
                advanceDetails.setAdPayerName(salesReturnHData.getSrhPatientName());
                advanceDetails.setAdPayerNo(salesReturnHData.getSrhPatientMrn());
                advanceDetails.setAdAmount(salesReturnH.getSrhPaidAmount());
                advanceDetails.setAdBalanceAmount(salesReturnH.getSrhPaidAmount());
                advanceDetails.setAdCreationMode(1);
                advanceDetails.setCurrentObject(salesReturnH);
                advanceDetailsService.save(advanceDetails, BOState.IDRAFT, "ADVANCE", "CREATE_ADVANCE");
            }

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public void saveSalesReturnD(List<SalesReturnD> salesReturnDList) {
        try {
            List<SalesReturnDData> salesReturnDDataList = pharmacyAdapter.convertSalesReturnDToSalesReturnDData(salesReturnDList);
            pharmacyDAO.saveSalesReturnD(salesReturnDDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MaterialIssueHData saveMaterialIssueH(MaterialIssueH materialIssueH, boolean includeChild) throws DcometServiceException {
        try {
            materialIssueH.setMatEntRid(materialIssueH.getEntityRid());
            MaterialIssueHData materialIssueHData = pharmacyAdapter.convertMaterialIssueHToMaterialIssueHData(materialIssueH);
            pharmacyDAO.saveMaterialIssueH(materialIssueHData);
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(materialIssueH.getMaterialIssueD())) {
                    List<MaterialIssueDData> materialIssueDDataList = pharmacyAdapter.convertMaterialIssueDToMaterialIssueDData(materialIssueH.getMaterialIssueD());
                    for (MaterialIssueDData materialIssueDData : materialIssueDDataList) {
                        materialIssueDData.setMatIssueHRid(materialIssueHData.getId());
                    }
                    pharmacyDAO.saveMaterialIssueD(materialIssueDDataList);
                }
            }
            return materialIssueHData;

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

    }

    @Override
    public List<MaterialIssueH> getMaterialIssueH(MaterialIssueHSearchRequest materialIssueHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<MaterialIssueH> result = null;
        try {
            List<MaterialIssueHData> resultData = pharmacyDAO.getMaterialIssueH(materialIssueHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertMaterialIssueHDataToMaterialIssueH(resultData);
                if (includeChilds) {
                    for (MaterialIssueH materialIssueH : result) {
                        MaterialIssueHSearchRequest childSearchRequest = new MaterialIssueHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("matIssueHRid", materialIssueH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<MaterialIssueD> materialIssueDResult = getMaterialIssueD(childSearchRequest);
                        if (materialIssueDResult != null && !materialIssueDResult.isEmpty()) {
                            materialIssueH.setMaterialIssueD(materialIssueDResult);
                        }
                    }
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

    @Override
    public void saveMaterialIssueD(List<MaterialIssueD> materialIssueDList) throws DcometServiceException {
        try {
            List<MaterialIssueDData> materialIssueDDataList = pharmacyAdapter.convertMaterialIssueDToMaterialIssueDData(materialIssueDList);
            pharmacyDAO.saveMaterialIssueD(materialIssueDDataList);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<MaterialIssueD> getMaterialIssueD(MaterialIssueHSearchRequest materialIssueHSearchRequest) throws DcometServiceException {
        List<MaterialIssueD> result = null;
        try {
            List<MaterialIssueDData> resultData = pharmacyDAO.getMaterialIssueD(materialIssueHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = pharmacyAdapter.convertMaterialIssueDDataToMaterialIssueD(resultData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }
}
