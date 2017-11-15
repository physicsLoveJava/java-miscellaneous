package com.lujian.spring.transaction.main.local;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class LocalMain extends AbstractMain {

    public LocalMain () {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.localTransaction();
    }

    public static void main(String[] args) {
        new LocalMain();
    }

}
