package inpact.ch1;

public class MainPrimeGenerator {


    public static void main(String[] args) {

        int size = 10;
        PrimeGenerator[] generators = new PrimeGenerator[size];
        for (int i = 0; i < size; i++) {
            generators[i] = new PrimeGenerator();
            generators[i].start();
        }

        try {
            Thread.sleep(3000);
            generators[0].switchOff();
            System.out.println("primes: " + generators[0].getPrimeList());
            System.out.println("primes list size: " + generators[0].getPrimeList().size());
            System.out.println("primes count: " + generators[0].getPrimeCount());
            System.out.println("primes next number: " + generators[0].getNextNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
