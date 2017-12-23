package com.lujian.spring.transaction.service.impl;

import com.lujian.spring.transaction.data.dao.UserDao;
import com.lujian.spring.transaction.data.entity.User;
import com.lujian.spring.transaction.service.UserxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserxServiceImpl implements UserxService {

    @Resource
    UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionalServiceMethodRequiresNew() {
        userDao.update("update User set name = 'aaa-requires-new' where id= 2");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void transactionalServiceMethodSupports() {
        User user = userDao.findById(2);
        System.out.println(user);
    }


}
