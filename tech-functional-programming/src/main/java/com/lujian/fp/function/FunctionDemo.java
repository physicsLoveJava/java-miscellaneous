package com.lujian.fp.function;

public class FunctionDemo {

    public interface Function<T, U> {
        U apply(T arg);
    }

    static Function<Integer, Integer> compose(Function<Integer, Integer> f1,
                                      Function<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    public static void main(String[] args) {

        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        System.out.println(triple.apply(square.apply(5)));
        System.out.println(compose(triple, square).apply(5));
        System.out.println(compose(square, triple).apply(5));
    }

}
