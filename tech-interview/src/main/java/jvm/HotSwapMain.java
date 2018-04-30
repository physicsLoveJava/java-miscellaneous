package jvm;

import java.lang.reflect.Method;

public class HotSwapMain {

    /**
     * just change the Person Class Content, will make a little class hot swap
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            loadClassAndInvoke();
            Thread.sleep(5000);
        }
    }

    private static void loadClassAndInvoke() {
        AClassLoader loader = new AClassLoader();
        try {
            Class<?> clazz = loader.loadClass("jvm.Person");
            System.out.println(clazz);
            System.out.println(clazz.getClassLoader());
            Object p = clazz.newInstance();
            Method method = clazz.getMethod("say", null);
            method.invoke(p, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
