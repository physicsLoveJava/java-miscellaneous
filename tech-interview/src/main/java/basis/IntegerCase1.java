package basis;

public class IntegerCase1 {

    public static void main(String[] args) {
        Integer x = 1;
        Integer y = x;
        System.out.println("before:" + x);
        System.out.println("before:" + y);
        x++;
        System.out.println("after:" + x);
        System.out.println("after:" + y);
    }

}
