package com.dcomet.health.service.business.impl;

import com.dcomet.module.billing.service.impl.DCometBillingServiceImpl;
import com.dcomet.module.billing.dao.data.BillDData;
import com.dcomet.module.billing.dao.data.BillHData;
import com.dcomet.module.domain.AutoNumber;
import com.dcomet.fw.domain.Base;
import com.dcomet.module.billing.domain.BillH;
import com.dcomet.module.billing.domain.ReceiptH;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;
import com.dcomet.health.domain.SalesH;
import com.dcomet.health.domain.ServiceOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.util.DateUtil;
import static com.dcomet.fw.workflow.service.impl.BaseWorkFlowServiceImpl.IDRAFT;
import com.dcomet.health.caching.CacheUtil;
import com.dcomet.health.domain.DrugRequestH;
import com.dcomet.health.domain.DrugRequestHSearchRequest;
import com.dcomet.health.domain.HealthBillH;
import com.dcomet.health.domain.ItemOrder;
import com.dcomet.module.master.domain.PackageItemMap;
import com.dcomet.module.master.domain.PackageServiceMap;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PayerMaster;
import com.dcomet.health.domain.PayerMasterSearchRequest;
import com.dcomet.health.domain.ServiceOrderSearchRequest;
import com.dcomet.health.domain.ServiceRequestH;
import com.dcomet.health.domain.ServiceRequestHSearchRequest;
import com.dcomet.health.domain.Visit;
import com.dcomet.module.billing.domain.AdvanceDetails;
import com.dcomet.module.billing.domain.BillD;
import com.dcomet.health.service.business.AdvanceDetailsService;
import com.dcomet.health.service.business.BillingService;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.health.service.business.DataDictionaryService;
import com.dcomet.health.service.business.ItemOrderService;
import com.dcomet.health.service.business.MasterService;
import com.dcomet.health.service.business.PharmacyService;
import com.dcomet.health.service.business.ReceiptService;
import com.dcomet.health.service.business.ServiceOrderService;
import com.dcomet.health.service.business.ServiceRequestService;
import com.dcomet.module.billing.dao.data.PaymentModeDetailsData;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.module.billing.domain.PaymentModeDetails;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.module.billing.domain.ReceiptD;
import com.dcomet.module.domain.CurrencyM;
import com.dcomet.module.domain.CurrencyMSearchRequest;
import com.dcomet.module.domain.Ddict;
import com.dcomet.module.domain.DdictSearchRequest;
import com.dcomet.module.domain.User;
import com.dcomet.module.master.domain.ServiceMaster;
import com.sun.org.apache.bcel.internal.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("billingService")
@Transactional(propagation = Propagation.SUPPORTS)
public class BillingServiceImpl extends DCometBillingServiceImpl implements BillingService {

    @Autowired
    @Qualifier("advanceDetailsService")
    AdvanceDetailsService advanceDetailsService;

    @Autowired
    @Qualifier("clinicalService")
    ClinicalService clinicalService;

    @Autowired
    @Qualifier("pharmacyService")
    PharmacyService pharmacyService;

    @Autowired
    @Qualifier("receiptService")
    ReceiptService receiptService;

    @Autowired
    @Qualifier("serviceOrderService")
    ServiceOrderService serviceOrderService;

    @Autowired
    @Qualifier("itemOrderService")
    ItemOrderService itemOrderService;

    @Autowired
    @Qualifier("serviceRequestService")
    ServiceRequestService serviceRequestService;

    @Autowired
    @Qualifier("dataDictionaryService")
    private DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("masterService")
    public MasterService masterService;

    /**
     *
     * @param billH
     * @return
     */
    private AutoNumber generateAutoNumber(BillH billH) {
        AutoNumber autoNumber = null;
        String billcategory = "";
//        if (billH.getId() == null) {
        if (billH.getBhType() == 1) {
            billcategory = "OP";
        } else if (billH.getBhType() == 2) {
            if (billH.getBhIsDraft() == 1) {
                billcategory = "DRAFT/IP";
            } else if (billH.getBhIsDraft() == 0) {
                billcategory = "IP";
            }
        } else if (billH.getBhType() == 3) {
            billcategory = "PHA";
        }
//                    else if (Objects.equals(billH.getBhType(), OpBill)) {
//                        billcategory = "DRAFT/OP";
//                    } else if (Objects.equals(billH.getBhType(), IpBill)) {
//                        billcategory = "DRAFT/IP";
//                    } else if (Objects.equals(billH.getBhType(), pharmacy)) {
//                        billcategory = "DRAFT/PHA";
//                    }
        autoNumber = dataDictionaryService.getAutoNumberByCategory(billcategory, billH.getEntityRid());
        dataDictionaryService.saveAutoNumberIncrement(billcategory, billH.getEntityRid());
//        } else {
//            if (billH.getBhType() == 2) {
//                billcategory = "IP";
//                autoNumber = dataDictionaryService.getAutoNumberByCategory(billcategory, billH.getEntityRid());
//                dataDictionaryService.saveAutoNumberIncrement(billcategory, billH.getEntityRid());
//            }
//        }
        return autoNumber;
    }

