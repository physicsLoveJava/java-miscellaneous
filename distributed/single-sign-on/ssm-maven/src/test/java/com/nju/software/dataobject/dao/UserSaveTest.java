package com.nju.software.dataobject.dao;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.nju.software.dataobject.domain.UserDO;
import com.nju.software.service.UserService;
import com.nju.software.service.model.UserModel;
import com.nju.software.test.BaseTest;

public class UserSaveTest extends BaseTest{

	@Resource
	private UserDao userDao;
	
	@Resource
	private UserService userService;
	
	@Test
	@Transactional
	public void shouldInsertWithException(){
		System.out.println("shouldInsertWithException--------");
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++){
			threads[i] = new Thread(new SaveThread());
			threads[i].setUncaughtExceptionHandler(new DuplicateKeyHandler());
			threads[i].start();
		}
		
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<UserDO> userList = userDao.findAll();
		for(UserDO user : userList){
			System.out.println(user);
		}
		System.out.println("shouldInsertWithException--------");
	}
	
	class SaveThread implements Runnable{

		public void run() {
			System.out.println(Thread.currentThread().getId() + " save user start.");
			UserDO user = new UserDO();
			int id = userDao.getMaxId();
			user.setAge(20);
			user.setName("xx");
			user.setId(++id);
			userDao.save(user);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId() + " save user end.");
		}
		
	}
	
	class ServiceSaveThread implements Runnable{
		
		public void run() {
			System.out.println(Thread.currentThread().getId() + " save user start.");
			UserModel user = new UserModel();
			user.setAge(20);
			user.setName("xx");
			userService.save(user);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId() + " save user end.");
		}
		
	}
	
	class DuplicateKeyHandler implements UncaughtExceptionHandler{

		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("insert as expected throw exception:" + e.getClass().getName());
		}
		
	}
	
	@Test
	@Transactional
	public void shouldInsertAsExpectedWhenUseSychronized(){
		System.out.println("shouldInsertAsExpectedWhenUseSychronized--------");
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++){
			threads[i] = new Thread(new ServiceSaveThread());
			threads[i].start();
		}
		
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<UserDO> userList = userDao.findAll();
		for(UserDO user : userList){
			System.out.println(user);
		}
		System.out.println("shouldInsertAsExpectedWhenUseSychronized--------");
	}
	
}
