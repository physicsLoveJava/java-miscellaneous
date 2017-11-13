package creation.factory.prototype;

public class MainXObject3 {

    public static void main(String[] args) {

        XObject3 object = new XObject3();
        object.setAttribute("xx");
        object.setRef(new int[]{1, 2, 3});
        System.out.println(object);

        XObject3 object2 = object.clone();
        System.out.println(object2);

        System.out.println(object.getRef() == object2.getRef());//deep copy via java serialization
    }

}
