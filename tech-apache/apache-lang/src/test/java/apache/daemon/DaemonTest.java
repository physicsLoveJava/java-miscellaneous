package apache.daemon;

import org.apache.commons.daemon.support.DaemonLoader;
import org.apache.commons.daemon.support.DaemonWrapper;
import org.junit.Test;

public class DaemonTest {

    @Test
    public void testDaemon() {

        DaemonWrapper wrapper = new DaemonWrapper();
        DaemonLoader.Context context = new DaemonLoader.Context();
        try {
            wrapper.init(context);
            wrapper.start();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
