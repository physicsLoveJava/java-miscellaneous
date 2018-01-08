package com.lujian.casual.gc.self;

public class GCUtils {

    public static final int _1MB = 1024 * 1024;
    public static final int _2MB = _1MB * 2;
    public static final int _5MB = _1MB * 5;
    public static final int _8MB = _1MB * 8;
    public static final int _9MB = _1MB * 9;
    public static final int _10MB = _1MB * 10;
    public static final int _0 = -1;

    public static void start() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static void end() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static byte[] allocate(byte[] array, int arraySize, long sleep) throws InterruptedException {
        if(arraySize == _0) {
            return null;
        }
        array = new byte[arraySize];
        Thread.sleep(sleep);
        return array;
    }
}
