package com.lujian.springmulti.basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaCodeEntryPoint {

    @Bean
    public Dummy dummy() {
        return new Dummy();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(JavaCodeEntryPoint.class);
        Dummy dummy = app.getBean("dummy", Dummy.class);
        System.out.println(dummy);
    }

}
