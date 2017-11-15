package com.lujian.spring.transaction.main.declare;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class DeclareMainRequiresNew extends AbstractMain {

    public DeclareMainRequiresNew() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.declareTransactionRequiresNew();
    }

    public static void main(String[] args) {
        new DeclareMainRequiresNew();
    }
}
