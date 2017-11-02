package creation.singleton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingletonTest {
    @Test
    public void getInstance() throws Exception {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        assertEquals(s1, s2);
    }

}