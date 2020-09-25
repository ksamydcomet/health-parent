package com.dcomet.health.adapter;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.FavouriteItemOrderDData;
import com.dcomet.health.dao.data.FavouriteItemOrderHData;
import com.dcomet.health.domain.FavouriteItemOrderD;
import com.dcomet.health.domain.FavouriteItemOrderH;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.DrugRequestDData;
import com.dcomet.health.dao.data.DrugRequestHData;
import com.dcomet.health.dao.data.ItemOrderData;
import com.dcomet.health.domain.DrugRequestD;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.ItemOrder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev4
 */
@Component("itemOrderAdapter")
public class ItemOrderAdapter {

    public List<FavouriteItemOrderD> convertFavouriteItemOrderDDataToFavouriteItemOrderD(
            List<FavouriteItemOrderDData> favouriteItemOrderDDataList) throws DcometServiceException {
        List<FavouriteItemOrderD> favouriteItemOrderDList = new ArrayList<>();
        for (FavouriteItemOrderDData favouriteItemOrderDData : favouriteItemOrderDDataList) {
            favouriteItemOrderDList.add(convertFavouriteItemOrderDDataToFavouriteItemOrderD(favouriteItemOrderDData));
        }
        return favouriteItemOrderDList;
    }

    public List<FavouriteItemOrderDData> convertFavouriteItemOrderDToFavouriteItemOrderDData(
            List<FavouriteItemOrderD> favouriteItemOrderDList) throws DcometServiceException {
        List<FavouriteItemOrderDData> favouriteItemOrderDDataList = new ArrayList<>();
        for (FavouriteItemOrderD favouriteItemOrderD : favouriteItemOrderDList) {
            favouriteItemOrderDDataList.add(convertFavouriteItemOrderDToFavouriteItemOrderDData(favouriteItemOrderD));
        }
        return favouriteItemOrderDDataList;
    }

    public FavouriteItemOrderD convertFavouriteItemOrderDDataToFavouriteItemOrderD(FavouriteItemOrderDData favouriteItemOrderDData)
            throws DcometServiceException {
        FavouriteItemOrderD favouriteItemOrderD = new FavouriteItemOrderD();
        if (favouriteItemOrderDData.getId() != null) {
            favouriteItemOrderD.setId(favouriteItemOrderDData.getId());
        }
        if (favouriteItemOrderDData.getFiodFiohRID() != null) {
            favouriteItemOrderD.setFiodFiohRID(favouriteItemOrderDData.getFiodFiohRID());
        }
        if (favouriteItemOrderDData.getFiodDrugNode() != null) {
            favouriteItemOrderD.setFiodDrugNode(favouriteItemOrderDData.getFiodDrugNode());
        }
        if (favouriteItemOrderDData.getFiodItemRID() != null) {
            favouriteItemOrderD.setFiodItemRID(favouriteItemOrderDData.getFiodItemRID());
        }
        if (favouriteItemOrderDData.getFiodLastOrderDateTime() != null) {
            favouriteItemOrderD.setFiodLastOrderDateTime(DateUtil.convertCalendarToString(favouriteItemOrderDData.getFiodLastOrderDateTime()));
        }

        return favouriteItemOrderD;

    }

    public FavouriteItemOrderDData convertFavouriteItemOrderDToFavouriteItemOrderDData(FavouriteItemOrderD favouriteItemOrderD)
            throws DcometServiceException {
        FavouriteItemOrderDData favouriteItemOrderDData = new FavouriteItemOrderDData();
        if (favouriteItemOrderD.getId() != null) {
            favouriteItemOrderDData.setId(favouriteItemOrderD.getId());
        }
        if (favouriteItemOrderD.getFiodFiohRID() != null) {
            favouriteItemOrderDData.setFiodFiohRID(favouriteItemOrderD.getFiodFiohRID());
        }
        if (favouriteItemOrderD.getFiodDrugNode() != null) {
            favouriteItemOrderDData.setFiodDrugNode(favouriteItemOrderD.getFiodDrugNode());
        }
        if (favouriteItemOrderD.getFiodItemRID() != null) {
            favouriteItemOrderDData.setFiodItemRID(favouriteItemOrderD.getFiodItemRID());
        }
        if (favouriteItemOrderD.getFiodLastOrderDateTime() != null) {
            favouriteItemOrderDData.setFiodLastOrderDateTime(DateUtil.convertStringToCalendar(favouriteItemOrderD.getFiodLastOrderDateTime()));
        }
        return favouriteItemOrderDData;
    }

