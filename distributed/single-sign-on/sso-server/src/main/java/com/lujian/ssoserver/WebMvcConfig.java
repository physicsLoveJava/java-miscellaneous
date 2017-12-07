package com.lujian.ssoserver;

import com.lujian.ssoserver.web.filter.LoginFilter;
import com.lujian.ssoserver.web.filter.VariableInjector;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    LoginFilter loginFilter;

    @Resource
    VariableInjector variableInjector;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        configure(registry, variableInjector);
        configure(registry, loginFilter);
        super.addInterceptors(registry);
    }

    private void configure(InterceptorRegistry registry, VariableInjector variableInjector) {
        registry.addInterceptor(variableInjector);
    }

    private void configure(InterceptorRegistry registry, LoginFilter loginFilter) {
        InterceptorRegistration registration = registry.addInterceptor(loginFilter);
        registration.excludePathPatterns("/test.do", "/login.do");
    }

}
