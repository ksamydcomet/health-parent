package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.FileDData;
import com.dcomet.health.dao.data.FileHData;
import com.dcomet.health.domain.FileAttachment;
import com.dcomet.health.domain.FileAttachmentSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface FileService {

    public void saveFileInfo(FileHData fileInfo) throws DcometServiceException;

    public FileHData getFileInfo(Integer id, Integer type) throws DcometServiceException;

    public void deleteFile(Integer id) throws DcometServiceException;

    public FileDData storeFile(FileDData file) throws DcometServiceException;

    public FileDData updateFile(FileDData file) throws DcometServiceException;

    public FileDData getFile(Integer fileId) throws DcometServiceException;

    public List<FileAttachment> getFiles(FileAttachmentSearchRequest fileUploadSearchRequest) throws DcometServiceException;

    public FileAttachmentData getFileById(Integer id, Integer type) throws DcometServiceException;
    
    public List<FileAttachmentData> getByRefId(Integer refRid, Integer type) throws DcometServiceException;
    
    public FileAttachmentData getByReferrenceId(Integer refRid, Integer type) throws DcometServiceException;

    public FileAttachmentData saveFileAttachment(Integer refRid, Integer type, FileAttachmentData file) throws DcometServiceException;

}
