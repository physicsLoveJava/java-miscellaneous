package creation.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class Singleton2Test {
    @Test
    public void getInstance() throws Exception {
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        assertEquals(s1, s2);
    }
}