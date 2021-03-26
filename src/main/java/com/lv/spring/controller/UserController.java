package com.lv.spring.controller;

import com.lv.spring.annotation.PassToken;
import com.lv.spring.entity.User;
import com.lv.spring.service.UserService;
import com.lv.spring.utils.JwtUtil;
import com.lv.spring.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PassToken
    @PostMapping("/login")
    public ResultVO login(@RequestBody @Validated User user) {
        user.setUsername(user.getUsername().trim());
        boolean is_login = userService.UserLogin(user);
        String jwt =JwtUtil.generate(user.getUsername());
        Map<String, String> map = new HashMap<String,String>();
        map.put("token",jwt);
        return is_login?ResultVO.ok(map):ResultVO.fail();
    }

    @PassToken
    @PostMapping("/register")
    public ResultVO register(@RequestBody @Validated User user) {
        user.setUsername(user.getUsername().trim());
        boolean is_register =  userService.UserRegister(user);
        String jwt =JwtUtil.generate(user.getUsername());
        Map<String, String> map = new HashMap<String,String>();
        map.put("token",jwt);
        return is_register?ResultVO.ok(map):ResultVO.fail();
    }

    @GetMapping("/test")
    public ResultVO test() {
        return ResultVO.ok();
    }
}
