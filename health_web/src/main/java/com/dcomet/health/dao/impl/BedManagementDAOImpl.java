package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.BedManagementDAO;
import com.dcomet.health.dao.data.BedCancellationHistoryData;
import com.dcomet.health.dao.data.BedChargeDefinitionData;
import com.dcomet.health.dao.data.BedMasterData;
import com.dcomet.health.dao.data.BedOccupancyData;
import com.dcomet.health.dao.data.BedOccupancyDetailsData;
import com.dcomet.health.dao.data.BedTransferData;
import com.dcomet.health.dao.data.BedTransferRequestData;
import com.dcomet.health.dao.data.BedGroupMData;
import com.dcomet.health.dao.data.BedTypeOccupancySummaryData;
import com.dcomet.health.dao.data.BedWardData;
import com.dcomet.health.dao.data.BirthDetailsData;
import com.dcomet.health.dao.data.DischargeData;
import com.dcomet.health.domain.BedCancellationHistorySearchRequest;
import com.dcomet.health.domain.BedChargeDefinitionSearchRequest;
import com.dcomet.health.domain.BedOccupancyDetailsSearchRequest;
import com.dcomet.health.domain.BedOccupancySearchRequest;
import com.dcomet.health.domain.BedSearchRequest;
import com.dcomet.health.domain.BedTransferRequestSearchRequest;
import com.dcomet.health.domain.BedTransferSearchRequest;
import com.dcomet.health.domain.BedGroupMSearchRequest;
import com.dcomet.health.domain.BedMasterSearchRequest;
import com.dcomet.health.domain.BedTypeOccupancySummarySearchRequest;
import com.dcomet.health.domain.BedWardSearchRequest;
import com.dcomet.health.domain.BirthDetailsSearchRequest;
import com.dcomet.health.domain.DischargeSearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("bedManagementDAO")
public class BedManagementDAOImpl extends HibernateDaoSupport implements BedManagementDAO {

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Autowired
    public void setDCometSessionFactory(
            @Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<BedCancellationHistoryData> getBedCancellationHistory(BedCancellationHistorySearchRequest bedCancellationHistorySearchRequest) throws DcometDAOException {
        List<BedCancellationHistoryData> bedCancellationHistoryDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedCancellationHistorySearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedCancellationHistoryData.class);
            defaultSortOrder.createSortCriteria(bedCancellationHistorySearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedCancellationHistoryDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedCancellationHistoryDataList;
    }

    @Override
    public void saveBedCancellationHistory(BedCancellationHistoryData bedCancellationHistoryData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedCancellationHistoryData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedChargeDefinitionData> getBedChargeDefinition(BedChargeDefinitionSearchRequest bedChargeDefinitionSearchRequest) throws DcometDAOException {
        List<BedChargeDefinitionData> bedChargeDefinitionDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedChargeDefinitionSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedCancellationHistoryData.class);
            defaultSortOrder.createSortCriteria(bedChargeDefinitionSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedChargeDefinitionDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedChargeDefinitionDataList;
    }

    @Override
    public void saveBedChargeDefinition(BedChargeDefinitionData bedChargeDefinitionData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedChargeDefinitionData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedMasterData> getBed(BedSearchRequest bedSearchRequest) throws DcometDAOException {
        List<BedMasterData> bedDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedCancellationHistoryData.class);
            defaultSortOrder.createSortCriteria(bedSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedDataList;
    }

    @Override
    public List<BedMasterData> getBedMaster(BedMasterSearchRequest bedMasterSearchRequest) throws DcometDAOException {
        List<BedMasterData> bedDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedMasterSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedMasterData.class);
            defaultSortOrder.createSortCriteria(bedMasterSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedDataList;
    }

    @Override
    public void saveBed(BedMasterData bedData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }

    }

    @Override
    public List<BedOccupancyData> getBedOccupancy(BedOccupancySearchRequest bedOccupancySearchRequest) throws DcometDAOException {
        List<BedOccupancyData> bedOccupancyDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedOccupancySearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedOccupancyData.class);
            defaultSortOrder.createSortCriteria(bedOccupancySearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedOccupancyDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedOccupancyDataList;
    }

    @Override
    public void saveBedOccupancyData(BedOccupancyData bedOccupancyData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedOccupancyData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedOccupancyDetailsData> getBedOccupancyDetails(BedOccupancyDetailsSearchRequest bedOccupancyDetailsSearchRequest) throws DcometDAOException {
        List<BedOccupancyDetailsData> bedOccupancyDetailsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedOccupancyDetailsSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedOccupancyDetailsData.class);
            defaultSortOrder.createSortCriteria(bedOccupancyDetailsSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedOccupancyDetailsDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedOccupancyDetailsDataList;
    }

    @Override
    public void saveBedOccupancyDetails(BedOccupancyDetailsData bedOccupancyDetailsData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedOccupancyDetailsData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedTransferData> getBedTransfer(BedTransferSearchRequest bedTransferSearchRequest) throws DcometDAOException {
        List<BedTransferData> bedTransferDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedTransferSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedTransferData.class);
            defaultSortOrder.createSortCriteria(bedTransferSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedTransferDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedTransferDataList;
    }

    @Override
    public void saveBedTransfer(BedTransferData bedTransferData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedTransferData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedTransferRequestData> getBedTransferRequest(BedTransferRequestSearchRequest bedTransferRequestSearchRequest) throws DcometDAOException {
        List<BedTransferRequestData> bedTransferRequestDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedTransferRequestSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedTransferRequestData.class);
            defaultSortOrder.createSortCriteria(bedTransferRequestSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedTransferRequestDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedTransferRequestDataList;
    }

    @Override
    public void saveBedTransferRequest(BedTransferRequestData bedTransferRequestData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedTransferRequestData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public void saveBedMaster(List<BedMasterData> bedDataList) throws DcometDAOException {
        try {
            getSession().clear();
            for (BedMasterData bedMasterData : bedDataList) {
                getSessionFactory().getCurrentSession().saveOrUpdate(bedMasterData);
            }
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedGroupMData> getBedGroupMaster(BedGroupMSearchRequest bedTypeMSearchRequest) throws DcometDAOException {
        List<BedGroupMData> bedTypeMDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedTypeMSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedGroupMData.class);
            defaultSortOrder.createSortCriteria(bedTypeMSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedTypeMDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedTypeMDataList;
    }

    @Override
    public void saveBedGroupMaster(BedGroupMData bedTypeMData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedTypeMData);
            getSession().flush();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedTypeOccupancySummaryData> getBedTypeOccupancySummary(BedTypeOccupancySummarySearchRequest bedTypeOccupancySummarySearchRequest) throws DcometDAOException {
        List<BedTypeOccupancySummaryData> bedTypeOccupancySummaryDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedTypeOccupancySummarySearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedTypeOccupancySummaryData.class);
            defaultSortOrder.createSortCriteria(bedTypeOccupancySummarySearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedTypeOccupancySummaryDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedTypeOccupancySummaryDataList;
    }

    @Override
    public void saveBedTypeOccupancySummary(BedTypeOccupancySummaryData bedTypeOccupancySummaryData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedTypeOccupancySummaryData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedWardData> getBedWard(BedWardSearchRequest bedWardSearchRequest) throws DcometDAOException {
        List<BedWardData> bedWardDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = bedWardSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedWardData.class);
            defaultSortOrder.createSortCriteria(bedWardSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            bedWardDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return bedWardDataList;
    }

    @Override
    public void saveBedWard(BedWardData bedWardData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(bedWardData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BirthDetailsData> getBirthDetails(BirthDetailsSearchRequest birthDetailsSearchRequest) throws DcometDAOException {
        List<BirthDetailsData> birthDetailsDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = birthDetailsSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BirthDetailsData.class);
            defaultSortOrder.createSortCriteria(birthDetailsSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            birthDetailsDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return birthDetailsDataList;
    }

    @Override
    public void saveBirthDetails(BirthDetailsData birthDetailsData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(birthDetailsData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public List<BedGroupMData> getBedTypeMList(List<Criterion> criterionList) throws DcometDAOException {
        List<BedGroupMData> bedTypeMDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedGroupMData.class);
            if (criterionList != null) {
                for (Criterion criterion : criterionList) {
                    criteria.add(criterion);
                }
            }
            bedTypeMDataList = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return bedTypeMDataList;
    }

    @Override
    public List<BedMasterData> getBedMasterList(List<Criterion> criterionList) throws DcometDAOException {
        List<BedMasterData> bedMasterDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BedMasterData.class);
            if (criterionList != null) {
                for (Criterion criterion : criterionList) {
                    criteria.add(criterion);
                }
            }
            bedMasterDataList = criteria.list();
        } catch (DataAccessException | HibernateException e) {
            throw new DcometDAOException(e);
        }
        return bedMasterDataList;
    }

    @Override
    public List<DischargeData> getDischargeDetails(DischargeSearchRequest dischargeSearchRequest) throws DcometDAOException {
        List<DischargeData> dischargeDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = dischargeSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DischargeData.class);
            defaultSortOrder.createSortCriteria(dischargeSearchRequest.getSortOrder(), criteria);
            if (CollectionUtils.isNotEmpty(criteriaList)) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            dischargeDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return dischargeDataList;
    }

    @Override
    public void saveDischargeDetails(DischargeData dischargeData) throws DcometDAOException {
        try {
            getSession().clear();
            getSessionFactory().getCurrentSession().saveOrUpdate(dischargeData);
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
    }
}
