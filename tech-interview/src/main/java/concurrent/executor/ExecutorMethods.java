package concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorMethods {

    static class Test1 {

        public static void main(String[] args) {

            ExecutorService service = Executors.newFixedThreadPool(5);
            List<Callable<String>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return String.valueOf(new Random().nextInt());
                    }
                });
            }
            try {
                List<Future<String>> futures = service.invokeAll(list, 3000, TimeUnit.MILLISECONDS);
                service.shutdown();
                for (Future<String> future : futures) {
                    System.out.println(future.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
