package collections;

import java.util.TreeMap;

public class TreeSetOrMapWithoutComparableObject {

    static class Person {}

    public static void main(String[] args) {

//        TreeSet<Person> set = new TreeSet<>();
//
//        set.add(new Person());
//        set.add(new Person());
//        set.add(new Person());
//        set.add(new Person());
//
//        System.out.println(set);


        TreeMap<Person, Integer> map = new TreeMap<>();

        map.put(new Person(), 1);
        map.put(new Person(), 1);
        map.put(new Person(), 1);
        map.put(new Person(), 1);

        System.out.println(map);
    }

}
