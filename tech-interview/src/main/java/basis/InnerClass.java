package basis;

public class InnerClass {

    class Inner {

    }

    static class StaticInner {

    }

    public static void main(String[] args) {

//        Inner inner = new Inner();
        Inner inner = new InnerClass().new Inner();
        StaticInner staticInner = new StaticInner();
    }

}
