package com.lujian.casual.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryoom {

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while(true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }

}
