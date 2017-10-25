package typical.threads;

public class TimerThread extends Thread{

    private long opPerunit;
    private Object lock = new Object();

    public TimerThread(String name, long opPerunit) {
        super(name);
        this.opPerunit = opPerunit;
    }

    @Override
    public void run() {
        while (true) {
            await(opPerunit);
            logTimeStamp();
        }
    }

    private void logTimeStamp() {
        System.out.println(System.currentTimeMillis());
    }

    private void await(long opPerunit) {
        synchronized (lock) {
            try {
                lock.wait(opPerunit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
