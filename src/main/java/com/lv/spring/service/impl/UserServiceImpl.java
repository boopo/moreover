package com.lv.spring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lv.spring.entity.User;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.mapper.UserMapper;
import com.lv.spring.repository.PostRepository;
import com.lv.spring.repository.UserInfoRepository;
import com.lv.spring.service.UserService;
import com.lv.spring.utils.Base64;
import com.lv.spring.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public boolean UserRegister(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).last("limit 1");
        Integer count = userMapper.selectCount(queryWrapper);
        if (count == 1) {
            return false;
        }
        String pwd = new String(user.getPassword());
        user.setPassword(MD5.encrypt(pwd));
        userMapper.insert(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        userInfo.setExp(0);
        userInfo.setNickname(user.getUsername());
        userInfo.setHead("https://pic.imgdb.cn/item/60b6ebf739f6859bc2cc14d2.jpg");
        userInfo.setStars(0);
        userInfo.setBack("https://p6.toutiaoimg.com/origin/pgc-image/e95a332a5b084d5e82d5b4e3e946895b");
        userInfo.setSex("未知");
        userInfo.setCountOfFollow(0);
        userInfo.setCountOfFollowers(0);
        List<String> list = new ArrayList<>();
        userInfo.setFollow(list);
        userInfo.setFollowers(list);
        userInfo.setCollection(list);
        userInfoRepository.save(userInfo);
        return true;
    }

    @Override
    public boolean UserLogin(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User realUser = userMapper.selectOne(queryWrapper);
        if (null == realUser) return false;
        String md5Pwd = MD5.encrypt(user.getPassword());
        String encryPwd = realUser.getPassword();
        return md5Pwd.equals(encryPwd);
    }

    @Override
    public Integer UserPermission(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User realUser = userMapper.selectOne(queryWrapper);
        if (null == realUser) return null;
        return realUser.getPermission();
    }


}
