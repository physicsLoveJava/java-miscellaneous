package concurrent.basic;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerDemo {

    public static void main(String[] args) {

        final Exchanger<Integer> changer = new Exchanger<>();
        final Random random = new Random();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int computed = random.nextInt(1000);
                System.out.println(Thread.currentThread().getId() + " computed:" + computed);
                try {
                    Integer exchange = changer.exchange(computed, 1000, TimeUnit.MILLISECONDS);
                    System.out.println(Thread.currentThread().getId() + " exchange:" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }

    }

}
