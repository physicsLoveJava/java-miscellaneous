package com.lujian.ssoserver.web.controller;

import com.lujian.ssoserver.service.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    RedisService redisService;

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    public String test() {
//        redisService.ping();
        return "test";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        redisService.login(request.getSession().getId());
        return "redirect:/index.do";
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        redisService.logout(request.getSession().getId());
        return "redirect:/test.do";
    }

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String login_GET(HttpServletRequest request) {
        redisService.ping(request.getSession().getId());
        return "index";
    }

}
