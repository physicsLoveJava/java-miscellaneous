package basis;

public class AnonymousInnerClass {

    interface Feature1 {
        void f1();
    }

    interface Feature2 {
        void f2();
    }

    public void doX(Feature1 feature1) {

    }

    public static void main(String[] args) {
        new AnonymousInnerClass().doX(new Feature1() {
            @Override
            public void f1() {

            }
        });
    }

}
