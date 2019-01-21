package com.lujian.springmulti.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopEntryPoint {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("context.xml");
        Say dummy = app.getBean("proxyBean", Say.class);
        System.out.println(dummy);

        dummy.say("hello");

        Object proxy = AopContext.currentProxy();
        System.out.println(proxy);
        Say proxySay = (Say) proxy;
        proxySay.say("xxx");
    }

}
