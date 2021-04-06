package com.lv.spring.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.spring.context.UserContext;
import com.lv.spring.entity.User;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.exceptioin.ApiException;
import com.lv.spring.repository.UserInfoRepository;
import com.lv.spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<String> list = userInfo.getFollow();
        Optional<String> f = list.stream().filter(p -> list.contains(username)).findFirst();
        if(f.isPresent()){
            throw new ApiException(ResultVOEnum.REPEAT_FORBIDDEN);
        }

        list.add(username);
        userInfo.setFollow(list);
        userInfo.setCountOfFollow(list.size());
        //修改被关注着列表
        UserInfo userBeFllowed = getInfo(username);
        List<String> list1 = userBeFllowed.getFollowers();
        list1.add(UserContext.getCurrentUserName());
        userBeFllowed.setFollowers(list1);
        userBeFllowed.setCountOfFollowers(list1.size());
        userInfoRepository.save(userInfo);
        userInfoRepository.save(userBeFllowed);
        return true;
    }

    @Override
    public boolean unstar(String username) {
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        //添加用户关注列表
        List<String> list = userInfo.getFollow();

        Optional<String> f = list.stream().filter(p -> list.contains(username)).findFirst();
        if(f.isPresent()){
            throw new ApiException(ResultVOEnum.USER_NOT_EXISTS);
        }
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
        UserInfo userInfo = getInfo(username);
        List<String> list;
        list = userInfo.getFollow();
        return list;
    }

    @Override
    public List findFllowers(String username) {
        UserInfo userInfo = getInfo(username);
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

    @Override
    public String saveSex(String sex) {
        if(sex == null){
            throw  new ApiException(ResultVOEnum.NOT_FOUND);
        }
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setSex(sex);
        userInfoRepository.save(userInfo);
        return sex;
    }

    @Override
    public String changeDescribe(String describe) {
        if(describe == null) {
            throw new ApiException(ResultVOEnum.NOT_FOUND);
        }
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setDescribe(describe);
        userInfoRepository.save(userInfo);
        return describe;
    }

    @Override
    public String changeBackGround(String url) {
        if( url == null){
            throw new ApiException(ResultVOEnum.NOT_FOUND);
        }
        UserInfo userInfo = getInfo(UserContext.getCurrentUserName());
        userInfo.setBack(url);
        userInfoRepository.save(userInfo);
        return url;
    }


}
