package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.PrintTemplateData;
import com.dcomet.health.domain.PrintTemplateSearchRequest;
import java.util.List;

/**
 *
 * @author KS
 */
public interface UtilityDAO {

    public void savePrintTemplate(PrintTemplateData printTemplateData) throws DcometDAOException;

    public List<PrintTemplateData> getPrintTemplate(PrintTemplateSearchRequest printTemplateSearchRequest) throws DcometDAOException;
}
