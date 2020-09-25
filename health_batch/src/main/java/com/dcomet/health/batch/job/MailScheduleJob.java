package com.dcomet.health.batch.job;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.dcomet.health.batch.service.MailScheduleService;
import com.dcomet.fw.exception.DcometBatchException;

/**
 * @author John
 */
public class MailScheduleJob extends AbstractJob {

    @Autowired
    private MailScheduleService mailScheduleService;

    @Override
    public void executeJob(Date time) throws DcometBatchException, Exception {
        mailScheduleService.sendMail(time);
    }

    public MailScheduleService getMailScheduleService() {
        return mailScheduleService;
    }

    public void setMailScheduleService(MailScheduleService mailScheduleService) {
        this.mailScheduleService = mailScheduleService;
    }

}
