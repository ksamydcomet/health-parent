package com.dcomet.health.batch.service;

import com.dcomet.fw.exception.DcometBatchException;
import java.util.Date;

public interface DashBoardService {

    public void refreshDashBoard(Date date) throws DcometBatchException;
}
