package creation.factory.prototype;

public class MainXObject2 {

    public static void main(String[] args) {

        XObject2 object = new XObject2();
        object.setAttribute("xx");
        object.setRef(new int[]{1, 2, 3});
        System.out.println(object);

        XObject2 object2 = object.clone();
        System.out.println(object2);

        System.out.println(object.getRef() == object2.getRef());//java support shallow copy
    }

}
