package com.nju.software.util;

import org.springframework.beans.BeanUtils;

public class MyBeanUtils {

	public static <T> T copy(Object source, T target){
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
}
