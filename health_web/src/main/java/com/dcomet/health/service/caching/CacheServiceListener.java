package com.dcomet.health.service.caching;

import com.dcomet.health.caching.CacheDataConfig;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cacheServiceListener")
public class CacheServiceListener {

    private static Logger logger = LoggerFactory
            .getLogger(CacheServiceListener.class);

    @Autowired
    @Qualifier("cacheDataConfig")
    CacheDataConfig cacheDataConfig;

    @PostConstruct
    public void startCache() {
        logger.info("Caching is started.");
        try {
            cacheDataConfig.startCaching();
        } catch (Exception e) {
            logger.error("Cahcing failed due to {} : {}", e.getMessage(), e);
        }
        logger.info("Caching successfully Done.");
    }

    @PreDestroy
    public void clearCache() {
    }
}
