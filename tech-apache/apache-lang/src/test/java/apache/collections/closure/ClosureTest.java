package apache.collections.closure;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.ClosureUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;

public class ClosureTest {

    @Test
    public void testClosure() {
        Closure<Integer> closure = ClosureUtils.whileClosure(new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer integer) {
                return integer > 10;
            }
        }, new Closure<Integer>() {
            @Override
            public void execute(Integer integer) {
                System.out.println(integer--);
            }
        });
        closure.execute(20);
    }

}
