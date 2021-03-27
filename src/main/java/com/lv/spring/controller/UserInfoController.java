package com.lv.spring.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.spring.context.UserContext;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.service.UserInfoService;
import com.lv.spring.vo.ResultVO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/userinfo/{username}")
    public ResultVO getUserInfo(@PathVariable String username){
        UserInfo userInfo = userInfoService.getInfo(username);
        return ResultVO.ok(userInfo);
    }

    @GetMapping("/nickname/{name}")
    public ResultVO updateNickName(@PathVariable String name) {
        return userInfoService.changeNickName(name)?ResultVO.ok():ResultVO.fail();
    }

    @PutMapping("/star/{username}")
    public ResultVO star(@PathVariable String username) {
        return userInfoService.star(username)?ResultVO.ok():ResultVO.fail();
    }

    @DeleteMapping("/unstar/{username}")
    public ResultVO unstar(@PathVariable String username) {
        return userInfoService.unstar(username)?ResultVO.ok():ResultVO.fail();
    }

    @GetMapping("/fllow/{username}")
    public ResultVO fllow(@PathVariable String username){
        List<String> list = userInfoService.findFllow(username);
        return ResultVO.ok(list);
    }

    @GetMapping("/fllowers/{username}")
    public ResultVO fllowers(@PathVariable String username){
        List<String> list = userInfoService.findFllowers(username);
        return ResultVO.ok(list);
    }

    @PutMapping("/exp/{exp}")
    public ResultVO exp(@PathVariable Integer exp){
        Map<String,String> map = new HashMap<>();
        String ex = userInfoService.getExp(exp);
        map.put("exp",ex);
        return ResultVO.ok(map);
    }

    @PutMapping("/userinfo")
    public ResultVO change(@RequestBody @Validated UserInfo userInfo  ) {
        userInfoService.changehead(userInfo.getHead());
        return ResultVO.ok();
    }
}
