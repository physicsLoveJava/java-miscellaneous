package creation.factory.simple;

public class Square implements Shape {

    private Integer a;

    public Square(Integer a) {
        this.a = a;
    }

    @Override
    public void draw() {
        System.out.println("square draw");
    }

    @Override
    public void area() {
        System.out.println("area: " + a * a);
    }
}
