package com.lujian.spring.transaction.main.declare;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class DeclareMainMandatory1 extends AbstractMain {

    public DeclareMainMandatory1() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.declareTransactionMandatory();
    }

    public static void main(String[] args) {
        new DeclareMainMandatory1();
    }
}
