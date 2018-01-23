package com.lujian.apache.pool;


import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class PersonPool extends GenericObjectPool<Person> {
    public PersonPool(PooledObjectFactory<Person> factory) {
        super(factory);
    }

    public PersonPool(PooledObjectFactory<Person> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
}
