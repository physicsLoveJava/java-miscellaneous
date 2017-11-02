package creation.factory.simple;

public abstract class ShapeFactory2 {

    protected Shape shape;

    public void drawArea() {
        doSome();
        shape.draw();
        shape.area();
    }

    abstract void doSome();

}
