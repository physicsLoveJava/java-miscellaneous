package com.lujian.casual.gc.book;

public class ReferenceCountGC {

    private Object ref;

    private byte[] bytes = new byte[1024 * 1024 * 100];

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000);
        ReferenceCountGC a = new ReferenceCountGC();
        ReferenceCountGC b = new ReferenceCountGC();

        a.ref = b;
        b.ref = a;

        Thread.sleep(5000);

        System.out.println("1 gc");
        System.gc();

        Thread.sleep(5000);

        a = null;
        b = null;

        System.out.println("2 gc");

        Thread.sleep(5000);
        System.gc();

        Thread.sleep(10000);

    }

}
