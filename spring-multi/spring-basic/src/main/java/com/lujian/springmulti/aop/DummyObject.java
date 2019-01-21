package com.lujian.springmulti.aop;

public class DummyObject implements Say {

    @Override
    public void say(String msg) {
        System.out.println(msg);
    }

}
