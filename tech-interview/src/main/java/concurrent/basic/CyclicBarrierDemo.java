package concurrent.basic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        final CyclicBarrier barrier = new CyclicBarrier(5, new CallbackAction());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                    System.out.println("begin do work");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }

    }

    private static class CallbackAction implements Runnable {
        @Override
        public void run() {
            System.out.println("complete");
        }
    }
}
