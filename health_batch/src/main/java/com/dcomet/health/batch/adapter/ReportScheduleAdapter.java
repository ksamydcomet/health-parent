package com.dcomet.health.batch.adapter;

import com.dcomet.fw.domain.ReportScheduleD;
import com.dcomet.fw.domain.ReportScheduleH;
import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.batch.dao.data.ReportScheduleDData;
import com.dcomet.health.batch.dao.data.ReportScheduleHData;
import com.dcomet.fw.exception.DcometBatchException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dev2
 */
@Component("reportScheduleAdapter")
public class ReportScheduleAdapter {

    public List<ReportScheduleH> convertReportScheduleHDataToReportScheduleH(
            List<ReportScheduleHData> reportScheduleHDataList) throws DcometBatchException {
        List<ReportScheduleH> reportScheduleHList = new ArrayList<>();
        for (ReportScheduleHData reportScheduleHData : reportScheduleHDataList) {
            reportScheduleHList.add(convertReportScheduleHDataToReportScheduleH(reportScheduleHData));
        }
        return reportScheduleHList;
    }

    public ReportScheduleH convertReportScheduleHDataToReportScheduleH(ReportScheduleHData reportScheduleHData)
            throws DcometBatchException {
        ReportScheduleH reportScheduleH = new ReportScheduleH();

        if (reportScheduleHData.getId() != null) {
            reportScheduleH.setId(reportScheduleHData.getId());
        }
        if (reportScheduleHData.getRshDesc() != null) {
            reportScheduleH.setRshDesc(reportScheduleHData.getRshDesc());
        }
        if (reportScheduleHData.getRshEntityRID() != null) {
            reportScheduleH.setEntityRid(reportScheduleHData.getRshEntityRID());
        }
        if (reportScheduleHData.getRshFrequency() != null) {
            reportScheduleH.setRshFrequency(reportScheduleHData.getRshFrequency());
        }
        if (reportScheduleHData.getRshStartDateTime() != null) {
            reportScheduleH.setRshStartDateTime(DateUtil.convertDateTimeToString(reportScheduleHData.getRshStartDateTime()));
        }
        if (reportScheduleHData.getRshSubject() != null) {
            reportScheduleH.setRshSubject(reportScheduleHData.getRshSubject());
        }
        if (reportScheduleHData.getRshOutputMode() != null) {
            reportScheduleH.setRshOutputMode(reportScheduleHData.getRshOutputMode());
        }
        if (reportScheduleHData.getRshOutputCommunication() != null) {
            reportScheduleH.setRshOutputCommunication(reportScheduleHData.getRshOutputCommunication());
        }
        if (reportScheduleHData.getRshOutputFilname() != null) {
            reportScheduleH.setRshOutputFilname(reportScheduleHData.getRshOutputFilname());
        }
        if (reportScheduleHData.getRshOutputFileExtn() != null) {
            reportScheduleH.setRshOutputFileExtn(reportScheduleHData.getRshOutputFileExtn());
        }
        if (reportScheduleHData.getRshOutputFilepath() != null) {
            reportScheduleH.setRshOutputFilepath(reportScheduleHData.getRshOutputFilepath());
        }
        if (reportScheduleHData.getRshLastExeDateTime() != null) {
            reportScheduleH.setRshLastExeDateTime(DateUtil.convertDateTimeToString(reportScheduleHData.getRshLastExeDateTime()));
        }
        if (reportScheduleHData.getRshNextSchDateTime() != null) {
            reportScheduleH.setRshNextSchDateTime(DateUtil.convertDateTimeToString(reportScheduleHData.getRshNextSchDateTime()));
        }
        if (reportScheduleHData.getRshUserID() != null) {
            reportScheduleH.setRshUserID(reportScheduleHData.getRshUserID());
        }
        if (reportScheduleHData.getRshUserPassword() != null) {
            reportScheduleH.setRshUserPassword(reportScheduleHData.getRshUserPassword());
        }
        if (reportScheduleHData.getRshReportHeader() != null) {
            reportScheduleH.setRshReportHeader(reportScheduleHData.getRshReportHeader());
        }
        if (reportScheduleHData.getRshHtmlHeader() != null) {
            reportScheduleH.setRshHtmlHeader(reportScheduleHData.getRshHtmlHeader());
        }
        if (reportScheduleHData.getRshHtmlFooter() != null) {
            reportScheduleH.setRshHtmlFooter(reportScheduleHData.getRshHtmlFooter());
        }
        if (reportScheduleHData.getRshIsActive() != null) {
            reportScheduleH.setRshIsActive(reportScheduleHData.getRshIsActive());
        }
        if (reportScheduleHData.getRshIsAttached() != null) {
            reportScheduleH.setRshIsAttached(reportScheduleHData.getRshIsAttached());
        }
        if (reportScheduleHData.getRshMailIds() != null) {
            reportScheduleH.setRshMailIds(reportScheduleHData.getRshMailIds());
        }
        if (CollectionUtils.isNotEmpty(reportScheduleHData.getReportScheduleDData())) {
            List<ReportScheduleD> reportScheduleDList = new ArrayList<>();
            for (ReportScheduleDData reportScheduleDData : reportScheduleHData.getReportScheduleDData()) {
                reportScheduleDList.add(convertReportScheduleDDataToReportScheduleD(reportScheduleDData));
            }
            reportScheduleH.setReportScheduleDList(reportScheduleDList);
        }
        return reportScheduleH;
    }

