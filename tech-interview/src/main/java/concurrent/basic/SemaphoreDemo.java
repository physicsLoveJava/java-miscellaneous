package concurrent.basic;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(10);
        final AtomicInteger id = new AtomicInteger();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int idx = id.incrementAndGet();
                try {
                    semaphore.acquire();
                    System.out.println(idx + " get resource");
                    Thread.sleep(1000);
                    semaphore.release();
                    System.out.println(idx + " release resource");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(runnable).start();
        }
    }

}
