package com.lujian.springmulti.basic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLEntryPoint {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("context.xml");
        Dummy dummy = app.getBean("dummy", Dummy.class);
        System.out.println(dummy);
    }

}
