package com.lujian.casual.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class Gaps {

    /*
     * This example shows another HotSpot layout quirk.
     *
     * HotSpot rounds up the instance field block up to reference size.
     * That unfortunately yields the artificial gaps at the end of the class.
     *
     * See also:
     *    https://bugs.openjdk.java.net/browse/JDK-8024912
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        boolean a;
    }

    public static class B extends A {
        boolean b;
    }

    public static class C extends B {
        boolean c;
    }

}
