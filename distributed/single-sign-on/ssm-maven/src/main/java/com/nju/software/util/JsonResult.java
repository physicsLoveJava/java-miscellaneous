package com.nju.software.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class JsonResult {
	
	private static final String CHARSET = "UTF-8";
	private static String CONTENT_TYPE = "application/json;charset=utf-8";

	public static void toJson(HttpServletResponse response, Object object) throws IOException {

		response.addHeader("Content-Type", CONTENT_TYPE);
		response.setCharacterEncoding(CHARSET);
		PrintWriter pw = response.getWriter();
		pw.write(JSON.toJSONString(object));
		pw.flush();
		pw.close();
	}
	
	private final Map<String, Object> map = Maps.newLinkedHashMap();
	
	private JsonResult(){
	}

	public static JsonResult useDefault(boolean success) {
		JsonResult jr = new JsonResult();
		jr.add("success", success);
		jr.add("message", "");
		jr.add("data", Maps.newHashMap());
		return jr;
	}
	
	public static JsonResult useDefault(boolean success, String message) {
		JsonResult jr = new JsonResult();
		jr.add("success", success);
		jr.add("message", message);
		jr.add("data", Maps.newHashMap());
		return jr;
	}
	
	public static JsonResult useDefault(boolean success, String message, Object content) {
		JsonResult jr = new JsonResult();
		jr.add("success", success);
		jr.add("message", message);
		jr.add("data", content);
		return jr;
	}
	
	public JsonResult add(String key, Object val) {
		if( key == null){
			return this;
		}
		if( val == null){
			return this;
		}
		map.put(key, val);
		return this;
	}

	public ImmutableMap<Object, Object> build() {
		return ImmutableMap.builder().putAll(this.map).build();
	}

	@SuppressWarnings("unchecked")
	public JsonResult put(String key, Object obj) {
		try {
			Map<String, Object> map = (Map<String, Object>) this.map.get("data");
			map.put(key, obj);
			return this;
		} catch (ClassCastException e) {
			throw e;
		}
	}

	public static JSONObject parseJson(String body) {
		return (JSONObject) JSON.parse(body);
	}

	public static JsonMapResult useMap() {
		JsonMapResult jr = new JsonMapResult();
		return jr;
	}
	
	public static class JsonMapResult {
		private final Map<String, Object> map = Maps.newLinkedHashMap();
		
		public JsonMapResult put(String key, Object obj) {
			this.map.put(key, obj);
			return this;
		}
		
		public ImmutableMap<Object, Object> build() {
			return ImmutableMap.builder().putAll(this.map).build();
		}
		
	}

}
