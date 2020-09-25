package com.dcomet.health.dao.impl;

import com.dcomet.fw.dao.helper.DefaultSortOrder;
import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.FileDAO;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.FileDData;
import com.dcomet.health.dao.data.FileHData;
import com.dcomet.health.domain.FileAttachmentSearchRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dev4
 */
@Repository("fileDAO")
public class FileDAOImpl extends HibernateDaoSupport implements FileDAO {

    @Autowired
    public void setDCometSessionFactory(@Qualifier("dcometSessionFactory") SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Autowired
    @Qualifier("defaultSortOrder")
    private DefaultSortOrder defaultSortOrder;

    @Override
    public FileDData storeFile(FileDData file) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(file);
            getSessionFactory().getCurrentSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
        return file;
    }

    @Override
    public FileDData updateFile(FileDData file) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(file);
            getSessionFactory().getCurrentSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
        return file;
    }

    @Override
    public FileDData getFile(Integer fileId) throws DcometDAOException {
        List<FileDData> fileDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileDData.class);
            criteria.add(Restrictions.eq("id", fileId));
            criteria.setFetchSize(1);
            fileDataList = criteria.list();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(fileDataList) ? fileDataList.get(0) : null;
    }

    @Override
    public void saveFileInfo(FileHData fileInfo) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(fileInfo);
            getSessionFactory().getCurrentSession().flush();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
    }

    @Override
    public FileHData getFileInfo(Integer id, Integer type) throws DcometDAOException {
        List<FileHData> fileDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileHData.class);
            criteria.add(Restrictions.eq("refRid", id));
            criteria.add(Restrictions.eq("refType", type));
            criteria.setFetchSize(1);
            fileDataList = criteria.list();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(fileDataList) ? fileDataList.get(0) : null;
    }

    @Override
    public void deleteFile(Integer id) throws DcometDAOException {
        try {
            Query query = getSessionFactory().getCurrentSession().createQuery("delete FileDData where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            throw new DcometDAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FileAttachmentData> getFiles(FileAttachmentSearchRequest fileAttachmentSearchRequest) throws DcometDAOException {
        List<FileAttachmentData> fileUploadDataList = new ArrayList<>();
        try {
            List<Criterion> criteriaList = fileAttachmentSearchRequest.getSearchCriterionList();
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileAttachmentData.class);
            defaultSortOrder.createSortCriteria(fileAttachmentSearchRequest.getSortOrder(), criteria);
            if (criteriaList != null) {
                for (Criterion criterion : criteriaList) {
                    criteria.add(criterion);
                }
            }
            fileUploadDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return fileUploadDataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FileAttachmentData getFileById(Integer id, Integer type) throws DcometDAOException {
        List<FileAttachmentData> fileUploadDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileAttachmentData.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("taActive", 1));
            criteria.add(Restrictions.eq("taType", type));

            criteria.setFetchSize(1);
            fileUploadDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(fileUploadDataList) ? fileUploadDataList.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveFiles(List<FileAttachmentData> fileAttachmentDataList) throws DcometDAOException {
        try {
            getSessionFactory().getCurrentSession().clear();
            for (FileAttachmentData fileAttachmentData : fileAttachmentDataList) {
                if (fileAttachmentData.getTaActive() == 0) {
                    getSessionFactory().getCurrentSession().delete(fileAttachmentData);
                } else {
                    getSessionFactory().getCurrentSession().saveOrUpdate(fileAttachmentData);
                }
            }
            getSessionFactory().getCurrentSession().flush();
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

    @SuppressWarnings("unchecked")
    @Override
    public List<FileAttachmentData> getByRefId(Integer refRid, Integer type) throws DcometDAOException {
        List<FileAttachmentData> fileUploadDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileAttachmentData.class);
            criteria.add(Restrictions.eq("taRefRid", refRid));
            criteria.add(Restrictions.eq("taActive", 1));
            criteria.add(Restrictions.eq("taType", type));

            criteria.setFetchSize(1);
            fileUploadDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(fileUploadDataList) ? fileUploadDataList : null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public FileAttachmentData getByReferenceId(Integer refRid, Integer type) throws DcometDAOException {
        List<FileAttachmentData> fileUploadDataList = new ArrayList<>();
        try {
            Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(FileAttachmentData.class);
            criteria.add(Restrictions.eq("taRefRid", refRid));
            criteria.add(Restrictions.eq("taActive", 1));
            criteria.add(Restrictions.eq("taType", type));

            criteria.setFetchSize(1);
            fileUploadDataList = criteria.list();
        } catch (DataAccessException e) {
            throw new DcometDAOException(e);
        } catch (HibernateException e) {
            throw new DcometDAOException(e);
        } catch (Exception e) {
            throw new DcometDAOException(e);
        } catch (Throwable e) {
            throw new DcometDAOException(e);
        }
        return CollectionUtils.isNotEmpty(fileUploadDataList) ? fileUploadDataList.get(0) : null;
    }
}
