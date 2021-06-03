package com.lv.spring;

import com.lv.spring.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.lv.spring.mapper")
public class WebStage implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(WebStage.class, args);
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 使拦截器生效
        registry.addInterceptor(new LoginInterceptor());
    }
}

//TODO
/*
1.收藏，点赞的取消
2.判断是否收藏，点赞
3.抽象评论模块
4.闲聊，答疑
5.等级，签到
6.贴吧
7.树洞

 */