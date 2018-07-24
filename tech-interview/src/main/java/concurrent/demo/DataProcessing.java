package concurrent.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.*;

public class DataProcessing {
}

class Select {

    public static BlockingQueue<String> getAllFileName(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return new ArrayBlockingQueue<String>(0);
        }
        ArrayBlockingQueue<String> fileNameQueue = new ArrayBlockingQueue<String>(file.list().length);
        for (String s : file.list()) {
            fileNameQueue.add(path + "\\" + s);
        }
        return fileNameQueue;
    }

    public static void main(String[] args) {
        BlockingQueue<String> fileNameQueue = getAllFileName("C:\\Users\\jianc\\Desktop\\three");

        Stack stack = new Stack();
        Producer producer = new Producer(fileNameQueue, stack);
        Consumer consumer = new Consumer(stack);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(producer);
        Thread thread3 = new Thread(producer);
        Thread thread4 = new Thread(consumer);
        Thread thread5 = new Thread(consumer);
        Thread thread6 = new Thread(consumer);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        while(consumer.isFlag()){
            for (Object s : consumer.getResultMap().keySet()) {
                System.out.println(s);
            }
            break;
        }
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {

    BlockingQueue<String> fileNameQueue;
    Stack stack;

    Producer(BlockingQueue<String> fileNameQueue, Stack stack) {
        this.fileNameQueue = fileNameQueue;
        this.stack = stack;
    }

    @Override
    public void run() {
        while (!fileNameQueue.isEmpty()) {
            final String fileName = fileNameQueue.poll();
            if (fileName != null) {
                readContent(fileName);
            }
        }
    }

    /**
     * 读取文件内容
     *
     * @param fileName
     */
    public void readContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            // 一次读入一行，直到读入null为文件结束
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll(" ", "");
                String[] str = line.split(",");
                stack.push(str);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}


class Consumer implements Runnable {

    Stack stack;
    boolean flag = false;
    ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<>();

    Consumer(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            String[] fileContent = stack.pop();
            if ((fileContent != null) && (!"".equals(fileContent[0]))) {
                sortFile(fileContent);
            } else {
                flag = true;
                return;
            }
        }
    }

    public synchronized void sortFile(String[] fileContent) {
        String groupId = fileContent[1];
        if (!"".equals(groupId)) {
            boolean flag = resultMap.containsKey(groupId);
            if (flag) {
                Double quota1 = Double.valueOf(fileContent[2]);
                Double quota2 = Double.valueOf(resultMap.get(groupId).split(",")[1]);
                if (quota1 < quota2) {
                    resultMap.put(groupId, fileContent[0] + "," + fileContent[2]);
                }
            } else {
                resultMap.put(groupId, fileContent[0] + "," + fileContent[2]);
            }
        } else {
            return;
        }
    }

    public ConcurrentHashMap<String, String> getResultMap() {
        return resultMap;
    }

    public boolean isFlag() {
        return flag;
    }
}


/**
 * 仓库对象，生产者生产的产品放在仓库中，供消费者从仓库中取产品
 */
class Stack {

    BlockingQueue<String[]> queues = new LinkedBlockingQueue<String[]>(100);

    //存产品
    public void push(String[] fileContent) {
        try {
            queues.put(fileContent);
            System.out.println("生产者放入仓库");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //取产品
    public String[] pop() {
        String[] product = null;
        try {
            product = queues.poll(1, TimeUnit.SECONDS);
            System.out.println("消费者者放入仓库");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

}
