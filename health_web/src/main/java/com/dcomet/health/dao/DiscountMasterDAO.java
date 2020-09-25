package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.DiscountMasterData;
import com.dcomet.health.domain.DiscountMaster;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import java.util.List;

public interface DiscountMasterDAO {

    public List<DiscountMaster> getDiscountMaster(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometDAOException;

    public void saveDiscountMaster(DiscountMasterData discountMasterData) throws DcometDAOException;
}
