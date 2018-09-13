package com.lujian.tech.libs.guava.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Arrays;

public class SplitterAndJoiner {

    public static void main(String[] args) {

        //splitter
        Splitter aSplitter = Splitter.on("#");
        String sequence = " a#b##c#d### ";
        System.out.println(aSplitter.split(sequence));
        System.out.println(aSplitter.omitEmptyStrings().split(sequence));
        System.out.println(aSplitter.trimResults().split(sequence));

        //joiner
        Joiner joiner = Joiner.on("#");
        System.out.println(joiner.skipNulls().join(Arrays.asList("a", "b", "c", " ", "", null)));
        System.out.println(joiner.useForNull("default").join(Arrays.asList("a", "b", "c", " ", "", null)));

    }

}
