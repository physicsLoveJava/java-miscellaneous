package com.lujian.techs.log4j;

import org.apache.log4j.BasicConfigurator;

public class ConsoleMain {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        Log4jService service = new Log4jService();

        service.test();
        service.testTimingMethod();
    }

}
