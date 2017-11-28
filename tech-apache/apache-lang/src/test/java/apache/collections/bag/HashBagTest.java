package apache.collections.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TransformedBag;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HashBagTest {

    @Test
    public void testBag() {

        HashBag<String> bag = new HashBag<String>();
        bag.add("a");
        bag.add("c");
        bag.add("b");
        bag.add("a");

        System.out.println(bag);
        System.out.println(bag.getCount("a"));

        bag.remove("a");

        System.out.println(bag);

    }

    @Test
    public void testArrayInstance() {
        Integer[] array = (Integer[]) Array.newInstance(Integer.class, 10);
        System.out.println(Arrays.toString(array));
    }

    @Test(expected = ClassCastException.class)
    public void testGenericArray () {
        Integer[] array = getGenericArray(Integer.class, 10);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testTransform() {
        HashBag<String> bag = new HashBag<String>();
        bag.add("1");
        bag.add("2");
        bag.add("3");
        bag.add("4");
        bag.add("4");

        Bag<String> newBag = TransformedBag.transformedBag(bag, new Transformer<String, String>() {
            @Override
            public String transform(String s) {
                return s + "ss";
            }
        });

        System.out.println(newBag);
    }

    //wrong
    public static <E> E[] getGenericArray(Class<E> clazz, int size) {
        E[] array = (E[]) new Object[size];
        return array;
    }



}
