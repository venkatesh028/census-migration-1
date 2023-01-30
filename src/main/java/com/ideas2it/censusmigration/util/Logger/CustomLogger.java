package com.ideas2it.censusmigration.util.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class CustomLogger {
    private final Logger logger;

    public CustomLogger(Class<?> className) {
        logger = LogManager.getLogger(className);
    }

    public void info(String message){
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message){
        logger.error(message);
    }

    public void debug(String message){
        logger.debug(message);
    }
}
