package apache.collections.bidimap;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.junit.Test;

public class BidiMapTest {

    @Test
    public void testBidiMap() {
        DualHashBidiMap<String, Integer> bidiMap = new DualHashBidiMap<>();
        bidiMap.put("hello", 1);
        bidiMap.put("world", 2);

        System.out.println(bidiMap.getKey(1));
        System.out.println(bidiMap.getKey(2));
    }

}
