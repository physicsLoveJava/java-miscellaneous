package collections;

import java.util.TreeSet;

public class TreeSetWithObjectOverwrite {

    static class Person implements Comparable<Person> {

        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Person o) {
            if(o == null) {
                return 1;
            }
            return age.compareTo(o.age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            return age != null ? age.equals(person.age) : person.age == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (age != null ? age.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {

        TreeSet<Person> set = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new Person("a" + i, i));
        }

        for (int i = 5; i < 15; i++) {
            set.add(new Person("a" + i, i));
        }

        System.out.println(set);
    }

}
