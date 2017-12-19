package com.nju.software.service.converter;

import com.nju.software.biz.vo.UserVO;
import com.nju.software.dataobject.domain.UserDO;
import com.nju.software.util.MyBeanUtils;

public class UserConverter {

	UserVO converFromDOToVO(UserDO userDO){
		return MyBeanUtils.copy(userDO, new UserVO());
	}
}
