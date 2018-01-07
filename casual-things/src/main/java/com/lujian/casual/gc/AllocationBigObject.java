package com.lujian.casual.gc;

public class AllocationBigObject {

    private static int _1mb = 1024 * 1024;

    /**
     * VM 参 数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        byte[] alloc = new byte[4 * _1mb];
        Thread.sleep(2000);
    }

}