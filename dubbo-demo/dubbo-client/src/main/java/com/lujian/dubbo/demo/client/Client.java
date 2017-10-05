package com.lujian.dubbo.demo.client;

import com.lujian.dubbo.demo.AddService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        AddService addService = (AddService) context.getBean("addService"); // 获取远程服务代理
        int result = addService.add(1, 2); // 执行远程方法
        System.out.println("-----------------");
        System.out.println(result); // 显示调用结果
        System.out.println("-----------------");
    }
}
