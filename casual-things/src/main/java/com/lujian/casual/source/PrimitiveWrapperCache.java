package com.lujian.casual.source;

public class PrimitiveWrapperCache {

    public static void main(String[] args) {

        //value of use the CharacterCache
        Character a1 = Character.valueOf('a');
        Character a2 = Character.valueOf('a');

        System.out.println(a1 == a2);

        Character a3 = 'a';
        Character a4 = 'a';

        System.out.println(a3 == a4);

        Character a5 = new Character('a');
        Character a6 = new Character('a');

        System.out.println(a5 == a6);

        //自动装箱 autobox int --> Integer
        Integer i1 = 4;
        Integer i2 = 4;

        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;

        System.out.println(i3 == i4);
    }

}
