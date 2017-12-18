package com.lujian.ssoserver.web.filter;

import com.lujian.ssoserver.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends HandlerInterceptorAdapter {

    @Resource
    RedisService redisService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = request.getSession().getId();
        if(redisService.hasLogin(id)) {
            return true;
        }else {
            response.sendRedirect("/test.do");
            return true;
        }
    }

}
