package com.dcomet.health.service.business.impl;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.adapter.UtilityAdapter;
import com.dcomet.health.dao.UtilityDAO;
import com.dcomet.health.dao.data.PrintTemplateData;
import com.dcomet.health.domain.PrintTemplate;
import com.dcomet.health.domain.PrintTemplateSearchRequest;
import com.dcomet.health.service.business.UtilityService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("utilityService")
@Transactional(propagation = Propagation.SUPPORTS)
public class UtilityServiceImpl implements UtilityService {

    @Autowired
    @Qualifier("utilityDAO")
    UtilityDAO utilityDAO;

    @Autowired
    @Qualifier("utilityAdapter")
    UtilityAdapter utilityAdapter;

    @Override
    public void savePrintTemplate(List<PrintTemplate> printTemplateList) throws DcometServiceException {
        try {
            List<PrintTemplateData> printTemplateDataList = utilityAdapter.convertPrintTemplateToPrintTemplateData(printTemplateList);
            for (PrintTemplateData printTemplateData : printTemplateDataList) {
                utilityDAO.savePrintTemplate(printTemplateData);
            }
        } catch (DcometServiceException e) {
            throw e;
        }
    }

    @Override
    public List<PrintTemplate> getPrintTemplate(PrintTemplateSearchRequest printTemplateSearchRequest) throws DcometServiceException {
        List<PrintTemplate> result = null;
        try {
            List<PrintTemplateData> listData = utilityDAO.getPrintTemplate(printTemplateSearchRequest);
            if (CollectionUtils.isNotEmpty(listData)) {
                result = utilityAdapter.convertPrintTemplateDataToPrintTemplate(listData);
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return result;
    }

}
