package creation.factory.simple;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeFactory2Test {
    @Test
    public void drawArea() throws Exception {
        CircleShapeFactory2 factory = new CircleShapeFactory2(5);
        factory.drawArea();
    }

}