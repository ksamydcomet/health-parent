package com.dcomet.health.batch.job;

import java.util.Date;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class is meant to be subclassed by individual jobs that need attention.
 * For example, any emails that need to get sent should subclass this.
 */
abstract public class AbstractJob implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void executeInternal() throws Throwable {
        Date now = new Date();

        for (int i = 0; i < executions; i++) {
            if (isShouldContinue()) {
                executeJob(now);
            }
        }
    }

    public void resetExecutionContext() {
        this.executions = 1;
        this.shouldContinue = true;
    }

    private boolean shouldContinue = true;
    private int executions = 1;

    public int getExecutions() {
        return executions;
    }

    public void setExecutions(int executions) {
        this.executions = executions;
    }

    synchronized public boolean isShouldContinue() {
        return shouldContinue;
    }

    synchronized public void setShouldContinue(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    abstract public void executeJob(Date timeOfBatchRun) throws Throwable;
}
