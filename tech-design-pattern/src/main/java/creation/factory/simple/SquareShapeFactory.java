package creation.factory.simple;

public class SquareShapeFactory implements ShapeFactory {

    private Shape shape;

    public SquareShapeFactory(Integer r) {
        shape = new Square(r);
    }

    @Override
    public void drawArea() {
        shape.draw();
        shape.area();
    }

}
