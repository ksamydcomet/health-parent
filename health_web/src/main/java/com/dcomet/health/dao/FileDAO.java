package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.FileDData;
import com.dcomet.health.dao.data.FileHData;
import com.dcomet.health.domain.FileAttachmentSearchRequest;
import java.util.List;

/**
 *
 * @author Dev4
 */
public interface FileDAO {

    public void saveFileInfo(FileHData fileInfo) throws DcometDAOException;

    public FileHData getFileInfo(Integer id, Integer type) throws DcometDAOException;

    public void deleteFile(Integer id) throws DcometDAOException;

    public FileDData storeFile(FileDData file) throws DcometDAOException;

    public FileDData updateFile(FileDData file) throws DcometDAOException;

    public FileDData getFile(Integer fileId) throws DcometDAOException;
    
    public List<FileAttachmentData> getFiles(FileAttachmentSearchRequest fileUploadSearchRequest) throws DcometDAOException;

    public FileAttachmentData getFileById(Integer id,Integer type) throws DcometDAOException;
    
    public List<FileAttachmentData> getByRefId(Integer refRid,Integer type) throws DcometDAOException;
    
    public FileAttachmentData getByReferenceId(Integer refRid,Integer type) throws DcometDAOException;

    public void saveFiles(List<FileAttachmentData> file) throws DcometDAOException;
}
