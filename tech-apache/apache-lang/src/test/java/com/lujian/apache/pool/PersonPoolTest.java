package com.lujian.apache.pool;

import org.junit.Test;

public class PersonPoolTest {

    PersonPool pool = new PersonPool(new PersonObjectFactory());

    @Test
    public void test() {
        try {
            Person person = pool.borrowObject();
            System.out.println(person);
            pool.returnObject(person);
            Person person1 = pool.borrowObject();
            System.out.println(person1);
            pool.returnObject(person1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            for (int i = 0; i < 10; i++) {
                Person person = pool.borrowObject();
                System.out.println(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}