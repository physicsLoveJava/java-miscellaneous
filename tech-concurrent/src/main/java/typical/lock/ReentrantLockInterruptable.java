package typical.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    static class Worker implements Runnable {

        private ReentrantLock first;
        private ReentrantLock second;

        public Worker(ReentrantLock first, ReentrantLock second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            try {
                first.lockInterruptibly();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " get lock:" + first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    second.lockInterruptibly();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " get lock:" + second);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(second.isHeldByCurrentThread()) {
                        second.unlock();
                        System.out.println(Thread.currentThread().getName() + " release lock:" + second);
                    }
                }
                if(first.isHeldByCurrentThread()) {
                    first.unlock();
                    System.out.println(Thread.currentThread().getName() + " release lock:" + first);
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(lock1, lock2));
        Thread t2 = new Thread(new Worker(lock2, lock1));
        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
