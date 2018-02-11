package com.lujian.apache.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class PersonObjectFactory implements PooledObjectFactory<Person> {

    @Override
    public PooledObject<Person> makeObject() throws Exception {
        System.out.println("make object");
        return new DefaultPooledObject<>(createPerson());
    }

    private Person createPerson() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Person(System.currentTimeMillis() + "", (int) System.currentTimeMillis());
    }

    @Override
    public void destroyObject(PooledObject<Person> p) throws Exception {
        System.out.println("destroy object");
    }

    @Override
    public boolean validateObject(PooledObject<Person> p) {
        System.out.println("validate object");
        return true;
    }

    @Override
    public void activateObject(PooledObject<Person> p) throws Exception {
        System.out.println("active object");
    }

    @Override
    public void passivateObject(PooledObject<Person> p) throws Exception {
        System.out.println("passivate object");
    }
}
