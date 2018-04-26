package concurrent.basic;

public class ThreadResumeCase {

    static class Reader extends Thread {

        Object lock;

        public Reader(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //java中是可重入
            synchronized (lock) {
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Reader reader1 = new Reader(lock);
        Reader reader2 = new Reader(lock);
        reader1.start();
        reader2.start();
        reader1.resume();
        reader2.resume();//这个是时间顺序问题
        System.out.println("complete");
    }

}
