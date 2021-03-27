package com.lv.spring.service.impl;

import com.lv.spring.context.UserContext;
import com.lv.spring.entity.User;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.repository.UserInfoRepository;
import com.lv.spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UserInfo getInfo(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
        return userInfo;
    }

    @Override
    public boolean changeNickName(String name) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setNickname(name);
        userInfoRepository.save(userInfo);
        return true;
    }

    @Override
    public boolean star(String username) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        //添加用户关注列表
        List<String> list = new ArrayList<>();
        list = userInfo.getFollow();
        list.add(username);
        userInfo.setFollow(list);
        //修改被关注着列表
        UserInfo userBeFllowed = getInfo(username);
        List<String> list1 = new ArrayList<>();
        list1 = userBeFllowed.getFollowers();
        list1.add(UserContext.getCurrentUserName());
        userBeFllowed.setFollowers(list1);
        userInfoRepository.save(userInfo);
        userInfoRepository.save(userBeFllowed);
        return true;
    }

    @Override
    public boolean unstar(String username) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        //添加用户关注列表
        List<String> list = new ArrayList<>();
        list = userInfo.getFollow();
        list.remove(username);
        userInfo.setFollow(list);
        //修改被关注着列表
        UserInfo userBeFllowed = getInfo(username);
        List<String> list1 = new ArrayList<>();
        list1 = userBeFllowed.getFollowers();
        list1.remove(UserContext.getCurrentUserName());
        userBeFllowed.setFollowers(list1);
        userInfoRepository.save(userInfo);
        userInfoRepository.save(userBeFllowed);
        return true;
    }

    @Override
    public List findFllow(String username) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        List<String> list;
        list = userInfo.getFollow();
        return list;
    }

    @Override
    public List findFllowers(String username) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        List<String> list;
        list = userInfo.getFollowers();
        return list;
    }

    @Override
    public String getExp(Integer exp) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setExp(userInfo.getExp()+exp);
        userInfoRepository.save(userInfo);
        return userInfo.getExp().toString();
    }

    @Override
    public String changehead(String base64) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setHead(base64);
        userInfoRepository.save(userInfo);
        return userInfo.getHead();
    }
}