    /**
     *
     * @param billH
     * @param billHData
     * @param billD
     * @param billDData
     */
    private void createServiceOrder(BillH billH, BillHData billHData, List<BillDData> billDDataList) {
        Integer soState = 0;
        for (BillDData billDData : billDDataList) {
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setCurrentObject(billH);
            if (billH.getBhProcedureIntraService() != null && billH.getBhProcedureIntraService() == 1) {
                serviceOrder.setSoMajorProcedureStatus(0);
            }
            serviceOrder.setSoAgainstUnitRID(billH.getUnitRid());
            serviceOrder.setSoOrderingUnitRID(billH.getUnitRid());
            serviceOrder.setSoPatientRID(billHData.getBhPatientRID());
            serviceOrder.setSoVisitRID(billHData.getBhPatientVisitRID());
            serviceOrder.setSoItemID(billDData.getBdItemRID());
            serviceOrder.setSoItemName(billDData.getBdItemName());
            serviceOrder.setSoQty(billDData.getBdQty());
            serviceOrder.setSoDiscPercentage(billDData.getBdDiscountAmount());

            ServiceOrderSearchRequest serviceOrderSearchRequest = new ServiceOrderSearchRequest();
            List<Criterion> criterionList = new ArrayList<>();
            criterionList.add(Restrictions.eq("id", billDData.getBdItemOrderRID()));
            serviceOrderSearchRequest.setSearchCriterionList(criterionList);
            List<ServiceOrder> serviceOrderList = serviceOrderService.getServiceOrder(serviceOrderSearchRequest, false);
            if (CollectionUtils.isNotEmpty(serviceOrderList)) {
                soState = serviceOrderList.get(0).getSoState();
            }
            if (soState != 2 && soState != 4) {
                if (billDData.getBdItemOrderRID() != null) {
                    serviceOrder.setId(billDData.getBdItemOrderRID());
                    serviceOrderService.save(serviceOrder, billDData.getBdItemOrderRID(), "SERVICE_ORDER", "BUILTIN_ACTION");
                } else {
                    Integer soRid = serviceOrderService.save(serviceOrder, IDRAFT, "SERVICE_ORDER", "BUILTIN_ACTION");
                    if (CollectionUtils.isNotEmpty(billH.getReceiptH()) || Objects.equals(serviceOrder.getSoDiscPercentage(), 100.0f)) {
                        serviceOrder.setId(soRid);
                        serviceOrderService.save(serviceOrder, soRid, "SERVICE_ORDER", "BUILTIN_ACTION");
                    }
                    billDData.setBdItemOrderRID(soRid);
                    billingDAO.saveBillD(Arrays.asList(new BillDData[]{billDData}));
                }
            }
        }
    }

    private void createItemOrder(BillH billH, BillHData billHData, List<BillDData> billDDataList) {
        List<ItemOrder> itemOrders = new ArrayList<>();
        for (BillDData billDData : billDDataList) {
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setCurrentObject(billH);
            if (billH.getBhProcedureIntraMaterial() != null && billH.getBhProcedureIntraMaterial() == 1) {
                itemOrder.setIoMajorProcedureStatus(0);
            }
//            itemOrder.setIoAgabillH.getUnitRid());
//            itemOrder.setIoO(billH.getUnitRid());
            itemOrder.setIoPatientRid(billHData.getBhPatientRID());
            itemOrder.setIoVisitRid(billHData.getBhPatientVisitRID());
            itemOrder.setIoEntityRid(billHData.getBhEntityRID());
            itemOrder.setIoItemId(billDData.getBdItemRID());
            itemOrder.setIoItemName(billDData.getBdItemName());
            itemOrder.setIoItemQty(billDData.getBdQty());
            itemOrders.add(itemOrder);
        }
        itemOrderService.saveItemOrder(itemOrders);
    }

    private void cancelServiceOrder(BillH billH, BillH dbBillH, BillD billD) {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setCurrentObject(billH);
        serviceOrder.setSoOrderingUnitRID(billH.getUnitRid());

        serviceOrder.setSoPatientRID(dbBillH.getBhPatientRID());
        serviceOrder.setSoVisitRID(dbBillH.getBhPatientVisitRID());
        serviceOrder.setId(billD.getBdItemOrderRID());
        serviceOrder.setSoItemID(billD.getBdItemRID());
        serviceOrder.setSoItemName(billD.getBdItemName());
        serviceOrder.setSoQty(billD.getBdQty());
        serviceOrderService.save(serviceOrder, serviceOrder.getId(), "SERVICE_ORDER", "CANCEL_SERVICE_ORDER");
    }

    private void cancelReceipt(BillH billH, BillHData billHData) {
        Float totalPaidAmt = 0.00f;
        if (billHData.getBhPaidAmount() == null) {
            billHData.setBhPaidAmount(0.00f);
        }
        if (CollectionUtils.isNotEmpty(billH.getReceiptH())) {
            for (ReceiptH receiptH : billH.getReceiptH()) {
                if (receiptH.getId() != null) {
                    totalPaidAmt = receiptH.getRhPaidAmount();
                    receiptH.setCurrentObject(billH);
                    receiptService.save(receiptH, receiptH.getId(), "RECEIPT", "CANCEL_RECEIPT");
                    billHData.setBhPaidAmount(billHData.getBhPaidAmount() - totalPaidAmt);
                    saveBillHByCondtion(billHData);
                }
            }
        } else {
            Integer currentState = getCurrentState(billHData.getId());
            Integer nxtConState = getBOStateTransitionConfig("Bill_Cancelled", currentState).getBostcBostToStateIndex();
            billHData.setBhState(nxtConState);
            billHData.setBhStatus(nxtConState);
            billHData.setBhDueAmount(0.00f);
            billingDAO.saveBillH(Arrays.asList(billHData));

            ReceiptHSearchRequest receiptHSearchRequest = new ReceiptHSearchRequest();
            receiptHSearchRequest.setSearchCriterionList(Arrays.asList(new Criterion[]{Restrictions.eq("rhBhRID", billH.getId())}));
            List<ReceiptH> receiptHList = getReceiptH(receiptHSearchRequest, false);

            if (CollectionUtils.isNotEmpty(receiptHList)) {
                for (ReceiptH receiptH : receiptHList) {
                    receiptH.setCurrentObject(billH);
                    totalPaidAmt += receiptH.getRhPaidAmount();
                    receiptService.save(receiptH, receiptH.getId(), "RECEIPT", "CANCEL_RECEIPT");
                }
            }
        }
        if (totalPaidAmt != 0) {
            createAdvance(billH, billHData, totalPaidAmt);
        }

    }

    private void createAdvance(BillH billH, BillHData billHData, Float totalPaidAmt) {
        // -----CreateAdvance-------
        AdvanceDetails advanceDetails = new AdvanceDetails();
        advanceDetails.setAdType(0);
        advanceDetails.setAdRegDate(billH.getCurrentDateByUTZ());
        advanceDetails.setAdRefRID(billHData.getId());
        advanceDetails.setAdPayerRID(billHData.getBhPatientRID());
        advanceDetails.setAdPayerName(billHData.getBhPatientName());
        advanceDetails.setAdPayerNo(billHData.getBhPatientNo());//PayerRID or PatientRID
        advanceDetails.setAdAmount(totalPaidAmt);
        advanceDetails.setAdAdjustedAmount(0f);
        advanceDetails.setAdRefundedAmount(0f);
        advanceDetails.setAdBalanceAmount(totalPaidAmt);
        advanceDetails.setAdCreationMode(1);
        advanceDetails.setCurrentObject(billH);
        advanceDetailsService.save(advanceDetails, IDRAFT, "ADVANCE", "CREATE_ADVANCE");
    }

