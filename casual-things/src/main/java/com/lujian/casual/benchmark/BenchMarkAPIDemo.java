package com.lujian.casual.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;

public class BenchMarkAPIDemo {

    @Benchmark
    public void measureName() {
    }

    @Benchmark
    public void functionBlank() {
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMarkAPIDemo.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
