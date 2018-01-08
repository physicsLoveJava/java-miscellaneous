package com.lujian.casual.gc.self;

public class ObjectAllocation {

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        GCUtils.start();

        byte[] alloc = null;
        byte[] alloc1 = null;
        System.gc();
        alloc = GCUtils.allocate(alloc, GCUtils._8MB, 2000);
        alloc = GCUtils.allocate(alloc, GCUtils._5MB, 2000);
        alloc = GCUtils.allocate(alloc, GCUtils._0, 2000);
        System.gc();
        alloc = GCUtils.allocate(alloc, GCUtils._5MB, 2000);
        alloc1 = GCUtils.allocate(alloc1, GCUtils._5MB, 2000);
        System.out.println(alloc.length);
        System.out.println(alloc1.length);
        System.gc();
        alloc1 = GCUtils.allocate(alloc1, GCUtils._8MB, 2000);
        System.out.println(alloc.length);
        System.out.println(alloc1.length);
        GCUtils.end();

    }

}
