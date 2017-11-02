package creation.factory.simple;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeFactoryTest {
    @Test
    public void drawArea() throws Exception {

        ShapeFactory circle = new CircleShapeFactory(5);
        ShapeFactory square = new SquareShapeFactory(5);
        circle.drawArea();
        square.drawArea();

    }

}