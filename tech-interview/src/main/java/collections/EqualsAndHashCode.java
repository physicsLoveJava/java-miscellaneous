package collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EqualsAndHashCode {

    static class Person  {
        static int count = 0;
        String name;
        Integer age;
        int id;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
            this.id = count++;
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
//            int result = name != null ? name.hashCode() : 0;
//            result = 31 * result + (age != null ? age.hashCode() : 0);
//            return result;
            return this.id;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    static class Test1 {
        //violate set contract every element is equals
        //can't use hash to reduce object equality
        public static void main(String[] args) {
            HashSet<Person> set = new HashSet<>(10);
            for (int i = 0; i < 10; i++) {
                set.add(new Person("person" + i, i));
            }
            for (int i = 0; i < 10; i++) {
                set.add(new Person("person", 15));
            }
            Person p1 = new Person("person", 15);
            Person p2 = new Person("person", 15);
            System.out.println(p1 + " hascode: " + p1.hashCode());
            System.out.println(p2 + " hascode: " + p2.hashCode());
            System.out.println("person1 equals person2 " + p1.equals(p2));
            for (Person person : set) {
                System.out.println(person);
            }
        }
    }

    static class Test2 {
        public static void main(String[] args) {
            Map<Person, Person> map = new HashMap<>();
            Person p1 = new Person("person", 15);
            Person p2 = new Person("person", 15);
            map.put(p1, p1);
            System.out.println(map.get(p2));
        }
    }
}
