package com.dcomet.health.dao;

import com.dcomet.fw.domain.MailQueueSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.DiscountMasterData;
import com.dcomet.health.dao.data.DiscountServiceMapData;
import com.dcomet.module.master.dao.data.PackageItemMapData;
import com.dcomet.module.master.dao.data.PackageMasterData;
import com.dcomet.module.master.dao.data.PackageServiceMapData;
import com.dcomet.health.dao.data.PayerAuthorizationData;
import com.dcomet.health.dao.data.PayerMasterData;
import com.dcomet.health.dao.data.PayerIncidentData;
import com.dcomet.health.dao.data.PayerServiceMapData;
import com.dcomet.health.dao.data.PrintableInfoData;
import com.dcomet.health.dao.data.TemplateMailData;
import com.dcomet.health.domain.DiscountMasterSearchRequest;
import com.dcomet.module.master.domain.PackageMasterSearchRequest;
import com.dcomet.health.domain.PayerAuthorizationSearchRequest;
import com.dcomet.health.domain.PayerIncidentSearchRequest;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.PayerServiceMapSearchRequest;
import com.dcomet.health.domain.PrintableInfoSearchRequest;
import com.dcomet.module.dao.data.MailQueueData;
import com.dcomet.module.master.dao.DCometMasterDAO;
import java.util.List;

/**
 *
 * @author KS
 */
public interface MasterDAO extends DCometMasterDAO {

    public void saveDiscountMaster(DiscountMasterData discountMasterData) throws DcometDAOException;

    public void saveDiscountServiceMap(List<DiscountServiceMapData> list) throws DcometDAOException;

    public List<DiscountMasterData> getDiscountMaster(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometDAOException;

    public List<DiscountServiceMapData> getDiscountServiceMap(DiscountMasterSearchRequest discountMasterSearchRequest) throws DcometDAOException;

    public void savePayerMaster(PayerMasterData payerMasterData) throws DcometDAOException;

    public void savePayerServiceMap(List<PayerServiceMapData> payerServiceMapDataList) throws DcometDAOException;

    public List<PayerMasterData> getPayerMaster(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometDAOException;

    public List<PayerServiceMapData> getPayerServiceMap(PayerMasterSearchRequest payerMasterSearchRequest) throws DcometDAOException;

//    public void deletePayerServiceMap(Integer psmPdRid) throws DcometDAOException;
    public List<PayerServiceMapData> getPayerServiceMap(PayerServiceMapSearchRequest payerServiceMapSearchRequest) throws DcometDAOException;

    public List<PayerIncidentData> getPayerIncident(PayerIncidentSearchRequest payerIncidentSearchRequest) throws DcometDAOException;

    public List<PayerAuthorizationData> getPayerAuthorization(PayerAuthorizationSearchRequest payerAuthorizationSearchRequest) throws DcometDAOException;

    public void savePayerAuthorization(PayerAuthorizationData payerAuthorizationData) throws DcometDAOException;

    public void savePayerIncident(List<PayerIncidentData> payerIncidentDataList) throws DcometDAOException;

    public List<PrintableInfoData> getPrintInfo(PrintableInfoSearchRequest printableInfoSearchRequest) throws DcometDAOException;

    public PrintableInfoData getPrintInfo(Integer entityRid) throws DcometDAOException;

    public PrintableInfoData getPrintInfoLogo(Integer entRid) throws DcometDAOException;

    public void savePrintInfo(PrintableInfoData printableInfoData) throws DcometDAOException;

    public List<MailQueueData> getMailQueue(MailQueueSearchRequest mailQueueSearchRequest) throws DcometDAOException;

    public void saveMailQueue(MailQueueData mailQueueData) throws DcometDAOException;

    public List<TemplateMailData> getMailTemplate(String tempCode) throws DcometDAOException;

}
