package com.nju.software.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
public class BaseControllerTest extends BaseTest{

	@Resource
	protected WebApplicationContext wac;
	private static final String CHARSET = "UTF-8";
	protected static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";
	protected static final String API_BASE_URL = "/api/v1_0/";
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	protected MockMvc mockMvc;
	
	@Before
	public void setUpControllerRequestAndResponse(){
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		response.setCharacterEncoding(CHARSET);
	}
}
