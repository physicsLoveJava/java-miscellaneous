package com.lujian.ssoserver.web.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VariableInjector extends HandlerInterceptorAdapter {

    @Value("${server.display-name}")
    String serverName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("serverName", serverName);
        return super.preHandle(request, response, handler);
    }
}
