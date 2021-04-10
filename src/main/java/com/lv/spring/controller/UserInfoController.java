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
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/userinfo/{username}")
    public ResultVO getUserInfo(@PathVariable String username){
        UserInfo userInfo = userInfoService.getInfo(username);
        return ResultVO.ok(userInfo);
    }

    @PutMapping("/nickname/{name}")
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

    @GetMapping("/follow/{username}")
    public ResultVO fllow(@PathVariable String username){
        List<String> list = userInfoService.findFllow(username);
        return ResultVO.ok(list);
    }

    @GetMapping("/followers/{username}")
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

    @PutMapping("/head")
    public ResultVO change(@RequestBody @Validated UserInfo userInfo  ) {
        userInfoService.changehead(userInfo.getHead());
        return ResultVO.ok();
    }

    @GetMapping("/head/{username}")
    public ResultVO gethead(@PathVariable String username){
        String head = userInfoService.getHead(username);
        return ResultVO.ok(head);
    }

    @PostMapping("/sex")
    public ResultVO changeInfo(@RequestBody UserInfo userInfo) {
        userInfoService.saveSex(userInfo.getSex());
        return ResultVO.ok();
    }

    @PostMapping("/describe")
    public ResultVO changeDesc(@RequestBody UserInfo userInfo) {
        userInfoService.changeDescribe(userInfo.getDescribe());
        return ResultVO.ok();
    }

    @PostMapping("/background")
    public ResultVO chageBack(@RequestBody UserInfo userInfo) {
        userInfoService.changeBackGround(userInfo.getBack());
        return ResultVO.ok();
    }

//    @PutMapping("/userinfo")
}
