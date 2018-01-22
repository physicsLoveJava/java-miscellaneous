package com.lujian.fp.stream;

import java.util.*;

import static java.util.stream.Collectors.*;

public class StreamDemo {

    static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public static Integer sum(Integer a, Integer b) {
            if(a == null || b == null) {
                throw new RuntimeException();
            }
            return a + b;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("aa", 10),
                new Person("ab", 12),
                new Person("ac", 13),
                new Person("ad", 14)
        );

        //collect
        Map<String, List<Person>> nameMap = personList.stream().collect(groupingBy(Person::getName));
        System.out.println(nameMap);

        //cal max
        Comparator<Person> ageComp = Comparator.comparingInt(Person::getAge);
        Optional<Person> maxAge = personList.stream().collect(maxBy(ageComp));
        maxAge.ifPresent(person -> System.out.println(person.getAge()));

        //sum age
        Integer sumAge = personList.stream().collect(summingInt(Person::getAge));
        System.out.println(sumAge);

        //average age
        Double avgAge = personList.stream().collect(averagingInt(Person::getAge));
        System.out.println(avgAge);

        //statistics data
        IntSummaryStatistics statistics = personList.stream().collect(summarizingInt(Person::getAge));
        System.out.println(statistics);

        //connection string
        String ageJoin = personList.stream().map(Person::getName).collect(joining("-"));
        System.out.println(ageJoin);

        //connection person
        String personJoin = personList.stream().map(Person::toString).collect(joining("-"));
        System.out.println(personJoin);

        //reducing age
        Integer sumAgeReduce = personList.stream().collect(reducing(0, Person::getAge, (i, j) -> i + j));
        System.out.println(sumAgeReduce);

        //reducing use integer age
        int xxAge = personList.stream().collect(reducing(0, Person::getAge, Integer::sum));
        System.out.println(xxAge);

        //several methods to cal
        Optional<Integer> sumAgex = personList.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println(sumAgex);

        int sumAgeY = personList.stream().mapToInt(Person::getAge).sum();
        System.out.println(sumAgeY);

        //groupBy
        Map<Integer, List<Person>> ageSplitMap = personList.stream().collect(groupingBy(person -> person.getAge() > 12 ? 1 : 0));
        System.out.println(ageSplitMap);

        Map<Integer, Map<Boolean, List<Person>>> ageNameSplitMap = personList.stream().collect(groupingBy(person -> person.getAge() > 12 ? 1 : 0, groupingBy(person ->
                person.getName().equals("aa"))));
        System.out.println(ageNameSplitMap);

        Map<Integer, Long> ageCountingMap = personList.stream().collect(groupingBy(person -> person.getAge() > 12 ? 1 : 0, counting()));
        System.out.println(ageCountingMap);

        Map<Integer, Optional<Person>> ageNameComparingMap = personList.stream().collect(groupingBy(person -> person.getAge() > 12 ? 1 : 0, maxBy(Comparator.comparing(Person::getName))));
        System.out.println(ageNameComparingMap);

    }

}
