package typical.threads;

import org.junit.Test;

public class TimerThreadTest {

    private TimerThread thread = new TimerThread("Timer-Thread", 1000);

    @Test
    public void testTimer() {
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}