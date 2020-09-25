package com.dcomet.health.batch.job.scheduler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigLocator {

    private ConfigLocator() {

    }

    private ClassPathXmlApplicationContext factory;

    private static final ConfigLocator instance = new ConfigLocator();

    public static final ConfigLocator getInstance() {
        return instance;
    }

    protected synchronized ClassPathXmlApplicationContext getContext() {
        factory = new ClassPathXmlApplicationContext(
                "health_test_batchjob_applicationcontext.xml");
        return factory;
    }

    public final Object getBean(final String serviceName) {
        return getContext().getBean(serviceName);
    }

}
