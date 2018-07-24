package concurrent.demo;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Processing {

    class MetaInfo {
        private String id;
        private Integer groupId;
        private Double quota;
    }

    static class DataStore {
        public void getData() {

        }
    }

    static class QueryWorker implements Runnable {

        public QueryWorker(DataStore store) {

        }

        @Override
        public void run() {
            while (true) {
                System.out.println("query data from data store start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("query data from data store end");
            }
        }
    }

    static class DataResolver implements Runnable {

        public DataResolver(DataStore store, File file) {

        }

        @Override
        public void run() {
            System.out.println("data resolver from file start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("data resolver from file end");
        }
    }

    public static void main(String[] args) {

        ExecutorService producerExecutor = Executors.newFixedThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "data resolver");
            }
        });

        ExecutorService queryExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "data query");
            }
        });

        DataStore store = new DataStore();

        //start query
        queryExecutor.submit(new QueryWorker(store));


        //start data resolver
        List<File> fileList = getFileList();

        if(fileList == null) {
            return;
        }
        for (final File file : fileList) {
            try {
                producerExecutor.submit(new DataResolver(store, file)).get();
            } catch (Exception e) {
                throw new RuntimeException("data resolve failed", e);
            }
        }

        //shutdown
        producerExecutor.shutdown();
        queryExecutor.shutdown();

        //print final result
        store.getData();
    }

    private static List<File> getFileList() {
        return Arrays.asList(
                new File("not exist"),
                new File("not exist"),
                new File("not exist"),
                new File("not exist")
        );
    }

}
