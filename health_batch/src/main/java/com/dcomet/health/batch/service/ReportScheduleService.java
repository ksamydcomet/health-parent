package com.dcomet.health.batch.service;

import com.dcomet.fw.exception.DcometBatchException;
import java.util.Date;

/**
 *
 * @author Dev2
 */
public interface ReportScheduleService {

    public void reportSchedule(Date date) throws DcometBatchException;

    public void dailyReportSchedule(Date date) throws DcometBatchException;

}
