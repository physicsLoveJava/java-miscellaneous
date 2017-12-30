package com.lujian.casual.oom;

import java.util.ArrayList;
import java.util.List;

public class Heapoom {

    public static void main(String[] args) {

        class OxObject {}

        List<Object> list = new ArrayList<>();

        while(true) {
            list.add(new OxObject());
        }

    }

}