    public List<FavouriteItemOrderH> convertFavouriteItemOrderHDataToFavouriteItemOrderH(
            List<FavouriteItemOrderHData> favouriteItemOrderHDataList) throws DcometServiceException {
        List<FavouriteItemOrderH> favouriteItemOrderHList = new ArrayList<>();
        for (FavouriteItemOrderHData favouriteItemOrderHData : favouriteItemOrderHDataList) {
            favouriteItemOrderHList.add(convertFavouriteItemOrderHDataToFavouriteItemOrderH(favouriteItemOrderHData));
        }
        return favouriteItemOrderHList;
    }

    public List<FavouriteItemOrderHData> convertFavouriteItemOrderHToFavouriteItemOrderHData(
            List<FavouriteItemOrderH> favouriteItemOrderHList) throws DcometServiceException {
        List<FavouriteItemOrderHData> favouriteItemOrderHDataList = new ArrayList<>();
        for (FavouriteItemOrderH favouriteItemOrderH : favouriteItemOrderHList) {
            favouriteItemOrderHDataList.add(convertFavouriteItemOrderHToFavouriteItemOrderHData(favouriteItemOrderH));
        }
        return favouriteItemOrderHDataList;
    }

    public FavouriteItemOrderH convertFavouriteItemOrderHDataToFavouriteItemOrderH(FavouriteItemOrderHData favouriteItemOrderHData)
            throws DcometServiceException {
        FavouriteItemOrderH favouriteItemOrderH = new FavouriteItemOrderH();
        if (favouriteItemOrderHData.getId() != null) {
            favouriteItemOrderH.setId(favouriteItemOrderHData.getId());
        }
        if (favouriteItemOrderHData.getFiohUserRID() != null) {
            favouriteItemOrderH.setFiohUserRID(favouriteItemOrderHData.getFiohUserRID());
        }
        if (favouriteItemOrderHData.getFiohName() != null) {
            favouriteItemOrderH.setFiohName(favouriteItemOrderHData.getFiohName());
        }
        if (favouriteItemOrderHData.getFiohEntityRID() != null) {
            favouriteItemOrderH.setEntityRid(favouriteItemOrderHData.getFiohEntityRID());
        }
        if (favouriteItemOrderHData.getCreatedDateTime() != null) {
            favouriteItemOrderH.setCreatedDateTime(DateUtil.convertCalendarToString(favouriteItemOrderHData.getCreatedDateTime()));
        }
        if (favouriteItemOrderHData.getCreatedUserRid() != null) {
            favouriteItemOrderH.setCreatedUserRid(favouriteItemOrderHData.getCreatedUserRid());
        }
        if (favouriteItemOrderHData.getModifiedDateTime() != null) {
            favouriteItemOrderH.setModifiedDateTime(DateUtil.convertCalendarToString(favouriteItemOrderHData.getModifiedDateTime()));
        }
        if (favouriteItemOrderHData.getModifiedUserRid() != null) {
            favouriteItemOrderH.setModifiedUserRid(favouriteItemOrderHData.getModifiedUserRid());
        }

        return favouriteItemOrderH;
    }

    public FavouriteItemOrderHData convertFavouriteItemOrderHToFavouriteItemOrderHData(FavouriteItemOrderH favouriteItemOrderH)
            throws DcometServiceException {
        FavouriteItemOrderHData favouriteItemOrderHData = new FavouriteItemOrderHData();
        if (favouriteItemOrderH.getId() != null) {
            favouriteItemOrderHData.setId(favouriteItemOrderH.getId());
        }
        if (favouriteItemOrderH.getFiohUserRID() != null) {
            favouriteItemOrderHData.setFiohUserRID(favouriteItemOrderH.getFiohUserRID());
        }
        if (favouriteItemOrderH.getFiohName() != null) {
            favouriteItemOrderHData.setFiohName(favouriteItemOrderH.getFiohName());
        }
        if (favouriteItemOrderH.getEntityRid() != null) {
            favouriteItemOrderHData.setFiohEntityRID(favouriteItemOrderH.getEntityRid());
        }
        if (favouriteItemOrderH.getCreatedDateTime() != null) {
            favouriteItemOrderHData.setCreatedDateTime(DateUtil.convertStringToCalendar(favouriteItemOrderH.getCreatedDateTime()));
        }
        if (favouriteItemOrderH.getCreatedUserRid() != null) {
            favouriteItemOrderHData.setCreatedUserRid(favouriteItemOrderH.getCreatedUserRid());
        }
        if (favouriteItemOrderH.getModifiedUserRid() != null) {
            favouriteItemOrderHData.setModifiedUserRid(favouriteItemOrderH.getModifiedUserRid());
        }
        return favouriteItemOrderHData;
    }

