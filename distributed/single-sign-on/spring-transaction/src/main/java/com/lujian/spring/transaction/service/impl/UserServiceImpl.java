package com.lujian.spring.transaction.service.impl;

import com.lujian.spring.transaction.annotation.Right;
import com.lujian.spring.transaction.annotation.Wrong;
import com.lujian.spring.transaction.data.dao.UserDao;
import com.lujian.spring.transaction.service.UserService;
import com.lujian.spring.transaction.service.UserxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private DataSource dataSource;

    @Resource
    private UserDao userDao;

    @Resource
    private UserxService userxService;

    @Right
    @Override
    public void localTransaction() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            //auto commit set false, make all of the changes into single unit of work
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("update User set name = 'aaa' where id= 1");
            statement.executeUpdate("update User set name = 'bbb' where id= 2");
            connection.commit();
        } catch (Exception e) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Wrong
    @Override
    public void localTransactionWrong() {
        userDao.jdbcUpdateWithAutoCommit("update User set name = 'aaa1' where id= 1");
        //if failed here, can't make it atomic because of the auto commit settings
        userDao.jdbcUpdateWithAutoCommit("update User set name = 'bbb1' where id= 2");
    }

    @Right
    @Override
    public void programTransaction() {
        userDao.programTransaction(
                "update User set name = 'aaa2' where id= 1",
                "update User set name = 'bbb2' where id= 2");
    }

    public void plainServiceMethod() {

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionalServiceMethod() {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void declareTransactionRequired() {
        userDao.update("update User set name = 'aaa2' where id= 1");
        int i = 1 / 0;
        userDao.update("update User set name = 'bbb2' where id= 2");
    }

    @Override
    public void declareTransactionNested() {

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void declareTransactionMandatory() {
        plainServiceMethod();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void declareTransactionSupports() {
        userDao.update("update User set name = 'transaction supports ' where id= 2");
        userxService.transactionalServiceMethodSupports();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void declareTransactionRequiresNew() {
        userDao.update("update User set name = 'aaa2' where id= 1");
        userxService.transactionalServiceMethodRequiresNew();
        int i = 1 / 0;
    }

    @Override
    public void declareTransactionNotSupported() {

    }

    @Override
    public void declareTransactionNever() {

    }

}
