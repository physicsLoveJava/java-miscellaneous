package com.lujian.casual.oom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MethodAreaoom {

    interface XI {
        void say();
    }

    static class XInstance implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    public static void main(String[] args) {
        //jdk proxy use the same class
        System.out.println(createInstance().getClass());
        System.out.println(createInstance().getClass());
        System.out.println(createInstance().getClass());
    }

    private static XI createInstance() {
        return (XI) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{XI.class}, new XInstance());
    }

}
