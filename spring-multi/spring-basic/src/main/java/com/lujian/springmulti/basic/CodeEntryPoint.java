package com.lujian.springmulti.basic;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class CodeEntryPoint {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition dummyDef = new RootBeanDefinition("com.lujian.springmulti.basic.Dummy");
        factory.registerBeanDefinition("dummy", dummyDef);
    }

}
