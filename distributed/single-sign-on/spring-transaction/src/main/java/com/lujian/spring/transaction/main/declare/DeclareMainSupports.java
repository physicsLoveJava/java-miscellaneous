package com.lujian.spring.transaction.main.declare;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class DeclareMainSupports extends AbstractMain {

    public DeclareMainSupports() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.declareTransactionSupports();
    }

    public static void main(String[] args) {
        new DeclareMainSupports();
    }
}
