package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.domain.PrintTemplate;
import com.dcomet.health.domain.PrintTemplateSearchRequest;
import java.util.List;

public interface UtilityService {

    public void savePrintTemplate(List<PrintTemplate> printTemplateList) throws DcometServiceException;

    public List<PrintTemplate> getPrintTemplate(PrintTemplateSearchRequest printTemplateSearchRequest) throws DcometServiceException;

}
