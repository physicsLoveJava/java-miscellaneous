package creation.factory.prototype;

import java.util.Arrays;

public class XObject2 implements Cloneable {

    private String attribute;

    private int[] ref;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int[] getRef() {
        return ref;
    }

    public void setRef(int[] ref) {
        this.ref = ref;
    }

    @Override
    public XObject2 clone() {
        Object clone = null;
        try {
            clone = super.clone();//support by java
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (XObject2) clone;
    }

    @Override
    public String toString() {
        return "XObject2{" +
                "attribute='" + attribute + '\'' +
                ", ref=" + Arrays.toString(ref) +
                '}';
    }
}
