package inpact.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeGenerator extends Thread {

    private static List<Integer> primeList = new ArrayList<>();

    private static AtomicInteger primeCount = new AtomicInteger(0);

    private static AtomicInteger primeIndex = new AtomicInteger(1);

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            int prime = primeIndex.getAndIncrement();
            if(isPrime(prime)) {
                synchronized (primeList) {
                    primeList.add(prime);
                    primeCount.getAndIncrement();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isPrime(int prime) {
        if(prime < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(prime); i++) {
            if(prime % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void switchOff() {
        flag = false;
    }

    public List<Integer> getPrimeList() {
        return primeList;
    }

    public Integer getPrimeCount() {
        return primeCount.get();
    }

    public Integer getNextNumber() {
        return primeIndex.get();
    }
}
