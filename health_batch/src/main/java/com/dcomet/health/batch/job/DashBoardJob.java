package com.dcomet.health.batch.job;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.dcomet.health.batch.service.DashBoardService;

/**
 * @author John
 */
public class DashBoardJob extends AbstractJob {

    @Autowired
    private DashBoardService dashBoardService;

    @Override
    public void executeJob(Date time) throws Throwable {
        dashBoardService.refreshDashBoard(time);
    }

    public DashBoardService getDashBoardService() {
        return dashBoardService;
    }

    public void setDashBoardService(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

}
