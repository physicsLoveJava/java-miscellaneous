package com.lujian.casual.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BenchMarkList {

    static class Person {
        private String name;
        private String age;
        private String address;
        private String name1;
        private String name2;

        public Person(String name, String age, String address, String name1, String name2) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.name1 = name1;
            this.name2 = name2;
        }

        public static Person getDefault() {
            return new Person("a", "b", "c",
                    "d", "e");
        }
    }

    @Benchmark
    public int listWithIncrease(Blackhole blackhole) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(Person.getDefault());
        }
        Person[] array = list.toArray(new Person[0]);
        blackhole.consume(array);
        return array.length;
    }

    @Benchmark
    public int listWithoutIncrease(Blackhole blackhole) {
        List<Person> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(Person.getDefault());
        }
        Person[] array = list.toArray(new Person[0]);
        blackhole.consume(array);
        return array.length;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMarkList.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
