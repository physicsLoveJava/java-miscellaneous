package com.lujian.techs.log4j;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.net.URL;

public class FileMain {

    public static void main(String[] args) {

        URL url = FileMain.class.getResource("/log4j/log4j.properties");
        PropertyConfigurator.configure(url);

        Log4jService service = new Log4jService();

        service.test();
        service.testTimingMethod();

    }

}
