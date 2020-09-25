package com.dcomet.health.batch.dao.fn;

import com.dcomet.fw.exception.DcometBatchException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

@Repository("fnDailyReport")
public class FnDailyReport {

    @Autowired
    DataSource adAppDataSource;

    public void call(Date date) throws DcometBatchException {
        DashBoard procedure = new DashBoard(adAppDataSource);
        procedure.execute(date);
    }

    private static class DashBoard extends StoredProcedure {

        public DashBoard(DataSource datasource) throws DcometBatchException {
            super(datasource, "fn_daily_report");
            declareParameter(new SqlParameter("p_date", Types.DATE));
            try {
                compile();
            } catch (InvalidDataAccessApiUsageException e) {
                throw new DcometBatchException(e);
            } catch (DataAccessException e) {
                throw new DcometBatchException(e);
            } catch (HibernateException e) {
                throw new DcometBatchException(e);
            } catch (Exception e) {
                throw new DcometBatchException(e);
            } catch (Throwable e) {
                throw new DcometBatchException(e);
            }
        }

        public void execute(Date date) throws DcometBatchException {
            try {
                Map<String, Object> inputs = new HashMap<>();
                inputs.put("p_date", date);
                super.execute(inputs);
            } catch (InvalidDataAccessApiUsageException e) {
                throw new DcometBatchException(e);
            } catch (DataAccessException e) {
                throw new DcometBatchException(e);
            } catch (HibernateException e) {
                throw new DcometBatchException(e);
            } catch (Exception e) {
                throw new DcometBatchException(e);
            } catch (Throwable e) {
                throw new DcometBatchException(e);
            }
        }
    }
}
