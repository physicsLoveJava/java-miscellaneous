package typical.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTryLock {

    private static ReentrantLock lock1 = new ReentrantLock();

    static class Worker implements Runnable {

        private ReentrantLock lock;

        public Worker(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (lock.tryLock()) {
                        //hold for 3s
                        System.out.println(Thread.currentThread().getName() + " get lock success:" + lock);
                        Thread.sleep(3000);
                    }else {
                        System.out.println(Thread.currentThread().getName() + " get lock failed:" + lock);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(lock1));
        Thread t2 = new Thread(new Worker(lock1));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
