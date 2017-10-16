package com.lujian.commons;

public class TimeUtils {

    public static long getMethodDuration(MethodAction action) {
        long start = System.currentTimeMillis();
        action.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void logMethodDuration(MethodAction action) {
        System.out.println("Method Duration: " + getMethodDuration(action));
    }

    public interface MethodAction{
        void run();
    }

}
