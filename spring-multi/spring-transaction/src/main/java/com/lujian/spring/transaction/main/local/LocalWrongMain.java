package com.lujian.spring.transaction.main.local;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class LocalWrongMain extends AbstractMain {

    public LocalWrongMain() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.localTransactionWrong();
    }

    public static void main(String[] args) {
        new LocalWrongMain();
    }

}
