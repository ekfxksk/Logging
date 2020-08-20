package com.kyu.test.controller;


import ch.qos.logback.classic.Logger;
import com.kyu.test.service.TestService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testSvc;

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test")
    public String test(){

        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");


        testSvc.testService();

        return "";
    }
}
