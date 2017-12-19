package com.nju.software.service;

import java.util.List;

import com.nju.software.biz.vo.UserVO;
import com.nju.software.service.model.UserModel;

public interface UserService {

	List<UserVO> findAll();

	boolean save(UserModel user);
}
