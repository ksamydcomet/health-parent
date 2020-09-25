package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.module.laboratory.domain.LabResultH;
import com.dcomet.module.laboratory.domain.LabResultHSearchRequest;
import com.dcomet.module.laboratory.service.DCometLaboratoryService;
import java.util.List;

/**
 *
 * @author Adhithya
 */
public interface LaboratoryService extends DCometLaboratoryService {

    public List<LabResultH> getLabResultH(LabResultHSearchRequest labResultHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public String getLabServicePrint(LabResultHSearchRequest labResultHSearchRequest,Integer groupId) throws DcometServiceException;
    
    public List<LabResultH> getLabResultHForPrint(LabResultHSearchRequest labResultHSearchRequest, boolean includeChilds, Integer groupId) throws DcometServiceException;

}
