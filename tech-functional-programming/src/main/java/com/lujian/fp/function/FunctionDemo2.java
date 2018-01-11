package com.lujian.fp.function;

public class FunctionDemo2 {

    public interface Function<T, U> {
        U apply(T arg);
    }

    public interface BinaryOperator extends Function<Integer, Function<Integer, Integer>> {

    }

    static Function<Function<Integer, Integer>,
            Function<Function<Integer, Integer>,
                    Function<Integer, Integer>>> compose =  f1 -> f2 -> arg -> f1.apply(f2.apply(arg));

    public static void main(String[] args) {

        BinaryOperator add = x -> y -> x + y;
        BinaryOperator mul = x -> y -> x * y;

        System.out.println(add.apply(5).apply(6));
        System.out.println(add.apply(5).apply(mul.apply(5).apply(6)));

        Function<Integer, Integer> f1 = x -> x + 3;
        Function<Integer, Integer> f2 = x -> x * x;
        System.out.println(compose.apply(f1).apply(f2).apply(5));

    }

}
