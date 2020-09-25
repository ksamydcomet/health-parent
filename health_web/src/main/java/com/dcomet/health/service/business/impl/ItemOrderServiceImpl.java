package com.dcomet.health.service.business.impl;

import com.dcomet.fw.domain.Base;
import com.dcomet.health.adapter.ItemOrderAdapter;
import com.dcomet.health.dao.ItemOrderDAO;
import com.dcomet.health.dao.data.FavouriteItemOrderDData;
import com.dcomet.health.dao.data.FavouriteItemOrderHData;
import com.dcomet.health.domain.FavouriteItemOrderD;
import com.dcomet.health.domain.FavouriteItemOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteItemOrderH;
import com.dcomet.health.domain.FavouriteItemOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl;
import com.dcomet.health.dao.data.DrugRequestDData;
import com.dcomet.health.dao.data.DrugRequestHData;
import com.dcomet.health.dao.data.ItemOrderData;
import com.dcomet.health.domain.DrugRequestD;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.ItemOrder;
import com.dcomet.health.domain.ItemOrderSearchRequest;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.InventoryService;
import com.dcomet.health.service.business.ItemOrderService;
import com.dcomet.health.service.business.MasterService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * @author Dev4
 */
@Service("itemOrderService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ItemOrderServiceImpl extends BaseWorkFlowServiceImpl implements ItemOrderService {

    @Autowired
    @Qualifier("itemOrderDAO")
    private ItemOrderDAO itemOrderDAO;

    @Autowired
    @Qualifier("itemOrderAdapter")
    private ItemOrderAdapter itemOrderAdapter;

    @Autowired
    @Qualifier("masterService")
    MasterService masterService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("inventoryService")
    InventoryService inventoryService;

    @Override
    public Integer save(Base object, Integer nextState, String actionCode) throws DcometServiceException {
        DrugRequestH drugRequestH = (DrugRequestH) object;
        Integer Id = null;
        try {
            drugRequestH.setDrugReqHState(nextState);
            drugRequestH.setDrugReqHStatus(nextState);

            Id = saveDrugHReturnRid(drugRequestH, true, actionCode);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return Id;
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        DrugRequestH drugRequestH = getDrugReqH(boRID);
        return drugRequestH != null ? drugRequestH.getDrugReqHState() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        DrugRequestH drugRequestH = getDrugReqH(boRID);
        return drugRequestH != null ? new StringBuilder(drugRequestH.getDrugReqHPatMrn()).append("&").append(drugRequestH.getDrugReqHPatName()).append("&").append(drugRequestH.getCreatedDateTime()).toString() : null;

    }

    private DrugRequestH getDrugReqH(Integer boRid) throws DcometServiceException {
        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
        drugRequestHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", boRid)}));
        List<DrugRequestH> drugRequestHs = getDrugH(drugRequestHSearchRequest, true);
        return CollectionUtils.isNotEmpty(drugRequestHs) ? drugRequestHs.get(0) : null;
    }

    @Override
    public List<FavouriteItemOrderD> getFavouriteItemOrderD(FavouriteItemOrderDSearchRequest favouriteItemOrderDSearchRequest) throws DcometServiceException {
        List<FavouriteItemOrderD> result = new ArrayList<>();
        List<FavouriteItemOrderDData> resultData = itemOrderDAO.getFavouriteItemOrderD(favouriteItemOrderDSearchRequest);
        if (CollectionUtils.isNotEmpty(resultData)) {
            result = itemOrderAdapter.convertFavouriteItemOrderDDataToFavouriteItemOrderD(resultData);
        }
        return result;
    }

    @Override
    public List<FavouriteItemOrderH> getFavouriteItemOrderH(FavouriteItemOrderHSearchRequest favouriteItemOrderHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<FavouriteItemOrderH> result = new ArrayList<>();
        try {
            List<FavouriteItemOrderHData> resultData = itemOrderDAO.getFavouriteItemOrderH(favouriteItemOrderHSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = itemOrderAdapter.convertFavouriteItemOrderHDataToFavouriteItemOrderH(resultData);
                if (includeChilds) {
                    for (FavouriteItemOrderH favouriteItemOrderH : result) {
                        FavouriteItemOrderDSearchRequest childSearchRequest = new FavouriteItemOrderDSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("fiodFiohRID", favouriteItemOrderH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<FavouriteItemOrderD> favouriteItemOrderDResult = getFavouriteItemOrderD(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(favouriteItemOrderDResult)) {
                            favouriteItemOrderH.setFavouriteItemOrderD(favouriteItemOrderDResult);
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
    public void saveFavouriteItemOrderH(FavouriteItemOrderH favouriteItemOrderH, boolean includeChilds) throws DcometServiceException {
        try {
            FavouriteItemOrderHData favouriteItemOrderHData = itemOrderAdapter.convertFavouriteItemOrderHToFavouriteItemOrderHData(favouriteItemOrderH);
            favouriteItemOrderHData.setFiohEntityRID(favouriteItemOrderH.getEntityRid());
            favouriteItemOrderHData.setFiohUserRID(favouriteItemOrderH.getUserRid());
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(favouriteItemOrderH.getFavouriteItemOrderD())) {
                    List<FavouriteItemOrderDData> favouriteItemOrderDList = itemOrderAdapter.convertFavouriteItemOrderDToFavouriteItemOrderDData(favouriteItemOrderH.getFavouriteItemOrderD());
                    for (FavouriteItemOrderDData favouriteItemOrderDData : favouriteItemOrderDList) {
                        favouriteItemOrderDData.setFavouriteItemOrderHData(favouriteItemOrderHData);
                    }
                    favouriteItemOrderHData.setFavouriteItemOrderDData(favouriteItemOrderDList);
                }
            }
            itemOrderDAO.saveFavouriteItemOrderH(favouriteItemOrderHData);
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
    public Integer saveDrugH(DrugRequestH drugRequestH, boolean includeChilds) throws DcometServiceException {
        try {
            DrugRequestHData drugRequestHData = itemOrderAdapter.convertDrugRequestHToDrugRequestHData(drugRequestH);
            itemOrderDAO.saveDrugH(drugRequestHData);
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(drugRequestH.getDrugRequestDList())) {
                    List<DrugRequestDData> drugRequestDDataList = itemOrderAdapter.convertDrugRequestDToDrugRequestDData(drugRequestH.getDrugRequestDList());
                    for (DrugRequestDData drugRequestDData : drugRequestDDataList) {
                        drugRequestDData.setDruReqDSrhRID(drugRequestHData.getId());
                    }
                    itemOrderDAO.saveDrugD(drugRequestDDataList);
                }
            }
            return drugRequestHData.getDrugReqHOpVBisitRID();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    private Integer saveDrugHReturnRid(DrugRequestH drugRequestH, boolean includeChilds, String actionCode) throws DcometServiceException {
        try {
            Integer UOM = 0, stkType = 0, tranCode = 1, ddStkTypeIndex = 4;
            String curDatetime = drugRequestH.getCurrentDateTimeByUTZ(), ddStockType = "Book Stock";
            Float ReceiptQty = 0.00f;

            DrugRequestHData drugRequestHData = itemOrderAdapter.convertDrugRequestHToDrugRequestHData(drugRequestH);
            itemOrderDAO.saveDrugH(drugRequestHData);
            if (includeChilds) {
                if (CollectionUtils.isNotEmpty(drugRequestH.getDrugRequestDList())) {
                    List<DrugRequestDData> drugRequestDDataList = itemOrderAdapter.convertDrugRequestDToDrugRequestDData(drugRequestH.getDrugRequestDList());
                    for (DrugRequestDData drugRequestDData : drugRequestDDataList) {
                        drugRequestDData.setDruReqDSrhRID(drugRequestHData.getId());
                        if (actionCode.equals("REQUEST")) {
                        } else {
                            inventoryService.handleStock(stkType, tranCode, false, drugRequestDData.getDrugReqDItemRID(), 0, drugRequestDData.getDrugReqDItemBatchNo(),
                                    drugRequestDData.getDrugReqDItemExpiryDate(), ReceiptQty, drugRequestDData.getDrugReqDItemQty(), 0, drugRequestDData.getDrugReqDItemPrice(), drugRequestDData.getDrugReqDItemPrice(), drugRequestH.getDrugReqHBillNo(),
                                    drugRequestH.getId(), curDatetime, ddStockType, ddStkTypeIndex, drugRequestH.getEntityRid(), drugRequestH.getUnitRid());
                        }
                    }
                    itemOrderDAO.saveDrugD(drugRequestDDataList);
                }
            }

            return drugRequestHData.getId();
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

    }

    @Override
    public List<DrugRequestH> getDrugH(DrugRequestHSearchRequest drugRequestHSearchRequest, boolean includeChilds) throws DcometServiceException {
        List<DrugRequestH> result = null;
        try {
            List<DrugRequestHData> listData = itemOrderDAO.getDrugH(drugRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = itemOrderAdapter.convertDrugRequestHDataToDrugRequestH(listData);
                if (includeChilds) {
                    for (DrugRequestH drugRequestH : result) {
                        DrugRequestHSearchRequest childSearchRequest = new DrugRequestHSearchRequest();
                        List<Criterion> searchCriterionList = new ArrayList<>();
                        searchCriterionList.add(Restrictions.eq("druReqDSrhRID", drugRequestH.getId()));
                        childSearchRequest.setSearchCriterionList(searchCriterionList);
                        List<DrugRequestD> ServiceResult = getDrugD(childSearchRequest);
                        if (CollectionUtils.isNotEmpty(ServiceResult)) {
                            drugRequestH.setDrugRequestDList(ServiceResult);
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
    public List<DrugRequestD> getDrugD(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException {
        List<DrugRequestD> result = null;
        try {
            List<DrugRequestDData> drugRequestDDatalist = itemOrderDAO.getDrugD(drugRequestHSearchRequest);
            if (CollectionUtils.isNotEmpty(drugRequestDDatalist)) {
                result = itemOrderAdapter.convertDrugRequestDDataToDrugRequestD(drugRequestDDatalist);
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

//    @Override
//    public Visit getVisit(Integer id) throws DcometServiceException {
//        VisitSearchRequest visitSearchRequest = new VisitSearchRequest();
//        visitSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("id", id)}));
//        List<Visit> result = getVisit(visitSearchRequest, false);
//        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
//    }
    @Override
    public String getDrugPrintService(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
//            List<Criterion> searchCriterionList = new ArrayList<>();
//            DrugRequestH drugRequestH = getDrugH(drugRequestHSearchRequest, true).get(0);
//            string = dataDictionaryService.getReportFromTemplate("DRUG_REPORT", drugRequestH, "drug", drugRequestHSearchRequest.getEntityRid());
//
//            Visit visit = clinicalService.getVisit(drugRequestH.getDrugReqHOpVBisitRID());
//            string = dataDictionaryService.getReportFromHTML(string, visit, "visit");
//
//            Patient patient = getPatient(visit.getVisPatRid());
//            string = dataDictionaryService.getReportFromHTML(string, patient, "patient");
//
//            searchCriterionList.clear();
//            StaffSearchRequest staffSearchRequest = new StaffSearchRequest();
//            searchCriterionList.add(Restrictions.eq("id", visit.getVisConsDocRid()));
//            searchCriterionList.add(Restrictions.eq("staffEntityRID", drugRequestHSearchRequest.getEntityRid()));
//            staffSearchRequest.setSearchCriterionList(searchCriterionList);
//            List<Staff> staffList = masterService.getStaff(staffSearchRequest, true);
//            if (CollectionUtils.isNotEmpty(staffList)) {
//                for (Staff staff : staffList) {
//                    string = dataDictionaryService.getReportFromHTML(string, staff, "staff");
//                }
//            }
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveItemOrder(List<ItemOrder> itemOrderList) throws DcometServiceException {
        try {
            for (ItemOrder itemOrder : itemOrderList) {
                ItemOrderData itemOrderData = itemOrderAdapter.convertItemOrderToItemOrderData(itemOrder);
                itemOrderDAO.saveItemOrder(itemOrderData);
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
    public List<ItemOrder> getItemOrder(ItemOrderSearchRequest itemOrderSearchRequest) throws DcometServiceException {
        List<ItemOrder> itemOrders = new ArrayList<>();
        try {
            itemOrders = itemOrderAdapter.convertItemOrderDataToItemOrder(itemOrderDAO.getItemOrder(itemOrderSearchRequest));
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return itemOrders;
    }
}
