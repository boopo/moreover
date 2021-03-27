package com.lv.spring.service;

import com.lv.spring.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
     UserInfo getInfo(String username);
     boolean changeNickName(String name);
     boolean star(String username);
     boolean unstar(String username);
     List findFllow(String username);
     List findFllowers(String username);
     String getExp(Integer exp);
     String changehead(String base64);
}