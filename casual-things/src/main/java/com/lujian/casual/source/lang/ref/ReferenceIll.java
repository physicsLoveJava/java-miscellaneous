package com.lujian.casual.source.lang.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceIll {

    public static void main(String[] args) throws InterruptedException {

        ReferenceQueue<DummyByte> rq = new ReferenceQueue<>();

        List<Reference> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            displayRefList(list, i);
            SoftReference<DummyByte> sr = new SoftReference<DummyByte>(new DummyByte(), rq);
//            WeakReference<DummyByte> sr = new WeakReference<DummyByte>(new DummyByte(), rq);
//            PhantomReference<DummyByte> sr = new PhantomReference<DummyByte>(new DummyByte(), rq);
            list.add(sr);
            System.gc();
            Thread.sleep(100);
        }

        System.out.println("start:final");
        for (Reference reference : list) {
            System.out.println(reference.get());
        }
        System.out.println("end:final");

    }

    private static void displayRefList(List<Reference> list, int seq) {
        System.out.println("start:" + seq);
        for (Reference reference : list) {
            System.out.println(reference.get());
        }
        System.out.println("end:" + seq);
    }

    static class DummyByte {
        private byte[] bytes;

        public DummyByte() {
            bytes = new byte[1024 * 1024];
        }

        public DummyByte(int size) {
            bytes = new byte[size];
        }
    }

}
