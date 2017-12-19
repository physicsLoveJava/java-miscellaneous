package com.nju.software.web.controller.api;

import static com.nju.software.util.JsonUtils.toJsonBytes;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.nju.software.service.model.UserModel;
import com.nju.software.test.BaseControllerTest;

public class ApiUserControllerTest extends BaseControllerTest{

	private static final String USERS_GET = API_BASE_URL + "users";
	private static final String USERS_USER_PUT = API_BASE_URL + "users";
//	private static final String USERS_USER_POST = null;
//	private static final String USERS_USER_DELETE = null;
//	private static final String USERS_USER_GET = null;
	private UserModel user;
	
	@Before
	public void setUp(){
		user = new UserModel();
		user.setAge(20);
		user.setId(6);
		user.setName("xx");
	}
	
	@Test
	public void testJson_users() {
		try {
			mockMvc.perform(get(USERS_GET).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
				.andExpect(jsonPath("$").value(Matchers.hasSize(5)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJson_user_PUT() {
		try {
			mockMvc.perform(put(USERS_USER_PUT).contentType(MediaType.APPLICATION_JSON).content(toJsonBytes(user)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
				.andExpect(jsonPath("$.success").value(Matchers.equalTo(true)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testJson_user_GET() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testJson_user_DELETE() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testJson_user_POST() {
		fail("Not yet implemented");
	}

}
