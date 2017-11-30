package apache.collections.sequence;

import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.SequencesComparator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SequenceTest {

    @Test
    public void testSequence() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(5, 4, 3, 2, 1);
        SequencesComparator<Integer> comparator = new SequencesComparator<>(list1, list2);
        EditScript<Integer> scripts = comparator.getScript();
        scripts.visit(new CommandVisitor<Integer>() {
            @Override
            public void visitInsertCommand(Integer integer) {
                System.out.println("insert:" + integer);
            }

            @Override
            public void visitKeepCommand(Integer integer) {
                System.out.println("keep:" + integer);
            }

            @Override
            public void visitDeleteCommand(Integer integer) {
                System.out.println("delete:" + integer);
            }
        });
    }

}
