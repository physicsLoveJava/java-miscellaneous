package creation.factory.simple;

public class CircleShapeFactory implements ShapeFactory {

    private Shape shape;

    public CircleShapeFactory(Integer r) {
        shape = new Circle(r);
    }

    @Override
    public void drawArea() {
        shape.draw();
        shape.draw();
        shape.area();
    }

}
