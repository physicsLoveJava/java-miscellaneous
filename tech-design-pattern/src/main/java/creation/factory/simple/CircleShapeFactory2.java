package creation.factory.simple;

public class CircleShapeFactory2 extends ShapeFactory2 {

    public CircleShapeFactory2(Integer r) {
        shape = new Circle(r);
    }

    @Override
    void doSome() {
        System.out.println("do some operation");
    }
}
