package typical.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithFair {

    private static ReentrantLock lock = new ReentrantLock(true);
    private static int times = 20;

    static class Worker implements Runnable {

        private ReentrantLock lock;

        public Worker(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (times > 0) {
                try {
                    lock.lock();
                    times --;
                    System.out.println(Thread.currentThread().getName() + " get lock");
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(lock));
        Thread t2 = new Thread(new Worker(lock));
        t1.start();
        t2.start();
    }

}
