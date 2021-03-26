package com.lv.spring.service;

import com.lv.spring.entity.User;


public interface UserService {
     boolean UserRegister(User user);
     boolean UserLogin(User user);
     Integer UserPermission(String username);
}