    //-------------------------------DrugRequest----------------------------------
    public List<DrugRequestHData> convertDrugRequestHToDrugRequestHData(List<DrugRequestH> drugRequestHList) throws DcometServiceException {
        List<DrugRequestHData> DrugRequestHDataList = new ArrayList<>();
        for (DrugRequestH drugRequestH : drugRequestHList) {
            DrugRequestHDataList.add(convertDrugRequestHToDrugRequestHData(drugRequestH));
        }
        return DrugRequestHDataList;
    }

    public List<DrugRequestH> convertDrugRequestHDataToDrugRequestH(List<DrugRequestHData> drugRequestHDataList) throws DcometServiceException {
        List<DrugRequestH> DrugRequestHList = new ArrayList<>();
        for (DrugRequestHData drugRequestHData : drugRequestHDataList) {
            DrugRequestHList.add(convertDrugRequestHDataToDrugRequestH(drugRequestHData));
        }
        return DrugRequestHList;
    }

    public DrugRequestHData convertDrugRequestHToDrugRequestHData(DrugRequestH DrugRequestH)
            throws DcometServiceException {
        DrugRequestHData drugRequestHData = new DrugRequestHData();

        if (DrugRequestH.getId() != null) {
            drugRequestHData.setId(DrugRequestH.getId());
        }
        if (DrugRequestH.getDrugReqHOpRID() != null) {
            drugRequestHData.setDrugReqHOpRID(DrugRequestH.getDrugReqHOpRID());
        }
        if (DrugRequestH.getDrugReqHOpVBisitRID() != null) {
            drugRequestHData.setDrugReqHOpVBisitRID(DrugRequestH.getDrugReqHOpVBisitRID());
        }
        if (DrugRequestH.getDrugReqHPatMrn() != null) {
            drugRequestHData.setDrugReqHPatMrn(DrugRequestH.getDrugReqHPatMrn());
        }
        if (DrugRequestH.getDrugReqHPatName() != null) {
            drugRequestHData.setDrugReqHPatName(DrugRequestH.getDrugReqHPatName());
        }
        if (DrugRequestH.getDrugReqHPatRid() != null) {
            drugRequestHData.setDrugReqHPatRid(DrugRequestH.getDrugReqHPatRid());
        }
        if (DrugRequestH.getDrugReqHBillHRID() != null) {
            drugRequestHData.setDrugReqHBillHRID(DrugRequestH.getDrugReqHBillHRID());
        }
        if (DrugRequestH.getDrugReqHBillNo() != null) {
            drugRequestHData.setDrugReqHBillNo(DrugRequestH.getDrugReqHBillNo());
        }
        if (DrugRequestH.getDrugReqHProcedureRid() != null) {
            drugRequestHData.setDrugReqHProcedureRid(DrugRequestH.getDrugReqHProcedureRid());
        }
        if (DrugRequestH.getDrugReqHStatus() != null) {
            drugRequestHData.setDrugReqHStatus(DrugRequestH.getDrugReqHStatus());
        }
        if (DrugRequestH.getDrugReqHState() != null) {
            drugRequestHData.setDrugReqHState(DrugRequestH.getDrugReqHState());
        }
        if (DrugRequestH.getDrugReqHType() != null) {
            drugRequestHData.setDrugReqHType(DrugRequestH.getDrugReqHType());
        }
        if (DrugRequestH.getDrugReqHOpCheck() != null) {
            drugRequestHData.setDrugReqHOpCheck(DrugRequestH.getDrugReqHOpCheck());
        }
        if (DrugRequestH.getDrugReqHfollowupDate() != null) {
            drugRequestHData.setDrugReqHfollowupDate(DateUtil.convertStringToDate(DrugRequestH.getDrugReqHfollowupDate()));
        }
        if (DrugRequestH.getDrugReqHComments() != null) {
            drugRequestHData.setDrugReqHComments(DrugRequestH.getDrugReqHComments());
        }
        if (DrugRequestH.getDrugReqHLabEnHRID() != null) {
            drugRequestHData.setDrugReqHLabEnHRID(DrugRequestH.getDrugReqHLabEnHRID());
        }
        if (DrugRequestH.getEntityRid() != null) {
            drugRequestHData.setDrugReqHEntityRID(DrugRequestH.getEntityRid());
        }
        if (DrugRequestH.getCreatedUserRid() != null) {
            drugRequestHData.setCreatedUserRid(DrugRequestH.getCreatedUserRid());
        }
        if (DrugRequestH.getModifiedUserRid() != null) {
            drugRequestHData.setModifiedUserRid(DrugRequestH.getModifiedUserRid());
        }

        return drugRequestHData;
    }

