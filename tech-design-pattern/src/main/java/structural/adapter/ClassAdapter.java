package structural.adapter;

public class ClassAdapter {

    class Adaptee {

        public void say() {
            System.out.println("say");
        }

    }

    interface Target {
        void speak();
    }

    class Adapter extends Adaptee implements Target {

        @Override
        public void speak() {
            for (int i = 0; i < 10; i++) {
                say();
            }
        }
    }

}
