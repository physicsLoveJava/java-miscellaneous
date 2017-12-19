package com.nju.software.dataobject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nju.software.dataobject.annotation.MybatisRepo;
import com.nju.software.dataobject.domain.UserDO;
import com.nju.software.service.model.UserModel;

@MybatisRepo
public interface UserDao {

	boolean save(@Param("user")UserDO userDO);
	
	@Select("select count(*) from user")
	int getMaxId();
	
	@Select("select * from user where id = #{id}")
	UserDO findById(@Param("id") Integer id);
	
	@Select("select * from user where name = #{name}")
	UserModel findByName(@Param("name") String name);
	
	@Select("select * from user")
	List<UserDO> findAll();
}
