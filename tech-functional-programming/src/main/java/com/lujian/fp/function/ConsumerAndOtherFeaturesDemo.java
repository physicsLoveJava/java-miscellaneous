package com.lujian.fp.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsumerAndOtherFeaturesDemo {

    private static Consumer<Object> out = System.out::println;

    public static void forEach(List<Integer> list, Consumer<Integer> each) {
        for (Integer val : list) {
            each.accept(val);
        }
    }

    public static List<Integer> map(List<Integer> list, Function<Integer, Integer> fun) {
        List<Integer> result = new ArrayList<>();
        for (Integer val : list) {
            result.add(fun.apply(val));
        }
        return result;
    }

    private static Predicate<Integer> gt50 = val -> val > 50;
    private static Predicate<Integer> lt100 = val -> val < 100;
    private static Predicate<Integer> gt50Andlt100 = gt50.and(lt100);

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        forEach(list, System.out::println);

        out.accept(map(list, val -> val + 3));

        int number = 100;
        Runnable runnable = () -> System.out.println(number);

        new Thread(runnable).start();

        Function<String, Integer> strToInt = Integer::parseInt;
        out.accept(strToInt.apply("123"));

        Function<Integer, Integer> intSupplier = Integer::new;
        System.out.println(intSupplier.apply(5));

        list.sort(Comparator.comparing(integer -> -1 * integer));
        System.out.println(list);

        Function<Integer, Integer> addFunc = x -> x + 1;
        Function<Integer, Integer> doubleToIntFunction = x -> x * 2;
        addFunc.andThen(doubleToIntFunction);
        addFunc.andThen(doubleToIntFunction);
    }

}
