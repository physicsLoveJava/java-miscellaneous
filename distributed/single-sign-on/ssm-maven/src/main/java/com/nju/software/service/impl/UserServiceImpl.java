package com.nju.software.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nju.software.biz.vo.UserVO;
import com.nju.software.dataobject.dao.UserDao;
import com.nju.software.dataobject.domain.UserDO;
import com.nju.software.service.UserService;
import com.nju.software.service.model.UserModel;
import com.nju.software.util.MyBeanUtils;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public synchronized boolean save(UserModel user) {
		int id = userDao.getMaxId();
		user.setId(++id);
		return userDao.save(MyBeanUtils.copy(user, new UserDO()));
	}

	public List<UserVO> findAll() {
		List<UserDO> userList = userDao.findAll();
		List<UserVO> resultList = Lists.newArrayList();
		for(UserDO user : userList){
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);
			resultList.add(userVO);
		}
		return resultList;
	}

}
