package com.nju.software.util;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

	public static byte[] toJsonBytes(Object obj){
		return JSON.toJSONBytes(obj);
	}
	
}
