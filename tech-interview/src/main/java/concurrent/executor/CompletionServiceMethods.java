package concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceMethods {

    static class Test1 {

        public static void main(String[] args) {

            ExecutorService service = Executors.newFixedThreadPool(5);
            ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(service);
            for (int i = 0; i < 10; i++) {
                completionService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return String.valueOf(new Random().nextInt());
                    }
                });
            }
            System.out.println("do some basic work");

            for (int i = 0; i < 10; i++) {
                Future<String> future = null;
                try {
                    future = completionService.take();
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            service.shutdown();
        }

    }

    static class Test2 {

        public static void main(String[] args) {

            ExecutorService service = Executors.newFixedThreadPool(5);
            List<Future<String>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Future<String> future = service.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return String.valueOf(new Random().nextInt());
                    }
                });
                list.add(future);
            }
            System.out.println("do some basic work");

            for (Future<String> future : list) {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            service.shutdown();
        }

    }

    static class Test3 {

        private static class MyFuture extends FutureTask<String> {

            public MyFuture(Callable<String> callable) {
                super(callable);
            }

            @Override
            protected void done() {
                System.out.println("current task is done!");
            }
        }

        public static void main(String[] args) {

            MyFuture myFuture = new MyFuture(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(2000);
                    return String.valueOf(new Random().nextInt());
                }
            });

            try {
                myFuture.run();
                String s = myFuture.get();//if you get the value, the value hasn't evaluation
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

    }

}
