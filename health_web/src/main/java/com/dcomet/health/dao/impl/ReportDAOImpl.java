package com.dcomet.health.dao.impl;
import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.health.dao.ReportDAO;
import com.dcomet.health.dao.data.rt.RBillCollectionData;
import com.dcomet.health.domain.dbd.ReportSearchRequest;
import com.dcomet.fw.exception.DcometDAOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KS
 */
@Repository("reportDAO")
public class ReportDAOImpl extends HibernateDaoSupport implements ReportDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(
            @Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RBillCollectionData> getRBillCollectionData(ReportSearchRequest reportSearchRequest) throws DcometDAOException {
        List<RBillCollectionData> rBillCollectionDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = reportSearchRequest
                    .getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession()
                    .createCriteria(RBillCollectionData.class);
            defaultSortOrder.createSortCriteria(
                    reportSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            rBillCollectionDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return rBillCollectionDataList;
    }

    @Override
    public List<Object[]> getUnitSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT  unit_name, SUM(bd_gross_amt)AS GrossAmt, SUM(bd_net_amt) AS NetAmt\n"
                + "FROM t_bill_d\n"
                + "INNER JOIN t_bill_h ON bd_bh_rid = bh_rid\n"
                + "INNER JOIN t_service_master ON bs_rid = bd_item_rid\n"
                + "INNER JOIN s_unit ON bs_unit = unit_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid = " + entityRid + " AND bh_status != 4 AND bh_type != 3\n"
                + "GROUP BY bs_unit");
        List<Object[]> entities = sqlQuery.list();
        SQLQuery sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery("SELECT 'Pharmacy' AS unitName, SUM(bd_gross_amt)AS GrossAmt, SUM(bd_net_amt) AS NetAmt\n"
                + "FROM t_bill_d\n"
                + "INNER JOIN t_bill_h ON bd_bh_rid = bh_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid = " + entityRid + " AND bh_status != 4 AND bh_type = 3\n");

        List<Object[]> entities1 = sqlQuery1.list();
        entities.add(entities1.get(0));
        return entities;
    }

//    @Override
//    public List<Object[]> getUnitSalesDetailsReport(String fromDate, String toDate, Integer unitRid, Integer entityRid) throws DcometDAOException {
//        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT bd_net_amt FROM t_bill_h INNER JOIN t_bill_d ON bd_bh_rid = bh_rid  INNER JOIN t_service_master ON bs_rid = bd_item_rid WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid = " + entityRid + " AND bs_unit = " + unitRid + "");
//        List<Object[]> entities = sqlQuery.list();
//        return entities;
//    }
    @Override
    public List<Object[]> getServiceSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, DATE(bh_created_datetime), user_full_name, bh_pat_name, staff_name, vis_ref_name, ddict_value, bd_item_name, bd_net_amt \n"
                + "FROM t_bill_h\n"
                + "INNER JOIN t_bill_d ON bh_rid = bd_bh_rid\n"
                + "INNER JOIN s_ddict ON ddict_index = bd_group_rid\n"
                + "INNER JOIN `s_user` ON bh_user_rid = user_rid\n"
                + "INNER JOIN t_visit ON vis_rid = bh_pat_visit_rid\n"
                + "INNER JOIN s_staff ON bh_doc_ref_rid = staff_rid\n"
                + "INNER JOIN t_patient ON bh_pat_rid = pat_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid =" + entityRid + " AND bh_status != 4;");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
