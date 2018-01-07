package com.lujian.casual.gc;

public class AllocationTenuringThreshold {

    private static int _1mb = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
        -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     -XX:+PrintTenuringDistribution
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        byte[] alloc1, alloc2, alloc3;
        alloc1 = new byte[_1mb / 4];
        alloc2 = new byte[_1mb * 4];
        alloc3 = new byte[_1mb * 4];
        alloc3 = null;
        alloc3 = new byte[_1mb * 4];
        Thread.sleep(5000);
    }

}
