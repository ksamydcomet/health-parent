package com.dcomet.health.dao;

import com.dcomet.fw.exception.DcometDAOException;
import com.dcomet.health.dao.data.ProcedureAnesthesistData;
import com.dcomet.health.dao.data.ProcedureAttendDoctorData;
import com.dcomet.health.dao.data.ProcedureNurseData;
import com.dcomet.health.dao.data.ProcedureRequestHData;
import com.dcomet.health.dao.data.ProcedureTechnicianData;
import com.dcomet.health.domain.ProcedureRequestHSearchRequest;
import java.util.List;

/**
 *
 * @author CVS
 */
public interface ProcedureRequestDAO {
//--------ProcedureRequestH---------------

    public List<ProcedureRequestHData> getProcedureRequestH(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;

    public void saveProcedureRequestH(ProcedureRequestHData procedureRequestHData) throws DcometDAOException;

    public void saveProcedureAttendDoctor(List<ProcedureAttendDoctorData> procedureAttendDoctorDataList) throws DcometDAOException;

    public void saveProcedureNurse(List<ProcedureNurseData> procedureNurseDataList) throws DcometDAOException;

    public void saveProcedureTechnician(List<ProcedureTechnicianData> procedureTechnicianDataList) throws DcometDAOException;

    public void saveProcedureAnesthesist(List<ProcedureAnesthesistData> procedureAnesthesistDataList) throws DcometDAOException;

    public List<ProcedureAttendDoctorData> getProcedureAttendDoctor(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;

    public List<ProcedureNurseData> getProcedureNurse(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;

    public List<ProcedureTechnicianData> getProcedureTechnician(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;

    public List<ProcedureAnesthesistData> getProcedureAnesthesist(ProcedureRequestHSearchRequest procedureRequestHSearchRequest) throws DcometDAOException;

}
