package creation.factory.prototype;

public class XObject implements Cloneable {

    private String attribute;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public XObject clone() {
        Object clone = null;
        try {
            clone = super.clone();//support by java
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (XObject) clone;
    }

    @Override
    public String toString() {
        return "XObject{" +
                "attribute='" + attribute + '\'' +
                '}';
    }
}
