package com.dcomet.health.batch.job;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.dcomet.health.batch.service.ReportScheduleService;
import com.dcomet.fw.exception.DcometBatchException;

/**
 * @author John
 */
public class ReportScheduleJob extends AbstractJob {

    @Autowired
    private ReportScheduleService reportScheduleService;

    @Override
    public void executeJob(Date time) throws DcometBatchException, Exception {
        reportScheduleService.reportSchedule(time);
    }

    public ReportScheduleService getReportScheduleService() {
        return reportScheduleService;
    }

    public void setReportScheduleService(ReportScheduleService reportScheduleService) {
        this.reportScheduleService = reportScheduleService;
    }
}
