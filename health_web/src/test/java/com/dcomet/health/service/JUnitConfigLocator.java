package com.dcomet.health.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JUnitConfigLocator {

    private ClassPathXmlApplicationContext factory;

    private static final JUnitConfigLocator instance = new JUnitConfigLocator();

    public static final JUnitConfigLocator getInstance() {
        return instance;
    }

    protected synchronized ClassPathXmlApplicationContext getContext() {
        factory = new ClassPathXmlApplicationContext(
                "health-test-service-applicationcontext.xml");
        return factory;
    }

    public final Object getBean(final String serviceName) {
        return getContext().getBean(serviceName);
    }

}
