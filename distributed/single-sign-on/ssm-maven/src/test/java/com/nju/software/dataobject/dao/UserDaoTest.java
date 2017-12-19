package com.nju.software.dataobject.dao;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.nju.software.dataobject.domain.UserDO;
import com.nju.software.service.model.UserModel;
import com.nju.software.test.BaseTest;

@Transactional
public class UserDaoTest extends BaseTest{

	@Resource
	private UserDao userDao;
	
	private UserDO user;
	
	@Before
	public void setUp(){
		user = new UserDO();
		user.setAge(20);
		user.setId(11);
		user.setName("xx");
	}
	
	@Test
	public void testSave() {
		boolean isSave = userDao.save(user);
		System.out.println(isSave);
	}
	
	@Test
	public void testGetId(){
		int id = userDao.getMaxId();
		System.out.println(id);
	}
	
	@Test
	public void testFindById(){
		user = new UserDO();
		user.setAge(20);
		user.setId(12);
		user.setName("xx");
		userDao.save(user);
		UserDO user = userDao.findById(2);
		
		System.out.println(user);
	}
	
	@Test
	public void testFindByName(){
		user = new UserDO();
		user.setAge(20);
		user.setId(13);
		user.setName("xx");
		userDao.save(user);
		UserModel userModel = userDao.findByName("xx");
		System.out.println(userModel);
	}

}
