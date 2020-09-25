package com.dcomet.health.adapter;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.PrintTemplateData;
import com.dcomet.health.domain.PrintTemplate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("utilityAdapter")
public class UtilityAdapter {

    public List<PrintTemplate> convertPrintTemplateDataToPrintTemplate(List<PrintTemplateData> printTemplateDataList) throws DcometServiceException {
        List<PrintTemplate> printTemplateList = new ArrayList<>();
        for (PrintTemplateData printTemplateData : printTemplateDataList) {
            printTemplateList.add(convertPrintTemplateDataToPrintTemplate(printTemplateData));
        }
        return printTemplateList;
    }

    public List<PrintTemplateData> convertPrintTemplateToPrintTemplateData(List<PrintTemplate> printTemplateList) throws DcometServiceException {
        List<PrintTemplateData> printTemplateDataList = new ArrayList<>();
        for (PrintTemplate printTemplate : printTemplateList) {
            printTemplateDataList.add(convertPrintTemplateToPrintTemplateData(printTemplate));
        }
        return printTemplateDataList;
    }

    public PrintTemplate convertPrintTemplateDataToPrintTemplate(PrintTemplateData printTemplateData) throws DcometServiceException {
        PrintTemplate printTemplate = new PrintTemplate();
        if (printTemplateData.getPtDefaultData() != null) {
            printTemplate.setPtDefaultData(printTemplateData.getPtDefaultData());
        }
        if (printTemplateData.getPtEntityRID() != null) {
            printTemplate.setPtEntityRID(printTemplateData.getPtEntityRID());
        }
        if (printTemplateData.getPtId() != null) {
            printTemplate.setPtId(printTemplateData.getPtId());
        }
        if (printTemplateData.getPtName() != null) {
            printTemplate.setPtName(printTemplateData.getPtName());
        }
        if (printTemplateData.getPtNodes() != null) {
            printTemplate.setPtNodes(printTemplateData.getPtNodes());
        }
        if (printTemplateData.getPtType() != null) {
            printTemplate.setPtType(printTemplateData.getPtType());
        }
        return printTemplate;
    }

    public PrintTemplateData convertPrintTemplateToPrintTemplateData(PrintTemplate printTemplate) throws DcometServiceException {
        PrintTemplateData printTemplateData = new PrintTemplateData();
        if (printTemplate.getPtDefaultData() != null) {
            printTemplateData.setPtDefaultData(printTemplate.getPtDefaultData());
        }
        if (printTemplate.getPtEntityRID() != null) {
            printTemplateData.setPtEntityRID(printTemplate.getPtEntityRID());
        }
        if (printTemplate.getPtId() != null) {
            printTemplateData.setPtId(printTemplate.getPtId());
        }
        if (printTemplate.getPtName() != null) {
            printTemplateData.setPtName(printTemplate.getPtName());
        }
        if (printTemplate.getPtNodes() != null) {
            printTemplateData.setPtNodes(printTemplate.getPtNodes());
        }
        if (printTemplate.getPtType() != null) {
            printTemplateData.setPtType(printTemplate.getPtType());
        }
        return printTemplateData;
    }
}
