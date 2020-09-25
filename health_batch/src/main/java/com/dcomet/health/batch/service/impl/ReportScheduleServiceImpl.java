package com.dcomet.health.batch.service.impl;

import com.dcomet.fw.config.Config;
import com.dcomet.fw.domain.MailQueue;
import com.dcomet.fw.domain.ReportScheduleHSearchRequest;
import com.dcomet.fw.exception.GException;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.batch.adapter.ReportScheduleAdapter;
import com.dcomet.health.batch.dao.ReportScheduleDAO;
import com.dcomet.health.batch.dao.data.ReportScheduleDData;
import com.dcomet.health.batch.dao.data.ReportScheduleHData;
import com.dcomet.health.batch.dao.fn.FnDailyReport;
import com.dcomet.health.batch.service.ReportScheduleService;
import com.dcomet.health.batch.util.RESTUtil;
import com.dcomet.fw.exception.DcometBatchException;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev2
 */
@Service("reportScheduleService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ReportScheduleServiceImpl implements ReportScheduleService {

    @Autowired
    @Qualifier("reportScheduleDAO")
    ReportScheduleDAO reportScheduleDAO;

    @Autowired
    @Qualifier("reportScheduleAdapter")
    ReportScheduleAdapter reportScheduleAdapter;

    @Autowired
    @Qualifier("fnDailyReport")
    private FnDailyReport fnDailyReport;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reportSchedule(Date date) throws DcometBatchException {

        ReportScheduleHSearchRequest reportScheduleHSearchRequest = new ReportScheduleHSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.le("rshStartDateTime", date));
        searchCriterionList.add(Restrictions.le("rshNextSchDateTime", date));

        reportScheduleHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<ReportScheduleHData> reportScheduleHList = reportScheduleDAO.getReportScheduleH(reportScheduleHSearchRequest);
        if (CollectionUtils.isEmpty(reportScheduleHList)) {
            return;
        }
//        List<ReportScheduleH> reportScheduleHList = reportScheduleAdapter.convertReportScheduleHDataToReportScheduleH(resultData);
        for (ReportScheduleHData reportScheduleH : reportScheduleHList) {
            MailQueue mailQueue = new MailQueue();
            mailQueue.setMqSubject(new StringBuilder(reportScheduleH.getRshSubject()).append(" - ").append(DateUtil.getCurrentDate()).toString());
            mailQueue.setMqBody(reportScheduleH.getRshReportHeader());
            if (CollectionUtils.isNotEmpty(reportScheduleH.getReportScheduleDData())) {
                File directory = new File(reportScheduleH.getRshOutputFilepath() + File.separator + DateUtil.getCurrentDate().substring(6) + File.separator + DateUtil.getCurrentDate().substring(3, 5));
                createDirIfNotExist(directory);

                String report = directory.getAbsolutePath() + File.separator + reportScheduleH.getRshOutputFilname() + reportScheduleH.getRshOutputFileExtn();
                generateReport(report, reportScheduleH.getReportScheduleDData());

                mailQueue.setMqFileNameWithPath(new StringBuilder(report).toString());
                mailQueue.setMqFileName(new StringBuilder(reportScheduleH.getRshOutputFilname()).append(reportScheduleH.getRshOutputFileExtn()).toString());
                mailQueue.setMqIsAttachmentExist(1);
                mailQueue.setMqSent(0);

            }
            nextReportSchedule(reportScheduleH);
            String[] mailIds = reportScheduleH.getRshMailIds().split(",");
            List<MailQueue> mailQueueList = new ArrayList<>();
            for (String to : mailIds) {
                MailQueue mq;
                try {
                    mq = (MailQueue) mailQueue.clone();
                    mq.setMqTo(to);
                    mailQueueList.add(mq);
                } catch (CloneNotSupportedException ex) {
                    throw new DcometBatchException(ex);
                }
            }
            String mailQueues = new Gson().toJson(mailQueueList);
            sendToMailQueue(mailQueues);
        }

    }

    private void nextReportSchedule(ReportScheduleHData reportScheduleH) throws DcometBatchException {
        reportScheduleDAO.nextReportSchedule(reportScheduleH);
    }

    private void createDirIfNotExist(File directory) throws DcometBatchException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void generateReport(String report, List<ReportScheduleDData> reportScheduleDList) throws DcometBatchException {
        for (ReportScheduleDData reportScheduleDData : reportScheduleDList) {
            generateReport(report, reportScheduleDData.getRsdReportUrl());
        }
    }

    private void sendToMailQueue(String mailQueues) throws DcometBatchException {
        try {
            new RESTUtil().assert200Post(Config.getString("DCOMET_WEB") + Config.getString("DCOMET_MAILQUEUE"), Config.getString("DCOMET_BATCH_USERNAME"), Config.getString("DCOMET_BATCH_PASSWORD"), mailQueues);
        } catch (GException e) {
            throw new DcometBatchException(e);
        }
    }

    private void generateReport(String report, String URL) throws DcometBatchException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpResponse response = null;
        try {
            String today = DateUtil.getCurrentDate();
            String data = "{\"from\":\"" + today + "\",\"to\":\"" + today + "\"}";
//            System.out.println(data);
            response = new RESTUtil().assert200Post(Config.getString("DCOMET_WEB") + URL, Config.getString("DCOMET_BATCH_USERNAME"), Config.getString("DCOMET_BATCH_PASSWORD"), data);
            inputStream = response.getEntity().getContent();
            outputStream = new FileOutputStream(report);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (GException e) {
            throw new DcometBatchException(e);
        } catch (MalformedURLException e) {
            throw new DcometBatchException(e);
        } catch (IOException e) {
            throw new DcometBatchException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    throw new DcometBatchException(e);
                }
            }
        }
    }

    @Override
    public void dailyReportSchedule(Date date) throws DcometBatchException {
        fnDailyReport.call(date);
    }

}
