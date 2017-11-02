package creation.factory.simple;

public class Circle implements Shape {

    private Integer r;

    public Circle(Integer r) {
        this.r = r;
    }

    @Override
    public void draw() {
        System.out.println("circle draw");
    }

    @Override
    public void area() {
        System.out.println("area: " + r*r*3);
    }
}
