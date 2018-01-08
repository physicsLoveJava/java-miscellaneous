package com.lujian.casual.gc.book;

public class HandlePromotion {

    private static int _1mb = 1024 * 1024;

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-
     HandlePromotionFailure
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        byte[] alloc1, alloc2, alloc3, alloc4, alloc5, alloc6, alloc7;
        alloc1 = new byte[_1mb * 2];
        alloc2 = new byte[_1mb * 2];
        alloc3 = new byte[_1mb * 2];
        alloc1 = null;

        alloc4 = new byte[_1mb * 2];
        alloc5 = new byte[_1mb * 2];
        alloc6 = new byte[_1mb * 2];

        alloc4 = null;
        alloc5 = null;
        alloc6 = null;

        Thread.sleep(2000);
        alloc7 = new byte[_1mb * 2];

        Thread.sleep(5000);

    }

}
