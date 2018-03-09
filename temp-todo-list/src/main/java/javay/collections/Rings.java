package javay.collections;

import org.junit.Assert;
import org.junit.Test;

public class Rings {


    @Test
    public void testNewCopy() {

        int[] arr = {
                1, 2, 3, 4, 5
        };

        int index = 2;
        int count = arr.length - index - 1;
        System.arraycopy(arr, index + 1, arr, index, count);
        arr[arr.length - 1] = -1;

        Assert.assertArrayEquals(new int[] {
                1, 2, 4, 5, -1
        }, arr);
    }

    @Test
    public void testNewAddCopy() {
        int[] arr = {
                1, 2, 3, 4, 0
        };

        int index = 2;
        int count = arr.length - index - 1;
        System.arraycopy(arr, index, arr, index + 1, count);
        arr[index] = 3;

        Assert.assertArrayEquals(new int[] {
                1, 2, 3, 3, 4
        }, arr);
    }

}
