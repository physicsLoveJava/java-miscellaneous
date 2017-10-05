package com.lujian.dubbo.demo.server;

import com.lujian.dubbo.demo.AddService;

public class AddServiceImpl implements AddService {
    @Override
    public int add(int x, int y) {
        System.out.println("invoking add method: " + x + ":" + y);
        return x + y;
    }
}
