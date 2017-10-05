package com.lujian.spring.multi;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class PersonNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("person", new PersonBeanDefinitionParser());
    }
}
