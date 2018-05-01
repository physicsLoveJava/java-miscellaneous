package com.lujian.casual.oom;

public class Stackoom {

    /**
     * -XX:HeapDumpPath=e:\ -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        test();
    }

}
