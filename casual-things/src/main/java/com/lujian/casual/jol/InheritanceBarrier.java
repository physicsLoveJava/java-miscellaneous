package com.lujian.casual.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class InheritanceBarrier {

     /*
     * This example shows the HotSpot field layout quirk.
     * (Works best with 64-bit VMs)
     *
     * Even though we have the alignment gap before A.a field, HotSpot
     * does not claim it, because it does not track the gaps in the
     * already laid out superclasses. This yields the virtual
     * "inheritance barrier" between super- and sub-class fields blocks.
     *
     * See also:
     *    https://bugs.openjdk.java.net/browse/JDK-8024913
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        long a;
    }

    public static class B extends A {
        long b;
    }

    public static class C extends B {
        long c;
        int d;
    }

}
