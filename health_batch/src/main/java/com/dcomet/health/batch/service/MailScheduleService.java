package com.dcomet.health.batch.service;

import com.dcomet.fw.exception.DcometBatchException;
import java.util.Date;

/**
 *
 * @author Dev2
 */
public interface MailScheduleService {

    public void sendMail(Date time) throws DcometBatchException;

}
