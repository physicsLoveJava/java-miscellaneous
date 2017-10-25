package com.lujian.dubbo.demo.client;

import com.lujian.dubbo.demo.AddService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        AddService addService = (AddService) context.getBean("addService"); // 获取远程服务代理
        for (int i = 0; i < 10000; i++) {
            addService.add(1, 2); // 执行远程方法
        }
    }
}
