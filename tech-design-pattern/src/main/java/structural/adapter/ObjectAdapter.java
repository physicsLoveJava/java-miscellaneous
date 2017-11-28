package structural.adapter;

public class ObjectAdapter {

    class Adaptee {

        public void say() {
            System.out.println("say");
        }

    }

    interface Target {
        void speak();
    }

    class Adapter implements Target {

        private Adaptee adaptee = new Adaptee();

        @Override
        public void speak() {
            for (int i = 0; i < 10; i++) {
                adaptee.say();
            }
        }
    }

}