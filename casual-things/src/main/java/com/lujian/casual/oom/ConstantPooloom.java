package com.lujian.casual.oom;

import java.util.ArrayList;

public class ConstantPooloom {

    /**
     * -XX:HeapDumpPath=e:\ -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        while(true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
