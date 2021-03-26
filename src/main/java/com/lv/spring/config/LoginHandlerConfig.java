package com.lv.spring.config;

import com.lv.spring.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class LoginHandlerConfig implements WebMvcConfigurer {



public class LoginHandlerConfig   {
//    @Bean
//    public LoginInterceptor login() {
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(login()).excludePathPatterns("/static/**").addPathPatterns("/**");
//    }
}
