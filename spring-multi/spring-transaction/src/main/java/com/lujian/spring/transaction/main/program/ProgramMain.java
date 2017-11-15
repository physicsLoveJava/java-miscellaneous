package com.lujian.spring.transaction.main.program;

import com.lujian.spring.transaction.main.AbstractMain;
import com.lujian.spring.transaction.service.UserService;

public class ProgramMain extends AbstractMain {

    public ProgramMain() {
        super();
    }

    @Override
    protected void play() {
        UserService userService = context.getBean(UserService.class);
        userService.programTransaction();
    }

    public static void main(String[] args) {
        new ProgramMain();
    }
}
