package com.lujian.techs.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jService {

    private static Logger logger = LoggerFactory.getLogger(Slf4jService.class);

    public static void main(String[] args) {
        logger.info("Hello World");
        logger.info("params {} {} {}", "param1", "param2", "param3");
    }

}
