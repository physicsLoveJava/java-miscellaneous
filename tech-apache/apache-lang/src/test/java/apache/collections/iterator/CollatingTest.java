package apache.collections.iterator;

import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CollatingTest {

    @Test
    public void testCollating() {

        CollatingIterator iterator = new CollatingIterator();

        List<String> list1 = Arrays.asList("a1", "c3", "b2");
        List<String> list2 = Arrays.asList("a11", "b3", "c2");

        iterator.addIterator(list1.iterator());
        iterator.addIterator(list2.iterator());
        iterator.setComparator(ComparatorUtils.NATURAL_COMPARATOR);

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
