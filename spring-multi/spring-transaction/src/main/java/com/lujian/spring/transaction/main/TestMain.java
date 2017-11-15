package com.lujian.spring.transaction.main;

import com.lujian.spring.transaction.data.dao.UserDao;
import com.lujian.spring.transaction.data.entity.User;

public class TestMain extends AbstractMain {

    public TestMain() {
        super();
    }

    @Override
    protected void play() {
        UserDao userDao = context.getBean(UserDao.class);
        User user = userDao.findById(1);
        System.out.println(user);
    }

    public static void main(String[] args) {
        new TestMain();
    }
}
