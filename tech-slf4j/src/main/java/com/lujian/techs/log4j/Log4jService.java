package com.lujian.techs.log4j;

import org.apache.log4j.Logger;

public class Log4jService {

    private static Logger logger = Logger.getLogger(Log4jService.class);

    public void test() {
        logger.debug("this is test method");
    }

    public void testTimingMethod() {
        logger.debug("timing method start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("timing method end");
    }

}
