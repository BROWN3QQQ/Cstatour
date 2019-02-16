package com.brown3qqq.cstatour.configuration;

import com.brown3qqq.cstatour.interceptor.AdminInterceptor;
import com.brown3qqq.cstatour.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//拦截器
@Configuration
public class WendaWepConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/update");

        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
    }
}
