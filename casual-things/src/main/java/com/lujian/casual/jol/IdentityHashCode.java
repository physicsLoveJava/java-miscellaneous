package com.lujian.casual.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class IdentityHashCode {

    /*
     * The example for identity hash code.
     *
     * The identity hash code, once computed, should stay the same.
     * HotSpot opts to store the hash code in the mark word as well.
     * You can clearly see the hash code bytes in the header once
     * it was computed.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Fresh object");
        out.println(layout.toPrintable());

        out.println("hashCode: " + Integer.toHexString(a.hashCode()));
        out.println();

        out.println("**** After identityHashCode()");
        out.println(layout.toPrintable());

        System.gc();

        out.println("**** After GC");
        out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}
