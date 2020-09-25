package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.fw.workflow.service.WorkFlowService;
import com.dcomet.health.domain.ProcedureAnesthesist;
import com.dcomet.health.domain.ProcedureAttendDoctor;
import com.dcomet.health.domain.ProcedureNurse;
import com.dcomet.health.domain.ProcedureRequestH;
import com.dcomet.health.domain.ProcedureRequestHSearchRequest;
import com.dcomet.health.domain.ProcedureTechnician;
import java.util.List;

/**
 *
 * @author CVS
 */
public interface ProcedureRequestService extends WorkFlowService {

    //-----------ProcedureRequestH---------------
    public void saveProcedureRequestH(ProcedureRequestH procedureRequestH) throws DcometDAOException;

    public List<ProcedureRequestH> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest, boolean includeChilds) throws DcometServiceException;

    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctor> procedureAttendDoctorList) throws DcometDAOException;

    public void saveProcedureNurse(List<ProcedureNurse> procedureNurseList) throws DcometServiceException;

    public void saveProcedureTechnician(List<ProcedureTechnician> procedureTechnicianList) throws DcometServiceException;

    public List<ProcedureAttendDoctor> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException;

    public List<ProcedureNurse> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException;

    public List<ProcedureTechnician> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException;

    public List<ProcedureAnesthesist> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometServiceException;

}
