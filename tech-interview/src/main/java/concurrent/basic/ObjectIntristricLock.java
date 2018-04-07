package concurrent.basic;

public class ObjectIntristricLock {

    public static void main(String[] args) {

        Object obj = new Object();
        synchronized (obj) {
            System.out.println(obj);
        }

    }
}
