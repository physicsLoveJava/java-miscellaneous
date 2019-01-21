package com.lujian.springmulti.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAutoProxyCreatorEntry {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("context.xml");
        Say dummy = app.getBean("proxyBean", Say.class);
        System.out.println(dummy);
    }

}
