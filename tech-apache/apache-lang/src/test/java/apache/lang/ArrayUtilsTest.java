package apache.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayUtilsTest {

    @Test
    public void testArrayUtils() {
        assertTrue(ArrayUtils.contains(new int[]{1, 2, 3}, 1));
        assertEquals(0, ArrayUtils.indexOf(new int[]{1, 2, 3}, 1));
        Integer[] array = new Integer[3];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        assertEquals(array, ArrayUtils.toObject(new int[]{1, 2, 3}));
    }

}
