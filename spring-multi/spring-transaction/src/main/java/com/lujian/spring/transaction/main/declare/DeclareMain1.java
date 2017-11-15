package com.lujian.spring.transaction.main.declare;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class DeclareMain1 extends AbstractMain {

    public DeclareMain1() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.declareTransactionRequired();
    }

    public static void main(String[] args) {
        new DeclareMain1();
    }
}
