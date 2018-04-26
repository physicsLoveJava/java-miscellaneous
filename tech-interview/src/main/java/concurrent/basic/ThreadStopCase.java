package concurrent.basic;

public class ThreadStopCase {

    static class Person {
        String name;
        int age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class Writer extends Thread {
        Person person;

        public Writer(Person person) {
            this.person = person;
        }

        @Override
        public void run() {
            try {
                synchronized (person) {
                    Thread.sleep(1000);
                    person.name = "writer";
                    System.out.println("write name");
                    Thread.sleep(1000);
                    person.age = 10;
                    System.out.println("write age");
                    Thread.sleep(1000);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        Writer writer1 = new Writer(person);
        Writer writer2 = new Writer(person);
        writer1.start();
        Thread.sleep(1500);
        writer1.stop(); // make obj inconsistent with contract, cause stop can be randomly called.
        System.out.println(person);
        writer2.start();
        Thread.sleep(2500);
        writer2.stop();
        System.out.println(person);
    }

}