    /**
     *
     * @param billH
     * @param billHData
     */
    private void createOrCancelReceipt(BillH billH, BillHData billHData) {
        for (ReceiptH receiptH : billH.getReceiptH()) {
            receiptH.setCurrentObject(billH);
            receiptH.setRhPayerRID(billHData.getBhPayerRID());
            if (receiptH.getId() != null) {
                receiptService.save(receiptH, receiptH.getId(), "RECEIPT", "CANCEL_RECEIPT");
                saveBillHByCondtion(billHData);
            } else if (billH.getBhPaidAmount() != 0) {
                for (int i = 0; i < receiptH.getReceiptD().size(); i++) {
                    receiptH.getReceiptD().get(i).setRdBillRID(billHData.getId());
                }
                receiptH.setRhBhRID(billHData.getId());
                receiptService.save(receiptH, 0, "RECEIPT", "BUILTIN_ACTION");
                saveBillHByCondtion(billHData);
            }
        }
    }

    /**
     *
     * @param billH
     * @param billHData
     */
    private void saveSalesH(BillH billH, BillHData billHData) {
        HealthBillH healthBillH = (HealthBillH) billH;
        if (CollectionUtils.isNotEmpty(healthBillH.getSalesH())) {
            for (SalesH salesH : healthBillH.getSalesH()) {
                salesH.setSalBhRID(billHData.getId());
                salesH.setSalBillNo(billHData.getBhBillNo());
                salesH.setSalBillDate(billH.getCreatedDateTime());
                salesH.setSalDueAmount(billHData.getBhDueAmount());
                salesH.setCurrentObject(billH);
                pharmacyService.saveSalesH(salesH, true);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Base object, Integer nextState, String actionCode) {
        BillH billH = (BillH) object;
        int billhID = 0;
        if (billH.getId() != null) {
            billhID = billH.getId();
        }
        BillHData billHData = new BillHData();
        try {
            int eligibleCheck = 0;
            if (null != actionCode) {
                switch (actionCode) {
                    case "BUILTIN_ACTION":
                    case "COLLECT_PAYMENT":
                    case "REQUEST":
                        // Generate Auto Number     
                        if (billH.getId() == null) {
                            AutoNumber autoNumber = generateAutoNumber(billH);
                            if (autoNumber != null) {
                                billH.setBhBillNo(autoNumber.getAutoNumber());
                                billH.setBhPrefix(autoNumber.getAutoPrefix());
                                billH.setBhPrintableBillNo(autoNumber.getAutoNumber());
                                billH.setBhBillDate(billH.getCurrentDateByUTZ());
                                if (billH.getBhType() == 2) {
                                    billH.setBhDraftBillNo(autoNumber.getAutoNumber());
                                }
                            }
                        }

                        if (actionCode.equals("COLLECT_PAYMENT")) {
                            if (billH.getBhType() == 2 && billH.getBhIsDraft() == 1) {//type == 1 op bill 
                                billH.setBhIsDraft(0);
                                AutoNumber autoNumber = generateAutoNumber(billH);
                                billH.setBhBillNo(autoNumber.getAutoNumber());
                                billH.setBhPrefix(autoNumber.getAutoPrefix());
                                billH.setBhPrintableBillNo(autoNumber.getAutoNumber());
                            }
                        }
                        if ("BUILTIN_ACTION".equals(actionCode) || "REQUEST".equals(actionCode)) {
                            billH.setBhState(nextState);
                            billH.setBhStatus(nextState);
                        } else {
                            billH.setBhState(getCurrentState(billH.getId()));
                            billH.setBhStatus(getCurrentState(billH.getId()));
                        }
                        if (billH.getBhEligibleAmount() != 0) {
//                            eligibleCheck = 1;
                            billH.setBhDueAmount(billH.getBhNetAmount() - billH.getBhPaidAmount());
                        } else {
                            if (billH.getBhNetAmount() != null && billH.getBhPaidAmount() != null) {
                                billH.setBhDueAmount(billH.getBhNetAmount() - billH.getBhPaidAmount());
                            }
                        }
                        // Save H
                        if (billH.getBhPayerType() == null) {
                            billH.setBhPayerType("31");
                            billH.setBhPayerRID(billH.getBhPatientRID());
                        }
                        billHData = billingAdapter.convertBillHToBillHData(billH);
                        billingDAO.saveBillH(Arrays.asList(billHData));
                        billH.setId(billHData.getId());

                        if (CollectionUtils.isNotEmpty(billH.getBillD())) {
                            List<BillD> billDs = new ArrayList<>();
//                            if (billH.getId() == null) {
                            billDs = createBillDList(billH.getBillD(), billhID);
//                            } else {
//                                billDs = removeDublicateBillDList(billH.getBillD());
//                            }
                            billH.setId(billHData.getId());
                            List<BillDData> billDDataList = billingAdapter.convertBillDToBillDData(billDs);

                            for (BillDData billDData : billDDataList) {
                                billDData.setBdBhRID(billHData.getId());
                            }
                            if (!billH.getBhType().equals(3)) {
                                createServiceOrder(billH, billHData, billDDataList);
                            }
//                            if (Objects.equals("COLLECT_PAYMENT", actionCode)) {
//                                for (BillDData billDData : billDDataList) {
//                                    if (billDData.getId() == null) {
//                                        billDDataList.remove(billDData);
//                                    }
//                                    if (billDData.getId() == 0) {
//                                        billDDataList.remove(billDData);
//                                    }
//                                }
//                            }
                            billingDAO.saveBillD(billDDataList);
                        }
                        if (actionCode.equals("COLLECT_PAYMENT") && CollectionUtils.isEmpty(billH.getReceiptH()) && billH.getBhEligibleAmount() != 0) {
                            eligibleCheck = 1;
                        }
//                        if (1 == 2) {
//                        saveSalesH(billH, billHData);
//                        }
                        if (CollectionUtils.isNotEmpty(billH.getReceiptH())) {
                            eligibleCheck = 0;
                            createOrCancelReceipt(billH, billHData);
                        }
                        if (eligibleCheck == 1) {
                            saveBillHByCondtion(billHData);
                        }

                        break;

                    case "CANCEL_BILL":
                        BillH dbBillH = getBillH(billH.getId(), true);
                        for (BillD billD : dbBillH.getBillD()) {
                            if (billD.getBdItemOrderRID() == null) {
                                billD.setBdItemOrderRID(0);
                            }
                            if (billD.getBdItemOrderRID() != 0 && billD.getBdItemOrderRID() != null) {
                                cancelServiceOrder(billH, dbBillH, billD);
                            }
                        }
                        billHData = billingAdapter.convertBillHToBillHData(dbBillH);
                        cancelReceipt(billH, billHData);
                        return dbBillH.getId();
                }
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return billHData.getId();
    }

    @Override
    public List<BillD> setBillD(List<BillD> billDs) throws DcometServiceException {
        List<BillD> billDs1 = new ArrayList<>();
        int packageRid = 0;
        for (BillD billD : billDs) {
            if (billD.getBdItemPackageRID() != null && billD.getBdItemPackageRID() != 0) {
                if (packageRid != billD.getBdItemPackageRID()) {
                    BillD billD1 = new BillD();
                    packageRid = billD.getBdItemPackageRID();
                    ServiceMaster serviceMaster = masterService.getServiceMasterByID(billD.getBdItemPackageRID());
                    billD1.setBdItemName(serviceMaster.getBsName());
                    billD1.setBdGroupRID(0);
                    billD1.setBdItemRID(serviceMaster.getId());
                    billD1.setBdItemType("Service");
                    billD1.setBdPrice(serviceMaster.getbPrice());
                    billD1.setBdQty(1f);
                    billD1.setBdIsItemPackage(1);
                    billD1.setBdItemPackageRID(billD.getBdItemPackageRID());
                    billD1.setBdDiscountAmount(0f);
                    billD1.setBdGrossAmount(serviceMaster.getbPrice());
                    billD1.setBdNetAmount(serviceMaster.getbPrice());
                    billDs1.add(billD1);
                }
            } else {
                billDs1.add(billD);
            }
        }
        return billDs1;
    }

    private List<BillD> createBillDList(List<BillD> billDs, Integer billhID) {
        List<BillD> billDs1 = new ArrayList<>();
        BillH billH = new BillH();
        List<BillD> billDBs = new ArrayList<>();
        if (billhID != 0 && billhID != null) {
            billH = getBillHWithActualBillD(billhID, true);
            billDBs = billH.getBillD();
        }
        for (BillD billD : billDs) {
//            if (billD.getBdItemPackageRID() != null && billD.getBdItemPackageRID() != 0 && billD.getBdIsItemPackage() != 0 && billD.getBdIsItemPackage() != null) {
            if (billD.getBdIsItemPackage() != null) {
                if (billD.getBdIsItemPackage() != 0) {
                    ServiceMaster serviceMaster = masterService.getServiceMasterByID(billD.getBdItemPackageRID());
                    if (serviceMaster != null) {
                        for (PackageServiceMap packageServiceMap : serviceMaster.getPackageServiceMap()) {
                            if (packageServiceMap.getPsServiceIsActive() == 1) {
                                BillD billD1 = new BillD();
                                if (billhID == 0) {
                                    billD1.setId(null);
                                } else {
                                    for (BillD billD2 : billDBs) {
                                        if (Objects.equals(billD2.getBdItemRID(), packageServiceMap.getPsServiceRid())
                                                && Objects.equals(billD2.getBdItemPackageRID(), serviceMaster.getId())) {
                                            billD1.setId(billD2.getId());
                                        }
                                    }
                                }
                                billD1.setBdItemName(packageServiceMap.getPsServiceName());
//                            billD1.setBdItemOrderRID(null);
                                billD1.setBdItemRID(packageServiceMap.getPsServiceRid());
                                billD1.setBdItemPackageRID(serviceMaster.getId());
//                            billD1.setBdGroupRID(null);
                                billD1.setBdItemType("Service");
                                billD1.setBdQty(packageServiceMap.getPsServiceQty());
                                billD1.setBdPrice(0f);
                                billD1.setBdGrossAmount(0f);
                                billD1.setBdDiscountAmount(0f);
                                billD1.setBdNetAmount(0f);
                                billDs1.add(billD1);
                            }
                        }
                        for (PackageItemMap packageItemMap : serviceMaster.getPackageitemMap()) {
                            if (packageItemMap.getPiItemIsActive() == 1) {
                                BillD billD1 = new BillD();
                                if (billhID == 0) {
                                    billD1.setId(null);
                                } else {
                                    for (BillD billD2 : billDBs) {
                                        if (Objects.equals(billD2.getBdItemRID(), packageItemMap.getPiItemRid())
                                                && Objects.equals(billD2.getBdItemPackageRID(), serviceMaster.getId())) {
                                            billD1.setId(billD2.getId());
                                        }
                                    }
                                }
                                billD1.setBdItemName(packageItemMap.getPiItemName());
//                            billD1.setBdItemOrderRID(null);
                                billD1.setBdItemRID(packageItemMap.getPiItemRid());
                                billD1.setBdItemPackageRID(serviceMaster.getId());
//                            billD1.setBdGroupRID(null);
                                billD1.setBdItemType("Drugs");
                                billD1.setBdQty(packageItemMap.getPiItemQty());
                                billD1.setBdPrice(0f);
                                billD1.setBdGrossAmount(0f);
                                billD1.setBdDiscountAmount(0f);
                                billD1.setBdNetAmount(0f);
                                billDs1.add(billD1);
                            }
                        }
                    }
                } else {
                    billDs1.add(billD);
                }
            } else {
                billDs1.add(billD);
            }
        }
        return billDs1;
    }

    private void saveBillHByCondtion(BillHData billHData) {
//        + billHData.getBhEligibleAmount()
        String bostcCondtion = billHData.getBhNetAmount().equals(billHData.getBhPaidAmount()) ? "Bill_amount=Collected_amount" : "Bill_amount>Collected_amount";
        Integer nxtConState = getBOStateTransitionConfig(bostcCondtion, billHData.getBhState()).getBostcBostToStateIndex();
        billHData.setBhState(nxtConState);
        billHData.setBhStatus(nxtConState);
        billingDAO.saveBillH(Arrays.asList(billHData));
    }

    @Override
    public Integer getCurrentState(Integer boRID) {
        BillH billH = getBillH(boRID);
        return billH != null ? billH.getBhState() : 0;
    }

    @Override
    public String buildBODescriptor(String[] fieldSpec, Integer boRID) {
        BillH billH = getBillH(boRID);
        return billH != null ? new StringBuilder(billH.getBhBillNo()).append("&").append(billH.getBhPatientName()).append("&").append(billH.getBhNetAmount()).toString() : null;
    }

    @Override
    public String getTransactionReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            BillH billH = getBillH(billHSearchRequest, true).get(0);
            if (CollectionUtils.isNotEmpty(billH.getReceiptH())) {
                string = dataDictionaryService.getReportFromTemplate("TRAN_REPORT_PAYMENTMODES", billH, "bill", billHSearchRequest.getEntityRid());
                if (CollectionUtils.isNotEmpty(billH.getReceiptH())) {
                    List<Criterion> searchCriterionList = new ArrayList<>();
                    PaymentModeSearchRequest paymentModeSearchRequest = new PaymentModeSearchRequest();
                    searchCriterionList.add(Restrictions.eq("pmdTransRID", billH.getReceiptH().get(0).getId()));
                    paymentModeSearchRequest.setSearchCriterionList(searchCriterionList);
                    List<PaymentModeDetailsData> paymentMode = billingDAO.getPaymentModeDetails(paymentModeSearchRequest);
                    if (CollectionUtils.isNotEmpty(paymentMode)) {
                        List<PaymentModeDetails> result = null;
                        result = billingAdapter.convertPaymentModeDetailsDataToPaymentModeDetails(paymentMode);
                        for (PaymentModeDetails paymentModeDetails : result) {
                            paymentModeDetails.setPmdCurrencyShortName(getCurrencyMById(paymentModeDetails.getPmdCurrencyRID()).getCurShortName());
                            searchCriterionList.clear();
                            DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
                            searchCriterionList.add(Restrictions.eq("id", paymentModeDetails.getPmdPaymentMode()));
                            ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                            Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
                            if (ddictResult != null) {
                                paymentModeDetails.setPmdPaymentModeName(ddictResult.getDdictValue());
                            }
                        }
//                            if (paymentMode.get(0).getPmdTransType() == 3) {
                        string = dataDictionaryService.getReportFromHTML(string, result, "payM");
//                            }
                        searchCriterionList.clear();
                        DdictSearchRequest ddictSearchRequest = new DdictSearchRequest();
                        searchCriterionList.add(Restrictions.eq("id", paymentMode.get(0).getPmdPaymentMode()));
                        ddictSearchRequest.setSearchCriterionList(searchCriterionList);
                        Ddict ddictResult = dataDictionaryService.getDdict(ddictSearchRequest).get(0);
                        if (ddictResult != null) {
                            string = dataDictionaryService.getReportFromHTML(string, ddictResult, "dict");
                        }
                        if (Integer.parseInt(billH.getBhPayerType()) != 31) {
                            searchCriterionList.clear();
                            DdictSearchRequest ddictSearchRequestTitle = new DdictSearchRequest();
                            searchCriterionList.add(Restrictions.eq("id", Integer.parseInt(billH.getBhPayerType())));
                            ddictSearchRequestTitle.setSearchCriterionList(searchCriterionList);
                            Ddict ddictResultTitle = dataDictionaryService.getDdict(ddictSearchRequestTitle).get(0);
                            if (ddictResultTitle != null) {
                                string = dataDictionaryService.getReportFromHTML(string, ddictResultTitle, "dDictPayer");
                            }
                        }
//                        }
                    }
                }
            } else {
                string = dataDictionaryService.getReportFromTemplate("TRAN_REPORT", billH, "bill", billHSearchRequest.getEntityRid());
                if (Integer.parseInt(billH.getBhPayerType()) != 31) {
                    List<Criterion> searchCriterionList = new ArrayList<>();
                    PayerMasterSearchRequest PayerMasterSearchRequest = new PayerMasterSearchRequest();
                    searchCriterionList.add(Restrictions.eq("pdId", billH.getBhPayerRID()));
                    PayerMasterSearchRequest.setSearchCriterionList(searchCriterionList);
                    List<PayerMaster> payerMasterList = masterService.getPayerMaster(PayerMasterSearchRequest, false);
                    for (PayerMaster payerMaster : payerMasterList) {
                        string = dataDictionaryService.getReportFromHTML(string, payerMaster, "payer");
                    }
                    searchCriterionList.clear();
                    DdictSearchRequest ddictSearchRequestTitle = new DdictSearchRequest();
                    searchCriterionList.add(Restrictions.eq("id", Integer.parseInt(billH.getBhPayerType())));
                    ddictSearchRequestTitle.setSearchCriterionList(searchCriterionList);
                    Ddict ddictResultTitle = dataDictionaryService.getDdict(ddictSearchRequestTitle).get(0);
                    if (ddictResultTitle != null) {
                        string = dataDictionaryService.getReportFromHTML(string, ddictResultTitle, "dDictPayer");
                    }
                }
            }

            Patient patient = clinicalService.getPatient(billH.getBhPatientRID());
            string = dataDictionaryService.getReportFromHTML(string, patient, "pat");

            List<Criterion> searchCriterionLists = new ArrayList<>();
            DdictSearchRequest ddictSearchRequestTitle = new DdictSearchRequest();
            searchCriterionLists.add(Restrictions.eq("id", Integer.parseInt(patient.getPatTitle())));
            ddictSearchRequestTitle.setSearchCriterionList(searchCriterionLists);
            Ddict ddictResultTitle = dataDictionaryService.getDdict(ddictSearchRequestTitle).get(0);
            if (ddictResultTitle != null) {
                string = dataDictionaryService.getReportFromHTML(string, ddictResultTitle, "dDictTitle");
            }

//            User user = CacheUtil.getUserById(billH.getCreatedUserRid());
            string = dataDictionaryService.getReportFromHTML(string, CacheUtil.getUserById(billH.getCreatedUserRid()), "usr");

            string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(billHSearchRequest.getEntityRid()), "pe");

        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    private CurrencyM getCurrencyMById(Integer id) {
        List<Criterion> searchCriterionLists = new ArrayList<>();
        CurrencyMSearchRequest currencyMSearchRequest = new CurrencyMSearchRequest();
        searchCriterionLists.add(Restrictions.eq("id", id));
        currencyMSearchRequest.setSearchCriterionList(searchCriterionLists);
        return dataDictionaryService.getCurrencyM(currencyMSearchRequest).get(0);
    }

    @Override
    public String getPayerReport(BillHSearchRequest billHSearchRequest) throws DcometServiceException {
        String string = new String();
        try {
            List<BillH> billHList = getBillH(billHSearchRequest, true);
            if (CollectionUtils.isNotEmpty(billHList)) {
                string = dataDictionaryService.getReportFromTemplate("PAYER_REPORT", billHList, "billHList", billHSearchRequest.getEntityRid());
                List<Criterion> searchCriterionList = new ArrayList<>();
                PayerMasterSearchRequest PayerMasterSearchRequest = new PayerMasterSearchRequest();
                searchCriterionList.add(Restrictions.eq("pdId", billHList.get(0).getBhPayerRID()));
                PayerMasterSearchRequest.setSearchCriterionList(searchCriterionList);
                List<PayerMaster> payerMasterList = masterService.getPayerMaster(PayerMasterSearchRequest, false);
                for (PayerMaster payerMaster : payerMasterList) {
                    string = dataDictionaryService.getReportFromHTML(string, payerMaster, "payer");
                }
                Patient patient = clinicalService.getPatient(billHList.get(0).getBhPatientRID());
                string = dataDictionaryService.getReportFromHTML(string, patient, "pat");
            }

            string = dataDictionaryService.getReportFromHTML(string, masterService.getPrintInfo(billHSearchRequest.getEntityRid()), "pe");

        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return string;
    }

    @Override
    public BillH saveBillHWithCondition(BillH billH, boolean includeChild) throws DcometServiceException {
        BillH billH1 = new BillH();
        int billID = 0;
        if (Objects.equals(billH.getBhActionCode(), "BILL_WITH_APPROVAL")) {
            billH1 = modifyBillH(billH, true);
        } else if (Objects.equals(billH.getBhActionCode(), "DRAFT_BILL_ADD")) {
            billH1 = saveBillH(billH, true);
        } else {
            billID = billH.getId() != null ? billID = billH.getId() : 0;
            int id = save(billH, billID, "BILL", billH.getBhActionCode());
            billH1 = getBillHwithChild(id);
        }
        return billH1;
    }

    @Override
    public BillH modifyBillH(BillH billH, boolean includeChild) throws DcometServiceException {
        BillH billH1 = new BillH();
        BillH billH2 = getBillH(billH.getId(), true);
        try {
            billH.setBhState(billH2.getBhState());
            billH.setBhStatus(billH2.getBhStatus());
            billH.setBhPaidAmount(billH2.getBhPaidAmount());
            billH.setBhEligibleAmount(billH2.getBhEligibleAmount());
            billH.setBhDueAmount(billH2.getBhNetAmount());
            BillHData billHData = billingAdapter.convertBillHToBillHData(billH);
            billingDAO.saveBillH(Arrays.asList(billHData));
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(billH.getBillD())) {

                    for (BillD billD : billH.getBillD()) {
                        if (billD.getId() != null) {
                            for (BillD billD1 : billH2.getBillD()) {
                                if (Objects.equals(billD.getId(), billD1.getId())) {
                                    billD1.setBdDiscountAmount(billD.getBdDiscountAmount());
                                }
                            }
                        }
                    }

                    List<BillDData> billDDatas = billingAdapter.convertBillDToBillDData(billH2.getBillD());
                    for (BillDData billDData : billDDatas) {
                        billDData.setBdBhRID(billHData.getId());
                    }
                    billingDAO.saveBillD(billDDatas);
                }
            }
            billH1 = billingAdapter.convertBillHDataToBillH(billHData, billH.getEntityCurrCode());

        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return billH1;
    }

    @Override
    public BillH saveBillH(BillH billH, boolean includeChild) throws DcometServiceException {
        try {
            BillH billH1 = getBillH(billH.getId(), true);
            billH.setBhState(billH1.getBhState());
            billH.setBhStatus(billH1.getBhStatus());
            billH.setBhDraftBillNo(billH1.getBhDraftBillNo());
            billH.setBhIsDraft(billH1.getBhIsDraft());
            billH.setBhPayerType(billH1.getBhPayerType());
            billH.setBhPayerRID(billH1.getBhPayerRID());
            billH.setBhDocRefRID(billH1.getBhDocRefRID());
            billH.setBhPatientRID(billH1.getBhPatientRID());
            billH.setBhDiscount(billH1.getBhDiscount());
            billH.setBhType(billH1.getBhType());
            if (billH.getBhBillNo() == null) {
                billH.setBhBillNo(billH1.getBhBillNo());
                billH.setBhPrefix(billH1.getBhPrefix());
                billH.setBhPrintableBillNo(billH1.getBhPrintableBillNo());
            }
            billH.setBhDraftBillNo(billH1.getBhDraftBillNo());
            if (CollectionUtils.isNotEmpty(billH1.getBillD())) {
                if (CollectionUtils.isNotEmpty(billH.getBillD())) {
                    for (BillD billD1 : billH1.getBillD()) {
                        for (BillD billD : billH.getBillD()) {
                            if (billD.getId() != null && billD1.getId() != null) {
                                if (Objects.equals(billD1.getId(), billD.getId())) {
                                    billD.setBdGrossAmount(billD.getBdGrossAmount() + billD1.getBdGrossAmount());
                                    billD.setBdNetAmount(billD.getBdNetAmount() + billD1.getBdNetAmount());
                                }
                            }
                        }
                    }
                }
            }
            if (billH.getBhPaidAmount() == null) {
                billH.setBhPaidAmount(0.0f);
            }
            if (billH.getBhEligibleAmount() == null) {
                billH.setBhEligibleAmount(0.0f);
            }
            BillHData billHData = billingAdapter.convertBillHToBillHData(billH);

            billingDAO.saveBillH(Arrays.asList(billHData));
            if (includeChild) {
                if (CollectionUtils.isNotEmpty(billH.getBillD())) {
                    List<BillDData> billDDatas = billingAdapter.convertBillDToBillDData(billH.getBillD());
                    for (BillDData billDData : billDDatas) {
                        billDData.setBdBhRID(billHData.getId());
                        if (billDData.getBdItemPackageRID() != null) {
                            if (billDData.getBdItemPackageRID() != 0) {
                                billDData.setBdGrossAmount(0.0f);
                                billDData.setBdNetAmount(0.0f);
                            }
                        }
                    }
                    if (billH.getBhProcedureIntraService() != null && billH.getBhProcedureIntraService() == 1) {
                        createServiceOrder(billH, billHData, billDDatas);
                    }
                    if (billH.getBhProcedureIntraMaterial() != null && billH.getBhProcedureIntraMaterial() == 1) {
                        createItemOrder(billH, billHData, billDDatas);
                    }
                    billingDAO.saveBillD(billDDatas);
                    List<BillD> billD = billingAdapter.convertBillDDataToBillD(billDDatas);
                    billH.setBillD(billD);
                }
            }
            BillH billH2 = getBillHwithChild(billH.getId());
            float gAmount = 0, nAmount = 0;
            if (CollectionUtils.isNotEmpty(billH2.getBillD())) {
                for (BillD billD : billH2.getBillD()) {
                    int packageRid = 0;
                    if (billD.getBdItemPackageRID() != null && billD.getBdItemPackageRID() != 0) {
                        if (packageRid != billD.getBdItemPackageRID()) {
                            packageRid = billD.getBdItemPackageRID();
                            ServiceMaster serviceMaster = masterService.getServiceMasterByID(billD.getBdItemPackageRID());
                            gAmount += serviceMaster.getbPrice();
                            nAmount += serviceMaster.getbPrice();
                        }
                    } else {
                        gAmount += billD.getBdGrossAmount();
                        nAmount += billD.getBdNetAmount();
                    }
                }
            }
            billH2.setBhGrossAmount(gAmount);
            billH2.setBhNetAmount(nAmount);
            billH2.setBhDueAmount(nAmount);
            BillHData billHData1 = billingAdapter.convertBillHToBillHData(billH2);
            billingDAO.saveBillH(Arrays.asList(billHData1));
            return billH;
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public BillH saveBillHAlone(BillH billH) throws DcometServiceException {
        BillH billH1 = getBillH(billH.getId());
//        Integer disRid = Integer.valueOf(billH.getBhDiscount());
//        Integer disPerc = billH.getBhIsDiscountPercentage();
//
//        billH = billH1;
//        billH.setBhDiscount(disRid);
//        billH.setBhIsDiscountPercentage(disPerc);
//        BillHData billHData = billingAdapter.convertBillHToBillHData(billH);
//        billingDAO.saveBillH(Arrays.asList(billHData));
        return billH;
    }

    @Override
    public List<ServiceRequestH> getServiceRequestByBill(ServiceRequestHSearchRequest serviceRequestHSearchRequest) throws DcometServiceException {
        Integer billRid = 0;
        List<ServiceRequestH> serviceRequestHs = new ArrayList<>();
        serviceRequestHs = serviceRequestService.getServiceRequestH(serviceRequestHSearchRequest, true);
        String dateStop = DateUtil.convertCalendarToString(Calendar.getInstance());

        String[] splited = dateStop.split("\\s+");
        String dateS = splited[0];
        Date visitDate = DateUtil.convertStringToDate(dateS);
        Visit visit = clinicalService.getVisitByPatientRid(serviceRequestHs.get(0).getSerReqhPatRid(), visitDate);
        if (visit == null) {
            visit = clinicalService.getActiveIpVisitByPatientRid(serviceRequestHs.get(0).getSerReqhPatRid());
        } else {
            serviceRequestHs.get(0).setSerReqOpVisitRid(visit.getId());
            serviceRequestHs.get(0).setSerReqhVisitType(visit.getVisTypeIndex());
        }
        if (visit == null) {
            serviceRequestHs.get(0).setSerReqOpVisitRid(0);
            serviceRequestHs.get(0).setSerReqhVisitType(0);
        } else {
            serviceRequestHs.get(0).setSerReqOpVisitRid(visit.getId());
            serviceRequestHs.get(0).setSerReqhVisitType(visit.getVisTypeIndex());
        }
        int visitID = 0;
        if (serviceRequestHs.get(0).getSerReqhVisitType() != null && serviceRequestHs.get(0).getSerReqhVisitType() == 19) {
            visitID = serviceRequestHs.get(0).getSerReqOpVisitRid();
        } else {
            visitID = 0;
        }
        if (serviceRequestHs.get(0).getSerReqhBillHRid() == null || serviceRequestHs.get(0).getSerReqhBillHRid() == 0) {
            billRid = getBillRidFromMaterialReqByPatientRid(serviceRequestHs.get(0).getSerReqhPatRid(), serviceRequestHs.get(0).getSerReqhProcedureRid(), visitID);
            serviceRequestHs.get(0).setSerReqhBillHRid(billRid);
        }
        return serviceRequestHs;
    }

    @Override
    public List<DrugRequestH> getmaterialRequestByBill(DrugRequestHSearchRequest drugRequestHSearchRequest) throws DcometServiceException {
        Integer billRid = 0;
        List<DrugRequestH> drugRequestHs = new ArrayList<>();
        drugRequestHs = itemOrderService.getDrugH(drugRequestHSearchRequest, true);
        String dateStop = DateUtil.convertCalendarToString(Calendar.getInstance());

        String[] splited = dateStop.split("\\s+");
        String dateS = splited[0];
        Date visitDate = DateUtil.convertStringToDate(dateS);
        Visit visit = clinicalService.getActiveIpVisitByPatientRid(drugRequestHs.get(0).getDrugReqHPatRid());

        if (visit == null) {
            visit = clinicalService.getVisitByPatientRid(drugRequestHs.get(0).getDrugReqHPatRid(), visitDate);
        } else {
            drugRequestHs.get(0).setDrugReqHOpVBisitRID(visit.getId());
            drugRequestHs.get(0).setDrugReqHVisitType(visit.getVisTypeIndex());
        }
        if (visit == null) {
            drugRequestHs.get(0).setDrugReqHOpVBisitRID(0);
            drugRequestHs.get(0).setDrugReqHVisitType(0);
        } else {
            drugRequestHs.get(0).setDrugReqHOpVBisitRID(visit.getId());
            drugRequestHs.get(0).setDrugReqHVisitType(visit.getVisTypeIndex());
        }
        int visitID = 0;
        if (drugRequestHs.get(0).getDrugReqHVisitType() != null && drugRequestHs.get(0).getDrugReqHVisitType() == 19) {
            visitID = drugRequestHs.get(0).getDrugReqHOpVBisitRID();
        } else {
            visitID = 0;
        }
        if (drugRequestHs.get(0).getDrugReqHBillHRID() == null || drugRequestHs.get(0).getDrugReqHBillHRID() == 0) {
            billRid = getBillRidFromServiceReqByPatientRid(drugRequestHs.get(0).getDrugReqHPatRid(), drugRequestHs.get(0).getDrugReqHProcedureRid(), visitID);
            drugRequestHs.get(0).setDrugReqHBillHRID(billRid);
        }
        return drugRequestHs;
    }

    private Integer getBillRidByVisitRid(Integer VisitRid) {
        Integer billRid = 0;
        List<Criterion> searchCriterionList = new ArrayList<>();
        BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
        searchCriterionList.add(Restrictions.eq("bhPatientVisitRID", VisitRid));
        billHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<BillH> billHs = getBillH(billHSearchRequest, false);
        if (CollectionUtils.isNotEmpty(billHs)) {
            billRid = billHs.get(0).getId();
        }
        return billRid;
    }

    private Integer getBillRidFromMaterialReqByPatientRid(Integer patRid, Integer procedureRid, Integer visitRid) {
        Integer billRid = 0;
        List<Criterion> searchCriterionList = new ArrayList<>();
        DrugRequestHSearchRequest drugRequestHSearchRequest = new DrugRequestHSearchRequest();
        searchCriterionList.add(Restrictions.eq("drugReqHPatRid", patRid));
        searchCriterionList.add(Restrictions.eq("drugReqHProcedureRid", procedureRid));
        drugRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<DrugRequestH> drugRequestHs = itemOrderService.getDrugH(drugRequestHSearchRequest, false);
        if (CollectionUtils.isNotEmpty(drugRequestHs)) {
            if (drugRequestHs.get(0).getDrugReqHBillHRID() != null && drugRequestHs.get(0).getDrugReqHBillHRID() != 0) {
                billRid = drugRequestHs.get(0).getDrugReqHBillHRID();
            }
        }
        if (Objects.equals(billRid, 0)) {
            billRid = getBillRidByVisitRid(visitRid);
        }
        return billRid;
    }

    private Integer getBillRidFromServiceReqByPatientRid(Integer patRid, Integer procedureRid, Integer visitRid) {
        Integer billRid = 0;
        List<Criterion> searchCriterionList = new ArrayList<>();
        ServiceRequestHSearchRequest serviceRequestHSearchRequest = new ServiceRequestHSearchRequest();
        searchCriterionList.add(Restrictions.eq("serReqhPatRid", patRid));
        searchCriterionList.add(Restrictions.eq("serReqhProcedureRid", procedureRid));
        serviceRequestHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<ServiceRequestH> serviceRequestHs = serviceRequestService.getServiceRequestH(serviceRequestHSearchRequest, false);
        if (CollectionUtils.isNotEmpty(serviceRequestHs)) {
            if (serviceRequestHs.get(0).getSerReqhBillHRid() != null && serviceRequestHs.get(0).getSerReqhBillHRid() != 0) {
                billRid = serviceRequestHs.get(0).getSerReqhBillHRid();
            }
        }
        if (Objects.equals(billRid, 0) && visitRid != 0) {
            billRid = getBillRidByVisitRid(visitRid);
        }
        return billRid;
    }

    @Override
    public BillH getBillDHById(Integer bhrid) throws DcometServiceException {
        BillHSearchRequest billHSearchRequest = new BillHSearchRequest();
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("id", bhrid));
        billHSearchRequest.setSearchCriterionList(searchCriterionList);
        List<BillH> billHs = getBillH(billHSearchRequest, true);
        if (CollectionUtils.isNotEmpty(billHs)) {
            return billHs.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveReceipt(ReceiptH receipt, Integer value) throws DcometServiceException {
        try {
            if (CollectionUtils.isNotEmpty(receipt.getReceiptD())) {
                if (value == 1) {
                    for (ReceiptD receiptDList : receipt.getReceiptD()) {
                        BillH billh = getBillDHById(receiptDList.getRdBillRID());
                        billh.setCurrentObject(receipt);
                        if (billh.getBhPaidAmount() != 0) {
                            billh.setBhPaidAmount(billh.getBhPaidAmount() + receiptDList.getRdPaidAmount());
                            billh.setBhPayerAmount(receiptDList.getRdPaidAmount());
                        } else {
                            billh.setBhPaidAmount(receiptDList.getRdPaidAmount());
                            billh.setBhPayerAmount(receiptDList.getRdPaidAmount());
                        }

                        if (CollectionUtils.isNotEmpty(billh.getReceiptH())) {
                            billh.getReceiptH().clear();
                        }
                        save(billh, billh.getId(), "BILL", "COLLECT_PAYMENT");
                    }
                } else {
                    for (ReceiptD receiptDList : receipt.getReceiptD()) {
                        BillH billh = getBillDHById(receiptDList.getRdBillRID());
                        billh.setCurrentObject(receipt);
                        billh.setBhPaidAmount(billh.getBhPaidAmount() - receiptDList.getRdPaidAmount());
                        billh.setBhDueAmount(billh.getBhDueAmount() + receiptDList.getRdPaidAmount());
                        if (CollectionUtils.isNotEmpty(billh.getReceiptH())) {
                            billh.getReceiptH().clear();
                        }
                        if (Objects.equals(billh.getBhPayerAmount(), receiptDList.getRdPaidAmount())) {
                            billh.setBhPayerAmount(0.0f);
                        }
                        save(billh, 0, "BILL", "BUILTIN_ACTION");
                    }
                }

            }
            if (value == 1) {
                receiptService.save(receipt, 0, "RECEIPT", "CREATE_RECEIPT");
            } else {
                receiptService.save(receipt, receipt.getId(), "RECEIPT", "CANCEL_RECEIPT");
            }
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }

    }
}
