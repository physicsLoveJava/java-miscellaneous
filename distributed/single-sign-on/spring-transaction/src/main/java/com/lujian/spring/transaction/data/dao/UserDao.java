package com.lujian.spring.transaction.data.dao;

import com.lujian.spring.transaction.data.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDao extends HibernateDaoSupport {

    @Resource
    private DataSource dataSource;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    public void setSessionFactoryX(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void jdbcUpdateWithAutoCommit(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
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

    public void update(String hql) {
        getHibernateTemplate().bulkUpdate(hql);
    }

    public void programTransaction(final String... sql) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    for (int i = 0; i < sql.length; i++) {
                        update(sql[i]);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    status.setRollbackOnly();
                }
                return null;
            }
        });
    }

    public User findById(int id) {
        List list = getHibernateTemplate().find("from User where id=?", id);
        return list.isEmpty() ? null : (User) list.get(0);
    }
}
