package com.kyu.test.service.impl;

import ch.qos.logback.classic.Logger;
import com.kyu.test.service.TestService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public void testService() {
        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");
    }
}
