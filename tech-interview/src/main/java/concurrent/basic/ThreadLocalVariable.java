package concurrent.basic;

public class ThreadLocalVariable {

    static class Person {
        private String name;
        private Integer age;
        private byte[] bytes = new byte[10 * 1024 * 1024];//10mb

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class HoldThreadLocal {
        private ThreadLocal<Person> local = new ThreadLocal<>();

        public HoldThreadLocal() {
            Person p = new Person();
            p.name = "Tim";
            p.age = 10;
            local.set(p);
        }

        public HoldThreadLocal(Person p) {
            local.set(p);
        }

        public Person get() {
            return local.get();
        }

        public void remove() {
            local.remove();
        }

    }

    /**
     * -verbose:gc -Xms15m -Xmx15m
     * thread local remove后，对象被回收，类似普通对象的回收
     * @param args
     */
    public static void main(String[] args) {
        HoldThreadLocal local = new HoldThreadLocal();
        System.out.println(local.get());
        System.gc();
        local.remove();
        System.out.println(local.get());
        System.gc(); // 普通的对象被回收
        System.out.println(local.get());
    }

    /**
     * -verbose:gc -Xms15m -Xmx15m
     * thread 被回收后，thread local中的值也被回收
     */
    static class Test2 {
        public static void main(String[] args) throws InterruptedException {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    HoldThreadLocal local = new HoldThreadLocal();
                    System.out.println(local.get());
                    System.out.println("will not remove");
                    System.gc();
                    System.out.println(local.get());
                }
            };

            Thread t1 = new Thread(runnable);
            t1.start();
            t1.join();

            System.out.println("remove thread 1");
            System.gc();

            Thread t2 = new Thread(runnable);
            t2.start();
            t2.join();

            System.out.println("remove thread 2");
            System.gc();
        }
    }

    /**
     * -verbose:gc -Xms15m -Xmx15m
     * thread 被回收后，person没有被回收
     */
    static class Test3 {
        public static void main(String[] args) throws InterruptedException {
            final Person p = new Person();
            p.name = "abc";
            p.age = 10;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    HoldThreadLocal local = new HoldThreadLocal(p);
                    System.out.println(local.get());
                    System.gc();
                }
            };

            for (int i = 0; i < 100; i++) {
                new Thread(runnable).start();
            }

            Thread.sleep(1000);
            System.gc();
        }
    }

}
