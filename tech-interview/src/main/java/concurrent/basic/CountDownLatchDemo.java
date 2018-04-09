package concurrent.basic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(5);
        final Random random = new Random();

        Runnable loader = new Runnable() {
            @Override
            public void run() {
                int s = random.nextInt(10);
                try {
                    Thread.sleep(s * 1000);
                    System.out.println("time: " + s + " to load.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(loader).start();
        }
        try {
            latch.await();
            System.out.println("load complete!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