    public DrugRequestH convertDrugRequestHDataToDrugRequestH(DrugRequestHData drugRequestHData)
            throws DcometServiceException {
        DrugRequestH drugRequestH = new DrugRequestH();

        if (drugRequestHData.getId() != null) {
            drugRequestH.setId(drugRequestHData.getId());
        }
        if (drugRequestHData.getDrugReqHOpRID() != null) {
            drugRequestH.setDrugReqHOpRID(drugRequestHData.getDrugReqHOpRID());
        }
        if (drugRequestHData.getDrugReqHOpVBisitRID() != null) {
            drugRequestH.setDrugReqHOpVBisitRID(drugRequestHData.getDrugReqHOpVBisitRID());
        }
        if (drugRequestHData.getDrugReqHPatMrn() != null) {
            drugRequestH.setDrugReqHPatMrn(drugRequestHData.getDrugReqHPatMrn());
        }
        if (drugRequestHData.getDrugReqHPatName() != null) {
            drugRequestH.setDrugReqHPatName(drugRequestHData.getDrugReqHPatName());
        }
        if (drugRequestHData.getDrugReqHPatRid() != null) {
            drugRequestH.setDrugReqHPatRid(drugRequestHData.getDrugReqHPatRid());
        }
        if (drugRequestHData.getDrugReqHBillHRID() != null) {
            drugRequestH.setDrugReqHBillHRID(drugRequestHData.getDrugReqHBillHRID());
        }
        if (drugRequestHData.getDrugReqHBillNo() != null) {
            drugRequestH.setDrugReqHBillNo(drugRequestHData.getDrugReqHBillNo());
        }
        if (drugRequestHData.getDrugReqHProcedureRid() != null) {
            drugRequestH.setDrugReqHProcedureRid(drugRequestHData.getDrugReqHProcedureRid());
        }
        if (drugRequestHData.getDrugReqHStatus() != null) {
            drugRequestH.setDrugReqHStatus(drugRequestHData.getDrugReqHStatus());
        }
        if (drugRequestHData.getDrugReqHState() != null) {
            drugRequestH.setDrugReqHState(drugRequestHData.getDrugReqHState());
        }
        if (drugRequestHData.getDrugReqHType() != null) {
            drugRequestH.setDrugReqHType(drugRequestHData.getDrugReqHType());
        }
        if (drugRequestHData.getDrugReqHOpCheck() != null) {
            drugRequestH.setDrugReqHOpCheck(drugRequestHData.getDrugReqHOpCheck());
        }
        if (drugRequestHData.getDrugReqHfollowupDate() != null) {
            drugRequestH.setDrugReqHfollowupDate(DateUtil.convertDateToString(drugRequestHData.getDrugReqHfollowupDate()));
        }
        if (drugRequestHData.getDrugReqHComments() != null) {
            drugRequestH.setDrugReqHComments(drugRequestHData.getDrugReqHComments());
        }
        if (drugRequestHData.getDrugReqHLabEnHRID() != null) {
            drugRequestH.setDrugReqHLabEnHRID(drugRequestHData.getDrugReqHLabEnHRID());
        }
        if (drugRequestHData.getDrugReqHEntityRID() != null) {
            drugRequestH.setEntityRid(drugRequestHData.getDrugReqHEntityRID());
        }
        if (drugRequestHData.getCreatedUserRid() != null) {
            drugRequestH.setCreatedUserRid(drugRequestHData.getCreatedUserRid());
        }
        if (drugRequestHData.getModifiedUserRid() != null) {
            drugRequestH.setModifiedUserRid(drugRequestHData.getModifiedUserRid());
        }
        if (drugRequestHData.getModifiedDateTime() != null) {
            drugRequestH.setModifiedDateTime(DateUtil.convertCalendarToString(drugRequestHData.getModifiedDateTime()));
        }
        if (drugRequestHData.getCreatedDateTime() != null) {
            drugRequestH.setCreatedDateTime(DateUtil.convertCalendarToString(drugRequestHData.getCreatedDateTime()));
        }

        return drugRequestH;
    }

