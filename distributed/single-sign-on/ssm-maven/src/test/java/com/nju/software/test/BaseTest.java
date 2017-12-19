package com.nju.software.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = {
		"classpath:root-context.xml", 
		"classpath:dao-context.xml", 
		"classpath:servlet-context.xml", 
		"classpath:mybatis-config.xml"})
@TransactionConfiguration(transactionManager = "txManager")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

}