//    @Override
//    public List<Object[]> getPayerSummary(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
//        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DATE(bh_created_datetime),pat_mrn_no,bh_bill_no,pat_name,pat_phone_no,pat_address,bh_approval_number,pd_payer_name,bh_eligible_amount FROM t_bill_h INNER JOIN `t_patient` ON bh_pat_rid=pat_rid INNER JOIN `s_payer` ON pd_id=bh_payer_rid WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid=" + entityRid + " AND bh_payer_type !=31");
//        List<Object[]> entities = sqlQuery.list();
//        return entities;
//    }

    @Override
    public List<Object[]> getPayerSummary(Integer entityRid, String fromDate, String toDate, String payerType) throws DcometDAOException {
        String payerid = " ";
        if (!"0".equals(payerType)) {
            payerid = "AND bh_payer_rid IN (" + payerType + ")";
        }
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DATE(bh_created_datetime),pat_mrn_no,bh_bill_no,pat_name,pat_phone_no,pat_address,bh_approval_number,pd_payer_name,bh_eligible_amount,pd_nuit_id,pd_contact FROM t_bill_h INNER JOIN `t_patient` ON bh_pat_rid=pat_rid INNER JOIN `s_payer` ON pd_id=bh_payer_rid WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid=" + entityRid + " AND bh_payer_type !=31 " + payerid + "");
        List<Object[]> payerReport = sqlQuery.list();
        return payerReport;
    }

    @Override
    public List<Object[]> getCollectionSummaryReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ddict_value, SUM(pmd_amt)\n"
                + "FROM t_payment_mode_details\n"
                + "INNER JOIN s_ddict ON ddict_index = pmd_payment_mode\n"
                + "INNER JOIN t_receipt_h ON rh_rid = pmd_trans_rid AND rh_status NOT IN (2,-2)\n"
                + "WHERE DATE_FORMAT(`pmd_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND pmd_entity_rid = " + entityRid + "\n"
                + "GROUP BY pmd_payment_mode");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }

    @Override
    public List<Object[]> getCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ddict_value, pmd_payment_mode, pmd_amt, user_full_name\n"
                + "FROM t_payment_mode_details\n"
                + "INNER JOIN s_ddict ON ddict_index = pmd_payment_mode\n"
                + "INNER JOIN s_user ON user_rid = pmd_user_rid\n"
                + "INNER JOIN t_receipt_h ON rh_rid = pmd_trans_rid AND rh_status NOT IN (2,-2)\n"
                + "WHERE DATE_FORMAT(`pmd_datetime`, '%Y-%m-%d')  BETWEEN '" + fromDate + "' AND '" + toDate + "' AND pmd_entity_rid=" + entityRid + "");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