    public List<DrugRequestD> convertDrugRequestDDataToDrugRequestD(List<DrugRequestDData> drugRequestDDataList) throws DcometServiceException {
        List<DrugRequestD> drugRequestDList = new ArrayList<>();
        for (DrugRequestDData drugRequestDData : drugRequestDDataList) {
            drugRequestDList.add(convertDrugRequestDDataToDrugRequestD(drugRequestDData));
        }
        return drugRequestDList;
    }

    public List<DrugRequestDData> convertDrugRequestDToDrugRequestDData(List<DrugRequestD> drugRequestDList) throws DcometServiceException {
        List<DrugRequestDData> drugRequestDDataList = new ArrayList<>();
        for (DrugRequestD drugRequestD : drugRequestDList) {
            drugRequestDDataList.add(convertDrugRequestDToDrugRequestDData(drugRequestD));
        }
        return drugRequestDDataList;
    }

    public DrugRequestD convertDrugRequestDDataToDrugRequestD(DrugRequestDData drugRequestDData)
            throws DcometServiceException {
        DrugRequestD drugRequestD = new DrugRequestD();

        if (drugRequestDData.getId() != null) {
            drugRequestD.setId(drugRequestDData.getId());
        }
        if (drugRequestDData.getDrugReqDProcessDate() != null) {
            drugRequestD.setDrugReqDProcessDate(DateUtil.convertDateToString(drugRequestDData.getDrugReqDProcessDate()));
        }
        if (drugRequestDData.getDrugReqDNight() != null) {
            drugRequestD.setDrugReqDNight(drugRequestDData.getDrugReqDNight());
        }
        if (drugRequestDData.getDrugReqDMrng() != null) {
            drugRequestD.setDrugReqDMrng(drugRequestDData.getDrugReqDMrng());
        }
        if (drugRequestDData.getDrugReqDItemRID() != null) {
            drugRequestD.setDrugReqDItemRID(drugRequestDData.getDrugReqDItemRID());
        }
        if (drugRequestDData.getDrugReqDItemQty() != null) {
            drugRequestD.setDrugReqDItemQty(drugRequestDData.getDrugReqDItemQty());
        }
        if (drugRequestDData.getDrugReqDItemPackageRid() != null) {
            drugRequestD.setDrugReqDItemPackageRid(drugRequestDData.getDrugReqDItemPackageRid());
        }
        if (drugRequestDData.getDrugReqDItemPrice() != null) {
            drugRequestD.setDrugReqDItemPrice(drugRequestDData.getDrugReqDItemPrice());
        }
        if (drugRequestDData.getDrugReqDItemName() != null) {
            drugRequestD.setDrugReqDItemName(drugRequestDData.getDrugReqDItemName());
        }
        if (drugRequestDData.getDrugReqDItemBatchNo() != null) {
            drugRequestD.setDrugReqDItemBatchNo(drugRequestDData.getDrugReqDItemBatchNo());
        }
        if (drugRequestDData.getDrugReqDItemExpiryDate() != null) {
            drugRequestD.setDrugReqDItemExpiryDate(drugRequestDData.getDrugReqDItemExpiryDate());
        }
        if (drugRequestDData.getDrugReqDItemIssuedQty() != null) {
            drugRequestD.setDrugReqDItemIssuedQty(drugRequestDData.getDrugReqDItemIssuedQty());
        }
        if (drugRequestDData.getDrugReqDItemBalanceQty() != null) {
            drugRequestD.setDrugReqDItemBalanceQty(drugRequestDData.getDrugReqDItemBalanceQty());
        }
        if (drugRequestDData.getDrugReqDEve() != null) {
            drugRequestD.setDrugReqDEve(drugRequestDData.getDrugReqDEve());
        }
        if (drugRequestDData.getDrugReqDBillDRID() != null) {
            drugRequestD.setDrugReqDBillDRID(drugRequestDData.getDrugReqDBillDRID());
        }
        if (drugRequestDData.getDrugReqDAfternoon() != null) {
            drugRequestD.setDrugReqDAfternoon(drugRequestDData.getDrugReqDAfternoon());
        }
        if (drugRequestDData.getDruReqDSrhRID() != null) {
            drugRequestD.setDruReqDSrhRID(drugRequestDData.getDruReqDSrhRID());
        }
        return drugRequestD;

    }

