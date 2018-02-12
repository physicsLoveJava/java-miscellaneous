package behavioral.command;

import org.junit.Test;

public class InvokerTest {
    @Test
    public void randomRun() throws Exception {

        Invoker invoker = new Invoker();
        for (int i = 0; i < 10; i++) {
            invoker.randomRun();
        }

        invoker.undo();

        for (int i = 0; i < 5; i++) {
            invoker.randomRun();
        }

        invoker.undoAll();

    }

}