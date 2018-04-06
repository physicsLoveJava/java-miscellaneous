package designpatterns.immobj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointMain {

    public static void main(String[] args) {

        PointHolder holder = setUpHolder();

        Thread[] threads1 = createThreads(holder, 5, ChangerTypeEnum.ADD);
        Thread[] threads2 = createThreads(holder, 5, ChangerTypeEnum.MINUS);
        Thread[] threads3 = createThreads(holder, 5, ChangerTypeEnum.MULTIPLY);
        Thread[] threads4 = createThreads(holder, 5, ChangerTypeEnum.BOUNCE);

        List<Thread> list = new ArrayList<>();
        list.addAll(Arrays.asList(threads1));
        list.addAll(Arrays.asList(threads2));
        list.addAll(Arrays.asList(threads3));
        list.addAll(Arrays.asList(threads4));
        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(holder.getSnapShot());
    }

    private static Thread[] createThreads(PointHolder holder, int size,  ChangerTypeEnum type) {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PointChangerWorker(holder, type));
        }
        return threads;
    }


    private static PointHolder setUpHolder() {
        PointHolder holder = new PointHolder();
        for (int i = 0; i < 10; i++) {
            holder.add(new Point(i, i));
        }
        return holder;
    }

}