    public DrugRequestDData convertDrugRequestDToDrugRequestDData(DrugRequestD drugRequestD) throws DcometServiceException {
        DrugRequestDData drugRequestDData = new DrugRequestDData();

        if (drugRequestD.getId() != null) {
            drugRequestDData.setId(drugRequestD.getId());
        }
        if (drugRequestD.getDrugReqDProcessDate() != null) {
            drugRequestDData.setDrugReqDProcessDate(DateUtil.convertStringToDate(drugRequestD.getDrugReqDProcessDate()));
        }
        if (drugRequestD.getDrugReqDNight() != null) {
            drugRequestDData.setDrugReqDNight(drugRequestD.getDrugReqDNight());
        }
        if (drugRequestD.getDrugReqDMrng() != null) {
            drugRequestDData.setDrugReqDMrng(drugRequestD.getDrugReqDMrng());
        }
        if (drugRequestD.getDrugReqDItemRID() != null) {
            drugRequestDData.setDrugReqDItemRID(drugRequestD.getDrugReqDItemRID());
        }
        if (drugRequestD.getDrugReqDItemQty() != null) {
            drugRequestDData.setDrugReqDItemQty(drugRequestD.getDrugReqDItemQty());
        }
        if (drugRequestD.getDrugReqDItemPackageRid() != null) {
            drugRequestDData.setDrugReqDItemPackageRid(drugRequestD.getDrugReqDItemPackageRid());
        }
        if (drugRequestD.getDrugReqDItemPrice() != null) {
            drugRequestDData.setDrugReqDItemPrice(drugRequestD.getDrugReqDItemPrice());
        }
        if (drugRequestD.getDrugReqDItemName() != null) {
            drugRequestDData.setDrugReqDItemName(drugRequestD.getDrugReqDItemName());
        }
        if (drugRequestD.getDrugReqDItemBatchNo() != null) {
            drugRequestDData.setDrugReqDItemBatchNo(drugRequestD.getDrugReqDItemBatchNo());
        }
        if (drugRequestD.getDrugReqDItemExpiryDate() != null) {
            drugRequestDData.setDrugReqDItemExpiryDate(drugRequestD.getDrugReqDItemExpiryDate());
        }
        if (drugRequestD.getDrugReqDItemIssuedQty() != null) {
            drugRequestDData.setDrugReqDItemIssuedQty(drugRequestD.getDrugReqDItemIssuedQty());
        }
        if (drugRequestD.getDrugReqDItemBalanceQty() != null) {
            drugRequestDData.setDrugReqDItemBalanceQty(drugRequestD.getDrugReqDItemBalanceQty());
        }
        if (drugRequestD.getDrugReqDEve() != null) {
            drugRequestDData.setDrugReqDEve(drugRequestD.getDrugReqDEve());
        }
        if (drugRequestD.getDrugReqDBillDRID() != null) {
            drugRequestDData.setDrugReqDBillDRID(drugRequestD.getDrugReqDBillDRID());
        }
        if (drugRequestD.getDrugReqDAfternoon() != null) {
            drugRequestDData.setDrugReqDAfternoon(drugRequestD.getDrugReqDAfternoon());
        }
        if (drugRequestD.getDruReqDSrhRID() != null) {
            drugRequestDData.setDruReqDSrhRID(drugRequestD.getDruReqDSrhRID());
        }

        return drugRequestDData;
    }

    //---------------------ItemOrder----------------------
    public List<ItemOrder> convertItemOrderDataToItemOrder(
            List<ItemOrderData> itemOrderDataList) throws DcometServiceException {
        List<ItemOrder> itemOrderList = new ArrayList<>();
        for (ItemOrderData itemOrderData : itemOrderDataList) {
            itemOrderList.add(convertItemOrderDataToItemOrder(itemOrderData));
        }
        return itemOrderList;
    }

