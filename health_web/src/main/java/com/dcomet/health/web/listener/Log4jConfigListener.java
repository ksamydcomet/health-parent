package com.dcomet.health.web.listener;

import java.net.URL;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.LoggerFactory;

public class Log4jConfigListener implements ServletContextListener {

    public static final String LOG4J_PROPERTY_FILE = "log4j.property.file";
    public static final String LOG4J_PROPERTY_REFRESH_SECS = "log4j.property.refreshSecs";

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LogManager.shutdown();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        String propertyFileName = event.getServletContext().getInitParameter(LOG4J_PROPERTY_FILE);
        if (propertyFileName == null) {
            System.err.println("Unable to find health_service_log4j.xml file.");
            propertyFileName = "classpath:log4j.xml";
        }

        if (propertyFileName.startsWith("classpath")) {
            propertyFileName = propertyFileName.substring("classpath:".length());
            URL configFileUrl = getClass().getClassLoader().getResource(propertyFileName);
            if (configFileUrl == null) {
                System.err.println("Unable to find log4j.xml file.");
            }
            propertyFileName = configFileUrl.getPath();
        }

        String refreshInSecsStr = event.getServletContext().getInitParameter(LOG4J_PROPERTY_REFRESH_SECS);
        long refreshInSecs = 0;
        try {
            if (refreshInSecsStr != null) {
                refreshInSecs = Long.parseLong(refreshInSecsStr);
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Unable to find log4j.property.refreshSecs time.");
            return;
        }

        if (propertyFileName.toLowerCase().endsWith(".xml")) {
            if (refreshInSecs > 0) {
                DOMConfigurator.configureAndWatch(propertyFileName, refreshInSecs * 1000L);
            } else {
                DOMConfigurator.configure(propertyFileName);
            }
        } else {
            if (refreshInSecs > 0) {
                DOMConfigurator.configureAndWatch(propertyFileName, refreshInSecs * 1000L);
            } else {
                DOMConfigurator.configure(propertyFileName);
            }
        }
        LoggerFactory.getLogger(Log4jConfigListener.class).info(
                "Loaded log4j config file {} with refresh time {} ",
                propertyFileName, refreshInSecs);
    }
}
