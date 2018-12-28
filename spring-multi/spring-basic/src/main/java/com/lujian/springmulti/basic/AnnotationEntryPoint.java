package com.lujian.springmulti.basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationEntryPoint {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(
                "com.lujian.springmulti.basic");
        Dummy dummy = app.getBean("dummy", Dummy.class);
        System.out.println(dummy);
    }

}
