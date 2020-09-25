package com.dcomet.health.batch.dao;

import com.dcomet.fw.domain.ReportScheduleHSearchRequest;
import com.dcomet.health.batch.dao.data.ReportScheduleHData;
import com.dcomet.fw.exception.DcometBatchException;
import java.util.List;

/**
 *
 * @author Dev2
 */
public interface ReportScheduleDAO {

    public List<ReportScheduleHData> getReportScheduleH(ReportScheduleHSearchRequest reportScheduleHSearchRequest) throws DcometBatchException;

    public void nextReportSchedule(ReportScheduleHData reportScheduleHData) throws DcometBatchException;
}