    public ReportScheduleD convertReportScheduleDDataToReportScheduleD(ReportScheduleDData reportScheduleDData)
            throws DcometBatchException {
        ReportScheduleD reportScheduleD = new ReportScheduleD();

        if (reportScheduleDData.getId() != null) {
            reportScheduleD.setId(reportScheduleDData.getId());
        }
        if (reportScheduleDData.getRsdRshRID() != null) {
            reportScheduleD.setRsdRshRID(reportScheduleDData.getRsdRshRID());
        }
        if (reportScheduleDData.getRsdReportUrl() != null) {
            reportScheduleD.setRsdReportUrl(reportScheduleDData.getRsdReportUrl());
        }
        if (reportScheduleDData.getRsdSeqNo() != null) {
            reportScheduleD.setRsdSeqNo(reportScheduleDData.getRsdSeqNo());
        }
        if (reportScheduleDData.getRsdIsActive() != null) {
            reportScheduleD.setRsdIsActive(reportScheduleDData.getRsdIsActive());
        }
        return reportScheduleD;
    }

    public ReportScheduleHData convertReportScheduleHToReportScheduleHData(ReportScheduleH reportScheduleH) throws DcometBatchException {
        ReportScheduleHData reportScheduleHData = new ReportScheduleHData();

        if (reportScheduleH.getId() != null) {
            reportScheduleHData.setId(reportScheduleH.getId());
        }
        if (reportScheduleH.getRshDesc() != null) {
            reportScheduleHData.setRshDesc(reportScheduleH.getRshDesc());
        }
        if (reportScheduleH.getEntityRid() != null) {
            reportScheduleHData.setRshEntityRID(reportScheduleH.getEntityRid());
        }
        if (reportScheduleH.getRshFrequency() != null) {
            reportScheduleHData.setRshFrequency(reportScheduleH.getRshFrequency());
        }
        if (reportScheduleH.getRshStartDateTime() != null) {
            reportScheduleHData.setRshStartDateTime(DateUtil.convertStringToDate(reportScheduleH.getRshStartDateTime()));
        }
        if (reportScheduleH.getRshSubject() != null) {
            reportScheduleHData.setRshSubject(reportScheduleH.getRshSubject());
        }
        if (reportScheduleH.getRshOutputMode() != null) {
            reportScheduleHData.setRshOutputMode(reportScheduleH.getRshOutputMode());
        }
        if (reportScheduleH.getRshOutputCommunication() != null) {
            reportScheduleHData.setRshOutputCommunication(reportScheduleH.getRshOutputCommunication());
        }
        if (reportScheduleH.getRshOutputFilname() != null) {
            reportScheduleHData.setRshOutputFilname(reportScheduleH.getRshOutputFilname());
        }
        if (reportScheduleH.getRshOutputFileExtn() != null) {
            reportScheduleHData.setRshOutputFileExtn(reportScheduleH.getRshOutputFileExtn());
        }
        if (reportScheduleH.getRshOutputFilepath() != null) {
            reportScheduleHData.setRshOutputFilepath(reportScheduleH.getRshOutputFilepath());
        }
        if (reportScheduleH.getRshLastExeDateTime() != null) {
            reportScheduleHData.setRshLastExeDateTime(DateUtil.convertStringToDate(reportScheduleH.getRshLastExeDateTime()));
        }
        if (reportScheduleH.getRshNextSchDateTime() != null) {
            reportScheduleHData.setRshNextSchDateTime(DateUtil.convertStringToDate(reportScheduleH.getRshNextSchDateTime()));
        }
        if (reportScheduleH.getRshUserID() != null) {
            reportScheduleHData.setRshUserID(reportScheduleH.getRshUserID());
        }
        if (reportScheduleH.getRshUserPassword() != null) {
            reportScheduleHData.setRshUserPassword(reportScheduleH.getRshUserPassword());
        }
        if (reportScheduleH.getRshReportHeader() != null) {
            reportScheduleHData.setRshReportHeader(reportScheduleH.getRshReportHeader());
        }
        if (reportScheduleH.getRshHtmlHeader() != null) {
            reportScheduleHData.setRshHtmlHeader(reportScheduleH.getRshHtmlHeader());
        }
        if (reportScheduleH.getRshHtmlFooter() != null) {
            reportScheduleHData.setRshHtmlFooter(reportScheduleH.getRshHtmlFooter());
        }
        if (reportScheduleH.getRshIsActive() != null) {
            reportScheduleHData.setRshIsActive(reportScheduleH.getRshIsActive());
        }
        if (reportScheduleH.getRshIsAttached() != null) {
            reportScheduleHData.setRshIsAttached(reportScheduleH.getRshIsAttached());
        }
        return reportScheduleHData;
    }
}
