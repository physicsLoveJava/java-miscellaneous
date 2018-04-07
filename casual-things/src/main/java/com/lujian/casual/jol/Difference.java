package com.lujian.casual.jol;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.System.out;

public class Difference {

     /*
     * This is the example how would one use the GraphLayout differences to
     * figure out the object graph changes.
     *
     * Here, we have the ConcurrentHashMap, and three measurements:
     *   1) The initial CHM that has no backing storage;
     *   2) After adding the first KV pair, when both KV pair is allocated,
     *      and the backing storage is allocated;
     *   3) After adding the second KV pair.
     *
     * An API for subtracting the GraphLayouts helps to show the difference
     * between the snapshots. Note that differences are based on object
     * addresses, so if GC moves under our feet, the difference is unreliable.
     * It is a good idea to keep the allocations at minimum between the snapshots.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());

        Map<String, String> chm = new ConcurrentHashMap<String, String>();

        GraphLayout gl1 = GraphLayout.parseInstance(chm);

        chm.put("Foo", "Bar");
        GraphLayout gl2 = GraphLayout.parseInstance(chm);

        chm.put("Foo2", "Bar2");
        GraphLayout gl3 = GraphLayout.parseInstance(chm);

        out.println(gl2.subtract(gl1).toPrintable());
        out.println(gl3.subtract(gl2).toPrintable());
    }

}
