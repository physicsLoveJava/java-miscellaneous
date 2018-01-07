package com.lujian.casual.gc;

public class MemoryAllocationInEden {

    private static int _1mb = 1024 * 1024;

    /**
     * VM 参 数 ： -verbose:gc -Xms20M -Xmx20M -XmnlOM -XX:+PrintGCDetails
     -XX:SurvivorRatio=8
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        byte[][] alloc = new byte[4][];
        alloc[0] = new byte[2 * _1mb];
        alloc[1] = new byte[2 * _1mb];
        alloc[2] = new byte[2 * _1mb];
        alloc[3] = new byte[4 * _1mb];
        Thread.sleep(2000);
    }

}
