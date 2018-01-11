package com.lujian.fp.function;

import java.util.*;
import java.util.function.Predicate;

public class PredicateDemo {

    static List<Integer> filter(List<Integer> collection, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Iterator<Integer> it = collection.listIterator(); it.hasNext();) {
            Integer val = it.next();
            if (predicate.test(val)) {
                result.add(val);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(filter(list, val -> val > 3));
    }

}