    public List<ItemOrderData> convertItemOrderToItemOrderData(List<ItemOrder> itemOrderList)
            throws DcometServiceException {
        List<ItemOrderData> itemOrderDataList = new ArrayList<>();
        for (ItemOrder itemOrder : itemOrderList) {
            itemOrderDataList.add(convertItemOrderToItemOrderData(itemOrder));
        }
        return itemOrderDataList;
    }

    public ItemOrder convertItemOrderDataToItemOrder(ItemOrderData itemOrderData)
            throws DcometServiceException {
        ItemOrder itemOrder = new ItemOrder();
        if (itemOrderData.getId() != null) {
            itemOrder.setId(itemOrderData.getId());
        }
        if (itemOrderData.getIoUnitRid() != null) {
            itemOrder.setIoUnitRid(itemOrderData.getIoUnitRid());
        }
        if (itemOrderData.getIoPatientRid() != null) {
            itemOrder.setIoPatientRid(itemOrderData.getIoPatientRid());
        }
        if (itemOrderData.getIoVisitRid() != null) {
            itemOrder.setIoVisitRid(itemOrderData.getIoVisitRid());
        }
        if (itemOrderData.getIoOrderDate() != null) {
            itemOrder.setIoOrderDate(DateUtil.convertDateToString(itemOrderData.getIoOrderDate()));
        }
        if (itemOrderData.getIoOrderNo() != null) {
            itemOrder.setIoOrderNo(itemOrderData.getIoOrderNo());
        }
        if (itemOrderData.getIoOrderType() != null) {
            itemOrder.setIoOrderType(itemOrderData.getIoOrderType());
        }
        if (itemOrderData.getIoItemId() != null) {
            itemOrder.setIoItemId(itemOrderData.getIoItemId());
        }
        if (itemOrderData.getIoItemName() != null) {
            itemOrder.setIoItemName(itemOrderData.getIoItemName());
        }
        if (itemOrderData.getIoItemQty() != null) {
            itemOrder.setIoItemQty(itemOrderData.getIoItemQty());
        }
        if (itemOrderData.getIoEntityRid() != null) {
            itemOrder.setIoEntityRid(itemOrderData.getIoEntityRid());
        }
        if (itemOrderData.getIoMajorProcedureStatus() != null) {
            itemOrder.setIoMajorProcedureStatus(itemOrderData.getIoMajorProcedureStatus());
        }
        if (itemOrderData.getIoState() != null) {
            itemOrder.setIoState(itemOrderData.getIoState());
        }
        if (itemOrderData.getIoStatus() != null) {
            itemOrder.setIoStatus(itemOrderData.getIoStatus());
        }
        if (itemOrderData.getIoCancellationReason() != null) {
            itemOrder.setIoCancellationReason(itemOrderData.getIoCancellationReason());
        }
        if (itemOrderData.getIoRemarks() != null) {
            itemOrder.setIoRemarks(itemOrderData.getIoRemarks());
        }
        if (itemOrderData.getIoAttnDocRid() != null) {
            itemOrder.setIoAttnDocRid(itemOrderData.getIoAttnDocRid());
        }
        if (itemOrderData.getIoOrderDocRid() != null) {
            itemOrder.setIoOrderDocRid(itemOrderData.getIoOrderDocRid());
        }
        if (itemOrderData.getIoProcessedBy() != null) {
            itemOrder.setIoProcessedBy(itemOrderData.getIoProcessedBy());
        }
        if (itemOrderData.getIoProcessedDatetime() != null) {
            itemOrder.setIoProcessedDatetime(DateUtil.convertCalendarToString(itemOrderData.getIoProcessedDatetime()));
        }
        if (itemOrderData.getIoScheduleRid() != null) {
            itemOrder.setIoScheduleRid(itemOrderData.getIoScheduleRid());
        }
        if (itemOrderData.getIoServiceUnit() != null) {
            itemOrder.setIoServiceUnit(itemOrderData.getIoServiceUnit());
        }
        if (itemOrderData.getCreatedUserRid() != null) {
            itemOrder.setCreatedUserRid(itemOrderData.getCreatedUserRid());
        }
        if (itemOrderData.getCreatedDateTime() != null) {
            itemOrder.setCreatedDateTime(DateUtil.convertCalendarToString(itemOrderData.getCreatedDateTime()));
        }
        if (itemOrderData.getModifiedUserRid() != null) {
            itemOrder.setModifiedUserRid(itemOrderData.getModifiedUserRid());
        }
        if (itemOrderData.getModifiedDateTime() != null) {
            itemOrder.setModifiedDateTime(DateUtil.convertCalendarToString(itemOrderData.getModifiedDateTime()));
        }
        return itemOrder;
    }

