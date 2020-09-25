package com.dcomet.health.batch.service.impl;

import com.dcomet.fw.email.IEmail;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.fw.util.FileUtil;
import com.dcomet.health.batch.dao.MailScheduleDAO;
import com.dcomet.health.batch.dao.data.MailQueueData;
import com.dcomet.health.batch.service.MailScheduleService;
import com.dcomet.fw.exception.DcometBatchException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev2
 */
@Service("mailScheduleService")
@Transactional(propagation = Propagation.SUPPORTS)
public class MailScheduleServiceImpl implements MailScheduleService {

    @Autowired
    @Qualifier("mailScheduleDAO")
    MailScheduleDAO mailScheduleDAO;

    @Autowired
    @Qualifier("mailGenerator")
    private IEmail email;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void sendMail(Date time) throws DcometBatchException {
        List<MailQueueData> mailQueueDataList = mailScheduleDAO.getMailQueue();
        if (CollectionUtils.isEmpty(mailQueueDataList)) {
            return;
        }
        for (MailQueueData mailQueueData : mailQueueDataList) {
            String type = mailQueueData.getMqIsHtmlContent() == 1 ? "text/html" : "text/plain";
            if (StringUtils.isNotBlank(mailQueueData.getMqFileNameWithPath())) {
                File file = new File(mailQueueData.getMqFileNameWithPath());
                try {
                    sendMail(mailQueueData.getMqSubject(), type, mailQueueData.getMqBody(), null,
                            Arrays.asList(new String[]{mailQueueData.getMqTo()}), null, FileUtil.read(file), mailQueueData.getMqFileName());
                    mailQueueData.setMqSentDate(DateUtil.getToday());
                    mailQueueData.setMqSentTime(Calendar.getInstance());
                    mailQueueData.setMqSent(1);
                } catch (IOException ex) {
                    mailQueueData.setMqReasonForFailure(ex.getMessage());
                }
            } else {
                try {
                    sendMail(mailQueueData.getMqSubject(), type, mailQueueData.getMqBody(), null,
                            Arrays.asList(new String[]{mailQueueData.getMqTo()}), null, null, null);
                    mailQueueData.setMqSentDate(DateUtil.getToday());
                    mailQueueData.setMqSentTime(Calendar.getInstance());
                    mailQueueData.setMqSent(1);
                } catch (Exception ex) {
                    mailQueueData.setMqReasonForFailure(ex.getMessage());
                }
            }
        }
        mailScheduleDAO.saveMailQueue(mailQueueDataList);
    }

    private void sendMail(String subject, String type, String body, String from, List<String> toList, List<String> ccList, byte[] attachment,
            String attachmentName) throws DcometBatchException {
        try {
            if (StringUtils.isNotBlank(subject) && StringUtils.isNotBlank(body)) {
                if (null != from) {
                    email.setFrom(from);
                }
                if (null != toList) {
                    email.setToRecipients(toList);
                }
                if (null != ccList) {
                    email.setCcRecipients(toList);
                }
                email.setSubject(subject);
                email.setBody(body);
                email.setType(type);
                if (attachment != null) {
                    email.sendEmail(attachment,attachmentName,email.getBody());
                } else {
                    email.sendEmail(email.getBody());
                }

            }
        } catch (Exception e) {
            throw new DcometBatchException(e);
        } catch (Throwable t) {
            throw new DcometBatchException(t);
        }
    }

}