//

    @Override
    public List<Object[]> getLaboratoryReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, DATE(bh_created_datetime), user_full_name, bh_pat_name, bh_pat_no, staff_name,\n"
                + "(SELECT ddict_value FROM s_ddict WHERE vis_ref_type_index = ddict_index) AS referralType,\n"
                + "vis_ref_name, bd_net_amt, bd_item_name\n"
                + "FROM t_bill_h\n"
                + "INNER JOIN t_patient ON bh_pat_rid = pat_rid\n"
                + "INNER JOIN s_user ON bh_user_rid = user_rid\n"
                + "INNER JOIN s_staff ON bh_doc_ref_rid = staff_rid\n"
                + "INNER JOIN t_visit ON bh_pat_visit_rid = vis_rid\n"
                + "INNER JOIN t_bill_d ON bh_rid = bd_bh_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid=" + entityRid + " AND bd_group_rid IN (36);");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }

    @Override
    public List<Object[]> getPendingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DATE(bh_created_datetime), bh_bill_no, user_full_name, bh_pat_name,"
                + "(SELECT ddict_value FROM s_ddict WHERE vis_speciality_index = ddict_index)AS Speciality,"
                + "(SELECT ddict_value FROM s_ddict WHERE bh_payer_type = ddict_index)AS PayerType,"
                + "bh_net_amt, bh_total_disc_amt, bh_paid_amt, bh_due_amt\n"
                + "FROM t_bill_h\n"
                + "INNER JOIN t_bill_d ON bh_rid = bd_bh_rid\n"
                + "INNER JOIN s_user ON bh_user_rid = user_rid\n"
                + "INNER JOIN t_visit ON bh_pat_visit_rid = vis_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_status IN (2) AND bh_entity_rid=" + entityRid + "\n"
                + "GROUP BY bh_rid;");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }

    @Override
    public List<Object[]> getPharmacySalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, DATE(bh_created_datetime), user_full_name, bh_pat_name, staff_name, bh_net_amt, bh_total_disc_amt, bh_paid_amt, bh_due_amt, bh_gross_amt\n"
                + "FROM t_bill_h "
                + "INNER JOIN t_bill_d ON bh_rid = bd_bh_rid\n"
                + "INNER JOIN t_patient ON bh_pat_rid = pat_rid\n"
                + "INNER JOIN s_user ON bh_user_rid = user_rid\n"
                + "INNER JOIN s_staff ON bh_doc_ref_rid = staff_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_prefix IN ('PHA/') AND bh_entity_rid =" + entityRid + " AND bh_status NOT IN (4)\n"
                + "GROUP BY bh_rid;");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
    @Override
    public List<Object[]> getWiseCollectionReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT pat_mrn_no, DATE(bh_created_datetime), user_full_name, bh_pat_name, staff_name, bh_net_amt, bh_total_disc_amt, bh_paid_amt, bh_due_amt, bh_gross_amt\n"
                + "FROM t_bill_h "
                + "INNER JOIN t_bill_d ON bh_rid = bd_bh_rid\n"
                + "INNER JOIN t_patient ON bh_pat_rid = pat_rid\n"
                + "INNER JOIN s_user ON bh_user_rid = user_rid\n"
                + "INNER JOIN s_staff ON bh_doc_ref_rid = staff_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_prefix IN ('PHA/') AND bh_entity_rid =" + entityRid + " AND bh_status NOT IN (4)\n"
                + "GROUP BY bh_rid;");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
    @Override
    public List<Object[]> getCancelledReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT bh_bill_no, bh_bill_date,pat_mrn_no, bh_pat_name,\n"
                + "bh_mod_datetime, bh_net_amt,`user_full_name`\n"
                + "FROM `t_bill_h` INNER JOIN `t_patient` ON bh_pat_rid=`pat_rid`\n"
                + "INNER JOIN `s_user` ON `user_rid`= bh_user_rid\n"                
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid =" + entityRid + " AND bh_status IN (4)");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
    @Override
    public List<Object[]> getDiscountBill(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT bh_bill_no, bh_bill_date, pat_mrn_no, bh_pat_name, bh_gross_amt,\n "
                + "bh_total_disc_amt, bh_net_amt, user_full_name  FROM `t_bill_h` INNER JOIN `t_patient` ON bh_pat_rid=`pat_rid`\n"
                + "INNER JOIN `s_user` ON `user_rid`= bh_user_rid \n"                       
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid =" + entityRid + " AND bh_total_disc_amt !=0");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }
//    @Override
//    public List<Object[]> getadvanceReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
//        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT bh_bill_no, bh_bill_date, pat_mrn_no, bh_pat_name, bh_gross_amt,\n "
//                + "bh_total_disc_amt, bh_net_amt, user_full_name  FROM `t_bill_h` INNER JOIN `t_patient` ON bh_pat_rid=`pat_rid`\n"
//                + "INNER JOIN `s_user` ON `user_rid`= bh_user_rid \n"                       
//                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid =" + entityRid + " AND bh_total_disc_amt !=0");
//        List<Object[]> entities = sqlQuery.list();
//        return entities;
//    }

    @Override
    public List<Object[]> getBillingSalesReport(String fromDate, String toDate, Integer entityRid) throws DcometDAOException {
        SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DATE(bh_created_datetime)AS DATE, bh_bill_no, user_full_name, bh_pat_name, staff_name, bh_gross_amt, bh_net_amt, bh_paid_amt, bh_total_disc_amt,\n"
                + "(SELECT ddict_value FROM s_ddict WHERE vis_speciality_index = ddict_index)AS Speciality\n"
                + "FROM t_bill_h "
                + "INNER JOIN s_user ON user_rid = bh_user_rid\n"
                + "INNER JOIN s_staff ON staff_rid = bh_doc_ref_rid\n"
                + "LEFT JOIN t_visit ON bh_pat_visit_rid = vis_rid\n"
                + "WHERE DATE_FORMAT(`bh_created_datetime`, '%Y-%m-%d') BETWEEN '" + fromDate + "' AND '" + toDate + "' AND bh_entity_rid =" + entityRid + " AND bh_status NOT IN (4)\n"
                + "GROUP BY bh_rid;");
        List<Object[]> entities = sqlQuery.list();
        return entities;
    }

}
