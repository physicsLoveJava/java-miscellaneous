package com.lujian.casual.jol;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;

import static java.lang.System.out;

public class Arrays {

    /*
     * This example shows the array layout quirks.
     *
     * If you run it with parallel GC, you might notice that
     * fresh object elements are laid out after the array in
     * the forward order, but after GC then can be rearranged
     * in the reverse order. This is because GC records the
     * to-be-promoted objects on the stack.
     *
     * See also:
     *   https://bugs.openjdk.java.net/browse/JDK-8024394
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());

        PrintWriter pw = new PrintWriter(out, true);

        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Integer(i);
        }

        String last = null;
        for (int c = 0; c < 100; c++) {
            String current = GraphLayout.parseInstance((Object) arr).toPrintable();

            if (last == null || !last.equalsIgnoreCase(current)) {
                pw.println(current);
                last = current;
            }

            System.gc();
        }

        pw.close();
    }

}
