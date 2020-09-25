package com.dcomet.health.dao;

import com.dcomet.health.dao.data.FavouriteItemOrderDData;
import com.dcomet.health.dao.data.FavouriteItemOrderHData;
import com.dcomet.health.domain.FavouriteItemOrderDSearchRequest;
import com.dcomet.health.domain.FavouriteItemOrderHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.DrugRequestDData;
import com.dcomet.health.dao.data.DrugRequestHData;
import com.dcomet.health.dao.data.ItemOrderData;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.ItemOrderSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface ItemOrderDAO {

    //------------FavouriteItemOrderH----------------
    public List<FavouriteItemOrderHData> getFavouriteItemOrderH(FavouriteItemOrderHSearchRequest FavouriteItemOrderHSearchRequestList) throws DcometDAOException;

    public void saveFavouriteItemOrderH(FavouriteItemOrderHData favouriteItemOrderHData) throws DcometDAOException;

    //    ------------FavouriteItemOrderD----------------
    public List<FavouriteItemOrderDData> getFavouriteItemOrderD(FavouriteItemOrderDSearchRequest FavouriteItemOrderDSearchRequestList) throws DcometDAOException;

    public void saveFavouriteItemOrderD(List<FavouriteItemOrderDData> favouriteItemOrderDDataList) throws DcometDAOException;

    //----------Drug----
    public void saveDrugH(DrugRequestHData drugRequestHData) throws DcometDAOException;

    public void saveDrugD(List<DrugRequestDData> drugRequestDDataList) throws DcometDAOException;

    public List<DrugRequestHData> getDrugH(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometDAOException;

    public List<DrugRequestDData> getDrugD(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometDAOException;

    //-------ItemOrder ---------
    public List<ItemOrderData> getItemOrder(ItemOrderSearchRequest itemOrderSearchRequest) throws DcometDAOException;

    public void saveItemOrder(ItemOrderData itemOrderData) throws DcometDAOException;

}
