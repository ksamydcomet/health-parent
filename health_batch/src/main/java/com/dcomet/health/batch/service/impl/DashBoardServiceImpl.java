package com.dcomet.health.batch.service.impl;

import com.dcomet.health.batch.dao.fn.FnDashBoard;
import com.dcomet.health.batch.service.BaseService;
import com.dcomet.health.batch.service.DashBoardService;
import com.dcomet.fw.exception.DcometBatchException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("dashBoardService")
public class DashBoardServiceImpl extends BaseService implements DashBoardService {

    @Autowired
    @Qualifier("fnDashBoard")
    private FnDashBoard fnDashBoard;

    @Override
    public void refreshDashBoard(Date date) throws DcometBatchException {
        fnDashBoard.call(date);
    }

}
