package com.dcomet.health.batch.job.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.dcomet.health.batch.job.AbstractJob;

/**
 * this bean is what we actually schedule using Quartz. This bean runs every
 * minute. We don't let it overlap, hence 'Stateful'
 */
public class ReportSchedule extends QuartzJobBean implements StatefulJob {

    private static Logger logger = LoggerFactory.getLogger(ReportSchedule.class);

    public void execute(ApplicationContext appContext) throws Throwable {
        Object beanHavingJobInName = appContext.getBean("BBReportScheduleJob");
        if (beanHavingJobInName instanceof AbstractJob) {
            AbstractJob job = (AbstractJob) beanHavingJobInName;
            job.resetExecutionContext();
            job.executeInternal();
            job.resetExecutionContext();
        }
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Scheduler scheduler = jobExecutionContext.getScheduler();
            SchedulerContext context = scheduler.getContext();
            ApplicationContext appContext = (ApplicationContext) context.get("applicationContext");
            execute(appContext);

        } catch (Throwable throwable) {
            logger.error("Unable to load application context (or) bean: {}" + throwable.getMessage());
        }
    }
}
