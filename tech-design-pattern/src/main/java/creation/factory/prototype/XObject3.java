package creation.factory.prototype;

import java.io.*;
import java.util.Arrays;

public class XObject3 implements Cloneable, Serializable {

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
    public XObject3 clone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object clone = ois.readObject();
            return (XObject3) clone;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "XObject2{" +
                "attribute='" + attribute + '\'' +
                ", ref=" + Arrays.toString(ref) +
                '}';
    }
}