    public ItemOrderData convertItemOrderToItemOrderData(ItemOrder itemOrder) throws DcometServiceException {
        ItemOrderData itemOrderData = new ItemOrderData();

        if (itemOrder.getId() != null) {
            itemOrderData.setId(itemOrder.getId());
        }

        if (itemOrder.getIoUnitRid() != null) {
            itemOrderData.setIoUnitRid(itemOrder.getIoUnitRid());
        }
        if (itemOrder.getIoPatientRid() != null) {
            itemOrderData.setIoPatientRid(itemOrder.getIoPatientRid());
        }
        if (itemOrder.getIoVisitRid() != null) {
            itemOrderData.setIoVisitRid(itemOrder.getIoVisitRid());
        }
        if (itemOrder.getIoOrderDate() != null) {
            itemOrderData.setIoOrderDate(DateUtil.convertStringToDate(itemOrder.getIoOrderDate()));
        }
        if (itemOrder.getIoOrderNo() != null) {
            itemOrderData.setIoOrderNo(itemOrder.getIoOrderNo());
        }
        if (itemOrder.getIoOrderType() != null) {
            itemOrderData.setIoOrderType(itemOrder.getIoOrderType());
        }
        if (itemOrder.getIoItemId() != null) {
            itemOrderData.setIoItemId(itemOrder.getIoItemId());
        }
        if (itemOrder.getIoItemName() != null) {
            itemOrderData.setIoItemName(itemOrder.getIoItemName());
        }
        if (itemOrder.getIoItemQty() != null) {
            itemOrderData.setIoItemQty(itemOrder.getIoItemQty());
        }
        if (itemOrder.getIoEntityRid() != null) {
            itemOrderData.setIoEntityRid(itemOrder.getIoEntityRid());
        }
        if (itemOrder.getIoMajorProcedureStatus() != null) {
            itemOrderData.setIoMajorProcedureStatus(itemOrder.getIoMajorProcedureStatus());
        }
        if (itemOrder.getIoState() != null) {
            itemOrderData.setIoState(itemOrder.getIoState());
        }
        if (itemOrder.getIoStatus() != null) {
            itemOrderData.setIoStatus(itemOrder.getIoStatus());
        }
        if (itemOrder.getIoCancellationReason() != null) {
            itemOrderData.setIoCancellationReason(itemOrder.getIoCancellationReason());
        }
        if (itemOrder.getIoRemarks() != null) {
            itemOrderData.setIoRemarks(itemOrder.getIoRemarks());
        }
        if (itemOrder.getIoAttnDocRid() != null) {
            itemOrderData.setIoAttnDocRid(itemOrder.getIoAttnDocRid());
        }
        if (itemOrder.getIoOrderDocRid() != null) {
            itemOrderData.setIoOrderDocRid(itemOrder.getIoOrderDocRid());
        }
        if (itemOrder.getIoProcessedBy() != null) {
            itemOrderData.setIoProcessedBy(itemOrder.getIoProcessedBy());
        }
        if (itemOrder.getIoProcessedDatetime() != null) {
            itemOrderData.setIoProcessedDatetime(DateUtil.convertStringToCalendar(itemOrder.getIoProcessedDatetime()));
        }
        if (itemOrder.getIoScheduleRid() != null) {
            itemOrderData.setIoScheduleRid(itemOrder.getIoScheduleRid());
        }
        if (itemOrder.getIoServiceUnit() != null) {
            itemOrderData.setIoServiceUnit(itemOrder.getIoServiceUnit());
        }
        if (itemOrder.getCreatedUserRid() != null) {
            itemOrderData.setCreatedUserRid(itemOrder.getCreatedUserRid());
        }
        if (itemOrder.getModifiedUserRid() != null) {
            itemOrderData.setModifiedUserRid(itemOrder.getModifiedUserRid());
        }

        return itemOrderData;
    }

}
