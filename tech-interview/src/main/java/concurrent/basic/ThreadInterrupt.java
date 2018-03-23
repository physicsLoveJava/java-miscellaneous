package concurrent.basic;

import java.util.Random;

public class ThreadInterrupt {

    static class Test1 {

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println("AA --> Thread is interrupted: " + Thread.interrupted());
                            e.printStackTrace();// set interrupt state, then receive exception, then clear
                            break;
                        }
                    }
                }
            });
            t.start();
            int i = 4;
            while (i-- > 0) {
                Thread.sleep(500);
                System.out.println("Thread is interrupted: " + t.isInterrupted());
            }
            t.interrupt();
            System.out.println("BB --> Thread is interrupted: " + t.isInterrupted());
            i = 4;
            while (i-- > 0) {
                Thread.sleep(500);
                System.out.println("Thread is interrupted: " + t.isInterrupted());
            }
        }

    }

    static class Test2 {

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int sum = 0;
                    for (int i = 0; i < Integer.MAX_VALUE && !Thread.interrupted(); i++) {
                        sum += new Random().nextInt(i + 1);
                    }
                    System.out.println("thread is interrupted");
                    System.out.println(sum);
                }
            });
            t.start();
            t.interrupt();
            System.out.println("BB --> Thread is interrupted: " + t.isInterrupted());
            Thread.sleep(1000);
            System.out.println("CC --> Thread is interrupted: " + t.isInterrupted());
            Thread.sleep(1000);
            System.out.println("DD --> Thread is interrupted: " + t.isInterrupted());
            System.exit(1);
        }

    }

}
