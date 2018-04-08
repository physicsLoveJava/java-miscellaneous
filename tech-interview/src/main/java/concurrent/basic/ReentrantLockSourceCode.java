package concurrent.basic;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSourceCode {

    public static void main(String[] args) throws InterruptedException {

        final ReentrantLock lock = new ReentrantLock(true);

        lock.lock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        lock.unlock();
    }

}
