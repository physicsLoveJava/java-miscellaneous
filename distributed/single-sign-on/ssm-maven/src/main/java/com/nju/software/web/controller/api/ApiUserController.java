package com.nju.software.web.controller.api;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nju.software.service.UserService;
import com.nju.software.service.model.UserModel;
import com.nju.software.util.JsonResult;

@Controller
@RequestMapping("api/v1_0/users")
public class ApiUserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void json_users(
			HttpServletResponse response
			) throws IOException{
		JsonResult.toJson(response, userService.findAll());
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void json_user_PUT(
			@RequestBody UserModel user,
			HttpServletResponse response
			) throws IOException{
		boolean isSaved = userService.save(user);
		if(!isSaved){
			JsonResult.toJson(response, JsonResult.useDefault(false).build());
			return;
		}
		JsonResult.toJson(response, JsonResult.useDefault(true).build());
		return;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void json_user_GET(
			@PathVariable(value="id") Integer id,
			HttpServletResponse response
			) throws IOException{
		System.out.println(id);
		JsonResult.toJson(response, JsonResult.useDefault(true).build());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void json_user_DELETE(
			@PathVariable(value="id") Integer id,
			HttpServletResponse response
			) throws IOException{
		System.out.println(id);
		JsonResult.toJson(response, JsonResult.useDefault(true).build());
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST)
	public void json_user_POST(
			@PathVariable(value="id") Integer id,
			@RequestBody UserModel user,
			HttpServletResponse response
			) throws IOException{
		System.out.println(user);
		JsonResult.toJson(response, JsonResult.useDefault(true).build());
	}
}
