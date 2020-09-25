package com.dcomet.health.service.business.impl;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.adapter.MasterAdapter;
import com.dcomet.health.dao.FileDAO;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.FileDData;
import com.dcomet.health.dao.data.FileHData;
import com.dcomet.health.domain.FileAttachment;
import com.dcomet.health.domain.FileAttachmentSearchRequest;
import com.dcomet.health.service.business.FileService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dev4
 */
@Service("fileService")
@Transactional(propagation = Propagation.SUPPORTS)
public class FileServiceImpl implements FileService {

    @Autowired
    @Qualifier("fileDAO")
    FileDAO fileDAO;

    @Autowired
    @Qualifier("masterAdapter")
    MasterAdapter masterAdapter;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FileDData storeFile(FileDData file) throws DcometServiceException {
        try {
            return fileDAO.storeFile(file);
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FileDData updateFile(FileDData file) throws DcometServiceException {
        try {
            return fileDAO.updateFile(file);
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public FileDData getFile(Integer fileId) throws DcometServiceException {
        try {
            return fileDAO.getFile(fileId);
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFileInfo(FileHData file) throws DcometServiceException {
        fileDAO.saveFileInfo(file);
    }

    @Override
    public FileHData getFileInfo(Integer fileId, Integer type) throws DcometServiceException {
        try {
            return fileDAO.getFileInfo(fileId, type);
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public void deleteFile(Integer fileId) throws DcometServiceException {
        try {
            fileDAO.deleteFile(fileId);
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<FileAttachment> getFiles(FileAttachmentSearchRequest fileAttachmentSearchRequest) throws DcometServiceException {
        List<FileAttachment> result = null;
        try {
            List<FileAttachmentData> resultData = fileDAO.getFiles(fileAttachmentSearchRequest);
            if (CollectionUtils.isNotEmpty(resultData)) {
                result = masterAdapter.convertFileUploadDataToFileUpload(resultData);
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

    @Override
    public FileAttachmentData getFileById(Integer id, Integer type) throws DcometServiceException {
        try {
            return fileDAO.getFileById(id, type);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    public List<FileAttachmentData> getByRefId(Integer refRid, Integer type) throws DcometServiceException {
        try {
            return fileDAO.getByRefId(refRid, type);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }
    
    @Override
    public FileAttachmentData getByReferrenceId(Integer refRid, Integer type) throws DcometServiceException {
        try {
            return fileDAO.getByReferenceId(refRid, type);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FileAttachmentData saveFileAttachment(Integer refRid, Integer type, FileAttachmentData file) throws DcometDAOException {
        try {
            List<FileAttachmentData> files = new ArrayList<>();
//            FileAttachmentData fileUploadData = fileDAO.getByRefId(refRid, type);
//            if (fileUploadData != null) {
//                fileUploadData.setTaActive(0);
//                files.add(fileUploadData);
//            }
            file.setTaActive(1);
            files.add(file);
            fileDAO.saveFiles(files);
        } catch (DcometDAOException e) {
            throw new DcometServiceException(e);
        } catch (DcometServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new DcometServiceException(e);
        }
        return getByReferrenceId(refRid, type);
    }
}
