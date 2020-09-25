package com.dcomet.health.batch.dao;

import com.dcomet.health.batch.dao.data.MailQueueData;
import com.dcomet.fw.exception.DcometBatchException;
import java.util.List;

/**
 *
 * @author Dev2
 */
public interface MailScheduleDAO {

    public List<MailQueueData> getMailQueue() throws DcometBatchException;

    public void saveMailQueue(List<MailQueueData> mailQueueDataList) throws DcometBatchException;
}
