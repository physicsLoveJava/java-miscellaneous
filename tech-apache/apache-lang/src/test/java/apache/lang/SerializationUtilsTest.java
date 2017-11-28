package apache.lang;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

public class SerializationUtilsTest {

    @Test
    public void testSerialization() {
        String xx = "aa";
        byte[] bytes = SerializationUtils.serialize(xx);
        String nObject = SerializationUtils.deserialize(bytes);
        System.out.println(nObject);
    }

}
