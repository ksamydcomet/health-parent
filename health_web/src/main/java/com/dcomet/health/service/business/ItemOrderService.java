package com.dcomet.health.service.business;

import com.dcomet.health.domain.FavouriteItemOrderD;
import com.dcomet.health.domain.FavouriteItemOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteItemOrderH;
import com.dcomet.health.domain.FavouriteItemOrderHSearchRequest;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.DrugRequestD;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.ItemOrder;
import com.dcomet.health.domain.ItemOrderSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface ItemOrderService extends WorkFlowService {

    public List<FavouriteItemOrderH> getFavouriteItemOrderH(FavouriteItemOrderHSearchRequest favouriteItemOrderHSearchRequest,
            boolean includeChilds) throws DcometServiceException;

    public void saveFavouriteItemOrderH(FavouriteItemOrderH favouriteItemOrderH, boolean includeChilds) throws DcometServiceException;

    public List<FavouriteItemOrderD> getFavouriteItemOrderD(FavouriteItemOrderDSearchRequest favouriteItemOrderDSearchRequest)
            throws DcometServiceException;

    public Integer saveDrugH(DrugRequestH drugRequestH, boolean includeChilds) throws DcometServiceException;

    public List<DrugRequestH> getDrugH(DrugRequestHSearchRequest drugRequestHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public List<DrugRequestD> getDrugD(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException;

    public String getDrugPrintService(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException;

    public List<ItemOrder> getItemOrder(ItemOrderSearchRequest itemOrderSearchRequest) throws DcometServiceException;

    public void saveItemOrder(List<ItemOrder> itemOrderList) throws DcometServiceException;
}
