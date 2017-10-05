package com.lujian.spring.multi;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PersonBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Person.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        Integer age = null;
        try {
            age = Integer.valueOf(element.getAttribute("age"));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String address = element.getAttribute("address");
        if(StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }
        if(StringUtils.hasText(address)) {
            builder.addPropertyValue("address", address);
        }
        if(age != null) {
            builder.addPropertyValue("age", age);
        }
    }
}
