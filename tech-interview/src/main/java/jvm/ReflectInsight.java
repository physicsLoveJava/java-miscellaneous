package jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectInsight {

    public static void main(String[] args) {

        TestX testX = new TestX();
        try {
            Method test = TestX.class.getDeclaredMethod("test", null);
            test.invoke(testX);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    static class TestX {
        public void test() {
            System.out.println("test method");
        }
    }

}
