package com.dcomet.health.dao;

import com.dcomet.module.billing.dao.DCometBillingDAO;
import com.dcomet.module.billing.dao.data.BillDData;
import com.dcomet.module.billing.dao.data.BillHData;
import com.dcomet.module.billing.domain.BillHSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.module.billing.dao.data.AdvanceDetailsData;
import com.dcomet.module.billing.dao.data.PaymentModeDetailsData;
import com.dcomet.module.billing.dao.data.ReceiptDData;
import com.dcomet.module.billing.dao.data.ReceiptHData;
import com.dcomet.module.billing.dao.data.RefundDData;
import com.dcomet.module.billing.dao.data.RefundHData;
import com.dcomet.module.billing.domain.AdvanceDetailsSearchRequest;
import com.dcomet.module.billing.domain.PaymentModeSearchRequest;
import com.dcomet.module.billing.domain.ReceiptHSearchRequest;
import com.dcomet.module.billing.domain.RefundHSearchRequest;
import java.util.List;

/**
 *
 * @author KS
 */
public interface BillingDAO extends DCometBillingDAO{


}
