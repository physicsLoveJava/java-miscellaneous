package inpact.ch1;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimeGeneratorTest {

    static boolean isPrime(int prime) {
        for (int i = 2; i <= Math.sqrt(prime); i++) {
            if(prime % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isPrime() throws Exception {
        assertTrue(isPrime(2));
        assertTrue(isPrime(3));
        assertTrue(isPrime(5));
        assertTrue(isPrime(7));
        assertFalse(isPrime(9));
    }

}