package creation.factory.prototype;

public class MainXObject {

    public static void main(String[] args) {

        XObject object = new XObject();
        object.setAttribute("xx");
        System.out.println(object);

        XObject object2 = object.clone();
        System.out.println(object2);
    }

}
