package com.lujian.spring.transaction.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractMain {

    protected ApplicationContext context;

    protected AbstractMain() {
        init();
    }

    protected void prepareContext() {
        context = new ClassPathXmlApplicationContext("context.xml");
    }

    public void init() {
        prepareContext();
        play();
    }

    protected abstract void play();

}
